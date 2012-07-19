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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewServiceImpl;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

/**
 * The Protocol Amendment/Renewal Service Implementation.
 */
public class IacucProtocolAmendRenewServiceImpl extends ProtocolAmendRenewServiceImpl implements IacucProtocolAmendRenewService {
    

    @Override
    protected void addModules(Protocol protocol, ProtocolAmendmentBean amendmentBean) {
        ProtocolAmendRenewal amendmentEntry = protocol.getProtocolAmendRenewal();
        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.GENERAL_INFO));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.GENERAL_INFO);
            amendmentEntry.removeModule(IacucProtocolModule.GENERAL_INFO);
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.ADD_MODIFY_ATTACHMENTS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.ADD_MODIFY_ATTACHMENTS);
            amendmentEntry.removeModule(IacucProtocolModule.ADD_MODIFY_ATTACHMENTS);
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.AREAS_OF_RESEARCH));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.AREAS_OF_RESEARCH);
            amendmentEntry.removeModule(IacucProtocolModule.AREAS_OF_RESEARCH);
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.FUNDING_SOURCE));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.FUNDING_SOURCE);
            amendmentEntry.removeModule(IacucProtocolModule.FUNDING_SOURCE);
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_ORGANIZATIONS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.PROTOCOL_ORGANIZATIONS);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_ORGANIZATIONS);
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_PERSONNEL));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.PROTOCOL_PERSONNEL);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_PERSONNEL);
        }
        
        if (amendmentBean.getProtocolReferencesAndOtherIdentifiers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_REFERENCES));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.PROTOCOL_REFERENCES);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_REFERENCES);
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.SUBJECTS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.SUBJECTS);
            amendmentEntry.removeModule(IacucProtocolModule.SUBJECTS);
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.SPECIAL_REVIEW));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.SPECIAL_REVIEW);
            amendmentEntry.removeModule(IacucProtocolModule.SPECIAL_REVIEW);
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.OTHERS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.OTHERS);
            amendmentEntry.removeModule(IacucProtocolModule.OTHERS);
        }
        
        if (amendmentBean.getProtocolPermissions()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.PROTOCOL_PERMISSIONS));
        } else {
            protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()), IacucProtocolModule.PROTOCOL_PERMISSIONS);
            amendmentEntry.removeModule(IacucProtocolModule.PROTOCOL_PERMISSIONS);
        }
        if (amendmentBean.getQuestionnaire()) {
            amendmentEntry.addModule(createModule(amendmentEntry, IacucProtocolModule.QUESTIONNAIRE));
        } else {
            // TODO : need further work for merge
            removeEditedQuestionaire(protocol);
            // protocol.merge(protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber()),
            // IacucProtocolModule.QUESTIONNAIRE);
            amendmentEntry.removeModule(IacucProtocolModule.QUESTIONNAIRE);
        }

    }

    @Override
    protected ProtocolAction getNewAmendmentProtocolActionInstanceHook(Protocol protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, IacucProtocolActionType.AMENDMENT_CREATED);
    }

    @Override
    protected ProtocolAction getNewRenewalProtocolActionInstanceHook(Protocol protocol) {
        return new IacucProtocolAction((IacucProtocol)protocol, IacucProtocolActionType.RENEWAL_CREATED);
    }

    @Override
    protected ModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean(protocol);
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
        return moduleTypeCodes;
    }

    @Override
    protected Class<? extends Protocol> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

}
