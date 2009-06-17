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
<%@ attribute name="name" required="true" %>

<c:set var="action" value="${name}Permissions" />

<kul:tabTop tabTitle="Assigned Roles" defaultOpen="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Assigned Roles</span>
    		<span class="subhead-right">
    		    <html:image property="methodToCall.getPermissionsRoleRights.anchor${tabKey}"
    			            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewrights.gif' styleClass="tinybutton"
					        onclick="javascript: permissionsRoleRightsPop('${name}', ${KualiForm.formKey}, ${KualiForm.document.sessionDocument});return false"/>		
			</span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
            <c:forEach var="assignedRole" items="${KualiForm.permissionsHelper.assignedRoles}" varStatus="status">
			    <kra-permissions:roleUsers id="${assignedRole.role.name}" roleName="${assignedRole.role.displayName}" userList="${assignedRole.userNames}" />
        	</c:forEach>
        </table>
    </div> 
</kul:tabTop>
