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
<%@ attribute name="boClassName" required="true" %>
<%@ attribute name="inquiryParameters" required="false" %>
<%@ attribute name="anchor" required="false" %>
<%@ attribute name="tabindexOverride" required="false" %>

<c:choose>
  <c:when test="${!empty tabindexOverride}">
    <c:set var="tabindex" value="${tabindexOverride}"/>
  </c:when>
  <c:otherwise>
    <c:set var="tabindex" value="${KualiForm.nextArbitrarilyHighIndex}"/>
  </c:otherwise>
</c:choose>
<c:set var="epMethodToCallAttribute" value="methodToCall.performInquiry.(!!${boClassName}!!).((#${inquiryParameters}#)).anchor${anchor}"/>
${kfunc:registerEditableProperty(KualiForm, epMethodToCallAttribute)} 
<html:image tabindex="${tabindex}" property="${epMethodToCallAttribute}"
   onclick="javascript: inquiryPop('${boClassName}','${inquiryParameters}'); return false"
   src="${ConfigProperties.kr.externalizable.images.url}book_open.png" styleClass="tinybutton" title="Direct Inquiry" alt="Direct Inquiry"/>
