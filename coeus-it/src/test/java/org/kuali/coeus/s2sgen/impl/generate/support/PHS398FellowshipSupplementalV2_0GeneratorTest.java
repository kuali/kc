/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.generate.support;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.framework.person.attr.CitizenshipType;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms.S2sOppFormsId;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class PHS398FellowshipSupplementalV2_0GeneratorTest extends
		S2SModularBudgetTestBase {

	@Override
	protected String getFormGeneratorName() {
		return PHS398FellowshipSupplementalV2_0Generator.class.getSimpleName();
	}

    @Override
    protected void prepareS2sData(ProposalDevelopmentDocument document) {
        super.prepareS2sData(document);

        S2sOpportunity s2sOpportunity = document.getDevelopmentProposal().getS2sOpportunity();
        s2sOpportunity.setOpportunityId("PA-C-R01");
        List<S2sOppForms> S2sOppFormsList = new ArrayList<S2sOppForms>();
		S2sOppForms s2sOppForms = new S2sOppForms();
		S2sOppFormsId s2sOppFormsId = new S2sOppFormsId();
		s2sOppFormsId.setOppNameSpace("http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_2_0-V2.0");
		s2sOppForms.setFormName("PHS_Fellowship_Supplemental_2_0");
		s2sOppForms.setS2sOppFormsId(s2sOppFormsId);		
		s2sOppForms.getS2sOppFormsId().setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
		s2sOppForms.setInclude(true);
		S2sOppFormsList.add(s2sOppForms);
		s2sOpportunity.setS2sOppForms(S2sOppFormsList);
		document.getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
    }

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		
		prepareS2sData(document);
		List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		ProposalPerson principalInvestigator = new ProposalPerson();
		principalInvestigator.setFirstName("ALAN");
		principalInvestigator.setLastName("MCAFEE");
		principalInvestigator.setProposalPersonRoleId("PI");
		principalInvestigator.setPersonId("0001");
        principalInvestigator.setProposalPersonNumber(1);
        principalInvestigator.setDevelopmentProposal(document.getDevelopmentProposal());
		CitizenshipType citizenshipType = new CitizenshipType();
		citizenshipType.setCode(1);
		principalInvestigator.setCitizenshipType(citizenshipType);
		proposalPersons.add(principalInvestigator);
		document.getDevelopmentProposal().setProposalPersons(proposalPersons);

		ProposalType proposalType = new ProposalType();
		proposalType.setCode("1");
		document.getDevelopmentProposal().setProposalType(proposalType);

		List<ProposalSpecialReview> proposalSpecialReviewList = new ArrayList<ProposalSpecialReview>();
		ProposalSpecialReview proposalSpecialReview = new ProposalSpecialReview();
		SpecialReviewType specialReviewType = new SpecialReviewType();
		specialReviewType.setSpecialReviewTypeCode("5");
        proposalSpecialReview.setSpecialReviewTypeCode("5");
        proposalSpecialReview.setSpecialReviewType(specialReviewType);
        proposalSpecialReview.setApprovalTypeCode("1");
        proposalSpecialReview.setSpecialReviewNumber(1);
        proposalSpecialReview.setDevelopmentProposal(document.getDevelopmentProposal());
        SpecialReviewApprovalType approvalType = new SpecialReviewApprovalType();
        approvalType.setApprovalTypeCode("1");
        proposalSpecialReview.setApprovalType(approvalType);

        proposalSpecialReviewList.add(proposalSpecialReview);
		document.getDevelopmentProposal().setPropSpecialReviews(
				proposalSpecialReviewList);

		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
				ClassLoaderUtils.getDefaultClassLoader());
		Resource resource = resourceLoader
				.getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
		InputStream inputStream = resource.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setData(narrativePdf);
		narrativeAttachment.setName("exercise1");
		Narrative narrative = new Narrative();
		List<Narrative> narrativeList = new ArrayList<Narrative>();
		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setCode("96");
		narrativeType.setAllowMultiple(true);
		narrativeType.setSystemGenerated(false);
		narrativeType.setDescription("Test for Attachment");
        narrative.setModuleTitle("Allows Multiples Description");
		getService(DataObjectService.class).save(narrativeType);
		narrative.setName("exercise1");
        narrative.setNarrativeType(narrativeType);
		narrative.setNarrativeTypeCode("96");
		narrative.setNarrativeAttachment(narrativeAttachment);
        narrative.setModuleNumber(0);
        narrative.setModuleSequenceNumber(0);
        narrative.setModuleStatusCode("C");
        narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		narrativeList.add(narrative);

		Narrative narrative1 = new Narrative();
		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType1 = new NarrativeType();
		narrativeType1.setCode("98");
		narrativeType1.setAllowMultiple(false);
		narrativeType1.setSystemGenerated(false);
		narrativeType1.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType1);
        narrative1.setName("exercise1");
        narrative1.setNarrativeType(narrativeType1);
		narrative1.setNarrativeTypeCode("98");
		narrative1.setNarrativeAttachment(narrativeAttachment);
        narrative1.setModuleNumber(1);
        narrative1.setModuleSequenceNumber(1);
        narrative1.setModuleStatusCode("C");
        narrative1.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative1);

		Narrative narrative2 = new Narrative();
		narrative2.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType2 = new NarrativeType();
		narrativeType2.setCode("127");
		narrativeType2.setAllowMultiple(false);
		narrativeType2.setSystemGenerated(false);
		narrativeType2.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType2);
        narrative2.setName("exercise1");
        narrative2.setNarrativeType(narrativeType2);
		narrative2.setNarrativeTypeCode("127");
		narrative2.setNarrativeAttachment(narrativeAttachment);
        narrative2.setModuleNumber(2);
        narrative2.setModuleSequenceNumber(2);
        narrative2.setModuleStatusCode("C");
        narrative2.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative2);

		Narrative narrative3 = new Narrative();
		narrative3.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType3 = new NarrativeType();
		narrativeType3.setCode("88");
		narrativeType3.setAllowMultiple(false);
		narrativeType3.setSystemGenerated(false);
		narrativeType3.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType3);
        narrative3.setName("exercise1");
        narrative3.setNarrativeType(narrativeType3);
		narrative3.setNarrativeTypeCode("88");
		narrative3.setNarrativeAttachment(narrativeAttachment);
        narrative3.setModuleNumber(3);
        narrative3.setModuleSequenceNumber(3);
        narrative3.setModuleStatusCode("C");
        narrative3.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative3);

		Narrative narrative4 = new Narrative();
		narrative4.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType4 = new NarrativeType();
		narrativeType4.setCode("89");
		narrativeType4.setAllowMultiple(false);
		narrativeType4.setSystemGenerated(false);
		narrativeType4.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType4);
        narrative4.setName("exercise1");
        narrative4.setNarrativeType(narrativeType4);
		narrative4.setNarrativeTypeCode("89");
		narrative4.setNarrativeAttachment(narrativeAttachment);
        narrative4.setModuleNumber(4);
        narrative4.setModuleSequenceNumber(4);
        narrative4.setModuleStatusCode("C");
        narrative4.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative4);

		Narrative narrative5 = new Narrative();
		narrative5.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType5 = new NarrativeType();
		narrativeType5.setCode("90");
		narrativeType5.setAllowMultiple(false);
		narrativeType5.setSystemGenerated(false);
		narrativeType5.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType5);
        narrative5.setName("exercise1");
        narrative5.setNarrativeType(narrativeType5);
		narrative5.setNarrativeTypeCode("90");
		narrative5.setNarrativeAttachment(narrativeAttachment);
        narrative5.setModuleNumber(5);
        narrative5.setModuleSequenceNumber(5);
        narrative5.setModuleStatusCode("C");
        narrative5.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative5);

		Narrative narrative6 = new Narrative();
		narrative6.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType6 = new NarrativeType();
		narrativeType6.setCode("104");
		narrativeType6.setAllowMultiple(false);
		narrativeType6.setSystemGenerated(false);
		narrativeType6.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType5);
        narrative6.setName("exercise1");
        narrative6.setNarrativeType(narrativeType6);
		narrative6.setNarrativeTypeCode("104");
		narrative6.setNarrativeAttachment(narrativeAttachment);
        narrative6.setModuleNumber(6);
        narrative6.setModuleSequenceNumber(6);
        narrative6.setModuleStatusCode("C");
        narrative6.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative6);

		Narrative narrative7 = new Narrative();
		narrative7.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType7 = new NarrativeType();
		narrativeType7.setCode("92");
		narrativeType7.setAllowMultiple(false);
		narrativeType7.setSystemGenerated(false);
		narrativeType7.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType7);
        narrative7.setName("exercise1");
        narrative7.setNarrativeType(narrativeType7);
		narrative7.setNarrativeTypeCode("92");
		narrative7.setNarrativeAttachment(narrativeAttachment);
        narrative7.setModuleNumber(7);
        narrative7.setModuleSequenceNumber(7);
        narrative7.setModuleStatusCode("C");
        narrative7.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative7);

		Narrative narrative8 = new Narrative();
		narrative8.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType8 = new NarrativeType();
		narrativeType8.setCode("94");
		narrativeType8.setAllowMultiple(false);
		narrativeType8.setSystemGenerated(false);
		narrativeType8.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType8);
        narrative8.setName("exercise1");
        narrative8.setNarrativeType(narrativeType8);
		narrative8.setNarrativeTypeCode("94");
		narrative8.setNarrativeAttachment(narrativeAttachment);
        narrative8.setModuleNumber(8);
        narrative8.setModuleSequenceNumber(8);
        narrative8.setModuleStatusCode("C");
        narrative8.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative8);

		Narrative narrative9 = new Narrative();
		narrative9.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType9 = new NarrativeType();
		narrativeType9.setCode("134");
		narrativeType9.setAllowMultiple(false);
		narrativeType9.setSystemGenerated(false);
		narrativeType9.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType9);
        narrative9.setName("exercise1");
        narrative9.setNarrativeType(narrativeType9);
		narrative9.setNarrativeTypeCode("134");
		narrative9.setNarrativeAttachment(narrativeAttachment);
        narrative9.setModuleNumber(9);
        narrative9.setModuleSequenceNumber(9);
        narrative9.setModuleStatusCode("C");
        narrative9.setDevelopmentProposal(document.getDevelopmentProposal());
        narrativeList.add(narrative9);

		document.getDevelopmentProposal().setNarratives(narrativeList);

		BusinessObjectService businessObjectService = getService(BusinessObjectService.class);
		ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(
				document.getDevelopmentProposal());

		AnswerHeader answerHeader = new AnswerHeader();
		answerHeader.setModuleItemCode(moduleQuestionnaireBean
				.getModuleItemCode());
		answerHeader.setModuleItemKey(moduleQuestionnaireBean
				.getModuleItemKey());
		answerHeader.setModuleSubItemCode("2");
		answerHeader.setModuleSubItemKey(moduleQuestionnaireBean
				.getModuleSubItemKey());

		Questionnaire questionnaire = null;
		questionnaire = businessObjectService.findBySinglePrimaryKey(
				Questionnaire.class, 5276);
		answerHeader.setQuestionnaireId(questionnaire.getId());
		answerHeader.setQuestionnaire(questionnaire);

		QuestionnaireQuestion questionnaireQuestion1 = null;
		questionnaireQuestion1 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 714);
		Answer answer1 = new Answer();
		answer1.setQuestionId(1L);
		answer1.setAnswer("N");
		answer1.setQuestionNumber(1);
		answer1.setAnswerNumber(1);
		answer1.setQuestionnaireQuestionsId(questionnaireQuestion1.getId());
		answer1.setQuestionnaireQuestion(questionnaireQuestion1);

		QuestionnaireQuestion questionnaireQuestion2 = null;
		questionnaireQuestion2 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 715);
		Question question2 = businessObjectService.findBySinglePrimaryKey(
				Question.class, 771);
		questionnaireQuestion2.setQuestion(question2);
		Answer answer2 = new Answer();
		answer2.setQuestionId(771L);
		answer2.setAnswer("N");
		answer2.setQuestionNumber(1);
		answer2.setAnswerNumber(1);
		answer2.setQuestionnaireQuestionsId(questionnaireQuestion2.getId());
		answer2.setQuestionnaireQuestion(questionnaireQuestion2);

		QuestionnaireQuestion questionnaireQuestion3 = null;
		questionnaireQuestion3 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 717);
		Question question3 = businessObjectService.findBySinglePrimaryKey(
				Question.class, 773);
		questionnaireQuestion3.setQuestion(question3);
		Answer answer3 = new Answer();
		answer3.setQuestionId(773L);
		answer3.setAnswer("N");
		answer3.setQuestionNumber(1);
		answer3.setAnswerNumber(1);
		answer3.setQuestionnaireQuestionsId(questionnaireQuestion3.getId());
		answer3.setQuestionnaireQuestion(questionnaireQuestion3);

		QuestionnaireQuestion questionnaireQuestion4 = null;
		questionnaireQuestion4 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 718);
		Question question4 = businessObjectService.findBySinglePrimaryKey(
				Question.class, 774);
		questionnaireQuestion4.setQuestion(question4);
		Answer answer4 = new Answer();
		answer4.setQuestionId(774L);
		answer4.setAnswer("N");
		answer4.setQuestionNumber(1);
		answer4.setAnswerNumber(1);
		answer4.setQuestionnaireQuestionsId(questionnaireQuestion4.getId());
		answer4.setQuestionnaireQuestion(questionnaireQuestion4);

		QuestionnaireQuestion questionnaireQuestion5 = null;
		questionnaireQuestion5 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 721);
		Question question5 = businessObjectService.findBySinglePrimaryKey(
				Question.class, 791);
		questionnaireQuestion5.setQuestion(question5);
		Answer answer5 = new Answer();
		answer5.setQuestionId(791L);
		answer5.setAnswer("1100 BIOCHEMISTRY");
		answer5.setQuestionNumber(1);
		answer5.setAnswerNumber(1);
		answer5.setQuestionnaireQuestionsId(questionnaireQuestion5.getId());
		answer5.setQuestionnaireQuestion(questionnaireQuestion5);

		QuestionnaireQuestion questionnaireQuestion6 = null;
		questionnaireQuestion6 = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 723);
		Question question6 = businessObjectService.findBySinglePrimaryKey(
				Question.class, 793);
		questionnaireQuestion6.setQuestion(question6);
		Answer answer6 = new Answer();
		answer6.setQuestionId(793L);
		answer6.setAnswer("N");
		answer6.setQuestionNumber(1);
		answer6.setAnswerNumber(1);
		answer6.setQuestionnaireQuestionsId(questionnaireQuestion6.getId());
		answer6.setQuestionnaireQuestion(questionnaireQuestion6);

		List<Answer> answers = new ArrayList<Answer>();
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);
		answers.add(answer4);
		answers.add(answer5);
		answers.add(answer6);
		answerHeader.setAnswers(answers);
		businessObjectService.save(answerHeader);
		for (Answer a : answers) {
			a.refreshReferenceObject("question");
		}
	}
}
