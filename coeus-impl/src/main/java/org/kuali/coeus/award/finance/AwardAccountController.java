package org.kuali.coeus.award.finance;

import com.codiform.moo.Moo;
import com.codiform.moo.curry.Translate;

import org.kuali.coeus.award.finance.dao.AccountDao;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.summary.SearchResults;
import org.kuali.kra.award.home.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller("awardAccountController")
public class AwardAccountController extends RestController {

    public static final String NO_SUCH_ACCOUNT = "No such account.";
    public static final String NO_SUCH_AWARD = "No such award.";
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @RequestMapping(value="/v1/accounts")
    public @ResponseBody
    AccountResults getAccounts(@RequestParam(value = "startIndex", required = false) Integer startIndex,
                                     @RequestParam(value = "size", required = false) Integer size) {
        Moo moo = new Moo();
        return Translate.to(AccountResults.class).from(transformSearchResults(getAccountDao().getAccounts(startIndex, size), new ArrayList<>()));
    }

    @RequestMapping(method=RequestMethod.PUT, value="v1/accounts/{accountNumber}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public
    void updateAccount(@Valid @RequestBody AccountDto account, @PathVariable String accountNumber, HttpServletResponse response) throws Exception {
        AwardAccount currentAccount = accountDao.getAccount(accountNumber);
        if(Objects.isNull(currentAccount)) {
            sendErrorResponse(response, NO_SUCH_ACCOUNT);
        } else {
            if (account.getStatus() != null) currentAccount.setStatus(account.getStatus());
            if (account.getPending() != null) currentAccount.setPending(account.getPending());
            if (account.getBudgeted() != null) currentAccount.setBudgeted(account.getBudgeted());
            if (account.getAvailable() != null) currentAccount.setAvailable(account.getAvailable());
            if (account.getExpense() != null) currentAccount.setExpense(account.getExpense());
            if (account.getIncome() != null) currentAccount.setIncome(account.getIncome());

            accountDao.saveAccount(currentAccount);
        }

    }

    @RequestMapping(method=RequestMethod.GET, value="v1/accounts/{accountNumber}")
    public @ResponseBody
    AccountResults getAccount(@PathVariable String accountNumber,
                              @RequestParam(value = "showAwards", required = false) boolean showAwards,
                              HttpServletResponse response) throws Exception {
        AwardAccount account = accountDao.getAccount(accountNumber);
        ArrayList<AwardAccount> accounts = new ArrayList<>();
        accounts.add(account);
        List<Long> awardIds = new ArrayList<>();
        if(Objects.isNull(account)) {
            sendErrorResponse(response, NO_SUCH_ACCOUNT);
        }
        Moo moo = new Moo();
        if (showAwards) {
            List<Award> awards = accountDao.getLinkedAwards(accountNumber);
            awardIds = awards.stream().map(Award::getAwardId)
                    .collect(Collectors.toList());

        }
        return Translate.to(AccountResults.class).from(transformSearchResults(accounts, awardIds));
    }

    @RequestMapping(method=RequestMethod.GET, value="v1/accounts/awards/{awardId}")
    public @ResponseBody
    AccountInformationResults getAccountInformation(@PathVariable String awardId, HttpServletResponse response) throws Exception {
        Award award = accountDao.getAward(awardId);
        AccountInformationResults accountInformationResults = new AccountInformationResults();
        if(award == null) {
            sendErrorResponse(response, NO_SUCH_AWARD);
        } else {
            AccountInformationDto accountInformation = accountService.createAccountInformation(award);
            List<AccountInformationDto> accountsInformation = new ArrayList<>();
            accountsInformation.add(accountInformation);
            Moo moo = new Moo();
            accountInformationResults = Translate.to(AccountInformationResults.class).from(transformAwardInformation(accountsInformation));
            return accountInformationResults;
        }
        return accountInformationResults;
    }

    SearchResults<AccountInformationDto> transformAwardInformation(List<AccountInformationDto> accountsInformation) {
        SearchResults<AccountInformationDto> result = new SearchResults<>();
        result.setResults(accountsInformation);
        result.setTotalResults(accountsInformation.size());
        return result;
    }

    AccountSearchResults<AwardAccount> transformSearchResults(List<AwardAccount> accounts, List<Long> awardIds) {
        AccountSearchResults<AwardAccount> result = new AccountSearchResults<>();
        result.setResults(accounts);
        result.setTotalResults(accounts.size());
        result.setAwards(awardIds);
        return result;
    }

    protected void sendErrorResponse(HttpServletResponse response, String msg) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}
