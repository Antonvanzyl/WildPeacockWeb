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
				<div>
					<h2 style="color: red">${message }</h2>
				</div>
				<br />
				<br />
				<h3>Category Management</h3>
				<ul title="Category Management">
					<li><a href="viewAddCategory">Add A Category</a></li>
					<li><a href="viewEditCategory">Edit A Category</a></li>
					<li><a href="viewDeleteCategory">Delete A Category</a></li>
				</ul>
				<br />
				<br />
				<h3>Tag Management</h3>
				<ul title="Tag Management">
					<li><a href="viewAddTag">Add A Tag</a></li>
					<li><a href="viewEditTag">Edit A Tag</a></li>
					<li><a href="viewDeleteTag">Delete A Tag</a></li>
				</ul>
				<br />
				<br />
				<h3>Product Management</h3>
				<ul title="Product Management">
					<li><a href="viewAddProduct">Add A Product</a></li>
					<li><a href="viewEditProduct">Edit A Product</a></li>
					<li><a href="viewDeleteProduct">Delete A Product</a></li>
				</ul>
				<br />
				<br />
				<h3>Publishing Management</h3>
				<ul title="Publishing Management">
					<li><a href="viewAddPublishing">Write a event/blog entry</a></li>
					<li><a href="viewEditPublishing">Edit a event/blog entry</a></li>
					<li><a href="viewDeletePublishing">Delete a event/blog entry</a></li>
				</ul>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>