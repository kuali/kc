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
package org.kuali.kra.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
/**
 * 
 * This class used for supplying customized query to OJB repository <code>collection-descriptor</code> tag.
 * Eg: 
 *          <query-customizer
 *               class="org.kuali.kra.proposaldevelopment.dao.NarrativeQueryCustomizer">
 *             <attribute
 *                 attribute-name="narrativeType.narrativeTypeGroup"
 *                 attribute-value="P"
 *             />
 *           </query-customizer>
 * 
 * @author KRSDEV team
 * @version 1.0
 */
public class QueryCustomizerDefaultImpl extends org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl {
    private final Map<String,String> queryMap = new HashMap<String,String>();
    /**
     * 
     * @see org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl#customizeQuery(java.lang.Object, org.apache.ojb.broker.PersistenceBroker, org.apache.ojb.broker.metadata.CollectionDescriptor, org.apache.ojb.broker.query.QueryByCriteria)
     */
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
        Iterator<String> keys = queryMap.keySet().iterator();
        Criteria crit = aQuery.getCriteria();
        while(keys.hasNext()){
            String key = keys.next();
            crit.addEqualTo(key, queryMap.get(key));
        }
        return aQuery;
    }

    /**
     * Override this method, if developer needs more than just supplying values to the 'where' clause
     * @see org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl#addAttribute(java.lang.String, java.lang.String)
     */
    public void addAttribute(String name, String value) {
        queryMap.put(name, value);
    }
}
