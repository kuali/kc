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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sRevisionType;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

public class RRSF424_2_0V2_0GeneratorTest extends S2STestBase {

    @Override
    protected void prepareS2sData(ProposalDevelopmentDocument document) {
        super.prepareS2sData(document);

		document.getDevelopmentProposal().setSponsorProposalNumber("1234");

        S2sOpportunity s2sOpportunity = document.getDevelopmentProposal().getS2sOpportunity();
        S2sSubmissionType s2sSubmissionType = new S2sSubmissionType();
        s2sSubmissionType.setCode("1");
        s2sSubmissionType.setDescription("Preapplication");
        s2sOpportunity.setS2sSubmissionType(s2sSubmissionType);
        S2sRevisionType s2sRevisionType = new S2sRevisionType();
        s2sRevisionType.setCode("A");
        s2sOpportunity.setS2sRevisionType(s2sRevisionType);
        s2sOpportunity.setRevisionOtherDescription("revisionOtherDescription");
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
        person.setProposalPersonNumber(1);
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

        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
        final List<AnswerHeader> answerHeaders = KcServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(moduleQuestionnaireBean);

        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader != null) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    if (Integer.valueOf(128).equals(answers.getQuestion().getQuestionSeqId())) {
                        answers.setAnswer("N");
                        answers.getQuestionnaireQuestion().setRuleId("");
                    } else if (Integer.valueOf(129).equals(answers.getQuestion().getQuestionSeqId())) {
                        answers.setAnswer("Y");
                        answers.getQuestionnaireQuestion().setRuleId("");
                    } else if (Integer.valueOf(131).equals(answers.getQuestion().getQuestionSeqId())) {
                        answers.setAnswer("Program not covered by EO 12372");
                    }
                    else if (Integer.valueOf(130).equals(answers.getQuestion().getQuestionSeqId())) {
                        answers.setAnswer("03/03/2003");
                        answers.getQuestionnaireQuestion().setRuleId("");
                    }
                }
            }
        }
        KcServiceLocator.getService(BusinessObjectService.class).save(answerHeaders);
    }


    @Override
    protected String getFormGeneratorName() {
        return RRSF424_2_0V2_0Generator.class.getSimpleName();
    }
}
