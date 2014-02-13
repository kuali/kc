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

<kra:permission value="${KualiForm.actionHelper.canViewOnlineReviewers}">

<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewerType.attributes}" />
<c:set var="onlineReviewAttributes" value="${DataDictionary.IacucProtocolOnlineReview.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kul:innerTab tabTitle="Reviewers" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                     <tr>
                                       <th width="20%">Reviewer</th>
                                       <th width="10%">Type</th>
                                       
                                       <th width="10%">Determination Recommendation</th>
                                       <th width="10%">Due Date</th>
                                       
                                       <th width="20%">Reviewer</th>
                                       <th width="10%">Type</th>
                                       
                                       <th width="10%">Determination Recommendation</th>
                                       <th width="10%">Due Date</th>
                                    </tr>
                                    <c:forEach items="${KualiForm.actionHelper.currentReviewers}" var="reviewer" varStatus="status">
                                        <c:if test="${status.index % 2 == 0}"  >
                                            <tr>
                                                <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.currentReviewers[${status.index}].fullName" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" readOnly="true"/>
                                                    </div>
                                                </td>

                                                <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.currentReviewers[${status.index}].reviewerTypeCode" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}"
                                                            readOnlyAlternateDisplay="${reviewer.protocolReviewerType.description}" readOnly="true"/>
                                                    </div>
                                                </td>
                                                
                                                <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.onlineReviewsMap(${reviewer.protocolReviewerId}).protocolOnlineReviewDeterminationRecommendationCode" 
                                                          attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" readOnly="true" />
                                                    </div>
                                                </td>

                                                 <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.onlineReviewsMap(${reviewer.protocolReviewerId}).dateDue" 
                                                        	attributeEntry="${onlineReviewAttributes.dateDue}" readOnly="true"/>
                                                    </div>
                                                </td>

                                                <c:choose>
                                                 <c:when test="${fn:length(KualiForm.actionHelper.currentReviewers) >  status.index+1}">
                                                
                                                	<c:set var="nextReviewer" value="${KualiForm.actionHelper.currentReviewers[status.index+1]}" />
                                                	       
                                                    <td align="left" valign="middle">
                                                        <div align="left">
                                                            <kul:htmlControlAttribute property="actionHelper.currentReviewers[${status.index+1}].fullName" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" readOnly="true"/>
                                                        </div>
                                                    </td>

                                                    <td align="left" valign="middle">
                                                        <div align="left">
                                                            <kul:htmlControlAttribute property="actionHelper.currentReviewers[${status.index+1}].reviewerTypeCode" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}"
                                                            readOnlyAlternateDisplay="${KualiForm.actionHelper.currentReviewers[status.index+1].protocolReviewerType.description}" readOnly="true"/>
                                                        </div>
                                                    </td>
                                                    
                                                    <td align="left" valign="middle" >
	                                                    <div align="left">
	                                                        <kul:htmlControlAttribute property="actionHelper.onlineReviewsMap(${nextReviewer.protocolReviewerId}).protocolOnlineReviewDeterminationRecommendationCode" 
	                                                          attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" readOnly="true" />
	                                                    </div>
                                                	</td>

	                                                <td align="left" valign="middle" >
	                                                    <div align="left">
	                                                        <kul:htmlControlAttribute property="actionHelper.onlineReviewsMap(${nextReviewer.protocolReviewerId}).dateDue" 
	                                                        	attributeEntry="${onlineReviewAttributes.dateDue}" readOnly="true"/>
	                                                    </div>
          		                                    </td>
                                                 </c:when>
                                                 
                                                 <c:otherwise>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                 </c:otherwise>
                                               </c:choose>         
                                            </tr>
                                        </c:if>                    
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>         
                    </kul:innerTab>  
	            </td>
            </tr>
</kra:permission>