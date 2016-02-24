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
package org.kuali.kra.institutionalproposal.proposallog.service.impl;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.printing.ProposalLogPrint;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

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
        source.setName("ProposalLog-" + log.getProposalNumber());
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
