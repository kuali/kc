/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.kra.questionnaire.CoeusModuleValuesFinder;

public class MinuteEntryTypeValuesFinderTest extends ValuesFinderTestBase {

        /**
         * Constructs an CoeusModueValuesFinderTest.
         */
        public MinuteEntryTypeValuesFinderTest() {
            setTestClass(MinuteEntryTypeValuesFinder.class);
        }

        @Test public void testGetKeyValues() throws Exception {
            super.testGetKeyValues();
        }

        /**
         * @see org.kuali.kra.keyvalue.ValuesFinderTestBase#addKeyValues()
         */
        @Override
        protected void addKeyValues() {
            // if permission changed, this needs to be adjusted too.
            this.addKeyValue("", "select");
            this.addKeyValue("1", "General Comments");
            this.addKeyValue("2", "Attendance");
            this.addKeyValue("3", "Protocol");
            this.addKeyValue("4", "Other Business");
            this.addKeyValue("5", "Adverse Events");
        }
    }


