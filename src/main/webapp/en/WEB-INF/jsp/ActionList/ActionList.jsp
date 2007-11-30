<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<html>
<head>
<title><bean-el:message key="actionList.ActionList.title"/></title>

<c:if test="${!empty preferences.refreshRate && preferences.refreshRate != 0}">
<c:if test="${!noRefresh}">
<META HTTP-EQUIV="Refresh" CONTENT="<c:out value="${preferences.refreshRate * 60}"/>; URL=ActionList.do">
</c:if>
</c:if>

<link href="<c:out value="css/${ActionListForm.cssFile}"/>" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/actionlist-common.js"></script>

</head>
<body>
<html-el:form action="ActionList">
<html-el:hidden property="methodToCall" value="" />
<html-el:hidden property="cssFile"/>
<html-el:hidden property="logoAlign"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <c:if test="${ActionListForm.logoAlign != 'right'}">
    <td width="10%">
        <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    </c:if>
    <td align="left">
		&nbsp;<html-el:link page="/Preferences.do?returnMapping=viewActionList">Preferences</html-el:link>&nbsp;&nbsp;
		<a href="
			<c:url value="ActionList.do">
				<c:param name="methodToCall" value="start" />
			</c:url>">Refresh <bean-el:message key="actionList.ActionList.title"/></a>&nbsp;&nbsp;
		<html-el:link action="ActionListFilter">Filter</html-el:link>&nbsp;&nbsp;
		<c:if test="${kewUserSession.actionListFilter != null && kewUserSession.actionListFilter.filterOn}">
			<a href="
			<c:url value="ActionList.do">
				<c:param name="methodToCall" value="clearFilter" />
				<c:param name="key" value="${key}"/>
			</c:url>">Clear Filter</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${helpDeskActionList != null}">
			<html-el:text property="helpDeskActionListUserName" size="12"/>&nbsp;
            <html-el:image src="images/tinybutton-hlpdesk.gif" align="absmiddle" property="methodToCall.helpDeskActionListLogin" />
			<c:if test="${kewUserSession.helpDeskActionListUser != null}">
				<a href="
					<c:url value="ActionList.do">
						<c:param name="methodToCall" value="clearHelpDeskActionListUser" />
					</c:url>">Clear <c:out value="${kewUserSession.helpDeskActionListUser.displayName}"/>'s List</a>
			</c:if>&nbsp;&nbsp;
		</c:if>
		<c:if test="${! empty ActionListForm.delegators}">
            <html-el:select property="delegationId" onchange="document.forms[0].methodToCall.value='start';document.forms[0].submit();">
              <html-el:option value="${Constants.DELEGATION_DEFAULT}"><c:out value="${Constants.DELEGATION_DEFAULT}" /></html-el:option>
              <html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			  <c:forEach var="delegator" items="${ActionListForm.delegators}">
				<html-el:option value="${delegator.recipientId}"><c:out value="${delegator.displayName}" /></html-el:option>
			  </c:forEach>
            </html-el:select>
		</c:if>
    </td>
    <c:if test="${ActionListForm.logoAlign == 'right'}">
    <td align="right">
        <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 vspace=2>
    </td>
    </c:if>
  </tr>
</table>
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
      <table width="100%" border=0 cellspacing=0 cellpadding=0>
        <tr>
          <td>
            <c:choose>
	            <c:when test="${ActionListForm.viewOutbox && ActionListForm.showOutbox}">
		            <a href="<c:url value="ActionList.do?viewOutbox=false" />"><bean-el:message key="actionList.ActionList.title"/></a>
		            | <strong><bean-el:message key="actionList.Outbox.title"/></strong>
		             <c:if test="${! ActionListForm.outBoxEmpty }">
			            <td align="right">
			  	        	<html-el:image src="images/buttonsmall_delselitems.gif" align="absmiddle" property="methodToCall.removeOutboxItems"/>
			          	</td>
			          </c:if>
	            </c:when>
	            <c:otherwise>
	                <strong><bean-el:message key="actionList.ActionList.title"/></strong>
	                <c:if test="${ActionListForm.showOutbox }">
		            	| <a href="<c:url value="ActionList.do?viewOutbox=true" />"><bean-el:message key="actionList.Outbox.title"/></a>
	            	</c:if>
	            	<c:if test="${kewUserSession.helpDeskActionListUser == null && ! empty actionList && ! empty ActionListForm.defaultActions}">
			            <td align="right">
			               <c:set var="defaultActions" value="${ActionListForm.defaultActions}" scope="request" />
			               <html-el:select styleId='defaultAction' property="defaultActionToTake">
			                 <html-el:options collection="defaultActions" labelProperty="value" property="key" filter="false"/>
			               </html-el:select>&nbsp;<html-el:img src="images/tinybutton-applydflt.gif" align="absmiddle" onclick="setActions();" /><br>
			            </td>
			          </c:if>
	            </c:otherwise>
            </c:choose>
          </td>
          

          
          
        </tr>
      </table>
    </td>
    <td></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>

  <c:if test="${ActionListForm.filterLegend != null && ActionListForm.filterLegend != ''}">
    <tr>
	  <td></td>
      <td>
        <strong><c:out value="${ActionListForm.filterLegend}"/></strong>
      </td>
	  <td></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </c:if>

  <tr>
	<td></td>
  	<td>

  <c:url var="actionListURI" value="ActionList.do">
    <c:param name="methodToCall" value="start"/>
    <c:param name="currentPage" value="${ActionListForm.currentPage}"/>
    <c:param name="currentSort" value="${ActionListForm.currentSort}"/>
    <c:param name="currentDir" value="${ActionListForm.currentDir}"/>
  </c:url>

  <%-- Setup column lables based on ApplicationsResources --%>
  <bean:define id="documentIdLabel">
 	<bean-el:message key="actionList.ActionList.results.label.documentId"/>
  </bean:define>
  <bean:define id="typeLabel">
 	<bean-el:message key="actionList.ActionList.results.label.type"/>
  </bean:define>
  <bean:define id="titleLabel">
 	<bean-el:message key="actionList.ActionList.results.label.title"/>
  </bean:define>
  <bean:define id="routeStatusLabel">
 	<bean-el:message key="actionList.ActionList.results.label.routeStatus"/>
  </bean:define>
  <bean:define id="actionRequestedLabel">
 	<bean-el:message key="actionList.ActionList.results.label.actionRequested"/>
  </bean:define>
  <bean:define id="initiatorLabel">
 	<bean-el:message key="actionList.ActionList.results.label.initiator"/>
  </bean:define>
  <bean:define id="delegatorLabel">
 	<bean-el:message key="actionList.ActionList.results.label.delegator"/>
  </bean:define>
  <bean:define id="dateCreatedLabel">
 	<bean-el:message key="actionList.ActionList.results.label.dateCreated"/>
  </bean:define>
  <bean:define id="workgroupRequestLabel">
 	<bean-el:message key="actionList.ActionList.results.label.workgroupRequest"/>
  </bean:define>
  <bean:define id="actionsLabel">
 	<bean-el:message key="actionList.ActionList.results.label.actions"/>
  </bean:define>
  <bean:define id="routeLogLabel">
 	<bean-el:message key="actionList.ActionList.results.label.routeLog"/>
  </bean:define>
  <bean:define id="outboxActionItemDelete">
  	Delete Item
  </bean:define>

  <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="actionListPage" pagesize="${preferences.pageSize}" export="true" id="result"
          decorator="edu.iu.uis.eden.actionlist.web.ActionListDecorator" excludedParams="*"
          requestURI="${actionListURI}">
  <display-el:setProperty name="paging.banner.placement" value="both" />
  <display-el:setProperty name="export.banner" value="" />

  <%-- Since we are using the external paging and sorting features of the display tag now, if a new sortable column is added, remember to add it to the
       ActionItemComparator in the ActionListAction as well --%>

  <display-el:column sortable="true" title="${documentIdLabel}" sortProperty="routeHeaderId" class="display-column">
  	<c:choose>
      <c:when test="${kewUserSession.helpDeskActionListUser == null}">
		  	  <a href="<c:url value="${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
		  				<c:param name="docId" value="${result.routeHeaderId}"/>
		  				<c:param name="command" value="displayActionListView" />
		  			 </c:url>" <c:if test="${ActionListForm.documentPopup == Constants.ACTION_LIST_DOCUMENT_POPUP_VALUE}"> target="_blank" </c:if> class="showvisit">
				<c:out value="${result.routeHeaderId}"/>
			  </a>
        <c:if test="${result.displayParameters != null}">
          <br>
          <a id='A<c:out value="${result.actionItemIndex}"/>'
             href="<c:url value="${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
		  	<c:param name="docId" value="${result.routeHeaderId}"/>
		  	<c:param name="command" value="displayActionListInlineView" />
		  </c:url>" target='iframeAL_<c:out value="${result.actionItemIndex}"/>' onclick="rend(this, false)"><img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0 id='F<c:out value="${result.actionItemIndex}"/>'></a><br>
        </c:if>
      </c:when>
	  <c:otherwise>
		<c:out value="${result.routeHeaderId}"/>
	  </c:otherwise>
	</c:choose>
  </display-el:column>

  <c:if test="${preferences.showDocType == Constants.PREFERENCES_YES_VAL}">
	  <display-el:column property="docLabel" sortable="true" title="${typeLabel}" class="display-column" />
  </c:if>
  <c:if test="${preferences.showDocTitle == Constants.PREFERENCES_YES_VAL}">
	  <display-el:column sortProperty="docTitle" sortable="true" title="${titleLabel}" class="display-column">
	  	<c:out value="${result.docTitle}" />&nbsp;
	  </display-el:column>
  </c:if>
  <c:if test="${preferences.showDocumentStatus == Constants.PREFERENCES_YES_VAL}">
	  <display-el:column property="routeHeader.docRouteStatusLabel" sortable="true" title="${routeStatusLabel}" class="display-column" />
  </c:if>
  <c:if test="${preferences.showActionRequested == Constants.PREFERENCES_YES_VAL}">
 	<display-el:column property="actionRequestLabel" sortable="true" title="${actionRequestedLabel}" class="display-column" />
  </c:if>
  <c:if test="${preferences.showInitiator == Constants.PREFERENCES_YES_VAL}">
	  <display-el:column sortable="true" title="${initiatorLabel}" sortProperty="routeHeader.initiatorName" class="display-column" >
          <a href="<c:url value="${UrlResolver.userReportUrl}">
                     <c:param name="workflowId" value="${result.routeHeader.actionListInitiatorUser.workflowUserId.workflowId}"/>
                     <c:param name="showEdit" value="no"/>
                     <c:param name="methodToCall" value="report"/></c:url>" target="_blank">
            <c:out value="${result.routeHeader.actionListInitiatorUser.transposedName}"/></a>
 	  </display-el:column>
  </c:if>

  <c:if test="${preferences.showDelegator == Constants.PREFERENCES_YES_VAL}">
    <display-el:column sortable="true" title="${delegatorLabel}" sortProperty="delegatorName" class="display-column">
    	<c:choose>
        <c:when test="${result.delegatorUser != null}">
          <a href="<c:url value="${UrlResolver.userReportUrl}">
                     <c:param name="workflowId" value="${result.delegatorUser.workflowUserId.workflowId}"/>
                     <c:param name="showEdit" value="no"/>
                     <c:param name="methodToCall" value="report"/></c:url>" target="_blank">
            <c:out value="${result.delegatorUser.transposedName}"/></a>
        </c:when>
        <c:when test="${result.delegatorWorkgroup != null}">
           <a href="<c:url value="${UrlResolver.workgroupReportUrl}">
                      <c:param name="workgroupId" value="${result.delegatorWorkgroup.workflowGroupId.groupId}"/>
                      <c:param name="methodToCall" value="report"/>
                      <c:param name="showEdit" value="no"/>
                    </c:url>" target="_blank"><c:out value="${result.delegatorWorkgroup.groupNameId.nameId}"/></a>
       </c:when>
        <c:otherwise>
        	&nbsp;
        </c:otherwise>
      </c:choose>
    </display-el:column>
  </c:if>
  <c:if test="${preferences.showDateCreated == Constants.PREFERENCES_YES_VAL}">
  	<display-el:column sortable="true" title="${dateCreatedLabel}" sortProperty="routeHeader.createDate" class="display-column">
  		<fmt:formatDate value="${result.routeHeader.createDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;
  	</display-el:column>
  </c:if>

  <c:if test="${preferences.showWorkgroupRequest == Constants.PREFERENCES_YES_VAL}">
  	<display-el:column sortable="true" title="${workgroupRequestLabel}" sortProperty="workgroup.groupNameId.nameId" class="display-column">
  		<c:choose>
  			<c:when test="${result.workgroupId != null && result.workgroupId != 0}">
  			  <a href="<c:url value="${UrlResolver.workgroupReportUrl}">
                      <c:param name="workgroupId" value="${result.workgroup.workflowGroupId.groupId}"/>
                      <c:param name="methodToCall" value="report"/>
                      <c:param name="showEdit" value="no"/>
                    </c:url>" target="_blank"><c:out value="${result.workgroup.groupNameId.nameId}"/>
              </a>
  			</c:when>
  			<c:otherwise>
  				&nbsp;
  			</c:otherwise>
  		</c:choose>
	</display-el:column>
  </c:if>

  <c:if test="${! ActionListForm.viewOutbox && kewUserSession.helpDeskActionListUser == null && ActionListForm.hasCustomActions && (ActionListForm.customActionList || (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL))}">
    <display-el:column title="${actionsLabel}" class="display-column">
        <c:if test="${! empty result.customActions}">
          <c:set var="customActions" value="${result.customActions}" scope="request" />
          <html-el:hidden property="actions[${result.actionItemIndex}].actionItemId" value="${result.actionItemId}" />
          <html-el:select property="actions[${result.actionItemIndex}].actionTakenCd">
            <html-el:options collection="customActions" labelProperty="value" property="key" filter="false"/>
          </html-el:select>
          <c:set var="customActionsPresent" value="true" />
        </c:if>&nbsp;
    </display-el:column>
  </c:if>
  
  <c:if test="${ActionListForm.viewOutbox }">
      <display-el:column title="${outboxActionItemDelete}" class="display-column">
          <html-el:checkbox property="outboxItems" value="${result.actionItemId}"/>
    </display-el:column>
  </c:if>

  <display-el:column title="${routeLogLabel}" class="display-column">
  	<div align="center"><a href="<c:url value="RouteLog.do"><c:param name="routeHeaderId" value="${result.routeHeaderId}"/></c:url>" <c:if test="${ActionListForm.routeLogPopup == Constants.ACTION_LIST_ROUTE_LOG_POPUP_VALUE}">target="_blank"</c:if>>
	  <img alt="Route Log for Document" src="images/my_route_log.gif" />
	</a></div>
  </display-el:column>

</display-el:table>
</td>
<td></td>
</tr>

  <c:if test="${kewUserSession.helpDeskActionListUser == null && (! empty customActionsPresent) && (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL || ActionListForm.customActionList)}">
    <tr><td colspan=3>&nbsp;</td></tr>
  	<tr>
  		<td></td>
  		<td height="0" class="thnormal-fullbord">
            <div align="center">
              <a id="takeMassActions" href="javascript: setMethodToCallAndSubmit('takeMassActions')">
           		<img src="images/buttonsmall_takeactions.gif" />
              </a>
            </div>
  		</td>
  		<td></td>
	</tr>
  </c:if>

</table>

</html-el:form>

<center>
<c:if test="${kewUserSession.helpDeskActionListUser != null}">
	<c:out value="${kewUserSession.workflowUser.displayName}"/> Viewing <c:out value="${kewUserSession.helpDeskActionListUser.displayName}"/>'s Action List
</c:if>
</center>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>


</body>
</html>