<%@ page import="java.util.Iterator" %>
<%@ page import="org.kuali.rice.kew.*" %>
<%@ page import="org.kuali.rice.kew.edl.*" %>
<%@ page import="org.kuali.rice.kns.service.KNSServiceLocator" %>
<%@ page import="org.kuali.rice.kns.util.KNSConstants" %>
<%@ page import="org.kuali.rice.kew.util.KEWConstants" %>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<html>
  <title>EDocLite: Choose Doc Type</title>
  <link href="css/edoclite.css" rel="stylesheet" type="text/css"/>
  <link href="css/screen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <br>
  	<table>
			<%
				pageContext.setAttribute("associations", SpringServiceLocator.getEDocLiteService().getEDocLiteAssociations());
			                String targetFrame=KNSServiceLocator.getKualiConfigurationService().getParameterWithoutExceptions(KEWConstants.DEFAULT_KIM_NAMESPACE, KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE, KEWConstants.BACKDOOR_TARGET_FRAME_NAME).getParameterValue();
			                String action="Choosedoctype";
			                String def="__default__";
			                String style="__default__";
			%>

            <c:forEach items="${associations}" var="assoc">
                <c:if test="${assoc.activeInd}">
                <tr>
					<td>
                  <a href="EDocLite?action=<%=action%>&docType=<c:out value='${assoc.docType}'/>" target="<%=targetFrame%>"><c:out value="${assoc.docType}"/></a>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
 	 </table>


</body>
</html>