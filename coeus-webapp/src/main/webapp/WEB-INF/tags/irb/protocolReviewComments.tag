<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
                                    <c:forEach items="${KualiForm.actionHelper.sortedReviewComments}" var="comment" varStatus="status">
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
	                                                <kra:truncateComment textAreaFieldName="actionHelper.sortedReviewComments[${status.index}].minuteEntry" action="protocolProtocolActions" textAreaLabel="Review Comment" textValue="${comment.minuteEntry}" displaySize="200"/>
	                                                <input type="hidden" name="actionHelper.sortedReviewComments[${status.index}].minuteEntry" value="${comment.minuteEntry}" id="actionHelper.sortedReviewComments[${status.index}].minuteEntry">
	                                            </td>                
	                                            
	                                            <c:if test="${not KualiForm.actionHelper.hidePrivateFinalFlagsForPublicCommentsAttachments}">
		                                            <td style="text-align:center; vertical-align:middle">
		                                                <kul:htmlControlAttribute property="actionHelper.sortedReviewComments[${status.index}].privateCommentFlag"
		                                                      attributeEntry="${minutesAttributes.privateCommentFlag}"
		                                                      readOnly="true" />
		                                            </td>
		                                            
		                                            <td style="text-align:center; vertical-align:middle">
		                                                <kul:htmlControlAttribute property="actionHelper.sortedReviewComments[${status.index}].finalFlag"
		                                                      attributeEntry="${minutesAttributes.finalFlag}"
		                                                      readOnly="true" />
		                                            </td>
                                                </c:if>
                                                <c:if test="${not KualiForm.actionHelper.hideReviewerName}">
                                                    <c:choose>
                                                        <c:when test="${KualiForm.actionHelper.sortedReviewComments[status.index].displayReviewerName}">
	                                                        <td style="text-align:center; vertical-align:middle">
	                                    	                     <kul:htmlControlAttribute property="actionHelper.sortedReviewComments[${status.index}].updateUserFullName"
	                                                                  attributeEntry="${minutesAttributes.updateUser}"
	                                                                  readOnly="true" />  <kul:htmlControlAttribute property="actionHelper.sortedReviewComments[${status.index}].updateTimestamp"
	                                                                  attributeEntry="${minutesAttributes.updateTimestamp}"
	                                                                  readOnly="true" />
	                                                        </td>
	                        
	                                                        <td style="text-align:center; vertical-align:middle">
             	                        	                     <kul:htmlControlAttribute property="actionHelper.reviewComments[${status.index}].createUserFullName"
	                                                                  attributeEntry="${minutesAttributes.createUser}"
	                                                                  readOnly="true" /> <kul:htmlControlAttribute property="actionHelper.sortedReviewComments[${status.index}].createTimestamp"
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