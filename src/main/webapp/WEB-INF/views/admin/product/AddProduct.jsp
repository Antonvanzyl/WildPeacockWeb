<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
<jsp:attribute name="extraScripts">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/multi-select.css" type="text/css" media="screen" />
		<script src="${pageContext.request.contextPath}/resources/js/jquery.multi-select.js" type="text/javascript"></script>
        <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
</jsp:attribute>
<jsp:body>
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin - Add Product<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
                <form:form commandName="productModelForm" action="${pageContext.request.contextPath}/submitAddProduct">
						<p class="mainText_Contacts">
							<b>This is the Product add for wild peacock:</b><br />
							
							<label>Title</label>
							<form:input path="title" /> 
							<form:errors path="title" cssStyle="color:red" />
							
							<label>Sub Title</label>
							<form:input path="subTitle" /> 
							<form:errors path="subTitle" cssStyle="color:red" />
							
							<label>Description (Max 2048)</label>
							<form:textarea path="description" /> 
							<form:errors path="description" cssStyle="color:red" />
							
							<label>Price</label>
							<form:input path="price" /> 
							<form:errors path="price" cssStyle="color:red" />
							
							<label>Category</label>
							<form:errors path="categoryId" cssStyle="color:red" />
							<select id="categoryId" name="categoryId">
								<c:forEach items="${categoryList }" var="category" >
									<option value="${category.categoryId }">${category.categoryName }</option>
								</c:forEach>
							</select> 
							
							<label>Tag</label>
							<form:errors path="tagIds" cssStyle="color:red" />
							<select multiple="multiple" id="tagIds" name="tagIds">
								<c:forEach items="${tagList }" var="tag">
									<option value="${tag.tagId }">${tag.tagTitle }</option>
								</c:forEach>
							</select>
							
							<br /> <input type="submit" value="Add Product">
					</form:form>
                
                
                
                
                
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
		 </div>
		 <script type="text/javascript">
		 $('#tagIds').multiSelect()
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