<%--
 Copyright 2005-2010 The Kuali Foundation

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


<c:set var="protocolExceptions" value="${KualiForm.document.protocol.iacucProtocolExceptions}" />
<c:set var="protocolExceptionAttributes" value="${DataDictionary.IacucProtocolException.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolException"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolException">

  	<div align="right"><kul:help documentTypeName="${KualiForm.docTypeName}" pageName="Protocol Exception" /></div>
  	
	<div id="workarea">
		<kra-iacuc:protocolException businessObjectClassName="org.kuali.kra.iacuc.species.exception.IacucProtocolException"
		                            protocolExceptionAttributes="${protocolExceptionAttributes}"
		                            collectionReference="${protocolExceptions}"
		                            collectionProperty="document.protocolList[0].iacucProtocolExceptions"
		                            action="iacucProtocolException" />
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
