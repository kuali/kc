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
package org.kuali.kra.protocol;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

import java.util.List;

/**
 * The ProtocolFinderDao implementation for OJB.
 */
public abstract class ProtocolFinderDaoOjbBase extends PlatformAwareDaoBaseOjb implements ProtocolFinderDao {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String MAX_SEQUENCE_NUMBER = "max(" + SEQUENCE_NUMBER + ")";
    private static final String SUBMISSION_NUMBER = "submissionNumber"; 

    @Override
    @SuppressWarnings("unchecked")
    public ProtocolBase findCurrentProtocolByNumber(String protocolNumber) {
        
        Criteria subCrit = new Criteria();
        subCrit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
        ReportQueryByCriteria subQuery = QueryFactory.newReportQuery(getProtocolBOClassHook(), subCrit);
        subQuery.setAttributes(new String[] { MAX_SEQUENCE_NUMBER });

        Criteria crit = new Criteria();
        crit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
        crit.addEqualTo(SEQUENCE_NUMBER, subQuery);
        Query q = QueryFactory.newQuery(getProtocolBOClassHook(), crit);

        return (ProtocolBase) getPersistenceBrokerTemplate().getObjectByQuery(q);
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProtocolSubmissionBase> findProtocolSubmissions(String protocolNumber, int submissionNumber) {
        Criteria crit = new Criteria();
        crit.addLike(PROTOCOL_NUMBER, protocolNumber + "%");
        crit.addEqualTo(SUBMISSION_NUMBER, submissionNumber);
        Query q = QueryFactory.newQuery(getProtocolSubmissionBOClassHook(), crit);
        return (List<ProtocolSubmissionBase>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProtocolBase> findProtocols(String protocolNumber) {
        
        Criteria crit = new Criteria();
        crit.addLike(PROTOCOL_NUMBER, protocolNumber + "%");
        Query q = QueryFactory.newQuery(getProtocolBOClassHook(), crit);

        return (List<ProtocolBase>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();

    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();
    
}
