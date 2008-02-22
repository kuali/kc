/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import org.kuali.RiceKeyConstants;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.CustomAttribute;

public class CustomAttributeMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {
    
    /**
     * Constructs a CustomAttributeMaintenanceDocumentRule.java.
     */
    public CustomAttributeMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }

    /**
     * 
     * This method to check wheteher 'lookupreturn' is specified if lookupclass is selected.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkLookupReturn(MaintenanceDocument maintenanceDocument) {


        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        CustomAttribute newCustomAttribute = (CustomAttribute) maintenanceDocument.getNewMaintainableObject().getBusinessObject();

        // FIXME : iffy solution - will be used to retrieve lookupreturn list by the valuesfinder class
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())) {
            GlobalVariables.getUserSession().addObject("lookupClassName", (Object)newCustomAttribute.getLookupClass());
        }
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())
                && StringUtils.isBlank(newCustomAttribute.getLookupReturn())) {
            GlobalVariables.getErrorMap().putError("document.newMaintainableObject.lookupReturn", RiceKeyConstants.ERROR_REQUIRED,
                    new String[] { "Lookup Return" });
            return false;
        }


        return true;

    }
}
