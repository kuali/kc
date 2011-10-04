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
package org.kuali.kra.irb.protocol.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;

public class ProtocolQuestionnaireAuditRuleTest extends ProtocolRuleTestBase {
    private Mockery context = new JUnit4Mockery();
    private ProtocolQuestionnaireAuditRule rule;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * test audit rule
     */
    @Test
    public void testProcessRunAuditBusinessRules() throws Exception {     
                
      List<Questionnaire> questionnaire =  getQuestionnaire(CoeusSubModule.AMENDMENT);
        
        AnswerHeader hdr1 = getAnswerHeader(questionnaire.get(0), true);
        AnswerHeader hdr2 = getAnswerHeader(questionnaire.get(1), true);
        AnswerHeader hdr3 = getAnswerHeader(questionnaire.get(2), true);
        AnswerHeader hdr4 = getAnswerHeader(questionnaire.get(3), true);
        AnswerHeader hdr5 = getAnswerHeader(questionnaire.get(4), true);
        AnswerHeader hdr6 = getAnswerHeader(questionnaire.get(5), true);
        AnswerHeader hdr7 = getAnswerHeader(questionnaire.get(6), true);
        
        
        final List<AnswerHeader> headers1 = new ArrayList<AnswerHeader>();
        headers1.add(hdr1);
        headers1.add(hdr2);
        headers1.add(hdr3);
        headers1.add(hdr4);
        headers1.add(hdr5);
        
        
        
        Protocol defaultProtocol = getNewProtocolDocument().getProtocol(); 
        defaultProtocol.setProtocolNumber("0"); // default protocol
        
        final ProtocolModuleQuestionnaireBean defModule = new ProtocolModuleQuestionnaireBean(defaultProtocol);
        final QuestionnaireAnswerService qnnrService1 = context.mock(QuestionnaireAnswerService.class, "case1");
        context.checking(new Expectations() {
            {
                allowing(qnnrService1).getQuestionnaireAnswer(defModule); 
                will(returnValue(headers1));
            }
        });       
        
        rule = new ProtocolQuestionnaireAuditRule() {
            protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
                return qnnrService1;
            }
        };
        
        // check case 1: that mandatory questionnaires for def usages are being caught for a protocol in that mode
        // should pass all audit rules since all questionnaires are complete and no new versions are published
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        // change header 2 to incomplete, should still pass since its non-mandatory in def mode
        hdr2.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        hdr2.setCompleted(true);
        
        // change header 5 to incomplete, should still pass since its non-mandatory in def mode
        hdr5.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        hdr5.setCompleted(true);
        
        // change header 1 to incomplete, should not pass since its mandatory in def mode
        hdr1.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr1.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        // change header 3 to incomplete, should not pass since its mandatory in def mode
        hdr3.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr3.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        // change header 4 to incomplete, should not pass since its mandatory in def mode
        hdr4.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr4.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        
        //check case 2: that no answerheaders with newer versions published are being caught 
        hdr1.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr1.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        hdr2.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr2.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        hdr3.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr3.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        hdr4.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr4.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        
        hdr5.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
        // switch back
        hdr5.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(defaultProtocol.getProtocolDocument()));
                
        
        // now check the amendment usages
        
        final List<AnswerHeader> headers2 = new ArrayList<AnswerHeader>();
        headers2.add(hdr1);
        headers2.add(hdr2);
        headers2.add(hdr3);
        headers2.add(hdr6);
        headers2.add(hdr7);
        
        Protocol amendmentProtocol = getNewProtocolDocument().getProtocol();
        amendmentProtocol.setProtocolAmendRenewal(new ProtocolAmendRenewal());
        amendmentProtocol.setProtocolNumber("0A"); // amendment protocol
        final ProtocolModuleQuestionnaireBean amendModule = new ProtocolModuleQuestionnaireBean(amendmentProtocol);
        final QuestionnaireAnswerService qnnrService2 = context.mock(QuestionnaireAnswerService.class, "case2");
        context.checking(new Expectations() {
            {
                allowing(qnnrService2).getQuestionnaireAnswer(amendModule); 
                will(returnValue(headers2));
            }
        });       
        
        rule = new ProtocolQuestionnaireAuditRule() {
            protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
                return qnnrService2;
            }
        };               
        
        // check case 1: that mandatory questionnaires for amend usages are being caught for a protocol in that mode
        // change header 2 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr2.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        hdr2.setCompleted(true);
        
        // change header 3 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr3.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        hdr3.setCompleted(true);
        
        // change header 7 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr7.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        hdr7.setCompleted(true);
        
        // change header 6 to incomplete, should not pass since its mandatory in amend/ren mode
        hdr6.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr6.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        // change header 1 to incomplete, should not pass since its mandatory in amend/ren mode
        hdr1.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr1.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        
        
        // check case 2: that no answerheaders have newer versions published 
        hdr1.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr1.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        hdr2.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr2.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        hdr3.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr3.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        hdr6.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr6.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        hdr7.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr7.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        // check case 3: that answerheaders created in amendment mode for questionnaires with mandatory default usage are also complete.
        ProtocolAmendRenewModule parm = new ProtocolAmendRenewModule();
        parm.setProtocolModuleTypeCode(ProtocolModule.QUESTIONNAIRE);
        amendmentProtocol.getProtocolAmendRenewal().getModules().add(parm);
        
        
        final List<AnswerHeader> amendmentHeaders = new ArrayList<AnswerHeader>();
        amendmentHeaders.add(hdr1);
        amendmentHeaders.add(hdr2);       
        amendmentHeaders.add(hdr3);
        amendmentHeaders.add(hdr6);
        amendmentHeaders.add(hdr7);
        
        final List<AnswerHeader> defHeaders = new ArrayList<AnswerHeader>();
        defHeaders.add(hdr1);
        defHeaders.add(hdr2);       
        defHeaders.add(hdr3);
        defHeaders.add(hdr4);
        defHeaders.add(hdr5);
        
        final ProtocolModuleQuestionnaireBean defAmendModule = new ProtocolModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, amendmentProtocol.getProtocolNumber(), "0", 
           amendmentProtocol.getSequenceNumber().toString(), amendmentProtocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().stateIsApproved());
        
        final QuestionnaireAnswerService qnnrService3 = context.mock(QuestionnaireAnswerService.class, "case3");
        context.checking(new Expectations() {
            {
                allowing(qnnrService3).getQuestionnaireAnswer(amendModule); 
                will(returnValue(amendmentHeaders));
                allowing(qnnrService3).getQuestionnaireAnswer(defAmendModule); 
                will(returnValue(defHeaders));
            }
        });       
        
        rule = new ProtocolQuestionnaireAuditRule() {
            protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
                return qnnrService3;
            }
        };               
        
               
        // change header 2 to incomplete, should still pass since its non-mandatory in def mode
        hdr2.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        hdr2.setCompleted(true);
        
        // change header 5 to incomplete, should still pass since its non-mandatory in def mode
        hdr5.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        hdr5.setCompleted(true);
        
        // change header 1 to incomplete,should not pass since its mandatory in def mode
        hdr1.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr1.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));        
        
        // change header 3 to incomplete,should not pass since its mandatory in def mode
        hdr3.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr3.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
        // change header 4 to incomplete, should not pass since its mandatory in def mode
        hdr4.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        // switch back
        hdr4.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(amendmentProtocol.getProtocolDocument()));
        
    }
  
    @Test
    public void testProcessRunRenewalAuditBusinessRules() throws Exception {     
        // finally, check renewal usages 
        List<Questionnaire> questionnaire =  getQuestionnaire(CoeusSubModule.RENEWAL);
        
        AnswerHeader hdr1 = getAnswerHeader(questionnaire.get(0), true);
        AnswerHeader hdr2 = getAnswerHeader(questionnaire.get(1), true);
        AnswerHeader hdr3 = getAnswerHeader(questionnaire.get(2), true);
        AnswerHeader hdr4 = getAnswerHeader(questionnaire.get(3), true);
        AnswerHeader hdr5 = getAnswerHeader(questionnaire.get(4), true);
        AnswerHeader hdr6 = getAnswerHeader(questionnaire.get(5), true);
        AnswerHeader hdr7 = getAnswerHeader(questionnaire.get(6), true);
        
        
        final List<AnswerHeader> headers1 = new ArrayList<AnswerHeader>();
        headers1.add(hdr1);
        headers1.add(hdr2);
        headers1.add(hdr3);
        headers1.add(hdr4);
        headers1.add(hdr5);
        
        final List<AnswerHeader> headers2 = new ArrayList<AnswerHeader>();
        headers2.add(hdr1);
        headers2.add(hdr2);
        headers2.add(hdr3);
        headers2.add(hdr6);
        headers2.add(hdr7);
        
        Protocol renewalProtocol = getNewProtocolDocument().getProtocol();
        renewalProtocol.setProtocolAmendRenewal(new ProtocolAmendRenewal());
        renewalProtocol.setProtocolNumber("0R"); // renewal protocol
        final ProtocolModuleQuestionnaireBean renewalModule = new ProtocolModuleQuestionnaireBean(renewalProtocol);
        final QuestionnaireAnswerService qnnrService2 = context.mock(QuestionnaireAnswerService.class, "case2");
        context.checking(new Expectations() {
            {
                allowing(qnnrService2).getQuestionnaireAnswer(renewalModule); 
                will(returnValue(headers2));
            }
        });     
        
        rule = new ProtocolQuestionnaireAuditRule() {
            protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
                return qnnrService2;
            }
        };  
        // check case 1: that mandatory questionnaires for renewal usages are being caught for a protocol in that mode
        // change header 2 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr2.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        hdr2.setCompleted(true);
        
        // change header 3 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr3.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        hdr3.setCompleted(true);
        
        // change header 7 to incomplete, should still pass since its non-mandatory in amend/ren mode
        hdr7.setCompleted(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        hdr7.setCompleted(true);
        
        // change header 6 to incomplete, should not pass since its mandatory in amend/ren mode
        hdr6.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr6.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        // change header 1 to incomplete, should not pass since its mandatory in amend/ren mode
        hdr1.setCompleted(false);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr1.setCompleted(true);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        
        
        // check case 2: that no answerheaders have newer versions published 
        hdr1.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr1.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        hdr2.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr2.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        hdr3.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr3.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        hdr6.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr6.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        
        hdr7.setNewerVersionPublished(true);
        Assert.assertFalse(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));
        // switch back
        hdr7.setNewerVersionPublished(false);
        Assert.assertTrue(rule.processRunAuditBusinessRules(renewalProtocol.getProtocolDocument()));    
        
        
      
    }
    

    protected List<Questionnaire> getQuestionnaire(String subModule) {
        List<Questionnaire> questionnaire = new ArrayList<Questionnaire>();
        // Questionnaire 1: both sub_module highest version usages are mandatory        
        QuestionnaireUsage defNonMandatoryUsage1 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 1, false);
        QuestionnaireUsage defMandatoryUsage1 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 2, true);
        QuestionnaireUsage amenRenNonMandatoryUsage1 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 1, false);
        QuestionnaireUsage amenRenMandatoryUsage1 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 2, true);
        
        List<QuestionnaireUsage> usages1 = new ArrayList<QuestionnaireUsage>();
        usages1.add(defNonMandatoryUsage1);
        usages1.add(defMandatoryUsage1);
        usages1.add(amenRenNonMandatoryUsage1);
        usages1.add(amenRenMandatoryUsage1);        
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setQuestionnaireUsages(usages1);
        questionnaire.add(questionnaire1);
                
        // Questionnaire 2: both sub_module highest version usages are non-mandatory        
        QuestionnaireUsage defNonMandatoryUsage2 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 1, true);
        QuestionnaireUsage defMandatoryUsage2 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 2, false);
        QuestionnaireUsage amenRenNonMandatoryUsage2 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 1, true);
        QuestionnaireUsage amenRenMandatoryUsage2 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 2, false);
        
        List<QuestionnaireUsage> usages2 = new ArrayList<QuestionnaireUsage>();
        usages2.add(defNonMandatoryUsage2);
        usages2.add(defMandatoryUsage2);
        usages2.add(amenRenNonMandatoryUsage2);
        usages2.add(amenRenMandatoryUsage2);        
        Questionnaire questionnaire2 = new Questionnaire();
        questionnaire2.setQuestionnaireUsages(usages2);
        questionnaire.add(questionnaire2);

        // Questionnaire 3: sub_module highest version usage is mandatory in def mode and non-mandatory in amend/renewal mode.        
        QuestionnaireUsage defNonMandatoryUsage3 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 1, false);
        QuestionnaireUsage defMandatoryUsage3 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 2, true);
        QuestionnaireUsage amenRenMandatoryUsage3 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 1, true);
        QuestionnaireUsage amenRenNonMandatoryUsage3 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 2, false);
        
        List<QuestionnaireUsage> usages3 = new ArrayList<QuestionnaireUsage>();
        usages3.add(defNonMandatoryUsage3);
        usages3.add(defMandatoryUsage3);
        usages3.add(amenRenNonMandatoryUsage3);
        usages3.add(amenRenMandatoryUsage3);        
        Questionnaire questionnaire3 = new Questionnaire();
        questionnaire3.setQuestionnaireUsages(usages3);
        questionnaire.add(questionnaire3);

        // Questionnaire 4: sub_module highest version usage is mandatory in def mode and completely absent in amend/renewal mode.        
        QuestionnaireUsage defNonMandatoryUsage4 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 1, false);
        QuestionnaireUsage defMandatoryUsage4 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 2, true);
        
        List<QuestionnaireUsage> usages4 = new ArrayList<QuestionnaireUsage>();
        usages4.add(defNonMandatoryUsage4);
        usages4.add(defMandatoryUsage4);        
        Questionnaire questionnaire4 = new Questionnaire();
        questionnaire4.setQuestionnaireUsages(usages4);
        questionnaire.add(questionnaire4);

        // Questionnaire 5: sub_module highest version usage is non-mandatory in def mode and completely absent in amend/renewal mode.        
        QuestionnaireUsage defNonMandatoryUsage5 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 1, true);
        QuestionnaireUsage defMandatoryUsage5 = getUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.ZERO_SUBMODULE, 2, false);
        
        List<QuestionnaireUsage> usages5 = new ArrayList<QuestionnaireUsage>();
        usages5.add(defNonMandatoryUsage5);
        usages5.add(defMandatoryUsage5);
        Questionnaire questionnaire5 = new Questionnaire();
        questionnaire5.setQuestionnaireUsages(usages5);
        questionnaire.add(questionnaire5);

        // Questionnaire 6: sub_module highest version usage is mandatory in amendment/renewal mode and completely absent in def mode.        
        QuestionnaireUsage amenRenNonMandatoryUsage6 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 1, false);
        QuestionnaireUsage amenRenMandatoryUsage6 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 2, true);
        
        List<QuestionnaireUsage> usages6 = new ArrayList<QuestionnaireUsage>();
        usages6.add(amenRenNonMandatoryUsage6);
        usages6.add(amenRenMandatoryUsage6);        
        Questionnaire questionnaire6 = new Questionnaire();
        questionnaire6.setQuestionnaireUsages(usages6);
        questionnaire.add(questionnaire6);

        // Questionnaire 7: sub_module highest version usage is non-mandatory in amendment/renewal mode and completely absent in def mode.        
        QuestionnaireUsage amenRenNonMandatoryUsage7 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 1, true);
        QuestionnaireUsage amenRenMandatoryUsage7 = getUsage(CoeusModule.IRB_MODULE_CODE, subModule, 2, false);
        
        List<QuestionnaireUsage> usages7 = new ArrayList<QuestionnaireUsage>();
        usages7.add(amenRenNonMandatoryUsage7);
        usages7.add(amenRenMandatoryUsage7);        
        Questionnaire questionnaire7 = new Questionnaire();
        questionnaire7.setQuestionnaireUsages(usages7);            
        questionnaire.add(questionnaire7);

        return questionnaire;
    }
    
    private QuestionnaireUsage getUsage(String moduleCode, String subModuleCode, int qnnrSeqNumber, boolean mandatory) {
        QuestionnaireUsage usage = new QuestionnaireUsage();
        usage.setQuestionnaireSequenceNumber(qnnrSeqNumber);
        usage.setModuleItemCode(moduleCode);
        usage.setModuleSubItemCode(subModuleCode);
        usage.setMandatory(mandatory);
       /* BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        businessObjectService.save(usage);*/
        return usage;
    }
    
    private AnswerHeader getAnswerHeader(Questionnaire qnnr, boolean completed) {
        AnswerHeader header = new AnswerHeader();
        header.setQuestionnaire(qnnr);
        header.setCompleted(completed);
        return header;
    }
  
}
