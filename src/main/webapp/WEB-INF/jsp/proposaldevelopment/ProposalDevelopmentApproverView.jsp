
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}"
	scope="request" />
	<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
	<kra:section permission="submitToSponsor">
	</kra:section>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<kul:documentPage showDocumentInfo="false"
	htmlFormAction="proposalDevelopmentApproverView"
		documentTypeName="ProposalDevelopmentDocument"
			renderMultipart="false"
				showTabButtons="true"
					auditCount="0"
						headerDispatch="${KualiForm.headerDispatch}"
							headerTabActive="approverView">


	<div id="workarea">

		<c:if test="${KualiForm.proposalDevelopmentParameters['enableProposalSummaryPanel'].value == 'Y'}">
		<kra-summary:proposalDevelopmentProposalSummary />
		</c:if>
		<c:if test="${KualiForm.document.budgetDocumentVersions[0]!=null}">
		<c:if test="${KualiForm.proposalDevelopmentParameters['enableBudgetSummaryPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentBudgetSummary />
			</c:if>
		</c:if>

		<c:if test="${KualiForm.proposalDevelopmentParameters['enableKeyPersonnelPanel'].value == 'Y'}">
		<kra-summary:proposalDevelopmentKeyPersonnel />
		</c:if>

		<c:if
			test="${fn:length(KualiForm.document.developmentProposalList[0].propSpecialReviews) > 0}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableSpecialReviewPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentSpecialReview
				businessObjectClassName="org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview"
				attributes="${DataDictionary.ProposalSpecialReview.attributes}"
				exemptionAttributes="${DataDictionary.ProposalSpecialReviewExemption.attributes}"
				collectionReference="${KualiForm.document.developmentProposal.propSpecialReviews}"
				collectionProperty="document.developmentProposalList[0].propSpecialReviews"
				action="proposalDevelopmentSpecialReview" />
				</c:if>
		</c:if>


		<c:if test="${KualiForm.proposalDevelopmentParameters['enableCustomDataInfoPanel'].value == 'Y'}">
		<kra-summary:proposalDevelopmentCustomDataInformation />
		</c:if>

		<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryQuestionsPanel'].value == 'Y'}">
		<kra-summary:proposalSummaryQuestions
			bean="${KualiForm.questionnaireHelper}"
			property="questionnaireHelper" />
		<c:set var="forceTabNonTransparent" value="true" />
		<c:if
			test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) == 0}">
			<c:set var="forceTabNonTransparent" value="false" />
		</c:if>

		<kra-summary:proposalSummaryQuestions
			bean="${KualiForm.s2sQuestionnaireHelper}"
			property="s2sQuestionnaireHelper"
			forceNonTransparent="${forceTabNonTransparent}" />

		<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>

		<c:set var="topTabTransparent" value="true" />
		<c:if
			test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) gt 0 or fn:length(KualiForm.s2sQuestionnaireHelper.answerHeaders) gt 0}">
			<c:set var="topTabTransparent" value="false" />
		</c:if>
		</c:if>
		<c:set scope="page" var="proposalAttachementCount" value="0" />
		<c:forEach var="narrative"
			items="${KualiForm.document.developmentProposalList[0].narratives}"
			varStatus="status">
			<c:if
				test="${narrative.narrativeType.narrativeTypeGroup eq KualiForm.proposalDevelopmentParameters['proposalNarrativeTypeGroup'].value}">
				<c:set scope="page" var="proposalAttachementCount"
					value="${proposalAttachementCount + 1}" />
			</c:if>
		</c:forEach>
		<c:if
			test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios)>0 || proposalAttachementCount > 0 ||fn:length(KualiForm.document.developmentProposalList[0].instituteAttachments) >0}">
			<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryAttachmentsPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentSummaryAttachments />
			</c:if>
		</c:if>
		<c:if
			test="${fn:length(KualiForm.document.developmentProposalList[0].propScienceKeywords) > 0}">
				<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryKeywordsPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentSummaryKeywords />
			</c:if>
		</c:if>
		
		<c:if test="${KualiForm.proposalDevelopmentParameters['enableSummaryPrintPanel'].value == 'Y'}">
			<kra-summary:proposalDevelopmentSummaryPrintForms />
			</c:if>

		<kul:panelFooter />
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
	</div>

<script language="javascript" src="scripts/kuali_application.js"></script>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
</kul:documentPage>
