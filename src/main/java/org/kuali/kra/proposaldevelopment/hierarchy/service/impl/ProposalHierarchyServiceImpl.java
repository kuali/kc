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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchy;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchyChild;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class...
 */
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {

    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#createHierarchy(java.lang.String)
     */
    public String createHierarchy(String initialChildProposalNumber) throws ProposalHierarchyException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyProposal(java.lang.String)
     */
    public String getHierarchyParent(String childProposalNumber) throws ProposalHierarchyException {
        return getHierarchyChild(childProposalNumber).getHierarchyProposalNumber();
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#isChild(java.lang.String)
     */
    public boolean isChild(String proposalNumber) throws ProposalHierarchyException {
        try {
            getHierarchyChild(proposalNumber);
            return true;
        }
        catch (ProposalHierarchyException x){
            return false;
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#isParent(java.lang.String)
     */
    public boolean isParent(String proposalNumber) throws ProposalHierarchyException {
        try {
            getHierarchy(proposalNumber);
            return true;
        }
        catch (ProposalHierarchyException x) {
            return false;
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#linkToHierarchy(java.lang.String, java.lang.String)
     */
    public void linkToHierarchy(String hierarchyProposalNumber, String newChildProposalNumber) throws ProposalHierarchyException,
            ProposalHierarchyException {
        // TODO Auto-generated method stub

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
        ProposalHierarchy hierarchy = getHierarchy(hierarchyProposalNumber);
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
        boolean changed = synchronizeChild(childProposalNumber, true);

    }
    
    @SuppressWarnings("unchecked")
    private boolean synchronizeChild(String childProposalNumber, boolean performAggregation) throws ProposalHierarchyException {
        ProposalHierarchyChild hierarchyChild = getHierarchyChild(childProposalNumber);
        String hierarchyProposalNumber = hierarchyChild.getHierarchyProposalNumber();

        DevelopmentProposal childProposal = getDevelopmentProposal(childProposalNumber);
        if (childProposal == null) throw new ProposalHierarchyException("Error finding source child proposal");
        
        if (childProposal.getUpdateTimestamp().equals(hierarchyChild.getProposalUpdateTimestamp())
                || childProposal.hierarchyChildHashCode() == hierarchyChild.getProposalChildHashCode()) {
            return false;
        }
        
        hierarchyChild.setPropScienceKeywords((List<PropScienceKeyword>) cloneAndUpdate(childProposal.getPropScienceKeywords(), hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setProposalPersons((List<ProposalPerson>) cloneAndUpdate(childProposal.getProposalPersons(), hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setPropSpecialReviews((List<ProposalSpecialReview>) cloneAndUpdate(childProposal.getPropSpecialReviews(), hierarchyProposalNumber, childProposalNumber));
        hierarchyChild.setNarratives((List<Narrative>) cloneAndUpdate(childProposal.getNarratives(), hierarchyProposalNumber, childProposalNumber));
        removeNonExclusiveNarratives(hierarchyChild.getNarratives());
        
        hierarchyChild.setProposalUpdateTimestamp(new Timestamp(childProposal.getUpdateTimestamp().getTime()));
        hierarchyChild.setProposalChildHashCode(childProposal.hierarchyChildHashCode());
        
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
        ProposalHierarchy hierarchy = getHierarchy(hierarchyProposalNumber);
        
        if (hierarchy == null) throw new ProposalHierarchyException("Hierarchy number " + hierarchyProposalNumber + " not found");

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
        
        for (int i=hierarchy.getPropPersonBios().size()-1; i>=0; i--) {
            if (!hierarchy.getProposalPersons().contains(hierarchy.getPropPersonBio(i).getPersonId())) {
                hierarchy.getPropPersonBios().remove(i);
            }
        }
        businessObjectService.save(hierarchy);
    }
    
    private void aggregateProposalPersons(ProposalHierarchy hierarchy) {
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
            if (hierarchy.getPrincipalInvestigator()==null 
                    && StringUtils.equalsIgnoreCase(investigator.getProposalPersonRoleId(), "PI")) {
                hierarchy.setPrincipalInvestigator(investigator);
            }
            else investigator.setProposalPersonRoleId("COI");
        }

    }
    
    private void removeNonExclusiveNarratives(List<Narrative> narratives) {
        Narrative narrative;
        for (int i = narratives.size()-1; i >= 0; i--) {
            narrative = narratives.get(i);
            if (StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getAllowMultiple(), "Y")) {
                narratives.remove(i);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private Object cloneAndUpdate(Object o, String proposalNumber, String childProposalNumber) {
        Object newO = ObjectUtils.deepCopy((Serializable)o);
        if (newO instanceof List<?>) {
            List<Object> oList = (List<Object>)newO;
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
            ((PersistableBusinessObjectBase)o).setVersionNumber(null);
        }
        if (o instanceof HierarchyMaintainable) {
            ((HierarchyMaintainable)o).setHierarchyProposalNumber(childProposalNumber);
            ((HierarchyMaintainable)o).setHiddenInHierarchy(true);
        }
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().equalsIgnoreCase("setProposalNumber")) {
                    method.invoke(o, proposalNumber);
                }
            } catch (Throwable e) {}
        }
        
    }
    
    /**
     * Set the Business Object Service.  It is set via dependency injection.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchy(java.lang.String)
     */
    public ProposalHierarchy getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", hierarchyProposalNumber);
        ProposalHierarchy hierarchy = (ProposalHierarchy)(businessObjectService.findByPrimaryKey(ProposalHierarchy.class, pk));
        if (hierarchy == null) throw new ProposalHierarchyException("Hierarchy " + hierarchyProposalNumber + " not found");
        return hierarchy;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService#getHierarchyChild(java.lang.String)
     */
    public ProposalHierarchyChild getHierarchyChild(String childProposalNumber) throws ProposalHierarchyException {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", childProposalNumber);
        ProposalHierarchyChild child = (ProposalHierarchyChild)(businessObjectService.findByPrimaryKey(ProposalHierarchyChild.class, pk));
        if (child == null) throw new ProposalHierarchyException("Proposal " + childProposalNumber + " not found or not a child in a hierarchy");
        return child;
    }
    
    private DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal)(businessObjectService.findByPrimaryKey(DevelopmentProposal.class, pk));
    }


}
