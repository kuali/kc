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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentPresentationControllerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Set;

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
