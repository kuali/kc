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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.print.watermark.Watermark;
import org.kuali.coeus.common.framework.print.watermark.WatermarkConstants;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is the maintenance document rule for valid status code for watermark table.
 */
public class ValidWatermarkStatusMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    @Override

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    @Override
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        final Watermark watermark = (Watermark) document.getNewMaintainableObject().getDataObject();
        if (watermark.getWatermarkType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_TEXT) && watermark.getWatermarkText() == null) {
            final MessageMap errorMap = GlobalVariables.getMessageMap();
            errorMap.putError("document.newMaintainableObject.watermarkText", KeyConstants.ERROR_WATERMARK_TEXT_REQUIRED);
            result = false;
        }
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            if(document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
                final Watermark oldWatermarkDocument = (Watermark) document.getOldMaintainableObject().getDataObject();
                if (watermark.getWatermarkType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE) && watermark.getWatermarkText() == null) {
                    result = true;
                }
                
                if(!oldWatermarkDocument.getStatusCode().equals(watermark.getStatusCode())){
                    result &= validateWatermarkStatusCode(watermark.getStatusCode(),watermark.getGroupName());
                }
            }else{
                result &= validateWatermarkStatusCode(watermark.getStatusCode(),watermark.getGroupName());
            }
           
        }
        else {
            result = true;
        }

        return result;
    }


    /**
     * 
     * This method ensure the uniqueness of status code of watermark.
     * 
     * @param watermarkStatusCode
     * @return boolean
     */
    private boolean validateWatermarkStatusCode(final String watermarkStatusCode, final String watermarkGroupName ) {
        boolean valid = true;
        if ((watermarkStatusCode != null && StringUtils.isNotBlank(watermarkStatusCode)) && (watermarkGroupName != null && StringUtils.isNotBlank(watermarkGroupName))) {
            final Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("statusCode", watermarkStatusCode);
            pkMap.put("groupName", watermarkGroupName);
            final int watermarkMatchingCount = KcServiceLocator.getService(BusinessObjectService.class).countMatching(
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
