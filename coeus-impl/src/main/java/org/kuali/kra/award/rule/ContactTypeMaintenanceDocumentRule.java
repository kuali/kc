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
