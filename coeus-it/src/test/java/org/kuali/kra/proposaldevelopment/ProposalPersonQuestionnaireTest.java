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
package org.kuali.kra.proposaldevelopment;


import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
public class ProposalPersonQuestionnaireTest extends KcIntegrationTestBase {

    private BusinessObjectService businessObjectService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private DocumentService documentService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private DevelopmentProposal proposal;
    private ProposalPersonQuestionnaireHelper questionnaireHelper;
    
    private static final String q1 = "Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.";
    private static final String q2 = "Is there any potential for a perceived or real conflict of interest as defined in MIT's Policies and Procedures with regard to this proposal?";
    private static final String q3 = "If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?";
    private static final String q4 = "Have lobbying activities been conducted on behalf of this proposal?";
    private static final String q5 = "Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?";
    private static final String q6 = "Are you familiar with the requirements of the Procurement Liabilities Act?";

    @Before
    public void setUp() throws Exception {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        documentService = KcServiceLocator.getService(DocumentService.class);
        proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        proposal = getDocument().getDevelopmentProposal();
        questionnaireHelper = new ProposalPersonQuestionnaireHelper(getPerson());
    }

    @After
    public void tearDown() throws Exception {
        questionnaireAnswerService  = null;

        documentService = null;
        proposalDevelopmentService = null;
        proposal = null;
        questionnaireHelper = null;
    }
    
    private ProposalDevelopmentDocument getDocument() throws WorkflowException {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.initialize();

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001", "000120");

        document = (ProposalDevelopmentDocument) documentService.saveDocument(document);
        
        if (document.getDevelopmentProposal().getProposalPersons().isEmpty()) {
            KcPerson person = KcServiceLocator.getService(KcPersonService.class).getKcPersonByUserName("quickstart");
            ProposalPerson pp = new ProposalPerson();
            pp.setPersonId(person.getPersonId());
            pp.setDevelopmentProposal(document.getDevelopmentProposal());
            pp.setProposalPersonNumber(0);
            pp.setDevelopmentProposal(document.getDevelopmentProposal());

            pp.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
            pp.setOptInUnitStatus(true);
            pp.setOptInCertificationStatus(true);
            pp.setUserName(person.getUserName());
            pp.setLastName(person.getLastName());
            pp.setFullName(person.getFullName());

            document.getDevelopmentProposal().getProposalPersons().add(pp);
        }

        documentService.saveDocument(document);

        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());

        return savedDocument;
    }
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit, String primeSponsorCode) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
        document.getDevelopmentProposal().setPrimeSponsorCode(primeSponsorCode);
        proposalDevelopmentService.initializeUnitOrganizationLocation(document);
        proposalDevelopmentService.initializeProposalSiteNumbers(document);

    }
    
    private ProposalPerson getPerson() {
        return proposal.getProposalPersons().get(0);
    }
    
    @Test
    public void testProposalReady() throws WorkflowException {
        assertEquals(1, proposal.getProposalPersons().size());
        assertEquals("project title", proposal.getTitle());
        assertEquals("McGregor", getPerson().getLastName());
        assertTrue(proposal.getProposalDocument().getDocumentHeader().hasWorkflowDocument());
        assertEquals(proposal.getProposalNumber(), getPerson().getDevelopmentProposal().getProposalNumber());
        
        assertNotSame(getPerson().getProposalPersonNumber().toString(), getDocument().getDevelopmentProposal().getProposalPerson(0).getDevelopmentProposal().getProposalNumber().toString());
        
    }
    
    @Test
    public void testProposalPersonModuleQuestionnaireBean() {
        ProposalPersonModuleQuestionnaireBean bean = new ProposalPersonModuleQuestionnaireBean(proposal, getPerson());
        assertEquals(CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION, bean.getModuleItemCode());
    }
    
    @Test
    public void testQuestionnaire() {
        AnswerHeader header = questionnaireAnswerService.getQuestionnaireAnswer(questionnaireHelper.getModuleQnBean()).get(0);
        Questionnaire questionnaire = header.getQuestionnaire();
        assertEquals(6, questionnaire.getQuestionnaireQuestions().size());
        
        boolean q1Found = false;
        boolean q2Found = false;
        boolean q3Found = false;
        boolean q4Found = false;
        boolean q5Found = false;
        boolean q6Found = false;
        
        for (QuestionnaireQuestion q : questionnaire.getQuestionnaireQuestions()) {
            assertFalse(StringUtils.isEmpty(q.getQuestion().getAffirmativeStatementConversion()));
            assertFalse(StringUtils.isEmpty(q.getQuestion().getNegativeStatementConversion()));
            if (StringUtils.equals(q1, q.getQuestion().getQuestion())) {
                assertEquals(Integer.valueOf(1), q.getQuestion().getAnswerMaxLength());
                q1Found = true;
            } else if (StringUtils.equals(q2, q.getQuestion().getQuestion())) {
                assertEquals("Yes/No", q.getQuestion().getQuestionType().getName());
                q2Found = true;
            } else if (StringUtils.equals(q3, q.getQuestion().getQuestion())) {
                assertEquals("Yes/No", q.getQuestion().getQuestionType().getName());
                q3Found = true;
            } else if (StringUtils.equals(q4, q.getQuestion().getQuestion())) {
                assertEquals(Integer.valueOf(1), q.getQuestion().getAnswerMaxLength());
                q4Found = true;
            } else if (StringUtils.equals(q5, q.getQuestion().getQuestion())) {
                assertEquals(Integer.valueOf(1), q.getQuestion().getAnswerMaxLength());
                q5Found = true;
            } else if (StringUtils.equals(q6, q.getQuestion().getQuestion())) {
                assertEquals(Integer.valueOf(1), q.getQuestion().getAnswerMaxLength());
                q6Found = true;
            } else {
                fail("Unknown Question: " + q.getQuestion().getQuestion());
            }
        }
        assertTrue(q1Found);
        assertTrue(q2Found);
        assertTrue(q3Found);
        assertTrue(q4Found);
        assertTrue(q5Found);
        assertTrue(q6Found);
    }
    
    @Test
    public void testGetNewAnswerHeader() throws Exception{
        AnswerHeader header = questionnaireAnswerService.getQuestionnaireAnswer(questionnaireHelper.getModuleQnBean()).get(0);
        assertEquals(6, header.getAnswers().size());

        for(Answer answer : header.getAnswers()) {
            answer.setAnswer("1");
        }
        
        this.businessObjectService.save(header);
        
        List<AnswerHeader> headers = questionnaireAnswerService.getQuestionnaireAnswer(questionnaireHelper.getModuleQnBean());
        assertEquals(1, headers.size());
        List<Answer> answers = headers.get(0).getAnswers();
        assertEquals(6, answers.size());
        
        boolean q1Found = false;
        boolean q2Found = false;
        boolean q3Found = false;
        boolean q4Found = false;
        boolean q5Found = false;
        boolean q6Found = false;
        for(Answer answer : answers) {
            Question thisQuestion = answer.getQuestion();
            if (StringUtils.equals(q1, thisQuestion.getQuestion())) {
                assertEquals(Integer.valueOf(1), thisQuestion.getAnswerMaxLength());
                q1Found = true;
            } else if (StringUtils.equals(q2, thisQuestion.getQuestion())) {
                assertEquals("Yes/No", thisQuestion.getQuestionType().getName());
                q2Found = true;
            } else if (StringUtils.equals(q3, thisQuestion.getQuestion())) {
                assertEquals("Yes/No", thisQuestion.getQuestionType().getName());
                q3Found = true;
            } else if (StringUtils.equals(q4, thisQuestion.getQuestion())) {
                assertEquals(Integer.valueOf(1), thisQuestion.getAnswerMaxLength());
                q4Found = true;
            } else if (StringUtils.equals(q5, thisQuestion.getQuestion())) {
                assertEquals(Integer.valueOf(1), thisQuestion.getAnswerMaxLength());
                q5Found = true;
            } else if (StringUtils.equals(q6, thisQuestion.getQuestion())) {
                assertEquals(Integer.valueOf(1), thisQuestion.getAnswerMaxLength());
                q6Found = true;
            } else {
                fail("Unknown Question: " + thisQuestion.getQuestion());
            }
        }
        assertTrue(q1Found);
        assertTrue(q2Found);
        assertTrue(q3Found);
        assertTrue(q4Found);
        assertTrue(q5Found);
        assertTrue(q6Found);
    }
}
