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

<c:set var="amendmentSummaries" value="${amendmentSummaries}"  />
<c:if test="${fn:length(amendmentSummaries) > 0}">

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
                <c:forEach items="${amendmentSummaries}" var="protocolSummary" varStatus="status">
            		<tr>
            		    <td>
            		        <nobr><u><a href="${protocolSummary.versionNumberUrl}" target="_blank" alt="Open this version in a separate tab">${protocolSummary.amendmentType}</a></u></nobr>
            		    </th>
            		    <td>
            		        <nobr><u><a href="${protocolSummary.versionNumberUrl}" target="_blank" alt="Open this version in a separate tab">${protocolSummary.versionNumber}</a></u></nobr>
            		    </td>
            		    <td>
            		        <nobr>
	                        	<kra:truncateComment textAreaFieldName="actionHelper.amendmentSummaries[${status.index}].description" 
	                            	action="protocolProtocolActions" 
	                                textAreaLabel="Action Comment" 
	                                textValue="${protocolSummary.description}" 
	                                displaySize="100"/>
            		        </nobr>
            		    </td>
            		    <td>
            		        <nobr>${protocolSummary.status}</nobr>
            		    </td>
            		    <td>
            		        <nobr>${protocolSummary.createDate}</nobr>
            		    </td>
            		</tr>
           			<tr>
           				<td>&nbsp;</td>
           			    <td class="infoline" colspan="5">
							<c:set var="protocolNumber" value="${amendmentSummaries[status.index].amendRenewProtocol.protocolSummary.protocolNumber}" />
							<c:set var="summaryTabTitle" value="${protocolSummary.amendmentType} Summary - ${protocolNumber}" />
							<kul:innerTab tabTitle="${summaryTabTitle}" parentTab="" defaultOpen="false">
    							<div class="innerTab-container">
    								<h3>
   										<span class="subhead-left">Summary</span>
							   			<span class="subhead-right">
   											<kul:help parameterNamespace="KC-PROTOCOL" parameterDetailType="Document" parameterName="protocolSummaryHelp" altText="Help"/>
										</span>
							       </h3>
					        		<kra-iacuc:protocolSummary prefix="protocolSummary.amendRenewProtocol.protocolSummary" protocolSummary="${amendmentSummaries[status.index].amendRenewProtocol.protocolSummary}" />
					        	</div>
					        </kul:innerTab>
					    </td>
					</tr>
           			<tr>
           				<td>&nbsp;</td>
           			    <td class="infoline" colspan="5">
							<c:set var="questionnaireTabTitle" value="${protocolSummary.amendmentType} Questionnaires - ${protocolNumber}" />
     						<kra-protocol:protocolViewAmendmentHistoryQuestionnaire questionnaireTabTitle = "${questionnaireTabTitle}" answerHeaders="${amendmentSummaries[status.index].answerHeaders}" />
					    </td>
					</tr>        
            	</c:forEach>
            </tbody>
        </table>
    </div>
    			
</kul:innerTab>
</c:if>
