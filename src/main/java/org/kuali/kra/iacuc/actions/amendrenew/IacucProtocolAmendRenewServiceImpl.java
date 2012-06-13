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
package org.kuali.kra.iacuc.actions.amendrenew;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.ProtocolActionType;
import org.kuali.kra.protocol.actions.ProtocolStatus;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewServiceImpl;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolModule;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;

/**
 * The Protocol Amendment/Renewal Service Implementation.
 */
public abstract class IacucProtocolAmendRenewServiceImpl extends ProtocolAmendRenewServiceImpl {

    /**
     * Create a Protocol Action indicating that an amendment has been created.
     * @param protocol
     * @param protocolNumber protocol number of the amendment
     * @return a protocol action
     */
    @Override
    protected ProtocolAction createCreateAmendmentProtocolAction(Protocol protocol, String protocolNumber) {
        ProtocolAction protocolAction = new IacucProtocolAction((IacucProtocol)protocol, null, IacucProtocolActionType.AMENDMENT_CREATED);
        protocolAction.setComments(AMENDMENT + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }
    
    @Override
    protected void removeEditedQuestionaire(Protocol protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new IacucProtocolModuleQuestionnaireBean(protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
        if (!answerHeaders.isEmpty() && answerHeaders.get(0).getAnswerHeaderId() != null) {
            businessObjectService.delete(answerHeaders);
        }
    }

    /**
     * Create a Protocol Action indicating that a renewal has been created.
     * @param protocol
     * @param protocolNumber protocol number of the renewal
     * @return a protocol action
     */
    @Override
    protected ProtocolAction createCreateRenewalProtocolAction(Protocol protocol, String protocolNumber) {
        ProtocolAction protocolAction = new IacucProtocolAction((IacucProtocol)protocol, null, IacucProtocolActionType.RENEWAL_CREATED);
        protocolAction.setComments(RENEWAL + "-" + protocolNumber.substring(11) + ": " + CREATED);
        return protocolAction;
    }

    /**
     * @throws Exception 
     * @see org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService#getAmendmentAndRenewals(java.lang.String)
     */
    public List<Protocol> getAmendmentAndRenewals(String protocolNumber) throws Exception {
        List<Protocol> protocols = new ArrayList<Protocol>();
        protocols.addAll(getAmendments(protocolNumber));
        protocols.addAll(getRenewals(protocolNumber));
        return protocols;
    }
    
    /**
     * Get the list of all of the module type codes.
     * @return
     */
    @Override
    protected List<String> getAllModuleTypeCodes() {
        List<String> moduleTypeCodes = super.getAllModuleTypeCodes();
//TODO: Add our modules here
//      moduleTypeCodes.add(ProtocolModule.GENERAL_INFO);
        return moduleTypeCodes;
    }

    protected void addModules(Protocol protocol, ProtocolAmendmentBean amendmentBean) {
        ProtocolAmendRenewal amendmentEntry = protocol.getProtocolAmendRenewal();
        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.GENERAL_INFO));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.GENERAL_INFO);
            amendmentEntry.removeModule(ProtocolModule.GENERAL_INFO);
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.ADD_MODIFY_ATTACHMENTS);
            amendmentEntry.removeModule(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.AREAS_OF_RESEARCH));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.AREAS_OF_RESEARCH);
            amendmentEntry.removeModule(ProtocolModule.AREAS_OF_RESEARCH);
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.FUNDING_SOURCE));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.FUNDING_SOURCE);
            amendmentEntry.removeModule(ProtocolModule.FUNDING_SOURCE);
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_ORGANIZATIONS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.PROTOCOL_ORGANIZATIONS);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_ORGANIZATIONS);
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERSONNEL));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.PROTOCOL_PERSONNEL);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_PERSONNEL);
        }
        
        if (amendmentBean.getProtocolReferencesAndOtherIdentifiers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_REFERENCES));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.PROTOCOL_REFERENCES);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_REFERENCES);
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SUBJECTS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.SUBJECTS);
            amendmentEntry.removeModule(ProtocolModule.SUBJECTS);
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SPECIAL_REVIEW));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.SPECIAL_REVIEW);
            amendmentEntry.removeModule(ProtocolModule.SPECIAL_REVIEW);
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.OTHERS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.OTHERS);
            amendmentEntry.removeModule(ProtocolModule.OTHERS);
        }
        
        if (amendmentBean.getProtocolPermissions()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERMISSIONS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), ProtocolModule.PROTOCOL_PERMISSIONS);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_PERMISSIONS);
        }
        if (amendmentBean.getQuestionnaire()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.QUESTIONNAIRE));
        } else {
            // TODO : need further work for merge
            removeEditedQuestionaire(protocol);
            // protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()),
            // ProtocolModule.QUESTIONNAIRE);
            amendmentEntry.removeModule(ProtocolModule.QUESTIONNAIRE);
        }

    }


    protected abstract String getAmendmentInProgressStatusHook();
    
    protected abstract String getRenewalInProgressStatusHook();

    protected abstract String getAmendmentCreatedStatusHook();
    
    protected abstract String getRenewalCreatedStatusHook();
}
