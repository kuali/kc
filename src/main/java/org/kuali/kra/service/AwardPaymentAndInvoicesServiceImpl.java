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
package org.kuali.kra.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;


public class AwardPaymentAndInvoicesServiceImpl implements AwardPaymentAndInvoicesService {
    
    static final String AWARDTYPECODE = "awardTypeCode";
    static final String VALIDAWARDBASISPAYMENT_ID = "validAwardBasisPaymentId";
    
    static final String BASISOFPAYMENTCODE = "basisOfPaymentCode";
    static final String METHODOFPAYMENTCODE = "methodOfPaymentCode";
    
    private static final String SEMICOLON_AS_DELIMITOR = ";";
    private static final String COMMA_AS_DELIMITOR = ",";
    
    private static final String AWARDBASISOFPAYMENT_CODE = "basisOfPaymentCode"; 
    private static final String AWARDMETHODOFPAYMENT_CODE = "methodOfPaymentCode";
    
    BusinessObjectService businessObjectService;
  
    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getEncodedValidAwardBasisPaymentsByAwardTypeCode(java.lang.Integer)
     */
    public String getEncodedValidAwardBasisPaymentsByAwardTypeCode(Integer awardTypeCode) {
        List<KeyValue> results = new ArrayList<KeyValue>();
        results.add(new ConcreteKeyValue("","select"));
        List<ValidAwardBasisPayment> found = getValidAwardBasisPaymentsByAwardTypeCode(awardTypeCode);
        for( ValidAwardBasisPayment current : found ) {
            current.refresh();
            results.add(new ConcreteKeyValue( current.getBasisOfPaymentCode(), current.getBasisOfPayment().getDescription() ));
        }
        
        return processKeyValueList(results);
    }

    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getEncodedValidBasisMethodPaymentsByBasisCode(java.lang.String)
     */
    public String getEncodedValidBasisMethodPaymentsByBasisCode(String basisOfPaymentCode) {
        List<KeyValue> results = new ArrayList<KeyValue>();
        results.add(new ConcreteKeyValue("","select"));
        List<ValidBasisMethodPayment> found = getValidBasisMethodPaymentByBasisCode(basisOfPaymentCode);
        for( ValidBasisMethodPayment current : found ) {
            current.refresh();
            results.add(new ConcreteKeyValue( current.getMethodOfPaymentCode(), current.getMethodOfPayment().getDescription() ));
        }
        return processKeyValueList(results);
    }
    

    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getValidAwardBasisPaymentsByAwardTypeCode(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<ValidAwardBasisPayment> getValidAwardBasisPaymentsByAwardTypeCode(Integer awardTypeCode) {
        List<ValidAwardBasisPayment> results = new ArrayList<ValidAwardBasisPayment>( businessObjectService.findMatchingOrderBy(ValidAwardBasisPayment.class,
                ServiceHelper.getInstance().buildCriteriaMap(AWARDTYPECODE, awardTypeCode),
                VALIDAWARDBASISPAYMENT_ID, 
                true ));
        return results;
    }

    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getValidBasisMethodPaymentByBasisCode(java.lang.String)
     */
    public List<ValidBasisMethodPayment> getValidBasisMethodPaymentByBasisCode(String basisOfPaymentCode) {
        
        List<ValidBasisMethodPayment> results = new ArrayList<ValidBasisMethodPayment>( businessObjectService.findMatchingOrderBy(ValidBasisMethodPayment.class,
                ServiceHelper.getInstance().buildCriteriaMap( new String[] {BASISOFPAYMENTCODE},  new String[]{basisOfPaymentCode} ),
                METHODOFPAYMENTCODE, 
                true ));
        return results;
    }
    
    
    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getValidBasisMethodPaymentByMethodCode(java.lang.String)
     */
    public List<ValidBasisMethodPayment> getValidBasisMethodPaymentByMethodCode(String methodOfPaymentCode) {
        List<ValidBasisMethodPayment> results = new ArrayList<ValidBasisMethodPayment>( businessObjectService.findMatchingOrderBy(ValidBasisMethodPayment.class,
                ServiceHelper.getInstance().buildCriteriaMap( METHODOFPAYMENTCODE, methodOfPaymentCode ),
                BASISOFPAYMENTCODE, 
                true ));
        return results;
    }
    
    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getValidAwardBasisPayment(java.lang.Integer)
     */
    public ValidAwardBasisPayment getValidAwardBasisPayment( Integer validAwardBasisPaymentId ) {
        ValidAwardBasisPayment vBasisPayment = (ValidAwardBasisPayment)businessObjectService.findByPrimaryKey(ValidAwardBasisPayment.class, ServiceHelper.getInstance().buildCriteriaMap(VALIDAWARDBASISPAYMENT_ID, validAwardBasisPaymentId));
        return vBasisPayment;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    
    /**
     * 
     * This method processes a list of KeyValue and converts them to a string separated
     * by semi-colons and comas.
     * This is used in both getFrequencyCodes and getFrequencyBaseCodes services.
     *  
     * @param KeyValueList
     * @return
     */
    protected String processKeyValueList(List<KeyValue> KeyValueList){
        
        StringBuilder strBuilder = new StringBuilder();
        
        int lastElementIndex = KeyValueList.size()-1;
        
        for(int i = 0; i < lastElementIndex; i++){
            strBuilder.append(KeyValueList.get(i).getKey());
            strBuilder.append(SEMICOLON_AS_DELIMITOR);
            strBuilder.append(KeyValueList.get(i).getValue());
            strBuilder.append(COMMA_AS_DELIMITOR);
        }
        
        strBuilder.append(KeyValueList.get(lastElementIndex).getKey());
        strBuilder.append(SEMICOLON_AS_DELIMITOR);
        strBuilder.append(KeyValueList.get(lastElementIndex).getValue());
        
        return strBuilder.toString();
    }

    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getAwardBasisOfPaymentDescription(java.lang.String)
     */
    public String getAwardBasisOfPaymentDescription(String awardBasisOfPaymentId) {
        AwardBasisOfPayment basisOfPayment = (AwardBasisOfPayment)businessObjectService.findByPrimaryKey(AwardBasisOfPayment.class, ServiceHelper.getInstance().buildCriteriaMap(AWARDBASISOFPAYMENT_CODE, awardBasisOfPaymentId));
        return basisOfPayment!=null?basisOfPayment.getDescription():"";
    }

    /**
     * @see org.kuali.kra.service.AwardPaymentAndInvoicesService#getAwardMethodOfPaymentDescription(java.lang.String)
     */
    public String getAwardMethodOfPaymentDescription(String awardMethodOfPaymentId) {
        AwardMethodOfPayment awardMethodOfPayment = (AwardMethodOfPayment)businessObjectService.findByPrimaryKey(AwardMethodOfPayment.class, ServiceHelper.getInstance().buildCriteriaMap(AWARDMETHODOFPAYMENT_CODE, awardMethodOfPaymentId));
        return awardMethodOfPayment!=null?awardMethodOfPayment.getDescription():"";
    }
  
}
