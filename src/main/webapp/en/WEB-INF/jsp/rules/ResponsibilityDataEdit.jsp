<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  isDelegateResponsibility - true if this responsibility is on a delegate rule (param)
  responsibility - responsibility object (request)
  responsibilityProperty - property path to the responsibility object (param)
--%>
<c:set var="isDelegateResponsibility" value="${param['isDelegateResponsibility']}"/>
<c:set var="responsibilityProperty" value="${param['responsibilityProperty']}"/>

<c:set var="actionRequestCodes" value="${rule.actionRequestCodes}"/>
<c:set var="roles" value="${rule.roles}"/>

<html-el:hidden property="${responsibilityProperty}.ruleResponsibilityKey" />
<html-el:hidden property="${responsibilityProperty}.numberOfDelegations" />
<html-el:hidden property="${responsibilityProperty}.responsibilityId" />	
<html-el:hidden property="${responsibilityProperty}.showDelegations" />	
<html-el:hidden property="${responsibilityProperty}.delegationRulesMaterialized" />	
<html-el:hidden property="${responsibilityProperty}.hasDelegateRuleTemplate" />	

<table width="100%" border=0 align=center cellpadding=0 cellspacing=0>
  <tr>
    <td width="20%" align=right class="thnormal">Type:</td>
    <td width="30%" class="datacell">
      <c:if test="${!isDelegateResponsibility}">
        <c:set var="responsibilityJs" value="javascript:responsibilityType(${ruleIndex},${respIndex})"/>
        <c:set var="respStyleId" value="rule${ruleIndex}resp${respIndex}"/>
      </c:if>
      <c:if test="${isDelegateResponsibility}">
        <c:set var="responsibilityJs" value="javascript:responsibilityType(${ruleIndex},${respIndex},${delIndex},${delRespIndex})"/>
        <c:set var="respStyleId" value="rule${ruleIndex}resp${respIndex}delRule${delIndex}delResp${delRespIndex}"/>
      </c:if>
      <html-el:radio styleId="${respStyleId}F" property="${responsibilityProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" onclick="${responsibilityJs}"/>person&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <html-el:radio styleId="${respStyleId}W" property="${responsibilityProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" onclick="${responsibilityJs}"/>workgroup&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <c:if test="${!empty roles}">
		<html-el:radio styleId="${respStyleId}R" property="${responsibilityProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_ROLE_ID}" onclick="${responsibilityJs}"/>role
	  </c:if>
	  <div class="error-message"><html-el:errors property="${responsibilityProperty}.ruleResponsibilityType"/></div>
    </td>
  </tr>
  <tr>
    <td width="20%" align=right class="thnormal">Reviewer:</td>
    <td width="30%" class="datacell">
      <span id="<c:out value="${respStyleId}REV"/>" style='<c:out value="${responsibility.reviewerStyle}" />'>
		<html-el:text property="${responsibilityProperty}.reviewer" />
      </span>
	  <span id="<c:out value="${respStyleId}PL"/>" style='<c:out value="${responsibility.personLookupStyle}" />'>
		<c:set var="userConversion" value="${responsibilityProperty}.reviewer"/>
        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';document.forms[0].elements['conversionFields'].value = 'networkId:${userConversion}';"/>
      </span>
      <span id="<c:out value="${respStyleId}WL"/>" style='<c:out value="${responsibility.workgroupLookupStyle}" />'>
        <c:set var="workgroupConversion" value="${responsibilityProperty}.workgroupId"/>
        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['conversionFields'].value = 'workgroupId:${workgroupConversion}';"/>
	  </span>
  	  <div class="error-message"><html-el:errors property="${responsibilityProperty}.reviewer"/></div>
	  <span id="<c:out value="${respStyleId}RA"/>" style='<c:out value="${responsibility.roleAreaStyle}" />'>
		<c:if test="${!empty roles }">
		  <html-el:select property="${responsibilityProperty}.roleReviewer">
		    <html-el:options collection="roles" property="name" labelProperty="label" />
		  </html-el:select>
		  <br>
		  <html-el:radio property="${responsibilityProperty}.approvePolicy" value="${Constants.APPROVE_POLICY_FIRST_APPROVE}">First Approve</html-el:radio><br>
		  <html-el:radio property="${responsibilityProperty}.approvePolicy" value="${Constants.APPROVE_POLICY_ALL_APPROVE}">All Approve</html-el:radio>
		</c:if>
		<div class="error-message">
		  <html-el:errors property="${responsibilityProperty}.responsibility[${respIndex}].ruleResponsibilityName"/>
		  <html-el:errors property="${responsibilityProperty}.responsibility[${respIndex}].approvePolicy"/>
		</div>
	  </span>
	</td>
  </tr>
  <c:if test="${!responsibility.ruleBaseValues.delegateRule}">
  <tr>
    <td width="20%" align=right class="thnormal">Action Request Code:</td>
    <td width="30%" class="datacell">
      <html-el:select property="${responsibilityProperty}.actionRequestedCd">
		<c:set var="actionRequestCodes" value="${rule.actionRequestCodes}"/>
		<html-el:options collection="actionRequestCodes" property="key" labelProperty="value" />
	  </html-el:select>
	  <div class="error-message"><html-el:errors property="${responsibilityProperty}.actionRequestedCd"/></div>
	</td>				      
  </tr>
  <tr>
    <td width="20%" align=right class="thnormal">Priority:</td>
    <td width="30%" class="datacell">
      <html-el:select property="${responsibilityProperty}.priority">
		<html-el:option value="1">1</html-el:option>
		<html-el:option value="2">2</html-el:option>
		<html-el:option value="3">3</html-el:option>
		<html-el:option value="4">4</html-el:option>
		<html-el:option value="5">5</html-el:option>
		<html-el:option value="6">6</html-el:option>
		<html-el:option value="7">7</html-el:option>
		<html-el:option value="8">8</html-el:option>
		<html-el:option value="9">9</html-el:option>
		<html-el:option value="10">10</html-el:option>
		<html-el:option value="11">11</html-el:option>								
      </html-el:select>
  	  <div class="error-message"><html-el:errors property="${responsibilityProperty}.priority"/></div>
  	</td>
  </tr>
  </c:if>
</table>