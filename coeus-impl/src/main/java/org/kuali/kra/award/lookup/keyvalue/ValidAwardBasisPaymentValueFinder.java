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
