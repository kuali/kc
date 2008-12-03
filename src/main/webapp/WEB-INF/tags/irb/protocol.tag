<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
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
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.protocolNumber}" /></div></th>
                <td>${KualiForm.document.protocolNumber}&nbsp;</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.protocolTypeCode}" /></div></th>
                <td>
                      <kra:kraControlAttribute property="document.protocolTypeCode" readOnly="${readOnly}" attributeEntry="${protocolDocumentAttributes.protocolTypeCode}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.protocolStatusCode}" /></div></th>
                <td>
                      <kra:kraControlAttribute property="document.protocolStatusCode" readOnly="${readOnly}" attributeEntry="${protocolDocumentAttributes.protocolStatusCode}" />
                </td>
<%-- 
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.billable}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.billable" attributeEntry="${protocolDocumentAttributes.billable}" />
           		</td>
--%>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.title" attributeEntry="${protocolDocumentAttributes.title}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolDocumentAttributes.title.label}" />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.description}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.description" attributeEntry="${protocolDocumentAttributes.description}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolDocumentAttributes.description.label}" />
                </td>
            </tr>
 --%>            
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.applicationDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.applicationDate" attributeEntry="${protocolDocumentAttributes.applicationDate}" datePicker="true" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.approvalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.approvalDate" attributeEntry="${protocolDocumentAttributes.approvalDate}" datePicker="true" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.expirationDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.expirationDate" attributeEntry="${protocolDocumentAttributes.expirationDate}" datePicker="true" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.lastApprovalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.lastApprovalDate" attributeEntry="${protocolDocumentAttributes.lastApprovalDate}" datePicker="true" />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.fdaApplicationNumber" attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" />
                </td>
            </tr>

            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.referenceNumber1}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber1" attributeEntry="${protocolDocumentAttributes.referenceNumber1}" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.referenceNumber2}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber2" attributeEntry="${protocolDocumentAttributes.referenceNumber2}" />
                </td>
            </tr>
 --%>              
        </table>
    </div>
</kul:tab>
