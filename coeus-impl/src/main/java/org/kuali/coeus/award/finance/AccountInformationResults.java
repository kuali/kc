package org.kuali.coeus.award.finance;

import com.codiform.moo.annotation.CollectionProperty;

import org.kuali.coeus.sys.framework.rest.ResponseResults;

import java.util.Collection;

public class AccountInformationResults extends ResponseResults {

    @CollectionProperty(source="results", itemClass=AccountInformationDto.class)
    private Collection<AccountInformationDto> accountsInformation;

    public Collection<AccountInformationDto> getAccounts() {
        return accountsInformation;
    }

    public void setAccounts(Collection<AccountInformationDto> accountsInformation) {
        this.accountsInformation = accountsInformation;
    }
}
