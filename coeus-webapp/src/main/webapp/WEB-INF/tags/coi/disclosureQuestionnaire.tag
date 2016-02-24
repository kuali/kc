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
