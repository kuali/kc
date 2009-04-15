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

<c:set var="roleStateAttributes" value="${DataDictionary.ProposalRoleState.attributes}" />

<kul:tabTop defaultOpen="true" tabTitle="Roles" tabErrorKey="proposalUserEditRole*">
	<div class="tab-container" align="center">
		<div class="h2-container">
    		<span class="subhead-left"><h2>Roles</h2></span>
        </div>	
        <table id="edit-roles-table" cellpadding="0" cellspacing="0" summary="">
             
            <c:forEach var="roleState" items="${KualiForm.proposalUserEditRoles.roleStates}" varStatus="status">
            
	          	<tr>
	            	<th width="35%"><div align="right">${roleState.name}</div></th>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="proposalUserEditRoles.roleStates[${status.index}].state" attributeEntry="${roleStateAttributes.state}" />
					</td>
	        	</tr>
	        	
	        </c:forEach>
        	
        </table>
    </div>
</kul:tabTop>
