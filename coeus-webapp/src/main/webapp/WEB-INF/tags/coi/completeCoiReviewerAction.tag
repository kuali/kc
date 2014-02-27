<%--
 Copyright 2005-2014 The Kuali Foundation

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
<c:set var="canMaintainReviewers" value="${KualiForm.disclosureActionHelper.maintainReviewers}" />
<c:set var="coiUserRoleAttributes" value="${DataDictionary.CoiUserRole.attributes}" />
<c:set var="userRoles" value="${KualiForm.disclosureActionHelper.coiUserRoles}"/>
<c:set var="readOnly" value="${!KualiForm.coiNotesAndAttachmentsHelper.performCoiDisclosureActions}"/>
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Complete Review</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
        <table id="coi-user-roles" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
            <%-- Table headers --%>
            
            <tr>
                <th><div align="left">&nbsp;</div></th> 
                <th><div align="center">Full Name</div></th>
                <th><div align="center">Reviewer Type</div></th>
                <th><div align="center">Lead Unit</div></th>
                <th><div align="center">Date Assigned</div></th>
                <th><div align="center">Recommended Action</div></th>
            </tr>
            
			<c:set var="userIndex" value="1"/>
            <c:forEach var="user" items="${userRoles}" varStatus="status">
				<html:hidden property="document.coiDisclosureList[0].coiUserRoles[${status.index}].oldCoiRecomendedTypeCode" />
            	<c:if test="${user.markedToCompleteReview}">
	                 <tr>
	                    <th>${userIndex}</th>
	                    <td align="center" valign="middle">${user.person.fullName}</td>
	                    <td align="center" valign="middle">${user.coiReviewer.description} (${user.reviewerCode})</td>
	                    <td align="center" valign="middle">${user.person.unit.unitName} (${user.person.unit.unitNumber})</td>
	                    <td align="center" valign="middle">${user.dateAssigned}</td>
	                    <td align="center" valign="middle">
	                        <div align="center">
	                        <nobr>
	                  				<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiUserRoles[${status.index}].coiRecomendedTypeCode" 
	                                            attributeEntry="${coiUserRoleAttributes.coiRecomendedTypeCode}" readOnly="${readOnly}"/>
	                        </nobr>
	                        </div>
	                    </td>
	                </tr>   
					<c:set var="userIndex" value="${userIndex+1}"/>
            	</c:if>
            </c:forEach>
            	<c:if test="${!readOnly}">
                <tr>
					<td align="center" colspan="6">
						<div align="center">
							<html:image property="methodToCall.completeReview.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						</div>
	                </td>
                </tr>
                </c:if>
        </tbody>
        </table>        
       </div> 
