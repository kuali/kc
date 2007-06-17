<!--    /auth/configuredUsers.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.RequestAttributes,
                 java.util.Map,
                 java.util.Collection,
                 java.util.Iterator,
                 org.jmanage.core.auth.User,
                 org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.auth.AuthConstants,
                 org.jmanage.webui.util.WebContext,
                 org.jmanage.core.auth.RoleConstants"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<script language="JavaScript">
    function deleteUser(username){
        if(confirm("Are you sure you want to delete this user?") == true){
            location = '/auth/deleteUser.do?<%=RequestParams.USER_NAME%>='+username;
        }
    }
</script>

<table cellspacing="0" cellpadding="5" width="400" class="table">
    <tr class="tableHeader">
        <td colspan="3">Users</td>
    </tr>
<%
    User loggedInUser = WebContext.get(request).getUser();

    Map users = (Map)request.getAttribute(RequestAttributes.USERS);
    Iterator iterator = users.values().iterator();
    while(iterator.hasNext()){
        User user = (User)iterator.next();
%>
  <tr>
    <td class="plaintext"><%=user.getName()%></td>
    <td class="plaintext" align="right">
    <%if(loggedInUser.hasRole(RoleConstants.ADMINISTRATOR) ||
            AuthConstants.USER_ADMIN.equals(loggedInUser.getUsername())){%>
    <a href="<%= request.getContextPath() %>/jmanage/auth/showEditUser.do?<%=RequestParams.USER_NAME+"="+user.getUsername()%>" class="a1">Edit</a>
    <%}else{%>
    &nbsp;
    <%}%>
    </td>
    <td class="plaintext" align="right" width="60">
    <%if(!AuthConstants.USER_ADMIN.equals(user.getUsername())){%>
    <a href="JavaScript:deleteUser('<%=user.getUsername()%>');" class="a1">Delete</a>
    <%}else{%>
    &nbsp;
    <%}%>
    </td>
  </tr>
  <%}//while ends %>
</table>
<br>
<jmhtml:link href="<%= request.getContextPath() %>/jmanage/auth/showAddUser.do" styleClass="a">Add New User</jmhtml:link>
