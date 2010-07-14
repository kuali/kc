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
<c:set var="actionHelper" value = "${KualiForm.onlineReviewsActionHelper}"/>
<c:set var="readOnly" value = "false"/>

<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" />
<c:set var="documentTypeName" value="${KualiForm.docTypeName}" />
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="documentOverviewReadOnly" value = "false"/>

<c:set var = "documentHelperMap" value = "${KualiForm.onlineReviewsActionHelper.documentHelperMap[documentNumber]}"/>
<c:set var = "document" value = "${documentHelperMap['document']}"/>
<c:set var = "reviewerComments" value = "${documentHelperMap['reviewerComments']}"/>
<c:set var = "reviewerPerson" value = "${documentHelperMap['reviewerPerson']}"/>
<c:set var = "kualiForm" value = "${documentHelperMap['kualiForm']}"/>



	<div class="tab-container" align=center>
		  <!-- DOC OVERVIEW TABLE -->
		
		<c:set var="currentOnlineReviewForm" value = "${kualiForm}"/>
		<jsp:useBean id="currentOnlineReviewForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" />
		
    	<c:set var="numberOfHeaderRows" value="2" />
    	<c:set var="headerFieldCount" value="4" />
  		<c:set var="headerFields" value="${currentOnlineReviewForm.docInfo}" />
  		<c:set var="fieldCounter" value="0" />
  	
  		<kra:innerTab tabTitle="Document Overview" parentTab="" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">	
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
		          		horizontal="true" width = "25%" rowspan="2"
		          	/>
		      		<td align="left" valign="middle" width = "25%">
		      			<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].documentHeader.documentDescription" attributeEntry="${docHeaderAttributes.documentDescription}" readOnly="${documentOverviewReadOnly}"/>
		      		</td>
		      		<kul:htmlAttributeHeaderCell
                  		labelFor="document.documentHeader.explanation"
                  		attributeEntry="${docHeaderAttributes.explanation}"
                  		horizontal="true"
		          		rowspan="2" width = "25%"
                  	/>
		      		<td align="left" valign="middle" rowspan="2" width = "25%">
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
         	</kra:innerTab>
		  <!-- DOC OVERVIEW TABLE -->

		 	<kra:innerTab tabTitle="Online Review" parentTab="" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
		    <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<tr>
                	<th width = "25%" class="grid">
                		<div align="right">
                			Reviewer:
                		</div>
                	</th>
                	<td width = "25%" nowrap class="grid">
						<c:out value = "${KualiForm.onlineReviewsActionHelper.reviewerPersons[renderIndex].fullName}"/>
					</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateRequested}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid">
					<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.dateRequested" attributeEntry="${onlineReviewAttributes.dateRequested}" datePicker="true" readOnly="${readOnly}" />
                </td>
              </tr>
              <tr>
				<th width = "25%" class="grid">
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" noColon="false" />
					</div>
				</th>
              	<td width = "25%" class = "grid">
              		<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.protocolOnlineReviewStatusCode" attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" datePicker="false" readOnly="${readOnly}" />
              	</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateDue}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="onlineReviewsActionHelper.protocolOnlineReviewDocuments[${renderIndex}].protocolOnlineReview.dateDue" attributeEntry="${onlineReviewAttributes.dateDue}" datePicker="true" readOnly="${readOnly}" />
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
                	</div>
                </th>
                <td width = "25%" class="grid" >
				</td>
              </tr>
         	</table>
			</kra:innerTab>
		
		
			<kra-irb-olr:onlineReviewComments actionName="Online" 
       										  bean="${KualiForm.onlineReviewsActionHelper.protocolOnlineReviewsReviewCommentsList[renderIndex]}"
       										  documentNumber = "${documentNumber}" 
       										  allowReadOnly="${readOnly}" 
       										  action="Online" 
       										  property="onlineReviewsActionHelper.protocolOnlineReviewsReviewCommentsList[${renderIndex}]"
       										  reviewIndex = "${renderIndex}"></kra-irb-olr:onlineReviewComments>
		

			<kra:innerTab tabTitle="Protocol Review Actions" parentTab="" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
				<c:set var = "viewOnly" value = "false"/>
				<c:set var = "onlineReviewForm" value = "${documentHelperMap['kualiForm']}"/>
				<c:set var="documentTypeName" value="${onlineReviewForm.docTypeName}" />
				<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
					<c:if test="${not KualiForm.suppressAllButtons}">
	        			<div id="globalbuttons" class="globalbuttons">
	        					
	        				<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_ROUTE]}">
	        					<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif" styleClass="globalbuttons" property="methodToCall.routeOnlineReview.${documentNumber}.anchor${tabKey}" title="submit" alt="submit"/>
	        				</c:if>
	        				
	        				<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_SAVE] and not viewOnly}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.saveOnlineReview.${documentNumber}.anchor${tabKey}" title="save" alt="save"/>
	            			</c:if>
	            			<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and not suppressRoutingControls}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif" styleClass="globalbuttons" property="methodToCall.approveOnlineReview.${documentNumber}.anchor${tabKey}" title="approve" alt="approve"/>
	            			</c:if>
	            			<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif" styleClass="globalbuttons" property="methodToCall.disapproveOnlineReview.${documentNumber}.anchor${tabKey}" title="disapprove" alt="disapprove"/>
	            			</c:if>
	            			<c:if test="${!empty kualiForm.documentActions[Constants.KUALI_ACTION_CAN_CANCEL] and not suppressCancelButton}">
	            				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancelOnlineReview.${documentNumber}.anchor${tabKey}" title="cancel" alt="cancel"/>
	            			</c:if>
	        			</div>
        			</c:if>
			</kra:innerTab>
        </div>
        
		


