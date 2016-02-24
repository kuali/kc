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
<c:set var="protocolSubmissionAttributes" value="${DataDictionary.IacucProtocolSubmission.attributes}" />
<c:set var="committeeAttributes" value="${DataDictionary.IacucCommittee.attributes}" />
<c:set var="committeeScheduleAttributes" value="${DataDictionary.IacucCommitteeSchedule.attributes}" />
    <tr>
    	<td class="tab-subhead" colspan="2" scope="row">
            <input type="hidden" name="actionHelper.currentSubmissionNumber" value="${KualiForm.actionHelper.currentSubmissionNumber}"/>
            <kul:innerTab tabTitle="Submission Details" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                <div class="innerTab-container" align="left">
                    
                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Committee Id:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.committeeId" attributeEntry="${committeeAttributes.committeeId}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Committee Name:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.iacucCommittee.committeeName" attributeEntry="${committeeAttributes.committeeName}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Scheduled Date:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.iacucCommitteeSchedule.scheduledDate" attributeEntry="${committeeScheduleAttributes.scheduledDate}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Submission Type:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.submissionTypeCode" attributeEntry="${protocolSubmissionAttributes.submissionTypeCode}" 
                                              readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.protocolSubmissionType.description}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Review Type:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.protocolReviewTypeCode" attributeEntry="${protocolSubmissionAttributes.protocolReviewTypeCode}" 
                                              readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.protocolReviewType.description}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Type Qualifier:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.submissionTypeQualifierCode" attributeEntry="${protocolSubmissionAttributes.submissionTypeQualifierCode}" 
                                              readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.protocolSubmissionQualifierType.description}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th class="infoline" align="right" width="20%">
                                       Submission Status:
                                </th>

                                <td align="left" valign="middle" width="80%">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.submissionStatusCode" attributeEntry="${protocolSubmissionAttributes.submissionStatusCode}" 
                                              readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.submissionStatus.description}" readOnly="true"/>
                                    </div>
                                </td>
                            </tr>
                            <c:if test="${KualiForm.protocolHelper.displayBillable}">
                            	<tr>
                            		<th class="infoline" align="right" width="20%">
                                       <kul:htmlAttributeLabel attributeEntry="${protocolSubmissionAttributes.billable}" />
                                	</th>
                                	<td align="left" valign="middle" width="80%">
                                		<div align="left">
                                			<kul:htmlControlAttribute property="actionHelper.selectedSubmission.billable" attributeEntry="${protocolSubmissionAttributes.billable}" readOnly="true"/>
                                		</div>
                                	</td>
                            	</tr>
                            </c:if>
                            
                        </tbody>
                    </table>
                </div>         
            </kul:innerTab>  
		</td>
    </tr>    
