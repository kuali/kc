<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Workflow User Report</title>
    <link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
  </head>
  
  <body>

    <html-el:form action="${UrlResolver.userUrl}">
    <html-el:hidden name="WorkflowUserForm" property="workflowId" />
    <html-el:hidden name="WorkflowUserForm" property="methodToCall" />

    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
        <td width="90%">
          <c:if test="${UserCaps.lookupSupported}"><a href="<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=UserLookupableImplService" >User Search</a></c:if>&nbsp;</td>
      </tr>
    </table>

    <table width="100%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td>
		  <table width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor="#FFFFFF">
            <tr>
              <td width="20"><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width="1" height="1"><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
	            <table width="100%" border=0 cellspacing=0 cellpadding=0>
	              <tr>
	                <td height=30><strong>Workflow User Report</strong></td>
	              </tr>
	            </table>
	          </td>
	          <td width="20"><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	  
	  <tr>
	    <td>
		  <jsp:include page="../WorkflowMessages.jsp" flush="true" />
	    </td>
	  </tr>
	
	  <tr>
	    <td>    
          <table width="100%" border=0 cellspacing=0 cellpadding=0>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width=20><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
              <td>
                <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
                  <tr>
  	                <td>
                      <jsp:include page="WorkflowUserDisplay.jsp" flush="true" />
                    </td>  
                  </tr>
                  <c:if test="${WorkflowUserForm.showEdit == Constants.PREFERENCES_YES_VAL && UserCaps.editSupported}">
                    <tr>
                      <td colspan="2" align="center" class="thnormal"  >
                        <html-el:image property="methodToCall.edit" src="${resourcePath}images/buttonsmall_edit.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
                      </td>    
                    </tr>
                  </c:if>
                </table>  
      		  </td>
      		  <td width=20><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
    		</tr>  
    </table>
    </html-el:form>

    <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
	
  </body>

</html>
