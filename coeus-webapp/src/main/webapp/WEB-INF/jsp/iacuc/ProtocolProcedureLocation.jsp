<%--
 Copyright 2005-2014 The Kuali Foundation

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

<script type="text/javascript">
   var $j = jQuery.noConflict();
   $j(document).ready(function() {
	   populateSelect('getIacucProcedureLocationNames', 'locationTypeCode', 'locationId');
   });
</script>


<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolProcedures"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="procedures">

  	<div align="right"><kul:help documentTypeName="${KualiForm.docTypeName}" pageName="Procedures" /></div>

    <link rel="stylesheet" href="scripts/fancybox2.1.5/jquery.fancybox.css" type="text/css" />

    <div id="workarea">
		<kra-iacuc:protocolProcedureOverviewAndTimeline businessObjectClassName="org.kuali.kra.iacuc.IacucProtocol"/>
		<kra-iacuc:iacucProtocolProcedureLocation/>
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
