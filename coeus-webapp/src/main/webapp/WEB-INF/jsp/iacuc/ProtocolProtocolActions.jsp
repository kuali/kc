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

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="showActions" value="${empty DocumentPessimisticLockMessages}" scope="request"/>
<c:set var="suppressRoutingControls" value="${KualiForm.actionHelper.canApproveFull || !KualiForm.actionHelper.canApproveOther}" scope="request"/>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>

<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="IacucProtocolDocument" pageName="IACUC Protocol Actions" />
</div>

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
