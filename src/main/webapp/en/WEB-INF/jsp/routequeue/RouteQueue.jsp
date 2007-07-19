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
      <table border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  			<tr>
  				<td class="thnormal" align="right" width="20%">
  					Queue Status:&nbsp;
  				</td>
  				<td class="datacell">
						<html-el:select property="queueStatus${Constants.ROUTE_QUEUE_FILTER_SUFFIX}">
							<html-el:option value=""></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_QUEUED}"><c:out value="${Constants.ROUTE_QUEUE_QUEUED_LABEL}" /></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_ROUTING}"><c:out value="${Constants.ROUTE_QUEUE_ROUTING_LABEL}" /></html-el:option>
							<html-el:option value="${Constants.ROUTE_QUEUE_EXCEPTION}"><c:out value="${Constants.ROUTE_QUEUE_EXCEPTION_LABEL}" /></html-el:option>
						</html-el:select>
						&nbsp;
   				</td>
   			</tr>
   			<tr>
  				<td class="thnormal" align="right" width="20%">
  					IP Number:&nbsp;
  				</td>
  				<td class="datacell">
   					<html-el:text property="ipNumber${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" size="20" maxlength="15" />
   					&nbsp;
   				</td>
   			</tr>
   			<tr>
  				<td class="thnormal" align="right" width="20%">
   					Service Name:&nbsp;
  				</td>
  				<td class="datacell">
   					<html-el:text property="serviceName${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" />
   				</td>
   			</tr>
   			<tr>
  				<td class="thnormal" align="right" width="20%">
   					Message Entity:&nbsp;
  				</td>
  				<td class="datacell">
   					<html-el:text property="messageEntity${Constants.ROUTE_QUEUE_FILTER_SUFFIX}" />
   				</td>
   			</tr>
   			<tr>
  				<td class="thnormal" align="right" width="20%">
   					&nbsp;
  				</td>
  				<td class="datacell">
  					<html-el:submit property="filterApplied" value="Filter" />
  				</td>
  			</tr>
  		</table>
  	</td>
  </tr>
  <tr>
  	<td colspan="2">&nbsp;</td>
  </tr>
</table>
</html-el:form>
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <c:if test="${hasAnyRows}">
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>

		  <%-- Table layout of the search results --%>
		  <display-el:table excludedParams="*" pagesize="${preferences.pageSize}" class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${RouteQueueForm.routeQueueRows}" export="true" id="result" requestURI="RouteQueue.do?methodToCall=start&filterApplied=${filterApplied}&queueStatusFilter=${queueStatusFilter}&ipNumberFilter=${ipNumberFilter}&serviceNameFilter=${serviceNameFilter}&messageEntityFilter=${messageEntityFilter}" defaultsort="1" defaultorder="descending" 
				decorator="edu.iu.uis.eden.docsearch.web.DocumentSearchDecorator">
		    <display-el:setProperty name="paging.banner.placement" value="both" />
		    <display-el:setProperty name="export.banner" value="" />
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Route<br />Queue Id</div>" sortProperty="routeQueueId">
		    	<c:out value="${result.routeQueueId}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Service<br />Name</div>" >
		    	<c:out value="${result.serviceName}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;"  class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Message<br />Entity</div>" >
		    	<c:out value="${result.messageEntity}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>IP Number</div>" >
		    	<c:out value="${result.ipNumber}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Queue<br />Status</div>" >
		    	<c:out value="${result.routeQueueStatusLabel}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Queue<br />Priority</div>" >
		    	<c:out value="${result.queuePriority}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Queue<br />Date</div>" sortProperty="queueDate.time">
		    	<fmt:formatDate value="${result.queueDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="true" title="<div style='text-align:center;vertical-align:top;'>Retry<br />Count</div>" >
		    	<c:out value="${result.retryCount}"/>&nbsp;
		    </display-el:column>
		    <display-el:column style="text-align:center;vertical-align:middle;" class="datacell" sortable="false" title="<div style='text-align:center;vertical-align:top;'>Actions</div>" >
		    	<a href='RouteQueue.do?methodToCall=viewPayload&routeQueueId=<c:out value="${result.routeQueueId}" />'>View Payload</a>
		    	&nbsp;
		    	<a href='RouteQueue.do?methodToCall=edit&routeQueueId=<c:out value="${result.routeQueueId}" />'>Edit</a>
		    	&nbsp;
		    	<a href='RouteQueue.do?methodToCall=quickRequeueMessage&routeQueueId=<c:out value="${result.routeQueueId}" />' onClick="return confirm('Are you sure you want to ReQueue this message?\n\nThe QueueDate will be reset to today, the Status changed to QUEUED, and the Retry set to zero.');">ReQueue</a>
		    </display-el:column>
		  </display-el:table>

    </td>
  </tr>
  </c:if> 
</table>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>
