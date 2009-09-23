<%--
 Copyright 2006-2008 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="defaultOpen" required="true" %>
<%@ attribute name="totalErrors" required="true" %>
<%@ attribute name="category" required="true" %>

<c:set var="tabKeyName" value="${tabTitle}${category}" />
<c:set var="tabKey" value="${kfunc:generateTabKey(tabKeyName)}"/>
<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/>
<c:set var="incrementerDummy" value="${kfunc:incrementTabIndex(KualiForm, currentTabIndex)}" />
<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, currentTabIndex)}"/>

<c:choose>
    <c:when test="${empty currentTab}">
        <c:set var="isOpen" value="${defaultOpen}" />
    </c:when>
    <c:when test="${!empty currentTab}" >
        <c:set var="isOpen" value="${(currentTab == 'OPEN')}" />
    </c:when>
</c:choose>

<html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />

<!-- ROW -->

<tbody>
    <tr>
	    <td class="tab-subhead">
	      	<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	            <html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="hide" alt="hide" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
	        </c:if>
	        <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	            <html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="show" alt="show" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
	        </c:if>
	    </td>
	    <td colspan="3" class="tab-subhead" width="99%"><b>${tabTitle} (${totalErrors})</b></td>
    </tr>
</tbody>

<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	<tbody style="display: ;" id="tab-${tabKey}-div">
</c:if>
<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	<tbody style="display: none;" id="tab-${tabKey}-div">
</c:if>

<!-- Before the jsp:doBody of the kul:tab tag -->
<jsp:doBody/>
<!-- After the jsp:doBody of the kul:tab tag -->

</tbody>
