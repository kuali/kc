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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;

public class ProposalTypeValuesFinder extends KeyValuesBase {
    
    public List getKeyValues() {
        // TODO Use reference table
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        keyValues.add(new KeyLabelPair("1", "Continuation"));
        keyValues.add(new KeyLabelPair("2", "New"));
        keyValues.add(new KeyLabelPair("3", "Resubmission"));
        keyValues.add(new KeyLabelPair("4", "Revision"));
        keyValues.add(new KeyLabelPair("5", "Task Order"));
        return keyValues;
    }
}
