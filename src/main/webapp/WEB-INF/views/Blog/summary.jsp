<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Blog<br />Wild Peacock Food Emporium
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<c:forEach items="${news }" var="item">
					<div class="ui-widget">
						<div class="ui-state-highlight ui-corner-all" style="margin-top: 15px; max-height: 100px;">
							<p>
								<a href="${pageContext.request.contextPath}/blog/detail?id=${item.id}" class="mainTextLinkPress"> <strong><i>${item.title }</i></strong><br /> ${item.subtitle }</a>
								<span style="position: relative; height: 25px; float:right;top: -10px "><wp:facebook-like pageURL="${pageContext.request.contextPath}/blog/detail?id=${item.id}" showFaces="false" height="25px"/></span>
							</p>
						</div>
					</div>
				</c:forEach>
				<wp:pagingControl pageNumber="${currentPage }" pageURL="${pageContext.request.contextPath}/blog/page?page=" showNext="${ fn:length( news ) >=20}" />
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>