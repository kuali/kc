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
<c:set var="extraButtons" value="${KualiForm.extraButtons}" scope="request"/>
<kra-b:swapProposalDevelopmentEditModes/>
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>


<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="${KualiForm.actionPrefix}Parameters"
	documentTypeName="${KualiForm.docTypeName}"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="parameters"
  	extraTopButtons="${KualiForm.extraTopButtons}">
  	
   	<c:choose>
		<c:when test="${proposalBudgetFlag}">
        	<div align="right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetParametersHelp" altText="help"/></div>
			<kra-b:budgetParameters />
		</c:when>
		<c:otherwise>
        	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetParametersHelpUrl" altText="help"/></div>
			<kra-b:awardBudgetParameters />
		</c:otherwise>
	</c:choose>

	<kra-b:budgetPeriodAndTotals /> 
<script language="javascript" src="scripts/kuali_application.js"></script>

<kra-b:swapProposalDevelopmentEditModes/>
<c:if test="${readOnly}">
	<c:set var="extraButtons" value="" scope="request"/>
</c:if>
<kul:documentControls 
		transactionalDocument="true" 
		suppressRoutingControls="true" 
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		suppressCancelButton="true"
/>
</kul:documentPage>
<kra-b:swapProposalDevelopmentEditModes/>
        	