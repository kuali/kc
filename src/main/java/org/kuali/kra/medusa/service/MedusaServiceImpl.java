/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.medusa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.medusa.MedusaNode;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Medusa Service provides the methods to get MedusaNodes that describe the tree-like structure that describes
 * the nodes and provides a method of retrieving related BOs for summary display.
 */
public class MedusaServiceImpl implements MedusaService {

    public static final String AWARD_MODULE = "award";
    public static final String INSTITUTIONAL_PROPOSAL_MODULE = "IP";
    public static final String DEVELOPMENT_PROPOSAL_MODULE = "DP";    
    public static final String NEGOTIATION_MODULE = "neg";
    private static final int INST_PROPOSAL_STATUS_FUNDED = 2;
    private static final String SUBAWARD_MODULE = "subaward";

    private BusinessObjectService businessObjectService;
    private AwardAmountInfoService awardAmountInfoService;
    private VersionHistoryService versionHistoryService;
    private NegotiationService negotiationService;
    
    /**
     * 
     * @see org.kuali.kra.medusa.service.MedusaService#getMedusaNode(java.lang.String, java.lang.Long)
     */
    public MedusaNode getMedusaNode(String moduleName, Long moduleId) {
        MedusaNode curNode = new MedusaNode();
        curNode.setType(moduleName);
        if (StringUtils.equalsIgnoreCase(AWARD_MODULE, moduleName)) {
            Award award = (Award) businessObjectService.findByPrimaryKey(Award.class, getFieldValues("awardId", moduleId));
            curNode.setBo(award);
            curNode.setExtraInfo(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos()));
        } else if (StringUtils.equalsIgnoreCase(INSTITUTIONAL_PROPOSAL_MODULE, moduleName)) {
            InstitutionalProposal proposal = 
                (InstitutionalProposal) businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", moduleId));
            proposal.setNsfCodeBo(getNsfCode(proposal.getNsfCode()));
            curNode.setBo(proposal);
        } else if (StringUtils.equalsIgnoreCase(DEVELOPMENT_PROPOSAL_MODULE , moduleName)) {
            DevelopmentProposal devProp = 
                (DevelopmentProposal) businessObjectService.findByPrimaryKey(DevelopmentProposal.class, getFieldValues("proposalNumber", moduleId));
            devProp.setNsfCodeBo(getNsfCode(devProp.getNsfCode()));
            curNode.setBo(devProp);
        } else if (StringUtils.equalsIgnoreCase(NEGOTIATION_MODULE, moduleName)) {
            Negotiation negotiation = getNegotiation(moduleId);
            curNode.setBo(negotiation);
        }
        else if (StringUtils.equalsIgnoreCase(SUBAWARD_MODULE, moduleName)) {
            SubAward subaward = getSubAward(moduleId);
            curNode.setBo(subaward);
        }
        return curNode;
    }
    
    /**
     * 
     * Helper function as nsfCode has been wired up such that it needs
     * outside help getting the actual BO loaded.
     * @param nsfCode
     * @return
     */
    @SuppressWarnings("unchecked")
    protected NsfCode getNsfCode(String nsfCode) {
        Collection<NsfCode> bos = businessObjectService.findMatching(NsfCode.class, getFieldValues("nsfCode", nsfCode));
        for (NsfCode nsfBo : bos) {
            return nsfBo;
        }
        return null;
    }
    
    /**
     * 
     * @see org.kuali.kra.medusa.service.MedusaService#getMedusaByProposal(java.lang.String, java.lang.Long)
     */
    public List<MedusaNode> getMedusaByProposal(String moduleName, Long moduleIdentifier) {
        String preferredModule = INSTITUTIONAL_PROPOSAL_MODULE;
        return getMedusaTree(moduleName, moduleIdentifier, preferredModule);
    }
    
    /**
     * 
     * @see org.kuali.kra.medusa.service.MedusaService#getMedusaByAward(java.lang.String, java.lang.Long)
     */
    public List<MedusaNode> getMedusaByAward(String moduleName, Long moduleIdentifier) {
        String preferredModule = AWARD_MODULE;
        return getMedusaTree(moduleName, moduleIdentifier, preferredModule);
    }
    
    /**
     * 
     * Builds the tree-like structure of MedusaNode objects using the module name and identifier
     * @param moduleName name of the module to be looked up (ie. award, IP, DP)
     * @param moduleIdentifier the primary key of the object to be looked up in the specified module
     * @param preferredModule defines the object type that should be placed at the top level when possible
     * @return
     */
    protected List<MedusaNode> getMedusaTree(String moduleName, Long moduleIdentifier, String preferredModule) {
        List<MedusaNode> nodes = new ArrayList<MedusaNode>();
        HashMap<BusinessObject, List<BusinessObject>> graph = new HashMap<BusinessObject, List<BusinessObject>>();
        if (StringUtils.equals(moduleName, AWARD_MODULE)) {
            Award award = getAward(moduleIdentifier);
            addVertex(graph, award);
            buildGraph(graph, award);
            nodes = getParentNodes(graph, new String[]{preferredModule, AWARD_MODULE});
        } else if (StringUtils.equals(moduleName, INSTITUTIONAL_PROPOSAL_MODULE)) {
            InstitutionalProposal proposal = getInstitutionalProposal(moduleIdentifier);
            addVertex(graph, proposal);
            buildGraph(graph, proposal);
            nodes = getParentNodes(graph, new String[]{preferredModule, INSTITUTIONAL_PROPOSAL_MODULE});
        } else if (StringUtils.equals(moduleName, DEVELOPMENT_PROPOSAL_MODULE)) {
            DevelopmentProposal proposal = getDevelopmentProposal(moduleIdentifier);
            addVertex(graph, proposal);
            buildGraph(graph, proposal);
            nodes = getParentNodes(graph, new String[]{preferredModule, DEVELOPMENT_PROPOSAL_MODULE});
        } else if (StringUtils.equals(moduleName, NEGOTIATION_MODULE)) {
            Negotiation negotiation = getNegotiation(moduleIdentifier);
            if (negotiation != null) {
                addVertex(graph, negotiation);
                buildGraph(graph, negotiation);
                nodes = getParentNodes(graph, new String[]{preferredModule, NEGOTIATION_MODULE});
            }
        }else if (StringUtils.equals(moduleName, SUBAWARD_MODULE)) {
            SubAward subAward = getSubAward(moduleIdentifier);
            
            if (subAward != null) {
                addVertex(graph, subAward);
                buildGraph(graph, subAward);
                nodes = getParentNodes(graph, new String[]{preferredModule, SUBAWARD_MODULE});
            }
        }
        return nodes;
    }
    
    /**
     * 
     * Adds a new bo to the graph like hash after checking to make sure the same bo is not already added.
     * This is done outside of the typical hash uniqueness due to the fact the BOs do not all defined equals and hash
     * code so we do the checks here
     * @param graph
     * @param bo
     * @return the bo that was already in the graph or was added to the graph
     */
    protected BusinessObject addVertex(HashMap<BusinessObject, List<BusinessObject>> graph, BusinessObject bo) {
        BusinessObject graphBo = findMatchingBo(graph.keySet(), bo);
        if (graphBo == null) {
            graph.put(bo, new ArrayList<BusinessObject>());
            return bo;
        } else {
            return graphBo;
        }
        
    }
    
    /**
     * 
     * First adds both bos into the graph as vertexes(using addVertex) and then if the links do not already
     * exist, adds a bi-directional link to the graph.
     * @param graph
     * @param bo1
     * @param bo2
     */
    protected void addEdge(HashMap<BusinessObject, List<BusinessObject>> graph, BusinessObject bo1, BusinessObject bo2) {
        BusinessObject graphBo1 = addVertex(graph, bo1);
        BusinessObject graphBo2 = addVertex(graph, bo2);
        if (findMatchingBo(graph.get(graphBo1), graphBo2) == null) {
            graph.get(graphBo1).add(graphBo2);
        }
        if (findMatchingBo(graph.get(graphBo2), graphBo1) == null) {
            graph.get(graphBo2).add(graphBo1);
        }
    }
    
    /**
     * 
     * Searches through the graph using the preferred order for Bos to occupy the top level(or parent nodes)
     * and then populates all child nodes of each top level node.
     * @param graph
     * @param preferedOrder an array of module names (ie. award, DP, IP)
     * @return
     */
    protected List<MedusaNode> getParentNodes(HashMap<BusinessObject, List<BusinessObject>> graph, String[] preferedOrder) {
        List<MedusaNode> parentNodes = new ArrayList<MedusaNode>();
        
        for (String prefType : preferedOrder) {
            for (BusinessObject bo : graph.keySet()) {
                MedusaNode node = getNode(bo);
                if (StringUtils.equals(node.getType(), prefType)) {
                    parentNodes.add(node);
                }
            }
            if (!parentNodes.isEmpty()) { break; }
        }
        
        //while adding in child nodes make sure the top-level nodes are not duplicated in the tree structure
        for (MedusaNode node : parentNodes) {
            List<MedusaNode> seenNodes = new ArrayList<MedusaNode>(parentNodes);
            populateChildNodes(graph, node, seenNodes);
        }

        return parentNodes;
    }
    
    /**
     * 
     * Using the links defined in the graph hash, add all nodes linked to the current node
     * @param graph
     * @param node
     * @param parentNodes
     */
    protected void populateChildNodes(HashMap<BusinessObject, List<BusinessObject>> graph, MedusaNode node, List<MedusaNode> parentNodes) {
        Collection<BusinessObject> links = graph.get(node.getBo());
        for (BusinessObject bo : links) {
            MedusaNode nextNode = getNode(bo);
            if (parentNodes == null || !isBoInList(parentNodes, bo)) {
                node.getChildNodes().add(nextNode);
                parentNodes.add(node);
                populateChildNodes(graph, nextNode, parentNodes);
            }
        }
    }
    
    /**
     * 
     * Looks through the list of MedusaNodes to see if the BO is equal to the BO of one of the nodes.
     * @param nodes
     * @param bo
     * @return
     */
    protected boolean isBoInList(List<MedusaNode> nodes, BusinessObject bo) {
        for (MedusaNode node : nodes) {
            if (areBusinessObjectsEqual(node.getBo(), bo)) {
                return true;
            }
        }
        return false;
    }
    
    protected void buildGraph(HashMap<BusinessObject, List<BusinessObject>> graph, SubAward subAward) {
        
        Collection<Award> awards = getAwards(subAward);
        for (Award award : awards) {
            if (findMatchingBo(graph.keySet(), award) == null) {
                addEdge(graph, subAward, award);
                buildGraph(graph, award);
            } else {
                addEdge(graph, subAward, award);                
            }
        }
    }
    
    
    /**
     * 
     * Builds the graph recursively by finding links from the Award.
     * @param graph
     * @param award
     */
    protected void buildGraph(HashMap<BusinessObject, List<BusinessObject>> graph, Award award) {
        Collection<InstitutionalProposal> proposals = getProposals(award);
        for (InstitutionalProposal proposal : proposals) {
            if (findMatchingBo(graph.keySet(), proposal) == null) {
                addEdge(graph, award, proposal);
                buildGraph(graph, proposal);
            } else {
                addEdge(graph, award, proposal);                
            }
        }
        Collection<Negotiation> negotiations = getNegotiations(award);
        for (Negotiation negotiation : negotiations) {
            if (findMatchingBo(graph.keySet(), negotiation) == null) {
                addEdge(graph, award, negotiation);
                buildGraph(graph, negotiation);
            } else {
                addEdge(graph, award, negotiation);                
            }
        }        
    }
    
    /**
     * 
     * Builds the graph using links found from the institutional proposal.
     * @param graph
     * @param proposal
     */
    protected void buildGraph(HashMap<BusinessObject, List<BusinessObject>> graph, InstitutionalProposal proposal) {
        Collection<Award> awards = getAwards(proposal);
        for (Award award : awards) {
            if (findMatchingBo(graph.keySet(), award) == null) {
                addEdge(graph, proposal, award);
                buildGraph(graph, award);
            } else {
                addEdge(graph, proposal, award);                
            }
        }
        Collection<DevelopmentProposal> proposals = getDevelopmentProposals(proposal);
        for (DevelopmentProposal devProp : proposals) {
            if (findMatchingBo(graph.keySet(), devProp) == null) {
                addEdge(graph, proposal, devProp);
                buildGraph(graph, devProp);
            } else {
                addEdge(graph, proposal, devProp);                
            }
        }
        Collection<Negotiation> negotiations = getNegotiations(proposal);
        for (Negotiation negotiation : negotiations) {
            if (findMatchingBo(graph.keySet(), negotiation) == null) {
                addEdge(graph, proposal, negotiation);
                buildGraph(graph, negotiation);
            } else {
                addEdge(graph, proposal, negotiation);                
            }
        }
        
    }
    
    /**
     * 
     * Continues to build the graph finding links from the development proposal
     * @param graph
     * @param devProp
     */
    protected void buildGraph(HashMap<BusinessObject, List<BusinessObject>> graph, DevelopmentProposal devProp) {
        Collection<InstitutionalProposal> proposals = getProposals(devProp);
        for (InstitutionalProposal proposal : proposals) {
            if (findMatchingBo(graph.keySet(), proposal) == null) {
                addEdge(graph, devProp, proposal);
                buildGraph(graph, proposal);
            } else {
                addEdge(graph, devProp, proposal);
            }
        }       
    }
    
    protected void buildGraph(HashMap<BusinessObject, List<BusinessObject>> graph, Negotiation negotiation) {
        BusinessObject bo = (BusinessObject)getNegotiationService().getAssociatedObject(negotiation);
        if (bo instanceof Award || bo instanceof InstitutionalProposal) {
            if (findMatchingBo(graph.keySet(), bo) == null) {
                addEdge(graph, negotiation, bo);
                if (bo instanceof Award) {
                    Award award = (Award) bo;
                    buildGraph(graph, award);
                } else if (bo instanceof InstitutionalProposal) {
                    InstitutionalProposal proposal = (InstitutionalProposal) bo;
                    buildGraph(graph, proposal);
                }
            } else {
                addEdge(graph, negotiation, bo);
            }
        }
    }
    
    /**
     * 
     * Looks through the boSet for a matching medusa BO.
     * @param boSet
     * @param bo
     * @return
     */
    protected BusinessObject findMatchingBo(Collection<BusinessObject> boSet, BusinessObject bo) {
        for (BusinessObject curBo : boSet) {
            if (areBusinessObjectsEqual(bo, curBo)) {
                return curBo;
            }
        }
        return null;
    } 
    
    /**
     * 
     * Checks the buisness objects for equality assuming they are medusa supported BOs
     * (Development Proposal, Institutional Proposal or Award), this is because the current
     * BOs do not support equality checking.
     * @param bo1
     * @param bo2
     * @return
     */
    protected boolean areBusinessObjectsEqual(BusinessObject bo1, BusinessObject bo2) {
        if (bo1 instanceof DevelopmentProposal
                && bo2 instanceof DevelopmentProposal) {
            if (ObjectUtils.equals(((DevelopmentProposal)bo1).getProposalNumber(),
                    ((DevelopmentProposal)bo2).getProposalNumber())) {
                return true;
            }
        } else if (bo1 instanceof InstitutionalProposal
                && bo2 instanceof InstitutionalProposal) {
            if (ObjectUtils.equals(((InstitutionalProposal)bo1).getProposalId(),
                    ((InstitutionalProposal)bo2).getProposalId())) {
                return true;
            }
        } else if (bo1 instanceof Award && bo2 instanceof Award) {
            if (ObjectUtils.equals(((Award)bo1).getAwardId(),
                    ((Award)bo2).getAwardId())) {
                return true;
            }
        } else if (bo1 instanceof Negotiation && bo2 instanceof Negotiation) {
            if (ObjectUtils.equals(((Negotiation) bo1).getNegotiationId(),
                    ((Negotiation) bo2).getNegotiationId())) {
                return true;
            }
        }        
        else if (bo1 instanceof SubAward && bo2 instanceof SubAward) {
            if (ObjectUtils.equals(((SubAward) bo1).getSubAwardId(),
                    ((SubAward) bo2).getSubAwardId())) {
                return true;
            }
        } 
        return false;
    }
    
    protected DevelopmentProposal getDevelopmentProposal(Long proposalNumber) {
        return (DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, getFieldValues("proposalNumber", proposalNumber));
    }
    
    /**
     * 
     * Returns the newest active institutional proposal if its available otherwise the newest pending and then archived proposal is returned.
     * @param proposalId
     * @return
     */
    protected InstitutionalProposal getInstitutionalProposal(Long proposalId) {
        InstitutionalProposal proposal = (InstitutionalProposal) businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", proposalId));
        if (proposal == null) {
            return null;
        }
        InstitutionalProposal currentProposal = null;
        for (VersionStatus status : new VersionStatus[]{VersionStatus.ACTIVE, VersionStatus.PENDING, VersionStatus.ARCHIVED}) {
            currentProposal = getNewestProposalByStatus(proposal.getProposalNumber(), status);
            if (currentProposal != null) {
                break;
            }
        }
        return currentProposal == null ? proposal : currentProposal;
    }
    
    /**
     * 
     * Returns the newest active if available or the newest no cancelled award if it is not
     * @param awardId
     * @return
     */
    protected Award getAward(Long awardId) {
        Award award = (Award)businessObjectService.findByPrimaryKey(Award.class, getFieldValues("awardId", awardId));
        if (award == null) {
            return null;
        }
        Award currentAward = (Award) getActiveOrCurrentVersion(Award.class, award.getAwardNumber());
        return currentAward == null ? award : currentAward;
    }
    
    protected Negotiation getNegotiation(Long negotiationId) {
        Negotiation negotiation = (Negotiation) businessObjectService.findBySinglePrimaryKey(Negotiation.class, negotiationId);
        return negotiation;
    }
    
    protected SubAward getSubAward(Long subAwardId) {
        SubAward subAward = (SubAward) businessObjectService.findBySinglePrimaryKey(SubAward.class, subAwardId);
        return subAward;
    }
    /**
     * 
     * Gets the active or if not available the most current, not cancelled version of a 
     * versioned BO. Currently only Awards use the Version History framework though
     * @param clazz
     * @param sequenceName
     * @return
     */
    @SuppressWarnings("unchecked")
    protected SequenceOwner getActiveOrCurrentVersion(Class clazz, String sequenceName) {
        VersionHistory activeVersion = versionHistoryService.findActiveVersion(clazz, sequenceName);
        SequenceOwner bestVersion = null;
        if (activeVersion != null) {
            bestVersion = (SequenceOwner)activeVersion.getSequenceOwner();
        } else {
            List<VersionHistory> history = versionHistoryService.loadVersionHistory(clazz, sequenceName);
            if (history != null && !history.isEmpty()) {
                VersionHistory best = history.get(0);
                for (VersionHistory curVersion : history) {
                    if (curVersion.getVersionNumber() > best.getVersionNumber()) {
                        if (curVersion.getStatus() != VersionStatus.CANCELED) {
                            best = curVersion;
                        }
                    }
                }
                bestVersion = (SequenceOwner)best.getSequenceOwner();
            } 
        }
        return bestVersion;  
    }
    
    /**
     * 
     * Creates a MedusaNode for the BO given the BO is supported by Medusa(Award, Dev Prop or Inst Prop).
     * @param bo
     * @return
     */
    protected MedusaNode getNode(BusinessObject bo) {
        if (bo instanceof Award) {
            return getNode((Award)bo);
        } else if (bo instanceof InstitutionalProposal) {
            return getNode((InstitutionalProposal)bo);
        } else if (bo instanceof DevelopmentProposal) {
            return getNode((DevelopmentProposal)bo);
        } else if (bo instanceof Negotiation) {
            return getNode((Negotiation)bo);
        } else if (bo instanceof SubAward) {
            return getNode((SubAward)bo);
        } else {
            return null;
        }
    }
    
    protected MedusaNode getNode(Award award) {
        AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        MedusaNode node = new MedusaNode();
        node.setBo(award);
        node.setType(AWARD_MODULE);
        node.setExtraInfo(awardAmountInfo);
        return node;
    }
    
    protected MedusaNode getNode(InstitutionalProposal proposal) {
        MedusaNode node = new MedusaNode();
        node.setBo(proposal);
        node.setType(INSTITUTIONAL_PROPOSAL_MODULE);
        return node;
    }
    
    protected MedusaNode getNode(DevelopmentProposal proposal) {
        MedusaNode node = new MedusaNode();
        node.setBo(proposal);
        node.setType(DEVELOPMENT_PROPOSAL_MODULE);
        return node;
    }
    
    protected MedusaNode getNode(Negotiation negotiation) {
        MedusaNode node = new MedusaNode();
        node.setBo(negotiation);
        node.setType(NEGOTIATION_MODULE);
        return node;
    }
    protected MedusaNode getNode(SubAward subAward) {
        MedusaNode node = new MedusaNode();
        node.setBo(subAward);
        node.setType(SUBAWARD_MODULE);
        return node;
    }
    /**
     * 
     * Returns a list of the Development Proposals linked to the institutional proposal.
     * This will involve a search through all institutional proposal versions for a matching ProposalAdminDetails object
     * as the admin details are not versioned with the institutional proposal.
     * @param instProposal
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal instProposal) {
        //find any dev prop linked to any version of this inst prop
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        Collection<InstitutionalProposal> proposalVersions = 
            businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", instProposal.getProposalNumber()));
        for (InstitutionalProposal ip : proposalVersions) {
            Collection<ProposalAdminDetails> proposalAdminDetails = 
                businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", ip.getProposalId()));
            for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                proposalAdminDetail.refreshReferenceObject("developmentProposal");
                devProposals.add(proposalAdminDetail.getDevelopmentProposal());
            }
        }
        return devProposals;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<Award> getAwards(SubAward subAward) {
        
        Collection<Award> awards = new ArrayList<Award>();
        Collection<SubAward> subAwardVersions = businessObjectService.findMatching(SubAward.class, getFieldValues("subAwardCode", subAward.getSubAwardCode()));
        SubAward newestSubAaward = null;
        for (SubAward currenSubAward : subAwardVersions) {
            
            if(newestSubAaward==null){
                newestSubAaward = currenSubAward;
            }
            else if (currenSubAward.getSequenceNumber() > newestSubAaward.getSequenceNumber()) {
                newestSubAaward = currenSubAward;
            }
        }
        Collection<SubAwardFundingSource> subAwardFundingSources =newestSubAaward.getSubAwardFundingSourceList();
        for (SubAwardFundingSource subAwardFundingSource :subAwardFundingSources){
            awards.add(getAward(subAwardFundingSource.getAwardId()));
        }
        return awards;
    }
    /**
     * 
     * Generates and returns a collection of all awards linked to the 
     * institutional proposal
     * @param ip
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<Award> getAwards(InstitutionalProposal ip) {
        Collection<Award> awards = new ArrayList<Award>();
        Collection<InstitutionalProposal> institutionalProposalVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", ip.getProposalNumber()));
        for (InstitutionalProposal curIp : institutionalProposalVersions) {
            Collection<AwardFundingProposal> awardFundingProposals = curIp.getAwardFundingProposals();
            for (AwardFundingProposal awardFunding : awardFundingProposals) {
                if (awardFunding.isActive()) {
                    awards.add(getAward(awardFunding.getAwardId()));
                }
            }
        }
        InstitutionalProposal activeProposal = getNewestProposalByStatus(ip.getProposalNumber(), VersionStatus.ACTIVE);
        if (activeProposal != null && StringUtils.isNotBlank(activeProposal.getCurrentAwardNumber()) 
                && activeProposal.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED) {
            Collection<Award> proposalCurrentAwards = 
                businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", activeProposal.getCurrentAwardNumber()));
            for (Award curAward : proposalCurrentAwards) {
                awards.add(getAward(curAward.getAwardId()));
            }
        }
        return awards;
    }
    
    /**
     * 
     * Gets and returns the newest active proposal version for the proposal number.
     * @param proposalNumber
     * @return
     */
    @SuppressWarnings("unchecked")
    protected InstitutionalProposal getNewestProposalByStatus(String proposalNumber, VersionStatus status) {
        Collection<InstitutionalProposal> versions = getBusinessObjectService().findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", proposalNumber));
        InstitutionalProposal newestProposal = null;
        for (InstitutionalProposal curProposal : versions) {
            if (newestProposal == null && curProposal.isActiveVersion()) {
                newestProposal = curProposal;
            } else if (curProposal.isActiveVersion() && curProposal.getSequenceNumber() > newestProposal.getSequenceNumber()) {
                newestProposal = curProposal;
            }
        }
        return newestProposal;
    }
    
    /**
     * 
     * Returns all institutional proposals linked to this award. This will involve a search through 
     * all award versions as award funding proposals are not versioned with the award. It will also
     * look through all institutional proposals to see if the institutional proposal lists the award id
     * and it not funded and the currently active version.
     * @param award
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<InstitutionalProposal> getProposals(Award award) {
        Collection<InstitutionalProposal> ips = new ArrayList<InstitutionalProposal>();
        Collection<Award> awardVersions = businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", award.getAwardNumber()));
        for (Award curAward : awardVersions) {
            Collection<AwardFundingProposal> awardFundingProposals = curAward.getFundingProposals();
            for (AwardFundingProposal awardFunding : awardFundingProposals) {
                if (awardFunding.isActive()) {
                    ips.add(getInstitutionalProposal(awardFunding.getProposalId()));
                }
            }
        }
        
        Collection <InstitutionalProposal> curAwardIps = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("currentAwardNumber", award.getAwardNumber()));
        for (InstitutionalProposal proposal : curAwardIps) {
            if (proposal.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED && proposal.isActiveVersion()) {
                ips.add(getInstitutionalProposal(proposal.getProposalId()));
            }
        }
        return ips;
    }
    
    /**
     * 
     * Gets all institutional proposals linked to this development proposal via the ProposalAdminDetails. 
     * @param devProposal
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<InstitutionalProposal> getProposals(DevelopmentProposal devProposal) {
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("devProposalNumber", devProposal.getProposalNumber()));
        Collection<InstitutionalProposal> instProposals = new ArrayList<InstitutionalProposal>();
        for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
            //find the newest version of the institutional proposal that is linked
            instProposals.add(getInstitutionalProposal(proposalAdminDetail.getInstProposalId()));
        }
        return instProposals;        
    }
    
    protected Collection<Negotiation> getNegotiations(BusinessObject bo) {
        return getNegotiationService().getAssociatedNegotiations(bo);
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected Map<String, Object> getFieldValues(String key, Object value) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(key, value);
        return fieldValues;
    }
    
    /**
     * Gets the awardAmountInfoService attribute. 
     * @return Returns the awardAmountInfoService.
     */
    public AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    public NegotiationService getNegotiationService() {
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    } 
    
}
