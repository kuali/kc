<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="KualiForm" value="${RoutingReportForm}" scope="request"/>
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/routetemplate-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<!-- following script contains executed code, not just definitions -->
<script language="JavaScript">
  addCalendar("dateRef", "Select Date", "dateRef", "RoutingReportForm");
</script>
<link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">
<kul:page headerTitle="Routing Report" transactionalDocument="false"
	showDocumentInfo="false" htmlFormAction="RoutingReport" docTitle="Routing Report">

<html-el:form method="post" action="/RoutingReport.do">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="methodToCall" />
<c:if test="${RoutingReportForm.reportType != null}">
	<html-el:hidden property="reportType" />
</c:if>

<table width="95%" align="center" >
	<tr>
		<td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
	</tr>
    <c:if test="${RoutingReportForm.displayCloseButton}">
      <tr>
        <td align="center"><a href="#" onclick="javascript:window.close();"><img src="images/buttonsmall_close.gif" alt="Close This Window" /></a></td>
	  </tr>
	</c:if>
</table>

<c:if test="${RoutingReportForm.reportType == 'template'}">
    <table width="95%" align="center" class="datatable">
		<tr>
			<kul:htmlAttributeHeaderCell scope="col" align="left">Select A Rule Template</kul:htmlAttributeHeaderCell>
			<td class="datacell">	<html-el:select property="ruleTemplateId" onchange="post_to_action('RoutingReport.do','loadTemplate')">
				  <c:set var="ruleTemplates" value="${RoutingReportForm.ruleTemplates}"/>
					<html-el:option value="chooser">Please select a template</html-el:option>
					<html-el:options collection="ruleTemplates" property="ruleTemplateId" labelProperty="name"/>
				</html-el:select>
			</td>
		</tr>
    </table>
<br>
<c:if test="${RoutingReportForm.showFields}">
	<table width="95%" align="center" class="datatable">
		<tr>
			<kul:htmlAttributeHeaderCell colspan="3" scope="col" align="left">Enter Routing Data</kul:htmlAttributeHeaderCell>
		</tr>
		<tr>
  	  	 	<kul:htmlAttributeHeaderCell scope="col" align="left" >Effective Date:</kul:htmlAttributeHeaderCell>
		  	<td class="datacell">&nbsp;&nbsp;<html-el:text property="dateRef" size="10"/>&nbsp;
		    <a href="javascript:showCal('<c:out value="dateRef"/>');"><img src="images/cal.gif" alt="Click Here to select the from date" align="middle" height="16" width="16"/></a>
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
			<kul:htmlAttributeHeaderCell scope="col" align="left">Document Type:</kul:htmlAttributeHeaderCell>
			<td class="datacell">
				&nbsp;&nbsp;
				<html-el:text property="documentType" />
				&nbsp;
				<kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" />
			</td>
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
</c:if>
</html-el:form>
</kul:page>