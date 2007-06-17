<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

	            <table width="100%" border="0" cellpadding="0" cellspacing="0">
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
			  		    <td class="datacell"><c:out value="${WorkflowUserForm.existingWorkflowId}" />&nbsp;</td>
					  </c:if>
					  <td class="datacell"><c:out value="${WorkflowUserForm.workflowId}" />&nbsp;</td>	
		  		    </tr>
			      </c:if>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Network Id:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		  <td class="datacell"><c:out value="${WorkflowUserForm.existingAuthenticationId}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.authenticationId}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">University Id:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingEmplId}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.emplId}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">UUID:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUuId}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.uuId}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Full Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.displayName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.user.displayName}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Given Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.givenName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.user.givenName}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Last Name:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.lastName}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.user.lastName}" />&nbsp;</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" width="20%">Email Address:</td>
					<c:if test="${WorkflowUserForm.existingUser != null}">
			  		<td class="datacell"><c:out value="${WorkflowUserForm.existingUser.emailAddress}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><c:out value="${WorkflowUserForm.user.emailAddress}" />&nbsp;</td>
		  		  </tr>
		 		</table>