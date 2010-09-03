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

<c:set var="protocolSpecialReviewAttributes" value="${DataDictionary.ProtocolSpecialReview.attributes}" />
<c:set var="protocolSpecialReviewExemptionAttributes" value="${DataDictionary.ProtocolSpecialReviewExemption.attributes}" />
<c:set var="action" value="protocolSpecialReview" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_SPECIAL_REVIEW_COMMENT_LENGTH%>" />

<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.protocolList[0].specialReview*,specialReviewHelper.newSpecialReview*,documentExemptNumber*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.protocol.specialReview*" useRiceAuditMode="false">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SpecialReview" altText="help"/></span>
        </h3>
        
        <table id="specialReviewTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><nobr><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.protocolNumber}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.approvalDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.expirationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewExemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
              	<c:if test="${!readOnly}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     
          		
          	 <c:if test="${!readOnly}"> 
	             <tr>
	                <c:set var="textAreaFieldName" value="specialReviewHelper.newSpecialReview.comments" />
					<th class="infoline" rowspan="2">
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
	                	<div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.applicationDate" attributeEntry="${protocolSpecialReviewAttributes.applicationDate}" />
	                    </nobr>
	                </div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                <div align="center">
	                    <nobr>
	                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalDate" attributeEntry="${protocolSpecialReviewAttributes.approvalDate}" />
	                    </nobr>
	                </div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="center">
	                	<nobr>
	                	<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.expirationDate" attributeEntry="${protocolSpecialReviewAttributes.expirationDate}" />
	                    </nobr>
	                    </div>
	                </td>
	                
	                <td align="left" valign="middle" class="infoline">
	               		 <div align="center">
	               		     <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.exemptionTypeCodes" attributeEntry="${protocolSpecialReviewExemptionAttributes.exemptionTypeCode}" />
						 </div>	  			
	                </td>
					<td class="infoline" rowspan="2">
						<div align=center>
							<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
	            
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.comments}" noColon="false" /></div></th>
	            	<td colspan="6">
	            		<nobr>
	            			<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.comments" attributeEntry="${protocolSpecialReviewAttributes.comments}" />
	            		</nobr>
	            	</td>  
	            </tr>
            	          
            </c:if>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.protocol.specialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.protocol.specialReview[${status.index}].comments" />
					<th class="infoline" rowspan="2">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].specialReviewCode" readOnlyAlternateDisplay="${specialReview.specialReview.description}" attributeEntry="${protocolSpecialReviewAttributes.specialReviewCode}"  styleClass="fixed-size-select" />
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].approvalTypeCode" readOnlyAlternateDisplay="${specialReview.specialReviewApprovalType.description}" attributeEntry="${protocolSpecialReviewAttributes.approvalTypeCode}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].protocolNumber" attributeEntry="${protocolSpecialReviewAttributes.protocolNumber}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].applicationDate" attributeEntry="${protocolSpecialReviewAttributes.applicationDate}"  /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].approvalDate" attributeEntry="${protocolSpecialReviewAttributes.approvalDate}"  /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].expirationDate" attributeEntry="${protocolSpecialReviewAttributes.expirationDate}"  /></div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
                        <div align="center">
                            <c:choose>
                                <c:when test="${!readOnly}">
                                    <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].exemptionTypeCodes" attributeEntry="${protocolSpecialReviewExemptionAttributes.exemptionTypeCode}" />
                                </c:when>
                                <c:otherwise>
                                    <!-- struts/rice wants to display "[]" for empty array so do not display anything for an empty array instead -->
                                    <c:if test="${not empty KualiForm.document.protocolList[0].specialReviews[status.index].exemptionTypeCodes}">
                                        <kul:htmlControlAttribute property="document.protocolList[0].specialReview[${status.index}].exemptionTypeCodes" attributeEntry="${protocolSpecialReviewExemptionAttributes.exemptionTypeCode}" />
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>			
	                </td>
					<td rowspan="2">
						<div align=center>&nbsp;
							<c:if test="${!readOnly}"> 
								<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</c:if>  
						</div>
	                </td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolSpecialReviewAttributes.comments}" noColon="false" /></div></th>
	            	<td colspan="6">
		            	<nobr>
	                        <c:choose>
	                            <c:when test="${!readOnly}">
	                                <kul:htmlControlAttribute property="document.protocol.specialReview[${status.index}].comments" 
	                                                          attributeEntry="${protocolSpecialReviewAttributes.comments}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="document.protocol.specialReview[${status.index}].comments" action="${action}" 
		                                                 textAreaLabel="${protocolSpecialReviewAttributes.comments.label}" textValue="${specialReview.comments}"  
		                                                 displaySize="${commentDisplayLength}"/>
	                            </c:otherwise>
	                        </c:choose>
	                    </nobr>
	            	</td> 
	            </tr>
        	</c:forEach>
        </table>
    </div> 
</kul:tab>

<kul:panelFooter />
</div>