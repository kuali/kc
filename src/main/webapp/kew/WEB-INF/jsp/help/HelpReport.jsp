<%--
 Copyright 2007-2009 The Kuali Foundation
 
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

<html-el:html>
<head>
<title>Help Report</title>
<link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>

<div id="headerarea-small" class="headerarea-small">
  <h1>Help Entry</h1>
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

<table width="100%" border=0 cellspacing=0 cellpadding=0>
<html-el:form action="/Help.do">
  <html-el:hidden property="helpEntry.helpId" />
  <html-el:hidden property="helpEntry.helpName" />
  <html-el:hidden property="helpEntry.helpKey" />
  <html-el:hidden property="helpEntry.helpText" />
  <html-el:hidden property="helpEntry.lockVerNbr" />
  <html-el:hidden property="methodToCall" value=""/>
  <html-el:hidden property="showEdit" />

  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td><strong>Help Entry</strong></td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
  	<td></td>
  	<td>
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" >
        <tr>
  	      <td>
             <jsp:include page="HelpDisplay.jsp" flush="true" />
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <c:if test="${HelpForm.showEdit == 'yes'}">
          <tr>
            <td colspan="2" align="center" class="thnormal"  >
              <html-el:image style="border-style:none;" property="methodToCall.showEdit" src="images/buttonsmall_edit.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
          </tr>
        </c:if>
      </table>
    </td>
	<td></td>
  </tr>
</html-el:form>
</table>
</body>
</html-el:html>
