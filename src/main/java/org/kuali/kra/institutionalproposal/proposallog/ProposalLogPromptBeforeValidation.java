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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.rule.PromptBeforeValidation;
import org.kuali.rice.kns.rules.PromptBeforeValidationBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * If a newly created Permanent proposal log has the same PI as one or more Temporary logs,
 * check if the user wants to merge one of the Temporary logs to the Permanent log.
 * This prompt will occur upon submission of the Permanent log.
 */
public class ProposalLogPromptBeforeValidation extends PromptBeforeValidationBase implements PromptBeforeValidation {
    
    private transient BusinessObjectService businessObjectService;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean doPrompts(Document document) {
        ProposalLog proposalLog = (ProposalLog) document.getNoteTarget();
        String piId = proposalLog.getPiId();
        if (piId != null && proposalLog.getProposalLogTypeCode().equals(ProposalLogUtils.getProposalLogPermanentTypeCode())) {
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put("piId", piId);
            criteria.put("proposalLogTypeCode", ProposalLogUtils.getProposalLogTemporaryTypeCode());
            Collection<ProposalLog> pLogs = this.getBusinessObjectService().findMatching(ProposalLog.class, criteria);
            purgeAlreadyMergedLogs(pLogs);
            if (!pLogs.isEmpty()) {
                StringBuffer text = new StringBuffer("The following temporary proposal logs have the same Principal Investigator: ");
                int index = 0;
                for (ProposalLog pLog : pLogs) {
                    if (index != 0) {
                        text.append(", ");
                    }
                    text.append(pLog.getProposalNumber() + " (");
                    text.append("PI: " + denullify(pLog.getPerson().getFullName()) + "; ");
                    text.append("Sponsor: " + denullify(pLog.getSponsorCode()) + " - " + denullify(pLog.getSponsorName()) + "; ");
                    text.append("Title: " + denullify(pLog.getTitle()) + "; ");
                    text.append("Proposal Type: " + denullify(pLog.getProposalType().getDescription()) + "; ");
                    text.append("Lead Unit: " + denullify(pLog.getLeadUnit()) + "; ");
                    text.append("Comments: " + denullify(pLog.getComments()));
                    text.append(")");
                    index++;
                }
                
                if (pLogs.size() > 1) {
                    boolean preMergeCheck = this.askOrAnalyzeYesNoQuestion(
                            "preMerge", text + ". Do you want to merge the new permanent log to one of these temporary logs?");
                    if (!preMergeCheck) {
                        return true;
                    }
                }
                
                index = 0;
                int oldProposalNumberLength = 0;
                for (ProposalLog pLog : pLogs) {
                    if (index == 0) {
                        text.append(". Merge new permanent log to temporary proposal log " + pLog.getProposalNumber() + "?");
                        
                    } else {
                        text.delete(text.length() - oldProposalNumberLength - 1, text.length());
                        text.append(pLog.getProposalNumber() + "?");
                    }
                    
                    boolean merge = this.askOrAnalyzeYesNoQuestion("mergeQuestion" + index, text.toString());
                    if (merge) {
                        proposalLog.setProposalLogToMerge(pLog.getProposalNumber());
                        // the calls below will save to the object that will update the database
                        ProposalLog proposalLogToModify = (ProposalLog) ((MaintenanceDocument)document).getNewMaintainableObject().getDataObject();
                        proposalLogToModify.setProposalLogToMerge(pLog.getProposalNumber());
                        proposalLogToModify.setMergedWith(pLog.getProposalNumber()); 
                        break;
                    }
                    oldProposalNumberLength = pLog.getProposalNumber().length();
                    index++;
                }
            }
        }
        
        return true;
    }
    
    /* Replace a null string with an empty string. */
    private String denullify(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
    /* Temporary logs that have already been merged can't be merged again. */
    private void purgeAlreadyMergedLogs(Collection<ProposalLog> pLogs) {
        Collection<ProposalLog> logsToRemove = new ArrayList<ProposalLog>();
        for (ProposalLog pLog : pLogs) {
            if (pLog.getLogStatus().equals(ProposalLogUtils.getProposalLogMergedStatusCode())) {
                logsToRemove.add(pLog);
            }
        }
        pLogs.removeAll(logsToRemove);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }

}
