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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;

@SuppressWarnings("serial")
public abstract class AwardReportTermRecipientBase extends AwardSponsorContactBase {

    private Integer numberOfCopies; 

    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getSyncBaseClass()
     */
    public Class getSyncBaseClass() {
        return AwardReportTermRecipientBase.class;
    }
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getAwardSyncClass()
     */
    public Class getAwardSyncClass() {
        return AwardReportTermRecipient.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("numberOfCopies", getNumberOfCopies());
        return hashMap;
    }

    /**
     * Gets the numberOfCopies attribute. 
     * @return Returns the numberOfCopies.
     */
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     * Sets the numberOfCopies attribute value.
     * @param numberOfCopies The numberOfCopies to set.
     */
    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

}
