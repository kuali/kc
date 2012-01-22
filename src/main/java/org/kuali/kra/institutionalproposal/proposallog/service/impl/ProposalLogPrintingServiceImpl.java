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
package org.kuali.kra.institutionalproposal.proposallog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.printing.ProposalLogPrint;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProposalLogPrintingServiceImpl implements ProposalLogPrintingService {
    
    private BusinessObjectService businessObjectService;
    private PrintingService printingService;
    private ProposalLogPrint proposalLogPrint;


    public AttachmentDataSource printProposalLog(String proposalLogNumber) throws PrintingException {        
        ProposalLog proposalLog = (ProposalLog)getBusinessObjectService().findByPrimaryKey(ProposalLog.class, getFieldValues("proposalNumber", proposalLogNumber));
        return printProposalLog(proposalLog);
    }
    
    public AttachmentDataSource printProposalLog(ProposalLog log) throws PrintingException {
        Map<String, Object> reportParams = new HashMap<String, Object>();
        reportParams.put(PROPOSAL_LOG_KEY, log);
        AbstractPrint printable = getProposalLogPrint();
        printable.setPrintableBusinessObject(null);
        printable.setReportParameters(reportParams);
        AttachmentDataSource source = getPrintingService().print(printable);
        source.setFileName("ProposalLog-" + log.getProposalNumber());
        return source;
    }
    
    protected Map<String, String> getFieldValues(String attr, String value) {
        HashMap<String, String> retVal = new HashMap<String, String>();
        retVal.put(attr, value);
        return retVal;
    }    

    public PrintingService getPrintingService() {
        return printingService;
    }

    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }

    public ProposalLogPrint getProposalLogPrint() {
        return proposalLogPrint;
    }

    public void setProposalLogPrint(ProposalLogPrint proposalLogPrint) {
        this.proposalLogPrint = proposalLogPrint;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
