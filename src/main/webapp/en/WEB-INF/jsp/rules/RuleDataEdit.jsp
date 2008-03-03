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

<%-- Parameters:
  rule - the rule to display data for (request)
  ruleProperty - the property name of the rule (param)
  ruleDelegation - reference to the rule delegation (request)
  delegationProperty - the property name of the rule delegation (param)
--%>
<c:set var="ruleProperty" value="${param['ruleProperty']}"/>
<c:set var="delegationProperty" value="${param['delegationProperty']}"/>

<!-- Rule <c:out value="${rule.ruleBaseValuesId}"/> -->

<c:set var="fromDateRef" value="${ruleProperty}.fromDateValue"/>
<c:set var="toDateRef" value="${ruleProperty}.toDateValue"/>
<c:set var="isDelegateRule" value="${Rule2Form.editingDelegate || ruleDelegation != null}"/>
<html-el:hidden property="${ruleProperty}.previousVersionId"/>
<html-el:hidden property="${ruleProperty}.currentInd" />
<html-el:hidden property="${ruleProperty}.versionNbr" />
<html-el:hidden property="${ruleProperty}.delegateRule" />
<html-el:hidden property="${ruleProperty}.ruleTemplateId" />
<html-el:hidden property="${ruleProperty}.docTypeName" />

<c:choose>
  <c:when test="${ruleDelegation != null && ruleDelegation.delegationRuleBaseValues.ruleTemplateId == null}">
    <tr>
	  <td width="20%" class="thnormal" align="right">Choose Delegation Rule Template:</td>
      <td width="30%" class="datacell">
      	<c:set var="templateConversion" value="${ruleProperty}.ruleTemplateId"/>
        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';document.forms[0].elements['conversionFields'].value = 'ruleTemplate.ruleTemplateId:${templateConversion}';"/>
        <div class="error-message"><html-el:errors property="${ruleProperty}.ruleTemplateId"/></div>
      </td>
    </tr>
  </c:when>
  <c:otherwise>

<%--
<html-el:hidden property="${ruleProperty}.routeHeaderId" />
<html-el:hidden property="${ruleProperty}.ruleBaseValuesId" />--%>
<script language="javascript">
  addCalendar("<c:out value="${fromDateRef}"/>", "Select Date", "<c:out value="${fromDateRef}"/>", "Rule2Form");
  addCalendar("<c:out value="${toDateRef}"/>", "Select Date", "<c:out value="${toDateRef}"/>", "Rule2Form");
</script>

<c:choose>
<c:when test="${ruleDelegation != null}">
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Delegation Type:</td>
  <td width="30%" class="datacell">
      <html-el:radio property="${delegationProperty}.delegationType" value="${Constants.DELEGATION_PRIMARY}"/>Primary Delegation<br>
      <html-el:radio property="${delegationProperty}.delegationType" value="${Constants.DELEGATION_SECONDARY}"/>Secondary Delegation
      <div class="error-message"><html-el:errors property="${ruleProperty}.delegationType"/></div>
  </td>
</tr>
</c:when>
<c:otherwise>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Document Type:</td>
  <td width="30%" class="datacell"><c:out value="${rule.docTypeName}" default="N/A"/></td>
</tr>
</c:otherwise>
</c:choose>
<%--
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Document Id:</td>
  <td width="30%" class="datacell"><c:out value="${rule.routeHeaderId}" default="N/A"/></td>
</tr>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Rule Id:</td>
  <td width="30%" class="datacell"><c:out value="${rule.ruleBaseValuesId}" default="N/A"/></td>
</tr>--%>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Rule Template:</td>
  <td width="30%" class="datacell">
    <c:out value="${rule.ruleTemplateName}"/>
    <div class="error-message"><html-el:errors property="${ruleProperty}.ruleTemplateId"/></div>
  </td>
</tr>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Description:</td>
  <td width="30%" class="datacell">
    <html-el:textarea property="${ruleProperty}.description" cols="40" rows="2"/>
    <div class="error-message"><html-el:errors property="${ruleProperty}.description"/></div>
  </td>
</tr>
<tr>
  <td align=right colspan="2" class="thnormal">From Date:</td>
  <td class="datacell">
    <html-el:text property="${fromDateRef}" size="10"/>
    &nbsp;
    <a href="javascript:showCal('<c:out value="${fromDateRef}"/>');">
      <img src="images/cal.gif" alt="Click Here to select the from date" align=middle height=16 width=16>
    </a>
    <div class="error-message"><html-el:errors property="${ruleProperty}.fromDateValue"/></div>
  </td>
</tr>
<tr>
  <td align=right colspan="2" class="thnormal">To Date:</td>
  <td class="datacell">
    <html-el:text property="${toDateRef}" size="10"/>
    &nbsp;
    <a href="javascript:showCal('<c:out value="${toDateRef}"/>');">
      <img src="images/cal.gif" alt="Click Here to select the from date" align=middle height=16 width=16>
    </a>
    <div class="error-message"><html-el:errors property="${ruleProperty}.toDateValue"/></div>
  </td>
</tr>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Ignore Previous:</td>
  <td width="30%" class="datacell">
    <html-el:radio property="${ruleProperty}.ignorePrevious" value="true" />Yes<br>
	<html-el:radio property="${ruleProperty}.ignorePrevious" value="false" />No
    <div class="error-message"><html-el:errors property="${ruleProperty}.ignorePrevious"/></div>
  </td>
</tr>
<tr>
  <td width="20%" colspan="2" align=right class="thnormal">Active:</td>
  <td width="30%" class="datacell">
    <html-el:radio property="${ruleProperty}.activeInd" value="true"/>Yes<br>
    <html-el:radio property="${ruleProperty}.activeInd" value="false"/>No
    <div class="error-message"><html-el:errors property="${ruleProperty}.activeInd"/></div>
  </td>
</tr>
<c:import url="RuleExtensionValuesEdit.jsp">
  <c:param name="ruleProperty" value="${ruleProperty}"/>
</c:import>

  </c:otherwise>
</c:choose>