/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment;


import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonModuleQuestionnaireBean;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonQuestionnaireHelper;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class ProposalPersonQuestionnaireTest extends KcUnitTestBase {
    
    private BusinessObjectService businessObjectService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private DocumentService documentService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private DevelopmentProposal proposal;
    private ProposalPersonQuestionnaireHelper questionnaireHelper;
    private ProposalDevelopmentForm form;
    
    private static final String q1 = "Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.";
    private static final String q2 = "Is there any potential for a perceived or real conflict of interest as defined in MIT's Policies and Procedures with regard to this proposal?";
    private static final String q3 = "If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?";
    private static final String q4 = "Have lobbying activities been conducted on behalf of this proposal?";
    private static final String q5 = "Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?";
    private static final String q6 = "Are you familiar with the requirements of the Procurement Liabilities Act?";

    @Before
    public void setUp() throws Exception {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        questionnaireAnswerService = KraServiceLocator.getService(QuestionnaireAnswerService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        proposal = getDocument().getDevelopmentProposal();//throw this one away
        proposal = getDocument().getDevelopmentProposal();
        form = new ProposalDevelopmentForm();
        form.setDocument(proposal.getProposalDocument());
        form.initialize();
        questionnaireHelper = new ProposalPersonQuestionnaireHelper(form, getPerson());
    }

    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        questionnaireAnswerService  = null;

        documentService = null;
        proposalDevelopmentService = null;
        proposal = null;
        questionnaireHelper = null;
        form = null;
    }
    
    private ProposalDevelopmentDocument getDocument() throws WorkflowException {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.initialize();

        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());

        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001", "000120");
        
        documentService.saveDocument(document);
        
        if (document.getDevelopmentProposal().getProposalPersons().isEmpty()) {
            KcPerson person = KraServiceLocator.getService(KcPersonService.class).getKcPersonByUserName("quickstart");
            ProposalPerson pp = new ProposalPerson();
            pp.setPersonId(person.getPersonId());
            pp.setDevelopmentProposal(document.getDevelopmentProposal());
            pp.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
            pp.setProposalPersonNumber(new Integer(0));
            ProposalPersonRole role = (ProposalPersonRole)this.businessObjectService.findAll(ProposalPersonRole.class).iterator().next();
            pp.setRole(role);
            pp.setProposalPersonRoleId(role.getRoleCode());
            pp.setOptInUnitStatus("Y");
            pp.setOptInCertificationStatus("Y");
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
        assertEquals(proposal.getProposalNumber(), getPerson().getProposalNumber());
        
        assertNotSame(getPerson().getProposalPersonNumber().toString(), getDocument().getDevelopmentProposal().getProposalPerson(0).getProposalNumber().toString());
        
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
                assertEquals("1", q.getQuestion().getAnswerMaxLength().toString());
                q1Found = true;
            } else if (StringUtils.equals(q2, q.getQuestion().getQuestion())) {
                assertEquals("Yes/No", q.getQuestion().getQuestionType().getQuestionTypeName());
                q2Found = true;
            } else if (StringUtils.equals(q3, q.getQuestion().getQuestion())) {
                assertEquals("1", q.getQuestion().getMaxAnswers().toString());
                q3Found = true;
            } else if (StringUtils.equals(q4, q.getQuestion().getQuestion())) {
                assertEquals("1", q.getQuestion().getAnswerMaxLength().toString());
                q4Found = true;
            } else if (StringUtils.equals(q5, q.getQuestion().getQuestion())) {
                assertEquals("1", q.getQuestion().getAnswerMaxLength().toString());
                q5Found = true;
            } else if (StringUtils.equals(q6, q.getQuestion().getQuestion())) {
                assertEquals("1", q.getQuestion().getAnswerMaxLength().toString());
                q6Found = true;
            } else {
                assertTrue("Unknown Question: " + q.getQuestion().getQuestion(), false);
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
        
        //ProposalPersonModuleQuestionnaireBean bean = new ProposalPersonModuleQuestionnaireBean(proposal, getPerson());
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
                assertEquals("1", thisQuestion.getAnswerMaxLength().toString());
                q1Found = true;
            } else if (StringUtils.equals(q2, thisQuestion.getQuestion())) {
                assertEquals("Yes/No", thisQuestion.getQuestionType().getQuestionTypeName());
                q2Found = true;
            } else if (StringUtils.equals(q3, thisQuestion.getQuestion())) {
                assertEquals("1", thisQuestion.getMaxAnswers().toString());
                q3Found = true;
            } else if (StringUtils.equals(q4, thisQuestion.getQuestion())) {
                assertEquals("1", thisQuestion.getAnswerMaxLength().toString());
                q4Found = true;
            } else if (StringUtils.equals(q5, thisQuestion.getQuestion())) {
                assertEquals("1", thisQuestion.getAnswerMaxLength().toString());
                q5Found = true;
            } else if (StringUtils.equals(q6, thisQuestion.getQuestion())) {
                assertEquals("1", thisQuestion.getAnswerMaxLength().toString());
                q6Found = true;
            } else {
                assertTrue("Unknown Question: " + thisQuestion.getQuestion(), false);
            }
        }
        assertTrue(q1Found);
        assertTrue(q2Found);
        assertTrue(q3Found);
        assertTrue(q4Found);
        assertTrue(q5Found);

    }
}