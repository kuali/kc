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


<c:set var="institutionalProposalSpecialReviewAttributes" value="${DataDictionary.InstitutionalProposalSpecialReview.attributes}" />
<c:set var="institutionalProposalSpecialReviewExemptionAttributes" value="${DataDictionary.InstitutionalProposalSpecialReviewExemption.attributes}" />
<c:set var="action" value="institutionalProposalSpecialReview" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />
<c:set var="exemptionTypes" value="${KualiForm.newSpecialReview.exemptionTypes}" />

<div id="workarea">
<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.institutionalProposal.specialReview*,document.institutionalProposalList[0].specialReview*,newSpecialReview*,documentExemptNumber*,propSpecialReview*" auditCluster="specialReviewAuditWarnings"  tabAuditKey="document.institutionalProposal.specialReview*" useRiceAuditMode="false">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SpecialReview" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.protocolNumber}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalDate}"noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.expirationDate}"noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewExemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalSpecialReviewAttributes.comments}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	
          	</tr>     
          	
          	<%-- <kra:section permission="modifyInstitutionalProposal"> --%>   
            <c:if test="${!readOnly}">
             <tr>
                <c:set var="textAreaFieldName" value="newSpecialReview.comments" />
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.specialReviewCode" attributeEntry="${institutionalProposalSpecialReviewAttributes.specialReviewCode}" styleClass="fixed-size-select"/>
	            </div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.approvalTypeCode" attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalTypeCode}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newSpecialReview.protocolNumber" attributeEntry="${institutionalProposalSpecialReviewAttributes.protocolNumber}" />
                    <!-- <img class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <img class="nobord" src="kr/static/images/book_open.png" alt="inquiry"> -->
                 <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
                    fieldConversions="protocolNumber:newSpecialReview.protocolNumber" anchor="${currentTabIndex}" /> --> 
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="newSpecialReview.applicationDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.applicationDate}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.approvalDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalDate}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.expirationDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.expirationDate}" />
               </div>
                </td>
                <td align="left" valign="middle" class="infoline">
               		 <div align="center">
	               		 <html:select property="newExemptionTypeCodes" multiple="true" size="4">
							<html:optionsCollection name="exemptionTypes" value="exemptionTypeCode" label="description"/>
						 </html:select>
					 </div>	  			
                </td>
                 
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newSpecialReview.comments" attributeEntry="${institutionalProposalSpecialReviewAttributes.comments}" />
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
            <%--</kra:section>--%>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.institutionalProposal.specialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.institutionalProposal.specialReview[${status.index}].comments" />
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].specialReviewCode" readOnlyAlternateDisplay="${specialReview.specialReview.description}" attributeEntry="${institutionalProposalSpecialReviewAttributes.specialReviewCode}"  styleClass="fixed-size-select"/>
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].approvalTypeCode" readOnlyAlternateDisplay="${specialReview.specialReviewApprovalType.description}" attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalTypeCode}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].protocolNumber" attributeEntry="${institutionalProposalSpecialReviewAttributes.protocolNumber}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].applicationDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.applicationDate}" /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].approvalDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.approvalDate}" /></div>
	                </td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].expirationDate" attributeEntry="${institutionalProposalSpecialReviewAttributes.expirationDate}" /></div>
	                </td>
	                <td align="left" valign="middle">
	               		 <div align="center">
	               		    <c:choose>
	               		      <c:when test="${!readOnly}">
	               		 	<c:set var="selected" value="" />
	               		 	<c:set var="sltdExemptionTypeCodes" value="${KualiForm.document.institutionalProposal.specialReviews[status.index].exemptionTypeCodes}"/>
	               		 	<c:forEach var="key" items="${sltdExemptionTypeCodes}">
						   		<c:set var="selected" value="${selected},${key}" />
							</c:forEach>
		               		 <html:select property="document.institutionalProposal.specialReviews[${status.index}].exemptionTypeCodes" multiple="true" size="4">
		               		    <c:forEach var="keyLabel" items="${exemptionTypes}">
				  			    	<c:if test="${!empty keyLabel.exemptionTypeCode}" >
		                                <option value="${keyLabel.exemptionTypeCode}" <c:if test="${fn:contains(selected, keyLabel.exemptionTypeCode)}"> selected="true" </c:if> >${keyLabel.description}</option>
									</c:if>
								</c:forEach>
							 </html:select>
							 </c:when>
							 <c:otherwise>
							     <c:forEach var="exemptionCode" items="${KualiForm.document.institutionalProposal.specialReviews[status.index].exemptionTypeCodes}">
							         <c:forEach var="keyLabel" items="${exemptionTypes}">
							             <c:if test="${keyLabel.exemptionTypeCode == exemptionCode}">
							                 <c:out value="${keyLabel.description}" /><br/>
							             </c:if>
							         </c:forEach>
							     </c:forEach>
							 </c:otherwise>
							</c:choose>
						 </div>	  			
	                </td>
	                <td align="left" valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="document.institutionalProposal.specialReview[${status.index}].comments" attributeEntry="${institutionalProposalSpecialReviewAttributes.comments}" />
	                </div>
	                </td>
					<td>
					<div align=center>&nbsp;
					<%--<kra:section permission="modifyInstitutionalProposal">--%>  
                       <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                       </c:if>
					<%--</kra:section>--%>  
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tab>

<kul:panelFooter /> 