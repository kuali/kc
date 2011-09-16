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
 