/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.krms;

import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("proposalDevelopmentFactBuilderService")
public class ProposalDevelopmentFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {
    
	@Autowired
	@Qualifier("documentService")
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
    
    public void addFacts(Facts.Builder factsBuilder, KrmsRulesContext document) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        addBudgetFacts(factsBuilder,proposalDevelopmentDocument);
        addProposalFacts(factsBuilder,developmentProposal);
        factsBuilder.addFact("moduleCode", CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", developmentProposal.getProposalNumber());
        
    }
    
    private void addBudgetFacts(Builder factsBuilder, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Budget budget =  proposalDevelopmentDocument.getDevelopmentProposal().getFinalBudget();
        addObjectMembersAsFacts(factsBuilder,budget,KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT_ID,Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
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
