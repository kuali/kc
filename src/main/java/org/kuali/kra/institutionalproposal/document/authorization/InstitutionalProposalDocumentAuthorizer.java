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
package org.kuali.kra.institutionalproposal.document.authorization;

import java.util.Map;

import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;

/**
 * This class is the Institutional Proposal Document Authorizer.  It determines the edit modes and
 * document actions for all institutional proposal documents.
 */
public class InstitutionalProposalDocumentAuthorizer extends TransactionalDocumentAuthorizerBase 
		implements TransactionalDocumentAuthorizer {
    
    @Override
    protected void addRoleQualification(
            BusinessObject primaryBusinessObjectOrDocument,
            Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) primaryBusinessObjectOrDocument;
        if (institutionalProposalDocument.getInstitutionalProposal() != null && 
                institutionalProposalDocument.getInstitutionalProposal().getLeadUnit() != null) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, institutionalProposalDocument.getInstitutionalProposal().getLeadUnit().getUnitNumber());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
    }
    
}

//public class InstitutionalProposalDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
//
//    /**
//     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
//     */
//    public Set<String> getEditModes(Document document, Person user, Set<String> oldEditModes) {
//        Set<String> editModes = new HashSet<String>();
//        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
//        if (getKraWorkflowService().isInWorkflow(institutionalProposalDocument) || institutionalProposalDocument.isViewOnly()) {
//            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
//        } else {
//            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
//        }
//        return editModes;
//    }
//
//    /**
//     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
//     */
//    public boolean canInitiate(String documentTypeName, Person user) {
//        return true;
//    }
//
//    /**
//     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
//     */
//    public boolean canOpen(Document document, Person user) {
//        return true;
//    }
//    
//    /**
//     * @return
//     */
//    protected KraWorkflowService getKraWorkflowService() {
//        return KraServiceLocator.getService(KraWorkflowService.class);
//    }
//    
//}
