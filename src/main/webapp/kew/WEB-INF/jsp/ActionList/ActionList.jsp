<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

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
    <c:param name="currentPage" value="${ActionListForm.currentPage}"/>
    <c:param name="currentSort" value="${ActionListForm.currentSort}"/>
    <c:param name="currentDir" value="${ActionListForm.currentDir}"/>
  </c:url>

<kul:page headerTitle="Action List" lookup="true"
	headerMenuBar="${ActionListForm.menuBar}"
	transactionalDocument="false" showDocumentInfo="false"
	htmlFormAction="ActionList" docTitle="Action List">
  <script language="JavaScript" src="scripts/en-common.js"></script>
  <script language="JavaScript" src="scripts/actionlist-common.js"></script>
  <style type="text/css">
  <!--
    tr.over { background-color:#CCFFFF; }
    tr.actionlist_anyRow:hover { background-color:#CCFFFF; }
    tr.actionlist_anyRow { visibility:visible; }
    <logic-el:iterate name="KEWConstants" id="colorEntry" property="ACTION_LIST_COLOR_PALETTE">
    tr.actionlist_${colorEntry.key} { background-color:${colorEntry.value}; }
    </logic-el:iterate>
  -->
  </style>
	<%-- Since we are using the external paging and sorting features of the display tag now, if a new sortable column is added, remember to add it to the
       ActionItemComparator in the ActionListAction as well --%>
	<div class="headerarea-small" id="headerarea-small">
	<div style="float:left"><h1><c:out value="Action List" /></h1></div><br />
    <div style="clear:both">
	<div style="float:right">
	  <div style="float:left; width:75px">
		<html-el:image src="${ConfigProperties.kr.url}/images/tinybutton-preferences.gif" property="methodToCall.viewPreferences" styleClass="tinybutton" alt="preferences" title="preferences" />
      </div>
      <div style="float:left; width:52px">
		<html-el:image src="${ConfigProperties.kr.url}/images/tinybutton-refresh.gif" property="methodToCall.start" styleClass="tinybutton" alt="refresh" title="refresh" />
      </div>
      <div style="float:left; width:39px">
		<html-el:image src="${ConfigProperties.kr.url}/images/tinybutton-filter.gif" property="methodToCall.viewFilter" styleClass="tinybutton" alt="filter" title="filter" />
      </div>

        <!-- Delegator selection list -->

		<c:if test="${! empty ActionListForm.delegators}">
			<html-el:hidden property="oldDelegationId" value="${ActionListForm.delegationId}" />
			<div style="float:left; width:226px; position: relative; top: -.5em;">
	            <html-el:select property="delegationId" onchange="document.forms[0].methodToCall.value='start';if(document.forms[0].primaryDelegateId){document.forms[0].primaryDelegateId.value='${Constants.PRIMARY_DELEGATION_DEFAULT}';}document.forms[0].submit();">
	              <html-el:option value="${Constants.DELEGATION_DEFAULT}"><c:out value="${Constants.DELEGATION_DEFAULT}" /></html-el:option>
	              <html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_SECONDARY_DELEGATIONS}" /></html-el:option>
				  <c:forEach var="delegator" items="${ActionListForm.delegators}">
					<html-el:option value="${delegator.recipientId}"><c:out value="${delegator.displayName}" /></html-el:option>
				  </c:forEach>
	            </html-el:select>
    		</div>
		</c:if>

		<!-- Primary Delegate selection list -->
		<c:if test="${! empty ActionListForm.primaryDelegates}">
			<html-el:hidden property="oldPrimaryDelegateId" value="${ActionListForm.primaryDelegateId}" />
			<div style="float:left; width:226px; position: relative; top: -.5em;">
				<html-el:select property="primaryDelegateId" onchange="document.forms[0].methodToCall.value='start';if(document.forms[0].delegationId){document.forms[0].delegationId.value='${Constants.DELEGATION_DEFAULT}';}document.forms[0].submit();">
					<html-el:option value="${Constants.PRIMARY_DELEGATION_DEFAULT}"><c:out value="${Constants.PRIMARY_DELEGATION_DEFAULT}" /></html-el:option>
					<html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_PRIMARY_DELEGATES}" /></html-el:option>
					<c:forEach var="primaryDelegate" items="${ActionListForm.primaryDelegates}">
						<html-el:option value="${primaryDelegate.recipientId}"><c:out value="${primaryDelegate.displayName}" /></html-el:option>
					</c:forEach>
				</html-el:select>
			</div>
		</c:if>

		<c:if test="${kewUserSession.actionListFilter != null && kewUserSession.actionListFilter.filterOn}">
		<div style="float:left; width:70px">
	   <a
         href='<c:out value="ActionList.do?methodToCall=clearFilter" />'  title="clearFilter"><img
         src="${ConfigProperties.kr.url}/images/tinybutton-clearfilter.gif" class="tinybutton" alt="clearFilter" title="clearFilter"
         border="0" /></a>
        </div>
		</c:if>

         <c:if test="${helpDeskActionList != null}">
         	<!--<p> Testing is this shows up on the screen </p> -->
            <div style="float:left">
			<html-el:text property="helpDeskActionListUserName" size="12" style="position: relative; top: -.35em;" />&nbsp;
            </div>
            <div style="float:left">
            <html-el:image src="${ConfigProperties.kr.url}/images/tinybutton-hlpdesk.gif" property="methodToCall.helpDeskActionListLogin" styleClass="tinybutton" />
            </div>
			<c:if test="${kewUserSession.helpDeskActionListPerson != null}">
				<a href="
					<c:url value="ActionList.do">
						<c:param name="methodToCall" value="clearHelpDeskActionListUser" />
					</c:url>">Clear <c:out value="${kewUserSession.helpDeskActionListPerson.name}"/>'s List</a>
			</c:if>&nbsp;&nbsp;
		</c:if>

    </div>
    </div>
	</div>

	<div align="right">
	<br/>
         <c:if
            test="${kewUserSession.helpDeskActionListPerson == null && ! empty actionList && ! empty ActionListForm.defaultActions}">
              <c:set var="defaultActions" value="${ActionListForm.defaultActions}" scope="request" />
              <html-el:select styleId='defaultAction' property="defaultActionToTake">
                    <html-el:options collection="defaultActions" labelProperty="value" property="key" filter="false" />
              </html-el:select>&nbsp;<html-el:img src="images/tinybutton-applydflt.gif" align="absmiddle" onclick="setActions();" /><br>
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
		<table width="100%">
			<tr>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
			<td>
			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
				<td></td>
				<td>
                  <kul:errors errorTitle="Error loading action list : "/> <br/>
				  <kul:messages/>
                </td>
                <td></td>
			</tr>

			<tr><td></td><td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>

						<c:choose>
							<c:when
								test="${ActionListForm.viewOutbox && ActionListForm.showOutbox}">
								<a href="<c:url value="ActionList.do?methodToCall=start&viewOutbox=false" />">
								    <bean-el:message key="actionList.ActionList.title" /></a>
                                | <strong><bean-el:message key="actionList.Outbox.title" /></strong>
							</c:when>
							<c:otherwise>
								<strong>
								<bean-el:message key="actionList.ActionList.title" /></strong>
								<c:if test="${ActionListForm.showOutbox }">
                                    | <a href="<c:url value="ActionList.do?methodToCall=start&viewOutbox=true" />">
                                        <bean-el:message key="actionList.Outbox.title" />
                                       </a>
								</c:if>
							</c:otherwise>
						</c:choose>

						</td>
                        <td>
                        <div align="right">
                          <c:if test="${ActionListForm.viewOutbox && ActionListForm.showOutbox && !ActionListForm.outBoxEmpty}">
                           <html-el:image
                              src="${ConfigProperties.kr.url}/images/buttonsmall_delselitems.gif" align="absmiddle"
                              property="methodToCall.removeOutboxItems" style="border-style:none;" />
                          </c:if>
                        </div>
                        </td>
					</tr>
					</table>
					</td>
					<td></td>
					</tr>
			<c:if
				test="${kewUserSession.actionListFilter.filterLegend != null && kewUserSession.actionListFilter.filterLegend != ''}">
					<tr>
				 	<td></td>
					<td><strong><c:out
					value="${kewUserSession.actionListFilter.filterLegend}" /></strong></td>
					<td></td>
					</tr>
			 </c:if>
			 <tr>
                <td colspan="3"><br /></td>
			</tr>
            <tr>
              <td></td>
              <td>
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <br />
				<display:table class="datatable-100" cellpadding="2" cellspacing="0"
					name="actionListPage" pagesize="${preferences.pageSize}"
					export="true" id="result" htmlId="row"
					decorator="org.kuali.rice.kew.actionlist.web.ActionListDecorator"
					excludedParams="*" requestURI="${actionListURI}">
					<display-el:setProperty name="export.banner" value="" />
					<display-el:setProperty name="css.tr.even" value="actionlist_anyRow" />
					<display-el:setProperty name="css.tr.odd" value="actionlist_anyRow" />
                    <c:if test="${kewUserSession.helpDeskActionListPerson == null && ActionListForm.hasDisplayParameters}">
  					  <display-el:column title="&nbsp;">
						<c:choose>
						   <c:when test="${result.displayParameters != null}">
                             <br>
                             <a id='A<c:out value="${result.actionItemIndex}"/>'
                              href="<c:url value="${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
                             <c:param name="docId" value="${result.routeHeaderId}"/>
                             <c:param name="command" value="displayActionListInlineView" />
                             </c:url>"
                             target='iframeAL_<c:out value="${result.actionItemIndex}"/>'
                             onclick="rend(this, false)"><img
                             src="images/tinybutton-show.gif" alt="show" width="45" height="15"
                             border="0" id='F<c:out value="${result.actionItemIndex}"/>'></a>
                             <br>
                           </c:when>
                           <c:otherwise>&nbsp;</c:otherwise>
						</c:choose>
                      </display-el:column>
                    </c:if>
					<display-el:column sortable="true" title="${documentIdLabel}"
						sortProperty="routeHeaderId">
						<c:choose>
							<c:when test="${kewUserSession.helpDeskActionListPerson == null}">
                                <a
									href="<c:url value="${Constants.DOC_HANDLER_REDIRECT_PAGE}" >
                                     <c:param name="${Constants.ROUTEHEADER_ID_PARAMETER}" value="${result.routeHeaderId}"/>
                                         <c:param name="${Constants.COMMAND_PARAMETER}" value="${Constants.ACTIONLIST_COMMAND}" />
                                             </c:url>"
									<c:if test="${ActionListForm.documentPopup}"> target="_blank" </c:if>
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
						<display-el:column property="routeHeader.combinedStatus"
							sortable="true" title="${routeStatusLabel}" class="infocell" />
					</c:if>
					<c:if test="${preferences.showActionRequested == Constants.PREFERENCES_YES_VAL}">
						<display-el:column property="actionRequestLabel" sortable="true"
							title="${actionRequestedLabel}" class="infocell" />
					</c:if>
					<c:if test="${preferences.showInitiator == Constants.PREFERENCES_YES_VAL}">
						<display-el:column sortable="true" title="${initiatorLabel}"
							sortProperty="routeHeader.initiatorName" class="infocell">
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
                                    <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" keyValues="groupId=${result.delegatorGroup.groupId}" render="true">
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
								<c:when test="${!empty result.groupId}">
                                    <kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.GroupImpl" keyValues="groupId=${result.group.groupId}" render="true">
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
						test="${! ActionListForm.viewOutbox && kewUserSession.helpDeskActionListPerson == null && ActionListForm.hasCustomActions && (ActionListForm.customActionList || (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL))}">
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

					<c:if test="${ActionListForm.viewOutbox }">
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
							href="<c:url value="RouteLog.do"><c:param name="routeHeaderId" value="${result.routeHeaderId}"/></c:url>"
							<c:if test="${ActionListForm.routeLogPopup}">target="_blank"</c:if>>
						<img alt="Route Log for Document"
							src="images/my_route_log.gif" /> </a></div>
					</display-el:column>
				</display:table>
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
				test="${kewUserSession.helpDeskActionListPerson == null && (! empty customActionsPresent) && (preferences.showClearFyi == Constants.PREFERENCES_YES_VAL || ActionListForm.customActionList)}">
				<tr>
					<td></td>
					<td height="0" class="tinybutton">
					<div align="center"><a id="takeMassActions"
						href="javascript: setMethodToCallAndSubmit('takeMassActions')">
					<img src="images/buttonsmall_takeactions.gif" /> </a></div>
					</td>
					<td></td>
				</tr>
			</c:if>
		</table>
		
		</td>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
		</tr>
	</table>
	</html-el:form>
</kul:page>
