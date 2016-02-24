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

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CorrespondencePrintingServiceImpl implements CorrespondencePrintingService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";

    @Autowired
    @Qualifier("printingService")
    private PrintingService printingService;

    private AbstractPrint correspondencePrint;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    
    protected ProtocolCorrespondence getCorrespondence(Long correspondenceId) {
        Map pkMap = new HashMap();
        pkMap.put("correspondenceId", correspondenceId);
        return businessObjectService.findByPrimaryKey(ProtocolCorrespondence.class, pkMap);
    }
    
    @Override
    public List<Printable> getCorrespondencePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
            List<CorrespondencePrintOption> correspondenceToPrint) {
        List<Printable> printables = new ArrayList<Printable>();
        for (CorrespondencePrintOption printOption : correspondenceToPrint) {
            if (printOption.isSelected()) {
                AbstractPrint printable =  new CorrespondencePrint();
                printable.setXmlStream(getCorrespondencePrint().getXmlStream());
                Map<String, Object> reportParameters = new HashMap<String, Object>();
                if(printOption.getScheduleId() != null) {
                    reportParameters.put("scheduleId",printOption.getScheduleId());
                }

               	reportParameters.put("correspondenceId", "1");
                if(ObjectUtils.isNotNull(printOption.getProtocolCorrespondenceTemplate())) {
                    reportParameters.put("template", printOption.getProtocolCorrespondenceTemplate().getCorrespondenceTemplate());
                }
                //  will be used by amendcorrespondence
                reportParameters.put("moduleSubItemCode", printOption.getSubItemCode());
                if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(printOption.getSubItemCode())) {
                    reportParameters.put(PROTOCOL_NUMBER, printOption.getItemKey());
                    reportParameters.put(SUBMISSION_NUMBER, printOption.getSubItemKey());
                }

                if (printable != null) {
                    printable.setPrintableBusinessObject(printableBusinessObject);
                    printable.setReportParameters(reportParameters);
                    printables.add(printable);
                }
            }
        }
        return printables;
    }
    
    /*
     * get the appropriate protocol as printable.
     * need further work for requestion submission correspondence printables
     * which should be retrieved from protocolsubmission ?
     */
    protected ProtocolBase getProtocolPrintable(CorrespondencePrintOption printOption) {
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

    public AbstractPrint getCorrespondencePrint() {
        return correspondencePrint;
    }

    public void setCorrespondencePrint(AbstractPrint correspondencePrint) {
        this.correspondencePrint = correspondencePrint;
    }

    public PrintingService getPrintingService() {
        return printingService;
    }

    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();
}
