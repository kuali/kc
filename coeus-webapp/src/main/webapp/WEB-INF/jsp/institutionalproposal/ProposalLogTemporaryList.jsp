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

<c:set var="proposalLogAttributes" value="${DataDictionary.ProposalLog.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />

<html>
<body>
<div id="proposalLogMergeList" style="text-align: center;">
	<div class="message"><h2>The following matching temporary proposal logs exist. Select to merge one or select cancel at the bottom.</h2></div>
	<table style="margin-left: auto; margin-right: auto; border: 1px solid gray; border-collapse: collapse;">
		<tr>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.proposalNumber}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.piName}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.sponsorCode}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.sponsorName}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.title}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.leadUnit}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${unitAttributes.unitName}" readOnly="true" noColon="true" /></th>
			<th class="grid"><kul:htmlAttributeLabel attributeEntry="${proposalLogAttributes.comments}" readOnly="true" noColon="true" /></th>
			<th class="grid">&nbsp;</th>
		</tr>
		<c:forEach items="${KualiForm.matchedProposalLogs}" var="pLog">
			<tr>
				<td class="grid"><c:out value="${pLog.proposalNumber}"/></td>
				<td class="grid"><c:out value="${pLog.piName}"/></td>
				<td class="grid"><c:out value="${pLog.sponsorCode}"/></td>
				<td class="grid"><c:out value="${pLog.sponsorName}"/></td>
				<td class="grid"><c:out value="${pLog.title}"/></td>
				<td class="grid"><c:out value="${pLog.leadUnit}"/></td>
				<td class="grid"><c:out value="${pLog.unit.unitName}"/></td>
				<td class="grid"><c:out value="${pLog.comments}"/></td>
				<td class="grid"><a href="#" class="mergeLink" proposalnumber="${pLog.proposalNumber}"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-merge.gif" alt="merge" title="merge"/></a></td>				
			</tr>
		</c:forEach>
	</table>
	
	
	<div class="globalbuttons">
	<a href="#" class="cancel globalbuttons"><img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="globalbuttons" alt="Cancel" title="Cancel"/></a>
	</div>
</div>
<kul:csrf />
</body>
</html>
