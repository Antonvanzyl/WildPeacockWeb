<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Contact<br />
				</div>
			</div>

			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
					<div class="WS_Main_Holder_Right">
                	<!-- Text on the right hand side -->
                    <div class="WS_Main_Holder_Right1">
                    	<p class="mainText_Contacts"><b>Postal Address:</b><br>
							P.O. Box 12168<br>Die Boord<br>Stellenbosch<br>7613</p>
                    </div>
                    <div class="WS_Main_Holder_Right2">
                    	<p class="mainText_Contacts"><b>Food Emporium</b><br>
						T. 021 887 7585<br>
						E. <a href="mailto:sarah@wildpeacock.co.za" class="mainTextLink">sarah@wildpeacock.co.za</a></p>
                    </div>
                    
                </div>
                <form:form commandName="contact" action="${pageContext.request.contextPath}/message">
	                    <c:choose>
		                    <c:when test="${not empty sent && sent == true }">
		                    	<p class="mainText_Contacts" style="color: red"><b>Message Sent Success</b></p>
		                    </c:when>
		                    <c:when test="${not empty sent && sent == false }">
		                    	<p class="mainText_Contacts" style="color: red"><b>Message Sent Failed<br/> please contact Sarah directly at <a href="mailto:sarah@wildpeacock.co.za">sarah@wildpeacock.co.za</a></p> </b></p>
		                   </c:when>
	                    </c:choose>
	                    <p class="mainText_Contacts"><b>Get in contact with us:</b><br/><br/>
	                    <label>Name</label><form:input path="name"/><form:errors path="name" cssStyle="color:red"/><br/>
	                    <label>Number</label><form:input path="number"/><form:errors path="number"cssStyle="color:red"/>
	                    <label>Email</label><form:input path="email"/><form:errors path="email"cssStyle="color:red"/>
	                    <label>What do you want to share?</label><form:textarea path="question" rows="10" cols="250"/><form:errors path="question"cssStyle="color:red"/>
	                    <br/>
	                    <input type="submit" value="Send Message">  
                </form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>