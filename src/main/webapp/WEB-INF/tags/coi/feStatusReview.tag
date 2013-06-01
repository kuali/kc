<%--
 Copyright 2005-2013 The Kuali Foundation

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
<c:set var="permissionsUserAttributes" value="${DataDictionary.PermissionsUser.attributes}" />
<c:set var="coiUserRoleAttributes" value="${DataDictionary.CoiUserRole.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="userRoles" value="${KualiForm.disclosureActionHelper.coiUserRoles}"/>

	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Financial Entity Status Review</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
        <table id="coi-user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
            <%-- Table headers --%>
            
            <tr>
                <th width="5%"><div align="left">&nbsp;</div></th> 
                <th width="15%"><div align="center">Active Entities</div></th>
                <th><div align="center">Comments</div></th>
                <th width="12%"><div align="center">Last Updated</div></th>
                <th width="12%"><div align="center">Updated By</div></th>
                <th width="12%"><div align="center">Recommended Status</div></th>
            </tr>
            
			<c:set var="userIndex" value="1"/>
            <c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
				<c:forEach var="disclosureDetail" items="${disclProject.coiDiscDetails}" varStatus="festatus">
        			<tr>
        				<td>
        					${userIndex}
        				</td>
		           		<td style="text-align: left;" valign="middle">
           					<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiDiscDetails[${festatus.index}].personFinIntDisclosure.entityName" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
           				</td>
		           		<td style="text-align: left;" valign="middle">
           					<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiDiscDetails[${festatus.index}].comments" readOnly="true" attributeEntry="${coiDiscDetailAttributes.comments}" /> 
           				</td>
		           		<td style="text-align: left;" valign="middle">
           					<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiDiscDetails[${festatus.index}].updateTimestamp" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
           				</td>
		           		<td style="text-align: left;" valign="middle">
           					<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiDiscDetails[${festatus.index}].updateUser" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
           				</td>
            	 		<td style="text-align: left;" valign="middle">
        	   				<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${status.index}].coiDiscDetails[${festatus.index}].entityStatusCode" 
  								attributeEntry="${coiDiscDetailAttributes.entityStatusCode}" styleClass="conflictClass${status.index}" />
						</td>
    	    		</tr>
		        	<c:set var="userIndex" value="${userIndex+1}" />
	        	</c:forEach>
            </c:forEach>
        </tbody>
        </table>        
       </div> 
