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
<%@ attribute name="financialEntity" required="true" type="org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure" %>
                                    <kul:innerTab tabTitle="Financial Entity Versions : ${financialEntity.entityName}" parentTab="${financialEntity.entityName}" defaultOpen="false">
                                        <div class="innerTab-container" align="left">
                                            <table class=tab cellpadding=0 cellspacing="0" summary="" width="100%">
                                                <tr>
                                                    <th style="width: 60%">
                                                        Status
                                                    </th>
                                                    <th style="width: 20%">
                                                        Last Modified Date
                                                    </th>
                                                    <th style="width: 20%">
                                                        Last Modifier
                                                    </th>
                                                </tr>
                                                <c:forEach var="finEntityVersion" items="${financialEntity.versions}" varStatus="innerItrStatus">
                                                    <tr>
                                                    <td style="width: 60%">
                                                         <div align="left">
                                                            ${finEntityVersion.finIntEntityStatus.description}
                                                        </div>
                                                        </td>
                                                        <td style="width: 20%">
                                                           <fmt:formatDate value="${finEntityVersion.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />
                                                        </td>
                                                        <td style="width: 20%">
                                                            ${finEntityVersion.updateUser}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </kul:innerTab>
 
