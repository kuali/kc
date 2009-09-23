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
<title>Help Entry</title>

<link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>

</head>
<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

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
        <span class="maintext">Search Help Entry</span></a>
</div>
<br clear="all"/>

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" value=""/>
<html-el:hidden property="helpEntry.lockVerNbr" />
<html-el:hidden property="helpEntry.helpId" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
      <strong><c:if test="${HelpForm.showEdit == 'yes'}">Edit</c:if> Help Entry</strong>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="datatable-80">
        <tr>
          <th width="33%" align=right class="grid">*Help Name:</th>
		  <td width="66%" class="grid">
            <html-el:text property="helpEntry.helpName" />
            <kul:checkErrors keyMatch="helpEntry.helpName" />
            <c:if test="${hasErrors}">
              <kul:fieldShowErrorIcon />
            </c:if>
            &nbsp;&nbsp;<a href="javascript:workflowHelpPop('HelpName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
          </td>
        </tr>
        <tr>
          <th width="33%" align=right class="grid">*Help Key:</th>
		  <td width="66%" class="grid" >
            <html-el:text property="helpEntry.helpKey" />
            <kul:checkErrors keyMatch="helpEntry.helpKey" />
            <c:if test="${hasErrors}">
              <kul:fieldShowErrorIcon />
            </c:if>
            &nbsp;&nbsp;<a href="javascript:workflowHelpPop('HelpKey')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
          </td>
        </tr>
        <tr>
         <th width="33%" align=right class="grid">*Help Text:</th>
		 <td width="66%" class="grid">
		   <table>
		     <tr>
		       <td>
                 <html-el:textarea cols="120" rows="5" property="helpEntry.helpText" />
                 <kul:checkErrors keyMatch="helpEntry.helpText" />
                 <c:if test="${hasErrors}">
                   <kul:fieldShowErrorIcon />
                 </c:if>
               </td>
		       <td align="middle"><a href="javascript:workflowHelpPop('HelpText')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a></td>
		     </tr>
		   </table>
		 </td>
        </tr>
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="grid">
            <div align="center">
        	<html-el:image property="methodToCall.save" style="border-style:none;" src="images/buttonsmall_save.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
            </div>
          </th>
        </tr>
      </table>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>
