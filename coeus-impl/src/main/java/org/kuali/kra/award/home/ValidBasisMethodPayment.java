/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.home;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class ValidBasisMethodPayment extends KcPersistableBusinessObjectBase {

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
