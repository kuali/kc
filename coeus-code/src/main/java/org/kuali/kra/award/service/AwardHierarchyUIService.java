/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.service;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;

import java.text.ParseException;

/**
 * 
 * This interface declares services that would populate the Award Hierarchy in UI.
 */
public interface AwardHierarchyUIService {
   
    
    /**
     * 
     * This service is used to populate the root node of the award hierarchy UI.
     *  
     * @param awardNumber
     * @return
     * @throws ParseException
     */
    public String getRootAwardNode(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException;
    
    /**
     * 
     * This service is used to populate the all the non-root nodes of award hierarchy UI.
     * 
     * @return
     */
    public String getSubAwardHierarchiesForTreeView(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException;
    
    /**
     * 
     * This service is used to populate the all the non-root nodes of award hierarchy UI.
     * 
     * @return
     */
    public String getSubAwardHierarchiesForTreeViewTandM(String awardNumber, String currentAwardNumber, String currentSequenceNumber) throws ParseException;
    
    /**
     * 
     * This method retrieves the award details in a string form for a single award.
     * 
     * This will be used in AwardInquirable to show the custom inquiry results for Award.
     * 
     * @param award
     * @return
     * @throws ParseException
     */
    public String getAwardRecord(Award award) throws ParseException;
    

    /**
     * This method returns the root award node of the award
     * @param award
     * @return
     * @throws ParseException
     */
    public AwardHierarchyNode getRootAwardNode(Award award) throws ParseException;
    
}
