<!--/app/graphView.jsp-->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.Utils,
                 org.jmanage.core.config.GraphConfig,
                 org.jmanage.core.util.JManageProperties,
                 org.jmanage.webui.applets.GraphAppletParameters"%>
<%
    GraphConfig graphConfig = (GraphConfig)request.getAttribute("graphConfig");
    StringBuffer remoteURL = request.getRequestURL();
    int i = remoteURL.indexOf(request.getRequestURI());
    remoteURL.delete(i, remoteURL.length());
%>

<p>
<applet
    code="org/jmanage/webui/applets/GraphApplet.class"
    archive="/applets/applets.jar,/applets/jfreechart-0.9.20.jar,/applets/jcommon-0.9.5.jar"
    width="600"
    height="500">
    <param name="<%=GraphAppletParameters.GRAPH_TITLE%>"
           value="<%=graphConfig.getName()%>"/>
    <param name="<%=GraphAppletParameters.POLLING_INTERVAL%>"
           value="<%=graphConfig.getPollingInterval()%>"/>
    <param name="<%=GraphAppletParameters.REMOTE_URL%>"
           value="<%=remoteURL%>/app/fetchAttributeValues.do;jsessionid=<%=Utils.getCookieValue(request, "JSESSIONID")%>"/>
    <param name="<%=GraphAppletParameters.ATTRIBUTE_DISPLAY_NAMES%>"
           value="<%=graphConfig.getAttributeDisplayNames()%>"/>
    <param name="<%=GraphAppletParameters.ATTRIBUTES%>"
           value='<%=graphConfig.getAttributesAsString()%>'/>
    <param name="<%=GraphAppletParameters.Y_AXIS_LABEL%>"
           value='<%=graphConfig.getYAxisLabel()%>'/>
<%
    if(graphConfig.getScaleFactor() != null){
%>
    <param name="<%=GraphAppletParameters.SCALE_FACTOR%>"
           value='<%=graphConfig.getScaleFactor()%>'/>
<%
    }
    if(graphConfig.isScaleUp() != null){
%>
    <param name="<%=GraphAppletParameters.SCALE_UP%>"
           value='<%=graphConfig.isScaleUp()%>'/>
<%
    }
%>
</applet>
</p>
