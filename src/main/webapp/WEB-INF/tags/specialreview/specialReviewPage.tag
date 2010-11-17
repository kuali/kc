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
<%@ attribute name="businessObjectClassName" required="true" 
              description="The specific per-module business class to use for the help pages" %>
<%@ attribute name="attributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review attributes" %>
<%@ attribute name="exemptionAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review Exemption attributes" %>
<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Special Reviews" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Special Reviews" %>
<%@ attribute name="action" required="true" 
              description="The name of the action class" %>

<c:set var="canModify" value="${KualiForm.specialReviewHelper.canModifySpecialReview}"/>
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.SPECIAL_REVIEW_COMMENT_LENGTH%>" />

<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="specialReviewHelper.newSpecialReview*, document.protocol.specialReviews*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="specialReviewTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.specialReviewTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.protocolNumber}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.expirationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${exemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
              	<c:if test="${canModify}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     

            <c:if test="${canModify}"> 
                <tr>
	                <c:set var="textAreaFieldName" value="specialReviewHelper.newSpecialReview.comments" />
					<th class="infoline" rowspan="2">
						Add:
					</th>
	                <td align="left" valign="middle" class="infoline">
		                <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.specialReviewTypeCode" 
		                                                              attributeEntry="${attributes.specialReviewTypeCode}" styleClass="fixed-size-select"/></div>
					</td>
	                <td class="infoline">
		                <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalTypeCode" 
		                                                              attributeEntry="${attributes.approvalTypeCode}" /></div>
	                </td>
	                <td class="infoline">   
		                <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.protocolNumber" 
		                                                              attributeEntry="${attributes.protocolNumber}" /></div>
					</td>
	                <td align="left" valign="middle" class="infoline">
                        <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.applicationDate" 
                                                                      attributeEntry="${attributes.applicationDate}" /></div>
	                </td>
                    <td align="left" valign="middle" class="infoline">
                        <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalDate" 
                                                                      attributeEntry="${attributes.approvalDate}" /></div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.expirationDate" 
	                	                                              attributeEntry="${attributes.expirationDate}" /></div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
                        <div align="center"><kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.exemptionTypeCodes" 
                                                                      attributeEntry="${exemptionAttributes.exemptionTypeCode}" /></div>	  			
	                </td>
					<td class="infoline" rowspan="2">
						<div align="center"><html:image property="methodToCall.addSpecialReview.anchor${tabKey}" 
						                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						                                styleClass="tinybutton"/></div>
	                </td>
	            </tr>
	            
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="6">
	            		<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.comments" 
	            		                          attributeEntry="${attributes.comments}" />
	            	</td>  
	            </tr>      
            </c:if>
            
        	<c:forEach var="specialReview" items="${collectionReference}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].comments" />
					<th class="infoline" rowspan="2">
					   <c:out value="${status.index+1}" />
					</th>
                    <td align="left" valign="middle">
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].specialReviewTypeCode" 
	                                                                  attributeEntry="${attributes.specialReviewTypeCode}"  
	                                                                  readOnly="${not canModify}"
	                                                                  readOnlyAlternateDisplay="${specialReview.specialReviewType.description}" 
	                                                                  styleClass="fixed-size-select" /></div>
					</td>
                    <td>
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].approvalTypeCode" 
	                                                                  attributeEntry="${attributes.approvalTypeCode}" 
	                                                                  readOnly="${not canModify}"
	                                                                  readOnlyAlternateDisplay="${specialReview.approvalType.description}" /></div>
	                </td>
                    <td>
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].protocolNumber" 
                                                                      attributeEntry="${attributes.protocolNumber}" 
                                                                      readOnly="${not canModify}"/></div>
					</td>
                    <td align="left" valign="middle">
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].applicationDate" 
                                                                      attributeEntry="${attributes.applicationDate}" 
                                                                      readOnly="${not canModify}"/></div>
	                </td>
	                <td align="left" valign="middle">
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].approvalDate" 
                                                                      attributeEntry="${attributes.approvalDate}" 
                                                                      readOnly="${not canModify}"/></div>
	                </td>
	                <td align="left" valign="middle">
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].expirationDate" 
                                                                      attributeEntry="${attributes.expirationDate}" 
                                                                      readOnly="${not canModify}"/></div>
	                </td>
	                <td align="left" valign="middle">
                        <div align="center"><kul:htmlControlAttribute property="${collectionProperty}[${status.index}].exemptionTypeCodes" 
                                                                      attributeEntry="${exemptionAttributes.exemptionTypeCode}" 
                                                                      readOnly="${not canModify}"/></div>
	                </td>
					<td rowspan="2">
						<div align=center>&nbsp;
							<c:if test="${canModify}"> 
								<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}.validate0"
									        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</c:if>  
						</div>
	                </td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="6">
		            	<nobr>
	                        <c:choose>
	                            <c:when test="${canModify}">
	                                <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].comments" 
	                                                          attributeEntry="${attributes.comments}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="${collectionProperty}[${status.index}].comments" action="${action}" 
		                                                 textAreaLabel="${attributes.comments.label}" textValue="${specialReview.comments}"  
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