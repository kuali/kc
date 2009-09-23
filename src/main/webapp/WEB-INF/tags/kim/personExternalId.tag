<%--
 Copyright 2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="personAttributes" value="${DataDictionary.IdentityManagementPersonDocument.attributes}" />

<kul:subtab lookedUpCollectionName="externalId" width="${tableWidth}" subTabTitle="External Identifiers">      
    <table cellpadding="0" cellspacing="0" summary="">
      	<tr>
      		<th width="50%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.taxId}"  /></div></th>
			 <td><kul:htmlControlAttribute property="document.taxId" attributeEntry="${personAttributes.taxId}" readOnly="${readOnly}" /></td>
		</tr>
		<tr>
      		<th width="50%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.univId}"  /></div></th>
			 <td><kul:htmlControlAttribute property="document.univId" attributeEntry="${personAttributes.univId}" readOnly="${readOnly}"  /></td>          	
      	</tr>                 
    </table>
</kul:subtab>
