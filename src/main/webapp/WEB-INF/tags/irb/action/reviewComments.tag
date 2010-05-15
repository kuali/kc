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


<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.reviewcomments.ReviewComments" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="actionName" required="true" %>
<%@ attribute name="allowReadOnly" required="true" %>

<c:set var="minutesAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />

<kra:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                                    
                <%-- Table headers --%>
                <tr>
                    <th><div align="left">&nbsp</div></th> 
                    <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                    <kul:htmlAttributeHeaderCell literalLabel="Private" scope="col" />
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
                                ${bean.newComment.protocolContingencyCode}
                            </c:otherwise>
                        </c:choose> 
                        <kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:${property}.newComment.protocolContingencyCode" />
                    </td>
                                            
                    <td align="left" valign="middle">
                        <c:choose>
                            <c:when test="${empty bean.newComment.protocolContingencyCode}">
                                <kul:htmlControlAttribute property="${property}.newComment.minuteEntry" 
                                                          attributeEntry="${minutesAttributes.minuteEntry}" />
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

                    <td>
                        <div align="center">
                            <html:image property="methodToCall.add${actionName}ReviewComment.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                                        
                <c:forEach var="comment" items="${bean.comments}" varStatus="status">
                    <tr>
                    	<c:set var="readOnly" value="${allowReadOnly && comment.persisted }" />
                        <th>${status.index + 1}</th>

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
                                    ${comment.protocolContingencyCode}
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
                                                
                        <td>
                            <div align="center">&nbsp;
                            	<nobr>
                            	 	<html:image property="methodToCall.moveUp${actionName}ReviewComment.line${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-moveup.gif' styleClass="tinybutton"/>
                                	<html:image property="methodToCall.moveDown${actionName}ReviewComment.line${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-movedown.gif' styleClass="tinybutton"/>
	                            	<c:choose>
	                            		<c:when test="${!readOnly}">
			                                <html:image property="methodToCall.delete${actionName}ReviewComment.line${status.index}.anchor${tabKey}"
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
