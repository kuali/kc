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
	<c:set target="${KualiForm.documentActionFlags}" property="canSave" value="true"/>
</c:if>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetVersions"
	documentTypeName="BudgetDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="versions"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	>
  	
  	<div align="right"><kul:help documentTypeName="BudgetDocument" pageName="Budget Versions" /></div>

	<kra-b:budgetVersions 
		budgetVersionOverviews="${KualiForm.proposal.developmentProposalList[0].budgetVersionOverviews}"
		pathToVersions="document.proposal.developmentProposalList[0]"
		errorKey="document.proposal.developmentProposalList[0].budgetVersion*,document.proposal.developmentProposalList[0].finalVersionFlag,document.proposal.developmentProposalList[0].documentDescription"
		requestedStartDateInitial="${KualiForm.document.proposal.developmentProposalList[0].requestedStartDateInitial}"
		requestedEndDateInitial="${KualiForm.document.proposal.developmentProposalList[0].requestedEndDateInitial}"
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
