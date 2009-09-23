<%--
 Copyright 2008-2009 The Kuali Foundation
 
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

<kul:tab tabTitle="Overview" defaultOpen="true" transparentBackground="${!KualiForm.hasWorkflowDocument}" tabErrorKey="document.pr*,document.tax*,document.univ*,document.active,document.affiliations*">

	<div class="tab-container" align="center">
		<table cellpadding="0" cellspacing="0" summary=""> 
		 	<tr>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.entityId}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.entityId" attributeEntry="${personAttributes.entityId}" readOnly="true" /></td>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.principalId}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.principalId" attributeEntry="${personAttributes.principalId}" readOnly="true" /></td>
		 	</tr>
			<tr>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.principalName}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.principalName" attributeEntry="${personAttributes.principalName}" readOnly="${readOnlyEntity}" /></td>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.taxId}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.taxId" attributeEntry="${personAttributes.taxId}" readOnly="${readOnlyEntity}" /></td>
		 	</tr>
		 	<tr>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.password}" /></div></th>
		 		<td>
			 		<c:if test="${not readOnlyEntity}">
			 		    <html:password property="document.password" />
			 		</c:if>
		 		</td>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.active}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.active" attributeEntry="${personAttributes.active}" readOnly="${readOnlyEntity}" /></td>
		 	</tr>
		</table> 
		<kim:personAffln />		
	</div>
</kul:tab>

