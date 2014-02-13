 <%--
 Copyright 2005-2013 The Kuali Foundation

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
<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="forceNonTransparent" required="false" %>

<c:if test = "${empty forceNonTransparent}">
	<c:set var = "forceNonTransparent"  value = "false"/>
</c:if> 


<c:set var="readOnly" value="${!bean.answerQuestionnaire}" scope = "request"/>

<c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="status">
	
	<c:set var="prop" value="${property}.answerHeaders[${status.index}].showQuestions"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${bean.answerHeaders[status.index].showQuestions}" />
		
     <kra-questionnaire:questionnaireAnswers bean = "${bean}" property = "${property}" answerHeaderIndex = "${status.index}" forceNonTransparent="${forceNonTransparent}"/>
   </c:forEach>

<c:if test="${fn:length(bean.answerHeaders) > 0}">

	${kfunc:registerEditableProperty(KualiForm, "numberOfQuestionaires")}
    <input type="hidden" name="numberOfQuestionaires" id ="numberOfQuestionaires:${property}" class = "numberOfQuestionnaires" 
       value = "${fn:length(bean.answerHeaders)}" />
    
</c:if>



