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
                <form:form commandName="publishModelForm" action="${pageContext.request.contextPath}/wildAdmin/submitAddPublishing">
	                    <p class="mainText_Contacts"><b>This is the publishing for wild peacock:</b><br/><br/>
	                    
	
	                    <label>Title</label><form:input path="title"/><form:errors path="title" cssStyle="color:red"/><br/>
	                    <label>Sub-Title</label><form:input path="subtitle"/><form:errors path="subtitle"cssStyle="color:red"/>
	                    <label>Description</label><form:input path="description"/><form:errors path="description"cssStyle="color:red"/>
	                    <label>EventDate</label><form:input path="eventDate" id="datepicker"/><form:errors path="eventDate"cssStyle="color:red"/>
	                     
	                    <label>Section</label>
	                    <select name="section">
	                    <option value="BLOG">Blog</option>
	                    <option value="NEWS">News</option>
	                    </select>
	                    <form:errors path="section" cssStyle="color:red"/>
	                    
	                    <br/>
	                    <input type="submit" value="Login">  
                </form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
		
		 <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</wp:page>