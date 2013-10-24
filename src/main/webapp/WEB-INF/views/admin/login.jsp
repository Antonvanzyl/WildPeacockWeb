<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin Login<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<p class="mainText_Contacts">
					<c:set var="loginUrl">
						<c:url value='j_spring_security_check' />
					</c:set>
				<p class="mainText_Contacts">
					<b>This is the Admin login for wild peacock:</b><br />
					<br />
				<div id="login-error">${error}</div>
				<form action="${loginUrl }" method="post">
					<label for="j_username">Username</label> <input id="j_username" name="j_username" type="text" />
					<label for="j_password">Password</label> <input id="j_password" name="j_password" type="password" /><br/><br/>
					<input type="submit" value="Login" />
				</form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>