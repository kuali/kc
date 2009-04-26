/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.dao.ojb;

import java.util.Iterator;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.bo.Person;
import org.kuali.kra.dao.PersonDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;

/**
 * Person DAO Implementation.
 */
public class PersonDaoOjb extends PlatformAwareDaoBaseOjb implements PersonDao {
    
    /**
     * @see org.kuali.kra.dao.PersonDao#getUserName(java.lang.String)
     */
    public String getUserName(String userId) {
        String userName = null;
        
        Criteria crit = new Criteria();
        crit.addEqualTo("personId", userId);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(Person.class, crit);
        q.setAttributes(new String[] { "userName" });

        Iterator<Object[]> iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        while (iter != null && iter.hasNext()) {
            Object[] row = iter.next();
            userName = (String) row[0];
        }
        return userName;
    }
}
