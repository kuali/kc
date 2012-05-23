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
<%@ page import="java.util.HashMap" %>

<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.KcPerson.attributes}" />
<c:set var="protocolUnitsAttributes" value="${DataDictionary.ProtocolUnit.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<c:set var="protocolAttachmentPersonnelAttributes" value="${DataDictionary.ProtocolAttachmentPersonnel.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucPersonnel"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="personnel">
  	
    <kra-protocol:protocolAddPersonnelSection protocolPersonAttributes="${protocolPersonAttributes}"/>
    <kra-protocol:protocolPersons 
        protocolAttributes="${protocolAttributes}"
        protocolPersonAttributes="${protocolPersonAttributes}"
        personAttributes="${personAttributes}"
        protocolUnitsAttributes="${protocolUnitsAttributes}"
        unitAttributes="${unitAttributes}"
        protocolAttachmentPersonnelAttributes="${protocolAttachmentPersonnelAttributes}"
        attachmentFileAttributes="${attachmentFileAttributes}"
        optionListClass="org.kuali.kra.iacuc.personnel.ProtocolPersonRoleValuesFinder"
        protocolAttachmentTypeByGroupValuesFinder="org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentTypeByGroupValuesFinder"
        />
	<c:if test="${not empty viewOnly && ! viewOnly and fn:length(KualiForm.document.protocolList[0].protocolPersons) > 0}">
	    <c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_deletesel.gif"/>
	    <c:set var="extraButtonProperty" value="methodToCall.deleteProtocolPerson"/>
	    <c:set var="extraButtonAlt" value="Delete a Person"/>
	</c:if>     
    <kul:documentControls 
        transactionalDocument="false"
        suppressRoutingControls="true"
        extraButtonSource="${extraButtonSource}"
        extraButtonProperty="${extraButtonProperty}"
        extraButtonAlt="${extraButtonAlt}"
        viewOnly="${KualiForm.editingMode['viewOnly']}"
        />    

<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js"></script>

</kul:documentPage>
