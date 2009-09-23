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
              <td class="tabtable1-left"><img src="images/tab-topleft1.gif" alt="#" width="12" height="29" align="absmiddle">Workgroups</td>
              <td class="tabtable2-mid">

              <c:set var="showHide" value="${RemoveReplaceForm.showHide}" scope="request"/>
              <c:set var="showHideProperty" value="showHide" scope="request"/>
              <c:set var="ruleIndex" value="1" scope="request"/>
              <c:import url="../rules/ShowHideButton.jsp">
                <c:param name="idVal" value="2"/>
              </c:import>
              </td>
              <td class="tabtable2-right"><img src="images/tab-topright1.gif" alt="#" width="12" height="29" align="absmiddle"></td>
            </tr>
          </table>

          <!-- TAB -->
          <div class="tab-container" align="center" id="G2" <c:if test="${!showHide.children[ruleIndex].show}">style="display:none"</c:if>>
            <div class="h2-container"> <span class="subhead-left">
              <h2>Workgroups</h2>
              </span>
            </div>
            <br>
            <table border="0" align="center" cellpadding="0" cellspacing="0" style="width:auto; background-color:#e4e4e4" class="nobord">
              <tr>
                <td colspan="2" class="nobord"style="width:auto; background-color:#e4e4e4"><div align="center">
                    <html-el:image styleClass="nobord" styleId="imageField" src="images/tinybutton-choosewkgrps.gif" property="methodToCall.chooseWorkgroups"/>
                  </div></td>
              </tr>
            </table>
            <br>

  <c:set var="workgroupIndex" value="0"/>
  <display-el:table class="result-table" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.workgroups}" defaultsort="2" id="workgroup" requestURI="RemoveReplace.do"
       decorator="org.kuali.rice.kew.lookupable.LookupDecorator" >
       <c:set var="wgProp" value="workgroups[${workgroupIndex}]"/>
	   <display-el:column sortable="false" title="<div align=&quot;center&quot;><input type=&quot;checkbox&quot; id=&quot;masterWorkgroupCheckbox&quot; onclick=&quot;javascript:selectAllWorkgroupCheckboxes(${fn:length(RemoveReplaceForm.workgroups)})&quot;></div>" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <div align="center">
	        <c:if test="${workgroup.disabled}">
	          <img src="images/errormark.gif" alt="warning" width="10" height="10">
	        </c:if>
	        <c:if test="${!workgroup.disabled}">
	     	  <html-el:checkbox styleId="${wgProp}.selected" property="${wgProp}.selected"/>
	     	</c:if>
	     </div>
	   </display-el:column>
       <display-el:column sortable="true" title="Id"decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Workgroup.do?methodToCall=report&workgroupId=<c:out value="${workgroup.id}"/>"><c:out value="${workgroup.id}"/></a>
       </display-el:column>
       <display-el:column sortable="true" title="Name" property="name" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Type" property="type" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
	   <display-el:column sortable="true" title="Warnings" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <c:if test="${!empty workgroup.warning}"><img src="images/errormark.gif" alt="warning" width="10" height="10"> <c:out value="${workgroup.warning}"/></c:if>
	   </display-el:column>

       <c:set var="workgroupIndex" value="${workgroupIndex + 1}"/>
  </display-el:table>

  <c:forEach items="${RemoveReplaceForm.workgroups}" varStatus="status">
  	<c:set var="workgroupProp" value="workgroups[${status.index}]"/>
  	<html-el:hidden property="${workgroupProp}.id"/>
	<html-el:hidden property="${workgroupProp}.name"/>
	<html-el:hidden property="${workgroupProp}.type"/>
    <html-el:hidden property="${workgroupProp}.warning"/>
    <html-el:hidden property="${workgroupProp}.disabled"/>
  </c:forEach>

</div>
