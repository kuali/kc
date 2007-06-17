<!--/auth/userActivity.jsp-->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.List,
                 org.jmanage.webui.util.RequestAttributes,
                 java.util.Iterator"%>
<table cellspacing="0" cellpadding="5" width="900" class="table">
<tr class="tableHeader">
    <td>User Activities</td>
</tr>
<%
    List activities = (List)request.getAttribute(RequestAttributes.USER_ACTIVITIES);
    Iterator iterator = activities.iterator();
    int row = 0;
    while(iterator.hasNext()){
%>
  <tr>
    <td class="plaintext"><%=iterator.next()%></td>
  </tr>
  <%}//while ends %>
</table>
