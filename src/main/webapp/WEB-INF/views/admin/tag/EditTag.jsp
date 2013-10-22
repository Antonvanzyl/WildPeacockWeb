<%@include file="/WEB-INF/jspf/include-header.jspf"%>

<wp:page pageCSS="retail" pageHeader="Page Header">
	<div class="mainContentWrapper_WSP_Press">
		<div class="WSP_Main_Holder">
			
			<%@include file="/WEB-INF/jspf/retailLinks.jspf"%>

			<div class="WSP_Main_Holder_Left">&nbsp;</div>

			<div class="Retail_Main_Holder_Right">
				<div class="retailHeader">
					Admin Edit Tag<br />
				</div>
			</div>
			<div class="Retail_Main_Holder_Right">
			<p class="mainText_Contacts">
               <form:form commandName="tagModelForm" action="${pageContext.request.contextPath}/submitAddTag">
						<p class="mainText_Contacts">
							<b>This is the tag Edit for wild peacock:</b><br />
							
							<br />If you are adding a main Tag, select none. <br/>
							<select id="parentId" name="parentId" >
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
							<form:hidden path="id" />
							<br/>
							<br /> <input type="submit" value="Update Tag">
					</form:form>
			</div>
			<div class="mainContentWrapperCol2_holder_WSP">
				<!-- Button on the right -->
			</div>
		</div>
</wp:page>