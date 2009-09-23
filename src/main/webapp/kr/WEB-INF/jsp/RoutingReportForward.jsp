<%--
 Copyright 2007 The Kuali Foundation
 
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
<%@ include file="tldHeader.jsp"%>
<html>
<head>

</head>
<body>
<%-- Below form used for non java script enabled browsers --%>
<form name="disabledJavaScriptReportForm" id="disabledJavaScriptReportForm" method="post" action="${workflowRouteReportUrl}">
  <c:forEach var="keyLabelPair" items="${noJavaScriptFormVariables}">
    <input type="hidden" name='${keyLabelPair.key}' value='<c:out value="${keyLabelPair.label}" escapeXml="true"/>'>
  </c:forEach>
  <noscript>
    Click this button to see the Routing Report:&nbsp;&nbsp;&nbsp;<input type="submit" value="View Report">
  </noscript>
</form>
<%-- Below forms used for java script enabled browsers --%>
<form name="backForm" id="backForm" method="post" action="${backUrlBase}">
  <c:forEach var="keyLabelPair" items="${backFormHiddenVariables}">
    <input type="hidden" name="${keyLabelPair.key}" value="${keyLabelPair.label}">
  </c:forEach>
</form>
<form name="routeReportForm" id="routeReportForm" method="post" action="${workflowRouteReportUrl}">
  <c:forEach var="keyLabelPair" items="${javaScriptFormVariables}">
    <input type="hidden" name='${keyLabelPair.key}' value='<c:out value="${keyLabelPair.label}" escapeXml="true"/>'>
  </c:forEach>
<script language ="javascript">
window.onload = dothis();
function dothis() {
  _win = window.open('', 'routereport');
  document.routeReportForm.target=_win.name;
  document.routeReportForm.submit();
  document.backForm.submit();
}
</script>
</form>
</body>
</html>
