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
