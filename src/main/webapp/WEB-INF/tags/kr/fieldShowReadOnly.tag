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

<%@ attribute name="field" required="true" type="org.kuali.rice.kns.web.ui.Field" description="The field." %>
<%@ attribute name="addHighlighting" required="false"
              description="boolean indicating if this field should be highlighted (to indicate old/new change)" %>
<%@ attribute name="isLookup" required="false"
              description="boolean indicating if this is a Lookup Screen" %>

<%-- Put the .div span around the link instead of vice versa,
    so that if JavaScript changes the .div contents there is no misleading link. --%>
<span id="${field.propertyName}.div">
<c:if test="${not (empty field.inquiryURL.href || empty field.propertyValue)}">
	<a title="<c:out value="${field.inquiryURL.title}"/>" href="<c:out value="${field.inquiryURL.href}"/>" target="_blank">
</c:if>

<kul:readonlyfield addHighlighting="${addHighlighting}" field="${field}" isLookup="${isLookup}" />

<c:if test="${not (empty field.inquiryURL.href || empty field.propertyValue)}">
  </a>
</c:if>
&nbsp;
</span>
