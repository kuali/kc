package org.kuali.coeus.award.finance;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Property;
import org.kuali.coeus.sys.framework.Rest.ResponseResults;

import java.util.Collection;

public class AccountResults extends ResponseResults {

    @CollectionProperty(source="results", itemClass=AccountDto.class)
    private Collection<AccountDto> accounts;

    public Collection<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<AccountDto> accounts) {
        this.accounts = accounts;
    }

}
