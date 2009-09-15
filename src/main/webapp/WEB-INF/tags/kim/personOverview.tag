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
		 		<td><kul:htmlControlAttribute property="document.principalName" attributeEntry="${personAttributes.principalName}" readOnly="${readOnly}" /></td>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.taxId}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.taxId" attributeEntry="${personAttributes.taxId}" readOnly="${readOnly}" /></td>
		 	</tr>
		 	<tr>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.password}" /></div></th>
		 		<td>
			 		<c:if test="${not readOnly}">
			 		    <html:password property="document.password" />
			 		</c:if>
		 		</td>
     			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${personAttributes.active}"  /></div></th>
		 		<td><kul:htmlControlAttribute property="document.active" attributeEntry="${personAttributes.active}" readOnly="${readOnly}" /></td>
		 	</tr>
		</table> 
		<kim:personAffln />		
	</div>
</kul:tab>

