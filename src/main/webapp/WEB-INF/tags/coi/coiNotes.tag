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
<c:forEach var="coiDisclosureNotepad" items="${KualiForm.document.coiDisclosure.coiDisclosureNotepads}" varStatus="status">
    <c:if test="${viewRestrictedNotes || !coiDisclosureNotepad.restrictedView}">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:if>
</c:forEach>
<kul:tab tabTitle="Notes" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="error.coi.notes.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.notesandattachments.notepad.CoiDisclosureNotepad" altText="help"/></span>
        </h3>
        <table id="coiDisclosure-notepad-table" cellpadding="0" cellspacing="0" summary="Coi Disclosure Notepad">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><kul:htmlAttributeLabel attributeEntry="${notesAttributes.updateTimestamp}" useShortLabel="true" noColon="true" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${notesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.projectId}" useShortLabel="true" noColon="true" /></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${notesAttributes.entityNumber}" useShortLabel="true" noColon="true" /></th>
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
		            <html:select property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.projectId" style="width:180px" tabindex="0" disabled="${readOnly}">
					<c:forEach
							items="${krafn:getOptionList('org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectValuesFinder', paramMap1)}" var="option">

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
					
					</td>
					<td><kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad.entityNumber" attributeEntry="${notesAttributes.entityNumber}" readOnly="${!modifyPermission}" />
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
         	
		             <tr>
						<th class="infoline">
							<c:out value="${status.index+1}" />
						</th>
		                <td valign="middle">
							    <kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].updateTimestamp" attributeEntry="${notesAttributes.updateTimestamp}" readOnly="true"/>
						</td>
		                <td valign="middle">
		                		<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].updateUser" attributeEntry="${notesAttributes.updateUser}" readOnly="true"/>
		                
		                </td>
		                <td valign="middle">                	
						<div align="center">
						<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].noteTopic" attributeEntry="${notesAttributes.noteTopic}" />
						</div>
						</td>
		                <td valign="middle">                	
						<div align="left">
												    
						<kul:htmlControlAttribute property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].comments" attributeEntry="${notesAttributes.comments}" />
						
						</div>
						</td>
					<td valign="middle">
						${KualiForm.document.coiDisclosure.coiDisclosureNotepads[status.index].projectId}				
					</td>
					<td valign="middle">
						${KualiForm.document.coiDisclosure.coiDisclosureNotepads[status.index].entityNumber}			
					</td>
					<td valign="middle">

					<div align="center">
					<kul:htmlControlAttribute
						property="document.coiDisclosure.coiDisclosureNotepads[${status.index}].restrictedView"
						attributeEntry="${notesAttributes.restrictedView}"
						readOnly="${!modifyPermission || !viewRestrictedNotes}" /></div>
					</td>
					<td class="infoline">
		            	<div align=center>
							<c:choose>
							<c:when test="${modifyPermission}">
							<html:image property="methodToCall.deleteNote.line${status.index}.anchor${tabKey}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</c:when>
							</c:choose>
						</div>
		            </td>
		            </tr>
		        </c:if>
        	</c:forEach> 
        </table>
   </div>
</kul:tab>
