<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


<link href="css/Proposal_Approver_View.css" rel="stylesheet" type="text/css">

<div class="tab-container" align="center" height="100">
    	
        
        <div id="approver_wrapper">
		<div id="approver_top">
        
        
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

<div id="approver_bottom">
</div>


</div>

