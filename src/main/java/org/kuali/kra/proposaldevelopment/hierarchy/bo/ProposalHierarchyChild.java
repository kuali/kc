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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;

public class ProposalHierarchyChild extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6265373411837495670L;

    private String proposalNumber;
    private String hierarchyProposalNumber;
    private int proposalHashCode;
    private List<PropScienceKeyword> propScienceKeywords;
    private List<ProposalPerson> proposalPersons;
    private List<ProposalSpecialReview> propSpecialReviews;
    private List<Narrative> narratives;

    public ProposalHierarchyChild() {
        propScienceKeywords = new ArrayList<PropScienceKeyword>();
        proposalPersons = new ArrayList<ProposalPerson>();
        propSpecialReviews = new ArrayList<ProposalSpecialReview>();
        narratives = new ArrayList<Narrative>();
    }
    
    /**
     * Gets the proposalNumber attribute.
     * 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }


    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }


    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }


    /**
     * Sets the proposalNumber attribute value.
     * 
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    

    /**
     * Gets the proposalHashCode attribute. 
     * @return Returns the proposalHashCode.
     */
    public int getProposalHashCode() {
        return proposalHashCode;
    }


    /**
     * Sets the proposalHashCode attribute value.
     * @param proposalHashCode The proposalHashCode to set.
     */
    public void setProposalHashCode(int proposalHashCode) {
        this.proposalHashCode = proposalHashCode;
    }

    /**
     * Gets the propScienceKeywords attribute.
     * 
     * @return Returns the propScienceKeywords.
     */
    public List<PropScienceKeyword> getPropScienceKeywords() {
        return propScienceKeywords;
    }


    /**
     * Sets the propScienceKeywords attribute value.
     * 
     * @param propScienceKeywords The propScienceKeywords to set.
     */
    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
        this.propScienceKeywords = propScienceKeywords;
    }


    /**
     * Gets the proposalPersons attribute.
     * 
     * @return Returns the proposalPersons.
     */
    public List<ProposalPerson> getProposalPersons() {
        return proposalPersons;
    }


    /**
     * Sets the proposalPersons attribute value.
     * 
     * @param proposalPersons The proposalPersons to set.
     */
    public void setProposalPersons(List<ProposalPerson> proposalPersons) {
        this.proposalPersons = proposalPersons;
    }


    /**
     * Gets the propSpecialReviews attribute.
     * 
     * @return Returns the propSpecialReviews.
     */
    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }


    /**
     * Sets the propSpecialReviews attribute value.
     * 
     * @param propSpecialReviews The propSpecialReviews to set.
     */
    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }


    /**
     * Gets the narratives attribute.
     * 
     * @return Returns the narratives.
     */
    public List<Narrative> getNarratives() {
        return narratives;
    }


    /**
     * Sets the narratives attribute value.
     * 
     * @param narratives The narratives to set.
     */
    public void setNarratives(List<Narrative> narratives) {
        this.narratives = narratives;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @see org.kuali.rice.kns.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedList = super.buildListOfDeletionAwareLists();
        managedList.add(getNarratives());
        managedList.add(getProposalPersons());
        managedList.add(getPropScienceKeywords());
        managedList.add(getPropSpecialReviews());
        return managedList;
    }

    
}
