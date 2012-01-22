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
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

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
     * This method to check whether 'lookupreturn' is specified if lookupclass is selected.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkLookupReturn(MaintenanceDocument maintenanceDocument) {


        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        CustomAttribute newCustomAttribute = (CustomAttribute) maintenanceDocument.getNewMaintainableObject().getDataObject();

        // FIXME : iffy solution - will be used to retrieve lookupreturn list by the valuesfinder class
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())) {
            GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object)newCustomAttribute.getLookupClass());
        }
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())
                && StringUtils.isBlank(newCustomAttribute.getLookupReturn())) {
            GlobalVariables.getMessageMap().putError(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN, RiceKeyConstants.ERROR_REQUIRED,
                    new String[] { "Lookup Return" });
            return false;
        }


        return true;

    }
}
