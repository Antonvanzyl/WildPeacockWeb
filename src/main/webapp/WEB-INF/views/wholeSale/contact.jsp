<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="wholesale" pageHeader="Page Header" slogan="contact">
<jsp:attribute name="extraScripts">
    	<script src="${pageContext.request.contextPath}/resources/js/gmaps.js" type="text/javascript"></script>
</jsp:attribute>
<jsp:body>
	<div class="mainContentWrapper_WS_Contact">
		<%@include file="/WEB-INF/jspf/wholeSaleLinks.jspf"%>

		<div class="WS_Main_Holder">
			<div class="WS_Main_Holder_Left">
				<!-- Spacer on left side -->
				<p>&nbsp;</p>
			</div>

			<div class="WS_Main_Holder_Right">
				<!-- Text on the right hand side -->
				<h5>
					Wholesale<br />Wild Peacock Products - contacts
				</h5>
				<div class="WS_Main_Holder_Right1">
					<p class="mainText_Contacts">
						<b>Orders and Enquiries:</b><br /> T. 021 801 3663/4/5<br /> F.
						086 577 3663<br /> E. <a
							href="mailto:food@wildpeacock.co.za>subject=Contact From Website"
							class="mainTextLink">food@wildpeacock.co.za</a>
					</p>
				</div>
				<div class="WS_Main_Holder_Right2">
					<p class="mainText_Contacts">
						<b>Accounts:</b><br /> T. 021 801 3662<br /> F. 086 577 3663<br />
						E. <a
							href="mailto:accounts@wildpeacock.co.za>subject=Contact From Website"
							class="mainTextLink">accounts@wildpeacock.co.za</a>
					</p>
				</div>

				<div class="WS_Main_Holder_SplitLine"></div>

				<div class="WS_Main_Holder_Right1">
					<p class="mainText_Contacts">
						<b>Sue Baker:</b><br /> C. 082 782 1296<br /> E. <a
							href="mailto:sue@wildpeacock.co.za>subject=Contact From Website"
							class="mainTextLink">sue@wildpeacock.co.za</a>
					</p>
				</div>
				<div class="WS_Main_Holder_Right2">
					<p class="mainText_Contacts">
						<b>Ross Baker:</b><br /> C. 082 923 1583<br /> E. <a
							href="mailto:Ross@wildpeacock.co.za>subject=Contact From Website"
							class="mainTextLink">Ross@wildpeacock.co.za</a>
					</p>
				</div>

				<div class="WS_Main_Holder_SplitLine"></div>

				<div class="WS_Main_Holder_Right1">
					<p class="mainText_Contacts">
						<b>Postal Address:</b><br /> P.O. Box 12168<br />Die Boord<br />Stellenbosch<br />7613
					</p>
	                <wp:facebook-like pageURL="" showFaces="false" height="25px"/>
				</div>
	            </div>
	            <wp:GoogleMap style="margin:25px;"></wp:GoogleMap>
	            <div class="WS_Main_Holder_Right">
	            <div class="WS_Main_Holder_Right1">
	                
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
			</div>
		</div>

		<div class="mainContentWrapperCol2_holder_WS">
			<div class="mainContentWrapperCol2_squares_holder_WS">
			</div>
		</div>
		

	</div>
	</jsp:body>
</wp:page>