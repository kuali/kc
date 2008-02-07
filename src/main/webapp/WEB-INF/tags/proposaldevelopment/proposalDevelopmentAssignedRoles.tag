<%--
 Copyright 2008 The Kuali Foundation.
 
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

<c:set var="action" value="proposalDevelopmentPermissions" />

<kul:tabTop tabTitle="Assigned Roles" defaultOpen="true"  
            tabErrorKey="document.proposalPermissionRole">
         
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Assigned Roles</h2></span>
    		<span class="subhead-right">
    		    <html:image property="methodToCall.getPermissionsRoleRights.anchor${tabKey}"
    			            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewrights.gif'
					        onclick="javascript: proposalRoleRightsPop(${KualiForm.formKey}, ${KualiForm.document.sessionDocument});return false"/>		
			</span>
        </div>
        
        <table cellpadding="0" cellspacing="0" summary="">
			<kra-pd:roleUsers id="Aggregators" roleName="Aggregators" userList="${KualiForm.aggregatorsByName}" />
        	<kra-pd:roleUsers id="BudgetCreators" roleName="Budget Creators" userList="${KualiForm.budgetCreatorsByName}" />
        	<kra-pd:roleUsers id="NarrativeWriters" roleName="Narrative Writers" userList="${KualiForm.narrativeWritersByName}" />
			<kra-pd:roleUsers id="Viewers" roleName="Viewers" userList="${KualiForm.viewersByName}" />
        </table>
    </div> 
</kul:tabTop>
