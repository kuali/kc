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
package org.kuali.kra.kim.service.impl;

import java.util.HashMap;
import java.util.Map;

public class KimCache {

    private Map<String, Long> cache = new HashMap<String, Long>();
    private Map<Long, KimCache> keyCache = new HashMap<Long, KimCache>();
    
    public synchronized Long getId(String name) {
        return cache.get(name);
    }
    
    public synchronized void addId(String name, Long id) {
        cache.put(name, id);
    }
    
    public synchronized Long getId(Long key, String name) {
        KimCache kimCache = getKimCache(key);
        return kimCache.getId(name);
    }
    
    public synchronized void addId(Long key, String name, Long id) {
        KimCache kimCache = getKimCache(key);
        kimCache.addId(name, id);
    }
    
    private synchronized KimCache getKimCache(Long key) {
        KimCache kimCache = keyCache.get(key);
        if (kimCache == null) {
            kimCache = new KimCache();
            keyCache.put(key, kimCache);
        }
        return kimCache;
    }
}
