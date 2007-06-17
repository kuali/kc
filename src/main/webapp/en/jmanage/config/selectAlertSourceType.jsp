<!--    /config/selectAlertSource.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.webui.util.WebContext,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.config.AlertDeliveryConstants,
                 org.jmanage.core.config.AlertSourceConfig"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<jmhtml:form action="/config/selectAlertSourceType" method="post">

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
    <td>Select Alert Source</td>
</tr>
<tr>
    <td class="plaintext"><jmhtml:radio property="alertSourceType"
                      value="<%=AlertSourceConfig.SOURCE_TYPE_NOTIFICATION%>"/>
                      <%=AlertSourceConfig.getSourceTypeDescription(AlertSourceConfig.SOURCE_TYPE_NOTIFICATION)%></td>
</tr>
<tr>
    <td class="plaintext"><jmhtml:radio property="alertSourceType"
                      value="<%=AlertSourceConfig.SOURCE_TYPE_GAUGE_MONITOR%>"/>
                      <%=AlertSourceConfig.getSourceTypeDescription(AlertSourceConfig.SOURCE_TYPE_GAUGE_MONITOR)%></td>
</tr>
<tr>
    <td class="plaintext"><jmhtml:radio property="alertSourceType"
                      value="<%=AlertSourceConfig.SOURCE_TYPE_STRING_MONITOR%>"/>
                      <%=AlertSourceConfig.getSourceTypeDescription(AlertSourceConfig.SOURCE_TYPE_STRING_MONITOR)%></td>
</tr>
<tr>
    <td align="center" colspan="2">
        <jmhtml:submit property="" value="Next" styleClass="Inside3d" />
        &nbsp;&nbsp;&nbsp;
        <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
    </td>
</tr>
</table>
</jmhtml:form>
