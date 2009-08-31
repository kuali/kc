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
package org.kuali.kra.questionnaire;

import org.junit.Test;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
@PerSuiteUnitTestData(
    @UnitTestData(
          sqlFiles = {
               @UnitTestFile(filename = "classpath:sql/dml/load_COEUS_MODULE.sql", delimiter = ";")
        }
    )
)

public class CoeusModueValuesFinderTest extends ValuesFinderTestBase {

    /**
     * Constructs an CoeusModueValuesFinderTest.
     */
    public CoeusModueValuesFinderTest() {
        setTestClass(CoeusModuleValuesFinder.class);
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
        this.addKeyValue("3", "Development Proposal");
     //   this.addKeyValue("7", "IRB");
    }
}
