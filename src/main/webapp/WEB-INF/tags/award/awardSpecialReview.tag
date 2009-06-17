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

<c:set var="awardSpecialReviewAttributes" value="${DataDictionary.AwardSpecialReview.attributes}" />
<c:set var="awardSpecialReviewExemptionAttributes" value="${DataDictionary.AwardSpecialReviewExemption.attributes}" />
<c:set var="action" value="awardSpecialReview" />
<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.award.specialReview*,newSpecialReview*,documentExemptNumber*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.award.specialReview*" useRiceAuditMode="false">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SpecialReview" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.protocolNumber}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.approvalDate}"noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.expirationDate}"noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewExemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardSpecialReviewAttributes.comments}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	
          	</tr>     
          	
          	<%-- <kra:section permission="modifyAward"> --%>   
             <tr>
                <c:set var="textAreaFieldName" value="newSpecialReview.comments" />
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.specialReviewCode" attributeEntry="${awardSpecialReviewAttributes.specialReviewCode}" styleClass="fixed-size-select"/>
	            </div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.approvalTypeCode" attributeEntry="${awardSpecialReviewAttributes.approvalTypeCode}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newSpecialReview.protocolNumber" attributeEntry="${awardSpecialReviewAttributes.protocolNumber}" />
                    <!-- <img class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <img class="nobord" src="kr/static/images/book_open.png" alt="inquiry"> -->
                 <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
                    fieldConversions="protocolNumber:newSpecialReview.protocolNumber" anchor="${currentTabIndex}" /> --> 
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="newSpecialReview.applicationDate" attributeEntry="${awardSpecialReviewAttributes.applicationDate}" datePicker="true"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.approvalDate" attributeEntry="${awardSpecialReviewAttributes.approvalDate}" datePicker="true"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.expirationDate" attributeEntry="${awardSpecialReviewAttributes.expirationDate}" datePicker="true"/>
               </div>
                </td>
                
                 <c:set var="exemptionTypes" value="${KualiForm.newSpecialReview.exemptionTypes}" />
                <td align="left" valign="middle" class="infoline">
               		 <div align="center">
	               		 <html:select property="newExemptionTypeCodes" multiple="true" size="4">
							<html:optionsCollection name="exemptionTypes" value="exemptionTypeCode" label="description"/>
						 </html:select>
					 </div>	  			
                </td>
                 
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.comments" attributeEntry="${awardSpecialReviewAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${awardSpecialReviewAttributes.comments.label}" />
                </div>
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            <%--</kra:section>--%>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.award.specialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.award.specialReview[${status.index}].comments" />
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.award.specialReview[${status.index}].specialReviewCode" readOnlyAlternateDisplay="${specialReview.specialReview.description}" attributeEntry="${awardSpecialReviewAttributes.specialReviewCode}"  styleClass="fixed-size-select"/>
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="document.award.specialReview[${status.index}].approvalTypeCode" readOnlyAlternateDisplay="${specialReview.specialReviewApprovalType.description}" attributeEntry="${awardSpecialReviewAttributes.approvalTypeCode}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.award.specialReview[${status.index}].protocolNumber" attributeEntry="${awardSpecialReviewAttributes.protocolNumber}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.award.specialReview[${status.index}].applicationDate" attributeEntry="${awardSpecialReviewAttributes.applicationDate}" datePicker="true"/></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.award.specialReview[${status.index}].approvalDate" attributeEntry="${awardSpecialReviewAttributes.approvalDate}" datePicker="true"/></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.award.specialReview[${status.index}].expirationDate" attributeEntry="${awardSpecialReviewAttributes.expirationDate}" datePicker="true"/></div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	               		 <div align="center">
	               		 	<c:set var="selected" value="" />
	               		 	<c:set var="sltdExemptionTypeCodes" value="${KualiForm.document.award.specialReviews[status.index].exemptionTypeCodes}"/>
	               		 	<c:forEach var="key" items="${sltdExemptionTypeCodes}">
						   		<c:set var="selected" value="${selected},${key}" />
							</c:forEach>
		               		 <html:select property="document.award.specialReviews[${status.index}].exemptionTypeCodes" multiple="true" size="4">
		               		    <c:forEach var="keyLabel" items="${exemptionTypes}">
				  			    	<c:if test="${!empty keyLabel.exemptionTypeCode}" >
		                                <option value="${keyLabel.exemptionTypeCode}" <c:if test="${fn:contains(selected, keyLabel.exemptionTypeCode)}"> selected="true" </c:if> >${keyLabel.description}</option>
									</c:if>
								</c:forEach>
							 </html:select>
						 </div>	  			
	                </td>
	                <td align="left" valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="document.award.specialReview[${status.index}].comments" attributeEntry="${awardSpecialReviewAttributes.comments}" />
                        <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${awardSpecialReviewAttributes.comments.label}" />
	                
	                </div>
	                </td>
					<td>
					<div align=center>&nbsp;
					<%--<kra:section permission="modifyAward">--%>  
						<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					<%--</kra:section>--%>  
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tab>

<kul:panelFooter /> 
