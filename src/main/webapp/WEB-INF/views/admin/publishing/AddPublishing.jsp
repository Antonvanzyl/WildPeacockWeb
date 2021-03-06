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
					Admin Login<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
                <form:form commandName="publishModelForm" action="${pageContext.request.contextPath}/submitAddPublishing">
	                    <p class="mainText_Contacts"><b>This is the publishing for wild peacock:</b><br/><br/>
	                    <div class="ui-widget">
								<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
									<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
									<strong>Remember:</strong> When writing about a upcoming event, choose event, when writing about a blog, choose blog </p>
								</div>
						</div>
	
	                    <label>Title</label><form:input path="title"/><form:errors path="title" cssStyle="color:red"/><br/>
	                    <label>Sub-Title</label><form:input path="subtitle"/><form:errors path="subtitle"cssStyle="color:red"/><br/><br/>
	                    <label>Description</label><form:textarea rows="30" path="description"/><form:errors path="description"cssStyle="color:red"/>
	                    <label>EventDate</label><form:input path="eventDate" id="datepicker" /><form:errors path="eventDate"cssStyle="color:red"/>
	                     
	                    <label>Section</label>
	                    <select name="section">
	                    	<option value="BLOG"  <c:if test="${ publishModelForm.section ==  'BLOG'}">selected="selected"</c:if>>Blog</option>
	                    	<option value="NEWS" <c:if test="${ publishModelForm.section ==  'NEWS'}">selected="selected"</c:if>>News</option>
	                    </select>
	                    <form:errors path="section" cssStyle="color:red"/>
	                    
	                    <br/><br/>
	                    <input type="submit" value="Add Publishing">  
                </form:form>
                
				<script type="text/javascript">
				
				</script>
				
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
		 
			$(function() {
				    $("#datepicker").datepicker({
			            dateFormat:'dd/mm/yy'
			        }).val();
			});
 		</script>
 		</jsp:body>
</wp:page>