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

<%@ attribute name="bean" required="true" type="org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="taskName" required="true" %>

<c:set var="attributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />

<kra:permission value="${KualiForm.actionHelper.canEnterRiskLevel}">

<kul:innerTab tabTitle="Enter Risk Level" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.protocolApproveBean.protocolRiskLevelBean.*,document.protocol.protocolRiskLevels*">
   
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            
	        <%-- Header --%>
	        <tr>
	            <th><div align="left">&nbsp;</div></th> 
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.riskLevelCode}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateAssigned}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.dateInactivated}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.status}" />
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributes.comments}" />
	            <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
	        </tr>
                
            <%-- New data --%>                
            <tr> 
                <th class="infoline">
                    <c:out value="Add:" />
                </th>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.riskLevelCode" attributeEntry="${attributes.riskLevelCode}" styleClass="fixed-size-200-select" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.dateAssigned" attributeEntry="${attributes.dateAssigned}" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.dateInactivated" attributeEntry="${attributes.dateInactivated}" readOnly="true" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.status" attributeEntry="${attributes.status}" readOnly="true" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="${property}.newProtocolRiskLevel.comments" attributeEntry="${attributes.comments}" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="center">
                        <html:image property="methodToCall.addRiskLevel.taskName${taskName}.anchor${tabKey}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                    </div>
                </td>
            </tr>
            
            <%-- Existing data --%>
			<c:forEach var="protocolRiskLevel" items="${KualiForm.document.protocol.protocolRiskLevels}" varStatus="status">
			    <c:set var="updateOnly" value="${protocolRiskLevel.persisted}" />
			    <tr>
			         <th class="infoline">
			             ${status.index + 1}
			         </th>
                    <td align="left" valign="middle">
			             <div align="left">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].riskLevelCode" 
			                                           attributeEntry="${attributes.riskLevelCode}" readOnly="true" />
			             </div>
			        </td>
			        <td align="left" valign="middle">
			             <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateAssigned" 
			                                           attributeEntry="${attributes.dateAssigned}" readOnly="true" />
			             </div>
			        </td>
                    <td align="left" valign="middle">
			             <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateInactivated" 
			                                           attributeEntry="${attributes.dateInactivated}" readOnly="${!protocolRiskLevel.persisted}" />
                         </div>
                    </td>
			        <td align="left" valign="middle">
			            <div align="center">
			                 <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].status" 
			                                           attributeEntry="${attributes.status}" readOnly="true" />
			            </div>
			        </td>
			        <td align="left" valign="middle">
			            <div align="left">
			                 <%--<kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].comments" 
			                                           attributeEntry="${attributes.comments}" readOnly="true" />--%>
			                 <kra:truncateComment textAreaFieldName="document.protocol.protocolRiskLevels[${status.index}].comments" action="protocolProtocolActions" textAreaLabel="${attributes.comments.label}" textValue="${KualiForm.document.protocolList[0].protocolRiskLevels[status.index].comments}" displaySize="200"/>
			            </div>
			        </td>
			        <td align="left" valign="middle">
			            <div align="center">
			                <c:choose>
				                <c:when test="${!protocolRiskLevel.persisted}">
					                <html:image property="methodToCall.deleteRiskLevel.taskName${taskName}.line${status.index}.anchor${tabKey}"
					                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
				                </c:when>
				                <c:otherwise>
				                    <html:image property="methodToCall.updateRiskLevel.taskName${taskName}.line${status.index}.anchor${tabKey}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' styleClass="tinybutton"/>
                                </c:otherwise>
				            </c:choose>
			            </div>
			        </td>
			    </tr>
			</c:forEach>
        </table>       
    
</kul:innerTab>

</kra:permission>
