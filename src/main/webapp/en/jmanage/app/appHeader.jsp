<!-- /app/appHeader.jsp -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.WebContext,
                 org.jmanage.core.management.ObjectName,
                 org.jmanage.webui.util.RequestAttributes,
                 java.util.List,
                 java.util.LinkedList,
                 java.util.Iterator,
                 java.net.URLEncoder"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/struts/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<script language="JavaScript1.1" type="text/javascript">

    function showDescription(description){
        if(description.length == 0){
            description = "No description available.";
        }
        alert(description);
    }
</script>

<%!
    private static class Navigation{
        String name;
        String link;
        Navigation(String name, String link){
            this.name = name;
            this.link = link;
        }
    }

%>
<%
    WebContext webContext = WebContext.get(request);
    ApplicationConfig appConfig = webContext.getApplicationConfig();
    ApplicationConfig clusterConfig = null;
    if(appConfig != null)
        clusterConfig = appConfig.getClusterConfig();
    ObjectName objName = webContext.getObjectName();
    String currentNavPage =
            (String)request.getAttribute(RequestAttributes.NAV_CURRENT_PAGE);

    List navList = new LinkedList();
    if(currentNavPage != null)
        navList.add(0, new Navigation(currentNavPage, null));

    if(objName != null){
        String link = "/app/mbeanView.do?" + RequestParams.APPLICATION_ID +
                "=" + appConfig.getApplicationId() + "&" +
                RequestParams.OBJECT_NAME + "=" + URLEncoder.encode(objName.getDisplayName(), "UTF-8");
        navList.add(0, new Navigation(objName.getShortName(), link));
    }

    if(appConfig != null){
        String link = "/app/appView.do?" + RequestParams.APPLICATION_ID +
                "=" + appConfig.getApplicationId();
        navList.add(0, new Navigation(appConfig.getName(), link));
    }

    if(clusterConfig != null){
        String link = "/app/appView.do?" + RequestParams.APPLICATION_ID +
                "=" + clusterConfig.getApplicationId();
        navList.add(0, new Navigation(clusterConfig.getName(), link));
    }

    navList.add(0, new Navigation("Applications", "/config/managedApplications.do"));
%>

<div id="breadcrumb">
    <%
        for(Iterator it=navList.iterator(); it.hasNext(); ){
            Navigation nav = (Navigation)it.next();
            if(it.hasNext()){
    %>
                <a href="<%= request.getContextPath() %>/jmanage/<%=nav.link%>"><%=nav.name%></a>&nbsp;&gt;&nbsp;
    <%
            }else{
                // last element should be without link
    %>
                <%=nav.name%>
    <%
            }
        }
    %>
</div>

<br/>