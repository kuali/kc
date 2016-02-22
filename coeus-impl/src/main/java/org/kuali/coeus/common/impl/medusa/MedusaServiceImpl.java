/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.impl.medusa;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.medusa.MedusaService;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.NsfCode;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.irb.Protocol;
import org.kuali.coeus.common.framework.medusa.MedusaNode;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Medusa Service provides the methods to get MedusaNodes that describe the tree-like structure that describes
 * the nodes and provides a method of retrieving related BOs for summary display.
 */
@Component("medusaService")
@Lazy
public class MedusaServiceImpl implements MedusaService {

    private static final int INST_PROPOSAL_STATUS_FUNDED = 2;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    @Autowired
    @Qualifier("awardAmountInfoService")
    private AwardAmountInfoService awardAmountInfoService;
    @Autowired
    @Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;
    @Autowired
    @Qualifier("negotiationService")
    private NegotiationService negotiationService;
    @Autowired
    @Qualifier("subAwardService")
    private SubAwardService subAwardService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    
    @Override
    public MedusaNode getMedusaNode(String moduleName, Long moduleId) {
        MedusaNode curNode = new MedusaNode();
        curNode.setType(moduleName);
        if (StringUtils.equalsIgnoreCase(Constants.AWARD_MODULE, moduleName)) {
            Award award = (Award) businessObjectService.findByPrimaryKey(Award.class, getFieldValues("awardId", moduleId));
            curNode.setBo(award);
            curNode.setExtraInfo(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos()));
        } else if (StringUtils.equalsIgnoreCase(Constants.INSTITUTIONAL_PROPOSAL_MODULE, moduleName)) {
            InstitutionalProposal proposal = 
                (InstitutionalProposal) businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", moduleId));
            proposal.setNsfCodeBo(getNsfCode(proposal.getNsfCode()));
            curNode.setBo(proposal);
        } else if (StringUtils.equalsIgnoreCase(Constants.DEVELOPMENT_PROPOSAL_MODULE , moduleName)) {
            DevelopmentProposal devProp = getDevelopmentProposal(moduleId.toString());
            devProp.setNsfCodeBo(getNsfCode(devProp.getNsfCode()));
            curNode.setBo(devProp);
        } else if (StringUtils.equalsIgnoreCase(Constants.NEGOTIATION_MODULE, moduleName)) {
            Negotiation negotiation = getNegotiation(moduleId);
            curNode.setBo(negotiation);
        } else if (StringUtils.equalsIgnoreCase(Constants.SUBAWARD_MODULE, moduleName)) {
            SubAward subaward = getSubAward(moduleId);
            curNode.setBo(subaward);
        } else if (StringUtils.equalsIgnoreCase(Constants.IRB_MODULE, moduleName)) {
            Protocol protocol = getProtocol(moduleId);
            curNode.setBo(protocol);
        } else if (StringUtils.equalsIgnoreCase(Constants.IACUC_MODULE, moduleName)) {
            IacucProtocol protocol = getIacuc(moduleId);
            curNode.setBo(protocol);
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
    
    @Override
    public List<MedusaNode> getMedusaByProposal(String moduleName, Long moduleIdentifier, boolean includeComplianceModules) {
        String preferredModule = Constants.INSTITUTIONAL_PROPOSAL_MODULE;
        return getMedusaTree(moduleName, moduleIdentifier, preferredModule, includeComplianceModules);
    }
    
    @Override
    public List<MedusaNode> getMedusaByAward(String moduleName, Long moduleIdentifier, boolean includeComplianceModules) {
        String preferredModule = Constants.AWARD_MODULE;
        return getMedusaTree(moduleName, moduleIdentifier, preferredModule, includeComplianceModules);
    }
    
    /**
     * 
     * Builds the tree-like structure of MedusaNode objects using the module name and identifier
     * @param moduleName name of the module to be looked up (ie. award, IP, DP)
     * @param moduleIdentifier the primary key of the object to be looked up in the specified module
     * @param preferredModule defines the object type that should be placed at the top level when possible
     * @return
     */
    protected List<MedusaNode> getMedusaTree(String moduleName, Long moduleIdentifier, String preferredModule, boolean includeComplianceModules) {
        List<MedusaNode> nodes = new ArrayList<MedusaNode>();
        HashMap<Object, List<Object>> graph = new HashMap<Object, List<Object>>();
        if (StringUtils.equals(moduleName, Constants.AWARD_MODULE)) {
            Award award = getAward(moduleIdentifier);
            addVertex(graph, award);
            buildGraph(graph, award, includeComplianceModules);
            nodes = getParentNodes(graph, new String[]{preferredModule, Constants.AWARD_MODULE});
        } else if (StringUtils.equals(moduleName, Constants.INSTITUTIONAL_PROPOSAL_MODULE)) {
            InstitutionalProposal proposal = getInstitutionalProposal(moduleIdentifier);
            addVertex(graph, proposal);
            buildGraph(graph, proposal, includeComplianceModules);
            nodes = getParentNodes(graph, new String[]{preferredModule, Constants.INSTITUTIONAL_PROPOSAL_MODULE});
        } else if (StringUtils.equals(moduleName, Constants.DEVELOPMENT_PROPOSAL_MODULE)) {
            DevelopmentProposal proposal = getDevelopmentProposal(moduleIdentifier.toString());
            addVertex(graph, proposal);
            buildGraph(graph, proposal, includeComplianceModules);
            nodes = getParentNodes(graph, new String[]{preferredModule, Constants.DEVELOPMENT_PROPOSAL_MODULE});
        } else if (StringUtils.equals(moduleName, Constants.NEGOTIATION_MODULE)) {
            Negotiation negotiation = getNegotiation(moduleIdentifier);
            if (negotiation != null) {
                addVertex(graph, negotiation);
                buildGraph(graph, negotiation, includeComplianceModules);
                nodes = getParentNodes(graph, new String[]{preferredModule, Constants.NEGOTIATION_MODULE});
            }
        } else if (StringUtils.equals(moduleName, Constants.SUBAWARD_MODULE)) {
            SubAward subAward = getSubAward(moduleIdentifier);
            if (subAward != null) {
                addVertex(graph, subAward);
                buildGraph(graph, subAward, includeComplianceModules);
                nodes = getParentNodes(graph, new String[]{preferredModule, Constants.SUBAWARD_MODULE});
            }
        } else if (StringUtils.equals(moduleName, Constants.IRB_MODULE)) {
            Protocol protocol = getProtocol(moduleIdentifier);
            if (protocol != null) {
                addVertex(graph, protocol);
                buildGraph(graph, protocol, includeComplianceModules);
                nodes = getParentNodes(graph, new String[]{preferredModule, Constants.IRB_MODULE});
            }
        } else if (StringUtils.equals(moduleName, Constants.IACUC_MODULE)) {
            IacucProtocol protocol = getIacuc(moduleIdentifier);
            if (protocol != null) {
                addVertex(graph, protocol);
                buildGraph(graph, protocol, includeComplianceModules);
                nodes = getParentNodes(graph, new String[]{preferredModule, Constants.IACUC_MODULE});
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
    protected Object addVertex(HashMap<Object, List<Object>> graph, Object bo) {
    	Object graphBo = findMatchingBo(graph.keySet(), bo);
        if (graphBo == null) {
            graph.put(bo, new ArrayList<Object>());
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
    protected void addEdge(HashMap<Object, List<Object>> graph, Object bo1, Object bo2) {
    	Object graphBo1 = addVertex(graph, bo1);
    	Object graphBo2 = addVertex(graph, bo2);
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
    protected List<MedusaNode> getParentNodes(HashMap<Object, List<Object>> graph, String[] preferedOrder) {
        List<MedusaNode> parentNodes = new ArrayList<MedusaNode>();
        
        for (String prefType : preferedOrder) {
            for (Object bo : graph.keySet()) {
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
    protected void populateChildNodes(HashMap<Object, List<Object>> graph, MedusaNode node, List<MedusaNode> parentNodes) {
        Collection<Object> links = graph.get(node.getBo());
        for (Object bo : links) {
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
    protected boolean isBoInList(List<MedusaNode> nodes, Object bo) {
        for (MedusaNode node : nodes) {
            if (areBusinessObjectsEqual(node.getBo(), bo)) {
                return true;
            }
        }
        return false;
    }
    
    protected void buildGraph(HashMap<Object, List<Object>> graph, SubAward subAward, boolean includeComplianceModules) {
        
        Collection<Award> awards = getAwards(subAward);
        for (Award award : awards) {
            addToGraph(graph, award, subAward, includeComplianceModules);
        }
        Collection<Negotiation> negotiations = getNegotiations(subAward);
        for (Negotiation negotiation : negotiations) {
            addToGraph(graph, negotiation, subAward, includeComplianceModules);
        }        
    }
    
    protected void buildGraph(HashMap<Object, List<Object>> graph, ProtocolBase protocol, boolean includeComplianceModules) {
        for (ProtocolFundingSourceBase fundingSource : protocol.getProtocolFundingSources()) {
            if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.AWARD)) {
                addToGraph(graph, getAward(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            } else if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.INSTITUTIONAL_PROPOSAL)) {
                addToGraph(graph, getInstitutionalProposal(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            } else if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.PROPOSAL_DEVELOPMENT)) {
                addToGraph(graph, getDevelopmentProposal(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            }
        }       
    }

    protected void buildGraph(HashMap<Object, List<Object>> graph, IacucProtocol protocol, boolean includeComplianceModules) {
        for (org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase fundingSource : protocol.getProtocolFundingSources()) {
            if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.AWARD)) {
                addToGraph(graph, getAward(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            } else if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.INSTITUTIONAL_PROPOSAL)) {
                addToGraph(graph, getInstitutionalProposal(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            } else if (StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.PROPOSAL_DEVELOPMENT)) {
                addToGraph(graph, getDevelopmentProposal(fundingSource.getFundingSourceNumber()), protocol, includeComplianceModules);
            }
        }
    }

    protected void addSpecialReviewLinksToGraph(HashMap<Object, List<Object>> graph, List<? extends SpecialReview> specialReviews, Object existingBo) {
        Map<String, Boolean> specialReviewLinking = getSpecialReviewLinkingEnabled(existingBo);
        for (SpecialReview specialReview : specialReviews) {
            if (StringUtils.equals(specialReview.getSpecialReviewTypeCode(), SpecialReviewType.HUMAN_SUBJECTS)
                    && specialReviewLinking.get(SpecialReviewType.HUMAN_SUBJECTS)
                    && !StringUtils.equals(specialReview.getApprovalTypeCode(), SpecialReviewApprovalType.NOT_YET_APPLIED)) {
                Protocol protocol = getProtocol(specialReview.getProtocolNumber());
                if (protocol != null) {
                    addToGraph(graph, protocol, existingBo, true);
                }
            } else if (StringUtils.equals(specialReview.getSpecialReviewTypeCode(), SpecialReviewType.ANIMAL_USAGE)
                    && specialReviewLinking.get(SpecialReviewType.ANIMAL_USAGE)
                    && !StringUtils.equals(specialReview.getApprovalTypeCode(), SpecialReviewApprovalType.NOT_YET_APPLIED)) {
                IacucProtocol protocol = getIacuc(specialReview.getProtocolNumber());
                if (protocol != null) {
                    addToGraph(graph, protocol, existingBo, true);
                }
            }

        }
    }
    
    protected Map<String, Boolean> getSpecialReviewLinkingEnabled(Object existingBo) {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        String irbLinkingName = null;
        String iacucLinkingName = null;
        if (existingBo instanceof DevelopmentProposal) {
            irbLinkingName = Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK;
            iacucLinkingName = Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER;
        } else if (existingBo instanceof InstitutionalProposal) {
            irbLinkingName = Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK;
            iacucLinkingName = Constants.IACUC_PROTOCOL_INSTITUTE_PROPOSAL_LINKING_ENABLED_PARAMETER;
        } else if (existingBo instanceof Award) {
            irbLinkingName = Constants.ENABLE_PROTOCOL_TO_AWARD_LINK;
            iacucLinkingName = Constants.IACUC_PROTOCOL_AWARD_LINKING_ENABLED_PARAMETER;
        }
        if (irbLinkingName != null) {
            result.put(SpecialReviewType.HUMAN_SUBJECTS, 
                    getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, irbLinkingName));
        } else {
            result.put(SpecialReviewType.HUMAN_SUBJECTS, Boolean.FALSE);
        }
        if (iacucLinkingName != null) {
            result.put(SpecialReviewType.ANIMAL_USAGE, 
                    getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IACUC, Constants.PARAMETER_COMPONENT_DOCUMENT, iacucLinkingName));
        } else {
            result.put(SpecialReviewType.ANIMAL_USAGE, Boolean.FALSE);
        }
        return result;
    }
    
    /**
     * 
     * Builds the graph recursively by finding links from the Award.
     * @param graph
     * @param award
     */
    protected void buildGraph(HashMap<Object, List<Object>> graph, Award award, boolean includeComplianceModules) {
        Collection<InstitutionalProposal> proposals = getProposals(award);
        for (InstitutionalProposal proposal : proposals) {
            addToGraph(graph, proposal, award, includeComplianceModules);
        }
        Collection<Negotiation> negotiations = getNegotiations(award);
        for (Negotiation negotiation : negotiations) {
            addToGraph(graph, negotiation, award, includeComplianceModules);
        }
        Collection<SubAward> subAwards = getSubAwards(award);
        for (SubAward subAward : subAwards) {
            addToGraph(graph, subAward, award, includeComplianceModules);
        }
        if (includeComplianceModules) {
            addSpecialReviewLinksToGraph(graph, award.getSpecialReviews(), award);
        }
    }
    
    /**
     * 
     * Builds the graph using links found from the institutional proposal.
     * @param graph
     * @param proposal
     */
    protected void buildGraph(HashMap<Object, List<Object>> graph, InstitutionalProposal proposal, boolean includeComplianceModules) {
        Collection<Award> awards = getAwards(proposal);
        for (Award award : awards) {
            addToGraph(graph, award, proposal, includeComplianceModules);
        }
        Collection<DevelopmentProposal> proposals = getDevelopmentProposals(proposal);
        for (DevelopmentProposal devProp : proposals) {
            addToGraph(graph, devProp, proposal, includeComplianceModules);
        }
        Collection<Negotiation> negotiations = getNegotiations(proposal);
        for (Negotiation negotiation : negotiations) {
            addToGraph(graph, negotiation, proposal, includeComplianceModules);
        }
        if (includeComplianceModules) {
            addSpecialReviewLinksToGraph(graph, proposal.getSpecialReviews(), proposal);
        }
    }
    
    /**
     * 
     * Continues to build the graph finding links from the development proposal
     * @param graph
     * @param devProp
     */
    protected void buildGraph(HashMap<Object, List<Object>> graph, DevelopmentProposal devProp, boolean includeComplianceModules) {
        Collection<InstitutionalProposal> proposals = getProposals(devProp);
        for (InstitutionalProposal proposal : proposals) {
            addToGraph(graph, proposal, devProp, includeComplianceModules);
        } 
        addSpecialReviewLinksToGraph(graph, devProp.getPropSpecialReviews(), devProp);
    }
    
    protected void buildGraph(HashMap<Object, List<Object>> graph, Negotiation negotiation, boolean includeComplianceModules) {
    	Object bo = getNegotiationService().getAssociatedObject(negotiation);
        if (bo instanceof Award || bo instanceof InstitutionalProposal || bo instanceof SubAward) {
            addToGraph(graph, bo, negotiation, includeComplianceModules);
        }
    }
    
    protected void addToGraph(HashMap<Object, List<Object>> graph, Object newBo, Object existingBo, boolean includeComplianceModules) {
        if (newBo == null || existingBo == null) {
            throw new RuntimeException("Inavlid or null Medusa link found");
        }
        if (findMatchingBo(graph.keySet(), newBo) == null) {
            addEdge(graph, existingBo, newBo);
            if (newBo instanceof Award) {
                buildGraph(graph, (Award)newBo, includeComplianceModules);
            } else if (newBo instanceof InstitutionalProposal) {
                buildGraph(graph, (InstitutionalProposal) newBo, includeComplianceModules);
            } else if (newBo instanceof DevelopmentProposal) {
                buildGraph(graph, (DevelopmentProposal) newBo, includeComplianceModules);
            } else if (newBo instanceof Negotiation) {
                buildGraph(graph, (Negotiation) newBo, includeComplianceModules);
            } else if (newBo instanceof SubAward) {
                buildGraph(graph, (SubAward) newBo, includeComplianceModules);
            } else if (newBo instanceof Protocol) {
                buildGraph(graph, (Protocol) newBo, includeComplianceModules);
            } else if (newBo instanceof IacucProtocol) {
                buildGraph(graph, (IacucProtocol) newBo, includeComplianceModules);
            }
        } else {
            addEdge(graph, existingBo, newBo);            
        }
    }
    
    /**
     * 
     * Looks through the boSet for a matching medusa BO.
     * @param boSet
     * @param bo
     * @return
     */
    protected Object findMatchingBo(Collection<Object> boSet, Object bo) {
        for (Object curBo : boSet) {
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
    protected boolean areBusinessObjectsEqual(Object bo1, Object bo2) {
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
        } else if (bo1 instanceof SubAward && bo2 instanceof SubAward) {
            if (ObjectUtils.equals(((SubAward) bo1).getSubAwardId(),
                    ((SubAward) bo2).getSubAwardId())) {
                return true;
            }
        } else if (bo1 instanceof Protocol && bo2 instanceof Protocol) {
            if (ObjectUtils.equals(((Protocol) bo1).getProtocolId(),
                    ((Protocol) bo2).getProtocolId()))
                return true;
        } else if (bo1 instanceof IacucProtocol && bo2 instanceof IacucProtocol) {
            if (ObjectUtils.equals(((IacucProtocol) bo1).getProtocolId(),
                    ((IacucProtocol) bo2).getProtocolId()))
                return true;
        }
        return false;
    }
    
    protected DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return (DevelopmentProposal)dataObjectService.find(DevelopmentProposal.class, proposalNumber);
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
        InstitutionalProposal currentProposal = getInstitutionalProposal(proposal.getProposalNumber());
        return currentProposal == null ? proposal : currentProposal;
    }
    
    protected InstitutionalProposal getInstitutionalProposal(String proposalNumber) {
        InstitutionalProposal currentProposal = null;
        for (VersionStatus status : new VersionStatus[]{VersionStatus.ACTIVE, VersionStatus.PENDING, VersionStatus.ARCHIVED}) {
            currentProposal = getNewestProposalByStatus(proposalNumber, status);
            if (currentProposal != null) {
                break;
            }
        }
        return currentProposal;
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
    
    protected Award getAward(String awardNumber) {
        Award currentAward = (Award) getActiveOrCurrentVersion(Award.class, awardNumber);
        return currentAward;
    }
    
    protected Negotiation getNegotiation(Long negotiationId) {
        Negotiation negotiation = negotiationId == null ? null : (Negotiation) businessObjectService.findBySinglePrimaryKey(Negotiation.class, negotiationId);
        return negotiation;
    }
    
    protected SubAward getSubAward(Long subAwardId) {
        SubAward subAward = (SubAward) businessObjectService.findBySinglePrimaryKey(SubAward.class, subAwardId);
        if (subAward == null) {
            return null;
        }
        SubAward currentSubAward = (SubAward) getActiveOrCurrentVersion(SubAward.class, subAward.getSubAwardCode());
        if (currentSubAward != null) {
            getSubAwardService().getAmountInfo(currentSubAward);
        }
        return currentSubAward == null ? subAward : currentSubAward;
    }
    
    protected Protocol getProtocol(Long protocolId) {
        Protocol protocol = (Protocol)businessObjectService.findBySinglePrimaryKey(Protocol.class, protocolId);
        return protocol;
    }
    protected Protocol getProtocol(String protocolNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("protocolNumber", protocolNumber);
        List<Protocol> versions = (List<Protocol>) businessObjectService.findMatching(Protocol.class, values);
        Protocol newest = null;
        for (Protocol version : versions) {
            if (newest == null || version.getSequenceNumber() > newest.getSequenceNumber()) {
                newest = version;
            }
        }
        return newest;
    }
    
    protected IacucProtocol getIacuc(Long protocolId) {
        IacucProtocol protocol = (IacucProtocol) businessObjectService.findBySinglePrimaryKey(IacucProtocol.class, protocolId);
        return protocol;
    }
    protected IacucProtocol getIacuc(String protocolNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("protocolNumber", protocolNumber);
        List<IacucProtocol> versions = (List<IacucProtocol>) businessObjectService.findMatching(IacucProtocol.class, values);
        IacucProtocol newest = null;
        for (IacucProtocol version : versions) {
            if (newest == null || version.getSequenceNumber() > newest.getSequenceNumber()) {
                newest = version;
            }
        }
        return newest;
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
    protected MedusaNode getNode(Object bo) {
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
        } else if (bo instanceof Protocol) {
            return getNode((Protocol) bo);
        } else if (bo instanceof IacucProtocol) {
            return getNode((IacucProtocol) bo);
        } else {
            return null;
        }
    }
    
    protected MedusaNode getNode(Award award) {
        AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
        MedusaNode node = new MedusaNode();
        node.setBo(award);
        node.setType(Constants.AWARD_MODULE);
        node.setExtraInfo(awardAmountInfo);
        return node;
    }
    
    protected MedusaNode getNode(InstitutionalProposal proposal) {
        MedusaNode node = new MedusaNode();
        node.setBo(proposal);
        node.setType(Constants.INSTITUTIONAL_PROPOSAL_MODULE);
        proposal.setNsfCodeBo(getNsfCode(proposal.getNsfCode()));
        return node;
    }
    
    protected MedusaNode getNode(DevelopmentProposal proposal) {
        MedusaNode node = new MedusaNode();
        node.setBo(proposal);
        node.setType(Constants.DEVELOPMENT_PROPOSAL_MODULE);
        return node;
    }
    
    protected MedusaNode getNode(Negotiation negotiation) {
        MedusaNode node = new MedusaNode();
        node.setBo(negotiation);
        node.setType(Constants.NEGOTIATION_MODULE);
        return node;
    }
    protected MedusaNode getNode(SubAward subAward) {
        MedusaNode node = new MedusaNode();
        node.setBo(subAward);
        node.setType(Constants.SUBAWARD_MODULE);
        return node;
    }
    protected MedusaNode getNode(Protocol protocol) {
        MedusaNode node = new MedusaNode();
        node.setBo(protocol);
        node.setType(Constants.IRB_MODULE);
        return node;
    }
    protected MedusaNode getNode(IacucProtocol protocol) {
        MedusaNode node = new MedusaNode();
        node.setBo(protocol);
        node.setType(Constants.IACUC_MODULE);
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
            	devProposals.add(getDevelopmentProposal(proposalAdminDetail.getDevProposalNumber()));
            }
        }
        return devProposals;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<Award> getAwards(SubAward subAward) {
        
        Collection<Award> awards = new ArrayList<Award>();
        SubAward newestSubAaward = getSubAward(subAward.getSubAwardCode());

        Collection<SubAwardFundingSource> subAwardFundingSources = newestSubAaward.getSubAwardFundingSourceList();
        for (SubAwardFundingSource subAwardFundingSource : subAwardFundingSources){
            awards.add(getAward(subAwardFundingSource.getAwardId()));
        }
        return awards;
    }
    
    protected SubAward getSubAward(String subAwardCode) {
        SubAward subAward = (SubAward) getActiveOrCurrentVersion(SubAward.class, subAwardCode);
        return subAward;
    }
    
    protected Collection<SubAward> getSubAwards(Award award) {
        Collection<SubAward> subAwards = getSubAwardService().getLinkedSubAwards(award);
        return subAwards;
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
                /*
                 * KRACOEUS-6539: Medusa needs to check that this awardFundingProposal is STILL active in the current active IP version
                 * before displaying it. Currently, awards from cancelled IPs are also appearing in the list.
                 */
                if (awardFunding.isActive() && !curIp.isCancelled()) {
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
                InstitutionalProposal curProposal = businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", awardFunding.getProposalId()));
                boolean proposalNotCancelled = !curProposal.isCancelled();
                if (proposalNotCancelled && awardFunding.isActive()) {
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
        return proposalAdminDetails.stream()
        		.map(ProposalAdminDetails::getInstProposalId)
        		.filter(proposalNumber -> proposalNumber != null)
        		.map(this::getInstitutionalProposal)
        		.collect(Collectors.toList());      
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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
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
    protected AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }

    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected NegotiationService getNegotiationService() {
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    protected SubAwardService getSubAwardService() {
        return subAwardService;
    }

    public void setSubAwardService(SubAwardService subAwardService) {
        this.subAwardService = subAwardService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
