<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script language="javascript" src="scripts/kuali_application.js"></script>
<script>
	var roleStates = new Array();
	var rs;
	var hasRole = false;
	<c:forEach var="roleState" items="${KualiForm.permissionsHelper.editRoles.roleStates}" varStatus="status">
        rs = new PermissionsRoleState('${roleState.role.name}', '${roleState.role.displayName}', '${roleState.state}');
        roleStates[roleStates.length] = rs;
        if (rs.getState().toLowerCase() == 'true') {
            hasRole = true;
        }
    </c:forEach>
    if (!hasRole && ${KualiForm.permissionsHelper.unassignedRoleName != null}) {
        roleStates[roleStates.length] = new PermissionsRoleState('${KualiForm.permissionsHelper.unassignedRoleName}', '${KualiForm.permissionsHelper.unassignedRoleDisplayName}', 'true');
    }
        
	updateEditRoles(${KualiForm.permissionsHelper.editRoles.lineNum}, roleStates, '${KualiForm.permissionsHelper.unassignedRoleDisplayName}');
</script>
