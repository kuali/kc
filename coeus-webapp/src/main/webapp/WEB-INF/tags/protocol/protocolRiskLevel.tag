<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="action" required="true" %>
<%@ attribute name="protocolRiskLevelsAttributes" required="true" type="java.util.Map"%>
<%@ attribute name="riskLevelAttributes" required="true" type="java.util.Map"%>

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