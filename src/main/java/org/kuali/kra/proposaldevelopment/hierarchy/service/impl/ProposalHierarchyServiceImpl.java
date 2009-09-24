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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchyChild;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class...
 */
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {

    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private KraAuthorizationService kraAuthorizationService;
    private ProposalHierarchyDao proposalHierarchyDao;
    private NarrativeService narrativeService;

    /**
     * Set the Business Object Service. It is set via dependency injection.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
    }
    
    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#createHierarchy(java.lang.String)
     */
    public String createHierarchy(DevelopmentProposal initialChild) throws ProposalHierarchyException {
        if (initialChild.isInHierarchy()) {
            throw new ProposalHierarchyException("Cannot create hierarchy: proposal " + initialChild.getProposalNumber()
                    + " is already a member of a hierarchy.");
        }

        // create a new proposal document
        ProposalDevelopmentDocument newDoc;
        try {
            newDoc = (ProposalDevelopmentDocument) documentService.getNewDocument(ProposalDevelopmentDocument.class);
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

        // persist the document
        try {
            documentService.saveDocument(newDoc);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }

        // add aggregator to the document
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        String username = user.getPersonUserIdentifier();
        kraAuthorizationService.addRole(username, RoleConstants.AGGREGATOR, newDoc);

        // link the child to the parent
        linkChild(hierarchy, initialChild);
        setInitialPi(hierarchy, initialChild);

        // persist the document again
        try {
            documentService.saveDocument(newDoc);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }
        
        businessObjectService.save(initialChild);

        // return the parent id
        return hierarchy.getProposalNumber();
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#linkToHierarchy(java.lang.String,
     *      java.lang.String)
     */
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal) throws ProposalHierarchyException {
        if (!hierarchyProposal.isParent()) {
            throw new ProposalHierarchyException("Proposal " + hierarchyProposal.getProposalNumber()
                    + " is not a hierarchy parent");
        }
        if (newChildProposal.isInHierarchy()) {
            throw new ProposalHierarchyException("Proposal " + newChildProposal.getProposalNumber()
                    + " is already a member of a hierarchy");
        }
        linkChild(hierarchyProposal, newChildProposal);
        businessObjectService.save(newChildProposal);
        businessObjectService.save(hierarchyProposal);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#removeFromHierarchy(java.lang.String)
     */
    public void removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposal.getProposalNumber());
        String hierarchyProposalNumber = hierarchyChild.getHierarchyProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);

        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        hierarchyProposal.getChildren().remove(hierarchyChild);

        businessObjectService.delete(hierarchyChild);

        if (hierarchyProposal.getChildren().size() == 0) {
            try {
                Document doc = documentService.getByDocumentHeaderId(hierarchyProposal.getProposalDocument().getDocumentNumber());
                documentService.cancelDocument(doc, "Removed last child from Proposal Hierarchy");
            }
            catch (WorkflowException e) {
                throw new ProposalHierarchyException("Error cancelling empty parent proposal");
            }
        }
        else {
            synchronizeAllChildren(hierarchyProposal);
        }
        businessObjectService.save(childProposal);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeAllChildren(java.lang.String)
     */
    public void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        if (!hierarchyProposal.isParent()) {
            throw new ProposalHierarchyException("Proposal " + hierarchyProposal.getProposalNumber()
                    + " is not a hierarchy parent");
        }
        boolean changed = false;
        for (ProposalHierarchyChild child : hierarchyProposal.getChildren()) {
            changed |= synchronizeChild(hierarchyProposal, child, getDevelopmentProposal(child.getProposalNumber()));
        }
        if (changed) {
            aggregateHierarchy(hierarchyProposal);
        }
        businessObjectService.save(hierarchyProposal);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeChild(java.lang.String)
     */
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposal.getProposalNumber());
        DevelopmentProposal hierarchy = getHierarchy(hierarchyChild.getHierarchyProposalNumber());
        
        // the next statement is to avoid an OJB optimistic locking exception because of different object references
        hierarchyChild = hierarchy.getChildren().get(hierarchy.getChildren().indexOf(hierarchyChild));
        
        boolean changed = synchronizeChild(hierarchy, hierarchyChild, childProposal);
        if (changed) {
            aggregateHierarchy(hierarchy);
        }
        businessObjectService.save(hierarchy);
    }
    
    

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#lookupParent(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal)
     */
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposal.getProposalNumber());
        DevelopmentProposal hierarchy = getHierarchy(hierarchyChild.getHierarchyProposalNumber());
        return hierarchy;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getProposalSummaries(java.lang.String)
     */
    public List<HierarchyProposalSummary> getProposalSummaries(String proposalNumber) throws ProposalHierarchyException {
        List<HierarchyProposalSummary> summaries = new ArrayList<HierarchyProposalSummary>();
        DevelopmentProposal hierarchy = null;
        try {
            hierarchy = getHierarchy(proposalNumber);
        }
        catch (ProposalHierarchyException x) {
            try {
                ProposalHierarchyChild child = getHierarchyChild(proposalNumber);
                hierarchy = getHierarchy(child.getHierarchyProposalNumber());
            }
            catch (ProposalHierarchyException phx) {
                throw new ProposalHierarchyException("Proposal " + proposalNumber + " is not a member of a hierarchy.");
            }
        }
        List<String> proposalNumbers = new ArrayList<String>();
        proposalNumbers.add(hierarchy.getProposalNumber());
        List<ProposalHierarchyChild> children = hierarchy.getChildren();
        HierarchyProposalSummary summary;
        for (ProposalHierarchyChild child : children) {
            proposalNumbers.add(child.getProposalNumber());
        }

        for (String number : proposalNumbers) {
            summary = proposalHierarchyDao.getProposalSummary(number);
            if (!StringUtils.equals(number, hierarchy.getProposalNumber())) {
                summary.setSynced(isSynchronized(number));
            }
            summaries.add(summary);
        }

        return summaries;
    }

    private void linkChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal)
            throws ProposalHierarchyException {
        // set child to child status
        newChildProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        // create and populate child bo
        ProposalHierarchyChild childBO = new ProposalHierarchyChild();
        childBO.setProposalNumber(newChildProposal.getProposalNumber());
        childBO.setHierarchyProposalNumber(hierarchyProposal.getProposalNumber());
        // add bo to parent
        hierarchyProposal.getChildren().add(childBO);
        // call synchronize
        synchronizeChild(hierarchyProposal, childBO, newChildProposal);
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
        hierarchyProposal.setApplicantOrganization(srcProposal.getApplicantOrganization());
        hierarchyProposal.setApplicantOrganizationId(srcProposal.getApplicantOrganization().getOrganizationId());
        hierarchyProposal.setPerformingOrganization(srcProposal.getPerformingOrganization());
        hierarchyProposal.setPerformingOrganizationId(srcProposal.getPerformingOrganization().getOrganizationId());
        for (ProposalSite site : srcProposal.getPerformanceSites()) {
            hierarchyProposal.addPerformanceSite(site);
        }
        for (ProposalSite site : srcProposal.getOtherOrganizations()) {
            hierarchyProposal.addOtherOrganization(site);
        }

        // Delivery info
        hierarchyProposal.setMailBy(srcProposal.getMailBy());
        hierarchyProposal.setMailType(srcProposal.getMailType());
        hierarchyProposal.setMailAccountNumber(srcProposal.getMailAccountNumber());
        hierarchyProposal.setNumberOfCopies(srcProposal.getNumberOfCopies());
        hierarchyProposal.setMailingAddressId(srcProposal.getMailingAddressId());
        hierarchyProposal.setMailDescription(srcProposal.getMailDescription());
    }

    private boolean synchronizeChild(DevelopmentProposal hierarchyProposal, ProposalHierarchyChild hierarchyChild, DevelopmentProposal childProposal)
            throws ProposalHierarchyException {
        
        String principleInvestigatorId = null;

        if (childProposal.hierarchyChildHashCode() == hierarchyChild.getProposalHashCode()) {
            return false;
        }

        // remove and copy PropScienceKeywords
        for (PropScienceKeyword keyword : hierarchyChild.getPropScienceKeywords()) {
            hierarchyProposal.getPropScienceKeywords().remove(keyword);
        }
        hierarchyChild.getPropScienceKeywords().clear();
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal.getProposalNumber(), keyword.getScienceKeyword());
            newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.addPropScienceKeyword(newKeyword);
            hierarchyChild.getPropScienceKeywords().add(newKeyword);
        }
        
        // remove and copy PropSpecialReviews
        for (ProposalSpecialReview review : hierarchyChild.getPropSpecialReviews()) {
            hierarchyProposal.getPropSpecialReviews().remove(review);
        }
        hierarchyChild.getPropSpecialReviews().clear();
        for(ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = (ProposalSpecialReview)ObjectUtils.deepCopy(review);
            newReview.setProposalNumber(hierarchyProposal.getProposalNumber());
            newReview.setSpecialReviewNumber(hierarchyProposal.getProposalDocument().getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));            newReview.setVersionNumber(null);
            newReview.setHierarchyProposalNumber(childProposal.getProposalNumber());
            newReview.setVersionNumber(null);
            hierarchyProposal.getPropSpecialReviews().add(newReview);
            hierarchyChild.getPropSpecialReviews().add(newReview);
        }
        
        // remove and copy Narratives
        for (Narrative narrative : hierarchyChild.getNarratives()) {
            hierarchyProposal.getNarratives().remove(narrative);
        }
        hierarchyChild.getNarratives().clear();
        for (Narrative narrative : childProposal.getNarratives()) {
            if (!StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getAllowMultiple(), "N")) {
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
                hierarchyChild.getNarratives().add(newNarrative);
            }
        }

        // remove and copy ProposalPersons
        for (ProposalPerson person : hierarchyChild.getProposalPersons()) {
            if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), "PI")) {
                principleInvestigatorId = person.getPersonId();
            }
            hierarchyProposal.getProposalPersons().remove(person);
        }
        hierarchyChild.getProposalPersons().clear();
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            ProposalPerson newPerson = (ProposalPerson)ObjectUtils.deepCopy(person);
            if (StringUtils.equalsIgnoreCase(newPerson.getProposalPersonRoleId(), "PI")) {
                newPerson.setProposalPersonRoleId("COI");
            }
            if (newPerson.getPersonId().equals(principleInvestigatorId)) {
                newPerson.setProposalPersonRoleId("PI");
            }
            newPerson.setProposalNumber(hierarchyProposal.getProposalNumber());
            newPerson.setProposalPersonNumber(null);
            newPerson.setVersionNumber(null);
            newPerson.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.addProposalPerson(newPerson);
            hierarchyChild.getProposalPersons().add(newPerson);
        }

        hierarchyChild.setProposalHashCode(childProposal.hierarchyChildHashCode());

        return true;
    }

    private void aggregateHierarchy(DevelopmentProposal hierarchy) throws ProposalHierarchyException {
        
    }

    private void aggregateProposalPersons(DevelopmentProposal hierarchy) {
        ProposalPerson oldPrincipalInvestigator = null;
        for (ProposalPerson person : hierarchy.getProposalPersons()) {
            if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), "PI")) {
                oldPrincipalInvestigator = person;
                break;
            }
        }

        Set<ProposalPerson> proposalPersons = new HashSet<ProposalPerson>();
        for (ProposalHierarchyChild child : hierarchy.getChildren()) {
            for (ProposalPerson person : child.getProposalPersons()) {
                proposalPersons.add(person);
                person.setVersionNumber(null);
                if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), "PI")) {
                    person.setProposalPersonRoleId("COI");
                }
            }
        }

        hierarchy.getProposalPersons().clear();
        hierarchy.getProposalPersons().addAll(proposalPersons);
        for (ProposalPerson person : hierarchy.getProposalPersons()) {
            person.setHiddenInHierarchy(false);
            if (person.equals(oldPrincipalInvestigator))
                person.setProposalPersonRoleId("PI");
        }
    }

    private void removeNonExclusiveNarratives(List<Narrative> narratives) {
        Narrative narrative;
        for (int i = narratives.size() - 1; i >= 0; i--) {
            narrative = narratives.get(i);
            if (StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getAllowMultiple(), "Y")) {
                narratives.remove(i);
            }
        }
    }

    private DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }

    private ProposalHierarchyChild getHierarchyChild(String childProposalNumber) throws ProposalHierarchyException {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", childProposalNumber);
        ProposalHierarchyChild child = (ProposalHierarchyChild) (businessObjectService.findByPrimaryKey(
                ProposalHierarchyChild.class, pk));
        if (child == null)
            throw new ProposalHierarchyException("Proposal " + childProposalNumber + " not found or not a child in a hierarchy");
        return child;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (businessObjectService.findByPrimaryKey(DevelopmentProposal.class, pk));
    }

    private boolean isSynchronized(String childProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal childProposal = getDevelopmentProposal(childProposalNumber);
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposalNumber);
        return childProposal.hierarchyChildHashCode() == hierarchyChild.getProposalHashCode();
    }
    
    private void setInitialPi(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        ProposalPerson pi = null;
        for (ProposalPerson person : child.getProposalPersons()) {
            if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), "PI")) {
                pi = person;
                break;
            }
        }
        if (pi != null) {
            int index = hierarchy.getProposalPersons().indexOf(pi);
            if (index > -1) hierarchy.getProposalPerson(index).setProposalPersonRoleId("PI");
        }
    }
    
}
