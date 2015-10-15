package org.kuali.coeus.award.finance;

import com.codiform.moo.annotation.CollectionProperty;

import org.kuali.coeus.sys.framework.rest.ResponseResults;

import java.util.Collection;

public class AccountResults extends ResponseResults {

    @CollectionProperty(source="results", itemClass=AccountDto.class)
    private Collection<AccountDto> accounts;

    @CollectionProperty(source="awards")
    private Collection<Long> awards;

    public Collection<Long> getAwards() {
        return awards;
    }

    public void setAwards(Collection<Long> awards) {
        this.awards = awards;
    }

    public Collection<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<AccountDto> accounts) {
        this.accounts = accounts;
    }


}
