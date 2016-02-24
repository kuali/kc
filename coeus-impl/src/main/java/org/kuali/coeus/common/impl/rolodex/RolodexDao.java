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
package org.kuali.coeus.common.impl.rolodex;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.List;
import java.util.Map;

/**
 * Data Access Object for special needs of <code>{@link Rolodex}</code> like the <code>{@link NonOrganizationalRolodex}</code>
 * 
 * @see org.kuali.coeus.common.framework.rolodex.Rolodex
 * @see org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex
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
}
