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


<c:set var="protocolSpeciesList" value="${KualiForm.document.protocol.iacucProtocolSpeciesList}" />
<c:set var="protocolSpeciesAttributes" value="${DataDictionary.IacucProtocolSpecies.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolSpeciesAndGroups"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="speciesAndGroups">

  	<div align="right">
		<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
		<kul:help documentTypeName="${KualiForm.docTypeName}" pageName="Species/Groups" />
	</div>
  	
	<div id="workarea">
		<kra-iacuc:speciesAndGroups businessObjectClassName="org.kuali.kra.iacuc.species.IacucProtocolSpecies"
		                            protocolSpeciesAttributes="${protocolSpeciesAttributes}"
		                            collectionReference="${protocolSpeciesList}"
		                            collectionProperty="document.protocolList[0].iacucProtocolSpeciesList"
		                            action="iacucProtocolSpeciesAndGroups" />
		<kul:panelFooter />
	</div>


	<script type="text/javascript">
	   var $j = jQuery.noConflict();
	</script>
	
	<SCRIPT type="text/javascript">
	var kualiForm = document.forms['KualiForm'];
	var kualiElements = kualiForm.elements;
	</SCRIPT>
	<script language="javascript" src="scripts/kuali_application.js"></script>

	<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" />
</kul:documentPage>
