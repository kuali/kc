<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Workflow User Entry</title>
    <link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/user-common.js"></script>
  </head>
  
  <body>

    <html-el:form action="${UrlResolver.userUrl}">
    <html-el:hidden name="WorkflowUserForm" property="workflowId" />
    <html-el:hidden name="WorkflowUserForm" property="methodToCall" />
    <html-el:hidden name="WorkflowUserForm" property="showEdit" />
    <html-el:hidden name="WorkflowUserForm" property="user.lockVerNbr" />
    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
        <td width="90%">
          <c:if test="${UserCaps.lookupSupported}"><a href="<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=UserLookupableImplService">User Search</a></c:if>
          <c:if test="${UserCaps.createSupported}"><c:if test="${UserCaps.lookupSupported}"> | </c:if><a href="<c:out value="${UrlResolver.userUrl}"/>" >Create new User</a></c:if>
          &nbsp;
        </td>
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
	                <td height=30><strong>Workflow User</strong></td>
	              </tr>
	              <tr>
	                <td>
	                  <p><c:out value="${WorkflowUserForm.instructionForCreateNew}" /></p>
	                </td>
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
	            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
			      <c:if test="${WorkflowUserForm.existingUser != null}">
	              <tr>
		     		<td class="thnormal" width="20%">&nbsp;</td>
				    <td class="thnormal">Existing Workflow User Values</td>
				    <td class="thnormal">New Workflow User Values</td>
	      		  </tr>
			      </c:if>
                  <c:if test="${WorkflowUserForm.workflowId != null && WorkflowUserForm.workflowId != ''}">
				    <tr>
					  <td class="thnormal" align="right" width="20%">Workflow User Id:</td>
					  <c:if test="${WorkflowUserForm.existingUser != null}">
			  		    <td class="datacell"><c:out value="${WorkflowUserForm.existingWorkflowId}" /></td>
					  </c:if>
					  <td class="datacell"><c:out value="${WorkflowUserForm.workflowId}" />&nbsp;<bean-el:message key="general.help.workflowId" arg0="${resourcePath}"/></td>	
		  		    </tr>
			      </c:if>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Network Id:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		  <td class="datacell"><c:out value="${WorkflowUserForm.existingAuthenticationId}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><html-el:text property="authenticationId" maxlength="30" />&nbsp;<bean-el:message key="general.help.networkId" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">University Id:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingEmplId}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="emplId" maxlength="11" />&nbsp;<bean-el:message key="general.help.emplId" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">UUID:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUuId}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="uuId" maxlength="10" />&nbsp;<bean-el:message key="general.help.uuId" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Full Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.displayName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="user.displayName" size="50" />&nbsp;<bean-el:message key="general.help.displayName" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Given Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.givenName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="user.givenName" size="50" />&nbsp;<bean-el:message key="general.help.givenName" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Last Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.lastName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="user.lastName" size="50" />&nbsp;<bean-el:message key="general.help.lastName" arg0="${resourcePath}"/></td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Email Address:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.emailAddress}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:text property="user.emailAddress" size="50" />&nbsp;<bean-el:message key="general.help.emailAddress" arg0="${resourcePath}"/></td>
		  		  </tr>
	              <tr>
		            <td class="thnormal" colspan="3" align="center">
		              <html-el:image src="${resourcePath}images/buttonsmall_save.gif" align="absmiddle" property="methodToCall.save" onclick="setMethod('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;
		              <html-el:image src="${resourcePath}images/buttonsmall_clear.gif" align="absmiddle" property="methodToCall.clear"/>
		            </td>
	              </tr>
		 		</table>  
      		  </td>
      		  <td width=20><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
    		</tr>  
  </table>
  </html-el:form>

  <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
	
  </body>

</html>
