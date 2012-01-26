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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Test the Abstract Type Values Finder.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AbstractTypeValuesFinderTest extends ValuesFinderTestBase {
    @Override
    public void setUp() throws Exception {
        GlobalVariables.clear();
        KNSGlobalVariables.clear();
    }
    
    @Override
    protected Class<AbstractTypeValuesFinder> getTestClass() {
        return AbstractTypeValuesFinder.class;
    }

    @Override
    protected List<KeyValue> getKeyValues() {
        final List<KeyValue> keylabel = new ArrayList<KeyValue>();
        
        keylabel.add(createKeyValue("", "select"));
        keylabel.add(createKeyValue("1", "Project Summary"));
        keylabel.add(createKeyValue("2", "Technical Abstract"));
        keylabel.add(createKeyValue("3", "Layman Abstract"));
        keylabel.add(createKeyValue("4", "Labs"));
        keylabel.add(createKeyValue("5", "Clinical"));
        keylabel.add(createKeyValue("6", "Animal"));
        keylabel.add(createKeyValue("7", "Computer"));
        keylabel.add(createKeyValue("8", "Office"));
        keylabel.add(createKeyValue("9", "Other Facilities"));
        keylabel.add(createKeyValue("10", "Equipment"));
        keylabel.add(createKeyValue("11", "Other Resources"));
        keylabel.add(createKeyValue("12", "Suggested Reviewers"));
        keylabel.add(createKeyValue("13", "Publications"));
        keylabel.add(createKeyValue("14", "Reviewers Not to Include"));
        keylabel.add(createKeyValue("15", "Deviation Authorization"));
        keylabel.add(createKeyValue("16", "Areas Affected"));
        keylabel.add(createKeyValue("17", "Relevance"));
        
        return keylabel;
    }
}
