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
package org.kuali.kra.award;

import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;

public interface AwardAmountInfoService {
    
    
    AwardAmountInfo fetchLastAwardAmountInfoForAwardVersionAndFinalizedTandMDocumentNumber(Award award);
    /**
     * 
     * This method should return the awardAmountInfo object having highest transaction id.
     * @param awardAmountInfos
     * @return
     */
    AwardAmountInfo fetchAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos);
    
    AwardAmountInfo fetchLastAwardAmountInfoForDocNum(Award award, String docNum);
    
    /**
     * This method...
     * @param awardAmountInfos
     * @return
     */
    int fetchIndexOfAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos);
}
