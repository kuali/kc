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
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>
<c:set var="permissionsUserAttributes" value="${DataDictionary.PermissionsUser.attributes}" />
<c:set var="modifyPermissions" value="${KualiForm.disclosureActionHelper.maintainReviewers}" />
<c:set var="usageSectionId" value="<%=org.kuali.kra.infrastructure.Constants.COI_NOTEPAD_DISCLOSURE_REVIEWER_SECTION_ID%>" />
<c:if test="${topTab == true}">
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
</c:if>


<c:set var="disclosureAssignedToReviewer" value="${KualiForm.disclosureActionHelper.disclosureAssignedToReviewer}" />

<kul:tabTop tabTitle="Reviewer Actions" defaultOpen="false" tabErrorKey="disclosureActionHelper.newCoiUserRole.*, coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.*, coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.*">
	<kra-coi:coiNotes usageSectionId = "${usageSectionId}"/>
	<kra-coi:coiAttachments usageSectionId = "${usageSectionId}"/>
    <c:if test="${disclosureAssignedToReviewer}">
		<kra-coi:completeCoiReviewerAction/>
    </c:if>
</kul:tabTop>

