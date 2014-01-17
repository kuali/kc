<%--
 Copyright 2005-2014 The Kuali Foundation

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

<c:set var="protocolNotesAttributes" value="${DataDictionary.ProtocolNotepad.attributes}" />
<c:set var="tabItemCount" value="0" />

<c:set var="isOpen" value="${KualiForm.notesAttachmentsHelper.manageNotesOpen}" />

<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">              
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>

<kra:permission value="${KualiForm.actionHelper.canManageNotes}">

<kul:innerTab tabTitle="Manage Notes (${tabItemCount})" parentTab="" defaultOpen="${isOpen}" tabErrorKey="notesAttachmentsHelper.newProtocolNotepad.*,document.protocol.notepads.*">
   
   <div class="tab-container" align="center">
        <table id="protocol-notepad-table" cellpadding="0" cellspacing="0" summary="Protocol Notepad">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.updateTimestamp}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
				<tr>
	            	<th width="40" align="center" scope="row"><div align="center">Add:</div></th>
	            	<td width="80" class="infoline">
	            		&nbsp;           	
	            	</td>
		            <td width="50" class="infoline">
		              	&nbsp;
		            </td>
		            <td width="150" class="infoline">
		            	<div align="center">
	            	    	<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.noteTopic" attributeEntry="${protocolNotesAttributes.noteTopic}" />
	            	  	</div>
		            </td>
		            <td width="1000" class="infoline">
		            	<div align="left">
	            	    	<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.comments" attributeEntry="${protocolNotesAttributes.comments}" />
	            	  	</div>
		            </td>
		            <td class="infoline">
		            	<div align="center">
		            		<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.restrictedView" attributeEntry="${protocolNotesAttributes.restrictedView}"  />
		            	 </div>
			        </td>
		            <td class="infoline">
		            	<div align=center>
							<html:image property="methodToCall.addNote.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
		            </td>
	          	</tr>

         	<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">

		             <tr>
						<th class="infoline">
							<c:out value="${status.index+1}" />
						</th>
		                <td valign="middle">
                            <c:if test="${!protocolNotepad.editable}">
							    <kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].updateTimestamp" attributeEntry="${protocolNotesAttributes.updateTimestamp}" readOnly="${true}"/>
                            </c:if>
                            &nbsp;
						</td>
		                <td valign="middle">
							${KualiForm.document.protocol.notepads[status.index].updateUserFullName}
							&nbsp;
		                </td>
		                <td valign="middle">                	
							<div align="center">
								<kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].noteTopic" 
									attributeEntry="${protocolNotesAttributes.noteTopic}" readOnly="${ !protocolNotepad.editable}"/>
							</div>
						</td>
		                <td valign="middle">                	
							<div align="left">
								<kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].comments" 
									attributeEntry="${protocolNotesAttributes.comments}" readOnly="${ !protocolNotepad.editable}"/>
							</div>
						</td>
		                <td valign="middle">
							<div align="center">
				               <kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].restrictedView" 
				               	attributeEntry="${protocolNotesAttributes.restrictedView}" readOnly="${false}"/>
							</div>
		                </td>
						<td>
							&nbsp;
		                </td>
		            </tr>
		            
        	</c:forEach> 
        	<tr>
        		<td class="infoline" colspan="7">
	            	<div align="center">
						<html:image property="methodToCall.saveNotes.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
					</div>
	            </td>
        	</tr>
        </table>
   </div>
    
</kul:innerTab>

</kra:permission>
