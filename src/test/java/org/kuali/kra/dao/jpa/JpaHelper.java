/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JpaHelper {
    private static final String ENTITY_MANAGER_FACTORY_BEAN_ID = "entityManagerFactory";
    
    
    private static EntityManagerFactory emf;
    
    private JpaHelper() { }
    
    public static EntityManager createEntityManager() {
        if(emf == null) {
            emf = (EntityManagerFactory) SpringHelper.getBean(ENTITY_MANAGER_FACTORY_BEAN_ID);
        }
        return emf.createEntityManager();
    }
    
    public static void closeEntityManagerfactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
