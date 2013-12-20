<%--
 Copyright 2005-2013 The Kuali Foundation

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

<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="taskName" required="true" %>
<%@ attribute name="tabCustomTitle" required="false" %>
<%@ attribute name="methodToCall" required="false" %>

<c:if test="${empty tabCustomTitle}">
	<c:set var="tabCustomTitle" value="Review Comments" />
</c:if>

<c:set var="readOnly" value="${not KualiForm.actionHelper.canManageReviewComments}" />

<c:set var="attributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />

<kul:innerTab tabTitle="${tabCustomTitle}" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
                <%-- Table headers --%>
                <tr>
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Private" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
                    <c:if test="${not bean.hideReviewerName}">
                        <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                        <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                                        
                <tr> 
                    <th class="infoline">add</th>

                    <td valign="middle" style="text-align:center">
                        <c:choose>
                            <c:when test="${empty bean.newReviewComment.protocolContingencyCode}" >
                                (select)
                            </c:when>
                            <c:otherwise>
                                ${bean.newReviewComment.protocolContingencyCode}
                            </c:otherwise>
                        </c:choose> 
                        <kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:${property}.newReviewComment.protocolContingencyCode" />
                    </td>
                                            
                    <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="${property}.newReviewComment.minuteEntry" 
                                                  attributeEntry="${attributes.minuteEntry}" />
                    </td>
                                     
                    <td valign="middle" style="text-align:center">
                        <kul:htmlControlAttribute property="${property}.newReviewComment.privateCommentFlag" 
                                                  attributeEntry="${attributes.privateCommentFlag}" />
                    </td>
					
					<td valign="middle" style="text-align:center">
				        <kul:htmlControlAttribute property="${property}.newReviewComment.finalFlag" 
                                                  attributeEntry="${attributes.finalFlag}" />
                    </td>
                    <c:if test="${not bean.hideReviewerName}">
                        <td>&nbsp;</td>
					    <td>&nbsp;</td>
                    </c:if>
                    <td>
                        <div align="center">
                            <html:image property="methodToCall.addReviewComment.taskName${taskName}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                        styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                
                <c:set var="displayCount" value="0"/>
                <c:forEach var="reviewComment" items="${bean.reviewComments}" varStatus="status">
                	<c:set var="isCommentForCurrentProtocol" value="${reviewComment.protocolId == KualiForm.actionHelper.protocol.protocolId}" /> 
                	<c:if test="${isCommentForCurrentProtocol}">
                        <c:set var="displayCount" value="${displayCount + 1}"/>
                    </c:if>
                   	<c:set var="reviewCommentReadOnly" value="${readOnly && reviewComment.persisted }" />
                	
                	<c:if test="${isCommentForCurrentProtocol}">
	                    <tr>
	                        <th class="infoline">${displayCount}</th>
	
	                        <c:choose>
	                            <c:when test="${empty reviewComment.protocolContingencyCode}">
	                                <td style="text-align:center;">
	                                    n/a
	                                </td>
	                            </c:when>
	                            <c:otherwise>
	                                <td style="text-align:center;">
	                                    ${reviewComment.protocolContingencyCode}
	                                    <c:choose>
		                            		<c:when test="${!reviewCommentReadOnly}">
	                                    		<kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency"
	                                                        fieldConversions="protocolContingencyCode:${property}.reviewComments[${status.index}].protocolContingencyCode" />
	                                        </c:when>
	                                    </c:choose>
	                                </td>
	                            </c:otherwise>
	                        </c:choose>
	                             
	                        <td>
	                        	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].minuteEntry"
	                                                      attributeEntry="${attributes.minuteEntry}" 
	                                                      readOnly="${reviewCommentReadOnly}" />
	                        </td>
	                                                   
	                        <td style="text-align:center; vertical-align:middle">
	                            <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].privateCommentFlag" 
	                                                      attributeEntry="${attributes.privateCommentFlag}"
	                                                      readOnly="${reviewCommentReadOnly}" />
	                        </td>
	                        
	                        <td style="text-align:center; vertical-align:middle">
	                            <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].finalFlag" 
	                                                      attributeEntry="${attributes.finalFlag}"
	                                                      readOnly="${reviewCommentReadOnly}" />
	                        </td>
                    <c:if test="${not bean.hideReviewerName}">
                       <c:choose>
                            <c:when test="${bean.reviewComments[status.index].displayReviewerName}">

	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].updateUserFullName" 
	                                                      attributeEntry="${attributes.updateUser}"
	                                                      readOnly="true" />  <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].updateTimestamp" 
	                                                      attributeEntry="${attributes.updateTimestamp}"
	                                                      readOnly="true" />
	                        </td>
	                        
	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].createUserFullName" 
	                                                      attributeEntry="${attributes.createUser}"
	                                                      readOnly="true" /> <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].createTimestamp" 
	                                                      attributeEntry="${attributes.createTimestamp}"
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
	                            	 	<html:image property="methodToCall.moveUpReviewComment.taskName${taskName}.line${status.index}.anchor${tabKey}"
	                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-moveup.gif' 
	                                                styleClass="tinybutton"/>
	                                	<html:image property="methodToCall.moveDownReviewComment.taskName${taskName}.line${status.index}.anchor${tabKey}"
	                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-movedown.gif' 
	                                                styleClass="tinybutton"/>
		                            	<c:choose>
		                            		<c:when test="${!reviewCommentReadOnly}">
				                                <html:image property="methodToCall.deleteReviewComment.taskName${taskName}.line${status.index}.anchor${tabKey}"
				                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' 
				                                            styleClass="tinybutton"/>
			                                </c:when>
		                                </c:choose>
	                                </nobr>
	                            </div>
	                        </td>
	                    </tr>
	                </c:if>
                </c:forEach>
                
                <c:if test="${not empty methodToCall}">
	                <tr>
	                	<td colspan="6">
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
