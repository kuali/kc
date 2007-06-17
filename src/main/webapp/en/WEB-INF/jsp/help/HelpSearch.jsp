<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<title>Help Search</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
      <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
    <a href="Help.do?methodToCall=start">
        <span class="maintext">Create New Help Entry</span></a>&nbsp;&nbsp;
    </td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" />
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
      <strong>Search Help Entry</strong>  
       <html-el:messages id="msg">
		 <font color="red"><c:out value="${msg}"/></font><br>
	   </html-el:messages>   
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <td width="33%" align=right class="thnormal">Help Id:</td>     
		  <td width="66%" class="datacell"><html-el:text property="helpEntry.helpId" /></td>
        </tr>
        <tr>
          <td width="33%" align=right class="thnormal">Help Name:</td>     
		  <td width="66%" class="datacell"><html-el:text property="helpEntry.helpName" /></td>
        </tr>
        <tr>
          <td width="33%" align=right class="thnormal">Help Key:</td>     
		  <td width="66%" class="datacell"><html-el:text property="helpEntry.helpKey" /></td>
        </tr>
        <tr>
          <td width="33%" align=right class="thnormal">Help Text:</td>     
		  <td width="66%" class="datacell">
	          <html-el:textarea cols="80" rows="2" property="helpEntry.helpText" />
	      </td>
        </tr>
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal">
            <div align="center">
        	<html-el:image property="methodToCall.search" src="images/buttonsmall_search.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
        	<html-el:image property="methodToCall.clearSearch" src="images/buttonsmall_clear.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
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
      <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${reqSearchResults}" id="helpEntry" defaultsort="1" defaultorder="descending" requestURI="Help.do?methodToCall=search">
        <display-el:column class="datacell" sortable="true" title="ID" sortProperty="helpId"><c:out value="${helpEntry.helpId}"/>&nbsp;</display-el:column>
  		<display-el:column class="datacell" sortable="true" title="Name" sortProperty="helpName"><c:out value="${helpEntry.helpName}"/>&nbsp;</display-el:column>
	    <display-el:column class="datacell" sortable="true" title="Key" sortProperty="helpKey"><c:out value="${helpEntry.helpKey}"/>&nbsp;</display-el:column>
	    <display-el:column class="datacell" sortable="true" title="Text" sortProperty="helpText"><c:out value="${helpEntry.helpText}" escapeXml="false"/>&nbsp;</display-el:column>
	    <display-el:column class="datacell" sortable="false" title="Action" ><a href="Help.do?methodToCall=report&helpId=<c:out value="${helpEntry.helpId}"/>&showEdit=yes">view</a> <c:if test="${HelpForm.isAdmin}"> | <a href="Help.do?methodToCall=showEdit&helpId=<c:out value="${helpEntry.helpId}"/>&showEdit=yes">edit</a> | <a href="Help.do?methodToCall=showDelete&helpId=<c:out value="${helpEntry.helpId}"/>&showDelete=yes"> delete </a> </c:if> </display-el:column>
	  </display-el:table>
    </td>
    <td></td>
  </tr> 

</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>