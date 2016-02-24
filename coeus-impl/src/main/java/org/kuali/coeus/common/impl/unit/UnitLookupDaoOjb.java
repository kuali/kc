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
    
    public Unit getTopUnit() {
        Criteria crit = new Criteria();
        crit.addIsNull("parentUnitNumber");
        Query q = QueryFactory.newQuery(Unit.class, crit);
        return (Unit) getPersistenceBrokerTemplate().getObjectByQuery(q);
    	
    }

}
