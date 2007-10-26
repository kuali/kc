/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.util.OjbCollectionAware;
import org.kuali.kra.bo.RoleRight;
import org.kuali.kra.bo.UserRole;

public class UserRoleDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, UserRoleDao {

    public List<UserRole> getUserRoles(String personId) {
        
        Criteria criteria = new Criteria();
        criteria.addEqualTo("userId", personId);

        QueryByCriteria query = QueryFactory.newQuery(UserRole.class, criteria);
        return new ArrayList<UserRole>(getPersistenceBrokerTemplate().getCollectionByQuery(query));
    }

    public List<UserRole> getUserRoles(String personId, String rightId) {
        Criteria userRoleCriteria = new Criteria();
        userRoleCriteria.addEqualTo("userId", personId);
        
      
        ReportQueryByCriteria roleRightsSubQuery;
        Criteria roleRightsSubQueryCriteria = new Criteria();
        roleRightsSubQueryCriteria.addEqualTo("rightId", rightId);
        
        roleRightsSubQuery = QueryFactory.newReportQuery(RoleRight.class, roleRightsSubQueryCriteria);
        roleRightsSubQuery.setAttributes(new String[] { "ROLE_ID" });
        
        userRoleCriteria.addIn("roleId", roleRightsSubQuery);

        QueryByCriteria query = QueryFactory.newQuery(UserRole.class, userRoleCriteria);
        Collection results = getPersistenceBrokerTemplate().getCollectionByQuery(query);
        return new ArrayList<UserRole>(results);
    }

    public boolean userHasRole(String personId, String roleId) {
        // TODO Auto-generated method stub
        return false;
    }

}
