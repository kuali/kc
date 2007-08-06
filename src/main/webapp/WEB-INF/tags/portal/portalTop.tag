<%--
 Copyright 2005-2006 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Kuali Portal Index</title>
<link href="css/portal.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="kr/scripts/my_common.js"></script>
<script language="javascript" >
if (top.location != self.location) {
	top.location = self.location;
}
</script>

</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

 <div id="header" title="Kuali Research Administration">
    <h1 class="kra"></h1>Kuali Research Administration
  </div>
  <div id="build">${ConfigProperties.version}</div>
