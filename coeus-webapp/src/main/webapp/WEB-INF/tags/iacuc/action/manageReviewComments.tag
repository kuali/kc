<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canManageReviewComments}">
      <kra-iacuc-action:reviewComments bean="${KualiForm.actionHelper.protocolManageReviewCommentsBean.reviewCommentsBean}"
              property="actionHelper.protocolManageReviewCommentsBean.reviewCommentsBean"
              action="${action}"
              taskName="protocolManageReviewComments" 
              tabCustomTitle="Manage Review Comments" 
              methodToCall="manageComments" />
              
      <kra-iacuc-action:reviewAttachments bean="${KualiForm.actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean}"
              property="actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean"
              action="${action}" 
              taskName="protocolManageReviewComments" 
              tabCustomTitle="Manage Review Attachments" 
              methodToCall="manageAttachments" />
</kra:permission>
