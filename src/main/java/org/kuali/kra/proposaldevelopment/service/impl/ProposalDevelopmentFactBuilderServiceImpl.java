/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.io.ByteArrayInputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.service.impl.KcKrmsFactBuilderServiceHelper;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;
import org.w3c.dom.Document;

public class ProposalDevelopmentFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {
    
    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//document/documentNumber");
        try {
            ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, proposalDevelopmentDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Facts.Builder factsBuilder, ResearchDocumentBase document) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        addBudgetFacts(factsBuilder,proposalDevelopmentDocument);
        addProposalFacts(factsBuilder,developmentProposal);
        factsBuilder.addFact("moduleCode", CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", developmentProposal.getProposalNumber());
        
    }
    
    private void addBudgetFacts(Builder factsBuilder, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Budget budget =  proposalDevelopmentDocument.getFinalBudgetForThisProposal();
        addObjectMembersAsFacts(factsBuilder,budget,KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT_ID,Constants.MODULE_NAMESPACE_BUDGET);
    }
    
    private void addProposalFacts(Builder factsBuilder, DevelopmentProposal developmentProposal) {
        addObjectMembersAsFacts(factsBuilder,developmentProposal,KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT_ID,Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.DEVELOPMENT_PROPOSAL, developmentProposal);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.PROPOSAL_NARRATIVES_COMPLETE, isProposalNarrativesComplete(developmentProposal));
    }
    
    
    protected boolean isProposalNarrativesComplete(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getNarratives()) {
            if (!"C".equals(narrative.getNarrativeStatus())) {
                return false;
            }
        }
        return true;
    }

    protected String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String) xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException();
        }
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
