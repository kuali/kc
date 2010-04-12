<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<%@ attribute name="requestUri" required="true" %>

<c:set var="personAttributes" value="${DataDictionary.KcPerson.attributes}" />

<div align="center">
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
            <td>
                <kra:innerTab parentTab="Print Forms" tabTitle="Print Reports" defaultOpen="false" tabErrorKey="">
                    <div class="innerTab-container" align="center" >
                        <table align="right" cellpadding="0" cellspacing="0" summary="">
                            <tbody>
                                    <kra:currentOrPendingReport title="Current Report" methodName="prepareCurrentReport" printPdfMethodName="printCurrentReportPdf" requestUri="${requestUri}" />
                                    <kra:currentOrPendingReport title="Pending Report" methodName="preparePendingReport" printPdfMethodName="printPendingReportPdf" requestUri="${requestUri}" />

                                    <c:set var="showCurrentReport" value='${currentReportBeans != null && !empty currentReportBeans}' />
                                    <c:set var="showPendingReport" value='${pendingReportBeans != null && !empty pendingReportBeans}' />
                                    <c:if test="${showCurrentReport || showPendingReport}">
                                        <tr>
                                            <td colspan="4">
                                                <c:if test='${showCurrentReport}'>
                                                    <strong>Current Support - ${reportPersonName}</strong><br/>
                                                    <display:table class="datatable-100" cellspacing="0" cellpadding="0" name="${currentReportBeans}"
                                                        id="row" export="true" pagesize="100" requestURI="${requestUri}" requestURIcontext="true" >
                                                        <c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
                                                            <display:column style="text-align: center;" sortable="${column.sortable}" title="${column.columnTitle}" comparator="${column.comparator}" maxLength="${column.maxLength}">
                                                                <%-- The following logic is needed because propertyValues from the Rice formatters will produce empty strings and not nulls
                                                                      The emptyStrings then are not handled by the default attribute on c:out and the grid renders improperly.
                                                                      So we test for empty strings and output a non-breaking space instead --%>
                                                                 <c:set var="isEmptyString" value="${column.propertyValue == null || fn:length(fn:trim(column.propertyValue)) == 0}" />
                                                                 <c:if test="${isEmptyString}">
                                                                     <c:out value="&nbsp;" escapeXml="false"/>
                                                                 </c:if>
                                                                 <c:if test="${!isEmptyString}">
                                                                    <c:out value="${column.propertyValue}" escapeXml="true" default="&nbsp;" />
                                                                 </c:if>
                                                            </display:column>
                                                        </c:forEach>
                                                    </display:table>
                                                </c:if>
                                                <c:if test='${showPendingReport}'>
                                                    <strong>Pending Support - ${reportPersonName}</strong><br/>
                                                     <display:table class="datatable-100" cellspacing="0" cellpadding="0" name="${pendingReportBeans}"
                                                                    id="row" export="true" pagesize="100" requestURI="${requestUri}" requestURIcontext="true" >
                                                         <c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
                                                             <display:column sortable="${column.sortable}" title="${column.columnTitle}" comparator="${column.comparator}" maxLength="${column.maxLength}" class="">
                                                                 <%-- The following logic is needed because propertyValues from the Rice formatters will produce empty strings and not nulls
                                                                      The emptyStrings then are not handled by the default attribute on c:out and the grid renders improperly.
                                                                      So we test for empty strings and output a non-breaking space instead --%>
                                                                 <c:set var="isEmptyString" value="${column.propertyValue == null || fn:length(fn:trim(column.propertyValue)) == 0}" />
                                                                 <c:if test="${isEmptyString}">
                                                                     <c:out value="&nbsp;" escapeXml="false"/>
                                                                 </c:if>
                                                                 <c:if test="${!isEmptyString}">
                                                                    <c:out value="${column.propertyValue}" escapeXml="true" default="&nbsp;" />
                                                                 </c:if>
                                                             </display:column>
                                                        </c:forEach>
                                                    </display:table>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:if>
                            </tbody>
                        </table>
                    </div>
                </kra:innerTab>
            </td>
        </tr>
    </table>
</div>  
