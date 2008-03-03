<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<html>
<head>
<title>Action List Filter</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="javascript" src="scripts/en-common.js"></script>
<script language="javascript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
</head>
<body>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="90%"><html-el:link action="ActionList">Return to Action List</html-el:link></td>
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
      <strong>Action List Filter</strong>
    </td>
    <td></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
  	<td></td>
  	<td>
<html-el:form action="ActionListFilter">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="lookupType" />
<html-el:hidden property="docTypeFullName" />
<html-el:hidden property="methodToCall" />

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
    <c:if test="${! empty delegators}">
      <tr>
	    <td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.secondaryDelegatorId"/> <bean-el:message key="general.help.delegatorId"/></td>
	    <td class="datacell">
		     <html-el:select property="filter.delegatorId">
			   <html-el:option value="${Constants.DELEGATION_DEFAULT}"><c:out value="${Constants.DELEGATION_DEFAULT}" /></html-el:option>
			   <html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			   <c:forEach var="delegator" items="${delegators}">
				 <html-el:option value="${delegator.recipientId}"><c:out value="${delegator.displayName}" /></html-el:option>
			   </c:forEach>
		     </html-el:select>
        </td>
      </tr>
    </c:if>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentTitle"/> <bean-el:message key="general.help.documentTitle"/></td>
		<td class="datacell"><html-el:text property="filter.documentTitle"/>&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeDocumentTitle"/></td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentRouteStatus"/> <bean-el:message key="general.help.routeStatus"/></td>
		<td class="datacell"><html-el:select property="filter.docRouteStatus">
			<html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_APPROVED_CD}"><c:out value="${Constants.ROUTE_HEADER_APPROVED_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_DISAPPROVED_CD}"><c:out value="${Constants.ROUTE_HEADER_DISAPPROVED_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_ENROUTE_CD}"><c:out value="${Constants.ROUTE_HEADER_ENROUTE_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_EXCEPTION_CD}"><c:out value="${Constants.ROUTE_HEADER_EXCEPTION_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_PROCESSED_CD}"><c:out value="${Constants.ROUTE_HEADER_PROCESSED_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ROUTE_HEADER_SAVED_CD}"><c:out value="${Constants.ROUTE_HEADER_SAVED_LABEL}" /></html-el:option>
			</html-el:select>
			&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeRouteStatus"/></td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.actionRequested"/><bean-el:message key="general.help.actionRequested"/></td>
		<td class="datacell"><html-el:select property="filter.actionRequestCd">
			<html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			<html-el:option value="${Constants.ACTION_REQUEST_ACKNOWLEDGE_REQ}"><c:out value="${Constants.ACTION_REQUEST_ACKNOWLEDGE_REQ_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ACTION_REQUEST_APPROVE_REQ}"><c:out value="${Constants.ACTION_REQUEST_APPROVE_REQ_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ACTION_REQUEST_COMPLETE_REQ}"><c:out value="${Constants.ACTION_REQUEST_COMPLETE_REQ_LABEL}" /></html-el:option>
			<html-el:option value="${Constants.ACTION_REQUEST_FYI_REQ}"><c:out value="${Constants.ACTION_REQUEST_FYI_REQ_LABEL}" /></html-el:option>
			</html-el:select>
			&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeActionRequestCd"/></td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.actionRequestWorkgroup"/> <bean-el:message key="general.help.actionRequestWorkgroup"/></td>
		<td class="datacell">
		    <html-el:select name="ActionListFilterForm" property="filter.workgroupIdString">
              <html-el:optionsCollection property="userWorkgroups" label="value" value="key" filter="false"/>
            </html-el:select>&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeWorkgroupId"/></td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentType"/> <bean-el:message key="general.help.documentType"/></td>
		<td class="datacell"><span id="docTypeElementId"><c:out value="${ActionListFilterForm.docTypeFullName}" /></span>
		    <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle"
		     onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeDocumentType"/></td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.dateCreated"/> <bean-el:message key="general.help.dateCreated"/></td>
		<td class="datacell">
          <table>
            <tr>
              <td>
		        <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text property="createDateFrom" size="10"/>
                      <a href="javascript:showCal('createDateFrom');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the from date created"></a>&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text property="createDateTo" size="10"/>
                      <a href="javascript:showCal('createDateTo');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the to date created"></a>&nbsp;
                    </td>
                  </tr>
                </table>
              </td>
              <td>
		        <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeCreateDate"/></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
		</td>
	</tr>
	<tr>
		<td class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.dateLastAssigned"/> <bean-el:message key="general.help.dateLastAssigned"/></td>
		<td class="datacell">
          <table>
            <tr>
              <td>
                <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.from"/>:</td>
                    <td nowrap>
                      <html-el:text property="lastAssignedDateFrom" size="10" />
                      <a href="javascript:showCal('lastAssignedDateFrom');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the from last assigned date"></a>&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.to"/>:</td>
                    <td nowrap>
                      <html-el:text property="lastAssignedDateTo" size="10" />
                      <a href="javascript:showCal('lastAssignedDateTo');"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to pick up the to last assigned date"></a>&nbsp;
                    </td>
                  </tr>
                </table>
              </td>
              <td>
		        <table border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeLastAssignedDate"/></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
		</td>
    </tr>
	<tr>
		<td class="thnormal" colspan="2" align="center">
            <html-el:image src="images/buttonsmall_filter.gif" align="absmiddle" property="methodToCall.filter" onclick="document.forms[0].elements['methodToCall'].value = 'filter';" />&nbsp;&nbsp;
            <html-el:image src="images/buttonsmall_clear.gif" align="absmiddle" property="methodToCall.clear" onclick="document.forms[0].elements['methodToCall'].value = 'clear';" />&nbsp;&nbsp;
            <a href="javascript:document.forms[0].reset()"><img src="images/buttonsmall_reset.gif" border=0 alt="reset" align="absmiddle"></a>
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