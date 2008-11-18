<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="protocol" />
<c:set var="className" value="org.kuali.kra.irb.document.ProtocolDocument" />

<kul:tab tabTitle="Additional Information" defaultOpen="false" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Additional Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">

	       <tr>
                <th><div align="right" width="52%"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" /></div></th>
                <td align="left" valign="middle" width="25%" >
                	<kul:htmlControlAttribute property="document.fdaApplicationNumber" attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" />
                </td>
                <th><div align="right" width="5%"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.billable}" /></div></th>
                <td align="left" valign="middle" width="18%">
                	<kul:htmlControlAttribute property="document.billable" attributeEntry="${protocolDocumentAttributes.billable}" />
                </td>
            </tr>


            <tr>
                <th><div align="right">${KualiForm.protocolParameters['irb.protocol.referenceID1'].parameterValue}:</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber1" attributeEntry="${protocolDocumentAttributes.referenceNumber1}" />
                </td>
                <th><div align="right">${KualiForm.protocolParameters['irb.protocol.referenceID2'].parameterValue}:</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber2" attributeEntry="${protocolDocumentAttributes.referenceNumber2}" />
                </td>
            </tr>

            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.description}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.description" attributeEntry="${protocolDocumentAttributes.description}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolDocumentAttributes.description.label}" />
                </td>
            </tr>

       </table>
    </div>
</kul:tab>
