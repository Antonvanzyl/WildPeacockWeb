function PutSWF(strFile, intWidth, intHeight, strLink)
{
        if (strLink!='')
            document.write('<a href="' + strLink + '">');
        document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="' + intWidth + '" height="' + intHeight + '" id="nav" align="middle">');
        document.write('<param name="allowScriptAccess" value="sameDomain" />');
        document.write('<param name="movie" value="' + strFile + '" />');
        document.write('<param name="quality" value="high" />');
        document.write('<param name="wmode" value="transparent" />');
        document.write('<param name="bgcolor" value="#ffffff" />');
        document.write('<embed src="' + strFile + '" quality="high" bgcolor="#ffffff" width="' + intWidth + '" height="' + intHeight + '" name="nav" align="middle" wmode="transparent" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
        document.write('</object>')
        if (strLink!='')
            document.write('</a>');
}