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

<c:set var="attributes" value="${DataDictionary.ProtocolSubmitAction.attributes}" />
<c:set var="expeditedAttributes" value="${DataDictionary.ExpeditedReviewCheckListItem.attributes}" />
<c:set var="exemptAttributes" value="${DataDictionary.ExemptStudiesCheckListItem.attributes}" />
<c:set var="reviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />



<noscript>
${kfunc:registerEditableProperty(KualiForm, "actionHelper.protocolSubmitAction.javascriptEnabled")}
<html:hidden styleId="javaScriptIndicator" property="actionHelper.protocolSubmitAction.javascriptEnabled" value="DISABLED"></html:hidden>
</noscript>

${kfunc:registerEditableProperty(KualiForm, "actionHelper.protocolSubmitAction.numberOfReviewers")}
<html:hidden styleId="numberOfReviewers" property="actionHelper.protocolSubmitAction.numberOfReviewers" value="${fn:length(KualiForm.actionHelper.protocolSubmitAction.reviewers)}"></html:hidden>

<jsp:useBean id="paramMap" class="java.util.HashMap"/>

<kra:permission value="${KualiForm.actionHelper.canSubmitProtocol}">

<kul:innerTab tabTitle="Submit for Review" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolSubmitAction*">
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
                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.submissionTypeCode" attributeEntry="${attributes.submissionTypeCode}" />
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
                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.protocolReviewTypeCode" 
                                                  attributeEntry="${attributes.protocolReviewTypeCode}" 
                                                  onchange="updateCheckList('actionHelper.protocolSubmitAction.protocolReviewTypeCode')" />
                        </nobr>
                    </td>
                </tr>
                
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${attributes.submissionQualifierTypeCode}" />
                        </div>
                    </th>
                    <td colspan="3" width="100%">
                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.submissionQualifierTypeCode" attributeEntry="${attributes.submissionQualifierTypeCode}" />
                    </td>
                </tr>
                <c:if test="${KualiForm.actionHelper.showCommittee}">
	                <c:set var="hasCommitteeError" value="false"/>
	                <c:set var="fieldName" value="actionHelper.protocolSubmitAction.committeeId" />
	                <c:forEach items="${ErrorPropertyList}" var="key">
	                    <c:if test="${key eq fieldName }">
	                        <c:set var="hasCommitteeError" value="true"/>
	                    </c:if>
	                </c:forEach>                
	                <tr>
	                	<th width="15%"> 
	                        <div align="right">
	                            <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                        </div>
	                    </th>
	                    <c:set target="${paramMap}" property="protocolLeadUnit" value="${KualiForm.document.protocolList[0].leadUnitNumber}" />
	                    <c:set target="${paramMap}" property="docRouteStatus" value="${KualiForm.document.documentHeader.workflowDocument.routeHeader.docRouteStatus}" />
	                    <c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />
	                    <c:choose>
	                        <c:when test="${KualiForm.actionHelper.showCommittee}">
	                            <td>
				                    <html:select property="actionHelper.protocolSubmitAction.committeeId" onchange="loadScheduleDates('actionHelper.protocolSubmitAction.committeeId', '${docNumber}', 'actionHelper.protocolSubmitAction.scheduleId');" >                               
				                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.committee.lookup.keyvalue.CommitteeIdByUnitValuesFinder', paramMap)}" var="option">   
				                            <c:choose>                      
				                                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.committeeId == option.key}">
				                                    <option value="${option.key}" selected="selected">${option.label}</option>
				                                </c:when>
				                                <c:otherwise>                               
				                                    <c:out value="${option.label}"/>
				                                    <option value="${option.key}">${option.label}</option>
				                                </c:otherwise>
				                            </c:choose>                                                
				                        </c:forEach>
				                    </html:select>	                            
	                    	    </td>
	                        </c:when>
	                    	<c:otherwise>
	                    		 <td colspan="3">
                                    <html:select property="actionHelper.protocolSubmitAction.committeeId" >                               
                                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.committee.lookup.keyvalue.CommitteeIdByUnitValuesFinder', paramMap)}" var="option">
                                            <c:choose>                      
                                                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.committeeId == option.key}">
                                                    <option value="${option.key}" selected="selected">${option.label}</option>
                                                </c:when>
                                                <c:otherwise>                               
                                                    <c:out value="${option.label}"/>
                                                    <option value="${option.key}">${option.label}</option>
                                                </c:otherwise>
                                            </c:choose>                                                
                                        </c:forEach>
                                    </html:select>
			                     </td>
	                    	</c:otherwise>
                        </c:choose>	                    	
	                    <c:if test="${hasCommitteeError}">
	                        <kul:fieldShowErrorIcon />
	                    </c:if>	                    	
    
		                    <th width="20%"> 
		                        <div align="right">
		                              <kul:htmlAttributeLabel attributeEntry="${attributes.scheduleId}" />
		                        </div>
		                    </th>
		                    <td>
		                        <nobr>
		                        <c:choose>		                     
		                            <c:when test="${KualiForm.actionHelper.showCommittee}">
		                            	<c:choose>
		                                	<c:when test="${KualiForm.actionHelper.canAssignReviewersCmtSel}">
				                            	<kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.scheduleId" 
				                                	                      attributeEntry="${attributes.scheduleId}"
				                                    	                  onchange="displayReviewers(${KualiForm.document.protocol.protocolId})" />
				                        	</c:when>
				                        	<c:otherwise>
				                            	<kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.scheduleId" 
				                                	                      attributeEntry="${attributes.scheduleId}" />
    				                    	</c:otherwise>
						                </c:choose>
				                    </c:when>
				                    <c:otherwise>
				                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.scheduleId" 
				                                                  attributeEntry="${attributes.scheduleId}" />
				                    </c:otherwise>
				                </c:choose>
		                        </nobr>
		                    </td>
	                    </tr>
                
                        <c:choose>
		                    <c:when test="${empty KualiForm.actionHelper.protocolSubmitAction.scheduleId}">
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
						                    	<c:forEach var="reviewer" items="${KualiForm.actionHelper.protocolSubmitAction.leftReviewers}" varStatus="status">
						                    	    <tr>
						                    	        <td style="border: 0 none">
		                                                       ${reviewer.fullName}
		                                                </td>
						                       	        <td style="border: 0 none">
						                       	            <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.reviewer[${status.index}].reviewerTypeCode"
		                                                                                 attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>
										                </td>
										            </tr>
						                       	</c:forEach>
						                    </table>
				                   		</td>
				                   		<td style="border: 0 none">
						                    <table id="reviewersTableRight" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
						                        <c:set var="numLeftReviewers" value="${fn:length(KualiForm.actionHelper.protocolSubmitAction.leftReviewers)}" />
						                        <c:forEach var="reviewer" items="${KualiForm.actionHelper.protocolSubmitAction.rightReviewers}" varStatus="status">
						                            <tr>
						                                <td style="border: 0 none">
		                                                    ${reviewer.fullName}
		                                                </td>
						                             	<td style="border: 0 none">
						                        	        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.reviewer[${status.index + numLeftReviewers}].reviewerTypeCode"
		                                                                              attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>
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
	              
                </c:if>
                   
                <c:choose>
	                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.protocolReviewTypeCode == '2'}">
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
			                	<c:forEach items="${KualiForm.actionHelper.protocolSubmitAction.expeditedReviewCheckList}" var="item" varStatus="status">
			                		<tr>
                                        <td style="border-left: 0 none; border-right: 1 none; align: center; vertical-align:center">
	                                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.expeditedReviewCheckList[${status.index}].checked"
	                                                                  attributeEntry="${expeditedAttributes.checked}" />
	                                       
                                        </td>
                                        <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                            <kra:truncateComment textAreaFieldName="actionHelper.protocolSubmitAction.expeditedReviewCheckList[${status.index}].description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${item.description}" displaySize="250"/>
                                        </td>
                                    </tr>
								</c:forEach>
							</tbody>
						</table>
                	</td>
                </tr>
                
                 <c:choose>
	                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.protocolReviewTypeCode == '3'}">
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
			                	<c:forEach items="${KualiForm.actionHelper.protocolSubmitAction.exemptStudiesCheckList}" var="item" varStatus="status">
			                		<tr>
                                        <td style="border-left: 0 none; border-right: 1 none; align: center; vertical-align:center">
	                                        <kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.exemptStudiesCheckList[${status.index}].checked"
	                                                                  attributeEntry="${exemptAttributes.checked}" />
	                                       
                                        </td>
                                        <td style="border-left: 1 none; border-right: 0 none; padding: 5px ">
                                            <kra:truncateComment textAreaFieldName="actionHelper.protocolSubmitAction.exemptStudiesCheckList[${status.index}].description" action="protocolProtocolActions" textAreaLabel="CheckList Item" textValue="${item.description}" displaySize="250"/>
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
						    <noscript>
	                        <html:image property="methodToCall.refreshPage.anchor${tabKey}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton"/>
							</noscript>
							<html:image property="methodToCall.submitForReview.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
    <script>
        updateCheckList('actionHelper.protocolSubmitAction.protocolReviewTypeCode');
    </script>
</kul:innerTab>

</kra:permission>
