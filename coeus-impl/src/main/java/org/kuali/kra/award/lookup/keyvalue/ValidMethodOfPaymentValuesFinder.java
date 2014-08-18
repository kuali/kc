/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.award.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class ValidMethodOfPaymentValuesFinder extends UifKeyValuesFinderBase {
    
    private String basisOfPaymentCode;
    private AwardPaymentAndInvoicesService awardPaymentAndInvoicesService;
    

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
    @Override
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
            awardPaymentAndInvoicesService = KcServiceLocator.getService(AwardPaymentAndInvoicesService.class);
        }
        return awardPaymentAndInvoicesService;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }
}
