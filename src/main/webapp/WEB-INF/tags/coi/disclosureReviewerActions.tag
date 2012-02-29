<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
<c:set var="modifyPermissions" value="${KualiForm.disclosureActionHelper.maintainReviewers}" />


<kul:tab tabTitle="Reviewer Actions" defaultOpen="false" tabErrorKey="disclosureActionHelper.newCoiUserRole.*">
    <div class="tab-container"  align="center">
        <h3> 
            <span class="subhead-left">Reviewer Actions</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiUserRole" altText="help"/></span>
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
                <tr> 
                    <th class="infoline">
                        <c:out value="Add:" />
                    </th>
                    
                    <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="disclosureActionHelper.newCoiUserRole.userId" 
                                                  attributeEntry="${permissionsUserAttributes.userName}" />
                        <kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
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
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            </kra:permission>
            
            <%-- The list of current users --%>
            
            <c:forEach var="user" items="${KualiForm.disclosureActionHelper.coiUserRoles}" varStatus="status">
                 <tr>
                    <th>${status.index + 1}</th>
                    <td align="left" valign="middle">${user.userId}</td>
                    <td align="center" valign="middle">${user.person.fullName}</td>
                    <td align="center" valign="middle">${user.coiReviewer.description} (${user.reviewerCode})</td>
                    <td align="center" valign="middle">${user.person.unit.unitName} (${user.person.unit.unitNumber})</td>
                    <td align="center" valign="middle">${user.updateTimestamp}</td>
                    
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
</kul:tab>