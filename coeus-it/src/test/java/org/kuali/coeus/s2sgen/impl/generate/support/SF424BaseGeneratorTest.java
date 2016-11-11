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

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.List;

public abstract class SF424BaseGeneratorTest extends S2STestBase {

    @Override
    protected void prepareS2sData(ProposalDevelopmentDocument document) {
        super.prepareS2sData(document);

        document.getDevelopmentProposal().getS2sOpportunity().setOpportunityId("1234");

        S2sSubmissionType stype = new S2sSubmissionType();
        stype.setCode("2");

        document.getDevelopmentProposal().getS2sOpportunity().setS2sSubmissionType(stype);
        document.getDevelopmentProposal().getS2sOpportunity().setS2sSubmissionTypeCode("2");
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        ProposalType ptype = new ProposalType();
        ptype.setCode("1");

        document.getDevelopmentProposal().setProposalType(ptype);

        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setProposalPersonRoleId("PI");
        proposalPerson.setFirstName("Philip");
        proposalPerson.setLastName("Berg");
        proposalPerson.setDirectoryTitle("Title");
        proposalPerson.setAddressLine1("Sanfransisco");
        proposalPerson.setAddressLine2("Newyork");
        proposalPerson.setCity("Los Angels");
        proposalPerson.setCounty("County");
        proposalPerson.setState("AL");
        proposalPerson.setPostalCode("58623415");
        proposalPerson.setCountryCode("USA");
        proposalPerson.setOfficePhone("63254152");
        proposalPerson.setFaxNumber("52374512");
        proposalPerson.setDegree("MS");
        proposalPerson.setProjectRole("Manager");
        proposalPerson.setYearGraduated("2006");
        proposalPerson.setEmailAddress("philip@hotmail.com");
        proposalPerson.setOptInCertificationStatus(true);
        proposalPerson.setOptInUnitStatus(true);
        proposalPerson.setProposalPersonNumber(1001);
        proposalPerson.setRolodexId(1);
        proposalPerson.setDevelopmentProposal(document.getDevelopmentProposal());

        List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
        proposalPersonList.add(proposalPerson);

        document.getDevelopmentProposal().setProposalPersons(proposalPersonList);

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


        List<ProposalSite> proposalSites = new ArrayList<>();
        List<CongressionalDistrict> congressionalDistricts = new ArrayList<>();
        Rolodex rolodex = new Rolodex();
        rolodex.setRolodexId(1);
        rolodex.setAddressLine1("addressLine1");
        rolodex.setAddressLine2("addressLine2");
        rolodex.setAddressLine3("addressLine3");
        rolodex.setOwnedByUnit("000001");
        rolodex.setOrganization("University");
        rolodex.setSponsorAddressFlag(Boolean.FALSE);
        rolodex.setCreateUser("admin");
        rolodex.setCity("Camebridge");
        rolodex.setCounty(null);
        rolodex.setCountryCode("USA");
        rolodex.setPostalCode("02039-4307");
        rolodex.setState("MA");
        int siteNumber = 0;
        Organization organization = new Organization();
        organization.setOrganizationName("University");
        organization.setOrganizationId("000001");
        organization.setContactAddressId(1);
        organization.setRolodex(rolodex);

        ProposalSite performingOrganization = new ProposalSite();
        performingOrganization.setLocationTypeCode(2);
        performingOrganization.setOrganization(organization);
        performingOrganization.setSiteNumber(++siteNumber);
        performingOrganization.setLocationName(organization.getOrganizationName());
        performingOrganization.setDevelopmentProposal(document.getDevelopmentProposal());

        CongressionalDistrict congressionalDistrict = new CongressionalDistrict();
        congressionalDistrict.setId(1111111L);
        congressionalDistrict.setCongressionalDistrict("MA-008");
        congressionalDistrict.setProposalSite(performingOrganization);
        congressionalDistricts.add(congressionalDistrict);
        performingOrganization.setCongressionalDistricts(congressionalDistricts);

        ProposalSite applicantOrganization = new ProposalSite();
        applicantOrganization.setLocationTypeCode(1);
        applicantOrganization.setOrganization(organization);
        applicantOrganization.setSiteNumber(++siteNumber);
        applicantOrganization.setLocationName(organization.getOrganizationName());
        applicantOrganization.setDevelopmentProposal(document.getDevelopmentProposal());


        proposalSites.add(performingOrganization);
        proposalSites.add(applicantOrganization);
        document.getDevelopmentProposal().setProposalSites(proposalSites);
    }
}
