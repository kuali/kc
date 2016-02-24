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

<c:set var="protocolAttachmentNotificationAttributes" value="${DataDictionary.ProtocolAttachmentNotification.attributes}" />
<c:set var="notesAttachmentsHelper" value="${KualiForm.notesAttachmentsHelper}" />
<c:set var="readOnly" value="${!KualiForm.notesAttachmentsHelper.modifyProtocol}" />
<c:set var="action" value="protocolNoteAndAttachment" />
<c:set var="attachmentNotifications" value="${KualiForm.document.protocolList[0].attachmentNotifications}"/>
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_ATTACHMENT_NOTIFICATION_COMMENTS%>" />

<kul:tab tabTitle="Notifications From Attachments" tabItemCount="${fn:length(attachmentNotifications)}" defaultOpen="false" tabErrorKey="notesAttachmentsHelper.newAttachmentNotification.*,document.protocol.attachmentNotifications*" transparentBackground="false">
	<div class="tab-container" align="center">
   		<%-- add functionality for dev only - START --%>
   		<h3>
   			<span class="subhead-left">Add Notification Attachment - <em>FOR DEVELOPMENT ONLY</em></span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentNotification" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
       		<tbody class="addline">
        	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentNotification.updateUser" attributeEntry="${protocolAttachmentNotificationAttributes.updateUser}" readOnly="true"/>
	            	</div>
				</td>
               <th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes['fileId']}" noColon="false" />
					</div>
				</th>
       			<td align="left" valign="middle">
              		<div align="left">
              			<c:set var="property" value="notesAttachmentsHelper.newAttachmentNotification.newFile" />
              		
              		    <%-- attachment file error handling logic start--%>
               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
               				<%-- highlighting does not work in firefox but does in ie... --%>
               				<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
               			<%-- attachment file error handling logic start--%>
              		
              			<html:file property="${property}" style="${textStyle}"/>
           			</div>
				</td>
         	</tr>
         	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentNotification.updateTimestamp" attributeEntry="${protocolAttachmentNotificationAttributes.updateTimestamp}" readOnly="true"/>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" noColon="false" />
					</div>
				</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentNotification.actionDate" attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" />
	            	</div>
				</td>
         	</tr>
            <tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.comments}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAttachmentsHelper.newAttachmentNotification.comments" attributeEntry="${protocolAttachmentNotificationAttributes.comments}"/>
	            	</div>
				</td>
         	</tr>
            <tr>
         		<td colspan="4" class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAttachmentNotification.anchor${tabKey}"
						src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
					</div>
				</td>
         	</tr> 
         	</tbody>
		</table>
		<%-- add functionality for dev only - END --%>

   	   <h3>
       		<span class="subhead-left">Attachments from Notifications</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentNotification" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
        	<c:forEach var="attachmentNotification" items="${attachmentNotifications}" varStatus="itrStatus">
				<tr>
					<td rowspan="2">
	         			<div align="center">
	                		${itrStatus.index + 1}
		            	</div>
	         		</td>
					<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.updateTimestamp}" noColon="false" />
	         			</div>
	         		</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentNotifications[${itrStatus.index}].updateTimestamp" attributeEntry="${protocolAttachmentNotificationAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
					
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" noColon="false" />
						</div>
					</th>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentNotifications[${itrStatus.index}].actionDate" attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}"  readOnly="true"/>
		            	</div>
					</td>
					<td rowspan="2">
						<div align="center">
							<html:image property="methodToCall.viewAttachmentNotification.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Notification Attachment" onclick="excludeSubmitRestriction = true;"/>
							
							<%-- delete functionality for dev only - START --%>
							<html:image property="methodToCall.deleteAttachmentNotification.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
								alt="Delete Notification Attachment - FOR DEVELOPMENT ONLY"/>
							<%-- delete functionality for dev only - END --%>
							<div><em>DELETE - DEV ONLY</em></div>
						</div>
	         		</td>
				</tr>
				<tr>
					<th>
	         			<div align="right">
	         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.comments}" noColon="false" />
	         			</div>
	         		</th>
	         		<td colspan="3" align="left" valign="middle">
	         			<kra:truncateComment textAreaFieldName="document.protocolList[0].attachmentNotifications[${itrStatus.index}].comments" action="${action}" textAreaLabel="${protocolAttachmentNotificationAttributes.comments.label}"
    	                	textValue="${attachmentNotification.comments}" displaySize="${commentDisplayLength}"/>
					</td>
	         	</tr>
         	</c:forEach>
		</table>
     </div>		
</kul:tab>
