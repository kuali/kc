/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.BusinessObjectAuthorizerBase;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer;

/** 
 *  Use this authorizer class when you want to bypass KIM auth checks.
 *  This should be used for development only and should be replaced with
 *  a KIM-enable authorizer.
 */
public class GenericPermissableMaintenanceDocumentAuthorizer extends BusinessObjectAuthorizerBase implements MaintenanceDocumentAuthorizer {
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        return true;
    }
    
    public boolean canReceiveAdHoc(Document document, Person user,
            String actionRequestCode) {
        return true;
    }
    
    public Set<String> getSecurePotentiallyReadOnlySectionIds() {
        return new HashSet<String>();
    }
    
    public Set<String> getSecurePotentiallyHiddenSectionIds() {
        return new HashSet<String>();
    }
    
    public boolean canSendAdHocRequests(Document document, String actionRequestCd, Person user) {
        return true;
    }
    
    public boolean canCreate(Class boClass, Person user) {
        return true;
    }

    public boolean canMaintain(BusinessObject businessObject, Person user) {
        return true;
    }

    public boolean canCreateOrMaintain(MaintenanceDocument maintenanceDocument,
            Person user) {
        return true;
    }
    
    public boolean canAddNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return true;
    }
    
    public boolean canDeleteNoteAttachment(Document document, String attachmentTypeCode, String createdBySelfOnly, Person user) {
        return true;
    }
    
    public boolean canViewNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return true;
    }
    
    public Set<String> getDocumentActions(Document document, Person user,
            Set<String> documentActions) {
        
        return documentActions;
    }

}
