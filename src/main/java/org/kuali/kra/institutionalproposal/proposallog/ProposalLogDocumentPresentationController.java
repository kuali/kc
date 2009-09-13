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

import java.util.Set;

import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentPresentationControllerBase;

public class ProposalLogDocumentPresentationController extends MaintenanceDocumentPresentationControllerBase
implements DocumentPresentationController {
    
    @Override
    public Set<String> getConditionallyReadOnlyPropertyNames(
            MaintenanceDocument document) {
        Set<String> fields = super.getConditionallyReadOnlyPropertyNames(document);
        if (document.getOldMaintainableObject() != null 
                && document.getOldMaintainableObject().getBusinessObject() != null
                && document.getOldMaintainableObject().getBusinessObject() instanceof ProposalLog) {
            ProposalLog proposalLog = (ProposalLog) document.getOldMaintainableObject().getBusinessObject();
            if (ProposalLogUtils.getProposalLogMergedStatusCode().equals(proposalLog.getLogStatus())) {
                fields.add("logStatus");
            }
            if (proposalLog.getProposalNumber() != null) {
                fields.add("proposalLogTypeCode");
            }
        }
        return fields;
    }

}
