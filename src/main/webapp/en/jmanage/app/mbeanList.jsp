<!--    /app/mbeanList.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.List,
                 java.util.Iterator,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.RequestAttributes,
                 java.net.URLEncoder,
                 org.jmanage.core.management.ObjectName,
                 java.util.Map,
                 java.util.Set"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/struts/struts-bean.tld" prefix="bean"%>

<div bgcolor="#E6EEF9">
<jmhtml:form action="/app/mbeanList" method="post">
    <jmhtml:text property="objectName" />&nbsp;&nbsp;<jmhtml:submit styleClass="Inside3d" value="Filter by object name" />
</jmhtml:form>
</div>
<br/>
<%
    Map domainToObjectNameListMap = (Map)request.getAttribute("domainToObjectNameListMap");
    for(Iterator it = domainToObjectNameListMap.keySet().iterator(); it.hasNext(); ){
        String domain = (String)it.next();
        %>
<table border="0" cellspacing="0" cellpadding="5" width="900" class="table">
    <tr class="tableHeader">
        <td colspan="2"><%=domain%></td>
    </tr>
        <%
        Set objectNameList = (Set)domainToObjectNameListMap.get(domain);
        for(Iterator objectNameIt = objectNameList.iterator(); objectNameIt.hasNext();){
            String objectName = (String)objectNameIt.next();
            pageContext.setAttribute("objectName",
                    domain + ":" + objectName, PageContext.PAGE_SCOPE);
%>
    <tr>
        <td class="plaintext">
                <jmhtml:link action="/app/mbeanView"
                             paramId="objName"
                             paramName="objectName">
                    <%=objectName%></jmhtml:link>
        </td>
   </tr>
<%      } // inner for
%>
</table>
<br/>
<%
    } // outer for
%>
