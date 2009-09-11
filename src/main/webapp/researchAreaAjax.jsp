<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Research Area Hierarchy Ajax</title>

</head>

<body>
<html:form styleId="kualiForm" method="post"
    action="/researchAreaAjax.do" enctype=""
    onsubmit="return hasFormAlreadyBeenSubmitted();"> 

<input type="text" id = "researchAreaCode" name="researchAreaCode"   value="${ResearchAreasForm.researchAreaCode}"/>
<input type="text" id = "addRA" name="addRA"   value="${ResearchAreasForm.addRA}"/>
${ResearchAreasForm.researchAreas}



</html:form>
</body>
</html>
