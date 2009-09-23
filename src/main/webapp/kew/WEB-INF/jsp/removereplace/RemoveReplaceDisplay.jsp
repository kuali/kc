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

  <jsp:include page="../DocumentEntryHeader.jsp"/>

  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
      <td><br><jsp:include page="../WorkflowMessages.jsp"/><br></td>
      <td class="column-right"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    </tr>
  </table>

  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>

      <td><table width="100%" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-t"><img src="images/annotate-tl1.gif" alt="asdf" width=12 height=24 align="absmiddle" class="annotate-t">Action</td>
            <td class="annotate-t"><div align="right"><img src="images/annotate-tr1.gif" alt="asdf" width=12 height=24 align="absmiddle"></div></td>
          </tr>
        </table>

        <jsp:include page="RemoveReplaceOperationDisplay.jsp"/>

        <table width="100%" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-b"><img src="images/annotate-bl1.gif" alt="asdf" width=12 height=24></td>
            <td class="annotate-b"><div align="right"><img src="images/annotate-br1.gif" alt="asdf" width=12 height=24></div></td>
          </tr>
        </table>

		<html-el:form action="RemoveReplace">

        <div id="workarea"><br>
        <br>

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
  <display-el:table class="result-table" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.rules}" defaultsort="1" id="rule" requestURI="RemoveReplace.do"
       decorator="org.kuali.rice.kew.lookupable.LookupDecorator" >
       <display-el:column sortable="true" title="Id"decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Rule.do?methodToCall=report&currentRuleId=<c:out value="${rule.rule.ruleBaseValuesId}"/>"><c:out value="${rule.rule.ruleBaseValuesId}"/></a>
       </display-el:column>
       <display-el:column sortable="true" title="Document Type" property="rule.docTypeName" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Rule Template" property="ruleTemplateName" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Description" property="rule.description" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Active" property="rule.activeInd" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Delegate Rule" property="rule.delegateRule" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
	   <display-el:column sortable="true" title="Warnings" property="warning" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <c:if test="${!empty rule.warning}"><img src="images/errormark.gif" alt="warning" width="10" height="10"> <c:out value="${rule.warning}"/></c:if>
	   </display-el:column>
  </display-el:table>
  </div>

		<!-- Workgroups -->

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

  <c:set var="workgroupIndex" value="0"/>
  <display-el:table class="result-table" cellspacing="0" cellpadding="0" name="${RemoveReplaceForm.workgroups}" defaultsort="1" id="workgroup" requestURI="RemoveReplace.do"
       decorator="org.kuali.rice.kew.lookupable.LookupDecorator" >
       <display-el:column sortable="true" title="Id"decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
         <a target="_blank" href="Workgroup.do?methodToCall=report&workgroupId=<c:out value="${workgroup.id}"/>"><c:out value="${workgroup.id}"/></a>
       </display-el:column>
       <display-el:column sortable="true" title="Name" property="name" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
       <display-el:column sortable="true" title="Type" property="type" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator"/>
	   <display-el:column sortable="true" title="Warnings" decorator="org.kuali.rice.kew.lookupable.LookupColumnDecorator">
	     <c:if test="${!empty workgroup.warning}"><img src="images/errormark.gif" alt="warning" width="10" height="10"> <c:out value="${workgroup.warning}"/></c:if>
	   </display-el:column>
  </display-el:table>
	  </div>
		<!-- End workgroups -->


<table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
        <tr>
          <td align="left" class="footer"><img src="images/pixel_clear.gif" alt="#" width="12" height="14" class="bl3"></td>
          <td align="right" class="footer-right"><img src="images/pixel_clear.gif" alt="#" width="12" height="14" class="br3"></td>
        </tr>
      </table>
    </div>

    </html-el:form>

    <div><img src="images/pixel_clear.gif" alt="#" width="12" height="14"></div>

      <c:if test="${ActionForm.methodToCall == 'docHandler' && !ActionForm.superUserSearch}">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td></td>
            <td>
              <c:set var="inputLocation" value="WorkgroupDocHandler.jsp" scope="request"/>
              <jsp:include page="../DocHandlerButtons.jsp" flush="true" />
            </td>
            <td></td>
          </tr>
        </table>
      </c:if>

      </td>
      <td class="column-right"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    </tr>
  </table>
