<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<kul:page lookup="true" 
          docTitle="Roles" 
          transactionalDocument="true" 
          htmlFormAction="proposalDevelopmentPermissions">
          
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<script>
	    var propRoleStates = new Array();
	    var rs;
	    var cnt = 0;
	    <c:forEach var="roleState" items="${KualiForm.proposalUserEditRoles.roleStates}" varStatus="status">
           rs = new PermissionsRoleState('${roleState.name}', '${roleState.name}', '${roleState.state}');
           propRoleStates[propRoleStates.length] = rs;
           if (rs.getState().toLowerCase() == 'true') {
               cnt++;
           }
        </c:forEach>
        if (cnt == 0) {
            propRoleStates[propRoleStates.length] = new PermissionsRoleState('unassigned', 'unassigned', 'true');
        }
        
		updateEditRoles(${KualiForm.proposalUserEditRoles.lineNum}, propRoleStates, 'unassigned');
	</script>
	
</kul:page>
