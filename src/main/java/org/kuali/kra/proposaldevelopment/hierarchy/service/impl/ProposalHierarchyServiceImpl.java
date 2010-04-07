/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.hierarchy.service.impl;

import static org.apache.commons.lang.StringUtils.replace;
import static org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyKeyConstants.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyBudgetTypeConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyErrorDto;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.service.DeepCopyPostProcessor;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.doctype.service.DocumentTypeService;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.dto.DocumentTypeDTO;
import org.kuali.rice.kew.dto.ProcessDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.kns.workflow.service.WorkflowDocumentService;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class...
 */
@Transactional
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {
    
    private static final Log LOG = LogFactory.getLog(ProposalHierarchyServiceImpl.class);
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private KraAuthorizationService kraAuthorizationService;
    private ProposalHierarchyDao proposalHierarchyDao;
    private NarrativeService narrativeService;
    private BudgetService budgetService;
    private ProposalPersonBiographyService propPersonBioService;
    private ParameterService parameterService;
    private IdentityManagementService identityManagementService;
    private KualiConfigurationService configurationService;

    /**
     * Sets the identityManagerService attribute value.
     * @param identityManagerService The IdentityManagerService to set.
     */
    
    public void setIdentityManagementService(IdentityManagementService identityManagerService) {
        this.identityManagementService = identityManagerService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Sets the kraAuthorizationService attribute value.
     * @param kraAuthorizationService The kraAuthorizationService to set.
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    /**
     * Sets the proposalHierarchyDao attribute value.
     * @param proposalHierarchyDao The proposalHierarchyDao to set.
     */
    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
    }

    /**
     * Sets the narrativeService attribute value.
     * @param narrativeService The narrativeService to set.
     */
    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    /**
     * Sets the budgetService attribute value.
     * @param budgetService The budgetService to set.
     */
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Sets the propPersonBioService attribute value.
     * @param propPersonBioService The propPersonBioService to set.
     */
    public void setPropPersonBioService(ProposalPersonBiographyService propPersonBioService) {
        this.propPersonBioService = propPersonBioService;
    }

    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#createHierarchy(java.lang.String)
     */
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
            KualiWorkflowDocument workflowDocument = KraServiceLocator.getService(WorkflowDocumentService.class).createWorkflowDocument("ProposalDevelopmentDocument", GlobalVariables.getUserSession().getPerson());
            GlobalVariables.getUserSession().setWorkflowDocument(workflowDocument);
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getRouteHeaderId().toString());
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
            budgetService.addBudgetVersion(newDoc, "Hierarchy Budget");
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }
        LOG.info(String.format("***New Hierarchy Parent (#%s) budget created", hierarchy.getProposalNumber()));
        
        // add aggregator to the document
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addRole(userId, RoleConstants.AGGREGATOR, newDoc);

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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#linkToHierarchy(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal, org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal, java.lang.String)
     */
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#removeFromHierarchy(java.lang.String)
     */
    public void removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String hierarchyProposalNumber = childProposal.getHierarchyParentProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDoc = getHierarchyBudget(hierarchyProposal);
        Budget hierarchyBudget = hierarchyBudgetDoc.getBudget();
        LOG.info(String.format("***Removing Child (#%s) from Parent (#%s)", childProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
        
        boolean isLast = proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposalNumber).size()==1;
        
        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        childProposal.setHierarchyParentProposalNumber(null);
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
            businessObjectService.save(childProposal);
            synchronizeAllChildren(hierarchyProposal);
        }
        LOG.info(String.format("***Removing Child (#%s) from Parent (#%s) complete", childProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()));
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeAllChildren(java.lang.String)
     */
    public void synchronizeAllChildren(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        prepareHierarchySync(pdDoc);
        synchronizeAll(pdDoc.getDevelopmentProposal());
        finalizeHierarchySync(pdDoc);
    }

    private void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        prepareHierarchySync(hierarchyProposal);
        synchronizeAll(hierarchyProposal);
        finalizeHierarchySync(hierarchyProposal);
    }

    private void synchronizeAll(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;
        DevelopmentProposal childProposal;
        LOG.info(String.format("***Synchronizing all Children of Parent (#%s)", hierarchyProposal.getProposalNumber()));
        if (!hierarchyProposal.isParent()) {
            throw new ProposalHierarchyException("Proposal " + hierarchyProposal.getProposalNumber()
                    + " is not a hierarchy parent");
        }
        for (String childProposalNumber : proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposal.getProposalNumber())) {
            childProposal = getDevelopmentProposal(childProposalNumber);
            changed |= synchronizeChild(hierarchyProposal, childProposal);
        }
        if (changed) {
            aggregateHierarchy(hierarchyProposal);
        }
        LOG.info(String.format("***Synchronizing all Children of Parent (#%s) complete", hierarchyProposal.getProposalNumber()));
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeChild(java.lang.String)
     */
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());
        LOG.info(String.format("***Synchronizing Child (#%s) of Parent (#%s)", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
        
        prepareHierarchySync(hierarchy);
        boolean changed = synchronizeChild(hierarchy, childProposal);
        if (changed) {
            aggregateHierarchy(hierarchy);
        }
        finalizeHierarchySync(hierarchy);
        LOG.info(String.format("***Synchronizing Child (#%s) of Parent (#%s) complete", childProposal.getProposalNumber(), hierarchy.getProposalNumber()));
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#lookupParent(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal)
     */
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        return getHierarchy(childProposal.getHierarchyParentProposalNumber());
    }

    private void linkChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode)
            throws ProposalHierarchyException {
        // set child to child status
        newChildProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        newChildProposal.setHierarchyParentProposalNumber(hierarchyProposal.getProposalNumber());
        newChildProposal.setHierarchyBudgetType(hierarchyBudgetTypeCode);
        // call synchronize
        synchronizeChild(hierarchyProposal, newChildProposal);
        // call aggregate
        aggregateHierarchy(hierarchyProposal);        
    }

    private void copyInitialData(DevelopmentProposal hierarchyProposal, DevelopmentProposal srcProposal)
            throws ProposalHierarchyException {
        // Required fields for saving document
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
        hierarchyProposal.setDeadlineType(srcProposal.getDeadlineType());
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
                cd.setCongressionalDistrictId(null);
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

    private boolean synchronizeChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal)
            throws ProposalHierarchyException {
        
        String instituteNarrativeTypeGroup = parameterService.getParameterValue(ProposalDevelopmentDocument.class, PARAMETER_NAME_INSTITUTE_NARRATIVE_TYPE_GROUP);
        
/*  TODO restore code below after testing
        if (isSynchronized(childProposal.getProposalNumber())) {
            return false;
        }
*/
        ProposalPerson pi = hierarchyProposal.getPrincipalInvestigator();
        List<PropScienceKeyword> oldKeywords = new ArrayList<PropScienceKeyword>();
        for (PropScienceKeyword keyword : hierarchyProposal.getPropScienceKeywords()) {
            if (StringUtils.equals(childProposal.getProposalNumber(), keyword.getHierarchyProposalNumber())) {
                oldKeywords.add(keyword);
            }
        }
        BudgetDocument<DevelopmentProposal> hierarchyBudgetDocument = getHierarchyBudget(hierarchyProposal); 
        Budget hierarchyBudget = hierarchyBudgetDocument.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getFinalOrLatestChildBudget(childProposal);
        Budget childBudget = childBudgetDocument.getBudget();
        ObjectUtils.materializeAllSubObjects(hierarchyBudget);
        ObjectUtils.materializeAllSubObjects(childBudget);
        childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal, childBudget));
        
        removeChildElements(hierarchyProposal, hierarchyBudget, childProposal.getProposalNumber());
        
        // copy PropScienceKeywords
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal.getProposalNumber(), keyword.getScienceKeyword());
            int index = oldKeywords.indexOf(newKeyword);
            if (index > -1) {
                newKeyword = oldKeywords.get(index);
            }
            newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.addPropScienceKeyword(newKeyword);
        }
        
        // copy PropSpecialReviews
        for(ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = (ProposalSpecialReview)ObjectUtils.deepCopy(review);
            newReview.setProposalNumber(hierarchyProposal.getProposalNumber());
            newReview.setSpecialReviewNumber(hierarchyProposal.getProposalDocument().getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));            
            newReview.setVersionNumber(null);
            newReview.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.getPropSpecialReviews().add(newReview);
        }
        
        // copy Narratives
        for (Narrative narrative : childProposal.getNarratives()) {
            if (!StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getAllowMultiple(), "N")
                    && !StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getNarrativeTypeGroup(), instituteNarrativeTypeGroup)) {
                Map<String,String> primaryKey = new HashMap<String,String>();            
                primaryKey.put("proposalNumber", narrative.getProposalNumber());
                primaryKey.put("moduleNumber", narrative.getModuleNumber()+"");
                NarrativeAttachment attachment = (NarrativeAttachment)businessObjectService.findByPrimaryKey(NarrativeAttachment.class, primaryKey);
                narrative.getNarrativeAttachmentList().clear();
                narrative.getNarrativeAttachmentList().add(attachment);
                
                Narrative newNarrative = (Narrative)ObjectUtils.deepCopy(narrative);
                newNarrative.setVersionNumber(null);
                newNarrative.setHierarchyProposalNumber(childProposal.getProposalNumber());
                narrativeService.addNarrative(hierarchyProposal.getProposalDocument(), newNarrative);
            }
        }

        // copy ProposalPersons
        int firstIndex, lastIndex;
        ProposalPerson firstInstance;
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            lastIndex = hierarchyProposal.getProposalPersons().lastIndexOf(person);
            firstInstance = (firstIndex == -1) ? null : hierarchyProposal.getProposalPersons().get(firstIndex);
            if (firstIndex == -1 || (firstIndex == lastIndex && !rolesAreSimilar(person, firstInstance))) {
                ProposalPerson newPerson;
                newPerson = (ProposalPerson)ObjectUtils.deepCopy(person);
                newPerson.setProposalNumber(hierarchyProposal.getProposalNumber());
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
                if (newPerson.equals(pi) && (firstIndex == -1 || !firstInstance.isInvestigator())) {
                    newPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                }
                hierarchyProposal.addProposalPerson(newPerson);
            }
        }
        businessObjectService.save(childProposal);
        LOG.info(String.format("***Beginning Hierarchy Budget Sync for Parent %s and Child %s", hierarchyProposal.getProposalNumber(), childProposal.getProposalNumber()));
        synchronizeChildBudget(hierarchyBudget, childBudget, childProposal.getProposalNumber(), childProposal.getHierarchyBudgetType());
        if (hierarchyBudget.getEndDate().after(hierarchyProposal.getRequestedEndDateInitial())) {
            hierarchyProposal.setRequestedEndDateInitial(hierarchyBudget.getEndDate());
        }
        if (childProposal.getRequestedEndDateInitial().after(hierarchyProposal.getRequestedEndDateInitial())) {
            hierarchyProposal.setRequestedEndDateInitial(childProposal.getRequestedEndDateInitial());
        }
        try {
            documentService.saveDocument(hierarchyBudgetDocument);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        LOG.info(String.format("***Completed Hierarchy Budget Sync for Parent %s and Child %s", hierarchyProposal.getProposalNumber(), childProposal.getProposalNumber()));
        
        return true;
    }
    
    private void synchronizeChildBudget(Budget parentBudget, Budget childBudget, String childProposalNumber, String hierarchyBudgetTypeCode)
            throws ProposalHierarchyException {
        try {
            BudgetPerson newPerson;
            Map<Integer, BudgetPerson> personMap = new HashMap<Integer, BudgetPerson>();
            for (BudgetPerson person : childBudget.getBudgetPersons()) {
                newPerson = (BudgetPerson) ObjectUtils.deepCopy(person);
                newPerson.setPersonSequenceNumber(parentBudget.getBudgetDocument().getHackedDocumentNextValue(
                        Constants.PERSON_SEQUENCE_NUMBER));
//                newPerson.setBudget(parentBudget);
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
//                newSubAwards.setBudget(parentBudget);
                newSubAwards.setBudgetVersionNumber(parentBudget.getBudgetVersionNumber());
                newSubAwards.setSubAwardNumber(parentBudget.getBudgetDocument().getHackedDocumentNextValue("subAwardNumber") != null ? parentBudget.getBudgetDocument().getHackedDocumentNextValue("subAwardNumber") : 1);
                newSubAwards.setVersionNumber(null);
                newSubAwards.setHierarchyProposalNumber(childProposalNumber);
                for (BudgetSubAwardAttachment attachment : newSubAwards.getBudgetSubAwardAttachments()) {
                    attachment.setSubAwardNumber(newSubAwards.getSubAwardNumber());
//                    attachment.setBudget(parentBudget);
                    attachment.setBudgetId(parentBudget.getBudgetId());
                    attachment.setBudgetSubawardAttachmentId(null);
                    attachment.setVersionNumber(null);
                }
                for (BudgetSubAwardFiles files : newSubAwards.getBudgetSubAwardFiles()) {
                    files.setSubAwardNumber(newSubAwards.getSubAwardNumber());
//                    files.setBudget(parentBudget);
                    files.setBudgetId(parentBudget.getBudgetId());
                    files.setVersionNumber(null);
                }
                List<BudgetAssociate> listToBeSaved = new ArrayList<BudgetAssociate>();
                listToBeSaved.add(newSubAwards);
                listToBeSaved.addAll(newSubAwards.getBudgetSubAwardFiles());
                listToBeSaved.addAll(newSubAwards.getBudgetSubAwardAttachments());
                businessObjectService.save(listToBeSaved);
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
                newCostShare = (BudgetCostShare)ObjectUtils.deepCopy(costShare);
                newCostShare.setBudgetId(budgetId);
                newCostShare.setDocumentComponentId(null);
                newCostShare.setObjectId(null);
                newCostShare.setVersionNumber(null);
                newCostShare.setHierarchyProposalNumber(childProposalNumber);
                parentBudget.add(newCostShare);
            }
            
            BudgetUnrecoveredFandA newUnrecoveredFandA;
            for (BudgetUnrecoveredFandA unrecoveredFandA : childBudget.getBudgetUnrecoveredFandAs()) {
                newUnrecoveredFandA = (BudgetUnrecoveredFandA)ObjectUtils.deepCopy(unrecoveredFandA);
                newUnrecoveredFandA.setBudgetId(budgetId);
                newUnrecoveredFandA.setDocumentComponentId(null);
                newUnrecoveredFandA.setObjectId(null);
                newUnrecoveredFandA.setVersionNumber(null);
                newUnrecoveredFandA.setHierarchyProposalNumber(childProposalNumber);
                parentBudget.add(newUnrecoveredFandA);
            }
            
            Map<Long, List<BudgetProjectIncome>> newProjectIncomes = new HashMap<Long, List<BudgetProjectIncome>>();
            BudgetProjectIncome newProjectIncome;
            List<BudgetProjectIncome> projectIncomeList;
            for (BudgetProjectIncome projectIncome : childBudget.getBudgetProjectIncomes()) {
                newProjectIncome = (BudgetProjectIncome)ObjectUtils.deepCopy(projectIncome);
                newProjectIncome.setBudgetId(budgetId);
                newProjectIncome.setDocumentComponentId(null);
                newProjectIncome.setObjectId(null);
                newProjectIncome.setVersionNumber(null);
                projectIncomeList = newProjectIncomes.get(projectIncome.getBudgetPeriodId());
                if (projectIncomeList == null) {
                    projectIncomeList = new ArrayList<BudgetProjectIncome>();
                    newProjectIncomes.put(projectIncome.getBudgetPeriodId(), projectIncomeList);
                }
                projectIncomeList.add(newProjectIncome);
            }
            
            for (int i = 0, j = parentStartPeriod; i < childPeriods.size(); i++, j++) {
                childPeriod = childPeriods.get(i);
                if (j >= parentPeriods.size()) {
                    parentPeriod = parentBudget.getNewBudgetPeriod();
                    parentPeriod.setBudgetPeriod(j + 1);
                    parentPeriod.setBudget(parentBudget);
                    parentPeriod.setStartDate(childPeriod.getStartDate());
                    parentPeriod.setEndDate(childPeriod.getEndDate());
                    parentPeriod.setBudgetId(budgetId);
                    parentBudget.add(parentPeriod);
                }
                else {
                    parentPeriod = parentPeriods.get(j);
                }
                
                budgetPeriodId = parentPeriod.getBudgetPeriodId();
                budgetPeriod = parentPeriod.getBudgetPeriod();
                BudgetLineItem parentLineItem;
                Integer lineItemNumber;

                projectIncomeList = newProjectIncomes.get(childPeriod.getBudgetPeriodId());
                for (BudgetProjectIncome projectIncome : projectIncomeList) {
                    projectIncome.setBudgetPeriodId(budgetPeriodId);
                    projectIncome.setBudgetPeriodNumber(budgetPeriod);
                    parentBudget.add(projectIncome);
                }
                
                if (StringUtils.equals(hierarchyBudgetTypeCode, HierarchyBudgetTypeConstants.SubBudget.code())) {
                    for (BudgetLineItem childLineItem : childPeriod.getBudgetLineItems()) {
                        parentLineItem = (BudgetLineItem) (KraServiceLocator.getService(DeepCopyPostProcessor.class).processDeepCopyWithDeepCopyIgnore(childLineItem));
                        lineItemNumber = parentBudget.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        
                        parentLineItem.setBudgetLineItemId(null);
                        parentLineItem.setBudgetId(budgetId);
                        parentLineItem.setBudgetPeriodId(budgetPeriodId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setVersionNumber(null);
                        parentLineItem.setObjectId(null);
                        
                        for (BudgetLineItemCalculatedAmount calAmt : parentLineItem.getBudgetLineItemCalculatedAmounts()) {
                            calAmt.setBudgetLineItemCalculatedAmountId(null);
                            calAmt.setBudgetId(budgetId);
                            calAmt.setBudgetPeriodId(budgetPeriodId);
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
                            details.setPersonNumber(parentBudget.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));

                            details.setBudgetPersonnelLineItemId(null);
                            details.setBudgetId(budgetId);
                            details.setBudgetPeriodId(budgetPeriodId);
                            details.setBudgetPeriod(budgetPeriod);
                            details.setLineItemNumber(lineItemNumber);
                            details.setVersionNumber(null);
                            details.setObjectId(null);
                            
                            for (BudgetPersonnelCalculatedAmount calAmt : details.getBudgetPersonnelCalculatedAmounts()) {
                                calAmt.setBudgetPersonnelCalculatedAmountId(null);
                                calAmt.setBudgetId(budgetId);
                                calAmt.setBudgetPeriodId(budgetPeriodId);
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
                    String directCostElement = parameterService.getParameterValue(BudgetDocument.class, PARAMETER_NAME_DIRECT_COST_ELEMENT);
                    String indirectCostElement = parameterService.getParameterValue(BudgetDocument.class, PARAMETER_NAME_INDIRECT_COST_ELEMENT);
                    
                    if (childPeriod.getTotalIndirectCost().isNonZero()) {
                        primaryKeys = new HashMap<String, String>();
                        primaryKeys.put("costElement", indirectCostElement);
                        costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, primaryKeys);
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setBudgetId(budgetId);
                        parentLineItem.setBudgetPeriodId(budgetPeriodId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
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
                        parentLineItem.setBudgetPeriodId(budgetPeriodId);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
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

    }

    private void aggregateHierarchy(DevelopmentProposal hierarchy) throws ProposalHierarchyException {
        LOG.info(String.format("***Aggregating Proposal Hierarchy #%s", hierarchy.getProposalNumber()));
        List<ProposalPersonBiography> biosToRemove = new ArrayList<ProposalPersonBiography>();
        for (ProposalPersonBiography bio : hierarchy.getPropPersonBios()) {
            String bioPersonId = bio.getPersonId();
            Integer bioRolodexId = bio.getRolodexId();
            boolean keep = false;
            for (ProposalPerson person : hierarchy.getProposalPersons()) {
                if ((bioPersonId != null && bioPersonId.equals(person.getPersonId())) 
                        || (bioRolodexId != null && bioRolodexId.equals(person.getRolodexId()))) {
                    bio.setProposalPersonNumber(person.getProposalPersonNumber());
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
        KualiForm oldForm = GlobalVariables.getKualiForm();
        GlobalVariables.setKualiForm(null);
        KraServiceLocator.getService(BudgetCalculationService.class).calculateBudget(hierarchyBudget);
        KraServiceLocator.getService(BudgetCalculationService.class).calculateBudgetSummaryTotals(hierarchyBudget);
        GlobalVariables.setKualiForm(oldForm);
        try {
            documentService.saveDocument(hierarchyBudgetDocument);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }

    private DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
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

    private boolean isSynchronized(String childProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal childProposal = getDevelopmentProposal(childProposalNumber);
        Budget childBudget = getFinalOrLatestChildBudget(childProposal).getBudget();
        ObjectUtils.materializeAllSubObjects(childBudget);
        int hc1 = computeHierarchyHashCode(childProposal, childBudget);
        int hc2 = childProposal.getHierarchyLastSyncHashCode();
        return hc1 == hc2;
    }
    
    private void setInitialPi(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        ProposalPerson pi = child.getPrincipalInvestigator();
        if (pi != null) {
            int index = hierarchy.getProposalPersons().indexOf(pi);
            if (index > -1) {
                hierarchy.getProposalPerson(index).setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                hierarchy.getProposalPerson(index).setHierarchyProposalNumber(null);
            }
        }
    }
    
    private BudgetDocument<DevelopmentProposal> getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        String budgetDocumentNumber = hierarchyProposal.getProposalDocument().getBudgetDocumentVersions().get(0).getBudgetVersionOverview().getDocumentNumber();
        BudgetDocument<DevelopmentProposal> budgetDocument = null;
        try {
            budgetDocument = (BudgetDocument<DevelopmentProposal>) documentService.getByDocumentHeaderId(budgetDocumentNumber);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
        return budgetDocument;//.getBudget();
    }
 
    private BudgetDocument<DevelopmentProposal> getFinalOrLatestChildBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException {
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
        return budgetDocument;//.getBudget();
    }
    
    private void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        BudgetDocument<DevelopmentProposal> parentBudgetDoc = getHierarchyBudget(hierarchyProposal);
        Budget parentBudget = parentBudgetDoc.getBudget();
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getFinalOrLatestChildBudget(childProposal); 
        Budget childBudget = childBudgetDocument.getBudget();
        BudgetPeriod parentPeriod, childPeriod;
        for (int i=0; i < childBudget.getBudgetPeriods().size(); i++) {
            parentPeriod = parentBudget.getBudgetPeriod(i);
            childPeriod = childBudget.getBudgetPeriod(i);
            parentPeriod.setStartDate(childPeriod.getStartDate());
            parentPeriod.setEndDate(childPeriod.getEndDate());
            parentPeriod.setBudgetPeriod(childPeriod.getBudgetPeriod());
        }
        
        parentBudget.setCostSharingAmount(new BudgetDecimal(0));
        parentBudget.setTotalCost(new BudgetDecimal(0));
        parentBudget.setTotalDirectCost(new BudgetDecimal(0));
        parentBudget.setTotalIndirectCost(new BudgetDecimal(0));
        parentBudget.setUnderrecoveryAmount(new BudgetDecimal(0));
        
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
        BudgetDocument<DevelopmentProposal> childBudgetDocument = getFinalOrLatestChildBudget(childProposal); 
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
    
    private int getCorrespondingParentPeriod(Budget parentBudget, Budget childBudget) {
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
    
    private void removeChildElements(DevelopmentProposal parentProposal, Budget parentBudget, String childProposalNumber) {
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
                narratives.remove(i);
            }
        }

        List<ProposalPerson> persons = parentProposal.getProposalPersons();
        for (int i=persons.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, persons.get(i).getHierarchyProposalNumber())) {
                persons.remove(i);
            }
        }
        
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

        List<BudgetCostShare> costShares = parentBudget.getBudgetCostShares();
        for (int i=costShares.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, costShares.get(i).getHierarchyProposalNumber())) {
                costShares.remove(i);
            }
        }

        List<BudgetUnrecoveredFandA> unrecoveredFandAs = parentBudget.getBudgetUnrecoveredFandAs();
        for (int i=unrecoveredFandAs.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, unrecoveredFandAs.get(i).getHierarchyProposalNumber())) {
                unrecoveredFandAs.remove(i);
            }
        }

        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
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
//            if (lineItems.isEmpty()) {
//                periods.remove(period);
//            }
        }
        
        List<BudgetPerson> budgetPersons = parentBudget.getBudgetPersons();
        for (int i=budgetPersons.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, budgetPersons.get(i).getHierarchyProposalNumber())) {
                budgetPersonnelBudgetService.deleteBudgetPersonnelDetailsForPerson(parentBudget, budgetPersons.get(i));
                budgetPersons.remove(i);
            }
        }
    }
    
    private void prepareHierarchySync(DevelopmentProposal hierarchyProposal) {
        prepareHierarchySync(hierarchyProposal.getProposalDocument());
    }
    
    private void prepareHierarchySync(ProposalDevelopmentDocument pdDoc) {
        pdDoc.refreshReferenceObject("documentNextvalues");
    }
    
    private void finalizeHierarchySync(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        pdDoc.refreshReferenceObject("budgetDocumentVersions");
        try {
            documentService.saveDocument(pdDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }
    
    private void finalizeHierarchySync(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        businessObjectService.save(hierarchyProposal.getProposalDocument().getDocumentNextvalues());
        businessObjectService.save(hierarchyProposal);
    }
        
    private void copyInitialAttachments(DevelopmentProposal srcProposal, DevelopmentProposal destProposal) {
        String instituteNarrativeTypeGroup = parameterService.getParameterValue(ProposalDevelopmentDocument.class, PARAMETER_NAME_INSTITUTE_NARRATIVE_TYPE_GROUP);
        
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
            propPersonBioService.addProposalPersonBiography(destProposal.getProposalDocument(), destPropPersonBio);
        }

        Narrative destNarrative;
        for (Narrative srcNarrative : srcProposal.getNarratives()) {
            if (StringUtils.equalsIgnoreCase(srcNarrative.getNarrativeType().getAllowMultiple(), "N") 
                    && !srcProposal.getInstituteAttachments().contains(srcNarrative)
                    && !StringUtils.equalsIgnoreCase(srcNarrative.getNarrativeType().getNarrativeTypeGroup(), instituteNarrativeTypeGroup)) {
                loadAttachmentContent(srcNarrative);
                destNarrative = (Narrative)ObjectUtils.deepCopy(srcNarrative);
                destNarrative.setModuleStatusCode("I");
                narrativeService.addNarrative(destProposal.getProposalDocument(), destNarrative);
            }
        }
    }

    private void loadAttachmentContent(Narrative narrative){
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put("proposalNumber", narrative.getProposalNumber());
        primaryKey.put("moduleNumber", narrative.getModuleNumber()+"");
        NarrativeAttachment attachment = (NarrativeAttachment)businessObjectService.findByPrimaryKey(NarrativeAttachment.class, primaryKey);
        narrative.getNarrativeAttachmentList().clear();
        narrative.getNarrativeAttachmentList().add(attachment);
    }
    
    private void loadBioContent(ProposalPersonBiography bio){
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put("proposalNumber", bio.getProposalNumber());
        primaryKey.put("biographyNumber", bio.getBiographyNumber()+"");
        primaryKey.put("proposalPersonNumber", bio.getProposalPersonNumber()+"");
        ProposalPersonBiographyAttachment attachment = (ProposalPersonBiographyAttachment)businessObjectService.findByPrimaryKey(ProposalPersonBiographyAttachment.class, primaryKey);
        bio.getPersonnelAttachmentList().clear();
        bio.getPersonnelAttachmentList().add(attachment);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyChildRouteStatus(java.lang.String, java.lang.String)
     */
    public String getHierarchyChildRouteStatus( String oldStatus, String newStatus) {
        
        LOG.info( String.format( "Route status change %s:%s",oldStatus,newStatus));
        
        String retCd = null;
        if( StringUtils.equals(newStatus,KEWConstants.ROUTE_HEADER_ENROUTE_CD) 
                && ( StringUtils.equals( oldStatus, KEWConstants.ROUTE_HEADER_INITIATED_CD) 
                || StringUtils.equals(oldStatus, KEWConstants.ROUTE_HEADER_SAVED_CD)  
                || StringUtils.equals(KEWConstants.ROUTE_HEADER_ENROUTE_CD, oldStatus)) ) { 
                retCd = renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(newStatus, KEWConstants.ROUTE_HEADER_FINAL_CD)) {
                retCd = renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KEWConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
                retCd = renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KEWConstants.ROUTE_HEADER_CANCEL_CD ) ) {
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
    private int computeHierarchyHashCode(DevelopmentProposal proposal, Budget budget) {
        int prime = 31;
        int result = 1;
        KraServiceLocator.getService(BudgetCalculationService.class).calculateBudget(budget);
        KraServiceLocator.getService(BudgetCalculationService.class).calculateBudgetSummaryTotals(budget);
        for (ProposalPerson person : proposal.getProposalPersons()) {
            result = prime * result + person.hashCode();
        }
        for (Narrative narrative : proposal.getNarratives()) {
            result = prime * result + narrative.hierarchyHashCode();
        }
        for (PropScienceKeyword keyword : proposal.getPropScienceKeywords()) {
            result = prime * result + keyword.getScienceKeywordCode().hashCode();
        }
        for (ProposalSpecialReview review : proposal.getPropSpecialReviews()) {
            result = prime * result + review.hierarchyHashCode();
        }
        result = prime * result + budget.getBudgetSummaryTotals().hashCode();
        return result;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getChildProposalDevelopmentDocuments(java.lang.String)
     */
    public List<ProposalDevelopmentDocument> getChildProposalDevelopmentDocuments(String parentProposalNumber) throws ProposalHierarchyException {
       
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyChildren(java.lang.String)
     */
    public List<DevelopmentProposal> getHierarchyChildren(String parentProposalNumber) {
        List<DevelopmentProposal> children = new ArrayList<DevelopmentProposal>();
        for( String childProposalNumber : proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber)) {
            children.add(getDevelopmentProposal(childProposalNumber));
        }
        return children;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getParentWorkflowStatus(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal)
     */
    public KualiWorkflowDocument getParentWorkflowDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
            return getParentDocument( child ).getDocumentHeader().getWorkflowDocument();
    }

    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getParentDocument(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
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
     * @param principalName the principal name we are rejecting the document as.
     * @param appDocStatus the application document status to apply ( does not apply if null )
     * @throws WorkflowException
     */
    private void rejectProposal( ProposalDevelopmentDocument proposalDoc, String reason, String principalName, String appDocStatus ) throws WorkflowException  {
            WorkflowDocument workflowDocument = new WorkflowDocument(identityManagementService.getPrincipalByPrincipalName(principalName).getPrincipalId(), proposalDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
            workflowDocument.returnToPreviousNode(reason, getProposalDevelopmentInitialNodeName() );
            workflowDocument.updateAppDocStatus( appDocStatus );
    }
    
    
    /**
     * Reject an entire proposal hierarchy.  This works by first rejecting each child, and then rejecting the parent.
     * @param hierarchyParent The hierarchy to reject
     * @param reason the reason to be applied to the annotation field.  The reason will be pre-pended with static text indicating if it was a child or the parent.
     * @param principalName the name of the principal that is rejecting the document.  
     * @throws ProposalHierarchyException If hierarchyParent is not a hierarchy, or there was a problem rejecting one of the documents.
     */
    private void rejectProposalHierarchy(ProposalDevelopmentDocument hierarchyParent, String reason, String principalName ) throws ProposalHierarchyException {
        
      //1. reject the parent.
        try {
            rejectProposal( hierarchyParent, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalName, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting proposal hierarchy parent %s", hierarchyParent.getDevelopmentProposal().getProposalNumber() ),e);
        }
        
        //2. Try to reject all of the children.
        for( ProposalDevelopmentDocument child : getChildProposalDevelopmentDocuments(hierarchyParent.getDevelopmentProposal().getProposalNumber())) {
            try {
                rejectProposal( child, renderMessage( HIERARCHY_ROUTING_PARENT_REJECTED_ANNOTATION, reason ), KEWConstants.SYSTEM_USER, renderMessage( HIERARCHY_CHILD_REJECTED_APPSTATUS ) );
            } catch (WorkflowException e) {
                throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting child document %s", child.getDevelopmentProposal().getProposalNumber()), e );
            }
        }
     
    }
    
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#rejectProposalDevelopmentDocument(java.lang.String, java.lang.String)
     */
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName ) throws WorkflowException, ProposalHierarchyException {
        DevelopmentProposal pbo = getDevelopmentProposal(proposalNumber);
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(getDevelopmentProposal(proposalNumber).getProposalDocument().getDocumentNumber());
        if( !pbo.isInHierarchy() ) {
            rejectProposal( pDoc, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalName, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        } else if ( pbo.isParent() ) {
            rejectProposalHierarchy( pDoc, reason, principalName );
        } else {
            //it is a child or in some unknown state, either way we do not support rejecting it.
            throw new UnsupportedOperationException( String.format( "Cannot reject proposal %s it is a hierarchy child or ", proposalNumber ));
        }
    }
    
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getProposalDevelopmentInitialNodeName()
     */
    public String getProposalDevelopmentInitialNodeName() {
        DocumentTypeService dService = KEWServiceLocator.getDocumentTypeService();
        DocumentTypeDTO proposalDevDocType = dService.getDocumentTypeVO("ProposalDevelopmentDocument");
        ProcessDTO p = proposalDevDocType.getRoutePath().getPrimaryProcess();
        return p.getInitialRouteNode().getRouteNodeName();
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#isProposalOnInitialRouteNode(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean isProposalOnInitialRouteNode( ProposalDevelopmentDocument document ) {
        boolean ret = false;
        try {
            if( ArrayUtils.contains(document.getDocumentHeader().getWorkflowDocument().getNodeNames(), getProposalDevelopmentInitialNodeName())) ret = true;
        } catch ( WorkflowException we ) {
            throw new RuntimeException( String.format( "Could not get node names for document: %s", document.getDocumentNumber()), we );
        }
        return ret;
    }
    
    /**
     * Based on the hierarchy, and route status change of the parent, calculate what route action should be taken on the children.
     * @param hierarchy the heirarchy being routed
     * @param dto the route status change information.
     * @return The route action to take on the children.
     */
    private String calculateChildRouteStatus( ProposalDevelopmentDocument hierarchy, DocumentRouteStatusChangeDTO dto ) {
        
        String parentOldStatus = dto.getOldRouteStatus();
        String parentNewStatus = dto.getNewRouteStatus();
        String newChildStatusTarget = "";

        if (StringUtils.equals(parentOldStatus, KEWConstants.ROUTE_HEADER_INITIATED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KEWConstants.ROUTE_HEADER_SAVED_CD)) {
            // previous status was saved
            newChildStatusTarget = parentNewStatus;
            if (StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_ENROUTE_CD)) {
                // nothing to do
            }
            else if (StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_CANCEL_CD)) {
                // nothing to do.
            }
            else {
                throw new UnsupportedOperationException(String.format(
                        "Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,
                        parentNewStatus));
            }
        }
        else if (StringUtils.equals(parentOldStatus, KEWConstants.ROUTE_HEADER_ENROUTE_CD)) {
            // we are moving from enroute to some other state.

            if (StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_CANCEL_CD)
                    || StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                newChildStatusTarget = parentNewStatus;
            }
            else if (StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_APPROVED_CD)) {
                // nothing to do here, wait for the document to go final.
            } else if( StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_ENROUTE_CD)) {
                //special case, document has been rejected and being approved again to simulate entry into workflow.
                //this value will trigger an approve.
                newChildStatusTarget = KEWConstants.ROUTE_HEADER_ENROUTE_CD;
            }
            else {
                throw new UnsupportedOperationException(String.format("Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
            }

        }
        else if (StringUtils.equals(parentOldStatus, KEWConstants.ROUTE_HEADER_APPROVED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KEWConstants.ROUTE_HEADER_PROCESSED_CD)) {
            if (StringUtils.equals(parentNewStatus, KEWConstants.ROUTE_HEADER_FINAL_CD)) {
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#routeHierarchyChildren(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO, java.lang.String)
     */
    public void routeHierarchyChildren(ProposalDevelopmentDocument proposalDevelopmentDocument, DocumentRouteStatusChangeDTO dto ) throws ProposalHierarchyException {
        
        String childStatusTarget = calculateChildRouteStatus(proposalDevelopmentDocument, dto );
        WorkflowDocument workdoc;
        ProposalDevelopmentDocument child = null;
        try {
            LOG.info(  IdentityManagementService.class );
            for (ProposalDevelopmentDocument c : getChildProposalDevelopmentDocuments(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber() )) {
                child = c;
                if (!StringUtils.equals("", childStatusTarget)) {

                    if (StringUtils.equals(KEWConstants.ROUTE_HEADER_ENROUTE_CD, childStatusTarget)) {
                        //The user currently must initially route the child documents in order for them to hold in the system users action list.

                        workdoc = new WorkflowDocument(child.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId(), child.getDocumentHeader().getWorkflowDocument().getRouteHeaderId() );
                        workdoc.updateAppDocStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));
                        if( !workdoc.stateIsEnroute() ) {
                            workdoc.routeDocument(renderMessage( HIERARCHY_ROUTING_PARENT_SUBMITTED_ANNOTATION ));

                        } else {
                            //this means the status change is actually in the form of an approve action on a document that was moved back to the initial node.
                            //we need to do an approval.
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_RESUBMITTED_ANNOTATION ));
                            workdoc.updateAppDocStatus(renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS ) );
                        }

                    } else {
                        workdoc = new WorkflowDocument( identityManagementService.getPrincipalByPrincipalName(KNSConstants.SYSTEM_USER ).getPrincipalId(),child.getDocumentHeader().getWorkflowDocument().getRouteHeaderId() );
                        workdoc.updateAppDocStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));

                        if (StringUtils.equals(KEWConstants.ROUTE_HEADER_CANCEL_CD,childStatusTarget)) {
                            workdoc.cancel(renderMessage( HIERARCHY_ROUTING_PARENT_CANCELLED_ANNOTATION));
                            workdoc.updateAppDocStatus(renderMessage( HIERARCHY_CHILD_CANCEL_APPSTATUS  ));

                        } else if (StringUtils.equals(KEWConstants.ROUTE_HEADER_FINAL_CD, childStatusTarget)) {
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_APPROVED_ANNOTATION ));
                            workdoc.updateAppDocStatus(renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS ));

                        } else if (StringUtils.equals(KEWConstants.ROUTE_HEADER_DISAPPROVED_CD, childStatusTarget)) {
                            workdoc.disapprove(renderMessage( HIERARCHY_ROUTING_PARENT_DISAPPROVED_ANNOTATION ));
                            workdoc.updateAppDocStatus(renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS ));
                        } else {
                            throw new UnsupportedOperationException(String.format("Do not know how to handle new child status of %s", childStatusTarget));
                        }

                    }
                }
            }
        } catch ( WorkflowException we ) {
            throw new ProposalHierarchyException( String.format( "WorkflowException encountrered while attempting to route child proposal %s ( document #%s ) of proposal hierarchy %s ( document #%s )", child.getDevelopmentProposal().getProposalNumber(), child.getDocumentNumber(), proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), proposalDevelopmentDocument.getDocumentNumber() ), we);
        }
        
    }
    
    public void calculateAndSetProposalAppDocStatus( ProposalDevelopmentDocument doc, DocumentRouteStatusChangeDTO dto  ) throws ProposalHierarchyException {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        if( StringUtils.equals( dto.getNewRouteStatus(), KEWConstants.ROUTE_HEADER_ENROUTE_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KEWConstants.ROUTE_HEADER_CANCEL_CD)) {
            updateAppDocStatus( doc, principalId, HIERARCHY_CANCEL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KEWConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_DISAPPROVE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KEWConstants.ROUTE_HEADER_FINAL_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_FINAL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KEWConstants.ROUTE_HEADER_PROCESSED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_PROCESSED_APPSTATUS ) ;
        } 
    }
    
    public void updateAppDocStatus( ProposalDevelopmentDocument doc, String principalId, String newStatus ) throws ProposalHierarchyException {
        try {
            WorkflowDocument wdoc = new WorkflowDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId() );
            wdoc.updateAppDocStatus(renderMessage( newStatus ));
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format( "WorkflowException encountrered while attempting to update App Doc Status of proposal %s ( document #%s )", doc.getDevelopmentProposal().getProposalNumber(), doc.getDocumentNumber() ), e);
        }
    }
    
    
    
    public boolean allChildBudgetsAreComplete(String parentProposalNumber) {
        boolean retval = true;
        String completeCode = parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        for (ProposalBudgetStatus status : proposalHierarchyDao.getHierarchyChildProposalBudgetStatuses(parentProposalNumber)) {
            if (!StringUtils.equalsIgnoreCase(completeCode, status.getBudgetStatusCode())) {
                retval = false;
                break;
            }
        }
        return retval;
    }
    
    private boolean rolesAreSimilar(ProposalPerson person1, ProposalPerson person2) {
        boolean isInvestigator1 = StringUtils.equals(person1.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person1.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        boolean isInvestigator2 = StringUtils.equals(person2.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person2.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        return isInvestigator1 == isInvestigator2;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyProposalSummaries(java.lang.String)
     */
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
                summary.setSynced(isSynchronized(number));
            }
            summaries.add(summary);
        }
        return summaries;
    }

    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId) {
        boolean valid = true;
        valid &= kraAuthorizationService.hasPermission(principalId, childProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        try {
            valid &= kraAuthorizationService.hasPermission(principalId, getHierarchy(childProposal.getHierarchyParentProposalNumber()).getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        catch (ProposalHierarchyException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Gets the configurationService attribute. 
     * @return Returns the configurationService.
     */
    public KualiConfigurationService getConfigurationService() {
        return configurationService;
    }

    /**
     * Sets the configurationService attribute value.
     * @param configurationService The configurationService to set.
     */
    public void setConfigurationService(KualiConfigurationService configurationService) {
        this.configurationService = configurationService;
    }


    private String renderMessage( String key, String... params ) {
       String msg = configurationService.getPropertyString(key);
       for (int i = 0; i < params.length; i++) {
           msg = replace(msg, "{" + i + "}", params[i]);
       }
       return msg;
       
    }

}