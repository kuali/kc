<%--
 Copyright 2005-2014 The Kuali Foundation

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
<%@ attribute name="permission" required="true" %>
<%@ attribute name="beanName" required="true" %>
<%@ attribute name="taskName" required="true" %>
<%@ attribute name="actionTypeCode" required="true" %>
<%@ attribute name="tabTitle" required="true" %>

<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.request.ProtocolRequestBean" %>
<c:set var="attributes" value="${DataDictionary.ProtocolRequestBean.attributes}" />
<c:set var="submissionDocAttributes" value="${DataDictionary.ProtocolSubmissionDoc.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
                                    
<kra:permission value="${permission}">

<kul:innerTab tabTitle="${tabTitle}" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.${beanName}*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                <tr>
                    <td class="subhead" colspan="4">Details</td>
                </tr>
                <tr>
                    <th width="15%"> 
                        <c:if test="${KualiForm.actionHelper.showCommittee}">
	                        <div align="right">
	                            <nobr>
	                            <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
	                            </nobr>
	                        </div>
                        </c:if>
                    </th>
                    <td>
                        <c:if test="${KualiForm.actionHelper.showCommittee}">
	                        <nobr>
	                        <kul:htmlControlAttribute property="actionHelper.${beanName}.committeeId" attributeEntry="${attributes.committeeId}" />
	                        </nobr>
                        </c:if>
                    </td>

                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.reason}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.${beanName}.reason" attributeEntry="${attributes.reason}" />
                        </nobr>
                    </td>
                 </tr>
                 
                </table>
                
                <table cellpadding="0" cellspacing="0" summary="">
                    <tr>
                        <td class="subhead" colspan="4">Attachments</td>
                    </tr>
                    <tr>
         	            <th>
         	    	       &nbsp;
         	            </th>
         		        <th>
         			        <div align="center">
         				        Attachment
         			        </div>
         		        </th>
         		        <th>
         			        <div align="center">
         				        Description
         			        </div>
         		        </th>
         		        <th>
         			        <div align="center">
         				        Actions
         			        </div>
         		        </th>
                    </tr>
                    <tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
					    <td align="left" valign="middle" class="infoline">
	              		    <div align="center">
	              		        <c:set var="property" value="actionHelper.${beanName}.newActionAttachment.file" />
	              		
	              		    <%-- attachment file error handling logic start--%>
	               				<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
	               				<%-- highlighting does not work in firefox but does in ie... --%>
	               			<%-- attachment file error handling logic start--%>
	              		
	              			    <html:file property="${property}"/>
	               			    <c:if test="${hasErrors}">
                    	 		    <kul:fieldShowErrorIcon />
                                </c:if>
	           			    </div>
					    </td>
					    <td align="left" valign="middle" class="infoline">
						    <div align="left">
                                <kul:htmlControlAttribute property="actionHelper.${beanName}.newActionAttachment.description" attributeEntry="${submissionDocAttributes.description}" />
						    </div>
					    </td>
					    <td align="center" valign="middle" class="infoline">
						    <div align="center">
							    <html:image property="methodToCall.addRequestAttachment.taskName${taskName}.anchor${tabKey}"
							    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
						    </div>
					    </td>
				    </tr>
                    
 			        <c:forEach var="actionAttachment" items="${bean.actionAttachments}" varStatus="itrStatus">
				        <tr>
	         		        <td>
	         			        <div align="center">
	                		        ${itrStatus.index + 1}
		            	        </div>
	         		        </td>
	       			        <td align="left" valign="middle">
	           			        <div align="center" id="attachmentFileName${itrStatus.index}">
	              			        ${actionAttachment.file.fileName}
	           			        </div>
					        </td>
	       			        <td align="left" valign="middle">
	           			        <div align="left" >
                                   <kul:htmlControlAttribute property="actionHelper.${beanName}.actionAttachments[${itrStatus.index}].description" attributeEntry="${submissionDocAttributes.description}" />
	           			        </div>
					        </td>
					        <td align="center" valign="middle">
						        <div align="center">
							        <html:image property="methodToCall.viewRequestAttachment.taskName${taskName}.line${itrStatus.index}.anchor${currentTabIndex}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								        alt="View Attachment" onclick="excludeSubmitRestriction = true;"/>
								    <html:image property="methodToCall.deleteRequestAttachment.taskName${taskName}.line${itrStatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									alt="Delete Attachment"/>
						        </div>
					        </td>
	         	        </tr>
			        </c:forEach>
                    
                </table>

			<c:forEach var="answerHeader" items="${bean.questionnaireHelper.answerHeaders}" varStatus="status">
				<kra-questionnaire:questionnaireAnswersInnerTab 
				parentTab="${tabTitle}" 
				bean="${bean.questionnaireHelper}" 
				answerHeaderIndex="${status.index}" 
				property="actionHelper.${beanName}.questionnaireHelper"/>
			</c:forEach>
                                                
            <table cellpadding="0" cellspacing="0" summary="">                 
                 <tr>
					<td align="center" colspan="4">
						<div align="center">
							<html:image property="methodToCall.requestAction.taskName${taskName}.anchor${tabKey}"  onclick="closeQuestionnairePop()"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
