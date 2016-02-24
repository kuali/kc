<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.IACUC_PROCEDURE_SUMMARY_LENGTH%>" />
<c:set var="modifyPermission" value="${KualiForm.iacucProtocolSpeciesHelper.modifyProtocolSpecies}" />
<c:set var="readOnly" value="${!modifyPermission}" />


<kul:tab tabTitle="Species/Groups" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="newIacucProtocolSpecies*,iacucProtocolSpeciesHelper.newIacucProtocolSpecies*,${collectionProperty}*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Species/Groups</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="protocolSpeciesTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.speciesGroup}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.speciesCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.strain}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.painCategoryCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.usdaCovered}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.speciesCountCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.speciesCount}" noColon="true" /></div></th>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     

        	<kra:permission value="${KualiForm.iacucProtocolSpeciesHelper.modifyProtocolSpecies}">
        		<tbody class="addline">            
                <tr>
	                <c:set var="textAreaFieldName" value="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.procedureSummary" />
					<th class="infoline" rowspan="2">
						Add:
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.speciesGroup" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesGroup}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.speciesCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCode}" 
		                                              styleClass="fixed-size-500-select"
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.strain" 
		               		                          attributeEntry="${protocolSpeciesAttributes.strain}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.painCategoryCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.painCategoryCode}" 
		                                              styleClass="fixed-size-200-select"
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center" class="ignoreMeFromWarningOnAddRow">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.usdaCovered" 
		               		                          attributeEntry="${protocolSpeciesAttributes.usdaCovered}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.speciesCountCode" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCountCode}" 
		                                              styleClass="fixed-size-200-select"
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.speciesCount" 
		               		                          attributeEntry="${protocolSpeciesAttributes.speciesCount}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
					<td class="infoline" rowspan="1">
						<div align="center">
							<html:image property="methodToCall.addProtocolSpecies.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>
	                	</div>
	                </td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolSpeciesAttributes.procedureSummary}" noColon="false" /></div></th>
	            	<td align="left" valign="middle" class="infoline" colspan="7">
	            		<div align="left">
	            			<kul:htmlControlAttribute property="iacucProtocolSpeciesHelper.newIacucProtocolSpecies.procedureSummary" 
	            		                          attributeEntry="${protocolSpeciesAttributes.procedureSummary}" />
	            		</div>                          
	            	</td>  
	            </tr>
	            </tbody>
	        </kra:permission>          
            
        	<c:forEach var="protocolSpecies" items="${collectionReference}" varStatus="status">
                <tr>
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
		                                              styleClass="fixed-size-500-select"
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
                    <c:choose>
                        <c:when test="${!readOnly}">
                            <html:image property="methodToCall.deleteProtocolSpecies.line${status.index}.anchor${currentTabIndex}.validate0"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:when>
                        <c:otherwise>
                            &nbsp;
                        </c:otherwise>
                    </c:choose>
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
