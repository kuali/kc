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

    <tr>
        <kul:htmlAttributeHeaderCell literalLabel="${KualiForm.batchCorrespondence.daysToEventUiText}" scope="col" align="center" width="30%" />
        <kul:htmlAttributeHeaderCell literalLabel="Take this Action" scope="col" align="center" width="50%" />
        <c:if test="${!readOnly}">
            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" width="20%" />
        </c:if>
    </tr>
    
    <%-- New Details --%>
    <c:if test="${!readOnly}">
    	<tbody class="addline">
        <tr>
            <td class="infoline nobord">
                <div align="center" style="font-weight:bold;">
                    <kul:htmlControlAttribute property="newBatchCorrespondenceDetail.daysToEvent"
                                              attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.daysToEvent}" />
                </div>
            </td>
            <td class="infoline nobord">
                <div align="left">
                    <kul:htmlControlAttribute property="newBatchCorrespondenceDetail.protoCorrespTypeCode"
                                              attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.protoCorrespTypeCode}" />
                </div>
            </td>
            <td class="infoline nobord">
                <div align="center">
                <html:image property="methodToCall.addBatchCorrespondenceDetail" 
                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" 
                    title="Add Correspondence Template" 
                    alt="Add Correspondence Template" 
                    styleClass="tinybutton addButton" />
                </div>
            </td>
        </tr>
        </tbody>
    </c:if>
    <%-- New Details --%>
    
    <%-- Existing Details --%> 
    <c:forEach items="${KualiForm.batchCorrespondence.batchCorrespondenceDetails}" var="details" varStatus="status">
        <tr>
            <td>
                <div align="center">
                   ${details.daysToEvent}
                </div>
            </td>
            <td>
                <div align="left">
                    ${details.protocolCorrespondenceType.description}
                </div>
            </td>
            <c:if test="${!readOnly}">
                <td>
                    <div align="center">
                        <html:image property="methodToCall.deleteBatchCorrespondenceDetail.batchCorrespondenceDetail[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif" 
                            title="delete" 
                            alt="delete" 
                            styleClass="tinybutton" />
                    </div>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    <%-- Existing Details --%>
    
    <%-- Final IRB Notification Action --%>
    <tr>
        <td>
            <c:if test="${KualiForm.batchCorrespondence.sendCorrespondence == 'BEFORE'}">
                <div align="center" style="font-weight:bold;">
                    0
                </div>
            </c:if>
            <c:if test="${KualiForm.batchCorrespondence.sendCorrespondence == 'AFTER'}">
                <div align="center" style="font-weight:bold;">
                        <kul:htmlControlAttribute property="batchCorrespondence.finalActionDay"
                                                  attributeEntry="${DataDictionary.BatchCorrespondence.attributes.finalActionDay}" />          
                </div>
            </c:if>
        </td>
        <td>
            <div align="left">
                <kul:htmlControlAttribute property="batchCorrespondence.finalActionCorrespType"
                                          attributeEntry="${DataDictionary.BatchCorrespondence.attributes.finalActionCorrespType}"
                                          readOnlyAlternateDisplay="${KualiForm.batchCorrespondence.protocolCorrespondenceType.description}" />                                          
                <c:if test="${readOnly && not empty KualiForm.batchCorrespondence.finalActionCorrespType && not empty KualiForm.batchCorrespondence.finalActionTypeCode}">
                    &nbsp; | &nbsp;  
                </c:if>
                <html:select property="batchCorrespondence.finalActionTypeCode">
                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.irb.actions.ProtocolFinalActionTypeValuesFinder', paramMap)}" var="option">   
	                    <c:choose>                      
	                        <c:when test="${KualiForm.batchCorrespondence.finalActionTypeCode eq option.key}">
	                            <option value="${option.key}" selected="selected">${option.value}</option>
	                        </c:when>
	                        <c:otherwise>                               
	                            <option value="${option.key}">${option.value}</option>
	                        </c:otherwise>
	                    </c:choose>                                                
                    </c:forEach>
                </html:select>
                
            </div>
        </td>
        <c:if test="${!readOnly}">
            <td>
            </td>
        </c:if>
    </tr>
    <%-- Final --%>    
