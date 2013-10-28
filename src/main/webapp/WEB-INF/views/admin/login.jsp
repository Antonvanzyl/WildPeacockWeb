<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="ui-widget" style="width: 400px; left: 25%; position: relative;">
					<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
						<p>
							<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> <strong>Admin Login</strong><br />This is the login for
							wild peacock site Admin, unauthorised access is strictly prohibited.
							<div id="login-error">${error}</div>
							<form action="<c:url value='secure' />" method="post">
							<label for="username">Username</label> <input id="username" name="username" type="text" /> 
							<label for="password">Password</label> <input id="password" name="password" type="password" />
							<br />
							<br /> 
							<input type="submit" value="Login" />
							</form>
						</p>
					</div>
				</div>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>

</wp:page>