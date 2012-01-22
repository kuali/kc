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
package org.kuali.kra.committee.dao.ojb;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.util.OjbCollectionAware;

/**
 * 
 * This class is the OJB implementation of CommitteeBatchCorrespondenceDao 
 * which provides enhanced database access functionality.
 */
public class CommitteeBatchCorrespondenceDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, CommitteeBatchCorrespondenceDao {

    private static final Log LOG = LogFactory.getLog(CommitteeBatchCorrespondenceDaoOjb.class);

    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";
    private static final String BATCH_RUN_DATE = "batchRunDate";

    /**
     * {@inheritDoc} 
     */
    @SuppressWarnings("unchecked")
    public List<CommitteeBatchCorrespondence> getCommitteeBatchCorrespondence(String batchCorrespondenceTypeCode, Date startDate, Date endDate) {
        Criteria crit = new Criteria();
        crit.addEqualTo(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);
        if (startDate != null) {
            crit.addGreaterOrEqualThan(BATCH_RUN_DATE, startDate);
        }
        if (endDate != null) {
            crit.addLessOrEqualThan(BATCH_RUN_DATE, endDate);
        }
        Query q = QueryFactory.newQuery(CommitteeBatchCorrespondence.class, crit, true);
        logQuery(q);
        return (List<CommitteeBatchCorrespondence>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
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
