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


<kra:permission value="${KualiForm.actionHelper.canSubmitProtocol}">

<c:choose>
	<c:when test="${KualiForm.actionHelper.canAssignReviewersCmtSel}">
		<c:set var="displayReviewersInvocation" value=" displayReviewers(${KualiForm.document.protocol.protocolId});" />
	</c:when>
	<c:otherwise>
		<c:set var="displayReviewersInvocation" value=" ;" />
	</c:otherwise>
</c:choose>

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
                                                  onchange="protocolReviewTypeChanged()" />
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
	                   
	                    <c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />
	                    <c:choose>
	                        <c:when test="${KualiForm.actionHelper.showCommittee}">
	                            <td>	                            		
				                    <html:select property="actionHelper.protocolSubmitAction.committeeId" onchange="loadScheduleDates('actionHelper.protocolSubmitAction.committeeId', '${docNumber}', 'actionHelper.protocolSubmitAction.scheduleId'); ${displayReviewersInvocation}" >                               
				                        <c:forEach items="${KualiForm.actionHelper.submitActionCommitteeIdByUnitKeyValues}" var="option">   
				                            <c:choose>                      
				                                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.committeeId == option.key}">
				                                    <option value="${option.key}" selected="selected">${fn:escapeXml(option.value)}</option>
				                                </c:when>
				                                <c:otherwise>                               
				                                    <c:out value="${option.value}"/>
				                                    <option value="${option.key}">${fn:escapeXml(option.value)}</option>
				                                </c:otherwise>
				                            </c:choose>                                                
				                        </c:forEach>
				                    </html:select>	                            
	                    	    </td>
	                        </c:when>
	                    	<c:otherwise>
	                    		 <td colspan="3">
                                    <html:select property="actionHelper.protocolSubmitAction.committeeId" >                               
                                        <c:forEach items="${KualiForm.actionHelper.submitActionCommitteeIdByUnitKeyValues}" var="option">
                                            <c:choose>                      
                                                <c:when test="${KualiForm.actionHelper.protocolSubmitAction.committeeId == option.key}">
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
			                            	<kul:htmlControlAttribute property="actionHelper.protocolSubmitAction.scheduleId" 
					                         	                      attributeEntry="${attributes.scheduleId}"
					                               	                  onchange="${displayReviewersInvocation}" />
					                        	
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
		                    <c:when test="${KualiForm.actionHelper.reviewersDisplayToBeSuppressed}">
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
        
	    function protocolReviewTypeChanged() {
	    	// we will update the review listing only if a committee was chosen but a schedule was not
	    	if( ($j(jq_escape("actionHelper.protocolSubmitAction.scheduleId")).val() == "") && 
		    	($j(jq_escape("actionHelper.protocolSubmitAction.committeeId")).val() != "")) {
	    		
	    		${displayReviewersInvocation}
	    		
	    	}
	    	updateCheckList('actionHelper.protocolSubmitAction.protocolReviewTypeCode');
		}
    	
        $j(document).ready(function() { 
        	updateCheckList('actionHelper.protocolSubmitAction.protocolReviewTypeCode');
        });
        
    </script>
</kul:innerTab>

</kra:permission>
