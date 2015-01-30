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

<c:set var="proposalUserAttributes" value="${DataDictionary.ProposalUser.attributes}" />
<c:set var="action" value="proposalDevelopmentPermissions" />

<kul:tab tabTitle="Users" defaultOpen="true"  
         tabErrorKey="document.developmentProposalList[0].proposalPermissionUser*,newProposalUser*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Users</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.propdev.impl.docperm.ProposalUser" altText="help"/></span>
        </h3>
        
        <table id="user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
        	<%-- Table headers --%>
        	
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proposalUserAttributes.username}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="center">Full Name</div></th>
          		<th><div align="center">Unit #</div></th>
          		<th><div align="center">Unit Name</div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proposalUserAttributes.roleName}" skipHelpUrl="true" noColon="true" /></div></th>
          		<kra:section permission="modifyPermissions">
          		    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          		</kra:section>
          	</tr>
          	
          	<%-- The input controls for adding a new user. --%>

          	<kra:section permission="modifyPermissions">
	            <tr class="addline"> 
					<th class="infoline">
						<c:out value="Add:" />
					</th>
					
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="newProposalUser.username" 
	                	                          attributeEntry="${proposalUserAttributes.username}" 
	                	                          onblur="loadPersonName('newProposalUser.username', 
	                	                          						'fullnameCell', 'unitNumberCell', 'unitNameCell');"/>
	                	<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
	                	            fieldConversions="userName:newProposalUser.username,fullName:newProposalUser.fullname,unit.unitName:newProposalUser.unitName,unit.unitNumber:newProposalUser.unitNumber"
	                	            anchor="${tabKey}" />                        
					</td>
					
					<td id="fullnameCell" align="left" valign="middle">&nbsp; ${KualiForm.newProposalUser.fullname}</td>
					<td id="unitNumberCell">&nbsp; ${KualiForm.newProposalUser.unitNumber}</td>
					<td id="unitNameCell">&nbsp; ${KualiForm.newProposalUser.unitName}</td>
					
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="newProposalUser.roleName" 
	                	                          attributeEntry="${proposalUserAttributes.roleName}" />
					</td>
	 
					<td>
						<div align="center">
							<html:image property="methodToCall.addProposalUser.anchor${tabKey}"
								        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
						</div>
		            </td>
	            </tr>
            </kra:section>
            
            <%-- The list of current users --%>
            
            <c:forEach var="user" items="${KualiForm.proposalUserRoles}" varStatus="status">
	             <tr>
	             	<th>${status.index + 1}</th>
	             	<td align="left" valign="middle">${user.username}</td>
	             	<td align="left" valign="middle"><nobr>${user.fullname}</nobr></td>
					<td align="left" valign="middle">${user.unitNumber}</td>
					<td align="left" valign="middle"><nobr>${user.unitName}</nobr></td>
					<td id="role${status.index}" align="left" valign="middle">
					    <c:forEach var="roleLabel" items="${user.roleLabels}" varStatus="status2">
					       <c:if test="${status2.index != 0}"><br /></c:if>
					       <nobr>${roleLabel}</nobr>
					    </c:forEach>
					</td>
					<kra:section permission="modifyPermissions">
						<td align="center" valign="middle">
						 	<div align="center">
							<nobr>
								<html:image property="methodToCall.editRoles.line${status.index}.anchor${tabKey}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-editrole.gif' styleClass="tinybutton"
											onclick="javascript: editRolesPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false"/>
								&nbsp;
								<html:image property="methodToCall.deleteProposalUser.line${status.index}.anchor${tabKey}"
										    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</nobr>
							</div>
						</td>
					</kra:section>
				</tr>	
        	</c:forEach>
        </tbody>
    	</table>
    </div> 
    <input type="hidden" name="lookup.ownedByUnitNumber" value="${KualiForm.document.developmentProposalList[0].ownedByUnitNumber}" />
</kul:tab>
