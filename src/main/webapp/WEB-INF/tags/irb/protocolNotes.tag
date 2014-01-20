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
<c:set var="modifyPermission" value="${KualiForm.notesAttachmentsHelper.modifyNotepads}" />
<c:set var="viewRestrictedNotes" value="${KualiForm.notesAttachmentsHelper.viewRestricted}" />
<c:set var="irbAdmin" value="${KualiForm.notesAttachmentsHelper.protocolAdmin}" />
<c:set var="tabItemCount" value="0" />
<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">
    <c:if test="${viewRestrictedNotes || !protocolNotepad.restrictedView}">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:if>
</c:forEach>

<kul:tab tabTitle="Notes" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="notesAttachmentsHelper.newProtocolNotepad.*,document.protocol.notepads.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
			<span class="subhead-right"><kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolNotesHelpUrl" altText="help"/></span>
        </h3>
        <table id="protocol-notepad-table" cellpadding="0" cellspacing="0" summary="Protocol Notepad">
			<tr>
				<th scope="row">&nbsp;</th>
				<th align="center">Created By</th>
				<th align="center">Updated By</th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
				<th align="left"><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th><kul:htmlAttributeLabel attributeEntry="${protocolNotesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
				<th><div align="center">Actions</div></th>
			</tr>
			<kra:permission value="${modifyPermission}">
				<tbody class="addline">
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
	            	    	<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.noteTopic" attributeEntry="${protocolNotesAttributes.noteTopic}" readOnly="${!modifyPermission}" />
	            	  	</div>
		            </td>
		            <td width="1000" class="infoline">
		            	<div align="left">
	            	    	<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.comments" attributeEntry="${protocolNotesAttributes.comments}" readOnly="${!modifyPermission}" />
	            	  	</div>
		            </td>
		            <td class="infoline">
		            	<div align="center" class="ignoreMeFromWarningOnAddRow">
        			            <c:if test="${viewRestrictedNotes}" >
		            	   	 		<kul:htmlControlAttribute property="notesAttachmentsHelper.newProtocolNotepad.restrictedView" attributeEntry="${protocolNotesAttributes.restrictedView}" readOnly="${!modifyPermission || !viewRestrictedNotes}" />
				            	</c:if>
		            	 </div>
			        </td>
		            <td class="infoline">
		            	<div align=center>
							<html:image property="methodToCall.addNote.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
						</div>
		            </td>
	          	</tr>
	          	</tbody>
          	</kra:permission>
         	<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">
	             <c:if test="${viewRestrictedNotes || !protocolNotepad.restrictedView}">
		             
		             <kra:noteLineItem statusIndex="${status.index}" noteParmeterString="document.protocol.notepads[${status.index}]" 
		             	viewRestrictedNotes="${viewRestrictedNotes }" noteObject="${protocolNotepad }" modifyPermission="${modifyPermission }" 
		             		hasAdministratorRole="${irbAdmin }" action="protocolProtocolActions" isAddLine="${false }" />
	            
	            </c:if>
        	</c:forEach> 
        </table>
   </div>
</kul:tab>
