/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kra.bo.Person;
import org.kuali.kra.dao.PersonDao;

/**
 * Person DAO Implementation.
 */
public class PersonDaoOjb extends PlatformAwareDaoBaseOjb implements PersonDao {

    /**
     * @see org.kuali.kra.dao.PersonDao#isActiveByName(java.lang.String)
     */
    public boolean isActiveByName(String username) {
        Criteria crit = new Criteria();
        crit.addEqualTo("userName", username);
        crit.addEqualTo("active", Boolean.TRUE);
        Query q = QueryFactory.newQuery(Person.class, crit);

        return getPersistenceBrokerTemplate().getCount(q) > 0;
    }
}
