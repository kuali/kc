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

<c:set var="roleStateAttributes" value="${DataDictionary.PermissionsRoleState.attributes}" />

<kul:tabTop defaultOpen="true" tabTitle="Roles" tabErrorKey="permissionsUserEditRole*">
	<div class="tab-container" align="center">
		<h3>
    		<span class="subhead-left">Roles</span>
        </h3>	
        <table id="edit-roles-table" cellpadding="0" cellspacing="0" summary="">
             
            <c:forEach var="roleState" items="${KualiForm.permissionsHelper.editRoles.roleStates}" varStatus="status">
                <c:choose>
                    <c:when test="${KualiForm.permissionsHelper.editRoles.principalInvestigator && (roleState.role.displayName == 'Aggregator')}">
                        <c:set var="updateDisabled" value="true" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="updateDisabled" value="false" />
                    </c:otherwise>
                </c:choose>
	          	<tr>
	            	<th width="35%"><div align="right">${roleState.role.displayName}</div></th>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="permissionsHelper.editRoles.roleStates[${status.index}].state" 
	                	                          attributeEntry="${roleStateAttributes.state}" disabled="${updateDisabled}" />
					</td>
	        	</tr>
	        	
	        </c:forEach>
        	
        </table>
    </div>
</kul:tabTop>
