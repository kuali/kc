<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentBudgetVersions"
	documentTypeName="ProposalDevelopmentDocument"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="budgetVersions">
  	
  	<kra-b:budgetVersions 
  		budgetVersionOverviews="${KualiForm.document.budgetVersionOverviews}" 
  		pathToVersions="document"
  		errorKey="document.budgetVersion*"
  		requestedStartDateInitial="${KualiForm.document.requestedStartDateInitial}"
		requestedEndDateInitial="${KualiForm.document.requestedEndDateInitial}"
		/>
  	
  	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>
		
</kul:documentPage>