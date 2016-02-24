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
package org.kuali.kra.award;

import org.kuali.kra.award.document.AwardDocument;

/**
 * This class is used for sync AwardSponsorTemplate details to different Award Details Objects.
 */
public interface AwardTemplateSyncService {

    /**
     * 
     * This method is to sync a particular syncable list from award template
     * @param award
     * @param syncPropertyName is the name of member variable to represent syncable list
     * @return true if successful
     */
    public boolean syncAwardToTemplate(AwardDocument awardDocument, AwardTemplateSyncScope[] scopes);
    
    /**
     * Method checks if a scope sync will alter existing data within the award when called with a specific scope.
     * 
     * @param awardDocument The award document to check.
     * @param scope The scope to check.
     * @return true if existing data will be lost, false otherwise.
     * 
     */
    public boolean syncWillAlterData( AwardDocument awardDocument, AwardTemplateSyncScope scope );
 
    /**
     * Method returns true if the award's award template contains data that would
     * be synchronized in the provided scope.
     * 
     * @param awardDocument The award document to check.
     * @param scope The scope to check.
     * @return true if the template contains data that would be synchronized.
     * 
     */
    public boolean templateContainsScopedData( AwardDocument awardDocument, AwardTemplateSyncScope scope );
 
    
    
    
    
}
