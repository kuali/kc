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
<%@ page language="java" %> 
<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="moduleCode" value="${KualiForm.moduleCode}" />


<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Research Area Hierarchy Ajax</title>

</head>

<body>
<html:form styleId="kualiForm" method="post"
    action="/maintenanceQn.do" enctype=""
    onsubmit="return hasFormAlreadyBeenSubmitted();"> 
<h3>
    <select title="Sub Module" class="fixed-size-select" id="newQuestionnaireUsage.moduleSubItemCode"  name="newQuestionnaireUsage.moduleSubItemCode">
		<c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.questionnaire.impl.core.CoeusSubModuleValuesFinder', paramMap)}" var="option">
				<option value="${option.key}">${option.value}</option>
		 </c:forEach>
	</select> 

</h3>


	<kul:csrf />
</html:form>
</body>
</html>
