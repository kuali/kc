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
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewServiceImplBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * The Protocol Amendment/Renewal Service Implementation.
 */
public class ProtocolAmendRenewServiceImpl extends ProtocolAmendRenewServiceImplBase implements ProtocolAmendRenewService {

    @Override
    protected ProtocolActionBase getNewAmendmentProtocolActionInstanceHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, ProtocolActionType.AMENDMENT_CREATED);
    }

    @Override
    protected ProtocolActionBase getNewRenewalProtocolActionInstanceHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, ProtocolActionType.RENEWAL_CREATED);
    }

    @Override
    protected ProtocolActionBase getNewRenewalWithAmendmentProtocolActionInstanceHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED);
    }

    @Override
    protected ProtocolActionBase getNewFyiProtocolActionInstanceHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, ProtocolActionType.NOTIFY_IRB);
    }

    @Override
    protected ModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(ProtocolBase protocol) {
        return new ProtocolModuleQuestionnaireBean((Protocol) protocol);
    }

    @Override
    protected String getAmendmentInProgressStatusHook() {
        return ProtocolStatus.AMENDMENT_IN_PROGRESS;
    }

    @Override
    protected String getRenewalInProgressStatusHook() {
        return ProtocolStatus.RENEWAL_IN_PROGRESS;
    }

    @Override
    protected String getFyiInProgressStatusHook() {
        return ProtocolStatus.FYI_IN_PROGRESS;
    }

    @Override
    protected List<String> getAllModuleTypeCodes() {
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
        moduleTypeCodes.add(ProtocolModule.PROTOCOL_PERMISSIONS);
        moduleTypeCodes.add(ProtocolModule.QUESTIONNAIRE);
        return moduleTypeCodes;
    }

    @Override
    protected void addModules(ProtocolBase protocol, ProtocolAmendmentBean amendmentBean) {
        ProtocolAmendRenewal amendmentEntry = (ProtocolAmendRenewal)protocol.getProtocolAmendRenewal();
        final ProtocolBase currentProtocolByNumber = protocolFinderDao.findCurrentProtocolByNumber(protocol.getAmendedProtocolNumber());

        if (amendmentBean.getGeneralInfo()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.GENERAL_INFO));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.GENERAL_INFO);
            amendmentEntry.removeModule(ProtocolModule.GENERAL_INFO);
        }
        
        if (amendmentBean.getAddModifyAttachments()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.ADD_MODIFY_ATTACHMENTS));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.ADD_MODIFY_ATTACHMENTS);
            amendmentEntry.removeModule(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        }
        
        if (amendmentBean.getAreasOfResearch()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.AREAS_OF_RESEARCH));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.AREAS_OF_RESEARCH);
            amendmentEntry.removeModule(ProtocolModule.AREAS_OF_RESEARCH);
        }
        
        if (amendmentBean.getFundingSource()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.FUNDING_SOURCE));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.FUNDING_SOURCE);
            amendmentEntry.removeModule(ProtocolModule.FUNDING_SOURCE);
        }
        
        if (amendmentBean.getProtocolOrganizations()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_ORGANIZATIONS));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.PROTOCOL_ORGANIZATIONS);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_ORGANIZATIONS);
        }
        
        if (amendmentBean.getProtocolPersonnel()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERSONNEL));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.PROTOCOL_PERSONNEL);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_PERSONNEL);
        }
        
        if (amendmentBean.getProtocolReferencesAndOtherIdentifiers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_REFERENCES));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.PROTOCOL_REFERENCES);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_REFERENCES);
        }
        
        if (amendmentBean.getSubjects()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SUBJECTS));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.SUBJECTS);
            amendmentEntry.removeModule(ProtocolModule.SUBJECTS);
        }
        
        if (amendmentBean.getSpecialReview()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.SPECIAL_REVIEW));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.SPECIAL_REVIEW);
            amendmentEntry.removeModule(ProtocolModule.SPECIAL_REVIEW);
        }
        
        if (amendmentBean.getOthers()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.OTHERS));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.OTHERS);
            amendmentEntry.removeModule(ProtocolModule.OTHERS);
        }
        
        if (amendmentBean.getProtocolPermissions()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.PROTOCOL_PERMISSIONS));
        } else {
            protocol.merge(currentProtocolByNumber, ProtocolModule.PROTOCOL_PERMISSIONS);
            amendmentEntry.removeModule(ProtocolModule.PROTOCOL_PERMISSIONS);
        }
        if (amendmentBean.getQuestionnaire()) {
            amendmentEntry.addModule(createModule(amendmentEntry, ProtocolModule.QUESTIONNAIRE));
        } else {
            removeEditedQuestionaire(protocol);
            amendmentEntry.removeModule(ProtocolModule.QUESTIONNAIRE);
        }

    }

    @Override
    public String createFYI(ProtocolDocumentBase protocolDocument, ProtocolNotifyIrbBean fyiBean) throws Exception {
        return createFYI(protocolDocument, fyiBean.getActionHelper(), fyiBean.getComment());
    }

    @Override
    protected ProtocolAmendmentBean getFyiAttachmentsBean(ActionHelperBase actionHelper) {
        ProtocolAmendmentBean fyiAttachmentsBean = new org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean((ActionHelper) actionHelper);
        fyiAttachmentsBean.setAddModifyAttachments(true);
        return fyiAttachmentsBean;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }

    @Override
    protected ProtocolAmendRenewalBase getNewProtocolAmendRenewalInstanceHook() {
        return new ProtocolAmendRenewal();
    }

    @Override
    protected ProtocolAmendRenewModuleBase getNewProtocolAmendRenewModuleInstanceHook() {
        return new ProtocolAmendRenewModule();
    }
}
