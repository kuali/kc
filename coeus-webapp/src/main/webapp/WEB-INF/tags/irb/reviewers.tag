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

<kra:permission value="${KualiForm.actionHelper.canViewOnlineReviewers}">

<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewerType.attributes}" />
<c:set var="onlineReviewAttributes" value="${DataDictionary.ProtocolOnlineReview.attributes}" />
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
