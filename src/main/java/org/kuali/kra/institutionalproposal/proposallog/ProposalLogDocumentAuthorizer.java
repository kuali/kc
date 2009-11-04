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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.HashSet;
import java.util.Set;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.BusinessObjectAuthorizerBase;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer;

/* Use this until proper KIM perms are set up */

public class ProposalLogDocumentAuthorizer extends BusinessObjectAuthorizerBase implements MaintenanceDocumentAuthorizer {
    
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
        
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_EDIT)
//                && !isAuthorizedByTemplate(document,
//                        KNSConstants.KNS_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.EDIT_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_EDIT);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_COPY)
//                && !isAuthorizedByTemplate(document,
//                        KNSConstants.KNS_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.COPY_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_COPY);
//        }
//        if (documentActions
//                .contains(KNSConstants.KUALI_ACTION_CAN_BLANKET_APPROVE)
//                && !isAuthorizedByTemplate(
//                        document,
//                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.BLANKET_APPROVE_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions
//                    .remove(KNSConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_CANCEL)
//                && !isAuthorizedByTemplate(document,
//                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.CANCEL_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_CANCEL);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_SAVE)
//                && !isAuthorizedByTemplate(document,
//                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.SAVE_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_SAVE);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_ROUTE)
//                && !isAuthorizedByTemplate(document,
//                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                        KimConstants.PermissionTemplateNames.ROUTE_DOCUMENT,
//                        user.getPrincipalId())) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_ROUTE);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_ACKNOWLEDGE) 
//                && !canTakeRequestedAction(document,
//                KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ, user)) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_ACKNOWLEDGE);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_FYI) &&
//                !canTakeRequestedAction(document, KEWConstants.ACTION_REQUEST_FYI_REQ, user)) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_FYI);
//        }
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_APPROVE)
//                || documentActions
//                        .contains(KNSConstants.KUALI_ACTION_CAN_DISAPPROVE)) {
//            if (!canTakeRequestedAction(document,
//                    KEWConstants.ACTION_REQUEST_APPROVE_REQ, user)) {
//                documentActions.remove(KNSConstants.KUALI_ACTION_CAN_APPROVE);
//                documentActions
//                        .remove(KNSConstants.KUALI_ACTION_CAN_DISAPPROVE);
//            }
//        }
//        if (documentActions
//                .contains(KNSConstants.KUALI_ACTION_CAN_ADD_ADHOC_REQUESTS)
//                && !canSendAnyTypeAdHocRequests(document, user)) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_ADD_ADHOC_REQUESTS);
//        }
//        
//        if(documentActions
//                .contains(KNSConstants.KUALI_ACTION_CAN_SEND_NOTE_FYI)
//                && !canSendAdHocRequests(document, KEWConstants.ACTION_REQUEST_FYI_REQ, user)){
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_SEND_NOTE_FYI);
//        }
//        
//        
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS)
//                && !canSendAnyTypeAdHocRequests(document, user) ) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS);
//        }
//        
//        
//        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_ANNOTATE)
//                && !documentActions
//                        .contains(KNSConstants.KUALI_ACTION_CAN_EDIT)) {
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_ANNOTATE);
//        }
//        if(documentActions.contains(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW) 
//                &&!canEditDocumentOverview(document, user)){
//            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW);
//        }
        return documentActions;
    }

}
