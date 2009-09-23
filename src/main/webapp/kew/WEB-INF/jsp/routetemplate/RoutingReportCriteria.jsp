<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<kul:page headerTitle="Routing Report" transactionalDocument="false"
  showDocumentInfo="false" htmlFormAction="RoutingReport" docTitle="Routing Report">

  <%--<html-el:form action="RoutingReport.do">--%>
    <html-el:hidden name="KualiForm" property="methodToCall" value="" />
    <script language="javaScript" src="scripts/en-common.js"></script>

    <c:if test="${KualiForm.reportType != null}">
      <html-el:hidden property="reportType" />
    </c:if>


    <%--
      <tr>
        <td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
      </tr>
    --%>
    <c:if test="${KualiForm.displayCloseButton}">
      <table width="95%" align="center" >
        <tr>
          <td align="center"><a href="#" onclick="javascript:window.close();"><img src="images/buttonsmall_close.gif" alt="Close This Window" /></a></td>
        </tr>
      </table>
    </c:if>
    <c:if test="${KualiForm.reportType == 'template'}">
      <table class="datatable-100" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <th class="grid" width="35%" scope="col" align="left">Select A Rule Template</th>
          <td class="grid">
            <html-el:select property="ruleTemplateId" onchange="document.forms[0].methodToCall.value='start';document.forms[0].submit();">
              <c:set var="ruleTemplates" value="${KualiForm.ruleTemplates}"/>
              <html-el:option value="chooser">Please select a template</html-el:option>
              <html-el:options collection="ruleTemplates" property="ruleTemplateId" labelProperty="name"/>
            </html-el:select>
          </td>
        </tr>
      </table>
      <br>
      <c:if test="${KualiForm.showFields}">
        <table class="datatable-100" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <th class="grid" colspan="3" scope="col" align="left">Enter Routing Data</th>
          </tr>
          <tr>
            <th class="grid" scope="col" align="left" width="35%">Effective Date:</th>
            <td class="grid">
              <html-el:text property="dateRef" styleId="dateRef" size="10"/>&nbsp;
              <img src="images/cal.gif" id="dateRef_trigger" alt="Click Here to select the from date" align="middle" height="16" width="16"/>
              <script type="text/javascript">
                  Calendar.setup({
                      inputField     :    "dateRef",     // id of the input field
                      ifFormat       :    "%m/%d/%Y",     // format of the input field (even if hidden, this format will be honored)
                      button         :    "dateRef_trigger", // the button or image that triggers this
                      showsTime      :    false,            // will display a time selector
                      daFormat       :    "%A, %B %d, %Y",// format of the displayed date
                      singleClick    :    true,
                      step           :    1
                  });
              </script>
              &nbsp;&nbsp;Time:&nbsp;
              <c:set var="hour" value="${KualiForm.hours}" />
              <html-el:select property="effectiveHour">
                <html-el:options collection="hour" labelProperty="value" property="key"/>
              </html-el:select>
              <c:set var="min" value="${KualiForm.minutes}" />
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
            <th class="grid" width="35%" scope="col" align="left">Document Type:</th>
            <td class="grid">
              <html-el:text property="documentType" />&nbsp;
              <kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" fieldConversions="name:documentType"/>
            </td>
          </tr>

          <c:set var="FieldRows" value="${KualiForm.ruleTemplateAttributes}" scope="request" />
          <c:set var="ActionName" value="RoutingReport.do" scope="request" />
          <kul:rowDisplay rows="${FieldRows}" skipTheOldNewBar="true" />

          <c:if test="${KualiForm.showViewResults}">
            <tr>
              <td colspan="2" class="thnormal" height="30" align="center">
                <html-el:image style="border-width:0px" src="images/buttonsmall_viewresults.gif" alt="View results" align="absmiddle" property="methodToCall.calculateRoute" />
              </td>
            </tr>
          </c:if>
        </table>
      </c:if>
    </c:if>
  <%--</html-el:form>--%>
</kul:page>
