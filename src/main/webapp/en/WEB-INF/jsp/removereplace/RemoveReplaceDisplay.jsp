<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

This is my display<br>

<%--
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
		<tr>
			<td class="thnormal" width="25%" colspan="2">&nbsp;</td>
			<td class="thnormal">Existing Workgroup Values</td>
			<td class="thnormal" colspan="3">New Workgroup Values</td>
		</tr>
		</c:if>
		<c:if test="${WorkgroupForm.routable}">
		<tr>
		  	<td class="thnormal" width="25%" align="right" colspan="2">Document Id:</td>
		  <c:if test="${WorkgroupForm.existingWorkgroup != null}">
              <td class="datacell">
              <c:out value="${WorkgroupForm.existingWorkgroup.documentId}" />
              <c:if test="${WorkgroupForm.existingWorkgroup.documentId != null && WorkgroupForm.existingWorkgroup.documentId > 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${WorkgroupForm.existingWorkgroup.documentId}"/></c:url>">
	            <img alt="Route Log for Document" src="<c:out value="${resourcePath}"/>images/my_route_log.gif"/></a></c:if>&nbsp;
	          </td>
		  </c:if>
          <td class="datacell" colspan="3">

             <c:out value="${WorkgroupForm.workgroup.documentId}" />  <c:if test="${WorkgroupForm.workgroup.documentId != null && WorkgroupForm.workgroup.documentId > 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${WorkgroupForm.workgroup.documentId}"/></c:url>">
	          <img alt="Route Log for Document" src="<c:out value="${resourcePath}"/>images/my_route_log.gif"/></a></c:if>&nbsp;
          </td>
		</tr>
		</c:if>
		<tr>
		  	<td class="thnormal" width="25%" align="right" colspan="2">Workgroup Id:</td>
		  <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.workflowGroupId.groupId}" />&nbsp;</td>
		  </c:if>
			<td class="datacell" colspan="3"><c:out value="${WorkgroupForm.workgroup.workflowGroupId.groupId}" />&nbsp;</td>
		</tr>
	  <tr>
	 	<td class="thnormal" width="25%" align="right" colspan="2">Workgroup Name:</td>
	    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.groupNameId.nameId}" />&nbsp;</td>
		</c:if>
	    <td class="datacell" colspan="3"><c:out value="${WorkgroupForm.workgroup.groupNameId.nameId}" />&nbsp;</td>
	  </tr>
	  <tr>
	 	<td class="thnormal" width="25%" align="right" colspan="2">Workgroup Type:</td>
	    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroupType.label}" default="Default"/>&nbsp;</td>
		</c:if>
	    <td class="datacell" colspan="3"><c:out value="${WorkgroupForm.workgroupType.label}" default="Default"/>&nbsp;</td>
	  </tr>

	  <tr>
	    <td class="thnormal" width="25%" align="right" colspan="2">Workgroup Active Indicator:</td>
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.activeIndDisplay}" />&nbsp;</td>
		</c:if>
		<td class="datacell" colspan="3"><c:out value="${WorkgroupForm.workgroup.activeIndDisplay}" />&nbsp;</td>
	  </tr>

      <c:set var="extensions" scope="request" value="${WorkgroupForm.extensions}"/>
      <c:set var="existingExtensions" scope="request" value="${WorkgroupForm.existingExtensions}"/>
	  <c:import url="../extension/ExtensionData.jsp"/>

	  <tr>
	  	<td class="thnormal" valign="top" width="25%" align="right" colspan="2">Workgroup Members:</td>
		<c:if test="${WorkgroupForm.existingWorkgroup != null}">
		  <td class="datacell">
			<c:forEach var="member" items="${WorkgroupForm.existingWorkgroupMembers}" >
		  		<c:if test="${member.memberType == 'U'}">
				<c:set var="displayName" value="${member.displayName}"/>
			   <c:if test="${UserSession.workflowUser.workflowId != member.workflowId}">
  		         <c:set var="displayName" value="${member.displayNameSafe}"/>
			   </c:if>
	          <c:url var="userReportUrl" value="${UrlResolver.userReportUrl}">
	            <c:param name="workflowId" value="${member.workflowId}" />
	            <c:param name="methodToCall" value="report" />
                <c:param name="showEdit" value="no" />
	          </c:url>
		  	  <c:out value="${member.displayName}" />
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
		    <br>
		  	</c:forEach>
		  </td>
	  	</c:if>
	  	<td class="datacell" colspan="3">
	  	  <c:choose>
	  	  <c:when test="${empty WorkgroupForm.workgroup.members}">
	  	  	&nbsp;
	  	  </c:when>
	  	  <c:otherwise>
		  <c:forEach var="member" items="${WorkgroupForm.workgroupMembers}" >
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
		  <c:out value="${member.displayName}" />
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
		  </c:forEach>
		  </c:otherwise>
		  </c:choose>
		</td>
	  </tr>

	  <tr>
	    <td class="thnormal" width="25%" align="right" colspan="2">Workgroup Description:</td>
	    <c:if test="${WorkgroupForm.existingWorkgroup != null}">
			<td class="datacell"><c:out value="${WorkgroupForm.existingWorkgroup.description}" />&nbsp;</td>
		</c:if>
	    <td class="datacell" colspan="3"><c:out value="${WorkgroupForm.workgroup.description}" />&nbsp;</td>
	  </tr>
	  <c:if test="${! empty Annotation}">
		  <tr>
			<td class="thnormal" width="25%" align="right">Annotation:</td>
			<td class="datacell" colspan="2"><c:out value="${Annotation}" /></td>
		  </tr>
	  </c:if>
</table>
--%>