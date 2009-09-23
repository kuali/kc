<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html-el:html>
<head>
<title>Thread Pool</title>
<style type="text/css">
   .highlightrow {}
   tr.highlightrow:hover, tr.over td { background-color: #66FFFF; }
</style>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/messagequeue-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="15%"><img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="85%"><a href="ThreadPool.do?methodToCall=start" />Refresh Page</a></td>
    <td>&nbsp;&nbsp;</td>
  </tr>
</table>

<html-el:form action="/ThreadPool.do">
<html-el:hidden property="methodToCall" />

  <table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
        <td width="20" height="20">&nbsp;</td>
  	<td>

      <br>
  	  <jsp:include page="../Messages.jsp"/>
      <br>

Core Pool Size: <html-el:text property="corePoolSize"/><br>
Maximum Pool Size: <html-el:text property="maximumPoolSize"/><br>
Pool Size: <c:out value="${ThreadPoolForm.threadPool.poolSize}"/><br>
Active Count: <c:out value="${ThreadPoolForm.threadPool.activeCount}"/><br>
Largest Pool Size: <c:out value="${ThreadPoolForm.threadPool.largestPoolSize}"/><br>
Keep Alive Time: <c:out value="${ThreadPoolForm.threadPool.keepAliveTime}"/><br>
Task Count: <c:out value="${ThreadPoolForm.threadPool.taskCount}"/><br>
Completed Task Count: <c:out value="${ThreadPoolForm.threadPool.completedTaskCount}"/><br>
RouteQueue.TimeIncrement: <html-el:text property="timeIncrement"/><br>
RouteQueue.maxRetryAttempts: <html-el:text property="maxRetryAttempts"/><br>
<br>
<html-el:checkbox property="allServers"/> Execute Across All Servers with Service Namespace <c:out value="${ThreadPoolForm.serviceNamespace}"/><br/>
<input type="button" value="Update" onclick="setMethodToCallAndSubmit('update')"/>
</td>
</tr>
  <tr>
        <td width="20" height="20">&nbsp;</td>
  </tr>

</table>
</html-el:form>
<br>
<jsp:include page="../Footer.jsp"/>

</body>
</html-el:html>
