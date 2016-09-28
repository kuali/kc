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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.DisclosureProjectStatus;
import org.kuali.coeus.coi.framework.DisclosureStatusRetrievalService;
import org.kuali.coeus.common.framework.medusa.MedusaNode;
import org.kuali.coeus.common.framework.medusa.MedusaService;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.person.attr.PersonEditableField;
import org.kuali.coeus.common.framework.print.ReportHelper;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplateList;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentActionBean;
import org.kuali.coeus.propdev.impl.attachment.NarrativeUserRights;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachmentHelper;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.core.AddBudgetDto;
import org.kuali.coeus.propdev.impl.budget.core.SelectableBudget;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataGroupDto;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataHelper;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonCoiIntegrationService;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalCreditSplitListDto;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.coi.CoiConstants;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedForm;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.specialreview.SpecialReviewHelper;
import org.kuali.coeus.propdev.impl.location.OrganizationAddWizardHelper;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.tree.Tree;
import org.kuali.rice.krad.data.util.Link;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.uif.util.SessionTransient;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.bind.ChangeTracking;
import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;

import javax.persistence.Transient;
import java.util.*;
import java.util.stream.Collectors;

@ChangeTracking
@Link(path = "document.developmentProposal")
public class ProposalDevelopmentDocumentForm extends TransactionalDocumentFormBase implements Auditable, SelectableBudget {

    private static final long serialVersionUID = 1381360399393420225L;
    
    private SpecialReviewHelper specialReviewHelper;
    private ProposalDevelopmentQuestionnaireHelper questionnaireHelper;
    private ProposalDevelopmentS2sQuestionnaireHelper s2sQuestionnaireHelper;
    private AddLineHelper addKeyPersonHelper;
    private AddLineHelper addRecipientHelper;
    private S2sOpportunity newS2sOpportunity;
    private List<ProposalUserRoles> workingUserRoles;
    private transient MedusaService medusaService;
    private transient LegacyDataAdapter legacyDataAdapter;
    private Map<String,List<String>> editableCollectionLines;
    private ProposalDevelopmentCustomDataHelper customDataHelper;
    private List<ProposalDevelopmentCustomDataGroupDto> customDataGroups;
    private List<DataValidationItem> dataValidationItems;
    private boolean auditActivated;
    private List<ProposalCreditSplitListDto> creditSplitListItems;
    private AddBudgetDto addBudgetDto;
    private AddBudgetDto copyBudgetDto;
    private ProposalCopyCriteria proposalCopyCriteria;
    private ProposalDevelopmentAttachmentHelper proposalDevelopmentAttachmentHelper;
    private OrganizationAddWizardHelper addOrganizationHelper;
    private ProposalPersonQuestionnaireHelper proposalPersonQuestionnaireHelper;
    private String hierarchyBudgetTypeCode;
    private String hierarchyProposalNumber;
    private S2sUserAttachedForm s2sUserAttachedForm;
    private List<NarrativeUserRights> narrativeUserRights;
    private String narrativeUserRightsSelectedAttachment;
    private ProposalChangedData newProposalChangedData;
    private boolean sendOverrideNotification;
    private ProposalDevelopmentActionBean proposalDevelopmentRejectionBean;
    private ProposalDevelopmentActionBean proposalDevelopmentApprovalBean;
    private List<SponsorFormTemplateList> sponsorFormTemplates;
    private ReportHelper reportHelper;
    private List<DevelopmentProposal> hierarchyDevelopmentProposals;
    private boolean viewOnly = false;
    private List<S2sOppForms> printS2sOppForms;
    private AnswerHeader updateAnswerHeader;

    /* These 2 properties are used for autogenerating an institutional proposal for a resubmission */
    private String resubmissionOption;
    private String institutionalProposalToVersion;

    private MessageMap deferredMessages;
    private String defaultOpenTab;

    private Map<String, Boolean> personEditableFields;

    private transient boolean grantsGovSubmitFlag;
    private transient boolean showSubmissionDetails;

    @SessionTransient
    private Tree<Object, String> medusaTree;

    private NotificationHelper<ProposalDevelopmentNotificationContext> notificationHelper;

    private List<String> unitRulesMessages = new ArrayList<>();

    private ProposalDevelopmentBudgetExt selectedBudget;
    private boolean sendNarrativeChangeNotification;

    @Transient
    private transient List<DisclosureProjectStatus> disclosureProjectStatuses;
    @Transient
    private transient DisclosureStatusRetrievalService disclosureStatusRetrievalService;

    public ProposalPersonQuestionnaireHelper getProposalPersonQuestionnaireHelper() {
        return proposalPersonQuestionnaireHelper;
    }

    public void setProposalPersonQuestionnaireHelper(ProposalPersonQuestionnaireHelper proposalPersonQuestionnaireHelper) {
        this.proposalPersonQuestionnaireHelper = proposalPersonQuestionnaireHelper;
    }

    public ProposalDevelopmentDocumentForm() {
        super();
    }

    public void initialize() {
        specialReviewHelper = new SpecialReviewHelper(getProposalDevelopmentDocument(), true);
        specialReviewHelper.prepareView();

        questionnaireHelper = new ProposalDevelopmentQuestionnaireHelper(getProposalDevelopmentDocument());

        s2sQuestionnaireHelper = new ProposalDevelopmentS2sQuestionnaireHelper(this);

        addKeyPersonHelper = new AddLineHelper();

        addRecipientHelper = new AddLineHelper();

        newS2sOpportunity = new S2sOpportunity();

        workingUserRoles = new ArrayList<>();

        editableCollectionLines = new HashMap<>();

        customDataHelper = new ProposalDevelopmentCustomDataHelper(this.getProposalDevelopmentDocument());

        dataValidationItems = new ArrayList<>();

        creditSplitListItems = new ArrayList<>();

        proposalDevelopmentAttachmentHelper = new ProposalDevelopmentAttachmentHelper();

        addOrganizationHelper = new OrganizationAddWizardHelper();

        customDataGroups = new ArrayList<>();

        notificationHelper = new NotificationHelper<>();

        s2sUserAttachedForm = new S2sUserAttachedForm();

        newProposalChangedData = new ProposalChangedData();

        narrativeUserRights = new ArrayList<>();

        proposalDevelopmentRejectionBean = new ProposalDevelopmentActionBean();
        proposalDevelopmentApprovalBean = new ProposalDevelopmentActionBean();
        populatePersonEditableFields();

        updateAnswerHeader = new AnswerHeader();

        sponsorFormTemplates = new ArrayList<>();

        reportHelper = new ReportHelper();

        hierarchyDevelopmentProposals = new ArrayList<>();

        printS2sOppForms = new ArrayList<>();
    }

    public int findIndexOfPageId(List<Action> actions) {
        return findIndexOfPageId(actions, getPageId());
    }

    public int findIndexOfPageId(List<Action> actions, String pageId) {
        for (int i = 0, len = actions.size(); i < len; i++) {
            Action action = actions.get(i);
            if (StringUtils.equals(pageId, action.getNavigateToPageId())) {
                return i;
            }
        }
        return 0;
    }

    public List<Action> getOrderedNavigationActions() {
        List<Action> actions = new ArrayList<>();
        addAllActions(actions, view.getNavigation().getItems());
        return actions;
    }

    protected void addAllActions(List<Action> actionList, List<? extends Component> components) {
        if (components != null) {
            for (Component component : components) {
                if (component instanceof ToggleMenu && component.isRender() && !component.isHidden()) {
                    ((ToggleMenu) component).getMenuItems().stream()
                            .filter(menuComponent -> menuComponent instanceof Action)
                            .forEach(menuComponent -> addActionIfAvailable((Action) menuComponent, actionList));
                } else if (component instanceof Action) {
                    addActionIfAvailable((Action) component, actionList);
                }
            }
        }
    }

    protected void addActionIfAvailable(Action action, List<Action> actionList) {
        if (!action.isDisabled() && action.isRender() && !action.isHidden()) {
            actionList.add(action);
        }
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProposalDevelopmentDocument";
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) getDocument();
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return getProposalDevelopmentDocument().getDevelopmentProposal();
    }

    public SpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }

    public void setSpecialReviewHelper(SpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
    }

    public ProposalDevelopmentQuestionnaireHelper getQuestionnaireHelper() {
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(ProposalDevelopmentQuestionnaireHelper questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }

    public AddLineHelper getAddKeyPersonHelper() {
        return addKeyPersonHelper;
    }

    public void setAddKeyPersonHelper(AddLineHelper addKeyPersonHelper) {
        this.addKeyPersonHelper = addKeyPersonHelper;
    }

    public AddLineHelper getAddRecipientHelper() {
        return addRecipientHelper;
    }

    public void setAddRecipientHelper(AddLineHelper addRecipientHelper) {
        this.addRecipientHelper = addRecipientHelper;
    }

    public S2sOpportunity getNewS2sOpportunity() {
        return newS2sOpportunity;
    }

    public void setNewS2sOpportunity(S2sOpportunity newOpportunity) {
        this.newS2sOpportunity = newOpportunity;
    }

    public Map<String, List<String>> getEditableCollectionLines() {
        return editableCollectionLines;
    }

    public void setEditableCollectionLines(Map<String, List<String>> editableCollectionLines) {
        this.editableCollectionLines = editableCollectionLines;
    }

    public List<ProposalUserRoles> getWorkingUserRoles() {
        return workingUserRoles;
    }

    public void setWorkingUserRoles(List<ProposalUserRoles> workingUserRoles) {
        this.workingUserRoles = workingUserRoles;
    }

    public ProposalDevelopmentCustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(ProposalDevelopmentCustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }

    public List<ProposalDevelopmentCustomDataGroupDto> getCustomDataGroups() {
        return customDataGroups;
    }

    public void setCustomDataGroups(List<ProposalDevelopmentCustomDataGroupDto> customDataGroups) {
        this.customDataGroups = customDataGroups;
    }

    public List<DataValidationItem> getDataValidationItems() {
        return dataValidationItems;
    }

    public void setDataValidationItems(List<DataValidationItem> dataValidationItems) {
        this.dataValidationItems = dataValidationItems;
    }

    public List<ProposalPerson> getPersonnelWhoRequireCertification() {
        ProposalDevelopmentPermissionsService permissionsService = getProposalDevelopmentPermissionsService();
        return getDevelopmentProposal().getProposalPersons().stream().
                filter(permissionsService::doesPersonRequireCertification)
                .collect(Collectors.toList());
    }

    public List<ProposalPerson> getPersonnelWhoRequireDisclosure() {
    	ProposalPersonCoiIntegrationService proposalPersonCoiIntegrationService = getProposalPersonCoiIntegrationService();
    	return getPersonnelWhoRequireCertification().stream().filter(person -> {
    		String coiStatus = proposalPersonCoiIntegrationService.getProposalPersonCoiStatus(person);
    		return !coiStatus.equals(CoiConstants.CERTIFICATION_INCOMPLETE) && !coiStatus.equals(CoiConstants.DISCLOSURE_NOT_REQUIRED);
    	}).collect(Collectors.toList());
    }
    
    private ProposalPersonCoiIntegrationService getProposalPersonCoiIntegrationService() {
        return KcServiceLocator.getService(ProposalPersonCoiIntegrationService.class);
    }
    
    private ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
        return KcServiceLocator.getService(ProposalDevelopmentPermissionsService.class);
    }

    public OrganizationAddWizardHelper getAddOrganizationHelper() {
		return addOrganizationHelper;
	}

    public List<ProposalCreditSplitListDto> getCreditSplitListItems() {
        return creditSplitListItems;
    }

    public void setCreditSplitListItems(List<ProposalCreditSplitListDto> creditSplitListItems) {
        this.creditSplitListItems = creditSplitListItems;
    }

	public void setAddOrganizationHelper(
			OrganizationAddWizardHelper addOrganizationHelper) {
		this.addOrganizationHelper = addOrganizationHelper;
	}

    public Tree<Object, String> getMedusaTreeView() {
    	if (medusaTree == null) {
			medusaTree = new Tree<>();
			MedusaNode rootNode = new MedusaNode();
			rootNode.setNodeLabel("Medusa Tree");
			medusaTree.setRootElement(rootNode);
			if (getDevelopmentProposal().getProposalNumber() != null) {
			    rootNode.setChildNodes(getMedusaService().getMedusaByProposal("DP", Long.valueOf(getDevelopmentProposal().getProposalNumber())));
			}
			return medusaTree;
    	}
    	return medusaTree;
    }

    /**
     * Creates the list of <code>{@link org.kuali.coeus.common.framework.person.attr.PersonEditableField}</code> field names.
     */
    public void populatePersonEditableFields() {
        setPersonEditableFields(new HashMap<>());

        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("moduleCode", CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        Collection<PersonEditableField> fields = getLegacyDataAdapter().findMatching(PersonEditableField.class, fieldValues);
        for (PersonEditableField field : fields) {
            getPersonEditableFields().put(field.getFieldName(), field.isActive());
        }
    }

    private LegacyDataAdapter getLegacyDataAdapter() {
        if (legacyDataAdapter == null) {
            legacyDataAdapter = KcServiceLocator.getService(LegacyDataAdapter.class);
        }
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

    protected MedusaService getMedusaService() {
    	if (medusaService == null) {
    		medusaService = KcServiceLocator.getService(MedusaService.class);
    	}
    	return medusaService;
    }

    public void setMedusaService(MedusaService medusaService) {
    	this.medusaService = medusaService;
    }

	public AddBudgetDto getAddBudgetDto() {
		return addBudgetDto;
	}

	public void setAddBudgetDto(AddBudgetDto addBudgetDto) {
		this.addBudgetDto = addBudgetDto;
	}

    public ProposalCopyCriteria getProposalCopyCriteria (){return proposalCopyCriteria;}

    public void setProposalCopyCriteria(ProposalCopyCriteria proposalCopyCriteria) {
        this.proposalCopyCriteria = proposalCopyCriteria;
    }
    public ProposalDevelopmentAttachmentHelper getProposalDevelopmentAttachmentHelper() {
        return proposalDevelopmentAttachmentHelper;
    }

    public void setProposalDevelopmentAttachmentHelper(ProposalDevelopmentAttachmentHelper proposalDevelopmentAttachmentHelper) {
        this.proposalDevelopmentAttachmentHelper = proposalDevelopmentAttachmentHelper;
    }

    public ProposalDevelopmentS2sQuestionnaireHelper getS2sQuestionnaireHelper() {
        return s2sQuestionnaireHelper;
    }

    public void setS2sQuestionnaireHelper(ProposalDevelopmentS2sQuestionnaireHelper s2sQuestionnaireHelper) {
        this.s2sQuestionnaireHelper = s2sQuestionnaireHelper;
    }


    public ProposalChangedData getNewProposalChangedData() {
        return newProposalChangedData;
    }

    public void setNewProposalChangedData(ProposalChangedData newProposalChangedData) {
        this.newProposalChangedData = newProposalChangedData;
    }

    @Override
    public boolean isAuditActivated() {
        return auditActivated;
    }

    @Override
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

	public AddBudgetDto getCopyBudgetDto() {
		return copyBudgetDto;
	}

	public void setCopyBudgetDto(AddBudgetDto copyBudgetDto) {
		this.copyBudgetDto = copyBudgetDto;
	}

    public NotificationHelper<ProposalDevelopmentNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<ProposalDevelopmentNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public S2sUserAttachedForm getS2sUserAttachedForm() {
        return s2sUserAttachedForm;
    }

    public void setS2sUserAttachedForm(S2sUserAttachedForm s2sUserAttachedForm) {
        this.s2sUserAttachedForm = s2sUserAttachedForm;
    }

    public List<NarrativeUserRights> getNarrativeUserRights() {
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }

    public String getNarrativeUserRightsSelectedAttachment() {
        return narrativeUserRightsSelectedAttachment;
    }

    public void setNarrativeUserRightsSelectedAttachment(String narrativeUserRightsSelectedAttachment) {
        this.narrativeUserRightsSelectedAttachment = narrativeUserRightsSelectedAttachment;
    }

    public MessageMap getDeferredMessages() {
        return deferredMessages;
    }

    public void setDeferredMessages(MessageMap deferredMessages) {
        this.deferredMessages = deferredMessages;
    }

    public String getDefaultOpenTab() {
        return defaultOpenTab;
    }

    public void setDefaultOpenTab(String defaultOpenTab) {
        this.defaultOpenTab = defaultOpenTab;
    }

    public Map<String, Boolean> getPersonEditableFields() {
        if (personEditableFields == null) {
            populatePersonEditableFields();
        }
        return personEditableFields;
    }

    public void setPersonEditableFields(Map<String, Boolean> personEditableFields) {
        this.personEditableFields = personEditableFields;
    }

    public ProposalDevelopmentActionBean getProposalDevelopmentRejectionBean() {
        return proposalDevelopmentRejectionBean;
    }

    public void setProposalDevelopmentRejectionBean(ProposalDevelopmentActionBean proposalDevelopmentRejectionBean) {
        this.proposalDevelopmentRejectionBean = proposalDevelopmentRejectionBean;
    }

    public ProposalDevelopmentActionBean getProposalDevelopmentApprovalBean() {
        return proposalDevelopmentApprovalBean;
    }

    public void setProposalDevelopmentApprovalBean(ProposalDevelopmentActionBean proposalDevelopmentApprovalBean) {
        this.proposalDevelopmentApprovalBean = proposalDevelopmentApprovalBean;
    }

    public S2sAppSubmission getDisplayedS2sAppSubmission() {
        return this.getDevelopmentProposal().getDisplayedS2sAppSubmission();
    }

    public AnswerHeader getUpdateAnswerHeader() {
        return updateAnswerHeader;
    }

    public void setUpdateAnswerHeader(AnswerHeader updateAnswerHeader) {
        this.updateAnswerHeader = updateAnswerHeader;
    }

    public List<SponsorFormTemplateList> getSponsorFormTemplates() {
        return sponsorFormTemplates;
    }

    public void setSponsorFormTemplates(List<SponsorFormTemplateList> sponsorFormTemplates) {
        this.sponsorFormTemplates = sponsorFormTemplates;
    }

    public List<DevelopmentProposal> getHierarchyDevelopmentProposals() {
        return hierarchyDevelopmentProposals;
    }

    public void setHierarchyDevelopmentProposals(List<DevelopmentProposal> hierarchyDevelopmentProposals) {
        this.hierarchyDevelopmentProposals = hierarchyDevelopmentProposals;
    }
    public boolean isSendOverrideNotification() {
        return sendOverrideNotification;
    }

    public void setSendOverrideNotification(boolean proposalChangedDataSendNotification) {
        this.sendOverrideNotification = proposalChangedDataSendNotification;
    }

    public void setSendNarrativeChangeNotification(boolean send) {
        this.sendNarrativeChangeNotification = send;
    }

    public boolean isSendNarrativeChangeNotification() {
        return sendNarrativeChangeNotification;
    }

    public ReportHelper getReportHelper() {
        return reportHelper;
    }

    public void setReportHelper(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    public String getResubmissionOption() {
		return resubmissionOption;
	}

	public void setResubmissionOption(String resubmissionOption) {
		this.resubmissionOption = resubmissionOption;
	}

	public String getInstitutionalProposalToVersion() {
		return institutionalProposalToVersion;
	}

	public void setInstitutionalProposalToVersion(
			String institutionalProposalToVersion) {
		this.institutionalProposalToVersion = institutionalProposalToVersion;
	}

	public List<String> getUnitRulesMessages() {
		return unitRulesMessages;
	}

	public void setUnitRulesMessages(List<String> unitRulesMessages) {
		this.unitRulesMessages = unitRulesMessages;
	}
    public boolean isUnitRulesErrorsExist() {
        return getUnitRulesErrors().size() > 0;
    }
    public List<String> getUnitRulesErrors() {
        return getUnitRulesMessages(KcKrmsConstants.MESSAGE_TYPE_ERROR);
    }
    protected List<String> getUnitRulesMessages(String messageType) {
        List<String> messages = new ArrayList<>();
        for (String message : this.unitRulesMessages) {
            if (StringUtils.substringBefore(message, KcKrmsConstants.MESSAGE_SEPARATOR).equals(messageType)) {
                messages.add(StringUtils.substringAfter(message, KcKrmsConstants.MESSAGE_SEPARATOR));
            }
        }
        return messages;
    }

	public boolean isGrantsGovSubmitFlag() {
		return grantsGovSubmitFlag;
	}

	public void setGrantsGovSubmitFlag(boolean grantsGovSubmitFlag) {
		this.grantsGovSubmitFlag = grantsGovSubmitFlag;
	}

	public boolean isShowSubmissionDetails() {
		return showSubmissionDetails;
	}

	public void setShowSubmissionDetails(boolean showSubmissionDetails) {
		this.showSubmissionDetails = showSubmissionDetails;
	}

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    @Override
    public ProposalDevelopmentBudgetExt getSelectedBudget() {
        return selectedBudget;
    }

    @Override
    public void setSelectedBudget(ProposalDevelopmentBudgetExt selectedBudget) {
        this.selectedBudget = selectedBudget;
    }

	public String getHierarchyBudgetTypeCode() {
		return hierarchyBudgetTypeCode;
	}

	public void setHierarchyBudgetTypeCode(String hierarchyBudgetTypeCode) {
		this.hierarchyBudgetTypeCode = hierarchyBudgetTypeCode;
	}

	public String getHierarchyProposalNumber() {
		return hierarchyProposalNumber;
	}

	public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
		this.hierarchyProposalNumber = hierarchyProposalNumber;
	}

    public String getShortUrl() {
        String host = getConfigurationService().getPropertyValueAsString("application.url");
        return host + "/kc-common/development-proposals/" + getDevelopmentProposal().getProposalNumber();
    }

    protected ConfigurationService getConfigurationService() {
        return KcServiceLocator.getService(ConfigurationService.class);
    }

    public boolean isCanEdit() {
        if (isCanEditView() == null) {
            return false;
        }
        return isCanEditView();
    }

    public List<S2sOppForms> getPrintS2sOppForms() {
        return this.printS2sOppForms;
    }

    public void setPrintS2sOppForms(List<S2sOppForms> printS2sOppForms) {
        this.printS2sOppForms = printS2sOppForms;
    }

    public List<DisclosureProjectStatus> getDisclosureProjectStatuses() {
        if (disclosureProjectStatuses == null) {
            disclosureProjectStatuses = getDisclosureStatusRetrievalService().getDisclosureStatusesForProject(
                    Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                    getDevelopmentProposal().getProposalNumber()
                    );
        }
        return disclosureProjectStatuses;
    }


    protected DisclosureStatusRetrievalService getDisclosureStatusRetrievalService() {
        if (disclosureStatusRetrievalService == null) {
            disclosureStatusRetrievalService = KcServiceLocator.getService(DisclosureStatusRetrievalService.class);
        }
        return disclosureStatusRetrievalService;
    }
}
