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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.Set;

import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentPresentationControllerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * Determines read-only fields on the Proposal Log maintenance document.
 */
public class ProposalLogDocumentPresentationController extends MaintenanceDocumentPresentationControllerBase
    implements DocumentPresentationController {
    
    @Override
    public Set<String> getConditionallyReadOnlyPropertyNames(
            MaintenanceDocument document) {
        Set<String> fields = super.getConditionallyReadOnlyPropertyNames(document);
        if (isValidDocument(document)) {
            ProposalLog proposalLog = (ProposalLog) document.getOldMaintainableObject().getDataObject();
            if (isStatusMerged(proposalLog) || isNew(proposalLog) || isCopy(document)) {
                fields.add(ProposalLog.LOG_STATUS);
            }
            if (isEdit(document) || isSaved(document)) {
                fields.add(ProposalLog.PROPOSAL_LOG_TYPE_CODE);
            }
        }
        return fields;
    }

    @Override
    public boolean canEdit(Document document) {
        boolean canEdit = super.canEdit(document);
        if (canEdit) {
            ProposalLog proposalLog = (ProposalLog) ((MaintenanceDocument) document).getOldMaintainableObject().getDataObject();
            if (proposalLog.isSubmitted() && !"Copy".equals(((MaintenanceDocument) document).getNewMaintainableObject().getMaintenanceAction())) {
                canEdit = false;
            }
        }
        return canEdit;
    }
    
    private boolean isValidDocument(MaintenanceDocument document) {
        return document.getOldMaintainableObject() != null 
            && document.getOldMaintainableObject().getDataObject() != null
            && document.getOldMaintainableObject().getDataObject() instanceof ProposalLog;
    }
    
    private boolean isNew(ProposalLog proposalLog) {
        return proposalLog.getProposalNumber() == null;
    }
    
    private boolean isEdit(MaintenanceDocument document) {
        return document.getNewMaintainableObject() != null 
            && KRADConstants.MAINTENANCE_EDIT_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction());
    }
    
    private boolean isSaved(MaintenanceDocument document) {
        return document.getDocumentHeader().getWorkflowDocument().getStatus().getCode().equals(
                KewApiConstants.ROUTE_HEADER_SAVED_CD);
    }
    
    private boolean isCopy(MaintenanceDocument document) {
        return document.getNewMaintainableObject() != null 
            && KRADConstants.MAINTENANCE_COPY_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction());
    }
    
    private boolean isStatusMerged(ProposalLog proposalLog) {
        return ProposalLogUtils.getProposalLogMergedStatusCode().equals(proposalLog.getLogStatus());
    }

}
