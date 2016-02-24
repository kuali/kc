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
	htmlFormAction="subAwardMedusa"
	documentTypeName="SubAwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="medusa">

<kul:tabTop tabTitle="Medusa" defaultOpen="true" tabErrorKey="">
<kra-m:medusa helpParameterNamespace="KC-SUBAWARD" helpParameterDetailType="Document" helpParameterName="subAwardMedusaHelpUrl" />

</kul:tabTop>
<kul:panelFooter />
<c:choose>
	<c:when test="${not KualiForm.editingMode['viewOnly']}">
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />
	</c:when>
	<c:otherwise>
		<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" viewOnly= "true" suppressCancelButton="true" />
	</c:otherwise>
</c:choose>

	<script type="text/javascript" src="scripts/medusaView.js"></script>	
	
</kul:documentPage>
