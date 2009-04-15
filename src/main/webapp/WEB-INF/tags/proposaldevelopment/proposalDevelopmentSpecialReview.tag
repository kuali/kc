<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proSpecialAttriburesAttributes" value="${DataDictionary.ProposalSpecialReview.attributes}" />
<c:set var="exemptionTypeAttributes" value="${DataDictionary.ProposalExemptNumber.attributes}" />

<c:set var="action" value="proposalDevelopmentSpecialReview" />
<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.propSpecialReview*,newPropSpecialReview*,documentExemptNumber*,newExemptNumber*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.propSpecialReview*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Special Review</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SpecialReview" altText="help"/></span>
        </div>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.approvalDate}"noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.expirationDate}"noColon="true" /></div></th>
          		<th><div align="center">Exempt #</div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.comments}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	
          	</tr>     
          	
          	<kra:section permission="modifyProposal">   
             <tr>
                <c:set var="textAreaFieldName" value="newPropSpecialReview.comments" />
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.specialReviewCode" attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}" styleClass="fixed-size-select"/>
	            </div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.approvalTypeCode" attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newPropSpecialReview.protocolNumber" attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="newPropSpecialReview.applicationDate" attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" datePicker="true"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.approvalDate" attributeEntry="${proSpecialAttriburesAttributes.approvalDate}" datePicker="true"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.expirationDate" attributeEntry="${proSpecialAttriburesAttributes.expirationDate}" datePicker="true"/>
               </div>
                </td>
                
                <td align="left" valign="middle" class="infoline">
               		 <div align="center">
					 <kul:htmlControlAttribute property="newPropSpecialReview.exemptNumbers" attributeEntry="${exemptionTypeAttributes.exemptionTypeCode}" isMultiSelect="true" multiSelectSize="4" />
					</div>	  			
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.comments" attributeEntry="${proSpecialAttriburesAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proSpecialAttriburesAttributes.comments.label}" />
                </div>
                </td>
				<td class="infoline">
					<div align=center>&nbsp;
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            </kra:section>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.propSpecialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.propSpecialReview[${status.index}].comments" />
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].specialReviewCode" readOnlyAlternateDisplay="${specialReview.specialReview.description}" attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}"  styleClass="fixed-size-select"/>
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].approvalTypeCode" readOnlyAlternateDisplay="${specialReview.specialReviewApprovalType.description}" attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].protocolNumber" attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].applicationDate" attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" datePicker="true"/></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].approvalDate" attributeEntry="${proSpecialAttriburesAttributes.approvalDate}" datePicker="true"/></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].expirationDate" attributeEntry="${proSpecialAttriburesAttributes.expirationDate}" datePicker="true"/></div>
	                </td>
 
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<%-- always make (readOnly = false) so that the select control will always appear even when (readOnly == true) but make disabled when the values should not be changed --%>
                    <kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].exemptNumbers" attributeEntry="${exemptionTypeAttributes.exemptionTypeCode}" isMultiSelect="true" multiSelectSize="4" readOnly="false" disabled="${readOnly}"/>
					</div>	  			
                </td>

	                <td align="left" valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].comments" attributeEntry="${proSpecialAttriburesAttributes.comments}" />
                        <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proSpecialAttriburesAttributes.comments.label}" />
	                
	                </div>
	                </td>
					<td>
					<div align=center>&nbsp;
					<kra:section permission="modifyProposal">  
						<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</kra:section>  
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tab>

<kul:panelFooter /> 
