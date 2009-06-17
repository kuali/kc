<%--
 Copyright 2006-2009 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="readOnly" value="true" scope="request" /> 
<c:set var="inquiry" value="true" scope="request" /> 


<kul:documentPage
	showDocumentInfo="false"
	htmlFormAction="identityManagementPersonDocument"
	documentTypeName="IdentityManagementPersonDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0">
<div id="workarea">

	<kim:personOverview />
	<kim:personContact />
	<kim:personPrivacy />
	<kim:personMembership />
		

<kul:panelFooter /> 

		 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
	
	<tr>
	<td>
	<div align="center"><a href="javascript:window.close();"
		title="close this window"><img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" alt="close this window"></a></div>
    </td></tr>
</table>
</kul:documentPage>
