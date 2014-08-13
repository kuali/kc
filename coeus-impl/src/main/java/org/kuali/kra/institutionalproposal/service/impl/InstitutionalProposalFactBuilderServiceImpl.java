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
package org.kuali.kra.institutionalproposal.service.impl;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;

public class InstitutionalProposalFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {
    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument)getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, institutionalProposalDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Facts.Builder factsBuilder, KcTransactionalDocumentBase document) {
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument)document;
        InstitutionalProposal institutionalProposal = institutionalProposalDocument.getInstitutionalProposal();
        addProposalFacts(factsBuilder,institutionalProposal);
        factsBuilder.addFact("moduleCode", CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", institutionalProposal.getProposalNumber());
        
    }
    
    private void addProposalFacts(Builder factsBuilder, InstitutionalProposal institutionalProposal) {
        addObjectMembersAsFacts(factsBuilder,institutionalProposal,KcKrmsConstants.InstitutionalProposal.INSTITUTIONAL_PROPOSAL_CONTEXT_ID,Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL);
        factsBuilder.addFact(KcKrmsConstants.InstitutionalProposal.INSTITUTIONAL_PROPOSAL, institutionalProposal);
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
