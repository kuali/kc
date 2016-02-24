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
package org.kuali.coeus.common.impl.custom.attr;

import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

public class CustomAttributeDocumentMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {
    

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

        if (newCustomAttributeDocument.getId() != null) {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("id", newCustomAttributeDocument.getId().toString());

            if (getBoService().countMatching(CustomAttribute.class, queryMap) == 0) {
                GlobalVariables.getMessageMap().putError(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID, KeyConstants.ERROR_INVALID_CUSTOM_ATT_ID,
                        new String[] {newCustomAttributeDocument.getId().toString()});
                return false;
            }
        }


        return true;

    }

}
