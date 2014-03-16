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
package org.kuali.kra.award.service;

import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.award.home.ValidBasisMethodPayment;

import java.util.List;


public interface AwardPaymentAndInvoicesService {
    /**
     * This method returns the ValidAwardBasisPayment objects associated with the given awardTypeCode
     * @param awardTypeCode the awardTypeCode that will be used in the lookup of ValidAwardBasisPayments.
     * @return List of ValidAwardBasisPayment records associated with the given awardTypeCode
     */
    public List<ValidAwardBasisPayment> getValidAwardBasisPaymentsByAwardTypeCode( Integer awardTypeCode );

    /**
     * 
     * @param basisOfPaymentCode The basisOfPayment code
     * @return List of ValidBasisMethodPayment codes that have basisOfPaymentCode equal to the given parameter.
     */
    public List<ValidBasisMethodPayment> getValidBasisMethodPaymentByBasisCode( String basisOfPaymentCode );
    
    public ValidAwardBasisPayment getValidAwardBasisPayment(Integer validAwardBasisPaymentId);
    
    /**
     * Return a delimited string representation of the getValidAwardBasisPaymentsByAwardTypeCode method.
     * @param awardTypeCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getEncodedValidAwardBasisPaymentsByAwardTypeCode(Integer awardTypeCode);


    /**
     * Return a delimited string representation of the output from getValidBasisMethodPayment method.
     * @param basisOfPaymentCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getEncodedValidBasisMethodPaymentsByBasisCode( String basisOfPaymentCode);
}
