<%--
 Copyright 2005-2008 The Kuali Foundation
 
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

<%@ attribute name="auditCount" required="false" description="The number of audit errors displayed on the page or section including this tag." %>

<c:if test="${empty auditCount}">
  <c:set var="auditCount" value="0" />
</c:if>
<c:set var="errorCount" value="${ErrorContainer.errorCount + auditCount}" />

<c:if test="${errorCount > 0}">
  <div class="error">
    ${errorCount} error(s) found on page.
  </div>
</c:if>
