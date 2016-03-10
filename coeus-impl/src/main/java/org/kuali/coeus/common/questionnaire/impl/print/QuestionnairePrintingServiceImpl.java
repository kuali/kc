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
package org.kuali.coeus.common.questionnaire.impl.print;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrint;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("questionnairePrintingService")
public class QuestionnairePrintingServiceImpl implements QuestionnairePrintingService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    @Autowired
    @Qualifier("printingService")
    private PrintingService printingService;
    @Autowired
    @Qualifier("questionnairePrint")
    private QuestionnairePrint questionnairePrint;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    public AttachmentDataSource printQuestionnaire(
            KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters)
            throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnairePrint();
        if (printable != null) {
            printable.setPrintableBusinessObject(printableBusinessObject);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setName("Questionnaire-" + reportParameters.get("documentNumber") + Constants.PDF_FILE_EXTENSION);
            source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }


    public AttachmentDataSource printQuestionnaireAnswer(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) throws PrintingException {
        AttachmentDataSource source = null;
        AbstractPrint printable = getQuestionnairePrint();
        if (printable != null) {
            printable.setPrintableBusinessObject(printableBusinessObject);
            printable.setReportParameters(reportParameters);
            source = getPrintingService().print(printable);
            source.setName("QuestionnaireAnswer" + reportParameters.get(QuestionnaireConstants.QUESTIONNAIRE_ID_PARAMETER_NAME) + Constants.PDF_FILE_EXTENSION);
            source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        }
        return source;
    }

    
    private Questionnaire getQuestionnaire(Long questionnaireRefId) {
        Map<String, Object> pkMap = new HashMap<>();
        pkMap.put("id", questionnaireRefId);
        return businessObjectService.findByPrimaryKey(Questionnaire.class, pkMap);
        
    }

    @Override
    public List<Printable> getQuestionnairePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
                                                     List<QuestionnairePrintOption> questionnairesToPrints) {
        List<Printable> printables = new ArrayList<>();
        for (QuestionnairePrintOption printOption : questionnairesToPrints) {
            if (printOption.isSelected()) {
                AbstractPrint printable =  new QuestionnairePrint();
                printable.setXmlStream(getQuestionnairePrint().getXmlStream());
                Map<String, Object> reportParameters = new HashMap<>();
                Questionnaire questionnaire = getQuestionnaire(printOption.getQuestionnaireId());
                reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, questionnaire.getQuestionnaireSeqIdAsInteger());
                reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_ID_PARAMETER_NAME, questionnaire.getId());
                reportParameters.put("template", questionnaire.getTemplate());
                //  will be used by amendquestionnaire
                reportParameters.put("moduleSubItemCode", printOption.getSubItemCode());
                if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
                    reportParameters.put(PROTOCOL_NUMBER, printOption.getItemKey());
                    reportParameters.put(SUBMISSION_NUMBER, printOption.getSubItemKey());
                }

                printable.setPrintableBusinessObject(getProtocolPrintable(printOption));
                printable.setReportParameters(reportParameters);
                printables.add(printable);

            }
        }
        return printables;
    }

    /*
     * get the appropriate protocol as printable.
     * need further work for requestion submission questionnaire printables
     * which should be retrieved from protocolsubmission ?
     */
    private Protocol getProtocolPrintable(QuestionnairePrintOption printOption) {
        if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("submissionNumber", printOption.getSubItemKey());
            return ((List<ProtocolSubmission>) businessObjectService.findMatchingOrderBy(ProtocolSubmission.class, keyValues,
                    "submissionId", false)).get(0).getProtocol();
        }
        else {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("sequenceNumber", printOption.getSubItemKey());
            return ((List<Protocol>) businessObjectService.findMatching(Protocol.class, keyValues)).get(0);
        }

    }

    public PrintingService getPrintingService() {
        return printingService;
    }

    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public QuestionnairePrint getQuestionnairePrint() {
        return questionnairePrint;
    }

    public void setQuestionnairePrint(QuestionnairePrint questionnairePrint) {
        this.questionnairePrint = questionnairePrint;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
