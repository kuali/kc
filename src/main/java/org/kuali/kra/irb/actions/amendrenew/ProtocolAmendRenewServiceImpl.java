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
package org.kuali.kra.irb.actions.amendrenew;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Protocol Amendment/Renewal Service Implementation.
 */
@Transactional
public class ProtocolAmendRenewServiceImpl implements ProtocolAmendRenewService {

    private static final String AMEND_ID = "A";
    private static final String RENEW_ID = "R";
    private static final int DIGIT_COUNT = 3;
    private static final String AMEND_NEXT_VALUE = "nextAmendValue";
    private static final String RENEW_NEXT_VALUE = "nextRenewValue";
    private static final String AMENDMENT = "Amendment";
    private static final String RENEWAL = "Renewal";
    private static final String CREATED = "created";
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    
    private DocumentService documentService;
    private ProtocolCopyService protocolCopyService;
    private KraLookupDao kraLookupDao;
    
    /**
     * Set the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the Protocol Copy Service.
     * @param protocolCopyService
     */
    public void setProtocolCopyService(ProtocolCopyService protocolCopyService) {
        this.protocolCopyService = protocolCopyService;
    }
    
    /**
     * Set the KRA Lookup DAO.
     * @param kraLookupDao
     */
    public void setKraLookupDao(KraLookupDao kraLookupDao) {
        this.kraLookupDao = kraLookupDao;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createAmendment(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean)
     */
    public String createAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocument amendProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolAmendmentNumber(protocolDocument));
        amendProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.AMENDMENT_IN_PROGRESS);
        amendProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        
        ProtocolAction protocolAction = createCreateAmendmentProtocolAction(protocolDocument.getProtocol(), 
                                                             amendProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, amendProtocolDocument, amendmentBean);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createRenewal(org.kuali.kra.irb.ProtocolDocument)
     */
    public String createRenewal(ProtocolDocument protocolDocument) throws Exception {
        ProtocolDocument renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument));
        renewProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.RENEWAL_IN_PROGRESS);
        renewProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        
        ProtocolAction protocolAction = createCreateRenewalProtocolAction(protocolDocument.getProtocol(),
                                                                          renewProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        ProtocolAmendRenewal protocolAmendRenewal = createAmendmentRenewal(protocolDocument, renewProtocolDocument, null);
        renewProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        
        documentService.saveDocument(protocolDocument);
        documentService.saveDocument(renewProtocolDocument);
        
        return renewProtocolDocument.getDocumentNumber();
    }
    
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#createRenewalWithAmendment(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean)
     */
    public String createRenewalWithAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocument renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument));
        renewProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.RENEWAL_IN_PROGRESS);
        renewProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        
        ProtocolAction protocolAction = createCreateRenewalProtocolAction(protocolDocument.getProtocol(),
                                                                          renewProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, renewProtocolDocument, amendmentBean);
    }
    
    /**
     * Create an Amendment.  Adds an amendment entry into the database as well as the modules that
     * can be modified with this amendment.
     * @param protocolDocument the original protocol document to be amended
     * @param amendProtocolDocument the amended protocol document
     * @param amendmentBean the amendment bean info
     * @return
     * @throws WorkflowException 
     */
    private String createAmendment(ProtocolDocument protocolDocument, ProtocolDocument amendProtocolDocument,
                                   ProtocolAmendmentBean amendmentBean) throws WorkflowException {

        ProtocolAmendRenewal protocolAmendRenewal = createAmendmentRenewal(protocolDocument, amendProtocolDocument, amendmentBean.getSummary());
        addModules(protocolAmendRenewal, amendmentBean);
        amendProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        
        documentService.saveDocument(protocolDocument);
        documentService.saveDocument(amendProtocolDocument);
        
        return amendProtocolDocument.getDocumentNumber();
    }

    /**
     * Generate the protocol number for an amendment.  The protocol number for
     * an amendment is the original protocol's number appended with "Axxx" where
     * "xxx" is the next sequence number.  A protocol can have more than one
     * amendment.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolAmendmentNumber(ProtocolDocument protocolDocument) {
        return generateProtocolNumber(protocolDocument, AMEND_ID, AMEND_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an renewal.  The protocol number for
     * an renewal is the original protocol's number appended with "Rxxx" where
     * "xxx" is the next sequence number.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolRenewalNumber(ProtocolDocument protocolDocument) {
        return generateProtocolNumber(protocolDocument, RENEW_ID, RENEW_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an amendment or renewal.
     * @param protocolDocument
     * @return
     */
    private String generateProtocolNumber(ProtocolDocument protocolDocument, String letter, String nextValueKey) {
        String protocolNumber = protocolDocument.getProtocol().getProtocolNumber();
        Integer nextValue = protocolDocument.getDocumentNextValue(nextValueKey);
        String s = nextValue.toString();
        int length = s.length();
        for (int i = 0; i < DIGIT_COUNT - length; i++) {
            s = "0" + s;
        }
        return protocolNumber + letter + s;
    }
    
    /**
     * Create an Amendment Entry.
     * @param protocolDocument the original protocol document
     * @param amendProtocolDocument the amended protocol document
     * @param amendmentBean the user form containing the summary and modules to be amended
     * @return
     */
    private ProtocolAmendRenewal createAmendmentRenewal(ProtocolDocument protocolDocument, ProtocolDocument amendProtocolDocument, String summary) {
        ProtocolAmendRenewal protocolAmendRenewal = new ProtocolAmendRenewal();
        protocolAmendRenewal.setProtoAmendRenNumber(amendProtocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setDateCreated(new Date(System.currentTimeMillis()));
        protocolAmendRenewal.setSummary(summary);
        protocolAmendRenewal.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setProtocolId(amendProtocolDocument.getProtocol().getProtocolId());
        protocolAmendRenewal.setProtocol(amendProtocolDocument.getProtocol());
        protocolAmendRenewal.setSequenceNumber(0);
        return protocolAmendRenewal;
    }

    /**
     * Add the modules to the amendment that were selected by the end user.
     * @param amendmentEntry
     * @param amendmentBean
     */
    private void addModules(ProtocolAmendRenewal amendmentEntry, ProtocolAmendmentBean amendmentBean) {
        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.GENERAL_INFO));
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.AREAS_OF_RESEARCH));
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.FUNDING_SOURCE));
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_ORGANIZATIONS));
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERSONNEL));
        }
        
        if (amendmentBean.getProtocolReferences()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_REFERENCES));
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SUBJECTS));
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SPECIAL_REVIEW));
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.OTHERS));
        }
    }
    
    /**
     * Create a module entry.
     * @param amendmentEntry
     * @param moduleTypeCode
     * @return
     */
    private ProtocolAmendRenewModule createModule(ProtocolAmendRenewal amendmentEntry, String moduleTypeCode) {
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolAmendRenewalNumber(amendmentEntry.getProtoAmendRenNumber());
        module.setProtocolAmendRenewal(amendmentEntry);
        module.setProtocolAmendRenewalId(amendmentEntry.getId());
        module.setProtocolNumber(amendmentEntry.getProtocolNumber());
        module.setProtocolModuleTypeCode(moduleTypeCode);
        return module;
    }
    
    /**
     * Create a Protocol Action indicating that an amendment has been created.
     * @param protocol
     * @param protocolNumber protocol number of the amendment
     * @return a protocol action
     */
    private ProtocolAction createCreateAmendmentProtocolAction(Protocol protocol, String protocolNumber) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.AMENDMENT_CREATED);
        protocolAction.setComments(AMENDMENT + " " + protocolNumber.substring(11) + " " + CREATED + ".");
        return protocolAction;
    }
    
    /**
     * Create a Protocol Action indicating that a renewal has been created.
     * @param protocol
     * @param protocolNumber protocol number of the renewal
     * @return a protocol action
     */
    private ProtocolAction createCreateRenewalProtocolAction(Protocol protocol, String protocolNumber) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.RENEWAL_CREATED);
        protocolAction.setComments(RENEWAL + " " + protocolNumber.substring(11) + " " + CREATED + ".");
        return protocolAction;
    }

    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#getAmendmentAndRenewals(java.lang.String)
     */
    public List<Protocol> getAmendmentAndRenewals(String protocolNumber) {
        List<Protocol> protocols = new ArrayList<Protocol>();
        protocols.addAll(getAmendments(protocolNumber));
        protocols.addAll(getRenewals(protocolNumber));
        return protocols;
    }
    
    @SuppressWarnings("unchecked")
    private Collection<Protocol> getAmendments(String protocolNumber) {
        List<Protocol> amendments = new ArrayList<Protocol>();
        Collection<Protocol> protocols = (Collection<Protocol>) kraLookupDao.findCollectionUsingWildCard(Protocol.class, PROTOCOL_NUMBER, protocolNumber + AMEND_ID + "%", true);
        for (Protocol protocol : protocols) {
            try {
                ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                amendments.add(protocolDocument.getProtocol());
            }
            catch (WorkflowException e) {
                throw new RuntimeException(e);
            }
        }
        return amendments;
    }

    @SuppressWarnings("unchecked")
    private Collection<Protocol> getRenewals(String protocolNumber) {
        List<Protocol> renewals = new ArrayList<Protocol>();
        Collection<Protocol> protocols = (Collection<Protocol>) kraLookupDao.findCollectionUsingWildCard(Protocol.class, PROTOCOL_NUMBER, protocolNumber + RENEW_ID + "%", true);
        for (Protocol protocol : protocols) {
            try {
                ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                renewals.add(protocolDocument.getProtocol());
            }
            catch (WorkflowException e) {
                throw new RuntimeException(e);
            }
        }
        return renewals;
    }
  
    /**
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#getAvailableModules(java.lang.String)
     */
    public List<String> getAvailableModules(String protocolNumber) {
        List<String> moduleTypeCodes = getAllModuleTypeCodes();
        
        /*
         * Filter out the modules that are currently being modified by
         * outstanding amendments.
         */
        List<Protocol> protocols = getAmendmentAndRenewals(protocolNumber);
        for (Protocol protocol : protocols) {
            if (!isAmendmentCompleted(protocol)) {
                ProtocolAmendRenewal amendRenewal = protocol.getProtocolAmendRenewal();
                if (amendRenewal != null) {
                    List<ProtocolAmendRenewModule> modules = amendRenewal.getModules();
                    for (ProtocolAmendRenewModule module : modules) {
                        moduleTypeCodes.remove(module.getProtocolModuleTypeCode());
                    }
                }
            }
        }
        
        return moduleTypeCodes;
    }

    /**
     * Get the list of all of the module type codes.
     * @return
     */
    private List<String> getAllModuleTypeCodes() {
        List<String> moduleTypeCodes = new ArrayList<String>();
        moduleTypeCodes.add(ProtocolModule.GENERAL_INFO);
        moduleTypeCodes.add(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        moduleTypeCodes.add(ProtocolModule.AREAS_OF_RESEARCH);
        moduleTypeCodes.add(ProtocolModule.FUNDING_SOURCE);
        moduleTypeCodes.add(ProtocolModule.OTHERS);
        moduleTypeCodes.add(ProtocolModule.PROTOCOL_ORGANIZATIONS);
        moduleTypeCodes.add(ProtocolModule.PROTOCOL_PERSONNEL);
        moduleTypeCodes.add(ProtocolModule.PROTOCOL_REFERENCES);
        moduleTypeCodes.add(ProtocolModule.SPECIAL_REVIEW);
        moduleTypeCodes.add(ProtocolModule.SUBJECTS);
        return moduleTypeCodes;
    }

    /**
     * Has the amendment completed, e.g. been approved, disapproved, etc?
     * @param protocol
     * @return
     */
    private boolean isAmendmentCompleted(Protocol protocol) {
        KualiWorkflowDocument workflowDocument = getWorkflowDocument(protocol.getProtocolDocument());
        if (workflowDocument != null) {
            return workflowDocument.stateIsApproved() ||
                   workflowDocument.stateIsFinal() ||
                   workflowDocument.stateIsDisapproved() ||
                   workflowDocument.stateIsCanceled() ||
                   workflowDocument.stateIsException();
        }
        return false;
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    private KualiWorkflowDocument getWorkflowDocument(Document doc) {
        KualiWorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }
}
