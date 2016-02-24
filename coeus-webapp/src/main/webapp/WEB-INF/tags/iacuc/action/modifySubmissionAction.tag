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

<c:set var="attributes" value="${DataDictionary.IacucProtocolModifySubmissionBean.attributes}" />
<c:set var="reviewerAttributes" value="${DataDictionary.IacucProtocolReviewerBean.attributes}" />
<c:set var="action" value="modifySubmissionAction" />
<c:set var="cmtAttributes" value="${DataDictionary.IacucProtocolAssignCmtBean.attributes}" />

<script language="javascript" src="dwr/interface/ProtocolActionAjaxService.js"></script>
<script language="javascript" src="dwr/interface/IacucProtocolActionAjaxService.js"></script>
<c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />
<c:set var="fullCommitteeReview" value="<%=org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType.FULL_COMMITTEE_REVIEW%>" />
<c:set var="notifyIacucSubmissionType" value="<%=org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType.NOTIFY_IACUC%>" />
<c:set var="requestSuspensionSubmissionType" value="<%=org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType.REQUEST_SUSPEND%>" />

<kra:permission value="${KualiForm.actionHelper.canModifyProtocolSubmission}">
${kfunc:registerEditableProperty(KualiForm, "actionHelper.iacucProtocolModifySubmissionBean.numberOfReviewers")}
<html:hidden styleId="numberOfReviewers" property="actionHelper.iacucProtocolModifySubmissionBean.numberOfReviewers" value="${fn:length(KualiForm.actionHelper.iacucProtocolModifySubmissionBean.reviewers)}"></html:hidden>

    <script type="text/javascript">
        jQuery(document).ready(function() {
            protocolModifySubmissionReviewers('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId',
            'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId},
                    'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode');
            setModifySubmissionDefaultReviewerTypeCode('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId',
                    'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId},
                    'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode');
        });
    </script>

<kul:innerTab tabTitle="Modify Submission Request" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.iacucProtocolModifySubmissionBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            
                <tr>
	                <th style="width: 150px"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                    </div>
	                </th>
	                <td style="width : 150px">               
                       	<html:select styleId="actionHelper.iacucProtocolModifySubmissionBean.committeeId" property="actionHelper.iacucProtocolModifySubmissionBean.committeeId" 
                       	onchange="loadScheduleDates('actionHelper.iacucProtocolModifySubmissionBean.committeeId', '${docNumber}', 'actionHelper.iacucProtocolModifySubmissionBean.scheduleId');
                       	          protocolModifySubmissionReviewers('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                    'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                    'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode'); 
                                  setModifySubmissionDefaultReviewerTypeCode('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                             'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                             'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode');" >                              
                            <c:forEach items="${KualiForm.actionHelper.modifySubmissionActionCommitteeIdByUnitKeyValues}" var="option" >
                                <c:choose>                      
                                    <c:when test="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.committeeId == option.key}">
                                        <option value="${option.key}" selected="selected">${option.value}</option>
                                    </c:when>
                                    <c:otherwise>                               
                                        <option value="${option.key}">${option.value}</option>
                                    </c:otherwise>
                                </c:choose>                                                
                            </c:forEach>
                        </html:select>
                    </td>
                    <th style="width: 150px"> 
	                    <div align="right">
	                        <kul:htmlAttributeLabel attributeEntry="${attributes.scheduleId}" />
	                    </div>
	                </th>
                        <td>
		                	<nobr>
		                    	<kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.scheduleId" 
				                                	      attributeEntry="${attributes.scheduleId}" 
				                                	      onchange="protocolModifySubmissionReviewers('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                                                      'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                                                      'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode'); 
                                                                   setModifySubmissionDefaultReviewerTypeCode('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                                              'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                                              'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode');"/>
		                    </nobr>
		               </td>
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode == notifyIacucSubmissionType }">                      
                            <th width="15%"> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
                                </div>
                            </th>
                            <td>
                                <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode" 
                                                      attributeEntry="${attributes.submissionTypeCode}"
                                                      readOnly="true" />
                            </td>
                        </c:when>
                        <c:when test="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode == requestSuspensionSubmissionType }">                      
                            <th width="15%"> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
                                </div>
                            </th>
                            <td>
                                <html:select property="actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode">
                                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.iacuc.actions.submit.IacucSubmissionTypeValuesFinder', paramMap)}" var="option">  
                                        <c:choose>                     
                                            <c:when test="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode == option.key}">
                                                <option value="${option.key}" selected="selected">${option.value}</option>
                                            </c:when>
                                            <c:otherwise>                              
                                                <option value="${option.key}">${option.value}</option>
                                            </c:otherwise>
                                        </c:choose>                                               
                                    </c:forEach>
                                </html:select>
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
                                    <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode" 
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode" 
                                                  attributeEntry="${attributes.protocolReviewTypeCode}" 
                                                  onchange="protocolModifySubmissionReviewers('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                                              'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                                              'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode'); 
                                                           setModifySubmissionDefaultReviewerTypeCode('getModifySubmissionProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 
                                                                                      'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, 
                                                                                      'iacucProtocolModifySubmissionBean', 'actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode');"/>
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
                			<kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.billable" attributeEntry="${attributes.billable}" disabled="${KualiForm.protocolHelper.billableReadOnly}" />
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.submissionQualifierTypeCode" attributeEntry="${attributes.submissionQualifierTypeCode}" />
                    </td>
                </tr>
                                <!-- Assign reviewers part -->
                
                  <c:choose>
		                    <c:when test="${empty KualiForm.actionHelper.iacucProtocolModifySubmissionBean.committeeId}">
		                        <tr id="reviewers" style="display: none">
		                    </c:when>
		                    <c:when test="${empty KualiForm.actionHelper.iacucProtocolModifySubmissionBean.scheduleId and KualiForm.actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode == fullCommitteeReview}">
                                <tr id="reviewers" style="display: none">
                            </c:when>
		                    <c:otherwise>
		                        <tr id="reviewers">
		                    </c:otherwise>
		                </c:choose>
		                <th>
		                    <div align="right">
		                        Reviewers:
		                    </div>
		                </th>
                
                            
                    <td colspan="3">
                        <div width="100%">
                            <table style="border: 0 none yellow">
                                <tr valign="top">
                                    <td width="50%" style="border: 0 none">
                                        <table id="reviewersTableLeft" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
                                            <c:forEach var="reviewer" items="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.leftReviewers}" varStatus="status">
                                                <tr>
                                                    <td style="border: 0 none">
                                                        ${reviewer.fullName}
                                                    </td>
                                                    <td style="border: 0 none">
                                                    
                                                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index}].reviewerTypeCode"
                                                                                  attributeEntry="${reviewerAttributes.reviewerTypeCode}"/> 
                                                                                  
                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index}].personId" 
                                                        			value="${reviewer.personId}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index}].fullName" 
                                                        			value="${reviewer.fullName}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index}].nonEmployeeFlag" 
                                                        			value="${reviewer.nonEmployeeFlag}" />						                        
                                                        
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <td style="border: 0 none">
                                        <table id="reviewersTableRight" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
                                            <c:set var="numLeftReviewers" value="${fn:length(KualiForm.actionHelper.iacucProtocolModifySubmissionBean.leftReviewers)}" />
                                            <c:forEach var="reviewer" items="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.rightReviewers}" varStatus="status">
                                                <tr>
                                                    <td style="border: 0 none">
                                                        ${reviewer.fullName}
                                                    </td>
                                                    <td style="border: 0 none">
                                                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index + numLeftReviewers}].reviewerTypeCode"
                                                                                  attributeEntry="${reviewerAttributes.reviewerTypeCode}" />

                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index + numLeftReviewers}].personId" 
                                                        			value="${reviewer.personId}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index + numLeftReviewers}].fullName" 
                                                        			value="${reviewer.fullName}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolModifySubmissionBean.reviewer[${status.index + numLeftReviewers}].nonEmployeeFlag" 
                                                        			value="${reviewer.nonEmployeeFlag}" />						                        
                                                        
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                <th rowspan="2">
                	<div align="right">
                            	<nobr>
                				Review Type Determination:
                				</nobr>
                	</div>
                </th>
                
                <td rowspan="2">
                <c:choose>
		              <c:when test="${empty KualiForm.actionHelper.reviewRecommendations}">
		              No recommendations so far.
		              </c:when>
		              <c:otherwise>
                	  <c:forEach var="recommendation" items="${KualiForm.actionHelper.reviewRecommendations}" varStatus="status">
                	  	<c:out value="${recommendation}" /><br>
                	  </c:forEach>
					</c:otherwise>   
				</c:choose>    	
                </td>
                
               	<th>
                	<div align="right">
                    	<nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.dueDate}" />
                        </nobr>
                    </div>
                </th>
                <td>
                <nobr>
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.dueDate" attributeEntry="${attributes.dueDate}" /> 
                </nobr>
                </td>
                </tr>
                
                <tr>
               
                <td colspan="2">
                	<div align="center">
                	<html:image property="methodToCall.sendReviewDeterminationNotificationAction.anchor${tabKey}" src='${ConfigProperties.kra.externalizable.images.url}buttonsmall_send_notification.gif' styleClass="tinybutton"/>
                	</div>
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
    
  </kul:innerTab>

</kra:permission>
