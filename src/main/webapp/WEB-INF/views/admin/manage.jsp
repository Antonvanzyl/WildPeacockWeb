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
				Welcome to the admin management page, use the navigation to manage the content of the site.<br/>
				You will notice the added menu items when your are in admin mode.
				
				<br/><br/>
				Deli Products:<br/>
				 * Categories - Here you will manage your categries. Categories are the main classification of products, like wine, meat, cheese,ect..<br/>
				 * Tags - Here you will manage your tags. Tags are quick search one word items related to the product. A product can have as many tags as it wants.<br/>
				 * Products - Here you will manage your products. Add new products, edit current products or delete them. A product is created to fall within a category and have at least one tag.<br/>
				 <br/>
				 Publishing:<br/>
				 * With publishing you will manage your latest news and blog entries. Here you will be able to write about anything on your mind. When managing a publishing, you can also view how many times someone read the entry.
				
				<br/><br/>
				When in admin mode, a timeout of 30min is in-forced with no activity. However; It is recommended that you log out once done as a safety measure. 
				Admin functions will only be available to a valid admin user. There is no way to add a new admin user with the UI interface, a admin user needs to be added directly on the database it self.   
				
				<div>
					<h2 style="color: red">${message }</h2>
				</div>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>