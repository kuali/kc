/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol.actions.amendrenew;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * The ProtocolBase Amendment/Renewal Service Implementation.
 */

public abstract class ProtocolAmendRenewServiceImplBase implements ProtocolAmendRenewService {

    private static Log LOGGER = LogFactory.getLog(ProtocolAmendRenewServiceImplBase.class);
    protected static final String AMEND_ID = "A";
    protected static final String RENEW_ID = "R";
    protected static final int DIGIT_COUNT = 3;
    protected static final String AMEND_NEXT_VALUE = "nextAmendValue";
    protected static final String RENEW_NEXT_VALUE = "nextRenewValue";
    protected static final String AMENDMENT = "Amendment";
    protected static final String RENEWAL = "Renewal";
    protected static final String CREATED = "Created";
    protected static final String PROTOCOL_NUMBER = "protocolNumber";
    protected static final String PROTOCOL_STATUS = "protocolStatus";
    protected static final String PROTOCOL_ID = "protocolId";

    protected DocumentService documentService;
    protected ProtocolCopyService protocolCopyService;
    protected KraLookupDao kraLookupDao;
    protected ProtocolFinderDao protocolFinderDao;

    protected QuestionnaireAnswerService questionnaireAnswerService;
    protected BusinessObjectService businessObjectService;

    private LoadingCache<String,List<ProtocolBase>> amendmentAndRenewalsCache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build(new CacheLoader<String, List<ProtocolBase>>() {
                @Override
                public List<ProtocolBase> load(String protocolNumber) throws Exception {
                    return Collections.unmodifiableList(new ArrayList<ProtocolBase>(kraLookupDao.findCollectionUsingWildCardWithSorting(getProtocolBOClassHook(), PROTOCOL_NUMBER, protocolNumber + "_%", PROTOCOL_ID, true, true)));
                }
            });

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolCopyService(ProtocolCopyService protocolCopyService) {
        this.protocolCopyService = protocolCopyService;
    }

    public void setKraLookupDao(KraLookupDao kraLookupDao) {
        this.kraLookupDao = kraLookupDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
    @Override
    public String createAmendment(ProtocolDocumentBase protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocumentBase amendProtocolDocument = null;
        try {
            //since the user probably doesn't have permission to create the document, we are going to add session variable so the document
            //authorizer knows to approve the user for initiating the document
            GlobalVariables.getUserSession().addObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT, Boolean.TRUE);
            amendProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolAmendmentNumber(protocolDocument), true);
        } finally {
            GlobalVariables.getUserSession().removeObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT);
        }
        amendProtocolDocument.getProtocol().setInitialSubmissionDate(protocolDocument.getProtocol().getInitialSubmissionDate());
        amendProtocolDocument.getProtocol().setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        amendProtocolDocument.getProtocol().setExpirationDate(protocolDocument.getProtocol().getExpirationDate());
        amendProtocolDocument.getProtocol().setLastApprovalDate(protocolDocument.getProtocol().getLastApprovalDate());
        amendProtocolDocument.getProtocol().setProtocolStatusCode(getAmendmentInProgressStatusHook());
        amendProtocolDocument.getProtocol().refreshReferenceObject(PROTOCOL_STATUS);
       
        markProtocolAttachmentsAsFinalized(amendProtocolDocument.getProtocol().getAttachmentProtocols());
        
        ProtocolActionBase protocolAction = createCreateAmendmentProtocolAction(protocolDocument.getProtocol(), 
                                                             amendProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, amendProtocolDocument, amendmentBean);
    }
    
    /**
     * This method marks all protocol attachment as finalized.
     */
    protected void markProtocolAttachmentsAsFinalized(List<ProtocolAttachmentProtocolBase> attachmentProtocols) {
        for (ProtocolAttachmentProtocolBase protocolAttachment : attachmentProtocols) {
            if ("1".equals(protocolAttachment.getDocumentStatusCode())) {
                protocolAttachment.setDocumentStatusCode("2");
            }
        }
        
    }

    @Override
    public String createRenewal(ProtocolDocumentBase protocolDocument, String renewalSummary) throws Exception {
        ProtocolDocumentBase renewProtocolDocument = null;
        try {
            //since the user probably doesn't have permission to create the document, we are going to add session variable so the document
            //authorizer knows to approve the user for initiating the document
            GlobalVariables.getUserSession().addObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT, Boolean.TRUE);
            renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument), true);
        } finally {
            GlobalVariables.getUserSession().removeObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT);
        }
        renewProtocolDocument.getProtocol().setInitialSubmissionDate(protocolDocument.getProtocol().getInitialSubmissionDate());
        renewProtocolDocument.getProtocol().setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        renewProtocolDocument.getProtocol().setExpirationDate(protocolDocument.getProtocol().getExpirationDate());
        renewProtocolDocument.getProtocol().setLastApprovalDate(protocolDocument.getProtocol().getLastApprovalDate());
        renewProtocolDocument.getProtocol().setProtocolStatusCode(getRenewalInProgressStatusHook());
        renewProtocolDocument.getProtocol().refreshReferenceObject(PROTOCOL_STATUS);
        
        markProtocolAttachmentsAsFinalized(renewProtocolDocument.getProtocol().getAttachmentProtocols());

        ProtocolActionBase protocolAction = createCreateRenewalProtocolAction(protocolDocument.getProtocol(),
                                                                          renewProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        ProtocolAmendRenewalBase protocolAmendRenewal = createAmendmentRenewal(protocolDocument, renewProtocolDocument, renewalSummary);
        renewProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        documentService.saveDocument(protocolDocument);
        documentService.saveDocument(renewProtocolDocument);
        
        return renewProtocolDocument.getDocumentNumber();
    }
    
    @Override
    public String createRenewalWithAmendment(ProtocolDocumentBase protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        ProtocolDocumentBase renewProtocolDocument = null;
        try {
            //since the user probably doesn't have permission to create the document, we are going to add session variable so the document
            //authorizer knows to approve the user for initiating the document
            GlobalVariables.getUserSession().addObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT, Boolean.TRUE);
            renewProtocolDocument = protocolCopyService.copyProtocol(protocolDocument, generateProtocolRenewalNumber(protocolDocument), true);
        } finally {
            GlobalVariables.getUserSession().removeObject(AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT);
        }
        renewProtocolDocument.getProtocol().setInitialSubmissionDate(protocolDocument.getProtocol().getInitialSubmissionDate());
        renewProtocolDocument.getProtocol().setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        renewProtocolDocument.getProtocol().setExpirationDate(protocolDocument.getProtocol().getExpirationDate());
        renewProtocolDocument.getProtocol().setLastApprovalDate(protocolDocument.getProtocol().getLastApprovalDate());
        renewProtocolDocument.getProtocol().setProtocolStatusCode(getRenewalInProgressStatusHook());
        renewProtocolDocument.getProtocol().refreshReferenceObject(PROTOCOL_STATUS);
        
        markProtocolAttachmentsAsFinalized(renewProtocolDocument.getProtocol().getAttachmentProtocols());

        ProtocolActionBase protocolAction = createCreateRenewalWithAmendmentProtocolAction(protocolDocument.getProtocol(),
                                                                          renewProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, renewProtocolDocument, amendmentBean);
    }

    public void updateAmendmentRenewal(ProtocolDocumentBase protocolDocument, ProtocolAmendmentBean amendmentBean) throws WorkflowException {
        protocolDocument.getProtocol().getProtocolAmendRenewal().setSummary(amendmentBean.getSummary());
        protocolDocument.getProtocol().getProtocolAmendRenewal().setModules(new ArrayList<ProtocolAmendRenewModuleBase>());
        addModules(protocolDocument.getProtocol(), amendmentBean);
    }
    
    /**
     * Create an Amendment.  Adds an amendment entry into the database as well as the modules that
     * can be modified with this amendment.
     * @param protocolDocument the original protocol document to be amended
     * @param amendProtocolDocument the amended protocol document
     * @param amendmentBean the amendment bean info
     */
    protected String createAmendment(ProtocolDocumentBase protocolDocument, ProtocolDocumentBase amendProtocolDocument,
                                   ProtocolAmendmentBean amendmentBean) throws WorkflowException {

        ProtocolAmendRenewalBase protocolAmendRenewal = createAmendmentRenewal(protocolDocument, amendProtocolDocument, amendmentBean.getSummary());
        amendProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        addModules(amendProtocolDocument.getProtocol(), amendmentBean);
        documentService.saveDocument(protocolDocument);
        documentService.saveDocument(amendProtocolDocument);
        
        return amendProtocolDocument.getDocumentNumber();
    }

    /**
     * Generate the protocol number for an amendment.  The protocol number for
     * an amendment is the original protocol's number appended with "Axxx" where
     * "xxx" is the next sequence number.  A protocol can have more than one
     * amendment.
     */
    protected String generateProtocolAmendmentNumber(ProtocolDocumentBase protocolDocument) {
        return generateProtocolNumber(protocolDocument, AMEND_ID, AMEND_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an renewal.  The protocol number for
     * an renewal is the original protocol's number appended with "Rxxx" where
     * "xxx" is the next sequence number.
     */
    protected String generateProtocolRenewalNumber(ProtocolDocumentBase protocolDocument) {
        return generateProtocolNumber(protocolDocument, RENEW_ID, RENEW_NEXT_VALUE);
    }
    
    /**
     * Generate the protocol number for an amendment or renewal.
     */
    protected String generateProtocolNumber(ProtocolDocumentBase protocolDocument, String letter, String nextValueKey) {
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
     * @param summary the summary to be amended
     */
    protected ProtocolAmendRenewalBase createAmendmentRenewal(ProtocolDocumentBase protocolDocument, ProtocolDocumentBase amendProtocolDocument, String summary) {
        ProtocolAmendRenewalBase protocolAmendRenewal = getNewProtocolAmendRenewalInstanceHook();
        protocolAmendRenewal.setProtoAmendRenNumber(amendProtocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setDateCreated(new Date(System.currentTimeMillis()));
        protocolAmendRenewal.setSummary(summary);
        protocolAmendRenewal.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        protocolAmendRenewal.setProtocolId(amendProtocolDocument.getProtocol().getProtocolId());
        protocolAmendRenewal.setProtocol(amendProtocolDocument.getProtocol());
        protocolAmendRenewal.setSequenceNumber(0);
        return protocolAmendRenewal;
    }

    protected void removeEditedQuestionaire(ProtocolBase protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = getNewProtocolModuleQuestionnaireBeanInstanceHook(protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
        if (!answerHeaders.isEmpty() && answerHeaders.get(0).getId() != null) {
            businessObjectService.delete(answerHeaders);
        }
    }

    /**
     * Create a module entry.
     */
    protected ProtocolAmendRenewModuleBase createModule(ProtocolAmendRenewalBase amendmentEntry, String moduleTypeCode) {
        ProtocolAmendRenewModuleBase module = getNewProtocolAmendRenewModuleInstanceHook();
        module.setProtocolAmendRenewalNumber(amendmentEntry.getProtoAmendRenNumber());
        module.setProtocolAmendRenewal(amendmentEntry);
        module.setProtocolAmendRenewalId(amendmentEntry.getId());
        module.setProtocolNumber(amendmentEntry.getProtocolNumber());
        module.setProtocolModuleTypeCode(moduleTypeCode);
        return module;
    }
    
    /**
     * Create a ProtocolBase Action indicating that an amendment has been created.
     * @param protocolNumber protocol number of the amendment
     * @return a protocol action
     */
    protected ProtocolActionBase createCreateAmendmentProtocolAction(ProtocolBase protocol, String protocolNumber) {
        ProtocolActionBase protocolAction = getNewAmendmentProtocolActionInstanceHook(protocol);
        protocolAction.setComments(AMENDMENT + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }
    
    
    
    /**
     * Create a ProtocolBase Action indicating that a renewal has been created.
     * @param protocolNumber protocol number of the renewal
     * @return a protocol action
     */
    protected ProtocolActionBase createCreateRenewalProtocolAction(ProtocolBase protocol, String protocolNumber) {
        ProtocolActionBase protocolAction = getNewRenewalProtocolActionInstanceHook(protocol);
        protocolAction.setComments(RENEWAL + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }

    /**
     * Create a ProtocolBase Action indicating that a renewal with amendment has been created.
     * @param protocolNumber protocol number of the renewal
     * @return a protocol action
     */
    protected ProtocolActionBase createCreateRenewalWithAmendmentProtocolAction(ProtocolBase protocol, String protocolNumber) {
        ProtocolActionBase protocolAction = getNewRenewalWithAmendmentProtocolActionInstanceHook(protocol);
        protocolAction.setComments(RENEWAL + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }

    @Override
    public List<ProtocolBase> getAmendmentAndRenewals(String protocolNumber)  throws Exception {
        try {
            return amendmentAndRenewalsCache.get(protocolNumber);
        } catch (ExecutionException e) {
            LOGGER.error("amendmentAndRenewalsCache for fetching amendment renewals did not execute properly. Trying to fetch it from database with DAO",e);
            return new ArrayList<ProtocolBase>(kraLookupDao.findCollectionUsingWildCardWithSorting(getProtocolBOClassHook(), PROTOCOL_NUMBER, protocolNumber + "_%", PROTOCOL_ID, true, true));
        }
    }

    @SuppressWarnings("unchecked")
    public Collection<ProtocolBase> getAmendments(String protocolNumber) throws Exception {
        return new ArrayList<>( kraLookupDao.findCollectionUsingWildCard(getProtocolBOClassHook(), PROTOCOL_NUMBER, protocolNumber + AMEND_ID + "%", true));
    }

    @SuppressWarnings("unchecked")
    public Collection<ProtocolBase> getRenewals(String protocolNumber) throws Exception {
        return new ArrayList<>( kraLookupDao.findCollectionUsingWildCard(getProtocolBOClassHook(), PROTOCOL_NUMBER, protocolNumber + RENEW_ID + "%", true));
    }
  
    /**
     * @throws Exception 
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#getAvailableModules(java.lang.String)
     */
    public List<String> getAvailableModules(String protocolNumber) throws Exception {
        List<String> moduleTypeCodes = getAllModuleTypeCodes();
        
        /*
         * Filter out the modules that are currently being modified by
         * outstanding amendments.
         */
        List<ProtocolBase> protocols = getAmendmentAndRenewals(protocolNumber);
        for (ProtocolBase protocol : protocols) {
            if (!isAmendmentCompleted(protocol)) {
                ProtocolAmendRenewalBase amendRenewal = protocol.getProtocolAmendRenewal();
                if (amendRenewal != null) {
                    List<ProtocolAmendRenewModuleBase> modules = amendRenewal.getModules();
                    for (ProtocolAmendRenewModuleBase module : modules) {
                        moduleTypeCodes.remove(module.getProtocolModuleTypeCode());
                    }
                }
            }
        }
        
        return moduleTypeCodes;
    }

    public String getAmendedOrRenewalProtocolNumber(String protocolNumber) {
        if (protocolNumber.contains(AMEND_ID)) {
            return StringUtils.substringBefore(protocolNumber, AMEND_ID);
        } else if (protocolNumber.contains(RENEW_ID)) {
            return StringUtils.substringBefore(protocolNumber, RENEW_ID);
        } else {
            return protocolNumber;
        }
    }
    
    /**
     * Has the amendment completed, e.g. been approved, disapproved, etc?
     */
    protected boolean isAmendmentCompleted(ProtocolBase protocol) {
        WorkflowDocument workflowDocument = getWorkflowDocument(protocol.getProtocolDocument());
        if (workflowDocument != null) {
            return workflowDocument.isApproved() ||
                   workflowDocument.isFinal() ||
                   workflowDocument.isDisapproved() ||
                   workflowDocument.isCanceled() ||
                   workflowDocument.isException();
        }
        return false;
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
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

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected abstract ProtocolActionBase getNewAmendmentProtocolActionInstanceHook(ProtocolBase protocol);
    
    protected abstract ProtocolActionBase getNewRenewalProtocolActionInstanceHook(ProtocolBase protocol);

    protected abstract ProtocolActionBase getNewRenewalWithAmendmentProtocolActionInstanceHook(ProtocolBase protocol);

    protected abstract ModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(ProtocolBase protocol);
    
    protected abstract String getAmendmentInProgressStatusHook();
    
    protected abstract String getRenewalInProgressStatusHook();

    protected abstract List<String> getAllModuleTypeCodes();
    
    protected abstract void addModules(ProtocolBase protocol, ProtocolAmendmentBean amendmentBean);
    
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    
    protected abstract ProtocolAmendRenewalBase getNewProtocolAmendRenewalInstanceHook();
    
    protected abstract ProtocolAmendRenewModuleBase getNewProtocolAmendRenewModuleInstanceHook();

    public ProtocolCopyService<ProtocolDocumentBase> getProtocolCopyService() {
        return protocolCopyService;
    }
}
