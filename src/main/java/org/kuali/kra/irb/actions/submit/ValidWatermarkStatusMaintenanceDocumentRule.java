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
package org.kuali.kra.irb.actions.submit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Watermark;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.MessageMap;

/**
 * 
 * This class is the maintenance document rule for valid status code for watermark table.
 */
public class ValidWatermarkStatusMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        final Watermark watermark = (Watermark) document.getNewMaintainableObject().getBusinessObject();

        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KNSConstants.MAINTENANCE_DELETE_ACTION)) {
            return validateWatermarkStatusCode(watermark.getStatusCode());
        }
        else {
            return true;
        }
    }
    
    
    
    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
   
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        final Watermark watermark = (Watermark) document.getNewMaintainableObject().getBusinessObject();
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KNSConstants.MAINTENANCE_DELETE_ACTION)) {
            return validateWatermarkStatusCode(watermark.getStatusCode());
        }
        else {
            return true;
        }

    }


    /**
     * 
     * This method ensure the uniqueness of status code of watermark.
     * 
     * @param watermarkStatusCode
     * @return boolean
     */
    private boolean validateWatermarkStatusCode(final String watermarkStatusCode) {
        boolean valid = true;
        if (watermarkStatusCode != null && StringUtils.isNotBlank(watermarkStatusCode)) {
            final Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("statusCode", watermarkStatusCode);
            final int watermarkMatchingCount = KraServiceLocator.getService(BusinessObjectService.class).countMatching(
                    Watermark.class, pkMap);

            if (watermarkMatchingCount > 0) {
                final MessageMap errorMap = GlobalVariables.getMessageMap();
                errorMap.putError("document.newMaintainableObject.statusCode", KeyConstants.ERROR_WATERMARK_STATUS_CODE_EXIST,
                        new String[] { watermarkStatusCode });
                valid = false;
            }
        }
        return valid;
    }


}
