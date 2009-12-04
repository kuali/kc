<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="KualiForm" value="${ActionListFilterForm}" scope="request"/>
<kul:page headerTitle="Action List Filter" lookup="false"
  headerMenuBar="" transactionalDocument="false" showDocumentInfo="false"
  htmlFormAction="ActionListFilter" docTitle="Action List Filter">

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="t3" summary="">
  <tbody>
    <tr>
      <td><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tl3"></td>
      <td align="right"><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tr3"></td>
    </tr>
  </tbody>
</table>
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="lookupType" />
<html-el:hidden property="docTypeFullName" />
<%--<html-el:hidden property="methodToCall" />--%>
<div id="workarea" >
<div class="tab-container" align="center">
  <table class="datatable-80" style="align:center" cellspacing="0" align="center">
    <tr>
      <td class="subhead" colspan="2"><bean-el:message key="actionList.ActionListFilter.filter.label.parametersTitle"/></td>
    </tr>
    <c:if test="${! empty delegators}">
        <tr>
	    <th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.secondaryDelegatorId"/>: <bean-el:message key="general.help.delegatorId"/></span></div></th>
	    <td>
		     <html-el:select property="filter.delegatorId" onchange="if(document.forms[0]['filter.primaryDelegateId']){document.forms[0]['filter.primaryDelegateId'].value='${Constants.PRIMARY_DELEGATION_DEFAULT}';}">
			   <html-el:option value="${Constants.DELEGATION_DEFAULT}"><c:out value="${Constants.DELEGATION_DEFAULT}" /></html-el:option>
			   <html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			   <c:forEach var="delegator" items="${delegators}">
				 <html-el:option value="${delegator.recipientId}"><c:out value="${delegator.displayName}" /></html-el:option>
			   </c:forEach>
		     </html-el:select>
        </td>
      </tr>
    </c:if>
    <c:if test="${! empty primaryDelegates}">
      <tr>
	    <th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.primaryDelegateId"/>: <bean-el:message key="general.help.primaryDelegateId"/></span></div></th>
	    <td class="datacell">
		     <html-el:select property="filter.primaryDelegateId" onchange="if(document.forms[0]['filter.delegatorId']){document.forms[0]['filter.delegatorId'].value='${Constants.DELEGATION_DEFAULT}';}">
			   <html-el:option value="${Constants.PRIMARY_DELEGATION_DEFAULT}"><c:out value="${Constants.PRIMARY_DELEGATION_DEFAULT}" /></html-el:option>
			   <html-el:option value="${Constants.ALL_CODE}"><c:out value="${Constants.ALL_CODE}" /></html-el:option>
			   <c:forEach var="delegatee" items="${primaryDelegates}">
				 <html-el:option value="${delegatee.recipientId}"><c:out value="${delegatee.displayName}" /></html-el:option>
			   </c:forEach>
		     </html-el:select>
        </td>
      </tr>
    </c:if>
	<tr>
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentTitle"/>: <bean-el:message key="general.help.documentTitle"/></span></div></th>
		<td><html-el:text property="filter.documentTitle"/>&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeDocumentTitle"/></td>
	</tr>
	<tr>
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentRouteStatus"/>: <bean-el:message key="general.help.routeStatus"/></span></div></th>
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
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.actionRequested"/>: <bean-el:message key="general.help.actionRequested"/></span></div></th>
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
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.actionRequestGroup"/>: <bean-el:message key="general.help.actionRequestWorkgroup"/></span></div></th>
		<td class="datacell">
		    <html-el:select name="ActionListFilterForm" property="filter.groupId">
              <html-el:optionsCollection property="userWorkgroups" label="value" value="key" filter="false"/>
            </html-el:select>&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeGroupId"/></td>
	</tr>
	<tr>
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.documentType"/>: <bean-el:message key="general.help.documentType"/></span></div></th>
		<td class="datacell"><span id="docTypeElementId"><c:out value="${ActionListFilterForm.docTypeFullName}" /></span>
		    <kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" fieldConversions="name:docTypeFullName"/>
			&nbsp;<bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeDocumentType"/>
	    </td>
	</tr>
	<tr>
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.dateCreated"/>: <bean-el:message key="general.help.dateCreated"/></span></div></th>
		<td class="datacell">
          <table class="neutral" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td class="neutral">
		        <table class="neutral" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="neutral" style="text-align:right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.from"/>:</td>
                    <td class="neutral" nowrap>
                      <html-el:text property="createDateFrom" styleId="createDateFrom" size="10"/>
                      <img src="images/cal.gif" id="createDateFrom_trigger" alt="Click Here to pick up the from date created" height="16" width="16" border="0"/>
                      <script type="text/javascript">
                          Calendar.setup({
                              inputField     :    "createDateFrom",     // id of the input field
                              ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                              button         :    "createDateFrom_trigger", // the button or image that triggers this
                              showsTime      :    false,            // will display a time selector
                              daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                              singleClick    :    true,
                              step           :    1
                          });
                      </script>&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td class="neutral" style="text-align:right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.to"/>:</td>
                    <td class="neutral" nowrap>
                      <html-el:text property="createDateTo" styleId="createDateTo" size="10"/>
                      <img src="images/cal.gif" id="createDateTo_trigger" alt="Click Here to pick up the to date created" height="16" width="16" border="0"/>
                      <script type="text/javascript">
                          Calendar.setup({
                              inputField     :    "createDateTo",     // id of the input field
                              ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                              button         :    "createDateTo_trigger", // the button or image that triggers this
                              showsTime      :    false,            // will display a time selector
                              daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                              singleClick    :    true,
                              step           :    1
                          });
                      </script>&nbsp;
                    </td>
                  </tr>
                </table>
              </td>
              <td  class="neutral">
		        <table  class="neutral" border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td  class="neutral" align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeCreateDate"/></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
		</td>
	</tr>
	<tr>
		<th><div align="right"><span class="thnormal"><bean-el:message key="actionList.ActionListFilter.filter.label.dateLastAssigned"/>: <bean-el:message key="general.help.dateLastAssigned"/></span></div></th>
		<td class="datacell" >
          <table class="neutral" >
            <tr>
              <td class="neutral">
                <table class="neutral"  border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td class="neutral" style="text-align:right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.from"/>:</td>
                    <td class="neutral"  nowrap>
                      <html-el:text property="lastAssignedDateFrom" styleId="lastAssignedDateFrom" size="10" />
                      <img src="images/cal.gif" id="lastAssignedDateFrom_trigger" alt="Click Here to select the last assigned from date" height="16" width="16" border="0"/>
                      <script type="text/javascript">
                          Calendar.setup({
                              inputField     :    "lastAssignedDateFrom",     // id of the input field
                              ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                              button         :    "lastAssignedDateFrom_trigger", // the button or image that triggers this
                              showsTime      :    false,            // will display a time selector
                              daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                              singleClick    :    true,
                              step           :    1
                          });
                      </script>&nbsp;
                    </td>
                  </tr>
                  <tr>
                    <td class="neutral" style="text-align:right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.to"/>:</td>
                    <td class="neutral" nowrap>
                      <html-el:text property="lastAssignedDateTo" styleId="lastAssignedDateTo" size="10" />
                      <img src="images/cal.gif" id="lastAssignedDateTo_trigger" alt="Click Here to select the last assigned to date" height="16" width="16" border="0"/>
                      <script type="text/javascript">
                          Calendar.setup({
                              inputField     :    "lastAssignedDateTo",     // id of the input field
                              ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                              button         :    "lastAssignedDateTo_trigger", // the button or image that triggers this
                              showsTime      :    false,            // will display a time selector
                              daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                              singleClick    :    true,
                              step           :    1
                          });
                      </script>&nbsp;
                    </td>
                  </tr>
                </table>
              </td>
              <td class="neutral">
		        <table class="neutral" border="0" cellspacing="0" cellpadding="1">
                  <tr>
                    <td class="neutral" align="right" nowrap><bean-el:message key="actionList.ActionListFilter.filter.label.exclude"/><html-el:checkbox property="filter.excludeLastAssignedDate"/></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
		</td>
    </tr>
  </table>
</div><!-- end div tabcontainer -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
  <tr>
    <td align="left" class="footer"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
    <td align="right" class="footer-right"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
  </tr>
</table>
<br />
<div align="center">
    <html-el:image property="methodToCall.filter" style="border-width:0px" src="images/buttonsmall_filter.gif" align="absmiddle" />&nbsp;&nbsp;
    <html-el:image property="methodToCall.clear" style="border-width:0px" src="images/buttonsmall_clear.gif" align="absmiddle" />&nbsp;&nbsp;
    <a href="javascript:document.forms[0].reset()"><img src="images/buttonsmall_reset.gif" border=0 alt="reset" align="absmiddle"></a>
    <a href="ActionList.do?methodToCall=start"><img src="images/buttonsmall_cancel.gif" border=0 alt="cancel" align="absmiddle"></a>
</div>
</div><!-- end div workarea -->
</kul:page>
