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

<%@ attribute name="bean" required="true" type="org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewAttachmentsBean" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="taskName" required="true" %>
<%@ attribute name="tabCustomTitle" required="false" %>
<%@ attribute name="methodToCall" required="false" %>

<c:if test="${empty tabCustomTitle}">
    <c:set var="tabCustomTitle" value="Review Comments" />
</c:if>

<c:set var="readOnly" value="${not KualiForm.actionHelper.canManageReviewComments}" />

<c:set var="onlineReviewAttachmentAttributes" value="${DataDictionary.IacucProtocolReviewAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />

<kul:innerTab tabTitle="${tabCustomTitle}" parentTab="" defaultOpen="false" tabErrorKey="${property}.newReviewAttachment.*,${property}.reviewAttachments[*" useCurrentTabIndexAsKey="true">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
 
 <%-- attachment - refactor later --%>
 
                <tr>
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell attributeEntry="${onlineReviewAttachmentAttributes.description}" scope="col" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attachmentFileAttributes['name']}" scope="col"/>
                    <kul:htmlAttributeHeaderCell attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}" scope="col" />
                    <c:if test="${not bean.hideReviewerName}">
                        <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                        <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                                        
                <tr> 
                    <th class="infoline">add</th>

                    <td valign="middle" style="text-align:center">
                                <kul:htmlControlAttribute property="${property}.newReviewAttachment.description" 
                                                  attributeEntry="${onlineReviewAttachmentAttributes.description}" readOnly="${readOnly}" />
                    </td>
                                            
                    <td align="left" valign="middle">
                                <kul:checkErrors keyMatch="${property}.newReviewAttachment.newFile" auditMatch="${property}.newReviewAttachment.newFile"/>
                                    <html:file property="${property}.newReviewAttachment.newFile" size="50" />
                            <c:if test="${hasErrors}">
                                <kul:fieldShowErrorIcon />
                            </c:if>
                    </td>
                        <%--             
                    <td valign="middle" style="text-align:center">
                        <kul:htmlControlAttribute property="${property}.newReviewAttachment.privateFlag" 
                                                  attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}" />
                    </td>
                    --%>
                    <td valign="middle" style="text-align:center">
                        <kul:htmlControlAttribute property="${property}.newReviewAttachment.protocolPersonCanView" 
                                                  attributeEntry="${onlineReviewAttachmentAttributes.protocolPersonCanView}" />
                    </td>
                     
                    <c:if test="${not bean.hideReviewerName}">
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </c:if>
                    <td>
                        <div align="center">
                            <html:image property="methodToCall.addReviewAttachment.taskName${taskName}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                        styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                
                <c:set var="displayCount" value="0"/>
                <c:forEach var="reviewAttachment" items="${bean.reviewAttachments}" varStatus="status">
                    <c:set var="displayCount" value="${displayCount + 1}"/>
                    <c:set var="reviewAttachmentReadOnly" value="${readOnly && reviewAttachment.persisted }" />
                    
                    <tr>
                        <th class="infoline">${displayCount}</th>
    
                        <td style="text-align:center;">
                            <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].description" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.description}"
                                                          readOnly="${readOnly}" />
                         </td>
                                 
                        <td>
                            <c:set var="addStyle" value="display: none;"/>
                            <c:set var="viewStyle" value="display: block;"/>
                            <c:if test="${empty reviewAttachment.file.name}">
                                <c:set var="addStyle" value="display: block;"/>
                                <c:set var="viewStyle" value="display: none;"/>
                            </c:if>
                             ${reviewAttachment.file.name}
                            
                        </td>
                            <%--                           
                            <td style="text-align:center; vertical-align:middle">
                                <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].privateFlag" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}"
                                                          readOnly="${reviewAttachmentReadOnly}" />
                            </td>
                            --%>
                        <td style="text-align:center; vertical-align:middle">
                                <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].protocolPersonCanView" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.protocolPersonCanView}"
                                                          readOnly="${reviewAttachmentReadOnly}" />
                        </td>
                             
                        <c:if test="${not bean.hideReviewerName}">
                            <c:choose>
                                <c:when test="${bean.reviewAttachments[status.index].displayReviewerName}">

                                    <td style="text-align:center; vertical-align:middle">
                                        <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].updateUserFullName" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.createUser}"
                                                          readOnly="true" />  <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].updateTimestamp" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.createTimestamp}"
                                                          readOnly="true" />
                                    </td>
                            
                                    <td style="text-align:center; vertical-align:middle">
                                        <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].createUserFullName" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.createUser}"
                                                          readOnly="true" /> <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].createTimestamp" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.createTimestamp}"
                                                          readOnly="true" />
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>                                
                        <td>
                            <div align="center">&nbsp;
                                <nobr>
                                    <c:if test = "${!readOnly}">
                                        <div id="viewAttachment"  class="viewsection"  style="${viewStyle} align="center">
                                            <html:image property="methodToCall.viewReviewAttachment.taskName${taskName}.line${status.index}.anchor${tabKey}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                                 alt="View Protocol Attachment" onclick="excludeSubmitRestriction = true;"/>
                                            <html:image property="methodToCall.deleteReviewAttachment.taskName${taskName}.line${status.index}.anchor${tabKey}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
                                                alt="Delete Template"/>
                                        </div>
                                            
                                    </c:if>
                                </nobr>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
 
 <%-- end attachment --%>               
                <c:if test="${not empty methodToCall}">
                    <tr>
                        <td colspan="8">
                            <div align="center">
                                <html:image property="methodToCall.${methodToCall}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' 
                                            styleClass="tinybutton"/>
                            </div>
                        </td>
                    </tr>
                </c:if>

            </tbody>
        </table>
    </div>
</kul:innerTab>
