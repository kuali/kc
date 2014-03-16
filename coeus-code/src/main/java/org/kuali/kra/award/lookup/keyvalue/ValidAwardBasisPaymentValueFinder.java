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
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.award.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Values Finder for Invoice Instructions Indicator Values.
 * 
 */
public class ValidAwardBasisPaymentValueFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private transient AwardPaymentAndInvoicesService awardPaymentInfoService;
    
    private AwardPaymentAndInvoicesService getAwardPaymentAndInvoicesService() {
        if( awardPaymentInfoService == null ) {
            awardPaymentInfoService = KcServiceLocator.getService(AwardPaymentAndInvoicesService.class);
        }
        return awardPaymentInfoService;
    }

    @Override
    public List<KeyValue> getKeyValues() {
       List<KeyValue> keyLabels = new ArrayList<KeyValue>();
       AwardDocument awardDocument = (AwardDocument) getDocument();
       keyLabels.add( new ConcreteKeyValue( "", "select" ));
       if( awardDocument != null && awardDocument.getAward() != null
               && awardDocument.getAward().getAwardTypeCode() != null ) {
         
           List<ValidAwardBasisPayment> results =  getAwardPaymentAndInvoicesService().getValidAwardBasisPaymentsByAwardTypeCode(awardDocument.getAward().getAwardTypeCode());
           for( ValidAwardBasisPayment awardBasisPayment : results ) {
               awardBasisPayment.refresh();
               keyLabels.add( new ConcreteKeyValue( awardBasisPayment.getBasisOfPaymentCode(), awardBasisPayment.getBasisOfPayment().getDescription()));
           }
       }
       return keyLabels;
    }
    
    
}
