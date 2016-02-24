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
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />

<%@ attribute name="node" required="true" type="org.kuali.coeus.common.framework.medusa.MedusaNode"%>
  <table style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;">
    <tr>
      <th colspan="4" style="border-style: solid; text-align: left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Protocol ${node.bo.protocolNumber}</th>
    </tr>
    <tr>
      <td style="text-align: center;" colspan="4">
	  <a href="${ConfigProperties.application.url}/iacucProtocolProtocol.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.protocolDocument.documentNumber}&medusaOpenedDoc=true"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Protocol" 
	          alt="Open Protocol" style="border: medium none ;" 
	          src="static/images/tinybutton-openprotocol.gif"/>
	  </a>      	  
      </td>
    </tr>
    <tr>
      <th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Summary</th>
    </tr>
    <tr>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}"/></th>
      <td><c:out value="${node.bo.protocolNumber}"/></td>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolStatusCode}"  /></th>
      <td><c:out value="${node.bo.protocolStatus.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolTypeCode}" /></th>
      <td colspan="3"><c:out value="${node.bo.protocolType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></th>
      <td colspan="3"><c:out value="${node.bo.title}"/></td>
    </tr>    
    <tr>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.approvalDate}" /></th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.approvalDate}"/></td>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.lastApprovalDate}" /></th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.lastApprovalDate}"/></td>      
    </tr>
    <tr>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.initialSubmissionDate}"/></th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.initialSubmissionDate}"/></td>
      <th style="text-align: right;"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.expirationDate}"/></th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.expirationDate}"/></td>      
    </tr>    
    <tr>
      <th style="text-align: right;">Reference No 1:</th>
      <td colspan="3"><c:out value="${node.bo.referenceNumber1}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Reference No 2:</th>
      <td colspan="3"><c:out value="${node.bo.referenceNumber2}"/></td>
    </tr>    
  </table>
