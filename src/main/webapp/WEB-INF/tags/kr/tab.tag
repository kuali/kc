<%--
 Copyright 2005-2010 The Kuali Foundation

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

<%@ attribute name="tabTitle" required="false" description="The label to render for the tab." %>
<%@ attribute name="spanForLongTabTitle" required="false" description="If true, sets the CSS class for the title such that it will display over multiple columns" %>
<%@ attribute name="tabDescription" required="false" description="An explanatory description which will be rendered on the tab." %>
<%@ attribute name="defaultOpen" required="true" description="Whether the tab should default to rendering as open." %>
<%@ attribute name="tabErrorKey" required="false" description="The property key this tab should display errors associated with." %>
<%--KC MODIFICATION START--%>
<%@ attribute name="innerTabErrorKey" required="false" description="The error path for errors whose message should not be displayed in this tab.  Errors will cause the tab to be opened. Path can be wildcarded with and asterisk.  Multiple paths must be separated with a comma and no white spaces." %>
<%--KC MODIFICATION END--%>
<%@ attribute name="auditCluster" required="false" description="The error audit cluster associated with this page." %>
<%@ attribute name="tabAuditKey" required="false" description="The property key this tab should display audit errors associated with." %>
<%@ attribute name="tabItemCount" required="false" description="Expands the title to display this count alongside." %>
<%@ attribute name="leftSideHtmlProperty" required="false" description="The property name of an attribute to display at the left side of the tab. Used with leftSideHtmlAttribute." %>
<%@ attribute name="leftSideHtmlAttribute" required="false" type="java.util.Map" description="The data dictionary entry for an attribute to display at the left side of the tab.  Used with leftSideHtmlProperty." %>
<%@ attribute name="leftSideHtmlDisabled" required="false" description="If leftSideHtmlProperty and leftSideHtmlAttribute have been utilized, whether to display the left hand attribute as disabled." %>
<%@ attribute name="rightSideHtmlProperty" required="false" description="The property name of an attribute to display at the right side of the tab. Used with rightSideHtmlAttribute." %>
<%@ attribute name="rightSideHtmlAttribute" required="false" type="java.util.Map" description="The data dictionary entry for an attribute to display at the right side of the tab.  Used with rightSideHtmlProperty." %>
<%@ attribute name="transparentBackground" required="false" description="Whether the tab should render as having the background transparent around the corners of the tab." %>
<%@ attribute name="highlightTab" required="false" description="Whether the tab should be highlighted with the orange asterisk icon." %>
<%@ attribute name="extraButtonSource" required="false" description="The image source for an extra button to display on the tab." %>
<%@ attribute name="useCurrentTabIndexAsKey" required="false" description="Whether to use the current tab index as the current tab key, or (if this is false) generate a new one." %>
<%@ attribute name="hidden" required="false" description="Renders the tab as closed." %>
<%@ attribute name="useRiceAuditMode" required="false" description="If present and tabAuditKey is not present, renders all the audit errors in the audit cluster." %>
<%@ attribute name="midTabClassReplacement" required="false" description="Text to use as a replacement for the show/hide buttons rendering." %>
<%@ attribute name="boClassName" required="false" description="If present, makes the tab title an inquiry link using the business object class declared here.  Used with the keyValues attribute." %>
<%@ attribute name="keyValues" required="false" description="If present, makes the tab title an inquiry link using the primary key values declared here.  Used with the boClassName attribute." %>

<!-- KC MODIFICATION -->
<%@ attribute name="alwaysOpen" required="false" %>
<%@ variable name-given="tabKey" scope="NESTED" %>
<!-- END KC MODIFICATION -->

<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
<c:set var="topLevelTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>

<c:choose>
    <c:when test="${(useCurrentTabIndexAsKey)}">
        <c:set var="tabKey" value="${currentTabIndex}"/>
    </c:when>
    <c:otherwise>
        <c:set var="tabKey" value="${kfunc:generateTabKey(tabTitle)}"/>
    </c:otherwise>
</c:choose>

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

<%--KC MODIFICATION START--%>
		<c:if test="${isOpen != 'true' and !empty innerTabErrorKey}">
		  <kul:checkErrors keyMatch="${innerTabErrorKey}" />
		  <c:set var="isOpen" value="${hasErrors ? true : isOpen}" />
		</c:if>
<%--KC MODIFICATION END--%>

<c:if test="${hidden}">
	<c:set var="isOpen" value="false"/>
</c:if>

<html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
<!-- TAB -->

<c:if test="${! empty tabItemCount}">
  <c:set var="tabTitle" value="${tabTitle} (${tabItemCount})" />
</c:if>

<c:set var="tabTitleSpan" value="1" />
<c:if test="${! empty spanForLongTabTitle && spanForLongTabTitle eq true}">
	<c:set var="tabTitleSpan" value="${tabTitleSpan + 1}" />
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



        <table width="100%" class="tab" cellpadding="0" cellspacing="0" summary="" border="1" <c:if test="${hidden}">style="display:none;"</c:if>>
          <tr>
              <c:choose>
	          <c:when test="${tabTitleSpan > 1}">
				<td class="tabtable1-left-colspan" colspan="${tabTitleSpan}">
			  </c:when>
			  <c:otherwise>
			  	<td class="tabtable1-left">
			  </c:otherwise>
			  </c:choose>

              <img src="${leftTabImage}" alt="" width="12" height="29" align="absmiddle" />
              <c:if test="${not empty leftSideHtmlProperty and not empty leftSideHtmlAttribute}"><kul:htmlControlAttribute property="${leftSideHtmlProperty}" attributeEntry="${leftSideHtmlAttribute}" disabled="${leftSideHtmlDisabled}" /></c:if>
              <a name="${tabKey}" ></a>
              <c:choose>
                <c:when test="${not empty boClassName && not empty keyValues}">
                  <h2><kul:inquiry keyValues="${keyValues}" boClassName="${boClassName}" render="true"><c:out value="${tabTitle}" /></kul:inquiry></h2>
                </c:when>
                <c:otherwise>
                  <h2><c:out value="${tabTitle}" /></h2>
                </c:otherwise>
              </c:choose>
              <%--<h2><c:out value="${tabTitle}" /></h2>--%>
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

            <c:choose>
    		<c:when test="${empty midTabClassReplacement}">
<%-- KC MODIFICATION START --%>
               <c:if test="${isOpen == 'true' || isOpen == 'TRUE' || alwaysOpen == 'TRUE'}">
<%-- KC MODIFICATION END --%>
                 <html:image property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               </c:if>
<%-- KC MODIFICATION START --%>
               <c:if test="${isOpen != 'true' && isOpen != 'TRUE' && alwaysOpen != 'TRUE'}">
<%-- KC MODIFICATION END --%>
                 <html:image  property="methodToCall.toggleTab.tab${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${tabKey}-imageToggle" onclick="javascript: return toggleTab(document, '${tabKey}'); " />
               </c:if>
               </c:when>
                <c:otherwise>
                	${midTabClassReplacement}
                </c:otherwise>
                </c:choose>

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

        <!-- comment for reference by KRA devs during KNS extraction -->
        <c:if test="${! (empty tabAuditKey) && (useRiceAuditMode == 'true')}">
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
