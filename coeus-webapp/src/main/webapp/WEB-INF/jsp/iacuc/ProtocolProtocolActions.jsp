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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolActions"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolActions">
  	
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
    <link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
    <script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script> 
    <script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script>
    <script type="text/javascript" src="scripts/kuali_application.js"></script>

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="showActions" value="${empty DocumentPessimisticLockMessages}" scope="request"/>
<c:set var="suppressRoutingControls" value="${KualiForm.actionHelper.canApproveFull || !KualiForm.actionHelper.canApproveOther}" scope="request"/>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>

<div align="right"><kul:help documentTypeName="IacucProtocolDocument" pageName="IACUC Protocol Actions" /></div>

<kra-iacuc:protocolRequestAction />
<kra-iacuc:iacucProtocolSummaryPrint/>
<kra-iacuc:protocolSummaryViewPrint/>
<kra-iacuc:iacucProtocolCopyProtocol />		
<kul:routeLog /> 
<c:if test="${showActions}" >
    <kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false" helpParameterNamespace="KC-IACUC" helpParameterName="protocolDataValidationHelp" helpParameterDetailType="Document"/>
    <kul:adHocRecipients />
	<kul:superUserActions showTab="false"/>	
</c:if>
<kul:panelFooter />
	            
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="${suppressRoutingControls}"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		extraButtons="${extraButtons}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>
</kul:documentPage>
