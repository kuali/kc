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
<c:set var="permissionsUserAttributes" value="${DataDictionary.PermissionsUser.attributes}" />
<c:set var="modifyPermissions" value="${KualiForm.disclosureActionHelper.maintainReviewers}" />

	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Add Reviewers Action</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
        <table id="coi-user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
            <%-- Table headers --%>
            
            <tr>
                <th><div align="left">&nbsp;</div></th> 
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${permissionsUserAttributes.userName}" skipHelpUrl="true" noColon="true" /></div></th>
                <th><div align="center">Full Name</div></th>
                <th><div align="center">Reviewer Type</div></th>
                <th><div align="center">Lead Unit</div></th>
                <th><div align="center">Date Assigned</div></th>
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
                        <kul:htmlControlAttribute property="disclosureActionHelper.newCoiUserRole.userId" 
                                                  attributeEntry="${permissionsUserAttributes.userName}" />
                        <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                    fieldConversions="userName:disclosureActionHelper.newCoiUserRole.userId" 
                                    anchor="${tabKey}" />                        
                    </td>
                    <td>&nbsp;</td>
                    <td align="center">
                        <html:select property="disclosureActionHelper.newCoiUserRole.reviewerCode">                               
                           <c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.lookup.CoiReviewerValuesFinder', paramMap)}" var="option">   
                               <c:choose>                      
                                   <c:when test="${option.key eq 'RVW'}">
                                       <option value="${option.key}" selected="selected">${option.value}</option>
                                   </c:when>
                                   <c:otherwise>                               
                                       <option value="${option.key}">${option.value}</option>
                                   </c:otherwise>
                               </c:choose>                                                
                           </c:forEach>
                       </html:select>
                    </td> 
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>                    
                    <td>
                        <div align="center">
                            <html:image property="methodToCall.addCoiUserRole.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
                        </div>
                    </td>
                </tr>
                </tbody>
            </kra:permission>
            
            <%-- The list of current users --%>
            
            <c:forEach var="user" items="${KualiForm.disclosureActionHelper.coiUserRoles}" varStatus="status">
                 <tr>
                    <th>${status.index + 1}</th>
                    <td align="left" valign="middle">${user.userId}</td>
                    <td align="center" valign="middle">${user.person.fullName}</td>
                    <td align="center" valign="middle">${user.coiReviewer.description} (${user.reviewerCode})</td>
                    <td align="center" valign="middle">${user.person.unit.unitName} (${user.person.unit.unitNumber})</td>
                    <td align="center" valign="middle">${user.dateAssigned}</td>
                    
                    <kra:permission value="${modifyPermissions}">
                        <td align="center" valign="middle">
                            <div align="center">
                            <nobr>
	                            <html:image property="methodToCall.deleteCoiUserRole.line${status.index}.anchor${tabKey}"
	                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                            </nobr>
                            </div>
                        </td>
                    </kra:permission>
                </tr>   
            </c:forEach>
        </tbody>
        </table>        
       </div> 
