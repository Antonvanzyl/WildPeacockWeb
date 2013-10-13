<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin Manage<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
			<div ><h2 style="color:red">${message }</h2></div>
			<ul>
				<li><a href="viewAddCategory">Add A Category</a></li>
				<li><a href="viewAddTag">Add A Tag</a></li>
				<li><a href="viewAddProduct">Add A Product</a></li>
				<li><a href="viewAddPublishing">Write a event/blog entry</a></li>
			</ul>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
			
			
			
			
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>