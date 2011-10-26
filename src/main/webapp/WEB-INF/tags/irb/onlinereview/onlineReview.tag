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
<%@ attribute name="renderIndex" required = "true" description="The index into the action helpers array list for this review."%>
<%@ attribute name="documentNumber" required = "true" description="The protocol online review document number to be rendered." %>


<c:set var="onlineReviewAttributes" value="${DataDictionary.ProtocolOnlineReview.attributes}" />
<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" />

<c:set var="actionHelper" value = "${KualiForm.onlineReviewsActionHelper}"/>

<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" />
<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="documentOverviewReadOnly" value = "${KualiForm.editingMode['viewOnly']}"/>

<c:set var = "documentHelperMap" value = "${KualiForm.onlineReviewsActionHelper.documentHelperMap[documentNumber]}"/>
<c:set var = "document" value = "${documentHelperMap['document']}"/>
<c:set var = "reviewerComments" value = "${document.protocolOnlineReview.committeeScheduleMinutes}"/>
<c:set var = "reviewerPerson" value = "${document.protocolOnlineReview.protocolReviewer}"/>
<c:set var = "kualiForm" value = "${documentHelperMap['kualiForm']}"/>

<c:set var="readOnly" value="${kualiForm.editingMode['viewOnly']}" scope="request" />


<%--Keep in mind, KualiForm references the Protocol we are rendering in, kualiForm is a ProtocolOnlineReviewForm for the current protocol online review. --%>


	<div class="tab-container" align=center>
		  <!-- DOC OVERVIEW TABLE -->
		
		
    	<c:set var="numberOfHeaderRows" value="2" />
    	<c:set var="headerFieldCount" value="4" />
  		<c:set var="headerFields" value="${kualiForm.docInfo}" />
  		<c:set var="fieldCounter" value="0" />
  	
  	    <c:if test="${ !empty KualiForm.onlineReviewsActionHelper.protocolOnlineReviewDocuments[renderIndex].protocolOnlineReview.committeeScheduleMinutes or !empty KualiForm.onlineReviewsActionHelper.protocolOnlineReviewDocuments[renderIndex].protocolOnlineReview.reviewAttachments }">
	  	    <c:if test="${!KualiForm.onlineReviewsActionHelper.protocolOnlineReviewDocuments[renderIndex].protocolOnlineReview.reviewerApproved}">
	  	        <span style="color: red; font-weight: bold; text-align: left;">This online review has not yet been approved by the reviewer</span>
	  	    </c:if>
  	    </c:if>
  		<kul:innerTab tabTitle="Document Overview" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">	
		    <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<c:forEach var="i" begin="1" end="${numberOfHeaderRows}" varStatus="status">
	 				<tr>
						<c:forEach var="j" begin="1" end="2" varStatus="innerStatus">
							<c:choose>
								<c:when test="${headerFieldCount > fieldCounter}">
			 						<c:set var="headerField" value="${headerFields[fieldCounter]}" />
			 						<c:choose>
			 							<c:when test="${(empty headerField) or (empty headerField.ddAttributeEntryName)}">
											<kul:htmlAttributeHeaderCell width = "25%"/>
											<td width = "25%" class = "grid">&nbsp;</td>
			 							</c:when>
			 							<c:otherwise>
					        				<kul:htmlAttributeHeaderCell attributeEntryName="${headerField.ddAttributeEntryName}" horizontal="true" scope="row" width = "25%"/>
					        				<td width = "25%" class = "grid">
					        				
					        					<c:if test="${empty headerField.nonLookupValue and empty headerField.displayValue}">
					        						&nbsp;
					        					</c:if>
												<c:choose>
													<c:when test="${headerField.lookupAware and (not lookup)}" >
														${headerField.nonLookupValue}
													</c:when>
													<c:otherwise>
														${headerField.displayValue}
													</c:otherwise>
												</c:choose>
						 					</td>
			 							</c:otherwise>
			 						</c:choose>
								</c:when>
								<c:otherwise>
									<kul:htmlAttributeHeaderCell width = "25%"/>
									<td width = "25%" class = "grid">&nbsp;</td>
								</c:otherwise>
							</c:choose>
		 					<c:if test="${headerFieldCount > fieldCounter}"></c:if>
							<c:set var="fieldCounter" value="${fieldCounter+1}" />
		 				</c:forEach>
      				</tr>
    			</c:forEach>
	
			     <tr>
			     	
					<kul:htmlAttributeHeaderCell
		          		labelFor="document.documentHeader.documentDescription"
		          		attributeEntry="${docHeaderAttributes.documentDescription}"
		          		horizontal="true" width = "25%" rowspan="1"
		          	/>
		      		<td align="left" valign="middle" width = "25%">
		      			<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].documentHeader.documentDescription" attributeEntry="${docHeaderAttributes.documentDescription}" readOnly="${documentOverviewReadOnly}"/>
		      		</td>
		      		<kul:htmlAttributeHeaderCell
                  		labelFor="document.documentHeader.explanation"
                  		attributeEntry="${docHeaderAttributes.explanation}"
                  		horizontal="true"
		          		rowspan="1" width = "25%"
                  	/>
		      		<td align="left" valign="middle" rowspan="1" width = "25%">
                  		<kul:htmlControlAttribute
                      		property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].documentHeader.explanation"
                      		attributeEntry="${docHeaderAttributes.explanation}"
                      		readOnly="${documentOverviewReadOnly}"
                      		readOnlyAlternateDisplay="${fn:replace(fn:escapeXml(onlineReviewsActionHelper.protocolOnlineReviewDocuments[renderIndex].documentHeader.explanation), Constants.NEWLINE, '<br/>')}"
                      	/>
              		</td>
		    	</tr>
		    	<tr>
			  		<kul:htmlAttributeHeaderCell
		        		labelFor="document.documentHeader.organizationDocumentNumber"
		        		attributeEntry="${docHeaderAttributes.organizationDocumentNumber}"
		        		horizontal="true" width = "25%"
		      		/>			  
              		<td align="left" valign="middle" width = "25%">
              			<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].documentHeader.organizationDocumentNumber" attributeEntry="${docHeaderAttributes.organizationDocumentNumber}" readOnly="${documentOverviewReadOnly}"/>
              		</td>
            	</tr>
			</table>   
         	</kul:innerTab>
		  <!-- DOC OVERVIEW TABLE -->

		 	<kul:innerTab tabTitle="Online Review" parentTab="" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
		    <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<tr>
                	<th width = "25%" class="grid">
                		<div align="right">
                			Reviewer:
                		</div>
                	</th>
                	<td width = "25%" nowrap class="grid">
						<c:out value = "${reviewerPerson.fullName}"/>
					</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateRequested}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid">
					<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.dateRequested" attributeEntry="${onlineReviewAttributes.dateRequested}" datePicker="true" readOnly = "${readOnly || !kualiForm.irbAdminFieldsEditable}" />
                </td>
              </tr>
              <tr>
				<th width = "25%" class="grid">
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" noColon="false" />
					</div>
				</th>
              	<td width = "25%" class = "grid">
              		<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.protocolOnlineReviewStatusCode" attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" datePicker="false" readOnly="true" />
              	</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateDue}" noColon="false"  />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.dateDue" attributeEntry="${onlineReviewAttributes.dateDue}" datePicker="true" readOnly = "${readOnly || !kualiForm.irbAdminFieldsEditable}" />
                </td>
              </tr>
			  <tr>
              
              	<th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.protocolOnlineReviewDeterminationRecommendationCode" attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" datePicker="false" readOnly="${readOnly}" />
                </td>
                <th width = "25%" class="grid">
           			<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.reviewerTypeCode}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.protocolReviewer.reviewerTypeCode"
		                                                                                  attributeEntry="${onlineReviewAttributes.reviewerTypeCode}" readOnly = "${readOnly || !kualiForm.irbAdminFieldsEditable}"/>
				</td>
              </tr>
         	</table>
			</kul:innerTab>
		
		
			<kra-irb-olr:onlineReviewComments bean="${KualiForm.onlineReviewsActionHelper.reviewCommentsBeans[renderIndex]}"
       										  documentNumber = "${documentNumber}" 
       										  allowReadOnly="${readOnly}" 
       										  action="Online" 
       										  property="onlineReviewsActionHelper.reviewCommentsBeans[${renderIndex}]"
       										  reviewIndex = "${renderIndex}" readOnly="${readOnly}"></kra-irb-olr:onlineReviewComments>
		
			<kra-irb-olr:onlineReviewAttachments bean="${KualiForm.onlineReviewsActionHelper.reviewAttachmentsBeans[renderIndex]}"
       										  documentNumber = "${documentNumber}" 
       										  allowReadOnly="${readOnly}" 
       										  action="Online" 
       										  property="onlineReviewsActionHelper.reviewAttachmentsBeans[${renderIndex}]"
       										  reviewIndex = "${renderIndex}" readOnly="${readOnly}"></kra-irb-olr:onlineReviewAttachments>
		

			<kul:innerTab tabTitle="Protocol Review Actions" parentTab="" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
				 <table cellpadding="0" cellspacing="0" class="datatable">
				 <tr><td>
				<c:set var = "viewOnly" value = "false"/>
				
				<c:set var="documentTypeName" value="${kualiForm.docTypeName}" />
				<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
					<c:if test="${not kualiForm.suppressAllButtons}">
	        			<div id="globalbuttons" class="globalbuttons">
	        				<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_ROUTE]}">
	        					<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif" styleClass="globalbuttons" property="methodToCall.routeOnlineReview.${documentNumber}.anchor${tabKey}" title="submit" alt="submit"/>
	        				</c:if>
	        				<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_SAVE] and not viewOnly}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.saveOnlineReview.${documentNumber}.anchor${tabKey}" title="save" alt="save" onclick="return checkForUnprocessedComments('onlineReviewsActionHelper.reviewCommentsBeans[${renderIndex}].newReviewComment.minuteEntry');"/>
	            			</c:if>
	            			<c:if test="${(!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE]) and not suppressRoutingControls}">
	            				<html:image src="static/images/${kualiForm.approveImageName}" styleClass="globalbuttons" property="methodToCall.approveOnlineReview.${documentNumber}.anchor${tabKey}" title="approve" alt="approve" onclick="return checkForUnprocessedComments('onlineReviewsActionHelper.reviewCommentsBeans[${renderIndex}].newReviewComment.minuteEntry');"/>
	            			</c:if>
	            			<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif" styleClass="globalbuttons" property="methodToCall.disapproveOnlineReview.${documentNumber}.anchor${tabKey}" title="disapprove" alt="disapprove"/>
	            			</c:if>
                            <c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
                                <html:image src="static/images/buttonsmall_delete_review.gif" styleClass="globalbuttons" property="methodToCall.deleteOnlineReview.${documentNumber}.anchor${tabKey}" title="delete" alt="delete"/>
                            </c:if>	            			
	            			<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_CANCEL] and not suppressCancelButton}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancelOnlineReview.${documentNumber}.anchor${tabKey}" title="cancel" alt="cancel"/>
	            			</c:if>
	            			
	          				<c:if test="${!empty kualiForm.extraButtons}">
		        				<c:forEach items="${kualiForm.extraButtons}" var="extraButton">
        							<html:image src="${extraButton.extraButtonSource}" styleClass="globalbuttons" property="${extraButton.extraButtonProperty}.${documentNumber}.anchor${tabKey}" title="${extraButton.extraButtonAltText}" alt="${extraButton.extraButtonAltText}"  onclick="${extraButton.extraButtonOnclick}"/>
		        				</c:forEach>
	        				</c:if>
	        			
	            				
	        			</div>
	        			</td></tr></table>
        			</c:if>
			</kul:innerTab>
        </div>
        
		


