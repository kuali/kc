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
package org.kuali.kra.coi;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;

public class CoiDisclosureFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            CoiDisclosureDocument disclosureDocument = (CoiDisclosureDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, disclosureDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Builder factsBuilder, KcTransactionalDocumentBase document){
        CoiDisclosureDocument disclosureDocument = (CoiDisclosureDocument)document;
        CoiDisclosure coiDisclosure = disclosureDocument.getCoiDisclosure();
        addObjectMembersAsFacts(factsBuilder,coiDisclosure,KcKrmsConstants.CoiDisclosure.COI_DISCLOSURE_CONTEXT_ID,Constants.MODULE_NAMESPACE_COIDISCLOSURE);
        factsBuilder.addFact(KcKrmsConstants.CoiDisclosure.COI_DISCLOSURE, coiDisclosure);

        // Functions
        // Is person under campus code
        // Person campus code
    
        // Questionnaire Prereqs
        factsBuilder.addFact("moduleCode", CoeusModule.COI_DISCLOSURE_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", coiDisclosure.getCoiDisclosureNumber());
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

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
