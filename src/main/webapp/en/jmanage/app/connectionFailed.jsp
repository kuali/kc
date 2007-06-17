<!--    /app/connectionFailed.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.WebContext"%>
<%
    ApplicationConfig applicationConfig = null;
    WebContext webContext = null;
    try {
        webContext = WebContext.get(request);
        applicationConfig = webContext.getApplicationConfig();
        if(applicationConfig == null)
            throw new RuntimeException("Missing request param: " +
                    RequestParams.APPLICATION_ID);
    } finally {
        webContext.releaseResources();
    }

%>
<table cellspacing="0" cellpadding="5" width="600" class="table">
<%if(!applicationConfig.isCluster()){%>
    <tr class="tableHeader">
        <td>Connection to the application failed</td>
    </tr>
    <tr>
        <td class="plaintext">
            Please check the following:
            <ol>
                <li class="plaintext">Application is running.</li>
                <li class="plaintext">Application is correctly configured in jManage.
                Click
                <a href="<%= request.getContextPath() %>/jmanage/config/showEditApplication.do?<%=RequestParams.APPLICATION_ID+"="+applicationConfig.getApplicationId()%>" class="a1">here</a>
                to edit configuration.
                </li>
            </ol>
        </td>
    </tr>
<%}else{%>
    <tr class="tableHeader">
        <td>Connection to the application cluster failed</td>
    </tr>
    <tr>
        <td class="plaintext">
            Please check the following:
            <ol>
                <li class="plaintext">At least one application in the cluster is running.</li>
                <li class="plaintext">Application cluster is correctly configured in jManage.
                Click
                <a href="<%= request.getContextPath() %>/jmanage/config/showApplicationCluster.do?<%=RequestParams.APPLICATION_ID+"="+applicationConfig.getApplicationId()%>" class="a1">here</a>
                to edit configuration.
                </li>
            </ol>
        </td>
    </tr>
<%}%>
</table>
