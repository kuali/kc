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
package org.kuali.kra.award.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.bo.ScienceKeyword;

/**
 * 
 * This class represents the Award Document Object.
 * AwardDocument has a 1:1 relationship with Award Business Object.
 * We have declared a list of Award BOs in the AwardDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Award and AwardDocument can have a 1:1 relationship.
 */
public class AwardDocument extends ResearchDocumentBase implements Copyable, SessionDocument{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1668673531338660064L;
    private static final String ONE = "1";
    
    private String awardNumber;
    private List<Award> awardList;
    
    private String newKeyword;
    private List<ScienceKeyword> keywords;
    
    /**
     * 
     * Constructs a AwardDocument object
     */
    public AwardDocument(){        
        super();
        setAwardNumber(ONE);
        awardList = new ArrayList<Award>();
        Award newAward = new Award();
        awardList.add(newAward);
        keywords = new ArrayList<ScienceKeyword>();
    }
    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     * @return
     */
    public Award getAward() {
        return awardList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     * @param award
     */
    public void setAward(Award award) {
        awardList.set(0, award);
    }

    /**
     * 
     */
    public String getAwardNumber() {
        return awardNumber;
    }

   /**
    * 
    * @param awardNumber
    */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     *
     * @return
     */
    public List<Award> getAwardList() {
        return awardList;
    }

    /**
     *
     * @param awardList
     */
    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }
    
    /**
     * 
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();       
                
        managedLists.add(getAward().getAwardFandaRate());
        managedLists.add(awardList);
        return managedLists;
    }

    /**
     * Gets the newKeyword attribute. 
     * @return Returns the newKeyword.
     */
    public String getNewKeyword() {
        return newKeyword;
    }

    /**
     * Sets the newKeyword attribute value.
     * @param newKeyword The newKeyword to set.
     */
    public void setNewKeyword(String newKeyword) {
        this.newKeyword = newKeyword;
    }

    /**
     * Gets the keywords attribute. 
     * @return Returns the keywords.
     */
    public List<ScienceKeyword> getKeywords() {
        return keywords;
    }

    /**
     * Sets the keywords attribute value.
     * @param keywords The keywords to set.
     */
    public void setKeywords(List<ScienceKeyword> keywords) {
        this.keywords = keywords;
    }
}
