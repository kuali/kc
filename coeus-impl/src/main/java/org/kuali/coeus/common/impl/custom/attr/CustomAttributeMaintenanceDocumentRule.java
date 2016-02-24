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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

public class CustomAttributeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    

    public CustomAttributeMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkLookupReturn(document);
    }
    
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
