<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevels.attributes}" />
		<kul:subtab lookedUpCollectionName="riskLevel" width="100%" subTabTitle="Risk Levels">      
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.riskLevelCode" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.dateAssigned" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.dateUpdated" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.status" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.comments" />
            </tr>
        	<c:forEach var="riskLevel" items="${KualiForm.document.protocol.riskLevels}" varStatus="status">
        	<tr>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].riskLevelCode" attributeEntry="${protocolRiskLevelsAttributes.riskLevelCode}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].dateAssigned" attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].dateUpdated" attributeEntry="${protocolRiskLevelsAttributes.dateUpdated}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].status" attributeEntry="${protocolRiskLevelsAttributes.status}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].comments" attributeEntry="${protocolRiskLevelsAttributes.comments}" readOnly="true" />
                </td>
            </tr>
            </c:forEach>
        </table>
       </kul:subtab> 
