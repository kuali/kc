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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.maintenance.KraMaintainableImpl;
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
