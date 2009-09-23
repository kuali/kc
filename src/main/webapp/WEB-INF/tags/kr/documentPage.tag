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
<!-- Do not remove session check here. Since it used by other pages (not MD or TD) -->
<c:if test="${KualiForm.document.sessionDocument || sessionDocument}">
<% request.setAttribute("sessionDoc", Boolean.TRUE); %>
</c:if>

<c:if test="${not empty SESSION_TIMEOUT_WARNING_MILLISECONDS}">
	<script type="text/javascript">
	<!-- 
	setTimeout("alert('Your session will expire in ${SESSION_TIMEOUT_WARNING_MINUTES} minutes.')",'${SESSION_TIMEOUT_WARNING_MILLISECONDS}');
	// -->
	</script>
</c:if>
<c:set var="renderRequiredFieldsLabel" value="${(KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT]
||KualiForm.documentActions[Constants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS]) && (not KualiForm.suppressAllButtons)}" />
<kul:page docTitle="${documentEntry.label}" transactionalDocument="${documentEntry.transactionalDocument}"
  headerMenuBar="${headerMenuBar}" showDocumentInfo="${showDocumentInfo}" headerTitle="${headerTitle}" 
  htmlFormAction="${htmlFormAction}" renderMultipart="${renderMultipart}" showTabButtons="${showTabButtons}" 
  extraTopButtons="${extraTopButtons}" headerDispatch="${headerDispatch}" headerTabActive="${headerTabActive}" 
  feedbackKey="${feedbackKey}" auditCount="${auditCount}" additionalScriptFiles="${additionalScriptFiles}" renderRequiredFieldsLabel="${renderRequiredFieldsLabel}">
    <jsp:doBody/>
</kul:page>
