<!--    /auth/unauthorized.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<table cellspacing="0" cellpadding="5" width="600" class="table">
    <tr class="tableHeader">
        <td>Access Denied !</td>
    </tr>
    <tr>
        <td class="plaintext">
            <br>
            You are not authorized to perform this operation.
            <br><br>
        </td>
    </tr>
    <tr>
        <td>
        <input type="button" name="" value="Back" onclick="JavaScript:history.back();" class="Inside3d">
        </td>
    </tr>
</table>
