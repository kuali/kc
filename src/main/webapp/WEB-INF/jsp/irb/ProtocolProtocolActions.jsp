<%--
 Copyright 2006-2009 The Kuali Foundation

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

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolProtocolActions"
	documentTypeName="ProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocolActions">
  	
<div align="right"><kul:help documentTypeName="ProtocolDocument" pageName="Protocol Actions" /></div>
<kra-irb:protocolRequestAction />
<kra:dataValidation auditActivated="${KualiForm.auditActivated}" topTab="false"/>
<kra-irb:protocolPrint/>
<kra-irb:protocolSummaryViewPrint/>
<kra-irb:protocolCopyProtocol />

<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

<input id="javaScriptFlag" type="hidden" name="javaScriptEnabled" value="0" />
<script language="javascript" src="dwr/interface/ProtocolActionAjaxService.js"></script>
<script language="javascript">enableJavaScript()</script>

</kul:documentPage>
