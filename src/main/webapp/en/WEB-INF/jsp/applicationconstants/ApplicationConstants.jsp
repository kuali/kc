<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<html-el:html>
<head>
<style type="text/css">
   .highlightrow {}
   tr.highlightrow:hover, tr.over td { background-color: #66FFFF; }
</style>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/applicationconstants-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<html-el:form action="/ApplicationConstants.do">
<html-el:hidden property="methodToCall" />
<table width="100%" border=0 cellspacing=0 cellpadding=0>

  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
       <c:choose>
         <c:when test="${ApplicationConstantsForm.methodToCall == 'edit'}">
           <Strong>Edit application constant: </strong>
         </c:when>
         <c:otherwise>
           <strong>Create an application constant:</strong>
         </c:otherwise>
       </c:choose>
       <br>
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
            <div align="right">*Constant Name:&nbsp;</div>
          </th>
          <c:choose>
          <c:when test="${ApplicationConstantsForm.methodToCall == 'edit'}">
            <td class="datacell">
	          <html-el:text name="ApplicationConstantsForm" property="constant.applicationConstantName" readonly="true" styleClass="thnormal"/>
	        </td>
          </c:when>
          <c:otherwise>
	        <td class="datacell">
	          <html-el:text name="ApplicationConstantsForm" property="constant.applicationConstantName" />
	        </td>
          </c:otherwise>
          </c:choose>
        </tr>
        <tr>
          <th align="right" class="thnormal"  >
            <div align="right">*Constant Value:&nbsp;</div>
          </th>
          <td class="datacell">
            <html-el:text name="ApplicationConstantsForm" property="constant.applicationConstantValue" />
          </td>
        </tr>

        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal"  >
            <div align="center">
            <c:choose>
              <c:when test="${ApplicationConstantsForm.methodToCall == 'edit'}">
                <%-- <html-el:image property="ApplicationConstantsForm.methodToCall" value="save" onclick="javascript:setMethod('save')" src="images/buttonsmall_save.gif" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <html-el:image property="methodToCall.save" src="images/buttonsmall_save.gif" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <html-el:image property="methodToCall.cancel" src="images/buttonsmall_cancel.gif" align="absmiddle"/>
              </c:when>
              <c:otherwise>
                <html-el:image property="methodToCall.create" src="images/buttonsmall_save.gif" align="absmiddle" />&nbsp
                <html-el:image property="methodToCall.clear" src="images/buttonsmall_clearfields.gif" align="absmiddle" />
            </c:otherwise>
            </c:choose>
            </div>
          </th>
        </tr>
      </table>

    </td>
  </tr>

  <c:if test="${ApplicationConstantsForm.methodToCall != 'edit'}">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
       <strong>Existing application constants:</strong>
       <c:if test="${empty ApplicationConstantsForm.applicationConstants}">
  	   &nbsp;&nbsp;None.
  	   </c:if>
       <br>
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <c:if test="${! empty ApplicationConstantsForm.applicationConstants}">
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <th align="center" class="thnormal">
            <div align="center">Constant Name&nbsp;</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Constant Value&nbsp;</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Edit Constant</div>
          </th>
          <th align="center" class="thnormal">
            <div align="center">Delete Constant&nbsp;</div>
          </th>
        </tr>
        <c:forEach var="cnst" items="${ApplicationConstantsForm.applicationConstants}">
        <%-- mouseover and mouseout used for highlighting of rows; highlightrow is an empty style class used to identify these particular rows --%>
        <tr class="highlightrow" onmouseover="this.className = 'over';" onmouseout="this.className = this.className.replace('over', '');">
          <td class="datacell">
            <c:out value="${cnst.applicationConstantName}" />&nbsp;
          </td>
          <td class="datacell">
            <c:out value="${cnst.applicationConstantValue}" />&nbsp;
          </td>
          <td align="center" class="datacell">
            <div align="center"><a href="ApplicationConstants.do?methodToCall=edit&applicationConstantName=<c:out value="${cnst.applicationConstantName}" />">edit&nbsp;</a></div>
          </td>
          <td align="center" class="datacell">
            <div align="center"><a href="ApplicationConstants.do?methodToCall=confirmDelete&applicationConstantName=<c:out value="${cnst.applicationConstantName}" />">delete</div>
          </td>
        </tr>
        </c:forEach>
      </table>
    </td>
  </tr>
  </c:if> <!-- end of iterating through existing constants -->
  </c:if> <!-- end of testing for create vs. edit -->
  </table>
</html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>
