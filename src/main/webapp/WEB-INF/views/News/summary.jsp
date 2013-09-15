<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					News<br />Wild Peacock Food Emporium
				</div>
			</div>

			<div class="Retail_Main_Holder_Right">
			
			<c:forEach items="${news }" var="item">
				<a href="${pageContext.request.contextPath}/news/detail?id=${item.id}" class="mainTextLinkPress">
					<p class="mainText_Contacts">
						<b><i>${item.title }</i></b><br /> ${item.subtitle } 
					</p>
				</a>
				<div class="WS_Main_Holder_SplitLine"></div>
			</c:forEach>

			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>