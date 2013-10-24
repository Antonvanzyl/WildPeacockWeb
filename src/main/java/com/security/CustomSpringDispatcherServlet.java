/**
 * File: CustomSpringDispatcherServlet.java Date: 11 Jan 2013 Author: Anton Van Zyl
 */
package com.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import com.util.Copyright;


/**
 * @author Anton Van Zyl
 * 
 */
public class CustomSpringDispatcherServlet extends DispatcherServlet {
	
    private static final Logger logger = LoggerFactory.getLogger( CustomSpringDispatcherServlet.class );

    private static final long serialVersionUID = -23082510463767107L;

    private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

    /**
     * Process the actual dispatching to the handler. This handler was customized to suit the nojs and normal flow. A
     * special parameter in the session will indicate the movement
     * <p>
     * The handler will be obtained by applying the servlet's HandlerMappings in order. The HandlerAdapter will be
     * obtained by querying the servlet's installed HandlerAdapters to find the first that supports the handler class.
     * <p>
     * All HTTP methods are handled by this method. It's up to HandlerAdapters or handlers themselves to decide which
     * methods are acceptable.
     * 
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @throws Exception
     *             in case of any kind of processing failure
     */
    @Override
    protected void doDispatch( HttpServletRequest request, HttpServletResponse response ) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        int interceptorIndex = -1;

        try {
            ModelAndView mv;
            boolean errorView = false;

            try {
                processedRequest = checkMultipart( request );

                // Determine handler for the current request.
                mappedHandler = getHandler( processedRequest );
                if ( mappedHandler == null || mappedHandler.getHandler() == null ) {
                    noHandlerFound( processedRequest, response );
                    return;
                }

                // Determine handler adapter for the current request.
                HandlerAdapter ha = getHandlerAdapter( mappedHandler.getHandler() );

                // Process last-modified header, if supported by the handler.
                String method = request.getMethod();
                boolean isGet = "GET".equals( method );
                if ( isGet || "HEAD".equals( method ) ) {
                    long lastModified = ha.getLastModified( request, mappedHandler.getHandler() );
                    if ( logger.isDebugEnabled() ) {
                        String requestUri = urlPathHelper.getRequestUri( request );
                        logger.debug( "Last-Modified value for [" + requestUri + "] is: " + lastModified );
                    }
                    if ( new ServletWebRequest( request, response ).checkNotModified( lastModified ) && isGet ) {
                        return;
                    }
                }

                // Apply preHandle methods of registered interceptors.
                HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
                if ( interceptors != null ) {
                    for ( int i = 0; i < interceptors.length; i++ ) {
                        HandlerInterceptor interceptor = interceptors[i];
                        if ( !interceptor.preHandle( processedRequest, response, mappedHandler.getHandler() ) ) {
                            triggerAfterCompletion( mappedHandler, interceptorIndex, processedRequest, response, null );
                            return;
                        }
                        interceptorIndex = i;
                    }
                }

                // Actually invoke the handler.
                mv = ha.handle( processedRequest, response, mappedHandler.getHandler() );

                // Do we need view name translation?
                if ( mv != null && !mv.hasView() ) {

                    mv.setViewName( getDefaultViewName( request ) );
                }

                if ( mv != null ) {
                    // This is the code modified by capitec to use nojs param
                    String viewName = mv.getViewName();
                    if (!Copyright.available) {
                    	mv.setViewName("admin/unpaid");
            		}
                    
                    if ( StringUtils.startsWith( viewName, UrlBasedViewResolver.REDIRECT_URL_PREFIX ) == false
                            && StringUtils.startsWith( viewName, UrlBasedViewResolver.FORWARD_URL_PREFIX ) == false ) {
                        logger.info( "logClient: [Logger=JSPLogger;CurrentPage={};]", viewName );
                    } else {
                        logger.info( "logClient: [Logger=RequestLogger;request={};]", viewName );
                    }
                    
                    
                    
                }
                // Apply postHandle methods of registered interceptors.
                if ( interceptors != null ) {
                    for ( int i = interceptors.length - 1; i >= 0; i-- ) {
                        HandlerInterceptor interceptor = interceptors[i];
                        interceptor.postHandle( processedRequest, response, mappedHandler.getHandler(), mv );
                    }
                }
            } catch ( ModelAndViewDefiningException ex ) {
                logger.debug( "ModelAndViewDefiningException encountered", ex );
                mv = ex.getModelAndView();
            } catch ( Exception ex ) {
                Object handler = ( mappedHandler != null ? mappedHandler.getHandler() : null );
                mv = processHandlerException( processedRequest, response, handler, ex );
                errorView = ( mv != null );
            }

            // Did the handler return a view to render?
            if ( mv != null && !mv.wasCleared() ) {
                render( mv, processedRequest, response );
                if ( errorView ) {
                    WebUtils.clearErrorRequestAttributes( request );
                }
            } else {
                if ( logger.isDebugEnabled() ) {
                    logger.debug( "Null ModelAndView returned to DispatcherServlet with name '" + getServletName()
                            + "': assuming HandlerAdapter completed request handling" );
                }
            }

            // Trigger after-completion for successful outcome.
            triggerAfterCompletion( mappedHandler, interceptorIndex, processedRequest, response, null );
        }

        catch ( Exception ex ) {
            // Trigger after-completion for thrown exception.
            triggerAfterCompletion( mappedHandler, interceptorIndex, processedRequest, response, ex );
            throw ex;
        } catch ( Error err ) {
            ServletException ex = new NestedServletException( "Handler processing failed", err );
            // Trigger after-completion for thrown exception.
            triggerAfterCompletion( mappedHandler, interceptorIndex, processedRequest, response, ex );
            throw ex;
        }

        finally {
            // Clean up any resources used by a multipart request.
            if ( processedRequest != request ) {
                cleanupMultipart( processedRequest );
            }
        }
    }

    /**
     * Trigger afterCompletion callbacks on the mapped HandlerInterceptors. Will just invoke afterCompletion for all
     * interceptors whose preHandle invocation has successfully completed and returned true.
     * 
     * @param mappedHandler
     *            the mapped HandlerExecutionChain
     * @param interceptorIndex
     *            index of last interceptor that successfully completed
     * @param ex
     *            Exception thrown on handler execution, or <code>null</code> if none
     * @see HandlerInterceptor#afterCompletion
     */
    private void triggerAfterCompletion( HandlerExecutionChain mappedHandler, int interceptorIndex,
            HttpServletRequest request, HttpServletResponse response, Exception ex ) throws Exception {

        // Apply afterCompletion methods of registered interceptors.
        if ( mappedHandler != null ) {
            HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
            if ( interceptors != null ) {
                for ( int i = interceptorIndex; i >= 0; i-- ) {
                    HandlerInterceptor interceptor = interceptors[i];
                    try {
                        interceptor.afterCompletion( request, response, mappedHandler.getHandler(), ex );
                    } catch ( Throwable ex2 ) {
                        logger.error( "HandlerInterceptor.afterCompletion threw exception", ex2 );
                    }
                }
            }
        }
    }

}
