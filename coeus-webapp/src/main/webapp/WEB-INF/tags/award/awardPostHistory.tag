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
<c:set var="awardPostHistory" value="${KualiForm.awardPostHistory}" />

<kul:tab tabTitle="Award Post History" defaultOpen="${param.command eq 'displayDocSearchView' ? true : false}" >
	<div class="tab-container" align="left">
    	<h3> 
    		<span class="subhead-left">Award Post History</span>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
                <tr>
                    <th>
                        <div align="center">
                            Data retrieved
                        </div>
                    </th>
                    <th>
                        <div align="center"	>
                            Posted date
                        </div>
                    </th>
                    <th>
                        <div align="center"	>
                            Username
                        </div>
                    </th>
                    <th>
                        <div align="center"	>
                            First name
                        </div>
                    </th>
                    <th>
                        <div align="center"	>
                            Last name
                        </div>
                    </th>
                    <th>
                        <div align="center"	>
                            Document
                        </div>
                    </th>
                </tr>
            <c:forEach items="${awardPostHistory}" var="postHistory">
                <tr>
                    <td class="infoline">
                        <div align="center">
                            <c:out value="${!postHistory.active}" />
                        </div>
                    </td>
                    <td class="infoline">
                        <div align="center">
                            <fmt:formatDate value="${postHistory.updateTimestamp}" pattern="MM/dd/yyyy HH:mm:ss" />
                        </div>
                    </td>
                    <td class="infoline">
                        <div align="center">
                            <c:out value="${postHistory.updateUser}" />
                        </div>
                    </td>
                    <td class="infoline">
                        <div align="center">
                            <c:out value="${postHistory.firstName}" />
                        </div>
                    </td>
                    <td class="infoline">
                        <div align="center">
                            <c:out value="${postHistory.lastName}" />
                        </div>
                    </td>
                    <td class="infoline">
                        <div align="center">
                            <a href="${ConfigProperties.application.url}/kew/DocHandler.do?command=displayDocSearchView&amp;docId=${postHistory.documentNumber}"
                               target="_self" style="color:#068acd;" title="open award">${postHistory.documentNumber}</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
	</div>
</kul:tab>

