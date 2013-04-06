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
<%-- Member of institutionalProposalProjectPersonnel.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="institutionalProposalContact" required="true" type="org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson" %>
<%@ attribute name="institutionalProposalContactRowStatusIndex" required="true" %>
<c:set var="keypersonrole" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:set var="coirole" value="<%=org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE%>" />

<c:set var="institutionalProposalPersonAttributes" value="${DataDictionary.InstitutionalProposalPerson.attributes}" />

<kul:innerTab tabTitle="Person Details" parentTab="${institutionalProposalContact.fullName}" defaultOpen="false" tabErrorKey="document.institutionalProposal.projectPersons*">
	<table cellpadding="0" cellspacing="0" summary="Project Personnel Details">
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.faculty}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].faculty" 
											attributeEntry="${institutionalProposalPersonAttributes.faculty}" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.academicYearEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].academicYearEffort" 
											attributeEntry="${institutionalProposalPersonAttributes.academicYearEffort}" styleClass="amount"/>
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.totalEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].totalEffort" 
											attributeEntry="${institutionalProposalPersonAttributes.totalEffort}" styleClass="amount"/>
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.summerEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].summerEffort" 
											attributeEntry="${institutionalProposalPersonAttributes.summerEffort}" styleClass="amount"/>
			</td>
		</tr>
		<tr>
		  <c:choose>
		   <c:when test="${KualiForm.document.institutionalProposalList[0].projectPersons[institutionalProposalContactRowStatusIndex].contactRole.roleCode == keypersonrole}">
		    <th class="infoline">
		    	<div align="right">
					*<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.keyPersonRole}" useShortLabel="true" noColon="false" />
				</div>
		    </th> 
		    <td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].keyPersonRole" 
										attributeEntry="${institutionalProposalPersonAttributes.keyPersonRole}"/>		    
		    </td>
		   </c:when>
		   <c:when test="${KualiForm.document.institutionalProposalList[0].projectPersons[institutionalProposalContactRowStatusIndex].contactRole.roleCode == coirole && KualiForm.document.institutionalProposalList[0].sponsorNihMultiplePi}">
		    <th class="infoline">
		    	<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.multiplePi}" noColon="false" />
				</div>
		    </th> 
		    <td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].multiplePi" 
										attributeEntry="${institutionalProposalPersonAttributes.multiplePi}"/>		    
		    </td>
		   </c:when>		   
		   <c:otherwise>
			<th class="infoline">&nbsp;</th>
			<td>&nbsp;</td>
		   </c:otherwise>
		  </c:choose>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${institutionalProposalPersonAttributes.calendarYearEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.institutionalProposalList[0].projectPersons[${institutionalProposalContactRowStatusIndex}].calendarYearEffort" 
											attributeEntry="${institutionalProposalPersonAttributes.calendarYearEffort}" styleClass="amount"/>
			</td>
		</tr>	            				
	</table>
</kul:innerTab>
