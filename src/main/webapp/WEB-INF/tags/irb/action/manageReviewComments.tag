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

<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canManageReviewComments}">
      <kra-irb-action:reviewComments bean="${KualiForm.actionHelper.protocolManageReviewCommentsBean.reviewCommentsBean}"
              property="actionHelper.protocolManageReviewCommentsBean.reviewCommentsBean"
              action="${action}"
              taskName="protocolManageReviewComments" 
              tabCustomTitle="Manage Review Comments" 
              methodToCall="manageComments" />
      <kra-irb-action:reviewAttachments bean="${KualiForm.actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean}"
              property="actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean"
              action="${action}"
              taskName="protocolManageReviewComments" 
              tabCustomTitle="Manage Review Attachments" 
              methodToCall="manageAttachments" />
</kra:permission>
