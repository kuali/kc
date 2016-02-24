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
