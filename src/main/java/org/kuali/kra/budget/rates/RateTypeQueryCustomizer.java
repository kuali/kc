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
package org.kuali.kra.budget.rates;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;

/**
 * This class...
 */
@SuppressWarnings("serial")
public class RateTypeQueryCustomizer extends QueryCustomizerDefaultImpl {
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
        @SuppressWarnings("unused")
        Criteria crit = aQuery.getCriteria();
        
        return super.customizeQuery(anObject, aBroker, aCod, aQuery);
    }
}
