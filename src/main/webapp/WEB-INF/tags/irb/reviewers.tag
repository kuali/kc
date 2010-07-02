<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewer.attributes}" />
          	<tr>
           		<td class="tab-subhead" colspan="2" scope="row">
           		     
            		<kra:innerTab tabTitle="Reviewers" parentTab="" defaultOpen="false" tabErrorKey="" overrideDivClass="inner-subhead" >
    
                        <div class="innerTab-container" align="left">
                                
                            <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                <tbody>
                                    <tr>
                                       <th width="40%">Reviewer</th>
                                       <th width="10%">Type</th>
                                       <th width="40%">Reviewer</th>
                                       <th width="10%">Type</th>
                                    </tr>
                                    <c:forEach items="${KualiForm.actionHelper.selectedSubmission.protocolReviewers}" var="reviewer" varStatus="status">
                                        <c:if test="${status.index % 2 == 0}"  >
                                            <tr>
                                                <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.protocolReviewers[${status.index}].fullName" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" readOnly="true"/>
                                                    </div>
                                                </td>

                                                <td align="left" valign="middle" >
                                                    <div align="left">
                                                        <kul:htmlControlAttribute property="actionHelper.selectedSubmission.protocolReviewers[${status.index}].reviewerTypeCode" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}"
                                                            readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.protocolReviewers[status.index].protocolReviewerType.description}" readOnly="true"/>
                                                    </div>
                                                </td>

                                                <c:if test="${fn:length(KualiForm.actionHelper.selectedSubmission.protocolReviewers) >  status.index+1}">       
                                                    <td align="left" valign="middle">
                                                        <div align="left">
                                                            <kul:htmlControlAttribute property="actionHelper.selectedSubmission.protocolReviewers[${status.index+1}].fullName" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" readOnly="true"/>
                                                        </div>
                                                    </td>

                                                    <td align="left" valign="middle">
                                                        <div align="left">
                                                            <kul:htmlControlAttribute property="actionHelper.selectedSubmission.protocolReviewers[${status.index+1}].reviewerTypeCode" attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}"
                                                            readOnlyAlternateDisplay="${KualiForm.actionHelper.selectedSubmission.protocolReviewers[status.index+1].protocolReviewerType.description}" readOnly="true"/>
                                                        </div>
                                                    </td>

                                                </c:if>       
                                            </tr>
                                        </c:if>                    
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>         
                    </kra:innerTab>  
	            </td>
            </tr>    