<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<h3>
 	<span class="subhead-left">Status & Dates</span>
 	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolType" altText="help"/></span>
</h3>
<table cellpadding=0 cellspacing=0 summary="">
 	<tr>
	 	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}"/></div></th>
       	<td width="20%"><kul:htmlControlAttribute property="document.protocolList[0].protocolNumber" attributeEntry="${protocolAttributes.protocolNumber}" readOnly="true" />
       	      <html:hidden property="document.protocolList[0].protocolNumber" />
       	</td>
	 	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolStatusCode}"  /></div></th>
        <td width="20%">${KualiForm.document.protocolList[0].protocolStatus.description}&nbsp;</td>
 	</tr>
    <tr>
		<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.approvalDate}" /></div></th>
        <td width="20%"align="left" valign="middle">
          	<kul:htmlControlAttribute property="document.protocolList[0].approvalDate" attributeEntry="${protocolAttributes.approvalDate}" readOnly="true" />
        </td>
        <th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.lastApprovalDate}" /></div></th>
        <td width="20%"align="left" valign="middle">
          	<kul:htmlControlAttribute property="document.protocolList[0].lastApprovalDate" attributeEntry="${protocolAttributes.lastApprovalDate}" readOnly="true" />
        </td>
 	</tr>
    <tr>
       	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.submissionDate}"/></div></th>
        <td width="20%">
            <c:choose>
        	    <c:when test="${KualiForm.document.protocolList[0].submissionDate == null}">
                    Generated on Submission
                </c:when>
                <c:otherwise>
                    <kul:htmlControlAttribute property="document.protocolList[0].submissionDate" attributeEntry="${protocolAttributes.submissionDate}" readOnly="true" />
                </c:otherwise>    
            </c:choose> 
        </td>
        <th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.expirationDate}"/></div></th>
        <td width="20%"align="left" valign="middle">
          	<kul:htmlControlAttribute property="document.protocolList[0].expirationDate" attributeEntry="${protocolAttributes.expirationDate}" readOnly="true" />
        </td>
  	</tr>
</table>
