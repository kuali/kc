<!--    /config/admin.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
    <td>Administration</td>
</tr>
<c:if test="${!sessionScope.authenticatedUser.externalUser}" >
<tr>
    <td class="plaintext">
        <a href="<%= request.getContextPath() %>/jmanage/auth/listUsers.do">User Management</a>
    </td>
</tr>
</c:if>
<tr>
    <td class="plaintext">
        <a href="<%= request.getContextPath() %>/jmanage/auth/showUserActivity.do">User Activity Log</a>
    </td>
</tr>
</table>
