<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevels.attributes}" />
<c:set var="riskLevelAttributes" value="${DataDictionary.RiskLevel.attributes}" />
<c:set var="action" value="protocolProtocol" />
		<kul:subtab lookedUpCollectionName="riskLevel" width="100%" subTabTitle="Risk Levels">      
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.riskLevelCode" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.dateAssigned" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.dateUpdated" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.status" />
                <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProtocolRiskLevels.attributes.comments" />
 				<th>&nbsp</th>
            </tr>
        	<c:forEach var="riskLevel" items="${KualiForm.document.protocol.riskLevels}" varStatus="status">
        	<tr>
                <td align="left" valign="middle" width="25%">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].riskLevel.description" attributeEntry="${riskLevelAttributes.description}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].dateAssigned" attributeEntry="${protocolRiskLevelsAttributes.dateAssigned}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.riskLevels[${status.index}].dateUpdated" attributeEntry="${protocolRiskLevelsAttributes.dateUpdated}" readOnly="true" />
                </td>
                <td align="left" valign="middle">
                	<c:choose>
                	  <c:when test="${KualiForm.document.protocol.riskLevels[status.index].status == 'A'}" >
                	     Active
                	  </c:when>
                	  <c:otherwise>
                	      Inactive
                	  </c:otherwise>
                	</c:choose>
                </td>
                <td align="left" valign="middle">
                	<c:set var="comments" value="${KualiForm.document.protocol.riskLevels[status.index].comments}" />
                	<c:choose>
                	  <c:when test="${fn:length(comments) > 40}" >
                	  ${fn:substring(comments,0,39)}...
                	  </c:when>
                	  <c:otherwise>
                	      ${comments}
                	  </c:otherwise>
                	</c:choose>
					<html:hidden property="document.protocol.riskLevel[${status.index}].comments" />
                </td>
				<td class="infoline">
					<div align=center>
					<c:set var="commentFieldName" value="document.protocol.riskLevel[${status.index}].comments" />
                    <kra:expandedTextArea textAreaFieldName="${commentFieldName}" action="${action}" textAreaLabel="Risk Level Comments"  viewOnly="true"/>
					</div>
                </td>
            </tr>
            </c:forEach>
        </table>
       </kul:subtab> 
