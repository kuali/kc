/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kns.bo.ModuleConfiguration;
import org.kuali.rice.kns.service.PersistenceService;
import org.springframework.beans.factory.InitializingBean;

/**
 * This class is a shim until KC gets to Rice 0.9.4 when we can override ModuleConfiguration.
 * In the meantime we can use this class to break up our ojb repository files.
 */
public class KraModuleConfiguration extends ModuleConfiguration implements InitializingBean {
    
    protected List<String> databaseRepositoryFilePaths;
    
    protected PersistenceService persistenceService;
    
    public KraModuleConfiguration() {
        databaseRepositoryFilePaths = new ArrayList<String>();
    }
    
    /**
     * @param databaseRepositoryFilePaths the databaseRepositoryFilePaths to set
     */
    public void setDatabaseRepositoryFilePaths(
            List<String> databaseRepositoryFilePaths) {
        this.databaseRepositoryFilePaths = databaseRepositoryFilePaths;
    }
    
//    public void afterPropertiesSet() throws Exception {
//        if (getDatabaseRepositoryFilePaths() != null) {
//            for (String repositoryLocation : getDatabaseRepositoryFilePaths()) {
//                // Need the OJB persistence service because it is the only one ever using the database repository files
//                if (persistenceService != null)
//                    persistenceService.loadRepositoryDescriptor(repositoryLocation);
//                else
//                    KNSServiceLocator.getPersistenceServiceOjb().loadRepositoryDescriptor(repositoryLocation);
//            }
//        }
 //   }
    
    /**
     * @return the databaseRepositoryFilePaths
     */
    public List<String> getDatabaseRepositoryFilePaths() {
        return this.databaseRepositoryFilePaths;
    }
    
    /**
     * @return the persistenceService
     */
    public PersistenceService getPersistenceService() {
        return this.persistenceService;
    }

    /**
     * @param persistenceService the persistenceService to set
     */
    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

}
