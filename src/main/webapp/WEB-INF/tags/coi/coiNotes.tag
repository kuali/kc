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

<c:set var="notesAttributes" value="${DataDictionary.CoiDisclosureNotepad.attributes}" />
<c:set var="modifyPermission" value="${KualiForm.coiNotesAndAttachmentsHelper.modifyNotepads}" />
<c:set var="viewRestrictedNotes" value="${KualiForm.coiNotesAndAttachmentsHelper.viewRestricted}" />
<c:set var="tabItemCount" value="0" />
<c:set var="attachmentHelper" value="${KualiForm.coiNotesAndAttachmentsHelper}" />
<c:set var="canDeleteUpdateNotes" value="${attachmentHelper.canDeleteUpdateNote}" />
<c:set var="disclosureType" value="${KualiForm.document.coiDisclosure}" />
<c:set var="currentUser" value="${KualiForm.coiNotesAndAttachmentsHelper.currentUser}" />
<c:forEach var="coiDisclosureNotepad" items="${KualiForm.document.coiDisclosure.coiDisclosureNotepads}" varStatus="status">
    <c:if test="${viewRestrictedNotes || !coiDisclosureNotepad.restrictedView}">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:if>
</c:forEach>

	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Notes (${tabItemCount}) </span>
	   		<span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiNotesAndAttachmentsHelp" altText="help"/></span>
        </h3>
        <table id="coiDisclosure-notepad-table" cellpadding="0" cellspacing="0" summary="Coi Disclosure Notepad">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${notesAttributes.updateTimestamp}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${notesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.projectId}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.financialEntityId}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><div align="center">Actions</div></th>
				
			</tr>
			<kra:permission value="${modifyPermission}">
			
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
	            	    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.noteTopic" attributeEntry="${notesAttributes.noteTopic}" readOnly="${!modifyPermission}" />
	            	  	</div>
		            </td>
		            <td width="1000" class="infoline">
		            	<div align="left">
	            	    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.comments" attributeEntry="${notesAttributes.comments}" readOnly="${!modifyPermission}"/>
	            	  	</div>
		            </td>
			<td>
			<c:choose>
				<c:when
					test="${attachmentHelper.newCoiDisclosureNotepad.projectId == null}">
					<html:select
						property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.projectId"
						style="width:180px" tabindex="0" disabled="${!modifyPermission}">
						<c:forEach
							items="${krafn:getOptionList('org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder', paramMap1)}"
							var="option">
							<c:choose>
								<c:when
									test="${coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.projectId == option.key}">
									<option value="${option.key}" selected>${option.value}</option>
								</c:when>
								<c:otherwise>
									<c:out value="${option.value}" />
									<option value="${option.key}">${option.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</html:select>
				</c:when>
				<c:otherwise>
					<c:out
						value="${attachmentHelper.newCoiDisclosureNotepad.projectName}" />
				</c:otherwise>
			</c:choose>
			</td>
			<td>
						<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.financialEntityId" attributeEntry="${notesAttributes.financialEntityId}" readOnly="${!modifyPermission}" />
					</td>
		            
		            <td class="infoline">
		            	<div align="center">
		            		<c:if test="${viewRestrictedNotes}" >            	
		            	   	 <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.restrictedView" attributeEntry="${notesAttributes.restrictedView}" readOnly="${!modifyPermission || !viewRestrictedNotes}"/>
		            		</c:if>
		            	 </div>
			        </td>
		            <td class="infoline">
		            	<div align=center>
							<html:image property="methodToCall.addNote.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
		            </td>
	          	</tr>
	        </kra:permission>
	        <c:forEach var="coiDisclosureNotepad" items="${KualiForm.document.coiDisclosure.coiDisclosureNotepads}" varStatus="status">
                <c:if test="${viewRestrictedNotes || !coiDisclosureNotepad.restrictedView}">
	         	
	         	<c:set var="noteId" value="${coiDisclosureNotepad.id}" />
	         	<c:set var="permission" value="${canDeleteUpdateNotes[status.index]}" />
	         	<%--This noteReadOnly just makes the note readonly. Editing and Deleting permissions is controlled by another authorizer. --%>
					<c:set var="noteReadOnly" value="${!modifyPermission || !coiDisclosureNotepad.editable}" />
					<c:set var="disclosureEditable" value="${coiDisclosureNotepad.editable}" />
					<c:set var="statusIndex" >
						<c:out value="${status.index}" />
					</c:set>
		            <tr>
						<th class="infoline">
							<c:out value="${status.index+1}" />
						</th>
		                <td valign="middle">
							<c:choose>
								<c:when test="${coiDisclosureNotepad.editable}">         	
						    		&nbsp;
				        		</c:when>
								<c:otherwise>
						    		<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].updateTimestamp" attributeEntry="${notesAttributes.updateTimestamp}" readOnly="true"/>
				        		</c:otherwise>
				        	</c:choose>
						</td>
		                <td valign="middle">
	                		<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].updateUser" attributeEntry="${notesAttributes.updateUser}" readOnly="true"/>
		                </td>
		                <td valign="middle">                	
							<div align="center">
								<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].noteTopic" attributeEntry="${notesAttributes.noteTopic}" readOnly="${noteReadOnly}" />
							</div>
						</td>
		                <td valign="middle">                	
							<div align="left">
								<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].comments" attributeEntry="${notesAttributes.comments}" readOnly="${noteReadOnly}" />
							</div>
						</td>
						<td valign="middle">
							<div align="left">
								<c:out value="${KualiForm.document.coiDisclosure.coiDisclosureNotepads[statusIndex].projectName}" />
							</div>
						</td>
						<td valign="middle">	
							<div align="left">
								<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${statusIndex}].financialEntityId" attributeEntry="${notesAttributes.financialEntityId}" readOnly="${noteReadOnly}" />
							</div>
						</td>
						<td valign="middle">
							<div align="center">
								<c:out value="${KualiForm.document.coiDisclosure.coiDisclosureNotepads[statusIndex].restrictedView}" />
							</div>
						</td>
						<td>
			            	<div align=center><nobr> 
								<c:if test="${modifyPermission and not disclosureEditable and permission}">
									<html:image property="methodToCall.editNote.line${status.index}.anchor${tabKey}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
								</c:if>
								&nbsp;
								<c:if test="${modifyPermission and permission}">
									<html:image property="methodToCall.deleteNote.line${status.index}.anchor${tabKey}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
								</c:if>
							</nobr></div>
		            	</td>
		            </tr>
                </c:if>          
	        </c:forEach> 
        </table>
   </div>

