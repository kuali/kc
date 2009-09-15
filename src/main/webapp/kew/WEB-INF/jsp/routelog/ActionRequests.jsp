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
        <td width="20%" align=right class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.actionRequestId"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.actionRequestId}" />&nbsp;</td>
    </tr>
    <tr>
        <td align=right class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.requestStatus"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.statusLabel}" />&nbsp;</td>
    </tr>
    <tr>
        <td align=right class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.responsibilityId"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.responsibilityId}" />&nbsp;</td>
    </tr>
    <tr>
        <td align=right class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.responsibility"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.responsibilityDesc}" />&nbsp;</td>
    </tr>
    <tr>
        <td align=right class="thnormal"><bean-el:message key="routeLog.ActionRequests.actionRequest.label.annotation"/>:</td>
        <td class="datacell1"><c:out value="${actionRequest.annotation}" />&nbsp;</td>
    </tr>
    --%>
    <c:if test="${actionRequest.ruleBaseValuesId != null}">
        <tr>
            <td align=right class="thnormal">Rule:</td>
            <td class="datacell1">
             <a href="<c:url value="Rule.do">
                        <c:param name="currentRuleId" value="${actionRequest.ruleBaseValuesId}" />
                        <c:param name="methodToCall" value="report" />
                    </c:url>"><c:out value="${actionRequest.ruleBaseValuesId}" /></a>
            </td>
        </tr>
    </c:if>
    <c:if test="${! empty actionRequest.childrenRequests}">
        <tr>
          <td colspan="4" style="padding: 0; border: 0;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding: 0; border: 0;">
               <tr>
                <th width="5%">&nbsp;</td>
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
