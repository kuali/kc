<%--
Copyright 2005-2010 The Kuali Foundation

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
<%@ page language="java" %> 

<c:set var="readOnly" value="true" scope="request" />
<c:set var="hierarchySummaries" value="${KualiForm.hierarchyProposalSummaries}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentHierarchy"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="false"
	auditCount="0"
	headerDispatch="${KualiForm.headerDispatch}"
	headerTabActive="hierarchy">
							
<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Proposal Hierarchy" /></div>

<c:forEach var="summary" items="${hierarchySummaries}" varStatus="status">
	<c:set var="proposalNumber" value="${KualiForm.hierarchyProposalSummaries[status.index].proposalNumber}"/>
	<c:choose>
		<c:when test="${status.index eq 0}">
			<kul:tabTop tabTitle="Parent (Proposal # ${proposalNumber})" defaultOpen="false" >
				<kra-ph:proposalSummaryBody proposalNumber="${proposalNumber}" />
			</kul:tabTop>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${KualiForm.hierarchyProposalSummaries[status.index].synced}">
					<c:set var="syncLabel" value="Synced" />
				</c:when>
				<c:otherwise>
					<c:set var="syncLabel" value="Not synced" />
				</c:otherwise>
			</c:choose>	
			<kul:tab tabTitle="Child (Proposal # ${proposalNumber})" tabDescription="${syncLabel}" defaultOpen="false" >
				<kra-ph:proposalSummaryBody proposalNumber="${proposalNumber}" />
			</kul:tab>		
		</c:otherwise>
	</c:choose>
</c:forEach>

<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="scripts/proposalHierarchy.js"></script>

</kul:documentPage>
