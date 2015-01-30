<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="notesBo" required="false" type="java.util.List" %>
<%-- <%@ attribute name="propPrefix" required="false" %> --%>
<%@ attribute name="noteType" required="false" type="java.lang.Enum" %>
<%@ attribute name="displayTopicFieldInNotes" required="false" %>
<%@ attribute name="attachmentTypesValuesFinderClass" required="false" %>
<%@ attribute name="transparentBackground" required="false" %>
<%@ attribute name="defaultOpen" required="false" %>

<c:set var="noteColSpan" value="6" />

<c:if test="${empty noteType}">
  <%-- default to document header notes this default should probably be set somewhere else --%>
  <c:set var="noteType" value="${KualiForm.document.noteType}"/>
  <c:set var="notesBo" value="${KualiForm.document.notes}" />
</c:if>

<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="allowsNoteAttachments" value="${documentEntry.allowsNoteAttachments}" />
<c:set var="allowsNoteFYI" value="${documentEntry.allowsNoteFYI}" />
<c:set var="tabTitle" value="Notes and Attachments" />
<c:if test="${allowsNoteAttachments eq false}">
  <c:set var="tabTitle" value="Notes" />
</c:if>

<c:set var="propPrefix" value="document." />


<c:if test="${not empty attachmentTypesValuesFinderClass}">
  <c:set var="noteColSpan" value="${noteColSpan + 1}" />
</c:if>

<c:if test="${empty displayTopicFieldInNotes}">
  <c:set var="displayTopicFieldInNotes" value="${documentEntry.displayTopicFieldInNotes}" />
</c:if>

<c:if test="${displayTopicFieldInNotes eq true}">
  <c:set var="noteColSpan" value="${noteColSpan + 1}" />
</c:if>

<kul:tab tabTitle="${tabTitle}" defaultOpen="${!empty notesBo or (not empty defaultOpen and defaultOpen)}" tabErrorKey="${Constants.DOCUMENT_NOTES_ERRORS}" tabItemCount="${fn:length(notesBo)}" transparentBackground="${transparentBackground}" >
    <c:set var="notesAttributes" value="${DataDictionary.Note.attributes}" />
    <div class="tab-container" align=center id="G4">
    <p align=left><jsp:doBody/>
  <h3>${tabTitle}
          xx  <span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="proposalDevelopmentnotesHelpUrl" altText="help"/></span>
  </h3>
        <table cellpadding="0" cellspacing="0" class="datatable" summary="view/add notes">
            <tbody>

                <tr>
                    <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="left"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.notePostedTimestamp}" hideRequiredAsterisk="true" scope="col" align="left"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.authorUniversalIdentifier}" hideRequiredAsterisk="true" scope="col" align="left"/>

<%-- NEED TO ADD THIS TOPIC FIELD TO DATABASE REMOVE THIS COMMENT ONCE FIELD IS THERE--%>

                    <c:if test="${displayTopicFieldInNotes eq true}">
            <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.noteTopicText}" forceRequired="true" labelFor="newNote.noteTopicText" scope="col" align="left" />
          </c:if>
                     <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.noteText}" labelFor="newNote.noteText" scope="col" align="left"/>
                    <c:if test="${allowsNoteAttachments eq true}">
                      <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.attachment}" labelFor="attachmentFile" scope="col" align="left"/>
                    </c:if>
                    <c:if test="${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}">
                      <kul:htmlAttributeHeaderCell literalLabel="Attachment Type" scope="col" align="left"/>
                    </c:if>
                    <c:if test="${allowsNoteFYI}" >
                      <kul:htmlAttributeHeaderCell literalLabel="Notification Recipient" scope="col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
				<html:hidden property="newNote.noteTypeCode" value="${noteType.code}"/>
				<c:if test="${ ((not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)) || kfunc:canAddNoteAttachment(KualiForm.document)}" >
                  <tr class="addline">
                      <kul:htmlAttributeHeaderCell literalLabel="add:" scope="row"/>
                      <td class="infoline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td class="infoline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      
                      <c:if test="${displayTopicFieldInNotes eq true}">
                       <td class="infoline">
                       		<kul:htmlControlAttribute attributeEntry="${notesAttributes.noteTopicText}" property="newNote.noteTopicText" forceRequired="true" readOnly="${false}" />
                       	</td>
                      </c:if>
                      
                      <td class="infoline">
                      	<kul:htmlControlAttribute attributeEntry="${notesAttributes.noteText}" property="newNote.noteText" forceRequired="${notesAttributes.noteText.required}" readOnly="${false}"  />
                      </td>
                      
                      <c:if test="${allowsNoteAttachments eq true}">
                        <td class="infoline">
                          <div align="center"><br />
                          <html:file property="attachmentFile" size="30" styleId="attachmentFile" value="" /><br /><br />
                          <html:image property="methodToCall.cancelBOAttachment" src="${ConfigProperties.kr.externalizable.images.url}tinygrey-cancel.gif" title="Cancel Attachment" alt="Cancel Attachment" styleClass="tinybutton"/>
                          </div>
                        </td>
                     </c:if>
                     <c:if test="${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}">
                        <c:set var="finderClass" value="${fn:replace(attachmentTypesValuesFinderClass,'.','|')}"/>
                        <td class="infoline">
                            <html:select property="newNote.attachment.attachmentTypeCode">
                                <html:optionsCollection property="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}" label="label" value="key"/>
                            </html:select>
                        </td>
                     </c:if>
                     <c:if test="${allowsNoteFYI}" >
                      <td>&nbsp;</td>
                     </c:if>
                     <td class="infoline"><div align="center"><html:image property="methodToCall.insertBONote" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Add a Note" title="Add a Note" styleClass="tinybutton addButton"/></div></td>	 
			       </tr>
			   </c:if>   

  <c:forEach var="note" items="${notesBo}" varStatus="status">

	<c:set var="authorUniversalIdentifier" value = "${note.authorUniversalIdentifier}" />
      <tr>
            <kul:htmlAttributeHeaderCell literalLabel="${status.index + 1}" scope="row"/>
            <td class="datacell center">
			<bean:write name="KualiForm" property="${propPrefix}note[${status.index}].notePostedTimestamp"/>
            &nbsp;</td>

                        <td class="datacell center">
                        <bean:write name="KualiForm" property="${propPrefix}note[${status.index}].authorUniversal.name"/>
<%-- NEED TO ADD THIS TOPIC FIELD TO DATABASE --%>
                        <c:if test="${displayTopicFieldInNotes eq true}">
                          <td class="datacell center">
                          <bean:write name="KualiForm" property="${propPrefix}note[${status.index}].noteTopicText"/></td>
                        </c:if>

                        <td class="datacell center"><bean:write name="KualiForm" property="${propPrefix}note[${status.index}].noteText"/></td>

            <%-- use caution if you rename either of these two variables.  It seems that the properties are not read in sequentially
                 but instead in some other arbitrary way (sorted alphabetically?) and therefore you may end up with a reference to a null authorUniversal object --%>
                        <%--<html:hidden property="${propPrefix}note[${status.index}].authorUniversal.principalId" />--%>

<%-- won't work until I add attachment logic to action --%>

                            <c:choose>
                                <c:when test="${(!empty note.attachment) and (note.attachment.complete)}">
                                  <td class="datacell center">
                                    
                                    <c:if test="${allowsNoteAttachments eq true}">
                                      <c:if test="${(!empty note.attachment)}">
										<c:set var="attachmentTypeCode" value ="${note.attachment.attachmentTypeCode}" />
									  </c:if>
                                      <c:if test="${kfunc:canViewNoteAttachment(KualiForm.document, attachmentTypeCode)}" >
                                        <html:image property="methodToCall.downloadBOAttachment.attachment[${status.index}]" src="${ConfigProperties.kr.externalizable.images.url}clip.gif" title="download attachment" alt="download attachment" style="padding:5px" onclick="excludeSubmitRestriction=true"/>
                                      </c:if>
                                      <bean:write name="KualiForm" property="${propPrefix}note[${status.index}].attachment.attachmentFileName"/>
                                      &nbsp;
                                      &nbsp;
                                      <span style="white-space: nowrap">
                                        <kul:fileSize byteSize="${note.attachment.attachmentFileSize}">
                                            (<c:out value="${fileSize} ${fileSizeUnits}" />,  <bean:write name="KualiForm" property="${propPrefix}note[${status.index}].attachment.attachmentMimeTypeCode"/>)
                                        </kul:fileSize>
                                      </span>
                                    </c:if>
                                  </td>
                                  <c:if test="${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}">
                                     <td class="datacell center">
                                     &nbsp;
									 <c:set var="mapKey" value = "getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}" />
									 <c:set var="attachmentTypeFinderMap" value="${KualiForm.actionFormUtilMap[mapKey]}"  />
                                       <c:forEach items="${attachmentTypeFinderMap}" var="type">
                                         <c:if test="${type.key eq note.attachment.attachmentTypeCode}">${type.label}</c:if>
                                       </c:forEach>
                                     </td>
                                  </c:if>
                                </c:when>
                                <c:otherwise>
                                    <td class="datacell center">&nbsp;</td>
                                    <c:if test="${(not empty attachmentTypesValuesFinderClass) and (allowsNoteAttachments eq true)}">
                                        <td class="datacell center">&nbsp;</td>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${allowsNoteFYI}" >
                              <td class="infoline">
                                <c:if test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}">
                             <kul:user userIdFieldName="${propPrefix}note[${status.index}].adHocRouteRecipient.id" 
                              userId="${note.adHocRouteRecipient.id}" 
                              universalIdFieldName=""
                              universalId=""
                              userNameFieldName="${propPrefix}note[${status.index}].adHocRouteRecipient.name"
                              userName="${note.adHocRouteRecipient.name}"
                              readOnly="false"
                              fieldConversions="principalName:${propPrefix}note[${status.index}].adHocRouteRecipient.id,name:${propPrefix}note[${status.index}].adHocRouteRecipient.name" 
                              lookupParameters="${propPrefix}note[${status.index}].adHocRouteRecipient.id:principalName" />
                            </c:if>
                            <c:if test="${empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}">
                              &nbsp;
                            </c:if>
                             </td>
                           </c:if>
                           
                        <td class="datacell center"><div align="center">
                          <c:if test="${allowsNoteFYI && !empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_NOTE_FYI]}" >
                              <html:image property="methodToCall.sendNoteWorkflowNotification.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-send.gif" title="Send FYI" alt="Send FYI" styleClass="tinybutton"/>
                          </c:if>  
                        </div></td>
                    </tr>
  </c:forEach>
              </tbody>
        </table>
    </div>
</kul:tab>
