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

<c:set var="protocolNotesAttributes" value="${DataDictionary.ProtocolNotepad.attributes}" />
<c:set var="modify" value="${KualiForm.notepadHelper.modifyNotepads}" />
<c:set var="viewRestrictedNotes" value="${KualiForm.notepadHelper.viewRestricted}" />
<c:set var="tabItemCount" value="0" />
<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>


<kul:tab tabTitle="Notes" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="notepadHelper.newProtocolNotepad.*,document.protocol.notepads.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
        </h3>
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
			<kra:permission value="${modify}">
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
	            	    	<kul:htmlControlAttribute property="notepadHelper.newProtocolNotepad.noteTopic" attributeEntry="${protocolNotesAttributes.noteTopic}"/>
	            	  	</div>
		            </td>
		            <td width="1000" class="infoline">
		            	<div align="left">
	            	    	<kul:htmlControlAttribute property="notepadHelper.newProtocolNotepad.comments" attributeEntry="${protocolNotesAttributes.comments}"/>
	            	  	</div>
		            </td>
		            <td class="infoline">
		            	<div align="center">
        			            <c:if test="${viewRestrictedNotes}" >
		            	   	 		<kul:htmlControlAttribute property="notepadHelper.newProtocolNotepad.restrictedView" attributeEntry="${protocolNotesAttributes.restrictedView}"/>
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
         	<c:forEach var="protocolNotepad" items="${KualiForm.document.protocol.notepads}" varStatus="status">
	             <c:if test="${(viewRestrictedNotes && protocolNotepad.restrictedView) || !protocolNotepad.restrictedView}">
		             <tr>
						<th class="infoline">
							<c:out value="${status.index+1}" />
						</th>
		                <td valign="middle">
							<kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].updateTimestamp" attributeEntry="${protocolNotesAttributes.updateTimestamp}" readOnly="true"/>
						</td>
		                <td valign="middle">
							${KualiForm.document.protocol.notepads[status.index].updateUserFullName}
		                </td>
		                <td valign="middle">                	
						<div align="center">
						<kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].noteTopic" attributeEntry="${protocolNotesAttributes.noteTopic}" readOnly="${!modify}"/>
						</div>
						</td>
		                <td valign="middle">                	
						<div align="left">
						<kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].comments" attributeEntry="${protocolNotesAttributes.comments}" readOnly="${!modify}"/>
						</div>
						</td>
		                <td valign="middle">
						<div align="center">
			               <kul:htmlControlAttribute property="document.protocol.notepads[${status.index}].restrictedView" attributeEntry="${protocolNotesAttributes.restrictedView}" readOnly="${!viewRestrictedNotes}"/>
						</div>
		                </td>
						<td>
						<kra:permission value="${viewRestrictedNotes}">
							<div align="center">&nbsp;
								<html:image property="methodToCall.updateNote.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif' styleClass="tinybutton"/>
							</div>
						</kra:permission>
		                </td>
		            </tr>
	            </c:if>
        	</c:forEach> 
        </table>
   </div>
</kul:tab>
