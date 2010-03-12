<%--
 Copyright 2007 The Kuali Foundation
 
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

<%@ attribute name="width" required="true"
              description="The width of the table containing the show/hide button, e.g., '80%'." %>
<%@ attribute name="subTabTitle" required="false"
              description="The title to display next to the show/hide button." %>
<%@ attribute name="buttonAlt" required="false"
              description="The show/hide button alt text and title (must not contain HTML tags or quotes)." %>
<%@ attribute name="noShowHideButton" required="false"
              description="Boolean to hide the show/hide button (but the row is displayed anyway)." %>
<%@ attribute name="highlightTab" required="false" description="Whether the tab should be highlighted with the orange asterisk icon." %>
<%@ attribute name="boClassName" required="false" description="If present, renders a multiple value lookup icon at the top of the sub-tab collection." %>
<%@ attribute name="lookedUpBODisplayName" required="false" description="this value is the human readable name of the BO being looked up" %>
<%@ attribute name="lookedUpCollectionName" required="false" description="the name of the collection being looked up (perhaps on a document collection), this value will be returned to the calling document" %>
<%@ attribute name="useCurrentTabIndexAsKey" required="false" description="Whether to use the current tab index as the current tab key, or (if this is false) generate a new one." %>

<table class="datatable" cellpadding="0" cellspacing="0" align="center"
       style="width: ${width}; text-align: left; margin-left: auto; margin-right: auto;">
    <tbody>
        <tr>
            <td class="tab-subhead">
            	<c:if test="${!noShowHideButton}">
                	<a name="${KualiForm.currentTabIndex}"></a>
                </c:if>
                <span class="left">
<c:if test="${!noShowHideButton}">
    <c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}" scope="request"/>
    
    <c:choose>
        <c:when test="${(useCurrentTabIndexAsKey)}">
            <c:set var="tabKey" value="${currentTabIndex}"/>
        </c:when>
        <c:otherwise>
            <c:set var="tabKey" value="${kfunc:generateTabKey(subTabTitle)}"/>
        </c:otherwise>
    </c:choose>

    <!--  hit form method to increment tab index -->
    <c:set var="doINeedThis" value="${kfunc:incrementTabIndex(KualiForm, tabKey)}" />
    <c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>

    <%-- c:set var="currentTab" value="${KualiForm.tabStateJstl}"/ --%>
    <%-- getting tabStateJstl increments KualiForm.currentTabIndex as a side-effect --%>
    <c:set var="isOpen" value="${empty currentTab ? true : (currentTab == 'OPEN')}"/>
    <html:hidden property="tabStates(${tabKey})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />
                        <html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-${isOpen ? 'hide' : 'show'}.gif"
                                    property="methodToCall.toggleTab.tab${tabKey}"
                                    title="${isOpen ? 'close' : 'open'} ${buttonAlt}"
                                    alt="${isOpen ? 'close' : 'open'} ${buttonAlt}"
                                    styleClass="tinybutton"
                                    styleId="tab-${tabKey}-imageToggle"
                                    onclick="javascript: return toggleTab(document, '${tabKey}'); "/>
</c:if>
                    <%-- display the title anyway --%>
                    ${subTabTitle}
                    <c:if test="${highlightTab}">
                      &nbsp;<img src="${ConfigProperties.kr.externalizable.images.url}asterisk_orange.png" alt="changed"/>
                    </c:if>
                </span>
                <c:if test="${!empty boClassName}">
	                <span class="right">
    	            	<kul:multipleValueLookup boClassName="${boClassName}" lookedUpBODisplayName="${lookedUpBODisplayName}"
        	        			lookedUpCollectionName="${lookedUpCollectionName}" anchor="${tabKey}" />
            	    </span>
            	</c:if>
            </td>
        </tr>
    </tbody>
</table>

<c:if test="${!noShowHideButton}">
    <%-- these divs are taken from tab.tag --%>
    <div style="display: ${isOpen ? 'block' : 'none'};" id="tab-${tabKey}-div">
</c:if>

<jsp:doBody/>

<c:if test="${!noShowHideButton}">
    </div>
</c:if>
