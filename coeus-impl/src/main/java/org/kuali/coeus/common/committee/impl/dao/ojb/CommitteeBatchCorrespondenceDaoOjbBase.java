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
package org.kuali.coeus.common.committee.impl.dao.ojb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.sql.Date;
import java.util.List;

/**
 * 
 * This class is the OJB implementation of CommitteeBatchCorrespondenceDao 
 * which provides enhanced database access functionality.
 */
public abstract class CommitteeBatchCorrespondenceDaoOjbBase<CBC extends CommitteeBatchCorrespondenceBase> extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, CommitteeBatchCorrespondenceDao<CBC> {

    private static final Log LOG = LogFactory.getLog(CommitteeBatchCorrespondenceDaoOjbBase.class);

    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "batchCorrespondenceTypeCode";
    private static final String BATCH_RUN_DATE = "batchRunDate";

    @Override
    @SuppressWarnings("unchecked")
    public List<CBC> getCommitteeBatchCorrespondence(String batchCorrespondenceTypeCode, Date startDate, Date endDate) {
        Criteria crit = new Criteria();
        crit.addEqualTo(BATCH_CORRESPONDENCE_TYPE_CODE, batchCorrespondenceTypeCode);
        if (startDate != null) {
            crit.addGreaterOrEqualThan(BATCH_RUN_DATE, startDate);
        }
        if (endDate != null) {
            crit.addLessOrEqualThan(BATCH_RUN_DATE, endDate);
        }

        Query q = QueryFactory.newQuery(getCommitteeBatchCorrespondenceBOClassHook(), crit, true);
        logQuery(q);
        return (List<CBC>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

    protected abstract Class<CBC> getCommitteeBatchCorrespondenceBOClassHook();
    
    
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
