<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header"  slogan="products">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					${product.title }
					<span style="float: right;">${wp:formatMoneyWithCents( product.price) }</span>
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<div class="ui-widget">
					<div class="ui-state-highlight ui-corner-all" >
						<p>
							<strong><i>${product.subTitle }</i></strong><br />
							${product.description }
						</p>
					</div>
				</div>
			</div>
				<br/><wp:facebook-like pageURL="${pageContext.request.contextPath}/select_Retail_Products?productId=${product.productId}" showFaces="false" height="25px"/>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>