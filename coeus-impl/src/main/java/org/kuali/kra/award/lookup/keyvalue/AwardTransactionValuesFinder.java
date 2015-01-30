/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.lookup.AwardTransactionLookupService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gets all sequence numbers for the current award id.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardTransactionValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private AwardTransactionLookupService transactionLookupService;
    
    public AwardTransactionValuesFinder() {
        transactionLookupService = KcServiceLocator.getService(AwardTransactionLookupService.class);
    }
    
    /**
     * Grabs the current award from the current form and loops through all
     * AwardAmountInfos and puts the transaction id in both for key and value for
     * each pair.
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award tranaction ids
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
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
        return (AwardForm) getFormOrView();
    }

    public void setTransactionLookupService(AwardTransactionLookupService transactionLookupService) {
        this.transactionLookupService = transactionLookupService;
    }
}
