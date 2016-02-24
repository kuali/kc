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

<c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />
<c:set var="riskLevelAttributes" value="${DataDictionary.RiskLevel.attributes}" />
<c:set var="action" value="protocolProtocol" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_RISK_LEVEL_COMMENT_LENGTH%>" />

<kul:tab tabTitle="Risk Levels" defaultOpen="false" tabErrorKey="" >
    <div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Risk Levels</span>
		    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.RiskLevel" altText="help"/></span>
		</h3>
		
		<table cellpadding=0 cellspacing=0 summary="">
		    <tbody>
				<tr>
				    <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" /> 
			       	<kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.riskLevelCode}" />
			        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" />
			        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.dateInactivated}" />
			        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.status}" />
			        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.comments}" />
				</tr>
			    <c:forEach var="protocolRiskLevel" items="${KualiForm.document.protocol.protocolRiskLevels}" varStatus="status">
					<tr>
					    <th class="infoline">
	                        <c:out value="${status.index + 1}" />
	                    </th>
			           	<td align="left" valign="middle">
			           	    <div align="left">
			             	    <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].riskLevel.description" 
			             	                              attributeEntry="${riskLevelAttributes.description}" readOnly="true" styleClass="fixed-size-200-select" />
			                </div>
			            </td>
			            <td align="left" valign="middle">
			                <div align="left">
			             	    <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateAssigned" 
			             	                              attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" readOnly="true" />
			                </div>
			            </td>
			            <td align="left" valign="middle">
			                <div align="left">
			             	    <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].dateInactivated" 
			             	                              attributeEntry="${protocolRiskLevelsAttributes.dateInactivated}" readOnly="true" />
			                </div>
			            </td>
			          	<td align="left" valign="middle">
			          	    <div align="left">
			          	       <kul:htmlControlAttribute property="document.protocol.protocolRiskLevels[${status.index}].statusText" 
			          	                                 attributeEntry="${protocolRiskLevelsAttributes.status}" readOnly="true" />
			                </div>
			            </td>
			            <td align="left" valign="middle">
			                <div align="left">
			                    <kra:truncateComment textAreaFieldName="document.protocol.protocolRiskLevels[${status.index}].comments" action="${action}" 
			                                         textAreaLabel="${protocolRiskLevelsAttributes.comments.label}" textValue="${protocolRiskLevel.comments}"  
			                                         displaySize="${commentDisplayLength}"/>
			                </div>
			            </td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
    </div>
</kul:tab>
