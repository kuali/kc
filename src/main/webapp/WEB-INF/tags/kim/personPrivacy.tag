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

<c:set var="docPrivacyAttributes" value="${DataDictionary.PersonDocumentPrivacy.attributes}" />

<c:set var="canModifyPrivacyPreferences" scope="request" value="${KualiForm.canOverrideEntityPrivacyPreferences && !readOnly}" />

<kul:tab tabTitle="Privacy Preferences" defaultOpen="false" tabErrorKey="document.privacy*">
	<div class="tab-container" align="center">
		<table cellpadding="0" cellspacing="0" summary=""> 
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressName}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressName" attributeEntry="${docPrivacyAttributes.suppressName}" readOnly="${!canModifyPrivacyPreferences}" /></div></td>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressAddress}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressAddress" attributeEntry="${docPrivacyAttributes.suppressAddress}" readOnly="${!canModifyPrivacyPreferences}" /></div></td>
	 		</tr>
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressPersonal}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressPersonal" attributeEntry="${docPrivacyAttributes.suppressPersonal}" readOnly="${!canModifyPrivacyPreferences}" /></div></td>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressEmail}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressEmail" attributeEntry="${docPrivacyAttributes.suppressEmail}" readOnly="${!canModifyPrivacyPreferences}" /></div></td>
	 		</tr>
	 		<tr>
  				<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${docPrivacyAttributes.suppressPhone}"  /></div></th>
		 		<td width="20%"><div align="center"><kul:htmlControlAttribute property="document.privacy.suppressPhone" attributeEntry="${docPrivacyAttributes.suppressPhone}" readOnly="${!canModifyPrivacyPreferences}" /></div></td>
		 		<th></th>
		 		<td></td>
	 		</tr>
		</table> 		
	</div>
</kul:tab>
