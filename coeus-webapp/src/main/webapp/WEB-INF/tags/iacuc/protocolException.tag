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
<%@ attribute name="protocolExceptionAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Protocol Exception attributes" %>
<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Protocol Exception" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Protocol Exception" %>
<%@ attribute name="action" required="true" 
              description="The name of the action class" %>

<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.IACUC_PROTOCOL_EXCEPTION_DESC_LENGTH%>" />
<c:set var="modifyPermission" value="${KualiForm.iacucProtocolExceptionHelper.modifyProtocolException}" />
<c:set var="readOnly" value="${!modifyPermission}" />


<kul:tab tabTitle="Protocol Exceptions" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="newIacucProtocolException*,iacucProtocolExceptionHelper.newIacucProtocolException*,${collectionProperty}*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Protocol Exceptions</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="protocolExceptionTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolExceptionAttributes.exceptionCategoryCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolExceptionAttributes.speciesCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolExceptionAttributes.exceptionDescription}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolExceptionAttributes.exceptionCount}" noColon="true" /></div></th>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     

        	<kra:permission value="${modifyPermission}">         
        		<tbody class="addline">   
                <tr>
	                <c:set var="textAreaFieldName" value="iacucProtocolExceptionHelper.newIacucProtocolException.exceptionDescription" />
					<th class="infoline">
						Add:
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolExceptionHelper.newIacucProtocolException.exceptionCategoryCode" 
		               		                          attributeEntry="${protocolExceptionAttributes.exceptionCategoryCode}" 
		                                              styleClass="fixed-size-500-select"
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolExceptionHelper.newIacucProtocolException.speciesCode" 
		               		                          attributeEntry="${protocolExceptionAttributes.speciesCode}" 
		                                              styleClass="fixed-size-500-select"
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolExceptionHelper.newIacucProtocolException.exceptionDescription" 
		               		                          attributeEntry="${protocolExceptionAttributes.exceptionDescription}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="iacucProtocolExceptionHelper.newIacucProtocolException.exceptionCount" 
		               		                          attributeEntry="${protocolExceptionAttributes.exceptionCount}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.addProtocolException.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>
	                	</div>
	                </td>
	            </tr>
	            </tbody>
	        </kra:permission>          
            
        	<c:forEach var="protocolException" items="${collectionReference}" varStatus="status">
                <tr>
	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].exceptionDescription" />
					<th class="infoline">
					   <c:out value="${status.index+1}" />
					</th>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].exceptionCategoryCode" 
		                                              attributeEntry="${protocolExceptionAttributes.exceptionCategoryCode}"  
		                                              readOnly="${readOnly}"
		                                              styleClass="fixed-size-500-select"
		                                              readOnlyAlternateDisplay="${iacucProtocolException.iacucExceptionCategory.exceptionCategoryDesc}" 
		                                              />
	            	</div>
		            </td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].speciesCode" 
		                                              attributeEntry="${protocolExceptionAttributes.speciesCode}"  
		                                              readOnly="${readOnly}"
		                                              styleClass="fixed-size-500-select"
		                                              readOnlyAlternateDisplay="${iacucProtocolException.iacucSpecies.speciesName}" 
		                                              />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
	                        <c:choose>
	                            <c:when test="${!readOnly}">
	                                <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].exceptionDescription" 
	                                                          attributeEntry="${protocolExceptionAttributes.exceptionDescription}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="${collectionProperty}[${status.index}].exceptionDescription" action="${action}" 
		                                                 textAreaLabel="${protocolExceptionAttributes.exceptionDescription.label}" textValue="${protocolException.exceptionDescription}"  
		                                                 displaySize="${commentDisplayLength}"/>
	                            </c:otherwise>
	                        </c:choose>
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="${collectionProperty}[${status.index}].exceptionCount" 
		               		                          attributeEntry="${protocolExceptionAttributes.exceptionCount}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
                    <c:choose>
                        <c:when test="${!readOnly}">
                        <td align="center" valign="middle">
                            <div align="center">
                            <nobr>
                                <html:image property="methodToCall.deleteProtocolException.line${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                            </nobr>
                            </div>
                        </td>
                        </c:when>
                        <c:otherwise>
                            <td>&nbsp;</td>
                        </c:otherwise>
                    </c:choose>
	            </tr>
        	</c:forEach>
        </table>
    </div> 
</kul:tab>
