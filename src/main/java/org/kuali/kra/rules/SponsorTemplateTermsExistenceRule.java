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
package org.kuali.kra.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateTerm;
import org.kuali.kra.bo.SponsorTermType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

public class SponsorTemplateTermsExistenceRule extends KraMaintenanceDocumentRuleBase {

    /**
     * Constructs a SponsorTemplateTermsExistenceRule
     */
    public SponsorTemplateTermsExistenceRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkTermsExistence(document);
    }
    /**
     * 
     * This method is to check the existence of terms of the Sponsor Template.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkTermsExistence(MaintenanceDocument maintenanceDocument) {
        boolean valid= true;
        AwardTemplate awardTemplate = (AwardTemplate) maintenanceDocument.getNewMaintainableObject().getDataObject();
        List<AwardTemplateTerm> aList = awardTemplate.getAwardSponsorTerms();

        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<SponsorTermType> types = businessObjectService.findAll(SponsorTermType.class);
        ArrayList currentTypeCodes = new ArrayList();
        for(AwardTemplateTerm aTerm : aList) {
            currentTypeCodes.add(aTerm.getSponsorTermTypeCode());
        }
        
        for(SponsorTermType aType: types) {
            if(!currentTypeCodes.contains(aType.getSponsorTermTypeCode())) {
                ErrorReporter errorReporter = new ErrorReporter();
                errorReporter.reportError("document.newMaintainableObject.templateTerms", 
                      KeyConstants.ERROR_TERM_REQUIRED, aType.getDescription());
                valid = false;
            }
        }
        return valid;
    }
    
}
