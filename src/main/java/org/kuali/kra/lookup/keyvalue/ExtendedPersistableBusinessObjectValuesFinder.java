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
package org.kuali.kra.lookup.keyvalue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

/**
 * This class extends the PersistableBusinessObjectValuesFinder - the Generic ValuesFinder - 
 * to add a "select" entry.
 * 
 * <p>
 * It's a shame that rice is so tightly coupled to {@link PersistableBusinessObjectValuesFinder PersistableBusinessObjectValuesFinder}
 * It makes the design of these value finders very rigid.
 * </p>
 */
public class ExtendedPersistableBusinessObjectValuesFinder extends PersistableBusinessObjectValuesFinder {

    
    class PBOComparator implements Comparator
    {    
        public int compare(Object kv1, Object kv2 )
        {    
            try
            {
                String desc1 = ((KeyValue)kv1).getValue();
                String desc2 = ((KeyValue)kv2).getValue();
                if (desc1 == null)
                {
                    desc1 = "";
                }
                if (desc2 == null)
                {
                    desc2 = "";
                }
                return desc1.compareTo(desc2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        
    }
    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "select" entry.
     * 
     * {@inheritDoc}
     */
    @Override
    public List<KeyValue> getKeyValues(){
        List<KeyValue> labels;
        
        labels = super.getKeyValues();
        Collections.sort(labels, new PBOComparator());
        
        labels.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));    
        return labels;
    }
}
