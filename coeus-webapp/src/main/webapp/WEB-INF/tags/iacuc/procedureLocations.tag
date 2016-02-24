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

<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="procedureBeanIndex" required="true" 
              description="The procedure bean index" %>
<%@ attribute name="procedureDetailBeanIndex" required="true" 
              description="The procedure bean index" %>
<%@ attribute name="procedureLocationProperty" required="true" 
              description="The procedure location property" %>

<c:set var="procedureLocationAttributes" value="${DataDictionary.IacucProtocolStudyGroupLocation.attributes}" />
<c:set var="modifyPermission" value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />
<c:set var="readOnly" value="${!modifyPermission}" />

<kul:innerTab tabTitle="Location(s)" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${procedureDetailBeanIndex}].newIacucProtocolStudyGroupLocation*" useCurrentTabIndexAsKey="true">
	<div class="innerTab-container" align="left">
    	<h3>
    		<span class="subhead-left">Location(s)</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="procedureLocationsTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${procedureLocationAttributes.locationTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${procedureLocationAttributes.locationId}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${procedureLocationAttributes.locationRoom}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${procedureLocationAttributes.studyGroupLocationDescription}" noColon="true" /></nobr></div></th>
          		
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     

        	<kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}">
        		<tbody class="addline">   
                <tr>
					<th class="infoline">
						Add:
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
                			<html:select property="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationTypeCode" styleId="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationTypeCode" onchange="populateSelect('getIacucProcedureLocationNames', '${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationTypeCode', '${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationId');">                                              
                            <c:forEach items="${krafn:getOptionList('org.kuali.kra.iacuc.IacucLocationTypeValuesFinder', paramMap)}" var="option" >
							<c:set var="locationTypeCode" value="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationTypeCode" />
                            <c:choose>                    	
	                			<c:when test="${locationTypeCode == option.key}">
	                        		<option value="${option.key}" selected>${option.value}</option>
	                    		</c:when>
	                    		<c:otherwise>
	                        		<c:out value="${option.value}"/>
                                 	<option value="${option.key}">${option.value}</option>
                                </c:otherwise>
	                		</c:choose>   
                            </c:forEach>
                            </html:select>
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		                    <html:select property="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationId" styleId="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationId">                                              	                
								<option value="">select</option> 
							</html:select>                         
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.locationRoom" 
		               		                          attributeEntry="${procedureLocationAttributes.locationRoom}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${procedureLocationProperty}.newIacucProtocolStudyGroupLocation.studyGroupLocationDescription" 
		               		                          attributeEntry="${procedureLocationAttributes.studyGroupLocationDescription}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
				    <c:set var="procedureBean" value="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroups[${procedureDetailBeanIndex}]" />
					<td class="infoline">
						<div align="center">
 							<html:image property="methodToCall.addProcedureGroupLocation.${procedureBean}.line${status.index}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>
	                	</div>
	                </td>
	            </tr>
	            </tbody>
	        </kra:permission>          
            
        	<c:forEach var="protocolLocation" items="${collectionReference}" varStatus="status">
               	<c:set var="locationName" value="${protocolLocation.iacucLocationName.locationName}" />
               	<c:set var="locationId" value="${protocolLocation.locationId}" />
                <tr>
					<th class="infoline">
					   <c:out value="${status.index+1}" />
					</th>

		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		                	<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].locationTypeCode" 
		                	                          attributeEntry="${procedureLocationAttributes.locationTypeCode}" 
		                	                          onchange="populateSelect('getIacucProcedureLocationNames', '${collectionProperty}[${status.index}].locationTypeCode', '${collectionProperty}[${status.index}].locationId');" readOnly="${readOnly}"/>
							<script type="text/javascript">
							   var $j = jQuery.noConflict();
							   $j(document).ready(function() {
								   populateSelect('getIacucProcedureLocationNames', '${collectionProperty}[${status.index}].locationTypeCode', '${collectionProperty}[${status.index}].locationId');
							   });
							</script>
							<%-- 
                	                          readOnlyAlternateDisplay="${collectionProperty}[${status.index}].locationName}"
							--%>
		            	</div>
		            </td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
						<c:choose>
							<c:when test="${readOnly}" >
						   		<c:out value="${locationName}" />
							</c:when>
							<c:otherwise>
			                    <html:select property="${collectionProperty}[${status.index}].locationId" styleId="${collectionProperty}[${status.index}].locationId">                                              	                
                                 	<option value="${locationId}">${locationName}</option>
								</html:select>                         
							</c:otherwise>
						</c:choose>	
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].locationRoom" 
		               		                          attributeEntry="${procedureLocationAttributes.locationRoom}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].studyGroupLocationDescription" 
		               		                          attributeEntry="${procedureLocationAttributes.studyGroupLocationDescription}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
					<td><div align=center>
                        <c:if test="${!readOnly}">
						    <c:set var="locationBean" value="${collectionProperty}[${status.index}]" />
                            <html:image property="methodToCall.deleteProcedureGroupLocation.${locationBean}.line${status.index}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
	                </div></td>
	            </tr>
        	</c:forEach>
        </table>
    </div>
</kul:innerTab>
