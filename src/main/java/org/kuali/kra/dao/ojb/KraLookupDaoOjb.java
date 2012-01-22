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
package org.kuali.kra.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.springframework.dao.DataIntegrityViolationException;
import org.springmodules.orm.ojb.OjbOperationException;

/**
 * This class is the custom service class for kc Lookup Dao Ojb.
 */
public class KraLookupDaoOjb extends LookupDaoOjb implements KraLookupDao {

    /**
     * @see org.kuali.kra.service.KraLookupDao#findCollectionUsingWildCard(java.lang.Class, org.apache.ojb.broker.query.Criteria, boolean)
     */
    @SuppressWarnings("unchecked")
    public Collection findCollectionUsingWildCard(Class businessObjectClass, String field, String wildCard, boolean unbounded) {
        Criteria criteria = new Criteria();
        criteria.addLike(field, wildCard);
        Collection searchResults = new ArrayList();
        try {
            Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(businessObjectClass);
            if (!unbounded && (searchResultsLimit != null)) {
                LookupUtils.applySearchResultsLimit(businessObjectClass, criteria, getDbPlatform());
            }
            searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(businessObjectClass, criteria));   
            List bos = new ArrayList();
            bos.addAll(searchResults);
            searchResults = bos;
        }
        catch (OjbOperationException e) {
            throw new RuntimeException("KraLookupDaoOjb encountered exception during executeSearch", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new RuntimeException("KraLookupDaoOjb encountered exception during executeSearch", e);
        }
        return searchResults;
    }

}
