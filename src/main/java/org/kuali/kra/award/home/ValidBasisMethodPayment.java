/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.home;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidBasisMethodPayment extends KraPersistableBusinessObjectBase {

    private static final Log LOG = LogFactory.getLog(ValidBasisMethodPayment.class);

    private static final long serialVersionUID = 1L;

    private Integer validBasisMethodPaymentId;

    private String basisOfPaymentCode;

    private String methodOfPaymentCode;

    private String invInstructionsIndicator;

    private AwardMethodOfPayment methodOfPayment;

    private AwardBasisOfPayment basisOfPayment;

    public ValidBasisMethodPayment() {
    }

    public Integer getValidBasisMethodPaymentId() {
        return validBasisMethodPaymentId;
    }

    public void setValidBasisMethodPaymentId(Integer validBasisMethodPaymentId) {
        this.validBasisMethodPaymentId = validBasisMethodPaymentId;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    public String getInvInstructionsIndicator() {
        return invInstructionsIndicator;
    }

    public InvInstructionsIndicatorConstants getInvInstructionsIndicatorConstant() {
        InvInstructionsIndicatorConstants result = InvInstructionsIndicatorConstants.getByCode(invInstructionsIndicator);
        if (result == null && invInstructionsIndicator != null) LOG.warn(String.format("ValidBasisMethodPayment with id = %s has invalid InvInstructionsIndicator value of %s", getValidBasisMethodPaymentId(), getInvInstructionsIndicator()));
        return result;
    }

    public void setInvInstructionsIndicator(String invInstructionsIndicator) {
        this.invInstructionsIndicator = invInstructionsIndicator;
    }

    public String getInvInstructionsIndicatorName() {
        InvInstructionsIndicatorConstants result = InvInstructionsIndicatorConstants.getByCode(invInstructionsIndicator);
        if (result == null && invInstructionsIndicator != null) {
            LOG.warn(String.format("ValidBasisMethodPayment with id = %s has invalid InvInstructionsIndicator value of %s", getValidBasisMethodPaymentId(), getInvInstructionsIndicator()));
            return null;
        }
        return result.name();
    }

    public AwardMethodOfPayment getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(AwardMethodOfPayment methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public AwardBasisOfPayment getBasisOfPayment() {
        return basisOfPayment;
    }

    public void setBasisOfPayment(AwardBasisOfPayment basisOfPayment) {
        this.basisOfPayment = basisOfPayment;
    }
}
