/*
 * Copyright 2006-2009 The Kuali Foundation
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
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardHierarchy extends KraPersistableBusinessObjectBase { 
    public static final String ROOTS_PARENT_AWARD_NUMBER = "000000-00000";

    private static final long serialVersionUID = 1L;

    private String rootAwardNumber;
    private String awardNumber;
    private String parentAwardNumber; 
    private AwardHierarchy root; 
    private AwardHierarchy parent;
    
    private transient List<AwardHierarchy> children;
    
    public AwardHierarchy() { 
        children = new ArrayList<AwardHierarchy>();
    }
    
    public AwardHierarchy(AwardHierarchy rootNode, AwardHierarchy parentNode, String awardNumber) {
        setRoot(rootNode);
        setParent(parentNode);
        setAwardNumber(awardNumber);
    }

    public static AwardHierarchy createRootNode(String awardNumber) {
        AwardHierarchy hierarchyRootNode = null;
        if(awardNumber != null) {
            hierarchyRootNode = new AwardHierarchy();
            hierarchyRootNode.setAwardNumber(awardNumber);
            hierarchyRootNode.setRootAwardNumber(awardNumber);
            hierarchyRootNode.setParentAwardNumber(ROOTS_PARENT_AWARD_NUMBER);
        }
        return hierarchyRootNode;
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AwardHierarchy))
            return false;
        AwardHierarchy other = (AwardHierarchy) obj;
        if (awardNumber == null) {
            if (other.awardNumber != null)
                return false;
        }
        else if (!awardNumber.equals(other.awardNumber))
            return false;
        if (parentAwardNumber == null) {
            if (other.parentAwardNumber != null)
                return false;
        }
        else if (!parentAwardNumber.equals(other.parentAwardNumber))
            return false;
        if (rootAwardNumber == null) {
            if (other.rootAwardNumber != null)
                return false;
        }
        else if (!rootAwardNumber.equals(other.rootAwardNumber))
            return false;
        return true;
    } 
    
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

    /**
     * Gets the parent attribute. 
     * @return Returns the parent.
     */
    public AwardHierarchy getParent() {
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
        return root;
    }

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

    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(AwardHierarchy parent) {
        this.parent = parent;
        this.parentAwardNumber = parent != null ? parent.getAwardNumber() : null;
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

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("rootAwardNumber", this.getRootAwardNumber());
        hashMap.put("awardNumber", this.getAwardNumber());
        hashMap.put("parentAwardNumber", this.getParentAwardNumber());
        return hashMap;
    }
    
}