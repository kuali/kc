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
package org.kuali.kra.rules;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.regex.Pattern;

/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {


    private static final String SPONSOR_CODE_FIELD_NAME = "sponsorCode";
    private static final String SPONSOR_CODE_FORMAT_DESCRIPTION = "exactly six(6) alphanumeric characters";
    private static final String SPONSOR_CODE_ERROR_PROPERTY_NAME = "document.newMaintainableObject.sponsorCode";
    private static final String SPONSOR_CODE_REGEX = "[a-zA-Z0-9]{6}";

    /**
     * Constructs a SponsorMaintenanceDocumentRule.java.
     */
    public SponsorMaintenanceDocumentRule() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCode(document);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCode(document);
    }

    /**
     * This method verifies that the sponsorCode adheres to the required rules.
     * 
     * @param document
     * @return
     */
    private boolean checkSponsorCode(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (sponsor.getSponsorCode() != null && !Pattern.matches(SPONSOR_CODE_REGEX, sponsor.getSponsorCode())) {
            String errorLabel = KcServiceLocator.getService(DataDictionaryService.class).getAttributeErrorLabel(Sponsor.class, SPONSOR_CODE_FIELD_NAME);
            GlobalVariables.getMessageMap().putError(SPONSOR_CODE_ERROR_PROPERTY_NAME, KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT, errorLabel, 
                    sponsor.getSponsorCode(), SPONSOR_CODE_FORMAT_DESCRIPTION);
            valid = false;
        }
        return valid;
    }
    
}