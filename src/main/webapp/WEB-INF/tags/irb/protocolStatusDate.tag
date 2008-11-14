<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
		<kul:subtab lookedUpCollectionName="statusAndDate" width="100%" subTabTitle="Status & Dates">      
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.protocolNumber}"/></div></th>
                <td width="20%">${KualiForm.document.protocolNumber}&nbsp;</td>
				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.protocolStatusCode}"  /></div></th>
                <td width="20%">${KualiForm.document.protocolStatus.description}&nbsp;</td>
            </tr>
        	<tr>
				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.approvalDate}" /></div></th>
                <td width="20%"align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.approvalDate" attributeEntry="${protocolDocumentAttributes.approvalDate}" readOnly="true" />
                </td>
           		<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.lastApprovalDate}" /></div></th>
                <td width="20%"align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.lastApprovalDate" attributeEntry="${protocolDocumentAttributes.lastApprovalDate}" readOnly="true" />
                </td>
            </tr>
            <tr>
                <!-- submission date is from protocol_submission ? This field is pending -->
                <th width="30%"><div align="right">Submission Date:</div></th>
                <td width="20%">Generated on Submission</td>
                <th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.expirationDate}"/></div></th>
                <td width="20%"align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.expirationDate" attributeEntry="${protocolDocumentAttributes.expirationDate}" readOnly="true" />
                </td>
            </tr>
        </table>
       </kul:subtab> 
