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

<%-- Member of institutionalProposalProjectPersonnel.tag --%>

<%@ attribute name="institutionalProposalContact" required="true" type="org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson" %>
<%@ attribute name="institutionalProposalPersonIndex" required="true" %>

<c:set var="institutionalProposalPersonUnitAttributes" value="${DataDictionary.InstitutionalProposalPersonUnit.attributes}" />
<c:set var="isPrincipalInvestigator" value="${institutionalProposalContact.principalInvestigator}" />

<c:set var="newInstitutionalProposalPersonUnits" value="${KualiForm.projectPersonnelBean.newInstitutionalProposalPersonUnits}" />
<c:set var="targetInstitutionalProposalPersonUnit" value="${newInstitutionalProposalPersonUnits[institutionalProposalPersonIndex]}" />

<kul:innerTab tabTitle="Unit Details" parentTab="${institutionalProposalContact.fullName}" defaultOpen="false" 
				tabErrorKey="document.institutionalProposal[${institutionalProposalPersonUnitRowStatus.index}].institutionalProposalContact*,projectPersonnelBean.newInstitutionalProposalPersonUnit*">
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
					<kul:htmlControlAttribute property="projectPersonnelBean.newInstitutionalProposalPersonUnit[${institutionalProposalPersonIndex}].leadUnit" 
												attributeEntry="${institutionalProposalPersonUnitAttributes.leadUnit}" />
					</div>
				</th>
			</c:if>
			<th class="infoline">
				<div align="center">
					<div>
						<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonUnitAttributes.unitNumber}" skipHelpUrl="true"/>
                              <kul:htmlControlAttribute property="projectPersonnelBean.newInstitutionalProposalPersonUnit[${institutionalProposalPersonIndex}].unitNumber"
    	  	 								attributeEntry="${institutionalProposalPersonUnitAttributes.unitNumber}"
    	  	 								readOnly="false" />
						<kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:projectPersonnelBean.newInstitutionalProposalPersonUnit[${institutionalProposalPersonIndex}].unitNumber" 
											anchor="${tabKey}" lookupParameters="projectPersonnelBean.newInstitutionalProposalPersonUnit[${institutionalProposalPersonIndex}].unitNumber:unitNumber"/>
  	 				</div>
  	 				<c:choose>                  
						<c:when test="${empty targetInstitutionalProposalPersonUnit.unit}">
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label>
	              					<c:out value="${targetInstitutionalProposalPersonUnit.unit.unitName}" />
	              				</label>										            			
							</div>
						</c:otherwise>
					</c:choose>			
				</div>
			</th>
			<th class="infoline">
				<div align="center">
					<c:choose>                  
						<c:when test="${empty targetInstitutionalProposalPersonUnit.unit}">
							&nbsp;
						</c:when>
						<c:otherwise>
							<div align="center">
	              				<label>
	              				<c:out value="${targetInstitutionalProposalPersonUnit.unit.unitNumber}" />
	              				</label>
																				            			
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</th>
			<th>
				<div>
					&nbsp;
				</div>
			</th>			
			<th class="infoline">
				<div align="center">
                    <html:image property="methodToCall.addNewProjectPersonUnit.line${institutionalProposalPersonIndex}"
                                src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Contact" alt="Add Contact"
                                styleClass="tinybutton" />                    
                </div>
			</th>
		</tr>
		</c:if>
		
		<c:forEach var="institutionalProposalPersonUnit" items="${institutionalProposalContact.units}" varStatus="institutionalProposalPersonUnitRowStatus">
		<input type="hidden" name="institutional_proposal_person_unit.identifier_${institutionalProposalPersonIndex}_${institutionalProposalPersonUnitRowStatus.index}" value="${institutionalProposalPersonUnit.unit.unitNumber}" />
		<c:choose>                  
			<c:when test="${empty institutionalProposalPersonUnit.ospAdministrators}">
            <tr>
				<th class="infoline" scope="row">
					<c:out value="${institutionalProposalPersonUnitRowStatus.index + 1}" />
				</th>
				<c:if test="${isPrincipalInvestigator}">
	                <td valign="middle">
	                	<div align="center">
	                		<html:radio property="selectedLeadUnit" value="${institutionalProposalPersonUnit.unit.unitName}" disabled="${readOnly}"/>               		
						</div>
					</td>
				</c:if>
                <td valign="middle">
                	<div align="center">
                		${institutionalProposalPersonUnit.unit.unitName}&nbsp;
      	                <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="institutional_proposal_person_unit.identifier_${institutionalProposalPersonIndex}_${institutionalProposalPersonUnitRowStatus.index}:unitNumber" anchor="${tabKey}" />
                	</div> 
				</td>
				<td valign="middle">
                	<div align="center">
						${institutionalProposalPersonUnit.unit.unitNumber}&nbsp;						
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
						<html:image property="methodToCall.deleteProjectPersonUnit.${institutionalProposalPersonIndex}.line${institutionalProposalPersonUnitRowStatus.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
                </td>
            </tr>
            </c:when>
            <c:otherwise>
            	<c:forEach var="ospAdministrator" items="${institutionalProposalPersonUnit.ospAdministrators}" varStatus="institutionalProposalOspAdministratorRowStatus">
            		<tr>
						<th class="infoline" scope="row">
							<c:out value="${institutionalProposalPersonUnitRowStatus.index + 1}" />
						</th>
						<c:if test="${isPrincipalInvestigator}">
	                	<td valign="middle">
	                		<div align="center">
	                			<html:radio property="selectedLeadUnit" value="${institutionalProposalPersonUnit.unit.unitName}" disabled="${readOnly}"/>               		
							</div>
						</td>
						</c:if>
                		<td valign="middle">
                			<div align="center">
                				${institutionalProposalPersonUnit.unit.unitName}&nbsp;
        	                <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="institutional_proposal_person_unit.identifier_${institutionalProposalPersonIndex}_${institutionalProposalPersonUnitRowStatus.index}:unitNumber" anchor="${tabKey}" />
                			</div> 
						</td>
						<td valign="middle">
                			<div align="center">
								${institutionalProposalPersonUnit.unit.unitNumber}&nbsp;						
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
								<html:image property="methodToCall.deleteProjectPersonUnit.${institutionalProposalPersonIndex}.line${institutionalProposalPersonUnitRowStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
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
</kul:innerTab>
