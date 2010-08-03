<%--
 Copyright 2005-2007 The Kuali Foundation

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
<%@ attribute name="tabTitle" required="true" description="The label to render for the tab." %>
<%@ attribute name="defaultOpen" required="true" description="Whether the tab should default to rendering as open." %>
<%@ attribute name="tabErrorKey" required="false" description="The property key this tab should display errors associated with." %>
<%@ attribute name="boClassName" required="false" description="If present, makes the tab title an inquiry link using the business object class declared here.  Used with the keyValues attribute." %>
<%@ attribute name="keyValues" required="false" description="If present, makes the tab title an inquiry link using the primary key values declared here.  Used with the boClassName attribute." %>
<%-- KC MODIFICATION --%>
<%@ attribute name="auditCluster" required="false" %>
<%@ attribute name="tabAuditKey" required="false" %>
<%-- END KC MODIFICATION --%>

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
<%-- KC MODIFICATION --%>
<%-- if the section has errors, override and set isOpen to true --%>
<c:if test="${!empty tabErrorKey or !empty tabAuditKey}">
<%-- END KC MODIFICATIN --%>
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

<%-- KC MODIFICATION --%>
        <c:if test="${! (empty tabAuditKey) or ! (empty tabErrorKey)}">
        	<%-- display errors for this tab --%>
        	<div class="tab-container-error"><div class="left-errmsg-tab">
				<c:if test="${! (empty tabErrorKey)}">
					<kul:errors keyMatch="${tabErrorKey}" errorTitle="Errors found in this Section:" />
				</c:if>
				<c:if test="${! (empty tabAuditKey)}">
					<c:forEach items="${fn:split(auditCluster,',')}" var="cluster">
	        	   		<kul:auditErrors cluster="${cluster}" keyMatch="${tabAuditKey}" isLink="false" includesTitle="true"/>
					</c:forEach>
				</c:if>
        	</div></div>
      	</c:if>
<%-- END KC MODIFICATION --%>
        <%-- Before the jsp:doBody of the kul:tab tag --%>
        <jsp:doBody/>
        <%-- After the jsp:doBody of the kul:tab tag --%>

</div>
