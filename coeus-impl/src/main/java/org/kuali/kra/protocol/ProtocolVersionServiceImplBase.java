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
package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
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

import java.util.*;
import java.util.Map.Entry;


/**
 * ProtocolBase Version Service Implementation.
 */
public abstract class ProtocolVersionServiceImplBase implements ProtocolVersionService {
    
    private DocumentService documentService;
    protected BusinessObjectService businessObjectService;
    protected VersioningService versioningService;
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

    protected abstract String getProtocolDocumentTypeHook();
    
    protected ProtocolDocumentBase getNewProtocolDocument() throws Exception {
        // create a new ProtocolDocumentBase
        ProtocolDocumentBase newDoc = null;
        
        // manually assembling a new ProtocolDocumentBase here because the DocumentService will deny initiator permission without context
        // we circumvent the initiator step altogether. 
        try {
            WorkflowDocument workflowDocument = workflowDocumentService.createWorkflowDocument(getProtocolDocumentTypeHook(), GlobalVariables.getUserSession().getPerson());
            sessionDocumentService.addDocumentToUserSession(GlobalVariables.getUserSession(), workflowDocument);
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
            
            newDoc = createNewProtocolDocumentInstanceHook();
            
            newDoc.setDocumentHeader(documentHeader);
            newDoc.setDocumentNumber(documentHeader.getDocumentNumber());
        }
        catch (WorkflowException x) {
            throw new RuntimeException("Error creating new document: " + x);
        }
        
        return newDoc;
    }
    
    protected abstract ProtocolDocumentBase createNewProtocolDocumentInstanceHook();

    protected abstract ProtocolBase createProtocolNewVersionHook(ProtocolBase protocol) throws Exception;
    
    protected ProtocolBase getNewProtocolVersion(ProtocolDocumentBase protocolDocument) throws Exception {
        ProtocolBase newProtocol = createProtocolNewVersionHook(protocolDocument.getProtocol());
        if(newProtocol.getProtocolId() == null) {
            setNewProtocolId(newProtocol);
        }
        return newProtocol;
    }
    
    @Override
    public ProtocolDocumentBase versionProtocolDocument(ProtocolDocumentBase protocolDocument) throws Exception {
        materializeCollections(protocolDocument.getProtocol());
        ProtocolBase newProtocol = getNewProtocolVersion(protocolDocument);
        removeDeletedAttachment(newProtocol);
        ProtocolDocumentBase newProtocolDocument = getNewProtocolDocument();
        newProtocolDocument.getDocumentHeader().setDocumentDescription(protocolDocument.getDocumentHeader().getDocumentDescription());
      
        copyCustomDataAttributeValues(protocolDocument, newProtocolDocument);
        fixNextValues(protocolDocument, newProtocolDocument);
        fixActionSequenceNumbers(protocolDocument.getProtocol(), newProtocol);
        
        
        for (ProtocolPersonBase person : newProtocol.getProtocolPersons()) {
            for (ProtocolAttachmentPersonnelBase attachment : person.getAttachmentPersonnels()) {
                attachment.setProtocolId(newProtocol.getProtocolId());
            }
        }
        
        newProtocolDocument.setProtocol(newProtocol);
        newProtocol.setProtocolDocument(newProtocolDocument);
        protocolDocument.getProtocol().setActive(false);
        
        if (!(!protocolDocument.getProtocol().isNew() && !newProtocol.isNew())) {
            finalizeAttachmentProtocol(protocolDocument.getProtocol());
        }
        
        newProtocol.resetPersistenceStateForNotifications();
        businessObjectService.save(protocolDocument.getProtocol());        
        documentService.saveDocument(newProtocolDocument);
        newProtocol.resetForeignKeys();

        if (!(!protocolDocument.getProtocol().isNew() && !newProtocol.isNew())) {
            finalizeAttachmentProtocol(protocolDocument.getProtocol());
        }
        
        businessObjectService.save(newProtocol);
        // versioning questionnaire answer
        List<AnswerHeader> newAnswerHeaders = questionnaireAnswerService.versioningQuestionnaireAnswer(getNewInstanceProtocolModuleQuestionnaireBeanHook(protocolDocument.getProtocol())
            , newProtocol.getSequenceNumber());
        if (newProtocol.isAmendment() || newProtocol.isRenewalWithAmendment()) {
            ProtocolModuleQuestionnaireBeanBase moduleBean = getNewInstanceProtocolModuleQuestionnaireBeanHook(protocolDocument.getProtocol());
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
        newProtocol.reconcileActionsWithSubmissions();
        businessObjectService.save(newProtocol.getProtocolActions());
        
        return newProtocolDocument;
    }
    
    protected void setNewProtocolId(ProtocolBase newProtocol) {
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber(getProtocolSequenceIdHook(), newProtocol.getClass());
        newProtocol.setProtocolId(nextProtocolId);
    }
    
    protected abstract String getProtocolSequenceIdHook();

    /*
     * If the deleted att exist in previous version, then it will be removed from the new version.
     * It has to be done here before the attachment is saved.  if it is done after attachment is saved, 
     * then the 'delete' will also delete 'attachmentfile'; and ojb will also deleted any attachments that
     * are referenced to this attachmentfile.
     * add a transient 'mergeAmendment' in protocol.  so , then is done only when mergeamendment and a new merged protocol is created.
     */
    private void removeDeletedAttachment(ProtocolBase protocol) {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocolBase> attachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        List<ProtocolAttachmentProtocolBase> delAttachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
            attachment.setProtocol(protocol);
            if ("3".equals(attachment.getDocumentStatusCode())) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
                attachment.setProtocol(protocol);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                } else {
                    delAttachments.add(attachment);
                }
            }
            protocol.setAttachmentProtocols(attachments);
        }
    }

    /*
     * seems that deepcopy is not really create new instance for copied obj.  this is really confusing
     */
    protected void materializeCollections(ProtocolBase protocol) {
        checkCollection(protocol.getAttachmentProtocols());
        checkCollection(protocol.getProtocolLocations());
        checkCollection(protocol.getProtocolAmendRenewals());
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
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
    protected void finalizeAttachmentProtocol(ProtocolBase protocol) {
        for (ProtocolAttachmentProtocolBase attachment : protocol.getAttachmentProtocols()) {
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
    protected void copyCustomDataAttributeValues(ProtocolDocumentBase protocolDocument, ProtocolDocumentBase newProtocolDocument) {
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
     * This is incorrect for ProtocolBase Actions.  The original sequence
     * numbers must be maintained.  Therefore, they are restored from
     * the original protocol.
     * @param protocol
     * @param newProtocol
     */
    protected void fixActionSequenceNumbers(ProtocolBase protocol, ProtocolBase newProtocol) {
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
    protected void fixNextValues(ProtocolDocumentBase oldDoc, ProtocolDocumentBase newDoc) {
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

   
    @Override
    @SuppressWarnings("unchecked")
    public ProtocolBase getProtocolVersion(String protocolNumber, Integer sequenceNumber) {
        ProtocolBase protocol = null;
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("protocolNumber", protocolNumber);
        fields.put("sequenceNumber", sequenceNumber);
        Collection<ProtocolBase> protocols = (Collection<ProtocolBase>)businessObjectService.findMatching(getProtocolBOClassHook(), fields);
        if (protocols.size() == 1) {
            protocol = protocols.iterator().next();
        }
        return protocol;
    }

    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    protected abstract ProtocolModuleQuestionnaireBeanBase getNewInstanceProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol);

}
