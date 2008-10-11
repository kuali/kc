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
package org.kuali.kra.proposaldevelopment.dao.jpa;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kra.proposaldevelopment.dao.AttachmentDao;
import org.springframework.transaction.annotation.Transactional;

public class AttachmentDaoImpl implements AttachmentDao {

    private EntityManager em;
    
    @PersistenceContext
    public void setAttachmentDao(EntityManager em) {
        this.em = em;
    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.dao.AttachmentDao#getNarrativeTimeStampAndUploadUser(java.lang.Integer, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public Iterator<Object[]> getNarrativeTimeStampAndUploadUser(Integer moduleNumber, String proposalNumber) {
        Query query = em.createQuery(buildNarrativeAttachmentQueryString());
        setNarrativeAttachmentQueryParameters(moduleNumber, proposalNumber, query);        
        return query.getResultList().iterator();        
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.dao.AttachmentDao#getPersonnelTimeStampAndUploadUser(java.lang.Integer, java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public Iterator<Object[]> getPersonnelTimeStampAndUploadUser(Integer proposalPersonNumber, String proposalNumber, Integer biographyNumber) {
        Query query = em.createQuery(buildProposalPersonBiographyAttachmentQueryString());
        setProposalPersonBiographyAttachmentQueryParameters(proposalPersonNumber, proposalNumber, biographyNumber, query);        
        return query.getResultList().iterator();
    }

    private void setNarrativeAttachmentQueryParameters(Integer moduleNumber, String proposalNumber, Query query) {
        query.setParameter("moduleNumber", moduleNumber);
        query.setParameter("proposalNumber", proposalNumber);
    }
    
    private void setProposalPersonBiographyAttachmentQueryParameters(Integer proposalPersonNumber, String proposalNumber,
                                                                        Integer biographyNumber, Query query) {
        query.setParameter("proposalPersonNumber", proposalPersonNumber);
        query.setParameter("proposalNumber", proposalNumber);
        query.setParameter("biographyNumber", biographyNumber);
    }
    
    private String buildNarrativeAttachmentQueryString() {
        StringBuilder sb = new StringBuilder("select na.updateTimestamp, na.updateUser from NarrativeAttachment na");
        sb.append(" where na.moduleNumber = :moduleNumber");
        sb.append(" and na.proposalNumber = :proposalNumber");
        String queryString = sb.toString();
        return queryString;
    }

    private String buildProposalPersonBiographyAttachmentQueryString() {
        StringBuilder sb = new StringBuilder("select ppba.updateTimestamp, ppba.updateUser from ProposalPersonBiographyAttachment ppba");
        sb.append(" where ppba.proposalPersonNumber = :proposalPersonNumber");
        sb.append(" and ppba.proposalNumber = :proposalNumber");
        sb.append(" and ppba.biographyNumber = :biographyNumber");
        String queryString = sb.toString();
        return queryString;
    }
}