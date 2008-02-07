<%--
 Copyright 2005-2008 The Kuali Foundation.

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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:page lookup="true" 
          docTitle="Roles" 
          transactionalDocument="true" 
          htmlFormAction="proposalDevelopmentPermissions">
          
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<div align="center" style="margin:10px">
		<kra-pd:proposalDevelopmentPermissionsEditRoles />
		<kul:panelFooter />
				
		<div id="globalbuttons" class="globalbuttons">
			<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.setEditRoles" title="save" alt="save" />	
		</div>
	</div>
</kul:page>