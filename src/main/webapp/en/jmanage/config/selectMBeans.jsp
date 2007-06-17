<!--    /config/selectMBeans.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="java.util.Map,
                 java.util.Iterator,
                 java.util.Set,
                 org.jmanage.webui.util.RequestParams"%>

<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<jmhtml:errors/>
<jmhtml:javascript formName="attributeSelectionForm" page="1"/>
<jmhtml:form action="/config/showAttributes" onsubmit="return validateAttributeSelectionForm(this)">
<jmhtml:hidden property="endURL" />
<jmhtml:hidden property="multiple"/>
<jmhtml:hidden property="alertSourceType"/>
<jmhtml:hidden property="navigation"/>
<jmhtml:hidden property="page" value="1"/>
<%
    String[] dataType = request.getParameterValues(RequestParams.DATA_TYPE);
    if(dataType!=null){
        for(int i=0; i<dataType.length; i++){
%>
    <jmhtml:hidden property="dataTypes" value="<%=dataType[i]%>"/>
<%
        }
    }
%>
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
            String value = domain + ":" + objectName;
            if("true".equals(request.getParameter(RequestParams.MULTIPLE))){
        %>
            <tr>
                <td class="plaintext"><jmhtml:checkbox property="mbeans" value="<%=value%>"/> <%=objectName%></td>
           </tr>
        <%
            }else{
        %>
            <tr>
                <td class="plaintext"><jmhtml:radio property="mbeans" value="<%=value%>"/> <%=objectName%></td>
           </tr>
<%         }// end if
            } // inner for
%>
</table>
<br/>
<%
    } // outer for
%>
<table>
    <tr>
        <td align="center" colspan="2">
            <jmhtml:submit property="" value="Next" styleClass="Inside3d" />
            &nbsp;&nbsp;&nbsp;
            <jmhtml:button property="" value="Cancel"
                    onclick="JavaScript:history.back();" styleClass="Inside3d" />
        </td>
    </tr>
</table>
</jmhtml:form>
