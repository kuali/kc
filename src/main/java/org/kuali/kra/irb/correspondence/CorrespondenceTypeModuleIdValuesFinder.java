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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
/**
 * 
 * This class returns the module IDs that ProtocolCorrespondenceType can have.
 */
public class CorrespondenceTypeModuleIdValuesFinder extends KeyValuesBase {

    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyLabelPairs = new ArrayList<KeyLabelPair>();

        keyLabelPairs.add(new KeyLabelPair("", "select"));
        for (CorrespondenceTypeModuleIdConstants correspondenceTypeModuleIdConstants : CorrespondenceTypeModuleIdConstants.values()) {
            keyLabelPairs.add(new KeyLabelPair(correspondenceTypeModuleIdConstants.code(), correspondenceTypeModuleIdConstants.description()));
        }
        
        return keyLabelPairs; 
    }

}
