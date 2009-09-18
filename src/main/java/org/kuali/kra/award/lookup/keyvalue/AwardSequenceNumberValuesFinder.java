/*
 * Copyright 2006-2009 The Kuali Foundation
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
import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.AwardForm;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Gets all sequence numbers for the current award id.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardSequenceNumberValuesFinder extends KeyValuesBase {
    
    /**
     * Searches for all awards with the same award id and grabs all sequence numbers
     * for that award returning the sequence number as both the key and the value for
     * the pair.
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award sequence numbers
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        AwardDocument doc = getDocument();
        Long awardId = doc.getAward().getAwardId();
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Map<String, Long> idMatch = new HashMap<String, Long>();
        idMatch.put("awardId", awardId);
        Collection<Award> awards = keyValuesService.findMatching(Award.class, idMatch);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (Award award : awards) {
                keyValues.add(new KeyLabelPair(award.getSequenceNumber().toString(), award.getSequenceNumber().toString()));
        }
        return keyValues;
    }
    
    /**
     * Get the Award Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private AwardDocument getDocument() {
        AwardDocument doc = null;
        AwardForm form = (AwardForm) GlobalVariables.getKualiForm();
        if (form != null) {
            doc = form.getAwardDocument();
        }
        return doc;
    }
}
