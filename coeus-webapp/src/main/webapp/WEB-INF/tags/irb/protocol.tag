<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="textAreaFieldName" value="document.protocolList[0].title" />
<c:set var="action" value="protocol" />
<c:set var="className" value="${KualiForm.document.class.name}" />

<kul:tab tabTitle="Protocol Document" defaultOpen="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}" /></div></th>
<%--                <td>${KualiForm.document.protocolList[0].protocolNumber}&nbsp;</td>
--%>				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolTypeCode}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.protocolList[0].protocolTypeCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolTypeCode}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolStatusCode}" /></div></th>
                <td>
                      <kul:htmlControlAttribute property="document.protocolList[0].protocolStatusCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolStatusCode}" />
                </td>
<%-- 
           		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.billable}" /></div></th>
           		<td>
           			<kul:htmlControlAttribute property="document.protocolList[0].billable" attributeEntry="${protocolAttributes.billable}" />
           		</td>
--%>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].title" attributeEntry="${protocolAttributes.title}" />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.description}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].description" attributeEntry="${protocolAttributes.description}" />
                </td>
            </tr>
 --%>            
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.submissionDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].submissionDate" attributeEntry="${protocolAttributes.submissionDate}"  />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.approvalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].approvalDate" attributeEntry="${protocolAttributes.approvalDate}"  />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.expirationDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].expirationDate" attributeEntry="${protocolAttributes.expirationDate}"  />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.lastApprovalDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].lastApprovalDate" attributeEntry="${protocolAttributes.lastApprovalDate}"  />
                </td>
            </tr>
<%--             
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.fdaApplicationNumber}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].fdaApplicationNumber" attributeEntry="${protocolAttributes.fdaApplicationNumber}" />
                </td>
            </tr>

            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.referenceNumber1}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].referenceNumber1" attributeEntry="${protocolAttributes.referenceNumber1}" />
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.referenceNumber2}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.protocolList[0].referenceNumber2" attributeEntry="${protocolAttributes.referenceNumber2}" />
                </td>
            </tr>
 --%>              
        </table>
    </div>
</kul:tab>
