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
<title>Help Search</title>
<link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<div id="headerarea-small" class="headerarea-small">
  <h1>Search Help Entry</h1>
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
  <a href="Help.do?methodToCall=start">
        <span class="maintext">Create New Help Entry</span></a>&nbsp;&nbsp;
</div>
<br clear="all"/>

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" value=""/>
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
      <strong>Search Help Entry</strong>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="datatable-80">
        <tr>
          <th width="33%" align=right class="grid">Help Id:</th>
          <td width="66%" class="grid"><html-el:text property="helpId" /></td>
        </tr>
        <tr>
          <th width="33%" align=right class="grid">Help Name:</th>
		  <td width="66%" class="grid"><html-el:text property="helpEntry.helpName" /></td>
        </tr>
        <tr>
          <th width="33%" align=right class="grid">Help Key:</th>
		  <td width="66%" class="grid"><html-el:text property="helpEntry.helpKey" /></td>
        </tr>
        <tr>
          <th width="33%" align=right class="grid">Help Text:</th>
		  <td width="66%" class="grid">
	          <html-el:textarea cols="80" rows="2" property="helpEntry.helpText" />
	      </td>
        </tr>
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="grid">
            <div align="center">
        	<html-el:image property="methodToCall.search" style="border-style:none;" src="images/buttonsmall_search.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
        	<html-el:image property="methodToCall.clearSearch" style="border-style:none;" src="images/buttonsmall_clear.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
            </div>
          </th>
        </tr>

      </table>
    </td>
    <td width="20" height="30">&nbsp;<a></td>
  </tr>

  <!--Display search results -->
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <c:if test="${!empty reqSearchResults}">
  <tr>
      <td>&nbsp;</td>
      <td>
        <div width="100%" align="right">Export results to:
            <a href="javascript:export_results('HelpForm', 'Help.do', 'XML')">XML</a>
        </div>
      </td>
      <td>&nbsp;</td>
  </tr>
  </c:if>
  <tr>
    <td></td>
    <td>
      <display:table class="datatable-100" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" id="row" defaultsort="1" defaultorder="descending" requestURI="Help.do?methodToCall=search">
        <display:column class="infocell" sortable="true" title="ID" sortProperty="helpId"><c:out value="${row.helpId}"/>&nbsp;</display:column>
  		<display:column class="infocell" sortable="true" title="Name" sortProperty="helpName"><c:out value="${row.helpName}"/>&nbsp;</display:column>
	    <display:column class="infocell" sortable="true" title="Key" sortProperty="helpKey"><c:out value="${row.helpKey}"/>&nbsp;</display:column>
	    <display:column class="infocell" sortable="true" title="Text" sortProperty="helpText"><c:out value="${row.helpText}" escapeXml="false"/>&nbsp;</display:column>
	    <display:column class="infocell" sortable="false" title="Action" ><a href="Help.do?methodToCall=report&helpId=<c:out value="${row.helpId}"/>&showEdit=yes">view</a> <c:if test="${HelpForm.isAdmin}"> | <a href="Help.do?methodToCall=showEdit&helpId=<c:out value="${helpEntry.helpId}"/>&showEdit=yes">edit</a> | <a href="Help.do?methodToCall=showDelete&helpId=<c:out value="${helpEntry.helpId}"/>&showDelete=yes"> delete </a> </c:if> </display:column>
	  </display:table>
    </td>
    <td></td>
  </tr>

</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>
