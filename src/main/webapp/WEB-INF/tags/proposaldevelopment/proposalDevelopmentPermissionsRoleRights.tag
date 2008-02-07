<%--
 Copyright 2007 The Kuali Foundation.
 
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

<kul:tabTop defaultOpen="true" tabTitle="Roles">
	<kra-pd:roleRights roleName="Aggregator" permissions="${KualiForm.aggregatorPermissions}" />
	<kra-pd:roleRights roleName="Budget Creator" permissions="${KualiForm.budgetCreatorPermissions}" />
	<kra-pd:roleRights roleName="Narrative Writer" permissions="${KualiForm.narrativeWriterPermissions}" />
	<kra-pd:roleRights roleName="Viewer" permissions="${KualiForm.viewerPermissions}" />
</kul:tabTop>
