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

<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="InstitutionalProposalDocument" pageName="Institutional Proposal" />
</div>

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
