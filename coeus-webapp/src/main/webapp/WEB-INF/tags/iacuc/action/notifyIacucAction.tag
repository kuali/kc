<%--
 Copyright 2005-2013 The Kuali Foundation

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

<c:set var="attributes" value="${DataDictionary.IacucProtocolNotifyIacucBean.attributes}" />
<c:set var="submissionDocAttributes" value="${DataDictionary.ProtocolSubmissionDoc.attributes}" />
<c:set var="action" value="protocolProtocolActions" />

<c:set var="isOpen" value="false" />
<c:forEach items="${param}" var="par">
    <c:if test="${fn:startsWith(par.key, 'lookupActionNotifyIacucProtocol') and fn:startsWith(par.value, 'true')}">
        <c:set var="isOpen" value="true" />
    </c:if>
</c:forEach>
 <c:set var="parentTabValue" value="Notify IACUC" scope="request"/>

             
<kra:permission value="${KualiForm.actionHelper.canNotifyIacuc}">

<kul:innerTab tabTitle="Notify IACUC" parentTab="" defaultOpen="${isOpen}" tabErrorKey="actionHelper.iacucProtocolNotifyIacucBean*">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                    <tr>
                    <td class="subhead" colspan="4">Details</td>
                    </tr>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.submissionQualifierTypeCode}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.submissionQualifierTypeCode" attributeEntry="${attributes.submissionQualifierTypeCode}" />
                    </td>
	          		<th>
	          		    <div align="right">
	          		        <kul:htmlAttributeLabel attributeEntry="${attributes.reviewTypeCode}"/>
	          		    </div>
	          		</th>
	                <td align="left">
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.reviewTypeCode" attributeEntry="${attributes.reviewTypeCode}" />
					</td>
                </tr>
                <tr>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.committeeId}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.committeeId" attributeEntry="${attributes.committeeId}" readOnly="true"/>
                        </nobr>
                    </td>
                    <th width="15%"> 
                        <div align="right">
                            <nobr>
                            <kul:htmlAttributeLabel attributeEntry="${attributes.comment}" />
                            </nobr>
                        </div>
                    </th>
                    <td>
                        <nobr>
                        <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.comment" attributeEntry="${attributes.comment}" />
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
	              		        <c:set var="property" value="actionHelper.iacucProtocolNotifyIacucBean.newActionAttachment.file" />
	              		
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
                                <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.newActionAttachment.description" attributeEntry="${submissionDocAttributes.description}" />
						    </div>
					    </td>
					    <td align="center" valign="middle" class="infoline">
						    <div align="center">
							    <html:image property="methodToCall.addNotifyIacucAttachment.anchor${tabKey}"
							    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
						    </div>
					    </td>
				    </tr>
                    
 			        <c:forEach var="actionAttachment" items="${KualiForm.actionHelper.iacucProtocolNotifyIacucBean.actionAttachments}" varStatus="itrStatus">
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
                                   <kul:htmlControlAttribute property="actionHelper.iacucProtocolNotifyIacucBean.actionAttachments[${itrStatus.index}].description" attributeEntry="${submissionDocAttributes.description}" />
	           			        </div>
					        </td>
					        <td align="center" valign="middle">
						        <div align="center">
							        <html:image property="methodToCall.viewNotifyIacucAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
								        alt="View Notify Iacuc Attachment" onclick="excludeSubmitRestriction = true;"/>
								    <html:image property="methodToCall.deleteNotifyIacucAttachment.line${itrStatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									alt="Delete Notify Iacuc Attachment"/>
						        </div>
					        </td>
	         	        </tr>
			        </c:forEach>
                    
                </table>
                
           	<c:forEach var="answerHeader" items="${KualiForm.actionHelper.iacucProtocolNotifyIacucBean.questionnaireHelper.answerHeaders}" varStatus="status">
				<kra-questionnaire:questionnaireAnswersInnerTab 
				parentTab="Notify IACUC" 
				bean="${KualiForm.actionHelper.iacucProtocolNotifyIacucBean.questionnaireHelper}" 
				answerHeaderIndex="${status.index}" 
				property="actionHelper.iacucProtocolNotifyIacucBean.questionnaireHelper"/>
			</c:forEach>
                
                
            <table cellpadding="0" cellspacing="0" summary="">
                
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<html:image property="methodToCall.notifyIacucProtocol.anchor${tabKey}" onclick="closeQuestionnairePop()"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
    </div>
    
</kul:innerTab>

</kra:permission>
