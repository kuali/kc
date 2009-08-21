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
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
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
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchyChild;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#createHierarchy(java.lang.String)
     */
    public String createHierarchy(String initialChildProposalNumber) throws ProposalHierarchyException {
        ProposalDevelopmentDocument newDoc;
        DevelopmentProposal initialChild;
        DevelopmentProposal hierarchy;
        try {
            newDoc = (ProposalDevelopmentDocument) documentService.getNewDocument(ProposalDevelopmentDocument.class);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error creating new document: " + x);
        }
        initialChild = getDevelopmentProposal(initialChildProposalNumber);
        hierarchy = newDoc.getDevelopmentProposal();
        copyInitialData(hierarchy, initialChild);
        hierarchy.setHierarchyStatus(HierarchyStatusConstants.Parent.code());
        String docDescription = getDevelopmentProposal(initialChildProposalNumber).getProposalDocument().getDocumentHeader().getDocumentDescription();
        newDoc.getDocumentHeader().setDocumentDescription(docDescription);
        UniversalUser user = new UniversalUser (GlobalVariables.getUserSession().getPerson());
        String username = user.getPersonUserIdentifier();
        proposalAuthorizationService.addRole(username, RoleConstants.AGGREGATOR, newDoc);
        try {
            documentService.saveDocument(newDoc);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }

        linkToHierarchy(hierarchy.getProposalNumber(), initialChildProposalNumber);

        return hierarchy.getProposalNumber();
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyProposal(java.lang.String)
     */
    public String getHierarchyParent(String childProposalNumber) throws ProposalHierarchyException {
        return getHierarchyChild(childProposalNumber).getHierarchyProposalNumber();
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#linkToHierarchy(java.lang.String,
     *      java.lang.String)
     */
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) throws ProposalHierarchyException {
        //DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);
        DevelopmentProposal newChildProposal = getDevelopmentProposal(newChildProposalNumber);
        if (newChildProposal.isInHierarchy()) {
            throw new ProposalHierarchyException("Proposal " + newChildProposalNumber + " is already a member of a hierarchy");
        }
        newChildProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        ProposalHierarchyChild childBO = new ProposalHierarchyChild();
        childBO.setProposalNumber(newChildProposalNumber);
        childBO.setHierarchyProposalNumber(hierarchyProposalNumber);
        businessObjectService.save(childBO);
        businessObjectService.save(newChildProposal);
        synchronizeChild(newChildProposalNumber);
    }

    private void copyInitialData(DevelopmentProposal hierarchyProposal, DevelopmentProposal srcProposal) throws ProposalHierarchyException {        
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#removeFromHierarchy(java.lang.String)
     */
    public void removeFromHierarchy(String childProposalNumber) throws ProposalHierarchyException {
        // TODO Auto-generated method stub

    }


    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeAllChildren(java.lang.String)
     */
    public void synchronizeAllChildren(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(hierarchyProposalNumber);
        boolean changed = false;

        for (ProposalHierarchyChild child : hierarchy.getChildren()) {
            changed |= synchronizeChild(child.getProposalNumber(), false);
        }
        if (changed) {
            aggregateHierarchy(hierarchyProposalNumber);
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#synchronizeChild(java.lang.String)
     */
    public void synchronizeChild(String childProposalNumber) throws ProposalHierarchyException {
        synchronizeChild(childProposalNumber, true);
    }

    @SuppressWarnings("unchecked")
    private boolean synchronizeChild(String childProposalNumber, boolean performAggregation) throws ProposalHierarchyException {
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposalNumber);
        String hierarchyProposalNumber = hierarchyChild.getHierarchyProposalNumber();

        DevelopmentProposal childProposal = getDevelopmentProposal(childProposalNumber);
        if (childProposal == null)
            throw new ProposalHierarchyException("Error finding source child proposal");

        if (childProposal.getUpdateTimestamp().equals(hierarchyChild.getProposalUpdateTimestamp())
                || childProposal.hierarchyChildHashCode() == hierarchyChild.getProposalHashCode()) {
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

        hierarchyChild.setProposalUpdateTimestamp(new Timestamp(childProposal.getUpdateTimestamp().getTime()));
        hierarchyChild.setProposalHashCode(childProposal.hierarchyChildHashCode());

        businessObjectService.save(hierarchyChild);

        if (performAggregation) {
            aggregateHierarchy(hierarchyProposalNumber);
        }
        return true;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchySyncService#aggregateHierarchy(java.lang.String)
     */
    public void aggregateHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(hierarchyProposalNumber);

        if (hierarchy == null)
            throw new ProposalHierarchyException("Hierarchy number " + hierarchyProposalNumber + " not found");

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
        businessObjectService.save(hierarchy);
    }

    private void aggregateProposalPersons(DevelopmentProposal hierarchy) {
        ProposalPerson oldPrincipalInvestigator = hierarchy.getPrincipalInvestigator();
        Set<ProposalPerson> investigators = new HashSet<ProposalPerson>();
        Set<ProposalPerson> proposalPersons = new HashSet<ProposalPerson>();

        hierarchy.setPrincipalInvestigator(null);
        hierarchy.getInvestigators().clear();
        hierarchy.getProposalPersons().clear();

        for (ProposalHierarchyChild child : hierarchy.getChildren()) {
            for (ProposalPerson keyPerson : child.getProposalPersons()) {
                proposalPersons.add(keyPerson);
                if (StringUtils.equalsIgnoreCase(keyPerson.getProposalPersonRoleId(), "COI")
                        || StringUtils.equalsIgnoreCase(keyPerson.getProposalPersonRoleId(), "PI")) {
                    investigators.add(keyPerson);
                }
                if (keyPerson.equals(oldPrincipalInvestigator)) {
                    investigators.add(keyPerson);
                    keyPerson.setProposalPersonRoleId("PI");
                    hierarchy.setPrincipalInvestigator(keyPerson);
                }
            }
        }

        if (proposalPersons.contains(oldPrincipalInvestigator)) {
            investigators.add(oldPrincipalInvestigator);
            oldPrincipalInvestigator.setProposalPersonRoleId("PI");
            hierarchy.setPrincipalInvestigator(oldPrincipalInvestigator);
        }

        hierarchy.getProposalPersons().addAll(proposalPersons);
        hierarchy.getInvestigators().addAll(investigators);
        for (ProposalPerson proposalPerson : hierarchy.getProposalPersons()) {
            proposalPerson.setHiddenInHierarchy(false);
        }

        for (ProposalPerson investigator : hierarchy.getInvestigators()) {
            if (hierarchy.getPrincipalInvestigator() == null
                    && StringUtils.equalsIgnoreCase(investigator.getProposalPersonRoleId(), "PI")) {
                hierarchy.setPrincipalInvestigator(investigator);
            }
            else
                investigator.setProposalPersonRoleId("COI");
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

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchy(java.lang.String)
     */
    public DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyChild(java.lang.String)
     */
    public ProposalHierarchyChild getHierarchyChild(String childProposalNumber) throws ProposalHierarchyException {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", childProposalNumber);
        ProposalHierarchyChild child = (ProposalHierarchyChild) (businessObjectService.findByPrimaryKey(
                ProposalHierarchyChild.class, pk));
        if (child == null)
            throw new ProposalHierarchyException("Proposal " + childProposalNumber + " not found or not a child in a hierarchy");
        return child;
    }

    private DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (businessObjectService.findByPrimaryKey(DevelopmentProposal.class, pk));
    }

    /**
     * This method takes an instance of a bean and a Class object representing a subclass of the instance and creates a subclass
     * instance copying all of the data from the original Object to the new class. It does this by calling all getter methods which
     * take no arguments ( i.e. <code>Type getXyz()</code> or <code>Type isXyz()</code> ) and calling the corresponding setter (
     * i.e. <code>setXyz(Type param)</code> ) with the result of the getter. If the getter has no corresponding setter in the
     * subclass, the method is skipped. The method handles getters returning Arrays, Maps, and Collections (Lists and Sets) by
     * creating new Arrays, Maps, and Collections and populating them and, in the case of ordered collections, maintains the
     * ordering if the underlying Collection's addAll method does. If a return value is not an Array, Map, or Collection, it is
     * directly used in the setter.
     * 
     * @param superClass the instance acting as the source object
     * @param subClazz the type of the subclass to be created
     * @return an instance of the subclass with all of the fields from the superclass populated
     * @throws InstantiationException if the subclass represents an interface or abstract class or other error occurs during
     *         instantiation
     * @throws IllegalAccessException if the subclass or its nullary constructor or methods are not accessible
     * @throws InvocationTargetException if a setter method throws an exception
     */
    @SuppressWarnings("unchecked")
    private static <T, U extends T> U convertToSubclass(T superClass, Class<U> subClazz) throws InstantiationException,
            IllegalAccessException, InvocationTargetException {
        U retval;
        retval = subClazz.newInstance();
        Method[] srcMethods = superClass.getClass().getMethods();
        Method destMethod;
        String srcMethodName, destMethodName;
        Object srcRetval, destParam;
        for (Method srcMethod : srcMethods) {
            srcMethodName = srcMethod.getName();
            destMethodName = srcMethodName.replace("^get|^is", "set");

            if (!srcMethodName.startsWith("set") && destMethodName.startsWith("set")) {
                srcRetval = srcMethod.invoke(superClass);
                if (srcRetval == null) {
                    destParam = null;
                }
                else if (srcRetval.getClass().isArray()) {
                    destParam = Array.newInstance(srcRetval.getClass().getComponentType(), Array.getLength(srcRetval));
                    for (int i = 0; i < Array.getLength(srcRetval); i++) {
                        Array.set(destParam, i, Array.get(srcRetval, i));
                    }
                }
                else if (srcRetval instanceof Collection) {
                    destParam = srcRetval.getClass().newInstance();
                    ((Collection) destParam).addAll((Collection) srcRetval);
                }
                else if (srcRetval instanceof Map) {
                    destParam = srcRetval.getClass().newInstance();
                    ((Map) destParam).putAll((Map) srcRetval);
                }
                else {
                    destParam = srcRetval;
                }

                try {
                    destMethod = subClazz.getMethod(destMethodName, srcMethod.getReturnType());
                    destMethod.invoke(retval, destParam);
                }
                catch (NoSuchMethodException e) {
                    // Ignore - getter has no corresponding setter
                }
            }
        }
        return retval;
    }
}
