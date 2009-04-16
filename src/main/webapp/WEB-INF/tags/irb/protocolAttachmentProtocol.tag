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

<c:set var="protocolAttachmentProtocolAttributes" value="${DataDictionary.ProtocolAttachmentProtocol.attributes}" />
<c:set var="notesAndAttachmentsHelper" value="${KualiForm.notesAndAttachmentsHelper}" />
<c:set var="readOnly" value="${!KualiForm.notesAndAttachmentsHelper.modifyProtocol}" />
<c:set var="action" value="protocolNoteAndAttachment" />
<c:set var="attachmentProtocols" value="${KualiForm.document.protocol.attachmentProtocols}"/>

<kul:tab tabTitle="Protocol Attachments(${fn:length(KualiForm.document.protocol.attachmentProtocols)})" defaultOpen="true" tabErrorKey="notesAndAttachmentsHelper.newAttachmentProtocol.*" transparentBackground="true">
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Protocol Attachment</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ProtocolAttachmentProtocol" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
         	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.typeCode}" noColon="false" forceRequired="true"/>
         			</div>
         		</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<c:set var="property" value="notesAndAttachmentsHelper.newAttachmentProtocol.typeCode" />
                	
               			<%-- attachment type finder logic start--%>
							<jsp:useBean id="typeParams" class="java.util.HashMap"/>
							<c:set target="${typeParams}" property="groupCode" value="1" />
							<c:set target="${typeParams}" property="filterTypes" value="${KualiForm.document.protocol.attachmentProtocols}" />
							<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParams)}" />
						<%-- attachment type finder logic end --%>
						
               			<%-- attachment type error handling logic start--%>
               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
               				<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
               			<%-- attachment type error handling logic start--%>
               			
               			<html:select property="${property}" style="${textStyle}">
               				<html:options collection="options" labelProperty="label" property="key" />
               			</html:select>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.fileId}" noColon="false"  forceRequired="true"/>
					</div>
				</th>
       			<td align="left" valign="middle" class="infoline">
              		<div align="left">
              			<c:set var="property" value="notesAndAttachmentsHelper.newAttachmentProtocol.newFile" />
              		
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
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.statusCode}" noColon="false" forceRequired="true"/>
         			</div>
         		</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.statusCode" attributeEntry="${protocolAttachmentProtocolAttributes.statusCode}"/>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" noColon="false" />
					</div>
				</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.contactName" attributeEntry="${protocolAttachmentProtocolAttributes.contactName}"/>
	            	</div>
				</td>
         	</tr>
         	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.updateUser" attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" readOnly="true"/>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" noColon="false" />
					</div>
				</th>
         			<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.contactEmailAddress" attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}"/>
	            	</div>
				</td>
         	</tr>
         	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.updateTimestamp" attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" readOnly="true"/>
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" noColon="false" />
					</div>
				</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.contactPhoneNumber" attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}"/>
	            	</div>
				</td>
         	</tr>
         	<tr>
         		<th>
         			<div align="right">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.comments}" noColon="false" />
         			</div>
         		</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.comments" attributeEntry="${protocolAttachmentProtocolAttributes.comments}"/>
                		<kra:expandedTextArea textAreaFieldName="notesAndAttachmentsHelper.newAttachmentProtocol.comments" action="${action}" textAreaLabel="${protocolAttachmentProtocolAttributes.comments.label}" />
	            	</div>
				</td>
				<th>
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.description}" noColon="false" forceRequired="true"/>
					</div>
				</th>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentProtocol.description" attributeEntry="${protocolAttachmentProtocolAttributes.description}"/>
                		<kra:expandedTextArea textAreaFieldName="notesAndAttachmentsHelper.newAttachmentProtocol.description" action="${action}" textAreaLabel="${protocolAttachmentProtocolAttributes.description.label}" />
	            	</div>
				</td>
         	</tr>
         	<tr>
         		<td colspan="4" class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAttachmentProtocol.anchor${tabKey}"
						src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
				</td>
         	</tr>
		</table>
		
		<c:forEach var="attachmentProtocol" items="${KualiForm.document.protocol.attachmentProtocols}" varStatus="status">
			<kul:innerTab tabTitle="${attachmentProtocol.type.description} - ${attachmentProtocol.status.description}" parentTab="Protocol Attachments(${size})" defaultOpen="false" tabErrorKey="document.protocol.attachmentProtocol*">
				<div class="innerTab-container" align="left">
            		<table class=tab cellpadding=0 cellspacing="0" summary="">
						<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.typeCode}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].typeCode" attributeEntry="${protocolAttachmentProtocolAttributes.typeCode}" readOnly="true" readOnlyAlternateDisplay="${attachmentProtocol.type.description}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.fileId}" noColon="false" />
								</div>
							</th>
			       			<td align="left" valign="middle" class="infoline">
			              		<div align="left" style="display: none;" id="attachmentProtocolFile${status.index}">
			              			<html:file property="document.protocol.attachmentProtocol[${status.index}].newFile" />
			           			</div>
			           			<div align="left" id="attachmentProtocolFileName${status.index}">
			              			${attachmentProtocol.file.name}
			           			</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.statusCode}" noColon="false" forceRequired="true"/>
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].statusCode" attributeEntry="${protocolAttachmentProtocolAttributes.statusCode}"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactName}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].contactName" attributeEntry="${protocolAttachmentProtocolAttributes.contactName}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].updateUser" attributeEntry="${protocolAttachmentProtocolAttributes.updateUser}" readOnly="true"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}" noColon="false" />
								</div>
							</th>
			         			<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].contactEmailAddress" attributeEntry="${protocolAttachmentProtocolAttributes.contactEmailAddress}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].updateTimestamp" attributeEntry="${protocolAttachmentProtocolAttributes.updateTimestamp}" readOnly="true"/>
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}" noColon="false" />
								</div>
							</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].contactPhoneNumber" attributeEntry="${protocolAttachmentProtocolAttributes.contactPhoneNumber}"/>
				            	</div>
							</td>
			         	</tr>
			         	<tr>
			         		<th>
			         			<div align="right">
			         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.comments}" noColon="false" />
			         			</div>
			         		</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].comments" attributeEntry="${protocolAttachmentProtocolAttributes.comments}"/>
			                		<kra:expandedTextArea textAreaFieldName="document.protocol.attachmentProtocol[${status.index}].comments" action="${action}" textAreaLabel="${protocolAttachmentProtocolAttributes.comments.label}" />
				            	</div>
							</td>
							<th>
								<div align="right">
									<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentProtocolAttributes.description}" noColon="false" forceRequired="true"/>
								</div>
							</th>
			         		<td align="left" valign="middle" class="infoline">
			                	<div align="left">
			                		<kul:htmlControlAttribute property="document.protocol.attachmentProtocol[${status.index}].description" attributeEntry="${protocolAttachmentProtocolAttributes.description}"/>
			                		<kra:expandedTextArea textAreaFieldName="document.protocol.attachmentProtocol[${status.index}].description" action="${action}" textAreaLabel="${protocolAttachmentProtocolAttributes.description.label}" />
				            	</div>
							</td>
			         	</tr>
						<tr>
			         		<td colspan="4" class="infoline">
								<div align="center">
									<html:image property="methodToCall.viewAttachmentProtocol.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Protocol Attachment" onclick="excludeSubmitRestriction = true;"/>
									<input class="tinybutton" type="image" alt="Replace"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' class="tinybutton"
										alt="Replace Protocol Attachment"
										onclick="document.getElementById('attachmentProtocolFile${status.index}').style.display = 'block';
										document.getElementById('attachmentProtocolFileName${status.index}').style.display = 'none';
										return false;"/>
									<html:image property="methodToCall.deleteAttachmentProtocol.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
										alt="Delete Protocol Attachment"/>
								</div>
							</td>
			         	</tr>
         			</table>
         		</div>
         	</kul:innerTab>
		</c:forEach>
     </div>		
</kul:tab>