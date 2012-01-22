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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * Values Finder for Invoice Instructions Indicator Values.
 * 
 */
public class ValidAwardBasisPaymentValueFinder extends KeyValuesBase {
    
    private transient AwardPaymentAndInvoicesService awardPaymentInfoService;
    
    private AwardPaymentAndInvoicesService getAwardPaymentAndInvoicesService() {
        if( awardPaymentInfoService == null ) {
            awardPaymentInfoService = KraServiceLocator.getService(AwardPaymentAndInvoicesService.class);
        }
        return awardPaymentInfoService;
    }

    @SuppressWarnings("all")
    public List getKeyValues() {
       List<KeyValue> keyLabels = new ArrayList<KeyValue>();
       AwardForm awardForm = (AwardForm)KNSGlobalVariables.getKualiForm();
       keyLabels.add( new ConcreteKeyValue( "", "select" ));
       if( awardForm.getAwardDocument() != null && awardForm.getAwardDocument().getAward() != null
               && awardForm.getAwardDocument().getAward() != null && awardForm.getAwardDocument().getAward().getAwardTypeCode() != null ) { 
         
           List<ValidAwardBasisPayment> results =  getAwardPaymentAndInvoicesService().getValidAwardBasisPaymentsByAwardTypeCode(awardForm.getAwardDocument().getAward().getAwardTypeCode());
           for( ValidAwardBasisPayment awardBasisPayment : results ) {
               awardBasisPayment.refresh();
               keyLabels.add( new ConcreteKeyValue( awardBasisPayment.getBasisOfPaymentCode(), awardBasisPayment.getBasisOfPayment().getDescription()));
           }
       }
       return keyLabels;
    }
    
    
}
