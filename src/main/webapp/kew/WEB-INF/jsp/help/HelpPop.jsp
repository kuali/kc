<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<html-el:html>
<head>
<title>Help Pop</title>
<link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>
<%--
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
      <img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
      <a href="Help.do?methodToCall=getSearch">
        <span class="maintext">Search Help Entry</span></a>&nbsp;&nbsp;
    </td>
  </tr>
</table>
--%>
<div id="headerarea-small" class="headerarea-small">
  <h1>Help</h1>
</div>
<div class="msg-excol">
  <div class="left-errmsg">
     <kul:errorCount />
     <html-el:messages id="msg">
       <font color="red"><c:out value="${msg}"/></font><br>
     </html-el:messages>
  </div>
</div>
<div class="lookupcreatenew" style="padding: 3px 30px 3px 3px;" title="Supplemental Actions">
  <a href="Help.do?methodToCall=getSearch">
        <span class="maintext">Search Help Entry</span></a>&nbsp;&nbsp;
</div>
<br clear="all"/>

<table width="90%" border=0 align=center>
  <tr>
    <td>
      <table>
        <tr>
          <td valign='top' width='100%' colspan="2" class="channel-text">
            <br />
            <strong>
              <center>
                  <c:out value="${HelpForm.helpEntry.helpName}"/>
              </center>
            </strong>

            <p>
              <c:out value="${HelpForm.helpEntry.helpText}" escapeXml="false"/>
            </p>

          </td>
        </tr>
        </table>

      </table>
      <br/>
    </td>
  </tr>
</table>

</body>
</html-el:html>
