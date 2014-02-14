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
    <input type="hidden" id="questionId" name="questionId" value="${KualiForm.question.questionRefId}"/>
    <input type="hidden" id="question" name="question" value="${KualiForm.question.question}"/>
    <input type="hidden" id="questionTypeId" name="questionTypeId" value="${KualiForm.question.questionTypeId}"/>
    <input type="hidden" id="questionSequence" name="questionSequence" value="${KualiForm.question.sequenceNumber}"/>
    <input type="hidden" id="displayedAnswers" name="displayedAnswers" value="${KualiForm.question.displayedAnswers}"/>
    <input type="hidden" id="maxAnswers" name="maxAnswers" value="${KualiForm.question.maxAnswers}"/>
    <input type="hidden" id="answerMaxLength" name="answerMaxLength" value="${KualiForm.question.answerMaxLength}"/>                        
</html:form>
</body>
</html>