<%@ taglib uri="../../tld/c.tld" prefix="c" %>

<%-- This would be a good spot to use a tag library since this code is essentially verbatim from WorkgroupEntry.jsp --%>
<c:set var="userDisplayName" value="${RemoveReplaceForm.user.displayName}"/>
<c:if test="${kewUserSession.workflowUser.workflowId != RemoveReplaceForm.user.workflowId}">
  <c:set var="userDisplayName" value="${RemoveReplaceForm.user.displayNameSafe}"/>
</c:if>
<c:url var="userReportUrl" value="${UrlResolver.userReportUrl}">
  <c:param name="workflowId" value="${RemoveReplaceForm.user.workflowId}" />
  <c:param name="methodToCall" value="report" />
  <c:param name="showEdit" value="no" />
</c:url>

<c:set var="replacementUserDisplayName" value="${RemoveReplaceForm.replacementUser.displayName}"/>
<c:if test="${kewUserSession.workflowUser.workflowId != RemoveReplaceForm.replacementUser.workflowId}">
  <c:set var="replacementUserDisplayName" value="${RemoveReplaceForm.replacementUser.displayNameSafe}"/>
</c:if>
<c:url var="replacementUserReportUrl" value="${UrlResolver.userReportUrl}">
  <c:param name="workflowId" value="${RemoveReplaceForm.replacementUser.workflowId}" />
  <c:param name="methodToCall" value="report" />
  <c:param name="showEdit" value="no" />
</c:url>

  <div class="annotate-container">
    <div align="center">

      <table border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td><div align="right"><strong>Action:</strong></div></td>
          <td><c:out value="${RemoveReplaceForm.operationDisplayName}"/></td>
        </tr>
        <tr>
          <td><div align="right"><strong>User Id:</strong></div></td>
          <td><c:out value="${userDisplayName}" /> (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${RemoveReplaceForm.userId}"/></a>)</td>
        </tr>
        <c:if test="${RemoveReplaceForm.replace}">
        <tr>
          <td><div align="right"><strong>User Id to Replace With:</strong></div></td>
          <td><c:out value="${replacementUserDisplayName}" /> (<a href="<c:out value="${replacementUserReportUrl}"/>"><c:out value="${RemoveReplaceForm.replacementUserId}"/></a>)</td>
        </tr>
        </c:if>
      </table>

    </div>
  </div>
