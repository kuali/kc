<!--    /config/managedApplications.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.Map,
                 org.jmanage.webui.util.RequestAttributes,
                 java.util.Set,
                 org.jmanage.core.config.ApplicationConfig,
                 java.util.Iterator,
                 org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.config.MBeanConfig,
                 java.net.URLEncoder,
                 java.util.List,
                 org.jmanage.core.alert.AlertInfo"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<script language="JavaScript">
    function deleteApplication(appId, isCluster){
        var msg;
        if(isCluster){
            msg = "Are you sure you want to delete this Application Cluster and all child applications?";
        }else{
            msg = "Are you sure you want to delete this Application?";
        }
        if(confirm(msg) == true){
            location = '<%= request.getContextPath() %>/jmanage/config/deleteApplication.do?<%=RequestParams.APPLICATION_ID%>=' + appId + '&refreshApps=true';
        }
    }
</script>
<table cellspacing="0" cellpadding="5" width="600" class="table">
    <tr class="tableHeader">
        <td colspan="4">Managed Applications</td>
    </tr>
<%
    List applications = (List)request.getAttribute(RequestAttributes.APPLICATIONS);
    Iterator iterator = applications.iterator();
    while(iterator.hasNext()){
        ApplicationConfig applicationConfig = (ApplicationConfig)iterator.next();
%>
  <tr>
    <%
        String appHref = "/app/appView.do?" +
                RequestParams.APPLICATION_ID + "=" + applicationConfig.getApplicationId();
    %>
    <td class="plaintext">
        <a href="<%= request.getContextPath() %>/jmanage/<%=appHref%>"><%=applicationConfig.getName()%></a>
    </td>
    <td class="plaintext">
        <%if(!applicationConfig.isCluster()){%>
            <%=applicationConfig.getURL()%>
        <%}else{%>
            &nbsp;
        <%}%>
    </td>
    <%
      String href = null;
      if(!applicationConfig.isCluster()){
        href = "/config/showEditApplication.do";
      }else{
        href = "/config/showApplicationCluster.do";
      }%>
    <td align="right"><a href="<%= request.getContextPath() %>/jmanage/<%=href%>?<%=RequestParams.APPLICATION_ID+"="+applicationConfig.getApplicationId()%>" class="a1">Edit</a></td>
    <td align="right" width="60"><a href="JavaScript:deleteApplication('<%=applicationConfig.getApplicationId()%>', <%=applicationConfig.isCluster()%>);" class="a1">Delete</a></td>
  </tr>
  <%-- if this is a cluster, display the child applications as well --%>
      <%
      if(applicationConfig.isCluster()){
        for(Iterator childApps=applicationConfig.getApplications().iterator(); childApps.hasNext();){
            ApplicationConfig childAppConfig = (ApplicationConfig)childApps.next();
            appHref = "/app/appView.do" +
                "?" + RequestParams.APPLICATION_ID + "=" + childAppConfig.getApplicationId();
      %>
          <tr>
            <td class="plaintext">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="<%= request.getContextPath() %>/jmanage/<%=appHref%>"><%=childAppConfig.getName()%></a>
            </td>
            <td class="plaintext">
                <%=childAppConfig.getURL()%>
            </td>
            <td align="right"><a href="<%= request.getContextPath() %>/jmanage/config/showEditApplication.do?<%=RequestParams.APPLICATION_ID+"="+childAppConfig.getApplicationId()%>" class="a1">Edit</a></td>
            <td align="right" width="60"><a href="JavaScript:deleteApplication('<%=childAppConfig.getApplicationId()%>', false);" class="a1">Delete</a></td>
          </tr>
      <%
        }
      }
      %>
  <%}//while ends %>
</table>
<br>
<%-- don't use the link tag here, as it adds applicationId request param --%>
<a href="<%= request.getContextPath() %>/jmanage/config/showAvailableApplications.do" class="a">Add Application</a>
<br>
<a href="<%= request.getContextPath() %>/jmanage/config/showApplicationCluster.do" class="a">Add Application Cluster</a>
<br><br>

<%
    List alerts = (List)request.getAttribute("alerts");
    if(alerts.size() == 0){
%>
<p class="plaintext">
    There are no alerts.
</p>
<%
    }else{
%>

<table cellspacing="0" cellpadding="5" width="800" class="table">
    <tr class="tableHeader">
        <td colspan="7">Triggered Alerts</td>
    </tr>
    <tr>
        <td class="headtext">Timestamp</td>
        <td class="headtext">Application</td>
        <td class="headtext">Alert Name</td>
        <td class="headtext">Message</td>
        <td class="headtext">Source</td>
        <td class="headtext">&nbsp;</td>
    </tr>
<%
        for(Iterator it=alerts.iterator(); it.hasNext(); ){
            AlertInfo alert = (AlertInfo)it.next();
%>
    <tr>
        <td class="plaintext"><%=alert.getFormattedTimeStamp()%></td>
        <td class="plaintext"><%=alert.getApplicationName()%></td>
        <td class="plaintext"><%=alert.getAlertName()%></td>
        <td class="plaintext"><%=alert.getMessage()%></td>
        <td class="plaintext">
        <a href="<%= request.getContextPath() %>/jmanage/app/mbeanView.do?<%=RequestParams.APPLICATION_ID%>=<%=alert.getApplicationId()%>&<%=RequestParams.OBJECT_NAME%>=<%=URLEncoder.encode(alert.getObjectName(), "UTF-8")%>">
            <%=alert.getObjectName()%></a>
        </td>
        <td class="plaintext"><a href="<%= request.getContextPath() %>/jmanage/app/removeConsoleAlert.do?alertId=<%=alert.getAlertId()%>">Remove</a></td>
    </tr>
<%
        }
    }
%>
</table>


