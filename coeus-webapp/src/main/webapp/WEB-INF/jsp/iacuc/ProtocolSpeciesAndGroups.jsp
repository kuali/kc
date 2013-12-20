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

  	<div align="right"><kul:help documentTypeName="${KualiForm.docTypeName}" pageName="Species/Groups" /></div>
  	
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
	<!--   <script language="javascript" src="dwr/interface/UnitService.js"></script> -->

	<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" />
</kul:documentPage>
