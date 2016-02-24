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



<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean" %>
<%@ attribute name="documentNumber" required="true"%>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="allowReadOnly" required="true" %>
<%@ attribute name="reviewIndex" required = "true" %>
<%@ attribute name="readOnly" required = "true" %>

<c:set var="onlineReviewAttachmentAttributes" value="${DataDictionary.ProtocolReviewAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />

<kul:innerTab tabTitle="Review Attachment" parentTab="" defaultOpen="true" tabErrorKey="onlineReviewsActionHelper.reviewAttachmentsBeans[${reviewIndex}].*" useCurrentTabIndexAsKey="false">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
                <%-- Table headers --%>
                <tr>
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell attributeEntry="${onlineReviewAttachmentAttributes.description}" scope="col" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${attachmentFileAttributes['name']}" scope="col"/>
                    <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                    
                        <kul:htmlAttributeHeaderCell attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}" scope="col" />
                    </c:if>
                    <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].hideReviewerName}">
                        <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                        <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                        
                <c:if test = "${!readOnly}">                        
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
                    
                        <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                      
                    <%--                 
                    <td valign="middle" style="text-align:center">
                        <c:choose>
                        <c:when test = "${!readOnly}">
                            <kul:htmlControlAttribute property="${property}.newReviewAttachment.privateFlag" 
                                                  attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}" readOnly = "${readOnly}"/>
                        </c:when>
                        <c:otherwise>
                            &nbsp;
                        </c:otherwise>
                        </c:choose>
                    </td>
                    --%>
                            <td valign="middle" style="text-align:center">
                                <c:choose>
                                    <c:when test = "${!readOnly}">
                                        <kul:htmlControlAttribute property="${property}.newReviewAttachment.protocolPersonCanView" 
                                                  attributeEntry="${onlineReviewAttachmentAttributes.protocolPersonCanView}" readOnly = "${readOnly}"/>
                                    </c:when>
                                <c:otherwise>
                                    &nbsp;
                                </c:otherwise>
                                </c:choose>
                            </td>
                     
                        </c:if>
                        <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].hideReviewerName}">
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </c:if>
                        <td>
                            <div align="center">
                                <c:if test = "${!readOnly}">
                                    <html:image property="methodToCall.addOnlineReviewAttachment.${documentNumber}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:if>
                
                <c:set var="displayCount" value="0"/>           
                <c:forEach var="reviewAttachment" items="${bean.reviewAttachments}" varStatus="status">
                    <c:set var="displayCount" value="${displayCount + 1}"/>
                    
                        <tr>
                            <th>${displayCount}</th>
    
                                                    
                            <td style="text-align:center; vertical-align:middle">
                                
                                    <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].description" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.description}"
                                                          readOnly="${readOnly}" />
                                
                            </td>
                            <td>
                                <c:set var="addStyle" value="display: none;"/>
                                <c:set var="viewStyle" value="display: block;"/>
                                <c:if test="${empty KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].reviewAttachments[status.index].file.name}">
                                    <c:set var="addStyle" value="display: block;"/>
                                    <c:set var="viewStyle" value="display: none;"/>
                                </c:if>
                                ${KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].reviewAttachments[status.index].file.name}
                            
                            </td>
                            <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                    
                                <%--
                                <td style="text-align:center; vertical-align:middle">
                                
                                    <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].privateFlag" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.privateFlag}"
                                                          readOnly="${readOnly}" />
                                
                                </td>
                                --%>
                                <td style="text-align:center; vertical-align:middle">
                                
                                    <kul:htmlControlAttribute property="${property}.reviewAttachments[${status.index}].protocolPersonCanView" 
                                                          attributeEntry="${onlineReviewAttachmentAttributes.protocolPersonCanView}"
                                                          readOnly="${readOnly}" />
                                
                                </td>
                                   
                            </c:if>                  
                            <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].hideReviewerName}">
                                <c:choose>
                                    <c:when test="${KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[reviewIndex].reviewAttachments[status.index].displayReviewerName}">
                                        
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
                                                <html:image property="methodToCall.viewOnlineReviewAttachment.${documentNumber}.line.${status.index}.anchor${tabKey}"
                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                                    alt="View Protocol Attachment" onclick="excludeSubmitRestriction = true;"/>
                                                <html:image property="methodToCall.deleteOnlineReviewAttachment.${documentNumber}.line.${status.index}.anchor${tabKey}"
                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
                                                    alt="Delete Template"/>
                                            </div>
                                            
                                        </c:if>
                                    </nobr>
                                </div>
                            </td>
                        </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</kul:innerTab>
