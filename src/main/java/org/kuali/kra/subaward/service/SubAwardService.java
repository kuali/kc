/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.sql.Date;

import javax.servlet.http.HttpServletResponse;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

public interface SubAwardService {
    
    
    /**
     * Create new version of the Subaward document
     * @param subAwardDocument
     * @return
     * @throws VersionException
     */
    public SubAwardDocument createNewSubAwardVersion(SubAwardDocument subAwardDocument) throws VersionException, WorkflowException;
    
    /**
     * Update the subaward to use the new VersionStatus. If the version status is ACTIVE, any other active version of this
     * subAward will be set to ARCHIVED.
     * @param subAward
     * @param status
     */
    public void updateSubAwardSequenceStatus(SubAward subAward, VersionStatus status);
    
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

    /**
     * This method will downloadAttachment  to subaward.
     * @param subAward
     * @return
     */
     public void downloadAttachment(KraPersistableBusinessObjectBase attachmentDataSource, HttpServletResponse response) throws Exception;
     
     /**
      * 
      * This method returns the value of the parameter 'Subaward Follow Up'.
      * @return
      */
     public String getFollowupDateDefaultLength();
     
     /**
      * 
      * This method calculates a follow date based on getFollowupDateDefaultLength and the passed in baseDate.
      * @param baseDate
      * @return
      */
     public Date getCalculatedFollowupDate(Date baseDate);


}
