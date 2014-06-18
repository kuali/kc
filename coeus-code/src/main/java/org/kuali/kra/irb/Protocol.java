/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentFilter;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.irb.summary.ParticipantSummary;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentFilterBase;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * This class is Protocol Business Object.
 */
public class Protocol extends ProtocolBase {

    private static final long serialVersionUID = 4396393806439396971L;
    
    private static final String DEFAULT_PROTOCOL_TYPE_CODE = "1";
  
    private String vulnerableSubjectIndicator;
    private List<ProtocolRiskLevel> protocolRiskLevels;
    private List<ProtocolParticipant> protocolParticipants;    
    private transient boolean lookupActionNotifyIRBProtocol;

    /**
     * 
     * Constructs an Protocol BO.
     */
    public Protocol() {
        super();
        protocolRiskLevels = new ArrayList<ProtocolRiskLevel>();
        protocolParticipants = new ArrayList<ProtocolParticipant>();        
    }
    
    public String getVulnerableSubjectIndicator() {
        return vulnerableSubjectIndicator;
    }

    public void setVulnerableSubjectIndicator(String vulnerableSubjectIndicator) {
        this.vulnerableSubjectIndicator = vulnerableSubjectIndicator;
    }

    public List<ProtocolRiskLevel> getProtocolRiskLevels() {
        return protocolRiskLevels;
    }

    public void setProtocolRiskLevels(List<ProtocolRiskLevel> protocolRiskLevels) {
        this.protocolRiskLevels = protocolRiskLevels;
        for (ProtocolRiskLevel riskLevel : protocolRiskLevels) {
            riskLevel.init(this);
        }
    }
    
    public List<ProtocolParticipant> getProtocolParticipants() {
        return protocolParticipants;
    }

    public void setProtocolParticipants(List<ProtocolParticipant> protocolParticipants) {
        this.protocolParticipants = protocolParticipants;
        for (ProtocolParticipant participant : protocolParticipants) {
            participant.init(this);
        }
    }
    
    /**
     * Gets index i from the protocol participant list.
     * 
     * @param index
     * @return protocol participant at index i
     */
    public ProtocolParticipant getProtocolParticipant(int index) {
        return getProtocolParticipants().get(index);
    }    
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getProtocolRiskLevels());
        managedLists.add(getProtocolParticipants());
        return managedLists;
    }
    
    /**
     * This method is to get protocol personnel service
     * @return protocolPersonnelService
     */
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        ProtocolPersonnelService protocolPersonnelService = KcServiceLocator.getService(ProtocolPersonnelService.class);
        return protocolPersonnelService;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return (ProtocolSubmission) super.getProtocolSubmission(); 
    }    
    
    public ProtocolAction getLastProtocolAction() {
        return (ProtocolAction) super.getLastProtocolAction();
    }    
    
    /**
     * 
     * This method merges the data of a specific module of the amended protocol into this protocol.
     * @param amendment
     * @param protocolModuleTypeCode
     */
    public void merge(ProtocolBase amendment, String protocolModuleTypeCode) {
        if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.GENERAL_INFO)) {
            mergeGeneralInfo(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.AREAS_OF_RESEARCH)) {
            mergeResearchAreas(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.FUNDING_SOURCE)) {
            mergeFundingSources(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_ORGANIZATIONS)) {
            mergeOrganizations(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_PERSONNEL)) {
            mergePersonnel(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.ADD_MODIFY_ATTACHMENTS)) {
            if (amendment.isAmendment() || amendment.isRenewal()
                    || (!amendment.getAttachmentProtocols().isEmpty() && this.getAttachmentProtocols().isEmpty())) {
                mergeAttachments(amendment);
            }
            else {
                restoreAttachments(this);
            }
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_REFERENCES)) {
            mergeReferences(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.SPECIAL_REVIEW)) {
            mergeSpecialReview(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.SUBJECTS)) {
            mergeSubjects((Protocol) amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.OTHERS)) {
            mergeOthers(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_PERMISSIONS)) {
            mergeProtocolPermissions(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.QUESTIONNAIRE)) {
            mergeProtocolQuestionnaire(amendment);
        }
    }

    /*
     * get submit for review questionnaire answerheaders
     */
    public List <AnswerHeader> getAnswerHeaderForProtocol(ProtocolBase protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean((Protocol) protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        List <AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
        return answerHeaders;
    }
    
    /*
     * merge amendment/renewal protocol action to original protocol when A/R is approved
     */
    @SuppressWarnings("unchecked")
    protected void mergeProtocolAction(ProtocolBase amendment) {
        List<ProtocolAction> protocolActions = (List<ProtocolAction>) deepCopy(amendment.getProtocolActions());  
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action1.getActionId().compareTo(action2.getActionId());
            }
        });
        // the first 1 'protocol created is already added to original protocol
        // the last one is 'approve'
        protocolActions.remove(0);
        protocolActions.remove(protocolActions.size() - 1);
        for (ProtocolAction protocolAction : protocolActions) {
            protocolAction.setProtocolNumber(this.getProtocolNumber());
            protocolAction.setProtocolActionId(null);
            protocolAction.setSequenceNumber(getSequenceNumber());
            protocolAction.setProtocolId(this.getProtocolId());
            String index = amendment.getProtocolNumber().substring(11);
            protocolAction.setActionId(getNextValue(NEXT_ACTION_ID_KEY));
            String type = "Amendment";
            if (amendment.isRenewal()) {
                type = "Renewal";
            }
            if (StringUtils.isNotBlank(protocolAction.getComments())) {
                protocolAction.setComments(type + "-" + index + ": " + protocolAction.getComments());
            } else {
                protocolAction.setComments(type + "-" + index + ": ");
            }
            // reset persistence state for copied notifications so they break ties with old document
            for (KcNotification notification: protocolAction.getProtocolNotifications()) {
                notification.setDocumentNumber(getProtocolDocument().getDocumentNumber());
                notification.resetPersistenceState();
                notification.setOwningDocumentIdFk(null);
            }
            this.getProtocolActions().add(protocolAction);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void mergeSubjects(Protocol amendment) {
        setProtocolParticipants((List<ProtocolParticipant>) deepCopy(amendment.getProtocolParticipants()));
    }
    
    public ProtocolSummary getProtocolSummary() {
        ProtocolSummary protocolSummary = createProtocolSummary();
        addPersonnelSummaries(protocolSummary);
        addResearchAreaSummaries(protocolSummary);
        addAttachmentSummaries(protocolSummary);
        addFundingSourceSummaries(protocolSummary);
        addParticipantSummaries(protocolSummary);
        addOrganizationSummaries(protocolSummary);
        addSpecialReviewSummaries(protocolSummary);
        addAdditionalInfoSummary(protocolSummary);
        return protocolSummary;
    }
    
    private void addParticipantSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolParticipant participant : this.getProtocolParticipants()) {
            ParticipantSummary participantSummary = new ParticipantSummary();
            participantSummary.setDescription(participant.getParticipantType().getDescription());
            participantSummary.setCount(participant.getParticipantCount());
            protocolSummary.add(participantSummary);
        }
    }

    protected ProtocolSummary createProtocolSummary() {
        ProtocolSummary summary = new ProtocolSummary();
        summary.setLastProtocolAction(getLastProtocolAction());
        summary.setProtocolNumber(getProtocolNumber().toString());
        summary.setPiName(getPrincipalInvestigator().getPersonName());
        summary.setPiProtocolPersonId(getPrincipalInvestigator().getProtocolPersonId());
        summary.setInitialSubmissionDate(getInitialSubmissionDate());
        summary.setApprovalDate(getApprovalDate());
        summary.setLastApprovalDate(getLastApprovalDate());
        summary.setExpirationDate(getExpirationDate());
        if (getProtocolType() == null) {
            refreshReferenceObject("protocolType");
        }
        summary.setType(getProtocolType().getDescription());
        if (getProtocolStatus() == null) {
            refreshReferenceObject("protocolStatus");
        }
        summary.setStatus(getProtocolStatus().getDescription());
        summary.setTitle(getTitle());
        return summary;
    }

    
    @Override
    public String getDocumentKey() {
        return PermissionableKeys.PROTOCOL_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        roleNames.add(RoleConstants.PROTOCOL_AGGREGATOR);
        roleNames.add(RoleConstants.PROTOCOL_VIEWER);

        return roleNames;
    }
    
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROTOCOL_DOC_ROLE_TYPE;
    }

    public void initializeProtocolAttachmentFilter() {
        ProtocolAttachmentFilterBase protocolAttachmentFilter = new ProtocolAttachmentFilter();
        
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = getParameterService().getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                protocolAttachmentFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }        
        
        setProtocolAttachmentFilter(protocolAttachmentFilter);
    }
    
    public KrmsRulesContext getKrmsRulesContext() {
        return (KrmsRulesContext) getProtocolDocument();
    }

    @Override
    protected ProtocolStatusBase getProtocolStatusNewInstanceHook() {
        return new ProtocolStatus();
    }

    @Override
    protected String getDefaultProtocolStatusCodeHook() {
        return Constants.DEFAULT_PROTOCOL_STATUS_CODE;
    }

    @Override
    protected String getDefaultProtocolTypeCodeHook() {
        return DEFAULT_PROTOCOL_TYPE_CODE;
    }

    @Override
    protected ProtocolResearchAreaBase getNewProtocolResearchAreaInstance() {
        return new ProtocolResearchArea();
    }

    @Override
    protected ProtocolSubmissionStatusBase getProtocolSubmissionStatusNewInstanceHook() {
        return new ProtocolSubmissionStatus();
    }

    @Override
    protected ProtocolSubmissionTypeBase getProtocolSubmissionTypeNewInstanceHook() {
        return new ProtocolSubmissionType();
    }

    @Override
    protected ProtocolSubmissionBase getProtocolSubmissionNewInstanceHook() {
        return new ProtocolSubmission();
    }

    @Override
    protected String getProtocolModuleAddModifyAttachmentCodeHook() {
        return ProtocolModule.ADD_MODIFY_ATTACHMENTS;
    }

    @Override
    protected Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook() {
        return ProtocolAttachmentProtocol.class;
    }

    public boolean isLookupActionNotifyIRBProtocol() {
        return lookupActionNotifyIRBProtocol;
    }

    public void setLookupActionNotifyIRBProtocol(boolean lookupActionNotifyIRBProtocol) {
        this.lookupActionNotifyIRBProtocol = lookupActionNotifyIRBProtocol;
    }
    
}
