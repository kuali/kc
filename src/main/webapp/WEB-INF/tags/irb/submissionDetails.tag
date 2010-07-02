<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="protocolSubmissionAttributes" value="${DataDictionary.ProtocolSubmission.attributes}" />
<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
            <tr>
              	<td class="tab-subhead" colspan="2" scope="row">
                   <input type="hidden" name="actionHelper.currentSubmissionNumber" value="${KualiForm.actionHelper.currentSubmissionNumber}"/>
                        <kra:innerTab tabTitle="Submission Details" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
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
                                                    <kul:htmlControlAttribute property="actionHelper.selectedSubmission.committee.committeeName" attributeEntry="${committeeAttributes.committeeName}" readOnly="true"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th class="infoline" align="right" width="20%">
                                                   Scheduled Date:
                                            </th>

                                            <td align="left" valign="middle" width="80%">
                                                <div align="left">
                                                    <kul:htmlControlAttribute property="actionHelper.selectedSubmission.committeeSchedule.scheduledDate" attributeEntry="${committeeScheduleAttributes.scheduledDate}" readOnly="true"/>
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
                                    </tbody>
                                </table>
                              </div>         
                        </kra:innerTab>  
	                </td>
                </tr>    