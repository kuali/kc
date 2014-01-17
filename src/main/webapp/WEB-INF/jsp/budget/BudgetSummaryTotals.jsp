<%--
 Copyright 2005-2014 The Kuali Foundation

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
<c:set var="extraTopButtons" value="${KualiForm.extraTotalsTopButtons}" scope="request"/>
<c:set var="documentTypeName" value="reloadWithoutWarning" scope="request"/>
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
<c:if test="${not proposalBudgetFlag}">
	<c:set target="${KualiForm.documentActions}" property="canReload" value="true"/>
	<c:set var="documentTypeName" value="${KualiForm.headerDispatch}"/>
</c:if> 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}SummaryTotals"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${documentTypeName}" 
  	headerTabActive="summaryTotals"
  	extraTopButtons="${extraTopButtons}">
  	
   	<c:choose>
		<c:when test="${proposalBudgetFlag}">
         	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Summary" /></div>
			<kra-b:budgetSummaryTotals headerDispatch="reloadWithoutWarning" /> 
		</c:when>
		<c:otherwise>
		  	<div align="right"><kul:help documentTypeName="AwardBudgetDocument" pageName="Summary" /></div>
			<kra-b:awardBudgetSummaryTotals/> 
		</c:otherwise>
	</c:choose>
<kul:documentControls 
		transactionalDocument="false" 
		suppressRoutingControls="true" 
		extraButtons="${extraButtons}"
		viewOnly="${proposalBudgetFlag}"
		suppressCancelButton="true"
/>

</kul:documentPage>
