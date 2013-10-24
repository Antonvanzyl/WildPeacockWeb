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
				<table style="width: 800px;">
					<c:forEach items="${news }" var="item">
						<tr>
							<td width="600px" style="border-bottom: 1px; border-bottom-style: dashed;">
								<p class="mainText_Contacts">
									<a href="${pageContext.request.contextPath}/blog/detail?id=${item.id}" class="mainTextLinkPress"> <b><i>${item.title }</i></b><br /> ${item.subtitle }
									</a>
								</p>
							</td>
							<td style="border-bottom: 1px; border-bottom-style: dashed;"><wp:facebook-like pageURL="${pageContext.request.contextPath}/blog/detail?id=${item.id}"></wp:facebook-like>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<wp:pagingControl pageNumber="${currentPage }" pageURL="${pageContext.request.contextPath}/blog/page?page=" showNext="${ fn:length( news ) >20}"/>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>