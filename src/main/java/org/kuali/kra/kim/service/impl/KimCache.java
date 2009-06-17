/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.kim.service.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * The KIM Cache is used to store mappings between names and IDs.  In KIM, 
 * we always have to map names to IDs, this includes persons, namespaces, roles, 
 * and permissions.  Permissions are slightly different as explained below.
 * 
 * This cache was introduced in order to increase performance.
 */
public class KimCache {

    /**
     * The <code>nameIdMap</code> is used for simple mappings between
     * a name and an ID.  This is used by persons, roles, and namespaces.
     */
    private Map<String, Long> nameIdMap = new HashMap<String, Long>();
    
    /**
     * The <code>idNameIdMap</code> is used to handle permissions.  Unlike
     * persons, roles, and namespaces which are uniquely identified by their
     * name, permissions require a namespace and a permission name.  So, to
     * find a permission ID, we start with the namespace ID which maps to
     * a KimCache.  We can then use the permissions name to find its ID.
     */
    private Map<Long, KimCache> idNameIdMap = new HashMap<Long, KimCache>();
    
    /**
     * Get a ID for a name.
     * @param name the name
     * @return the ID or null if not found
     */
    public synchronized Long getId(String name) {
        return nameIdMap.get(name);
    }
    
    /**
     * Add an ID to the cache.
     * @param name the name
     * @param id the ID value
     */
    public synchronized void addId(String name, Long id) {
        nameIdMap.put(name, id);
    }
    
    /**
     * Get an ID based upon a 
     * @param namespaceId the namespace ID
     * @param name the name to look up
     * @return the ID or null if not found
     */
    public synchronized Long getId(Long namespaceId, String name) {
        KimCache kimCache = getCache(namespaceId);
        return kimCache.getId(name);
    }
    
    /**
     * Add an ID associated with a namespace and a name.
     * @param namespaceId the namespace ID
     * @param name the name
     * @param id the ID value
     */
    public synchronized void addId(Long namespaceId, String name, Long id) {
        KimCache kimCache = getCache(namespaceId);
        kimCache.addId(name, id);
    }
    
    /**
     * Get the cache for a namespace.
     * @param namespaceId the namespace ID
     * @return the associated cache of name/id mappings
     */
    private synchronized KimCache getCache(Long namespaceId) {
        KimCache kimCache = idNameIdMap.get(namespaceId);
        if (kimCache == null) {
            kimCache = new KimCache();
            idNameIdMap.put(namespaceId, kimCache);
        }
        return kimCache;
    }
}
