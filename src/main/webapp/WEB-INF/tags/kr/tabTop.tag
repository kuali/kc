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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="defaultOpen" required="true" %>
<%@ attribute name="tabErrorKey" required="false" %>
<!-- KC MODIFICATION -->
<%@ attribute name="auditCluster" required="false" %>
<%@ attribute name="tabAuditKey" required="false" %>
<!-- END KC MODIFICATIN -->
<%@ attribute name="boClassName" required="false" %>
<%@ attribute name="keyValues" required="false" %>

<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/>
<c:set var="tabKey" value="${kfunc:generateTabKey(tabTitle)}"/>
<c:set var="doINeedThis" value="${kfunc:incrementTabIndex(KualiForm, tabKey)}" />
<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>
<c:choose>
    <c:when test="${empty currentTab}">
        <c:set var="isOpen" value="${defaultOpen}" />
    </c:when>
    <c:when test="${!empty currentTab}" >
        <c:set var="isOpen" value="${(currentTab == 'OPEN')}" />
    </c:when>
</c:choose>
<!-- if the section has errors, override and set isOpen to true -->
<!-- KC MODIFICATION -->
<c:if test="${!empty tabErrorKey or not empty tabAuditKey}">
<%-- ORIGINAL 
<c:if test="${!empty tabErrorKey}">
--%>
<!-- END KC MODIFICATIN -->
  <kul:checkErrors keyMatch="${tabErrorKey}" auditMatch="${tabAuditKey}"/>
  <c:set var="isOpen" value="${hasErrors ? true : isOpen}"/>
</c:if>
<html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
<!-- TAB -->

<div id="workarea">

<table width="100%" class="tab" cellpadding=0 cellspacing=0 summary="">
	<tr>
		<td class="tabtable1-left">
		    <img src="${ConfigProperties.kr.externalizable.images.url}tab-topleft.gif" alt=""	width=12 height=29 align=middle>
            <c:choose>
              <c:when test="${not empty boClassName && not empty keyValues}">
                <h2><kul:inquiry keyValues="${keyValues}" boClassName="${boClassName}" render="true"><c:out value="${tabTitle}" /></kul:inquiry></h2>
              </c:when>
              <c:otherwise>
                <h2><c:out value="${tabTitle}" /></h2>
              </c:otherwise>
            </c:choose>
		</td>
		<td class="tabtable1-mid">
            <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
			    <html:image property="methodToCall.toggleTab.tab${tabKey}"	src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
		    </c:if>
		    <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
			    <html:image property="methodToCall.toggleTab.tab${tabKey}"	src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
		    </c:if>
		</td>
		<td class="tabtable1-right">
		    <img src="${ConfigProperties.kr.externalizable.images.url}tab-topright.gif" alt="" width="12" height="29" align="middle">
		</td>
	</tr>
</table>



<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}"><div style="display: block;" id="tab-${tabKey}-div"></c:if>
<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}"><div style="display: none;" id="tab-${tabKey}-div"></c:if>
 
		<c:if test="${! (empty tabErrorKey)}">
        <!-- display errors for this tab -->
        <div class="tab-container-error"><div class="left-errmsg-tab"><kul:errors keyMatch="${tabErrorKey}" errorTitle="Errors Found in Document:" /></div></div>
		</c:if>        

        <!-- KC MODIFICATION -->
        <c:if test="${! (empty tabAuditKey)}">
        	<div class="tab-container-error"><div class="left-errmsg-tab">
				<c:forEach items="${fn:split(auditCluster,',')}" var="cluster">
        	   		<kul:auditErrors cluster="${cluster}" keyMatch="${tabAuditKey}" isLink="false" includesTitle="true"/>
				</c:forEach>
        	</div></div>
      	</c:if>
      	<!-- END KC MODIFICATION -->
      	
        <!-- Before the jsp:doBody of the kul:tab tag -->            
        <jsp:doBody/>            
        <!-- After the jsp:doBody of the kul:tab tag -->

</div>