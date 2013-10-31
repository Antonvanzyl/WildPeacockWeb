<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
<jsp:attribute name="paging">
<wp:pagingControl pageNumber="${currentPage }" pageURL="${pageContext.request.contextPath}/news/page?page=" itemsTotal="${totalRecords }" itemsOnPage="20"/>
</jsp:attribute>
<jsp:attribute name="extraScripts">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simplePagination.css" type="text/css" />
    	<script src="${pageContext.request.contextPath}/resources/js/jquery.simplePagination.js" type="text/javascript"></script>
</jsp:attribute>
<jsp:body>
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					News<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
					<c:forEach items="${news }" var="item">
						<div class="ui-widget">
						<div class="ui-state-highlight ui-corner-all" style="margin-top: 15px; max-height: 100px;">
							<p>
								<a href="${pageContext.request.contextPath}/news/detail?id=${item.id}" class="mainTextLinkPress"> <strong><i>${item.title }</i></strong><br /> ${item.subtitle }</a>
								<span style="position: relative; height: 25px; float:right;top: -10px "><wp:facebook-like pageURL="${pageContext.request.contextPath}/news/detail?id=${item.id}" showFaces="false" height="25px"/></span>
							</p>
						</div>
					</div>
					</c:forEach>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</jsp:body>
</wp:page>