<%@ page import="org.jmanage.webui.util.WebContext,
                 org.jmanage.core.auth.User"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<%
    User user = WebContext.get(request).getUser();
%>

<table width="900" border="0" cellspacing="0" cellpadding="0" bgcolor="#e8b120">
  <tr>
    <!-- need context path in img src uri -->
    <td><jmhtml:img src="<%= request.getContextPath() + "/jmanage/images/logoNew.gif" %>" width="150" height="48" /></td>
    <td align="right" valign="bottom" class="plaintext">
        <%if(user != null){%>
        <div style="margin-right:3px;margin-bottom:3px">
        <a class="nav0" href="<%= request.getContextPath() %>/jmanage/config/managedApplications.do">Home</a>&nbsp;|&nbsp;
        <a class="nav0" href="<%= request.getContextPath() %>/jmanage/auth/profile.do">Profile</a>&nbsp;|&nbsp;
        <a class="nav0" href="<%= request.getContextPath() %>/jmanage/config/admin.do">Admin</a>&nbsp;|&nbsp;
        <a class="nav0" href="<%= request.getContextPath() %>/jmanage/auth/logout.do">Logout</a>&nbsp;|&nbsp;
        Logged-in as <b><%=user.getName()%></b>
        </div>
        <%}else{%>
            &nbsp;
        <%}%>
    </td>
  </tr>
</table>
