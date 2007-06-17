<!--/app/appView.jsp-->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.WebContext,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.core.config.MBeanConfig,
                 org.jmanage.webui.util.RequestParams,
                 java.net.URLEncoder,
                 java.util.*,
                 org.jmanage.core.config.GraphConfig,
                 org.jmanage.core.config.AlertConfig,
                 org.jmanage.webui.util.Utils,
                 org.jmanage.core.util.ACLConstants,
                 org.jmanage.core.management.ObjectName"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<script language="JavaScript">
    function deleteAlert(alertId, appId){
        var msg;
        msg = "Are you sure you want to delete this Alert?";
        if(confirm(msg) == true){
            location = 'jmanage/config/deleteAlert.do?<%=RequestParams.ALERT_ID%>=' + alertId + '&<%=RequestParams.APPLICATION_ID%>=' + appId + '&refreshApps=true';
        }
        return;
    }
    function deleteGraph(graphId, appId){
        var msg;
        msg = "Are you sure you want to delete this Graph?";
        if(confirm(msg) == true){
            location = 'jmanage/config/deleteGraph.do?<%=RequestParams.GRAPH_ID%>=' + graphId + '&<%=RequestParams.APPLICATION_ID%>=' + appId + '&refreshApps=true';
        }
        return;
    }
</script>
<%
    WebContext webContext = WebContext.get(request);
    ApplicationConfig appConfig = webContext.getApplicationConfig();
%>
<%-- Configured MBeans --%>
<%if(appConfig.getMBeans().size() > 0){%>
<table border="0" cellspacing="0" cellpadding="5" width="700" class="table">
    <tr class="tableHeader">
       <td colspan="2">Managed Objects</td>
    </tr>
<%
    List mbeans = appConfig.getMBeans();
    List sortedMBeans = new ArrayList(mbeans);
    Collections.sort(sortedMBeans, new Comparator(){
        public int compare(Object obj1, Object obj2){
            String name1 = ((MBeanConfig)obj1).getName();
            String name2 = ((MBeanConfig)obj2).getName();
            return name1.compareTo(name2);
        }
    });
    for(Iterator it=sortedMBeans.iterator(); it.hasNext();){
        MBeanConfig mbeanConfig = (MBeanConfig)it.next();
%>
    <tr>
        <td class="plaintext" width="25%">
            <a href="<%= request.getContextPath() %>/jmanage/app/mbeanView.do?<%=RequestParams.APPLICATION_ID%>=<%=appConfig.getApplicationId()%>&<%=RequestParams.OBJECT_NAME%>=<%=URLEncoder.encode(mbeanConfig.getObjectName(), "UTF-8")%>">
                    <%=mbeanConfig.getName()%></a>
        </td>
        <td class="plaintext">
            <%=mbeanConfig.getObjectName()%>
        </td>
    </tr>
<%
    }
%>
</table>
<p>
    <jmhtml:link href="/jmanage/config/startAddMultiMBeanConfig.do" acl="<%=ACLConstants.ACL_ADD_MBEAN_CONFIG%>"
        styleClass="a">Add more Managed Objects</jmhtml:link>
</p>

<%}else{%>
<p class="plaintext">
    There are no configured objects.
</p>
<p>
    <jmhtml:link href="/jmanage/config/startAddMultiMBeanConfig.do" acl="<%=ACLConstants.ACL_ADD_MBEAN_CONFIG%>"
        styleClass="a">Add Managed Objects</jmhtml:link>
</p>
<%}%>

<p>
<jmhtml:form action="/app/mbeanList" method="post">
    <jmhtml:text property="objectName" />&nbsp;&nbsp;<jmhtml:submit styleClass="Inside3d" value="Find More Objects" />
</jmhtml:form>
</p>
<%-- Configured Graphs --%>
<%if(appConfig.getGraphs().size() > 0){%>
<table border="0" cellspacing="0" cellpadding="5" width="700" class="table">
    <tr class="tableHeader">
       <td colspan="3">Graphs</td>
    </tr>
<%
    for(Iterator it=appConfig.getGraphs().iterator(); it.hasNext();){
        GraphConfig graphConfig = (GraphConfig)it.next();
%>
    <tr>
        <td class="plaintext" width="25%">
            <a href="<%= request.getContextPath() %>/jmanage/app/graphView.do?<%=RequestParams.APPLICATION_ID%>=<%=appConfig.getApplicationId()%>&graphId=<%=graphConfig.getId()%>">
                    <%=graphConfig.getName()%></a>
        </td>
        <td align="right">
        <%
                String editGraphLink ="/config/showEditGraph.do?"
                        + RequestParams.GRAPH_ID + "=" + graphConfig.getId();
            %>
            <jmhtml:link href="/jmanage/<%=editGraphLink%>" acl="<%=ACLConstants.ACL_EDIT_GRAPH%>" styleClass="a1">Edit</jmhtml:link>
        </td>
        <td align="right" width="60">
        <%
            String deleteGraphLink = "JavaScript:deleteGraph('"
                    + graphConfig.getId() + "','" + appConfig.getApplicationId() + "');";
        %>
            <jmhtml:link href="/jmanage/<%=deleteGraphLink%>" acl="<%=ACLConstants.ACL_EDIT_GRAPH%>" styleClass="a1">
                Delete</jmhtml:link>
       </td>
    </tr>
<%
    }
%>
</table>
<%}else{%>
<p class="plaintext">
    There are no configured graphs.
</p>
<%}%>
<%
    String link = "/config/showMBeans.do?"
            + RequestParams.END_URL + "=" + Utils.urlEncode("/config/showAddGraph.do")
            + "&" + RequestParams.MULTIPLE + "=true&"
            + RequestParams.DATA_TYPE + "=java.lang.Number&"
            + RequestParams.DATA_TYPE + "=javax.management.openmbean.CompositeData&"
            + RequestParams.NAVIGATION + "=" + Utils.urlEncode("Add Graph");
%>
<p>
    <jmhtml:link href='/jmanage/<%=link%>' acl="<%=ACLConstants.ACL_ADD_GRAPH%>" styleClass="a">
        Add Graph</jmhtml:link>
</p>
<%
if(appConfig.getAlerts().size() > 0){
%>
<table cellspacing="0" cellpadding="5" width="800" class="table">
    <tr class="tableHeader">
        <td colspan="6">Configured Alerts</td>
    </tr>
    <tr>
        <td class="headtext1">Alert Name</td>
        <td class="headtext1">Source</td>
        <td class="headtext1">Source Type</td>
        <td class="headtext1">Alert Delivery</td>
        <td class="headtext1">&nbsp;</td>
        <td class="headtext1">&nbsp;</td>
    </tr>
    <%
        List alerts = appConfig.getAlerts();
        Iterator itr = alerts.iterator();
        while(itr.hasNext()){
            AlertConfig alertConfig = (AlertConfig)itr.next();
            String[] alertDelivery = alertConfig.getAlertDelivery();
            String alertDel = "";
            for(int i=0; i<alertDelivery.length;i++){
                if(i>0){
                    alertDel = alertDel + "," + alertDelivery[i];
                }else{
                    alertDel = alertDel + alertDelivery[i];
                }
            }
    %>
    <tr>
        <td class="plaintext">
             <%=alertConfig.getAlertName()%>
        </td>
        <td class="plaintext">
            <a href="<%= request.getContextPath() %>/jmanage/app/mbeanView.do?<%=RequestParams.APPLICATION_ID%>=<%=alertConfig.getAlertSourceConfig().getApplicationConfig().getApplicationId()%>&<%=RequestParams.OBJECT_NAME%>=<%=URLEncoder.encode(alertConfig.getAlertSourceConfig().getObjectName(), "UTF-8")%>">
             <%=ObjectName.getShortName(alertConfig.getAlertSourceConfig().getObjectName())%>
        </td>
        <td class="plaintext"><%=alertConfig.getAlertSourceConfig().getSourceTypeDesc()%></td>
        <td class="plaintext"><%=alertDel%></td>
        <td align="right" width="60">
            <%
                String editAlertLink = "/config/showEditAlert.do?"
                        + RequestParams.ALERT_ID + "=" + alertConfig.getAlertId();
            %>
            <jmhtml:link href="/jmanage/<%=editAlertLink%>" acl="<%=ACLConstants.ACL_EDIT_ALERT%>" styleClass="a1">Edit</jmhtml:link>
        </td>
        <td align="right" width="60">
        <%
            String deleteAlertLink = "JavaScript:deleteAlert('"
                    + alertConfig.getAlertId() + "','" + appConfig.getApplicationId() + "');";
        %>
           <jmhtml:link href="/jmanage/<%=deleteAlertLink%>" acl="<%=ACLConstants.ACL_EDIT_ALERT%>"  styleClass="a1">
            Delete</jmhtml:link>
       </td>
    </tr>
    <%}%>
</table>
<%
}else{
%>
<p class="plaintext">
    There are no configured alerts.
</p>
<%}%>
<p>
<jmhtml:link href="<%=request.getContextPath() + "/jmanage/config/showSelectAlertSourceType.do" %>" acl="<%=ACLConstants.ACL_ADD_ALERT%>" styleClass="a">
    Add Alert</jmhtml:link>


