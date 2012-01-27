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

<kul:documentPage showDocumentInfo="true"
                  htmlFormAction="proposalDevelopmentNotificationEditor"
                  documentTypeName="ProposalDevelopmentDocument"
                  renderMultipart="false"
                  showTabButtons="true"
                  auditCount="0">

<div id="workarea">
    <kra-notification:notificationEditor />
    <kul:panelFooter />
</div>

<div id="globalbuttons" class="globalbuttons">
    <html:image src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_send.gif" styleClass="globalbuttons" property="methodToCall.sendNotification" title="send" alt="send" tabindex="${tabindex}" />
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancelNotification" title="cancel" alt="cancel" tabindex="${tabindex}" />
</div>
  
</kul:documentPage>