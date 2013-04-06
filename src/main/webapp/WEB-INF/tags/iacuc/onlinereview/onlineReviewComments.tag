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



<%@ attribute name="bean" required="true" type="org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase" %>
<%@ attribute name="documentNumber" required="true"%>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="allowReadOnly" required="true" %>
<%@ attribute name="reviewIndex" required = "true" %>
<%@ attribute name="readOnly" required = "true" %>

<c:set var="minutesAttributes" value="${DataDictionary.IacucCommitteeScheduleMinute.attributes}" />

<kul:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="true" tabErrorKey="onlineReviewsActionHelper.reviewCommentsBeans[${reviewIndex}].*" useCurrentTabIndexAsKey="false">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
                <%-- Table headers --%>
                <tr>
                           
                
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                    <c:if test = "${KualiForm.editingMode['maintainIacucProtocolOnlineReviews'] or readOnly}">                    
                        <kul:htmlAttributeHeaderCell literalLabel="Private" scope="col" />
                        <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
                    </c:if>
                    <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewCommentsBeans[reviewIndex].hideReviewerName}">
                    <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                    <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    </c:if>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                        
               <c:if test = "${!readOnly}">                        
                <tr>    
                	<th class="infoline">add</th>

                    <td valign="middle" style="text-align:center">
                                           
                        <c:choose>
                            <c:when test="${empty bean.newReviewComment.protocolContingencyCode}" >
                                (select)
                            </c:when>
                            <c:otherwise>
                                <kul:htmlControlAttribute property="${property}.newReviewComment.protocolContingencyCode" 
                                                  attributeEntry="${minutesAttributes.protocolContingencyCode}" readOnly="${readOnly}" />
                            </c:otherwise>
                        </c:choose> 
                    		<c:if test = "${!readOnly}">
                        		<kul:lookup boClassName="org.kuali.kra.iacuc.committee.meeting.IacucProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:${property}.newReviewComment.protocolContingencyCode"  />
                       		</c:if>
                    </td>
                                            
	               <td align="left" valign="middle">
                              <kul:htmlControlAttribute property="${property}.newReviewComment.minuteEntry" 
                                                        attributeEntry="${minutesAttributes.minuteEntry}" readOnly = "${readOnly}" />
                       
                    </td>
                    
                    <c:if test = "${KualiForm.editingMode['maintainIacucProtocolOnlineReviews'] or readOnly}">                    
                                     
                    <td valign="middle" style="text-align:center">
                        <c:choose>
                        <c:when test = "${!readOnly}">
                        	<kul:htmlControlAttribute property="${property}.newReviewComment.privateCommentFlag" 
                                                  attributeEntry="${minutesAttributes.privateCommentFlag}" readOnly = "${readOnly}"/>
                      	</c:when>
                      	<c:otherwise>
                      		&nbsp;
                      	</c:otherwise>
                      	</c:choose>
                    </td>
					
					<td valign="middle" style="text-align:center">
				        <c:choose>
				        	<c:when test = "${!readOnly}">
				        		<kul:htmlControlAttribute property="${property}.newReviewComment.finalFlag" 
                                                  attributeEntry="${minutesAttributes.finalFlag}" readOnly = "${readOnly}"/>
                        	</c:when>
                        	<c:otherwise>
                        		&nbsp;
                        	</c:otherwise>
                        </c:choose>
                    </td>
                    </c:if>
                    <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewCommentsBeans[reviewIndex].hideReviewerName}">
                    <td>&nbsp;</td>
					<td>&nbsp;</td>
					</c:if>
                    <td>
                        <div align="center">
                            <c:if test = "${!readOnly}">
                            	<html:image property="methodToCall.addOnlineReviewComment.${documentNumber}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                            </c:if>
                        </div>
                    </td>
                </tr>
                </c:if>
                <c:set var="displayCount" value="0"/>           
                <c:forEach var="reviewComment" items="${bean.reviewComments}" varStatus="status">
                    <c:set var="isCommentForCurrentProtocol" value="${reviewComment.protocolId == KualiForm.actionHelper.protocol.protocolId}" /> 
                	<c:set var="displayCount" value="${displayCount + 1}"/>
                	
                	<c:if test="${isCommentForCurrentProtocol}">
	                    <tr>
	                        <th>${displayCount}</th>
	
	                        <c:choose>
	                            <c:when test="${empty reviewComment.protocolContingencyCode}">
	                                <td style="text-align:center;">
	                                    n/a
	                                </td>
	                                <td>
	                                    <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].minuteEntry"
	                                                              attributeEntry="${minutesAttributes.minuteEntry}" readOnly="${reviewComment.readOnly}" />
	                                </td>
	                            </c:when>
	                            <c:otherwise>
	                                <td style="text-align:center;">
	                                   	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].protocolContingencyCode" 
	                                                  attributeEntry="${minutesAttributes.protocolContingencyCode}" readOnly="${reviewComment.readOnly}" />
	                               
	                                    <c:choose>
		                            		<c:when test="${!reviewComment.readOnly}">
	                                    		<kul:lookup boClassName="org.kuali.kra.iacuc.committee.meeting.IacucProtocolContingency"
	                                                fieldConversions="protocolContingencyCode:${property}.reviewComments[${status.index}].protocolContingencyCode" />
	                                        </c:when>
	                                    </c:choose>
	                                </td>
	                                <td>
	                                    <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].minuteEntry"
	                                                              attributeEntry="${minutesAttributes.minuteEntry}" readOnly="${reviewComment.readOnly}" />
	                                </td>
	                            </c:otherwise>
	                        </c:choose>
	                        <c:if test = "${KualiForm.editingMode['maintainIacucProtocolOnlineReviews'] or readOnly}">                    
	                                                
				                <td style="text-align:center; vertical-align:middle">
		                            
		                            	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].privateCommentFlag" 
		                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
		                                                      readOnly="${reviewComment.readOnly}" />
		                            
		                        </td>
		                                                
		                        <td style="text-align:center; vertical-align:middle">
		                            
		                            	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].finalFlag" 
		                                                      attributeEntry="${minutesAttributes.finalFlag}"
		                                                      readOnly="${reviewComment.readOnly}" />
		                            
		                        </td>
	                        </c:if>
                    <c:if test="${not KualiForm.onlineReviewsActionHelper.reviewCommentsBeans[reviewIndex].hideReviewerName}">
                       <c:choose>
                            <c:when test="${KualiForm.onlineReviewsActionHelper.reviewCommentsBeans[reviewIndex].reviewComments[status.index].displayReviewerName}">
	          	                        
	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].updateUserFullName" 
	                                                      attributeEntry="${minutesAttributes.updateUser}"
	                                                      readOnly="true" />  <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].updateTimestamp" 
	                                                      attributeEntry="${minutesAttributes.updateTimestamp}"
	                                                      readOnly="true" />
	                        </td>
	                        
	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].createUserFullName" 
	                                                      attributeEntry="${minutesAttributes.createUser}"
	                                                      readOnly="true" /> <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].createTimestamp" 
	                                                      attributeEntry="${minutesAttributes.createTimestamp}"
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
	                            		<c:if test = "${!reviewComment.readOnly}">
	                            	 		<html:image property="methodToCall.moveUpOnlineReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
	                                        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-moveup.gif' styleClass="tinybutton"/>
	                                		<html:image property="methodToCall.moveDownOnlineReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
	                                        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-movedown.gif' styleClass="tinybutton"/>
		                            		<html:image property="methodToCall.deleteOnlineReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
				                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			                                
		                                </c:if>
	                                </nobr>
	                            </div>
	                        </td>
	                    </tr>
	                </c:if>
                </c:forEach>

            </tbody>
        </table>
    </div>
</kul:innerTab>
