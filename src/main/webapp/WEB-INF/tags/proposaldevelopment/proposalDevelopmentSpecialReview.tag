<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proSpecialAttriburesAttributes" value="${DataDictionary.ProposalSpecialReview.attributes}" />
<c:set var="action" value="proposalDevelopmentSpecialReview" />
<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.propSpecialReview*,newPropSpecialReview*,documentExemptNumber*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.propSpecialReview*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Special Review</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
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
                    <!-- <img class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <img class="nobord" src="kr/static/images/book_open.png" alt="inquiry"> -->
                 <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
                    fieldConversions="protocolNumber:newPropSpecialReview.protocolNumber" anchor="${currentTabIndex}" /> --> 
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
                
                 <c:set var="exemptNumberStyle" value="" scope="request"/>
          	  
			     <c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq 'newExemptNumbers'}">
					  <c:set var="exemptNumberStyle" value="background-color:#FFD5D5" scope="request"/>
				    </c:if>
			     </c:forEach>
                
                
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <c:set var="selected" value="" />
						<c:forEach var="key" items="${KualiForm.newExemptNumbers}">
						   <c:set var="selected" value="${selected},${key}" />
						</c:forEach>
		  			<select name="newExemptNumbers" multiple="true" size="4"  style="${exemptNumberStyle}">
						<c:forEach var="keyLabel" items="${KualiForm.exemptNumberList}">
		  			    	<c:if test="${!empty keyLabel.key}" >
                                <option value="${keyLabel.key}" <c:if test="${fn:contains(selected, keyLabel.key)}"> selected="true" </c:if> >${keyLabel.label}</option>
							</c:if>
						</c:forEach>
					</select>	
					</div>	  			
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newPropSpecialReview.comments" attributeEntry="${proSpecialAttriburesAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proSpecialAttriburesAttributes.comments.label}" />
                </div>
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
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
                    <!-- <input type="image" class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <input type="image" class="nobord" src="kr/static/images/book_open.png" alt="inquiry">-->
	                <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
	                    fieldConversions="protocolNumber:document.propSpecialReview[${status.index}].protocolNumber"  anchor="${tabKey}"/> --> 
					
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

                 <c:set var="exemptNumberStyle" value="" scope="request"/>
          	     <c:set var="exemptNumberFieldName" value="documentExemptNumbers[${status.index}]" />
			     <c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq exemptNumberFieldName}">
					  <c:set var="exemptNumberStyle" value="background-color:#FFD5D5" scope="request"/>
				    </c:if>
			     </c:forEach>
 
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <c:set var="selected" value="" />
						<c:forEach var="key" items="${KualiForm.documentExemptNumbers[status.index]}">
						   <c:set var="selected" value="${selected},${key}" />
						</c:forEach>
		  			<select name="documentExemptNumbers[${status.index}]" multiple="true" size="4"  style="${exemptNumberStyle}">
						<c:forEach var="keyLabel" items="${KualiForm.exemptNumberList}">
		  			    	<c:if test="${!empty keyLabel.key}" >
                                <option value="${keyLabel.key}" <c:if test="${fn:contains(selected, keyLabel.key)}"> selected="true" </c:if> >${keyLabel.label}</option>
							</c:if>
						</c:forEach>
					</select>	
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
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</kra:section>  
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tab>

<kul:panelFooter /> 