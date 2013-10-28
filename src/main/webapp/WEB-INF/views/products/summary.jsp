<%@include file="/WEB-INF/jspf/include-header.jspf"%>
<wp:page pageCSS="retail" pageHeader="Page Header" slogan="products">
	
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>
			<div class="WSP_Main_Holder_Left">&nbsp;</div>
			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">Deli Products</div>
			</div>
			<div class="Retail_Main_Holder_Right">
				<div class="ui-widget">
					<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
						<p>
							<strong>Products Categories</strong><span align="right" style="position: relative; float: right; right: 14px;"><strong>Search:</strong> <input id="search" /></span>
						
							<table>
							<tr>
								<td width="300px" valign="top">
									<ul id="categoryMenu" style="z-index: 999">
										<c:forEach items="${categories }" var="mainCategory">
											<c:choose>
												<c:when test="${ fn:length(  mainCategory.subCategories ) >0 }">
													<li style="z-index: 999"><a href="javascript:getProductsForCategory(${mainCategory.categoryId })">${mainCategory.categoryName }</a>
														<ul style="z-index: 999">
															<c:forEach items="${mainCategory.subCategories }" var="subCategories">
																<li style="z-index: 999"><a href="javascript:getProductsForCategory(${subCategories.categoryId })">${subCategories.categoryName }</a></li>
															</c:forEach>
														</ul></li>
												</c:when>
												<c:otherwise>
													<li><a href="javascript:getProductsForCategory(${mainCategory.categoryId })">${mainCategory.categoryName }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</td>
								<td width="95%" style="vertical-align: text-top;">
									<ul id="container" type="none">Loading Products...
									</ul>
								</td>
							</tr>
						</table>
						</p>
					</div>
				</div>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
		</div>
		<script>
			$.widget("custom.catcomplete", $.ui.autocomplete, {
				_renderMenu : function(ul, items) {
					var that = this, currentCategory = "";
					$.each(items, function(index, item) {
						if (item.category != currentCategory) {
							ul.append("<li class='ui-autocomplete-category'>"
									+ item.category + "</li>");
							currentCategory = item.category;
						}
						that._renderItemData(ul, item);
					});
				}
			});
			
			$(function() {
				var data = [ ${tagsScript}];

				$("#search").catcomplete({
					delay : 0,
					source : data
				});
			});
			
			 var wall;
			
		    $(function() {
		   		wall = new freewall("#container");
		      
		      $.get( "${pageContext.request.contextPath}/getProducts?categoryId=${selectedCategoryId}", function( data ) {
		    	  var object = jQuery.parseJSON(data);
		    	  $( "#container" ).html( object.product);
			      wall.fitWidth();
		    	});
		      
		    });
		    
		    $(function() {
				$("#categoryMenu").menu();
			});
		    
		    function getProductsForCategory(categoryId)
		    {
		    	 $.get( "${pageContext.request.contextPath}/getProducts?categoryId="+categoryId, function( data ) {
			    	  var object = jQuery.parseJSON(data);
			    	  $( "#container" ).html( object.product);
				      wall.fitWidth();
			    });
		    }
		</script>
</wp:page>