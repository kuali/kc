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
package org.kuali.coeus.award.finance.dao.jpa;

import org.apache.commons.collections4.ListUtils;
import org.kuali.coeus.award.finance.AwardAccount;
import org.kuali.coeus.award.finance.dao.AccountDao;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository("accountDao")
public class AccountDaoJpa implements AccountDao {

    public static final String ACCOUNT_NUMBER = "accountNumber";
    public static final String AWARD_ID = "awardId";
    public static final String ALL_ACCOUNTS_QUERY = "select a from AwardAccount a ORDER BY a.id ASC";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;


    @Override
    public List<AwardAccount> getAccounts(Integer startIndex, Integer size) {
        Query query = getEntityManager().createQuery(ALL_ACCOUNTS_QUERY, AwardAccount.class);
        if (startIndex != null && size != null) {
            query.setFirstResult(startIndex);
            query.setMaxResults(size);
        }
        List<AwardAccount> accounts = (List<AwardAccount>)query.getResultList();
        return ListUtils.emptyIfNull(accounts);
    }

    public AwardAccount getAccount(Long accountNumber) {
        AwardAccount account = getDataObjectService().findUnique(AwardAccount.class,
                QueryByCriteria.Builder.forAttribute(ACCOUNT_NUMBER, accountNumber).build());
        return account;
    }

    public AwardAccount saveAccount(AwardAccount account) {
        account = getDataObjectService().save(account);
        return account;
    }

    public List<Award> getLinkedAwards(Long accountNumber) {
        return (List<Award>) getBusinessObjectService().findMatching(Award.class, Collections.singletonMap(ACCOUNT_NUMBER, accountNumber));
    }

    public Award getAward(Long awardId) {
        return  getBusinessObjectService().findByPrimaryKey(Award.class, Collections.singletonMap(AWARD_ID, awardId));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



}
