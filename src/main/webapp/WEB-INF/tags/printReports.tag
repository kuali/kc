<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
                <kul:innerTab parentTab="Print Forms" tabTitle="Print Reports" defaultOpen="false" tabErrorKey="">
                    <div class="innerTab-container" align="center" >
                        <table align="right" cellpadding="0" cellspacing="0" summary="">
                            <tbody>
                                    <kra:currentOrPendingReport title="Current Report" methodName="prepareCurrentReport" printPdfMethodName="printCurrentReportPdf" requestUri="${requestUri}" />
                                    <kra:currentOrPendingReport title="Pending Report" methodName="preparePendingReport" printPdfMethodName="printPendingReportPdf" requestUri="${requestUri}" />

                                    <c:set var="showCurrentReport" value='${currentReportRows != null}'/><!-- && !empty currentReportRows}' />-->
                                    <c:set var="showPendingReport" value='${pendingReportRows != null}'/><!-- && !empty pendingReportRows}' />-->
                                    
                                    <c:if test="${showCurrentReport || showPendingReport}">
                                        <tr>
                                            <td colspan="4">
                                                <c:if test='${showCurrentReport}'>
                                                    <strong>Current Support - ${reportPersonName}</strong><br/>
                                                    <display:table class="datatable-100" cellspacing="0" cellpadding="0" name="${currentReportRows}"
                                                        id="row" export="true" pagesize="100" requestURI="${requestUri}?methodToCall=prepareCurrentReport" requestURIcontext="true" >
                                                        <c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
															<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
                                                            <display:column style="text-align: center;" sortable="${column.sortable}" title="${column.columnTitle}" comparator="${column.comparator}" maxLength="${column.maxLength}" decorator="org.kuali.rice.kns.web.ui.FormatAwareDecorator"><c:out value="${column.propertyValue}" escapeXml="true" default="&nbsp;" /></display:column>
															<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
                                                        </c:forEach>
                                                        <display:setProperty name="export.csv.include_header" value="true"/>
                                                    </display:table>
                                                </c:if>
                                                <c:if test='${showPendingReport}'>
                                                    <strong>Pending Support - ${reportPersonName}</strong><br/>
                                                     <display:table class="datatable-100" cellspacing="0" cellpadding="0" name="${pendingReportRows}"
                                                                    id="row" export="true" pagesize="100" requestURI="${requestUri}?methodToCall=preparePendingReport=" requestURIcontext="true" >
                                                         <c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
															<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
                                                        	<display:column sortable="${column.sortable}" title="${column.columnTitle}" comparator="${column.comparator}" maxLength="${column.maxLength}" class="" decorator="org.kuali.rice.kns.web.ui.FormatAwareDecorator"><c:out value="${column.propertyValue}" escapeXml="true" default="&nbsp;" /></display:column>
															<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
                                                        </c:forEach>
                                                        <display:setProperty name="export.csv.include_header" value="true"/>
                                                    </display:table>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:if>
                            </tbody>
                        </table>
                    </div>
                </kul:innerTab>
            </td>
        </tr>
    </table>  
</div>
