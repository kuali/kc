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
package org.kuali.kra.institutionalproposal.proposallog.service;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

public interface ProposalLogPrintingService {

    public final static String PROPOSAL_LOG_REPORT_TYPE = "Proposal Log Report";
    public final static String PROPOSAL_LOG_KEY = "proposalLogToPrint";
    
    /**
     * 
     * Print proposal log report
     * @param proposalLogNumber
     * @return
     * @throws PrintingException
     */
    public AttachmentDataSource printProposalLog(String proposalLogNumber) throws PrintingException;
    
    /**
     * 
     * Print proposal log report
     * @param log
     * @return
     * @throws PrintingException 
     */
    public AttachmentDataSource printProposalLog(ProposalLog log) throws PrintingException;
}
