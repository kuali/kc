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
  <link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">  
  <style type="text/css">
@import url(scripts/jscalendar-1.0/calendar-win2k-1.css);
. {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; }
</style>
</head>

<body>
<div class="headerarea-small" id="headerarea-small">
<h1>Ingester</h1>
</div>
<div class="error">
 
<input class="tinybutton" name="methodToCall.search" src="images/pixel_clear.gif" type="image" border="0" height="0" width="0">
<%
    List list = (List) request.getAttribute("messages");
    if (list != null) {
%>
<ul>
<%
        Iterator it = list.iterator();
        while (it.hasNext()) {
%>
  <li><%= it.next().toString() %></li>
<%
        }
%>
</ul>
<%
    }
%>

</div>
<html-el:form method="post" action="/Ingester" enctype="multipart/form-data">
 <table width="80%" cellpadding="0" cellspacing="0">
    <tr>
      <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
      <td><div align="center"><br>
          <br>
          <br>
          <br>
  		    <html-el:file styleClass="dataCell" name="IngesterForm" property="file[0]"/>
  		  <br>
  		  	<html-el:file styleClass="dataCell" name="IngesterForm" property="file[1]"/>
  		  <br>
  			<html-el:file styleClass="dataCell" name="IngesterForm" property="file[2]"/>
  		  </div></td>
       </tr>
       <tr>            
          <td class="column-left"><img src="images/transparent.gif" alt="" width="20" height="20"></td>
           <td><div align="center"><br>
           	<html-el:image src="images/buttonsmall_uploadxml.gif" value="Upload XML data"  border="0" styleClass="nobord" styleId="imageField"></html-el:image>
          </div></td>
      <td class="column-right"><img src="images/transparent.gif" alt="" width="20" height="20"></td>
    </tr>
  </table>
</html-el:form>

</div>
</body>
</html>