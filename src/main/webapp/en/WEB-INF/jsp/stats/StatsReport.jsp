<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>

  <head>
  	<title>Workflow Statistics</title>
	<link rel="stylesheet" href="css/screen.css" type='text/css'/>
    <script language="JavaScript" src="scripts/en-common.js"></script>

	    <script language="javascript" src="scripts/cal2.js">
		    /*
		    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
		    featured on/available at http://www.dynamicdrive.com/
		    This notice must stay intact for use */
			</script>
			<script language="javascript">
        //Define calendar(s): addCalendar ("Unique Calendar Name", "Window title", "Form element's name", Form name")
        addCalendar("begDateCal", "Select Date", "begDate", "StatsForm");
        addCalendar("endDateCal", "Select Date", "endDate", "StatsForm");
      </script>
  </head>

  <body>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
      <html-el:form action="Stats.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">

       <html-el:hidden property="methodToCall"/>
	    <tr>
    		<td class="thnormal"><bean-el:message key="general.label.aggregates"/></td>
    	</tr>

  		<tr>
    		<td>
			    <table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="thnormal" width="50%"><bean-el:message key="stats.StatsReport.numDocTypes"/></td>
							<td class="datacell" width="50%"><c:out value="${StatsForm.stats.numDocTypes}"/>&nbsp;</td>
						</tr>
						<tr>
						    <td class="thnormal" width="50%"><bean-el:message key="stats.StatsReport.numUsers"/></td>
						    <td class="datacell" width="50%"><c:out value="${StatsForm.stats.numUsers}"/>&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal" width="50%"><bean-el:message key="stats.StatsReport.numActionItems"/></td>
							<td class="datacell" width="50%"><c:out value="${StatsForm.stats.numActionItems}"/>&nbsp;</td>
						</tr>
						<tr>
							<td class="thnormal3" width="50%"><bean-el:message key="stats.StatsReport.numInitiatedDocsByDocType"/></td>
							<td width="50%">
                              <c:if test="${! empty StatsForm.stats.numInitiatedDocsByDocType}">
							    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                  <c:forEach var="document" items="${StatsForm.stats.numInitiatedDocsByDocType}" >
                                    <tr>
							          <td width="50%" class="datacell3" nowrap><c:out value="${document.key}"/>&nbsp;</td>
							          <td width="50%" class="datacell3"><c:out value="${document.value}"/>&nbsp;</td>
							        </tr>
							      </c:forEach>
							    </table>
							  </c:if>
					        </td>
						</tr>
				</table>
			  </td>
		</tr>
	  </table>
    </td>
    <td></td>
  </tr>
  <tr>
	<td colspan="2">&nbsp;</td>
  </tr>
  <tr>
	<td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
			<tr>
				<td class="thnormal"><bean-el:message key="general.label.dateRange"/></td>
			</tr>

			<tr>
			  <td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			    		<td class="thnormal">
			    			<bean-el:message key="general.label.beginDate"/>:&nbsp;<html-el:text property="begDate"/>&nbsp;<a href="javascript:showCal('begDateCal');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the create date"></a> &nbsp;&nbsp;
			    			<bean-el:message key="general.label.endDate"/>:&nbsp;<html-el:text property="endDate"/>&nbsp;<a href="javascript:showCal('endDateCal');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the create date"></a> &nbsp;&nbsp;
			    		</td>
			    	</tr>
			    </table>
			  </td>
			</tr>
			<tr>
				<td class="datacell" colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>

				  <table width="100%" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			    		<td class="thnormal" width="50%"><bean-el:message key="general.label.routeStatus"/></td>
			    		<td class="thnormal" width="50%"><bean-el:message key="stats.StatsReport.routeStatusCount"/></td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.approvedLabel}" /></td>
			    		<td class="datacell" width="50%">
			    				<c:out value="${StatsForm.stats.approvedNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.canceledLabel}" /></td>
			    		<td class="datacell" width="50%">
			    				<c:out value="${StatsForm.stats.canceledNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.disapprovedLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.disapprovedNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.enrouteLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.enrouteNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.exceptionLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.exceptionNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.finalLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.finalNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.initiatedLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.initiatedNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.processedLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.processedNumber}" />&nbsp;
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="thnormal" width="50%"><c:out value="${StatsForm.savedLabel}" /></td>
			    		<td class="datacell" width="50%">
			    			<c:out value="${StatsForm.stats.savedNumber}" />&nbsp;
			    		</td>
			    	</tr>
			        <tr>
				      <td class="datacell" colspan="2">&nbsp;</td>
			        </tr>
			 			<tr>
			 				<td class="thnormal" width="50%"><bean-el:message key="stats.StatsReport.avgActionsPerDoc"/></td>
							<td class="datacell" width="50%"><c:out value="${StatsForm.stats.avgActionsPerDoc}"/>
									<c:if test="${StatsForm.avgActionsPerTimeUnit == StatsForm.dayTimeUnit}">
										<bean-el:message key="general.label.perDay"/>&nbsp;
									</c:if>
									<c:if test="${StatsForm.avgActionsPerTimeUnit == StatsForm.weekTimeUnit}">
										<bean-el:message key="general.label.perWeek"/>&nbsp;
									</c:if>
									<c:if test="${StatsForm.avgActionsPerTimeUnit == StatsForm.monthTimeUnit}">
										<bean-el:message key="general.label.perMonth"/>&nbsp;
									</c:if>
									<c:if test="${StatsForm.avgActionsPerTimeUnit == StatsForm.yearTimeUnit}">
										<bean-el:message key="general.label.perYear"/>&nbsp;
									</c:if>
									<html-el:select property="avgActionsPerTimeUnit">
							  		<html-el:options collection="timeUnitDropDown" property="key" labelProperty="value"/>
									</html-el:select>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
		        <td class="thnormal" colspan="2" align="center">
		            <html-el:image src="images/buttonsmall_refresh.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
                    <html-el:img src="images/buttonsmall_cancel.gif" align="absmiddle" onclick="javascript: window.close()"/>
		        </td>
			</tr>
		</table>
     </html-el:form>
    </td>
    <td></td>
  </tr>
</table>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html>

