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
<%@ attribute name="usageSectionId" required="false" type="java.lang.String" description="ID to identify where the attachment section is used." %>

<jsp:useBean id="paramMap1" class="java.util.HashMap"/>

<c:set var="numberOfAttachments" value="0" />
<c:set var="modify" value="${KualiForm.coiNotesAndAttachmentsHelper.modifyAttachments}" />
<c:set var="attributes" value="${DataDictionary.CoiDisclosureAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="attachmentFilterAttributes" value="${DataDictionary.CoiDisclosureAttachmentFilter.attributes}" />
<c:set var="attachmentHelper" value="${KualiForm.coiNotesAndAttachmentsHelper}" />
<c:set var="coiDisclosureAttachments" value="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachments}"/>
<c:set var="filteredAttachments" value="${KualiForm.document.coiDisclosureList[0].filteredAttachments}"/>
<c:set target="${paramMap1}" property = "projectId" value = "${coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.projectId}"/>
<c:set var="tabItemCount" value="0" />
<c:set var="canDeleteUpdateAttachments" value="${attachmentHelper.canDeleteUpdateAttachment}" />
<c:set var="canAddAttachment" value="${attachmentHelper.canAddAttachment}" />
<c:set var="openForNotesAndAttachments" value="${KualiForm.document.coiDisclosureList[0].openForNotesAndAttachments}"/>

<c:forEach var="coiDisclosureAttachment" items="${KualiForm.document.coiDisclosure.coiDisclosureAttachments}" varStatus="status">
    <c:set var="listUsageSectionId" value="${coiDisclosureAttachment.usageSectionId}" />
    <c:if test="${listUsageSectionId eq usageSectionId}">
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:if>    
</c:forEach>

<div class="tab-container" align="center">
    <%-- Note: we are overriding the readOnly parm passed into the control attributes in this section.  Since the entire
         disclosure may not be open for editing, the readOnly prevents any of the control attributes from being editable. --%>
    <kra:permission value="${canAddAttachment && openForNotesAndAttachments}">
        ${kfunc:registerEditableProperty(KualiForm, "coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.usageSectionId")}
        <input type="hidden" name="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.usageSectionId" value="${usageSectionId}"/>
        <h3>
           <span class="subhead-left">New Attachment</span> 
           <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="disclNotesAndAttachmentsHelp" altText="help"/></span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">   
        	<tbody class="addline">   
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.typeCode}" readOnly="false" noColon="false"/>
                    </div>
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.typeCode" 
                                                  attributeEntry="${attributes.typeCode}" readOnly="false" />
                    </div>
                </td>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.contactName}" noColon="false"/>
                    </div>
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactName" attributeEntry="${attributes.contactName}" readOnly="false" />
                    </div>
                </td>

                <th>
                    <div align="right">
                        Project:
                    </div>
                </th>
                <td>
                   <c:out value="${KualiForm.document.coiDisclosure.coiDisclProjects[0].coiProjectTitle}" />
				</td>

                <th>
                    <div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.financialEntityId}" noColon="false" /></div>
                </th>
                <td>                                            
                    <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.financialEntityId" attributeEntry="${attributes.financialEntityId}" readOnly="false" />                                              
                </td>
                        
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.updateUser}" noColon="false" />
                    </div>
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.updateUser" attributeEntry="${attributes.updateUser}" readOnly="true"/>
                    </div>
                </td>
                <th>
                    <div align="right">
                       <kul:htmlAttributeLabel attributeEntry="${attributes.contactEmailAddress}" noColon="false"/>
                    </div>
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactEmailAddress" attributeEntry="${attributes.contactEmailAddress}" readOnly="false" />
                    <div align="left">
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.updateTimestamp}" noColon="false" />
                    </div>
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.updateTimestamp" attributeEntry="${attributes.updateTimestamp}" readOnly="true"/>  
                    </div>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.contactPhoneNumber}" noColon="false"/>
                    </div>
                </th>
                <td align="left" valign="middle" colspan="3">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.contactPhoneNumber" attributeEntry="${attributes.contactPhoneNumber}" readOnly="false" />
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false"/>
                    </div>
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.comments" attributeEntry="${attributes.comments}" readOnly="false" />
                    </div>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attributes.description}" readOnly="false" noColon="false" />
                    </div>
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.description" attributeEntry="${attributes.description}" readOnly="false" />                       
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" readOnly="false" noColon="false" />
                    </div>
                </th>
                <td align="left" valign="middle" colspan="5">
                    <div align="left">
                        <c:set var="property" value="coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment.newFile" />
                                
                        <%-- attachment file error handling logic start--%>
                        <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
                        <%-- highlighting does not work in firefox but does in ie... --%>
                        <%-- attachment file error handling logic start--%>
                        <html:file property="${property}" size="50"/>
                        <c:if test="${hasErrors}">
                            <kul:fieldShowErrorIcon />
                        </c:if>                                     
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="6" class="infoline">
                    <div align="center">
                        <html:image property="methodToCall.addAttachmentCoi.anchor${tabKey}"
                                          src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </kra:permission>
    <c:if test="${not empty coiDisclosureAttachments}">


        <!--  Attached Items sub-panel -->
        <br/>
        <h3>
            <span class="subhead-left">Attached Items (${tabItemCount})</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.notesandattachments.attachment.CoiDisclosureAttachment" altText="help"/></span>        
        </h3>
    
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
               
                <td style="border: none; width: 10%;">
                    <div align="right">
                        Sort By:
                    </div>
                </td>
                <td align="left" valign="middle" style="border: none; width: 40%;">
                    <div align="left">
                        <kul:htmlControlAttribute property="coiNotesAndAttachmentsHelper.newAttachmentFilter.sortBy" attributeEntry="${attachmentFilterAttributes.sortBy}" readOnly="false"/>
                    </div>
                </td>                    
            </tr>
            <tr>
                <td colspan="4" class="infoline" style="border: none;">
                    <div align="center">
                        <html:image property="methodToCall.updateAttachmentFilter.anchor${tabKey}"
                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif" styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>                
        </table>
        
        <!-- show attachments -->
    
        <table cellpadding="4" cellspacing="0" summary="">
    
            <c:forEach var="attachment" items="${filteredAttachments}" varStatus="itrStatus">
                <c:set var="listUsageSectionId" value="${attachment.usageSectionId}" />
                <c:if test="${listUsageSectionId eq usageSectionId}">
                    <html:hidden property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].usageSectionId" />
                    <!--  Display logic to show the correct attribute being sorted on in the attachment header -->
                    <c:choose>
                        <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'UPBY'}">
                            <c:set var="sortDisplay" value="- ${attachment.updateUserFullName}"/>
                        </c:when>
                        <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'LAUP'}">
                            <c:set var="sortDisplay">
                                <fmt:formatDate value="${attachment.updateTimestamp}" pattern="- MM/dd/yyyy KK:mm a" />                      
                            </c:set>
                              <c:set var="lastUpdated" value="${sortDisplay}"/>
                        </c:when>
                        <c:when test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy eq 'DESC'}">
                            <c:set var="sortDisplay" >
                                <c:choose>
                                    <c:when test="${fn:length(attachment.description) > 29}">
                                        <c:out value="- ${fn:substring(attachment.description, 0, 29)}..." />
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="- ${attachment.description}" />
                                    </c:otherwise>
                                </c:choose>
                            </c:set>
                        </c:when>                        
                        <c:otherwise>
                            <c:set var="sortDisplay" value="&nbsp;"/>
                            <c:set var="lastUpdated" value="&nbsp"/>
                        </c:otherwise>
                    </c:choose>   
                    <c:if test="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachmentFilter.sortBy ne 'LAUP'}">	
                    	<c:set var="lastUpdated" value="&nbsp"/>
                    </c:if>
             			
                    <tr>
                        <td>
                            <kul:innerTab tabTitle="${attachment.shortDescription} ${lastUpdated}" parentTab="Coi Disclosure Attachments" defaultOpen="false" tabErrorKey="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*,document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*" useCurrentTabIndexAsKey="true" tabAuditKey="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}]*" auditCluster="NoteAndAttachmentAuditErrors">
                                <div class="innerTab-container" align="left">
                                    <table class=tab cellpadding=0 cellspacing="0" summary="">
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.typeCode}" readOnly="false" noColon="false"/>
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].typeCode" attributeEntry="${attributes.typeCode}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                            <td colspan="4">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.contactName}" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactName" attributeEntry="${attributes.contactName}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                                    
                                            <th>
                                                <div align="right">
                                                    Project:
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">       
                                                <div align="left">                                              
                                                    <!-- Just displaying the project name here, not the drop down because 
                                                    in the case of event based disclosures, the project becomes non-disclosureable once the disclosure is saved
                                                    and this drop down becomes empty. So the logic here becomes unduly complicated. -->             
                                                    <c:set var="statusIndex" >
                                                        <c:out value="${itrStatus.index}" />
                                                    </c:set>                        
                                                    <c:out value="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachments[statusIndex].projectName}" />
                                                </div>                                              
                                            </td>
                                            <th>
                                                <div align="right">
                                                    <c:out value="Financial Entity: " />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">                                              
                                                    <c:out value="${KualiForm.document.coiDisclosureList[0].coiDisclosureAttachments[statusIndex].financialEntityName}" />
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.updateUser}" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].updateUser" attributeEntry="${attributes.updateUser}" readOnly="true"/>
                                                </div>
                                            </td>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.contactEmailAddress}" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle" colspan="3">
                                                    
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactEmailAddress" attributeEntry="${attributes.contactEmailAddress}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.updateTimestamp}" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].updateTimestamp" attributeEntry="${attributes.updateTimestamp}" readOnly="true"/>  
                                                </div>
                                            </td>
                                        <th>
                                            <div align="right">
                                                <kul:htmlAttributeLabel attributeEntry="${attributes.contactPhoneNumber}" noColon="false" />
                                            </div>
                                                </th>
                                            <td align="left" valign="middle" colspan="3">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].contactPhoneNumber" attributeEntry="${attributes.contactPhoneNumber}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].comments" attributeEntry="${attributes.comments}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attributes.description}" readOnly="false" noColon="false"/>
                                                </div>
                                            </th>
                                            <td align="left" valign="middle" colspan="3">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].description" attributeEntry="${attributes.description}" readOnly="${!modify}"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <div align="right">
                                                    <kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" readOnly="false" noColon="false" />
                                                </div>
                                            </th>
                                            <td align="left" valign="middle" colspan="5"> 
                                                <div align="left" style="display: none;" id="attachmentCoiDisclosureFile${itrStatus.index}">
                                                    <html:file property="document.coiDisclosureList[0].coiDisclosureAttachments[${itrStatus.index}].newFile" size="50" />
                                                </div>
                                                <div align="left" id="attachmentCoiDisclosureFileName${itrStatus.index}">
                                                    <kra:fileicon attachment="${attachment.file}"/>${attachment.file.name}
                                                </div>
                                            </td>
                                        </tr>   
                                        <tr>
                                            <td colspan="6" class="infoline">
                                                <div align="center">
                                                    <c:set var="id" value="${attachment.attachmentIdForPermission}" />
                                                    <c:set var="permission" value="${canDeleteUpdateAttachments[itrStatus.index]}" />
                                                            
                                                    <input type="hidden" id="coiDisclosureRefreshButtonClicked${itrStatus.index}" name="coiDisclosureRefreshButtonClicked${itrStatus.index}" value="F"/>
                                                    <html:image property="methodToCall.viewAttachmentCoi.line${itrStatus.index}.anchor${currentTabIndex}"
                                                                 src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                                                 alt="View Coi Disclosure Attachment" onclick="excludeSubmitRestriction = true;"/>
                                                                                                                                                                                                                                            
                                                    <c:if test="${modify and permission}" >
                                                        <input class="tinybutton" type="image"
                                                               src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
                                                               id="replaceButton${itrStatus.index}"
                                                               alt="Replace Coi Disclosure Attachment"
                                                               onclick="document.getElementById('attachmentCoiDisclosureFile${itrStatus.index}').style.display = 'block';
                                                               document.getElementById('attachmentCoiDisclosureFileName${itrStatus.index}').style.display = 'none';
                                                               document.getElementById('replaceButton${itrStatus.index}').style.display = 'none';
                                                               document.getElementById('coiDisclosureRefreshButtonClicked${itrStatus.index}').value = 'T';
                                                               return false;"/>
                                                                                    
                                                         <html:image property="methodToCall.deleteCoiDisclosureAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
                                                               src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
                                                               alt="Delete Coi Disclosure Attachment"/>
                                                    </c:if>                     
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </kul:innerTab>
                
                        </td>
                    </tr>
                </c:if>   
            </c:forEach>
        </table>
    </c:if>
</div>

