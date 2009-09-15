<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="docPrivacyAttributes" value="${DataDictionary.PersonDocumentPrivacy.attributes}" />

<kul:tab tabTitle="Privacy Preferences" defaultOpen="false" tabErrorKey="document.privacy*">
	<div class="tab-container" align="center">
		<table cellpadding="0" cellspacing="0" summary=""> 
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressName}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressName" attributeEntry="${docPrivacyAttributes.suppressName}" readOnly="${readOnly}" /></div></td>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressAddress}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressAddress" attributeEntry="${docPrivacyAttributes.suppressAddress}" readOnly="${readOnly}" /></div></td>
	 		</tr>
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressPersonal}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressPersonal" attributeEntry="${docPrivacyAttributes.suppressPersonal}" readOnly="${readOnly}" /></div></td>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressEmail}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressEmail" attributeEntry="${docPrivacyAttributes.suppressEmail}" readOnly="${readOnly}" /></div></td>
	 		</tr>
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressPhone}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressPhone" attributeEntry="${docPrivacyAttributes.suppressPhone}" readOnly="${readOnly}" /></div></td>
		 		<th></th>
		 		<td></td>
	 		</tr>
		</table> 		
	</div>
</kul:tab>

