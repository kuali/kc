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

<c:set var="proposalUserAttributes" value="${DataDictionary.ProposalUser.attributes}" />
<c:set var="action" value="proposalDevelopmentPermissions" />

<kul:tab tabTitle="Users" defaultOpen="true"  
         tabErrorKey="document.proposalPermissionUser*,newProposalUser*">
         
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Users</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table id="user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
        	<%-- Table headers --%>
        	
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proposalUserAttributes.username}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="center">Full Name</div></th>
          		<th><div align="center">Unit #</div></th>
          		<th><div align="center">Unit Name</div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${proposalUserAttributes.roleName}" skipHelpUrl="true" noColon="true" /></div></th>
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
          	
          	<%-- The input controls for adding a new user. --%>
          	
            <tr> 
				<th class="infoline">
					<c:out value="Add:" />
				</th>
				
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newProposalUser.username" 
                	                          attributeEntry="${proposalUserAttributes.username}" 
                	                          onblur="loadPersonName('newProposalUser.username', 'fullname');"/>
                	<kul:lookup boClassName="org.kuali.kra.bo.Person" 
                	            fieldConversions="userName:newProposalUser.username" 
                	            lookupParameters="lookup.ownedByUnitNumber:homeUnit"
                	            anchor="${tabKey}" />                        
				</td>
				
				<td id="fullname" align="left" valign="middle" />
				<td />
				<td />
				
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newProposalUser.roleName" 
                	                          attributeEntry="${proposalUserAttributes.roleName}" />
				</td>
 
				<td>
					<div align="center">
						<html:image property="methodToCall.addProposalUser.anchor${tabKey}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            
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
					<td align="center" valign="middle">
					 	<div align="center">
						<nobr>
							<html:image property="methodToCall.editRoles.line${status.index}.anchor${tabKey}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-editrole.gif' 
										onclick="javascript: editRolesPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false"/>
							&nbsp;
							<html:image property="methodToCall.deleteProposalUser.line${status.index}.anchor${tabKey}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
						</nobr>
						</div>
					</td>
				</tr>	
        	</c:forEach>
        </tbody>
    	</table>
    </div> 
    <input type="hidden" name="lookup.ownedByUnitNumber" value="${KualiForm.document.ownedByUnitNumber}" />
</kul:tab>
