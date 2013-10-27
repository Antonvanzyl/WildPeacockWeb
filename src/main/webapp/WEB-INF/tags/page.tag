<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" description="This is the tag to generate the page" pageEncoding="ISO-8859-1"%>
<%@attribute name="firstContent" required="false" description="content in the white space" %>
<%@attribute name="secondContent" required="false" description="content in the grey space" %>
<%@attribute name="pageHeader" required="false" description="Main page header"%>
<%@attribute name="subtitle" required="false"%>
<%@attribute name="pageCSS" required="false" %>
<%@attribute name="slogan" required="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="imagetoolbar" content="no" />
	
		<title>.|.. Wild Peacock Products: RETAIL HOME ..|.</title>
	
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" >
		<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" type="image/gif" >

	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/retailStyle.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/wholesaleStyle.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/wildPeacock.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style_1/style.css" type="text/css"  />

		<!-- New styling for jquery mobile -->	    
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/multi-select.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.10.3.custom.min.css" type="text/css"/>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu-green-yellow.css" type="text/css" />
    
		
  		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.multi-select.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/freewall.js" type="text/javascript"></script>
    	<script src="${pageContext.request.contextPath}/resources/js/menu-green-yellow.js" type="text/javascript"></script>
    	
		<!-- IMAGE SCROLLER DETAILS -->
	
	</head>
	
	<body class="${pageCSS }">
		<div id="page-wrap">
			<div class="pageContent">
			
				<!-- Header with logo's -->
				<div class="headerContent-${pageCSS }">
		        	<div class="headerwrapperCol1">
		            	<!-- logo here -->
		            </div>
		            
		            <c:if test="${not empty slogan }">
			 	      	<div class="headerwrapperCol2-${slogan}">
			        		<!-- text/slogan here -->
			            </div>
		        	</c:if>
				</div>
			
				<!-- Main Content -->
				<div class="mainContent">
					
			        <jsp:doBody />

		    	</div>
		        
		        <!-- Content Footer -->
			    <div class="footerwithround">
			       	<div align="center"><p class="footerTextForRoundCorners">Wild Peacock Food Emporium  |  30 Piet Retief Street  |  Stellenbosch  |  7600  |  tel: 082 697 0870 | <a href="mailto:sarah@wildpeacock.co.za?subject=Contact from Website" class="footerLink">sarah@wildpeacock.co.za</a>   </p></div>
			    </div>
		  	</div>
		
			<!-- Page footer -->
			<div id="footer-wrap">
				<div class="footerContent">
					<div class="col1">
	            		<div class="col3">
	           		  		<div align="center">
								<p class="footer_copyright">
	                    			&copy; Copyright 2011 Wild Peacock Products.  All rights reserved. Designed by <a class="footer_link" href="http://www.tenfourmedia.co.za" target="_blank">tenfour media</a>. Development by <a class="footer_link" href="http://www.linkedin.com/pub/anton-adriaan-van-zyl/4a/252/90" target="_blank">Anton Van Zyl</a>.
	                    		<div id="copyright">Copyright &copy; 2013 <a href="http://apycom.com/">Apycom jQuery Menus</a></div>
	                    		
	                    		</p>
					  		</div>
	              		</div>
	         		</div>
				</div>
		    </div>
		</div>
	</body>
</html>
