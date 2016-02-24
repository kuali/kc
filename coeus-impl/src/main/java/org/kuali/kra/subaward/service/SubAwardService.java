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
package org.kuali.kra.subaward.service;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.sql.Date;
import java.util.List;

/**
 * This class represents SubAwardService...
 */
public interface SubAwardService {

    /**.
     * Create new version of the Subaward document
     * @param subAwardDocument
     * @return
     * @throws VersionException
     */
    public SubAwardDocument createNewSubAwardVersion(
    SubAwardDocument subAwardDocument) throws VersionException,
    WorkflowException;

    /**.
     * Update the subaward to use the new VersionStatus.
     *  If the version status is ACTIVE, any other
     *  active version of this
     * subAward will be set to ARCHIVED.
     * @param subAward
     * @param status
     */
    public void updateSubAwardSequenceStatus(
    SubAward subAward, VersionStatus status);

    /**
     * This method returns an unused SubAwardCode.
     * @return
     */
    String getNextSubAwardCode();

    /**
     * This method will add AmountInfo details to subaward.
     * @param subAward
     * @return
     */
    public SubAward getAmountInfo(SubAward subAward);

     /**.
      * 
      * This method returns the value of the parameter 'Subaward Follow Up'.
      * @return
      */
     public String getFollowupDateDefaultLength();

     /**
      * 
      * This method calculates a follow date based on
      * getFollowupDateDefaultLength and the passed in baseDate.
      * @param baseDate
      * @return
      */
     public Date getCalculatedFollowupDate(Date baseDate);
     
     /**
      * 
      * This method returns a formatted Date string based on the base date.
      * @param baseDate
      * @return
      */
     public String getCalculatedFollowupDateForAjaxCall(String baseDate);

     /**
      *
      * This method calls getFollowupDateDefaultLength
      *  translates the value into days or weeks,
      *   and then returns the value in days.
      * @return
      */
     public int getFollowupDateDefaultLengthInDays();
     
     SubAward getActiveSubAward(Long subAwardCode);

     List<SubAward> getLinkedSubAwards(Award award);
}
