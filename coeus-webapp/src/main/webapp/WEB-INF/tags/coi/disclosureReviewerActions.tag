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
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>
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

