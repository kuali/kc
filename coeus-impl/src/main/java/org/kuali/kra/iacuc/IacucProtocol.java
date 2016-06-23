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
package org.kuali.kra.iacuc;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.CustomDataContainer;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
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
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolSpecialVersion;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentFilterBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.*;

public class IacucProtocol extends ProtocolBase implements CustomDataContainer {
    

    private static final long serialVersionUID = 7380281405644745576L;
    private static final String NO = "no";
    private static final String PROTOCOL_PROJECT_TYPE = "protocolProjectType";

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

    public IacucProtocol() {         
        setCreateTimestamp(new Timestamp(new java.util.Date().getTime()));
        if (GlobalVariables.getUserSession() != null) {
            setCreateUser(GlobalVariables.getUserSession().getPrincipalId());
        }
        setScientificJustifIndicator(NO);
        setSpecialReviewIndicator(NO);
        setSpeciesStudyGroupIndicator(NO);
        setKeyStudyPersonIndicator(NO);
        setFundingSourceIndicator(NO);
        setCorrespondentIndicator(NO);
        setReferenceIndicator(NO);
        setAlternativeSearchIndicator(NO);
        setIacucProtocolSpeciesList(new ArrayList<>());
        setIacucAlternateSearches(new ArrayList<>());
        setIacucProtocolCustomDataList(new ArrayList<>());
        setIacucProtocolExceptions(new ArrayList<>());
        setIacucProtocolStudyGroups(new ArrayList<>());
        setIacucProtocolStudyGroupBeans(new ArrayList<>());
        setIacucProtocolStudyGroupLocations(new ArrayList<>());
        setIacucProtocolStudyGroupSpeciesList(new ArrayList<>());
        initIacucPrinciples();
    } 
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getIacucProtocolCustomDataList());
        managedLists.add(getIacucPrinciples());
        managedLists.add(getIacucAlternateSearches());
        
        List<IacucProtocolStudyGroupLocation> locationResponsibleProcedures = new ArrayList<>();
        List<IacucProcedurePersonResponsible> personResponsibleProcedures = new ArrayList<>();
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = new ArrayList<>();
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

    @Override
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    @Override
    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    protected IacucProtocolPersonnelService getProtocolPersonnelService() {
        return KcServiceLocator.getService(IacucProtocolPersonnelService.class);
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
      List<String> roleNames = new ArrayList<>();

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
        List<IacucPrinciples> newPrinciples = new ArrayList<>();
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

    /**
     * get submit for review questionnaire answerheaders.
     */
    @Override
    public List <AnswerHeader> getAnswerHeaderForProtocol(ProtocolBase protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        return getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
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

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.PROTOCOL_KEY;
    }

    @Override
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
        summary.setProtocolNumber(getProtocolNumber());
        summary.setPiName(getPrincipalInvestigator().getPersonName());
        summary.setPiProtocolPersonId(getPrincipalInvestigator().getProtocolPersonId());
        summary.setInitialSubmissionDate(getInitialSubmissionDate());
        summary.setApprovalDate(getApprovalDate());
        summary.setLastApprovalDate(getLastApprovalDate());
        summary.setExpirationDate(getExpirationDate());
        if (getProtocolType() == null) {
            refreshReferenceObject(PROTOCOL_TYPE);
        }
        summary.setType(getProtocolType().getDescription());
        if (getProtocolStatus() == null) {
            refreshReferenceObject(PROTOCOL_STATUS);
        }
        summary.setStatus(getProtocolStatus().getDescription());
        summary.setTitle(getTitle());
        
        summary.setLayStmt1(getLayStatement1()); 
        summary.setLayStmt2(getLayStatement2());
        
        if (getProtocolProjectType() == null) {
            refreshReferenceObject(PROTOCOL_PROJECT_TYPE);
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
                threeRsSummary.setSearchRequired(Constants.TRUE_FLAG.equals(principles.getSearchRequired()));
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

    @Override
    protected void removeMergeableLists(List<ProtocolAmendRenewModuleBase> modules) {
        for (ProtocolAmendRenewModuleBase module: modules) {
            String protocolModuleTypeCode = module.getProtocolModuleTypeCode();
            if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.AREAS_OF_RESEARCH)) {
                this.getProtocolResearchAreas().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.FUNDING_SOURCE)) {
                this.getProtocolFundingSources().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_ORGANIZATIONS)) {
                this.getProtocolLocations().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_PERSONNEL)) {
                this.getProtocolPersons().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROTOCOL_REFERENCES)) {
                this.getProtocolReferences().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.THREE_RS)) {
                this.getIacucPrinciples().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.PROCEDURES)) {
                this.getIacucPrinciples().clear();
            }
            else if (StringUtils.equals(protocolModuleTypeCode, IacucProtocolModule.EXCEPTIONS)) {
                this.getIacucPrinciples().clear();
            }
        }
    }



    /**
     * merge amendment/renewal protocol action to original protocol when A/R is approved
     */
    @Override
    protected void mergeProtocolAction(ProtocolBase amendment) {
        List<ProtocolActionBase> protocolActions =  deepCopy(amendment.getProtocolActions());
        Collections.sort(protocolActions, (action1, action2) -> action1.getActionId().compareTo(action2.getActionId()));
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
        getProtocolProcedureService().mergeProtocolSpecies((IacucProtocol) amendment, this);
    }
    
    protected void mergeProtocolProcedures(ProtocolBase amendment) {
        getProtocolProcedureService().mergeProtocolProcedures((IacucProtocol) amendment, this);
    }

    protected void mergeProtocolExceptions(ProtocolBase amendment) {
        getProtocolCopyService().copyProtocolExceptions((IacucProtocol) amendment, this);
    }
    
    protected IacucProtocolCopyService getProtocolCopyService() {
        return KcServiceLocator.getService(IacucProtocolCopyService.class);
    }

    public boolean isContinuation() {
        return getProtocolNumber() != null && getProtocolNumber().contains(ProtocolSpecialVersion.CONTINUATION.getCode());
    }


    protected String getProtocolMergeType(ProtocolBase amendment) {
        IacucProtocol protocolAmend = (IacucProtocol)amendment;
        String type = ProtocolSpecialVersion.AMENDMENT.getDescription();
        if (protocolAmend.isRenewal()) {
            type = ProtocolSpecialVersion.RENEWAL.getDescription();
        }else if(protocolAmend.isContinuation()) {
            type = ProtocolSpecialVersion.CONTINUATION.getDescription();
        }else if(protocolAmend.isFYI()) {
            type = ProtocolSpecialVersion.FYI.getDescription();
        }
        return type;
    }

    @Override
    public boolean isNew() {
        return !isAmendment() && !isRenewal() && !isContinuation() && !isFYI();
    }
    
    public boolean isContinuationWithoutAmendment() {
        return isContinuation() && CollectionUtils.isEmpty(this.getProtocolAmendRenewal().getModules());
    }

    @Override
    public String getAmendedProtocolNumber() {
        if (isAmendment()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.AMENDMENT.getCode());
            
        } else if (isRenewal()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.RENEWAL.getCode());
        
        } else if (isContinuation()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.CONTINUATION.getCode());
                
        } else if (isFYI()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.FYI.getCode());
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
            qualifiedRoleAttributes = new HashMap<>();
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


    @Override
    public List<? extends DocumentCustomData> getCustomDataList() {
        return getProtocolDocument().getDocumentCustomData();
    }
}
