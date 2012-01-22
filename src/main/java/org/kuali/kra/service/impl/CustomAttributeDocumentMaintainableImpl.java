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
package org.kuali.kra.service.impl;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;

/**
 * This class...
 */
public class CustomAttributeDocumentMaintainableImpl extends KualiMaintainableImpl {
    
    /**
     * 
     * @see org.kuali.rice.kns.maintenance.KualiMaintainableImpl#prepareForSave()
     */
    public void prepareForSave() {
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument) this.getBusinessObject();
        boolean needsTranslated = true;
        int val = 0;
        try {
            val = Integer.parseInt(customAttributeDocument.getDocumentTypeName());
            // the getDocumentTypeName is a number, so it needs translated
            needsTranslated = true;
        } catch (Exception e) {
            // getDocumentTypeName has already been translated, so can't do it again.
            needsTranslated = false;
        }
        if (needsTranslated) {
            
            customAttributeDocument.setDocumentTypeName(convertModuleNumberToDocumentTypeCode(val));

            /**
             * 1 Award 2 Institute Proposal 3 Development Proposal 4 Subcontracts 5 Negotiations 6 Person 7 IRB 8 COI
             * Disclosure 9 IACUC 11 Committee
             */
        }
        super.prepareForSave();
    }
    
    /**|
     * 
     * This method converts the module number to document type code.
     * Implemented: 1 Award, 2 Institute Proposal, 3 Development Proposal, 7 IRB, 8 COI Disclosure. 
     * Not Implemented: 4 Subcontracts, 5 Negotiations, 6 Person, 9 IACUC, 11 Committee.
     * @param moduleNumber
     * @return
     */
    public String convertModuleNumberToDocumentTypeCode(int moduleNumber) {
        String documentTypeCode = null;
        switch (moduleNumber) {
            case CoeusModule.AWARD_MODULE_CODE_INT: {
                documentTypeCode = AwardDocument.DOCUMENT_TYPE_CODE;
                break;
            }
            case CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE_INT: {
                documentTypeCode = InstitutionalProposalDocument.DOCUMENT_TYPE_CODE;
                break;
            }
            case CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE_INT: {
                documentTypeCode = ProposalDevelopmentDocument.DOCUMENT_TYPE_CODE;
                break;
            }
            case CoeusModule.IRB_MODULE_CODE_INT: {
                documentTypeCode = ProtocolDocument.DOCUMENT_TYPE_CODE;
                break;
            }
            case CoeusModule.COI_DISCLOSURE_MODULE_CODE_INT: {
                documentTypeCode = CoiDisclosureDocument.DOCUMENT_TYPE_CODE;
                break;
            }
            default: {
                throw new IllegalArgumentException("invalid typeName provided: " + documentTypeCode);
            }
        }
        return documentTypeCode;
    }

}
