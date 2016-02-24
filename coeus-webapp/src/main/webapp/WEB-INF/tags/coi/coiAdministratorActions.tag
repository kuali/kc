<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
