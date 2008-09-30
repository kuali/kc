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
<%@ attribute name="boClassName" required="true" %>
<%@ attribute name="fieldConversions" required="false" %>
<%@ attribute name="lookupParameters" required="false" %>
<%@ attribute name="hideReturnLink" required="false" %>
<%@ attribute name="suppressActions" required="false" %>
<%@ attribute name="tabindexOverride" required="false" %>
<%@ attribute name="extraButtonSource" required="false" %>
<%@ attribute name="extraButtonParams" required="false" %>
<%@ attribute name="anchor" required="false" %>
<%@ attribute name="fieldLabel" required="false" %>
<%@ attribute name="readOnlyFields" required="false" %>
<%@ attribute name="referencesToRefresh" required="false" %>
<%@ attribute name="autoSearch" required="false" %>

<c:choose>
  <c:when test="${!empty tabindexOverride}">
    <c:set var="tabindex" value="${tabindexOverride}"/>
  </c:when>
  <c:otherwise>
    <c:set var="tabindex" value="${KualiForm.nextArbitrarilyHighIndex}"/>
  </c:otherwise>
</c:choose>

<input type="image" tabindex="${tabindex}" name="methodToCall.performLookup.(!!${boClassName}!!).(((${fieldConversions}))).((#${lookupParameters}#)).((<${hideReturnLink}>)).(([${extraButtonSource}])).((*${extraButtonParams}*)).((^${suppressActions}^)).((&${readOnlyFields}&)).((/${referencesToRefresh}/)).((~${autoSearch}~)).anchor${anchor}"
   src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search ${fieldLabel}" title="Search ${fieldLabel}" />