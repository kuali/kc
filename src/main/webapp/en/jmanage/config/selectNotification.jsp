<!--    /config/selectNotification.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.Map,
                 java.util.Iterator,
                 org.jmanage.core.management.ObjectNotificationInfo,
                 org.jmanage.core.util.Expression,
                 org.jmanage.core.management.ObjectName"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<%
    Map mbeanToNotificationsMap =
            (Map)request.getAttribute("mbeanToNotificationsMap");

%>

<jmhtml:form action="/config/selectNotificationType" method="post">


<%
    for(Iterator it = mbeanToNotificationsMap.keySet().iterator(); it.hasNext();){
        String mbean = (String)it.next();
        ObjectNotificationInfo[] notifications =
                (ObjectNotificationInfo[])mbeanToNotificationsMap.get(mbean);
%>
<table class="table" border="0" cellspacing="0" cellpadding="3" width="700">
    <tr class="tableheader">
        <td><%=ObjectName.getShortName(mbean)%></td>
    </tr>
<%
        for(int i=0; i<notifications.length; i++){
%>
    <tr>
        <td class="plaintext"><%=notifications[i].getName()%></td>
    </tr>
<%          String[] notifTypes = notifications[i].getNotifTypes();
            for(int j=0; j<notifTypes.length; j++){
                Expression expr = new Expression(null, mbean, notifTypes[j]);
%>
    <tr>
    <td class="plaintext"><jmhtml:radio property="expression"
                      value="<%=expr.getHtmlEscaped()%>"/>
                      <%=notifTypes[j]%></td>
    </tr>
<%
            }
        }
%>
</table>
<br/>
<%
    }
%>
<table border="0" cellspacing="0" cellpadding="3" width="600">
<tr>
    <td align="center">
        <jmhtml:submit property="" value="Next" styleClass="Inside3d" />
        &nbsp;&nbsp;&nbsp;
        <jmhtml:button property="" value="Back" onclick="JavaScript:history.back();" styleClass="Inside3d" />
    </td>
</tr>
</table>
</jmhtml:form>
