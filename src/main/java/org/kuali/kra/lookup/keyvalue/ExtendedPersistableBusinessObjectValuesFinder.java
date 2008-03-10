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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.web.ui.KeyLabelPair;

/**
 * This class extends the PersistableBusinessObjectValuesFinder - the Generic ValuesFinder - 
 * to add a "select" entry.
 */
public class ExtendedPersistableBusinessObjectValuesFinder extends PersistableBusinessObjectValuesFinder {

    private static final Log LOG = LogFactory.getLog(ExtendedPersistableBusinessObjectValuesFinder.class);

    /**
     * Build the list of KeyLabelPairs using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "select" entry.
     */
    public List<KeyLabelPair> getKeyValues(){
        List<KeyLabelPair> labels = new ArrayList<KeyLabelPair>();
       
        Object key = "";
        String label = "select";
        
        labels = super.getKeyValues();
        labels.add(0, new KeyLabelPair(key,label));    
        
        return labels;
    }
}
