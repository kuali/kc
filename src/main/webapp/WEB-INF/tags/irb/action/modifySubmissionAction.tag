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

<c:set var="attributes" value="${DataDictionary.ProtocolModifySubmissionBean.attributes}" />
<c:set var="expeditedAttributes" value="${DataDictionary.ExpeditedReviewCheckListItem.attributes}" />
<c:set var="exemptAttributes" value="${DataDictionary.ExemptStudiesCheckListItem.attributes}" />
<%--<c:set var="reviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" /> --%>
<c:set var="action" value="modifySubmsionAction" />

<kra:permission value="${KualiForm.actionHelper.canModifyProtocolSubmission}">

<kul:innerTab tabTitle="Modify Submission Request" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolModifySubmissionBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.submissionTypeCode" attributeEntry="${attributes.submissionTypeCode}" />
                    </td>
                    <th width="20%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.protocolReviewTypeCode}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.protocolReviewTypeCode" 
                                                  attributeEntry="${attributes.protocolReviewTypeCode}" 
                                                  onchange="updateCheckList('actionHelper.protocolModifySubmissionBean.protocolReviewTypeCode')" />
                        </nobr>
                    </td>
                </tr>
                
                <c:if test="${KualiForm.protocolHelper.displayBillable}">
                	<tr>
                		<th>
                			<div align="right">
                            	<nobr>
                            		<kul:htmlAttributeLabel attributeEntry="${attributes.billable}" />
                            	</nobr>
                            </div>
                		</th>
                		<td colspan="3">
                			<kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.billable" attributeEntry="${attributes.billable}" disabled="${KualiForm.protocolHelper.billableReadOnly}" />
                		</td>
                	</tr>
                </c:if>
                
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.submissionQualifierTypeCode}" />
                        </div>
                    </th>
                    <td colspan="3" width="100%">
                        <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.submissionQualifierTypeCode" attributeEntry="${attributes.submissionQualifierTypeCode}" />
                    </td>
                </tr>
                
                
                   
                <c:choose>
	                <c:when test="${KualiForm.actionHelper.protocolModifySubmissionBean.protocolReviewTypeCode == '2'}">
	                	<tr id="expeditedReviewCheckList">
	                </c:when>
	                <c:otherwise>
	                    <tr id="expeditedReviewCheckList" style="display:none">
	                </c:otherwise>
                </c:choose>
                
                	<th>
                	    <div align="right">
                            *Checklist<br/>(Expedited)
                        </div>
                    </th>
                	<td colspan="3" style="padding: 0">
                	   	<table cellpadding="0" cellspacing="0" summary=""> 
				            <tbody>
			                	<c:forEach items="${KualiForm.actionHelper.protocolModifySubmissionBean.expeditedReviewCheckList}" var="item" varStatus="status">
			                		<tr>
                                        <td style="border-left: 0 none; border-right: 1 none; align: center; vertical-align:center">
	                                        <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.expeditedReviewCheckList[${status.index}].checked"
	                                                                  attributeEntry="${expeditedAttributes.checked}" />
	                                       
                                        </td>
                                        <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                            <kra:truncateComment textAreaFieldName="actionHelper.protocolModifySubmissionBean.expeditedReviewCheckList[${status.index}].description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${item.description}" displaySize="250"/>
                                        </td>
                                    </tr>
								</c:forEach>
							</tbody>
						</table>
                	</td>
                </tr>
                
                 <c:choose>
	                <c:when test="${KualiForm.actionHelper.protocolModifySubmissionBean.protocolReviewTypeCode == '3'}">
	                	<tr id="exemptStudiesCheckList">
	                </c:when>
	                <c:otherwise>
	                    <tr id="exemptStudiesCheckList" style="display:none">
	                </c:otherwise>
                </c:choose>
             
                	<th>
                	    <div align="right">
                            *Checklist<br/>(Exempt)
                        </div>
                    </th>
                	<td colspan="3" style="padding: 0">
                	   	<table cellpadding="0" cellspacing="0" summary=""> 
				            <tbody>
			                	<c:forEach items="${KualiForm.actionHelper.protocolModifySubmissionBean.exemptStudiesCheckList}" var="item" varStatus="status">
			                		<tr>
                                        <td style="border-left: 0 none; border-right: 1 none; align: center; vertical-align:center">
	                                        <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.exemptStudiesCheckList[${status.index}].checked"
	                                                                  attributeEntry="${exemptAttributes.checked}" />
	                                       
                                        </td>
                                        <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                            <kra:truncateComment textAreaFieldName="actionHelper.protocolModifySubmissionBean.exemptStudiesCheckList[${status.index}].description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${item.description}" displaySize="250"/>
                                        </td>
                                    </tr>
								</c:forEach>
							</tbody>
						</table>
                	</td>
                </tr>
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<html:image property="methodToCall.modifySubmsionAction.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
    <script>
        updateCheckList('actionHelper.protocolModifySubmissionBean.protocolReviewTypeCode');
    </script>
</kul:innerTab>

</kra:permission>
