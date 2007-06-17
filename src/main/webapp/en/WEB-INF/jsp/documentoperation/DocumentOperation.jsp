<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="JavaScript" src="scripts/documentoperation-common.js"></script>
</head>
<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="15%"><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<html-el:form action="/DocumentOperation.do">
<html-el:hidden property="methodToCall" />
<html-el:hidden property="lookupInvocationModule" />
<html-el:hidden property="lookupInvocationField" />
<html-el:hidden property="lookupInvocationIndex" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
      <strong>Operate on a document</strong>  
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
          <th align="right" class="thnormal"  >
            <div align="right">*Document ID:&nbsp;</div>
          </th>
		  <td class="datacell">
	          <%-- <html-el:text property="routeHeader.routeHeaderId" /> --%>
	          <html-el:text property="routeHeaderId" />
	      </td>
        </tr>
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal">
            <div align="center">
        	<html-el:image property="methodToCall.getDocument" src="images/buttonsmall_getdoc.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
        	<html-el:image property="methodToCall.clear" src="images/buttonsmall_clear.gif" align="absmiddle" tabindex="2" />
            </div>
          </th>
        </tr>
      </table> 
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>  

 <c:if test="${DocumentOperationForm.routeHeader.routeHeaderId != null && DocumentOperationForm.routeHeader.routeHeaderId != 0}"> 
  <tr>
  	<td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  	<td> &nbsp;</td>
  	<td width="20" height="10">&nbsp;</td>
  </tr>
  <jsp:include page="RouteHeader.jsp" flush="true"/>
  <jsp:include page="ActionRequests.jsp" flush="true"/>
  <jsp:include page="ActionsTaken.jsp" flush="true"/>
  <jsp:include page="ActionItems.jsp" flush="true"/>
  <jsp:include page="RouteNodeInstances.jsp" flush="true"/>
  <jsp:include page="BranchStates.jsp" flush="true"/>
   
   <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="10"></td>
  	<td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
		  <td class="thnormal" colspan=2 align=center height=30><strong>Annotation for Admin Change</strong></td>
	    </tr>
        <tr>
          <td width="25%" align=right valign=top class="thnormal">Annotation:</td>
          <td width="75%" class="datacell">
            <html-el:textarea property="annotation" cols="150" rows="5" />
          </td>
        </tr>
      </table>
    </td>
    <td width="20" height="10">&nbsp;</td>
  </tr>
  
   <tr>
  	<td><img src="images/pixel_clear.gif" alt="" width="20" height="10"></td>
  	<td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal">
            <div align="center">
        	<html-el:image property="methodToCall.save" src="images/buttonsmall_save.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
            </div>
          </th>
        </tr>
      </table> 
    </td>
  	<td width="20" height="10">&nbsp;</td>
  </tr>
  </c:if>
</table> 
</html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>
