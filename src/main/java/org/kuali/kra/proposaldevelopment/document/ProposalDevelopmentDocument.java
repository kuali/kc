/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.document;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.kra.workflow.KraDocumentXMLMaterializer;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.workflow.DocumentInitiator;
import org.kuali.rice.kns.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.kns.workflow.KualiTransactionalDocumentInformation;

public class ProposalDevelopmentDocument extends BudgetParentDocument implements Copyable, SessionDocument, Permissionable {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDocument.class);

    public static final String DOCUMENT_TYPE_CODE = "PRDV";

    private static final long serialVersionUID = 2958631745964610527L;
    private List<DevelopmentProposal> developmentProposalList;
    private List<BudgetDocumentVersion> budgetDocumentVersions;
    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    private static final String RETURN_TO_PROPOSAL_ALT_TEXT = "return to proposal";
    private static final String RETURN_TO_PROPOSAL_METHOD_TO_CALL = "methodToCall.returnToProposal";

    public ProposalDevelopmentDocument() {
        super();

        developmentProposalList = new ArrayList<DevelopmentProposal>();
        DevelopmentProposal newProposal = new DevelopmentProposal(); 
        newProposal.setProposalDocument(this);
        developmentProposalList.add(newProposal);
        budgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
    }

    public List<DevelopmentProposal> getDevelopmentProposalList() {
        return developmentProposalList;
    }

    public void setDevelopmentProposalList(List<DevelopmentProposal> proposalList) {
        this.developmentProposalList = proposalList;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposalList.get(0);
    }

    public void setDevelopmentProposal(DevelopmentProposal proposal) {
        developmentProposalList.set(0, proposal);
    }

    @Override
    public void initialize() {
        super.initialize();
        getDevelopmentProposal().initializeOwnedByUnitNumber();
    }

    @Override
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO dto) {
        super.doRouteStatusChange(dto);

        ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
        getDevelopmentProposal().setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(this, true));
    }


    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }


    /**
     * Wraps a document in an instance of KualiDocumentXmlMaterializer, that provides additional metadata for serialization
     * 
     * @see org.kuali.core.document.Document#wrapDocumentWithMetadataForXmlSerialization()
     */
    @Override
    // This method should go away in favor of using DD workflowProperties bean to serialize properties
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        ProposalAuthorizationService proposalauthservice = KraServiceLocator.getService(ProposalAuthorizationService.class);
        KualiTransactionalDocumentInformation transInfo = new KualiTransactionalDocumentInformation();
        DocumentInitiator initiatior = new DocumentInitiator();
        // String initiatorNetworkId = getDocumentHeader().getWorkflowDocument().getInitiatorNetworkId();
        // try {
        // UniversalUser initiatorUser = KNSServiceLocator.getUniversalUserService().getUniversalUser(new
        // AuthenticationUserId(initiatorNetworkId));
        // initiatorUser.getModuleUsers(); // init the module users map for serialization
        // initiatior.setUniversalUser(initiatorUser);
        // }
        // catch (UserNotFoundException e) {
        // throw new RuntimeException(e);
        // }
        transInfo.setDocumentInitiator(initiatior);
        KraDocumentXMLMaterializer xmlWrapper = new KraDocumentXMLMaterializer();
        // KualiDocumentXmlMaterializer xmlWrapper = new KualiDocumentXmlMaterializer();
        xmlWrapper.setDocument(this);
        xmlWrapper.setKualiTransactionalDocumentInformation(transInfo);
        xmlWrapper.setRolepersons(proposalauthservice.getAllRolePersons(this));
        return xmlWrapper;

    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        getDevelopmentProposal().updateS2sOpportunity();

        KraServiceLocator.getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);

        if (getBudgetDocumentVersions() != null) {
            updateDocumentDescriptions(getBudgetDocumentVersions());
        }
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(this.getDevelopmentProposal());

        getDevelopmentProposal().updateProposalChangeHistory();
    }

    public void setAllowsNoteAttachments(boolean allowsNoteAttachments) {
        getDevelopmentProposal().setAllowsNoteAttachments(allowsNoteAttachments);
    }
    

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getRoleNames()
     */
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        roleNames.add(RoleConstants.AGGREGATOR);
        roleNames.add(RoleConstants.BUDGET_CREATOR);
        roleNames.add(RoleConstants.NARRATIVE_WRITER);
        roleNames.add(RoleConstants.VIEWER);
        roleNames.add("approver");

        return roleNames;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentNumberForPermission()
     */
    public String getDocumentNumberForPermission() {
        return getDevelopmentProposal().getProposalNumber();
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentKey()
     */
    public String getDocumentKey() {
        return Permissionable.PROPOSAL_KEY;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.addAll(getDevelopmentProposal().buildListOfDeletionAwareLists());
        managedLists.add(developmentProposalList);
        return managedLists;
    }

    /**
     * Sets the budgetDocumentVersions attribute value.
     * @param budgetDocumentVersions The budgetDocumentVersions to set.
     */
    public void setBudgetDocumentVersions(List<BudgetDocumentVersion> budgetDocumentVersions) {
        this.budgetDocumentVersions = budgetDocumentVersions;
    }

    /**
     * Gets the budgetDocumentVersions attribute. 
     * @return Returns the budgetDocumentVersions.
     */
    public List<BudgetDocumentVersion> getBudgetDocumentVersions() {
        return budgetDocumentVersions;
    }

    @Override
    public String getActivityTypeCode() {
        return getDevelopmentProposal().getActivityTypeCode();
    }

    @Override
    public String getBudgetStatus() {
        return getDevelopmentProposal().getBudgetStatus();
    }

    @Override
    public Task getParentAuthZTask(String taskName) {
        return new ProposalTask(taskName,this);
    }

    @Override
    public Date getRequestedEndDateInitial() {
        return getDevelopmentProposal().getRequestedEndDateInitial();
    }

    @Override
    public Date getRequestedStartDateInitial() {
        return getDevelopmentProposal().getRequestedStartDateInitial();
    }

    @Override
    public boolean isComplete() {
        return getDevelopmentProposal().isProposalComplete();
    }

    @Override
    public void setBudgetStatus(String budgetStatus) {
        getDevelopmentProposal().setBudgetStatus(budgetStatus);
    }

    @Override
    public ActivityType getActivityType() {
        return getDevelopmentProposal().getActivityType();
    }

    @Override
    public Unit getUnit() {
        return getDevelopmentProposal().getOwnedByUnit();
    }

    @Override
    public List getPersonRolodexList() {
        return getDevelopmentProposal().getProposalPersons();
    }

    @Override
    public String getUnitNumber() {
        return getDevelopmentProposal().getOwnedByUnitNumber();
    }

    @Override
    public Map<String, String> getNihDescription() {
        return getDevelopmentProposal().getNihDescription();
    }

    @Override
    public PersonRolodex getProposalEmployee(String personId) {
        return getDevelopmentProposal().getProposalEmployee(personId);
    }

    @Override
    public ProposalPersonRole getProposalEmployeeRole(String personId) {
        return getDevelopmentProposal().getProposalEmployeeRole(personId);
    }

    @Override
    public PersonRolodex getProposalNonEmployee(Integer rolodexId) {
        return getDevelopmentProposal().getProposalNonEmployee(rolodexId);
    }

    @Override
    public ProposalPersonRole getProposalNonEmployeeRole(Integer rolodexId) {
        return getDevelopmentProposal().getProposalNonEmployeeRole(rolodexId);
    }

    @Override
    public boolean isNih() {
        return getDevelopmentProposal().isNih();
    }

    @Override
    public void saveBudgetFinalVersionStatus(BudgetDocument budgetDocument) {
        getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);
    }

    @Override
    public void processAfterRetrieveForBudget(BudgetDocument budgetDocument) {
        getService(ProposalStatusService.class).loadBudgetStatusByProposalDocumentNumber(getDocumentNumber());
    }

    @Override
    public String getTaskGroupName() {
        return TaskGroupName.PROPOSAL_BUDGET;
    }

    @Override
    public ExtraButton configureReturnToParentTopButton() {
        ExtraButton returnToProposalButton = new ExtraButton();
        returnToProposalButton.setExtraButtonProperty(RETURN_TO_PROPOSAL_METHOD_TO_CALL);
        returnToProposalButton.setExtraButtonSource(buildExtraButtonSourceURI("tinybutton-retprop.gif"));
        returnToProposalButton.setExtraButtonAltText(RETURN_TO_PROPOSAL_ALT_TEXT);
        
        return returnToProposalButton;
    }

    /**
     * This method does what its name says
     * @param buttonFileName
     * @return
     */
    private String buildExtraButtonSourceURI(String buttonFileName) {
        return lookupKualiConfigurationService().getPropertyString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    private KualiConfigurationService lookupKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }

}
