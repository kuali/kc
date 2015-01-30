/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.dao.ojb;

import org.apache.ojb.broker.query.*;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.dao.AwardPersonDao;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.util.*;

public class AwardDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AwardDao {

    @Override
    public String getAwardNumber(Long awardId) {
        Criteria crit = new Criteria();
        crit.addEqualTo("awardId", awardId);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(Award.class, crit);
        q.setAttributes(new String[] { "awardNumber" });
        Iterator<Object> resultsIter = getPersistenceBrokerTemplate().getIteratorByQuery(q);
        if (!resultsIter.hasNext()) {
            return null;
        }
        final String awardNumber = (String)resultsIter.next();
        while (resultsIter.hasNext()) {
            resultsIter.next(); // exhaust the iterator so the result set can be returned
        }
        return awardNumber;
    }

    /**
     * Builds a query from the given field values and then limits it based on the lookup limit for Award
     * @param fieldValues the field values to set
     * @return a Collection of retrieved awards
     */
    @Override
    public Collection<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues) {
        Criteria crit = new Criteria();

        // copy the criteria over; should we trust the criteria this much?
        for (String key : fieldValues.keySet()) {
            crit.addEqualTo(key, fieldValues.get(key));
        }

        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Award.class);
        if (searchResultsLimit != null) {
            LookupUtils.applySearchResultsLimit(Award.class, crit, getDbPlatform());
        }

        Collection searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Award.class, crit));
        return searchResults;
    }
}
