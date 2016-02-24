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
package org.kuali.kra.award.rule;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.dao.DataIntegrityViolationException;

public class ContactTypeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    public ContactTypeMaintenanceDocumentRule () {
        super();
    }
    
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return canRecordBeDeleted(document);
    }

    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return canRecordBeDeleted(document);
    }
    
    private boolean canRecordBeDeleted(MaintenanceDocument document) {        
        boolean recordCanBeDeleted = true;
        ContactType contactType = (ContactType)document.getDocumentBusinessObject();       
        if (StringUtils.isNotBlank(contactType.getContactTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("contactTypeCode", contactType.getContactTypeCode());
            try {
                boService.deleteMatching(ContactType.class, fieldValues);
            }
            catch (DataIntegrityViolationException dive) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.contactTypeCode", 
                        KeyConstants.ERROR_CONTACT_TYPE_CODE_FOREIGN_KEY_EXISTS, new String[] { contactType.getContactTypeCode() });
                recordCanBeDeleted = false;
            }
        }
        return recordCanBeDeleted;
    }
}
