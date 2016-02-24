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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee}"  scope="request" />

<kul:documentPage 
    showDocumentInfo="true"
	htmlFormAction="iacucCommitteeMembership" 
	documentTypeName="CommonCommitteeDocument"
	renderMultipart="false" 
	showTabButtons="true" 
	auditCount="0"
	headerDispatch="${KualiForm.headerDispatch}"
	headerTabActive="committeeMembership">

	<div align="right"><kul:help documentTypeName="CommonCommitteeDocument" pageName="Members" /></div>
 
    <kra-committee:committeeAddMembershipSection readOnly="${readOnly}" />
    
    <kra-committee:committeeMemberships readOnly="${readOnly}"
    researchAreaReference = "org.kuali.kra.iacuc.IacucResearchArea" 
    membershipRoleValuesFinderClassName="org.kuali.kra.iacuc.committee.keyvalue.IacucMembershipRoleValuesFinder"/>

    <c:if test="${!readOnly && fn:length(KualiForm.document.committee.committeeMemberships) > 0}">
        <c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_deletesel.gif"/>
        <c:set var="extraButtonProperty" value="methodToCall.deleteCommitteeMembership"/>
        <c:set var="extraButtonAlt" value="Delete a Person"/>
    </c:if>  

	<kul:documentControls 
	    transactionalDocument="false"
		suppressRoutingControls="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}" />

	<script type="text/javascript">
        var kualiForm = document.forms['KualiForm'];
        var kualiElements = kualiForm.elements;
    </script>
	<script language="javascript" src="dwr/interface/UnitService.js"></script>
</kul:documentPage>
