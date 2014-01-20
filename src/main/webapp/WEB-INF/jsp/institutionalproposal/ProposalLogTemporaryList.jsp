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
</body>
</html>