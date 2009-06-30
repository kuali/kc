/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.irb.protocol.participant.ParticipantTypeValuesFinder;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;

/**
 * 
 * Test the Participant Type Values Finder.
 *
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ParticipantTypeValuesFinderTest extends ValuesFinderTestBase {

    /**
     * Constructs an ParticipantTypeValuesFinderTest.
     */
    public ParticipantTypeValuesFinderTest() {
        setTestClass(ParticipantTypeValuesFinder.class);
    }

    @Test
    public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }

    /**
     * @see org.kuali.kra.keyvalue.ValuesFinderTestBase#addKeyValues()
     */
    @Override
    protected void addKeyValues() {
        this.addKeyValue("", "select");
        this.addKeyValue("1", "Children");
        this.addKeyValue("2", "Decisionally impaired");
        this.addKeyValue("3", "Employees");
        this.addKeyValue("4", "Prisoners");
        this.addKeyValue("5", "Pregnant women");
        this.addKeyValue("6", "Fetuses");
        this.addKeyValue("7", "Students");
        this.addKeyValue("8", "Students - minors");
        this.addKeyValue("9", "Wards of the state");
        this.addKeyValue("10", "Other");
    }
}
