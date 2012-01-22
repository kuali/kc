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
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogUtils;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Default implementation of services defined by ProposalLogService.
 */
public class ProposalLogServiceImpl implements ProposalLogService {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Update the status of the log entry for the given proposal number to 'merged'.
     * 
     * @param proposalNumber String
     */
    public void mergeProposalLog(String proposalNumber) {
        updateProposalLogStatus(proposalNumber, ProposalLogUtils.getProposalLogMergedStatusCode());
    }
        
    /**
     * Update the state of the log entry for the given proposal number to reflect that it has been promoted
     * to an Institutional Proposal.
     * 
     * @param proposalNumber String
     */
    public void promoteProposalLog(String proposalNumber) {
        updateProposalLogStatus(proposalNumber, ProposalLogUtils.getProposalLogSubmittedStatusCode());
    }
    
    protected void updateProposalLogStatus(String proposalNumber, String logStatus) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        ProposalLog proposalLog = 
            (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        if (proposalLog == null) {
            throw new IllegalArgumentException("Can't update proposal log status; proposal number " + proposalNumber + " not found.");
        }
        proposalLog.setLogStatus(logStatus);
        this.getBusinessObjectService().save(proposalLog);
    }

    public void updateMergedTempLog(String tempProposalNumber, String permProposalNumber) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", tempProposalNumber);
        ProposalLog proposalLog = (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        proposalLog.setMergedWith(permProposalNumber);
        this.getBusinessObjectService().save(proposalLog);
    }        
    
    public void updateMergedInstProposal(Long proposalId, String proposalNumber)
    {
        // insert proposalId into PROPOSAL_LOG.INST_PROPOSAL_NUMBER
        // where proposalNumber = PROPOSAL_LOG.PROPOSAL_NUMBER
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        ProposalLog proposalLog = 
            (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        if (proposalLog != null) {
            proposalLog.setInstProposalNumber(proposalId.toString());
            this.getBusinessObjectService().save(proposalLog);
        }        
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
