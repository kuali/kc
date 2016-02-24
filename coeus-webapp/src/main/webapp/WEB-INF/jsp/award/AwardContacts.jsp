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
	htmlFormAction="awardContacts"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="contacts"
  	extraTopButtons="${KualiForm.extraTopButtons}" >

<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<%-- modeled after ProposalDevelopmentKeyPersonnel.jsp --%>
<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="AwardDocument" pageName="Contacts" />
</div>

<div id="workarea">
	<kra-a:awardProjectPersonnel />
	<kra-a:awardUnitContacts />
	<kra-a:awardSponsorContacts />
	<kra-a:awardCentralAdministrationContacts />
	<kul:panelFooter />
</div>



<SCRIPT type="text/javascript">
	var kualiForm = document.forms['KualiForm'];
	var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

</kul:documentPage>
