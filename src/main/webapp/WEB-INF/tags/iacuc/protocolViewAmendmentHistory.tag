<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:if test="${fn:length(KualiForm.actionHelper.amendmentSummaries) > 0}">

<kul:innerTab tabTitle="Amendment/Renewal History" parentTab="" defaultOpen="false" tabErrorKey="">

    <div class="innerTab-container" align="left">
	    <h3>
   			<span class="subhead-left">Amendments/Renewals</span>
		</h3>
        <table id="amendHistoryTable" class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                <tr>
	                <th>Type</th>
	                <th>Version Number</th>
	                <th style="width:30%;">Summary</th>
	                <th>Status</th>
	                <th>Created Date</th>
                </tr>
                <c:forEach items="${KualiForm.actionHelper.amendmentSummaries}" var="protocolSummary" varStatus="status">
            		<tr>
            		    <td class="infoline">
            		        <nobr>${protocolSummary.amendmentType}</nobr>
            		    </th>
            		    <td class="infoline">
            		        <b><a href="${protocolSummary.versionNumberUrl}" target="_blank">${protocolSummary.versionNumber}</a></nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr>${protocolSummary.description}</nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr>${protocolSummary.status}</nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr><b>${protocolSummary.createDate}</b></nobr>
            		    </td>
            		</tr>
            	</c:forEach>
            </tbody>
        </table>
    </div>
    			
</kul:innerTab>
</c:if>