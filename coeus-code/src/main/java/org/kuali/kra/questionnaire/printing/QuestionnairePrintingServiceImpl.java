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
package org.kuali.kra.questionnaire.printing;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.print.QuestionnairePrint;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QuestionnairePrintingServiceImpl implements QuestionnairePrintingService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private PrintingService printingService;
    private QuestionnairePrint questionnairePrint;
    private BusinessObjectService businessObjectService;

    
    private Questionnaire getQuestionnaire(Long questionnaireRefId) {
        Map pkMap = new HashMap();
        pkMap.put("questionnaireRefId", questionnaireRefId);
        return (Questionnaire)businessObjectService.findByPrimaryKey(Questionnaire.class, pkMap);
        
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.print.QuestionnairePrintingService#getQuestionnairePrintable(org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase, java.util.List)
     */
    public List<Printable> getQuestionnairePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
                                                     List<QuestionnairePrintOption> questionnairesToPrints) {
        List<Printable> printables = new ArrayList<Printable>();
        for (QuestionnairePrintOption printOption : questionnairesToPrints) {
            if (printOption.isSelected()) {
                //   AbstractPrint printable = getQuestionnairePrint();
                AbstractPrint printable =  new QuestionnairePrint();
                printable.setXmlStream(getQuestionnairePrint().getXmlStream());
                Map<String, Object> reportParameters = new HashMap<String, Object>();
                Questionnaire questionnaire = getQuestionnaire(printOption.getQuestionnaireRefId());
                reportParameters.put("questionnaireId", questionnaire.getQuestionnaireIdAsInteger());
                reportParameters.put("template", questionnaire.getTemplate());
                //  will be used by amendquestionnaire
                reportParameters.put("moduleSubItemCode", printOption.getSubItemCode());
                if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
                    reportParameters.put(PROTOCOL_NUMBER, printOption.getItemKey());
                    reportParameters.put(SUBMISSION_NUMBER, printOption.getSubItemKey());
                }

                if (printable != null) {
                    printable.setPrintableBusinessObject(getProtocolPrintable(printOption));
                    printable.setReportParameters(reportParameters);
                    printables.add(printable);
                }
            }
        }
        return printables;
    }

    /*
     * get the appropriate protocol as printable.
     * need further work for requestion submission questionnaire printables
     * which should be retrieved from protocolsubmission ?
     */
    private ProtocolBase getProtocolPrintable(QuestionnairePrintOption printOption) {
        if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("submissionNumber", printOption.getSubItemKey());
            return ((List<ProtocolSubmissionBase>) businessObjectService.findMatchingOrderBy(getProtocolSubmissionBOClassHook(), keyValues,
                    "submissionId", false)).get(0).getProtocol();
        }
        else {
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", printOption.getItemKey());
            keyValues.put("sequenceNumber", printOption.getSubItemKey());
            return ((List<ProtocolBase>) businessObjectService.findMatching(getProtocolBOClassHook(), keyValues)).get(0);
        }

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

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();

}
