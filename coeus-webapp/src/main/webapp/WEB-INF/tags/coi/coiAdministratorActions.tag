<%--
 Copyright 2005-2014 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


<c:set var="disclosureReviewCompleted" value="${KualiForm.disclosureActionHelper.disclosureReviewComplete}" />
<kul:tab tabTitle="Administrator Actions" defaultOpen="false" tabErrorKey="coiAdminActionErrors">
		<c:if test="${not KualiForm.document.coiDisclosureList[0].currentDisclosure}">
            <kra-coi:addCoiReviewerAction />
            <kra:permission value="${KualiForm.disclosureHelper.canUpdateFEStatusAdmin}">
            	<kra-coi:coiProjectsFinancialEntity/>
            </kra:permission>
            <c:if test="${disclosureReviewCompleted}">
	            <kra-coi:disclosureReviewStatusAction/>
            </c:if>
            <c:if test="${!KualiForm.document.viewOnly}">
            <kra-coi:approveAction />
            </c:if>
        </c:if>    
</kul:tab>