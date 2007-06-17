<%-- used by the graph applet--%>

<%@ page import="java.util.List,
                 java.util.Iterator,
                 org.jmanage.core.management.ObjectAttribute,
                 java.util.Date"%>
<%
    List objectAttrList = (List)request.getAttribute("objectAttrList");
    StringBuffer attrNames = new StringBuffer();
    StringBuffer attrValues = new StringBuffer();
    for(Iterator it=objectAttrList.iterator(); it.hasNext();){
        if(attrNames.length() > 0){
            attrNames.append("|");
            attrValues.append("|");
        }
        ObjectAttribute objAttribute = (ObjectAttribute)it.next();
        attrNames.append(objAttribute.getName());
        attrValues.append(objAttribute.getValue());
    }
%>
timestamp=<%=new Date().getTime()%>
attributes=<%=attrNames.toString()%>
values=<%=attrValues.toString()%>
