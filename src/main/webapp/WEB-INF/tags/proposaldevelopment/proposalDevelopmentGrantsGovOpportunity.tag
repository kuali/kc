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
<c:set var="s2sOpportunity" value="${DataDictionary.S2sOpportunity.attributes}" />
<c:set var="textAreaFieldName" value="document.opportunityTitle" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Opportunity" tabErrorKey="document.s2sOpportunity.opportunityId,document.s2sOpportunity.opportunityTitle,document.s2sOpportunity.s2sSubmissionTypeCode,document.s2sOpportunity.revisionCode,document.s2sOpportunity.cfdaNumber,document.s2sOpportunity.competetionId,document.s2sOpportunity.openingDate,document.s2sOpportunity.closingDate,document.s2sOpportunity.instructionUrl,document.s2sOpportunity.schemaUrl,document.s2sOpportunity.revisionOtherDescription,noField" auditCluster="grantsGovAuditWarnings,grantsGovAuditErrors" tabAuditKey="document.s2sOpportunity.s2sSubmissionTypeCode,document.s2sOpportunity.revisionCode">
    <div class="innerTab-container" align="left">
        <table cellpadding=0 cellspacing=0 summary="">
        <tr>
            <td style="padding:0px">
                <table class="tab" cellpadding=0 cellspacing="0" summary=""> 
                <tbody id="G1">    
                   <tr>
                       <th>
                           <div align="right">
                               <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.opportunityId}" />
                           </div>
                       </th>
                       <td>
                           <kul:htmlControlAttribute property="document.s2sOpportunity.opportunityId" 
                                                     attributeEntry="${s2sOpportunity.opportunityId}" readOnly="true"/>
                       </td>
                   </tr>
                   <tr>
                      <th>
                          <div align="right">
                              <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.opportunityTitle}" />
                          </div>
                      </th>
                      <td>
                          <kul:htmlControlAttribute property="document.s2sOpportunity.opportunityTitle" 
                               attributeEntry="${s2sOpportunity.opportunityTitle}" readOnly="true" />
                      </td>
                   </tr>

                   <tr>
                      <th>
                          <div align="right">
                              <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.s2sSubmissionTypeCode}" />
                          </div>
                      </th>
                      <jsp:useBean id="paramMap" class="java.util.HashMap"/>
                      <td>
						<html:select property="document.s2sOpportunity.s2sSubmissionTypeCode" tabindex="0" >			
						<c:choose>
						<c:when test="${not empty KualiForm.document.continuedFrom}">
		       				<c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.S2sSubmissionTypeValuesFinderForResubmission', paramMap)}" var="option">
		        				<c:choose>                    	
		        				<c:when test="${KualiForm.document.s2sOpportunity.s2sSubmissionTypeCode == option.key}">
		            				<option value="${option.key}" selected>${option.label}</option>
		            			</c:when>
		            			<c:otherwise>
		            				<option value="${option.key}">${option.label}</option>
		            			</c:otherwise>
	            				</c:choose>                    
	        				</c:forEach>
	        			</c:when>
				        <c:otherwise>
	        				<c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.S2sSubmissionTypeValuesFinder', paramMap)}" var="option">
		        				<c:choose>                    	
	        					<c:when test="${KualiForm.document.s2sOpportunity.s2sSubmissionTypeCode == option.key}">
		            				<option value="${option.key}" selected>${option.label}</option>
		            			</c:when>
		            			<c:otherwise>
	            					<option value="${option.key}">${option.label}</option>
	            				</c:otherwise>
	            				</c:choose>                    
	        				</c:forEach>
	        			</c:otherwise>
	        			</c:choose>
	    				</html:select>                          
                     </td>
                   </tr>           

                   <tr>
                     <th>
                         <div align="right">
                             <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.revisionCode}" />
                         </div>
                     </th>
                     <td>
                     
                         <jsp:useBean id="revisionTypeParamMap" class="java.util.HashMap"/>
						 <%--<c:set target="${revisionTypeParamMap}" property="businessObjectClass" value="org.kuali.kra.bo.S2sRevisionType" />
						 <c:set target="${revisionTypeParamMap}" property="keyAttributeName" value="s2sRevisionTypeCode" />
						 <c:set target="${revisionTypeParamMap}" property="labelAttributeName" value="description" />
						 <c:set target="${revisionTypeParamMap}" property="includeKeyInDescription" value="false" />
                     	--%>
                     	<kul:checkErrors keyMatch="document.s2sOpportunity.revisionCode" auditMatch="document.s2sOpportunity.revisionCode"/>
					 	<c:if test="${hasErrors==true}">
						    <c:set var="textStyle" value="background-color:#FFD5D5"/>
					 	</c:if>
					 
						 <html:select property="document.s2sOpportunity.revisionCode" tabindex="0" style="${textStyle}" styleId="document.s2sOpportunity.revisionCode" >                                                   
                         <c:forEach items="${krafn:getOptionList('org.kuali.kra.proposaldevelopment.lookup.keyvalue.S2sRevisionTypeValuesFinder', revisionTypeParamMap)}" var="option">
		        		 	 <c:choose>                    	
	        					<c:when test="${KualiForm.document.s2sOpportunity.revisionCode == option.key}">
		            				<option value="${option.key}" selected>${option.label}</option>
		            			</c:when>
		            			<c:otherwise>
	            					<option value="${option.key}">${option.label}</option>
	            				</c:otherwise>
	            			 </c:choose>                    
	        			 </c:forEach>                         
                         </html:select>                        
                         <kul:htmlControlAttribute property="document.s2sOpportunity.revisionOtherDescription" 
                                                   attributeEntry="${s2sOpportunity.revisionOtherDescription}" />            
                     </td>
                   </tr>
    
                   <tr>
                     <th>
                         <div align="right">
                             <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.cfdaNumber}" />
                         </div>
                     </th>
                     <td>
                         <kul:htmlControlAttribute property="document.s2sOpportunity.cfdaNumber" 
                                                   attributeEntry="${s2sOpportunity.cfdaNumber}"  readOnly="true"/>
                     </td>
                     <!-- <td align="left" valign="middle"><kul:htmlControlAttribute property="document.currentAwardNumber" attributeEntry="${proposalDevelopmentAttributes.currentAwardNumber}" /></td>  --> 
                   </tr>
    
                   <tr>
                     <th>
                         <div align="right">
                             <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.competetionId}" />
                         </div>
                     </th>
                     <td>
                         <kul:htmlControlAttribute property="document.s2sOpportunity.competetionId" 
                                                   attributeEntry="${s2sOpportunity.competetionId}"  readOnly="true"/>
                     </td>
                   </tr>
    
                   <tr>
                    <th>
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.openingDate}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="document.s2sOpportunity.openingDate" 
                                                  attributeEntry="${s2sOpportunity.openingDate}" 
                                                  datePicker="true"  readOnly="true"/>
                    </td>

                   </tr>
    
                   <tr>
                    <th>
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.closingDate}" />
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="document.s2sOpportunity.closingDate" 
                                                  attributeEntry="${s2sOpportunity.closingDate}" 
                                                  datePicker="true"  readOnly="true"/>
                    </td>
                   </tr>
    
                   <tr>
                    <th>
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.instructionUrl}" />
                        </div>
                    </th>
                    <td>
						<input type="hidden" name="document.s2sOpportunity.instructionUrl" value="${KualiForm.document.s2sOpportunity.instructionUrl}" />
                        <html:link href="${KualiForm.document.s2sOpportunity.instructionUrl}" target="_blank">
                        	<c:out value="${KualiForm.document.s2sOpportunity.instructionUrl}"/> </html:link>
                    </td>
                  </tr>
    
                  <tr>
                   <th>
                       <div align="right">
                           <kul:htmlAttributeLabel attributeEntry="${s2sOpportunity.schemaUrl}" />
                       </div>
                  </th>
                  <td>
						<input type="hidden" name="document.s2sOpportunity.schemaUrl" value="${KualiForm.document.s2sOpportunity.schemaUrl}" />
                        <html:link href="${KualiForm.document.s2sOpportunity.schemaUrl}" target="_blank">
                        	<c:out value="${KualiForm.document.s2sOpportunity.schemaUrl}"/> </html:link>                                                
                  </td>
                 </tr> 

                </tbody>
                </table>        
            </td>
        </tr>
        </table>
    </div>
</kul:innerTab>    
