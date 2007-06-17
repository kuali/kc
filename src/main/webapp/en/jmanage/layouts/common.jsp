<!-- /layouts/common.jsp -->
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/struts/struts-tiles.tld" prefix="tiles" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link href="<%= request.getContextPath() %>/jmanage/css/styles.css" rel="stylesheet" type="text/css" />
    <title><tiles:getAsString name="title" /></title>
</head>
<body>
<tiles:insert attribute="header" />
<br/>
<tiles:insert attribute="body.header" />
<tiles:insert attribute="body.main" />
<tiles:insert attribute="footer" />
</body>
</html>