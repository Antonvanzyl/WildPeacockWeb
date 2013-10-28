<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin Add Tag<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
               <form:form commandName="tagModelForm" action="${pageContext.request.contextPath}/submitAddTag">
						<p class="mainText_Contacts">
							<b>This is the tag add for wild peacock:</b><br />
							<div class="ui-widget">
								<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
									<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
									<strong>Remember:</strong> A tag is a specific representation on a group of products, like year, taste, origen. </p>
								</div>
							</div>
							<br />If you are adding a main Tag, select none. <br/>
							<select id="parentId" name="parentId">
								<option value="0">None</option>
								<c:forEach items="${mainTags }" var="tag">
								<c:choose>
										<c:when test="${tag.tagId == tagModelForm.parentId}">
											<option value="${tag.tagId }" selected="selected">${tag.tagTitle }</option>
										</c:when>
										<c:otherwise>
										<option value="${tag.tagId }">${tag.tagTitle }</option>
										</c:otherwise>
								</c:choose>
								</c:forEach>
								
							</select> <label>Name</label>
							<form:input path="name" /> <form:errors path="name" cssStyle="color:red" />
							<br/>
							<br /> <input type="submit" value="Add Tag">
					</form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>