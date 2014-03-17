/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class CustomAttributeDocumentMaintenanceDocumentRule  extends MaintenanceDocumentRuleBase {
    

    public CustomAttributeDocumentMaintenanceDocumentRule() {
        super();
    }

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return validate(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return validate(document);
    }

    private boolean validate(MaintenanceDocument document) {
        boolean valid = checkOkToInActivate(document);
        valid &= checkCustomAttributeExist(document);
        return valid;
        
    }
    
    /**
     * 
     * This method to check whether it is ok to inactivate a custom attribute document.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkOkToInActivate(MaintenanceDocument maintenanceDocument) {


        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        CustomAttributeDocument newCustomAttributeDocument = (CustomAttributeDocument) maintenanceDocument.getNewMaintainableObject().getDataObject();

        return true;

    }

    /*
     * check if custom attribute id is valid
     */
    private boolean checkCustomAttributeExist(MaintenanceDocument maintenanceDocument) {


        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        CustomAttributeDocument newCustomAttributeDocument = (CustomAttributeDocument) maintenanceDocument.getNewMaintainableObject().getDataObject();

        if (newCustomAttributeDocument.getCustomAttributeId() != null) {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("id", newCustomAttributeDocument.getCustomAttributeId().toString());

            if (getBoService().countMatching(CustomAttribute.class, queryMap) == 0) {
                GlobalVariables.getMessageMap().putError(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID, KeyConstants.ERROR_INVALID_CUSTOM_ATT_ID,
                        new String[] {newCustomAttributeDocument.getCustomAttributeId().toString()});
                return false;
            }
        }


        return true;

    }

}
