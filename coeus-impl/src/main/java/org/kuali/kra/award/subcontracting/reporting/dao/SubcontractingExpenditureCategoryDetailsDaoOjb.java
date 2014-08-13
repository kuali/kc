/*
 * Copyright 2005-2014 The Kuali Foundation
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
