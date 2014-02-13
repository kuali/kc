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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="institutionalProposalHome"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="home">
  	
<c:set var="readOnly" value="${KualiForm.editingMode['viewOnly']}" scope="request" />
<c:set var="canEdit" value="${KualiForm.editingMode['modifyIP'] && KualiForm.displayEditButton}" scope="request" />

<div align="right"><kul:help documentTypeName="InstitutionalProposalDocument" pageName="Institutional Proposal" /></div>

<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-ip:institutionalProposalInstitutionalProposal />
<kra-ip:institutionalProposalSponsorAndProgramInformation />
<kra-ip:institutionalProposalFinancial />
<kra-ip:institutionalProposalGraduateStudents />
<kra-ip:institutionalProposalNotes />
<kra-ip:institutionalProposalDeliveryInfo />
<kra-ip:institutionalProposalKeywords />

<kul:panelFooter />	

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

<c:if test="${readOnly and canEdit}">
    <c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_edit_temp.gif"/>
    <c:set var="extraButtonProperty" value="methodToCall.editOrVersion"/>
    <c:set var="extraButtonAlt" value="Edit or Version"/>
</c:if>
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" 
                        extraButtonSource="${extraButtonSource}" 
                        extraButtonProperty="${extraButtonProperty}"
                        extraButtonAlt="${extraButtonAlt}" 
                        suppressCancelButton="true"/>

</kul:documentPage>