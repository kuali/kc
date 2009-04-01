<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />
<c:set var="riskLevelAttributes" value="${DataDictionary.RiskLevel.attributes}" />
<c:set var="action" value="protocolProtocol" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_RISK_LEVEL_COMMENT_LENGTH%>" />

<h3>
	<span class="subhead-left">Risk Levels</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.RiskLevel" altText="help"/></span>
</h3>
<table cellpadding=0 cellspacing=0 summary="">
	<tr>
       	<kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.riskLevelCode}" />
        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" />
        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.dateUpdated}" />
        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.status}" />
        <kul:htmlAttributeHeaderCell attributeEntry="${protocolRiskLevelsAttributes.comments}" />
	</tr>
    <c:forEach var="protocolRiskLevel" items="${KualiForm.document.protocolList[0].protocolRiskLevels}" varStatus="status">
      	<c:set var="protocolRiskLevelPath" value="document.protocolList[0].protocolRiskLevels[${status.index}]" />
		<tr>
           	<td align="left" valign="middle" width="25%">
             	<kul:htmlControlAttribute property="${protocolRiskLevelPath}.riskLevel.description" attributeEntry="${riskLevelAttributes.description}" readOnly="true" />
            </td>
            <td align="left" valign="middle">
             	<kul:htmlControlAttribute property="${protocolRiskLevelPath}.dateAssigned" attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" readOnly="true" />
            </td>
            <td align="left" valign="middle">
             	<kul:htmlControlAttribute property="${protocolRiskLevelPath}.dateUpdated" attributeEntry="${protocolRiskLevelsAttributes.dateUpdated}" readOnly="true" />
            </td>
          	<td align="left" valign="middle">
          	    ${protocolRiskLevel.statusText}
            </td>
            <td>
                <c:set var="comments" value="${protocolRiskLevel.comments}" />
				             <html:hidden property="${protocolRiskLevelPath}.comments" />
                                 <kra:truncateComment textAreaFieldName="${protocolRiskLevelPath}.comments" action="${action}" textAreaLabel="Risk Level Comments"  textValue="${comments}"  displaySize="${commentDisplayLength}"/>
            </td>
		</tr>
	</c:forEach>
</table>
