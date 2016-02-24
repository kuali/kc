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

<c:set var="attributes" value="${DataDictionary.IacucProtocolSubmitAction.attributes}" />
<c:set var="reviewerAttributes" value="${DataDictionary.IacucProtocolReviewerBean.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<kra:permission value="${KualiForm.actionHelper.canSubmitProtocol}">

${kfunc:registerEditableProperty(KualiForm, "actionHelper.iacucProtocolSubmitAction.numberOfReviewers")}
<html:hidden styleId="numberOfReviewers" property="actionHelper.iacucProtocolSubmitAction.numberOfReviewers" value="${fn:length(KualiForm.actionHelper.iacucProtocolSubmitAction.reviewers)}"></html:hidden>
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.submissionTypeCode" attributeEntry="${attributes.submissionTypeCode}" />
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.protocolReviewTypeCode" 
                                                  attributeEntry="${attributes.protocolReviewTypeCode}" />
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.submissionQualifierTypeCode" attributeEntry="${attributes.submissionQualifierTypeCode}" />
                    </td>
                </tr>
                <c:if test="${KualiForm.actionHelper.showCommittee}">
	                <c:set var="hasCommitteeError" value="false"/>
	                <c:set var="fieldName" value="actionHelper.iacucProtocolSubmitAction.committeeId" />
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
				                    <html:select property="actionHelper.iacucProtocolSubmitAction.committeeId" onchange="loadScheduleDates('actionHelper.iacucProtocolSubmitAction.committeeId', '${docNumber}', 'actionHelper.iacucProtocolSubmitAction.scheduleId');" >                               
				                        <c:forEach items="${KualiForm.actionHelper.submitActionCommitteeIdByUnitKeyValues}" var="option">   
				                            <c:choose>                      
				                                <c:when test="${KualiForm.actionHelper.iacucProtocolSubmitAction.committeeId == option.key}">
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
                                    <html:select property="actionHelper.iacucProtocolSubmitAction.committeeId" >                               
                                        <c:forEach items="${KualiForm.actionHelper.submitActionCommitteeIdByUnitKeyValues}" var="option">
                                            <c:choose>                      
                                                <c:when test="${KualiForm.actionHelper.iacucProtocolSubmitAction.committeeId == option.key}">
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
		                            	<c:choose>
		                                	<c:when test="${KualiForm.actionHelper.canAssignReviewersCmtSel}">
				                            	<kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.scheduleId" 
				                                	                      attributeEntry="${attributes.scheduleId}"
				                                    	                  onchange="protocolDisplayReviewers('getProtocolReviewers', 'actionHelper.iacucProtocolSubmitAction.committeeId', 
				                                    	                  'actionHelper.iacucProtocolSubmitAction.scheduleId', ${KualiForm.document.protocol.protocolId}, 
				                                    	                  'iacucProtocolSubmitAction'); 
				                                    	                  setDefaultReviewerTypeCode('getProtocolReviewers', 'actionHelper.iacucProtocolSubmitAction.committeeId', 
				                                    	                  'actionHelper.iacucProtocolSubmitAction.scheduleId', ${KualiForm.document.protocol.protocolId}, 
				                                    	                  'iacucProtocolSubmitAction')" />
				                        	</c:when>
				                        	<c:otherwise>
				                            	<kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.scheduleId" 
				                                	                      attributeEntry="${attributes.scheduleId}" />
    				                    	</c:otherwise>
						                </c:choose>
				                    </c:when>
				                    <c:otherwise>
				                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.scheduleId" 
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
						                    	<c:forEach var="reviewer" items="${KualiForm.actionHelper.iacucProtocolSubmitAction.leftReviewers}" varStatus="status">
						                    	    <tr>
						                    	        <td style="border: 0 none">
		                                                       ${reviewer.fullName}
		                                                </td>
						                       	        <td style="border: 0 none">
						                       	            <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index}].reviewerTypeCode"
		                                                                                 attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>

														
                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index}].personId" 
                                                        			value="${reviewer.personId}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index}].fullName" 
                                                        			value="${reviewer.fullName}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index}].nonEmployeeFlag" 
                                                        			value="${reviewer.nonEmployeeFlag}" />						                        
										                </td>
										            </tr>
						                       	</c:forEach>
						                    </table>
				                   		</td>
				                   		<td style="border: 0 none">
						                    <table id="reviewersTableRight" style="border: 0 none" cellpadding="0" cellspacing="0" summary="">
						                        <c:set var="numLeftReviewers" value="${fn:length(KualiForm.actionHelper.iacucProtocolSubmitAction.leftReviewers)}" />
						                        <c:forEach var="reviewer" items="${KualiForm.actionHelper.iacucProtocolSubmitAction.rightReviewers}" varStatus="status">
						                            <tr>
						                                <td style="border: 0 none">
		                                                    ${reviewer.fullName}
		                                                </td>
						                             	<td style="border: 0 none">
						                        	        <kul:htmlControlAttribute property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index + numLeftReviewers}].reviewerTypeCode"
		                                                                              attributeEntry="${reviewerAttributes.reviewerTypeCode}"/>

                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index + numLeftReviewers}].personId" 
                                                        			value="${reviewer.personId}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index + numLeftReviewers}].fullName" 
                                                        			value="${reviewer.fullName}" />
                                                        			
                                                        <html:hidden property="actionHelper.iacucProtocolSubmitAction.reviewer[${status.index + numLeftReviewers}].nonEmployeeFlag" 
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
	              
                </c:if>

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

</kul:innerTab>
</kra:permission>
