<%@tag import="java.net.URLEncoder"%>
<%@ tag language="java" description="This is the facebook like button" pageEncoding="ISO-8859-1"%>
<%@attribute name="pageURL" required="false"%>
<%@attribute name="showFaces" required="true" %>
<%@attribute name="height" required="true" %>


<iframe
	src="//www.facebook.com/plugins/like.php?href=<%=URLEncoder.encode("http://www.wildpeacock.co.za"+pageURL, "UTF-8")%>&amp;width=200px&amp;height=${height }&amp;colorscheme=light&amp;layout=standard&amp;action=like&amp;show_faces=${showFaces }&amp;send=false&amp;gid=100746986824"
	scrolling="no" frameborder="0" style="border: none; overflow: hidden; width: 200pxpx; height: 80px;" allowTransparency="true"
></iframe>


