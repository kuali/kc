package org.kuali.coeus.award.finance.dao;

import org.kuali.coeus.award.finance.AwardAccount;
import org.kuali.kra.award.home.Award;

import java.util.List;

public interface AccountDao {

    public List<AwardAccount> getAccounts(Integer startIndex, Integer size);

    public AwardAccount getAccount(Long accountNumber);

    public AwardAccount saveAccount(AwardAccount account);

    public Award getAward(Long awardId);

    public List<Award> getLinkedAwards(Long accountNumber);

    }
