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
		<c:forEach items="${krafn:getOptionList('org.kuali.kra.questionnaire.CoeusSubModuleValuesFinder', paramMap)}" var="option">
				<option value="${option.key}">${option.value}</option>
		 </c:forEach>
	</select> 

</h3>



</html:form>
</body>
</html>
