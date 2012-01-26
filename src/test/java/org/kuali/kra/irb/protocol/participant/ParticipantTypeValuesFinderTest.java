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
package org.kuali.kra.irb.protocol.participant;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * Test the Participant Type Values Finder.
 *
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ParticipantTypeValuesFinderTest extends ValuesFinderTestBase {
    @Override
    public void setUp() throws Exception {
        GlobalVariables.clear();
        KNSGlobalVariables.clear();
    }
    
    @Override
    protected Class<ParticipantTypeValuesFinder> getTestClass() {
        return ParticipantTypeValuesFinder.class;
    }

    @Override
    protected List<KeyValue> getKeyValues() {
        final List<KeyValue> keylabel = new ArrayList<KeyValue>();
        
        keylabel.add(createKeyValue("", "select"));
        keylabel.add(createKeyValue("1", "Children"));
        keylabel.add(createKeyValue("2", "Decisionally impaired"));
        keylabel.add(createKeyValue("3", "Employees"));
        keylabel.add(createKeyValue("4", "Prisoners"));
        keylabel.add(createKeyValue("5", "Pregnant women"));
        keylabel.add(createKeyValue("6", "Fetuses"));
        keylabel.add(createKeyValue("7", "Students"));
        keylabel.add(createKeyValue("8", "Students - minors"));
        keylabel.add(createKeyValue("9", "Wards of the state"));
        keylabel.add(createKeyValue("10", "Other"));
        
        return keylabel;
    }
}
