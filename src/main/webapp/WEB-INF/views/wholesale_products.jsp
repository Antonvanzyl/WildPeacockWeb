<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="wholesale" pageHeader="Page Header" slogan="products">
	<div class="mainContentWrapper_WSP">
		<div class="mainContentWrapper_WSP_TopLinks">
			<div class="mainContentWrapper_WSP_TopLinks1">
				<!-- 				<p class="mainText_BreadCrumbs">Home &raquo; Wholesale &raquo;
					Products</p> -->
			</div>
			<div class="mainContentWrapper_WSP_TopLinks2">
				<p align="right" class="mainText_Site">
					| <a href="${pageContext.request.contextPath}/wholesale_home"
						class="mainTextHead">Wholesale</a> | <font color="#7b9a75">Products</font>
					| <a href="${pageContext.request.contextPath}/wholesale_contact"
						class="mainTextHead">Contact</a>
				</p>
			</div>
		</div>

		<div class="WSP_Main_Holder">
			<div class="WSP_Main_Holder_Left">
				<!-- Spacer on left side -->
				<p>&nbsp;</p>
			</div>

			<div class="WSP_Main_Holder_Right">
				<!-- Text on the right hand side -->
				<h5>
					Wholesale<br />Wild Peacock Products - the products
				</h5>
				<p class="mainText_Contacts">Our product range is constantly
					growing to support new trends and industry requirements. Our
					primary business is supplying the best quality Oysters and Mussels
					around, and over the next few years an extensive range of SASSI
					green listed seafoods.</p>

			</div>


			<!-- ############################### IMAGE SCROLLER STARTS ##### -->

			<div class="WSP_Scroller_Holder">
				<div id="makeMeScrollable" align="center">

					<div class="scrollingHotSpotLeft"></div>

					<div class="scrollingHotSpotRight"></div>

					<div class="scrollWrapper">
						<div class="scrollableArea">
							<img
								src="${pageContext.request.contextPath}/resources/images/1.jpg"
								alt="image" width="510" height="105" /> <img
								src="${pageContext.request.contextPath}/resources/images/2.jpg"
								alt="image" width="452" height="105" /> <img
								src="${pageContext.request.contextPath}/resources/images/3.jpg"
								alt="image" width="474" height="105" id="startAtMe" /> <img
								src="${pageContext.request.contextPath}/resources/images/4.jpg"
								alt="image" width="505" height="105" /> <img
								src="${pageContext.request.contextPath}/resources/images/5.jpg"
								alt="image" width="480" height="105" />
						</div>
					</div>
				</div>
			</div>
			<!-- ############################### IMAGE SCROLLER STARTS ##### -->

			<div class="WSP_Main_Holder_Right">
				<!-- Text on the right hand side -->
				<p class="mainText_Contacts">Our poultry range focuses on
					free-range, fresh, sustainable bird products. From ducks, chickens,
					petit poussins, quails, pigeons and guinea fowl to an extensive
					range of Italian delicacies and Valrhona couverture chocolate.</p>
				<p class="mainText_Products_Links">
					For the full Wild Peacock Products products list please <a
						href="downloads/2011_product_list.pdf" class="mainTextProduct">click
						here.</a><br /> For the Wild Peacock Products customer application
					form please <a
						href="downloads/Customer_Detail_Form_wholesale_purchasing.pdf"
						class="mainTextProduct">click here.</a>
				</p>
			</div>

			<div class="WSP_Main_Holder_Right_Bot_Image">
				<!-- Bottom Image to come here -->
				<div class="WSP_Main_Holder_Right_Bot_ImageLink1">
					<a href="http://www.valrhona.fr" target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/blanklink.gif" /></a>
				</div>
				<div class="WSP_Main_Holder_Right_Bot_ImageLink2">
					<a href="http://www.cucinaitaliana.co.za" target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/blanklink.gif" /></a>
				</div>
				<div class="WSP_Main_Holder_Right_Bot_ImageLink3">
					<a href="http://www.cucinaitaliana.co.za" target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/blanklink.gif" /></a>
				</div>
				<div class="WSP_Main_Holder_Right_Bot_ImageLink4">
					<a href="http://www.riolargo.co.za" target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/blanklink.gif" /></a>
				</div>

			</div>
		</div>

		<div class="mainContentWrapperCol2_holder_WSP">
			<div class="mainContentWrapperCol2_squares_holder_WSP">
				<!-- Retail block -->
				<!-- 				<div class="mainContentWrapperCol2_square2_WSP">
					<a href="retail_home.htm"
						class="mainContentWrapperCol2_square2_WSP">
						<div class="mainContentWrapperCol2_square_text_WSP">
							<p align="right" class="TextForIndexSquares">Retail</p>
						</div>
					</a>
				</div> -->
			</div>
		</div>

	</div>
</wp:page>