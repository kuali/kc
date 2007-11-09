 <%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<div id="workarea">
<c:set var="personIndex" value="0" />
<c:forEach items="${KualiForm.document.proposalPersons}" var="person" varStatus="status">
    <bean:define id="keyPerson" name="KualiForm" property="document.proposalPerson[${status.index}]"/>
    <c:set var="proposalPerson" value="document.proposalPersons[${status.index}]" />
    <c:set var="transparent" value="false" />
    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if> 

    <kul:tab tabTitle="${fn:substring(keyPerson.fullName, 0, 22)}"
             tabDescription="${keyPerson.role.description}"
             leftSideHtmlProperty="${proposalPerson}.delete" 
            leftSideHtmlAttribute="${proposalPersonAttributes.delete}" 
         	 leftSideHtmlDisabled="false" 
                      defaultOpen="false" 
            transparentBackground="${transparent}" 
                      tabErrorKey="document.proposalPerson*">
        <kra-pd:person proposalPerson="${proposalPerson}" personIndex="${status.index}"/>
     </kul:tab>
</c:forEach>
<c:if test="${fn:length(KualiForm.document.investigators) > 0}">
    <kra-pd:creditSplit/>
</c:if>

<c:if test="${fn:length(KualiForm.document.proposalPersons) > 0}">
    <kul:panelFooter />
</c:if>
</div>