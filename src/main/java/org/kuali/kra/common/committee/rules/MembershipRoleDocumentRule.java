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
package org.kuali.kra.common.committee.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommitteeType;
import org.kuali.kra.common.committee.bo.MembershipRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;

public class MembershipRoleDocumentRule extends KraMaintenanceDocumentRuleBase {
    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }


    /**
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isDocumentValidForSave(document);
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#isDocumentValidForSave(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        final MembershipRole role = (MembershipRole) document.getNewMaintainableObject().getDataObject();
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
                final MembershipRole oldDocument = (MembershipRole) document.getOldMaintainableObject().getDataObject();
                if (! StringUtils.equalsIgnoreCase(oldDocument.getCommitteeTypeCode(), role.getCommitteeTypeCode())) {
                    
                    result &= checkExistence(role);
                }
            } else {
                result &= checkExistence(role);
            }
        }   else {
            result = true;
        }
        return result;
    }


    private boolean checkExistence(MembershipRole role) {

        boolean valid = true;
        if (StringUtils.isNotBlank(role.getCommitteeTypeCode()) && StringUtils.isNotBlank(role.getCommitteeTypeCode())) {
            Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("committeeTypeCode", role.getCommitteeTypeCode());
            CommitteeType committeeType = (CommitteeType) getBusinessObjectService().findByPrimaryKey(CommitteeType.class, pkMap);
            if (committeeType == null) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.committeeTypeCode", KeyConstants.ERROR_COMMITTEE_TYPE_NOT_EXIST,
                        new String[] {role.getCommitteeTypeCode()});
                valid = false;
            }
        }

        return valid;

    }
   
    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
