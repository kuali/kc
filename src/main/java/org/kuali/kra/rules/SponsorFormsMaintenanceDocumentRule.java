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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SponsorForms;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorFormsMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
    /**
     * Constructs a SponsorMaintenanceDocumentRule.java.
     */
    public SponsorFormsMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    /**
     * This method verifies that the sponsorCode adheres to the required rules.
     * 
     * @param document
     * @return
     */
    private boolean checkSponsorCodeOrHierarchyName(MaintenanceDocument document) {
        boolean valid = true;
        SponsorForms sponsorForm = (SponsorForms) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isBlank(sponsorForm.getSponsorCode()) && StringUtils.isBlank(sponsorForm.getSponsorHierarchyName())
                || (StringUtils.isNotBlank(sponsorForm.getSponsorCode()) && StringUtils.isNotBlank(sponsorForm.getSponsorHierarchyName()))) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.sponsorCode", "error.sponsorForms.selector");
            valid = false;
        }
        return valid;
    }
    
}