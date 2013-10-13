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
                <form:form commandName="User" action="${pageContext.request.contextPath}/wildAdmin/submitLogin">
	                    <p class="mainText_Contacts"><b>This is the Admin login for wild peacock:</b><br/><br/>
	                    <label>Username</label><form:input path="username"/><form:errors path="username" cssStyle="color:red"/><br/>
	                    <label>Password</label><form:input path="password"/><form:errors path="password"cssStyle="color:red"/>
	                    <br/>
	                    <input type="submit" value="Login">  
                </form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>