<%--
 Copyright 2006-2009 The Kuali Foundation.
 
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

<%@ attribute name="documentTypeName" required="true" %>

<%@ attribute name="showDocumentInfo" required="false" %>
<%@ attribute name="headerMenuBar" required="false" %>
<%@ attribute name="headerTitle" required="false" %>
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<%@ attribute name="showTabButtons" required="false" %>
<%@ attribute name="extraTopButtons" required="false" type="java.util.List" %>
<%@ attribute name="headerDispatch" required="false" %>
<%@ attribute name="headerTabActive" required="false" %>
<%@ attribute name="feedbackKey" required="false" %>
<%@ attribute name="auditCount" required="false" %>

<%@ variable name-given="documentEntry" scope="NESTED" %>
<c:set var="documentEntry" value="${DataDictionary[documentTypeName]}" />
<c:set var="sessionDocument" value="${documentEntry.sessionDocument}" />
<c:set var="additionalScriptFiles" value="${KualiForm.additionalScriptFiles}" />

<!--  pass documentTypeName into htmlControlAttribute -->
<c:if test="${KualiForm.document.sessionDocument || sessionDocument}">
<% request.setAttribute("sessionDoc", true); %>
</c:if>

<c:if test="${not empty SESSION_TIMEOUT_WARNING_MILLISECONDS}">
	<script type="text/javascript">
	<!-- 
	setTimeout("alert('Your session will expire in ${SESSION_TIMEOUT_WARNING_MINUTES} minutes.')",'${SESSION_TIMEOUT_WARNING_MILLISECONDS}');
	// -->
	</script>
</c:if>

<kul:page docTitle="${documentEntry.label}" transactionalDocument="${documentEntry.transactionalDocument}" sessionDocument="${documentEntry.sessionDocument}"
  headerMenuBar="${headerMenuBar}" showDocumentInfo="${showDocumentInfo}" headerTitle="${headerTitle}" 
  htmlFormAction="${htmlFormAction}" renderMultipart="${renderMultipart}" showTabButtons="${showTabButtons}" 
  extraTopButtons="${extraTopButtons}" headerDispatch="${headerDispatch}" headerTabActive="${headerTabActive}" 
  feedbackKey="${feedbackKey}" auditCount="${auditCount}" additionalScriptFiles="${additionalScriptFiles}">
    <jsp:doBody/>
</kul:page>
