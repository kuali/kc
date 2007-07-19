<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<TITLE>Routing Report</TITLE>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/routetemplate-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>

</head>
<body>
<!-- following script contains executed code, not just definitions -->
<script language="JavaScript">
  addCalendar("dateRef", "Select Date", "dateRef", "RoutingReportForm");
</script>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%">&nbsp;</td>
  	</tr>
</table>
<br>

<html-el:form method="post" action="/RoutingReport.do">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="methodToCall" />
<c:if test="${RoutingReportForm.reportType != null}">
	<html-el:hidden property="reportType" />
</c:if>

<table width="95%" align="center">
	<tr>
		<td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
	</tr>
<!-- 
	<tr>
		<td>
			Report by template <html-el:link href="RoutingReport.do?reportType=template">Go</html-el:link>
		</td>
	</tr>
-->
</table>

<table width="95%" align="center">
	<c:if test="${RoutingReportForm.reportType == 'template'}">
		<tr>
			<td>
				<strong>*Select A Rule Template</strong>
				<html-el:select property="ruleTemplateId" onchange="post_to_action('RoutingReport.do','loadTemplate')">
				  <c:set var="ruleTemplates" value="${RoutingReportForm.ruleTemplates}"/>
					<html-el:option value="chooser">Please select a template</html-el:option>
					<html-el:options collection="ruleTemplates" property="ruleTemplateId" labelProperty="name"/>
				</html-el:select>
			</td>
		</tr>
	</c:if>
</table>
<br>

<c:if test="${RoutingReportForm.showFields}">
	<table width="95%" align="center">
		<tr>
			<td><strong>Enter Routing Data</td>
		</tr>
	</table>
	<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
		  <td align=right class="thnormal" colspan="2">Effective Date:</td>
		  <td class="datacell">&nbsp;&nbsp;<html-el:text property="dateRef" size="10"/>&nbsp;
		    <a href="javascript:showCal('<c:out value="dateRef"/>');"><img src="images/cal.gif" alt="Click Here to select the from date" align=middle height=16 width=16></a>
		    &nbsp;&nbsp;Time:&nbsp;
	      <c:set var="hour" value="${RoutingReportForm.hours}" />
        <html-el:select property="effectiveHour">
			       <html-el:options collection="hour" labelProperty="value" property="key"/>
				</html-el:select>
	      <c:set var="min" value="${RoutingReportForm.minutes}" />
				<html-el:select property="effectiveMinute">
			       <html-el:options collection="min" labelProperty="value" property="key"/>
				</html-el:select>
				<html-el:select property="amPm">
				    <html-el:option value="0">AM</html-el:option>
				    <html-el:option value="1">PM</html-el:option>
				</html-el:select>
		  </td>
		</tr>
		<tr>
			<td class="thnormal" align="right" colspan="2">&nbsp;&nbsp;*Document Type:</td>
			<td class="datacell">&nbsp;&nbsp;<html-el:text property="documentType" />&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';" /></td>
		</tr>
		<c:set var="FieldRows" value="${RoutingReportForm.ruleTemplateAttributes}" scope="request" />
		<c:set var="ActionName" value="RoutingReport.do" scope="request" />
		<jsp:include page="../RowDisplay.jsp" />

		<c:if test="${RoutingReportForm.showViewResults}">
			<tr>
				<td colspan="3" class="thnormal" height="30" align="center">
					<a href="javascript:post_to_action('RoutingReport.do','calculateRoute')"><img src="images/buttonsmall_viewresults.gif" alt="View results"></a>
				</td>
			</tr>
		</c:if>
	</table>
</c:if>
</html-el:form>
</body>
</html>