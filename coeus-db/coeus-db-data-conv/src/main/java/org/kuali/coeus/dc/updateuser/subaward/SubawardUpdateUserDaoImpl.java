package org.kuali.coeus.dc.updateuser.subaward;

import org.kuali.coeus.dc.updateuser.AbstractUpdateUserDao;

public class SubawardUpdateUserDaoImpl extends AbstractUpdateUserDao {

    private static final String SUBAWARD_DOCUMENT_SELECT_SQL = "select distinct u.document_number, u.document_number from SUBAWARD_DOCUMENT u where u.update_user = 'kr'";
    private static final String SUBAWARD_DOCUMENT_UPDATE_SQL = "update SUBAWARD_DOCUMENT u set u.update_user = ? where u.document_number = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_SELECT_SQL = "select distinct u.document_number, u.document_number from SUBAWARD u where u.update_user = 'kr'";
    private static final String SUBAWARD_UPDATE_SQL = "update SUBAWARD u set u.update_user = ? where u.document_number = ? and u.update_user = 'kr'";
    
    private static final String SUBAWARD_FUNDING_SOURCE_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_FUNDING_SOURCE t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_FUNDING_SOURCE_UPDATE_SQL = "update SUBAWARD_FUNDING_SOURCE u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_AMOUNT_INFO_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_AMOUNT_INFO t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_AMOUNT_INFO_UPDATE_SQL = "update SUBAWARD_AMOUNT_INFO u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_CONTACT_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_CONTACT t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_CONTACT_UPDATE_SQL = "update SUBAWARD_CONTACT u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_CLOSEOUT_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_CLOSEOUT t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_CLOSEOUT_UPDATE_SQL = "update SUBAWARD_CLOSEOUT u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_CUSTOM_DATA_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_CUSTOM_DATA t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_CUSTOM_DATA_UPDATE_SQL = "update SUBAWARD_CUSTOM_DATA u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_ATTACHMENTS_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_ATTACHMENTS t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_ATTACHMENTS_UPDATE_SQL = "update SUBAWARD_ATTACHMENTS u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_REPORTS_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_REPORTS t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_REPORTS_UPDATE_SQL = "update SUBAWARD_REPORTS u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    private static final String SUBAWARD_TEMPLATE_INFO_SELECT_SQL = "select distinct u.document_number, u.SUBAWARD_ID from SUBAWARD_TEMPLATE_INFO t, SUBAWARD u where t.update_user = 'kr' and t.SUBAWARD_ID = u.SUBAWARD_ID";
    private static final String SUBAWARD_TEMPLATE_INFO_UPDATE_SQL = "update SUBAWARD_TEMPLATE_INFO u set u.update_user = ? where u.SUBAWARD_ID = ? and u.update_user = 'kr'";

    @Override
    public void fixUpdateUsers() {
        executeUserUpdate(SUBAWARD_DOCUMENT_SELECT_SQL, SUBAWARD_DOCUMENT_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_SELECT_SQL, SUBAWARD_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_FUNDING_SOURCE_SELECT_SQL, SUBAWARD_FUNDING_SOURCE_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_AMOUNT_INFO_SELECT_SQL, SUBAWARD_AMOUNT_INFO_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_CONTACT_SELECT_SQL, SUBAWARD_CONTACT_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_CLOSEOUT_SELECT_SQL, SUBAWARD_CLOSEOUT_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_CUSTOM_DATA_SELECT_SQL, SUBAWARD_CUSTOM_DATA_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_ATTACHMENTS_SELECT_SQL, SUBAWARD_ATTACHMENTS_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_REPORTS_SELECT_SQL, SUBAWARD_REPORTS_UPDATE_SQL);
        executeUserUpdate(SUBAWARD_TEMPLATE_INFO_SELECT_SQL, SUBAWARD_TEMPLATE_INFO_UPDATE_SQL);
    }
}
