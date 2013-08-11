<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" description="This is the tag to generate the page" pageEncoding="ISO-8859-1"%>
<%@attribute name="firstContent" required="false" description="content in the white space" %>
<%@attribute name="secondContent" required="false" description="content in the grey space" %>
<%@attribute name="pageHeader" required="false" description="Main page header"%>
<%@attribute name="subtitle" required="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="imagetoolbar" content="no" />
	
		<title>.|.. Wild Peacock Products: RETAIL HOME ..|.</title>
	
	    <link rel="stylesheet" href="css/retailStyle.css" type="text/css" media="screen" />
	    
	    <link href="css/style_1/style.css" rel="stylesheet" type="text/css" />
	
		<link rel="shortcut icon" href="favicon.ico" >
		<link rel="icon" href="favicon.ico" type="image/gif" >
	
		<!-- IMAGE SCROLLER DETAILS -->
		<link rel="Stylesheet" type="text/css" href="css/smoothDivScroll.css" />
	
	</head>
	
	<body>
		<div id="page-wrap">
			<div class="pageContent">
			
				<!-- Header with logo's -->
				<div class="headerContentWS"></div>
			
				<!-- Main Content -->
				<div class="mainContent">
			        <div class="mainContentWrapper_WSP">	
			        	<div class="mainContentWrapper_WSP_TopLinks">
			            	<div class="mainContentWrapper_WSP_TopLinks1">
			                </div>
			                <div class="mainContentWrapper_WSP_TopLinks2">
			                  	<p align="right" class="mainText_Site">| <font color="#7b9a75">Retail</font> | <a href="retail_products.htm" class="mainTextHead">Deli Products</a> | <a href="retail_press.htm" class="mainTextHead">Press</a> | <a href="retail_contact.htm"  class="mainTextHead">Contact</a></p>
			                </div>
			            </div>
			        
			        	<div class="WSP_Main_Holder">
			           		<h5>${pageHeader}</h5>
		            	</div>
		        
			        	<div class="mainContentWrapperCol2_holder_WSP">
							<jsp:doBody />
			            </div>
		       		</div>
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
	                    			&copy; Copyright 2011 Wild Peacock Products.  All rights reserved. Designed by <a class="footer_link" href="http://www.tenfourmedia.co.za" target="_blank">tenfour media</a>. Development by <a class="footer_link" href="http://www.gacreations.co.za" target="_blank">greenapple creations</a>.
	                    		</p>
					  		</div>
	              		</div>
	         		</div>
				</div>
		    </div>
		</div>
	</body>
</html>
