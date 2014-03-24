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