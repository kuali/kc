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
package org.kuali.kra.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kra.dao.PersonDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDaoJpa implements PersonDao {
    
    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    public String getUserName(String userId) {
        Query q = em.createQuery("select p.userName from Person p where p.personId = :userId");
        q.setParameter("userId", userId);        
        return (String) q.getSingleResult();
    }

}
