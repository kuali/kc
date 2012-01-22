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
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.lookup.AwardTransactionLookupService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Gets all sequence numbers for the current award id.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardTransactionValuesFinder extends KeyValuesBase {
    
    private AwardTransactionLookupService transactionLookupService;
    private BusinessObjectService businessObjectService;
    
    public AwardTransactionValuesFinder() {
        transactionLookupService = KraServiceLocator.getService(AwardTransactionLookupService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * Grabs the current award from the current form and loops through all
     * AwardAmountInfos and puts the transaction id in both for key and value for
     * each pair.
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award tranaction ids
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        AwardForm form = getAwardForm();
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Integer usableSequence = form.getAwardPrintChangeReport().getAwardVersion();
        if (usableSequence == null) {
            usableSequence = form.getAwardDocument().getAward().getSequenceNumber();
        }
        Map<Integer, String> transactionValues = transactionLookupService.getApplicableTransactionIds(form.getAwardDocument().getAward().getAwardNumber(), 
                usableSequence);
        
        for (Map.Entry<Integer, String> entry : transactionValues.entrySet()) {
            keyValues.add(new ConcreteKeyValue(entry.getKey().toString(), entry.getValue()));
        }

        return keyValues;
    }
    
    
    /**
     * Get the Award Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private AwardForm getAwardForm() {
        return (AwardForm) KNSGlobalVariables.getKualiForm();
    }

    public void setTransactionLookupService(AwardTransactionLookupService transactionLookupService) {
        this.transactionLookupService = transactionLookupService;
    }
}
