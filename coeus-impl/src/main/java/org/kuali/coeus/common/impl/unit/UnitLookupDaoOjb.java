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
package org.kuali.coeus.common.impl.unit;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class UnitLookupDaoOjb extends PlatformAwareDaoBaseOjb implements UnitLookupDao {

    @Override
    public Unit findUnitbyNumberCaseInsensitive(String unitNumber) {
        Criteria crit = new Criteria();
        crit.addEqualTo(getDbPlatform().getUpperCaseFunction() + "(UNIT_NUMBER)", unitNumber.toUpperCase());
        Query q = QueryFactory.newQuery(Unit.class, crit);
        return (Unit) getPersistenceBrokerTemplate().getObjectByQuery(q);
    }

}
