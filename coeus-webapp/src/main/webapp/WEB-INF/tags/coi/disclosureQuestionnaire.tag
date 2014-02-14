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

<kul:tab defaultOpen="${fn:length(KualiForm.screeningQuestionnaireHelper.answerHeaders) > 0}" tabTitle="Screening Questionnaire" transparentBackground="false" 
	auditCluster="coiQuestionnaireKey" tabAuditKey="screeningQuestionnaireHelper.*" useRiceAuditMode="true"
    tabErrorKey="" >
    
    <c:set var="answerHeaderIndex" value="0" />
	<c:set var="property" value="screeningQuestionnaireHelper" />
	<c:set var="bean" value="${KualiForm.screeningQuestionnaireHelper}" />
			
	<c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="status">
         <div class="tab-container" align="center">
             <kra-questionnaire:questionnaireAnswersInnerTab bean="${bean}" property="${property}" 
             	answerHeaderIndex="${status.index}" parentTab="Questionnaire"/>
         </div>					 
	</c:forEach>
				
</kul:tab>
<kul:tab defaultOpen="true" tabTitle="Questionnaire" transparentBackground="false" 
	auditCluster="coiQuestionnaireKey" tabAuditKey="disclosureQuestionnaireHelper.*" useRiceAuditMode="true"
    tabErrorKey="" >
    
    <c:set var="answerHeaderIndex" value="0" />
	<c:set var="property" value="disclosureQuestionnaireHelper" />
	<c:set var="bean" value="${KualiForm.disclosureQuestionnaireHelper}" />
			
	<c:forEach items="${bean.answerHeaders}" var="answerHeader" varStatus="status">
       <c:choose>
           <c:when test="${KualiForm.document.coiDisclosureList[0].updateEvent or (KualiForm.document.coiDisclosureList[0].annualEvent and KualiForm.document.coiDisclosureList[0].annualUpdate)}">
              <c:if  test="${answerHeader.moduleSubItemCode == '14' or answerHeader.moduleSubItemCode == '6'}">
		         <div class="tab-container" align="center">
		             <kra-questionnaire:questionnaireAnswersInnerTab bean="${bean}" property="${property}" 
		             	answerHeaderIndex="${status.index}" parentTab="Questionnaire"/>
		         </div>					 
              </c:if>
           </c:when>
           <c:otherwise>
		         <div class="tab-container" align="center">
		             <kra-questionnaire:questionnaireAnswersInnerTab bean="${bean}" property="${property}" 
		             	answerHeaderIndex="${status.index}" parentTab="Questionnaire"/>
		         </div>					 
           </c:otherwise>
       </c:choose>
	</c:forEach>
				
</kul:tab>