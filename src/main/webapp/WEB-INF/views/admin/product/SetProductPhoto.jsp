<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header">
	<jsp:attribute name="extraScripts">
</jsp:attribute>
	<jsp:body>
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin - Add Product Photo<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
			
			<c:if test="${not empty message }">
				<div class="ui-widget">
								<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
									<p>
										<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
									<strong>Attention:</strong>${message } </p>
								</div>
					</div>
			</c:if>
					
					
					<form method="POST" action="<c:url value='/uploadProductPhoto' />" enctype="multipart/form-data">
					    Please select a file to upload : <input type="file" name="file" />
					    <input type="submit" value="upload" />
					    <button value="cancel" onclick="<c:url value='/viewManage' />"></button>
					</form>	
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
		 </div>
		
		 </jsp:body>
</wp:page>