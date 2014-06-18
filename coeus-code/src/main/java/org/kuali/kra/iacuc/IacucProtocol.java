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
package org.kuali.kra.iacuc;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentFilter;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentProtocol;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.iacuc.procedures.*;
import org.kuali.kra.iacuc.protocol.IacucProtocolProjectType;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchArea;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.summary.*;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentFilterBase;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * This class is ProtocolBase Business Object.
 */
public class IacucProtocol extends ProtocolBase {
    

    private static final long serialVersionUID = 7380281405644745576L;
    
    private boolean isBillable;
    private String layStatement1; 
    private String layStatement2;
    
    private String protocolProjectTypeCode; 
    
    private String overviewTimeline; 
    private String speciesStudyGroupIndicator; 
    private String alternativeSearchIndicator; 
    private String scientificJustifIndicator; 

    private Timestamp createTimestamp;
    private String createUser;
    
    private IacucProtocolProjectType protocolProjectType;
    
    private List<IacucProtocolCustomData> iacucProtocolCustomDataList;

    private List<IacucPrinciples> iacucPrinciples;
    private List<IacucAlternateSearch> iacucAlternateSearches;
      
    private List<IacucProtocolSpecies> iacucProtocolSpeciesList;
    private List<IacucProtocolException> iacucProtocolExceptions;

    private List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups;

    // this collection is to regroup study procedures for UI display
    private List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans;
    private List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations;
    private List<IacucProtocolSpeciesStudyGroup> iacucProtocolStudyGroupSpeciesList;

    // lookup field
    private Integer speciesCode; 
    private Integer exceptionCategoryCode; 
    private static final CharSequence CONTINUATION_LETTER = "C";

    public IacucProtocol() {         
        setCreateTimestamp(new Timestamp(new java.util.Date().getTime()));
        if (GlobalVariables.getUserSession() != null) {
            setCreateUser(GlobalVariables.getUserSession().getPrincipalId());
        }
        setScientificJustifIndicator("no");
        setSpecialReviewIndicator("no");
        setSpeciesStudyGroupIndicator("no");
        setKeyStudyPersonIndicator("no");
        setFundingSourceIndicator("no");
        setCorrespondentIndicator("no");
        setReferenceIndicator("no");
        setAlternativeSearchIndicator("no");
        setIacucProtocolSpeciesList(new ArrayList<IacucProtocolSpecies>());
        setIacucAlternateSearches(new ArrayList<IacucAlternateSearch>());
        setIacucProtocolCustomDataList(new ArrayList<IacucProtocolCustomData>());
        setIacucProtocolExceptions(new ArrayList<IacucProtocolException>());
        setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroupBean>());
        setIacucProtocolStudyGroupBeans(new ArrayList<IacucProtocolStudyGroupBean>());
        setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
        setIacucProtocolStudyGroupSpeciesList(new ArrayList<IacucProtocolSpeciesStudyGroup>());
        initIacucPrinciples();
    } 
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getIacucProtocolCustomDataList());
        managedLists.add(getIacucPrinciples());
        managedLists.add(getIacucAlternateSearches());
        
        List<IacucProtocolStudyGroupLocation> locationResponsibleProcedures = new ArrayList<IacucProtocolStudyGroupLocation>();
        List<IacucProcedurePersonResponsible> personResponsibleProcedures = new ArrayList<IacucProcedurePersonResponsible>();
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : getIacucProtocolStudyGroups()) {
            iacucProtocolStudyGroups.addAll(iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups());
            for (IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                personResponsibleProcedures.addAll(iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList());
                locationResponsibleProcedures.addAll(iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList());
            }
        }

        managedLists.add(locationResponsibleProcedures);
        managedLists.add(personResponsibleProcedures);
        managedLists.add(iacucProtocolStudyGroups);
        
        managedLists.add(getIacucProtocolStudyGroups());
        managedLists.add(getIacucProtocolExceptions());
        managedLists.add(getIacucProtocolSpeciesList());
        
        return managedLists;
    }

    public IacucProtocolSubmission getIacucProtocolSubmission() {
        return (IacucProtocolSubmission)getProtocolSubmission();
    }

    public boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }
    
    public String getLayStatement1() {
        return layStatement1;
    }

    public void setLayStatement1(String layStatement1) {
        this.layStatement1 = layStatement1;
    }

    public String getLayStatement2() {
        return layStatement2;
    }

    public void setLayStatement2(String layStatement2) {
        this.layStatement2 = layStatement2;
    }

    public String getOverviewTimeline() {
        return overviewTimeline;
    }

    public void setOverviewTimeline(String overviewTimeline) {
        this.overviewTimeline = overviewTimeline;
    }

    public String getSpeciesStudyGroupIndicator() {
        return speciesStudyGroupIndicator;
    }

    public void setSpeciesStudyGroupIndicator(String speciesStudyGroupIndicator) {
        this.speciesStudyGroupIndicator = speciesStudyGroupIndicator;
    }

    public String getAlternativeSearchIndicator() {
        return alternativeSearchIndicator;
    }

    public void setAlternativeSearchIndicator(String alternativeSearchIndicator) {
        this.alternativeSearchIndicator = alternativeSearchIndicator;
    }

    public String getScientificJustifIndicator() {
        return scientificJustifIndicator;
    }

    public void setScientificJustifIndicator(String scientificJustifIndicator) {
        this.scientificJustifIndicator = scientificJustifIndicator;
    }
    
    

    public void setIacucProtocolDocument(IacucProtocolDocument iacucProtocolDocument) {
        this.setProtocolDocument(iacucProtocolDocument);
    }

    public IacucProtocolDocument getIacucProtocolDocument() {
        return (IacucProtocolDocument) this.getProtocolDocument();
    }

    public void setBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    

    @Override
    // implementation of hook method
    protected IacucProtocolPersonnelService getProtocolPersonnelService() {
        return (IacucProtocolPersonnelService) KcServiceLocator.getService("iacucProtocolPersonnelService");
    }


    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }


    @Override
    protected String getDefaultProtocolStatusCodeHook() {
        return IacucProtocolStatus.IN_PROGRESS;
    }


    @Override
    protected String getDefaultProtocolTypeCodeHook() {

        return null;
    }


    @Override
    protected ProtocolSubmissionStatusBase getProtocolSubmissionStatusNewInstanceHook() {
        return new IacucProtocolSubmissionStatus();
    }


    @Override
    protected ProtocolSubmissionTypeBase getProtocolSubmissionTypeNewInstanceHook() {
        return new IacucProtocolSubmissionType();
    }


    @Override
    protected ProtocolSubmissionBase getProtocolSubmissionNewInstanceHook() {
        return new IacucProtocolSubmission();
    }


    @Override
    protected ProtocolStatusBase getProtocolStatusNewInstanceHook() {
        return new IacucProtocolStatus();
    }


    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.IACUC_ROLE_TYPE;
    }    

    @Override
    public List<String> getRoleNames() {
      List<String> roleNames = new ArrayList<String>();

      roleNames.add(RoleConstants.IACUC_PROTOCOL_AGGREGATOR);
      roleNames.add(RoleConstants.IACUC_PROTOCOL_VIEWER);

      return roleNames;        
    }


    public List<IacucPrinciples> getIacucPrinciples() {
        return iacucPrinciples;
    }


    public void setIacucPrinciples(List<IacucPrinciples> iacucPrinciples) {
        this.iacucPrinciples = iacucPrinciples;
    }
    
    public List<IacucProtocolSpecies> getIacucProtocolSpeciesList() {
        return iacucProtocolSpeciesList;
    }


    public void setIacucProtocolSpeciesList(List<IacucProtocolSpecies> iacucProtocolSpeciesList) {
        this.iacucProtocolSpeciesList = iacucProtocolSpeciesList;
    }


    @Override
    protected ProtocolResearchAreaBase getNewProtocolResearchAreaInstance() {
        return new IacucProtocolResearchArea();
    }


    public List<IacucAlternateSearch> getIacucAlternateSearches() {
        return iacucAlternateSearches;
    }

    public void setIacucAlternateSearches(List<IacucAlternateSearch> iacucAlternateSearches) {
        this.iacucAlternateSearches = iacucAlternateSearches;
    }

    public void setProtocolProjectTypeCode(String protocolProjectTypeCode) {
        this.protocolProjectTypeCode = protocolProjectTypeCode;
    }

    public String getProtocolProjectTypeCode() {
        return protocolProjectTypeCode;
    }

    public void setProtocolProjectType(IacucProtocolProjectType protocolProjectType) {
        this.protocolProjectType = protocolProjectType;
    }

    public IacucProtocolProjectType getProtocolProjectType() {
        return protocolProjectType;
    }

    public List<IacucProtocolCustomData> getIacucProtocolCustomDataList() {
        return iacucProtocolCustomDataList;
    }

    public void setIacucProtocolCustomDataList(List<IacucProtocolCustomData> iacucProtocolCustomDataList) {
        this.iacucProtocolCustomDataList = iacucProtocolCustomDataList;
    }

    public List<IacucProtocolException> getIacucProtocolExceptions() {
        return iacucProtocolExceptions;
    }

    public void setIacucProtocolExceptions(List<IacucProtocolException> iacucProtocolExceptions) {
        this.iacucProtocolExceptions = iacucProtocolExceptions;
    }    
    
    private void initIacucPrinciples() {
        List<IacucPrinciples> newPrinciples = new ArrayList<IacucPrinciples>();
        IacucPrinciples iPrinciples = new IacucPrinciples();
        newPrinciples.add(iPrinciples);
        setIacucPrinciples(newPrinciples);

    }

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public Integer getExceptionCategoryCode() {
        return exceptionCategoryCode;
    }

    public void setExceptionCategoryCode(Integer exceptionCategoryCode) {
        this.exceptionCategoryCode = exceptionCategoryCode;
    }

    /*
     * get submit for review questionnaire answerheaders
     */
    public List <AnswerHeader> getAnswerHeaderForProtocol(ProtocolBase protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        List <AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
        return answerHeaders;
    }

    @Override
    public void initializeProtocolAttachmentFilter() {
        ProtocolAttachmentFilterBase protocolAttachmentFilter = new IacucProtocolAttachmentFilter();
  
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = getParameterService().getParameterValueAsString(IacucProtocolDocument.class, Constants.PARAMETER_IACUC_PROTOCOL_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                protocolAttachmentFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }
        
        setProtocolAttachmentFilter(protocolAttachmentFilter);
    } 
    
    
    public String getDocumentKey() {
        // TODO need to change this to IACUC PROTOCOL KEY!!!!
        return PermissionableKeys.PROTOCOL_KEY;
    }

    public ProtocolSummary getProtocolSummary() {
        ProtocolSummary protocolSummary = createProtocolSummary();
        addPersonnelSummaries(protocolSummary);
        addResearchAreaSummaries(protocolSummary);
        addAttachmentSummaries(protocolSummary);
        addFundingSourceSummaries(protocolSummary);
        addOrganizationSummaries(protocolSummary);
        addSpecialReviewSummaries(protocolSummary);
        addAdditionalInfoSummary(protocolSummary);
        addThreeRsSummary((IacucProtocolSummary)protocolSummary);
        addSpeciesAndGroupsSummaries((IacucProtocolSummary)protocolSummary);
        addExceptionsSummaries((IacucProtocolSummary)protocolSummary);
        addProceduresSummaries((IacucProtocolSummary)protocolSummary);
        return protocolSummary;
    }
    
    @Override
    protected ProtocolSummary createProtocolSummary() {
        IacucProtocolSummary summary = new IacucProtocolSummary();
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
        
        summary.setLayStmt1(getLayStatement1()); 
        summary.setLayStmt2(getLayStatement2());
        
        if (getProtocolProjectType() == null) {
            refreshReferenceObject("protocolProjectType");
        }
        summary.setProjectType((protocolProjectType != null) ? protocolProjectType.getDescription() : "N/A"); 
        return summary;
    }

    protected void addThreeRsSummary(IacucProtocolSummary protocolSummary) {
        IacucThreeRsSummary threeRsSummary = new IacucThreeRsSummary();
        if(getIacucPrinciples().size() > 0) {
            IacucPrinciples principles = getIacucPrinciples().get(0);
            threeRsSummary.setReduction(principles.getReduction());
            threeRsSummary.setRefinement(principles.getRefinement());
            threeRsSummary.setReplacement(principles.getReplacement());
            for (IacucAlternateSearch alternateSearch:iacucAlternateSearches) {
                threeRsSummary.getAlternateSearchSummaries().add(new IacucAlternateSearchSummary(alternateSearch));
                threeRsSummary.setSearchRequired("Y".equals(principles.getSearchRequired()));
            }
        }
        protocolSummary.setThreeRsInfo(threeRsSummary);
    }

    protected void addSpeciesAndGroupsSummaries(IacucProtocolSummary protocolSummary) {
        for (IacucProtocolSpecies species : iacucProtocolSpeciesList) {
            IacucProtocolSpeciesSummary newSummary = new IacucProtocolSpeciesSummary(species);
            protocolSummary.getSpeciesSummaries().add(newSummary);
        }
    }

    protected void addExceptionsSummaries(IacucProtocolSummary protocolSummary) {
        for (IacucProtocolException exception : iacucProtocolExceptions) {
            IacucProtocolExceptionSummary newSummary = new IacucProtocolExceptionSummary(exception);
            protocolSummary.getExceptionSummaries().add(newSummary);
        }
    }

    protected void addProceduresSummaries(IacucProtocolSummary protocolSummary) {
        protocolSummary.setProcedureOverviewSummary(overviewTimeline);
        for(IacucProtocolStudyGroupBean studyGroupBean : getIacucProtocolStudyGroups()) {
            for (IacucProtocolStudyGroup studyGroup : studyGroupBean.getIacucProtocolStudyGroups()) {
                IacucProcedureSummary newSummary = new IacucProcedureSummary(studyGroup, studyGroupBean.getIacucProcedureCategory(),
                        studyGroupBean.getIacucProcedure());
                protocolSummary.getProcedureSummaries().add(newSummary);
            }
        }
    }

    public List<IacucProtocolStudyGroupBean> getIacucProtocolStudyGroups() {
        return iacucProtocolStudyGroups;
    }

    public void setIacucProtocolStudyGroups(List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups) {
        this.iacucProtocolStudyGroups = iacucProtocolStudyGroups;
    }

    public List<IacucProtocolStudyGroupBean> getIacucProtocolStudyGroupBeans() {
        return iacucProtocolStudyGroupBeans;
    }

    public void setIacucProtocolStudyGroupBeans(List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans) {
        this.iacucProtocolStudyGroupBeans = iacucProtocolStudyGroupBeans;
    }

    @Override
    protected String getProtocolModuleAddModifyAttachmentCodeHook() {
        return IacucProtocolModule.ADD_MODIFY_ATTACHMENTS;
    }
    
    @Override
    protected boolean isExpirationDateToBeUpdated(ProtocolBase protocol) {
        return (super.isExpirationDateToBeUpdated(protocol) ||  ((IacucProtocol) protocol).isContinuation());
    }


    @Override
    public void merge(ProtocolBase amendment, String protocolModuleTypeCode) {
        if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.GENERAL_INFO)) {
            mergeGeneralInfo(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.AREAS_OF_RESEARCH)) {
            mergeResearchAreas(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.FUNDING_SOURCE)) {
            mergeFundingSources(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_ORGANIZATIONS)) {
            mergeOrganizations(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_PERSONNEL)) {
            mergePersonnel(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.ADD_MODIFY_ATTACHMENTS)) {
            if (amendment.isAmendment() || amendment.isRenewal()
                    || (!amendment.getAttachmentProtocols().isEmpty() && this.getAttachmentProtocols().isEmpty())) {
                mergeAttachments(amendment);
            }
            else {
                restoreAttachments(this);
            }
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_REFERENCES)) {
            mergeReferences(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.SPECIAL_REVIEW)) {
            mergeSpecialReview(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.OTHERS)) {
            mergeOthers(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_PERMISSIONS)) {
            mergeProtocolPermissions(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.QUESTIONNAIRE)) {
            mergeProtocolQuestionnaire(amendment);
        }

        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.THREE_RS)) {
            mergeProtocolThreers(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.SPECIES_GROUPS)) {
            mergeProtocolSpeciesAndGroups(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROCEDURES)) {
            mergeProtocolProcedures(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.EXCEPTIONS)) {
            mergeProtocolExceptions(amendment);
        }
    }
    
    
    /*
     * merge amendment/renewal protocol action to original protocol when A/R is approved
     */
    @SuppressWarnings("unchecked")
    protected void mergeProtocolAction(ProtocolBase amendment) {
        List<ProtocolActionBase> protocolActions = (List<ProtocolActionBase>) deepCopy(amendment.getProtocolActions());  
        Collections.sort(protocolActions, new Comparator<ProtocolActionBase>() {
            public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
                return action1.getActionId().compareTo(action2.getActionId());
            }
        });
        // the first 1 'protocol created is already added to original protocol
        // the last one is 'approve'
        protocolActions.remove(0);
        protocolActions.remove(protocolActions.size() - 1);
        for (ProtocolActionBase protocolAction : protocolActions) {
            protocolAction.setProtocolNumber(this.getProtocolNumber());
            protocolAction.setProtocolActionId(null);
            protocolAction.setSequenceNumber(getSequenceNumber());
            protocolAction.setProtocolId(this.getProtocolId());
            String index = amendment.getProtocolNumber().substring(11);
            protocolAction.setActionId(getNextValue(NEXT_ACTION_ID_KEY));
            String type = getProtocolMergeType(amendment);
            /*
            String type = "Amendment";
            if (amendment.isRenewal()) {
                type = "Renewal";
            }
            */
            if (StringUtils.isNotBlank(protocolAction.getComments())) {
                protocolAction.setComments(type + "-" + index + ": " + protocolAction.getComments());
            } else {
                protocolAction.setComments(type + "-" + index + ": ");
            }
            // reset persistence state for copied notifications so they break ties with old document
            for (KcNotification notification: protocolAction.getProtocolNotifications()) {
                notification.setDocumentNumber(getProtocolDocument().getDocumentNumber());
                notification.setNotificationId(null);
            }
            // reset persistence state for copied notifications so they break ties with old document
            for (KcNotification notification: protocolAction.getProtocolNotifications()) {
                notification.setDocumentNumber(getProtocolDocument().getDocumentNumber());
                notification.setNotificationId(null);
            }
            this.getProtocolActions().add(protocolAction);
        }
    }
    
    
    @Override
    protected void mergeGeneralInfo(ProtocolBase amendment) {
        super.mergeGeneralInfo(amendment);
        this.layStatement1 = ((IacucProtocol)amendment).layStatement1;
        this.layStatement2 = ((IacucProtocol)amendment).layStatement2;
        this.overviewTimeline = ((IacucProtocol)amendment).overviewTimeline;
        this.protocolProjectTypeCode = ((IacucProtocol)amendment).protocolProjectTypeCode;
    }
        
    protected void mergeProtocolThreers(ProtocolBase amendment) {
        getProtocolCopyService().copyProtocolThreers((IacucProtocol)amendment, this);
    }

    protected void mergeProtocolSpeciesAndGroups(ProtocolBase amendment) {
        getProtocolProcedureService().mergeProtocolSpecies((IacucProtocol)amendment, this);
    }
    
    protected void mergeProtocolProcedures(ProtocolBase amendment) {
        getProtocolProcedureService().mergeProtocolProcedures((IacucProtocol)amendment, this);
    }

    protected void mergeProtocolExceptions(ProtocolBase amendment) {
        getProtocolCopyService().copyProtocolExceptions((IacucProtocol)amendment, this);
    }
    
    protected IacucProtocolCopyService getProtocolCopyService() {
        return (IacucProtocolCopyService) KcServiceLocator.getService("iacucProtocolCopyService");
    }

    public boolean isContinuation() {
        return getProtocolNumber().contains(CONTINUATION_LETTER);
    }


    protected String getProtocolMergeType(ProtocolBase amendment) {
        IacucProtocol protocolAmend = (IacucProtocol)amendment;
        String type = "Amendment";
        if (protocolAmend.isRenewal()) {
            type = "Renewal";
        }else if(protocolAmend.isContinuation()) {
            type = "Continuation";
        }
        return type;
    }

    @Override
    public boolean isNew() {
        return !isAmendment() && !isRenewal() && !isContinuation();
    }
    
    public boolean isContinuationWithoutAmendment() {
        return isContinuation() && CollectionUtils.isEmpty(this.getProtocolAmendRenewal().getModules());
    }

    @Override
    public String getAmendedProtocolNumber() {
        if (isAmendment()) {
            return StringUtils.substringBefore(getProtocolNumber(), AMENDMENT_LETTER.toString());
            
        } else if (isRenewal()) {
            return StringUtils.substringBefore(getProtocolNumber(), RENEWAL_LETTER.toString());
        
        } else if (isContinuation()) {
            return StringUtils.substringBefore(getProtocolNumber(), CONTINUATION_LETTER.toString());
                
        } else {
            return null;
        }
    }

    @Override
    protected void prePersist() {
        super.prePersist();
    }

    protected IacucProtocolProcedureService getProtocolProcedureService() {
        return KcServiceLocator.getService(IacucProtocolProcedureService.class);
    }

    @Override
    public KrmsRulesContext getKrmsRulesContext() {
        return getIacucProtocolDocument();
    }
    
    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        if (qualifiedRoleAttributes == null) {
            qualifiedRoleAttributes = new HashMap<String, String>();
        }
        String protocolNumber = this.getProtocolNumber()  != null ? this.getProtocolNumber() : "*";
        qualifiedRoleAttributes.put(KcKimAttributes.PROTOCOL, protocolNumber);
    }

    @Override
    protected Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook() {
        return IacucProtocolAttachmentProtocol.class;
    }

    public List<IacucProtocolStudyGroupLocation> getIacucProtocolStudyGroupLocations() {
        return iacucProtocolStudyGroupLocations;
    }

    public void setIacucProtocolStudyGroupLocations(List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations) {
        this.iacucProtocolStudyGroupLocations = iacucProtocolStudyGroupLocations;
    }

    public List<IacucProtocolSpeciesStudyGroup> getIacucProtocolStudyGroupSpeciesList() {
        return iacucProtocolStudyGroupSpeciesList;
    }

    public void setIacucProtocolStudyGroupSpeciesList(List<IacucProtocolSpeciesStudyGroup> iacucProtocolStudyGroupSpeciesList) {
        this.iacucProtocolStudyGroupSpeciesList = iacucProtocolStudyGroupSpeciesList;
    }

    @Override
    protected void mergePersonnel(ProtocolBase amendment) {
        super.mergePersonnel(amendment);
        getProtocolProcedureService().mergeProtocolProcedurePersonnel(this);
    }


}
