<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- 
  Displays General Rule Data in a row format
  
  Designed to be included within a table as a set of <tr> elements.
--%>

<%-- Parameters (request scope): 
  rule - the rule to display data for
--%>

<!-- Rule <c:out value="${rule.ruleBaseValuesId}"/> -->
<c:if test="${rule.delegateRule}">
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Delegation Type:</td>
  <td width="30%" class="datacell">
    <c:choose>
      <c:when test="${ruleDelegation != null}">
		    <c:choose>
		      <c:when test="${ruleDelegation.delegationType == Constants.DELEGATION_PRIMARY}">Primary</c:when>
		      <c:when test="${ruleDelegation.delegationType == Constants.DELEGATION_SECONDARY}">Secondary</c:when>
		      <c:otherwise>N/A</c:otherwise>
		    </c:choose>
		</c:when>
		<c:otherwise>
		    <c:choose>
		      <c:when test="${rule.ruleDelegation.delegationType == Constants.DELEGATION_PRIMARY}">Primary</c:when>
		      <c:when test="${rule.ruleDelegation.delegationType == Constants.DELEGATION_SECONDARY}">Secondary</c:when>
		      <c:otherwise>N/A</c:otherwise>
		    </c:choose>
		</c:otherwise>
	</c:choose>
  </td>
</tr>
</c:if>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Document Id:</td>
  <td width="30%" class="datacell">
    <c:choose>
      <c:when test="${rule.routeHeaderId != null}">
        <a href="<c:url value="RouteLog.do"><c:param name="routeHeaderId" value="${rule.routeHeaderId}"/></c:url>" <c:if test="${Rule2Form.routeLogPopup == Constants.RULE_ROUTE_LOG_POPUP_VALUE}">target="_blank"</c:if>>
		  <c:out value="${rule.routeHeaderId}"/><img alt="Route Log for Document" src="images/my_route_log.gif" />
		</a>
      </c:when>
      <c:otherwise>N/A</c:otherwise>
    </c:choose>
  </td>
</tr>
<c:if test="${rule.delegateRule}">
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Parent Rule:</td>
  <td width="30%" class="datacell">
    <c:choose>
      <c:when test="${ruleDelegation != null}">
    	<a href="<c:url value="Rule.do">
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="currentRuleId" value="${parentRuleId}"/>
	             </c:url>">
          <c:out value="${parentRuleId}" />
        </a>
      </c:when>
      <c:otherwise>
    	<a href="<c:url value="Rule.do">
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="currentRuleId" value="${rule.parentRuleId}"/>
	             </c:url>">
          <c:out value="${rule.parentRuleId}" />
        </a>
      </c:otherwise>
    </c:choose>
  </td>
</tr>
</c:if>
<c:if test="${!showPreviousVersion}">
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Previous Version:</td>
  <td width="30%" class="datacell">
    <c:choose>
      <c:when test="${rule.previousVersion != null}">
    	<a href="<c:url value="Rule.do">
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="currentRuleId" value="${rule.previousVersionId}"/>
	             </c:url>">
          <c:out value="${rule.previousVersionId}" />
        </a>
      </c:when>
      <c:otherwise>N/A</c:otherwise>
    </c:choose>
  </td>
</tr>
</c:if>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Rule Id:</td>
  <td width="30%" class="datacell"><c:out value="${rule.ruleBaseValuesId}" default="N/A"/></td>
</tr>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Rule Name:</td>
  <td width="30%" class="datacell"><c:out value="${rule.name}" default="<none>"/></td>
</tr>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Active:</td>
  <td width="30%" class="datacell"><c:out value="${rule.activeInd}"/></td>
</tr>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Description:</td>
  <td width="30%" class="datacell"><c:out value="${rule.description}" default=""/></td>
</tr>
<tr>
  <td align=right colspan="2" class="thnormal">From Date:</td>
  <td class="datacell"><c:out value="${rule.fromDateValue}" default="N/A"/></td>
</tr>
<tr>
  <td align=right colspan="2" class="thnormal">To Date:</td>
  <td class="datacell"><c:out value="${rule.toDateValue}" default="N/A"/></td>
</tr>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Document Type:</td>
  <td width="30%" class="datacell">
	<c:if test="${Rule2Form.docTypeId!=null}">
  	<a href="<c:url value="DocumentType.do">
	               <c:param name="methodToCall" value="report"/>
	               <c:param name="docTypeId" value="${Rule2Form.docTypeId}"/>
	             </c:url>">
   </c:if>
	    <c:out value="${rule.docTypeName}" default="N/A"/>
	<c:if test="${Rule2Form.docTypeId!=null}">
	</a>
	</c:if>
  </td>
</tr>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Ignore Previous:</td>
  <td width="30%" class="datacell"><c:out value="${rule.ignorePrevious}"/></td>
</tr>
<tr>
  <td width="20%" align=right colspan="2" class="thnormal">Rule Template:</td>
  <td width="30%" class="datacell"><c:out value="${rule.ruleTemplateName}"/></td>
</tr>
<jsp:include page="RuleExtensionValues.jsp"/>
<%--
<c:forEach var="ruleExtension" items="${rule.ruleExtensions}">
  <c:forEach var="ruleExtensionValue" items="${ruleExtension.extensionValues}">
    <tr>
	  <c:forEach var="extensionLabel" items="${rule.ruleExtensionValueLabels}" >
	    <c:if test="${ruleExtensionValue.key == extensionLabel.key}" >
		  <td width="20%" align="right" class="thnormal"><c:out value="${extensionLabel.value}" />:</td>
	    </c:if>
	  </c:forEach>
	  <td width="30%" class="datacell"><c:out value="${ruleExtensionValue.value}"/></td>
    </tr>
  </c:forEach>
</c:forEach>
--%>