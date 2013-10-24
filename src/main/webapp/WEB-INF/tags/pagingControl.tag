<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageNumber" required="false" type="java.lang.Integer"%>
<%@attribute name="pageURL" required="false"%>
<%@attribute name="showNext" required="false" type="java.lang.Boolean"%>

<div style="width: 100%; text-align: center; font-size: 14px">
	<br />
	<c:if test="${pageNumber>1 }">
		<a href="${pageURL }${pageNumber-1 }">Previous Page</a>
	</c:if>
	<%
		for (int x = 1; x < (pageNumber + 1); x++) {
			out.print("<a href=\"" + pageURL + x + "\">|" + x + "|</a>");
		}
	%>
	<c:if test="${empty showNext || showNext == true }">
		<a href="${pageURL }${pageNumber +1 }">Next Page</a>
	</c:if>
</div>
