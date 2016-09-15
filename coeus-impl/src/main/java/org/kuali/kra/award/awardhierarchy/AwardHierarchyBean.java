/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.awardhierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.MessageList;
import org.kuali.rice.krad.util.ErrorMessage;

import java.io.Serializable;
import java.util.*;

/**
 * Assumptions:
 *
 * 1) All hierarchy changes occur in memory only until an explicit save occurs
 *    a) Multiple hierarchy changes must be tracked until the save
 *    b) Many hierarchy operations require creation of Award copies or new Awards. To keep track of these new Awards, the hierarchy node should keep a
 *       transient reference to the new Award
 *
 * 2) Placeholder AwardDocument
 *    a) All Awards in all hierarchies will be referenced in a flattened list of awards on a placeholder AwardDocument
 *    b) This document will be used to save Awards. (Can BOS be used to handle only new Awards which is all AwardHierarchyService produces?)
 *
 * 3) Service methods will generally return a hierarchy node representing the results of an operation
 *    a) Hierarchy operations start with the awardNumber for the UI-selected hierarchy node
 *
 * 4) This bean will
 *    a) This bean will keep a reference to that placeholder document once obtained
 *    b) Keep the root node of all hierarchy roots (including the current hierarchy) produced during a user session
 *    c) Be used by AwardActionsAction (and AwardActions) to interact with hierarchy
 *
 * 5) On save,
 *    a) All hierarchy roots will be recursed to produce a flattened list of new hierarchy nodes. These will be saved with the BOS
 *    b) No deletes? No updates? (i.e. no move hierarchy?)
 *
 *  Bean has been updated to null check the transient awardHierarchyService field before use.
 */
public class AwardHierarchyBean implements Serializable {
    private static final Log LOG = LogFactory.getLog(AwardHierarchyBean.class);
    
    private AwardForm awardForm;
    private transient AwardHierarchyService awardHierarchyService;

    private Map<String, AwardHierarchy> rootNodes;
    private AwardHierarchy rootNode;
    private Map<String, AwardHierarchy> hierarchy;
    private List<String> hierarchyOrder;
    private List<String> allAwardNumbers;
    private List<String> finalAwardNumbers;
    private static final String ERROR_AWARD_HIERARCHY_NOTSAVED = "error.award.hierarchy.notsaved";

    /**
     * C'tor
     * @param awardForm
     */
    public AwardHierarchyBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        init();
    }

    public AwardHierarchyBean() {
    }

    /**
     * C'tor mostly for testing
     * @param awardForm
     * @param awardHierarchyService
     */
    AwardHierarchyBean(AwardForm awardForm, AwardHierarchyService awardHierarchyService) {
        this.awardForm = awardForm;
        this.awardHierarchyService = awardHierarchyService;
        init();
    }
    
    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnotherAward(String sourceAwardNumber, String targetParentAwardNumber) {
        return getRootNode().findNodeInHierarchy(targetParentAwardNumber) != null ?
                    copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(sourceAwardNumber, targetParentAwardNumber) :
                    copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(sourceAwardNumber, targetParentAwardNumber);
    }

    //TODO: Make it protected once the old table UI is removed
    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(String sourceAwardNumber, String targetParentAwardNumber) {
        AwardHierarchy sourceNode = getRootNode().findNodeInHierarchy(sourceAwardNumber);
        AwardHierarchy targetParentNode = getRootNode().findNodeInHierarchy(targetParentAwardNumber);
        AwardHierarchy newNode = getAwardHierarchyService().copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(sourceNode, targetParentNode);
        return newNode;
    }

    //TODO: Make it protected once the old table UI is removed 
    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(String sourceAwardNumber, String targetParentAwardNumber) {
        AwardHierarchy sourceNode = getRootNode().findNodeInHierarchy(sourceAwardNumber);
        AwardHierarchy foreignRoot = getAwardHierarchyService().loadFullHierarchyFromAnyNode(targetParentAwardNumber);
        AwardHierarchy targetParentNode = foreignRoot.findNodeInHierarchy(targetParentAwardNumber);
        AwardHierarchy newBranchNode = getAwardHierarchyService().copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(sourceNode, targetParentNode);
        rootNodes.put(foreignRoot.getAwardNumber(), foreignRoot);
        saveHierarchyChanges();
        return newBranchNode;
    }

    public AwardHierarchy copyAwardAsChildOfAnotherAward(String sourceAwardNumber, String targetParentAwardNumber) {
        return getRootNode().findNodeInHierarchy(targetParentAwardNumber) != null ?
                        copyAwardAsChildOfAnAwardInCurrentHierarchy(sourceAwardNumber, targetParentAwardNumber) :
                        copyAwardAsChildOfAnAwardInAnotherHierarchy(sourceAwardNumber, targetParentAwardNumber);
    }

    //TODO: Make it protected once the old table UI is removed
    public AwardHierarchy copyAwardAsChildOfAnAwardInCurrentHierarchy(String sourceAwardNumber, String targetParentAwardNumber) {
        AwardHierarchy sourceNode = getRootNode().findNodeInHierarchy(sourceAwardNumber);
        AwardHierarchy targetParentNode = sourceNode.findNodeInHierarchy(targetParentAwardNumber);
        return getAwardHierarchyService().copyAwardAsChildOfAnAwardInCurrentHierarchy(sourceNode, targetParentNode);
    }

    //TODO: Make it protected once the old table UI is removed
    public AwardHierarchy copyAwardAsChildOfAnAwardInAnotherHierarchy(String sourceAwardNumber, String targetParentAwardNumber) {
        AwardHierarchy sourceNode = getRootNode().findNodeInHierarchy(sourceAwardNumber);
        AwardHierarchy foreignRoot = getAwardHierarchyService().loadFullHierarchyFromAnyNode(targetParentAwardNumber);
        AwardHierarchy targetParentNode = foreignRoot.findNodeInHierarchy(targetParentAwardNumber);
        AwardHierarchy newNode = getAwardHierarchyService().copyAwardAsChildOfAnAwardInAnotherHierarchy(sourceNode, targetParentNode);
        rootNodes.put(foreignRoot.getAwardNumber(), foreignRoot);
        saveHierarchyChanges();
        return newNode;
    }

    public AwardHierarchy copyAwardAndAllDescendantsAsNewHierarchy(String awardNumber) {
        AwardHierarchy newRoot = getAwardHierarchyService().copyAwardAndAllDescendantsAsNewHierarchy(getRootNode().findNodeInHierarchy(awardNumber));
        rootNodes.put(newRoot.getAwardNumber(), newRoot);
        return newRoot;
    }

    /**
     * @param awardNumber
     * @return
     */
    public AwardHierarchy copyAwardAsNewHierarchy(String awardNumber) {
        AwardHierarchy newRoot = getAwardHierarchyService().copyAwardAsNewHierarchy(getRootNode().findNodeInHierarchy(awardNumber));
        rootNodes.put(newRoot.getAwardNumber(), newRoot);
        return newRoot;
    }

    /**
     * @param awardNumber
     * @return
     */
    public AwardHierarchy createNewChildAward(String awardNumber) {
        AwardHierarchy targetNode = getRootNode().findNodeInHierarchy(awardNumber);
        return getAwardHierarchyService().createNewChildAward(targetNode);
    }

    public AwardHierarchy createNewChildAwardBasedOnAnotherAwardInHierarchy(String nodeToCopyFromAwardNumber, String targetParentNodeAwardNumber) {
        AwardHierarchy nodeToCopyFrom = null;
        AwardHierarchy foreignRoot = getAwardHierarchyService().loadFullHierarchyFromAnyNode(nodeToCopyFromAwardNumber);
        if(foreignRoot != null) {
            nodeToCopyFrom = foreignRoot.findNodeInHierarchy(nodeToCopyFromAwardNumber);
        }
        
        if(nodeToCopyFrom == null) {
            throw new MissingHierarchyException(nodeToCopyFromAwardNumber);
        }
        AwardHierarchy targetParentNode = getRootNode().findNodeInHierarchy(targetParentNodeAwardNumber);        
        if(targetParentNode == null) {
            throw new MissingHierarchyException(targetParentNodeAwardNumber);
        } 
        return getAwardHierarchyService().createNewAwardBasedOnAnotherAwardInHierarchy(nodeToCopyFrom, targetParentNode);
    }

    public AwardHierarchy createNewAwardBasedOnParent(String awardNumber) {
        final AwardHierarchy rootNode = getRootNode();
        return getNewNodeBasedOnParent(awardNumber, rootNode);
    }

    public AwardHierarchy getNewNodeBasedOnParent(String awardNumber, AwardHierarchy rootNode) {
        AwardHierarchy targetNode = rootNode.findNodeInHierarchy(awardNumber);
        AwardHierarchy newNode =  getAwardHierarchyService().createNewAwardBasedOnParent(targetNode);
        return newNode;
    }

    public Map<String, AwardHierarchy> getCurrentAwardHierarchy() {
        if (hierarchy == null) {
            loadHierarchy(this.getAwardForm().getAwardDocument().getAward().getAwardNumber());
        }
        return hierarchy;
    }
    
    public List<String> getCurrentHierarchyOrder() {
        return hierarchyOrder;
    }

    public Map<String, AwardHierarchy> getAwardHierarchy(AwardHierarchy rootNode, List<String> order) {
        return getAwardHierarchyService().getAwardHierarchy(rootNode, order);
    }

    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order) {
        return getAwardHierarchyService().getAwardHierarchy(awardNumber, order);
    }

    public void createDefaultAwardHierarchy(String parentAwardNumber, String rootAwardNumber, Award award) {
        AwardHierarchy newNode = new AwardHierarchy();
        newNode.setAwardNumber(award.getAwardNumber());
        newNode.setParentAwardNumber(parentAwardNumber);
        newNode.setRootAwardNumber(rootAwardNumber);
        newNode.setOriginatingAwardNumber(award.getAwardNumber());
        newNode.setAward(award);
        if(newNode.isRootNode()) {
            rootNode = newNode;
            rootNodes.put(award.getAwardNumber(), newNode);
            hierarchy = new HashMap<>();
            hierarchy.put(award.getAwardNumber(), newNode);
        }
    }    

    /**
     * @return Returns the nodeForCurrentAward.
     */
    public AwardHierarchy getCurrentAwardHierarchyNode() {
        return getRootNode().findNodeInHierarchy(getAward().getAwardNumber());
    }
    
    public AwardHierarchy getCurrentRootNode() {
        return rootNode;
    }

    public boolean saveHierarchyChanges() {
        MessageList messageList = new MessageList();
        MessageList originalMessageList = KNSGlobalVariables.getMessageList();
        if(originalMessageList != null) {
            messageList.addAll(originalMessageList);
        }
        boolean result;
        try {
            getAwardHierarchyService().persistAwardHierarchies(rootNodes.values());
            refreshCurrentHierarchy();
            LOG.info("Hierarchy changes saved");
            result = true;
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            messageList.add(new ErrorMessage(ERROR_AWARD_HIERARCHY_NOTSAVED, e.getMessage()));
            result = false;
        }
        KNSGlobalVariables.setMessageList(messageList);
        return result;
    }
    
    public Integer getMaxAwardNumber() {
        String nextAwardNumber = getRootNode().generateNextAwardNumberInSequence();
        String[] parts = nextAwardNumber.split("-");
        return Integer.valueOf(parts[1]);
    }

    public AwardHierarchy getRootNode() {
        String currentAwardNumber = getAward().getAwardNumber();
        return getRootNode(currentAwardNumber);
    }

    public AwardHierarchy getRootNode(String currentAwardNumber) {
        AwardHierarchy thisRootNode = findRootNodeForCurrentAward(currentAwardNumber);
        if(thisRootNode == null) {
            thisRootNode = loadRootNodeForAwardNumber(currentAwardNumber);
        }
        return thisRootNode;
    }

    public void refreshCurrentHierarchy() {
        if(awardForm != null) {
            rootNodes.clear();
            loadHierarchy(getAward().getAwardNumber());
        }
    }
    
    protected void loadHierarchy(String awardNumber) {
        hierarchyOrder = new ArrayList<>();
        hierarchy = getAwardHierarchyService().getAwardHierarchy(awardNumber, hierarchyOrder);
        if (!hierarchyOrder.isEmpty()) {
            rootNode = hierarchy.get(hierarchyOrder.get(0));
            rootNodes.put(rootNode.getAwardNumber(), rootNode);
        }
        allAwardNumbers = hierarchyOrder;
        finalAwardNumbers = hierarchyOrder;
    }

    /**
     * Method is untestable as a unit test until prevAwardNumber/prevRootAwardNmber is pulled off of form to this bean
     * @param targetNode
     */
    public void recordTargetNodeState(AwardHierarchy targetNode) {
        awardForm.setPrevAwardNumber(targetNode.getAwardNumber());
        awardForm.setPrevRootAwardNumber(targetNode.getRootAwardNumber());
    }

    public void init() {
        String awardNumber = getAward().getAwardNumber();
        init(awardNumber);
    }

    public void init(String awardNumber) {
        rootNodes = new TreeMap<>();
        awardHierarchyService = getAwardHierarchyService();
        if(Award.DEFAULT_AWARD_NUMBER.equals(awardNumber)) {
            awardNumber = awardForm.getPrevRootAwardNumber();
        }

        if(awardNumber != null) {
            loadHierarchy(awardNumber);
        }
    }

    Award getAward() {
        return awardForm.getAwardDocument().getAward();
    }

    AwardHierarchyService getAwardHierarchyService() {
        if(awardHierarchyService == null) {
            awardHierarchyService = KcServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

    AwardForm getAwardForm() {
        return awardForm;
    }

    void setAwardForm(AwardForm awardForm) {
        this.awardForm = awardForm;
    }

    private AwardHierarchy findRootNodeForCurrentAward(String currentAwardNumber) {
        AwardHierarchy thisRootNode = null;
        for(AwardHierarchy rootNode: rootNodes.values()) {
            if(rootNode.findNodeInHierarchy(currentAwardNumber) != null) {
                thisRootNode = rootNode;
                break;
            }
        }
        return thisRootNode;
    }

    private AwardHierarchy loadRootNodeForAwardNumber(String currentAwardNumber) {
        AwardHierarchy thisRootNode;
        thisRootNode = getAwardHierarchyService().loadFullHierarchyFromAnyNode(currentAwardNumber);
        if(thisRootNode != null) {
            rootNodes.put(thisRootNode.getAwardNumber(), thisRootNode);
        } else {
            // create temp new hierarchy
            thisRootNode = AwardHierarchy.createRootNode(currentAwardNumber);
        }
        return thisRootNode;
    }

    public List<String> getAllAwardNumbers() {
        return allAwardNumbers;
    }

    public void setAllAwardNumbers(List<String> allAwardNumbers) {
        this.allAwardNumbers = allAwardNumbers;
    }

    public List<String> getFinalAwardNumbers() {
        return finalAwardNumbers;
    }

    public void setFinalAwardNumbers(List<String> finalAwardNumbers) {
        this.finalAwardNumbers = finalAwardNumbers;
    }

    public Map<String, AwardHierarchy> getRootNodes() {
        return rootNodes;
    }

    public void setRootNodes(Map<String, AwardHierarchy> rootNodes) {
        this.rootNodes = rootNodes;
    }

}
