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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class intends to provide basic business service behavior 
 * and accessors for Awards. 

 */
public interface AwardService {
    /**
     * Get the Award based upon its unique id number.
     * 
     * @param awardId the Award's unique id number
     * @return the Award or null if not found.
     * 
     * @deprecated The identifier for Award is a Long, but this method expects a String
     */
    @Deprecated
    public Award getAward(String awardId);

    /**
     * Get the Award based upon its unique id number.
     * 
     * @param awardId
     * @return
     */
    public Award getAward(Long awardId);
    
    /**
     * This method finds all Awards for the specified awardNumber
     * @param awardNumber
     * @return The list of Awards
     */
    public List<Award> findAwardsForAwardNumber(String awardNumber);
    
    /**
     * Create new version of the award document
     * @param awardDocument
     * @return
     * @throws VersionExceptionInstitutionalProposalServ
     */
    public AwardDocument createNewAwardVersion(AwardDocument awardDocument) throws VersionException, WorkflowException;
    
    /**
     * Update the award to use the new VersionStatus. If the version status is ACTIVE, any other active version of this
     * award will be set to ARCHIVED.
     * @param award
     * @param status
     */
    void updateAwardSequenceStatus(Award award, VersionStatus status);
    
    /**
     * Returns the active award or if none exist, the newest non-cancelled award.
     * @param awardNumber
     * @return
     */
    Award getActiveOrNewestAward(String awardNumber);
    
    
    /**
     * This method is to synch custom attributes. During copy process only existing custom attributes
     * available in the old document is copied. We need to make sure we have all the latest custom attributes
     * tied to the new document.
     * @param newAward
     * @param oldAward
     */
    public void synchNewCustomAttributes(Award newAward, Award oldAward);
    
    public Award getAwardAssociatedWithDocument(String docNumber);

    /**
     * Get the Award Number for an Awrad based upon its unique id number.
     *
     * @param awardId
     * @return
     */
    public String getAwardNumber(Long awardId);

    /**
     * Retrieves awards by a given Map of criteria
     * @param fieldValues a Map of criteria to find matching awards for
     * @return a result-limited Collection of matching awards
     */
    public Collection<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues);
}
