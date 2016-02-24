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
package org.kuali.coeus.sys.framework.persistence;

import org.kuali.rice.krad.util.MessageMap;

import java.util.Collection;
import java.util.List;

public interface PersistenceVerificationService {

    /**
     * Determines if a BusinessObject can safely be deleted by looking for other BusinessObjects that have direct
     * references to this business object.
     *
     * @param bo the Business Object. Cannot be bull.
     * @param ignoredRelationships any BusinessObject classes to ignore.  Cannot be null.
     * @return any messages related to the inability to safely delete the BusinessObject
     */
    MessageMap verifyRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships);

    /**
     * Determines if a BusinessObject can safely be updated by looking for whether references to other business objects are valid.
     *
     * @param bo the Business Object. Cannot be bull.
     * @param ignoredRelationships any BusinessObject classes to ignore.  Cannot be null.
     * @return any messages related to the inability to safely update the BusinessObject
     */
    MessageMap verifyRelationshipsForUpdate(Object bo, Collection<Class<?>> ignoredRelationships);

    /**
     * Determines if a BusinessObject can safely be inserted by looking for whether references to other business objects are valid.
     *
     * @param bo the Business Object. Cannot be bull.
     * @param ignoredRelationships any BusinessObject classes to ignore.  Cannot be null.
     * @return any messages related to the inability to safely insert the BusinessObject
     */
    MessageMap verifyRelationshipsForInsert(Object bo, Collection<Class<?>> ignoredRelationships);

    /**
     * Retrieves all persistable business object fields.  Will not return null.
     * @param boClazz the Business Object class. Cannot be bull.
     * @return a list of fields.  never null.
     */
    List<String> persistableFields(Class<?> boClazz);

    /**
     * Retrieves all business object primary key fields.  Will not return null.
     * @param boClazz the Business Object class. Cannot be bull.
     * @return a list of primary key fields.  never null.
     */
    List<String> pkFields(Class<?> boClazz);
}
