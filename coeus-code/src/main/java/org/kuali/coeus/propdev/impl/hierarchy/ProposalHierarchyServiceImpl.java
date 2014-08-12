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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeStatus;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyPostProcessor;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardAttachment;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kns.service.SessionDocumentService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.*;

@Component("proposalHierarchyService")
@Transactional
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {
    
    private static final Log LOG = LogFactory.getLog(ProposalHierarchyServiceImpl.class);

   @Autowired
   @Qualifier("businessObjectService")
   private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;
    @Autowired
    @Qualifier("proposalHierarchyDao")
    private ProposalHierarchyDao proposalHierarchyDao;
    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService legacyNarrativeService;
    @Autowired
    @Qualifier("proposalBudgetService")
    private BudgetService budgetService;
    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;
    @Autowired
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;
    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;
    @Autowired
    @Qualifier("kcDocumentRejectionService")
    private KcDocumentRejectionService kcDocumentRejectionService;
    @Autowired
    @Qualifier("knsSessionDocumentService")
    private SessionDocumentService knsSessionDocumentService;
    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;

    @Autowired
    @Qualifier("deepCopyPostProcessor")
    private DeepCopyPostProcessor deepCopyPostProcessor;
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    @Autowired
    @Qualifier("budgetPersonnelBudgetService")
    private BudgetPersonnelBudgetService budgetPersonnelBudgetService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    //Setters for dependency injection
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }
    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
    }
    public void setLegacyNarrativeService(LegacyNarrativeService narrativeService) {
        this.legacyNarrativeService = narrativeService;
    }
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }
    public void setKnsSessionDocumentService(SessionDocumentService sessionDocumentService) {
        this.knsSessionDocumentService = sessionDocumentService;
    }
    public void setKradWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.kradWorkflowDocumentService = workflowDocumentService;
    }
    public void setKcDocumentRejectionService(KcDocumentRejectionService kcDocumentRejectionService) {
        this.kcDocumentRejectionService = kcDocumentRejectionService;
    }

    public void setBudgetCalculationService (BudgetCalculationService budgetCalculationService){
        this.budgetCalculationService = budgetCalculationService;
    }
    public  void setBudgetPersonnelBudgetService (BudgetPersonnelBudgetService budgetPersonnelBudgetService){
        this.budgetPersonnelBudgetService = budgetPersonnelBudgetService;
    }
    public void setDeepCopyPostProcessor (DeepCopyPostProcessor deepCopyPostProcessor){
        this.deepCopyPostProcessor = deepCopyPostProcessor;
    }

    @Override
    public String createHierarchy(DevelopmentProposal initialChild) throws ProposalHierarchyException {
        LOG.info(String.format("***Create Hierarchy using Proposal #%s", initialChild.getProposalNumber()));
        if (initialChild.isInHierarchy()) {
            throw new ProposalHierarchyException("Cannot create hierarchy: proposal " + initialChild.getProposalNumber()
                    + " is already a member of a hierarchy.");
        }

        // create a new proposal document
        ProposalDevelopmentDocument newDoc;
        
        // manually assembling a new PDDoc here because the DocumentService will deny initiator permission without context
        // since a person with MAINTAIN_PROPOSAL_HIERARCHY permission is allowed to initiate IF they are creating a parent
        // we circumvent the initiator step altogether. 
        try {
            WorkflowDocument workflowDocument = kradWorkflowDocumentService.createWorkflowDocument(PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE, globalVariableService.getUserSession().getPerson());
            knsSessionDocumentService.addDocumentToUserSession(globalVariableService.getUserSession(), workflowDocument);
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
            newDoc = new ProposalDevelopmentDocument();
            newDoc.setDocumentHeader(documentHeader);
            newDoc.setDocumentNumber(documentHeader.getDocumentNumber());
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error creating new document: " + x);
        }
        
        // copy the initial information to the new parent proposal
        DevelopmentProposal hierarchy = newDoc.getDevelopmentProposal();
        copyInitialData(hierarchy, initialChild);
        hierarchy.setHierarchyStatus(HierarchyStatusConstants.Parent.code());
        String docDescription = initialChild.getProposalDocument().getDocumentHeader()
                .getDocumentDescription();
        newDoc.getDocumentHeader().setDocumentDescription(docDescription);

        // persist the document and add a budget
        try {
            documentService.saveDocument(newDoc);
            budgetService.addBudgetVersion(newDoc, "Hierarchy Budget", Collections.EMPTY_MAP);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }
        LOG.info(String.format("***New Hierarchy Parent (#%s) budget created", hierarchy.getProposalNumber()));
        
        // add aggregator to the document
        String userId = globalVariableService.getUserSession().getPrincipalId();
        kcAuthorizationService.addRole(userId, RoleConstants.AGGREGATOR, newDoc);

        initializeBudget(hierarchy, initialChild);

        prepareHierarchySync(hierarchy);

        // link the child to the parent
        linkChild(hierarchy, initialChild, HierarchyBudgetTypeConstants.SubBudget.code());
        setInitialPi(hierarchy, initialChild);
        copyInitialAttachments(initialChild, hierarchy);
        aggregateHierarchy(hierarchy);
        LOG.info(String.format("***Initial Child (#%s) linked to Parent (#%s)", initialChild.getProposalNumber(), hierarchy.getProposalNumber()));
        
        finalizeHierarchySync(hierarchy);
        
        // return the parent id
        LOG.info(String.format("***Hierarchy creation (#%s) complete", hierarchy.getProposalNumber()));
        return hierarchy.getProposalNumber();
    }

    @Override
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) throws ProposalHierarchyException {
        LOG.info(String.format("***Linking Child (#%s) linked to Parent (#%s)", newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
        if (!hierarchyProposal.isParent()) {
            throw new ProposalHierarchyException("Proposal " + hierarchyProposal.getProposalNumber()
                    + " is not a hierarchy parent");
        }
        if (newChildProposal.isInHierarchy()) {
            throw new ProposalHierarchyException("Proposal " + newChildProposal.getProposalNumber()
                    + " is already a member of a hierarchy");
        }
        prepareHierarchySync(hierarchyProposal);
        linkChild(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
        finalizeHierarchySync(hierarchyProposal);
        LOG.info(String.format("***Linking Child (#%s) linked to Parent (#%s) complete", newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
    }

    @Override
    public void removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String hierarchyProposalNumber = childProposal.getHierarchyParentProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDoc = getHierarchyBudget(hierarchyProposal);
        Budget hierarchyBudget = hierarchyBudgetDoc.getBudget();
        LOG.info(String.format("***Removing Child (#%s) from Parent (#%s)", childProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
        
        
        List<String> childProposalNumbers = proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        boolean isLast = childProposalNumbers.size()==1 && StringUtils.equals(childProposalNumbers.get(0), childProposal.getProposalNumber());
     
        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        childProposal.setHierarchyParentProposalNumber(null);
        if (StringUtils.equalsIgnoreCase(hierarchyProposal.getHierarchyOriginatingChildProposalNumber(), childProposal.getProposalNumber())) {
            hierarchyProposal.getPrincipalInvestigator().setHierarchyProposalNumber(null);
        }
        removeChildElements(hierarchyProposal, hierarchyBudget, childProposal.getProposalNumber());

        try {
            documentService.saveDocument(hierarchyBudgetDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        
        if (isLast) {
            try {
                LOG.info(String.format("***Child (#%s) was last child, cancelling Parent (#%s)", childProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
                businessObjectService.save(childProposal);
                Document doc = documentService.getByDocumentHeaderId(hierarchyProposal.getProposalDocument().getDocumentNumber());
                documentService.cancelDocument(doc, "Removed last child from Proposal Hierarchy");
            }
            catch (WorkflowException e) {
                throw new ProposalHierarchyException("Error cancelling empty parent proposal");
            }
        }
        else {
            //need to find out what the lowest number is
            //so we can make it the new primary child for budget syncs.
            String lowestProposalNumber = "";
            for( String proposalNumber : childProposalNumbers ) {
                if ( !StringUtils.equals(proposalNumber, childProposal.getProposalNumber() )) {
                    lowestProposalNumber = proposalNumber;
                    break;
                }
            }
            hierarchyProposal.setHierarchyOriginatingChildProposalNumber(lowestProposalNumber);
            businessObjectService.save(childProposal);
            synchronizeAllChildren(hierarchyProposal);
        }
        LOG.info(String.format("***Removing Child (#%s) from Parent (#%s) complete", childProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
    }

    @Override
    public void synchronizeAllChildren(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        prepareHierarchySync(pdDoc);
        synchronizeAll(pdDoc.getDevelopmentProposal());
        finalizeHierarchySync(pdDoc);
    }

    protected void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        prepareHierarchySync(hierarchyProposal);
        synchronizeAll(hierarchyProposal);
        finalizeHierarchySync(hierarchyProposal);
    }

    protected void synchronizeAll(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;
        LOG.info(String.format("***Synchronizing all Children of Parent (#%s)", hierarchyProposal.getProposalNumber()));
        if (!hierarchyProposal.isParent()) {
            throw new ProposalHierarchyException("Proposal " + hierarchyProposal.getProposalNumber()
                    + " is not a hierarchy parent");
        }
        changed = synchronizeAllChildProposals(hierarchyProposal);
        if (changed) {
            aggregateHierarchy(hierarchyProposal);
        }
        LOG.info(String.format("***Synchronizing all Children of Parent (#%s) complete", hierarchyProposal.getProposalNumber()));
    }

    @Override
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());
        LOG.info(String.format("***Synchronizing Child (#%s) of Parent (#%s)", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
        
        prepareHierarchySync(hierarchy);
        boolean changed = synchronizeChildProposal(hierarchy, childProposal);
        if (changed) {
            aggregateHierarchy(hierarchy);
        }
        finalizeHierarchySync(hierarchy);
        LOG.info(String.format("***Synchronizing Child (#%s) of Parent (#%s) complete", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
    }
    
    
    public void synchronizeChildProposalBudget(ProposalDevelopmentBudgetExt budget, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());
        LOG.info(String.format("***Synchronizing Child Budget (#%s) of Parent (#%s)", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
        
        prepareHierarchySync(hierarchy);
        boolean changed = synchronizeChildProposalBudget(hierarchy, budget, childProposal);
        if (changed) {
            aggregateHierarchy(hierarchy);
        }
        finalizeHierarchySync(hierarchy);
        LOG.info(String.format("***Synchronizing Child Budget (#%s) of Parent (#%s) complete", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
    }
    
    @Override
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        return getHierarchy(childProposal.getHierarchyParentProposalNumber());
    }

    protected void linkChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode)
            throws ProposalHierarchyException {
        // set child to child status
        newChildProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        newChildProposal.setHierarchyParentProposalNumber(hierarchyProposal.getProposalNumber());
        newChildProposal.setHierarchyBudgetType(hierarchyBudgetTypeCode);
        // call synchronize
        synchronizeChildProposal(hierarchyProposal, newChildProposal);
        // call aggregate
        aggregateHierarchy(hierarchyProposal);        
    }

    protected void copyInitialData(DevelopmentProposal hierarchyProposal, DevelopmentProposal srcProposal)
            throws ProposalHierarchyException {
        // Required fields for saving document
        hierarchyProposal.setHierarchyOriginatingChildProposalNumber(srcProposal.getProposalNumber());
        hierarchyProposal.setSponsor(srcProposal.getSponsor());
        hierarchyProposal.setSponsorCode(srcProposal.getSponsorCode());
        hierarchyProposal.setProposalTypeCode(srcProposal.getProposalTypeCode());
        hierarchyProposal.setRequestedStartDateInitial(srcProposal.getRequestedStartDateInitial());
        hierarchyProposal.setRequestedEndDateInitial(srcProposal.getRequestedEndDateInitial());
        hierarchyProposal.setOwnedByUnit(srcProposal.getOwnedByUnit());
        hierarchyProposal.setOwnedByUnitNumber(srcProposal.getOwnedByUnitNumber());
        hierarchyProposal.setActivityType(srcProposal.getActivityType());
        hierarchyProposal.setActivityTypeCode(srcProposal.getActivityTypeCode());
        hierarchyProposal.setTitle(srcProposal.getTitle());

        // Sponsor & program information
        hierarchyProposal.setDeadlineDate(srcProposal.getDeadlineDate());
        hierarchyProposal.setDeadlineTime(srcProposal.getDeadlineTime());
        hierarchyProposal.setDeadlineType(srcProposal.getDeadlineType());
        hierarchyProposal.setAnticipatedAwardTypeCode(srcProposal.getAnticipatedAwardTypeCode());
        hierarchyProposal.setNoticeOfOpportunityCode(srcProposal.getNoticeOfOpportunityCode());
        hierarchyProposal.setCfdaNumber(srcProposal.getCfdaNumber());
        hierarchyProposal.setPrimeSponsorCode(srcProposal.getPrimeSponsorCode());
        hierarchyProposal.setNsfCode(srcProposal.getNsfCode());
        hierarchyProposal.setSponsorProposalNumber(srcProposal.getSponsorProposalNumber());
        hierarchyProposal.setAgencyDivisionCode(srcProposal.getAgencyDivisionCode());
        hierarchyProposal.setAgencyProgramCode(srcProposal.getAgencyProgramCode());
        hierarchyProposal.setSubcontracts(srcProposal.getSubcontracts());
        hierarchyProposal.setProgramAnnouncementNumber(srcProposal.getProgramAnnouncementNumber());
        hierarchyProposal.setProgramAnnouncementTitle(srcProposal.getProgramAnnouncementTitle());

        // Organization/location
        ProposalSite newSite;
        hierarchyProposal.getProposalSites().clear();
        for (ProposalSite site : srcProposal.getProposalSites()) {
            newSite = (ProposalSite)ObjectUtils.deepCopy(site);
            newSite.setProposalNumber(null);
            newSite.setVersionNumber(null);
            for (CongressionalDistrict cd : newSite.getCongressionalDistricts()) {
                cd.setProposalNumber(null);
                cd.setId(null);
                cd.setVersionNumber(null);
            }
            hierarchyProposal.addProposalSite(newSite);
        }
            
        // Delivery info
        hierarchyProposal.setMailBy(srcProposal.getMailBy());
        hierarchyProposal.setMailType(srcProposal.getMailType());
        hierarchyProposal.setMailAccountNumber(srcProposal.getMailAccountNumber());
        hierarchyProposal.setNumberOfCopies(srcProposal.getNumberOfCopies());
        hierarchyProposal.setMailingAddressId(srcProposal.getMailingAddressId());
        hierarchyProposal.setMailDescription(srcProposal.getMailDescription());
    }
    
    /**
     * Synchronizes all child proposals to the parent.
     * @param hierarchyProposal
     * @return
     * @throws ProposalHierarchyException
     */
    protected boolean synchronizeAllChildProposals(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;
        
        for (DevelopmentProposal childProposal : getHierarchyChildren(hierarchyProposal.getProposalNumber())) {

            List<PropScienceKeyword> oldKeywords = getOldKeywords(hierarchyProposal, childProposal);
            ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
            BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument = getHierarchyBudget(hierarchyProposal); 
            Budget hierarchyBudget = hierarchyBudgetDocument.getBudget();
            BudgetDocument<DevelopmentProposal> childBudgetDocument = getSyncableBudget(childProposal);
            ProposalDevelopmentBudgetExt childBudget = (ProposalDevelopmentBudgetExt) childBudgetDocument.getBudget();
            ObjectUtils.materializeAllSubObjects(hierarchyBudget);
            ObjectUtils.materializeAllSubObjects(childBudget);
            childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
            
            removeChildElements(hierarchyProposal, hierarchyBudget, childProposal.getProposalNumber());
            
            synchronizeKeywords(hierarchyProposal, childProposal, oldKeywords);
            synchronizeSpecialReviews(hierarchyProposal, childProposal);
            synchronizeNarratives(hierarchyProposal, childProposal);
            synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            businessObjectService.save(childProposal);
            
            synchronizeBudget(hierarchyProposal, childProposal, hierarchyBudget, childBudget, hierarchyBudgetDocument);
            changed = true;
        }
        
        return changed;
    }

    /**
     * Synchronizes the given child proposal to the parent.  
     * <p>
     * If a key protocol person appears in multiple child proposals and is removed from the given child 
     * proposal, then this also aggregates that key person back to the parent proposal from a different child proposal, making sure that all the key persons
     * in all of the child proposals are represented in the parent proposal.
     *  
     * @param hierarchyProposal
     * @param childProposal
     * @return
     * @throws ProposalHierarchyException
     */
    protected boolean synchronizeChildProposal(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        
        List<PropScienceKeyword> oldKeywords = getOldKeywords(hierarchyProposal, childProposal);
        ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument = getHierarchyBudget(hierarchyProposal); 
        Budget hierarchyBudget = hierarchyBudgetDocument.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getSyncableBudget(childProposal);
        ProposalDevelopmentBudgetExt childBudget = (ProposalDevelopmentBudgetExt) childBudgetDocument.getBudget();
        ObjectUtils.materializeAllSubObjects(hierarchyBudget);
        ObjectUtils.materializeAllSubObjects(childBudget);
        childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
        
        removeChildElements(hierarchyProposal, hierarchyBudget, childProposal.getProposalNumber());
        try {
            documentService.saveDocument(hierarchyBudgetDocument);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        
        synchronizeKeywords(hierarchyProposal, childProposal, oldKeywords);
        synchronizeSpecialReviews(hierarchyProposal, childProposal);
        synchronizeNarratives(hierarchyProposal, childProposal);
        synchronizePersonsAndAggregate(hierarchyProposal, childProposal, principalInvestigator);
        businessObjectService.save(childProposal);
        
        synchronizeBudget(hierarchyProposal, childProposal, hierarchyBudget, childBudget, hierarchyBudgetDocument);
        return true;
    }
    
    protected boolean synchronizeChildProposalBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument = getHierarchyBudget(hierarchyProposal); 
        Budget hierarchyBudget = hierarchyBudgetDocument.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getSyncableBudget(childProposal);
        if (!StringUtils.equals(childBudgetDocument.getDocumentNumber(), budget.getDocumentNumber())) {
            budget = (ProposalDevelopmentBudgetExt) childBudgetDocument.getBudget();
        }
        ObjectUtils.materializeAllSubObjects(hierarchyBudget);
        ObjectUtils.materializeAllSubObjects(budget);
        
        removeChildBudgetElements(hierarchyProposal, hierarchyBudget, childProposal.getProposalNumber());
        try {
            getDocumentService().saveDocument(hierarchyBudgetDocument);
        } catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        
        
        synchronizeBudget(hierarchyProposal, childProposal, hierarchyBudget, budget, hierarchyBudgetDocument);
        return true;
    }
    
    /**
     * Gets the old proposal science keywords before removing them from the parent.
     * @param hierarchyProposal
     * @param childProposal
     * @return
     */
    protected List<PropScienceKeyword> getOldKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        List<PropScienceKeyword> oldKeywords = new ArrayList<PropScienceKeyword>();
        for (PropScienceKeyword keyword : hierarchyProposal.getPropScienceKeywords()) {
            if (StringUtils.equals(childProposal.getProposalNumber(), keyword.getHierarchyProposalNumber())) {
                oldKeywords.add(keyword);
            }
        }
        return oldKeywords;
    }
    
    /**
     * Synchronizes the proposal science keywords from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     * @param oldKeywords
     */
    protected void synchronizeKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<PropScienceKeyword> oldKeywords) {
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal, keyword.getScienceKeyword());
            int index = oldKeywords.indexOf(newKeyword);
            if (index > -1) {
                newKeyword = oldKeywords.get(index);
            }
            newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.addPropScienceKeyword(newKeyword);
        }
    }
    
    /**
     * Synchronizes the proposal special reviews from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     */
    protected void synchronizeSpecialReviews(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = (ProposalSpecialReview) ObjectUtils.deepCopy(review);
            newReview.setId(null);
            newReview.setDevelopmentProposal(hierarchyProposal);
            newReview.setSpecialReviewNumber(hierarchyProposal.getProposalDocument().getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));            
            newReview.setVersionNumber(null);
            newReview.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.getPropSpecialReviews().add(newReview);
        }
    }
    
    /**
     * Synchronizes the proposal narratives from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     */
    protected void synchronizeNarratives(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        String instituteNarrativeTypeGroup = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, 
                PARAMETER_NAME_INSTITUTE_NARRATIVE_TYPE_GROUP);
        for (Narrative narrative : childProposal.getNarratives()) {
            if (!narrative.getNarrativeType().isAllowMultiple()
                    && !StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getNarrativeTypeGroup(), instituteNarrativeTypeGroup)) {
                Map<String,String> primaryKey = new HashMap<String,String>();            
                primaryKey.put("proposalNumber", narrative.getProposalNumber());
                primaryKey.put("moduleNumber", narrative.getModuleNumber() + "");
                NarrativeAttachment attachment = (NarrativeAttachment) businessObjectService.findByPrimaryKey(NarrativeAttachment.class, primaryKey);
                narrative.setNarrativeAttachment(attachment);
                
                Narrative newNarrative = (Narrative) ObjectUtils.deepCopy(narrative);
                newNarrative.setVersionNumber(null);
                newNarrative.setHierarchyProposalNumber(childProposal.getProposalNumber());
                legacyNarrativeService.addNarrative(hierarchyProposal.getProposalDocument(), newNarrative);
            }
        }
    }
    
    /**
     * Synchronizes the proposal persons from the child proposal to the parent proposal and then restores any proposal persons that were in the given child
     * proposal (and hence removed from the given parent proposal).
     * <p>
     * This first synchronizes the main proposal persons from the primary child proposal to the parent proposal and then runs the same algorithm on all other
     * children of the parent proposal.
     * @param hierarchyProposal
     * @param primaryChildProposal
     * @param principalInvestigator
     */
    protected void synchronizePersonsAndAggregate(DevelopmentProposal hierarchyProposal, DevelopmentProposal primaryChildProposal, 
            ProposalPerson principalInvestigator) {
        
        synchronizePersons(hierarchyProposal, primaryChildProposal, principalInvestigator);
        for (DevelopmentProposal childProposal : getHierarchyChildren(hierarchyProposal.getProposalNumber())) {
            if (!StringUtils.equals(primaryChildProposal.getProposalNumber(), childProposal.getProposalNumber())) {
                synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            }
        }
    }
    
    /**
     * Synchronizes the proposal persons from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     * @param principalInvestigator
     */
    protected void synchronizePersons(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, ProposalPerson principalInvestigator) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            int lastIndex = hierarchyProposal.getProposalPersons().lastIndexOf(person);
            ProposalPerson firstInstance = (firstIndex == -1) ? null : hierarchyProposal.getProposalPersons().get(firstIndex);
            if (firstIndex == -1 || (firstIndex == lastIndex && !rolesAreSimilar(person, firstInstance))) {
                ProposalPerson newPerson;
                newPerson = (ProposalPerson) ObjectUtils.deepCopy(person);
                newPerson.setDevelopmentProposal(hierarchyProposal);
                newPerson.getProposalPersonYnqs().clear();
                newPerson.getCreditSplits().clear();
                for (ProposalPersonUnit unit : newPerson.getUnits()) {
                    unit.getCreditSplits().clear();
                }
                newPerson.setProposalPersonNumber(null);
                newPerson.setVersionNumber(null);
                newPerson.setHierarchyProposalNumber(childProposal.getProposalNumber());
            
                if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                    newPerson.setProposalPersonRoleId(Constants.CO_INVESTIGATOR_ROLE);
                }
                if (newPerson.equals(principalInvestigator) && (firstIndex == -1 || !firstInstance.isInvestigator())) {
                    newPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                }
                
                hierarchyProposal.addProposalPerson(newPerson);
            }
        }
    }
    
    /**
     * Synchronizes the proposal budget from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     * @param hierarchyBudget
     * @param childBudget
     * @param hierarchyBudgetDocument
     * @throws ProposalHierarchyException
     */
    protected void synchronizeBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, Budget hierarchyBudget, ProposalDevelopmentBudgetExt childBudget, 
            BudgetDocument hierarchyBudgetDocument) throws ProposalHierarchyException {
        
        LOG.info(String.format("***Beginning Hierarchy Budget Sync for Parent %s and Child %s", 
                hierarchyProposal.getProposalNumber(), childProposal.getProposalNumber()));
        
        synchronizeChildBudget(hierarchyBudget, childBudget, childProposal.getProposalNumber(), childProposal.getHierarchyBudgetType(), 
                StringUtils.equals(childProposal.getProposalNumber(), hierarchyProposal.getHierarchyOriginatingChildProposalNumber()));
        if (hierarchyBudget.getEndDate().after(hierarchyProposal.getRequestedEndDateInitial())) {
            hierarchyProposal.setRequestedEndDateInitial(hierarchyBudget.getEndDate());
        }
        if (childProposal.getRequestedEndDateInitial().after(hierarchyProposal.getRequestedEndDateInitial())) {
            hierarchyProposal.setRequestedEndDateInitial(childProposal.getRequestedEndDateInitial());
        }
        try {
            documentService.saveDocument(hierarchyBudgetDocument);
        } catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        LOG.info(String.format("***Completed Hierarchy Budget Sync for Parent %s and Child %s", 
                hierarchyProposal.getProposalNumber(), childProposal.getProposalNumber()));
        
        childBudget.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childBudget));
        childProposal.setLastSyncedBudgetDocumentNumber(childBudget.getDocumentNumber());
        businessObjectService.save(childBudget);
        businessObjectService.save(childProposal);
    }
    

    
    protected void synchronizeChildBudget(Budget parentBudget, Budget childBudget, String childProposalNumber, String hierarchyBudgetTypeCode, boolean isOriginatingChildBudget )
            throws ProposalHierarchyException {
        try {
            
            if( isOriginatingChildBudget ) {
                parentBudget.setUrRateClassCode( childBudget.getUrRateClassCode() );
                parentBudget.setOhRateClassCode( childBudget.getOhRateClassCode() );
            }
            
            BudgetPerson newPerson;
            Map<Integer, BudgetPerson> personMap = new HashMap<Integer, BudgetPerson>();
            for (BudgetPerson person : childBudget.getBudgetPersons()) {
                newPerson = (BudgetPerson) ObjectUtils.deepCopy(person);
                newPerson.setPersonSequenceNumber(parentBudget.getHackedDocumentNextValue(
                        Constants.PERSON_SEQUENCE_NUMBER));
                newPerson.setBudgetId(parentBudget.getBudgetId());
                newPerson.setHierarchyProposalNumber(childProposalNumber);
                newPerson.setVersionNumber(null);
                parentBudget.addBudgetPerson(newPerson);
                personMap.put(person.getPersonSequenceNumber(), newPerson);
            }
            
            BudgetSubAwards newSubAwards;
            for (BudgetSubAwards childSubAwards : childBudget.getBudgetSubAwards()) {
                childSubAwards.refreshReferenceObject("budgetSubAwardAttachments");
                childSubAwards.refreshReferenceObject("budgetSubAwardFiles");
                newSubAwards = (BudgetSubAwards) ObjectUtils.deepCopy(childSubAwards);
                newSubAwards.setBudgetId(parentBudget.getBudgetId());
                newSubAwards.setBudgetVersionNumber(parentBudget.getBudgetVersionNumber());
                newSubAwards.setSubAwardNumber(parentBudget.getHackedDocumentNextValue("subAwardNumber") != null ? parentBudget.getHackedDocumentNextValue("subAwardNumber") : 1);
                newSubAwards.setVersionNumber(null);
                newSubAwards.setHierarchyProposalNumber(childProposalNumber);
                for (BudgetSubAwardAttachment attachment : newSubAwards.getBudgetSubAwardAttachments()) {
                    attachment.setSubAwardNumber(newSubAwards.getSubAwardNumber());
                    attachment.setBudgetId(parentBudget.getBudgetId());
                    attachment.setId(null);
                    attachment.setVersionNumber(null);
                }
                for (BudgetSubAwardFiles files : newSubAwards.getBudgetSubAwardFiles()) {
                    files.setSubAwardNumber(newSubAwards.getSubAwardNumber());
                    files.setBudgetId(parentBudget.getBudgetId());
                    files.setVersionNumber(null);
                }
                businessObjectService.save(newSubAwards);
                parentBudget.getBudgetSubAwards().add(newSubAwards);
            }
            
            int parentStartPeriod = getCorrespondingParentPeriod(parentBudget, childBudget);
            if (parentStartPeriod == -1) {
                throw new ProposalHierarchyException("Cannot find a parent budget period that corresponds to the child period.");
            }

            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
            BudgetPeriod parentPeriod, childPeriod;
            Long budgetId = parentBudget.getBudgetId();
            Long budgetPeriodId;
            Integer budgetPeriod;
            BudgetCostShare newCostShare;
            for (BudgetCostShare costShare : childBudget.getBudgetCostShares()) {
                if (StringUtils.isNotEmpty(costShare.getSourceAccount())) {
                    newCostShare = (BudgetCostShare)ObjectUtils.deepCopy(costShare);
                    newCostShare.setBudgetId(budgetId);
                    newCostShare.setDocumentComponentId(parentBudget.getHackedDocumentNextValue(newCostShare.getDocumentComponentIdKey()));
                    newCostShare.setObjectId(null);
                    newCostShare.setVersionNumber(null);
                    newCostShare.setHierarchyProposalNumber(childProposalNumber);
                    newCostShare.setHiddenInHierarchy(true);
                    businessObjectService.save(newCostShare);
                }
            }
            
            BudgetUnrecoveredFandA newUnrecoveredFandA;
            for (BudgetUnrecoveredFandA unrecoveredFandA : childBudget.getBudgetUnrecoveredFandAs()) {
                if (StringUtils.isNotEmpty(unrecoveredFandA.getSourceAccount())) {
                    newUnrecoveredFandA = (BudgetUnrecoveredFandA)ObjectUtils.deepCopy(unrecoveredFandA);
                    newUnrecoveredFandA.setBudgetId(budgetId);
                    newUnrecoveredFandA.setDocumentComponentId(parentBudget.getHackedDocumentNextValue(newUnrecoveredFandA.getDocumentComponentIdKey()));
                    newUnrecoveredFandA.setObjectId(null);
                    newUnrecoveredFandA.setVersionNumber(null);
                    newUnrecoveredFandA.setHierarchyProposalNumber(childProposalNumber);
                    newUnrecoveredFandA.setHiddenInHierarchy(true);
                    businessObjectService.save(newUnrecoveredFandA);
                }
            }

            for (int i = 0, j = parentStartPeriod; i < childPeriods.size(); i++, j++) {
                childPeriod = childPeriods.get(i);
                parentPeriod = findOrCreateMatchingPeriod(childPeriod, parentBudget);                
                budgetPeriodId = parentPeriod.getBudgetPeriodId();
                budgetPeriod = parentPeriod.getBudgetPeriod();
                BudgetLineItem parentLineItem;
                Integer lineItemNumber;

                if (StringUtils.equals(hierarchyBudgetTypeCode, HierarchyBudgetTypeConstants.SubBudget.code())) {
                    for (BudgetLineItem childLineItem : childPeriod.getBudgetLineItems()) {
                        ObjectUtils.materializeUpdateableCollections(childLineItem);
                        parentLineItem = (BudgetLineItem) (getDeepCopyPostProcessor().processDeepCopyWithDeepCopyIgnore(childLineItem));
                        lineItemNumber = parentBudget.getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        
                        parentLineItem.setBudgetLineItemId(null);
                        parentLineItem.setBudgetId(budgetId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setVersionNumber(null);
                        parentLineItem.setObjectId(null);
                        
                        for (BudgetLineItemCalculatedAmount calAmt : parentLineItem.getBudgetLineItemCalculatedAmounts()) {
                            calAmt.setBudgetLineItemCalculatedAmountId(null);
                            calAmt.setBudgetLineItemId(null);
                            calAmt.setBudgetId(budgetId);

                            calAmt.setBudgetPeriod(budgetPeriod);
                            calAmt.setLineItemNumber(lineItemNumber);
                            calAmt.setVersionNumber(null);
                            calAmt.setObjectId(null);
                        }
                        BudgetPerson budgetPerson;
                        for (BudgetPersonnelDetails details : parentLineItem.getBudgetPersonnelDetailsList()) {
                            budgetPerson = personMap.get(details.getPersonSequenceNumber());
                            details.setBudgetPerson(budgetPerson);
                            details.setJobCode(budgetPerson.getJobCode());
                            details.setPersonId(budgetPerson.getPersonRolodexTbnId());
                            details.setPersonSequenceNumber(budgetPerson.getPersonSequenceNumber());
                            details.setPersonNumber(parentBudget.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));

                            details.setBudgetPersonnelLineItemId(null);
                            details.setBudgetId(budgetId);
                            details.setBudgetPeriod(budgetPeriod);
                            details.setLineItemNumber(lineItemNumber);
                            details.setVersionNumber(null);
                            details.setObjectId(null);
                            
                            for (BudgetPersonnelCalculatedAmount calAmt : details.getBudgetPersonnelCalculatedAmounts()) {
                                calAmt.setBudgetPersonnelCalculatedAmountId(null);
                                calAmt.setBudgetLineItemId(null);
                                calAmt.setBudgetId(budgetId);

                                calAmt.setBudgetPeriod(budgetPeriod);
                                calAmt.setLineItemNumber(lineItemNumber);
                                calAmt.setVersionNumber(null);
                                calAmt.setObjectId(null);
                            }
                        }
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                }
                else { // subproject budget
                    Map<String, String> primaryKeys;
                    CostElement costElement;
                    String directCostElement = parameterService.getParameterValueAsString(BudgetDocument.class, PARAMETER_NAME_DIRECT_COST_ELEMENT);
                    String indirectCostElement = parameterService.getParameterValueAsString(BudgetDocument.class, PARAMETER_NAME_INDIRECT_COST_ELEMENT);
                    
                    if (childPeriod.getTotalIndirectCost().isNonZero()) {
                        primaryKeys = new HashMap<String, String>();
                        primaryKeys.put("costElement", indirectCostElement);
                        costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, primaryKeys);
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setBudgetId(budgetId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setIndirectCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                    if (childPeriod.getTotalDirectCost().isNonZero()) {
                        primaryKeys = new HashMap<String, String>();
                        primaryKeys.put("costElement", directCostElement);
                        costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, primaryKeys);
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setBudgetId(budgetId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setDirectCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                }
            }
            parentBudget.setStartDate(parentBudget.getBudgetPeriod(0).getStartDate());
            parentBudget.setEndDate(parentBudget.getBudgetPeriod(parentBudget.getBudgetPeriods().size()-1).getEndDate());
        }
        catch (Exception e) {
            LOG.error("Problem copying line items to parent", e);
            throw new ProposalHierarchyException("Problem copying line items to parent", e);
        }

        budgetSummaryService.calculateBudget(parentBudget);
    }
    
    protected BudgetPeriod findOrCreateMatchingPeriod(BudgetPeriod childPeriod, Budget parentBudget) {
        int priorPeriodIndex = 0;
        int index = 0;
        for (BudgetPeriod period : parentBudget.getBudgetPeriods()) {
            if (period.getStartDate().equals(childPeriod.getStartDate()) && period.getEndDate().equals(childPeriod.getEndDate())) {
                return period;
            } else if (period.getEndDate().before(childPeriod.getStartDate())) {
                priorPeriodIndex = index;
            }
            index++;
        }
        //otherwise add it after most recent budget period which should be valid based on previous validations.
        BudgetPeriod result = new BudgetPeriod();
        result.setStartDate(childPeriod.getStartDate());
        result.setEndDate(childPeriod.getEndDate());
        result.setBudgetPeriod(parentBudget.getBudgetPeriods().get(priorPeriodIndex).getBudgetPeriod()+1);
        result.setBudget(parentBudget);
        result.setBudgetId(parentBudget.getBudgetId());
        getBudgetSummaryService().addBudgetPeriod(parentBudget, result);
        return result;
    }

    protected void aggregateHierarchy(DevelopmentProposal hierarchy) throws ProposalHierarchyException {
        LOG.info(String.format("***Aggregating Proposal Hierarchy #%s", hierarchy.getProposalNumber()));
        List<ProposalPersonBiography> biosToRemove = new ArrayList<ProposalPersonBiography>();
        for (ProposalPersonBiography bio : hierarchy.getPropPersonBios()) {
            loadBioContent(bio);
            String bioPersonId = bio.getPersonId();
            Integer bioRolodexId = bio.getRolodexId();
            boolean keep = false;
            for (ProposalPerson person : hierarchy.getProposalPersons()) {
                if ((bioPersonId != null && bioPersonId.equals(person.getPersonId())) 
                        || (bioRolodexId != null && bioRolodexId.equals(person.getRolodexId()))) {
                    bio.setProposalPersonNumber(person.getProposalPersonNumber());
                    bio.getPersonnelAttachment().setProposalPersonNumber(person.getProposalPersonNumber());
                    keep = true;
                    break;
                }
            }
            if (!keep) {
                biosToRemove.add(bio);
            }
        }
        if (!biosToRemove.isEmpty()) {
            hierarchy.getPropPersonBios().removeAll(biosToRemove);
        }
        
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument = getHierarchyBudget(hierarchy); 
        Budget hierarchyBudget = hierarchyBudgetDocument.getBudget();
        
        hierarchyBudget.getBudgetCostShares().clear();
        hierarchyBudget.getBudgetUnrecoveredFandAs().clear();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("hiddenInHierarchy", true);
        fieldValues.put("budgetId", hierarchyBudget.getBudgetId());
        Collection<BudgetCostShare> hiddenCostShares = businessObjectService.findMatching(BudgetCostShare.class, fieldValues);
        Collection<BudgetUnrecoveredFandA> hiddenUnrecoveredFandAs = businessObjectService.findMatching(BudgetUnrecoveredFandA.class, fieldValues);
        Map<Integer,BudgetCostShare> newCostShares = new HashMap<Integer, BudgetCostShare>();
        Map<Integer,BudgetUnrecoveredFandA> newUnrecoveredFandAs = new HashMap<Integer, BudgetUnrecoveredFandA>();
        BudgetCostShare newCostShare;
        BudgetUnrecoveredFandA newUnrecoveredFandA;
        Integer keyHash;
        for (BudgetCostShare costShare : hiddenCostShares) {
            keyHash = Arrays.hashCode(new Object[]{costShare.getProjectPeriod(), costShare.getSourceAccount()});
            newCostShare = newCostShares.get(keyHash);
            if (newCostShare == null) {
                newCostShare = (BudgetCostShare)ObjectUtils.deepCopy(costShare);
                newCostShare.setDocumentComponentId(null);
                newCostShare.setObjectId(null);
                newCostShare.setVersionNumber(null);
                newCostShares.put(keyHash, newCostShare);
            }
            else {
                newCostShare.setSharePercentage(newCostShare.getSharePercentage().add(costShare.getSharePercentage()));
                if (newCostShare.getSharePercentage().isGreaterThan(new ScaleTwoDecimal(100.0))) {
                    newCostShare.setSharePercentage(new ScaleTwoDecimal(100.0));
                }
                newCostShare.setShareAmount(newCostShare.getShareAmount().add(costShare.getShareAmount()));
            }
        }
        for (BudgetUnrecoveredFandA unrecoveredFandA : hiddenUnrecoveredFandAs) {
            keyHash = Arrays.hashCode(new Object[]{unrecoveredFandA.getFiscalYear(), unrecoveredFandA.getSourceAccount(), unrecoveredFandA.getApplicableRate(), unrecoveredFandA.getOnCampusFlag()});
            newUnrecoveredFandA = newUnrecoveredFandAs.get(keyHash);
            if (newUnrecoveredFandA == null) {
                newUnrecoveredFandA = (BudgetUnrecoveredFandA)ObjectUtils.deepCopy(unrecoveredFandA);
                newUnrecoveredFandA.setBudgetId(hierarchyBudget.getBudgetId());
                newUnrecoveredFandA.setDocumentComponentId(null);
                newUnrecoveredFandA.setObjectId(null);
                newUnrecoveredFandA.setVersionNumber(null);
                newUnrecoveredFandAs.put(keyHash, newUnrecoveredFandA);
            }
            else {
                newUnrecoveredFandA.setAmount(newUnrecoveredFandA.getAmount().add(unrecoveredFandA.getAmount()));
            }
        }
        for (BudgetCostShare costShare : newCostShares.values()) {
            costShare.setHiddenInHierarchy(false);
            costShare.setHierarchyProposalNumber(null);
            hierarchyBudget.add(costShare);
        }
        for (BudgetUnrecoveredFandA unrecoveredFandA : newUnrecoveredFandAs.values()) {
            unrecoveredFandA.setHiddenInHierarchy(false);
            unrecoveredFandA.setHierarchyProposalNumber(null);
            hierarchyBudget.add(unrecoveredFandA);
        }
        
        KualiForm oldForm = KNSGlobalVariables.getKualiForm();
        KNSGlobalVariables.setKualiForm(null);
        budgetCalculationService.calculateBudget(hierarchyBudget);
        budgetCalculationService.calculateBudgetSummaryTotals(hierarchyBudget);
        KNSGlobalVariables.setKualiForm(oldForm);
        try {
            documentService.saveDocument(hierarchyBudgetDocument);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }

    protected DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (businessObjectService.findByPrimaryKey(DevelopmentProposal.class, pk));
    }
    
    protected boolean isSynchronized(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        Integer hc1 = computeHierarchyHashCode(childProposal);
        Integer hc2 = childProposal.getHierarchyLastSyncHashCode();
        return org.apache.commons.lang3.ObjectUtils.equals(hc1, hc2);
    }
    
    protected boolean isBudgetSynchronized(DevelopmentProposal childProposal, ProposalDevelopmentBudgetExt childBudget) throws ProposalHierarchyException {
        if (StringUtils.equals(childProposal.getLastSyncedBudgetDocumentNumber(), childBudget.getDocumentNumber())) {
            ObjectUtils.materializeAllSubObjects(childBudget);
            Integer hc1 = computeHierarchyHashCode(childBudget);
            Integer hc2 = childBudget.getHierarchyLastSyncHashCode();
            return org.apache.commons.lang3.ObjectUtils.equals(hc1, hc2);
        } else {
            return false;
        }
    }
    
    
    protected void setInitialPi(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        ProposalPerson pi = child.getPrincipalInvestigator();
        if (pi != null) {
            int index = hierarchy.getProposalPersons().indexOf(pi);
            if (index > -1) {
                hierarchy.getProposalPerson(index).setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
            }
        }
    }
    
    protected List<BudgetDocument<DevelopmentProposal>> getHierarchyBudgets(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        List<BudgetDocument<DevelopmentProposal>> hierarchyBudgets = new ArrayList<BudgetDocument<DevelopmentProposal>>();
        
        try {
            for (BudgetDocumentVersion budgetDocumentVersion : hierarchyProposal.getProposalDocument().getBudgetDocumentVersions()) {
                String budgetDocumentNumber = budgetDocumentVersion.getBudgetVersionOverview().getDocumentNumber();
                hierarchyBudgets.add((BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(budgetDocumentNumber));
            }
        } catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    
        return hierarchyBudgets;
    }
    
    protected BudgetDocument<DevelopmentProposal> getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        String budgetDocumentNumber = hierarchyProposal.getProposalDocument().getBudgetDocumentVersions().get(0).getBudgetVersionOverview().getDocumentNumber();
        BudgetDocument<DevelopmentProposal> budgetDocument = null;
        try {
            budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(budgetDocumentNumber);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        return budgetDocument;
    }
 
    public BudgetDocument<DevelopmentProposal> getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String budgetDocumentNumber = null;
        for (BudgetDocumentVersion version : childProposal.getProposalDocument().getBudgetDocumentVersions()) {
            budgetDocumentNumber = version.getDocumentNumber();
            if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
                break;
            }
        }
        BudgetDocument<DevelopmentProposal> budgetDocument = null;
        try {
            budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(budgetDocumentNumber);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        return budgetDocument;
    }
    
    protected void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        BudgetDocument<DevelopmentProposal> parentBudgetDoc = getHierarchyBudget(hierarchyProposal);
        Budget parentBudget = parentBudgetDoc.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getSyncableBudget(childProposal); 
        Budget childBudget = childBudgetDocument.getBudget();
        BudgetPeriod parentPeriod, childPeriod;
        for (int i=0; i < childBudget.getBudgetPeriods().size(); i++) {
            parentPeriod = parentBudget.getBudgetPeriod(i);
            childPeriod = childBudget.getBudgetPeriod(i);
            parentPeriod.setStartDate(childPeriod.getStartDate());
            parentPeriod.setEndDate(childPeriod.getEndDate());
            parentPeriod.setBudgetPeriod(childPeriod.getBudgetPeriod());
        }
        
        parentBudget.setCostSharingAmount(new ScaleTwoDecimal(0));
        parentBudget.setTotalCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalDirectCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalIndirectCost(new ScaleTwoDecimal(0));
        parentBudget.setUnderrecoveryAmount(new ScaleTwoDecimal(0));
        
        parentBudget.setOhRateClassCode(childBudget.getOhRateClassCode());
        parentBudget.setOhRateTypeCode(childBudget.getOhRateTypeCode());
        parentBudget.setUrRateClassCode(childBudget.getUrRateClassCode());
        try {
            documentService.saveDocument(parentBudgetDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }
    
    public ProposalHierarchyErrorDto validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException {
        BudgetDocument<DevelopmentProposal> parentBudgetDoc = getHierarchyBudget(hierarchyProposal);
        Budget parentBudget = parentBudgetDoc.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getSyncableBudget(childProposal); 
        Budget childBudget = childBudgetDocument.getBudget();

        ProposalHierarchyErrorDto retval = null;
        // check that child budget starts on one of the budget period starts
        int correspondingStart = getCorrespondingParentPeriod(parentBudget, childBudget);
        if (correspondingStart == -1) {
            retval = new ProposalHierarchyErrorDto(ERROR_BUDGET_START_DATE_INCONSISTENT, childProposal.getProposalNumber());
        }
        // check that child budget periods map to parent periods
        else {
            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
            BudgetPeriod parentPeriod, childPeriod;
            int i;
            int j;
            for (i = correspondingStart, j = 0; i < parentPeriods.size() && j < childPeriods.size(); i++, j++) {
                parentPeriod = parentPeriods.get(i);
                childPeriod = childPeriods.get(j);
                if (!parentPeriod.getStartDate().equals(childPeriod.getStartDate())
                        || !parentPeriod.getEndDate().equals(childPeriod.getEndDate())) {
                    retval = new ProposalHierarchyErrorDto(ERROR_BUDGET_PERIOD_DURATION_INCONSISTENT, childProposal.getProposalNumber());
                    break;
                }
            }
            if (retval == null 
                    && !allowEndDateChange 
                    && (j < childPeriods.size() 
                            || childProposal.getRequestedEndDateInitial().after(hierarchyProposal.getRequestedEndDateInitial()))) {
                retval = new ProposalHierarchyErrorDto(QUESTION_EXTEND_PROJECT_DATE_CONFIRM, childProposal.getProposalNumber());
            }
        }
        
        return retval;
    }
    
    protected int getCorrespondingParentPeriod(Budget parentBudget, Budget childBudget) {
        int correspondingStart = -1;
 
        // using start date of first period as start date and end date of last period
        // as end because budget start and end are not particularly reliable
        Date childStart = childBudget.getBudgetPeriod(0).getStartDate();
        Date parentStart = parentBudget.getBudgetPeriod(0).getStartDate();
        Date parentEnd = parentBudget.getBudgetPeriod(parentBudget.getBudgetPeriods().size()-1).getEndDate();
        // check that child budget starts somewhere during parent budget
        if (childStart.compareTo(parentStart) >= 0
                && childStart.compareTo(parentEnd) < 0) {
            // check that child budget starts on one of the budget period starts
            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            for (int i=0; i<parentPeriods.size(); i++) {
                if (childStart.equals(parentPeriods.get(i).getStartDate())) {
                    correspondingStart = i;
                    break;
                }
            }
        }
        return correspondingStart;
    }
    
    protected void removeChildElements(DevelopmentProposal parentProposal, Budget parentBudget, String childProposalNumber) {
        List<PropScienceKeyword> keywords = parentProposal.getPropScienceKeywords();
        for (int i=keywords.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, keywords.get(i).getHierarchyProposalNumber())) {
                keywords.remove(i);
            }
        }

        List<ProposalSpecialReview> reviews = parentProposal.getPropSpecialReviews();
        for (int i=reviews.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, reviews.get(i).getHierarchyProposalNumber())) {
                reviews.remove(i);
            }
        }

        List<Narrative> narratives = parentProposal.getNarratives();
        for (int i=narratives.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, narratives.get(i).getHierarchyProposalNumber())) {
                businessObjectService.delete(narratives.remove(i));
            }
        }
        
        removeChildBudgetElements(parentProposal, parentBudget, childProposalNumber);
    }
    
    protected void removeChildBudgetElements(DevelopmentProposal parentProposal, Budget parentBudget, String childProposalNumber) {
        List<BudgetSubAwards> subAwards = parentBudget.getBudgetSubAwards();
        for (int i=subAwards.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, subAwards.get(i).getHierarchyProposalNumber())) {
                subAwards.remove(i);
            }
        }

        List<BudgetProjectIncome> projectIncomes = parentBudget.getBudgetProjectIncomes();
        for (int i=projectIncomes.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, projectIncomes.get(i).getHierarchyProposalNumber())) {
                projectIncomes.remove(i);
            }
        }

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("hierarchyProposalNumber", childProposalNumber);
        businessObjectService.deleteMatching(BudgetCostShare.class, fieldValues);
        businessObjectService.deleteMatching(BudgetUnrecoveredFandA.class, fieldValues);

        BudgetPersonnelBudgetService budgetPersonnelBudgetService = getBudgetPersonnelBudgetService();
        List<BudgetPeriod> periods = parentBudget.getBudgetPeriods();
        List<BudgetLineItem> lineItems;
        List<BudgetPersonnelDetails> personnelDetailsList;
        BudgetPeriod period = null;
        BudgetLineItem lineItem = null;
        for (int i = periods.size()-1; i>=0; i--) {
            period = periods.get(i);
            lineItems = period.getBudgetLineItems();
            for (int j = lineItems.size()-1; j>=0; j--) {
                lineItem = lineItems.get(j);
                if (StringUtils.equals(childProposalNumber, lineItem.getHierarchyProposalNumber())) {
                    personnelDetailsList = lineItem.getBudgetPersonnelDetailsList();
                    for (int k = personnelDetailsList.size()-1; k>=0; k--) {
                        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(parentBudget, i, j, k);
                    }
                    lineItems.remove(j);
                    parentBudget.setBudgetLineItemDeleted(true);
                }
            }
            if (lineItems.isEmpty() && periods.indexOf(period)==periods.size()-1 && periods.indexOf(period)>0) {
                periods.remove(period);
            }
        }
        parentBudget.setEndDate(periods.get(periods.size()-1).getEndDate());
        List<BudgetPerson> budgetPersons = parentBudget.getBudgetPersons();
        for (int i=budgetPersons.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, budgetPersons.get(i).getHierarchyProposalNumber())) {
                budgetPersonnelBudgetService.deleteBudgetPersonnelDetailsForPerson(parentBudget, budgetPersons.get(i));
                budgetPersons.remove(i);
            }
        }        
    }
    
    protected void prepareHierarchySync(DevelopmentProposal hierarchyProposal) {
        prepareHierarchySync(hierarchyProposal.getProposalDocument());
    }
    
    protected void prepareHierarchySync(ProposalDevelopmentDocument pdDoc) {
        pdDoc.refreshReferenceObject("documentNextvalues");
    }
    
    protected void finalizeHierarchySync(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        pdDoc.refreshBudgetDocumentVersions();
        try {
            documentService.saveDocument(pdDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }
    
    protected void finalizeHierarchySync(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        businessObjectService.save(hierarchyProposal.getProposalDocument().getDocumentNextvalues());
        businessObjectService.save(hierarchyProposal);
    }
        
    protected void copyInitialAttachments(DevelopmentProposal srcProposal, DevelopmentProposal destProposal) {
        String instituteNarrativeTypeGroup = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, PARAMETER_NAME_INSTITUTE_NARRATIVE_TYPE_GROUP);
        
        ProposalPersonBiography destPropPersonBio;
        ProposalPerson srcPerson = null;
        ProposalPerson destPerson = null;
        for (ProposalPersonBiography srcPropPersonBio : srcProposal.getPropPersonBios()) {
            for (ProposalPerson person : srcProposal.getProposalPersons()) {
                if (person.getProposalPersonNumber().equals(srcPropPersonBio.getProposalPersonNumber())) {
                    srcPerson = person;
                    break;
                }
            }
            for (ProposalPerson person : destProposal.getProposalPersons()) {
                if (person.equals(srcPerson)) {
                    destPerson = person;
                    break;
                }
            }
            loadBioContent(srcPropPersonBio);
            destPropPersonBio = (ProposalPersonBiography)ObjectUtils.deepCopy(srcPropPersonBio);
            destPropPersonBio.setProposalPersonNumber(destPerson.getProposalPersonNumber());
            destPropPersonBio.setPersonId(destPerson.getPersonId());
            destPropPersonBio.setRolodexId(destPerson.getRolodexId());
            proposalPersonBiographyService.addProposalPersonBiography(destProposal.getProposalDocument(), destPropPersonBio);
        }

        Narrative destNarrative;
        for (Narrative srcNarrative : srcProposal.getNarratives()) {
            if (!srcNarrative.getNarrativeType().isAllowMultiple()
                    && !srcProposal.getInstituteAttachments().contains(srcNarrative)
                    && !StringUtils.equalsIgnoreCase(srcNarrative.getNarrativeType().getNarrativeTypeGroup(), instituteNarrativeTypeGroup)) {
                loadAttachmentContent(srcNarrative);
                destNarrative = (Narrative)ObjectUtils.deepCopy(srcNarrative);
                destNarrative.setModuleStatusCode("I");
                legacyNarrativeService.addNarrative(destProposal.getProposalDocument(), destNarrative);
            }
        }
    }

    protected void loadAttachmentContent(Narrative narrative){
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put("proposalNumber", narrative.getProposalNumber());
        primaryKey.put("moduleNumber", narrative.getModuleNumber()+"");
        NarrativeAttachment attachment = (NarrativeAttachment)businessObjectService.findByPrimaryKey(NarrativeAttachment.class, primaryKey);
        narrative.setNarrativeAttachment(attachment);
    }
    
    protected void loadBioContent(ProposalPersonBiography bio){
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put("proposalNumber", bio.getProposalNumber());
        primaryKey.put("biographyNumber", bio.getBiographyNumber()+"");
        primaryKey.put("proposalPersonNumber", bio.getProposalPersonNumber()+"");
        ProposalPersonBiographyAttachment attachment = (ProposalPersonBiographyAttachment)businessObjectService.findByPrimaryKey(ProposalPersonBiographyAttachment.class, primaryKey);
        bio.setPersonnelAttachment(attachment);
    }

    protected String getHierarchyChildRouteStatus( String oldStatus, String newStatus) {
        
        LOG.info( String.format( "Route status change %s:%s",oldStatus,newStatus));
        
        String retCd = null;
        if( StringUtils.equals(newStatus,KewApiConstants.ROUTE_HEADER_ENROUTE_CD) 
                && ( StringUtils.equals( oldStatus, KewApiConstants.ROUTE_HEADER_INITIATED_CD) 
                || StringUtils.equals(oldStatus, KewApiConstants.ROUTE_HEADER_SAVED_CD)  
                || StringUtils.equals(KewApiConstants.ROUTE_HEADER_ENROUTE_CD, oldStatus)) ) { 
                retCd = renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(newStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
                retCd = renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
                retCd = renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD ) ) {
               retCd = renderMessage( HIERARCHY_CHILD_CANCEL_APPSTATUS );
        } else {
            LOG.warn(String.format("Do not know how to calculate hierarchy child status for %s to %s",oldStatus,newStatus) );
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format("Route status for children:%s",retCd ));
        return retCd;
    }

    
    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    protected int computeHierarchyHashCode(DevelopmentProposal proposal) {
        int prime = 31;
        int result = 1;
        for (ProposalPerson person : proposal.getProposalPersons()) {
            result = prime * result + person.hashCode();
        }
        for (Narrative narrative : proposal.getNarratives()) {
            result = prime * result + narrative.hierarchyHashCode();
        }
        for (PropScienceKeyword keyword : proposal.getPropScienceKeywords()) {
            result = prime * result + keyword.getScienceKeyword().getCode().hashCode();
        }
        for (ProposalSpecialReview review : proposal.getPropSpecialReviews()) {
            result = prime * result + review.hierarchyHashCode();
        }
        return result;
    }
    
    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    protected int computeHierarchyHashCode(Budget budget) {
        int prime = 31;
        int result = 1;
        budgetCalculationService.calculateBudget(budget);
        budgetCalculationService.calculateBudgetSummaryTotals(budget);
        result = prime * result + budget.getBudgetSummaryTotals().hashCode();
        return result;
    }    

    protected List<ProposalDevelopmentDocument> getChildProposalDevelopmentDocuments(String parentProposalNumber) throws ProposalHierarchyException {
       
        List<ProposalDevelopmentDocument> outList = new ArrayList<ProposalDevelopmentDocument>();
        for( DevelopmentProposal child : getHierarchyChildren(parentProposalNumber)) {
            try {
                outList.add( (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId( child.getProposalDocument().getDocumentNumber() ) );
            }
            catch (WorkflowException e) {
                LOG.error( String.format( "Could not find document for child proposal number %s", parentProposalNumber, child.getProposalNumber() ), e);
                throw new ProposalHierarchyException( String.format( "Could not find document for child proposal number %s", parentProposalNumber, child.getProposalNumber() ), e );
            }
            
        }
        
        return outList;
        
    }

    @Override
    public List<DevelopmentProposal> getHierarchyChildren(String parentProposalNumber) {
        List<DevelopmentProposal> children = new ArrayList<DevelopmentProposal>();
        for( String childProposalNumber : proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber)) {
            children.add(getDevelopmentProposal(childProposalNumber));
        }
        return children;
    }
    
    @Override
    public WorkflowDocument getParentWorkflowDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
            return getParentDocument( child ).getDocumentHeader().getWorkflowDocument();
    }

    
    @Override
    public ProposalDevelopmentDocument getParentDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
        try {
            DevelopmentProposal parentProposal = getHierarchy(child.getDevelopmentProposal().getHierarchyParentProposalNumber());
            String parentDocumentNumber = parentProposal.getProposalDocument().getDocumentNumber();
            return (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(parentDocumentNumber);
        } catch (WorkflowException e) {
            LOG.error( "Workflow exception thrown getting hierarchy routing status.", e );
            throw new ProposalHierarchyException( String.format("Could not lookup hierarchy workflow status for child:%s",child.getDocumentHeader().getDocumentNumber()),e);
        }
    }
    
    
    /**
     * Reject a proposal by sending it to the first node ( as named by PROPOSALDEVELOPMENTDOCUMENT_KEW_INITIAL_NODE_NAME )
     * @param proposalDoc The ProposalDevelopmentDocument that should be rejected.
     * @param appDocStatus the application status to set in the workflow document.
     * @param principalId the principal we are rejecting the document as.
     * @param appDocStatus the application document status to apply ( does not apply if null )
     * @throws WorkflowException
     */
    protected void rejectProposal( ProposalDevelopmentDocument proposalDoc, String reason, String principalId, String appDocStatus ) throws WorkflowException  {
        kcDocumentRejectionService.reject(proposalDoc, reason, principalId, appDocStatus );
    }
    
    
    /**
     * Reject an entire proposal hierarchy.  This works by first rejecting each child, and then rejecting the parent.
     * @param hierarchyParent The hierarchy to reject
     * @param reason the reason to be applied to the annotation field.  The reason will be pre-pended with static text indicating if it was a child or the parent.
     * @param principalId the id of the principal that is rejecting the document.
     * @throws ProposalHierarchyException If hierarchyParent is not a hierarchy, or there was a problem rejecting one of the documents.
     */
    protected void rejectProposalHierarchy(ProposalDevelopmentDocument hierarchyParent, String reason, String principalId ) throws ProposalHierarchyException {
        
      //1. reject the parent.
        try {
            rejectProposal( hierarchyParent, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalId, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting proposal hierarchy parent %s", hierarchyParent.getDevelopmentProposal().getProposalNumber() ),e);
        }
        
        //2. Try to reject all of the children.
        for( ProposalDevelopmentDocument child : getChildProposalDevelopmentDocuments(hierarchyParent.getDevelopmentProposal().getProposalNumber())) {
            try {
                rejectProposal( child, renderMessage( HIERARCHY_ROUTING_PARENT_REJECTED_ANNOTATION, reason ), identityService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER ).getPrincipalId(), renderMessage( HIERARCHY_CHILD_REJECTED_APPSTATUS ) );
            } catch (WorkflowException e) {
                throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting child document %s", child.getDevelopmentProposal().getProposalNumber()), e );
            }
        }
     
    }
    
    
    @Override
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName, FormFile rejectFile ) 
    throws WorkflowException, ProposalHierarchyException, IOException {
        DevelopmentProposal pbo = getDevelopmentProposal(proposalNumber);
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(pbo.getProposalDocument().getDocumentNumber());
        if( !pbo.isInHierarchy() ) {
            rejectProposal( pDoc, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalName, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        } else if ( pbo.isParent() ) {
            rejectProposalHierarchy( pDoc, reason, principalName );
        } else {
            //it is a child or in some unknown state, either way we do not support rejecting it.
            throw new UnsupportedOperationException( String.format( "Cannot reject proposal %s it is a hierarchy child or ", proposalNumber ));
        }
        
        if (rejectFile != null && rejectFile.getFileData().length > 0) {
            Narrative narrative = new Narrative();
            narrative.setName(rejectFile.getFileName());
            narrative.setComments(reason);
            narrative.setNarrativeFile(rejectFile);
            narrative.setNarrativeTypeCode(getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.REJECT_NARRATIVE_TYPE_CODE_PARAM));
            Map keys = new HashMap();
            keys.put("NARRATIVE_STATUS_CODE", "C");
            NarrativeStatus status = (NarrativeStatus) this.businessObjectService.findByPrimaryKey(NarrativeStatus.class, keys);
            narrative.setNarrativeStatus(status);
            narrative.setModuleStatusCode(status.getCode());
            narrative.setModuleTitle("Proposal rejection attachment.");
            narrative.setContactName(globalVariableService.getUserSession().getPrincipalName());
            narrative.setPhoneNumber(globalVariableService.getUserSession().getPerson().getPhoneNumber());
            narrative.setEmailAddress(globalVariableService.getUserSession().getPerson().getEmailAddress());
            pDoc.getDevelopmentProposal().addInstituteAttachment(narrative);
            this.businessObjectService.save(pDoc);
        }
        
    }
    
    
    /**
     * Based on the hierarchy, and route status change of the parent, calculate what route action should be taken on the children.
     * @param hierarchy the heirarchy being routed
     * @param dto the route status change information.
     * @return The route action to take on the children.
     */
    protected String calculateChildRouteStatus( ProposalDevelopmentDocument hierarchy, DocumentRouteStatusChange dto ) {
        
        String parentOldStatus = dto.getOldRouteStatus();
        String parentNewStatus = dto.getNewRouteStatus();
        String newChildStatusTarget = "";

        if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_INITIATED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_SAVED_CD)) {
            // previous status was saved
            newChildStatusTarget = parentNewStatus;
            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
                // nothing to do
            }
            else if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
                // nothing to do.
            }
            else {
                throw new UnsupportedOperationException(String.format(
                        "Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,
                        parentNewStatus));
            }
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
            // we are moving from enroute to some other state.

            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD)
                    || StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                newChildStatusTarget = parentNewStatus;
            }
            else if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
                // nothing to do here, wait for the document to go final.
            } else if( StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
                //special case, document has been rejected and being approved again to simulate entry into workflow.
                //this value will trigger an approve.
                newChildStatusTarget = KewApiConstants.ROUTE_HEADER_ENROUTE_CD;
            }
            else {
                throw new UnsupportedOperationException(String.format("Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
            }

        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
                newChildStatusTarget = parentNewStatus;
            }
            else {
                throw new UnsupportedOperationException(String.format("Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
            }
        }
        else {
            throw new UnsupportedOperationException(String.format(
                    "Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
        }
        return newChildStatusTarget;
    }

    @Override
    public void routeHierarchyChildren(ProposalDevelopmentDocument proposalDevelopmentDocument, DocumentRouteStatusChange dto ) throws ProposalHierarchyException {
        
        String childStatusTarget = calculateChildRouteStatus(proposalDevelopmentDocument, dto );
        WorkflowDocument workdoc;
        ProposalDevelopmentDocument child = null;
        try {
            LOG.info(  IdentityService.class );
            for (ProposalDevelopmentDocument c : getChildProposalDevelopmentDocuments(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber() )) {
                child = c;
                if (!StringUtils.equals("", childStatusTarget)) {

                    if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_ENROUTE_CD, childStatusTarget)) {
                        //The user currently must initially route the child documents in order for them to hold in the system users action list.

                        workdoc = WorkflowDocumentFactory.loadDocument(child.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId(), child.getDocumentHeader().getWorkflowDocument().getDocumentId() );
                        workdoc.setApplicationDocumentStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));
                        if( !workdoc.isEnroute() ) {
                            workdoc.route(renderMessage( HIERARCHY_ROUTING_PARENT_SUBMITTED_ANNOTATION ));

                        } else {
                            //this means the status change is actually in the form of an approve action on a document that was moved back to the initial node.
                            //we need to do an approval.
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_RESUBMITTED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS ) );
                        }

                    } else {
                        workdoc = WorkflowDocumentFactory.loadDocument( identityService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER ).getPrincipalId(),child.getDocumentHeader().getWorkflowDocument().getDocumentId() );
                        workdoc.setApplicationDocumentStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));

                        if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_CANCEL_CD,childStatusTarget)) {
                            workdoc.cancel(renderMessage( HIERARCHY_ROUTING_PARENT_CANCELLED_ANNOTATION));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_CANCEL_APPSTATUS  ));

                        } else if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, childStatusTarget)) {
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_APPROVED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS ));

                        } else if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD, childStatusTarget)) {
                            workdoc.disapprove(renderMessage( HIERARCHY_ROUTING_PARENT_DISAPPROVED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS ));
                        } else {
                            throw new UnsupportedOperationException(String.format("Do not know how to handle new child status of %s", childStatusTarget));
                        }

                    }
                }
            }
        } catch ( Exception we ) {
            throw new ProposalHierarchyException( String.format( "Exception encountrered while attempting to route child proposal %s ( document #%s ) of proposal hierarchy %s ( document #%s )", child.getDevelopmentProposal().getProposalNumber(), child.getDocumentNumber(), proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), proposalDevelopmentDocument.getDocumentNumber() ), we);
        }
        
    }
    
    public void calculateAndSetProposalAppDocStatus( ProposalDevelopmentDocument doc, DocumentRouteStatusChange dto  ) throws ProposalHierarchyException {
        String principalId = globalVariableService.getUserSession().getPrincipalId();
        if( StringUtils.equals( dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_ENROUTE_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            updateAppDocStatus( doc, principalId, HIERARCHY_CANCEL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_DISAPPROVE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_FINAL_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_FINAL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_PROCESSED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_PROCESSED_APPSTATUS ) ;
        } 
    }
    
    protected void updateAppDocStatus( ProposalDevelopmentDocument doc, String principalId, String newStatus ) throws ProposalHierarchyException {
        try {
            WorkflowDocument wdoc = WorkflowDocumentFactory.loadDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getDocumentId() );
            wdoc.setApplicationDocumentStatus(renderMessage( newStatus ));
        }
        catch (Exception e) {
            throw new ProposalHierarchyException( String.format( "Exception encountrered while attempting to update App Doc Status of proposal %s ( document #%s )", doc.getDevelopmentProposal().getProposalNumber(), doc.getDocumentNumber() ), e);
        }
    }
    
    
    
    public boolean allChildBudgetsAreComplete(String parentProposalNumber) {
        boolean retval = true;
        String completeCode = parameterService.getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        for (ProposalBudgetStatus status : proposalHierarchyDao.getHierarchyChildProposalBudgetStatuses(parentProposalNumber)) {
            if (!StringUtils.equalsIgnoreCase(completeCode, status.getBudgetStatusCode())) {
                retval = false;
                break;
            }
        }
        return retval;
    }
    
    protected boolean rolesAreSimilar(ProposalPerson person1, ProposalPerson person2) {
        boolean isInvestigator1 = StringUtils.equals(person1.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person1.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        boolean isInvestigator2 = StringUtils.equals(person2.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person2.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        return isInvestigator1 == isInvestigator2;
    }

    @Override
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException {
        List<HierarchyPersonnelSummary> summaries = new ArrayList<HierarchyPersonnelSummary>();
        
        List<String> proposalNumbers = new ArrayList<String>();
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber));
        Collections.sort(proposalNumbers);
        
        for (String proposalNumber : proposalNumbers) {
            HierarchyPersonnelSummary summary = new HierarchyPersonnelSummary();
            
            DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
            List<Budget> hierarchyBudgets = new ArrayList<Budget>();
            for (BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument : getHierarchyBudgets(childProposal)) {
                hierarchyBudgets.add(hierarchyBudgetDocument.getBudget());
            }
            Collections.sort(hierarchyBudgets);
            
            summary.setProposalNumber(proposalNumber);
            summary.setHierarchyBudgets(hierarchyBudgets);
            summaries.add(summary);
        }
        
        return summaries;
    }
    
    @Override
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal proposal = getDevelopmentProposal(proposalNumber);
        List<HierarchyProposalSummary> summaries = new ArrayList<HierarchyProposalSummary>();

        List<String> proposalNumbers = new ArrayList<String>();
        if (proposal.isParent()) {
            proposalNumbers.add(proposalNumber);
        }
        else if (proposal.isChild()) {
            proposalNumbers.add(proposal.getHierarchyParentProposalNumber());
        }
        else {
            throw new ProposalHierarchyException("Proposal " + proposalNumber + " is not a member of a hierarchy.");            
        }
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(proposalNumbers.get(0)));

        HierarchyProposalSummary summary;
        for (String number : proposalNumbers) {
            summary = new HierarchyProposalSummary();
            summary.setProposalNumber(number);
            if (!StringUtils.equals(number, proposalNumbers.get(0))) {
                summary = getProposalSummary(number);
            }
            summaries.add(summary);
        }
        return summaries;
    }
    
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) throws ProposalHierarchyException {
        HierarchyProposalSummary summary = new HierarchyProposalSummary();
        summary.setProposalNumber(proposalNumber);
        DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
        summary.setSynced(isSynchronized(childProposal));
        BudgetDocument budgetDoc = getSyncableBudget(childProposal);
        summary.setSyncableBudgetDocumentNumber(budgetDoc.getDocumentNumber());
        summary.setBudgetSynced(isBudgetSynchronized(childProposal, (ProposalDevelopmentBudgetExt) budgetDoc.getBudget()));
        return summary;
    }

    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId) {
        boolean valid = true;
        valid &= kcAuthorizationService.hasPermission(principalId, childProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        try {
            valid &= kcAuthorizationService.hasPermission(principalId, getHierarchy(childProposal.getHierarchyParentProposalNumber()).getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        catch (ProposalHierarchyException e) {
            valid = false;
        }
        return valid;
    }

    protected String renderMessage( String key, String... params ) {
       String msg = kualiConfigurationService.getPropertyValueAsString(key);
       for (int i = 0; i < params.length; i++) {
           msg = replace(msg, "{" + i + "}", params[i]);
       }
       return msg;
       
    }
    protected KcDocumentRejectionService getKcDocumentRejectionService() {
        return kcDocumentRejectionService;
    }
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    protected DocumentService getDocumentService() {
        return documentService;
    }
    protected KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }
    protected ProposalHierarchyDao getProposalHierarchyDao() {
        return proposalHierarchyDao;
    }
    protected LegacyNarrativeService getLegacyNarrativeService() {
        return legacyNarrativeService;
    }
    protected BudgetService getBudgetService() {
        return budgetService;
    }
    protected ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }
    protected ParameterService getParameterService() {
        return parameterService;
    }
    protected IdentityService getIdentityManagementService() {
        return identityService;
    }
    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }
    protected SessionDocumentService getKnsSessionDocumentService() {
        return knsSessionDocumentService;
    }
    protected WorkflowDocumentService getkradWorkflowDocumentService() {
        return kradWorkflowDocumentService;
    }
    protected BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }
    protected BudgetCalculationService getBudgetCalculationService(){return budgetCalculationService;}
    protected BudgetPersonnelBudgetService getBudgetPersonnelBudgetService () {return budgetPersonnelBudgetService;}
    protected DeepCopyPostProcessor getDeepCopyPostProcessor (){ return  deepCopyPostProcessor;}

    public IdentityService getIdentityService() {
        return identityService;
    }

    public WorkflowDocumentService getKradWorkflowDocumentService() {
        return kradWorkflowDocumentService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
