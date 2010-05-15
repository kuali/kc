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

import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * This class tests LeadUnitValuesFinder.
 */
public class LeadUnitValuesFinderTest extends ValuesFinderTestBase {

    @Override
    protected Class<LeadUnitValuesFinder> getTestClass() {
        return LeadUnitValuesFinder.class;
    }

    @Override
    protected List<KeyLabelPair> getKeyValues() {
        final List<KeyLabelPair> keylabel = new ArrayList<KeyLabelPair>();
        
        keylabel.add(new KeyLabelPair("", "select"));
        keylabel.add(new KeyLabelPair("000001", "000001 - University"));
        keylabel.add(new KeyLabelPair("IN-CARD", "IN-CARD - CARDIOLOGY"));
        keylabel.add(new KeyLabelPair("IN-CARR", "IN-CARR - CARDIOLOGY RECHARGE CTR"));
        keylabel.add(new KeyLabelPair("BL-IIDC", "BL-IIDC - IND INST ON DISABILITY/COMMNTY"));
        
        return keylabel;
    }

}
