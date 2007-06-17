<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
		<tr>
			<td class="thnormal" width="25%">&nbsp;</td>
			<td class="thnormal">Existing Workgroup Values</td>
			<td class="thnormal">New Workgroup Values</td>
		</tr>
		</c:if>
		<c:if test="${WorkgroupForm.routable}">
		<tr>
		  	<td class="thnormal" width="25%" align="right">Document Id:</td>
		  <c:if test="${WorkgroupForm.existingWorkgroup != null}">
              <td class="datacell">
              <c:out value="${WorkgroupForm.existingWorkgroup.documentId}" />  
              <c:if test="${WorkgroupForm.existingWorkgroup.documentId != null && WorkgroupForm.existingWorkgroup.documentId > 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${WorkgroupForm.existingWorkgroup.documentId}"/></c:url>">
	            <img alt="Route Log for Document" src="<c:out value="${resourcePath}"/>images/my_route_log.gif"/></a></c:if>&nbsp;
	          </td>
		  </c:if>
          <td class="datacell">
             
             <c:out value="${WorkgroupForm.workgroup.documentId}" />  <c:if test="${WorkgroupForm.workgroup.documentId != null && WorkgroupForm.workgroup.documentId > 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${WorkgroupForm.workgroup.documentId}"/></c:url>">
	          <img alt="Route Log for Document" src="<c:out value="${resourcePath}"/>images/my_route_log.gif"/></a></c:if>&nbsp;
          </td>
		</tr>
		</c:if>
		<tr>
		  	<td class="thnormal" width="25%" align="right">Workgroup Id:</td>
		  <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.workflowGroupId.groupId}" />&nbsp;</td>
		  </c:if>
			<td class="datacell"><c:out value="${WorkgroupForm.workgroup.workflowGroupId.groupId}" />&nbsp;</td>			
		</tr>
	  <tr>
	 	<td class="thnormal" width="25%" align="right">Workgroup Name:</td>
	    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.groupNameId.nameId}" />&nbsp;</td>
		</c:if>
	    <td class="datacell"><c:out value="${WorkgroupForm.workgroup.groupNameId.nameId}" />&nbsp;</td>
	  </tr>
	  
	  <tr>
	    <td class="thnormal" width="25%" align="right">Workgroup Active Indicator:</td>
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.activeIndDisplay}" />&nbsp;</td>
		</c:if>	    
		<td class="datacell"><c:out value="${WorkgroupForm.workgroup.activeIndDisplay}" />&nbsp;</td>
	  </tr>
	  
	  <tr>
	  	<td class="thnormal" valign="top" width="25%" align="right">Workgroup Members:</td>
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
		  <td class="datacell">
			<c:forEach var="member" items="${WorkgroupForm.existingWorkgroup.members}" >
		  		<c:out value="${member.displayName}" /> <br />
		  	</c:forEach>&nbsp;
		  </td>
	  	</c:if>
	  	<td class="datacell">
	  	  <c:choose>
	  	  <c:when test="${empty WorkgroupForm.workgroup.members}">
	  	  	&nbsp;
	  	  </c:when>
	  	  <c:otherwise>
		  <c:forEach var="member" items="${WorkgroupForm.workgroup.members}" >
		  <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}"><c:param name="workflowId" value="${member.workflowUserId.workflowId}" />
		  	<c:param name="methodToCall" value="report" />
            <c:param name="showEdit" value="no" />
		  </c:url>
		  <c:out value="${member.displayName}" />
		  (<a href="<c:out value="${userReportUrl}"/>"><c:out value="${member.authenticationUserId.authenticationId}"/></a>)&nbsp;
		  <br/>
		  </c:forEach>
		  </c:otherwise>
		  </c:choose>
		</td>
	  </tr>
	  
	  <tr>
	    <td class="thnormal" width="25%" align="right">Workgroup Description:</td>
	    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.description}" />&nbsp;</td>
		</c:if>
	    <td class="datacell"><c:out value="${WorkgroupForm.workgroup.description}" />&nbsp;</td>	    
	  </tr>
	  <c:if test="${! empty Annotation}">
		  <tr>
			<td class="thnormal" width="25%" align="right">Annotation:</td>
			<td class="datacell" colspan="2"><c:out value="${Annotation}" /></td>
		  </tr>
	  </c:if>
</table>