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

<c:set var="action" value="proposalDevelopmentPermissions" />
<c:set var="name" value="proposalDevelopment" />

<kul:tabTop tabTitle="Assigned Roles" defaultOpen="true"  
            tabErrorKey="document.developmentProposalList[0].proposalPermissionRole">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Assigned Roles</span>
    		<span class="subhead-right"><html:image property="methodToCall.getPermissionsRoleRights"
                	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewpermissions.gif' styleClass="tinybutton" alt="View Rights"
                	 onclick="permissionsRoleRightsPop('${name}','${KualiForm.formKey}',' ${KualiForm.document.sessionDocument}'); return false;"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
            <c:forEach var="assignedRole" items="${KualiForm.proposalAssignedRoles}" varStatus="status">
			    <kra-permissions:roleUsers id="${assignedRole.roleName}" roleName="${assignedRole.roleName}" userList="${assignedRole.userNames}" />
        	</c:forEach>
        </table>
    </div> 
</kul:tabTop>
