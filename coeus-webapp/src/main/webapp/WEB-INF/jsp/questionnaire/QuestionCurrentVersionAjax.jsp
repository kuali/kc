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

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Questionnaire Ajax for questionnaire question current version</title>
</head>

<body>
<html:form styleId="kualiForm" method="post"
    action="/maintenanceQn.do" enctype="">
    <input type="hidden" id="questionId" name="questionId" value="${KualiForm.question.id}"/>
    <input type="hidden" id="question" name="question" value="${KualiForm.question.question}"/>
    <input type="hidden" id="questionTypeId" name="questionTypeId" value="${KualiForm.question.questionTypeId}"/>
    <input type="hidden" id="questionSequence" name="questionSequence" value="${KualiForm.question.sequenceNumber}"/>
    <input type="hidden" id="displayedAnswers" name="displayedAnswers" value="${KualiForm.question.displayedAnswers}"/>
    <input type="hidden" id="maxAnswers" name="maxAnswers" value="${KualiForm.question.maxAnswers}"/>
    <input type="hidden" id="answerMaxLength" name="answerMaxLength" value="${KualiForm.question.answerMaxLength}"/>
    <kul:csrf />
</html:form>
</body>
</html>
