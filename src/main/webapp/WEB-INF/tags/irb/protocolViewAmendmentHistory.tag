<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:innerTab tabTitle="Amendment/Renewal History" parentTab="" defaultOpen="false" tabErrorKey="">

    <div class="innerTab-container" align="left">
	    <h3>
   			<span class="subhead-left">Amendments/Renewals</span>
   			<span class="subhead-right">
   				<kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolHistoryHelp" altText="Help"/>
			</span>
		</h3>
        <table id="historyTable" class="tab" cellpadding="0" cellspacing="0" summary="">
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
            		        <nobr><b>${protocolSummary.amendmentType}</b></nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr><b><a href="${protocolSummary.versionNumberUrl}" target="_blank">${protocolSummary.versionNumber}</a></b></nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr><b>${protocolSummary.description}</b></nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr><b>${protocolSummary.status}</b></nobr>
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
