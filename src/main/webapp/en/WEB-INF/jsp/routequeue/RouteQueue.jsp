<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="hasAnyRows" value="${false}" />
<c:if test="${!empty RouteQueueForm.routeQueueRows}">
	<c:if test="${RouteQueueForm.routeQueueRowsSize > 0}">
		<c:set var="hasAnyRows" value="${true}" />
	</c:if>
</c:if>

<html-el:html>
<head>
<style type="text/css">
   .highlightrow {}
   tr.highlightrow:hover, tr.over td { background-color: #66FFFF; }
</style> 
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="15%"><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="85%"><a href="RouteQueue.do?methodToCall=start" />Refresh Page</a>&nbsp;|&nbsp;<a href="RouteQueue.do?methodToCall=addNew">Queue new Document</a></td>
    <td>&nbsp;&nbsp;</td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<html-el:form action="/RouteQueue.do">
<html-el:hidden property="methodToCall" />
<table width="100%" border=0 cellspacing=0 cellpadding=0>

  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
       <strong>Documents currently in route queue:</strong>
       <c:if test="${!hasAnyRows}">
  	   &nbsp;&nbsp;None.
  	   </c:if>
  	   <c:if test="${hasAnyRows}">
	  	   <c:if test="${RouteQueueForm.routeQueueRowsSize >= RouteQueueForm.maxRows}">
	  	     &nbsp;&nbsp;<c:out value="There were ${RouteQueueForm.maxRows} or more rows, only displaying the first ${RouteQueueForm.maxRows}."/>
	  	   </c:if>
	   </c:if>
       <br>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>

  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  	<td>
  		<table width="100%" border="0" cellpadding="5" cellspacing="0">
  			<tr>
  				<td>
  					Queue Status:<br />
						<html-el:select property="queueStatus${Constants.ROUTE_QUEUE_FILTER_SUFFIX}">
							<html-el:option value=""></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_QUEUED}"><c:out value="${Constants.ROUTE_QUEUE_QUEUED_LABEL}" /></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_ROUTING}"><c:out value="${Constants.ROUTE_QUEUE_ROUTING_LABEL}" /></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_EXCEPTION}"><c:out value="${Constants.ROUTE_QUEUE_EXCEPTION_LABEL}" /></html-el:option>
						</html-el:select>
   				</td>
   				<td>
   					IP Number:<br />
   					<html-el:text property="ipNumber${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" size="20" maxlength="15" />
   				</td>
   				<td>
   					Service Name:<br />
   					<html-el:text property="serviceName${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" />
   				</td>
   				<td>
   					Message Entity:<br />
   					<html-el:text property="messageEntity${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" />
   				</td>
  			</tr>
  			<tr>
  				<td>
  					<html-el:submit property="filterApplied" value="Filter" />
  				</td>
  				<td colspan="3">
  					&nbsp;
  				</td>
  			</tr>
  		</table>
  	</td>
  </tr>
  <tr>
  	<td colspan="2">&nbsp;</td>
  </tr>
  <c:if test="${hasAnyRows}">
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <th align="center" class="thnormal" width="5%">
            <div align="center">Route<br>Queue<br>Id&nbsp;</div>
          </th>
          <th align="center" class="thnormal" width="5%">
            <div align="center">Service<br>Name&nbsp;</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Message<br>Entity&nbsp;</div>
          </th>
          <th align="center" class="thnormal" width="13%">
            <div align="center">IP Number&nbsp;</div>
          </th>
          <th align="center" class="thnormal" width="10%">
            <div align="center">Queue<br>Status</div>
          </th>
          <th align="center" class="thnormal" width="5%">
            <div align="center">Queue<br>Priority&nbsp;</div>
          </th>
          <th align="center" class="thnormal" width="12%">
            <div align="center">Queue Date&nbsp;</div>
          </th>
          <th align="center" class="thnormal" width="5%">
            <div align="center">Retry<br>Count&nbsp;</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Payload&nbsp;</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Action&nbsp;</div>
          </th>        
        </tr>
        <c:forEach var="row" items="${RouteQueueForm.routeQueueRows}">
        <%-- mouseover and mouseout used for highlighting of rows; highlightrow is an empty style class used to identify these particular rows --%>
        <tr class="highlightrow" onmouseover="this.className = 'over';" onmouseout="this.className = this.className.replace('over', '');">
          <td class="datacell" width="5%">
            <c:out value="${row.routeQueueId}" />&nbsp;
          </td>
          <td class="datacell" width="5%">
            <c:out value="${row.serviceName}" />&nbsp;
          </td>
          <td class="datacell">
            <c:out value="${row.messageEntity}" />&nbsp;
          </td> 
          <td class="datacell" width="13%">
            <c:out value="${row.ipNumber}" />&nbsp;
          </td>       
          <td class="datacell" width="10%">
            <c:out value="${row.routeQueueStatusLabel}" />&nbsp;
          </td>
          <td class="datacell" width="5%">
            <c:out value="${row.queuePriority}" />&nbsp;
          </td>
          <td class="datacell" width="12%">
            <fmt:formatDate value="${row.queueDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />
          </td>
          <td class="datacell" width="5%">
            <c:out value="${row.retryCount}" />&nbsp;
          </td>
          <td class="datacell">
          	<a href='RouteQueue.do?methodToCall=viewPayload&routeQueueId=<c:out value="${row.routeQueueId}" />'>View</a>
          </td> 
          <td align="center" class="datacell">
            <div align="center"><a href='RouteQueue.do?methodToCall=edit&routeQueueId=<c:out value="${row.routeQueueId}" />'>Edit&nbsp;</a></div>
          </td>
        </tr>
        </c:forEach>
      </table>
    </td>
  </tr>
  </c:if> 
</table>
</html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>
