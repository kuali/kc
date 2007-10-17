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
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/workgroup-common.js"></script>
</head>

<body onload="javascript:appSpecificRouteRecipientLookup();">
<c:set var="ActionForm" value="${WorkgroupForm}" scope="request" />
<c:set var="actionName" value="${UrlResolver.workgroupUrl}" scope="request"/>

<html-el:form action="${actionName}">
  <html-el:hidden property="workgroupId" />
  <html-el:hidden property="workgroup.documentId" />
  <html-el:hidden property="workgroup.versionNumber" />
  <html-el:hidden property="workgroup.lockVerNbr" />
  <html-el:hidden property="showEdit" />
  <html-el:hidden property="docId" />
  <html-el:hidden property="lookupableImplServiceName" />
  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="conversionFields" value=""/>
  <html-el:hidden property="lookupType" />
  <html-el:hidden property="currentWorkgroupType" />

  <table width="100%" border=0 cellpadding=0 cellspacing=0>
    <tr>
      <td height=100 colspan=3>

      	<jsp:include page="../DocumentEntryHeader.jsp" />
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
	      <table width="100%" border=0 cellspacing=0 cellpadding=0>
	        <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
	        <tr>
	          <td width="20"><img src="images/pixel_clear.gif" alt="" width="1" height="1"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td><jsp:include page="../WorkflowMessages.jsp"/></td>
	          <td width="20"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>
	      </table>
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
		     		<td class="thnormal" colspan="2">&nbsp;</td>
				    <td class="thnormal">Existing Workgroup Values</td>
				    <td class="thnormal">New Workgroup Values</td>
	      		  </tr>
			      </c:if>
				  <tr>
					<td class="thnormal" align="right" colspan="2">*Workgroup Name:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.groupNameId.nameId}" /></td>
					</c:if>
					<td class="datacell"><html-el:text property="workgroupName" />&nbsp;<bean-el:message key="general.help.workGroupName"/></td>
		  		  </tr>
		  		  <tr>
		  		    <td class="thnormal" align="right" colspan="2">Type:</td>
		  		    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
		  		      <td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroupType.label}"  default="Default"/></td>
		  		    </c:if>
		  		    <td class="datacell">
		  		      <html-el:select property="workgroup.workgroupType" disabled="${!WorkgroupForm.workgroupTypeEditable}" onchange="post_to_action('Workgroup.do','refresh')">
				        <c:set var="workgroupTypes" value="${WorkgroupForm.workgroupTypes}"/>
					    <html-el:options collection="workgroupTypes" property="name" labelProperty="label"/>
				      </html-el:select>
				    </td>
		  		  </tr>
		  		  <tr>
					<td class="thnormal" align="right" colspan="2">*Active Indicator:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		  <td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.activeIndDisplay}" />&nbsp;</td>
				 	</c:if>
					<td class="datacell"><html-el:radio property="workgroup.activeInd" value="true" /> <bean-el:message key="general.label.active"/> &nbsp;
					  <html-el:radio property="workgroup.activeInd" value="false" /> <bean-el:message key="general.label.inactive"/> &nbsp;<bean-el:message key="general.help.workgroupActiveIndicator"/>
					</td>
		  		  </tr>
		  		  <c:set var="extensions" scope="request" value="${WorkgroupForm.extensions}"/>
			      <c:set var="existingExtensions" scope="request" value="${WorkgroupForm.existingExtensions}"/>
		  		  <c:import url="../extension/ExtensionDataEdit.jsp">
					  <c:param name="extensionsProperty" value="extensions"/>
				  </c:import>
		  	      <tr>
					<td class="thnormal" valign="top" align="right" colspan="2">*Members:</td>
					<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			  		<td class="datacell">
					  <c:forEach var="member" items="${WorkgroupForm.existingWorkgroupMembers}" >
					    <c:if test="${member.memberType == 'U'}">
	          			  <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}">
	            		    <c:param name="workflowId" value="${member.workflowId}" />
	            			<c:param name="methodToCall" value="report" />
                			<c:param name="showEdit" value="no" />
	          			  </c:url>
		  	  			  <c:set var="displayName" value="${member.displayName}"/>
			   			  <c:if test="${UserSession.workflowUser.workflowId != member.workflowId}">
  		         			<c:set var="displayName" value="${member.displayNameSafe}"/>
			   				</c:if>
		  			    <c:out value="${displayName}" />
		      			  (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${member.authenticationId}"/></a>)&nbsp;
		    			</c:if>
		    			<c:if test="${member.memberType == 'W'}">
		      			  <c:url var="workgroupReportUrl" value="${UrlResolver.workgroupReportUrl}">
		        			<c:param name="workgroupId" value="${member.workflowId}" />
	            			<c:param name="methodToCall" value="report" />
                			<c:param name="showEdit" value="no" />
	          			  </c:url>
		  	  			  <a href="<c:out value="${workgroupReportUrl}"/>"><c:out value="${member.displayName}" /></a>&nbsp;
		    			</c:if>
					    <br/>
			  	  	  </c:forEach>&nbsp;
			  		</td>
					</c:if>
					<td class="datacell">
					<html-el:select property="memberType">
				        <c:set var="memberTypes" value="${WorkgroupForm.memberTypes}"/>
					    <html-el:options collection="memberTypes" property="key" labelProperty="label"/>
				      </html-el:select>
					<html-el:text property="workgroupMember" />&nbsp;
					<html-el:image property="methodToCall.performLookup" src="${resourcePath}images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'memberLookup';"/>&nbsp;
					<html-el:image src="${resourcePath}images/tinybutton-addmember.gif" align="absmiddle" property="methodToCall.addMember" />&nbsp;<bean-el:message key="general.help.workGroupMember"/><br>
			  		  <logic-el:iterate id="member" name="WorkgroupForm" property="workgroupMembers" indexId="ctr">
	            	  <table width="100%" border=0 cellspacing=0 cellpadding=0>
	            		<tr>
	               		  <td width="50%">
	               		    <c:if test="${member.memberType == 'U'}">
	               		    <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}">
	               		        <c:param name="workflowId" value="${member.workflowId}" />
	               		    	<c:param name="methodToCall" value="report" />
                                <c:param name="showEdit" value="no" />
	               		    </c:url><c:set var="displayName" value="${member.displayName}"/>
			   <c:if test="${UserSession.workflowUser.workflowId != member.workflowId}">
  		         <c:set var="displayName" value="${member.displayNameSafe}"/>
			   </c:if>
		  			        <c:out value="${displayName}" />
		                    (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${member.authenticationId}"/></a>)&nbsp;
		                    </c:if>
		                    <c:if test="${member.memberType == 'W'}">
		                    <c:url var="workgroupReportUrl" value="${UrlResolver.workgroupReportUrl}">
		                    	<c:param name="workgroupId" value="${member.workflowId}" />
	               		    	<c:param name="methodToCall" value="report" />
                                <c:param name="showEdit" value="no" />
	               		    </c:url>
		  			        <a href="<c:out value="${workgroupReportUrl}"/>"><c:out value="${member.displayName}" /></a>&nbsp;
		                    </c:if>
					  	    <html-el:hidden property="workgroupMembers[${ctr}].workflowId" />
					  	    <html-el:hidden property="workgroupMembers[${ctr}].memberType" />
					  	    <html-el:hidden property="workgroupMembers[${ctr}].displayName" />
					  	    <html-el:hidden property="workgroupMembers[${ctr}].authenticationId" />
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
					<td class="thnormal" align="right" colspan="2">Description:</td>
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
				<jsp:include page="../DocumentEntryButtons.jsp"/>
			  </td>
    		  <td></td>
  			</tr>
		</table>
  </html-el:form>
<jsp:include page="../BackdoorMessage.jsp"/>

</body>
</html>