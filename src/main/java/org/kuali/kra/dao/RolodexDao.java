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
package org.kuali.kra.dao;

import java.util.List;
import java.util.Map;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.dao.LookupDao;
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

    public LookupDao getLookupDao();

    public void setLookupDao(LookupDao lookupDao);
}
