<%--
 Copyright 2006-2008 The Kuali Foundation

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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="institutionalProposalHome"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="home">
  	
This is the  Institutional Proposal Home - Under Construction

<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-ip:institutionalProposalInstitutionalProposal />
<kra-ip:institutionalProposalSponsorAndProgramInformation />
<kra-ip:institutionalProposalFinancial />
<kra-ip:institutionalProposalGraduateStudents />
<kra-ip:institutionalProposalNotes />
<kra-ip:institutionalProposalDeliveryInfo />
<kra-ip:institutionalProposalKeywords />

<kul:panelFooter />	

 
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>