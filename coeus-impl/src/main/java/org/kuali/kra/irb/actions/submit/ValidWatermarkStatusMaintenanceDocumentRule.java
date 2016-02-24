/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
