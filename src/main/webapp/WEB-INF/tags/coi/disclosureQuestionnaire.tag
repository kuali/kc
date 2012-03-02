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

<kul:tab defaultOpen="true" tabTitle="Questionnaire" transparentBackground="false"
    tabErrorKey="" >
    
    <c:set var="answerHeaderIndex" value="0" />
	<c:set var="property" value="disclosureQuestionnaireHelper" />
	<c:set var="bean" value="${KualiForm.disclosureQuestionnaireHelper}" />
			
			
	<c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="status">
		<div class="tab-container" align="center">
			<c:set var="prop" value="${property}.answerHeaders[${status.index}].showQuestions"/>
			${kfunc:registerEditableProperty(KualiForm, prop)}
			<input type="hidden" name="${prop}" id ="${prop}" 
		           value = "${bean.answerHeaders[status.index].showQuestions}" />
		    <kra-questionnaire:questionnaireAnswersInnerTabSinglePanel bean = "${bean}" property = "${property}" answerHeaderIndex = "${status.index}" parentTab="Questionnaire"/>
		</div>					 
	</c:forEach>
				
</kul:tab>