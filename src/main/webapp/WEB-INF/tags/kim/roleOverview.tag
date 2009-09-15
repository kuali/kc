<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="roleAttributes" value="${DataDictionary.RoleImpl.attributes}" />
<c:set var="roleTypeAttributes" value="${DataDictionary.KimTypeImpl.attributes}" />

<kul:tab tabTitle="Overview" defaultOpen="true" transparentBackground="${inquiry}" tabErrorKey="document.role*,document.active">

<div class="tab-container" align="center">
	<table cellpadding="0" cellspacing="0" summary=""> 
	 	<tr>
			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${roleAttributes.roleId}"  /></div></th>
	 		<td><kul:htmlControlAttribute property="document.roleId" attributeEntry="${roleAttributes.roleId}" readOnly="true" /></td>
    		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${roleTypeAttributes.name}"  /></div></th>
	 		<td><kul:htmlControlAttribute property="document.roleTypeName" attributeEntry="${roleTypeAttributes.name}" readOnly="true" /></td>
	 		<html:hidden property="document.roleTypeId" />
	 	</tr>
	 	<tr>
    		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${roleAttributes.namespaceCode}"  /></div></th>
	 		<td>
	 			<kul:htmlControlAttribute property="document.roleNamespace" attributeEntry="${roleAttributes.namespaceCode}" readOnly="${readOnly || editingDocument}" onchange="namespaceChanged( this.form );" />
	 		    <noscript>
	 		        <input type="image" tabindex="32768" name="methodToCall.changeNamespace" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-refresh.gif" class="tinybutton" title="Click to refresh the page after changing the namespace." alt="Click to refresh the page after changing the namespace." />
	 		    </noscript>
	 		</td>
    		<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${roleAttributes.roleName}"  /></div></th>
	 		<td><kul:htmlControlAttribute property="document.roleName" attributeEntry="${roleAttributes.roleName}" readOnly="${readOnly || editingDocument}" /></td>
	 	</tr>
	 	<tr>
			<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${roleAttributes.active}"  /></div></th>
	 		<td><kul:htmlControlAttribute property="document.active" attributeEntry="${roleAttributes.active}" readOnly="${readOnly}" /></td>
	 		<th></th>
	 		<td></td>
	 	</tr>
	</table> 

</div>
</kul:tab>