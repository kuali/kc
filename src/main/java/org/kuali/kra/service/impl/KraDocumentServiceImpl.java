/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.rules.rule.event.RouteDocumentEvent;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.service.impl.DocumentServiceImpl;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is to override documentservice.  It is mainly for CommitteeDocument.
 */
public class KraDocumentServiceImpl implements DocumentService {
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KraDocumentServiceImpl.class);
    
    private DocumentService delegateDocumentService;
    private LegacyDataAdapter legacyDataAdapter;

    @Override
    public Document validateAndPersistDocument(Document document, KualiDocumentEvent event) throws ValidationException {
        if (document == null) {
            LOG.error("document passed to validateAndPersist was null");
            throw new IllegalArgumentException("invalid (null) document");
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("validating and preparing to persist document " + document.getDocumentNumber());
        }

        document.validateBusinessRules(event);
        document.prepareForSave(event);

        // save the document
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("storing document " + document.getDocumentNumber());
            }

            if (document instanceof CommitteeDocument) {
                Committee committee = ((CommitteeDocument) document).getCommittee();
                ((CommitteeDocument) document).setCommitteeList(new ArrayList());
                getLegacyDataAdapter().save(document);
                ((CommitteeDocument) document).getCommitteeList().add(committee);
                if (event instanceof RouteDocumentEvent) {
                    getLegacyDataAdapter().save(document);
                }
            }
            // TODO remove this else if block after committee backfitting
            else if (document instanceof CommonCommitteeDocument) {
                IacucCommittee committee = ((CommonCommitteeDocument) document).getCommittee();
                ((CommonCommitteeDocument) document).setCommitteeList(new ArrayList());
                getLegacyDataAdapter().save(document);
                ((CommonCommitteeDocument) document).getCommitteeList().add(committee);
                if (event instanceof RouteDocumentEvent) {
                    getLegacyDataAdapter().save(document);
                }
            }            
            else {
                getLegacyDataAdapter().save(document);
            }
        }
        catch (OptimisticLockingFailureException e) {
            LOG.error("exception encountered on store of document " + e.getMessage());
            throw e;
        }

        document.postProcessSave(event);
        return document;
    }

    protected DocumentService getDelegateDocumentService() {
        return delegateDocumentService;
    }

    public void setDelegateDocumentService(DocumentService delegateDocumentService) {
        this.delegateDocumentService = delegateDocumentService;
    }

    public boolean documentExists(String documentHeaderId) {
        return delegateDocumentService.documentExists(documentHeaderId);
    }

    public Document getNewDocument(String documentTypeName) throws WorkflowException {
        return delegateDocumentService.getNewDocument(documentTypeName);
    }

    public Document getNewDocument(Class<? extends Document> documentClass) throws WorkflowException {
        return delegateDocumentService.getNewDocument(documentClass);
    }

    public Document getNewDocument(String documentTypeName, String initiatorPrincipalNm) throws WorkflowException {
        return delegateDocumentService.getNewDocument(documentTypeName, initiatorPrincipalNm);
    }

    public Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException {
        return delegateDocumentService.getByDocumentHeaderId(documentHeaderId);
    }

    public Document getByDocumentHeaderIdSessionless(String documentHeaderId) throws WorkflowException {
        return delegateDocumentService.getByDocumentHeaderIdSessionless(documentHeaderId);
    }

    public List<Document> getDocumentsByListOfDocumentHeaderIds(Class<? extends Document> documentClass,
            List<String> documentHeaderIds) throws WorkflowException {
        return delegateDocumentService.getDocumentsByListOfDocumentHeaderIds(documentClass, documentHeaderIds);
    }

    public Document updateDocument(Document document) {
        return delegateDocumentService.updateDocument(document);
    }

    public Document saveDocument(Document document) throws WorkflowException {
        return delegateDocumentService.saveDocument(document);
    }

    public Document saveDocument(Document document, Class<? extends KualiDocumentEvent> kualiDocumentEventClass)
            throws WorkflowException {
        return delegateDocumentService.saveDocument(document, kualiDocumentEventClass);
    }

    public Document routeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRoutingRecipients)
            throws WorkflowException {
        return delegateDocumentService.routeDocument(document, annotation, adHocRoutingRecipients);
    }

    public Document approveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRoutingRecipients)
            throws WorkflowException {
        return delegateDocumentService.approveDocument(document, annotation, adHocRoutingRecipients);
    }

    public Document superUserApproveDocument(Document document, String annotation) throws WorkflowException {
        return delegateDocumentService.superUserApproveDocument(document, annotation);
    }

    public Document superUserCancelDocument(Document document, String annotation) throws WorkflowException {
        return delegateDocumentService.superUserCancelDocument(document, annotation);
    }

    public Document superUserDisapproveDocument(Document document, String annotation) throws WorkflowException {
        return delegateDocumentService.superUserDisapproveDocument(document, annotation);
    }

    public Document superUserDisapproveDocumentWithoutSaving(Document document, String annotation) throws WorkflowException {
        return delegateDocumentService.superUserDisapproveDocumentWithoutSaving(document, annotation);
    }

    public Document disapproveDocument(Document document, String annotation) throws Exception {
        return delegateDocumentService.disapproveDocument(document, annotation);
    }

    public Document cancelDocument(Document document, String annotation) throws WorkflowException {
        return delegateDocumentService.cancelDocument(document, annotation);
    }

    public Document acknowledgeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients)
            throws WorkflowException {
        return delegateDocumentService.acknowledgeDocument(document, annotation, adHocRecipients);
    }

    public Document blanketApproveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients)
            throws WorkflowException {
        return delegateDocumentService.blanketApproveDocument(document, annotation, adHocRecipients);
    }

    public Document clearDocumentFyi(Document document, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {
        return delegateDocumentService.clearDocumentFyi(document, adHocRecipients);
    }

    public void prepareWorkflowDocument(Document document) throws WorkflowException {
        delegateDocumentService.prepareWorkflowDocument(document);
    }

    public Note createNoteFromDocument(Document document, String text) {
        return delegateDocumentService.createNoteFromDocument(document, text);
    }

    public boolean saveDocumentNotes(Document document) {
        return delegateDocumentService.saveDocumentNotes(document);
    }

    public void sendAdHocRequests(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients)
            throws WorkflowException {
        delegateDocumentService.sendAdHocRequests(document, annotation, adHocRecipients);
    }

    public void sendNoteRouteNotification(Document document, Note note, Person sender) throws WorkflowException {
        delegateDocumentService.sendNoteRouteNotification(document, note, sender);
    }

    public Document recallDocument(Document document, String annotation, boolean cancel) throws WorkflowException {
        return delegateDocumentService.recallDocument(document, annotation, cancel);
    }

    public Document completeDocument(Document document, String annotation, List adHocRecipients) throws WorkflowException {
        return delegateDocumentService.completeDocument(document, annotation, adHocRecipients);
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

}
