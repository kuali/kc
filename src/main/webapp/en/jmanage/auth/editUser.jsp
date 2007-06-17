<!--    /auth/editUser.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<jmhtml:javascript formName="userForm" />
<jmhtml:errors />
<jmhtml:form action="/auth/editUser" method="post"
                                    onsubmit="return validateUserForm(this)">
<jmhtml:hidden property="username" />

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
    <td colspan="2">Edit User</td>
</tr>
<tr>
    <td class="headtext1">Username:</td>
    <td class="plaintext"><c:out value="${requestScope.userForm.username}" /></td>
</tr>
<c:choose>
<c:when test="${requestScope.userForm.username != 'admin'}">
<tr>
    <td class="headtext1">Password:</td>
    <td><jmhtml:password property="password" /></td>
</tr>
<tr>
    <td class="headtext1">Re-enter Password:</td>
    <td><jmhtml:password property="confirmPassword" /></td>
</tr>
<tr>
    <td class="headtext1">Role:</td>
    <td><jmhtml:select property="role">
            <jmhtml:option value="" > --------- Select --------- </jmhtml:option>
            <jmhtml:options collection="roles" property="name" labelProperty="name" />
        </jmhtml:select>
    </td>
</tr>
</c:when>
<c:otherwise>
<tr>
    <jmhtml:hidden property="password" />
    <jmhtml:hidden property="confirmPassword" />
    <td class="plaintext" colspan="2">
     Please use 'changepwd' script for changing admin user password.
    </td>
</tr>
</c:otherwise>
</c:choose>
<tr>
    <td class="headtext1">Lock Account:</td>
    <td><jmhtml:checkbox property="status" value="L" styleId="checked"/></td>
</tr>
<tr>
    <td align="center" colspan="2">
        <jmhtml:submit value="Save" styleClass="Inside3d" />
        &nbsp;&nbsp;&nbsp;
        <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
    </td>
</tr>
</table>
</jmhtml:form>
