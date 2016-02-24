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
package org.kuali.kra.iacuc.actions.amendrenew;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewServiceImplBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * The ProtocolBase Amendment/Renewal Service Implementation.
 */
public class IacucProtocolAmendRenewServiceImpl extends ProtocolAmendRenewServiceImplBase implements IacucProtocolAmendRenewService {
    protected static final String CONTINUATION_ID = "C";
    protected static final String CONTINUATION_NEXT_VALUE = "nextContinuationValue";
    protected static final String CONTINUATION = "Continuation";
    

    @Override
    protected void addModules(ProtocolBase protocol, ProtocolAmendmentBean amendmentBean) {
        ProtocolAmendRenewalBase amendmentEntry = protocol.getProtocolAmendRenewal();
        final ProtocolBase currentProtocolByNumber = protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber());

        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.GENERAL_INFO));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.GENERAL_INFO);
            amendmentEntry.removeModule(IacucProtocolModule.GENERAL_INFO);
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.ADD_MODIFY_ATTACHMENTS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.ADD_MODIFY_ATTACHMENTS);
            amendmentEntry.removeModule(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS);
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.AREAS_OF_RESEARCH));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.AREAS_OF_RESEARCH);
            amendmentEntry.removeModule(IacucProtocolModule.AREAS_OF_RESEARCH);
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.FUNDING_SOURCE));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.FUNDING_SOURCE);
            amendmentEntry.removeModule(IacucProtocolModule.FUNDING_SOURCE);
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_ORGANIZATIONS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.PROTOCOL_ORGANIZATIONS);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_ORGANIZATIONS);
        }
        
        if (amendmentBean.getProtocolReferencesAndOtherIdentifiers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_REFERENCES));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.PROTOCOL_REFERENCES);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_REFERENCES);
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.SUBJECTS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.SUBJECTS);
            amendmentEntry.removeModule(IacucProtocolModule.SUBJECTS);
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.SPECIAL_REVIEW));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.SPECIAL_REVIEW);
            amendmentEntry.removeModule(IacucProtocolModule.SPECIAL_REVIEW);
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.OTHERS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.OTHERS);
            amendmentEntry.removeModule(IacucProtocolModule.OTHERS);
        }
        
        if (amendmentBean.getProtocolPermissions()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_PERMISSIONS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.PROTOCOL_PERMISSIONS);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_PERMISSIONS);
        }
        if (amendmentBean.getQuestionnaire()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.QUESTIONNAIRE));
        } else {
            removeEditedQuestionaire(protocol);
            amendmentEntry.removeModule(IacucProtocolModule.QUESTIONNAIRE);
        }
        if (((IacucProtocolAmendmentBean)amendmentBean).getThreers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.THREE_RS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.THREE_RS);
            amendmentEntry.removeModule(IacucProtocolModule.THREE_RS);
        }
        if (((IacucProtocolAmendmentBean)amendmentBean).getSpeciesAndGroups()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.SPECIES_GROUPS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.SPECIES_GROUPS);
            amendmentEntry.removeModule(IacucProtocolModule.SPECIES_GROUPS);
        }
        if (((IacucProtocolAmendmentBean)amendmentBean).getProcedures()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROCEDURES));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.PROCEDURES);
            amendmentEntry.removeModule(IacucProtocolModule.PROCEDURES);
        }
        if (((IacucProtocolAmendmentBean)amendmentBean).getProtocolExceptions()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.EXCEPTIONS));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.EXCEPTIONS);
            amendmentEntry.removeModule(IacucProtocolModule.EXCEPTIONS);
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_PERSONNEL));
        } else {
            protocol.merge(currentProtocolByNumber, IacucProtocolModule.PROTOCOL_PERSONNEL);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_PERSONNEL);
        }
    }

    @Override
    protected ProtocolActionBase getNewAmendmentProtocolActionInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, IacucProtocolActionType.AMENDMENT_CREATED);
    }

    @Override
    protected ProtocolActionBase getNewRenewalProtocolActionInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, IacucProtocolActionType.RENEWAL_CREATED);
    }

    @Override
    protected ProtocolActionBase getNewRenewalWithAmendmentProtocolActionInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, IacucProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED);
    }

    @Override
    protected ModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
    }

    @Override
    protected String getAmendmentInProgressStatusHook() {
        return IacucProtocolStatus.AMENDMENT_IN_PROGRESS;
    }

    @Override
    protected String getRenewalInProgressStatusHook() {
        return IacucProtocolStatus.RENEWAL_IN_PROGRESS;
    }

    protected List<String> getAllModuleTypeCodes() {
        List<String> moduleTypeCodes = new ArrayList<String>();
        moduleTypeCodes.add(IacucProtocolModule.GENERAL_INFO);
        moduleTypeCodes.add(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS);
        moduleTypeCodes.add(IacucProtocolModule.AREAS_OF_RESEARCH);
        moduleTypeCodes.add(IacucProtocolModule.FUNDING_SOURCE);
        moduleTypeCodes.add(IacucProtocolModule.OTHERS);
        moduleTypeCodes.add(IacucProtocolModule.PROTOCOL_ORGANIZATIONS);
        moduleTypeCodes.add(IacucProtocolModule.PROTOCOL_PERSONNEL);
        moduleTypeCodes.add(IacucProtocolModule.PROTOCOL_REFERENCES);
        moduleTypeCodes.add(IacucProtocolModule.SPECIAL_REVIEW);
        moduleTypeCodes.add(IacucProtocolModule.SUBJECTS);
        moduleTypeCodes.add(IacucProtocolModule.PROTOCOL_PERMISSIONS);
        moduleTypeCodes.add(IacucProtocolModule.QUESTIONNAIRE);
        moduleTypeCodes.add(IacucProtocolModule.THREE_RS);
        moduleTypeCodes.add(IacucProtocolModule.SPECIES_GROUPS);
        moduleTypeCodes.add(IacucProtocolModule.PROCEDURES);
        moduleTypeCodes.add(IacucProtocolModule.EXCEPTIONS);
        return moduleTypeCodes;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected ProtocolAmendRenewalBase getNewProtocolAmendRenewalInstanceHook() {
        return new IacucProtocolAmendRenewal();
    }

    @Override
    protected ProtocolAmendRenewModuleBase getNewProtocolAmendRenewModuleInstanceHook() {
        return new IacucProtocolAmendRenewModule();
    }

    public String createContinuation (IacucProtocolDocument protocolDocument, String continuationSummary) throws Exception {
        IacucProtocolDocument continuationProtocolDocument = null;
        try {
            //since the user probably doesn't have permission to create the document, we are going to add session variable so the document
            //authorizer knows to approve the user for initiating the document
            GlobalVariables.getUserSession().addObject(AMEND_RENEW_CONTINUATION_ALLOW_NEW_PROTOCOL_DOCUMENT, Boolean.TRUE);
            continuationProtocolDocument = (IacucProtocolDocument)getProtocolCopyService().copyProtocol(protocolDocument, generateProtocolContinuationNumber(protocolDocument), true);
        } finally {
            GlobalVariables.getUserSession().removeObject(AMEND_RENEW_CONTINUATION_ALLOW_NEW_PROTOCOL_DOCUMENT);
        }
        continuationProtocolDocument.getProtocol().setInitialSubmissionDate(protocolDocument.getProtocol().getInitialSubmissionDate());
        continuationProtocolDocument.getProtocol().setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        continuationProtocolDocument.getProtocol().setExpirationDate(protocolDocument.getProtocol().getExpirationDate());
        continuationProtocolDocument.getProtocol().setLastApprovalDate(protocolDocument.getProtocol().getLastApprovalDate());
        continuationProtocolDocument.getProtocol().setProtocolStatusCode(IacucProtocolStatus.CONTINUATION_IN_PROGRESS);
        continuationProtocolDocument.getProtocol().refreshReferenceObject(PROTOCOL_STATUS);
        
        markProtocolAttachmentsAsFinalized(continuationProtocolDocument.getProtocol().getAttachmentProtocols());

        IacucProtocolAction protocolAction = createCreateContinuationProtocolAction(protocolDocument.getIacucProtocol(),
                continuationProtocolDocument.getIacucProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        // attributes are same for continuation. Let us use the same amendrenewal object here.
        ProtocolAmendRenewalBase protocolAmendRenewal = createAmendmentRenewal(protocolDocument, continuationProtocolDocument, continuationSummary);
        continuationProtocolDocument.getProtocol().setProtocolAmendRenewal(protocolAmendRenewal);
        documentService.saveDocument(protocolDocument);
        documentService.saveDocument(continuationProtocolDocument);
        
        return continuationProtocolDocument.getDocumentNumber();
    }

    public String createContinuationWithAmendment(IacucProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception {
        IacucProtocolDocument continuationProtocolDocument = null;
        try {
            //since the user probably doesn't have permission to create the document, we are going to add session variable so the document
            //authorizer knows to approve the user for initiating the document
            GlobalVariables.getUserSession().addObject(AMEND_RENEW_CONTINUATION_ALLOW_NEW_PROTOCOL_DOCUMENT, Boolean.TRUE);
            continuationProtocolDocument = (IacucProtocolDocument)getProtocolCopyService().copyProtocol(protocolDocument, generateProtocolContinuationNumber(protocolDocument), true);
        } finally {
            GlobalVariables.getUserSession().removeObject(AMEND_RENEW_CONTINUATION_ALLOW_NEW_PROTOCOL_DOCUMENT);
        }
        continuationProtocolDocument.getProtocol().setInitialSubmissionDate(protocolDocument.getProtocol().getInitialSubmissionDate());
        continuationProtocolDocument.getProtocol().setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        continuationProtocolDocument.getProtocol().setExpirationDate(protocolDocument.getProtocol().getExpirationDate());
        continuationProtocolDocument.getProtocol().setLastApprovalDate(protocolDocument.getProtocol().getLastApprovalDate());
        continuationProtocolDocument.getProtocol().setProtocolStatusCode(IacucProtocolStatus.CONTINUATION_IN_PROGRESS);
        continuationProtocolDocument.getProtocol().refreshReferenceObject(PROTOCOL_STATUS);
        
        markProtocolAttachmentsAsFinalized(continuationProtocolDocument.getProtocol().getAttachmentProtocols());

        IacucProtocolAction protocolAction = createCreateContinuationProtocolAction(protocolDocument.getIacucProtocol(),
                continuationProtocolDocument.getProtocol().getProtocolNumber());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);
        
        return createAmendment(protocolDocument, continuationProtocolDocument, amendmentBean);
    }
    
    /**
     * Generate the protocol number for an continuation.  The protocol number for
     * continuation is the original protocol's number appended with "Cxxx" where
     * "xxx" is the next sequence number.
     * @param protocolDocument
     * @return
     */
    protected String generateProtocolContinuationNumber(IacucProtocolDocument protocolDocument) {
        return generateProtocolNumber(protocolDocument, CONTINUATION_ID, CONTINUATION_NEXT_VALUE);
    }

    /**
     * Create a ProtocolBase Action indicating that a renewal has been created.
     * @param protocol
     * @param protocolNumber protocol number of the renewal
     * @return a protocol action
     */
    protected IacucProtocolAction createCreateContinuationProtocolAction(IacucProtocol protocol, String protocolNumber) {
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, IacucProtocolActionType.CONTINUATION); 
        protocolAction.setComments(CONTINUATION + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }
    
    @SuppressWarnings("unchecked")
    public Collection<IacucProtocol> getContinuations(String protocolNumber) throws Exception {
        List<IacucProtocol> continuations = new ArrayList<IacucProtocol>();
        Collection<IacucProtocol> protocols = (Collection<IacucProtocol>) kraLookupDao.findCollectionUsingWildCard(IacucProtocol.class, PROTOCOL_NUMBER, protocolNumber + CONTINUATION_ID + "%", true);
        for (ProtocolBase protocol : protocols) {
            IacucProtocolDocument protocolDocument = (IacucProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
            continuations.add(protocolDocument.getIacucProtocol());
        }
        return continuations;
    }

    @Override
    public List<ProtocolBase> getAmendmentAndRenewals(String protocolNumber) throws Exception {
        List<ProtocolBase> protocols = super.getAmendmentAndRenewals(protocolNumber);
        // let us add continuations (continuation is same as renewal)
        protocols.addAll(getContinuations(protocolNumber));
        return protocols;
    }
    
}
