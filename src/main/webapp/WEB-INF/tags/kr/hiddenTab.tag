<%--
 Copyright 2006-2007 The Kuali Foundation
 
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

<%@ attribute name="forceOpen" required="true" description="Whether the hidden tab should be considered open or not." %>


<%-- maintain tabstate --%>
<%--   getTabStateJstl call is *required*, since it changes the currentTabIndex as a side-effect --%>
<%--   (which also means that I must retrieve currentTabIndex before retrieving tabStateJstl) --%>
<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/>
<!--  Ideally the tabKey should be the tabTitle, but since this is a hidden tab, I don't know what its title should be -->
<c:set var="tabKey" value="hiddenTabTitle"/>
<!--  hit form method to increment tab index -->
<c:set var="dummyIncrementer" value="${kfunc:incrementTabIndex(KualiForm, tabKey)}" />

<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, tabKey)}"/>

<html:hidden property="tabStates(${tabKey}).open" value="${forceOpen}" />

<%-- display tab contents --%>
<jsp:doBody/>            
