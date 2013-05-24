<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

<%@ attribute name="usageSectionId" required="false" type="java.lang.String" description="ID to identify where the note section is used." %>

<c:set var="notesAttributes" value="${DataDictionary.CoiDisclosureNotepad.attributes}" />
<c:set var="addPermission" value="${KualiForm.coiNotesAndAttachmentsHelper.addNotepads}" />
<c:set var="modifyPermission" value="${KualiForm.coiNotesAndAttachmentsHelper.modifyNotepads}" />
<c:set var="viewRestrictedNotes" value="${KualiForm.coiNotesAndAttachmentsHelper.viewRestricted}" />
<c:set var="tabItemCount" value="0" />
<c:set var="attachmentHelper" value="${KualiForm.coiNotesAndAttachmentsHelper}" />
<c:set var="canDeleteUpdateNotes" value="${attachmentHelper.canDeleteUpdateNote}" />
<c:set var="disclosureType" value="${KualiForm.document.coiDisclosure}" />
<c:set var="currentUser" value="${KualiForm.coiNotesAndAttachmentsHelper.currentUser}" />
<c:set var="isMasterDisclosure" value="${KualiForm.disclosureHelper.isMasterDisclosure}" />
<c:set var="openForNotesAndAttachments" value="${KualiForm.document.coiDisclosure.openForNotesAndAttachments}"/>
<c:forEach var="coiDisclosureNotepad" items="${KualiForm.document.coiDisclosure.coiDisclosureNotepads}" varStatus="status">
    <c:if test="${viewRestrictedNotes || !coiDisclosureNotepad.restrictedView}">               
		<c:set var="listUsageSectionId" value="${coiDisclosureNotepad.usageSectionId}" />
        <c:if test="${listUsageSectionId eq usageSectionId}">
        	<c:set var="tabItemCount" value="${tabItemCount+1}" />
        </c:if>	
    </c:if>
</c:forEach>

	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Notes (${tabItemCount}) </span>
	   		<span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="disclNotesAndAttachmentsHelp" altText="help"/></span>
        </h3>
        <table id="coiDisclosure-notepad-table" cellpadding="0" cellspacing="0" summary="Coi Disclosure Notepad">
			<tr>
				<th scope="row">&nbsp;</th>
				<th align="left">Created By</th>
				<th align="left">Updated By</th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.noteTopic}" useShortLabel="true" noColon="true" readOnly="${!openForNotesAndAttachments}"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.comments}" useShortLabel="true" noColon="true" readOnly="${!openForNotesAndAttachments}"/></th>
                <th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.noteTypeCode}" useShortLabel="true" noColon="true"/></th>				
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.projectId}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.financialEntityId}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><div align="center">Actions</div></th>
				
			</tr>
            <%-- Note: we are overriding the readOnly parm passed into the control attributes in this section.  Since the entire
                 disclosure may not be open for editing, the readOnly prevents any of the control attributes from being editable. --%>
			<c:if test="${addPermission && openForNotesAndAttachments}">
                ${kfunc:registerEditableProperty(KualiForm, "coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.usageSectionId")}
                <input type="hidden" name="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.usageSectionId" value="${usageSectionId}"/>
				<c:if test="${addPermission}">
					<tr>
	            		<th width="5%" align="center" scope="row"><div align="center">Add:</div></th>
		            	<td width="9%" class="infoline">
		            		&nbsp;           	
	    	        	</td>
		    	        <td width="7%" class="infoline">
		        	      	&nbsp;
		            	</td>
				            <td width="12%" class="infoline">
			            	<div align="center">
	        	    	    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.noteTopic" attributeEntry="${notesAttributes.noteTopic}" readOnly="false" />
	            		  	</div>
		            	</td>
			            <td class="infoline">
			            	<div align="left">
	    	        	    	<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.comments" attributeEntry="${notesAttributes.comments}" readOnly="false" />
	        	    	  	</div>
		        	    </td>
		        	    <td width="12%" class="infoline">
                            <div align="center">
                                <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.noteTypeCode" attributeEntry="${notesAttributes.noteTypeCode}" readOnly="${!modifyPermission}" />
                            </div>
                        </td>
			        	<td width="10%">
				        	<c:choose>
					        	<c:when test="${attachmentHelper.newCoiDisclosureNotepad.projectId == null}">
							       	<html:select property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.projectId" style="width:180px" tabindex="0">
									    <c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder', paramMap1)}" var="option">
									    	<c:choose>
								        	    <c:when test="${coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.projectId == option.key}">
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
						        	<c:out value="${attachmentHelper.newCoiDisclosureNotepad.projectName}" />
					        	</c:otherwise>
				            </c:choose>
			        	</td>
			        	<td width="10%">
							<kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.financialEntityId" attributeEntry="${notesAttributes.financialEntityId}" readOnly="false" />
						</td>
			            <td width="5%" class="infoline">
			            	<div align="center">
		    	        			<c:if test="${viewRestrictedNotes}" >            	
		        	    		   	 <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.restrictedView" attributeEntry="${notesAttributes.restrictedView}" readOnly="false" />
		            				</c:if>
		        	    	</div>
				        </td>
			            <td width="6%" class="infoline">
			            	<div align=center>
								<html:image property="methodToCall.addNote.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
							</div>
		            	</td>
	          		</tr>
				</c:if>
	        </c:if>
			<c:set var="notesIndex" value="1"/>
	        <c:forEach var="coiDisclosureNotepad" items="${KualiForm.document.coiDisclosure.coiDisclosureNotepads}" varStatus="status">
                <c:if test="${viewRestrictedNotes || !coiDisclosureNotepad.restrictedView}">

					<c:set var="listUsageSectionId" value="${coiDisclosureNotepad.usageSectionId}" />
    	            <c:if test="${listUsageSectionId eq usageSectionId}">

						<html:hidden property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].usageSectionId" />

			         	<c:set var="noteId" value="${coiDisclosureNotepad.id}" />
			         	<c:set var="deletePermission" value="${canDeleteUpdateNotes[status.index]}" />
	    	     		<%--This noteReadOnly just makes the note readonly. Editing and Deleting permissions is controlled by another authorizer. --%>
						<c:set var="noteReadOnly" value="${!modifyPermission || !coiDisclosureNotepad.editable}" />
						<c:set var="disclosureBeingEdited" value="${coiDisclosureNotepad.editable}" />
						<c:set var="statusIndex" >
							<c:out value="${status.index}" />
						</c:set>
		    	    	<tr>
							<th class="infoline">
								<c:out value="${notesIndex}" />
							</th>
							<td valign="middle"> 
								<div align="center">
									${KualiForm.document.coiDisclosure.coiDisclosureNotepads[status.index].createUserFullName}
									<br/>
									<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].createTimestamp" attributeEntry="${notesAttributes.createTimestamp}" readOnly="true"/>
								</div>
							</td>
							<td valign="middle"> 
								<div align="center">
									 ${KualiForm.document.coiDisclosure.coiDisclosureNotepads[status.index].updateUserFullName}
									<br/>
									<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].updateTimestamp" attributeEntry="${notesAttributes.updateTimestamp}" readOnly="true"/>
								</div>
							</td>
		            	    <td valign="middle">                	
								<div align="left">
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
                                    <kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${statusIndex}].noteTypeCode" attributeEntry="${notesAttributes.noteTypeCode}" readOnly="${noteReadOnly}" />
                                </div>
                            </td>
							<td valign="middle">
								<div align="left">
					                <c:choose>
						                <c:when test="${isMasterDisclosure && !noteReadOnly}">
						            	    <html:select property="coiNotesAndAttachmentsHelper.coiDisclosure.coiDisclosureNotepads[${status.index}].projectId"
						        	                     style="width:180px" tabindex="0" disabled="${!modifyPermission}">
					    		                <c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder', paramMap1)}" var="option">
							    	                <c:choose>
							        	                <c:when test="${coiDisclosureNotepad.projectId == option.key}">
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
						    	            <c:out value="${KualiForm.document.coiDisclosure.coiDisclosureNotepads[statusIndex].projectName}" />
					            	    </c:otherwise>
					                </c:choose>
								</div>
							</td>
							<td valign="middle">	
								<div align="left">
									<c:choose><c:when test="${noteReadOnly}">
										<c:out value="${KualiForm.document.coiDisclosure.coiDisclosureNotepads[statusIndex].financialEntity.entityName}" />	
									</c:when><c:otherwise>
										<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${statusIndex}].financialEntityId" attributeEntry="${notesAttributes.financialEntityId}" />
									</c:otherwise></c:choose>
								</div>
							</td>
							<td valign="middle">
								<div align="center">
									<c:out value="${KualiForm.document.coiDisclosure.coiDisclosureNotepads[statusIndex].restrictedView}" />
								</div>
							</td>
							<td>
					           	<div align=center><nobr> 
									<c:if test="${modifyPermission and not disclosureBeingEdited}">
										<html:image property="methodToCall.editNote.line${status.index}.anchor${tabKey}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
									</c:if>
									&nbsp;
									<c:if test="${modifyPermission and deletePermission}">
										<html:image property="methodToCall.deleteNote.line${status.index}.anchor${tabKey}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
									</c:if>
								</nobr></div>
			            	</td>
			        	</tr>
						<c:set var="notesIndex" value="${notesIndex+1}"/>
	                </c:if>          
		        </c:if> 	         	
	        </c:forEach> 
        </table>
   </div>
