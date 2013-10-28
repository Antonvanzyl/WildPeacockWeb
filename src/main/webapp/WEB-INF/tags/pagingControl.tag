<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageNumber" required="false" type="java.lang.Integer"%>
<%@attribute name="pageURL" required="false"%>
<%@attribute name="itemsTotal" required="true"%>
<%@attribute name="itemsOnPage" required="true"%>

<div id="paging-control" style="padding-left:20px; padding-right:20px;  font-size: 14px; ">
</div>

<script type="text/javascript">
$(function() {
    $('#paging-control').pagination({
        items: ${itemsTotal},
        itemsOnPage: ${itemsOnPage},
        cssStyle: 'light-theme',
        hrefTextPrefix: '${pageURL}',
        currentPage: ${pageNumber},
        displayedPages: 3
    })
});
</script>