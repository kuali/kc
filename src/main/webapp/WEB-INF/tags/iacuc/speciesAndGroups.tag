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
<%@ attribute name="protocolSpeciesAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Protocol Species attributes" %>
<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Protocol Species" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Protocol Species" %>
<%@ attribute name="action" required="true" 
              description="The name of the action class" %>

<c:set var="canModify" value="${KualiForm.iacucProtocolSpeciesHelper.canModifyProtocolSpecies}"/>

<c:set var="readOnly" value="${!KualiForm.iacucProtocolSpeciesHelper.modifyProtocolSpecies}" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.SPECIAL_REVIEW_COMMENT_LENGTH%>" />

<!--  
<%@ attribute name="exemptionAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Species Exemption attributes" %>
<c:set var="enableIrbProtocolLinking" value="${KualiForm.iacucProtocolSpeciesHelper.isIrbProtocolLinkingEnabled}" />
-->

<kul:tab tabTitle="Species/Groups" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="iacucProtocolSpeciesHelper.newProtocolSpecies*,${collectionProperty}*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Species/Groups</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="protocolSpeciesTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.speciesGroup}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.speciesCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.strain}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.painCategoryCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.usdaCovered}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.speciesCountCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${exemptionAttributes.speciesCount}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${exemptionAttributes.exceptionsPresent}" noColon="true" /></div></th>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     

        	<kra:permission value="${KualiForm.iacucProtocolSpeciesHelper.modifyProtocolSpecies}">            
                <tr>
                
                	<!--  
                    <c:set var="protocolLinkingReadOnly" value="${KualiForm.iacucProtocolSpeciesHelper.newProtocolSpecies.specialReviewTypeCode == '1'}" />
                    <c:choose>
                       <c:when test="${protocolLinkingReadOnly}">
                           <c:set var="initialStyle" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyle" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    -->
	                <c:set var="textAreaFieldName" value="iacucProtocolSpeciesHelper.newProtocolSpecies.procedureSummary" />
					<th class="infoline" rowspan="2">
						Add:
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.speciesGroup" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesGroup}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.speciesCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCode}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.strain" 
		               		                          attributeEntry="${protocolSpeciesAttributes.strain}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.painCategoryCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.painCategoryCode}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.usdaCovered" 
		               		                          attributeEntry="${protocolSpeciesAttributes.usdaCovered}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.speciesCountCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCountCode}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.speciesCount" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCount}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
					<td class="infoline" rowspan="1">
						<div align="center">
							<html:image property="methodToCall.addProtocolSpecies.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
	                	</div>
	                </td>
	            </tr>
	            <tr>
	            	<th>
	            		<div align="right">
	            			<kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.procedureSummary}" noColon="false" />
	            		</div>
	            	</th>
	            	<td colspan="7">
	            		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newProtocolSpecies.procedureSummary" 
	            		                          attributeEntry="${protocolSpeciesAttributes.procedureSummary}" />
	            	</td>  
	            </tr>
	        </kra:permission>          
            
        	<c:forEach var="protocolSpecies" items="${collectionReference}" varStatus="status">
                <tr>
                	<!--  
                    <c:set var="protocolLinkingReadOnly" value="${enableIrbProtocolLinking and collectionReference[status.index].specialReviewTypeCode == '1'}" />
	                <c:choose>
	                    <c:when test="${collectionReference[status.index].specialReviewTypeCode == '1'}">
	                        <c:set var="initialStyle" value="display:inline"/>
	                    </c:when>
	                    <c:otherwise>
	                        <c:set var="initialStyle" value="display:none"/>
	                    </c:otherwise>
	                </c:choose>
	                -->
	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].procedureSummary" />
					<th class="infoline" rowspan="2">
					   <c:out value="${status.index+1}" />
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].speciesGroup" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesGroup}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
		            </td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].speciesCode" 
		                                              attributeEntry="${protocolSpeciesAttributes.speciesCode}"  
		                                              readOnly="${readOnly}"
		                                              styleClass="fixed-size-200-select"
		                                              readOnlyAlternateDisplay="${iacucProtocolSpecies.iacucSpecies.speciesName}" 
		                                              />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].strain" 
		               		                          attributeEntry="${protocolSpeciesAttributes.strain}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].painCategoryCode" 
		                                              attributeEntry="${protocolSpeciesAttributes.painCategoryCode}"  
		                                              readOnly="${readOnly}"
		                                              styleClass="fixed-size-200-select"
		                                              readOnlyAlternateDisplay="${iacucProtocolSpecies.iacucPainCategory.painCategory}" 
		                                              />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].usdaCovered" 
		               		                          attributeEntry="${protocolSpeciesAttributes.usdaCovered}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].speciesCountCode" 
		                                              attributeEntry="${protocolSpeciesAttributes.speciesCountCode}"  
		                                              readOnly="${readOnly}"
		                                              styleClass="fixed-size-200-select"
		                                              readOnlyAlternateDisplay="${iacucProtocolSpecies.iacucSpeciesCountType.description}" 
		                                              />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].speciesCount" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCount}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
					<td rowspan="1"><div align=center>
                        <c:if test="${!readOnly}">
                            <html:image property="methodToCall.deleteProtocolSpecies.line${status.index}.anchor${currentTabIndex}.validate0"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
	                </div></td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.procedureSummary}" noColon="false" /></div></th>
	            	<td colspan="7">
		            	<nobr>
	                        <c:choose>
	                            <c:when test="${!readOnly}">
	                                <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].procedureSummary" 
	                                                          attributeEntry="${protocolSpeciesAttributes.procedureSummary}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="${collectionProperty}[${status.index}].procedureSummary" action="${action}" 
		                                                 textAreaLabel="${protocolSpeciesAttributes.procedureSummary.label}" textValue="${protocolSpecies.procedureSummary}"  
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