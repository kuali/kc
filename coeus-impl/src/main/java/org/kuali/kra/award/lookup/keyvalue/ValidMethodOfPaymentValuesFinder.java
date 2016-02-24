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
