<%--

    Copyright 2005-2011 The Kuali Foundation

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
<%@ page import="org.kuali.rice.kns.web.struts.action.KualiAction"%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<html>
<head>
 <title>Module Locked</title>
 <link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="scripts/en-common.js"></script>
</head>
<body>
 <div style="margin-top: 25px;">
   <strong><%=request.getAttribute(KualiAction.MODULE_LOCKED_MESSAGE)%></strong>
 </div>
</body>
</html>