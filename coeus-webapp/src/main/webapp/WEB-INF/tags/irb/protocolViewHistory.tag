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

<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
${kfunc:registerEditableProperty(KualiForm, "actionHelper.selectedHistoryItem")}
<c:set var="submissionDocAttributes" value="${DataDictionary.ProtocolSubmissionDoc.attributes}" />

<c:set var="canViewIRBCorrespondence" value="${KualiForm.actionHelper.allowedToViewProtocolCorrespondence}" />
<c:set var="canUpdateIRBCorrespondence" value="${KualiForm.actionHelper.allowedToUpdateProtocolCorrespondence}" />
<c:set var="canRegenerateIRBCorrespondence" value="${KualiForm.actionHelper.allowedToRegenerateProtocolCorrespondence}" />

<kul:innerTab tabTitle="History" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.filteredHistory*">

    <div class="innerTab-container" align="left">
    <h3>
   			<span class="subhead-left">History</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolHistoryHelp" altText="Help"/>
			</span>
       </h3>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            <tr>
                <th class="infoline"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;View Action Date Range:</nobr></th>
                <td class="infoline"> 
                    <nobr>&nbsp;Beginning On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.filteredHistoryStartDate" 
                                                 attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
                	   &nbsp;
                	</nobr>
                </td>
                <td class="infoline"> 
                    <nobr>&nbsp;Ending On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.filteredHistoryEndDate" 
                                                 attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
                		&nbsp;
                	</nobr>
                </td>
                <td class="infoline" style="width:90%;text-align:left;">&nbsp;
                    <html:image property="methodToCall.filterHistory.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' 
                                styleClass="tinybutton"/>
                    <html:image property="methodToCall.resetHistory.anchor${tabKey}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clear1.gif' 
                                styleClass="tinybutton"/>                      
                </td>
            </tr>
            </tbody>
        </table>
        
        <table id="historyTable" class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                <tr>
	                <td class="infoline">&nbsp;</td>
	                <th>Description</th>
	                <th>Date</th>
	                <th><nobr>Action Date</nobr></th>
	                <th style="width:30%;">Comments</th>
	                <th>Updated By</th>
	                <th>Update Time</th>
 	              <%--  <th>Questionnaire</th> --%>
                </tr>
                <c:forEach items="${KualiForm.document.protocol.protocolActions}" var="protocolAction" varStatus="status">
                    <c:if test="${protocolAction.isInFilterView}">
	            		<tr>
	            		    <td class="infoline" style="text-align:center;">
	            		        <c:choose>
	            		            <c:when test="${protocolAction.submissionNumber == null}">
	            		                &nbsp;
	            		            </c:when>
	            		            <c:otherwise>
	            		                <input name="actionHelper.selectedHistoryItem" type="radio" class="nobord" value="${protocolAction.protocolActionId}">
	            		            </c:otherwise>
	            		        </c:choose>
	            		    </td>
	            		    <td class="infoline">
	            		        <nobr><b>${protocolAction.protocolActionType.description}</b></nobr>
	            		    </th>
	            		    <td class="infoline">
	            		        <nobr>${protocolAction.actualActionDateString}</nobr>
	            		    </td>
	            		    <td class="infoline">
	            		        <nobr>${protocolAction.actionDateString}</nobr>
	            		    </td>
	            		    <td class="infoline">
	            		        <c:choose>
	            		            <c:when test="${fn:length(protocolAction.comments) > 0}">
	                                    <kra:truncateComment textAreaFieldName="document.protocol.protocolActions[${status.index}].comments" 
	                                                         action="protocolProtocolActions" 
	                                                         textAreaLabel="Action Comment" 
	                                                         textValue="${protocolAction.comments}" 
	                                                         displaySize="100"/>
	                                </c:when>
	                                <c:otherwise>
	                                      &nbsp;
	                                </c:otherwise>
	                            </c:choose>
	                        </td>
	            		    <td class="infoline">
	            		        <nobr>${protocolAction.createUser}</nobr>
	            		    </td>
	            		    <td class="infoline">
                                <fmt:formatDate value="${protocolAction.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" />
	            		    </td>
	            		</tr>
	            		
	            		<c:if test="${fn:length(protocolAction.protocolCorrespondences) > 0}">
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            		        <td colspan="6">
	            		        	<kul:innerTab tabTitle="Correspondences" tabItemCount="${fn:length(protocolAction.protocolCorrespondences)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
		                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
		                                        <tbody>
		                                            <tr>
                                                       <th style="text-align:center">Description</th>
                                                       <th style="text-align:center">Date Created</th>
                                                       <th style="text-align:center">Final</th>
		                                               <th style="text-align:center">Actions</th>
		                                            </tr>
		           		                            <c:forEach items="${protocolAction.protocolCorrespondences}" var="correspondence" varStatus="attachmentStatus">
		           		    	                        <tr>
		           		    	                            <td>${correspondence.protocolCorrespondenceType.description}</td>
                    										<td align="left" valign="middle">
                        										<div align="left"><fmt:formatDate value="${correspondence.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> </div>
                    										</td>
                    										<td align="left" valign="middle">
                        										<div align="left"> 
                            										<c:choose>
                                  										<c:when test="${correspondence.finalFlag}">
                                     										Yes (<fmt:formatDate value="${correspondence.finalFlagTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> )
                                  										</c:when>
                                  										<c:otherwise>
                                     										No
                                  										</c:otherwise>
                                     
                            										</c:choose>
                        										</div>
                    										</td>
		           		 					                <td align="center" valign="middle">
                                                                <div align="center">
                                                                  <c:if test="${canViewIRBCorrespondence or correspondence.finalFlag}">
                                                                    <html:image property="methodToCall.viewActionCorrespondence.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
										                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										                                        alt="View Correspondence" onclick="excludeSubmitRestriction = true;"
										                                        styleClass="tinybutton"/>
										                          </c:if>              
                                                                  <c:if test="${canRegenerateIRBCorrespondence}">
                                                                      <html:image property="methodToCall.regenerateCorrespondence.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
                                                                          src='${ConfigProperties.kra.externalizable.images.url}tinybutton-regenerate.gif' styleClass="tinybutton"
                                                                          alt="Regenerate Correspondence" title="Regenerate Correspondence" onclick="excludeSubmitRestriction = true;"/>
                                                                          
                                                                  </c:if>
																  <c:if test="${canUpdateIRBCorrespondence}">                                                                          
                                                                      <html:image property="methodToCall.updateCorrespondence.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
                                                                          src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' styleClass="tinybutton"
                                                                          alt="Update Correspondence" title="Update Correspondence" onclick="excludeSubmitRestriction = true;"/>
                                                                  </c:if>
								                               </div>
							                                 </td>
		           		    	                        </tr>
		           		                            </c:forEach>
		            		                    </tbody>
		            		                </table>
	            		                </div>
	            		        	</kul:innerTab>
	            		        </td>            		        
	            			</tr>
	            		</c:if>
	
		            	<c:if test="${fn:length(protocolAction.filteredProtocolNotifications) > 0}">
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            		        <td colspan="6">
	            		        	<kul:innerTab tabTitle="Notifications" tabItemCount="${fn:length(protocolAction.filteredProtocolNotifications)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
		                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
		                                        <tbody>
		                                            <tr>
                                                       <th style="text-align:center">Date Created</th>
		                                               <th style="text-align:center">Recipients</th>
		                                               <th style="text-align:center">Subject</th>
                                                       <th style="text-align:center">Message</th>
		                                            </tr>
		           		                            <c:forEach items="${protocolAction.filteredProtocolNotifications}" var="notification" varStatus="notificationStatus">
		           		    	                        <tr>
															<td>
																<div align="center">
																    <fmt:formatDate value="${notification.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> 
															    </div>
															</td>    
                    										<td align="center" valign="middle">
																<div align="center">
																    ${notification.recipients}
                        										</div>
                    										</td>
										                    <td align="left" valign="middle">
                        										<div align="left"> 
                            										${notification.subject}
                        										</div>
                    										</td>
		           		 					                <td align="center" valign="middle">
                                                                <div align="left">
                                                                	${notification.message}
								                            	</div>
							                                </td>
		           		    	                        </tr>
		           		                            </c:forEach>
		            		                    </tbody>
		            		                </table>
	            		                </div>
	            		        	</kul:innerTab>
	            		        </td>            		        
	            			</tr>
	            		</c:if>

	            		<c:if test="${fn:length(protocolAction.protocolSubmissionDocs) > 0}">
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            		        <td colspan="4">
	            		        	<kul:innerTab tabTitle="Actions Attachments" tabItemCount="${fn:length(protocolAction.protocolSubmissionDocs)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
		                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
		                                        <tbody>
		                                            <tr>
		                                               <th style="text-align:center">File Name</th>
		                                               <th style="text-align:center">Description</th>
		                                               <th style="text-align:center">Actions</th>
		                                            </tr>
		           		                            <c:forEach items="${protocolAction.protocolSubmissionDocs}" var="submissionDoc" varStatus="attachmentStatus">
		           		    	                        <tr>
		           		    	                            <td><div align="left">${submissionDoc.fileName}</div></td>
		           		    	                            <td><div align="left">
	                                                            <kul:htmlControlAttribute property="document.protocol.protocolActions[${status.index}].protocolSubmissionDocs[${attachmentStatus.index}].description" attributeEntry="${submissionDocAttributes.description}" readOnly="true"/>
		           		    	                            </div></td>
		           		 					                <td align="center" valign="middle">
	                                                            <div align="center">
									                            <html:image property="methodToCall.viewSubmissionDoc.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
										                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
										                                    alt="View Action Attachment" onclick="excludeSubmitRestriction = true;"
										                                    styleClass="tinybutton"/>
								                               </div>
							                               </td>
		           		    	                        </tr>
		           		                            </c:forEach>
		            		                    </tbody>
		            		                </table>
	            		                
	            		                </div>
	            		        	</kul:innerTab>
	            		        </td>            		        
	            			</tr>
	            		</c:if>

	            		<c:if test="${fn:length(protocolAction.questionnaireHelper.answerHeaders) > 0}">
	            		    <c:set var="printOption" value="${protocolAction.questionnairePrintOption}"/>
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            			    <td class="infoline" colspan="6">
	            		        	<kul:innerTab tabTitle="Questionnaires" tabItemCount="${fn:length(protocolAction.questionnaireHelper.answerHeaders)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
	            		        			<c:forEach var="answerHeader" items="${protocolAction.questionnaireHelper.answerHeaders}" varStatus="answerStatus">
	            		        				<kra-questionnaire:questionnaireAnswersInnerTab parentTab="Questionnaires" 
	            		        					bean="${protocolAction.questionnaireHelper}" 
	            		        					answerHeaderIndex="${answerStatus.index}" 
	            		        					property="document.protocol.protocolActions[${status.index}].questionnaireHelper"
	            		        					overrideDivClass="inner-subhead"
	            		        					readOnly="true"/>
	            		        			</c:forEach>
	            		        		</div>
	            		        	</kul:innerTab>	                                    
								</td>
	            			</tr>
	            		</c:if>
	

            		</c:if>
            	</c:forEach>
            	
            	<tr>
            	    <td class="infoline" colspan="7">
            	        <html:image property="methodToCall.loadProtocolSummary.line${ctr}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-load.gif' 
                                    styleClass="tinybutton" style="vertical-align:bottom"/>  
            	        Load selected node into Summary View, above
            	    </td>
            	</tr>
            </tbody>
        </table>
    </div>
    			
</kul:innerTab>
