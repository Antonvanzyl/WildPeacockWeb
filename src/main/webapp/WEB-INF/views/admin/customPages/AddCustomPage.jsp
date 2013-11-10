<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
<jsp:attribute name="extraScripts">
         <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
</jsp:attribute>
<jsp:body>

	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Custom Page<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
                <form:form commandName="customPageModelForm" action="${pageContext.request.contextPath}/submitAddCustomPage">
	                    <p class="mainText_Contacts"><b>This is the Custom Page for wild peacock:</b><br/><br/>
	                    <div class="ui-widget">
								<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
									<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
									<strong>Remember:</strong> A custom page is just something that you want to add, some ideas would be a about page, competition page or even a page to advertise your daily specials. Just remember that there is limited space on the menu bar, so choose wisely. </p>
								</div>
						</div>
	
	                    <label>Page Name</label><form:input path="pageName"/><form:errors path="pageName"cssStyle="color:red"/><br/><br/>
	                    <label>Title</label><form:input path="title"/><form:errors path="title" cssStyle="color:red"/><br/>
	                     
	                    <label>Site Space (WholeSale or Retail)</label>
	                    <select name="siteSpaceType">
	                    	<option value="WHOLESALE"  <c:if test="${ customPageModelForm.siteSpaceType ==  'WHOLESALE'}">selected="selected"</c:if>>Wholesale</option>
	                    	<option value="RETAIL" <c:if test="${ customPageModelForm.siteSpaceType ==  'RETAIL'}">selected="selected"</c:if>>Retail</option>
	                    </select>
	                    <form:errors path="siteSpaceType" cssStyle="color:red"/><br/>
	                    <label>Description</label><form:textarea rows="30" path="description"/><form:errors path="description"cssStyle="color:red"/>
	                    
	                    <br/><br/>
	                    <input type="submit" value="Add Custom Page">  
                </form:form>
				
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
		
		 <script>
			tinymce.init({
			    selector: "textarea",
			    plugins: [
			        "advlist autolink lists preview code",
			        "visualblocks ",
			        "table preview"
			    ],
			    toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | preview"
			});
		 
 		</script>
 		</jsp:body>
</wp:page>