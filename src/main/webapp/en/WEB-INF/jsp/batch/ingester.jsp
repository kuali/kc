<%@ page session="false" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
  <title>Ingester</title>
  <link href="css/edoclite.css" rel="stylesheet" type="text/css"/>
  <link href="css/screen.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<h1>Ingester</h1>
<div class="maindiv">
<h2>Select files to upload</h2>
<%
    List list = (List) request.getAttribute("messages");
    if (list != null) {
%>
<ul>
<%
        Iterator it = list.iterator();
        while (it.hasNext()) {
%>
  <li class="info-message"><%= it.next().toString() %></li>
<%
        }
%>
</ul>
<%
    }
%>
<html-el:form method="post" action="/Ingester" enctype="multipart/form-data">
<table>
  <tr><td>
    <html-el:file styleClass="dataCell" name="IngesterForm" property="file[0]"/>
  </td></tr>
  <tr><td>
    <html-el:file styleClass="dataCell" name="IngesterForm" property="file[1]"/>
  </td></tr>
  <tr><td>
    <html-el:file styleClass="dataCell" name="IngesterForm" property="file[2]"/>
  </td></tr>
  <tr><td>
    <html-el:file styleClass="dataCell" name="IngesterForm" property="file[3]"/>
  </td></tr>
  <tr><td align="center">
    <html-el:submit value="Upload XML data"></html-el:submit>
  </td></tr>
</table>
</html-el:form>

</div>
</body>
</html>