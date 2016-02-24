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
