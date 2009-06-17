/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class CustomAttributeDocumentMaintenanceDocumentRule  extends MaintenanceDocumentRuleBase {
    
    /**
     * Constructs a CustomAttributeMaintenanceDocumentRule.java.
     */
    public CustomAttributeDocumentMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkOkToInActivate(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkOkToInActivate(document);
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
        CustomAttributeDocument newCustomAttributeDocument = (CustomAttributeDocument) maintenanceDocument.getNewMaintainableObject().getBusinessObject();

        if (newCustomAttributeDocument.getCustomAttributeId() != null && !newCustomAttributeDocument.isActive()) {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put(Constants.CUSTOM_ATTRIBUTE_ID, newCustomAttributeDocument.getCustomAttributeId().toString());

            List<CustomAttributeDocValue> customAttributeDocValueList = (List<CustomAttributeDocValue>)KraServiceLocator.getService(BusinessObjectService.class).findMatching(CustomAttributeDocValue.class, queryMap);

            if (customAttributeDocValueList !=null && customAttributeDocValueList.size() > 0) {
                GlobalVariables.getErrorMap().putError(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_ACTIVE, KeyConstants.ERROR_INACTIVE_CUSTOM_ATT_DOC,
                        new String[] {});
                return false;
            }
        }


        return true;

    }

}
