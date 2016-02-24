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
<%-- Member of awardProjectPersonnel.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="awardContact" required="true" type="org.kuali.kra.award.contacts.AwardPerson" %>
<%@ attribute name="awardContactRowStatusIndex" required="true" %>
<c:set var="keypersonrole" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:set var="coirole" value="<%=org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE%>" />

<c:set var="awardPersonAttributes" value="${DataDictionary.AwardPerson.attributes}" />

<kul:innerTab tabTitle="Person Details" parentTab="${awardContact.fullName}" defaultOpen="false" 
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].k*,document.awardList[0].projectPersons[${awardContactRowStatusIndex}].t*,document.awardList[0].projectPersons[${awardContactRowStatusIndex}].a*,document.awardList[0].projectPersons[${awardContactRowStatusIndex}].s*,document.awardList[0].projectPersons[${awardContactRowStatusIndex}].ca*">
	<table cellpadding="0" cellspacing="0" summary="Project Personnel Details">
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.faculty}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].faculty" 
											attributeEntry="${awardPersonAttributes.faculty}" />
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.academicYearEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].academicYearEffort" 
											attributeEntry="${awardPersonAttributes.academicYearEffort}" styleClass="amount"/>
			</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.totalEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].totalEffort" 
											attributeEntry="${awardPersonAttributes.totalEffort}" styleClass="amount"/>
			</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.summerEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].summerEffort" 
											attributeEntry="${awardPersonAttributes.summerEffort}" styleClass="amount"/>
			</td>
		</tr>
		<tr>
			<th class="infoline">&nbsp;</th>
			<td>&nbsp;</td>
			<th class="infoline">
				<div align="right">
					<kul:htmlAttributeLabel attributeEntry="${awardPersonAttributes.calendarYearEffort}" useShortLabel="true" noColon="false" />
				</div>
			</th>
			<td>
				<kul:htmlControlAttribute property="document.awardList[0].projectPersons[${awardContactRowStatusIndex}].calendarYearEffort" 
											attributeEntry="${awardPersonAttributes.calendarYearEffort}" styleClass="amount"/>
			</td>
		</tr>	            				
	</table>
</kul:innerTab>
