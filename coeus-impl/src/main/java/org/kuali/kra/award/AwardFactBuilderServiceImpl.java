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
package org.kuali.kra.award;

import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;

public class AwardFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {
    
    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, awardDocument);
        } catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Facts.Builder factsBuilder, KrmsRulesContext researchDocument) {
        AwardDocument awardDocument = (AwardDocument)researchDocument;
        Award award = awardDocument.getAward();
        addObjectMembersAsFacts(factsBuilder,award,KcKrmsConstants.Award.AWARD_CONTEXT_ID,Constants.MODULE_NAMESPACE_AWARD);
        factsBuilder.addFact(KcKrmsConstants.Award.AWARD, award);
        // Questionnaire Prereqs
        factsBuilder.addFact(QuestionnaireConstants.MODULE_CODE, CoeusModule.AWARD_MODULE_CODE);
        factsBuilder.addFact(QuestionnaireConstants.MODULE_ITEM_KEY, award.getAwardNumber());
        factsBuilder.addFact(QuestionnaireConstants.MODULE_SUB_ITEM_KEY, award.getSequenceNumber());
    }
    
    protected String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String) xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException(e);
        }
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
