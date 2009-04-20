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

<kul:tab tabTitle="Personnel Attachments(${fn:length(KualiForm.document.protocol.attachmentPersonnels)})" defaultOpen="false" tabErrorKey="notesAndAttachmentsHelper.newAttachmentPersonnel.*,document.protocol.attachmentPersonnel*" transparentBackground="false">
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
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" noColon="false"/>
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" noColon="false"/>
         			</div>
         		</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.description}" noColon="false"/>
					</div>
				</th>
         		<th>
					<div align="center">
						<kul:htmlAttributeLabel attributeEntry="${protocolAttachmentPersonnelAttributes.fileId}" noColon="false"/>
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
                		<%--<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.personId" attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" />--%>
                		<c:set var="property" value="notesAndAttachmentsHelper.newAttachmentPersonnel.personId" />
                		
                		<%-- attachment type finder logic start--%>
							<jsp:useBean id="typeParamsPerson" class="java.util.HashMap" />
							<c:set target="${typeParamsPerson}" property="protocolId" value="${notesAndAttachmentsHelper.newAttachmentPersonnel.protocolId}" />
							<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.personnel.ProtocolPersonValuesFinder', typeParamsPerson)}" />
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
         		<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<c:set var="property" value="notesAndAttachmentsHelper.newAttachmentPersonnel.typeCode" />
                		
                		<%-- attachment type finder logic start--%>
							<jsp:useBean id="typeParamsType" class="java.util.HashMap"/>
							<c:set target="${typeParamsType}" property="groupCode" value="${notesAndAttachmentsHelper.newAttachmentPersonnel.groupCode}" />
							<c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParamsType)}" />
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
				<td align="left" valign="middle" class="infoline">
                	<div align="left">
                		<kul:htmlControlAttribute property="notesAndAttachmentsHelper.newAttachmentPersonnel.description" attributeEntry="${protocolAttachmentPersonnelAttributes.description}"/>
                		<kra:expandedTextArea textAreaFieldName="notesAndAttachmentsHelper.newAttachmentPersonnel.description" action="${action}" textAreaLabel="${protocolAttachmentPersonnelAttributes.description.label}" />
	            	</div>
				</td>
				<td align="left" valign="middle" class="infoline">
              		<div align="left">
              		    <c:set var="property" value="notesAndAttachmentsHelper.newAttachmentPersonnel.newFile" />
              		
              		    <%-- attachment file error handling logic start--%>
               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
               				<%-- highlighting does not work in firefox but does in ie... --%>
               				<c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
               			<%-- attachment file error handling logic start--%>
              		
              			<html:file property="${property}" style="${textStyle}"/>
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
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentPersonnel[${status.index}].updateTimestamp" attributeEntry="${protocolAttachmentPersonnelAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentPersonnel[${status.index}].updateUser" attributeEntry="${protocolAttachmentPersonnelAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentPersonnel[${status.index}].person.personName" attributeEntry="${protocolAttachmentPersonnelAttributes.personId}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentPersonnel[${status.index}].typeCode" attributeEntry="${protocolAttachmentPersonnelAttributes.typeCode}" readOnly="true" readOnlyAlternateDisplay="${attachmentPersonnel.type.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.protocolList[0].attachmentPersonnel[${status.index}].description" attributeEntry="${protocolAttachmentPersonnelAttributes.description}" readOnly="true"/>
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