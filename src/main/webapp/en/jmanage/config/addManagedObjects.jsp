<!--    /config/addManagedObjects.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.List,
                 java.util.Iterator,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.RequestAttributes,
                 java.net.URLEncoder,
                 org.jmanage.core.management.ObjectName,
                 java.util.Map,
                 java.util.Set,
                 org.jmanage.webui.util.WebContext"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/struts/struts-bean.tld" prefix="bean"%>
<%
    final WebContext webContext = WebContext.get(request);
    ApplicationConfig applicationConfig = webContext.getApplicationConfig();
    boolean hasMbeans = false;
%>

<p>
<jmhtml:form action="/config/startAddMultiMBeanConfig" method="post">
    <jmhtml:text property="objectName" />&nbsp;&nbsp;<jmhtml:submit styleClass="Inside3d" value="Filter by object name" />
</jmhtml:form>
</p>

<jmhtml:errors />
<jmhtml:form action="/config/addMultiMBeanConfig" method="post">
<input type="hidden" name="multiMBeanConfig" value="true" />
<input type="hidden" name="objectName" value="<%=request.getParameter("objectName") == null ? "" : request.getParameter("objectName")%>"/>
<br/>
    <%if(applicationConfig.isCluster()){%>
        <jmhtml:hidden property="applicationCluster" value="true" />
    <%}else{%>
        <jmhtml:hidden property="applicationCluster" value="false" />
    <%}%>
<%
    Map domainToObjectNameListMap = (Map)request.getAttribute("domainToObjectNameListMap");
    for(Iterator it = domainToObjectNameListMap.keySet().iterator(); it.hasNext(); ){
        String domain = (String)it.next();
%>
<table border="0" cellspacing="0" cellpadding="5" width="650" class="table">
    <tr class="tableHeader">
        <td colspan="2"><%=domain%></td>
    </tr>
        <%
        Set objectNameList = (Set)domainToObjectNameListMap.get(domain);
        for(Iterator objectNameIt = objectNameList.iterator(); objectNameIt.hasNext();){
            hasMbeans = true;
            String objectName = (String)objectNameIt.next();
            pageContext.setAttribute("objectName",
                    domain + ":" + objectName, PageContext.PAGE_SCOPE);
            String configuredName = request.getParameter(objectName);
            configuredName = configuredName == null ? "" : configuredName;
        %>
    <tr>
        <td class="plaintext">
                <jmhtml:link action="/app/mbeanView"
                             paramId="objName"
                             paramName="objectName">
                    <%=objectName%></jmhtml:link>
        </td>
        <td align="right">
            <input type="hidden" name="name" value="<%=pageContext.getAttribute("objectName", PageContext.PAGE_SCOPE)%>" />
            <input type="text" size="50" name="<%=pageContext.getAttribute("objectName", PageContext.PAGE_SCOPE)%>" value="<%=configuredName%>"/>
        </td>
    </tr>
<%      } // inner for%>
</table>
<br/>
<%  } // outer for%>
<%if(hasMbeans){%>
<div bgcolor="#E6EEF9" align="left">
    <jmhtml:submit styleClass="Inside3d" value="Add" />
    &nbsp;&nbsp;&nbsp;
    <jmhtml:button property="" value="Cancel"
            onclick="JavaScript:history.back();" styleClass="Inside3d" />
</div>
<%}%>
</jmhtml:form>