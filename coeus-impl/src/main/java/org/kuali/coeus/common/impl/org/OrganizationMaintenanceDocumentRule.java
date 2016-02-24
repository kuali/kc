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
package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.framework.org.audit.OrganizationAudit;
import org.kuali.coeus.common.framework.org.audit.OrganizationAuditAcceptedType;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.Collections;

public class OrganizationMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {


    public OrganizationMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }
    
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }
    
   
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    public boolean isDocumentValidForSave( MaintenanceDocument document ) {
        boolean result = super.isDocumentValidForSave(document);
        
        result &= checkYNQ(document);
        result &= checkRolodexEntries(document);
        result &= checkAudits(document);
        return result;
    }
    
    
    /**
     * 
     * This method to check Ynq's explanation and review date is required field based on answer.
     */
    private boolean checkYNQ(MaintenanceDocument maintenanceDocument) {
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        boolean valid = true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        Organization newOrganization = (Organization) maintenanceDocument.getNewMaintainableObject().getDataObject();

        int i = 0;
        for (OrganizationYnq organizationYnq : newOrganization.getOrganizationYnqs()) {
            organizationYnq.refreshReferenceObject("ynq");
            
            if( StringUtils.isBlank(organizationYnq.getAnswer()) ) {
                errorReporter.reportError(String.format( "document.newMaintainableObject.organizationYnqs[%s].answer", i ), 
                        KeyConstants.ERROR_ORGANIZATION_QUESTIONYNQ_ANSWER_REQUIRED,
                        organizationYnq.getYnq().getQuestionId());
                valid=false;
            }
            
            if (StringUtils.isNotBlank(organizationYnq.getAnswer()) && 
                    organizationYnq.getAnswer().equalsIgnoreCase(organizationYnq.getYnq().getExplanationRequiredFor()) && StringUtils.isBlank(organizationYnq.getExplanation())) {
                
                errorReporter.reportError(String.format( "document.newMaintainableObject.organizationYnqs[%s].explanation", i ), 
                        KeyConstants.ERROR_ORGANIZATION_QUESTIONYNQ_EXPLANATION_REQUIRED,
                        organizationYnq.getYnq().getQuestionId());
                
                valid = false;
            }
            if (StringUtils.isNotBlank(organizationYnq.getAnswer()) && 
                    organizationYnq.getAnswer().equalsIgnoreCase(organizationYnq.getYnq().getDateRequiredFor()) && 
                    organizationYnq.getReviewDate() == null
                   ) {
                    errorReporter.reportError(String.format( "document.newMaintainableObject.organizationYnqs[%s].reviewDate", i ), 
                            KeyConstants.ERROR_ORGANIZATION_QUESTIONYNQ_DATE_REQUIRED,
                            organizationYnq.getYnq().getQuestionId());
                    valid = false;
            }
            i++;
        }
        return valid;
    }
    
    private boolean checkRolodexEntries( MaintenanceDocument maintenanceDocument) {
        boolean valid = true;
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        Organization newOrganization = (Organization) maintenanceDocument.getNewMaintainableObject().getDataObject();
        RolodexService rolodexService = KcServiceLocator.getService(RolodexService.class);
        
        
        
        
        if( ( newOrganization.getOnrResidentRep() != null ) && rolodexService.getRolodex( newOrganization.getOnrResidentRep() ) == null )  { 
            errorReporter.reportError("document.newMaintainableObject.onrResidentRep",
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY);
            valid = false;
        }
            
        if( ( newOrganization.getContactAddressId() != null ) && rolodexService.getRolodex( newOrganization.getContactAddressId() ) == null ) { 
            errorReporter.reportError("document.newMaintainableObject.contactAddressId",
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY);
            valid = false;
        }
        
        if( ( newOrganization.getCognizantAuditor() != null ) && rolodexService.getRolodex( newOrganization.getCognizantAuditor() ) == null ) {
            errorReporter.reportError("document.newMaintainableObject.cognizantAuditor",
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY);
            valid = false;
        }
        
        return valid;
    }

    private boolean checkAudits(MaintenanceDocument maintenanceDocument) {

        boolean valid = true;
        final Organization newOrganization = (Organization) maintenanceDocument.getNewMaintainableObject().getDataObject();
        int i = 0;
        for (OrganizationAudit organizationAudit : newOrganization.getOrganizationAudits()) {
            valid &= checkExistenceFromTable(OrganizationAuditAcceptedType.class, Collections.singletonMap("code", organizationAudit.getAuditAcceptedCode()), String.format( "organizationAudits[%s].auditAcceptedCode", i ), "Accepted Type");
            i++;
        }
        return valid;
    }
    

}
