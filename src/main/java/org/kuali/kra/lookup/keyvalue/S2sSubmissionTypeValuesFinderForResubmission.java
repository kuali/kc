/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

public class S2sSubmissionTypeValuesFinderForResubmission extends KeyValuesBase{

    public List<KeyLabelPair> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection S2SSubmissionTypes = keyValuesService.findAllOrderBy(S2sSubmissionType.class,"sortId",true);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select: "));
        for (Iterator iter = S2SSubmissionTypes.iterator(); iter.hasNext();) {
            S2sSubmissionType s2sSubmissionType = (S2sSubmissionType) iter.next();
            keyValues.add(new KeyLabelPair(s2sSubmissionType.getS2sSubmissionTypeCode(), s2sSubmissionType.getDescription()));                            
        }
        return keyValues;
    }
    
}
