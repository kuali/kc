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

<c:set var="protocolAttachmentPersonnelAttributes" value="${DataDictionary.ProtocolAttachmentPersonnel.attributes}" />
<c:set var="notesAndAttachmentsHelper" value="${KualiForm.notesAndAttachmentsHelper}" />
<c:set var="readOnly" value="${!KualiForm.notesAndAttachmentsHelper.modifyProtocol}" />
<c:set var="action" value="protocolNoteAndAttachment" />
<c:set var="attachmentPersonnels" value="${KualiForm.document.protocol.attachmentPersonnels}"/>

<kul:tab tabTitle="Personnel Attachments(${fn:length(KualiForm.document.protocol.attachmentPersonnels)})" defaultOpen="true" tabErrorKey="" transparentBackground="false">
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Personnel Attachment</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ProtocolAttachmentPersonnel" altText="help"/></span>
       </h3>
       <table cellpadding="4" cellspacing="0" summary="">
         	<tr>
         	    <th>
         	    	&nbsp;
         	    </th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" noColon="false" />
         			</div>
         		</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.description}" noColon="false" />
					</div>
				</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.fileId}" noColon="false"  />
					</div>
				</th>
         		<th>
					<div align="center">
						Actions
					</div>
				</th>
             </tr>
             <tr>
                <td align="center" valign="middle" class="infoline">
                	<div align="center">
                		Add:
	            	</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.updateTimestamp" attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" readOnly="true"/>
	            	</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.updateUser" attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" readOnly="true"/>
	            	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.personId" attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" />
	            	</div>
				</td>
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<%-- attachment type finder logic start--%>
						<jsp:useBean id="typeParams" class="java.util.HashMap"/>
						<c:set target="${typeParams}" property="groupCode" value="2" />
						<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParams)}" />
						<%-- attachment type finder logic end --%>
               				
               			<html:select property="notesAndAttachmentsHelper.newAttachmentProtocol.typeCode">
               				<html:options collection="options" labelProperty="label" property="key" />
               			</html:select>
	            	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.description" attributeEntry="${protocolAttachmentPersonnelAttributes.description}"/>
                		<kra:expandedTextArea textAreaFieldName="notesAndAttachmentsHelper.newAttachmentPersonnel.description" action="${action}" textAreaLabel="${protocolAttachmentPersonnelAttributes.description.label}" />
	            	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
              		<div align="left">
              			<html:file property="notesAndAttachmentsHelper.newAttachmentPersonnel.newFile" />
           			</div>
				</td>
				<td align="center" valign="middle" class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAttachmentPersonnel.anchor${tabKey}"
						src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
				</td>
			</tr>
			
			<c:forEach var="attachmentPersonnel" items="${KualiForm.document.protocol.attachmentPersonnels}" varStatus="status">
				<tr>
	         		<td>
	         			<div align="center">
	                		${status.index + 1}
		            	</div>
	         		</td>
	         		<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocol.attachmentPersonnel[${status.index}].updateTimestamp" attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocol.attachmentPersonnel[${status.index}].updateUser" attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocol.attachmentPersonnel[${status.index}].personId" attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocol.attachmentPersonnel[${status.index}].typeCode" attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" readOnly="true" readOnlyAlternateDisplay="${attachmentPersonnel.type.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocol.attachmentPersonnel[${status.index}].description" attributeEntry="${protocolAttachmentPersonnelAttributes.description}" readOnly="true"/>
		            	</div>
					</td>
	       			<td align="left" valign="middle" class="infoline">
	           			<div align="left" id="attachmentPersonnelFileName${status.index}">
	              			${attachmentPersonnel.file.name}
	           			</div>
					</td>
					<td align="center" valign="middle" class="infoline">
						<div align="center">
							<html:image property="methodToCall.viewAttachmentPersonnel.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								alt="View Personnel Attachment" onclick="excludeSubmitRestriction = true;"/>
							<html:image property="methodToCall.deleteAttachmentPersonnel.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
								alt="Delete Personnel Attachment"/>
						</div>
					</td>
	         	</tr>
			</c:forEach>
		</table>
     </div>		
</kul:tab>