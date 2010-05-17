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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.util.collection.CollectionsUtil;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

import sun.security.action.GetBooleanAction;

/**
 * Protocol Version Service Implementation.
 */
public class ProtocolVersionServiceImpl implements ProtocolVersionService {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private SequenceAccessorService sequenceAccessorService;

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
     
        materializeCollections(protocolDocument.getProtocol());
        Protocol newProtocol = versioningService.createNewVersion(protocolDocument.getProtocol());
      
        ProtocolDocument newProtocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
        newProtocolDocument.getDocumentHeader().setDocumentDescription(protocolDocument.getDocumentHeader().getDocumentDescription());
      
        copyCustomDataAttributeValues(protocolDocument, newProtocolDocument);
        fixNextValues(protocolDocument, newProtocolDocument);
        fixActionSequenceNumbers(protocolDocument.getProtocol(), newProtocol);
        
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        newProtocol.setProtocolId(nextProtocolId);
        resetPersonId(newProtocol);
        newProtocolDocument.setProtocol(newProtocol);
        newProtocol.setProtocolDocument(newProtocolDocument);
        protocolDocument.getProtocol().setActive(false);
        finalizeAttachmentProtocol(protocolDocument.getProtocol());
        businessObjectService.save(protocolDocument.getProtocol());
        documentService.saveDocument(newProtocolDocument);
        refreshAttachmentsPersonnels(newProtocol);
        newProtocol.resetForeignKeys();
        finalizeAttachmentProtocol(newProtocol);
        businessObjectService.save(newProtocol);
        // versioning questionnaire answer
        List<AnswerHeader> newAnswerHeaders = questionnaireAnswerService.versioningQuestionnaireAnswer(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE,
                protocolDocument.getProtocol()), newProtocol.getSequenceNumber());
        if (!newAnswerHeaders.isEmpty()) {
            businessObjectService.save(newAnswerHeaders);
        }

        
        return newProtocolDocument;
    }
    /*
     * seems that deepcopy is not really create new instance for copied obj.  this is really confusing
     */
    private void materializeCollections(Protocol protocol) {
        checkCollection(protocol.getAttachmentProtocols());
        checkCollection(protocol.getAttachmentPersonnels());
        checkCollection(protocol.getProtocolLocations());
        checkCollection(protocol.getProtocolAmendRenewals());
        for (ProtocolPerson person : protocol.getProtocolPersons()) {
            checkCollection(person.getAttachmentPersonnels());
            checkCollection(person.getProtocolUnits());
        }
        
    }
    
    /*
     * Utility method to force to materialize the proxy collection
     */
    private void checkCollection(List<? extends PersistableBusinessObject> bos) {
        if (!bos.isEmpty()) {
            bos.get(0);
        }
    }
    
    /*
     * This method is to make the document status of the attachment protocol to "finalized" 
     */
    private void finalizeAttachmentProtocol(Protocol protocol) {
        for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("1".equals(attachment.getDocumentStatusCode())) {
                attachment.setDocumentStatusCode("2");
            }
        }
    }
        

    /*
     * reset personnel attachment key fields for Amendment or renewal
     * Personnel attachment is technically belong to protocol person
     * But there is also a personnel attachments collection under protocol
     * This method is very similar to the one in protocolcopyservice, maybe should refactor to share.
     */
    private void resetPersonId(Protocol protocol) {
        List <ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
        if (protocol.getProtocolPersons() != null) {
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                Long nextPersonId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
                person.setProtocolPersonId(nextPersonId.intValue());
                for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
                    attachment.setProtocol(protocol);
                    attachment.setPersonId(nextPersonId.intValue());
                    attachment.setPerson(null);
                    attachment.setId(null);
                    attachment.setProtocolId(protocol.getProtocolId());
                    attachments.add(attachment);
                }
            }
            protocol.setAttachmentPersonnels(attachments);
        }

    }

    private void refreshAttachmentsPersonnels(Protocol protocol) {
        if (protocol.getProtocolPersons() != null) {
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                person.refreshReferenceObject("attachmentPersonnels");
            }
        }
    }

    /**
     * The Custom Data Attribute values are stored in the document.  Unfortunately, the
     * Versioning Service doesn't version documents, only BOs.  Thus, after the versioning
     * is complete, this method will copy the custom data attribute values over to the
     * new document.
     * @param protocolDocument
     * @param newProtocolDocument
     */
    private void copyCustomDataAttributeValues(ProtocolDocument protocolDocument, ProtocolDocument newProtocolDocument) {
        newProtocolDocument.initialize();
        if (protocolDocument.getCustomAttributeDocuments().isEmpty()) {
            protocolDocument.initialize();
        }
        for (Entry<String, CustomAttributeDocument> entry : newProtocolDocument.getCustomAttributeDocuments().entrySet()) {
            CustomAttributeDocument cad = protocolDocument.getCustomAttributeDocuments().get(entry.getKey());
            if (cad != null) {
                entry.getValue().getCustomAttribute().setValue(cad.getCustomAttribute().getValue());
            }
        }
    }

    /**
     * The Versioning Service increments all of the sequence numbers.  
     * This is incorrect for Protocol Actions.  The original sequence
     * numbers must be maintained.  Therefore, they are restored from
     * the original protocol.
     * @param protocol
     * @param newProtocol
     */
    private void fixActionSequenceNumbers(Protocol protocol, Protocol newProtocol) {
        for (int i = 0; i < protocol.getProtocolActions().size(); i++) {
            newProtocol.getProtocolActions().get(i).setSequenceNumber(protocol.getProtocolActions().get(i).getSequenceNumber());
        }
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

    /**
     * @see org.kuali.kra.irb.ProtocolVersionService#getProtocolVersion(java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public Protocol getProtocolVersion(String protocolNumber, Integer sequenceNumber) {
        Protocol protocol = null;
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("protocolNumber", protocolNumber);
        fields.put("sequenceNumber", sequenceNumber);
        Collection<Protocol> protocols = businessObjectService.findMatching(Protocol.class, fields);
        if (protocols.size() == 1) {
            protocol = protocols.iterator().next();
        }
        return protocol;
    }

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

}
