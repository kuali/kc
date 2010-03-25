/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.kuali.kra.award.home.AwardAmountInfo;

public class AwardAmountInfoServiceImpl implements AwardAmountInfoService {

    /**
     * 
     * @see org.kuali.kra.award.AwardAmountInfoService#fetchAwardAmountInfoWithHighestTransactionId(java.util.List)
     */
    public AwardAmountInfo fetchAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos) {
        
        AwardAmountInfo awardAmountInfo = null;
        for(AwardAmountInfo aai : awardAmountInfos){
            if(awardAmountInfo == null){
                awardAmountInfo = aai;
            }else if(awardAmountInfo.getAwardAmountInfoId() == null && aai.getAwardAmountInfoId()!=null){
                awardAmountInfo = aai;
            }else if(awardAmountInfo.getAwardAmountInfoId()!=null && aai.getAwardAmountInfoId()!=null && awardAmountInfo.getAwardAmountInfoId() < aai.getAwardAmountInfoId()){
                awardAmountInfo = aai;
            }
        }
        return awardAmountInfo;
    }
    
    /**
     * 
     * @see org.kuali.kra.award.AwardAmountInfoService#fetchIndexOfAwardAmountInfoWithHighestTransactionId(java.util.List)
     */
    public int fetchIndexOfAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos) {
        AwardAmountInfo awardAmountInfo = null;
        int returnVal = 0;
        int index = 0;
        for(AwardAmountInfo aai : awardAmountInfos){
            if(awardAmountInfo == null){
                awardAmountInfo = aai;
                returnVal = index;
                index++;
            }else if(awardAmountInfo.getAwardAmountInfoId() == null && aai.getAwardAmountInfoId()!=null){
                awardAmountInfo = aai;
                returnVal = index;
                index++;
            }else if(awardAmountInfo.getAwardAmountInfoId()!=null && aai.getAwardAmountInfoId()!=null && awardAmountInfo.getAwardAmountInfoId() < aai.getAwardAmountInfoId()){
                awardAmountInfo = aai;
                returnVal = index;
                index++;
            }else {
                index++;
            }
        }
        return returnVal;
    }
       

}
