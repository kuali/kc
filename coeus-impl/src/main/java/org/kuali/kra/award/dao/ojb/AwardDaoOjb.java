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

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.coeus.sys.framework.summary.SearchResults;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.framework.persistence.platform.DatabasePlatform;
import org.kuali.rice.core.framework.persistence.platform.MySQLDatabasePlatform;
import org.kuali.rice.core.framework.persistence.platform.OracleDatabasePlatform;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.util.*;

public class AwardDaoOjb extends LookupDaoOjb implements OjbCollectionAware, AwardDao {
	
    private static final String AWARD_NUMBER = "awardNumber";
	private static final String AWARD_ID = "awardId";
	private static final String UPDATE_TIMESTAMP = "updateTimestamp";
	private static final String AWARD_SEQUENCE_STATUS = "awardSequenceStatus";

	@Override
    public String getAwardNumber(Long awardId) {
        Criteria crit = new Criteria();
        crit.addEqualTo(AWARD_ID, awardId);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(Award.class, crit);
        q.setAttributes(new String[] { AWARD_NUMBER });
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

	@Override
	public SearchResults<Award> retrievePopulatedAwardByCriteria(
			Map<String, Object> fieldValues, Date updatedSince, Integer page,
			Integer numberPerPage) {

		SearchResults<Award> result = new SearchResults<>();
		Criteria origCrit = getCollectionCriteriaFromMap(new Award(), fieldValues);
		Criteria crit = new Criteria();
		crit.addEqualTo(AWARD_SEQUENCE_STATUS, VersionStatus.ACTIVE.toString());
		if (updatedSince != null) {
			crit.addGreaterOrEqualThan(UPDATE_TIMESTAMP, new java.sql.Date(updatedSince.getTime()));
		}
		crit.addAndCriteria(origCrit);
		if (page != null) {
			result.setTotalResults(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(Award.class, crit)));
			crit.addSql(generatePagingSql(page, numberPerPage == null ? 20 : numberPerPage));
		}
		
		result.setResults(getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Award.class, crit)));
		
		if (page == null) {
			result.setTotalResults(result.getResults().size());
		}
		return result;
	}
	
	public String generatePagingSql(Integer page, Integer numberPerPage) {
		DatabasePlatform dbPlatform = getDbPlatform();
		// OJB includes this as an AND to the existing statement so need it to say 'AND 1 = 1 ..."
		String result = " 1 = 1 ORDER BY AWARD_ID ";
		if (dbPlatform instanceof MySQLDatabasePlatform) {
			return result + " LIMIT " + ((page-1)*numberPerPage) + "," + numberPerPage;
		} else if (dbPlatform instanceof OracleDatabasePlatform) {
			return result + " ROWNUM >= " + ((page-1)*numberPerPage) + " AND ROWNUM < " + (page*numberPerPage);
		} else {
			throw new UnsupportedOperationException("Unsupported database detected");
		}
	}  
}
