<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<!-- ProposalDevelopmentMedusa.jsp -->

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentMedusa"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="medusa" >
  	
<kul:tabTop tabTitle="Medusa" defaultOpen="true" tabErrorKey="">
<kra-m:medusa helpParameterNamespace="KC-PD" helpParameterDetailType="Document" helpParameterName="proposalDevelopmentMedusaHelpUrl" />
</kul:tabTop>
<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true"/>

</kul:documentPage>
	
	<script type="text/javascript" src="scripts/medusaView.js"></script>	
	
	
