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

import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.service.SessionDocumentService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;


/**
 * Protocol Version Service Implementation.
 */
public class ProtocolVersionServiceImpl implements ProtocolVersionService {
    
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private SequenceAccessorService sequenceAccessorService;
    private SessionDocumentService sessionDocumentService;
    private WorkflowDocumentService workflowDocumentService;
    
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
    
    public void setSessionDocumentService(SessionDocumentService sessionDocumentService) {
        this.sessionDocumentService = sessionDocumentService;
    }

    /**
     * Inject the Versioning Service.
     * @param versioningService
     */
    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
    protected ProtocolDocument getNewProtocolDocument() throws Exception {
        // create a new ProtocolDocument
        ProtocolDocument newDoc = null;
        
        // manually assembling a new ProtocolDocument here because the DocumentService will deny initiator permission without context
        // we circumvent the initiator step altogether. 
        try {
            WorkflowDocument workflowDocument = workflowDocumentService.createWorkflowDocument("ProtocolDocument", GlobalVariables.getUserSession().getPerson());
            sessionDocumentService.addDocumentToUserSession(GlobalVariables.getUserSession(), workflowDocument);
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
            newDoc = new ProtocolDocument();
            newDoc.setDocumentHeader(documentHeader);
            newDoc.setDocumentNumber(documentHeader.getDocumentNumber());
        }
        catch (WorkflowException x) {
            throw new RuntimeException("Error creating new document: " + x);
        }
        
        return newDoc;
    }
    
    /**
     * @see org.kuali.kra.irb.ProtocolVersionService#versionProtocolDocument(org.kuali.kra.irb.ProtocolDocument)
     */
    public ProtocolDocument versionProtocolDocument(ProtocolDocument protocolDocument) throws Exception {
     
        materializeCollections(protocolDocument.getProtocol());
        Protocol newProtocol = versioningService.createNewVersion(protocolDocument.getProtocol());
        removeDeletedAttachment(newProtocol);
        ProtocolDocument newProtocolDocument = getNewProtocolDocument();
        newProtocolDocument.getDocumentHeader().setDocumentDescription(protocolDocument.getDocumentHeader().getDocumentDescription());
      
        copyCustomDataAttributeValues(protocolDocument, newProtocolDocument);
        fixNextValues(protocolDocument, newProtocolDocument);
        fixActionSequenceNumbers(protocolDocument.getProtocol(), newProtocol);
        
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        newProtocol.setProtocolId(nextProtocolId);

        for (ProtocolPerson person : newProtocol.getProtocolPersons()) {
            for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
                attachment.setProtocolId(newProtocol.getProtocolId());
            }
        }
        
        newProtocolDocument.setProtocol(newProtocol);
        newProtocol.setProtocolDocument(newProtocolDocument);
        protocolDocument.getProtocol().setActive(false);
        finalizeAttachmentProtocol(protocolDocument.getProtocol());
        businessObjectService.save(protocolDocument.getProtocol());
        documentService.saveDocument(newProtocolDocument);
        newProtocol.resetForeignKeys();
        finalizeAttachmentProtocol(newProtocol);
        businessObjectService.save(newProtocol);
        // versioning questionnaire answer
        List<AnswerHeader> newAnswerHeaders = questionnaireAnswerService.versioningQuestionnaireAnswer(new ProtocolModuleQuestionnaireBean(protocolDocument.getProtocol())
            , newProtocol.getSequenceNumber());
        if (newProtocol.isAmendment() || (newProtocol.isRenewal() && !newProtocol.isRenewalWithoutAmendment())) {
            ProtocolModuleQuestionnaireBean moduleBean = new ProtocolModuleQuestionnaireBean(protocolDocument.getProtocol());
            moduleBean.setModuleSubItemCode(CoeusSubModule.ZERO_SUBMODULE);
            List<AnswerHeader> newAmendAnswerHeaders = questionnaireAnswerService.versioningQuestionnaireAnswer(moduleBean
            , newProtocol.getSequenceNumber());
            if (!newAmendAnswerHeaders.isEmpty()) {
                newAnswerHeaders.addAll(newAmendAnswerHeaders);
            }
            
        }
        if (!newAnswerHeaders.isEmpty()) {
            businessObjectService.save(newAnswerHeaders);
        }
        
        return newProtocolDocument;
    }
    
    /*
     * If the deleted att exist in previous version, then it will be removed from the new version.
     * It has to be done here before the attachment is saved.  if it is done after attachment is saved, 
     * then the 'delete' will also delete 'attachmentfile'; and ojb will also deleted any attachments that
     * are referenced to this attachmentfile.
     * add a transient 'mergeAmendment' in protocol.  so , then is done only when mergeamendment and a new merged protocol is created.
     */
    private void removeDeletedAttachment(Protocol protocol) {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        List<ProtocolAttachmentProtocol> delAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("3".equals(attachment.getDocumentStatusCode())) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
                attachment.setProtocol(protocol);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                } else {
                    delAttachments.add(attachment);
                }
            }
            protocol.setAttachmentProtocols(attachments);
          //  protocol.getAttachmentProtocols().addAll(attachments);
        }
    }

    /*
     * seems that deepcopy is not really create new instance for copied obj.  this is really confusing
     */
    protected void materializeCollections(Protocol protocol) {
        checkCollection(protocol.getAttachmentProtocols());
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
    protected void checkCollection(List<? extends PersistableBusinessObject> bos) {
        if (!bos.isEmpty()) {
            bos.get(0);
        }
    }
    
    /*
     * This method is to make the document status of the attachment protocol to "finalized" 
     */
    protected void finalizeAttachmentProtocol(Protocol protocol) {
        for (ProtocolAttachmentProtocol attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("1".equals(attachment.getDocumentStatusCode())) {
                attachment.setDocumentStatusCode("2");
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
    protected void copyCustomDataAttributeValues(ProtocolDocument protocolDocument, ProtocolDocument newProtocolDocument) {
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
    protected void fixActionSequenceNumbers(Protocol protocol, Protocol newProtocol) {
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
    protected void fixNextValues(ProtocolDocument oldDoc, ProtocolDocument newDoc) {
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

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

}
