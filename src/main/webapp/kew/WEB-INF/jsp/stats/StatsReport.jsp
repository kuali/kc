<%--
 Copyright 2008-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="KualiForm" value="${StatsForm}" scope="request"/>
<kul:page headerTitle="Workflow Statistics" lookup="false"
  headerMenuBar="" transactionalDocument="false" showDocumentInfo="false"
  htmlFormAction="Stats" docTitle="Workflow Statistics">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="t3" summary="">
    <tbody>
      <tr>
        <td><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tl3"></td>
        <td align="right"><img src="images/pixel_clear.gif" alt="" width="12" height="12" class="tr3"></td>
      </tr>
    </tbody>
  </table>
  <div id="workarea" >
    <div class="tab-container" align="center">
      <html-el:hidden property="methodToCall" value="" />
      <html-el:hidden property="backLocation" />
      <table class="datatable-80" style="align:center" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td colspan="2" class="subhead"><bean-el:message key="general.label.aggregates"/></td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><bean-el:message key="stats.StatsReport.numDocTypes"/>:</span></div></th>
          <td class="datacell" width="50%"><c:out value="${StatsForm.stats.numDocTypes}"/>&nbsp;</td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><bean-el:message key="stats.StatsReport.numUsers"/>:</span></div></th>
          <td class="datacell" width="50%"><c:out value="${StatsForm.stats.numUsers}"/>&nbsp;</td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><bean-el:message key="stats.StatsReport.numActionItems"/>:</span></div></th>
          <td class="datacell" width="50%"><c:out value="${StatsForm.stats.numActionItems}"/>&nbsp;</td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><bean-el:message key="stats.StatsReport.numInitiatedDocsByDocType"/>:</span></div></th>
          <td width="50%">
            <c:if test="${! empty StatsForm.stats.numInitiatedDocsByDocType}">
              <table class="neutral" width="100%" cellpadding="0" cellspacing="0">
                <c:forEach var="document" items="${StatsForm.stats.numInitiatedDocsByDocType}" >
                  <tr>
                    <td class="datacell" width="50%"  nowrap><c:out value="${document.key}"/>&nbsp;</td>
                    <td class="datacell" width="50%" ><c:out value="${document.value}"/>&nbsp;</td>
                  </tr>
                </c:forEach>
              </table>
            </c:if>
          </td>
        </tr>
      </table>
      <br /><br />
      <table class="datatable-80" style="align:center" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="2" class="subhead"><bean-el:message key="general.label.dateRange"/></td>
        </tr>
        <tr>
        <tr>
          <td colspan="2" class="thnormal" style="text-align:center">
            <bean-el:message key="general.label.beginDate"/>:&nbsp;<html-el:text property="begDate" styleId="begDate"/>&nbsp;
            <img src="images/cal.gif" id="begDate_trigger" alt="Click Here to select the begin date" height="16" width="16"/>
            <script type="text/javascript">
                      Calendar.setup({
                      inputField     :    "begDate",     // id of the input field
                      ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                      button         :    "begDate_trigger", // the button or image that triggers this
                      showsTime      :    false,            // will display a time selector
                      daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                      singleClick    :    true,
                      step           :    1
                    });
            </script> &nbsp;&nbsp;
            <bean-el:message key="general.label.endDate"/>:&nbsp;<html-el:text property="endDate" styleId="endDate"/>&nbsp;
            <img src="images/cal.gif" id="endDate_trigger" alt="Click Here to select the begin date" height="16" width="16"/>
            <script type="text/javascript">
                    Calendar.setup({
                      inputField     :    "endDate",     // id of the input field
                      ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                      button         :    "endDate_trigger", // the button or image that triggers this
                      showsTime      :    false,            // will display a time selector
                      daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                      singleClick    :    true,
                      step           :    1
                    });
            </script> &nbsp;&nbsp;
          </td>
        </tr>
        <tr>
          <th class="subhead" width="50%"><bean-el:message key="general.label.routeStatus"/></th>
          <th class="subhead" width="50%"><bean-el:message key="stats.StatsReport.routeStatusCount"/></th>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.approvedLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.approvedNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.canceledLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
              <c:out value="${StatsForm.stats.canceledNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.disapprovedLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.disapprovedNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.enrouteLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.enrouteNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.exceptionLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.exceptionNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.finalLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.finalNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.initiatedLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.initiatedNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.processedLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.processedNumber}" />&nbsp;
          </td>
        </tr>
        <tr>
          <th width="50%"><div align="right"><span class="thnormal"><c:out value="${StatsForm.savedLabel}" />:</span></div></th>
          <td class="datacell" width="50%">
            <c:out value="${StatsForm.stats.savedNumber}" />&nbsp;
          </td>
        </tr>
      </table>
    </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
      <tr>
        <td align="left" class="footer"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
        <td align="right" class="footer-right"><img src="images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
      </tr>
    </table>
    <br />
    <div width="100%" style="text-align:center">
      <html-el:image src="images/buttonsmall_refresh.gif" styleClass="tinybutton" align="absmiddle" />&nbsp;&nbsp;
      <a href="${KualiForm.backLocation}"><img src="images/buttonsmall_cancel.gif" class="tinybutton" align="absmiddle" alt="cancel" /></a>
    </div>
  </div>
</kul:page>
