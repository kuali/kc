<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding: 0; border: 0;">
    <c:if test="${level == 0}">
    <tr>
        <th style="width: 10em;"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.routeNode"/></th>
        <td class="datacell1"><c:out value="${actionRequest.routeLevelName}" />&nbsp;</td>
    </tr>
    <tr>
        <th><bean-el:message key="routeLog.ActionRequests.actionRequest.label.routingPriority"/></th>
        <td class="datacell1"><c:out value="${actionRequest.priority}" />&nbsp;</td>
    </tr>
    <tr>
        <th><bean-el:message key="routeLog.ActionRequests.actionRequest.label.approvePolicy"/></th>
                        <td class="datacell1">
                            <c:choose>
                              <c:when test="${actionRequest.approvePolicy == KEWConstants.APPROVE_POLICY_ALL_APPROVE}">
                                <bean-el:message key="routeLog.ActionRequests.actionRequest.label.allApprove"/>
                              </c:when>
                              <c:when test="${actionRequest.approvePolicy == KEWConstants.APPROVE_POLICY_FIRST_APPROVE}">
                                <bean-el:message key="routeLog.ActionRequests.actionRequest.label.firstApprove"/>
                              </c:when>
                            </c:choose>
                        &nbsp;</td>
    </tr>
    <tr>
        <th><bean-el:message key="routeLog.ActionRequests.actionRequest.label.forceAction"/></th>
        <td class="datacell1"><c:out value="${actionRequest.forceAction}" />&nbsp;</td>
    </tr>
    </c:if>
    <%-- 
    <tr>
        <td width="20%" align="right" class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.actionRequestId"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.actionRequestId}" />&nbsp;</td>
    </tr>
    <tr>
        <td align="right" class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.requestStatus"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.statusLabel}" />&nbsp;</td>
    </tr>
    <tr>
        <td align="right" class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.responsibilityId"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.responsibilityId}" />&nbsp;</td>
    </tr>
    <tr>
        <td align="right" class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.responsibility"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.responsibilityDesc}" />&nbsp;</td>
    </tr>
    <tr>
        <td align="right" class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.annotation"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.annotation}" />&nbsp;</td>
    </tr>
    --%>
    <c:if test="${actionRequest.ruleBaseValuesId != null}">
        <tr>
            <c:if test="${level == 0}"><th>Rule</th></c:if>
            <c:if test="${level != 0}"><td class="thnormal" style="text-align:right;">Rule</td></c:if>
            <td class="datacell1">
                <kul:inquiry boClassName="org.kuali.rice.kew.rule.RuleBaseValues" keyValues="ruleBaseValuesId=${actionRequest.ruleBaseValuesId}"
                        render="true"><c:out value="${actionRequest.ruleBaseValuesId}" /></kul:inquiry>
            </td>
        </tr>
    </c:if>
    <c:if test="${! empty actionRequest.childrenRequests}">
        <tr>
          <td colspan="4" style="padding: 0; border: 0;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding: 0; border: 0;">
               <tr>
                <th width="5%">&nbsp;</th>
                <th width="15%"><bean-el:message key="routeLog.ActionRequests.pendingActionRequests.label.action"/></th>
                <th width="15%"><bean-el:message key="routeLog.ActionRequests.pendingActionRequests.label.requestedOf"/></th>
                <th width="22%"><bean-el:message key="routeLog.ActionRequests.pendingActionRequests.label.timeDate"/></th>
                <th width="40%"><bean-el:message key="routeLog.ActionRequests.pendingActionRequests.label.annotation"/></th>
               </tr>
               <c:set var="currentLevel" value="${level+1}" scope="page"/>
               <c:forEach var="actionRequest" items="${actionRequest.childrenRequests}" varStatus="arStatus">
                 <c:set var="level" value="${currentLevel}" scope="request"/>
                 <c:set var="index" value="${index}z${arStatus.index + shiftIndex}" scope="request" />
                 <c:set var="actionRequest" value="${actionRequest}" scope="request"/>
                 <c:set var="hasChildren" value="${! empty actionRequest.childrenRequests}" scope="request"/>
                 <jsp:include page="ActionRequest.jsp" flush="true" />
                </c:forEach>
              </table>
            </td>
         </tr>
    </c:if>
</table>
