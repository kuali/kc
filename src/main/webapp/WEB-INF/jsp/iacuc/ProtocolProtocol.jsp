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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />
<c:set var="textAreaFieldName" value="document.protocolList[0].title" />
<c:set var="action" value="protocolProtocol" />
<c:set var="nonEmpFlag" value="false" />
<c:set var="className" value="org.kuali.kra.iacuc.document.IacucProtocolDocument" />
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolProtocol"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="iacucProtocol">
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
 

  	
<div align="right"><kul:help documentTypeName="IacucProtocolDocument" pageName="Protocol" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-protocol:protocolRequiredFields
    protocolDocumentAttributes="${protocolDocumentAttributes}" 
    protocolAttributes="${protocolAttributes}"
    textAreaFieldName="${textAreaFieldName}"
    action="${action}"
    nonEmpFlag="${nonEmpFlag}"
    className="${className}"/>

    

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>

<script language="javascript" src="dwr/interface/ProtocolFundingSourceService.js"></script>
<!-- <script language="javascript" src="dwr/interface/UnitService.js"></script> -->
</kul:documentPage>
