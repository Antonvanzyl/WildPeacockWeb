<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Search publishing<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<p class="mainText_Contacts">
				<p class="mainText_Contacts">
					<b>This is the search publishing for wild peacock:</b><br />
					<br />
				<table border="1" style="width: 500px; border-bottom-style:  solid; " >
					<thead>
						<tr>
							<th>Title</th>
							<th>Sub Title</th>
							<th>Section</th>
							<th>Edit</th>
							<th>delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${publishings }" var="item">
							<tr>
								<td>${item.title }</td>
								<td>${item.subtitle }</td>
								<td>${item.publishingSectionType }</td>
								<td><a href="${pageContext.request.contextPath}/editPublishing?id=${item.id }">Edit ${item.title }</a></td>
								<td><a href="${pageContext.request.contextPath}/deletePublishing?id=${item.id }">Delete ${item.title }</a></td>
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