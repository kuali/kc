<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Unit Hierarchy</title>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
    margin:0;
    padding:0;
}
</style>


<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
<script language="JavaScript" type="text/javascript" src="scripts/kuali_application.js"></script>


</head>
<body>
<html:form styleId="kualiForm" method="post" action="/awardHierarchyAwardActionsAjax.do" enctype="" onsubmit="return hasFormAlreadyBeenSubmitted();">
<div id="json">
${KualiForm.awardHierarchy}
</div>

<script type="text/javascript">

alert ("in researchareaload ");
</script>


<!--END SOURCE CODE =============================== -->
    <kul:csrf />
</html:form>
</body>
</html>
