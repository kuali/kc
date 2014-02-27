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
package org.kuali.kra.irb.correspondence;

import java.util.List;

public interface BatchCorrespondenceDetailService {

    /**
     * 
     * This method add a BatchCorrespondenceDetail to the BatchCorrespondence.
     * @param batchCorrespondence
     * @param newBatchCorrespondenceDetail
     */
    void addBatchCorrespondenceDetail(BatchCorrespondence batchCorrespondence,
            BatchCorrespondenceDetail newBatchCorrespondenceDetail);

    /**
     * 
     * This method saves the BatchCorrespondenceDetais of the BatchCorrespondence.
     * @param batchCorrespondence
     * @param deletedBos
     */
    void saveBatchCorrespondenceDetails(BatchCorrespondence batchCorrespondence, 
            List<BatchCorrespondenceDetail> deletedBos);
}
