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
package org.kuali.coeus.common.committee.impl.dao;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;

import java.sql.Date;
import java.util.List;

/**
 * 
 * This class provides enhanced database access functionality.
 */
public interface CommitteeBatchCorrespondenceDao<CBC extends CommitteeBatchCorrespondenceBase> {
    
    /**
     * This method returns all CommitteeBatchCorrespondenceBase of the specified type.  Optionally a date range may be specified to further
     * narrow the result set.
     * @param batchCorrespondenceTypeCode
     * @param startDate - optional, if specified the CommitteeBatchCorrespondenceBase must be created on or after this date.
     * @param endDate - optional, if specified the CommitteeBatchCorrespondenceBase must be created on or before this date.
     * @return List of the requested CommitteeBatchCorrespondenceBase
     */
    List<CBC> getCommitteeBatchCorrespondence(String batchCorrespondenceTypeCode, Date startDate, Date endDate);

}
