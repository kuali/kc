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
