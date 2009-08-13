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
package org.kuali.kra.proposaldevelopment.hierarchy.bo;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;

/**
 * This class...
 */
public class ProposalHierarchy extends DevelopmentProposal {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8162683517589540746L;

    // the member fields below are items that are kept locally to the hierarchy as opposed to the aggregated hierarchy
    private List<ProposalHierarchyChild> children;
    private List<PropScienceKeyword> hierarchyPropScienceKeywords;
    private List<ProposalSpecialReview> hierarchySpecialReviews;
    private List<Narrative> hierarchyNarratives;
        
    /**
     * Gets the children attribute. 
     * @return Returns the children.
     */
    public List<ProposalHierarchyChild> getChildren() {
        return children;
    }
    /**
     * Sets the children attribute value.
     * @param children The children to set.
     */
    public void setChildren(List<ProposalHierarchyChild> children) {
        this.children = children;
    }
        
    /**
     * Gets the hierarchyPropScienceKeywords attribute. 
     * @return Returns the hierarchyPropScienceKeywords.
     */
    public List<PropScienceKeyword> getHierarchyPropScienceKeywords() {
        return hierarchyPropScienceKeywords;
    }
    
    /**
     * Sets the hierarchyPropScienceKeywords attribute value.
     * @param hierarchyPropScienceKeywords The hierarchyPropScienceKeywords to set.
     */
    public void setHierarchyPropScienceKeywords(List<PropScienceKeyword> hierarchyPropScienceKeywords) {
        this.hierarchyPropScienceKeywords = hierarchyPropScienceKeywords;
    }
    
    /**
     * Gets the hierarchySpecialReviews attribute. 
     * @return Returns the hierarchySpecialReviews.
     */
    public List<ProposalSpecialReview> getHierarchySpecialReviews() {
        return hierarchySpecialReviews;
    }
    /**
     * Sets the hierarchySpecialReviews attribute value.
     * @param hierarchySpecialReviews The hierarchySpecialReviews to set.
     */
    public void setHierarchySpecialReviews(List<ProposalSpecialReview> hierarchySpecialReviews) {
        this.hierarchySpecialReviews = hierarchySpecialReviews;
    }
    /**
     * Gets the hierarchyNarratives attribute. 
     * @return Returns the hierarchyNarratives.
     */
    public List<Narrative> getHierarchyNarratives() {
        return hierarchyNarratives;
    }
    /**
     * Sets the hierarchyNarratives attribute value.
     * @param hierarchyNarratives The hierarchyNarratives to set.
     */
    public void setHierarchyNarratives(List<Narrative> hierarchyNarratives) {
        this.hierarchyNarratives = hierarchyNarratives;
    }
    

    /**
     * @see org.kuali.rice.kns.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedList = super.buildListOfDeletionAwareLists();
        managedList.add(getChildren());
        managedList.add(getHierarchyNarratives());
        managedList.add(getHierarchyPropScienceKeywords());
        managedList.add(getHierarchySpecialReviews());
        return managedList;
    }

    
}
