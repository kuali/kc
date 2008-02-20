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
import org.kuali.core.document.Document;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.rules.PreRulesContinuationBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.infrastructure.Constants;

public class CustomAttributePreRules extends PreRulesContinuationBase {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CustomAttributePreRules.class);

    /**
     * 
     * This is to check the custom attribute.  
     * - if lookupClass is selected, then lookupReturn becomes a required field.
     */
    public boolean doRules(Document document) {
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;

        LOG.debug("doRules");

        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        CustomAttribute newCustomAttribute = (CustomAttribute) maintenanceDocument.getNewMaintainableObject().getBusinessObject();

        // FIXME : iffy solution
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())) {
            GlobalVariables.getUserSession().addObject("lookupClassName", (Object)newCustomAttribute.getLookupClass());
        }
        if (StringUtils.isNotBlank(newCustomAttribute.getLookupClass())
                && StringUtils.isBlank(newCustomAttribute.getLookupReturn())) {
            GlobalVariables.getErrorMap().putError("document.newMaintainableObject.lookupReturn", RiceKeyConstants.ERROR_REQUIRED,
                    new String[] { "Lookup Return" });
            event.setActionForwardName(Constants.MAPPING_BASIC);
            return false;
        }


        return true;

    }


}
