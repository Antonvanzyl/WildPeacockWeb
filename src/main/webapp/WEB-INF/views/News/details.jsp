<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			

			<div class="Retail_Main_Holder_Right">
				<p class="mainText_Contacts">
					<b><i>${details.title }</i></b><br />${details.subtitle }<br/><br/>${details.description }
				</p>

			<wp:facebook-like pageURL="${pageContext.request.contextPath}/news/detail?id=${details.id}"></wp:facebook-like>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>