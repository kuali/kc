<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="tabDescription" required="false" %>
<%@ attribute name="tabErrorKey" required="false" %>
<%@ attribute name="auditCluster" required="false" %>
<%@ attribute name="tabAuditKey" required="false" %>
<%@ attribute name="tabItemCount" required="false" %>
<%@ attribute name="styleClass" required="false" %>

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

  <table style="width:100%;" cellpadding="0" cellspacing="0" class="${styleClass}">
    <tr>      
      <td><table style="width:100%;" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
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
      	
      	
      	<c:if test="${! (empty tabAuditKey)}">
        	<div class="tab-container-error"><div class="left-errmsg-tab">
				<c:forEach items="${fn:split(auditCluster,',')}" var="cluster">
        	   		<kul:auditErrors cluster="${cluster}" keyMatch="${tabAuditKey}" isLink="false" includesTitle="true"/>
				</c:forEach>
        	</div></div>
      	</c:if>
      
        <!-- Before the jsp:doBody of the kul:tab tag -->            
        <jsp:doBody/>            
        <!-- After the jsp:doBody of the kul:tab tag -->
 
      

        </div>
        <table style="width:100%;" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-b"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-bl1.gif" alt="asdf" width=12 height=24></td>
            <td class="annotate-b"><div align="right"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-br1.gif" alt="asdf" width=12 height=24></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
