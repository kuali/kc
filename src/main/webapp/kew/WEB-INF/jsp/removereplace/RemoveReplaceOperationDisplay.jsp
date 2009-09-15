<%@ taglib uri="../../tld/c.tld" prefix="c" %>

<%-- This would be a good spot to use a tag library since this code is essentially verbatim from WorkgroupEntry.jsp --%>
<c:set var="userDisplayName" value="${RemoveReplaceForm.user.displayName}"/>
<c:if test="${kewUserSession.principalId != RemoveReplaceForm.user.workflowId}">
  <c:set var="userDisplayName" value="${RemoveReplaceForm.user.displayNameSafe}"/>
</c:if>

<c:set var="replacementUserDisplayName" value="${RemoveReplaceForm.replacementUser.displayName}"/>
<c:if test="${kewUserSession.principalId != RemoveReplaceForm.replacementUser.workflowId}">
  <c:set var="replacementUserDisplayName" value="${RemoveReplaceForm.replacementUser.displayNameSafe}"/>
</c:if>

  <div class="annotate-container">
    <div align="center">

      <table border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td><div align="right"><strong>Action:</strong></div></td>
          <td><c:out value="${RemoveReplaceForm.operationDisplayName}"/></td>
        </tr>
        <tr>
          <td><div align="right"><strong>User Id:</strong></div></td>
          <td><c:out value="${userDisplayName}" />
             (<kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.PersonImpl" keyValues="principalId=${RemoveReplaceForm.user.workflowId}" render="true">
                <c:out value="${RemoveReplaceForm.userId}" />
              </kul:inquiry>)
          </td>
        </tr>
        <c:if test="${RemoveReplaceForm.replace}">
        <tr>
          <td><div align="right"><strong>User Id to Replace With:</strong></div></td>
          <td><c:out value="${replacementUserDisplayName}" />
              (<kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.PersonImpl" keyValues="principalId=${RemoveReplaceForm.replacementUser.workflowId}" render="true">
                <c:out value="${RemoveReplaceForm.replacementUserId}" />
              </kul:inquiry>)
          </td>
        </tr>
        </c:if>
      </table>

    </div>
  </div>
