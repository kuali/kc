<%--
 Copyright 2008-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

          <table width="100%" cellpadding="0"  cellspacing="0" class="tab" summary="">
            <tr>
              <td class="tabtable1-left"><img src="images/tab-topleft.gif" alt="#" width="12" height="29" align="absmiddle">Rules</td>
              <td class="tabtable1-mid"><span class="tabtable2-mid">
                <c:set var="showHide" value="${RemoveReplaceForm.showHide}" scope="request"/>
                <c:set var="showHideProperty" value="showHide" scope="request"/>
                <c:set var="ruleIndex" value="0" scope="request"/>
                <c:import url="../rules/ShowHideButton.jsp">
                  <c:param name="idVal" value="1"/>
                </c:import>


              <!-- <a href="#" id="A02" onclick="rend(this, false)"><img src="images/tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 id="F02" ></a> -->

              </td>
              <td class="tabtable1-right"><img src="images/tab-topright.gif" alt="#" width="12" height="29" align="absmiddle"></td>
            </tr>
          </table>

          <!-- TAB -->
          <div class="tab-container" align="center" id="G1" <c:if test="${!showHide.children[ruleIndex].show}">style="display:none"</c:if>>
            <div class="h2-container"> <span class="subhead-left">
              <h2>Rules</h2>
              </span>
            </div>
            <br>
            <table border="0" align="center" cellpadding="0" cellspacing="0" style="width:auto; background-color:#e4e4e4" class="nobord">

              <tr>
                <td class="nobord"style="width:auto; background-color:#e4e4e4"><div align="right"><strong>Document Type:</strong></div></td>
                <td class="nobord"style="width:auto; background-color:#e4e4e4"><html-el:text property="ruleDocumentTypeName" styleClass="text"/></td>
              </tr>
              <tr>
                <td class="nobord"style="width:auto; background-color:#e4e4e4"><div align="right"><strong>Rule Template:</strong></div></td>
                <td class="nobord"style="width:auto; background-color:#e4e4e4"><html-el:text property="ruleRuleTemplate" styleClass="text"/></td>
              </tr>

              <tr>
                <td colspan="2" class="nobord"style="width:auto; background-color:#e4e4e4"><div align="center">
                    <html-el:image styleClass="nobord" styleId="imageField" src="images/tinybutton-chooserules.gif" property="methodToCall.chooseRules"/>
                  </div></td>
              </tr>
            </table>
            <br>
            <c:set var="ruleIndex" value="0"/>
  <display-el:table class="result-table" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.rules}" defaultsort="2" id="rule" requestURI="RemoveReplace.do"
       decorator="org.kuali.rice.kew.lookupable.LookupDecorator" >

       <c:set var="ruleProp" value="rules[${ruleIndex}]"/>

	   <display-el:column sortable="false" title="<div align=&quot;center&quot;><input type=&quot;checkbox&quot; id=&quot;masterRuleCheckbox&quot; onclick=&quot;javascript:selectAllRuleCheckboxes(${fn:length(RemoveReplaceForm.rules)})&quot;></div>" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <div align="center">
	        <c:if test="${rule.disabled}">
	          <img src="images/errormark.gif" alt="warning" width="10" height="10">
	        </c:if>
	        <c:if test="${!rule.disabled}">
	     	  <html-el:checkbox styleId="${ruleProp}.selected" property="${ruleProp}.selected"/>
	     	</c:if>
	     </div>
	   </display-el:column>
       <display-el:column sortable="true" title="Id"decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Rule.do?methodToCall=report&currentRuleId=<c:out value="${rule.rule.ruleBaseValuesId}"/>"><c:out value="${rule.rule.ruleBaseValuesId}"/></a>
       </display-el:column>
       <display-el:column sortable="true" title="Document Type" property="rule.docTypeName" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Rule Template" property="ruleTemplateName" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Description" property="rule.description" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Active" property="rule.activeInd" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Delegate Rule" property="rule.delegateRule" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
	   <display-el:column sortable="true" title="Warnings" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <c:if test="${!empty rule.warning}"><img src="images/errormark.gif" alt="warning" width="10" height="10"> <c:out value="${rule.warning}"/></c:if>
	   </display-el:column>

       <c:set var="ruleIndex" value="${ruleIndex + 1}"/>

  </display-el:table>

          </div>

            <c:forEach items="${RemoveReplaceForm.rules}" varStatus="status">
  	<c:set var="ruleProp" value="rules[${status.index}]"/>
  	<html-el:hidden property="${ruleProp}.rule.ruleBaseValuesId"/>
	<html-el:hidden property="${ruleProp}.rule.docTypeName"/>
	<html-el:hidden property="${ruleProp}.ruleTemplateName"/>
    <html-el:hidden property="${ruleProp}.rule.description"/>
    <html-el:hidden property="${ruleProp}.rule.activeInd"/>
    <html-el:hidden property="${ruleProp}.rule.delegateRule"/>
    <html-el:hidden property="${ruleProp}.warning"/>
    <html-el:hidden property="${ruleProp}.disabled"/>
  </c:forEach>

