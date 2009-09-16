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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchyChild;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;
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
    private ProposalAuthorizationService proposalAuthorizationService;
    private ProposalHierarchyDao proposalHierarchyDao;

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

    public void setProposalAuthorizationService(ProposalAuthorizationService proposalAuthorizationService) {
        this.proposalAuthorizationService = proposalAuthorizationService;
    }

    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
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
        proposalAuthorizationService.addRole(username, RoleConstants.AGGREGATOR, newDoc);

        // link the child to the parent
        linkChild(hierarchy, initialChild);

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
            changed |= synchronizeChild(child, getDevelopmentProposal(child.getProposalNumber()));
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
        
        boolean changed = synchronizeChild(hierarchyChild, childProposal);
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
        synchronizeChild(childBO, newChildProposal);
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


    @SuppressWarnings("unchecked")
    private boolean synchronizeChild(ProposalHierarchyChild hierarchyChild, DevelopmentProposal childProposal)
            throws ProposalHierarchyException {
        String childProposalNumber = childProposal.getProposalNumber();
        String hierarchyProposalNumber = hierarchyChild.getHierarchyProposalNumber();

        if (childProposal.hierarchyChildHashCode() == hierarchyChild.getProposalHashCode()) {
            return false;
        }

        hierarchyChild.setPropScienceKeywords((List<PropScienceKeyword>) cloneAndUpdate(childProposal.getPropScienceKeywords(),
                hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setProposalPersons((List<ProposalPerson>) cloneAndUpdate(childProposal.getProposalPersons(),
                hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setPropSpecialReviews((List<ProposalSpecialReview>) cloneAndUpdate(childProposal.getPropSpecialReviews(),
                hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setNarratives((List<Narrative>) cloneAndUpdate(childProposal.getNarratives(), hierarchyProposalNumber,
                childProposalNumber));
        removeNonExclusiveNarratives(hierarchyChild.getNarratives());

        hierarchyChild.setProposalHashCode(childProposal.hierarchyChildHashCode());

        return true;
    }

    private void aggregateHierarchy(DevelopmentProposal hierarchy) throws ProposalHierarchyException {
        hierarchy.getPropScienceKeywords().clear();
        removeNonExclusiveNarratives(hierarchy.getNarratives());
        hierarchy.getPropSpecialReviews().clear();

        for (Narrative narrative : hierarchy.getHierarchyNarratives()) {
            hierarchy.addNarrative(narrative);
            narrative.setHiddenInHierarchy(false);
        }
        for (PropScienceKeyword keyword : hierarchy.getHierarchyPropScienceKeywords()) {
            hierarchy.addPropScienceKeyword(keyword);
            keyword.setHiddenInHierarchy(false);
        }
        for (ProposalSpecialReview review : hierarchy.getHierarchySpecialReviews()) {
            hierarchy.getPropSpecialReviews().add(review);
            review.setHiddenInHierarchy(false);
        }

        for (ProposalHierarchyChild child : hierarchy.getChildren()) {
            for (Narrative narrative : child.getNarratives()) {
                hierarchy.addNarrative(narrative);
                narrative.setHiddenInHierarchy(false);
            }
            for (PropScienceKeyword keyword : child.getPropScienceKeywords()) {
                if (!hierarchy.getPropScienceKeywords().contains(keyword)) {
                    hierarchy.addPropScienceKeyword(keyword);
                    keyword.setHiddenInHierarchy(false);
                }
            }
            for (ProposalSpecialReview review : child.getPropSpecialReviews()) {
                hierarchy.getPropSpecialReviews().add(review);
                review.setHiddenInHierarchy(false);
            }
        }

        aggregateProposalPersons(hierarchy);

        for (int i = hierarchy.getPropPersonBios().size() - 1; i >= 0; i--) {
            if (!hierarchy.getProposalPersons().contains(hierarchy.getPropPersonBio(i).getPersonId())) {
                hierarchy.getPropPersonBios().remove(i);
            }
        }
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

    @SuppressWarnings("unchecked")
    private Object cloneAndUpdate(Object o, String proposalNumber, String childProposalNumber) {
        Object newO = ObjectUtils.deepCopy((Serializable) o);
        if (newO instanceof List<?>) {
            List<Object> oList = (List<Object>) newO;
            for (Object obj : oList) {
                updateProposalNumber(obj, proposalNumber, childProposalNumber);
            }
        }
        else {
            updateProposalNumber(newO, proposalNumber, childProposalNumber);
        }
        return newO;
    }

    private void updateProposalNumber(Object o, String proposalNumber, String childProposalNumber) {
        if (o instanceof PersistableBusinessObjectBase) {
            ((PersistableBusinessObjectBase) o).setVersionNumber(null);
        }
        if (o instanceof HierarchyMaintainable) {
            ((HierarchyMaintainable) o).setHierarchyProposalNumber(childProposalNumber);
            ((HierarchyMaintainable) o).setHiddenInHierarchy(true);
        }
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().equalsIgnoreCase("setProposalNumber")) {
                    method.invoke(o, proposalNumber);
                }
            }
            catch (Throwable e) {
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
}
