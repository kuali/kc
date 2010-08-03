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



<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.reviewcomments.ReviewerComments" %>
<%@ attribute name="documentNumber" required="true"%>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="actionName" required="true" %>
<%@ attribute name="allowReadOnly" required="true" %>
<%@ attribute name="reviewIndex" required = "true" %>

<c:set var="minutesAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />

<kra:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="true" tabErrorKey="onlineReviewsActionHelper.protocolOnlineReviewsReviewCommentsList[${reviewIndex}] .*" useCurrentTabIndexAsKey="false">
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
                    <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                    <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
                </tr>
                        
                                        
                <tr>    
                	<th class="infoline">add</th>

                    <td valign="middle" style="text-align:center">
                                           
                        <c:choose>
                            <c:when test="${empty bean.newComment.protocolContingencyCode}" >
                                (select)
                            </c:when>
                            <c:otherwise>
                                <kul:htmlControlAttribute property="${property}.newComment.protocolContingencyCode" 
                                                  attributeEntry="${minutesAttributes.protocolContingencyCode}" readOnly="${readOnly}" />
                            </c:otherwise>
                        </c:choose> 
                        <kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:${property}.newComment.protocolContingencyCode" />
                    </td>
                                            
                    <td align="left" valign="middle">
                        <c:choose>
                            <c:when test="${empty bean.newComment.protocolContingencyCode}">
                              <kul:htmlControlAttribute property="${property}.newComment.minuteEntry" 
                                                        attributeEntry="${minutesAttributes.minuteEntry}" readOnly = "${readOnly}" />
                            </c:when>
                            <c:otherwise>
                                ${bean.newComment.minuteEntry}
                            </c:otherwise>
                        </c:choose>
                    </td>
                                     
                    <td valign="middle" style="text-align:center">
                        <kul:htmlControlAttribute property="${property}.newComment.privateCommentFlag" 
                                                  attributeEntry="${minutesAttributes.privateCommentFlag}" />
                    </td>
					
					<td valign="middle" style="text-align:center">
				        <kul:htmlControlAttribute property="${property}.newComment.finalFlag" 
                                                  attributeEntry="${minutesAttributes.finalFlag}" />
                    </td>
                    
                    <td>&nbsp;</td>
					<td>&nbsp;</td>
                    <td>
                        <div align="center">
                            <html:image property="methodToCall.add${actionName}ReviewComment.${documentNumber}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                <c:set var="displayCount" value="0"/>
                                                   
                <c:forEach var="comment" items="${bean.comments}" varStatus="status">
                	<c:set var="doHide" value="${comment.protocolId != bean.protocolId}" />
                    <tr <c:choose>
                        		<c:when test="${doHide == true }">
                        			<c:set var="displayCount" value="${displayCount + 1}"/>
                        			style="display:none"
                        		</c:when>
                        	</c:choose>>
                    	<c:set var="readOnly" value="${allowReadOnly && comment.persisted }" />
                    	<c:set var="readOnly" value = "false"/>
                        <th>
                        	<c:choose>
                        		<c:when test="${doHide == false }">
                        			<c:set var="displayCount" value="${displayCount + 1}"/>
                        			${displayCount }
                        		</c:when>
                        	</c:choose>
                        </th>

                        <c:choose>
                            <c:when test="${empty comment.protocolContingencyCode}">
                                <td style="text-align:center;">
                                    n/a
                                </td>
                                <td>
                                    <kul:htmlControlAttribute property="${property}.comments[${status.index}].minuteEntry"
                                                              attributeEntry="${minutesAttributes.minuteEntry}" readOnly="${readOnly}" />
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center;">
                                   	<kul:htmlControlAttribute property="${property}.comments[${status.index}].protocolContingencyCode" 
                                                  attributeEntry="${minutesAttributes.protocolContingencyCode}" readOnly="${readOnly}" />
                               
                                    <c:choose>
	                            		<c:when test="${!readOnly}">
                                    		<kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency"
                                                fieldConversions="protocolContingencyCode:${property}.comments[${status.index}].protocolContingencyCode" />
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    ${comment.minuteEntry}
                                </td>
                            </c:otherwise>
                        </c:choose>
                                                
                        <td style="text-align:center; vertical-align:middle">
                            <kul:htmlControlAttribute property="${property}.comments[${status.index}].privateCommentFlag" 
                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
                                                      readOnly="${readOnly}" />
                        </td>
                                                
                        <td style="text-align:center; vertical-align:middle">
                            <kul:htmlControlAttribute property="${property}.comments[${status.index}].finalFlag" 
                                                      attributeEntry="${minutesAttributes.finalFlag}"
                                                      readOnly="${readOnly}" />
                        </td>
                        
                        <td style="text-align:center; vertical-align:middle">
                        	<kul:htmlControlAttribute property="${property}.comments[${status.index}].updateUserFullName" 
                                                      attributeEntry="${minutesAttributes.updateUser}"
                                                      readOnly="true" />  <kul:htmlControlAttribute property="${property}.comments[${status.index}].updateTimestamp" 
                                                      attributeEntry="${minutesAttributes.updateTimestamp}"
                                                      readOnly="true" />
                        </td>
                        
                        <td style="text-align:center; vertical-align:middle">
                        	<kul:htmlControlAttribute property="${property}.comments[${status.index}].createUserFullName" 
                                                      attributeEntry="${minutesAttributes.createUser}"
                                                      readOnly="true" /> <kul:htmlControlAttribute property="${property}.comments[${status.index}].createTimestamp" 
                                                      attributeEntry="${minutesAttributes.createTimestamp}"
                                                      readOnly="true" />
                        </td>
                        
                        <td>
                            <div align="center">&nbsp;
                            	<nobr>
                            	 	<html:image property="methodToCall.moveUp${actionName}ReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-moveup.gif' styleClass="tinybutton"/>
                                	<html:image property="methodToCall.moveDown${actionName}ReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-movedown.gif' styleClass="tinybutton"/>
	                            	<c:choose>
	                            		<c:when test="${!readOnly}">
			                                <html:image property="methodToCall.delete${actionName}ReviewComment.${documentNumber}.line.${status.index}.anchor${tabKey}"
			                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
		                                </c:when>
	                                </c:choose>
                                </nobr>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</kra:innerTab>
