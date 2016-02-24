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

<kul:documentPage showDocumentInfo="true"
                  htmlFormAction="institutionalProposalNotificationEditor"
                  documentTypeName="InstitutionalProposalDocument"
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
