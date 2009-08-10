/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;

/**
 * The ProtocolFinderDao implementation for OJB.
 */
public class ProtocolFinderDaoOjb extends PlatformAwareDaoBaseOjb implements ProtocolFinderDao {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String MAX_SEQUENCE_NUMBER = "max(" + SEQUENCE_NUMBER + ")";

    /**
     * @see org.kuali.kra.irb.ProtocolFinderDao#findCurrentProtocolByNumber(java.lang.String)
     */
    public Protocol findCurrentProtocolByNumber(String protocolNumber) {
        
        Criteria subCrit = new Criteria();
        subCrit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
        ReportQueryByCriteria subQuery = QueryFactory.newReportQuery(Protocol.class, subCrit);
        subQuery.setAttributes(new String[] { MAX_SEQUENCE_NUMBER });

        Criteria crit = new Criteria();
        crit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
        crit.addEqualTo(SEQUENCE_NUMBER, subQuery);
        Query q = QueryFactory.newQuery(Protocol.class, crit);

        return (Protocol) getPersistenceBrokerTemplate().getObjectByQuery(q);
    }
}
