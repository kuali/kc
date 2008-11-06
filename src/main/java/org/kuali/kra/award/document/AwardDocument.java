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

import java.util.List;

import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * 
 * This class represents the Award Document Object.
 */
public class AwardDocument extends ResearchDocumentBase implements Copyable, SessionDocument{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1668673531338660064L;
    private String awardNumber;
    private Award award;
    //private List<Award> awardList;
    
    /**
     * 
     * Constructs a AwardDocument object
     */
    public AwardDocument(){        
        super();
        setAwardNumber("1");
        //awardList = new ArrayList<Award>();
        //Award newAward = new Award();
        //newAward.setDocumentNumber(this.documentNumber);
        //awardList.add(newAward);
        award = new Award();        
    }
    
    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
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
    /*public List<Award> getAwardList() {
        return awardList;
    }*/

    /**
     *
     * @param awardList
     */
    /*public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }*/
    
    /**
     * 
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();       
                
        //managedLists.add(awardList);
        return managedLists;
    }
}
