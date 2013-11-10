<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header" slogan="home">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/WholeSaleLinks.jspf"%>
			
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					${customPage.title }<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">${customPage.description }</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
	</div>
</wp:page>