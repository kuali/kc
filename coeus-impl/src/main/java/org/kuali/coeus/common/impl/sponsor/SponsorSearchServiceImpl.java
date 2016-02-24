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
package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.SponsorSearchResult;
import org.kuali.coeus.common.framework.sponsor.SponsorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("sponsorSearchService")
public class SponsorSearchServiceImpl implements SponsorSearchService {

    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;

    @Override
    public List<SponsorSearchResult> findSponsors(String searchString) {
        if (StringUtils.isBlank(searchString)) {
            throw new IllegalArgumentException("searchString is blank");
        }

        final String likeCriteria = "%" + searchString.toUpperCase() + "%";
        TypedQuery<SponsorSearchResult> query = entityManager.createQuery(
                "SELECT NEW org.kuali.coeus.common.framework.sponsor.SponsorSearchResult(t.sponsorCode, t.sponsorName) " +
                        "FROM Sponsor t " +
                        "WHERE UPPER(t.sponsorCode) like :likeCriteria OR UPPER(t.acronym) like :likeCriteria or UPPER(t.sponsorName) like :likeCriteria", SponsorSearchResult.class)
                .setParameter("likeCriteria", likeCriteria);

        return ListUtils.emptyIfNull(query.setMaxResults(25).getResultList());
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
