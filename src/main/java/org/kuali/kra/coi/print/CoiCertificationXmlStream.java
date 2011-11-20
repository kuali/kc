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
package org.kuali.kra.coi.print;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.questionnaire.print.QuestionnaireXmlStream;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;

public class CoiCertificationXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    private static final Log LOG = LogFactory.getLog(QuestionnaireXmlStream.class);

    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        /* 
         * Just want to mention that Questionnaire is a maintenance document (not transactional doc), so passed in document will be null.
         * the report parameters does have a documentNumber, so it can be retrieved from document xml content.
         * "Questionnaire Answer" will pass in protocol document, so, it is fine.
         */
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
//TODO: Following must be implemented.
//        try {
//            xmlObjectList.put("CoiCertification", getCertificationData(printableBusinessObject,reportParameters));
//        }
 //       catch (PrintingException e) {
 //           LOG.error(e);
//      }
        return xmlObjectList;
    }

    /**
     * 
     * This method is to get the Disclosure Certification data for print.
     * @param document
     * @param params
     * @return
     * @throws PrintingException
     */
    @SuppressWarnings("unchecked")
    private CoiDisclosureDocument getCertificationData(KraPersistableBusinessObjectBase printableBusinessObject, 
                                                       Map<String, Object> reportParameters) {
        CoiDisclosureDocument coiDisclosureDocument = null;
        
        
        return coiDisclosureDocument;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


}
