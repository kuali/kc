<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:if test="${RouteManagerDriverForm.running}">
	<c:set var="runningValue" value="RUNNING"/>
</c:if>
<c:if test="${!RouteManagerDriverForm.running}">
	<c:set var="runningValue" value="NOT RUNNING"/>
</c:if>
<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="javascript" src="scripts/en-common.js"></script>
<script language="javascript" src="scripts/routemanager-common.js"></script>
</head>
<body>

<html-el:form method="get" action="/RouteManagerDriver.do" name="RouteManagerDriverForm" type="edu.iu.uis.eden.routemanager.web.RouteManagerDriverForm">
  <html-el:hidden name="RouteManagerDriverForm" property="methodToCall" />  
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="90%" >Route Manager Driver</td>
  </tr>
</table>
<div><h2>Route Manager Driver is Currently <c:out value="${runningValue}"/></h2></div>
<div>Route Manager Driver Class: <c:out value="${RouteManagerDriverForm.driverClassName}"/></div>
<div><input type="button" value="Start" onclick="javascript:post_to_action('RouteManagerDriver.do','startDriver')"/></div>
<div><input type="button" value="Stop" onclick="javascript:post_to_action('RouteManagerDriver.do','stopDriver')"/></div>
<c:if test="${RouteManagerDriverForm.pooled}">
<div>Pool Options:</div>
<div style="margin-left:10px">
  <div>Number of worker threads: <c:out value="${RouteManagerDriverForm.poolSize}"/></div>
  <div>Number of available worker threads: <c:out value="${RouteManagerDriverForm.numWorkersAvailable}"/></div>
  <div>Change number of worker threads: <html-el:text name="RouteManagerDriverForm" property="poolSize"/></div>
       &nbsp;&nbsp;&nbsp;
       <input type="button" value="Change" onclick="javascript:post_to_action('RouteManagerDriver.do','setPoolSize')"/>
  </div>
</div>
</c:if>
  </html-el:form>
</body>
</html-el:html>