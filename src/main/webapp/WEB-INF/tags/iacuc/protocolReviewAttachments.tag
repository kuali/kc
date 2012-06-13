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

<kra:permission value="${KualiForm.actionHelper.canViewOnlineReviewerComments}">

<c:set var="reviewAttachmentsAttributes" value="${DataDictionary.ProtocolReviewAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kul:innerTab tabTitle="Review Attachments" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <tr>
                                        <th><div align="left">&nbsp;</div></th> 
				                        <kul:htmlAttributeHeaderCell attributeEntry="${reviewAttachmentsAttributes.description}" hideRequiredAsterisk="true" scope="col" />
					                    <kul:htmlAttributeHeaderCell attributeEntry="${attachmentFileAttributes['name']}" hideRequiredAsterisk="true" scope="col"/>
				                        <kul:htmlAttributeHeaderCell attributeEntry="${reviewAttachmentsAttributes.privateFlag}" scope="col" />
                     <c:if test="${not KualiForm.actionHelper.hideReviewerNameForAttachment}">
                                        <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                                        <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    </c:if>
                                        <kul:htmlAttributeHeaderCell literalLabel="Action" scope = "col"/>
                                      </tr>
                                    <c:forEach items="${KualiForm.actionHelper.reviewAttachments}" var="reviewAttachment" varStatus="status">
                                      <%-- <c:if test="${reviewAttachment.displayReviewerName}"> --%>
                                            <c:set var="displayCount" value="${displayCount + 1}"/>
	                                        <tr>
	                                            <th class="infoline" align="right" >
	                                                   ${displayCount}
	                                            </th>
	                                                   <td>
	                                                   ${reviewAttachment.description}
	                                                    </td>
	                                     
	                                            <td>
	                                                 ${reviewAttachment.file.name}
	                                            </td>                
	                                            <%--    
	                                            <td style="text-align:center; vertical-align:middle">
	                                                <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].privateFlag" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.privateFlag}"
	                                                      readOnly="true" />
	                                            </td>
	                                            --%>
	                                            <td style="text-align:center; vertical-align:middle">
	                                                <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].protocolPersonCanView" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.protocolPersonCanView}"
	                                                      readOnly="true" />
	                                            </td>
                                                 
                    <c:if test="${not KualiForm.actionHelper.hideReviewerNameForAttachment}">
                        <c:choose>
                            <c:when test="${KualiForm.actionHelper.reviewAttachments[status.index].displayReviewerName}">
	                                            <td style="text-align:center; vertical-align:middle">
	                        	                     <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].updateUserFullName" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.createUser}"
	                                                      readOnly="true" />  <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].updateTimestamp" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.createTimestamp}"
	                                                      readOnly="true" />
	                                            </td>
	                        
	                                            <td style="text-align:center; vertical-align:middle">
	                        	                     <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].createUserFullName" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.createUser}"
	                                                      readOnly="true" /> <kul:htmlControlAttribute property="actionHelper.reviewAttachments[${status.index}].createTimestamp" 
	                                                      attributeEntry="${reviewAttachmentsAttributes.createTimestamp}"
	                                                      readOnly="true" />
	                                            </td>
                            </c:when>
                            <c:otherwise>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </c:otherwise>
                        </c:choose>
	                    </c:if>  
	                    <c:choose>                      
                            <c:when test="${KualiForm.actionHelper.reviewAttachments[status.index].displayViewButton}">
	                        <td>
	                            <div align="center">&nbsp;
	                            	<nobr>
		                <div id="viewAttachment"  class="viewsection"  style="${viewStyle} align="center">
									<html:image property="methodToCall.viewSubmissionReviewAttachment.taskName${taskName}.line${status.index}.anchor${tabKey}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Review Attachment" onclick="excludeSubmitRestriction = true;"/>
				          </div>
	                                </nobr>
	                            </div>
	                        </td>
                           </c:when>
                           <c:otherwise>
                                <td>&nbsp;</td>
                           </c:otherwise>
	                      </c:choose>
	                                </tr>
	                                  <%-- </c:if> --%>
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>         
                    </kul:innerTab>  
	            </td>
             </tr>

</kra:permission>