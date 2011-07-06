package org.kuali.kra.service.impl.adapters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;

public class AwardHierarchyServiceAdapter implements AwardHierarchyService {
    public AwardHierarchy copyAwardAndAllDescendantsAsNewHierarchy(AwardHierarchy targetNode) {
        return null;  
    }

    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return null;  
    }

    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return null;  
    }

    public AwardHierarchy copyAwardAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return null;  
    }

    public AwardHierarchy copyAwardAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return null;  
    }

    public AwardHierarchy copyAwardAsNewHierarchy(AwardHierarchy award) {
        return null;  
    }

    public AwardHierarchy createBasicHierarchy(String awardNumber) {
        return null;  
    }

    public AwardHierarchy createNewAwardBasedOnAnotherAwardInHierarchy(AwardHierarchy nodeToCopyFrom, AwardHierarchy targetParentNode) {
        return null;  
    }

    public AwardHierarchy createNewAwardBasedOnParent(AwardHierarchy targetNode) {
        return null;  
    }

    public AwardHierarchy createNewChildAward(AwardHierarchy targetNode) {
        return null;  
    }

    public AwardHierarchy loadAwardHierarchy(String awardNumber) {
        return null;  
    }

    public AwardHierarchy loadAwardHierarchyBranch(String parentAwardNumber) {
        return null;  
    }

    public AwardHierarchy loadFullHierarchyFromAnyNode(String awardNumber) {
        return null;  
    }

    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order) {
        return null;  
    }

    public Map<String, AwardHierarchy> getAwardHierarchy(AwardHierarchy rootNode, List<String> order) {
        return null;  
    }

    public AwardDocument loadPlaceholderDocument() {
        return null;  
    }

    public void persistAwardHierarchy(AwardHierarchy awardHierarchy) {
    }

    public void persistAwardHierarchy(AwardHierarchy awardHierarchy, boolean recurs) {
    }

    public void persistAwardHierarchies(Collection<AwardHierarchy> awardHierarchyRootNodes) {
    }

    public void populateAwardHierarchyNodes(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber) {
        
    }

    public void createNodeMapsOnFormForSummaryPanel(Map<String, AwardHierarchyNode> awardHierarchyNodes,
            Map<String, String> previousNodeMap, Map<String, String> nextNodeMap) {
        // TODO Auto-generated method stub
        
    }

    public void populateAwardHierarchyNodesForTandMDoc(Map<String, AwardHierarchy> awardHierarchyItems,
            Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber,
            String docNum) {
        // TODO Auto-generated method stub
        
    }

    public AwardHierarchyNode createAwardHierarchyNode(AwardHierarchy awardHierarchy, String currentAwardNumber,
            String currentSequenceNumber) {
        return null;
    }
}
