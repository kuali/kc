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

<c:set var="attributes" value="${DataDictionary.IacucProtocolModifySubmissionBean.attributes}" />
<c:set var="reviewerAttributes" value="${DataDictionary.IacucProtocolReviewerBean.attributes}" />
<c:set var="action" value="modifySubmissionAction" />
<c:set var="cmtAttributes" value="${DataDictionary.IacucProtocolAssignCmtBean.attributes}" />
<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<script language="javascript" src="dwr/interface/ProtocolActionAjaxService.js"></script>
<script language="javascript" src="dwr/interface/IacucProtocolActionAjaxService.js"></script>
<c:set var="docNumber" value="${KualiForm.document.protocol.protocolNumber}" />


<kra:permission value="${KualiForm.actionHelper.canModifyProtocolSubmission}">
${kfunc:registerEditableProperty(KualiForm, "actionHelper.iacucProtocolModifySubmissionBean.numberOfReviewers")}
<html:hidden styleId="numberOfReviewers" property="actionHelper.iacucProtocolModifySubmissionBean.numberOfReviewers" value="${fn:length(KualiForm.actionHelper.iacucProtocolModifySubmissionBean.reviewers)}"></html:hidden>

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
                        <c:set target="${paramMap}" property="committeeId" value="${KualiForm.actionHelper.iacucProtocolModifySubmissionBean.committeeId}" />
                        <c:set target="${paramMap}" property="docRouteStatus" value="${KualiForm.document.documentHeader.workflowDocument.status.code}" />	                
                        <c:set target="${paramMap}" property="protocolLeadUnit" value="${KualiForm.document.protocol.leadUnitNumber}" />	                
                       	<html:select styleId="actionHelper.iacucProtocolModifySubmissionBean.committeeId" property="actionHelper.iacucProtocolModifySubmissionBean.committeeId" 
                       	onchange="loadScheduleDates('actionHelper.iacucProtocolModifySubmissionBean.committeeId', '${docNumber}', 'actionHelper.iacucProtocolModifySubmissionBean.scheduleId');" >                               
                            <c:forEach items="${krafn:getOptionList('org.kuali.kra.committee.lookup.keyvalue.IacucCommitteeIdByUnitValuesFinder', paramMap)}" var="option" >
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
				                                    	  onchange="protocolDisplayReviewers('getProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, '${docNumber}'); setDefaultReviewerTypeCode('getProtocolReviewers', 'actionHelper.iacucProtocolModifySubmissionBean.committeeId', 'actionHelper.iacucProtocolModifySubmissionBean.scheduleId', ${KualiForm.document.protocol.protocolId}, '${docNumber}')" />
		                    </nobr>
		               </td>
		               <script language="javascript">
		               		function setDefaultReviewerTypeCode(methodToCall, committeeId, scheduleId, protocolId) {		               			
		               			var cmtId = $j(jq_escape(committeeId)).attr("value");
		               			var schedId = $j(jq_escape(scheduleId)).attr("value");
		               			var queryString = "&committeeId="+cmtId+"&scheduleId=" + schedId+"&protocolId="+protocolId;
		               	    	callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
		               	    			function(jQueyrData) {
		               	    				reviewersReturned = $j(jQueyrData).find('#ret_value').html();
		               						getProtocolReviewerTypes(reviewersReturned);
		               						
		               						var reviewersArr = reviewersReturned.split(";");
		               						
		               						var defaultReviewTyper;
		               						//just to note, this will probably be higher than the actual number of reviewers, but is a good number to loop through.
		    		               			var numberOfRevierwers = reviewersArr.length;
		    		               			var dwrReply = {
		    		               					callback:function(data) {
		    		               						if ( data != null ) {	
		    		               							defaultReviewTyper = data;
		    		               						} else {
		    		               							defaultReviewTyper = '';
		    		               						}
		    		               						
		    		               						for (i=0; i<numberOfRevierwers; i++) {
		    		    							  		var selectField = document.getElementsByName('actionHelper.iacucProtocolModifySubmissionBean.reviewer[' + i + '].reviewerTypeCode')[0];
		    		    							  		if (selectField != null) {
			    		    							  		for (j=0; j<selectField.length; j++) {
			    		    							  			if (selectField.options[j].value == defaultReviewTyper) {
			    		    							  				selectField.options[j].setAttribute("selected", "selected");
			    		    							  				selectField.selectedIndex = j;
			    		    							  			}
			    		    							  		}
		    		    							  		}
		    		    							  		
		    		    							  	}
		    		               					},
		    		               					errorHandler:function( errorMessage ) {	
		    		               						window.status = errorMessage;
		    		               						window.alert('C data: unknown, there is an error: ' + errorMessage);
		    		               						defaultReviewTyper = '';
		    		               					}
		    		               			};
		    		               			IacucProtocolActionAjaxService.getDefaultCommitteeReviewTypeCode(dwrReply);
		    		               			return defaultReviewTyper;
		               	    				
		               	    			},
		               	    			function(error) {
		               	    				alert("error is" + error);
		               	    			}
		               	    	);
		               		}
		               </script>
                </tr>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.submissionTypeCode}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.submissionTypeCode" attributeEntry="${attributes.submissionTypeCode}" />
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
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolModifySubmissionBean.protocolReviewTypeCode" 
                                                  attributeEntry="${attributes.protocolReviewTypeCode}" />
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
		                    <c:when test="${empty KualiForm.actionHelper.iacucProtocolModifySubmissionBean.scheduleId}">
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
