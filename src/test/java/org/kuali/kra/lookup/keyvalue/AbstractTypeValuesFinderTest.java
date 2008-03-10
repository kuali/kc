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

import org.junit.Test;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.kra.proposaldevelopment.lookup.keyvalue.AbstractTypeValuesFinder;

/**
 * Test the Abstract Type Values Finder.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AbstractTypeValuesFinderTest extends ValuesFinderTestBase {

    /**
     * Constructs an AbstractTypeValuesFinderTest.
     */
    public AbstractTypeValuesFinderTest() {
        setTestClass(AbstractTypeValuesFinder.class);
    }

    @Test public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }

    /**
     * @see org.kuali.kra.keyvalue.ValuesFinderTestBase#addKeyValues()
     */
    @Override
    protected void addKeyValues() {
        this.addKeyValue("", "select");
        this.addKeyValue("1", "Project Summary");
        this.addKeyValue("2", "Technical Abstract");
        this.addKeyValue("3", "Layman Abstract");
        this.addKeyValue("4", "Labs");
        this.addKeyValue("5", "Clinical");
        this.addKeyValue("6", "Animal");
        this.addKeyValue("7", "Computer");
        this.addKeyValue("8", "Office");
        this.addKeyValue("9", "Other Facilities");
        this.addKeyValue("10", "Equipment");
        this.addKeyValue("11", "Other Resources");
        this.addKeyValue("12", "Suggested Reviewers");
        this.addKeyValue("13", "Publications");
        this.addKeyValue("14", "Reviewers Not to Include");
        this.addKeyValue("15", "Deviation Authorization");
    }
}
