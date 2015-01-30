<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}"
	scope="request" />
	<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
	<kra:section permission="submitToSponsor">
	</kra:section>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link type="text/css" href="css/jquery/questionnaire.css" rel="stylesheet">
<kul:documentPage showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentApproverView"
		documentTypeName="ProposalDevelopmentDocument"
			renderMultipart="false"
				showTabButtons="true"
					auditCount="0"
						headerDispatch="${KualiForm.headerDispatch}"
							headerTabActive="approverView">


	<div id="workarea">
		<c:set var="isTopPanel" value="true"/>
		<c:if test="${KualiForm.proposalDevelopmentParameters['enableProposalSummaryPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentProposalSummary />
			<c:set var="isTopPanel" value="false"/>
		</c:if>
		
		<c:if test="${KualiForm.document.budgetDocumentVersions[0]!=null}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableBudgetSummaryPanel'].value == 'Y'}">
				<kra-summary:proposalDevelopmentBudgetSummary transparentBackground="${isTopPanel }" />
				<c:set var="isTopPanel" value="false"/>
			</c:if>
		</c:if>

		<c:if test="${KualiForm.proposalDevelopmentParameters['enableKeyPersonnelPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentKeyPersonnel transparentBackground="${isTopPanel }"/>
			<c:set var="isTopPanel" value="false"/>
		</c:if>

		<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propSpecialReviews) > 0}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableSpecialReviewPanel'].value == 'Y'}">
				<kra-summary:proposalDevelopmentSpecialReview
					businessObjectClassName="org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview"
					attributes="${DataDictionary.ProposalSpecialReview.attributes}"
					exemptionAttributes="${DataDictionary.ProposalSpecialReviewExemption.attributes}"
					collectionReference="${KualiForm.document.developmentProposal.propSpecialReviews}"
					collectionProperty="document.developmentProposalList[0].propSpecialReviews"
					action="proposalDevelopmentSpecialReview" transparentBackground="${isTopPanel }" />
				<c:set var="isTopPanel" value="false"/>
			</c:if>
		</c:if>


		<c:if test="${KualiForm.proposalDevelopmentParameters['enableCustomDataInfoPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentCustomDataInformation transparentBackground="${isTopPanel }"/>
			<c:set var="isTopPanel" value="false"/>
		</c:if>

		<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryQuestionsPanel'].value == 'Y'}">
			<kul:tab tabTitle="Questions" defaultOpen="false" transparentBackground="${transparentBackground }"><div class="tab-container" align="center">
			<kra-summary:proposalSummaryQuestions  transparentBackground="${isTopPanel }"
				bean="${KualiForm.questionnaireHelper}"
				property="questionnaireHelper" parentTab="Questions"/>
			
			<c:set var="isTopPanel" value="false"/>
			<c:set var="forceTabNonTransparent" value="true" />
			<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) == 0}">
				<c:set var="forceTabNonTransparent" value="false" />
			</c:if>
			
			<kra-summary:proposalSummaryQuestions  transparentBackground="${isTopPanel }"
				bean="${KualiForm.s2sQuestionnaireHelper}"
				property="s2sQuestionnaireHelper"
				forceNonTransparent="${forceTabNonTransparent}" parentTab="Questions"/>
	
			<script>var $j = jQuery.noConflict();</script>
			<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>
			<c:set var="topTabTransparent" value="true" />
			<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) gt 0 or fn:length(KualiForm.s2sQuestionnaireHelper.answerHeaders) gt 0}">
				<c:set var="topTabTransparent" value="false" />
			</c:if>
			</div></kul:tab>
		</c:if>
		
		<c:set scope="page" var="proposalAttachementCount" value="0" />
		<c:forEach var="narrative" items="${KualiForm.document.developmentProposalList[0].narratives}" varStatus="status">
			<c:if test="${narrative.narrativeType.narrativeTypeGroup eq KualiForm.proposalDevelopmentParameters['proposalNarrativeTypeGroup'].value}">
				<c:set scope="page" var="proposalAttachementCount"
					value="${proposalAttachementCount + 1}" />
			</c:if>
		</c:forEach>
		
		<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios)>0 || proposalAttachementCount > 0 ||fn:length(KualiForm.document.developmentProposalList[0].instituteAttachments) >0}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryAttachmentsPanel'].value == 'Y'}">
				<kra-summary:proposalDevelopmentSummaryAttachments transparentBackground="${isTopPanel }" />
				<c:set var="isTopPanel" value="false"/>
			</c:if>
		</c:if>
		
		<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propScienceKeywords) > 0}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryKeywordsPanel'].value == 'Y'}">
				<kra-summary:proposalDevelopmentSummaryKeywords transparentBackground="${isTopPanel }" />
				<c:set var="isTopPanel" value="false"/>
			</c:if>
		</c:if>
		
		<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryDataValidationPanel'].value == 'Y'}">
			<kra:dataValidation auditActivated="${KualiForm.auditActivated}"
				categories="Validation Errors,Warnings,Grants.Gov Errors" transparentBackground="${isTopPanel }"
				topTab="false"
				title="Validations"
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
			<c:set var="isTopPanel" value="false"/>
		</c:if>
		
		<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryPrintPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentSummaryPrintForms transparentBackground="${isTopPanel }" />
			<c:set var="isTopPanel" value="false"/>
		</c:if>

		<c:if test="${!isTopPanel}">
			<kul:panelFooter />
		</c:if>
	</div>
	<c:set var="tabindex" value="0" />


	<div id="globalbuttons" class="globalbuttons">
	<div class="globalbuttons">
	${KualiForm.proposalDevelopmentParameters['propSummaryDisclaimerText'].value }
	</div>
		<c:if
			test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_APPROVE] and not suppressRoutingControls}">
			<html:image
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif"
				styleClass="globalbuttons" property="methodToCall.approve"
				title="approve" alt="approve" onclick="resetScrollPosition();"
				tabindex="${tabindex}" />
		</c:if>
		<c:if
			test="${!empty KualiForm.documentActions[Constants.KUALI_ACTION_CAN_DISAPPROVE] and not suppressRoutingControls}">
			<html:image
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif"
				styleClass="globalbuttons" property="methodToCall.disapprove"
				title="disapprove" alt="disapprove" onclick="resetScrollPosition();"
				tabindex="${tabindex}" />
		</c:if>
		<c:if
			test="${!empty KualiForm.editingMode['rejectProposal'] and not suppressRoutingControls}">
			<html:image
				src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_reject.gif"
				styleClass="globalbuttons" property="methodToCall.reject"
				title="reject" alt="reject" onclick="resetScrollPosition();"
				tabindex="${tabindex}" />
		</c:if>
		
	</div>

<script language="javascript" src="scripts/kuali_application.js"></script>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
</kul:documentPage>
