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
package org.kuali.kra.questionnaire.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.print.QuestionnairePrintOption;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class QuestionnairePrintingServiceTest extends PrintingServiceTestBase {
    private QuestionnairePrintingService questionnairePrintingService;
    private Mockery context = new JUnit4Mockery();

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire report.
     */
    @Test
    public void testQuestionnairePrinting() {
        try {
            String docNumber = createQuestionnaireMaintDocument();
            Map<String, Object> reportParameters = new HashMap<String, Object>();
            reportParameters.put("documentNumber", docNumber);

            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaire(null, reportParameters);

            // FIXME Writing PDF to disk for testing purpose only.
            PrintingTestUtils.writePdftoDisk(pdfBytes,"Questionnaire");
            assertNotNull(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            //assert false;
            throw new RuntimeException(e);
        }
    }
    /**
     * This method...
     * @return
     * @throws WorkflowException
     */
    private String createQuestionnaireMaintDocument() throws WorkflowException {
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());

        String docNumber = maintDocument.getDocumentNumber();
        return docNumber;
    }
    /**
     * 
     * This method to create questionnaire for maintenance document manipulation
     * @param name
     * @param desc
     * @return
     */
    private Questionnaire createQuestionnaire(String name, String desc) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setDescription(desc);
        questionnaire.setSequenceNumber(1);
        
        /**
         * @ToDo get QuestionnaireQuestion working
         */
        
        /*
        QuestionnaireQuestion q1 = new QuestionnaireQuestion();
        q1.setParentQuestionNumber(0);
        q1.setQuestionNumber(1);
        q1.setQuestionRefIdFk(1L);
        q1.setQuestionSeqNumber(1);
        q1.setQuestion(createQuestion(1,"Question 1"));
        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
        questions.add(q1);
        questionnaire.setQuestionnaireQuestions(questions);
        */
        return questionnaire;
    }
    private Question createQuestion(Integer questionId, String questionText) {
        Question question = new Question();
        question.setQuestionIdFromInteger(questionId);
        question.setQuestion(questionText);
        return question;
    }

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire Answer report.
     */
    @Test
    public void testQuestionnaireAnswerPrinting() {
        try {
            Map<String, Object> reportParameters = new HashMap<String, Object>();
//            reportParameters.put("questionnaireId", "123");
            reportParameters.put("documentNumber", createQuestionnaireMaintDocument());
            ProtocolDocument document = new ProtocolDocument();
            document.getProtocol().setProtocolNumber("1234");
            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaireAnswer(document.getProtocol(), reportParameters);

            // FIXME Writing PDF to disk for testing purpose only.
            PrintingTestUtils.writePdftoDisk(pdfBytes,
                    "QuestionnaireAnswer");
            assertNotNull(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            //assert false;
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetQuestionnairePrintable() {
        List<QuestionnairePrintOption> questionnairesToPrints = new ArrayList<QuestionnairePrintOption>();
        QuestionnairePrintOption printOption1 = new QuestionnairePrintOption();
        printOption1.setLabel("Test1");
        printOption1.setItemKey("1234");
        printOption1.setSubItemKey("0");
        printOption1.setQuestionnaireRefId(1L);
        printOption1.setQuestionnaireId(1);
        printOption1.setSelected(true);
        questionnairesToPrints.add(printOption1);
        QuestionnairePrintOption printOption2 = new QuestionnairePrintOption();
        printOption2.setLabel("Test2");
        printOption2.setQuestionnaireRefId(2L);
        printOption2.setQuestionnaireId(1);
        printOption2.setSelected(false);
        questionnairesToPrints.add(printOption2);
        final Map  pkMap = new HashMap();
        pkMap.put("questionnaireRefId", 1L);
        try {
            final Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId("1");
            questionnaire.setQuestionnaireRefIdFromLong(1L);
            ProtocolDocument document = new ProtocolDocument();
            final List<Protocol>protocols = new ArrayList<Protocol>(); 
            protocols.add(document.getProtocol());
            document.getProtocol().setProtocolNumber("1234");
            final Map keyValues = new HashMap();
            keyValues.put("protocolNumber", "1234");
            keyValues.put("sequenceNumber", "0");
           final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
           QuestionnairePrint questionnairePrint = new QuestionnairePrint();
           QuestionnairePrintingServiceImpl qnPrintingServiceImpl = new QuestionnairePrintingServiceImpl();
            context.checking(new Expectations() {{
                one(businessObjectService).findByPrimaryKey(Questionnaire.class, pkMap); will(returnValue(questionnaire));
                one(businessObjectService).findMatching(Protocol.class, keyValues); will(returnValue(protocols));
            }});
            qnPrintingServiceImpl.setBusinessObjectService(businessObjectService);
            qnPrintingServiceImpl.setQuestionnairePrint(questionnairePrint);
            
            List<Printable> printables = qnPrintingServiceImpl.getQuestionnairePrintable(document.getProtocol(), questionnairesToPrints);
            // FIXME Writing PDF to disk for testing purpose only.
            assertEquals(printables.size(),1);
            assertEquals(((AbstractPrint)printables.get(0)).getReportParameters().get("questionnaireId"), 1);
        } catch (Exception e) {
            e.printStackTrace();
            //assert false;
            throw new RuntimeException(e);
        }
    }

    private QuestionnairePrintingService getPrintingService() {
        if (questionnairePrintingService == null) {
            questionnairePrintingService = KraServiceLocator
                    .getService(QuestionnairePrintingService.class);
        }
        return questionnairePrintingService;
    }


}
