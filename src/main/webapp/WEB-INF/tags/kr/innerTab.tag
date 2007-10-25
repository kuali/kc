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
<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="parentTab" required="true" %>
<%@ attribute name="tabDescription" required="false" %>
<%@ attribute name="defaultOpen" required="true" %>


<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
<c:set var="topLevelTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>

<c:set var="tabKey" value="${kfunc:generateTabKey(parentTab)}:${kfunc:generateTabKey(tabTitle)}" scope="request"/>
<!--  hit form method to increment tab index -->
<c:set var="doINeedThis" value="${kfunc:incrementTabIndex(KualiForm, tabKey)}" />

<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>
<c:choose>
    <c:when test="${empty currentTab}">
        <c:set var="isOpen" value="${defaultOpen}" />
    </c:when>
    <c:when test="${!empty currentTab}" >
        <c:set var="isOpen" value="${currentTab == 'OPEN'}" />
    </c:when>
</c:choose>

<!-- if the section has errors, override and set isOpen to true -->
<c:if test="${!empty tabErrorKey or not empty tabAuditKey}">
  <kul:checkErrors keyMatch="${tabErrorKey}" auditMatch="${tabAuditKey}"/>
  <c:set var="isOpen" value="${hasErrors ? true : isOpen}"/>
</c:if>

<html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
<!-- TAB -->

<c:if test="${! empty tabItemCount}">
  <c:set var="tabTitle" value="${tabTitle} (${tabItemCount})" />
</c:if>
              <div class="innerTab-head">
               <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
                 <html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " align="absmiddle" />&nbsp;${tabTitle}
               </c:if>
               <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
                 <html:image  property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " align="absmiddle"/>&nbsp;${tabTitle}
               </c:if>
              </div>

<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
<div style="display: block;" id="tab-${tabKey}-div">
</c:if>
<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}" >
<div style="display: none;" id="tab-${tabKey}-div">
</c:if>
  
 
      
        <!-- display errors for this tab -->
        <c:if test="${! (empty tabErrorKey)}">
          <div class="tab-container-error"><div class="left-errmsg-tab"><kul:errors keyMatch="${tabErrorKey}"/></div></div>
        </c:if>
        
        <%-- comment for reference by KRA devs during KNS extraction
        <c:if test="${! (empty tabAuditKey)}">
        	<div class="tab-container-error"><div class="left-errmsg-tab"><kra:auditErrors cluster="${auditCluster}" keyMatch="${tabAuditKey}" isLink="false" includesTitle="true"/></div></div>
      	</c:if>
      	--%>
      
        <!-- Before the jsp:doBody of the kul:tab tag -->            
        <jsp:doBody/>            
        <!-- After the jsp:doBody of the kul:tab tag -->
 
      
  
</div>         
