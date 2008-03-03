<%--
 Copyright 2005-2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="notesBo" required="false" type="java.util.List" %>
<%-- <%@ attribute name="propPrefix" required="false" %> --%>
<%@ attribute name="noteType" required="false" type="java.lang.Enum" %>
<%@ attribute name="displayTopicFieldInNotes" required="false" %>
<%@ attribute name="allowsNoteDelete" required="false" %>
<%@ attribute name="attachmentTypesValuesFinderClass" required="false" %>
<%@ attribute name="transparentBackground" required="false" %>
<%@ attribute name="defaultOpen" required="false" %>
<%@ attribute name="allowsNoteFYI" required="false"
 description="Indicator for determing whether to render the ad hoc fyi recipient box and send fyi button" %>

<c:set var="noteColSpan" value="6" />

<c:if test="${empty noteType}">
	<%-- default to document header notes this default should probably be set somewhere else --%>
	<c:set var="noteType" value="${Constants.NoteTypeEnum.DOCUMENT_HEADER_NOTE_TYPE}"/>
	<c:set var="notesBo" value="${KualiForm.document.documentHeader.boNotes}" />
</c:if>


<c:set var="propPrefix" value="${noteType.fullPath}." />



<c:if test="${not empty attachmentTypesValuesFinderClass}">
	<c:set var="noteColSpan" value="${noteColSpan + 1}" />
</c:if>

<c:if test="${displayTopicFieldInNotes eq true}">
	<c:set var="noteColSpan" value="${noteColSpan + 1}" />
</c:if>

<kul:tab tabTitle="Notes and Attachments" defaultOpen="${!empty notesBo or (not empty defaultOpen and defaultOpen)}" tabErrorKey="${Constants.DOCUMENT_NOTES_ERRORS}" tabItemCount="${fn:length(notesBo)}" transparentBackground="${transparentBackground}" >
    <c:set var="notesAttributes" value="${DataDictionary.Note.attributes}" />
    <div class="tab-container" align=center id="G4">
    <p align=left><jsp:doBody/>
	<div class="h2-container">
	<h2>Notes and Attachments</h2>
	</div>
        <table cellpadding="0" cellspacing="0" class="datatable" summary="view/add notes">
            <tbody>

                <tr>
                    <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="left"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.notePostedTimestamp}" hideRequiredAsterisk="true" scope="col" align="left"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.authorUniversalIdentifier}" hideRequiredAsterisk="true" scope="col" align="left"/>

<%-- NEED TO ADD THIS TOPIC FIELD TO DATABASE REMOVE THIS COMMENT ONCE FIELD IS THERE--%>

                    <c:if test="${displayTopicFieldInNotes eq true}">
					  <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.noteTopicText}" forceRequired="true" scope="col" align="left" />
					</c:if>


                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.noteText}" scope="col" align="left"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notesAttributes.attachment}" scope="col" align="left"/>
                    <c:if test="${not empty attachmentTypesValuesFinderClass}">
                      <kul:htmlAttributeHeaderCell literalLabel="Attachment Type" scope="col" align="left"/>
                    </c:if>
                    <c:if test="${allowsNoteFYI}" >
                      <kul:htmlAttributeHeaderCell literalLabel="Notification Recipient" scope="col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>

                <tr>
                	<html:hidden property="newNote.noteTypeCode" value="${noteType.code}"/>
                    <kul:htmlAttributeHeaderCell literalLabel="add:" scope="row"/>
                    <td class="infoline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class="infoline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <c:if test="${displayTopicFieldInNotes eq true}">
					  <td class="infoline"><kul:htmlControlAttribute attributeEntry="${notesAttributes.noteTopicText}" property="newNote.noteTopicText" /></td>
					</c:if>
                    <td class="infoline"><kul:htmlControlAttribute attributeEntry="${notesAttributes.noteText}" property="newNote.noteText" /></td>
                    <td class="infoline">
                        <div align="center"><br />
                        <html:file property="attachmentFile" size="30" value="" /><br /><br />
                        <html:image property="methodToCall.cancelBOAttachment" src="${ConfigProperties.kr.externalizable.images.url}tinygrey-cancel.gif" title="Cancel Attachment" alt="Cancel Attachment" styleClass="tinybutton"/>
                        </div>
                    </td>
                    <c:if test="${not empty attachmentTypesValuesFinderClass}">
					              <c:set var="finderClass" value="${fn:replace(DataDictionary.KualiBudgetDocument.attachmentTypesValuesFinderClass,'.','|')}"/>
					              <td class="infoline">
					                  <html:select property="newNote.attachment.attachmentTypeCode">
					                      <html:optionsCollection property="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}" label="label" value="key"/>
					                  </html:select>
					              </td>
                    </c:if>
                    <c:if test="${allowsNoteFYI}" >
                      <td>&nbsp;</td>
                    </c:if>
                    <td class="infoline"><div align="center"><html:image property="methodToCall.insertBONote" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Add a Note" title="Add a Note" styleClass="tinybutton"/></div></td>
                </tr>

	<c:forEach var="note" items="${notesBo}" varStatus="status">
    	<tr>
        	<html:hidden property="${propPrefix}boNote[${status.index}].versionNumber" />
        	<html:hidden property="${propPrefix}boNote[${status.index}].remoteObjectIdentifier" />
        	<html:hidden property="${propPrefix}boNote[${status.index}].noteIdentifier" />
        	<html:hidden property="${propPrefix}boNote[${status.index}].noteTypeCode" />
            <kul:htmlAttributeHeaderCell literalLabel="${status.index + 1}" scope="row"/>
            <td class="datacell center">
            <html:hidden write="true" property="${propPrefix}boNote[${status.index}].notePostedTimestamp" />
            &nbsp;</td>

                        <td class="datacell center"><html:hidden write="true" property="${propPrefix}boNote[${status.index}].authorUniversal.personName" /></td>

<%-- NEED TO ADD THIS TOPIC FIELD TO DATABASE --%>
                        <c:if test="${displayTopicFieldInNotes eq true}">
                        	<td class="datacell center"><html:hidden write="true" property="${propPrefix}boNote[${status.index}].noteTopicText"/></td>
                        </c:if>

                        <td class="datacell center"><html:hidden write="true" property="${propPrefix}boNote[${status.index}].noteText"/></td>

						<%-- use caution if you rename either of these two variables.  It seems that the properties are not read in sequentially
						     but instead in some other arbitrary way (sorted alphabetically?) and therefore you may end up with a reference to a null authorUniversal object --%>
                        <html:hidden property="${propPrefix}boNote[${status.index}].authorUniversalIdentifier" />
                        <html:hidden property="${propPrefix}boNote[${status.index}].authorUniversal.personUniversalIdentifier" />

<%-- won't work until I add attachment logic to action --%>

                            <c:choose>
                                <c:when test="${(!empty note.attachment) && (note.attachment.complete)}">
                                  <td class="datacell center">
                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.noteIdentifier"/>

                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.attachmentFileSize"/>
                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.attachmentIdentifier"/>
                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.versionNumber"/>
                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.objectId"/>
                                    <html:hidden property="${propPrefix}boNote[${status.index}].attachment.attachmentTypeCode"/>

                                    <html:image property="methodToCall.downloadBOAttachment.attachment[${status.index}]" src="${ConfigProperties.kr.externalizable.images.url}clip.gif" alt="download attachment" style="padding:5px" onclick="excludeSubmitRestriction=true"/>
                                    <html:hidden write="true" property="${propPrefix}boNote[${status.index}].attachment.attachmentFileName" />
                                    &nbsp;
                                    &nbsp;
                                    <span style="white-space: nowrap">
                                        <kul:fileSize byteSize="${note.attachment.attachmentFileSize}">
                                            (<c:out value="${fileSize} ${fileSizeUnits}" />, <html:hidden write="true" property="${propPrefix}boNote[${status.index}].attachment.attachmentMimeTypeCode" />)
                                        </kul:fileSize>
                                    </span>
                                    </td>

                                    <c:if test="${not empty attachmentTypesValuesFinderClass}">
                                        <td class="datacell center">
                                            &nbsp;
                                            <c:set var="attachmentTypeFinderMap" value="${KualiForm.validOptionsMap[finderClass]}"  />
                                            <c:forEach items="${attachmentTypeFinderMap}" var="type">
                                                <c:if test="${type.key eq note.attachment.attachmentTypeCode}">${type.label}</c:if>
                                            </c:forEach>
                                        </td>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <td class="datacell center">&nbsp;</td>
                                    <c:if test="${not empty attachmentTypesValuesFinderClass}">
                                        <td class="datacell center">&nbsp;</td>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${allowsNoteFYI}" >
                              <td class="infoline">
                                <c:if test="${KualiForm.documentActionFlags.canAdHocRoute}">
	                    	     <kul:user userIdFieldName="${propPrefix}boNote[${status.index}].adHocRouteRecipient.id" 
	                    			  userId="${note.adHocRouteRecipient.id}" 
	                    			  universalIdFieldName=""
	                    			  universalId=""
	                    			  userNameFieldName="${propPrefix}boNote[${status.index}].adHocRouteRecipient.name"
	                    			  userName="${note.adHocRouteRecipient.name}"
	                    			  readOnly="false" 
	                    			  renderOtherFields="true"
	                    			  fieldConversions="personUserIdentifier:${propPrefix}boNote[${status.index}].adHocRouteRecipient.id,personName:${propPrefix}boNote[${status.index}].adHocRouteRecipient.name" 
	                    			  lookupParameters="${propPrefix}boNote[${status.index}].adHocRouteRecipient.id:personUserIdentifier" />
	                    	    </c:if>
	                    	    <c:if test="${!KualiForm.documentActionFlags.canAdHocRoute}">
	                    	      &nbsp;
	                    	    </c:if>
                             </td>
                           </c:if>
                           
                        <td class="datacell center"><div align="center">
                          <c:if test="${allowsNoteDelete}">
                            <html:image property="methodToCall.deleteBONote.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete a Note" alt="Delete a Note" styleClass="tinybutton"/>
                          </c:if> &nbsp;
                          <c:if test="${allowsNoteFYI && KualiForm.documentActionFlags.canAdHocRoute}" >
                            <c:if test="${KualiForm.documentActionFlags.canAdHocRoute}">
                              <html:image property="methodToCall.sendNoteWorkflowNotification.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-send.gif" title="Send FYI" alt="Send FYI" styleClass="tinybutton"/>
                            </c:if>
                          </c:if>  
                        </div></td>
                    </tr>
	</c:forEach>
	            </tbody>
        </table>
    </div>
</kul:tab>