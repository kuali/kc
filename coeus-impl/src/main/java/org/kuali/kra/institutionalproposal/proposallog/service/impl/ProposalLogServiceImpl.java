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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogType;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogUtils;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

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
    public void mergeProposalLog(ProposalLog permanentProposalLog, String temporaryProposalNumber) {
        ProposalLog tempProposalLog = getBusinessObjectService().findBySinglePrimaryKey(ProposalLog.class, temporaryProposalNumber);
        if (tempProposalLog == null) {
            throw new IllegalArgumentException("Can't update proposal log status; proposal number " + temporaryProposalNumber + " not found.");
        } else {
            tempProposalLog.setMergedWith(permanentProposalLog.getProposalNumber());
            tempProposalLog.setLogStatus(ProposalLogUtils.getProposalLogMergedStatusCode());
            getBusinessObjectService().save(tempProposalLog);
        }
    }
    
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
        if (proposalNumber == null || proposalNumber.length() == 0) {
            throw new IllegalArgumentException("Can't update proposal log status; proposal number is null or empty!.");
        }
        ProposalLog proposalLog = getBusinessObjectService().findBySinglePrimaryKey(ProposalLog.class, proposalNumber);
        if (proposalLog == null) {
            throw new IllegalArgumentException("Can't update proposal log status; proposal number " + proposalNumber + " not found.");
        }
        proposalLog.setLogStatus(logStatus);
        this.getBusinessObjectService().save(proposalLog);
    }
   
    public void updateMergedInstProposal(Long proposalId, String proposalNumber)
    {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        ProposalLog proposalLog = 
            (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        if (proposalLog != null) {
            proposalLog.setInstProposalNumber(proposalId.toString());
            this.getBusinessObjectService().save(proposalLog);
        }        
    }
    
    public List<ProposalLog> getMatchingTemporaryProposalLogs(String proposalLogTypeCode, String piId, String rolodexId) {
        List<ProposalLog> matchedLogs = new ArrayList<ProposalLog>();
        if ((StringUtils.isNotBlank(piId) || StringUtils.isNotBlank(rolodexId)) && StringUtils.equals(proposalLogTypeCode, ProposalLogUtils.getProposalLogPermanentTypeCode())) {
            Map<String, String> criteria = new HashMap<String, String>(); 
            if (StringUtils.isNotBlank(piId)) {
                criteria.put("piId", piId);
            } else if (StringUtils.isNotBlank(rolodexId)) {
                criteria.put("rolodexId", rolodexId);
            }
            criteria.put("proposalLogTypeCode", ProposalLogUtils.getProposalLogTemporaryTypeCode());
            matchedLogs = (List<ProposalLog>) this.getBusinessObjectService().findMatching(ProposalLog.class, criteria);
            purgeAlreadyMergedLogs(matchedLogs);
        }
        return matchedLogs;
    }
    
    public ProposalLogType getProposalLogTypeFromDescription(String description) {
    	return getBusinessObjectService().findMatching(ProposalLogType.class, Collections.singletonMap("description", description))
    			.stream().findFirst().orElse(null);
    }

    /**
     * Temporary logs that have already been merged can't be merged again. 
     * @param pLogs
     */
    private void purgeAlreadyMergedLogs(Collection<ProposalLog> pLogs) {
        Iterator<ProposalLog> iter = pLogs.iterator();
        while (iter.hasNext()) {
            ProposalLog pLog = iter.next();
            if (pLog.getLogStatus().equals(ProposalLogUtils.getProposalLogMergedStatusCode())) {
                iter.remove();
            }
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
