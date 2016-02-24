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
