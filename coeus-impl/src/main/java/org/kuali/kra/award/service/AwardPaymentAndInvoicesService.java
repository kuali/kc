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
