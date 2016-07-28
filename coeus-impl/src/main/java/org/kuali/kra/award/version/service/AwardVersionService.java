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
package org.kuali.kra.award.version.service;

import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;

/**
 * The Award Version Service
 */
public interface AwardVersionService {
    
    /**
     * This method returns the proper Award for displaying information in T&amp;M and Award documents.  Logic for canceled documents.
     * @param awardNumber
     * @return
     */
    public Award getWorkingAwardVersion(String awardNumber);

    public Award getActiveAwardVersion(String awardNumber);

    public Award getPendingAwardVersion(String awardNumber);
    
    public List<Award> getAllActiveAwardsForHierarchy(String awardNumber);
    
    public boolean isPendingAwardInAwardHierarchy(String awardNumber);
    
    public boolean isActiveAwardInAwardHierarchy(String awardNumber);

    AwardDocument createAndSaveNewAwardVersion(AwardDocument awardDocument) throws Exception;


    }
