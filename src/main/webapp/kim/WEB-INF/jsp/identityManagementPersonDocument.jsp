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

<c:set var="inquiry" scope="request" value="${KualiForm.inquiry}" />
<c:set var="readOnly" scope="request" value="${!KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT] || inquiry}" />
<c:set var="readOnlyEntity" scope="request" value="${!KualiForm.canModifyEntity || readOnly}" />

<c:set var="formAction" value="identityManagementPersonDocument" />
<c:if test="${inquiry}">
    <c:set var="formAction" value="identityManagementPersonInquiry" />
</c:if>

<kul:documentPage
    showDocumentInfo="${!inquiry}"
    htmlFormAction="${formAction}"
	documentTypeName="IdentityManagementPersonDocument"
	renderMultipart="${inquiry}"
	showTabButtons="true"
	auditCount="0">

    <c:if test="${!inquiry}">
 	    <kul:hiddenDocumentFields />
	    <kul:documentOverview editingMode="${KualiForm.editingMode}" />
	</c:if>
    <c:if test="${inquiry}">
        <div id="workarea">
    </c:if>
	<kim:personOverview />
	<kim:personContact />
	<kim:personPrivacy />
	<kim:personMembership />

    <c:if test="${!inquiry}">    		
		<kul:adHocRecipients />
		<kul:routeLog />
	</c:if>
	<kul:panelFooter />
    <c:if test="${inquiry}">
        </div>
    </c:if>
    <c:choose>
        <c:when test="${!inquiry}">
            <kul:documentControls transactionalDocument="false" />
        </c:when>
        <c:otherwise>
            <kul:inquiryControls />
            <input type="hidden" name="principalId" value="${KualiForm.principalId}" />
        </c:otherwise>
    </c:choose>

</kul:documentPage>
