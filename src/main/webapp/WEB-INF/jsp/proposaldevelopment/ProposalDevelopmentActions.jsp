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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<c:set var="hierarchyStatus"
	value="${KualiForm.document.developmentProposalList[0].hierarchyStatus}" />
<c:set var="hierarchyChildStatus"
	value="${KualiForm.hierarchyChildStatus}" />
<c:set var="readOnly"
	value="${not KualiForm.editingMode['modifyProposal']}" scope="request" />
<%-- Proposal Actions Page - Submit To Grants.gov Button - Commented Temporarily--%>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}"
	scope="request" />
<c:set var="showRejectionConfirmation" value="${KualiForm.showRejectionConfirmation}"
	scope="request" />
<kra:section permission="submitToSponsor">

</kra:section>

<kul:documentPage showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentActions"
	documentTypeName="ProposalDevelopmentDocument" 
	renderMultipart="true"
	showTabButtons="true" auditCount="0"
	headerDispatch="${KualiForm.headerDispatch}" headerTabActive="actions">
	
	<c:choose>
		<c:when test="${showRejectionConfirmation}">
				<kra-pd:proposalDevelopmentRejection />
		</c:when>
		<c:otherwise>
			
			<div align="right"><kul:help
			documentTypeName="ProposalDevelopmentDocument"
			pageName="Proposal Actions" /></div>
		<c:if test="${hierarchyStatus != hierarchyChildStatus}">
			<kra:dataValidation auditActivated="${KualiForm.auditActivated}"
				categories="Validation Errors,Warnings,Grants.Gov Errors"
				topTab="true"
				helpParameterNamespace="KC-PD" 
				helpParameterDetailType="Document" 
				helpParameterName="proposalDevelopmentDataValidation1HelpUrl">
				<p>You can activate a Validation check to determine any errors or
				incomplete information. The following Validations types will be
				determined:</p>
				<ul>
					<li>errors that prevent submission into routing</li>
					<li>warnings that serve as alerts to possible data issues but
					will not prevent submission into routing</li>
					<li>errors that prevent submission to grants.gov</li>
				</ul>
	
			</kra:dataValidation>
		</c:if>
	
	
		<c:if test="${hierarchyStatus == hierarchyChildStatus}">
			<kul:tabTop tabTitle="Data Validation" defaultOpen="false">
				<div class="tab-container" align="center">
				<h3><span class="subhead-left">Data Validation</span></h3>
				<table cellpadding="0" cellspacing="0" summary="">
					<tr>
						<td>
						<div class="floaters">
						<p>Data Validation is not valid for a Child Proposal in a
						Proposal Hierarchy.</p>
						</div>
						</td>
					</tr>
				</table>
				</div>
			</kul:tabTop>
		</c:if>
		<kra-pd:proposalDevelopmentHierarchy />
		<kra:section permission="printProposal">
			<kra-pd:proposalDevelopmentPrintForms />
		</kra:section>
		<kra-pd:proposalDevelopmentCopy />
	
		<kra:section permission="showAlterProposalData">
			<kra-pd:proposalDataOverride />
		</kra:section>
	
		<c:if test="${hierarchyStatus != hierarchyChildStatus}">
			<kul:routeLog />
			<kul:adHocRecipients />
		</c:if>
	
		<kul:panelFooter />
		
		<%-- Added document authorizer for the reject functionality. --%>
		<%-- <c:if test="${not KualiForm.suppressAllButtons}">
	          <c:if test="${KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and KualiForm.reject}">
	              <c:set var="extraButtonSource" value="${ConfigProperties.externalizable.images.url}buttonsmall_reject.gif"/>
	              <c:set var="extraButtonProperty" value="methodToCall.reject"/>
	              <c:set var="extraButtonAlt" value="Reject the document"/>
	           </c:if> 
	
	</c:if> --%>
	
		<p><kul:documentControls transactionalDocument="true"
			extraButtonSource="${extraButtonSource}"
			extraButtonProperty="${extraButtonProperty}"
			extraButtonAlt="${extraButtonAlt}" extraButtons="${extraButtons}"
			suppressCancelButton="${hierarchyStatus == KualiForm.hierarchyParentStatus}" />
		</p>
	
		
		<script language="javascript" src="scripts/kuali_application.js"></script>
		<SCRIPT type="text/javascript">
			var kualiForm = document.forms['KualiForm'];
			var kualiElements = kualiForm.elements;
		</SCRIPT>
			
		</c:otherwise>
	</c:choose>

	

</kul:documentPage>
