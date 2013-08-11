<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" description="This is the tag to generate the page" pageEncoding="ISO-8859-1"%>
<%@attribute name="firstContent" required="false" description="content in the white space" %>
<%@attribute name="secondContent" required="false" description="content in the grey space" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Anton Van Zyl dot Com</title>
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="resources/css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="all">


</head>
<body id="page2">
<div class="body1">
  <div class="body2">
    <div class="body3">
      <div class="main">
        <!-- header -->
        <header>
          <div class="wrapper">
            <h1><a href="home" id="logo"></a></h1>
            <c:choose>
	            <c:when test="${not empty UserModel }">
					<div class="header-text"><br/>
						Hi ${UserModel.username }<br/>
						<a href="logout" >logout</a>
					</div>
	            </c:when>
	            <c:otherwise>
		            <form id="login" action="login" method="post">
		              <div>
		                <input type="submit" class="submit" value="">
		              	<input id="password" name="password" tabindex="2" class="input" type="text" value="Password" onBlur="if(this.value=='') this.value='Password'" onFocus="if(this.value =='Password' ) this.value=''">
		                <input id="username" name="username" tabindex="1" class="input" type="text" value="Username" onBlur="if(this.value=='') this.value='Username'" onFocus="if(this.value =='Username' ) this.value=''">
		             </div>
		            </form>
	            </c:otherwise>
            </c:choose>
            <nav>
              <ul id="menu">
                <li id="active"><a href="index.html">Home</a></li>
                
                <c:if test="${not empty UserModel }">
               	 	${UserModel.links }
                </c:if>
                
                <li class="end"><a href="contact.html">Contact</a></li>
              </ul>
            </nav>
          </div>
        </header>
        <!-- / header-->
        <!-- content -->
        ${firstContent }
      </div>
    </div>
  </div>
</div>
${secondContent }
<!-- / content -->
<div class="main">
  <!-- footer -->
  <footer>
    <div class="wrapper"> <span class="left left-align"> Copyright &copy; <a href="#">Anton Van Zyl</a> All Rights Reserved<br>
      Design by <a target="_blank" href="http://www.antonvanzyl.com/">Anton Van Zyl</a><br>
      </span>
      <ul id="icons">
        <li><a href="https://www.facebook.com/aavanzyl" class="normaltip" title="Facebook"><img src="resources/images/icon1.png" alt=""></a></li>
      </ul>
    </div>
    <!-- {%FOOTER_LINK} -->
  </footer>
  <!-- / footer -->
</div>
</html>