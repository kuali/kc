<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<html>
<head>
<title>Help Summary</title>
<link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>
<div id="headerarea-small" class="headerarea-small">
  <h1>Help Summary</h1>
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
  <a href="javascript:setMethod('getSearch');document.forms[0].submit();">
        <span class="maintext">Help Search</span></a>&nbsp;&nbsp;
  <a href="javascript:setMethod('start');document.forms[0].submit();">
        <span class="maintext">Create New Help Entry</span></a>&nbsp;&nbsp;
</div>
<br clear="all"/>

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" value=""/>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
  	<td></td>
  	<td>
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
  	      <td>
             <jsp:include page="HelpDisplay.jsp" flush="true" />
          </td>
        </tr>
      </table>
    </td>
	<td></td>
  </tr>
</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html>