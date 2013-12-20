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

<kra-b:swapProposalDevelopmentEditModes/>

	
<kra-b:swapProposalDevelopmentEditModes/>
<c:set var="readOnly" value="${(not KualiForm.editingMode['modifyBudgets']) && ( not parentReadOnlyFlag )}" scope="request" />
<c:set var="viewOnly" value="${readOnly}" scope="request" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}DistributionAndIncome"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="distributionAndIncome"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	showTabButtons="true">
  	
	 <c:choose>
		 <c:when test="${proposalBudgetFlag}">
        	<div align="right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetDistributionAndIncomeHelp" altText="help"/></div>
         </c:when>
         <c:otherwise>
        	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetDistributionAndIncomeHelpUrl" altText="help"/></div>
        </c:otherwise>
    </c:choose>
	
	<div align="center">
		<kra-b:budgetCostSharing />
		<kra-b:budgetUnrecoveredFandA />
		<kra-b:budgetProjectIncome />
		<kul:panelFooter />
	</div>

	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${readOnly}"
		suppressCancelButton="true"
		/>

</kul:documentPage>
<kra-b:swapProposalDevelopmentEditModes/>