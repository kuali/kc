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
package org.kuali.kra.dao;

import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.kuali.core.bo.BusinessObject;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;

/**
 * Data Access Object for special needs of <code>{@link Rolodex}</code> like the <code>{@link NonOrganizationalRolodex}</code>
 * 
 * @see org.kuali.kra.bo.Rolodex
 * @see org.kuali.kra.bo.NonOrganizationalRolodex
 */
public interface RolodexDao {

    /**
     * Search using the PersistanceBroker for <code>{@link Rolodex}</code> instances that have first, middle, and/or last names.
     * 
     * @param fieldValues Search criteria by object attribute
     * @param usePrimaryKeys indicates whether to simplify the search due to criteria restricted to primary keys
     * @return Collection of <code>{@link Rolodex}</code> instances
     */
    public List<? extends BusinessObject> getNonOrganizationalRolodexResults(Map fieldValues, boolean usePrimaryKeys);
    
    /**
     * Create <code>{@link Criteria}</code> instance for the Persistance Broker to search for <code>{@link NonOrganizationalRolodex}</code>
     * instances.
     * 
     * @param businessObjectClass
     * @param fieldValues
     * @param usePrimaryKeys indicates whether to simplify the search due to criteria restricted to primary keys
     * @return Criteria
     */
    public Criteria getNonOrganizationalRolodexCriteria(Class businessObjectClass, Map fieldValues, boolean usePrimaryKeys);

}
