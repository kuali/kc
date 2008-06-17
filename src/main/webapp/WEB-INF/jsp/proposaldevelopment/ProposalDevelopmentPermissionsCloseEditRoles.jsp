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
	<script>
	    var propRoleStates = new Array();
	    var rs;
	    var cnt = 0;
	    <c:forEach var="roleState" items="${KualiForm.proposalUserEditRoles.roleStates}" varStatus="status">
           rs = new PropRoleState('${roleState.name}', '${roleState.state}');
           propRoleStates[propRoleStates.length] = rs;
           if (rs.getState().toLowerCase() == 'true') {
               cnt++;
           }
        </c:forEach>
        if (cnt == 0) {
            propRoleStates[propRoleStates.length] = new PropRoleState('unassigned', 'true');
        }
        
		updateEditRoles(${KualiForm.proposalUserEditRoles.lineNum}, propRoleStates);
	</script>
	
</kul:page>