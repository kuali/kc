<%--
 Copyright 2005-2009 The Kuali Foundation

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
<script language="javascript" src="scripts/my_common.js"></script>

<c:set var="inquiry" scope="request" value="${KualiForm.inquiry}" />
<c:set var="readOnly" scope="request" value="${!KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT] || inquiry}" />
<c:set var="readOnlyAssignees" scope="request" value="${!KualiForm.canAssignRole || readOnly}" />
<c:set var="canModifyAssignees" scope="request" value="${KualiForm.canModifyAssignees && !readOnlyAssignees}" />
<c:set var="editingDocument" scope="request" value="${KualiForm.document.editing}" />

<c:set var="formAction" value="identityManagementRoleDocument" />
<c:if test="${inquiry}">
	<c:set var="formAction" value="identityManagementRoleInquiry" />
</c:if>

<kul:documentPage
	showDocumentInfo="${!inquiry}"
	htmlFormAction="${formAction}"
	documentTypeName="IdentityManagementRoleDocument"
	renderMultipart="${!inquiry}"
	showTabButtons="true"
	auditCount="0">

    <c:if test="${!inquiry}">
	 	<kul:hiddenDocumentFields />
        <kul:documentOverview editingMode="${KualiForm.editingMode}" />
	</c:if>
    <c:if test="${inquiry}">
        <div id="workarea">
    </c:if>
    <kim:roleOverview />
	<kim:rolePermissions />
	<kim:roleResponsibilities />
	<kim:roleAssignees />
	<kim:roleDelegations />

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
            <input type="hidden" name="roleId" value="${KualiForm.document.roleId}" />
            <script type="text/javascript">
			function changeDelegationMemberTypeCode( frm ) {
                postMethodToCall( frm, "changeDelegationMemberTypeCode" );
            }
            function changeMemberTypeCode( frm ) {
                postMethodToCall( frm, "changeMemberTypeCode" );
            }
            function namespaceChanged( frm ) {
                postMethodToCall( frm, "changeNamespace" );
            }
            function postMethodToCall( frm, methodToCall ) {
                var methodToCallElement=document.createElement("input");
                methodToCallElement.setAttribute("type","hidden");
                methodToCallElement.setAttribute("name","methodToCall");
                methodToCallElement.setAttribute("value", methodToCall );
                frm.appendChild(methodToCallElement);
                frm.submit();
            } 
            </script>
	    </c:when>
	    <c:otherwise>
	        <kul:inquiryControls />
            <input type="hidden" name="roleId" value="${KualiForm.document.roleId}" />
	    </c:otherwise>
    </c:choose>
</kul:documentPage>
