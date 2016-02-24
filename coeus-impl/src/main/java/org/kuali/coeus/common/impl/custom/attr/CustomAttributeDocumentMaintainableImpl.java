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
package org.kuali.coeus.common.impl.custom.attr;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;


public class CustomAttributeDocumentMaintainableImpl extends KualiMaintainableImpl {
    
    @Override
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
