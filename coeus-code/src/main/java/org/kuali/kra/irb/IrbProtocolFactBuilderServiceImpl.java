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
package org.kuali.kra.irb;


import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;

public class IrbProtocolFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {

    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            ProtocolDocument protocolDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, protocolDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFacts(Facts.Builder factsBuilder, KcTransactionalDocumentBase researchDocument) {
        ProtocolDocument  protocolDocument = (ProtocolDocument)researchDocument;
        Protocol protocol = protocolDocument.getProtocol();
        addObjectMembersAsFacts(factsBuilder,protocol,KcKrmsConstants.IrbProtocol.IRB_PROTOCOL_CONTEXT_ID,Constants.MODULE_NAMESPACE_PROTOCOL);
        factsBuilder.addFact(KcKrmsConstants.IrbProtocol.IRB_PROTOCOL, protocol);
        factsBuilder.addFact("moduleCode", CoeusModule.IRB_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", protocol.getProtocolNumber());
    }

    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}
