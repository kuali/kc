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
package org.kuali.kra.award.printing;

import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.BusinessObjectBase;

public class AwardPrintChangeReport extends BusinessObjectBase {
    
    private Integer awardVersion;
    private Integer transactionId;
    private Boolean requireSignature;

    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    public void refresh() {
        //do nothing
    }

    public Integer getAwardVersion() {
        return awardVersion;
    }

    public void setAwardVersion(Integer awardVersion) {
        this.awardVersion = awardVersion;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getRequireSignature() {
        return requireSignature;
    }

    public void setRequireSignature(Boolean requireSignature) {
        this.requireSignature = requireSignature;
    }
    

}
