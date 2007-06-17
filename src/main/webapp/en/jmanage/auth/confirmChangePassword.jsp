<!--    /auth/confirmChangePassword.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
    <td>Change Password</td>
</tr>
<tr>
    <td class="plaintext">
    Password has been successfully changed.&nbsp;&nbsp;&nbsp;
    <jmhtml:link href="<%= request.getContextPath() %>/jmanage/auth/profile.do" styleClass="a">Continue&gt;&gt;</jmhtml:link>
    </td>
</tr>
</table>

