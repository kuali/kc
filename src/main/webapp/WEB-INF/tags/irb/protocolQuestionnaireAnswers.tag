 <%--
 Copyright 2005-2010 The Kuali Foundation

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

<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="readOnly" value="${!KualiForm.questionnaireHelper.answerQuestionnaire}" scope = "request"/>
<div id="workarea">
<c:forEach items="${KualiForm.questionnaireHelper.answerHeaders}" var="answerHeader" varStatus="status">
	<c:set var="answerHeaderIndex" value="${status.index}" scope="request"/>
	
	<c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].showQuestions"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].showQuestions}" />
		
     <kra-questionnaire:questionnaireAnswers/>
				
				 
</c:forEach>

<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) > 0}">

	${kfunc:registerEditableProperty(KualiForm, "numberOfQuestionaires")}
    <input type="hidden" name="numberOfQuestionaires" id ="numberOfQuestionaires" 
       value = "${fn:length(KualiForm.questionnaireHelper.answerHeaders)}" />
    <kul:panelFooter />
</c:if>

</div>
