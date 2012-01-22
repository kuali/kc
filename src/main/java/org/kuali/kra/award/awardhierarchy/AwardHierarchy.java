/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.awardhierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * AwardHierarchy is version agnostic. It should always reference the active version of the Award if one is present. If not present, it will reference the one
 * and only pending Award matching the AwardHierarchy awardNumber.
 */
public class AwardHierarchy extends KraPersistableBusinessObjectBase implements Cloneable {

    public static final String ROOTS_PARENT_AWARD_NUMBER = "000000-00000";

    public static final String UNIQUE_IDENTIFIER_FIELD = "awardNumber";

    private static final long serialVersionUID = 1L;

    private Long awardHierarchyId;

    private String rootAwardNumber;

    private String awardNumber;

    private String parentAwardNumber;

    private String originatingAwardNumber = Award.DEFAULT_AWARD_NUMBER;

    private AwardHierarchy root;

    private AwardHierarchy parent;

    private Boolean active = true;

    private transient Award award;

    private transient List<AwardHierarchy> children;

    private transient BusinessObjectService boService;

    private transient VersionHistoryService versionHistoryService;

    /**
     * Default C'tor
     */
    public AwardHierarchy() {
        children = new ArrayList<AwardHierarchy>();
    }

    /**
     * C'tor
     * @param rootNode
     * @param parentNode
     * @param awardNumber
     * @param originatingAwardNumber
     */
    public AwardHierarchy(AwardHierarchy rootNode, AwardHierarchy parentNode, String awardNumber, String originatingAwardNumber) {
        this();
        setRoot(rootNode);
        setParent(parentNode);
        setAwardNumber(awardNumber);
        setOriginatingAwardNumber(originatingAwardNumber);
    }

    /**
     *
     * @param rootAwardNumber
     * @param parentAwardNumber
     * @param awardNumber
     * @param originatingAwardNumber
     */
    public AwardHierarchy(String rootAwardNumber, String parentAwardNumber, String awardNumber, String originatingAwardNumber) {
        this();
        this.rootAwardNumber = rootAwardNumber;
        this.parentAwardNumber = parentAwardNumber;
        this.awardNumber = awardNumber;
        this.originatingAwardNumber = originatingAwardNumber;
    }

    /**
     * 
     * @param rootAwardNumber
     * @param parentAwardNumber
     * @param awardNumber
     */
    public AwardHierarchy(String rootAwardNumber, String parentAwardNumber, String awardNumber) {
        this(rootAwardNumber, parentAwardNumber, awardNumber, awardNumber);
    }

    /**
     * Convenience method to get child count
     * @return
     */
    public int childCount() {
        return getChildren().size();
    }

    /**
     * Factory method for creating a root node from an Award
     * @param award
     * @return
     */
    public static AwardHierarchy createRootNode(Award award) {
        AwardHierarchy rootNode = createRootNode(award.getAwardNumber());
        rootNode.setAward(award);
        return rootNode;
    }

    /**
     * Factory method for creating a root node from an awardNumber
     * @param awardNumber
     * @return
     */
    public static AwardHierarchy createRootNode(String awardNumber) {
        return new AwardHierarchy(awardNumber, ROOTS_PARENT_AWARD_NUMBER, awardNumber, awardNumber);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof AwardHierarchy)) return false;
        AwardHierarchy other = (AwardHierarchy) obj;
        if (awardNumber == null) {
            if (other.awardNumber != null) return false;
        } else if (!awardNumber.equals(other.awardNumber)) return false;
        if (parentAwardNumber == null) {
            if (other.parentAwardNumber != null) return false;
        } else if (!parentAwardNumber.equals(other.parentAwardNumber)) return false;
        if (rootAwardNumber == null) {
            if (other.rootAwardNumber != null) return false;
        } else if (!rootAwardNumber.equals(other.rootAwardNumber)) return false;
        return true;
    }

    /**
     * @return
     */
    public Award getAward() {
        if (award == null) {
            lazyLoadAward();
        }
        return award;
    }

    /**
     * Method here for future JPA use
     * @return
     */
    public Long getAwardHierarchyId() {
        return awardHierarchyId;
    }

    /**
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Gets the children attribute.
     * @return Returns the children.
     */
    public List<AwardHierarchy> getChildren() {
        return children;
    }

    public String getOriginatingAwardNumber() {
        return originatingAwardNumber;
    }

    /**
     * Gets the parent attribute.
     * @return Returns the parent.
     */
    public AwardHierarchy getParent() {
        if (!isRootNode() && parent == null && parentAwardNumber != null) {
            parent = findAwardHierarchyMatchingAwardNumber(parentAwardNumber);
        }
        return parent;
    }

    public String getParentAwardNumber() {
        return parentAwardNumber;
    }

    /**
     * Gets the root attribute.
     * @return Returns the root.
     */
    public AwardHierarchy getRoot() {
        if (isRootNode()) {
            root = this;
        } else {
            if (root == null && rootAwardNumber != null) {
                root = findAwardHierarchyMatchingAwardNumber(rootAwardNumber);
            }
        }
        return root;
    }

    public List<AwardHierarchy> getFlattenedListOfNodesInHierarchy() {
        List<AwardHierarchy> list = new ArrayList<AwardHierarchy>();
        addNodeToFlattenedList(list, findRootNode());
        return list;
    }

    public Map<String, AwardHierarchy> getMapOfNodesInHierarchy() {
        Map<String, AwardHierarchy> nodeMap = new TreeMap<String, AwardHierarchy>();
        List<AwardHierarchy> nodes = getFlattenedListOfNodesInHierarchy();
        for (AwardHierarchy node : nodes) {
            nodeMap.put(node.getAwardNumber(), node);
        }
        return nodeMap;
    }

    /**
     * @return
     */
    public String getRootAwardNumber() {
        return rootAwardNumber;
    }

    /**
     * This method dtermines if children are present
     * @return
     */
    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = prime * result + ((parentAwardNumber == null) ? 0 : parentAwardNumber.hashCode());
        result = prime * result + ((rootAwardNumber == null) ? 0 : rootAwardNumber.hashCode());
        return result;
    }

    /**
     * Node is root if the awardNumber === rootAwardNumber
     * @return
     */
    public boolean isRootNode() {
        return awardNumber.equals(rootAwardNumber);
    }

    /**
     * @param awardNumber
     * @return
     */
    public AwardHierarchy findNodeInHierarchy(String awardNumber) {
        AwardHierarchy rootNode = findRootNode(this);
        return findNode(rootNode, awardNumber);
    }

    /**
     * @return
     */
    public String generateNextAwardNumberInSequence() {
        List<AwardHierarchy> list = getFlattenedListOfNodesInHierarchy();
        Set<String> awardNumberSet = new TreeSet<String>();
        for (AwardHierarchy node : list) {
            awardNumberSet.add(node.getAwardNumber());
        }
        List<String> orderedList = new ArrayList<String>(awardNumberSet);
        String maximumAwardNumber = orderedList.get(orderedList.size() - 1);
        String[] parts = maximumAwardNumber.split("-");
        Integer nextVal = Integer.valueOf(parts[1]) + 1;
        return String.format("%s-%05d", parts[0], nextVal);
    }

    /**
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
    }

    /**
     * Method here for future JPA use
     * @param awardHierarchyId
     */
    public void setAwardHierarchyId(Long awardHierarchyId) {
        this.awardHierarchyId = awardHierarchyId;
    }

    /**
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * Sets the children attribute value.
     * @param children The children to set.
     */
    public void setChildren(List<AwardHierarchy> children) {
        this.children = children;
    }

    public void setOriginatingAwardNumber(String originatingDocumentNumber) {
        this.originatingAwardNumber = originatingDocumentNumber;
    }

    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(AwardHierarchy parent) {
        this.parent = parent;
        this.parentAwardNumber = parent != null ? parent.getAwardNumber() : ROOTS_PARENT_AWARD_NUMBER;
    }

    public void setParentAwardNumber(String parentAwardNumber) {
        this.parentAwardNumber = parentAwardNumber;
    }

    /**
     * Sets the root attribute value.
     * @param root The root to set.
     */
    public void setRoot(AwardHierarchy root) {
        this.root = root;
        this.rootAwardNumber = root != null ? root.getAwardNumber() : null;
    }

    public void setRootAwardNumber(String rootAwardNumber) {
        this.rootAwardNumber = rootAwardNumber;
    }

    /**
     * @return Size of hierarchy (# of nodes)
     */
    public int hierarchySize() {
        return getFlattenedListOfNodesInHierarchy().size();
    }

    public boolean isNew() {
        return awardHierarchyId == null;
    }

    public boolean isPersisted() {
        return !isNew();
    }

    void addNodeToFlattenedList(List<AwardHierarchy> list, AwardHierarchy parentNode) {
        list.add(parentNode);
        if (parentNode.hasChildren()) {
            for (AwardHierarchy childNode : parentNode.getChildren()) {
                addNodeToFlattenedList(list, childNode);
            }
        }
    }

    void setBusinessObjectService(BusinessObjectService boService) {
        this.boService = boService;
    }

    void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    BusinessObjectService getBusinessObjectService() {
        if (boService == null) {
            boService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return boService;
    }

    VersionHistoryService getVersionHistoryService() {
        if (versionHistoryService == null) {
            versionHistoryService = KraServiceLocator.getService(VersionHistoryService.class);
        }
        return versionHistoryService;
    }

    AwardHierarchy findNode(AwardHierarchy testNode, String awardNumber) {
        AwardHierarchy foundNode = null;
        if (testNode.getAwardNumber().equals(awardNumber)) {
            foundNode = testNode;
        } else if (testNode.hasChildren()) {
            for (AwardHierarchy node : testNode.getChildren()) {
                foundNode = findNode(node, awardNumber);
                if (foundNode != null) {
                    break;
                }
            }
        }
        return foundNode;
    }

    AwardHierarchy findRootNode() {
        return findRootNode(this);
    }

    private AwardHierarchy findAwardHierarchyMatchingAwardNumber(String searchAwardNumber) {
        Map map = ServiceHelper.getInstance().buildCriteriaMap(new String[] { "awardNumber", "active" }, new Object[] { searchAwardNumber, Boolean.TRUE });
        Collection c = getBusinessObjectService().findMatching(AwardHierarchy.class, map);
        return c.size() == 1 ? (AwardHierarchy) c.iterator().next() : null;
    }

    private AwardHierarchy findRootNode(AwardHierarchy testNode) {
        return testNode.isRootNode() ? testNode : findRootNode(testNode.getParent());
    }

    private void lazyLoadAward() {
        VersionHistory vh = getVersionHistoryService().findActiveVersion(Award.class, awardNumber);
        if (vh != null) {
            award = (Award) vh.getSequenceOwner();
        } else {
            List<VersionHistory> histories = getVersionHistoryService().loadVersionHistory(Award.class, awardNumber);
            award = histories.size() == 1 ? (Award) histories.get(0).getSequenceOwner() : null;
        }
    }

    public AwardHierarchy clone() {
        AwardHierarchy copy = null;
        try {
            copy = (AwardHierarchy) super.clone();
            List<AwardHierarchy> copyChildren = new ArrayList<AwardHierarchy>();
            for (AwardHierarchy child : this.getChildren()) {
                copyChildren.add((AwardHierarchy) child.clone());
            }
            copy.setChildren(copyChildren);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
