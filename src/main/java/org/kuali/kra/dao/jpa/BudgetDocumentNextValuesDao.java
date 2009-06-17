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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.Query;

import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetDocumentNextValuesDao {

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @PostLoad
    @SuppressWarnings("unchecked")
    public void loadBudgetDocumentNextValues(Object entity) {
        if(entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        
        em.joinTransaction();
        BudgetDocument budgetDocument = (BudgetDocument) entity;
        
        Query q = em.createQuery("from DocumentNextValue dnv where dnv.documentKey = :documentkey");
        q.setParameter("documentkey", budgetDocument.getDocumentNumber());
        
        List<DocumentNextvalue> nextValues = (List<DocumentNextvalue>) q.getResultList(); 
        budgetDocument.setDocumentNextvalues(nextValues);
    }
    
}
