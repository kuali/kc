/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class OrganizationMaintenanceDocumentRule  extends MaintenanceDocumentRuleBase {

    /**
     * Constructs a OrganizationMaintenanceDocumentRule.java.
     */
    public OrganizationMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }
    
   
    /**
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    public boolean isDocumentValidForSave( MaintenanceDocument document ) {
        boolean result = super.isDocumentValidForSave(document);
        
        result &= checkYNQ(document);
        result &= checkRolodexEntries(document);
        return result;
    }
    
    
    /**
     * 
     * This method to check Ynq's explanation and review date is required field based on answer.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkYNQ(MaintenanceDocument maintenanceDocument) {
        ErrorReporter errorReporter = new ErrorReporter();
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
                        new String[] { organizationYnq.getYnq().getQuestionId()}
                    );
                valid=false;
            }
            
            if (StringUtils.isNotBlank(organizationYnq.getAnswer()) && 
                    organizationYnq.getAnswer().equalsIgnoreCase(organizationYnq.getYnq().getExplanationRequiredFor()) && StringUtils.isBlank(organizationYnq.getExplanation())) {
                
                errorReporter.reportError(String.format( "document.newMaintainableObject.organizationYnqs[%s].explanation", i ), 
                        KeyConstants.ERROR_ORGANIZATION_QUESTIONYNQ_EXPLANATION_REQUIRED,
                        new String[] { organizationYnq.getYnq().getQuestionId()}
                    );
                
                valid = false;
            }
            if (StringUtils.isNotBlank(organizationYnq.getAnswer()) && 
                    organizationYnq.getAnswer().equalsIgnoreCase(organizationYnq.getYnq().getDateRequiredFor()) && 
                    organizationYnq.getReviewDate() == null
                   ) {
                    errorReporter.reportError(String.format( "document.newMaintainableObject.organizationYnqs[%s].reviewDate", i ), 
                            KeyConstants.ERROR_ORGANIZATION_QUESTIONYNQ_DATE_REQUIRED,
                            new String[] { organizationYnq.getYnq().getQuestionId()}
                        );
                    valid = false;
            }
            i++;
        }
        return valid;
    }
    
    private boolean checkRolodexEntries( MaintenanceDocument maintenanceDocument) {
        boolean valid = true;
        ErrorReporter errorReporter = new ErrorReporter();
        Organization newOrganization = (Organization) maintenanceDocument.getNewMaintainableObject().getDataObject();
        RolodexService rolodexService = KraServiceLocator.getService(RolodexService.class);
        
        
        
        
        if( ( newOrganization.getOnrResidentRep() != null ) && rolodexService.getRolodex( newOrganization.getOnrResidentRep() ) == null )  { 
            errorReporter.reportError(String.format( "document.newMaintainableObject.onrResidentRep" ), 
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY,
                    new String[] { }
                );
            valid = false;
        }
            
        if( ( newOrganization.getContactAddressId() != null ) && rolodexService.getRolodex( newOrganization.getContactAddressId() ) == null ) { 
            errorReporter.reportError(String.format( "document.newMaintainableObject.contactAddressId" ), 
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY,
                    new String[] { }
                );
            valid = false;
        }
        
        if( ( newOrganization.getCognizantAuditor() != null ) && rolodexService.getRolodex( newOrganization.getCognizantAuditor() ) == null ) {
            errorReporter.reportError(String.format( "document.newMaintainableObject.cognizantAuditor" ), 
                    KeyConstants.ERROR_INVALID_ROLODEX_ENTRY,
                    new String[] { }
                );
            valid = false;
        }
        
        return valid;
    }
    
    
    
    

}
