<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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


<link href="css/Proposal_Approver_View.css" rel="stylesheet" type="text/css">

<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">My Approval</span>
        </h3>
        
        <div id="approver_wrapper">
		<div id="approver_top">
        
        <div id="buttons_container">
			<p>
<kra-pd:approverViewButtons 
transactionalDocument="true"
	extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
			extraButtonAlt="${extraButtonAlt}" 
				extraButtons="${extraButtons}"
				suppressCancelButton="${hierarchyStatus == KualiForm.hierarchyParentStatus}" />
</p>
		</div>
        
  <div id="approver_table_container">

<div id="left">

<table cellpadding="7">
<tr>
<th width="140">Project Title:</th>
<td width="272" class="even">${KualiForm.approverViewDO.projectTitle}</td>
</tr>
<tr class="hr">
<th>Proposal No.:</th>
<td>${KualiForm.approverViewDO.proposalNumber}</td>
</tr>
<tr class="hr">
<th>Proposal Type:</th>
<td class="even">${KualiForm.approverViewDO.proposalType}</td>
</tr>
<tr class="hr">
<th>Activity Type:</th>
<td class="even">${KualiForm.approverViewDO.activityType}</td>
</tr>
<tr class="hr">
<th>Sponsor:</th>
<td>${KualiForm.approverViewDO.sponsorName}</td>

</tr>
<tr class="hr">
<th>Lead Unit:</th>
<td class="even">${KualiForm.approverViewDO.leadUnit}</td>
</tr>
</table>
<table cellpadding="7">
<c:set var="costShareInfos" value="${KualiForm.approverViewDO.costShareInfos}" />
   <c:forEach items="${costShareInfos}"  var="costShareInfo" varStatus="status">
<tr class="hr">
<c:choose>
				<c:when test='${status.index eq 0}'>
      				<th width="140">Cost Shares:</th>
      			</c:when>
      			<c:otherwise>
      				<th>&nbsp;</th>
      			</c:otherwise>
      		</c:choose>
<td width="158">${costShareInfo.costShareUnit}</td>
<td width="102" class="right_data"><fmt:formatNumber value="${costShareInfo.costShareAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" />&nbsp;</td>
</tr>
</c:forEach>
</table>
</div>

<div id="right">
<table cellpadding="7">
<tr>
<th>Amounts:</th>
<td class="even">Total Direct Cost:</td>
<td class="even_right_data"><fmt:formatNumber value="${KualiForm.approverViewDO.directCost}" type="currency" currencySymbol="$" maxFractionDigits="2" />&nbsp;</td>
</tr>
<tr>
<th>&nbsp;</th>
<td class="even">Total F&A Cost:</td>
<td class="even_right_data"><fmt:formatNumber value="${KualiForm.approverViewDO.indirectCost}" type="currency" currencySymbol="$" maxFractionDigits="2" />&nbsp;</td>
</tr>
<tr>
<th>&nbsp;</th>
<td class="even">Total All Cost:</td>
<td class="even_right_data"><fmt:formatNumber value="${KualiForm.approverViewDO.totalCost}" type="currency" currencySymbol="$" maxFractionDigits="2" />&nbsp;</td>
</tr>
</table>

<table cellpadding="7">
<tr class="hr">
<th>Dates:</th>
<td>Proposal Due Date:</td>

<td class="right_data"><fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.approverViewDO.dueDate}"/></td>
</tr>
<tr><th>&nbsp;</th><td>Start Date:</td>
<td class="right_data"><fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.approverViewDO.startDate}"/></td>
</tr>
<tr>
<th>&nbsp;</th><td>End Date</td>
<td class="right_data"><fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.approverViewDO.endDate}"/></td>
</tr>

</table>
<table cellpadding="7">

<tr class="hr">
<th>PI:</th>
<td colspan="2" class="even">${KualiForm.approverViewDO.piName}</td>
</tr>

<c:set var="coPiInfos" value="${KualiForm.approverViewDO.coPiInfos}" />
<c:forEach items="${coPiInfos}"  var="coPiInfo" varStatus="status">

<tr class="hr">
<c:choose>
				<c:when test='${status.index eq 0}'>
      				<th >Co-PIs:</th>
      			</c:when>
      			<c:otherwise>
      				<th>&nbsp;</th>
      			</c:otherwise>
      		</c:choose>
<td>${coPiInfo.coPiName}</td>
<td class="right_data">${coPiInfo.coPiUnit}</td>
</tr>
</c:forEach>

</table>
</div>

</div>

</div>

