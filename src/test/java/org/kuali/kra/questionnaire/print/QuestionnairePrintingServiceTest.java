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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

public class QuestionnairePrintingServiceTest extends PrintingServiceTestBase {
    private QuestionnairePrintingService questionnairePrintingService;

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire report.
     */
    @Test
    public void testQuestionnairePrinting() {
        try {
            Map<String, Object> reportParameters = new HashMap<String, Object>();
            reportParameters.put("documentNumber", "123");

            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaire(null, reportParameters);

            // FIXME Writing PDF to disk for testing purpose only.
            PrintingTestUtils.writePdftoDisk(pdfBytes,
                    "Questionnaire");
            assertNotNull(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            //assert false;
            throw new RuntimeException(e);
        }
    }

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire Answer report.
     */
    @Test
    public void testQuestionnaireAnswerPrinting() {
        try {
            Map<String, Object> reportParameters = new HashMap<String, Object>();
            reportParameters.put("questionnaireId", "123");
            ProtocolDocument document = new ProtocolDocument();
            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaireAnswer(document, reportParameters);

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
