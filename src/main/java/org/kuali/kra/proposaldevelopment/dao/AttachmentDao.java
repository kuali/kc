/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.dao;

import java.util.Iterator;

public interface AttachmentDao {
    /**
     * 
     * This method set up the timestamp and upload user for personnel attachment
     * @param proposalPersonNumber
     * @param proposalNumber
     * @param biographyNumber
     * @return
     */
    public Iterator getPersonnelTimeStampAndUploadUser(Integer proposalPersonNumber, String proposalNumber, Integer biographyNumber);
    
    /**
     * 
     * This methodset up the timestamp and upload user for narrative & internal attachments
     * @param moduleNumber
     * @param proposalNumber
     * @return
     */
    public Iterator getNarrativeTimeStampAndUploadUser(Integer moduleNumber, String proposalNumber);

}
