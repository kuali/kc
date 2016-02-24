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

<c:set var="institutionalProposalNotesAttributes" value="${DataDictionary.InstitutionalProposalNotepad.attributes}" />

<c:set var="tabItemCount" value="0" />
<c:forEach var="institutionalProposalNotepad" items="${KualiForm.document.institutionalProposal.institutionalProposalNotepads}" varStatus="status">               
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
</c:forEach>

<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="allowsNoteAttachments" value="${documentEntry.allowsNoteAttachments}" />
<c:set var="tabTitle" value="Notes and Attachments" />
<c:if test="${allowsNoteAttachments eq false}">
  <c:set var="tabTitle" value="Notes" />
</c:if>
<c:set var="notesAttributes" value="${DataDictionary.Note.attributes}" />

<kul:tab tabTitle="${tabTitle}" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="document.institutionalProposalList[0].institutionalProposalNotepads*,institutionalProposalNotepadBean.newInstitutionalProposalNotepad.*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes</span>
    		<span class="subhead-right">
    			<kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="ipNotesAttachmentsHelp" altText="help"/>
			</span>
        </h3>
        <table id="cost-share-table" cellpadding="0" cellspacing="0" summary="Cost Share">
			<tr>
				<th width="3%" scope="row">&nbsp;</th>
				<th width="5%"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalNotesAttributes.createTimestamp}" useShortLabel="true" noColon="true" /></th>
				<th width="10%"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalNotesAttributes.updateUser}" useShortLabel="true" noColon="true" /></th>
				<th width="15%" align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalNotesAttributes.noteTopic}" useShortLabel="true" noColon="true"/></th>
                <%-- allow this cell to take up whatever is left over --%>
 				<th align="left"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalNotesAttributes.comments}" useShortLabel="true" noColon="true"/></th>
				<th width="5%"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalNotesAttributes.restrictedView}" useShortLabel="true" noColon="true"/></th>
                <c:if test="${allowsNoteAttachments eq true}">
                  <kul:htmlAttributeHeaderCell width="25%" attributeEntry="${notesAttributes.attachment}" labelFor="attachmentFile" scope="col" align="left"/>
                </c:if>
				<th width="5%"><div align="center">Actions</div></th>
			</tr>
			
            <c:if test="${!readOnly}">
			<tr>
            	<th align="center" scope="row"><div align="center">Add:</div></th>
            	<td class="infoline">
            		&nbsp;           	
            	</td>
	            <td class="infoline">
	              	&nbsp;
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	    	<kul:htmlControlAttribute property="institutionalProposalNotepadBean.newInstitutionalProposalNotepad.noteTopic" attributeEntry="${institutionalProposalNotesAttributes.noteTopic}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="left">
            	    	<kul:htmlControlAttribute property="institutionalProposalNotepadBean.newInstitutionalProposalNotepad.comments" attributeEntry="${institutionalProposalNotesAttributes.comments}"/>
            	  	</div>
	            </td>
	            <td class="infoline">
	            	<div align="center">
            	   	 	<kul:htmlControlAttribute property="institutionalProposalNotepadBean.newInstitutionalProposalNotepad.restrictedView" attributeEntry="${institutionalProposalNotesAttributes.restrictedView}"/>
            	  	</div>
	            </td>
                <c:if test="${allowsNoteAttachments eq true}">
                    <td class="infoline">
                        <div align="center"><br />
                        <html:file property="attachmentFile" size="30" styleId="attachmentFile" value="" /><br /><br />
                        <html:image property="methodToCall.cancelBOAttachment" src="${ConfigProperties.kr.externalizable.images.url}tinygrey-cancel.gif" title="Cancel Attachment" alt="Cancel Attachment" styleClass="tinybutton"/>
                        </div>
                    </td>
                </c:if>
	            <td class="infoline">
	            	<div align=center>
						<html:image property="methodToCall.addNote.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	            </td>
          	</tr>
            </c:if>
            
         <c:forEach var="institutionalProposalNotepad" items="${KualiForm.document.institutionalProposal.institutionalProposalNotepads}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td valign="middle">
						<c:out value="${KualiForm.document.institutionalProposal.institutionalProposalNotepads[status.index].createTimestamp}" />
					</td>
	                <td valign="middle">
						<c:out value="${KualiForm.document.institutionalProposal.institutionalProposalNotepads[status.index].createUserName}" />
	                </td>
	                <td valign="middle">                	
						<c:out value="${KualiForm.document.institutionalProposal.institutionalProposalNotepads[status.index].noteTopic}" />  
					</td>
	                <td valign="middle">                	
						<c:out value="${KualiForm.document.institutionalProposal.institutionalProposalNotepads[status.index].comments}" /> 
					</td>
	                <td valign="middle">
					<div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalNotepads[${status.index}].restrictedView" attributeEntry="${institutionalProposalNotesAttributes.restrictedView}"/>
					</div>
	                </td>
	                <c:if test="${allowsNoteAttachments eq true}">
		                <%-- for the IP note implementation, if a IPNotepad object has an attachment, that attachment is linked to a KNS note, and the
		                     KNS note is linked to the IPNotepad --%>
		                <c:set var="note" value="${institutionalProposalNotepad.attachments[0]}"/>
		                <c:set var="propPrefix" value="document.institutionalProposal.institutionalProposalNotepads[${status.index}]."/>
	                    <c:choose>
	                        <c:when test="${(!empty note) and (!empty note.attachment) and (note.attachment.complete)}">
	                            <td class="datacell center">                                    
		                            <c:if test="${kfunc:canViewNoteAttachment(KualiForm.document, attachmentTypeCode)}" >
		                                <html:image property="methodToCall.downloadBOAttachment.attachment[${status.index}]" src="${ConfigProperties.kr.externalizable.images.url}clip.gif" title="download attachment" alt="download attachment" style="padding:5px" onclick="excludeSubmitRestriction=true"/>
		                            </c:if>
		                            <bean:write name="KualiForm" property="${propPrefix}attachments[0].attachment.attachmentFileName"/>
		                            &nbsp;
		                            &nbsp;
		                            <span style="white-space: nowrap">
		                                <kul:fileSize byteSize="${note.attachment.attachmentFileSize}">
		                                    (<c:out value="${fileSize} ${fileSizeUnits}" />,  <bean:write name="KualiForm" property="${propPrefix}attachments[0].attachment.attachmentMimeTypeCode"/>)
		                                </kul:fileSize>
		                            </span>
	                            </td>
	                        </c:when>
	                        <c:otherwise>
	                            <td class="datacell center">&nbsp;</td>
	                        </c:otherwise>
	                    </c:choose>
                    </c:if>
					<td width="15%">
						<div align="center">&nbsp;
							<html:image property="methodToCall.updateNotes.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif' styleClass="tinybutton"/>
							&nbsp;
							<c:if test="${!readOnly}">
								<html:image property="methodToCall.deleteNote.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</c:if>
						</div>
	                </td>
	            </tr>
        	</c:forEach> 
        </table>
   </div>
</kul:tab>
