<!--    /config/cofigure.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%-- TODO: not used --%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link href="<%= request.getContextPath() %>/jmanage/css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body leftmargin="10" topmargin="10" marginwidth="0" marginheight="0">
<span class="headtext"><b><br />Configure</b></span><br /><br />
<%--<jmhtml:javascript formName="configureForm" />--%>
<jmhtml:errors />
<jmhtml:form action="/config/configure" method="post">
  <table border="0" bordercolor="black" cellspacing="1" cellpadding="2" width="350">
    <tr>
      <td class="headtext1">Maximum Login Attempts</td>
      <td> <jmhtml:text property="maxLoginAttempts" />
      </td>
    </tr>
  </table>
  </br>
  <jmhtml:submit value="Save" styleClass="Inside3d"/>
  &nbsp;&nbsp;&nbsp;
  <jmhtml:button property="" value="Back" onclick="JavaScript:history.back();" styleClass="Inside3d" />
  </jmhtml:form>
</body>
</html>