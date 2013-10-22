<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin - Search Category<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<p class="mainText_Contacts">
				<p class="mainText_Contacts">
					<b>This is the Category edit for wild peacock:</b><br />
				<table border="1" style="width: 500px; border-bottom-style:  solid; " >
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categories }" var="category">
							<tr>
								<td>${category.categoryName }</td>
								<td>${category.categoryDescription }</td>
								<td><a href="${pageContext.request.contextPath}/editCategory?categoryId=${category.categoryId }">Edit ${category.categoryName }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>
