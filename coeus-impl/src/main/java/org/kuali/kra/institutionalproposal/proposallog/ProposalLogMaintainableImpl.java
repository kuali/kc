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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.service.FiscalYearMonthService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.Map;

public class ProposalLogMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    private static final long serialVersionUID = 4690638717398206040L;
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    
    private transient DateTimeService dateTimeService;
    private transient FiscalYearMonthService fiscalYearMonthService;
    private transient InstitutionalProposalService institutionalProposalService;
    
    /* Temporary workaround..this overriding can be removed once 
     * DataObjectMetaDataService.areNotesSupported is fixed
     */
    @Override
    public boolean isNotesEnabled() {
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
        
        // If a person has been selected, lead unit should default to the person's home unit.
        if (KIM_PERSON_LOOKUPABLE_REFRESH_CALLER.equals(refreshCaller)) {
            ProposalLog proposalLog = (ProposalLog) this.getBusinessObject();
            String principalId = (String) fieldValues.get(KimConstants.PrimaryKeyConstants.PRINCIPAL_ID);
            proposalLog.setPiId(principalId);
            if (proposalLog.getPerson() != null) {
                proposalLog.setLeadUnit(proposalLog.getPerson().getOrganizationIdentifier());
            }
        }
    }
    
    @Override
    public void processAfterNew(MaintenanceDocument document, Map<String,String[]> parameters) {
        ProposalLog proposalLog = (ProposalLog) document.getNoteTarget();
        if (StringUtils.isBlank(proposalLog.getProposalNumber())) {
            // New record; set default values.
            setupDefaultValues(proposalLog);
            populateAuditProperties(proposalLog);

            proposalLog.setProposalNumber(getInstitutionalProposalService().getNextInstitutionalProposalNumber());
        }
    }
        
    @Override
    public void processAfterCopy(MaintenanceDocument document, Map<String,String[]> parameters) {
        ProposalLog proposalLog = (ProposalLog) document.getNoteTarget();
        proposalLog.setCreateTimestamp(null);
        proposalLog.setCreateUser(null);
        proposalLog.setUpdateTimestamp(null);
        proposalLog.setUpdateUser(null);
        proposalLog.setFiscalMonth(null);
        proposalLog.setFiscalYear(null);
        proposalLog.setLogStatus(ProposalLogUtils.getProposalLogPendingStatusCode());
        proposalLog.setProposalLogTypeCode(ProposalLogUtils.getProposalLogPermanentTypeCode());
        
        populateAuditProperties(proposalLog);
        proposalLog.setProposalNumber(getInstitutionalProposalService().getNextInstitutionalProposalNumber());
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        ProposalLog proposalLog = (ProposalLog) this.getBusinessObject();
        
        if (proposalLog.isLogTypeTemporary() && StringUtils.equalsIgnoreCase(proposalLog.getLogStatus(), ProposalLogUtils.getProposalLogPendingStatusCode())) {
            proposalLog.setLogStatus(ProposalLogUtils.getProposalLogTemporaryStatusCode());
        }
                
        // We need to set this here so it's in the stored XML
        proposalLog.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        
        if (proposalLog.getPiId() != null) {
            proposalLog.setPiName(proposalLog.getPerson().getFullName());
        } else if (proposalLog.getRolodexId() != null) {
            proposalLog.refreshReferenceObject("rolodex");
            if (proposalLog.getRolodex() != null) {
                proposalLog.setPiName(proposalLog.getRolodex().getFullName());
            }
        }
    }
    
    private void setupDefaultValues(ProposalLog proposalLog) {
        if (StringUtils.isBlank(proposalLog.getLogStatus())) {
            if (proposalLog.isLogTypeTemporary()) {
                proposalLog.setLogStatus(ProposalLogUtils.getProposalLogTemporaryStatusCode());
            }
            else {
                proposalLog.setLogStatus(ProposalLogUtils.getProposalLogPendingStatusCode());
            }
        }
        if (StringUtils.isBlank(proposalLog.getProposalLogTypeCode())) {
            proposalLog.setProposalLogTypeCode(ProposalLogUtils.getProposalLogPermanentTypeCode());
        }
    }
    
    private void populateAuditProperties(ProposalLog proposalLog) {
        Timestamp currentTimestamp = getDateTimeService().getCurrentTimestamp();
        proposalLog.setFiscalMonth(getFiscalYearMonthService().getCurrentFiscalMonthForDisplay());
        proposalLog.setFiscalYear(getFiscalYearMonthService().getCurrentFiscalYear());
        proposalLog.setCreateTimestamp(currentTimestamp);
        proposalLog.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
    }
    
    private FiscalYearMonthService getFiscalYearMonthService() {
        if (this.fiscalYearMonthService == null) {
            fiscalYearMonthService = KcServiceLocator.getService(FiscalYearMonthService.class);
        }
        return this.fiscalYearMonthService;
    }
    
    private DateTimeService getDateTimeService() {
        if (this.dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return this.dateTimeService;
    }

    protected InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

}
