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

<c:set var="attributes" value="${DataDictionary.ProtocolModifySubmissionBean.attributes}" />
<c:set var="acsAttributes" value="${DataDictionary.ProtocolAssignCmtSchedBean.attributes}" />
<c:set var="expeditedAttributes" value="${DataDictionary.ExpeditedReviewCheckListItem.attributes}" />
<c:set var="exemptAttributes" value="${DataDictionary.ExemptStudiesCheckListItem.attributes}" />
<%--<c:set var="reviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" /> --%>
<c:set var="action" value="modifySubmissionAction" />

<kra:permission value="${KualiForm.actionHelper.canModifyProtocolSubmission}">

<kul:innerTab tabTitle="Modify Submission Request" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolModifySubmissionBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>
	                <th style="width: 300px"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${acsAttributes.committeeId}" />
	                    </div>
	                </th>
	                <td style="width : 150px">
                        	                
	                    <c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />
                        <html:select property="actionHelper.assignCmtSchedBean.committeeId" onchange="onlyLoadScheduleDates('actionHelper.assignCmtSchedBean.committeeId', '${docNumber}', 'actionHelper.assignCmtSchedBean.scheduleId');" >
                            <c:forEach items="${KualiForm.actionHelper.assignCmtSchedActionCommitteeIdByUnitKeyValues}" var="option" >
                                <c:choose>                      
                                    <c:when test="${KualiForm.actionHelper.assignCmtSchedBean.committeeId == option.key}">
                                        <option value="${option.key}" selected="selected">${option.value}</option>
                                    </c:when>
                                    <c:otherwise>                               
                                        <c:out value="${option.value}"/>
                                        <option value="${option.key}">${option.value}</option>
                                    </c:otherwise>
                                </c:choose>                                                
                            </c:forEach>
                        </html:select>
	                </td>
	               
		            <th style="width: 150px"> 
		                <div align="right"><nobr>
		                    <kul:htmlAttributeLabel attributeEntry="${acsAttributes.scheduleId}" /></nobr>
		                </div>
		            </th>
		            <td>
		               <nobr>
				           <kul:htmlControlAttribute property="actionHelper.assignCmtSchedBean.scheduleId" 
				                                         attributeEntry="${acsAttributes.scheduleId}" />
				            <noscript>
                            <html:image property="methodToCall.refreshPage.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton"/>
                            </noscript>
		               </nobr>
		            </td>
	            </tr>
                <tr>
	               <c:choose>
	                    <c:when test="${KualiForm.actionHelper.protocolModifySubmissionBean.submissionTypeCode == '112'}">                      
	                        <th width="15%"> 
	                            <div align="right">
	                                <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
	                            </div>
	                        </th>
	                        <td>
	                            <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.submissionTypeCode" 
	                                                  attributeEntry="${attributes.submissionTypeCode}"
	                                                  readOnly="${true}" />
	                        </td>
	                    </c:when>
	                    <c:otherwise>
                            <th width="15%"> 
                                <div align="right">
                                    <nobr>
                                        <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
                                    </nobr>
                                </div>
                            </th>
                            <td>
                                <nobr>
                                    <kul:htmlControlAttribute property="actionHelper.protocolModifySubmissionBean.submissionTypeCode" 
                                                                       attributeEntry="${attributes.submissionTypeCode}" />
                                </nobr>
                            </td>
	                    </c:otherwise>
	                </c:choose>
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
							<html:image property="methodToCall.modifySubmissionAction.anchor${tabKey}"
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
