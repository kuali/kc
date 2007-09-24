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
<%@ attribute name="tabDescription" required="false" %>
<%@ attribute name="defaultOpen" required="true" %>
<%@ attribute name="tabErrorKey" required="false" %>
<%@ attribute name="leftSideHtmlProperty" required="false" %>
<%@ attribute name="leftSideHtmlAttribute" required="false" type="java.util.Map"%>
<%@ attribute name="leftSideHtmlDisabled" required="false" %>
<%@ attribute name="rightSideHtmlProperty" required="false" %>
<%@ attribute name="rightSideHtmlAttribute" required="false" type="java.util.Map"%>

<%-- c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/ --%>
<%-- c:set var="currentTab" value="${KualiForm.tabStateJstl}"/ --%>
<c:set var="tabKey" value="${kfunc:generateTabKey(tabTitle)}"/>
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
<c:if test="${!empty tabErrorKey}">
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
            <c:if test="${not empty leftSideHtmlProperty and not empty leftSideHtmlAttribute}"><kul:htmlControlAttribute property="${leftSideHtmlProperty}" attributeEntry="${leftSideHtmlAttribute}" disabled="${leftSideHtmlDisabled}" /></c:if>
            ${tabTitle}
		</td>

            <c:if test="${not empty tabDescription}">
              <td class="tabtable1-mid1"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" align="absmiddle" height="29" width="1" />${tabDescription}</td>
      		</c:if>

            <c:if test="${not empty rightSideHtmlProperty and not empty rightSideHtmlAttribute}">
              <td class="tabtable1-mid1"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" align="absmiddle" height="29" width="1" /><kul:htmlControlAttribute property="${rightSideHtmlProperty}" attributeEntry="${rightSideHtmlAttribute}" /></td>
      		</c:if>

		<td class="tabtable1-mid">
            <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
			    <html:image property="methodToCall.toggleTab.tab${tabKey}"	src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="hide" alt="hide" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
		    </c:if>
		    <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
			    <html:image property="methodToCall.toggleTab.tab${tabKey}"	src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open" alt="open" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
		    </c:if>
		</td>
		<td class="tabtable1-right">
		    <img src="${ConfigProperties.kr.externalizable.images.url}tab-topright.gif" alt="" width="12" height="29" align="middle">
		</td>
	</tr>
</table>



<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}"><div style="display: block;" id="tab-${tabKey}-div"></c:if>
<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}"><div style="display: none;" id="tab-${tabKey}-div"></c:if>
 
        <!-- display errors for this tab -->
        <div class="tab-container-error"><div class="left-errmsg-tab"><kul:errors keyMatch="${tabErrorKey}" errorTitle="Errors Found in Document:" /></div></div>
        <!-- Before the jsp:doBody of the kul:tab tag -->            
        <jsp:doBody/>            
        <!-- After the jsp:doBody of the kul:tab tag -->
      
</div>