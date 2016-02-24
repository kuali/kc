<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
