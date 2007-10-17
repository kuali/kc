<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>Remove/Replace User Document</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/removereplace.js"></script>
</head>

<body>

<c:set var="ActionForm" value="${RemoveReplaceForm}" scope="request"/>

<html-el:form action="RemoveReplace">

  <html-el:hidden property="methodToCall" /><br>
  <html-el:hidden property="docId" /><br>
  Operation: <html-el:radio property="operation" value="P"/>Replace <html-el:radio property="operation" value="M"/>Remove
  <br>
  User Id: <html-el:text property="userId"/><br>
  User Id to Replace With: <html-el:text property="replacementUserId"/><br><br>
<hr>
  Rules:<br>
  Document Type: <html-el:text property="ruleDocumentTypeName"/><br>
  Rule Template: <html-el:text property="ruleRuleTemplate"/><br>
  <input type="button" value="Choose Rules" onclick="javascript:setMethodToCallAndSubmit('chooseRules')"/><br>
  <c:set var="ruleIndex" value="0"/>
  <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.rules}" defaultsort="1" id="rule" requestURI="RemoveReplace.do"
       decorator="edu.iu.uis.eden.lookupable.LookupDecorator" >

       <c:set var="ruleProp" value="rules[${ruleIndex}]"/>

	   <display-el:column class="datacell" sortable="false" title="<input type=&quot;checkbox&quot; id=&quot;masterRuleCheckbox&quot; onclick=&quot;javascript:selectAllRuleCheckboxes(${fn:length(RemoveReplaceForm.rules)})&quot;>" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator">
	     <html-el:checkbox styleId="${ruleProp}.selected" property="${ruleProp}.selected"/>
	   </display-el:column>
       <display-el:column class="datacell" sortable="true" title="Id"decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Rule.do?methodToCall=report&currentRuleId=<c:out value="${rule.rule.ruleBaseValuesId}"/>"><c:out value="${rule.rule.ruleBaseValuesId}"/></a>
       </display-el:column>
       <display-el:column class="datacell" sortable="true" title="Document Type" property="rule.docTypeName" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
       <display-el:column class="datacell" sortable="true" title="Rule Template" property="ruleTemplateName" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
       <display-el:column class="datacell" sortable="true" title="Description" property="rule.description" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
       <display-el:column class="datacell" sortable="true" title="Active" property="rule.activeInd" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
       <display-el:column class="datacell" sortable="true" title="Delegate Rule" property="rule.delegateRule" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
	   <display-el:column class="datacell" sortable="true" title="Warnings" property="warning" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>

       <c:set var="ruleIndex" value="${ruleIndex + 1}"/>

  </display-el:table>
  <c:forEach items="${RemoveReplaceForm.rules}" varStatus="status">
  	<c:set var="ruleProp" value="rules[${status.index}]"/>
  	<html-el:hidden property="${ruleProp}.rule.ruleBaseValuesId"/>
	<html-el:hidden property="${ruleProp}.rule.docTypeName"/>
	<html-el:hidden property="${ruleProp}.ruleTemplateName"/>
    <html-el:hidden property="${ruleProp}.rule.description"/>
    <html-el:hidden property="${ruleProp}.rule.activeInd"/>
    <html-el:hidden property="${ruleProp}.rule.delegateRule"/>
    <html-el:hidden property="${ruleProp}.warning"/>
  </c:forEach>
<hr>
  <br><br>

  Workgroups:<br>
  Workgroup Type: <html-el:select property="workgroupType">
    <c:set var="workgroupTypes" value="${RemoveReplaceForm.workgroupTypes}"/>
	<html-el:options collection="workgroupTypes" property="name" labelProperty="label"/>
  </html-el:select><br>
  <input type="button" value="Choose Workgroups" onclick="javascript:setMethodToCallAndSubmit('chooseWorkgroups')"/><br><br>
  <c:set var="workgroupIndex" value="0"/>
  <display-el:table class="bord-r-t" style="width:100%" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.workgroups}" defaultsort="1" id="workgroup" requestURI="RemoveReplace.do"
       decorator="edu.iu.uis.eden.lookupable.LookupDecorator" >
       <c:set var="wgProp" value="workgroups[${workgroupIndex}]"/>
	   <display-el:column class="datacell" sortable="false" title="<input type=&quot;checkbox&quot; id=&quot;masterWorkgroupCheckbox&quot; onclick=&quot;javascript:selectAllWorkgroupCheckboxes(${fn:length(RemoveReplaceForm.workgroups)})&quot;>" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator">
	     <html-el:checkbox styleId="${wgProp}.selected" property="${wgProp}.selected"/>
	   </display-el:column>
       <display-el:column class="datacell" sortable="true" title="Id"decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Workgroup.do?methodToCall=report&workgroupId=<c:out value="${workgroup.id}"/>"><c:out value="${workgroup.id}"/></a>
       </display-el:column>
       <display-el:column class="datacell" sortable="true" title="Name" property="name" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
       <display-el:column class="datacell" sortable="true" title="Type" property="type" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>
	   <display-el:column class="datacell" sortable="true" title="Warnings" property="warning" decorator="edu.iu.uis.eden.lookupable.LookupColumnDecorator"/>

       <c:set var="workgroupIndex" value="${workgroupIndex + 1}"/>
  </display-el:table>

  <c:forEach items="${RemoveReplaceForm.workgroups}" varStatus="status">
  	<c:set var="workgroupProp" value="workgroups[${status.index}]"/>
  	<html-el:hidden property="${workgroupProp}.id"/>
	<html-el:hidden property="${workgroupProp}.name"/>
	<html-el:hidden property="${workgroupProp}.type"/>
    <html-el:hidden property="${workgroupProp}.warning"/>
  </c:forEach>

  <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t" >
    <tr>
      <td class="thnormal" align="center" colspan="6">
        <c:set var="inputLocation" value="RemoveReplaceEntry.jsp" scope="request"/>
		<jsp:include page="../DocumentEntryButtons.jsp" />
	  </td>
	</tr>
  </table>
  </html-el:form>
<jsp:include page="../BackdoorMessage.jsp"/>

</body>
</html>