<!--    /auth/ChangePassword.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<%--<jmhtml:javascript formName="ChangePasswordForm" />--%>
<jmhtml:errors />
<jmhtml:form action="/auth/changePassword" method="post">

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
    <td colspan="2">Change Password</td>
</tr>
<tr>
    <td class="headtext1">Old Password:</td>
    <td><jmhtml:password property="oldPassword" value=""/></td>
</tr>
<tr>
    <td class="headtext1">New Password:</td>
    <td><jmhtml:password property="newPassword" value=""/></td>
</tr>
<tr>
    <td class="headtext1">Confirm Password:</td>
    <td><jmhtml:password property="confirmPassword" value=""/></td>
</tr>
<tr>
    <td colspan="2" align="center">
    &nbsp;&nbsp;
    <jmhtml:submit value="Save" styleClass="Inside3d" />
    &nbsp;&nbsp;&nbsp;
    <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
    </td>
</tr>
</table>
</jmhtml:form>
