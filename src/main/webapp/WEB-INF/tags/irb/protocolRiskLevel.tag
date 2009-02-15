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
          	    ${protocolRiskLevel.statusText}
            </td>
            <td>
                <c:set var="comments" value="${protocolRiskLevel.comments}" />
                    <table border="0" cellpadding="0" cellspacing="0" style="border:none" >
                        <tr>
                           <td align="left" style="border:none" >
                              <c:choose>
                	             <c:when test="${fn:length(comments) > commentDisplayLength}" >
                	                 ${fn:substring(comments,0,commentDisplayLength - 1)}...
                	              </c:when>
                	              <c:otherwise>
                	                  ${comments}
                	              </c:otherwise>
                              </c:choose>
                          </td>
                          <td style="border:none" >
				             <html:hidden property="${protocolRiskLevelPath}.comments" />
				             <c:if test="${!empty comments}">
				               <div  align="right">
                                 <kra:expandedTextArea textAreaFieldName="${protocolRiskLevelPath}.comments" action="${action}" textAreaLabel="Risk Level Comments"  viewOnly="true"/>
                               </div> 
                             </c:if>
                          </td>
				     </tr>
				</table>
            </td>
		</tr>
	</c:forEach>
</table>
