/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;

public class NoticeOfOpportunityValuesFinder extends KeyValuesBase {

    public List getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        keyValues.add(new KeyLabelPair("1", "Federal Solicitation"));
        keyValues.add(new KeyLabelPair("2", "Unsolicited"));
        keyValues.add(new KeyLabelPair("3", "Verbal Request for Proposal"));
        keyValues.add(new KeyLabelPair("4", "SBIR Solicitation"));
        keyValues.add(new KeyLabelPair("5", "STTR Solicitation"));
        keyValues.add(new KeyLabelPair("6", "Non-Federal Solicitation"));
        keyValues.add(new KeyLabelPair("7", "MIT Alliance/Internal"));
        return keyValues;
    }
}
