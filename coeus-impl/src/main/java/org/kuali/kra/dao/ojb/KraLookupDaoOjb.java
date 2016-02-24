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
package org.kuali.kra.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.springframework.dao.DataIntegrityViolationException;
import org.springmodules.orm.ojb.OjbOperationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is the custom service class for kc Lookup Dao Ojb.
 */
public class KraLookupDaoOjb extends LookupDaoOjb implements KraLookupDao {

    @Override
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
