/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.*;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestConstants;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is used to test NasaOtherProjectInformationV1_0 form
 */
public class NASAOtherProjectInformationV1_0GeneratorTest extends S2STestBase<NASAOtherProjectInformationV1_0Generator> {

    @Override
    protected Class<NASAOtherProjectInformationV1_0Generator> getFormGeneratorClass() {
        return NASAOtherProjectInformationV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));

        String INTERNATIONAL_PARTICIPATION = "108";
        String HISTORICAL_IMPACT = "106";
        String EXPLATATION = "107";
        String INTERNATIONAL_PARTICIPATION_SUPPORT = "109";
        String CIVIL_SERVICE_PERSONNEL = "101";
        String FTE = "104";
        String FISCAL_YEAR = "103";
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("SCHULTE");
        person.setLastName("MARITZA");
        person.setMiddleName("D");
        person.setPersonId("000000077 ");
        person.setOptInCertificationStatus("Y");
        person.setOptInUnitStatus("Y");
        person.setProposalPersonNumber(1000);
        List<ProposalPerson> perList = new ArrayList<ProposalPerson>();
        perList.add(person);
        List<ProposalYnq> proList = new ArrayList<ProposalYnq>();
        document.getDevelopmentProposal().setProposalPersons(perList);

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestConstants.ATT_DIR_PATH + "exercise5.pdf");
        InputStream inStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("47");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise5");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for Project Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);
        document.getDevelopmentProposal().setNarratives(naList);
        
        List<AnswerHeader> answerHeaders;
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
        answerHeaders =KraServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(moduleQuestionnaireBean);
       
        for(AnswerHeader answerHeader:answerHeaders){
            if(answerHeader!=null){
                List<Answer> answerDetails = answerHeader.getAnswers();
                for(Answer answers:answerDetails){
                    if(answers.getParentAnswer()!= null){
                        Answer parentAnswer =  answers.getParentAnswer().get(0);

                        if(EXPLATATION.equals(answers.getQuestion().getQuestionId()) && parentAnswer.getQuestion().getQuestionId().equals(HISTORICAL_IMPACT) ){
                            answers.setAnswer("TEST");
                        }

                        if(EXPLATATION.equals(answers.getQuestion().getQuestionId()) && parentAnswer.getQuestion().getQuestionId().equals(INTERNATIONAL_PARTICIPATION) ){
                            answers.setAnswer("TEST");
                        }
                    }
                    if(INTERNATIONAL_PARTICIPATION.equals(answers.getQuestion().getQuestionId())
                            || HISTORICAL_IMPACT.equals(answers.getQuestion().getQuestionId())||
                            INTERNATIONAL_PARTICIPATION_SUPPORT.equals(answers.getQuestion().getQuestionId())
                                    || CIVIL_SERVICE_PERSONNEL.equals(answers.getQuestion().getQuestionId())){
                         answers.setAnswer("Y");
                    }
                    if(FTE.equals(answers.getQuestion().getQuestionId())
                            || FISCAL_YEAR.equals(answers.getQuestion().getQuestionId())){
                        answers.setAnswer("2012");
                    }
                }
            }
        }
        getBusinessObjectService().save(answerHeaders);
    }
}
