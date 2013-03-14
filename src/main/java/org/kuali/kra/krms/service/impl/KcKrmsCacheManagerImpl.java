/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.krms.service.impl;

import org.kuali.kra.krms.service.KcKrmsCacheManager;
import org.kuali.rice.core.impl.cache.DistributedCacheManagerDecorator;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.api.repository.proposition.PropositionDefinition;
import org.kuali.rice.krms.api.repository.rule.RuleDefinition;

public class KcKrmsCacheManagerImpl implements KcKrmsCacheManager {
    private DistributedCacheManagerDecorator krmsDistributedCacheManager;
    @Override
    public void clearCache() {
        if(krmsDistributedCacheManager!=null){
            krmsDistributedCacheManager.getCache(AgendaDefinition.Cache.NAME).clear();
            krmsDistributedCacheManager.getCache(RuleDefinition.Cache.NAME).clear();
            krmsDistributedCacheManager.getCache(PropositionDefinition.Cache.NAME).clear();
        }
    }
    /**
     * Gets the krmsDistributedCacheManager attribute. 
     * @return Returns the krmsDistributedCacheManager.
     */
    public DistributedCacheManagerDecorator getKrmsDistributedCacheManager() {
        return krmsDistributedCacheManager;
    }
    /**
     * Sets the krmsDistributedCacheManager attribute value.
     * @param krmsDistributedCacheManager The krmsDistributedCacheManager to set.
     */
    public void setKrmsDistributedCacheManager(DistributedCacheManagerDecorator krmsDistributedCacheManager) {
        this.krmsDistributedCacheManager = krmsDistributedCacheManager;
    }

}
