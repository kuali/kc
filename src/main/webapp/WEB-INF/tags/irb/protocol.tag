<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="textAreaFieldName" value="document.protocol.title" />
<c:set var="action" value="protocol" />
<c:set var="className" value="org.kuali.kra.irb.document.ProtocolDocument" />

<kul:tab tabTitle="Protocol Document" defaultOpen="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}" /></div></th>
                <td>${KualiForm.document.protocol.protocolNumber}&nbsp;</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolTypeCode}" /></div></th>
                <td>
                      <kra:kraControlAttribute property="document.protocol.protocolTypeCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolTypeCode}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolStatusCode}" /></div></th>
                <td>
                      <kra:kraControlAttribute property="document.protocol.protocolStatusCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolStatusCode}" />
                </td>
<%-- 
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.billable}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.protocol.billable" attributeEntry="${protocolAttributes.billable}" />
           		</td>
--%>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.title" attributeEntry="${protocolAttributes.title}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolAttributes.title.label}" />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.description}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.description" attributeEntry="${protocolAttributes.description}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolAttributes.description.label}" />
                </td>
            </tr>
 --%>            
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.applicationDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.applicationDate" attributeEntry="${protocolAttributes.applicationDate}" datePicker="true" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.approvalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.approvalDate" attributeEntry="${protocolAttributes.approvalDate}" datePicker="true" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.expirationDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.expirationDate" attributeEntry="${protocolAttributes.expirationDate}" datePicker="true" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.lastApprovalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.lastApprovalDate" attributeEntry="${protocolAttributes.lastApprovalDate}" datePicker="true" />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.fdaApplicationNumber}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.fdaApplicationNumber" attributeEntry="${protocolAttributes.fdaApplicationNumber}" />
                </td>
            </tr>

            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.referenceNumber1}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.referenceNumber1" attributeEntry="${protocolAttributes.referenceNumber1}" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.referenceNumber2}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocol.referenceNumber2" attributeEntry="${protocolAttributes.referenceNumber2}" />
                </td>
            </tr>
 --%>              
        </table>
    </div>
</kul:tab>
