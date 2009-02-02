<%--
 Copyright 2006-2008 The Kuali Foundation

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
<%@ page import="java.util.HashMap" %>

<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.Person.attributes}" />
<c:set var="readOnly" value="${not KualiForm.personnelHelper.modifyProtocol}" scope="request" />

<jsp:useBean id="newMap" class="java.util.HashMap" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolPersonnel"
	documentTypeName="ProtocolDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="personnel">
  	
  	<div align="right"><kul:help documentTypeName="ProtocolDocument" pageName="Personnel" /></div>

	<c:set var="viewOnly" value="${not KualiForm.personnelHelper.modifyProtocol}" />
	<kra-irb:protocolAddPersonnelSection/>
	<kra-irb:protocolPersons/>






  <c:if test="${not empty viewOnly && ! viewOnly and fn:length(KualiForm.document.protocol.protocolPersons) > 0}">
  	<c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_deletesel.gif"/>
  	<c:set var="extraButtonProperty" value="methodToCall.deleteProtocolPerson"/>
  	<c:set var="extraButtonAlt" value="Delete a Person"/>
  </c:if>  
  	<p>
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		/>
	</p>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js"></script>

</kul:documentPage>