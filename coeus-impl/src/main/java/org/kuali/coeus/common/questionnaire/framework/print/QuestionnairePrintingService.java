/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.questionnaire.framework.print;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;

import java.util.List;
import java.util.Map;

public interface QuestionnairePrintingService {
    
    /**
     * 
     * This method generates the Questionnaire report and returns the PDF stream as
     * {@link AttachmentDataSource}. It first identifies the report type to be
     * printed, then fetches the required report generator. The report generator
     * generates XML which is then passed to {@link PrintingService} for
     * transforming into PDF.
     * @param document
     *            data using which report is generated
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     */
    AttachmentDataSource printQuestionnaire(
            KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) throws PrintingException;

    /**
     * 
     * This method generates the Questionnaire Answer report and returns the PDF stream as
     * {@link AttachmentDataSource}. It first identifies the report type to be
     * printed, then fetches the required report generator. The report generator
     * generates XML which is then passed to {@link PrintingService} for
     * transforming into PDF.
     * @param printableBusinessObject
     *            data using which report is generated
     * @param reportName
     *            report to be generated
     * @param reportParameters
     *            {@link Map} of parameters required for report generation
     * @return {@link AttachmentDataSource} which contains the byte array of the
     *         generated PDF
     * @throws PrintingException
     *             if any errors occur during report generation
     */
    AttachmentDataSource printQuestionnaireAnswer(
            KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) throws PrintingException;
    
    /**
     * 
     * This method is to get the printables for the questions selected and printed with protocol summary.
     * @param printableBusinessObject
     * @param questionnairesToPrints
     * @return
     */
    List<Printable> getQuestionnairePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
            List<QuestionnairePrintOption> questionnairesToPrints);

}
