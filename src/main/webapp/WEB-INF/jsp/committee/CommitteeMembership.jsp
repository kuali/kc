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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="${!KualiForm.membershipHelper.modifyCommittee or KualiForm.lookupHelper.viewOnly}"  scope="request" />

<kul:documentPage 
    showDocumentInfo="true"
	htmlFormAction="committeeMembership" 
	documentTypeName="CommitteeDocument"
	renderMultipart="false" 
	showTabButtons="true" 
	auditCount="0"
	headerDispatch="${KualiForm.headerDispatch}"
	headerTabActive="committeeMembership">

	<div align="right"><kul:help documentTypeName="CommitteeDocument" pageName="CommitteeMembership" /></div>
 
    <kra-committee:committeeAddMembershipSection readOnly="${readOnly}" />
    
    <kra-committee:committeeMemberships readOnly="${readOnly}" />

    <c:if test="${!readOnly && fn:length(KualiForm.document.committee.committeeMemberships) > 0}">
        <c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_deletesel.gif"/>
        <c:set var="extraButtonProperty" value="methodToCall.deleteCommitteeMembership"/>
        <c:set var="extraButtonAlt" value="Delete a Person"/>
    </c:if>  

	<kul:documentControls 
	    transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}" />

	<script type="text/javascript">
        var kualiForm = document.forms['KualiForm'];
        var kualiElements = kualiForm.elements;
    </script>
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<script language="javascript" src="dwr/interface/UnitService.js"></script>
</kul:documentPage>
