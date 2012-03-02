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

<%-- Member of awardProjectPersonnel.tag --%>

<%@ attribute name="awardContact" required="true" type="org.kuali.kra.award.contacts.AwardPerson" %>
<%@ attribute name="awardPersonIndex" required="true" %>

<c:set var="awardPersonUnitAttributes" value="${DataDictionary.AwardPersonUnit.attributes}" />
<c:set var="isPrincipalInvestigator" value="${awardContact.principalInvestigator}" />

<c:set var="newAwardPersonUnits" value="${KualiForm.projectPersonnelBean.newAwardPersonUnits}" />
<c:set var="targetAwardPersonUnit" value="${newAwardPersonUnits[awardPersonIndex]}" />

<kul:innerTab tabTitle="Unit Details" parentTab="${awardContact.fullName}" defaultOpen="false" 
				tabErrorKey="document.award[${awardPersonUnitRowStatus.index}].awardContact*,projectPersonnelBean.newAwardPersonUnit[${ awardPersonIndex }]*">
	<c:choose><c:when test="${awardContact.keyPerson && !awardContact.optInUnitStatus}">
     <p><html:image property="methodToCall.addUnitDetails.line${awardPersonIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-addunitdet.gif" title="Add Unit Details" alt="Add Unit Details" styleClass="tinybutton"/>
     You have the option to add unit details for a key person.</p>
	</c:when><c:otherwise>
	<c:if test="${awardContact.keyPerson}">
     <p><html:image property="methodToCall.removeUnitDetails.line${awardPersonIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-remunitdet.gif" title="Remove Unit Details" alt="Remove Unit Details" styleClass="tinybutton"/>
     You have the option to remove unit details for a key person.</p>	
	</c:if>
	<table cellpadding="0" cellspacing="0" summary="Project Personnel Units">
		<tr>
			<th class="infoline">
				<div align="center">&nbsp;</div>
			</th>
			<c:if test="${isPrincipalInvestigator}" >
				<th class="infoline">
					<div align="center">*Lead Unit</div>
				</th>
			</c:if>
			<th class="infoline">
				<div align="center">Unit Name</div>
			</th>
			<th class="infoline">
				<div align="center">Unit Number</div>
			</th>
			<th class="infoline">
				<div align="center">OSP Administrator</div>
			</th>
			<th class="infoline">
				<div align="center">Actions</div>
				
			</th>
		</tr>
		<c:if test="${!readOnly}">
		<tr>
			<th class="infoline" scope="row">Add:</th>
			<c:if test="${isPrincipalInvestigator}" >
				<th class="infoline">
					<div align="center">
					<kul:htmlControlAttribute property="projectPersonnelBean.newAwardPersonUnit[${awardPersonIndex}].leadUnit" 
												attributeEntry="${awardPersonUnitAttributes.leadUnit}" />
					</div>
				</th>
			</c:if>
			<th class="infoline">
				<html:hidden property="projectPersonnelBean.newAwardPersonUnit[${awardPersonIndex}].unitName" />
				<div align="center">
					<c:choose>
						<c:when test="${empty KualiForm.projectPersonnelBean.newAwardPersonUnits[awardPersonIndex].unitName}">
							(select)
						</c:when>
						<c:otherwise>
							${KualiForm.projectPersonnelBean.newAwardPersonUnits[awardPersonIndex].unitName}
						</c:otherwise>
					</c:choose>
					&nbsp; <kul:lookup boClassName="org.kuali.kra.bo.Unit" 
							fieldConversions="unitNumber:projectPersonnelBean.newAwardPersonUnit[${awardPersonIndex}].unitNumber,unitName:projectPersonnelBean.newAwardPersonUnit[${awardPersonIndex}].unitName" />
								
				</div>
			</th>
			<th class="infoline">
				<div align="center">
                                <kul:htmlControlAttribute property="projectPersonnelBean.newAwardPersonUnit[${awardPersonIndex}].unitNumber"
		    	  	 								attributeEntry="${awardPersonUnitAttributes.unitNumber}"
		    	  	 								readOnly="false" />
				</div>
			</th>
			<th>
				<div>
					&nbsp;
				</div>
			</th>			
			<th class="infoline">
				<div align="center">
                    <html:image property="methodToCall.addNewProjectPersonUnit.line${awardPersonIndex}"
                                src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact"
                                styleClass="tinybutton" />                    
                </div>
			</th>
		</tr>
		</c:if>
		
		<c:forEach var="awardPersonUnit" items="${awardContact.units}" varStatus="awardPersonUnitRowStatus">
		<input type="hidden" name="award_person_unit.identifier_${awardPersonUnitRowStatus.index}" value="${awardPersonUnit.unit.unitNumber}" />
		<c:choose>                  
			<c:when test="${empty awardPersonUnit.ospAdministrators}">
            <tr>
				<th class="infoline" scope="row">
					<c:out value="${awardPersonUnitRowStatus.index + 1}" />
				</th>
				<c:if test="${isPrincipalInvestigator}">
	                <td valign="middle">
	                	<div align="center">
	                		<html:radio property="selectedLeadUnit" value="${awardPersonUnit.unit.unitName}" disabled="${readOnly}"/>               		
						</div>
					</td>
				</c:if>
                <td valign="middle">
                	<div align="center">
                		${awardPersonUnit.unit.unitName}&nbsp;
                		<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="award_person_unit.identifier_${awardPersonUnitRowStatus.index}:unitNumber" anchor="${tabKey}" />
                	</div> 
				</td>
				<td valign="middle">
                	<div align="center">
						${awardPersonUnit.unit.unitNumber}&nbsp;						
					</div>
				</td>
				<td valign="middle">
                	<div align="center">
						&nbsp;						
					</div>
				</td>
				<td class="infoline">
					<div align="center">
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteProjectPersonUnit.personIndex${awardPersonIndex}.line${awardPersonUnitRowStatus.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						<c:if test="${KualiForm.syncMode}">
 							<html:image property="methodToCall.syncProjectPersonUnit.personIndex${awardPersonIndex}.line${awardPersonUnitRowStatus.index}.anchor${currentTabIndex}"
 								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
						</c:if>						
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
                </td>
            </tr>
            </c:when>
            <c:otherwise>
            	<c:forEach var="ospAdministrator" items="${awardPersonUnit.ospAdministrators}" varStatus="awardOspAdministratorRowStatus">
            		<tr>
						<th class="infoline" scope="row">
							<c:out value="${awardPersonUnitRowStatus.index + 1}" />
						</th>
						<c:if test="${isPrincipalInvestigator}">
	                	<td valign="middle">
	                		<div align="center">
	                			<html:radio property="selectedLeadUnit" value="${awardPersonUnit.unit.unitName}" disabled="${readOnly}"/>               		
							</div>
						</td>
						</c:if>
                		<td valign="middle">
                			<div align="center">
                				${awardPersonUnit.unit.unitName}&nbsp;
                				<kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="award_person_unit.identifier_${awardPersonUnitRowStatus.index}:unitNumber" anchor="${tabKey}" />
                			</div> 
						</td>
						<td valign="middle">
                			<div align="center">
								${awardPersonUnit.unit.unitNumber}&nbsp;						
							</div>
						</td>
						<td valign="middle">
                			<div align="center">
								${ospAdministrator.person.fullName}						
							</div>
						</td>
						<td class="infoline">
							<div align="center">
							 <c:if test="${!readOnly}">
								<html:image property="methodToCall.deleteProjectPersonUnit.personIndex${awardPersonIndex}.line${awardPersonUnitRowStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							<c:if test="${KualiForm.syncMode}">
 								<html:image property="methodToCall.syncProjectPersonUnit.personIndex${awardPersonIndex}.line${awardPersonUnitRowStatus.index}.anchor${currentTabIndex}"
 									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
							</c:if>									
							 </c:if>
							 <c:if test="${readOnly}">&nbsp;</c:if>
							</div>
                		</td>
            		</tr>
            	</c:forEach>
            </c:otherwise>
            </c:choose>
		</c:forEach>
	</table>
	</c:otherwise></c:choose>
</kul:innerTab>
