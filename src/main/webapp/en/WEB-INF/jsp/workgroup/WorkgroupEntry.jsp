<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workgroup Document</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/workgroup-common.js"></script>
</head>

<body onload="javascript:appSpecificRouteRecipientLookup();">
<c:set var="ActionForm" value="${WorkgroupForm}" scope="request" />

<html-el:form action="${UrlResolver.workgroupUrl}">
  <html-el:hidden property="workgroupId" />
  <html-el:hidden property="workgroup.documentId" />
  <html-el:hidden property="workgroup.versionNumber" />
  <html-el:hidden property="workgroup.lockVerNbr" />
  <html-el:hidden property="showEdit" />
  <html-el:hidden property="docId" />
  <html-el:hidden property="lookupableImplServiceName" />
  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="lookupType" />

  <table width="100%" border=0 cellpadding=0 cellspacing=0>
    <tr>
      <td height=100 colspan=3>
        
      	<jsp:include page="../DocumentEntryHeader.jsp" flush="true" />
		<table width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor="#FFFFFF">
          <tr>
            <td width="20"><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width="1" height="1"><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
	            <table width="100%" border=0 cellspacing=0 cellpadding=0>
	              <tr>
	                <td height=30><strong>Workgroup</strong></td>
	              </tr>
	              <tr>
	                <td>
	                  <p><c:out value="${WorkgroupForm.instructionForCreateNew}" /></p>
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
			      <c:if test="${WorkgroupForm.existingWorkgroup != null}">
	              <tr>
		     		<td class="thnormal">&nbsp;</td>
				    <td class="thnormal">Existing Workgroup Values</td>
				    <td class="thnormal">New Workgroup Values</td>
	      		  </tr>
			      </c:if>
				  <tr>
					<td class="thnormal" align="right">*Workgroup Name:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.groupNameId.nameId}" /></td>
					</c:if>
					<td class="datacell"><html-el:text property="workgroupName" />&nbsp;<bean-el:message key="general.help.workGroupName"/></td>	
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right">*Active Indicator:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		  <td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.activeIndDisplay}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><html-el:radio property="workgroup.activeInd" value="true" /> <bean-el:message key="general.label.active"/> &nbsp;
					  <html-el:radio property="workgroup.activeInd" value="false" /> <bean-el:message key="general.label.inactive"/> &nbsp;<bean-el:message key="general.help.workgroupActiveIndicator"/>
					</td>
		  		  </tr>
		  	      <tr>
					<td class="thnormal" valign="top" align="right">*Members:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		<td class="datacell">
					  <c:forEach var="member" items="${WorkgroupForm.existingWorkgroup.members}" >
					    <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}"><c:param name="workflowId" value="${member.workflowUserId.workflowId}" />
					      <c:param name="methodToCall" value="report" />
                          <c:param name="showEdit" value="no" />	    
					    </c:url>
		  			    <c:out value="${member.displayName}" />
		                (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${member.authenticationUserId.authenticationId}"/></a>)&nbsp;
		                <br/>
			  	  	  </c:forEach>&nbsp;
			  		</td>
					</c:if>
					<td class="datacell"><html-el:text property="workgroupMember" />&nbsp;
					<html-el:image property="methodToCall.performLookup" src="${resourcePath}images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';"/>&nbsp;
					<html-el:image src="${resourcePath}images/tinybutton-addmember.gif" align="absmiddle" property="methodToCall.addMember" />&nbsp;<bean-el:message key="general.help.workGroupMember"/><br>
			  		  <logic-el:iterate id="member" name="WorkgroupForm" property="workgroup.members" indexId="ctr">
	            	  <table width="100%" border=0 cellspacing=0 cellpadding=0>
	            		<tr>
	               		  <td width="50%">
	               		    <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}"><c:param name="workflowId" value="${member.workflowUserId.workflowId}" />
	               		    	<c:param name="methodToCall" value="report" />
                                <c:param name="showEdit" value="no" />
	               		    </c:url>
		  			        <c:out value="${member.displayName}" />
		                    (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${member.authenticationUserId.authenticationId}"/></a>)&nbsp;
					  	    <html-el:hidden property="workgroupMembers[${ctr}].workflowId" />&nbsp;
	            		  </td>
		  	              <td width="50%"> 
	                		<html-el:image src="${resourcePath}images/tinybutton-removemember.gif" align="absmiddle" property="methodToCall.removeMember" onclick="removedMember.value=${ctr}" /><br>
	            		  </td> 
	            		</tr>  
	           		  </table>
			  		  </logic-el:iterate>	
			  			<html-el:hidden property="removedMember" />
					</td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right">*Description:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.description}" />&nbsp;</td>
					</c:if>
					<td class="datacell"><html-el:textarea rows="3" cols="30" property="workgroup.description" />&nbsp;<bean-el:message key="general.help.workgroupDescription"/></td>
		  		  </tr>
		 		</table>  
      		  </td>
      		  <td width=20><img src="<c:out value="${resourcePath}"/>images/pixel_clear.gif" alt="" width=20 height=20></td>
    		</tr>  
  			<tr>
    		  <td></td>
    		  <td>
    		    <c:set var="inputLocation" value="WorkgroupEntry.jsp" scope="request"/>
				<jsp:include page="../DocumentEntryButtons.jsp" flush="true" />
			  </td>
    		  <td></td>
  			</tr>	
		</table>
  </html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
	
</body>
</html>