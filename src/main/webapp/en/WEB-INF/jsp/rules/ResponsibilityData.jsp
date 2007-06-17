<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="actionRequestCodes" value="${Rule2Form.actionRequestCodes}"/>	
<table width="100%" border=0 align=center cellpadding=0 cellspacing=0>
  <tr>
    <td width="20%" align=right class="thnormal">Type:</td>
    <td width="30%" class="datacell">
    <c:choose>
      <c:when test="${responsibility.usingWorkflowUser}">Person</c:when>
      <c:when test="${responsibility.usingWorkgroup}">Workgroup</c:when>
      <c:when test="${responsibility.usingRole}">Role</c:when>
      <c:otherwise>N/A</c:otherwise>
    </c:choose>
    </td>
  </tr>
  <tr>
    <td width="20%" align=right class="thnormal">Reviewer:</td>
    <td width="30%" class="datacell">
        <c:if test="${responsibility.usingWorkflowUser}">
        	<a href="<c:url value="${UrlResolver.userReportUrl}">
        			<c:param name="workflowId" value="${responsibility.reviewerId}"/>
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="showEdit" value="no"/>
	             </c:url>">
        </c:if>
        <c:if test="${responsibility.usingWorkgroup}">
        	<a href="<c:url value="${UrlResolver.workgroupReportUrl}">
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="workgroupId" value="${responsibility.reviewerId}"/>
	               <c:param name="showEdit" value="no"/>
	             </c:url>">
        </c:if>
    	<c:out value="${responsibility.reviewer}" default="N/A"/>
    	<c:if test="${responsibility.usingWorkflowUser||responsibility.usingWorkgroup}">
    		</a>
    	</c:if>
    </td>
  </tr>
<c:if test="${responsibility.usingRole}">
  <tr>
    <td width="20%" align=right class="thnormal">Approve Policy:</td>
    <td width="30%" class="datacell">
      <c:choose>
        <c:when test="${responsibility.approvePolicy == Constants.APPROVE_POLICY_FIRST_APPROVE}">
           FIRST <c:out value="${actionRequestCodes[responsibility.actionRequestedCd]}"/>
        </c:when>
        <c:when test="${responsibility.approvePolicy == Constants.APPROVE_POLICY_ALL_APPROVE}">
           ALL <c:out value="${actionRequestCodes[responsibility.actionRequestedCd]}"/>
        </c:when>
        <c:otherwise>N/A</c:otherwise>
      </c:choose>
	</td>
  </tr>
</c:if>
<c:if test="${!responsibility.ruleBaseValues.delegateRule}">
  <tr>
    <td width="20%" align=right class="thnormal">Action Request Code:</td>
    <td width="30%" class="datacell"><c:out value="${actionRequestCodes[responsibility.actionRequestedCd]}" default="N/A"/></td>
  </tr>
  <tr>
    <td width="20%" align=right class="thnormal">Priority:</td>
    <td width="30%" class="datacell"><c:out value="${responsibility.priority}" default="N/A"/></td>
  </tr>
</c:if>
</table>