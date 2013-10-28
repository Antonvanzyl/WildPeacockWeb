<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin - Add Category<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<p class="mainText_Contacts">
					<form:form commandName="categoryModelForm" action="${pageContext.request.contextPath}/submitAddCategory">
						<p class="mainText_Contacts">
							<b>This is the Category add for wild peacock:</b><br />
							
							<div class="ui-widget">
								<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
									<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
									<strong>Remember:</strong> A category is a placing in which a product fall, like cheese, wine, food type. A category should not be used for when 
									you want to add more specific things to a product, like age, year, taste. To do that, please use tags.</p>
								</div>
							</div>
							
							<br />If you are adding a main category, select none. <br/>
							<select id="parentId" name="parentId">
								<option value="0">None</option>
									<c:forEach items="${mainCategories }" var="category" >
									<c:choose>
										<c:when test="${category.categoryId == categoryModelForm.parentId}">
											<option value="${category.categoryId }" selected="selected">${category.categoryName }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.categoryId }">${category.categoryName }</option>
										</c:otherwise>
									</c:choose>
									</c:forEach>
							</select> <label>Name</label>
							<form:input path="name" /> <form:errors path="name" cssStyle="color:red" />
							
							<br /> <label>Description</label>
							<form:input path="description" /><form:errors path="description" cssStyle="color:red" />
							
							<br /><br/> <input type="submit" value="Add Category">
					</form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>
