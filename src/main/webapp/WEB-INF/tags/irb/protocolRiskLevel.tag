<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" />
<c:set var="riskLevelAttributes" value="${DataDictionary.RiskLevel.attributes}" />
<c:set var="action" value="protocolProtocol" />
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
		<th>&nbsp</th>
	</tr>
    <c:forEach var="protocolRiskLevel" items="${KualiForm.document.protocol.protocolRiskLevels}" varStatus="status">
      	<c:set var="protocolRiskLevelPath" value="document.protocol.protocolRiskLevels[${status.index}]" />
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
             	<c:choose>
             	  	<c:when test="${protocolRiskLevel.status == 'A'}" >
             	      	Active
             	  	</c:when>
             	  	<c:otherwise>
             	      	Inactive
             	  	</c:otherwise>
             	</c:choose>
            </td>
            <td align="left" valign="middle">
                <c:set var="comments" value="${protocolRiskLevel.comments}" />
                <c:choose>
                	  <c:when test="${fn:length(comments) > 40}" >
                	      ${fn:substring(comments,0,39)}...
                	  </c:when>
                	  <c:otherwise>
                	      ${comments}
                	  </c:otherwise>
                </c:choose>
				<html:hidden property="${protocolRiskLevelPath}.comments" />
            </td>
			<td class="infoline">
				<div align=center>
                    <kra:expandedTextArea textAreaFieldName="${protocolRiskLevelPath}.comments" action="${action}" textAreaLabel="Risk Level Comments"  viewOnly="true"/>
				</div>
            </td>
		</tr>
	</c:forEach>
</table>
