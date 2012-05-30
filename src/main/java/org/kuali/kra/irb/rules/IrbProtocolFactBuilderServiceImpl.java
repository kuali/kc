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
package org.kuali.kra.irb.rules;

import java.io.ByteArrayInputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.w3c.dom.Document;

public class IrbProtocolFactBuilderServiceImpl implements IrbProtocolFactBuilderService {
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//documentNumber");
        try {
            ProtocolDocument protocolDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, protocolDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Facts.Builder factsBuilder, ProtocolDocument protocolDocument) {
        factsBuilder.addFact(KcKrmsConstants.IrbProtocol.PROTOCOL_REFERENCE_NUMBER_1, protocolDocument.getProtocol().getReferenceNumber1());
        factsBuilder.addFact(KcKrmsConstants.IrbProtocol.PROTOCOL_REFERENCE_NUMBER_2, protocolDocument.getProtocol().getReferenceNumber2());
        factsBuilder.addFact(KcKrmsConstants.IrbProtocol.FDA_APPLICATION_NUMBER, protocolDocument.getProtocol().getFdaApplicationNumber());
        
        // This special function hardcoded as 1 to enable routing/notification in any case.
        factsBuilder.addFact(KcKrmsConstants.IrbProtocol.ALL_PROTOCOLS, "1");
        
        // Functions
        // All persons training completed
        // All Protocols
        // Application date
        // Areas of research - do areas of research contain specified?  user input 1 area of research
        // Expiration date
        // Funding source sponsor
        // Funding source unit
        // Is amendment
        // Is renewal
        // PI training complete
        // Protocol campus
        // Protocol document type
        // Protocol lead unit
        // Protocol lead unit below
        // Protocol non-faculty PI
        // Protocol organization
        // Protocol organization changed
        // Protocol PI changed
        // Protocol PI is specified
        // Protocol special review type
        // Protocol submission type
        // Protocol submitted by PI
        // Protocol type
        // Subject type
    
        // Questionnaire Prereqs
        factsBuilder.addFact("moduleCode", CoeusModule.IRB_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", protocolDocument.getProtocol().getProtocolNumber());
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
