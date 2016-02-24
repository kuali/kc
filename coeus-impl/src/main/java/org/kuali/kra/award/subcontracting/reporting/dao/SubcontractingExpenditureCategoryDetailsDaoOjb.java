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
package org.kuali.kra.award.subcontracting.reporting.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryDetails;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.sql.Date;
import java.util.List;

public class SubcontractingExpenditureCategoryDetailsDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, SubcontractingExpenditureCategoryDetailsDao {

    private static final Object FISCAL_PERIOD = "fiscalPeriod";
    private static final Log LOG = LogFactory.getLog(SubcontractingExpenditureCategoryDetailsDaoOjb.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<SubcontractingExpenditureCategoryDetails> findCategoryDetailsByFiscalPeriodRange(Date startDate, Date endDate) {
        List<SubcontractingExpenditureCategoryDetails> retVal = null;
        
        if((startDate != null ) && (endDate != null)) {
            //set the less-than and greater-than criteria and run the query to get the BOs
            Criteria criteria = new Criteria();
            criteria.addGreaterOrEqualThan(FISCAL_PERIOD, startDate);
            criteria.addLessOrEqualThan(FISCAL_PERIOD, endDate);
            Query q = QueryFactory.newQuery(SubcontractingExpenditureCategoryDetails.class, criteria, true);
            logQuery(q);
            retVal = (List<SubcontractingExpenditureCategoryDetails>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
        }
        else {
            throw new IllegalArgumentException("Both start and end dates have to be non-null");
        }
        return retVal;
    }
    

    /**
     * Logs the Query
     * @param q the query
     */
    private static void logQuery(Query q) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(q.toString());
        }
    }

}
