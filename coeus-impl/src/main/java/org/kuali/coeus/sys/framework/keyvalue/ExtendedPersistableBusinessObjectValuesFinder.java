/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.keyvalue;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
