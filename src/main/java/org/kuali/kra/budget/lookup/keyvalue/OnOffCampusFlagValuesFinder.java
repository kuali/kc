/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiMaintenanceForm;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.Constants;

public class OnOffCampusFlagValuesFinder extends KeyValuesBase{

    /**
     * This method is to list the onoffcampus radio buttons in lookup and maintenance form
     * onoffcampusflag is saved as 'N','F' so it is not normal boolean field that is saved as Y/N
     * So, it complicated the method.  If it is normal boolean field, then checkbox will do it.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        // in maintenance form use y/n, so it can be translated to true and false.
        if (GlobalVariables.getKualiForm() instanceof KualiMaintenanceForm) {
            keyValues.add(new KeyLabelPair("Y", "On"));
            keyValues.add(new KeyLabelPair("N", "Off"));
        } else {
            // this is for lookup form
            keyValues.add(new KeyLabelPair(Constants.ON_CAMUS_FLAG, "On"));
            keyValues.add(new KeyLabelPair(Constants.OFF_CAMUS_FLAG, "Off"));
            keyValues.add(new KeyLabelPair("", "Both"));
        }
        return keyValues;
    }
}
