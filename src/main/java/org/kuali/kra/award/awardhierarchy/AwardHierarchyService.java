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
package org.kuali.kra.award.awardhierarchy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.rice.kew.api.exception.WorkflowException;

public interface AwardHierarchyService {
    static boolean RECURS_HIERARCHY = true;

    // Create new node, with Award copied from target node's Award. New node will be a root node and will be a branch node. (Descendant nodes are copied)
    AwardHierarchy copyAwardAndAllDescendantsAsNewHierarchy(AwardHierarchy targetNode);

    // Create new node. New node will be a child node of another node in a different hierarchy and will be a branch node,
    // All new Awards associated with copied branch will be copies of Awards from first hierarchy branch
    AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode);

    // Create new node, with Award copied from target node's Award. New node will be a child node of another node in SAME hierarchy and will be a branch node
    AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode);

    // Create new node, with Award copied from target node's Award. New node will be a child node of another node in a DIFFERENT hierarchy and will be a leaf node
    AwardHierarchy copyAwardAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode);

    // Create new node, with Award copied from target node's Award. New node will be a child node of another node in same hierarchy and will be a leaf node
    AwardHierarchy copyAwardAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode);

    // Create new node, with Award copied from target node's Award. New node will be a root node and will be a leaf node. (Descendant nodes not copied)
    AwardHierarchy copyAwardAsNewHierarchy(AwardHierarchy award);

    // Create a root hierarchy node
    AwardHierarchy createBasicHierarchy(String awardNumber);

    // Create new node, with Award copied from another Award in SAME hierarchy. New node will be child of target node and will be a leaf node
    AwardHierarchy createNewAwardBasedOnAnotherAwardInHierarchy(AwardHierarchy nodeToCopyFrom, AwardHierarchy targetParentNode);

    // Create new node, with Award copied from parent Award. New node will be child of target node and will be a leaf node
    AwardHierarchy createNewAwardBasedOnParent(AwardHierarchy targetNode);

    // Create new node, with new Award having awardNumber = newAwardNumber. New node will be child of target node and will be a leaf node
    AwardHierarchy createNewChildAward(AwardHierarchy targetNode);

    // Load single hierarchy node
    AwardHierarchy loadAwardHierarchy(String awardNumber);
    
    /**
     * This method loads the AwardHierarchy and recurs through all children (if any)
     * @param parentAwardNumber
     * @return The AwardHierarchy tree branch starting with the node at awardNumber
     */
    AwardHierarchy loadAwardHierarchyBranch(String parentAwardNumber);

    /**
     * Load a full hierarchy, returning the root node
     * @param awardNumber
     * @return
     */
    AwardHierarchy loadFullHierarchyFromAnyNode(String awardNumber);

    // Get hiearchy as map, starting with a node's awardNumber
    Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order);

    /**
     * Get hierarchy as map, starting with the rootNode. Suppose the hiearchy looks like this:
     *              Root
     *         _______|______
     *        |              |
     *     Node 1         Node 2
     *     |- Node 1.1      |- Node 2.1
     *     |- Node 1.2      |- Node 2.2
     *                      | -Node 3.2
     *
     *  The default flattened (i.e. List) presentation will be:
     *    Root
     *    Node 1
     *    Node 1.1
     *    Node 1.2
     *    Node 2
     *    Node 2.1
     *    Node 2.2
     *
     * @param rootNode
     * @return
     */
    Map<String, AwardHierarchy> getAwardHierarchy(AwardHierarchy rootNode, List<String> order);
    
    AwardDocument loadPlaceholderDocument();

    // save single hierarchy node, same as persistAwardHierarchy(node, false)
    void persistAwardHierarchy(AwardHierarchy awardHierarchy);

    /**
     * This method saves a node. If recurs is true, save complete branch; i.e. all child nodes, grandchildren, etc.
     * @param awardHierarchy
     * @param recurs
     */
    void persistAwardHierarchy(AwardHierarchy awardHierarchy, boolean recurs);

    /**
     * Save all hierarchy nodes for each hierarchy represented by each root node element in the supplied list
     * @param awardHierarchyRootNodes
     */
    void persistAwardHierarchies(Collection<AwardHierarchy> awardHierarchyRootNodes);
    
    /**
     * 
     * This method populates the AwardHierarchyNodes with relevant information required from other objects.
     * @param awardHierarchyItems
     * @param awardHierarchyNodes
     * @param currentAward
     * @throws WorkflowException 
     */
    public void populateAwardHierarchyNodes(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber);
    public void populateAwardHierarchyNodesForTandMDoc(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber, String docNum);
    public void createNodeMapsOnFormForSummaryPanel(Map<String, AwardHierarchyNode> awardHierarchyNodes, Map<String, String> previousNodeMap, Map<String, String> nextNodeMap);

    /**
     * Returns an AwardHierarchyNode for the awardHierarchy. If the awardHierarchy is for the currentAwardNumber,
     * then will use the currentSequenceNumber if it is pending instead of the usual method for determining
     * the current award.  
     * @param awardHierarchy
     * @param currentAwardNumber
     * @param currentSequenceNumber
     * @return
     */
    AwardHierarchyNode createAwardHierarchyNode(AwardHierarchy awardHierarchy, String currentAwardNumber, String currentSequenceNumber);
}
