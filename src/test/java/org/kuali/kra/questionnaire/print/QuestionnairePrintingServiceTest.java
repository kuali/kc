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

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;

public class QuestionnairePrintingServiceTest extends PrintingServiceTestBase {
    private QuestionnairePrintingService questionnairePrintingService;

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
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject());

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

        QuestionnaireQuestion q1 = new QuestionnaireQuestion();
        q1.setParentQuestionNumber(0);
        q1.setQuestionNumber(1);
        q1.setQuestionRefIdFk(1L);
        q1.setQuestionSeqNumber(1);
        q1.setQuestion(createQuestion(1,"Question 1"));
        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
        questions.add(q1);
        questionnaire.setQuestionnaireQuestions(questions);
        
        return questionnaire;
    }
    private Question createQuestion(Integer questionId, String questionText) {
        Question question = new Question();
        question.setQuestionId(questionId);
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

    private QuestionnairePrintingService getPrintingService() {
        if (questionnairePrintingService == null) {
            questionnairePrintingService = KraServiceLocator
                    .getService(QuestionnairePrintingService.class);
        }
        return questionnairePrintingService;
    }


}
