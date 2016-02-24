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
package org.kuali.coeus.common.impl.krms;

import org.kuali.coeus.common.framework.krms.KcKrmsCacheManager;
import org.kuali.rice.core.api.cache.CacheAdminService;
import org.kuali.rice.core.api.cache.CacheTarget;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.api.repository.proposition.PropositionDefinition;
import org.kuali.rice.krms.api.repository.rule.RuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("kcKrmsCacheManager")
public class KcKrmsCacheManagerImpl implements KcKrmsCacheManager {

    @Autowired
    @Qualifier("krmsCacheAdminService")
    private CacheAdminService krmsCacheAdminService;

    static final List<CacheTarget> cacheTargets= new ArrayList<CacheTarget>();
    static{
        cacheTargets.add(CacheTarget.entireCache(AgendaDefinition.Cache.NAME));
        cacheTargets.add(CacheTarget.entireCache(RuleDefinition.Cache.NAME));
        cacheTargets.add(CacheTarget.entireCache(PropositionDefinition.Cache.NAME));
    }

    @Override
    public void clearCache() {
        if(krmsCacheAdminService!=null){
            krmsCacheAdminService.flush(cacheTargets);
        }
    }

    public CacheAdminService getKrmsCacheAdminService() {
        return krmsCacheAdminService;
    }

    public void setKrmsCacheAdminService(CacheAdminService krmsCacheAdminService) {
        this.krmsCacheAdminService = krmsCacheAdminService;
    }
}
