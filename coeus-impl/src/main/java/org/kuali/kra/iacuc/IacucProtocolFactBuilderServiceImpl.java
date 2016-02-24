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
package org.kuali.kra.iacuc;


import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;

public class IacucProtocolFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {

    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            IacucProtocolDocument protocolDocument = (IacucProtocolDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, protocolDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFacts(Facts.Builder factsBuilder, KrmsRulesContext document) {
        IacucProtocolDocument  protocolDocument = (IacucProtocolDocument)document;
        IacucProtocol protocol = protocolDocument.getIacucProtocol();
        addObjectMembersAsFacts(factsBuilder,protocol,KcKrmsConstants.IacucProtocol.IACUC_PROTOCOL_CONTEXT_ID,Constants.MODULE_NAMESPACE_IACUC);
        factsBuilder.addFact(KcKrmsConstants.IacucProtocol.IACUC_PROTOCOL, protocol);
        factsBuilder.addFact(QuestionnaireConstants.MODULE_CODE, CoeusModule.IACUC_PROTOCOL_MODULE_CODE);
        factsBuilder.addFact(QuestionnaireConstants.MODULE_ITEM_KEY, protocol.getProtocolNumber());
        factsBuilder.addFact(QuestionnaireConstants.MODULE_SUB_ITEM_KEY, protocol.getSequenceNumber());
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
