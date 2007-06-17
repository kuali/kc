<!--/auth/showLogin.jsp-->
<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<jmhtml:javascript formName="loginForm" />
<jmhtml:form action="/auth/login" method="post" focus="username"
                            onsubmit="return validateLoginForm(this)" >

<jmhtml:errors />
<br/>
<table cellspacing="0" cellpadding="5" width="400" class="table">
  <tr class="tableHeader">
    <td colspan="2">Login</td>
  </tr>
  <tr>
    <td class="headtext1">
        Username
    </td>
    <td>
        <jmhtml:text property="username" />
    </td>
  </tr>
  <tr>
    <td class="headtext1">
        Password
    </td>
    <td>
        <jmhtml:password property="password" />
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><jmhtml:submit styleClass="Inside3d" value="Login" /></td>
  </tr>
</table>
</jmhtml:form>
