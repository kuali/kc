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
package org.kuali.coeus.common.committee.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.bo.MembershipRole;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.Map;

public class MembershipRoleDocumentRule extends KcMaintenanceDocumentRuleBase {
    private BusinessObjectService businessObjectService;

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
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
   
}
