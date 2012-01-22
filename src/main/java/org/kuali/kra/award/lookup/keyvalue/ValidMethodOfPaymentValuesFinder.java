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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class ValidMethodOfPaymentValuesFinder extends KeyValuesBase {
    
    private String basisOfPaymentCode;
    private KeyValuesService keyValuesService;
    private AwardPaymentAndInvoicesService awardPaymentAndInvoicesService;
    
    /**
     * 
     * Constructs a FrequencyCodeValuesFinder.java.
     */
    public ValidMethodOfPaymentValuesFinder() {
        super();
    }
    
    /**
     * 
     * Constructs a ValidMethodOfPaymentValuesFinder.java.
     * @param basisOfPaymentCode
     * 
     */
    public ValidMethodOfPaymentValuesFinder( String basisOfPaymentCode ) {
        super();
        this.basisOfPaymentCode = basisOfPaymentCode;
    }
    
    /**
     * Constructs the list of Valid MethodOfPayment pairs for the set basisOfPaymentCode.
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("all")
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        keyLabels.add( new ConcreteKeyValue( "", "select" ));
        if( StringUtils.isNotBlank(basisOfPaymentCode)) {
            for( ValidBasisMethodPayment basisMethodPayment : getAwardPaymentAndInvoicesService().getValidBasisMethodPaymentByBasisCode(basisOfPaymentCode) ) { 
                basisMethodPayment.refresh();
                keyLabels.add(new ConcreteKeyValue( basisMethodPayment.getMethodOfPaymentCode(), basisMethodPayment.getMethodOfPayment().getDescription() ) );
            
            }
        }
        return keyLabels; 
    }
    
    private AwardPaymentAndInvoicesService getAwardPaymentAndInvoicesService() {
        if( awardPaymentAndInvoicesService == null ) {
            awardPaymentAndInvoicesService = KraServiceLocator.getService(AwardPaymentAndInvoicesService.class);
        }
        return awardPaymentAndInvoicesService;
    }
    
    /**
     * 
     * Wrapper method for retrieval of KeyValuesService
     * @return
     */
    protected KeyValuesService getKeyValuesService(){
        if(keyValuesService == null){
            keyValuesService = 
                (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        }
        return keyValuesService;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }
}
