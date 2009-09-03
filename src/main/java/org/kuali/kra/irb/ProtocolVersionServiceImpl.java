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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Protocol Version Service Implementation.
 */
public class ProtocolVersionServiceImpl implements ProtocolVersionService {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;

    /**
     * Inject the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Inject the Versioning Service.
     * @param versioningService
     */
    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
    /**
     * @see org.kuali.kra.irb.ProtocolVersionService#versionProtocolDocument(org.kuali.kra.irb.ProtocolDocument)
     */
    public ProtocolDocument versionProtocolDocument(ProtocolDocument protocolDocument) throws Exception {
     
        Protocol newProtocol = versioningService.createNewVersion(protocolDocument.getProtocol());
        
        ProtocolDocument newProtocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
        newProtocolDocument.getDocumentHeader().setDocumentDescription(protocolDocument.getDocumentHeader().getDocumentDescription());
      
        fixNextValues(protocolDocument, newProtocolDocument);
        newProtocolDocument.setProtocol(newProtocol);
        newProtocol.setProtocolDocument(newProtocolDocument);
        
        protocolDocument.getProtocol().setActive(false);
        
        businessObjectService.save(protocolDocument.getProtocol());
        documentService.saveDocument(newProtocolDocument);
        
        return newProtocolDocument;
    }
    
    /**
     * The document next values must be the same in the new version as in
     * the old document.  Note that the next document values must be assigned
     * the document number of the new version.
     * @param oldDoc
     * @param newDoc
     */
    private void fixNextValues(ProtocolDocument oldDoc, ProtocolDocument newDoc) {
        List<DocumentNextvalue> newNextValues = new ArrayList<DocumentNextvalue>();
        List<DocumentNextvalue> oldNextValues = oldDoc.getDocumentNextvalues();
        for (DocumentNextvalue oldNextValue : oldNextValues) {
            DocumentNextvalue newNextValue = new DocumentNextvalue();
            newNextValue.setPropertyName(oldNextValue.getPropertyName());
            newNextValue.setNextValue(oldNextValue.getNextValue());
            newNextValue.setDocumentKey(newDoc.getDocumentNumber());
            newNextValues.add(newNextValue);
        }
        newDoc.setDocumentNextvalues(newNextValues);
    }
}
