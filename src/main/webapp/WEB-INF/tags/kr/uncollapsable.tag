<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="tabDescription" required="false" %>
<%@ attribute name="tabErrorKey" required="false" %>
<%@ attribute name="auditCluster" required="false" %>
<%@ attribute name="tabAuditKey" required="false" %>
<%@ attribute name="tabItemCount" required="false" %>

<%-- Copied from tab.tag, these are currently not used 
<%@ attribute name="leftSideHtmlProperty" required="false" %>
<%@ attribute name="leftSideHtmlAttribute" required="false" type="java.util.Map"%>
<%@ attribute name="leftSideHtmlDisabled" required="false" %>
<%@ attribute name="rightSideHtmlProperty" required="false" %>
<%@ attribute name="rightSideHtmlAttribute" required="false" type="java.util.Map"%>
--%>

<%@ attribute name="transparentBackground" required="false" %>
<%@ attribute name="highlightTab" required="false" %>
<%@ attribute name="extraButtonSource" required="false" %>

<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
<c:set var="topLevelTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>


<c:set var="tabKey" value="${kfunc:generateTabKey(tabTitle)}"/>
<!--  hit form method to increment tab index -->
<c:set var="doINeedThis" value="${kfunc:incrementTabIndex(KualiForm, tabKey)}" />

<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>

<!-- if the section has errors, override and set isOpen to true -->
<c:if test="${!empty tabErrorKey or not empty tabAuditKey}">
  <kul:checkErrors keyMatch="${tabErrorKey}" auditMatch="${tabAuditKey}"/>
</c:if>

<html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
<!-- TAB -->

<c:if test="${! empty tabItemCount}">
  <c:set var="tabTitle" value="${tabTitle} (${tabItemCount})" />
</c:if>

<c:set var="leftTabImage" value="${ConfigProperties.kr.externalizable.images.url}annotate-tl1.gif" />
<c:set var="rightTabImage" value="${ConfigProperties.kr.externalizable.images.url}annotate-tr1.gif" />
<c:if test="${transparentBackground}">
  <c:set var="leftTabImage" value="${ConfigProperties.kr.externalizable.images.url}annotate-tl1.gif" />
  <c:set var="rightTabImage" value="${ConfigProperties.kr.externalizable.images.url}annotate-tr1.gif" />
</c:if>

  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td class="column-left"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20" height="20"></td>
      <td><table width="100%" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-t"><img src="${leftTabImage}" alt="asdf" width=12 height=24 align="absmiddle" class="annotate-t">${tabTitle}</td>
            <td class="annotate-t"><div align="right"><img src="${rightTabImage}" alt="asdf" width=12 height=24 align="absmiddle"></div></td>

          </tr>
        </table>
        <div class="annotate-container">

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
        <table width="100%" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-b"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-bl1.gif" alt="asdf" width=12 height=24></td>
            <td class="annotate-b"><div align="right"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-br1.gif" alt="asdf" width=12 height=24></div></td>
          </tr>
        </table>
        <div id="workarea">
        </div>
        </td>
      </tr>
    </table>
    