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
package org.kuali.kra.dao;

import org.kuali.rice.krad.dao.LookupDao;

import java.util.Collection;

/**
 * This class is Custom lookup service Interface for kc development.
 */
public interface KraLookupDao extends LookupDao {

    /**
     * This method returns collection with wildcard in lookup string.
     * @param example
     * @param criteria
     * @param unbounded
     * @return
     */
    @SuppressWarnings("unchecked")
    Collection findCollectionUsingWildCard(Class businessObjectClass, String field, String wildCard, boolean unbounded);

}
