<!--    /app/mbeanView.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.core.management.*,
                 org.jmanage.webui.util.RequestParams,
                 org.jmanage.webui.util.WebContext,
                 org.jmanage.core.auth.User,
                 org.jmanage.webui.util.MBeanUtils,
                 org.jmanage.core.config.ApplicationConfig,
                 java.util.*,
                 org.jmanage.core.util.ACLConstants,
                 org.jmanage.core.services.AccessController,
                 org.jmanage.webui.util.Utils,
                 java.net.URLEncoder,
                 org.jmanage.util.StringUtils,
                 org.jmanage.core.util.Expression,
                 java.lang.reflect.Array,
                 org.jmanage.core.util.JManageProperties"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>
<%!
    // TODO: This should be moved to some utility class
    private Class getClass(String type){
        if(type.equals("boolean"))
             return Boolean.class;
        if(type.equals("byte"))
             return Byte.TYPE;
        if(type.equals("char"))
             return Character.class;
        if(type.equals("double"))
             return Double.class;
        if(type.equals("float"))
             return Float.class;
        if(type.equals("int"))
             return Integer.class;
        if(type.equals("long"))
             return Long.class;
        if(type.equals("short"))
             return Short.class;
        if(type.equals("void"))
             return Void.class;
        Class clazz = null;
        try{
            clazz = Class.forName(type);
        }catch(ClassNotFoundException e){
            // ignore exception
        }
        return clazz;
    }

    private boolean isNumber(String type){
        Class clazz = getClass(type);
        if(clazz != null && Number.class.isAssignableFrom(clazz)){
            return true;
        }
        return false;
    }
%>
<%
    boolean showGraphOption = false;
    final WebContext webContext = WebContext.get(request);

    ObjectInfo objectInfo = (ObjectInfo)request.getAttribute("objInfo");
    ObjectAttributeInfo[] attributes = objectInfo.getAttributes();
    ObjectOperationInfo[] operations = objectInfo.getOperations();
    ObjectNotificationInfo[] notifications = objectInfo.getNotifications();

    ApplicationConfig applicationConfig = webContext.getApplicationConfig();
    Map appConfigToAttrListMap =
            (Map)request.getAttribute("appConfigToAttrListMap");
%>
<script type="text/javascript" language="Javascript1.1">
<!--
    function addToApplicationCluster(){
        document.mbeanConfigForm.applicationCluster.value='true';
        document.mbeanConfigForm.submit();
        return;
    }

    // onclick event handler for checkboxes representing boolean attributes
    function onclick_booleanCheckbox(formName, checkbox) {
        var formObj = document.forms[formName];
        formObj.elements[checkbox.value].value = checkbox.checked;
    }
-->
</script>
<jmhtml:errors />
<table class="table" border="0" cellspacing="0" cellpadding="5" width="900">
    <tr>
        <td class="headtext" width="100" valign="top">Object Name</td>
        <td class="plaintext" valign="top" nowrap="true">
            <pre class="plaintext"><%=objectInfo.getObjectName().getWrappedName()%></pre>
            <%-- If this mbean is being viewed from an application which is
                part of a cluster, provide a link to view this mbean as
                part of the cluster --%>
            <%if(applicationConfig.getClusterConfig() != null){%>
                <a href="<%= request.getContextPath() %>/jmanage/app/mbeanView.do?<%=RequestParams.APPLICATION_ID%>=<%=applicationConfig.getClusterConfig().getApplicationId()%>&<%=RequestParams.OBJECT_NAME%>=<%=URLEncoder.encode(objectInfo.getObjectName().getDisplayName(), "UTF-8")%>">
                    Cluster View</a>
            <%}%>
        </td>
    </tr>
    <tr>
        <td class="headtext" width="100">Class Name</td>
        <td class="plaintext"><c:out value="${requestScope.objInfo.className}" /></td>
    </tr>
    <tr>
        <td class="headtext" width="100">Description</td>
        <td class="plaintext"><c:out value="${requestScope.objInfo.description}" /></td>
    </tr>
    <tr>
        <td class="headtext" width="150" valign="top">Configured Name</td>
        <td class="plaintext">
            <c:choose>
                <c:when test="${requestScope.mbeanIncludedIn != null}">
                    <jmhtml:form action="/config/removeMBeanConfig">
                        <jmhtml:hidden property="objectName"/>
                        <jmhtml:hidden property="refreshApps" value="true"/>
                        <c:out value="${requestScope.mbeanConfig.name}"/>&nbsp;
                        <a href="JavaScript:document.mbeanConfigForm.submit();" class="a1">
                            Remove from Application <c:if test="${requestScope.mbeanIncludedIn == 'cluster'}">Cluster</c:if></a>
                    </jmhtml:form>
                </c:when>
                <c:otherwise>
                    <%if(!applicationConfig.isCluster()){%>
                    <jmhtml:form action="/config/addMBeanConfig">
                        <input type="text" name="name"/>
                        <jmhtml:hidden property="objectName"/>
                        <jmhtml:hidden property="refreshApps" value="true"/>
                        <jmhtml:hidden property="applicationCluster"/>
                        <a href="JavaScript:document.mbeanConfigForm.submit();" class="a1">Add to Application</a>
                        <%if(applicationConfig.getClusterConfig() != null){%>
                        &nbsp;Or&nbsp;
                        <a href="JavaScript:addToApplicationCluster();" class="a1">Add to Application Cluster</a>
                        <%}%>
                    </jmhtml:form>
                    <%}%>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

<%
    if(attributes.length > 0){
        boolean showUpdateButton = false;
        int columns = 5;
%>

    <script type="text/javascript">
        function writeInputElements(inputArray, divSuffix, inputArrayName){
            var html = "";
            for (x in inputArray) {
                html += inputArray[x] + "<a href=\"JavaScript:onRemove(" + inputArrayName + ",'" + divSuffix + "','" + inputArrayName + "',"  + x + ")\" class='a3'>x</a>" + "<br />";
                //alert(inputArray[x] + "<a href=\"JavaScript:onRemove(" + inputArrayName + ",'" + divSuffix + "','" + inputArrayName + "'," + x + ")\" class='a3'>x</a>" + "<br />");
            }
            //alert(html);
            var divElement = document.getElementById('inputBoxes+' + divSuffix);
            divElement.innerHTML = html;
         }

        function onRemove(inputArray, divSuffix, inputArrayName, index){
           //alert("inputArray=" + inputArray);
           //alert("divSuffix=" + divSuffix);
           //alert("index=" + index);
            inputArray.splice(index, 1);
            writeInputElements(inputArray, divSuffix, inputArrayName);
        }

        function onAdd(inputArray, divSuffix, inputArrayName){
            inputArray.push("<input type='text' name='attr+" + divSuffix + "' size='50'/>");
            writeInputElements(inputArray, divSuffix, inputArrayName);
        }
    </script>
<br/>
<jmhtml:form action="/app/updateAttributes" method="post">
<table class="table" border="0" cellspacing="0" cellpadding="5">
<tr class="tableHeader">
    <td><b>Name</b></td>
<%
    if(applicationConfig.isCluster()){
        columns = columns + (applicationConfig.getApplications().size() - 1);
        for(Iterator it=applicationConfig.getApplications().iterator(); it.hasNext();){
            ApplicationConfig childAppConfig = (ApplicationConfig)it.next();
%>
    <td><b><%=childAppConfig.getName()%></b></td>
<%
        }
    }else{
%>
    <td><b>Value</b></td>
<%
    }
%>
    <td><b>RW</b></td>
    <td colspan="2"><b>Type</b></td>
</tr>
<%
    for(int index=0; index < attributes.length; index++){
        ObjectAttributeInfo attributeInfo = attributes[index];
%>
<tr>
<td class="plaintext" valign="top">
    <%if(attributeInfo.getDescription() != null){%>
        <a href="JavaScript:showDescription('<%=MBeanUtils.jsEscape(attributeInfo.getDescription())%>');"><%=attributeInfo.getName()%></a>
    <%}else{%>
        <%=attributeInfo.getName()%>
    <%}%>
</td>
<%
        List childApplications = null;
        if(applicationConfig.isCluster()){
            childApplications = applicationConfig.getApplications();
        }else{
            childApplications = new ArrayList(1);
            childApplications.add(applicationConfig);
        }

        for(Iterator it=childApplications.iterator(); it.hasNext();){
            ApplicationConfig childAppConfig = (ApplicationConfig)it.next();
            List attributeList = (List)appConfigToAttrListMap.get(childAppConfig);
    %>
<td class="plaintext" valign="top">
        <%if(attributeList != null){
            ObjectAttribute objAttribute =
                        MBeanUtils.getObjectAttribute(attributeList, attributeInfo);
            String attrValue = objAttribute.getDisplayValue();
            if(AccessController.canAccess(webContext.getServiceContext(),
                        ACLConstants.ACL_UPDATE_MBEAN_ATTRIBUTES,
                        attributeInfo.getName()) &&
                    attributeInfo.isWritable() &&
                    MBeanUtils.isDataTypeEditable(attributeInfo.getType()) &&
                    objAttribute.getStatus() == ObjectAttribute.STATUS_OK){
                showUpdateButton = true;
            %>
                <%if(attributeInfo.getType().equals("boolean") || attributeInfo.getType().equals("java.lang.Boolean")){
                    String attrName = "attr+" + childAppConfig.getApplicationId() + "+" + attributeInfo.getName();%>

                    <%if (JManageProperties.isBooleanInputTypeRadio()) { %>
                        <input type="radio" name="<%=attrName%>" value="true" <%=attrValue.equals("true")?" CHECKED":""%> />&nbsp;True
                        &nbsp;&nbsp;&nbsp;<input type="radio" name="<%=attrName%>" value="false" <%=attrValue.equals("false")?" CHECKED":""%>/>&nbsp;False
                    <%} else if (JManageProperties.isBooleanInputTypeSelect()) { %>
                        <select name="<%=attrName%>">
                            <option value="false" <%=attrValue.equals("false")?" SELECTED":""%>>False</option>
                            <option value="true" <%=attrValue.equals("true")?" SELECTED":""%>>True</option>
                        </select>
                    <%} else { %>
                        <input type="checkbox" name="dummy" value="<%=attrName%>" onClick="onclick_booleanCheckbox('emptyForm', this)" <%=attrValue.equals("true")?" CHECKED":""%>/>
                        <input type="hidden"   name="<%=attrName%>" value="<%=attrValue%>"/>
                    <%}%>

                <%}else if(MBeanUtils.isEditableArrayType(attributeInfo.getType())){
                    final int arrayLength = Array.getLength(objAttribute.getValue());
                    final String inputArrayName = "ia_" + childAppConfig.getApplicationId() + "_" + attributeInfo.getName();
                %>
                  <%-- The first field is a hidden field, to allow an empty array to be saved. There is special handling for this in MBeanServiceImpl. --%>
                  <input type='hidden' name='attr+<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>' size='50' value='NONE'/>
                  <script type="text/javascript">

                    var <%=inputArrayName%> = new Array(<%=arrayLength%>);
                  <%
                    for(int i=0; i<arrayLength; i++){
                  %>
                        <%=inputArrayName%>[<%=i%>]="<input type='text' name='attr+<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>' size='50' value='<%=Array.get(objAttribute.getValue(), i)%>'/>";
                  <%
                    }
                  %>
                  </script>
                  <div class="plaintext" id="inputBoxes+<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>">
                  </div>
                  <script type="text/javascript">
                    writeInputElements(<%=inputArrayName%>, '<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>', '<%=inputArrayName%>');
                  </script>
                  <a href="JavaScript:onAdd(<%=inputArrayName%>, '<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>', '<%=inputArrayName%>')" class="a3">+</a>
                <%}else if(attrValue.indexOf('\n') != -1){%>
                    <textarea name="attr+<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>" rows="3" cols="40"><%=attrValue%></textarea>
                <%}else{%>
                    <input type="text" name="attr+<%=childAppConfig.getApplicationId()%>+<%=attributeInfo.getName()%>" size="50"
                    value="<%=attrValue%>"/>
                <%}%>
            <%}else{%>
                <pre class="plaintext"><%=attrValue%></pre>
            <%}%>
            <%if(attrValue.length() > 0 && attributeInfo.getType().equals("javax.management.ObjectName")){
                pageContext.setAttribute("objectName",
                        attrValue, PageContext.PAGE_SCOPE);
                // provide a link to this mbean
            %>
                <jmhtml:link action="/app/mbeanView"
                             paramId="objName"
                             paramName="objectName">
                    View</jmhtml:link>
            <%}%>
        <%}else{%>
            &lt;unavailable&gt;
        <%}%>
</td>
<%
}
%>
<td class="plaintext" valign="top">
    <%=attributeInfo.getReadWrite()%>
</td>
<td class="plaintext" valign="top">
    <%=attributeInfo.getDisplayType()%>
</td>
<td class="plaintext" valign="top">
    <%  if(!applicationConfig.isCluster()){
        Expression expression = new Expression("",request.getParameter("objName"), attributeInfo.getName());%>
    <c:set var="expressionValue" scope="page">
        <%=expression%>
    </c:set>
    <%if("java.lang.String".equals(attributeInfo.getType())){%>
        <jmhtml:link href="/jmanage/config/showAddAlert.do?alertSourceType=string"
            paramId="attributes" paramName="expressionValue"
            acl="<%=ACLConstants.ACL_ADD_ALERT%>" styleClass="a1">Monitor</jmhtml:link>
    <%}else if(isNumber(attributeInfo.getType())){
        showGraphOption = true;%>
        <jmhtml:link href="/jmanage/config/showAddAlert.do?alertSourceType=gauge"
            paramId="attributes" paramName="expressionValue"
            acl="<%=ACLConstants.ACL_ADD_ALERT%>" styleClass="a1">Monitor</jmhtml:link>
    <%}
    }%>
</td>
</tr>
<%  }// for ends%>
<tr>
    <td class="plaintext" colspan="<%=columns-2 %>">
        To save the changes to the attribute values click on
        <%if(showUpdateButton){%>
            <jmhtml:submit value="Save" styleClass="Inside3d" />
        <%}else{%>
            <jmhtml:submit value="Save" styleClass="Inside3d" disabled="true" />
        <%}%>
    </td>
    <td class="plaintext" colspan="2">
    <%if(showGraphOption){
        String link = "/config/showAttributes.do?"
                + RequestParams.END_URL + "=" + Utils.urlEncode("/config/showAddGraph.do")
                + "&" + RequestParams.MULTIPLE + "=true&"
                + RequestParams.DATA_TYPE + "=java.lang.Number&"
                + RequestParams.DATA_TYPE + "=javax.management.openmbean.CompositeData&"
                + RequestParams.NAVIGATION + "=" + Utils.urlEncode("Add Graph")+"&"
                + RequestParams.MBEANS + "=" + request.getParameter("objName");
    %>
        <jmhtml:link href="<%=link%>" acl="<%=ACLConstants.ACL_ADD_GRAPH%>"
            styleClass="a">Plot Graph</jmhtml:link>
    <%}%>
    </td>
</tr>
</table>
</jmhtml:form>
<%
    }
%>
<%if(operations.length > 0){%>
<br/>
<table class="table" border="0" cellspacing="0" cellpadding="5" width="900">
<tr class="tableHeader">
    <td colspan="3">Operations</td>
</tr>
<%
    int tabIndex=1;
    for(int index=0; index < operations.length; index++){
        ObjectOperationInfo operationInfo = operations[index];
        ObjectParameterInfo[] params = operationInfo.getSignature();
%>
<jmhtml:form action="/app/executeOperation">
<tr>
    <td class="plaintext"><%=operationInfo.getDisplayReturnType()%>
    <%if(operationInfo.getDescription() != null){%>
        <a href="JavaScript:showDescription('<%=MBeanUtils.jsEscape(operationInfo.getDescription())%>');"><%=operationInfo.getName()%></a>
    <%}else{%>
        <%=operationInfo.getName()%>
    <%}%>
    <input type="hidden" name="paramCount" value="<%=params.length%>"/>
    </td>
    <td class="plaintext">
    <%if(params.length > 0){
        int paramIndex = 0;
    %>
        <input type="hidden" name="<%=operationInfo.getName()%><%=paramIndex%>_type" value="<%=params[paramIndex].getType()%>"/>
        <input tabindex="<%=tabIndex++%>" type="text" name="<%=operationInfo.getName()%><%=paramIndex%>_value" value=""/>
        <%
            String argName = params[paramIndex].getName();
            if(argName == null || argName.length() == 0){
                argName = params[paramIndex].getType();
            }
            String description = params[paramIndex].getDescription();
            if(description != null && description.length() > 0){
        %>
           <a href="JavaScript:showDescription('<%=MBeanUtils.jsEscape(description)%>');">
                    <%=argName%></a>
        <%}else{%>
            <%=argName%>
        <%}%>
        <%if(!params[paramIndex].getType().equals(argName)){%>
            (<%=params[paramIndex].getDisplayType()%>)
        <%}%>
    <%}else{%>
        &nbsp;
    <%}%>
    </td>
    <td class="plaintext">
        <input type="hidden" name="operationName" value="<%=operationInfo.getName()%>"/>
        <%  if(AccessController.canAccess(webContext.getServiceContext(),
                        ACLConstants.ACL_EXECUTE_MBEAN_OPERATIONS,
                        operationInfo.getName())){  %>
        <input tabindex="<%=(tabIndex++) + params.length%>" type="submit" value="Execute" class="Inside3d"/>&nbsp;
        <%  }else{  %>
        <input tabindex="<%=(tabIndex++) + params.length%>" type="submit" value="Execute" class="Inside3d" disabled/>&nbsp;
        <%  }   %>
        [Impact: <%=MBeanUtils.getImpact(operationInfo.getImpact())%>]
    </td>
</tr>
    <%
        for(int paramIndex = 1; paramIndex < params.length; paramIndex ++){
    %>
<tr>
    <td class="plaintext">&nbsp;</td>
    <td class="plaintext">
        <input type="hidden" name="<%=operationInfo.getName()%><%=paramIndex%>_type" value="<%=params[paramIndex].getType()%>"/>
        <input tabindex="<%=tabIndex++%>" type="text" name="<%=operationInfo.getName()%><%=paramIndex%>_value" value=""/>
        <%
            String argName = params[paramIndex].getName();
            if(argName == null || argName.length() == 0){
                argName = params[paramIndex].getType();
            }
            String description = params[paramIndex].getDescription();
            if(description != null && description.length() > 0){
        %>
           <a href="JavaScript:showDescription('<%=MBeanUtils.jsEscape(description)%>');">
                    <%=argName%></a>
        <%}else{%>
            <%=argName%>
        <%}%>
        <%if(!params[paramIndex].getType().equals(argName)){%>
            (<%=params[paramIndex].getDisplayType()%>)
        <%}%>
    </td>
    <td class="plaintext">&nbsp;</td>
</tr>
    <%  } %>

</jmhtml:form>
<%  }%>
</table>
<%}%>

<%if(notifications.length > 0 &&
        AccessController.canAccess(webContext.getServiceContext(),
                        ACLConstants.ACL_VIEW_MBEAN_NOTIFICATIONS)){%>
<br/>
<table class="table" border="0" cellspacing="0" cellpadding="5" width="900">
    <tr class="tableHeader">
        <td colspan="3">Notifications</td>
    </tr>
<%
    for(int index=0; index < notifications.length; index++){
        ObjectNotificationInfo notificationInfo = notifications[index];
%>
<c:set var="objNameValue" value="${param.objName}" />
<tr>
    <td class="plaintext"><%=notificationInfo.getName()%></td>
    <td class="plaintext"><%=notificationInfo.getDescription()%></td>
    <td class="plaintext">
    <%if(!applicationConfig.isCluster()){%>
        <jmhtml:link href="/jmanage/config/selectAlertSourceType.do?alertSourceType=notification" paramId="objName" paramName="objNameValue" >Monitor</jmhtml:link>
    <%}%>
    </td>
</tr>
<%  }%>
</table>
<%}%>
