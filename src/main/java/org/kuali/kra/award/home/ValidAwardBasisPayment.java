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
package org.kuali.kra.award.home;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidAwardBasisPayment extends KraPersistableBusinessObjectBase {

    private Integer validAwardBasisPaymentId;

    private String basisOfPaymentCode;

    private Integer awardTypeCode;

    private AwardBasisOfPayment basisOfPayment;

    private AwardType awardType;

    public ValidAwardBasisPayment() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getValidAwardBasisPaymentId() {
        return validAwardBasisPaymentId;
    }

    public void setValidAwardBasisPaymentId(Integer validAwardBasisPaymentId) {
        this.validAwardBasisPaymentId = validAwardBasisPaymentId;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    public AwardBasisOfPayment getBasisOfPayment() {
        return basisOfPayment;
    }

    public void setBasisOfPayment(AwardBasisOfPayment basisOfPayment) {
        this.basisOfPayment = basisOfPayment;
    }

    public AwardType getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }
}
