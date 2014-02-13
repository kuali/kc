/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TimeFormatter;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.rules.MaintenanceDocumentRule;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class ProposalLogMaintenanceDocumentRules extends MaintenanceDocumentRuleBase
    implements MaintenanceDocumentRule {
    
    private final String SPONSOR_CODE = "document.newMaintainableObject.sponsorCode";
    
    private final String SPONSOR_DEADLINE_TIME = "document.newMaintainableObject.deadlineTime";
    /**
     * Checks to see if document is in valid state to save.
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#isDocumentValidForSave(
     * org.kuali.rice.kns.document.MaintenanceDocument)
     
     * @param document the MaintenanceDocument to check
     * @return boolean
     */
    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean valid = super.isDocumentValidForSave(document);
        
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getDataObject();
        
        if (!isProposalStatusChangeValid(document)) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.logStatus", 
                    KeyConstants.ERROR_INVALID_STATUS_CHANGE, 
                    proposalLog.getProposalLogStatus().getDescription());
            valid = false;
        }
        
        if (ObjectUtils.isNotNull(proposalLog.getPiId())) {
            proposalLog.refreshReferenceObject("person");
            if (proposalLog.getPerson() == null) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.piName", 
                        KeyConstants.ERROR_INVALID_PI, "");
                valid = false;
            }
        }
        
        if (ObjectUtils.isNotNull(proposalLog.getRolodexId())) {
            proposalLog.refreshReferenceObject("rolodex");
            if (proposalLog.getRolodex() == null) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rolodexId", 
                        KeyConstants.ERROR_INVALID_PI, "");                
                valid = false;
            }
        }
        
        if (ObjectUtils.isNotNull(proposalLog.getPiId()) && ObjectUtils.isNotNull(proposalLog.getRolodexId())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rolodexId", 
                    KeyConstants.ERROR_MULTIPLE_PRINCIPAL_INVESTIGATORS, "");
            valid = false;
        }
        
        proposalLog.refreshReferenceObject("unit");
        if (StringUtils.isNotBlank(proposalLog.getLeadUnit()) && proposalLog.getUnit() == null) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.leadUnit", 
                    KeyConstants.ERROR_INVALID_LEADUNIT, "");
            
            valid = false;
        }
        valid &= isSponsorValid(document);
        return valid;
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomRouteDocumentBusinessRules(document);
        
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getDataObject();
        
        if (ObjectUtils.isNull(proposalLog.getPiId()) && ObjectUtils.isNull(proposalLog.getRolodexId())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.piId", 
                    KeyConstants.ERROR_MISSING_PRINCIPAL_INVESTIGATOR, "");
            valid = false;
        }
        
        if (!hasUnitAuthorization(proposalLog)) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.leadUnit", 
                    KeyConstants.ERROR_UNAUTHORIZED_LEAD_UNIT, 
                    GlobalVariables.getUserSession().getPrincipalName().toUpperCase(),
                    proposalLog.getLeadUnit());
            valid = false;
        }
        
        return valid;
    }
    
    /*
     * verify sponsor code is valid.
     */
    private boolean isSponsorValid(MaintenanceDocument document) {
        boolean valid = true;
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getDataObject();
        if (!StringUtils.isBlank(proposalLog.getSponsorCode())) {
            
            proposalLog.refreshReferenceObject("sponsor");
            if (!this.getSponsorService().validateSponsor(proposalLog.getSponsor())) {
                GlobalVariables.getMessageMap().putError(SPONSOR_CODE, KeyConstants.ERROR_INVALID_SPONSOR_CODE);
                valid = false;
            }
            
        }
        if (proposalLog.getDeadlineTime() != null) {
            TimeFormatter formatter = new TimeFormatter();
            
            String deadLineTime = (String) formatter.convertToObject(proposalLog.getDeadlineTime());
            if (!deadLineTime.equalsIgnoreCase(Constants.INVALID_TIME)) {
                proposalLog.setDeadlineTime(deadLineTime);
            } else {
                GlobalVariables.getMessageMap().putError(SPONSOR_DEADLINE_TIME, KeyConstants.INVALID_DEADLINE_TIME);
                valid = false;
            }
        }
        return valid;
    }

    private boolean isProposalStatusChangeValid(MaintenanceDocument document) {
        boolean retval = false;
        ProposalLog oldProposalLog = (ProposalLog) document.getOldMaintainableObject().getDataObject();
        ProposalLog newProposalLog = (ProposalLog) document.getNewMaintainableObject().getDataObject();
        String oldStatus = oldProposalLog.getLogStatus();
        String newStatus = newProposalLog.getLogStatus();
        if (oldProposalLog.getProposalNumber() == null) {
        	retval = true;
        }
        else if (StringUtils.equalsIgnoreCase(oldStatus, newStatus) || StringUtils.equalsIgnoreCase(newStatus, ProposalLogUtils.getProposalLogVoidStatusCode())) {
        	retval = true;
        }
        else if (StringUtils.equalsIgnoreCase(newStatus, ProposalLogUtils.getProposalLogTemporaryStatusCode()) == newProposalLog.isLogTypeTemporary()) {
        	retval = true;
        }

        return retval;
    }
    
    private boolean hasUnitAuthorization(ProposalLog proposalLog) {
        Map<String,String> permissionDetails =new HashMap<String,String>();
        permissionDetails.put("documentTypeName", "ProposalLogMaintenanceDocument");
        Map<String,String> qualifications =new HashMap<String,String>();
        qualifications.put(KraAuthorizationConstants.QUALIFICATION_UNIT_NUMBER, proposalLog.getLeadUnit());
        return getPermissionService().isAuthorized(
                GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                KraAuthorizationConstants.PERMISSION_SUBMIT_PROPOSAL_LOG, 
                qualifications);
    }

    public SponsorService getSponsorService() {
        return KcServiceLocator.getService(SponsorService.class);
    }
    
}
