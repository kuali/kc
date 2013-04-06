/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.service;

import java.sql.Date;

import org.kuali.kra.common.committee.bo.CommitteeBatchCorrespondenceBase;

/**
 * 
 * This class generates the batch correspondence of committees.
 */
public interface CommitteeBatchCorrespondenceServiceBase {
    
    /**
     * 
     * This method generates the batch correspondence of a committee.
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     * @return CommitteeBatchCorrespondenceBase
     * @throws Exception 
     */
    CommitteeBatchCorrespondenceBase generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, Date endDate) throws Exception;

}
