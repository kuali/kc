<%--
 Copyright 2006-2009 The Kuali Foundation

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
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />

<c:if test="${KualiForm.editingMode['modifyCompletedBudgets']}">
	<c:set target="${KualiForm.documentActions}" property="canSave" value="true"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentBudgetVersions"
	documentTypeName="ProposalDevelopmentDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="budgetVersions">
  	
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Budget Versions" /></div>
  	
  	<kra-b:budgetVersions 
  		budgetDocumentVersions="${KualiForm.document.budgetDocumentVersions}" 
  		pathToVersions="document"
  		errorKey="document.developmentProposalList[0].budgetVersion*,document.developmentProposalList[0].finalVersionFlag"
  		requestedStartDateInitial="${KualiForm.document.developmentProposal.requestedStartDateInitial}"
		requestedEndDateInitial="${KualiForm.document.developmentProposal.requestedEndDateInitial}"
		/>
  	
  	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		/>
		
</kul:documentPage>
