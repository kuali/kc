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



<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean" %>
<%@ attribute name="documentNumber" required="true"%>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="allowReadOnly" required="true" %>
<%@ attribute name="reviewIndex" required = "true" %>
<%@ attribute name="readOnly" required = "true" %>

<c:set var="minutesAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />

<kul:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="true" tabErrorKey="onlineReviewsActionHelper.reviewCommentsBeans[${reviewIndex}].*" useCurrentTabIndexAsKey="false">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
                <%-- Table headers --%>
                <tr>
                    <th><div align="left">&nbsp;</div></th> 
                    <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                    <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                    
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
                        		<kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:${property}.newReviewComment.protocolContingencyCode"  />
                       		</c:if>
                    </td>
                                            
	               <td align="left" valign="middle">
                              <kul:htmlControlAttribute property="${property}.newReviewComment.minuteEntry" 
                                                        attributeEntry="${minutesAttributes.minuteEntry}" readOnly = "${readOnly}" />
                       
                    </td>
                    
                    <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                    
                                     
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
	                                                              attributeEntry="${minutesAttributes.minuteEntry}" readOnly="${readOnly}" />
	                                </td>
	                            </c:when>
	                            <c:otherwise>
	                                <td style="text-align:center;">
	                                   	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].protocolContingencyCode" 
	                                                  attributeEntry="${minutesAttributes.protocolContingencyCode}" readOnly="${readOnly}" />
	                               
	                                    <c:choose>
		                            		<c:when test="${!readOnly}">
	                                    		<kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency"
	                                                fieldConversions="protocolContingencyCode:${property}.reviewComments[${status.index}].protocolContingencyCode" />
	                                        </c:when>
	                                    </c:choose>
	                                </td>
	                                <td>
	                                    <kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].minuteEntry"
	                                                              attributeEntry="${minutesAttributes.minuteEntry}" readOnly="${readOnly}" />
	                                </td>
	                            </c:otherwise>
	                        </c:choose>
	                        <c:if test = "${KualiForm.editingMode['maintainProtocolOnlineReviews'] or readOnly}">                    
	                                                
			                <td style="text-align:center; vertical-align:middle">
	                            
	                            	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].privateCommentFlag" 
	                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
	                                                      readOnly="${readOnly}" />
	                            
	                        </td>
	                                                
	                        <td style="text-align:center; vertical-align:middle">
	                            
	                            	<kul:htmlControlAttribute property="${property}.reviewComments[${status.index}].finalFlag" 
	                                                      attributeEntry="${minutesAttributes.finalFlag}"
	                                                      readOnly="${readOnly}" />
	                            
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
	                            		<c:if test = "${!readOnly}">
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
