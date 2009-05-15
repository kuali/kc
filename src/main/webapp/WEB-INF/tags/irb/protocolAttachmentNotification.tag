<%--
 Copyright 2006-2008 The Kuali Foundation

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

<c:set var="protocolAttachmentNotificationAttributes" value="${DataDictionary.ProtocolAttachmentNotification.attributes}" />
<c:set var="notesAndAttachmentsHelper" value="${KualiForm.notesAndAttachmentsHelper}" />
<c:set var="readOnly" value="${!KualiForm.notesAndAttachmentsHelper.modifyProtocol}" />
<c:set var="action" value="protocolNoteAndAttachment" />
<c:set var="attachmentNotifications" value="${KualiForm.document.protocolList[0].attachmentNotifications}"/>

<kul:tab tabTitle="Notifications From Attachments(${fn:length(attachmentNotifications)})" defaultOpen="false" tabErrorKey="notesAndAttachmentsHelper.newAttachmentNotification.*,document.protocol.attachmentNotifications*" transparentBackground="false">
	<div class="tab-container" align="center">
   		<%-- add functionality for dev only - START --%>
   		<h3>
   			<span class="subhead-left">Add Notification Attachment - FOR DEVELOPMENT ONLY</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentNotification" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
        	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentNotification.updateUser" attributeEntry="${protocolAttachmentNotificationAttributes.updateUser}" readOnly="true"/>
	            	</div>
				</td>
               <th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes['fileId']}" noColon="false" />
					</div>
				</th>
       			<td align="left" valign="middle">
              		<div align="left">
              			<c:set var="property" value="notesAndAttachmentsHelper.newAttachmentNotification.newFile" />
              		
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
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentNotification.updateTimestamp" attributeEntry="${protocolAttachmentNotificationAttributes.updateTimestamp}" readOnly="true"/>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" noColon="false" />
					</div>
				</th>
         		<td align="left" valign="middle">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentNotification.actionDate" attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" datePicker="true"/>
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
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentNotification.comments" attributeEntry="${protocolAttachmentNotificationAttributes.comments}"/>
                		<kra:expandedTextArea textAreaFieldName="notesAndAttachmentsHelper.newAttachmentNotification.comments" action="${action}" textAreaLabel="${protocolAttachmentNotificationAttributes.comments.label}" />
	            	</div>
				</td>
         	</tr>
            <tr>
         		<td colspan="4" class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAttachmentNotification.anchor${tabKey}"
						src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
				</td>
         	</tr> 
		</table>
		<%-- add functionality for dev only - END --%>

   	   <h3>
       		<span class="subhead-left">Attachments from Notifications</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.noteattachment.ProtocolAttachmentNotification" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
        	<c:forEach var="attachmentNotification" items="${attachmentNotifications}" varStatus="itrStatus">
				<tr>
					<td rowspan="3">
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
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentNotifications[${itrStatus.index}].actionDate" attributeEntry="${protocolAttachmentNotificationAttributes.actionDate}" datePicker="true" readOnly="true"/>
		            	</div>
					</td>
					<td rowspan="3">
						<div align="center">
							<html:image property="methodToCall.viewAttachmentNotification.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Notification Attachment" onclick="excludeSubmitRestriction = true;"/>
							
							<%-- delete functionality for dev only - START --%>
							<html:image property="methodToCall.deleteAttachmentNotification.line${itrStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
								alt="Delete Notification Attachment"/>
							<%-- delete functionality for dev only - END --%>
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
	         			<%-- using table for positioning - this should be done w/ div and or span...if I get get the css correct--%>
	                	<table cellpadding="0" cellspacing="0" summary="" style="border-style: none;"> 
	                		<tr>
                                 <td style="border-style: none;" align="left" valign="middle">
                                  	<kul:htmlControlAttribute property="document.protocolList[0].attachmentNotifications[${itrStatus.index}].comments" attributeEntry="${protocolAttachmentNotificationAttributes.comments}" readOnly="true"/>
                                 </td>
                                 <td style="border-style: none;" align="left" valign="middle">
                                 	<div align="right">
		               					<input class="tinybutton" type="image"
										src='${ConfigProperties.kra.externalizable.images.url}openreadonly_greenarrow01.png'
										alt="View Expanded Description"
										onclick="return false;"/>
									</div>
                                 </td>
                             </tr>
						</table>
					</td>
	         	</tr>
         	</c:forEach>
		</table>
     </div>		
</kul:tab>