<!--    /config/addAlert.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.core.config.AlertDeliveryConstants,
                 java.util.List,
                 org.jmanage.core.config.ApplicationConfigManager,
                 org.jmanage.webui.util.WebContext,
                 org.jmanage.core.config.ApplicationConfig,
                 org.jmanage.webui.util.RequestParams,
                 org.jmanage.core.config.AlertSourceConfig,
                 org.jmanage.core.management.ObjectName,
                 org.jmanage.webui.util.Utils"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<jmhtml:javascript formName="alertForm" />
<jmhtml:errors />
<jmhtml:form action="/config/addAlert" method="post"
                                    onsubmit="return validateAlertForm(this)">
<jmhtml:hidden property="alertId" />
<jmhtml:hidden property="expression" />

<table cellspacing="0" cellpadding="5" width="400" class="table">
<tr class="tableHeader">
<%
    if(request.getParameter(RequestParams.ALERT_ID)!=null){
%>
    <td colspan="2">Edit Alert</td>
<%
    }else{
%>
    <td colspan="2">Add Alert</td>
<%}%>
</tr>

<tr>
    <td class="headtext1">Name:</td>
    <td><jmhtml:text property="alertName" /></td>
</tr>
<tr>
    <td class="headtext1">Alert Delivery:</td>
    <td><jmhtml:select property="alertDelivery" multiple="true">
            <jmhtml:option value="<%=AlertDeliveryConstants.EMAIL_ALERT_DELIVERY_TYPE%>"/>
            <jmhtml:option value="<%=AlertDeliveryConstants.CONSOLE_ALERT_DELIVERY_TYPE%>"/>
        </jmhtml:select>
    </td>
</tr>
<tr>
    <td class="headtext1">Email Address:</td>
    <td><jmhtml:text property="emailAddress" /></td>
</tr>
<tr>
    <td class="headtext1"><nobr>Alert Source Type:</nobr></td>
    <td class="plaintext">
        <%=AlertSourceConfig.getSourceTypeDescription((String)request.getAttribute("alertSourceType"))%>
        <jmhtml:hidden property="alertSourceType"/>
    </td>
</tr>
<tr>
    <td class="headtext1"><nobr>Alert Source MBean:</nobr></td>
    <td class="plaintext">
    <%
        if(request.getParameter(RequestParams.ALERT_ID)!=null){
    %>

            <a href="<%= request.getContextPath() %>/jmanage/app/mbeanView.do?<%=RequestParams.OBJECT_NAME%>=<%=Utils.urlEncode((String)request.getAttribute("sourceMBean"))%>&<%=RequestParams.APPLICATION_ID%>=<%=request.getParameter(RequestParams.APPLICATION_ID)%>" class="a1">
                <%=ObjectName.getShortName((String)request.getAttribute("sourceMBean"))%>
            </a>
    <%
        }else{
    %>
            <%=ObjectName.getShortName((String)request.getAttribute("sourceMBean"))%>
    <%
        }
    %>
    </td>
</tr>
<%
    String sourceType = (String)request.getAttribute("alertSourceType");
    if(sourceType.equals(AlertSourceConfig.SOURCE_TYPE_NOTIFICATION)){
%>
<tr>
    <td class="headtext1">Notification Type:</td>
    <td class="plaintext">
        <%=request.getAttribute("notificationType")%>
    </td>
</tr>
<%
    }else if(sourceType.equals(AlertSourceConfig.SOURCE_TYPE_GAUGE_MONITOR)
            || sourceType.equals(AlertSourceConfig.SOURCE_TYPE_STRING_MONITOR)){
%>
<tr>
    <td class="headtext1">Attribute Name:</nobr></td>
    <td class="plaintext">
        <%=request.getAttribute("attribute")%>
    </td>
</tr>
<tr>
    <td class="headtext1">Current Value:</nobr></td>
    <td class="plaintext">
        <%=request.getAttribute("currentAttrValue")%>
    </td>
</tr>
<%
    if(sourceType.equals(AlertSourceConfig.SOURCE_TYPE_GAUGE_MONITOR)){
%>
<tr>
    <td class="headtext1">Minimum Attribute Value</nobr></td>
    <td class="plaintext"><jmhtml:text property="minAttributeValue"/>
</tr>
<tr>
    <td class="headtext1">Maximum Attribute Value</nobr></td>
    <td class="plaintext"><jmhtml:text property="maxAttributeValue"/>
</tr>
<%
    } else if(sourceType.equals(AlertSourceConfig.SOURCE_TYPE_STRING_MONITOR)){
%>

<tr>
    <td class="headtext1">Attribute Value</nobr></td>
    <td class="plaintext"><jmhtml:text property="stringAttributeValue"/>
</tr>
<%
    }
    }
%>

<tr>
    <td align="center" colspan="2">
        <jmhtml:submit property="" value="Save" styleClass="Inside3d" />
        &nbsp;&nbsp;&nbsp;
        <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
    </td>
</tr>
</table>
</jmhtml:form>
