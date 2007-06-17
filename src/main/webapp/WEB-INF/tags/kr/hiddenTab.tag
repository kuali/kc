<%--
 Copyright 2006 The Kuali Foundation.
 
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

<%@ attribute name="forceOpen" required="true" %>


<%-- maintain tabstate --%>
<%--   getTabStateJstl call is *required*, since it changes the currentTabIndex as a side-effect --%>
<%--   (which also means that I must retrieve currentTabIndex before retrieving tabStateJstl) --%>
<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/>
<c:set var="currentTab" value="${KualiForm.tabStateJstl}"/>

<html:hidden property="tabState[${currentTabIndex}].open" value="${forceOpen}" />

<%-- display tab contents --%>
<jsp:doBody/>            
