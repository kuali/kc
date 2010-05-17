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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.util.KeyLabelPair;
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
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class CoeusModueValuesFinderTest extends ValuesFinderTestBase {
    
    @Override
    protected Class<CoeusModuleValuesFinder> getTestClass() {
        return CoeusModuleValuesFinder.class;
    }

    @Override
    protected List<KeyLabelPair> getKeyValues() {
        final List<KeyLabelPair> keylabel = new ArrayList<KeyLabelPair>();
        // if permission changed, this needs to be adjusted too.
        keylabel.add(createKeyValue("", "select"));
        keylabel.add(createKeyValue("1", "Award")); 
        keylabel.add(createKeyValue("2", "Institute Proposal")); 
        keylabel.add(createKeyValue("3", "Development Proposal")); 
        keylabel.add(createKeyValue("4", "Subcontracts")); 
        keylabel.add(createKeyValue("5", "Negotiations")); 
        keylabel.add(createKeyValue("6", "Person")); 
        keylabel.add(createKeyValue("7", "IRB")); 
        keylabel.add(createKeyValue("8", "Annual Coi Disclosure")); 
        
        return keylabel;
    }
}
