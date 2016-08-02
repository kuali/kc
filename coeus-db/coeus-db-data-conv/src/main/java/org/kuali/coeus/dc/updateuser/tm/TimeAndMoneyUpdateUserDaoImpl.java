package org.kuali.coeus.dc.updateuser.tm;


import org.kuali.coeus.dc.updateuser.AbstractUpdateUserDao;

public class TimeAndMoneyUpdateUserDaoImpl extends AbstractUpdateUserDao {

    private static final String TIME_AND_MONEY_DOCUMENT_SELECT_SQL = "select distinct u.document_number, u.document_number from TIME_AND_MONEY_DOCUMENT u where u.update_user = 'kr'";
    private static final String TIME_AND_MONEY_DOCUMENT_UPDATE_SQL = "update TIME_AND_MONEY_DOCUMENT u set u.update_user = ? where u.document_number = ?";

    private static final String PENDING_TRANSACTIONS_SELECT_SQL = "select distinct u.document_number, u.document_number from PENDING_TRANSACTIONS u where u.update_user = 'kr'";
    private static final String PENDING_TRANSACTIONS_UPDATE_SQL = "update TIME_AND_MONEY_DOCUMENT u set u.update_user = ? where u.document_number = ?";

    private static final String AWARD_AMOUNT_TRANSACTION_SELECT_SQL = "select distinct u.document_number, u.document_number from AWARD_AMOUNT_TRANSACTION u where u.update_user = 'kr'";
    private static final String AWARD_AMOUNT_TRANSACTION_UPDATE_SQL = "update AWARD_AMOUNT_TRANSACTION u set u.update_user = ? where u.document_number = ?";

    @Override
    public void fixUpdateUsers() {
        executeUserUpdate(TIME_AND_MONEY_DOCUMENT_SELECT_SQL, TIME_AND_MONEY_DOCUMENT_UPDATE_SQL);
        executeUserUpdate(PENDING_TRANSACTIONS_SELECT_SQL, PENDING_TRANSACTIONS_UPDATE_SQL);
        executeUserUpdate(AWARD_AMOUNT_TRANSACTION_SELECT_SQL, AWARD_AMOUNT_TRANSACTION_UPDATE_SQL);
    }
}
