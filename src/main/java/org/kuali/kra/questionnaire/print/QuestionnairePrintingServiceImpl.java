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

import java.util.Map;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

public class QuestionnairePrintingServiceImpl implements QuestionnairePrintingService {

    private PrintingService printingService;
    private QuestionnairePrint questionnairePrint;
    private QuestionnaireAnswerPrint questionnaireAnswerPrint;

    /**
     * 
     * @see org.kuali.kra.questionnaire.print.QuestionnairePrintingService#printQuestionnaire(org.kuali.kra.document.ResearchDocumentBase,
     *      java.util.Map)
     */
    public AttachmentDataSource printQuestionnaire(ResearchDocumentBase document, Map<String, Object> reportParameters)
            throws PrintingException {
        /* TODO : Questionnaire is a maintenance document.  questionnaireId is generated when document is approved and
        *   saved to DB. so, pk is not in doc xml content, and passing questionnaireid will not work.
        *   Therefore, passing documentNumber, questionnaire can be retrieved from xml content by loaddocument.
        *   This is what I think how offshore team can get questionnaire data to generate pdf.
        */   
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnairePrint();
        if (printable != null) {
            printable.setDocument(document);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setFileName("Questionnaire-" + reportParameters.get("documentNumber") + Constants.PDF_FILE_EXTENSION);
            source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.print.QuestionnairePrintingService#printQuestionnaireAnswer(org.kuali.kra.document.ResearchDocumentBase,
     *      java.util.Map)
     */
    public AttachmentDataSource printQuestionnaireAnswer(ResearchDocumentBase document, Map<String, Object> reportParameters)
            throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnaireAnswerPrint();
        if (printable != null) {
            printable.setDocument(document);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setFileName("QuestionnaireAnswer" + reportParameters.get("questionnaireId") + Constants.PDF_FILE_EXTENSION);
            source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }

    /**
     * @return the printingService
     */
    public PrintingService getPrintingService() {
        return printingService;
    }

    /**
     * @param printingService the printingService to set
     */
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public QuestionnairePrint getQuestionnairePrint() {
        return questionnairePrint;
    }

    public void setQuestionnairePrint(QuestionnairePrint questionnairePrint) {
        this.questionnairePrint = questionnairePrint;
    }

    public QuestionnaireAnswerPrint getQuestionnaireAnswerPrint() {
        return questionnaireAnswerPrint;
    }

    public void setQuestionnaireAnswerPrint(QuestionnaireAnswerPrint questionnaireAnswerPrint) {
        this.questionnaireAnswerPrint = questionnaireAnswerPrint;
    }


}
