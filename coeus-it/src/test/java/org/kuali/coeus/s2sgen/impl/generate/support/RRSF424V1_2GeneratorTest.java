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
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms.S2sOppFormsId;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sRevisionType;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

public class RRSF424V1_2GeneratorTest extends
		S2STestBase {

    @Override
    protected void prepareS2sData(ProposalDevelopmentDocument document) {
        super.prepareS2sData(document);

        S2sOpportunity s2sOpportunity = document.getDevelopmentProposal().getS2sOpportunity();
        S2sSubmissionType s2sSubmissionType = new S2sSubmissionType();
        s2sSubmissionType.setCode("1");
        s2sSubmissionType.setDescription("Preapplication");
        s2sOpportunity.setS2sSubmissionType(s2sSubmissionType);
        S2sRevisionType s2sRevisionType = new S2sRevisionType();
        s2sRevisionType.setCode("A");
        s2sOpportunity.setS2sRevisionType(s2sRevisionType);
        s2sOpportunity.setRevisionOtherDescription("revisionOtherDescription");
        List<S2sOppForms> s2sOppForms = new ArrayList<>();
        S2sOppForms oppForms = new S2sOppForms();
        oppForms.setInclude(true);
        oppForms.setFormName("RR_SF424_1_2-V1.2");
        S2sOppFormsId oppFormsId = new S2sOppFormsId();
        oppFormsId.setOppNameSpace("http://apply.grants.gov/forms/RR_SF424_1_2-V1.2");
        oppFormsId.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        oppForms.setS2sOppFormsId(oppFormsId);
        s2sOppForms.add(oppForms);
        s2sOpportunity.setS2sOppForms(s2sOppForms);
        document.getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
    }

    @Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		BusinessObjectService businessObjectService = KcServiceLocator
				.getService(BusinessObjectService.class);

		prepareS2sData(document);
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();

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

		Questionnaire questionnaire;
		questionnaire = businessObjectService.findBySinglePrimaryKey(
				Questionnaire.class, 852);

		answerHeader.setQuestionnaireId(questionnaire.getId());
		answerHeader.setQuestionnaire(questionnaire);

		QuestionnaireQuestion questionnaireQuestion;
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
		
		Answer answer4 = new Answer();
		answer4.setQuestionId(766L);
		answer4.setAnswer("11-10-2014");
		answer4.setQuestionNumber(1);
		answer4.setAnswerNumber(1);
		answer4.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer4.setQuestionnaireQuestion(questionnaireQuestion);
		
		Answer answer5 = new Answer();
		answer5.setQuestionId(765L);
		answer5.setAnswer("N");
		answer5.setQuestionNumber(1);
		answer5.setAnswerNumber(1);
		answer5.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer5.setQuestionnaireQuestion(questionnaireQuestion);
		
		Answer answer6 = new Answer();
		answer6.setQuestionId(767L);
		answer6.setAnswer("Not Covered");
		answer6.setQuestionNumber(1);
		answer6.setAnswerNumber(1);
		answer6.setQuestionnaireQuestionsId(questionnaireQuestion.getId());
		answer6.setQuestionnaireQuestion(questionnaireQuestion);

		List<Answer> answers = new ArrayList<>();
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
		Organization organization;
		developmentProposal
				.setProgramAnnouncementTitle("programAnnouncementTitle");
		organization = businessObjectService.findBySinglePrimaryKey(
				Organization.class, "000001");
		if (organization != null) {
			List<ProposalSite> proposalSites;
			proposalSites = developmentProposal.getProposalSites();
			int siteNumber = 0;
			for (ProposalSite proposalSite : proposalSites) {
				if (proposalSite.getLocationTypeCode() == 1) {
					siteNumber = proposalSite.getSiteNumber();
				}
			}
			ProposalSite applicantOrganization = new ProposalSite();
			applicantOrganization.setOrganization(organization);
			CongressionalDistrict congressionalDistrict = new CongressionalDistrict();
			congressionalDistrict.setCongressionalDistrict("CONDI");
			congressionalDistrict.setProposalSite(applicantOrganization);
			List<CongressionalDistrict> congressionalDistricts = new ArrayList<>();
			congressionalDistricts.add(congressionalDistrict);
			applicantOrganization
					.setCongressionalDistricts(congressionalDistricts);
			applicantOrganization.setObjectId(organization.getOrganizationId());
			applicantOrganization.setLocationName(organization
					.getOrganizationName());
			applicantOrganization.setSiteNumber(siteNumber);
			developmentProposal.setApplicantOrganization(applicantOrganization);
		}

		ProposalPerson person = new ProposalPerson();
		person.setProposalPersonRoleId("PI");
        person.setProposalPersonNumber(1);
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
		return RRSF424V1_2Generator.class.getSimpleName();
	}
}
