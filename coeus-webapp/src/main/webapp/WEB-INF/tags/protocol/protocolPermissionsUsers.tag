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
<%@ attribute name="name" required="true" %>
<%@ attribute name="modifyPermissions" required="true" %>
<%@ attribute name="permissionsUserAttributes" required="true" type="java.util.Map"%>

<c:set var="action" value="${name}Permissions" />

<kul:tab tabTitle="Users" defaultOpen="true"  
         tabErrorKey="document.permissionsUser*,newPermissionsUser*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Users</span>
    		<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="protocolPermissionsUsersHelp" altText="help"/></span>
        </h3>
        
        <table id="user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
        	<%-- Table headers --%>
        	
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionsUserAttributes.userName}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="center">Full Name</div></th>
          		<th><div align="center">Unit #</div></th>
          		<th><div align="center">Unit Name</div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionsUserAttributes.roleName}" skipHelpUrl="true" noColon="true" /></div></th>
          		<kra:permission value="${modifyPermissions}">
          		    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          		</kra:permission>
          	</tr>
          	
          	<%-- The input controls for adding a new user. --%>
          	<kra:permission value="${modifyPermissions}">
          		<tbody class="addline">
	            <tr> 
					<th class="infoline">
						<c:out value="Add:" />
					</th>
					
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="permissionsHelper.newUser.userName" 
	                	                          attributeEntry="${permissionsUserAttributes.userName}" />
	                	<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
	                	            fieldConversions="personId:permissionsHelper.newUser.userId,userName:permissionsHelper.newUser.userName,fullName:permissionsHelper.newUser.fullName,unit.unitNumber:permissionsHelper.newUser.unitNumber,unit.unitName:permissionsHelper.newUser.unitName" 
	                	            lookupParameters="lookup.leadUnit:organizationIdentifier"
	                	            anchor="${tabKey}" />                        
					</td>
					
					<td align="left" valign="middle">
					   <kul:htmlControlAttribute property="permissionsHelper.newUser.fullName" 
                                                 attributeEntry="${permissionsUserAttributes.fullName}" readOnly="true" />
					</td>
					
					<td align="left" valign="middle">
                       <kul:htmlControlAttribute property="permissionsHelper.newUser.unitNumber" 
                                                 attributeEntry="${permissionsUserAttributes.unitNumber}" readOnly="true" />
                    </td>
                    
                    <td align="left" valign="middle">
                       <kul:htmlControlAttribute property="permissionsHelper.newUser.unitName" 
                                                 attributeEntry="${permissionsUserAttributes.unitName}" readOnly="true" />
                    </td>
					
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="permissionsHelper.newUser.roleName" 
	                	                          attributeEntry="${permissionsUserAttributes.roleName}" />
					</td>
	 
					<td>
						<div align="center">
							<html:image property="methodToCall.addUser.anchor${tabKey}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
						</div>
		            </td>
	            </tr>
	            </tbody>
            </kra:permission>
            
            <%-- The list of current users --%>
            
            <c:forEach var="user" items="${KualiForm.permissionsHelper.users}" varStatus="status">
	             <tr>
	             	<th>${status.index + 1}</th>
	             	<td align="left" valign="middle">${user.person.userName}</td>
	             	<td align="left" valign="middle"><nobr>${user.person.fullName}</nobr></td>
					<td align="left" valign="middle">${user.person.organizationIdentifier}</td>
					<td align="left" valign="middle"><nobr>${user.person.contactOrganizationName}</nobr></td>
					<td id="role${status.index}" align="left" valign="middle">
					    <c:forEach var="role" items="${user.roles}" varStatus="status2">
					       <c:if test="${status2.index != 0}"><br /></c:if>
					       <nobr>${role.displayName}</nobr>
					    </c:forEach>
					</td>
					<kra:permission value="${modifyPermissions}">
						<td align="center" valign="middle">
						 	<div align="center">
							<nobr>
								<html:image property="methodToCall.editRoles.line${status.index}.anchor${tabKey}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-editrole.gif' styleClass="tinybutton"
											onclick="javascript: permissionsEditRolesPop('${name}', '${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false"/>
                                <c:if test="${KualiForm.document.protocolList[0].principalInvestigatorId != user.person.personId}">            
								    &nbsp;
								    <html:image property="methodToCall.deleteUser.line${status.index}.anchor${tabKey}"
									    	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                                </c:if>
							</nobr>
							</div>
						</td>
					</kra:permission>
				</tr>	
        	</c:forEach>
        </tbody>
    	</table>
    </div>
 
    <input type="hidden" name="lookup.leadUnit" value="${KualiForm.document.protocolList[0].leadUnit.unitNumber}" />

</kul:tab>
