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
package org.kuali.kra.timeandmoney;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

public class AwardValuesFinder extends KeyValuesBase{
    
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        keyValues.add(new KeyLabelPair("000000-00000", "External"));
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) GlobalVariables.getKualiForm();        
        TimeAndMoneyDocument document = timeAndMoneyForm.getTimeAndMoneyDocument();
        if(document.getAwardHierarchyItems()!=null && document.getAwardHierarchyItems().size()!=0){
            for(Entry<String, AwardHierarchy> awardHierachy: document.getAwardHierarchyItems().entrySet()){            
                keyValues.add(new KeyLabelPair(awardHierachy.getKey(), awardHierachy.getValue().getAwardNumber()));
            }
        }
        
        return keyValues;
    }

}
