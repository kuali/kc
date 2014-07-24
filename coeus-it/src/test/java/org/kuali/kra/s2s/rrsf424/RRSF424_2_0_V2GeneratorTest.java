package org.kuali.kra.s2s.rrsf424;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sRevisionType;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionType;
import org.kuali.coeus.s2sgen.impl.generate.support.RRSF424_2_0_V2Generator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.s2s.generator.S2SModularBudgetTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class RRSF424_2_0_V2GeneratorTest extends S2SModularBudgetTestBase<RRSF424_2_0_V2Generator>{

	private BusinessObjectService businessObjectService = null;
	
	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		businessObjectService = KcServiceLocator
				.getService(BusinessObjectService.class);

		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();

		S2sOpportunity s2sOpportunity = new S2sOpportunity();
		S2sSubmissionType s2sSubmissionType = new S2sSubmissionType();
		s2sSubmissionType.setCode("1");
		s2sSubmissionType.setDescription("Preapplication");
		s2sOpportunity.setS2sSubmissionType(s2sSubmissionType);
		S2sRevisionType s2sRevisionType = new S2sRevisionType();
		s2sRevisionType.setCode("A");
		s2sOpportunity.setS2sRevisionType(s2sRevisionType);
		s2sOpportunity.setRevisionOtherDescription("revisionOtherDescription");
		developmentProposal.setS2sOpportunity(s2sOpportunity);

		ProposalType proposalType = new ProposalType();
		proposalType.setDescription("New");
		proposalType.setCode("1");
		developmentProposal.setProposalType(proposalType);

		final ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(
				developmentProposal);

		AnswerHeader answerHeader = new AnswerHeader();
		answerHeader.setModuleItemCode(moduleQuestionnaireBean
				.getModuleItemCode());
		answerHeader.setModuleItemKey(moduleQuestionnaireBean
				.getModuleItemKey());
		answerHeader.setModuleSubItemCode(moduleQuestionnaireBean
				.getModuleSubItemCode());
		answerHeader.setModuleSubItemKey(moduleQuestionnaireBean
				.getModuleSubItemKey());

		Questionnaire questionnaire = null;
		questionnaire = businessObjectService.findBySinglePrimaryKey(
				Questionnaire.class, 852);

		answerHeader.setQuestionnaireId(questionnaire.getId());
		answerHeader.setQuestionnaire(questionnaire);

		QuestionnaireQuestion questionnaireQuestion = null;
		questionnaireQuestion = businessObjectService.findBySinglePrimaryKey(
				QuestionnaireQuestion.class, 905);

		Answer answer1 = new Answer();
		answer1.setQuestionId(764L);
		answer1.setAnswer("N");
		answer1.setQuestionNumber(1);
		answer1.setAnswerNumber(1);
		answer1.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer1.setQuestionnaireQuestion(questionnaireQuestion);

		Question question = new Question();
		question.setId(131L);
		question.setQuestionSeqId(131);
		question.setSequenceNumber(1);
		question.setSequenceStatus("C");
		question.setQuestion("If No: Is the program not selected for review or not covered by  E.O. 12372? Select a response.");
		question.setStatus("A");
		question.setCategoryTypeCode(2L);
		question.setQuestionTypeId(6L);
		question.setLookupClass("org.kuali.coeus.common.framework.custom.arg.ArgValueLookup");
		question.setLookupReturn("STATE_EO_12372");
		question.setMaxAnswers(1);
		question.setAnswerMaxLength(25);
		businessObjectService.save(question);

		Answer answer2 = new Answer();
		answer2.setQuestionId(131L);
		answer2.setAnswer("Not Selected");
		answer2.setQuestionNumber(1);
		answer2.setAnswerNumber(1);
		answer2.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer2.setQuestionnaireQuestion(questionnaireQuestion);

		Answer answer3 = new Answer();
		answer3.setQuestionId(129L);
		answer3.setAnswer("N");
		answer3.setQuestionNumber(1);
		answer3.setAnswerNumber(1);
		answer3.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer3.setQuestionnaireQuestion(questionnaireQuestion);

		List<Answer> answers = new ArrayList<Answer>();
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);

		answerHeader.setAnswers(answers);
		businessObjectService.save(answerHeader);

		Organization organization = null;
		developmentProposal
				.setProgramAnnouncementTitle("programAnnouncementTitle");
		organization = businessObjectService.findBySinglePrimaryKey(
				Organization.class, "000001");
		if (organization != null) {
			ProposalSite applicantOrganization = new ProposalSite();
			applicantOrganization.setOrganization(organization);
			CongressionalDistrict congressionalDistrict = new CongressionalDistrict();
			congressionalDistrict.setCongressionalDistrict("CONDI");
			List<CongressionalDistrict> congressionalDistricts = new ArrayList<CongressionalDistrict>();
			congressionalDistricts.add(congressionalDistrict);
			applicantOrganization
					.setCongressionalDistricts(congressionalDistricts);
			applicantOrganization.setObjectId(organization.getOrganizationId());
			applicantOrganization.setLocationName(organization
					.getOrganizationName());
			developmentProposal.setApplicantOrganization(applicantOrganization);
		}

		ProposalPerson person = new ProposalPerson();
		person.setProposalPersonRoleId("PI");
		person.setFirstName("firstname");
		person.setLastName("argLastName");
		person.setMiddleName("argMiddleName");
		person.setOfficePhone("321-321-1228");
		person.setEmailAddress("kcnotification@gmail.com");
		person.setFaxNumber("321-321-1289");
		person.setAddressLine1("argAddressLine1");
		person.setAddressLine2("argAddressLine2");
		person.setCity("Coeus");
		person.setPostalCode("53421");
		person.setCounty("UNITED STATES");
		person.setCountryCode("USA");
		person.setState("MA");
		person.setDirectoryTitle("argDirectoryTitle");
		person.setDivision("division");
		person.setDevelopmentProposal(developmentProposal);
		developmentProposal.getProposalPersons().add(person);
	}

	@Override
	protected String getFormGeneratorName() {		
		return RRSF424_2_0_V2Generator.class.getSimpleName();
	}

}
