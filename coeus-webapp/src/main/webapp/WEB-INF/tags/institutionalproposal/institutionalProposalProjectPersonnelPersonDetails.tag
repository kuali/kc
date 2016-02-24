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
