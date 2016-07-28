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
package org.kuali.kra.award.dao.ojb;

import org.apache.commons.lang3.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.util.OjbCollectionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AwardDaoOjb extends LookupDaoOjb implements OjbCollectionAware, AwardDao {

    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String UPDATE_TIMESTAMP = "updateTimestamp";
    private static final String AWARD_SEQUENCE_STATUS = "awardSequenceStatus";
    public static final String AWARD_BUDGET_STATUS_CODE = "awardBudgetStatusCode";

    private DataSource dataSource;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public String getAwardNumber(Long awardId) {
        final Criteria crit = new Criteria();
        crit.addEqualTo(AWARD_ID, awardId);
        final ReportQueryByCriteria q = QueryFactory.newReportQuery(Award.class, crit);
        q.setAttributes(new String[] { AWARD_NUMBER });
        @SuppressWarnings("unchecked")
        final Iterator<String> resultsIter = getPersistenceBrokerTemplate().getIteratorByQuery(q);
        if (!resultsIter.hasNext()) {
            return null;
        }
        final String awardNumber = resultsIter.next();
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
        final Criteria crit = new Criteria();

        // copy the criteria over; should we trust the criteria this much?
        fieldValues.entrySet().forEach(entry -> crit.addEqualTo(entry.getKey(), entry.getValue()));

        final Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Award.class);
        if (searchResultsLimit != null) {
            LookupUtils.applySearchResultsLimit(Award.class, crit, getDbPlatform());
        }

        return getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Award.class, crit));
    }

    @Override
    public SearchResults<Award> retrieveActiveAwardsByCriteria(
            Map<String, Object> fieldValues, Date updatedSince, Integer page,
            Integer numberPerPage) {
        fieldValues.put(AWARD_SEQUENCE_STATUS, VersionStatus.ACTIVE.toString());
        return retrieveAwardsByCriteria(fieldValues, updatedSince, page, numberPerPage);
    }

    public SearchResults<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues, Date updatedSince, Integer page, Integer numberPerPage) {
        final SearchResults<Award> result = new SearchResults<>();
        final Criteria origCrit = getCollectionCriteriaFromMap(new Award(), fieldValues);
        final Criteria crit = new Criteria();
        if (updatedSince != null) {
            crit.addGreaterOrEqualThan(UPDATE_TIMESTAMP, updatedSince);
        }
        crit.addAndCriteria(origCrit);
        crit.addOrderByDescending(AWARD_ID);
        final QueryByCriteria newCrit = QueryFactory.newQuery(Award.class, crit);
        if (page != null && numberPerPage != -1) {
            result.setTotalResults(getPersistenceBrokerTemplate().getCount(newCrit));
            newCrit.setStartAtIndex((page-1)*numberPerPage);
            newCrit.setEndAtIndex(page*numberPerPage);
        }

        result.setResults((Collection<Award>) getPersistenceBrokerTemplate().getCollectionByQuery(newCrit));
        if (page == null) {
            result.setTotalResults(result.getResults().size());
        }
        return result;
    }

    @Override
    public List<Integer> getAwardSequenceNumbers(String awardNumber) {
        if (StringUtils.isBlank(awardNumber)) {
            return Collections.emptyList();
        }

        try (final Connection connection = getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT SEQUENCE_NUMBER FROM AWARD WHERE AWARD_NUMBER = ? ORDER BY SEQUENCE_NUMBER DESC")) {
            statement.setString(1, awardNumber);
            try (ResultSet result = statement.executeQuery()) {
                final List<Integer> sequenceNumbers = new ArrayList<>();
                while (result.next()) {
                    sequenceNumbers.add(result.getInt(1));
                }
                return sequenceNumbers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Award getAward(Long awardId) {
        return getBusinessObjectService().findBySinglePrimaryKey(Award.class, awardId);
    }

    @Override
    public List<Award> getAwardByAwardNumber(String awardNumber) {
        Map<String, String> criteria = new HashMap<>();
        criteria.put("awardNumber", awardNumber);
        return (List<Award>) getBusinessObjectService().findMatching(Award.class, criteria);
    }

    @Override
    public List<Award> getAwardByAwardHierarchy(String awardFamily) {
        QueryByCriteria queryCriteria = getQueryForAwardHierarchyByAwardFamily(awardFamily);
        return new ArrayList<>(getPersistenceBrokerTemplate().getCollectionByQuery(queryCriteria));

    }

    QueryByCriteria getQueryForAwardHierarchyByAwardFamily(String awardFamily) {
        Criteria crit = new Criteria();
        crit.addLike(AWARD_NUMBER, awardFamily + "-%");
        QueryByCriteria queryCrit = QueryFactory.newQuery(Award.class, crit);
        return queryCrit;
    }

    @Override
    public AwardBudgetExt getAwardBudget(String budgetId) {
        return getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, budgetId);
    }

    @Override
    public List<AwardBudgetExt> getAwardBudgetByStatusCode(Integer code) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(AWARD_BUDGET_STATUS_CODE, code);
        return (List<AwardBudgetExt>) getBusinessObjectService().findMatching(AwardBudgetExt.class, criteria);
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
