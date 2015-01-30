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
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.*;

/**
 * Gets all sequence numbers for the current award id.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardSequenceNumberValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * Searches for all awards with the same award id and grabs all sequence numbers
     * for that award returning the sequence number as both the key and the value for
     * the pair.
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award sequence numbers
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        AwardDocument doc = (AwardDocument) getDocument();
        String awardNumber = doc.getAward().getAwardNumber();
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Map<String, Object> idMatch = new HashMap<String, Object>();
        idMatch.put("awardNumber", awardNumber);
        Collection<Award> awards = keyValuesService.findMatching(Award.class, idMatch);
        List<Integer> sortedList = new ArrayList<Integer>();
        for (Award award : awards ) {
            sortedList.add(award.getSequenceNumber());
        }
        Collections.sort(sortedList, Collections.reverseOrder());
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (Integer num : sortedList) {
                keyValues.add(new ConcreteKeyValue(num.toString(), num.toString()));
        }
        return keyValues;
    }
}
