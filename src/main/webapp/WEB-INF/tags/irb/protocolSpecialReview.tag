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

<c:set var="protocolSpecialReviewAttributes" value="${DataDictionary.ProtocolSpecialReview.attributes}" />
<c:set var="protocolSpecialReviewExemptionAttributes" value="${DataDictionary.ProtocolSpecialReviewExemption.attributes}" />
<c:set var="action" value="protocolSpecialReview" />
<c:set var="readOnly" value="${!KualiForm.specialReviewHelper.modifyProtocol}" />
<c:set var="exemptionTypes" value="${KualiForm.specialReviewHelper.newSpecialReview.exemptionTypes}" />

<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.protocolList[0].specialReview*,specialReviewHelper.newSpecialReview*,documentExemptNumber*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.protocol.specialReview*" useRiceAuditMode="false">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SpecialReview" altText="help"/></span>
        </h3>
        
        <table id="specialReviewTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><nobr><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.protocolNumber}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.approvalDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.expirationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewExemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.comments}" noColon="true" /></div></th>
              	<c:if test="${!readOnly}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     
          		
          	 <c:if test="${!readOnly}"> 
             <tr>
                <c:set var="textAreaFieldName" value="specialReviewHelper.newSpecialReview.comments" />
				<th class="infoline">
					Add:
				</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.specialReviewCode" attributeEntry="${protocolSpecialReviewAttributes.specialReviewCode}" styleClass="fixed-size-select"/>
	            </div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalTypeCode" attributeEntry="${protocolSpecialReviewAttributes.approvalTypeCode}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.protocolNumber" attributeEntry="${protocolSpecialReviewAttributes.protocolNumber}" />
                </div>
				</td>
                <td align="left" valign="middle" class="infoline">
                    <nobr>
                	<div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.applicationDate" attributeEntry="${protocolSpecialReviewAttributes.applicationDate}" datePicker="true"/>
                    </nobr>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <nobr>
                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalDate" attributeEntry="${protocolSpecialReviewAttributes.approvalDate}" datePicker="true"/>
                    </nobr>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<nobr>
                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.expirationDate" attributeEntry="${protocolSpecialReviewAttributes.expirationDate}" datePicker="true"/>
                    </nobr>
                    </div>
                </td>
                
                <td align="left" valign="middle" class="infoline">
               		 <div align="center">
	               		 <html:select property="specialReviewHelper.newExemptionTypeCodes" multiple="true" size="4">
							<html:optionsCollection name="exemptionTypes" value="exemptionTypeCode" label="description"/>
						 </html:select>
					 </div>	  			
                </td>
                 
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <nobr>
                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.comments" attributeEntry="${protocolSpecialReviewAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolSpecialReviewAttributes.comments.label}" />
                    </nobr>
                </div>
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            </c:if>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.protocol.specialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.protocol.specialReview[${status.index}].comments" />
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].specialReviewCode" readOnlyAlternateDisplay="${specialReview.specialReview.description}" attributeEntry="${protocolSpecialReviewAttributes.specialReviewCode}"  styleClass="fixed-size-select" readOnly="${readOnly}" />
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].approvalTypeCode" readOnlyAlternateDisplay="${specialReview.specialReviewApprovalType.description}" attributeEntry="${protocolSpecialReviewAttributes.approvalTypeCode}" readOnly="${readOnly}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].protocolNumber" attributeEntry="${protocolSpecialReviewAttributes.protocolNumber}" readOnly="${readOnly}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].applicationDate" attributeEntry="${protocolSpecialReviewAttributes.applicationDate}" datePicker="true" readOnly="${readOnly}" /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].approvalDate" attributeEntry="${protocolSpecialReviewAttributes.approvalDate}" datePicker="true" readOnly="${readOnly}" /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].expirationDate" attributeEntry="${protocolSpecialReviewAttributes.expirationDate}" datePicker="true" readOnly="${readOnly}" /></div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	               		 <div align="center">
	               		 	<c:set var="selected" value="" />
	               		 	<c:set var="sltdExemptionTypeCodes" value="${KualiForm.document.protocolList[0].specialReviews[status.index].exemptionTypeCodes}"/>
	               		 	<c:forEach var="key" items="${sltdExemptionTypeCodes}">
						   		<c:set var="selected" value="${selected},${key}" />
							</c:forEach>
							<c:choose> 
								<c:when test="${readOnly}">
								    <c:set var="selectedList" value="" />
									<c:forEach var="keyLabel" items="${exemptionTypes}">
						  			    <c:if test="${!empty keyLabel.exemptionTypeCode}" >
						  			        <c:if test="${fn:contains(selected, keyLabel.exemptionTypeCode)}">
						  			            <c:set var="selectedList" value="${selectedList},${keyLabel.description}" />
										    </c:if>
										</c:if>
									</c:forEach>
									<c:out value="${fn:substring(selectedList, 1, fn:length(selectedList))}" />
								</c:when>
								<c:otherwise>
				               		 <html:select property="document.protocol.specialReviews[${status.index}].newExemptionTypeCodes" multiple="true" size="4">
				               		    <c:forEach var="keyLabel" items="${exemptionTypes}">
						  			    	<c:if test="${!empty keyLabel.exemptionTypeCode}" >
				                                <option value="${keyLabel.exemptionTypeCode}" <c:if test="${fn:contains(selected, keyLabel.exemptionTypeCode)}"> selected="true" </c:if> >${keyLabel.description}</option>
											</c:if>
										</c:forEach>
									 </html:select>
								 </select>
							   </c:otherwise>
							</c:choose>
						 </div>	  			
	                </td>
	                <td align="left" valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="document.protocol.specialReview[${status.index}].comments" attributeEntry="${protocolSpecialReviewAttributes.comments}" readOnly="${readOnly}" />
                        <c:if test="${!readOnly}">
                            <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolSpecialReviewAttributes.comments.label}" />
	                    </c:if>
	                </div>
	                </td>
					<td>
					<div align=center>&nbsp;
					<c:if test="${!readOnly}"> 
						<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</c:if>  
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tab>

<kul:panelFooter /> 
