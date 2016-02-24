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

<kra:permission value="${KualiForm.actionHelper.canViewOnlineReviewerComments}">

<c:set var="minutesAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kul:innerTab tabTitle="Review Comments" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <tr>
                                        <th><div align="left">&nbsp;</div></th> 
                                        <kul:htmlAttributeHeaderCell literalLabel="Standard Comment" scope="col" />
                                        <kul:htmlAttributeHeaderCell literalLabel="Comment" scope="col" />
                                        <c:if test="${not KualiForm.actionHelper.hidePrivateFinalFlagsForPublicCommentsAttachments}">
                                            <kul:htmlAttributeHeaderCell literalLabel="Private" scope="col" />
                                            <kul:htmlAttributeHeaderCell literalLabel="Final" scope = "col"/>
                                        </c:if>    
                    					<c:if test="${not KualiForm.actionHelper.hideReviewerName}">
                                        	<kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                                        	<kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                    					</c:if>
                                    </tr>
                                    <c:forEach items="${KualiForm.actionHelper.reviewComments}" var="comment" varStatus="status">
                                    	<c:set var="displayCount" value="${displayCount + 1}"/>
	                                    <tr>
	                                    	<th class="infoline" align="right" >
	                                           ${displayCount}
	                                        </th>
	
	                                        <c:choose>
	                                        	<c:when test="${empty comment.protocolContingencyCode}">
	                                            	<td style="text-align:center;">
	                                                	n/a
	                                                </td>
	                                            </c:when>
	                                            <c:otherwise>
	                                            	<td style="text-align:center;">
	                                                	${comment.protocolContingencyCode}
	                                                </td>
	                                            </c:otherwise>
	                                        </c:choose>
	                                     
	                                        <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
	                                            <kra:truncateComment textAreaFieldName="actionHelper.reviewComments[${status.index}].minuteEntry" action="protocolProtocolActions" textAreaLabel="Review Comment" textValue="${comment.minuteEntry}" displaySize="200"/>
                                            	<input type="hidden" name="actionHelper.reviewComments[${status.index}].minuteEntry" value="${comment.minuteEntry}" id="actionHelper.reviewComments[${status.index}].minuteEntry">
	                                        </td> 
	                                                       
	                                        <c:if test="${not KualiForm.actionHelper.hidePrivateFinalFlagsForPublicCommentsAttachments}">        
		                                        <td style="text-align:center; vertical-align:middle">
		                                            <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].privateCommentFlag" 
		                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
		                                                      readOnly="true" />
		                                        </td>		                                            
		                                        <td style="text-align:center; vertical-align:middle">
		                                            <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].finalFlag" 
		                                                      attributeEntry="${minutesAttributes.finalFlag}"
		                                                      readOnly="true" />
		                                        </td>
		                                    </c:if>

                    						<c:if test="${not KualiForm.actionHelper.hideReviewerName}">
                        						<c:choose>
                            						<c:when test="${KualiForm.actionHelper.reviewComments[status.index].displayReviewerName}">
	                                            		<td style="text-align:center; vertical-align:middle">
	                        	                     		<kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].updateUserFullName" 
	                                                      		attributeEntry="${minutesAttributes.updateUser}"
	                                                      		readOnly="true" />  <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].updateTimestamp" 
	                                                      		attributeEntry="${minutesAttributes.updateTimestamp}"
	                                                      		readOnly="true" />
	                                            		</td>
	                        
	                                            		<td style="text-align:center; vertical-align:middle">
	                        	                     		<kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].createUserFullName" 
	                                                      		attributeEntry="${minutesAttributes.createUser}"
	                                                      		readOnly="true" /> <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].createTimestamp" 
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
	                                    </tr>
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>         
                    </kul:innerTab>  
	            </td>
             </tr>

</kra:permission>
