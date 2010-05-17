/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.meeting;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.util.KeyLabelPair;

public class MinuteEntryTypeValuesFinderTest extends ValuesFinderTestBase {

    @Override
    protected Class<MinuteEntryTypeValuesFinder> getTestClass() {
        return MinuteEntryTypeValuesFinder.class;
    }

    @Override
    protected List<KeyLabelPair> getKeyValues() {
        final List<KeyLabelPair> keylabel = new ArrayList<KeyLabelPair>();
        
        // if permission changed, this needs to be adjusted too.
        keylabel.add(new KeyLabelPair("", "select"));
        keylabel.add(new KeyLabelPair("1", "General Comments"));
        keylabel.add(new KeyLabelPair("2", "Attendance"));
        keylabel.add(new KeyLabelPair("3", "Protocol"));
        keylabel.add(new KeyLabelPair("4", "Other Business"));
        keylabel.add(new KeyLabelPair("5", "Adverse Events"));
        
        return keylabel;
    }
}


