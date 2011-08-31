<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="negotiationAttributes" value="${DataDictionary.Negotiation.attributes}" />
<c:set var="action" value="negotiationNegotiation" />
<c:set var="className" value="org.kuali.kra.negotiations.document.NegotiationDocument" />
<c:set var="readOnly" value="${false}" scope="request" />

<kul:tab tabTitle="Negotiation" defaultOpen="true" tabErrorKey="document.negotiation.*" 
					auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
					
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Negotiation</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.negotiations.bo.Negotiation" altText="help"/></span>
        </h3>
		
		<table cellpadding="4" cellspacing="0" summary="">
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.negotiationId}" /></div></th>
                <td>
                	${KualiForm.document.negotiation.negotiationId}
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.documentNumber}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.negotiation.documentNumber" attributeEntry="${negotiationAttributes.documentNumber}" readOnly="${readOnly}"/>
                </td>
            </tr>
		</table>
	</div>	
</kul:tab>