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

<kul:tab tabTitle="Contact" defaultOpen="false" tabErrorKey="document.names*,document.phones*,newName.*,newPhone.*,document.addrs*,newAddr.*,document.emails*,newEmail.*">
	<div class="tab-container" align="center">
		<kim:personName />
		<kim:personAddress />
		<kim:personPhone />
		<kim:personEmail />		
	</div>
</kul:tab>
