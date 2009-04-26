<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<link href="<c:out value="../kr/css/${ActionListFormNew.cssFile}"/>"
	rel="stylesheet" type="text/css">
<script language="JavaScript" src="../en/scripts/en-common.js"></script>
<script language="JavaScript" src="../en/scripts/actionlist-common.js"></script>

<%-- Setup column labels based on ApplicationsResources --%>
<bean:define id="documentIdLabel">
	<bean-el:message key="actionList.ActionList.results.label.documentId" />
</bean:define>
<bean:define id="typeLabel">
	<bean-el:message key="actionList.ActionList.results.label.type" />
</bean:define>
<bean:define id="titleLabel">
	<bean-el:message key="actionList.ActionList.results.label.title" />
</bean:define>
<bean:define id="routeStatusLabel">
	<bean-el:message key="actionList.ActionList.results.label.routeStatus" />
</bean:define>
<bean:define id="actionRequestedLabel">
	<bean-el:message
		key="actionList.ActionList.results.label.actionRequested" />
</bean:define>
<bean:define id="initiatorLabel">
	<bean-el:message key="actionList.ActionList.results.label.initiator" />
</bean:define>
<bean:define id="delegatorLabel">
	<bean-el:message key="actionList.ActionList.results.label.delegator" />
</bean:define>
<bean:define id="dateCreatedLabel">
	<bean-el:message key="actionList.ActionList.results.label.dateCreated" />
</bean:define>
<bean:define id="dateApprovedLabel">
	<bean-el:message key="actionList.ActionList.results.label.dateApproved" />
</bean:define>
<bean:define id="currentRouteNodesLabel">
	<bean-el:message
		key="actionList.ActionList.results.label.currentRouteNodes" />
</bean:define>
<bean:define id="workgroupRequestLabel">
	<bean-el:message
		key="actionList.ActionList.results.label.workgroupRequest" />
</bean:define>
<bean:define id="actionsLabel">
	<bean-el:message key="actionList.ActionList.results.label.actions" />
</bean:define>
<bean:define id="routeLogLabel">
	<bean-el:message key="actionList.ActionList.results.label.routeLog" />
</bean:define>
<bean:define id="outboxActionItemDelete">
    Delete Item
  </bean:define>
    <c:url var="actionListURI" value="ActionList.do">
    <c:param name="methodToCall" value="start"/>
    <c:param name="currentPage" value="${ActionListFormNew.currentPage}"/>
    <c:param name="currentSort" value="${ActionListFormNew.currentSort}"/>
    <c:param name="currentDir" value="${ActionListFormNew.currentDir}"/>
  </c:url>

<kul:page headerTitle="Action List" lookup="true"
	headerMenuBar="${ActionListFormNew.menuBar}"
	transactionalDocument="false" showDocumentInfo="false"
	htmlFormAction="ActionList" docTitle="Action List">
	<%-- Since we are using the external paging and sorting features of the display tag now, if a new sortable column is added, remember to add it to the
       ActionItemComparator in the ActionListAction as well --%>
	<div class="headerarea-small" id="headerarea-small">
	<h1><c:out value="Action List" />
	</h1>
	<div align="right">
	  <br/>
	  <a
         href='<c:out value="../en/Preferences.do?returnMapping=viewActionList" />'  title="preferences"><img
         src="../kr/images/tinybutton-preferences.gif" class="tinybutton" alt="preferences" title="preferences"
         border="0" /></a>
	  <a
         href='<c:out value="ActionList.do?methodToCall=start" />'  title="refresh"><img
         src="../kr/images/tinybutton-refresh.gif" class="tinybutton" alt="refresh" title="refresh"
         border="0" /></a>
	   <a
         href='<c:out value="ActionListFilter.do?methodToCall=start" />'  title="filter"><img
         src="../kr/images/tinybutton-filter.gif" class="tinybutton" alt="filter" title="filter"
         border="0" /></a>

         <c:if test="${helpDeskActionList != null}">
			<html-el:text property="helpDeskActionListUserName" size="12" />&nbsp;
            <html-el:image src="../kr/images/tinybutton-hlpdesk.gif" property="methodToCall.helpDeskActionListLogin" border="0"/>
			<c:if test="${kewUserSession.helpDeskActionListPerson != null}">
				<a href="
					<c:url value="ActionList.do">
						<c:param name="methodToCall" value="clearHelpDeskActionListUser" />
					</c:url>">Clear <c:out value="${kewUserSession.helpDeskActionListPerson.name}"/>'s List</a>
			</c:if>&nbsp;&nbsp;
		</c:if>
    </div>
	</div>

	<div align="right">
	<br/>
         <c:if
            test="${kewUserSession.helpDeskActionListPerson == null && ! empty actionList && ! empty ActionListFormNew.defaultActions}">
              <c:set var="defaultActions" value="${ActionListFormNew.defaultActions}" scope="request" />
              <html-el:select styleId='defaultAction' property="defaultActionToTake">
                    <html-el:options collection="defaultActions" labelProperty="value" property="key" filter="false" />
              </html-el:select>&nbsp;<html-el:img src="../en/images/tinybutton-applydflt.gif" align="absmiddle" onclick="setActions();" /><br>
         </c:if>
    </div>
	<c:if
		test="${!empty preferences.refreshRate && preferences.refreshRate != 0}">
		<c:if test="${!noRefresh}">
			<META HTTP-EQUIV="Refresh"
				CONTENT="<c:out value="${preferences.refreshRate * 60}"/>; URL=ActionList.do">
		</c:if>
	</c:if>
	<html-el:form action="ActionList">
		<html-el:hidden property="methodToCall" value="" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20" height="30">&nbsp;</td>
				<td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
				<td width="20">&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>

						<c:choose>
							<c:when
								test="${ActionListFormNew.viewOutbox && ActionListFormNew.showOutbox}">
								<a href="<c:url value="ActionList.do?methodToCall=start&viewOutbox=false" />">
								    <bean-el:message key="actionList.ActionList.title" /></a>
                                | <strong><bean-el:message key="actionList.Outbox.title" /></strong>
							</c:when>
							<c:otherwise>
								<strong>
								<bean-el:message key="actionList.ActionList.title" /></strong>
								<c:if test="${ActionListFormNew.showOutbox }">
                                    | <a href="<c:url value="ActionList.do?methodToCall=start&viewOutbox=true" />">
                                        <bean-el:message key="actionList.Outbox.title" />
                                       </a>
								</c:if>
							</c:otherwise>
						</c:choose>

						</td>
                        <td>
                        <div align="right">
                          <c:if test="${ActionListFormNew.viewOutbox && ActionListFormNew.showOutbox && !ActionListFormNew.outBoxEmpty}">
                           <td align="right"><html-el:image
                              src="../kr/images/buttonsmall_delselitems.gif" align="absmiddle"
                              property="methodToCall.removeOutboxItems" />
                           </td>
                          </c:if>
                        </div>
                        </td>
					</tr>
				</table>

                </td>
                <td></td>
			</tr>
            <tr>
              <td></td>
              <td>
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td>
				<div id="row" >
				<div class="tab-container"><br />
				<display:table class="datatable-100" cellpadding="2" cellspacing="0"
					name="actionListPage" pagesize="${preferences.pageSize}"
					export="true" id="result"
					decorator="org.kuali.rice.kew.actionlist.web.ActionListDecorator"
					excludedParams="*" requestURI="${actionListURI}">
					<display-el:setProperty name="export.banner" value="" />
                    <c:if test="${kewUserSession.helpDeskActionListPerson == null && result.displayParameters != null}">
  					  <display-el:column title="&nbsp;">

                             <br>
                             <a id='A<c:out value="${result.actionItemIndex}"/>'
                              href="<c:url value="../en/${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
                             <c:param name="docId" value="${result.routeHeaderId}"/>
                             <c:param name="command" value="displayActionListInlineView" />
                             </c:url>"
                             target='iframeAL_<c:out value="${result.actionItemIndex}"/>'
                             onclick="rend(this, false)"><img
                             src="../en/images/tinybutton-show.gif" alt="show" width=45 height=15
                             border=0 id='F<c:out value="${result.actionItemIndex}"/>'></a>
                             <br>

                      </display-el:column>
                    </c:if>
					<display-el:column sortable="true" title="${documentIdLabel}"
						sortProperty="routeHeaderId">
						<c:choose>
							<c:when test="${kewUserSession.helpDeskActionListPerson == null}">
								<a
									href="<c:url value="../en/${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
                                     <c:param name="docId" value="${result.routeHeaderId}"/>
                                         <c:param name="command" value="displayActionListView" />
                                             </c:url>"
									<c:if test="${ActionListForm.documentPopup == Constants.ACTION_LIST_DOCUMENT_POPUP_VALUE}"> target="_blank" </c:if>
									class="showvisit"> <c:out value="${result.routeHeaderId}" />
								</a>
							</c:when>
							<c:otherwise>
								<c:out value="${result.routeHeaderId}" />
							</c:otherwise>
						</c:choose>
					</display-el:column>

					<c:if test="${preferences.showDocType == Constants.PREFERENCES_YES_VAL}">
						<display-el:column property="docLabel" sortable="true"
							title="${typeLabel}" />
					</c:if>
					<c:if test="${preferences.showDocTitle == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortProperty="docTitle" sortable="true"
							title="${titleLabel}" class="infocell">
							<c:out value="${result.docTitle}" />&nbsp;
                        </display-el:column>
					</c:if>
					<c:if test="${preferences.showDocumentStatus == Constants.PREFERENCES_YES_VAL}">
						<display-el:column property="routeHeader.docRouteStatusLabel"
							sortable="true" title="${routeStatusLabel}" class="infocell" />
					</c:if>
					<c:if test="${preferences.showActionRequested == Constants.PREFERENCES_YES_VAL}">
						<display-el:column property="actionRequestLabel" sortable="true"
							title="${actionRequestedLabel}" class="infocell" />
					</c:if>
					<c:if test="${preferences.showInitiator == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true" title="${initiatorLabel}"
							sortProperty="routeHeader.initiatorName" class="display-column">
                            <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.PersonImpl"
                                keyValues="principalId=${result.routeHeader.actionListInitiatorPrincipal.principalId}"
                                render="true">
                                  <c:out value="${result.routeHeader.initiatorName}" />
                            </kul:inquiry>
						</display-el:column>
					</c:if>

					<c:if test="${preferences.showDelegator == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true" title="${delegatorLabel}"
							sortProperty="delegatorName" class="infocell">
							<c:choose>
								<c:when test="${result.delegatorPerson != null}">
                                    <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.PersonImpl"
                                        keyValues="principalId=${result.delegatorPerson.principalId}"
                                        render="true">
                                          <c:out value="${result.delegatorPerson.name}" />
                                    </kul:inquiry>
								</c:when>
								<c:when test="${result.delegatorGroup != null}">
                                    <kul:inquiry boClassName="org.kuali.rice.kim.bo.group.impl.KimGroupImpl" keyValues="groupId=${result.delegatorGroup.groupId}" render="true">
                                        <c:out value="${result.delegatorGroup.groupName}" />
                                    </kul:inquiry>
								</c:when>
								<c:otherwise>
                                       &nbsp;
                                </c:otherwise>
							</c:choose>
						</display-el:column>
					</c:if>
					<c:if
						test="${preferences.showDateCreated == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true" title="${dateCreatedLabel}"
							sortProperty="routeHeader.createDate" class="infocell">
							<fmt:formatDate value="${result.routeHeader.createDate}"
								pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;
                         </display-el:column>
					</c:if>
					<c:if
						test="${preferences.showDateApproved == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true" title="${dateApprovedLabel}"
							sortProperty="lastApprovedDate" class="infocell">
							<fmt:formatDate value="${result.lastApprovedDate}"
								pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;
                           </display-el:column>
					</c:if>

					<c:if
						test="${preferences.showWorkgroupRequest == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true"
							title="${workgroupRequestLabel}" sortProperty="group.groupName"
							class="infocell">
							<c:choose>
								<c:when test="${result.groupId != null && result.groupId != 0}">
                                    <kul:inquiry boClassName="org.kuali.rice.kim.bo.group.impl.KimGroupImpl" keyValues="groupId=${result.group.groupId}" render="true">
                                        <c:out value="${result.group.groupName}" />
                                    </kul:inquiry>
								</c:when>
								<c:otherwise>
                                      &nbsp;
                                 </c:otherwise>
							</c:choose>
						</display-el:column>
					</c:if>

					<c:if
						test="${preferences.showCurrentNode == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true"
							title="${currentRouteNodesLabel}"
							sortProperty="routeHeader.currentRouteLevelName" class="infocell">
							<c:out value="${result.routeHeader.currentRouteLevelName}" />&nbsp;
                        </display-el:column>
					</c:if>

					<c:if
						test="${! ActionListFormNew.viewOutbox && kewUserSession.helpDeskActionListPerson == null && ActionListFormNew.hasCustomActions && (ActionListFormNew.customActionList || (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL))}">
						<display-el:column title="${actionsLabel}" class="infocell">
							<c:if test="${! empty result.customActions}">
								<c:set var="customActions" value="${result.customActions}"
									scope="request" />
								<html-el:hidden
									property="actions[${result.actionItemIndex}].actionItemId"
									value="${result.actionItemId}" />
								<html-el:select
									property="actions[${result.actionItemIndex}].actionTakenCd">
									<html-el:options collection="customActions"
										labelProperty="value" property="key" filter="false" />
								</html-el:select>
								<c:set var="customActionsPresent" value="true" />
							</c:if>&nbsp;
                        </display-el:column>
					</c:if>

					<c:if test="${ActionListFormNew.viewOutbox }">
						<display-el:column title="${outboxActionItemDelete}"
							class="infocell">
							<html-el:checkbox property="outboxItems"
								value="${result.actionItemId}" />
						</display-el:column>
					</c:if>

                    <display-e1:column title="Testing" class="infocell">
                        Testing
                    </display-e1:column>
					<display-el:column title="${routeLogLabel}" class="infocell">
						<div align="center"><a
							href="<c:url value="../en/RouteLog.do"><c:param name="routeHeaderId" value="${result.routeHeaderId}"/></c:url>"
							<c:if test="${ActionListForm.routeLogPopup == Constants.ACTION_LIST_ROUTE_LOG_POPUP_VALUE}">target="_blank"</c:if>>
						<img alt="Route Log for Document"
							src="../en/images/my_route_log.gif" /> </a></div>
					</display-el:column>
				</display:table></div>
                </div>
				</td>
				</tr>
				</table>
              </td>
              <td></td>
			</tr>

			<tr>
				<td colspan="3"><br />
				</td>
			</tr>
			<c:if
				test="${kewUserSession.helpDeskActionListPerson == null && (! empty customActionsPresent) && (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL || ActionListFormNew.customActionList)}">
				<tr>
					<td height="0" class="tinybutton">
					<div align="center"><a id="takeMassActions"
						href="javascript: setMethodToCallAndSubmit('takeMassActions')">
					<img src="../en/images/buttonsmall_takeactions.gif" /> </a></div>
					</td>
					<td></td>
				</tr>
			</c:if>
		</table>
	</html-el:form>
</kul:page>
