package org.kuali.coeus.dc.updateuser.ip;

import org.kuali.coeus.dc.updateuser.AbstractUpdateUserDao;

public class InstitutionalProposalUpdateUserDaoImpl extends AbstractUpdateUserDao {

    private static final String INSTITUTE_PROPOSAL_DOCUMENT_SELECT_SQL = "select distinct u.document_number, u.document_number from INSTITUTE_PROPOSAL_DOCUMENT u where u.update_user = 'kr'";
    private static final String INSTITUTE_PROPOSAL_DOCUMENT_UPDATE_SQL = "update INSTITUTE_PROPOSAL_DOCUMENT u set u.update_user = ? where u.document_number = ? and u.update_user = 'kr'";

    private static final String INSTITUTE_PROPOSAL_SELECT_SQL = "select distinct u.document_number, u.document_number from PROPOSAL u where u.update_user = 'kr'";
    private static final String INSTITUTE_PROPOSAL_UPDATE_SQL = "update PROPOSAL u set u.update_user = ? where u.document_number = ? and u.update_user = 'kr'";

    private static final String PROPOSAL_IP_REVIEW_JOIN_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_IP_REVIEW_JOIN t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_IP_REVIEW_JOIN_UPDATE_SQL = "update PROPOSAL_IP_REVIEW_JOIN t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String IP_REVIEW_SELECT_SQL = "select distinct u.document_number, t.IP_REVIEW_ID from IP_REVIEW t, PROPOSAL u, PROPOSAL_IP_REVIEW_JOIN v where t.update_user = 'kr' and t.IP_REVIEW_ID = v.IP_REVIEW_ID and v.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String IP_REVIEW_UPDATE_SQL = "update IP_REVIEW t set t.update_user = ? where t.IP_REVIEW_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_IP_REV_ACTIVITY_SELECT_SQL = "select distinct u.document_number, t.IP_REVIEW_ID from PROPOSAL_IP_REV_ACTIVITY t, PROPOSAL u, PROPOSAL_IP_REVIEW_JOIN v where t.update_user = 'kr' and t.IP_REVIEW_ID = v.IP_REVIEW_ID and v.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_IP_REV_ACTIVITY_UPDATE_SQL = "update PROPOSAL_IP_REV_ACTIVITY t set t.update_user = ? where t.IP_REVIEW_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_UNIT_CONTACTS_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_UNIT_CONTACTS t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_UNIT_CONTACTS_UPDATE_SQL = "update PROPOSAL_UNIT_CONTACTS t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_COST_SHARING_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_COST_SHARING t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_COST_SHARING_UPDATE_SQL = "update PROPOSAL_COST_SHARING t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_IDC_RATE_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_IDC_RATE t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_IDC_RATE_UPDATE_SQL = "update PROPOSAL_IDC_RATE t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_CUSTOM_DATA_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_CUSTOM_DATA t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_CUSTOM_DATA_UPDATE_SQL = "update PROPOSAL_CUSTOM_DATA t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_NOTEPAD_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_NOTEPAD t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_NOTEPAD_UPDATE_SQL = "update PROPOSAL_NOTEPAD t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_SCIENCE_KEYWORD_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_SCIENCE_KEYWORD t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_SCIENCE_KEYWORD_UPDATE_SQL = "update PROPOSAL_SCIENCE_KEYWORD t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_SPECIAL_REVIEW_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_SPECIAL_REVIEW t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_SPECIAL_REVIEW_UPDATE_SQL = "update PROPOSAL_SPECIAL_REVIEW t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_EXEMPT_NUMBER_SELECT_SQL = "select distinct u.document_number, t.PROPOSAL_SPECIAL_REVIEW_ID from PROPOSAL_EXEMPT_NUMBER t, PROPOSAL u, PROPOSAL_SPECIAL_REVIEW v where t.update_user = 'kr' and v.PROPOSAL_ID = u.PROPOSAL_ID and t.PROPOSAL_SPECIAL_REVIEW_ID = v.PROPOSAL_SPECIAL_REVIEW_ID";
    private static final String PROPOSAL_EXEMPT_NUMBER_UPDATE_SQL = "update PROPOSAL_EXEMPT_NUMBER t set t.update_user = ? where t.PROPOSAL_SPECIAL_REVIEW_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_PERSONS_REVIEW_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_PERSONS t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_PERSONS_REVIEW_UPDATE_SQL = "update PROPOSAL_PERSONS t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_PERSON_UNITS_SELECT_SQL = "select distinct u.document_number, t.PROPOSAL_PERSON_ID from PROPOSAL_PERSON_UNITS t, PROPOSAL u, PROPOSAL_PERSONS v where t.update_user = 'kr' and v.PROPOSAL_ID = u.PROPOSAL_ID and v.PROPOSAL_PERSON_ID = t.PROPOSAL_PERSON_ID";
    private static final String PROPOSAL_PERSON_UNITS_UPDATE_SQL = "update PROPOSAL_PERSON_UNITS t set t.update_user = ? where t.PROPOSAL_PERSON_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_PERS_UNIT_CRED_SPLITS_SELECT_SQL = "select distinct u.document_number, t.PROPOSAL_PERSON_UNIT_ID from PROPOSAL_PERS_UNIT_CRED_SPLITS t, PROPOSAL u, PROPOSAL_PERSONS v, PROPOSAL_PERSON_UNITS w where t.update_user = 'kr' and v.PROPOSAL_ID = u.PROPOSAL_ID and v.PROPOSAL_PERSON_ID = w.PROPOSAL_PERSON_ID and w.PROPOSAL_PERSON_UNIT_ID = t.PROPOSAL_PERSON_UNIT_ID";
    private static final String PROPOSAL_PERS_UNIT_CRED_SPLITS_UPDATE_SQL = "update PROPOSAL_PERS_UNIT_CRED_SPLITS t set t.update_user = ? where t.PROPOSAL_PERSON_UNIT_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_PER_CREDIT_SPLIT_SELECT_SQL = "select distinct u.document_number, t.PROPOSAL_PERSON_ID from PROPOSAL_PER_CREDIT_SPLIT t, PROPOSAL u, PROPOSAL_PERSONS v where t.update_user = 'kr' and v.PROPOSAL_ID = u.PROPOSAL_ID and v.PROPOSAL_PERSON_ID = t.PROPOSAL_PERSON_ID";
    private static final String PROPOSAL_PER_CREDIT_SPLIT_UPDATE_SQL = "update PROPOSAL_PER_CREDIT_SPLIT t set t.update_user = ? where t.PROPOSAL_PERSON_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_COMMENTS_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_COMMENTS t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_COMMENTS_UPDATE_SQL = "update PROPOSAL_COMMENTS t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    private static final String PROPOSAL_ATTACHMENTS_SELECT_SQL = "select distinct u.document_number, u.PROPOSAL_ID from PROPOSAL_ATTACHMENTS t, PROPOSAL u where t.update_user = 'kr' and t.PROPOSAL_ID = u.PROPOSAL_ID";
    private static final String PROPOSAL_ATTACHMENTS_UPDATE_SQL = "update PROPOSAL_ATTACHMENTS t set t.update_user = ? where t.PROPOSAL_ID = ? and t.update_user = 'kr'";

    @Override
    public void fixUpdateUsers() {

        executeUserUpdate(INSTITUTE_PROPOSAL_DOCUMENT_SELECT_SQL, INSTITUTE_PROPOSAL_DOCUMENT_UPDATE_SQL);
        executeUserUpdate(INSTITUTE_PROPOSAL_SELECT_SQL, INSTITUTE_PROPOSAL_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_IP_REVIEW_JOIN_SELECT_SQL, PROPOSAL_IP_REVIEW_JOIN_UPDATE_SQL);
        executeUserUpdate(IP_REVIEW_SELECT_SQL, IP_REVIEW_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_IP_REV_ACTIVITY_SELECT_SQL, PROPOSAL_IP_REV_ACTIVITY_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_UNIT_CONTACTS_SELECT_SQL, PROPOSAL_UNIT_CONTACTS_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_COST_SHARING_SELECT_SQL, PROPOSAL_COST_SHARING_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_IDC_RATE_SELECT_SQL, PROPOSAL_IDC_RATE_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_CUSTOM_DATA_SELECT_SQL, PROPOSAL_CUSTOM_DATA_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_NOTEPAD_SELECT_SQL, PROPOSAL_NOTEPAD_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_SCIENCE_KEYWORD_SELECT_SQL, PROPOSAL_SCIENCE_KEYWORD_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_SPECIAL_REVIEW_SELECT_SQL, PROPOSAL_SPECIAL_REVIEW_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_EXEMPT_NUMBER_SELECT_SQL, PROPOSAL_EXEMPT_NUMBER_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_PERSONS_REVIEW_SELECT_SQL, PROPOSAL_PERSONS_REVIEW_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_PERSON_UNITS_SELECT_SQL, PROPOSAL_PERSON_UNITS_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_PERS_UNIT_CRED_SPLITS_SELECT_SQL, PROPOSAL_PERS_UNIT_CRED_SPLITS_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_PER_CREDIT_SPLIT_SELECT_SQL, PROPOSAL_PER_CREDIT_SPLIT_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_COMMENTS_SELECT_SQL, PROPOSAL_COMMENTS_UPDATE_SQL);
        executeUserUpdate(PROPOSAL_ATTACHMENTS_SELECT_SQL, PROPOSAL_ATTACHMENTS_UPDATE_SQL);
    }
}
