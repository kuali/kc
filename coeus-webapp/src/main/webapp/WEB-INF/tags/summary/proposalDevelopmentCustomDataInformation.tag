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
<%@ attribute name="transparentBackground" required="false" %>
<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="scienceKeywordAttributes"
	value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="textAreaFieldName"
	value="document.developmentProposalList[0].mailDescription" />
<c:set var="action" value="proposalDevelopmentApproverView" />
<c:set var="className"
	value="org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument" />
<c:set var="className"
	value="org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument" />
<kul:tab tabTitle="Custom Data Information" defaultOpen="false" transparentBackground="${transparentBackground }"
	tabErrorKey="">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Custom Data Information</span>
		</h3>
		<table cellpadding=0 cellspacing="0" summary="">
			<c:forEach items="${KualiForm.document.customAttributeDocuments}"
				var="customAttributeDocument1" varStatus="status">
				<tr>
					<c:set var="customAttributeLabel"
						value="${customAttributeDocument1.value.customAttribute.label}" />
					<c:set var="customAttributeValue"
						value="${customAttributeDocument1.value.customAttribute.value}" />
					<th width="50%"><div align="left">
							<c:out value="${customAttributeLabel}" />
						</div>
					</th>
					<td><div align=center>
							<c:out value="${customAttributeValue}" />
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</kul:tab>


