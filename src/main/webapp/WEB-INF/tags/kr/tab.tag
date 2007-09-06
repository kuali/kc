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
<%@ attribute name="defaultOpen" required="true" %>
<%@ attribute name="tabErrorKey" required="false" %>
<%@ attribute name="auditCluster" required="false" %>
<%@ attribute name="tabAuditKey" required="false" %>
<%@ attribute name="tabItemCount" required="false" %>
<%@ attribute name="leftSideHtmlProperty" required="false" %>
<%@ attribute name="leftSideHtmlAttribute" required="false" type="java.util.Map"%>
<%@ attribute name="leftSideHtmlDisabled" required="false" %>
<%@ attribute name="rightSideHtmlProperty" required="false" %>
<%@ attribute name="rightSideHtmlAttribute" required="false" type="java.util.Map"%>
<%@ attribute name="transparentBackground" required="false" %>
<%@ attribute name="highlightTab" required="false" %>
<%@ attribute name="extraButtonSource" required="false" %>


<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
<c:set var="topLevelTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>


<c:set var="tabKey" value="${kfunc:generateTabKey(tabTitle)}" scope="request"/>
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

<c:set var="leftTabImage" value="${ConfigProperties.kr.externalizable.images.url}tab-topleft1.gif" />
<c:set var="rightTabImage" value="${ConfigProperties.kr.externalizable.images.url}tab-topright1.gif" />
<c:set var="rightTabClass" value="tabtable2-right" />
<c:set var="midTabClass" value="tabtable2-mid" />
<c:if test="${transparentBackground}">
  <c:set var="leftTabImage" value="${ConfigProperties.kr.externalizable.images.url}tab-topleft.gif" />
  <c:set var="rightTabImage" value="${ConfigProperties.kr.externalizable.images.url}tab-topright.gif" />
  <c:set var="rightTabClass" value="tabtable1-right" />
  <c:set var="midTabClass" value="tabtable1-mid" />
</c:if>

        <table width="100%" class="tab" cellpadding="0" cellspacing="0" summary="" border="1">
          <tr>
            <td class="tabtable1-left">
              <img src="${leftTabImage}" alt="" width="12" height="29" align="absmiddle" />
              <c:if test="${not empty leftSideHtmlProperty and not empty leftSideHtmlAttribute}"><kul:htmlControlAttribute property="${leftSideHtmlProperty}" attributeEntry="${leftSideHtmlAttribute}" disabled="${leftSideHtmlDisabled}" /></c:if>
              <a name="${tabKey}" ></a> ${tabTitle}
              <c:if test="${highlightTab}">
                &nbsp;<img src="${ConfigProperties.kr.externalizable.images.url}asterisk_orange.png" alt="changed"/>
              </c:if>
            </td>
            <c:if test="${not empty tabDescription}">
              <td class="tabtable1-mid1"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" align="absmiddle" height="29" width="1" />${tabDescription}</td>
      		</c:if>
      		
            <c:if test="${not empty rightSideHtmlProperty and not empty rightSideHtmlAttribute}">
              <td class="tabtable1-mid1"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" align="absmiddle" height="29" width="1" /><kul:htmlControlAttribute property="${rightSideHtmlProperty}" attributeEntry="${rightSideHtmlAttribute}" /></td>
      		</c:if>
      		
      		<c:if test="${not empty extraButtonSource}">
      		  <td class="tabtable1-mid1">${extraButtonSource}</td>
      		</c:if>

            <td class="${midTabClass}">
               <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
                 <html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               </c:if>
               <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
                 <html:image  property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               </c:if>
            </td>
            <td class="${rightTabClass}"><img src="${rightTabImage}" alt="" width="12" height="29" align="middle" /></td>
          </tr>
        </table>



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
