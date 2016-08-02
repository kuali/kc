package org.kuali.coeus.dc.updateuser.award;

import org.kuali.coeus.dc.updateuser.AbstractUpdateUserDao;
import org.kuali.coeus.dc.updateuser.LastActionInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AwardUpdateUserDaoImpl extends AbstractUpdateUserDao {
    
    //award
    private static final String AWARD_DOCUMENT_SELECT_SQL = "select distinct u.document_number, u.document_number from award_document u where u.update_user = 'kr'";
    private static final String AWARD_DOCUMENT_UPDATE_SQL = "update award_document u set u.update_user = ? where u.document_number = ?";

    private static final String AWARD_SELECT_SQL = "select distinct u.document_number, u.document_number from award u where u.update_user = 'kr'";
    private static final String AWARD_UPDATE_SQL = "update award u set u.update_user = ? where u.document_number = ?";

    private static final String AWARD_COMMENT_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_COMMENT t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_COMMENT_UPDATE_SQL = "update AWARD_COMMENT t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_FUNDING_PROPOSALS_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_FUNDING_PROPOSALS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_FUNDING_PROPOSALS_UPDATE_SQL = "update AWARD_FUNDING_PROPOSALS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_PERSONS_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_PERSONS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_PERSONS_UPDATE_SQL = "update AWARD_PERSONS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_PERSON_UNITS_SELECT_SQL = "select distinct u.document_number, t.AWARD_PERSON_ID from AWARD_PERSON_UNITS t, award u, AWARD_PERSONS v where t.update_user = 'kr' and v.award_id = u.award_id and t.AWARD_PERSON_ID = v.AWARD_PERSON_ID";
    private static final String AWARD_PERSON_UNITS_UPDATE_SQL = "update AWARD_PERSON_UNITS t set t.update_user = ? where t.AWARD_PERSON_ID = ?";

    private static final String AWARD_PERSON_CREDIT_SPLITS_SELECT_SQL = "select distinct u.document_number, t.AWARD_PERSON_ID from AWARD_PERSON_CREDIT_SPLITS t, award u, AWARD_PERSONS v where t.update_user = 'kr' and v.award_id = u.award_id and t.AWARD_PERSON_ID = v.AWARD_PERSON_ID";
    private static final String AWARD_PERSON_CREDIT_SPLITS_UPDATE_SQL = "update AWARD_PERSON_CREDIT_SPLITS t set t.update_user = ? where t.AWARD_PERSON_ID = ?";

    private static final String AWARD_PERS_UNIT_CRED_SPLITS_SELECT_SQL = "select distinct u.document_number, t.AWARD_PERSON_UNIT_ID from AWARD_PERS_UNIT_CRED_SPLITS t, award u, AWARD_PERSONS v, AWARD_PERSON_UNITS w where t.update_user = 'kr' and v.award_id = u.award_id and w.AWARD_PERSON_ID = v.AWARD_PERSON_ID and t.AWARD_PERSON_UNIT_ID = w.AWARD_PERSON_UNIT_ID";
    private static final String AWARD_PERS_UNIT_CRED_SPLITS_UPDATE_SQL = "update AWARD_PERS_UNIT_CRED_SPLITS t set t.update_user = ? where t.AWARD_PERSON_UNIT_ID = ?";

    private static final String AWARD_COST_SHARE_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_COST_SHARE t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_COST_SHARE_UPDATE_SQL = "update AWARD_COST_SHARE t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_IDC_RATE_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_IDC_RATE t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_IDC_RATE_UPDATE_SQL = "update AWARD_IDC_RATE t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_REPORT_TERMS_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_REPORT_TERMS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_REPORT_TERMS_UPDATE_SQL = "update AWARD_REPORT_TERMS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_REP_TERMS_RECNT_SELECT_SQL = "select distinct u.document_number, t.AWARD_REPORT_TERMS_ID from AWARD_REP_TERMS_RECNT t, award u, AWARD_REPORT_TERMS v where t.update_user = 'kr' and v.award_id = u.award_id and t.AWARD_REPORT_TERMS_ID = v.AWARD_REPORT_TERMS_ID";
    private static final String AWARD_REP_TERMS_RECNT_UPDATE_SQL = "update AWARD_REP_TERMS_RECNT t set t.update_user = ? where t.AWARD_REPORT_TERMS_ID = ?";

    private static final String AWARD_APPROVED_SUBAWARDS_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_APPROVED_SUBAWARDS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_APPROVED_SUBAWARDS_UPDATE_SQL = "update AWARD_APPROVED_SUBAWARDS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_APPROVED_EQUIPMENT_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_APPROVED_EQUIPMENT t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_APPROVED_EQUIPMENT_UPDATE_SQL = "update AWARD_APPROVED_EQUIPMENT t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_APPROVED_FOREIGN_TRAVEL_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_APPROVED_FOREIGN_TRAVEL t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_APPROVED_FOREIGN_TRAVEL_UPDATE_SQL = "update AWARD_APPROVED_FOREIGN_TRAVEL t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_SCIENCE_KEYWORD_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_SCIENCE_KEYWORD t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_SCIENCE_KEYWORD_UPDATE_SQL = "update AWARD_SCIENCE_KEYWORD t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_SPECIAL_REVIEW_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_SPECIAL_REVIEW t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_SPECIAL_REVIEW_UPDATE_SQL = "update AWARD_SPECIAL_REVIEW t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_EXEMPT_NUMBER_SELECT_SQL = "select distinct u.document_number, t.AWARD_SPECIAL_REVIEW_ID from AWARD_EXEMPT_NUMBER t, award u, AWARD_SPECIAL_REVIEW v where t.update_user = 'kr' and v.award_id = u.award_id and t.AWARD_SPECIAL_REVIEW_ID = v.AWARD_SPECIAL_REVIEW_ID";
    private static final String AWARD_EXEMPT_NUMBER_UPDATE_SQL = "update AWARD_EXEMPT_NUMBER t set t.update_user = ? where t.AWARD_SPECIAL_REVIEW_ID = ?";

    private static final String AWARD_SPONSOR_TERM_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_SPONSOR_TERM t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_SPONSOR_TERM_UPDATE_SQL = "update AWARD_SPONSOR_TERM t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_PAYMENT_SCHEDULE_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_PAYMENT_SCHEDULE t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_PAYMENT_SCHEDULE_UPDATE_SQL = "update AWARD_PAYMENT_SCHEDULE t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_TRANSFERRING_SPONSOR_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_TRANSFERRING_SPONSOR t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_TRANSFERRING_SPONSOR_UPDATE_SQL = "update AWARD_TRANSFERRING_SPONSOR t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_AMT_FNA_DISTRIBUTION_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_AMT_FNA_DISTRIBUTION t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_AMT_FNA_DISTRIBUTION_UPDATE_SQL = "update AWARD_AMT_FNA_DISTRIBUTION t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_AMOUNT_INFO_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_AMOUNT_INFO t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_AMOUNT_INFO_UPDATE_SQL = "update AWARD_AMOUNT_INFO t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_UNIT_CONTACTS_SELELCT_SQL = "select distinct u.document_number, u.award_id from AWARD_UNIT_CONTACTS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_UNIT_CONTACTS_UPDATE_SQL = "update AWARD_UNIT_CONTACTS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_SPONSOR_CONTACTS_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_SPONSOR_CONTACTS t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_SPONSOR_CONTACTS_UPDATE_SQL = "update AWARD_SPONSOR_CONTACTS t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_CUSTOM_DATA_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_CUSTOM_DATA t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_CUSTOM_DATA_UPDATE_SQL = "update AWARD_CUSTOM_DATA t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_CLOSEOUT_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_CLOSEOUT t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_CLOSEOUT_UPDATE_SQL = "update AWARD_CLOSEOUT t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_NOTEPAD_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_NOTEPAD t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_NOTEPAD_UPDATE_SQL = "update AWARD_NOTEPAD t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_ATTACHMENT_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_ATTACHMENT t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_ATTACHMENT_UPDATE_SQL = "update AWARD_ATTACHMENT t set t.update_user = ? where t.award_id = ?";

    private static final String ATTACHMENT_FILE_SELECT_SQL = "select distinct u.document_number, t.file_id from attachment_file t, award u, AWARD_ATTACHMENT v where t.update_user = 'kr' and v.award_id = u.award_id and t.file_id = v.file_id";
    private static final String ATTACHMENT_FILE_UPDATE_SQL = "update attachment_file t set t.update_user = ? where t.file_id = ?";

    private static final String AWARD_SYNC_CHANGE_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_SYNC_CHANGE t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_SYNC_CHANGE_UPDATE_SQL = "update AWARD_SYNC_CHANGE t set t.update_user = ? where t.award_id = ?";

    private static final String AWARD_CGB_SELECT_SQL = "select distinct u.document_number, u.award_id from AWARD_CGB t, award u where t.update_user = 'kr' and t.award_id = u.award_id";
    private static final String AWARD_CGB_UPDATE_SQL = "update AWARD_CGB t set t.update_user = ? where t.award_id = ?";

    //award budget
    private static final String BUDGET_DOCUMENT_SELECT_SQL = "select distinct u.document_number, u.document_number from BUDGET_DOCUMENT u where u.update_user = 'kr'";
    private static final String BUDGET_DOCUMENT_UPDATE_SQL = "update BUDGET_DOCUMENT u set u.update_user = ? where u.document_number = ?";

    private static final String AWARD_BUDGET_EXT_SELECT_SQL = "select distinct u.document_number, u.document_number from AWARD_BUDGET_EXT u where u.update_user = 'kr'";
    private static final String AWARD_BUDGET_EXT_UPDATE_SQL = "update AWARD_BUDGET_EXT u set u.update_user = ? where u.document_number = ?";

    private static final String BUDGET_SELECT_SQL = "select distinct u.document_number, u.budget_id from BUDGET t, AWARD_BUDGET_EXT u where t.update_user = 'kr' and t.budget_id = u.budget_id";
    private static final String BUDGET_UPDATE_SQL = "update BUDGET t set t.update_user = ? where t.budget_id = ?";

    private static final String AWARD_BUDGET_PERIOD_EXT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERIOD_NUMBER from AWARD_BUDGET_PERIOD_EXT t, AWARD_BUDGET_EXT u, BUDGET_PERIODS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_PERIOD_NUMBER = v.BUDGET_PERIOD_NUMBER";
    private static final String AWARD_BUDGET_PERIOD_EXT_UPDATE_SQL = "update AWARD_BUDGET_PERIOD_EXT t set t.update_user = ? where t.BUDGET_PERIOD_NUMBER = ?";

    private static final String BUDGET_PERIODS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERIOD_NUMBER from BUDGET_PERIODS t, AWARD_BUDGET_EXT u, AWARD_BUDGET_PERIOD_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and t.BUDGET_PERIOD_NUMBER = v.BUDGET_PERIOD_NUMBER";
    private static final String BUDGET_PERIODS_UPDATE_SQL = "update BUDGET_PERIODS t set t.update_user = ? where t.BUDGET_PERIOD_NUMBER = ?";

    private static final String EPS_PROP_RATES_SELECT_SQL = "select distinct u.document_number, t.budget_id from EPS_PROP_RATES t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String EPS_PROP_RATES_UPDATE_SQL = "update EPS_PROP_RATES t set t.update_user = ? where t.budget_id = ?";

    private static final String EPS_PROP_LA_RATES_SELECT_SQL = "select distinct u.document_number, t.budget_id from EPS_PROP_LA_RATES t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String EPS_PROP_LA_RATES_UPDATE_SQL = "update EPS_PROP_LA_RATES t set t.update_user = ? where t.budget_id = ?";

    private static final String BUDGET_PERSONS_SELECT_SQL = "select distinct u.document_number, t.budget_id from BUDGET_PERSONS t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String BUDGET_PERSONS_UPDATE_SQL = "update BUDGET_PERSONS t set t.update_user = ? where t.budget_id = ?";

    private static final String BUDGET_PERSON_SALARY_DETAILS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERSON_SALARY_DETAIL_ID from BUDGET_PERSON_SALARY_DETAILS t, BUDGET u, AWARD_BUDGET_EXT v, BUDGET_PERSONS w where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id and u.BUDGET_ID = t.BUDGET_ID and w.PERSON_SEQUENCE_NUMBER = t.PERSON_SEQUENCE_NUMBER";
    private static final String BUDGET_PERSON_SALARY_DETAILS_UPDATE_SQL = "update BUDGET_PERSON_SALARY_DETAILS t set t.update_user = ? where t.BUDGET_PERSON_SALARY_DETAIL_ID = ?";

    private static final String EPS_PROP_COST_SHARING_SELECT_SQL = "select distinct u.document_number, t.budget_id from EPS_PROP_COST_SHARING t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String EPS_PROP_COST_SHARING_UPDATE_SQL = "update EPS_PROP_COST_SHARING t set t.update_user = ? where t.budget_id = ?";

    private static final String BUDGET_PROJECT_INCOME_SELECT_SQL = "select distinct u.document_number, t.budget_id from BUDGET_PROJECT_INCOME t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String BUDGET_PROJECT_INCOME_UPDATE_SQL = "update BUDGET_PROJECT_INCOME t set t.update_user = ? where t.budget_id = ?";

    private static final String EPS_PROP_IDC_RATE_SELECT_SQL = "select distinct u.document_number, t.budget_id from EPS_PROP_IDC_RATE t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String EPS_PROP_IDC_RATE_UPDATE_SQL = "update EPS_PROP_IDC_RATE t set t.update_user = ? where t.budget_id = ";

    private static final String AWARD_BUDGET_DETAILS_EXT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_DETAILS_ID from AWARD_BUDGET_DETAILS_EXT t, AWARD_BUDGET_EXT u, BUDGET_DETAILS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_DETAILS_ID = v.BUDGET_DETAILS_ID";
    private static final String AWARD_BUDGET_DETAILS_EXT_UPDATE_SQL = "update AWARD_BUDGET_DETAILS_EXT t set t.update_user = ? where t.BUDGET_DETAILS_ID = ?";

    private static final String BUDGET_DETAILS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_DETAILS_ID from BUDGET_DETAILS t, AWARD_BUDGET_EXT u, AWARD_BUDGET_DETAILS_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and t.BUDGET_DETAILS_ID = v.BUDGET_DETAILS_ID";
    private static final String BUDGET_DETAILS_UPDATE_SQL = "update BUDGET_DETAILS t set t.update_user = ? where t.BUDGET_DETAILS_ID = ?";

    private static final String BUDGET_RATE_AND_BASE_SELECT_SQL = "select distinct u.document_number, t.budget_id from BUDGET_RATE_AND_BASE t, BUDGET u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and u.budget_id = v.budget_id";
    private static final String BUDGET_RATE_AND_BASE_UPDATE_SQL = "update BUDGET_RATE_AND_BASE t set t.update_user = ? where t.budget_id = ?";

    private static final String BUD_FORMULATED_COST_DETAIL_SELECT_SQL = "select distinct u.document_number, t.BUDGET_DETAILS_ID from BUD_FORMULATED_COST_DETAIL t, BUDGET u, BUDGET_DETAILS v, AWARD_BUDGET_EXT w where t.update_user = 'kr' and t.BUDGET_DETAILS_ID = v.BUDGET_DETAILS_ID and v.BUDGET_ID = w.BUDGET_ID and w.BUDGET_ID = u.BUDGET_ID";
    private static final String BUD_FORMULATED_COST_DETAIL_UPDATE_SQL = "update BUD_FORMULATED_COST_DETAIL t set t.update_user = ? where t.BUDGET_DETAILS_ID = ?";

    private static final String AWD_BGT_DET_CAL_AMTS_EXT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_DETAILS_CAL_AMTS_ID from AWD_BGT_DET_CAL_AMTS_EXT t, AWARD_BUDGET_EXT u, BUDGET_DETAILS_CAL_AMTS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_DETAILS_CAL_AMTS_ID = v.BUDGET_DETAILS_CAL_AMTS_ID";
    private static final String AWD_BGT_DET_CAL_AMTS_EXT_UPDATE_SQL = "update AWD_BGT_DET_CAL_AMTS_EXT t set t.update_user = ? where t.BUDGET_DETAILS_CAL_AMTS_ID = ?";

    private static final String BUDGET_DETAILS_CAL_AMTS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_DETAILS_CAL_AMTS_ID from BUDGET_DETAILS_CAL_AMTS t, AWARD_BUDGET_EXT u, AWD_BGT_DET_CAL_AMTS_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and t.BUDGET_DETAILS_CAL_AMTS_ID = v.BUDGET_DETAILS_CAL_AMTS_ID";
    private static final String BUDGET_DETAILS_CAL_AMTS_UPDATE_SQL = "update BUDGET_DETAILS_CAL_AMTS t set t.update_user = ? where t.BUDGET_DETAILS_CAL_AMTS_ID = ?";

    private static final String AWD_BUDGET_PER_DET_EXT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERSONNEL_DETAILS_ID from AWD_BUDGET_PER_DET_EXT t, AWARD_BUDGET_EXT u, BUDGET_PERSONNEL_DETAILS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_PERSONNEL_DETAILS_ID = v.BUDGET_PERSONNEL_DETAILS_ID";
    private static final String AWD_BUDGET_PER_DET_EXT_UPDATE_SQL = "update AWD_BUDGET_PER_DET_EXT t set t.update_user = ? where t.BUDGET_PERSONNEL_DETAILS_ID = ?";

    private static final String BUDGET_PERSONNEL_DETAILS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERSONNEL_DETAILS_ID from BUDGET_PERSONNEL_DETAILS t, AWARD_BUDGET_EXT u, AWD_BUDGET_PER_DET_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and t.BUDGET_PERSONNEL_DETAILS_ID = v.BUDGET_PERSONNEL_DETAILS_ID";
    private static final String BUDGET_PERSONNEL_DETAILS_UPDATE_SQL = "update BUDGET_PERSONNEL_DETAILS t set t.update_user = ? where t.BUDGET_PERSONNEL_DETAILS_ID = ?";

    private static final String AWD_BUDGET_PER_CAL_AMTS_EXT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERSONNEL_CAL_AMTS_ID from AWD_BUDGET_PER_CAL_AMTS_EXT t, AWARD_BUDGET_EXT u, BUDGET_PERSONNEL_CAL_AMTS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_PERSONNEL_CAL_AMTS_ID = v.BUDGET_PERSONNEL_CAL_AMTS_ID";
    private static final String AWD_BUDGET_PER_CAL_AMTS_EXT_UPDATE_SQL = "update AWD_BUDGET_PER_CAL_AMTS_EXT t set t.update_user = ? where t.BUDGET_PERSONNEL_CAL_AMTS_ID = ?";

    private static final String BUDGET_PERSONNEL_CAL_AMTS_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERSONNEL_CAL_AMTS_ID from BUDGET_PERSONNEL_CAL_AMTS t, AWARD_BUDGET_EXT u, AWD_BUDGET_PER_CAL_AMTS_EXT v where t.update_user = 'kr' and t.budget_id = u.budget_id and t.BUDGET_PERSONNEL_CAL_AMTS_ID = v.BUDGET_PERSONNEL_CAL_AMTS_ID";
    private static final String BUDGET_PERSONNEL_CAL_AMTS_UPDATE_SQL = "update BUDGET_PERSONNEL_CAL_AMTS t set t.update_user = ? where t.BUDGET_PERSONNEL_CAL_AMTS_ID = ?";

    private static final String AWD_BGT_PER_SUM_CALC_AMT_SELECT_SQL = "select distinct u.document_number, t.BUDGET_PERIOD_ID from AWD_BGT_PER_SUM_CALC_AMT t, AWARD_BUDGET_EXT u, BUDGET_PERIODS v where t.update_user = 'kr' and v.budget_id = u.budget_id and t.BUDGET_PERIOD_ID = v.BUDGET_PERIOD_NUMBER";
    private static final String AWD_BGT_PER_SUM_CALC_AMT_UPDATE_SQL = "update AWD_BGT_PER_SUM_CALC_AMT t set t.update_user = ? where t.BUDGET_PERIOD_ID = ?";

    //award budget limit
    private static final String AWARD_BUDGET_LIMIT_SELECT_SQL = "select distinct u.document_number as award_document_number, u.award_id, v.document_number as budget_document_number, t.budget_id from AWARD_BUDGET_LIMIT t, award u, AWARD_BUDGET_EXT v where t.update_user = 'kr' and t.award_id = u.award_id and v.award_id = u.award_id and t.budget_id = v.budget_id";
    private static final String AWARD_BUDGET_LIMIT_AWARD_UPDATE_SQL = "update AWARD_BUDGET_LIMIT t set t.update_user = ? where t.award_id = ?";
    private static final String AWARD_BUDGET_LIMIT_BUDGET_UPDATE_SQL = "update AWARD_BUDGET_LIMIT t set t.update_user = ? where t.budget_id = ?";

    //document next values
    private static final String DOCUMENT_NEXTVALUE_AD_SELECT_SQL = "select distinct u.document_number, u.document_number from DOCUMENT_NEXTVALUE t, award_document u where t.update_user = 'kr' and t.DOCUMENT_NUMBER = u.DOCUMENT_NUMBER";
    private static final String DOCUMENT_NEXTVALUE_AD_UPDATE_SQL = "update DOCUMENT_NEXTVALUE t set t.update_user = ? where t.DOCUMENT_NUMBER = ?";

    private static final String DOCUMENT_NEXTVALUE_BD_SELECT_SQL = "select distinct u.document_number, u.document_number from DOCUMENT_NEXTVALUE t, BUDGET_DOCUMENT u where t.update_user = 'kr' and t.DOCUMENT_NUMBER = u.DOCUMENT_NUMBER";
    private static final String DOCUMENT_NEXTVALUE_BD_UPDATE_SQL = "update DOCUMENT_NEXTVALUE t set t.update_user = ? where t.DOCUMENT_NUMBER = ?";

    @Override
    public void fixUpdateUsers() {
        //award
        executeUserUpdate(AWARD_DOCUMENT_SELECT_SQL, AWARD_DOCUMENT_UPDATE_SQL);
        executeUserUpdate(AWARD_SELECT_SQL, AWARD_UPDATE_SQL);
        executeUserUpdate(AWARD_COMMENT_SELECT_SQL, AWARD_COMMENT_UPDATE_SQL);
        executeUserUpdate(AWARD_FUNDING_PROPOSALS_SELECT_SQL, AWARD_FUNDING_PROPOSALS_UPDATE_SQL);
        executeUserUpdate(AWARD_PERSONS_SELECT_SQL, AWARD_PERSONS_UPDATE_SQL);
        executeUserUpdate(AWARD_PERSON_UNITS_SELECT_SQL, AWARD_PERSON_UNITS_UPDATE_SQL);
        executeUserUpdate(AWARD_PERSON_CREDIT_SPLITS_SELECT_SQL, AWARD_PERSON_CREDIT_SPLITS_UPDATE_SQL);
        executeUserUpdate(AWARD_PERS_UNIT_CRED_SPLITS_SELECT_SQL, AWARD_PERS_UNIT_CRED_SPLITS_UPDATE_SQL);
        executeUserUpdate(AWARD_COST_SHARE_SELECT_SQL, AWARD_COST_SHARE_UPDATE_SQL);
        executeUserUpdate(AWARD_IDC_RATE_SELECT_SQL, AWARD_IDC_RATE_UPDATE_SQL);
        executeUserUpdate(AWARD_REPORT_TERMS_SELECT_SQL, AWARD_REPORT_TERMS_UPDATE_SQL);
        executeUserUpdate(AWARD_REP_TERMS_RECNT_SELECT_SQL, AWARD_REP_TERMS_RECNT_UPDATE_SQL);
        executeUserUpdate(AWARD_APPROVED_SUBAWARDS_SELECT_SQL, AWARD_APPROVED_SUBAWARDS_UPDATE_SQL);
        executeUserUpdate(AWARD_APPROVED_EQUIPMENT_SELECT_SQL, AWARD_APPROVED_EQUIPMENT_UPDATE_SQL);
        executeUserUpdate(AWARD_APPROVED_FOREIGN_TRAVEL_SELECT_SQL, AWARD_APPROVED_FOREIGN_TRAVEL_UPDATE_SQL);
        executeUserUpdate(AWARD_SCIENCE_KEYWORD_SELECT_SQL, AWARD_SCIENCE_KEYWORD_UPDATE_SQL);
        executeUserUpdate(AWARD_SPECIAL_REVIEW_SELECT_SQL, AWARD_SPECIAL_REVIEW_UPDATE_SQL);
        executeUserUpdate(AWARD_EXEMPT_NUMBER_SELECT_SQL, AWARD_EXEMPT_NUMBER_UPDATE_SQL);
        executeUserUpdate(AWARD_SPONSOR_TERM_SELECT_SQL, AWARD_SPONSOR_TERM_UPDATE_SQL);
        executeUserUpdate(AWARD_PAYMENT_SCHEDULE_SELECT_SQL, AWARD_PAYMENT_SCHEDULE_UPDATE_SQL);
        executeUserUpdate(AWARD_TRANSFERRING_SPONSOR_SELECT_SQL, AWARD_TRANSFERRING_SPONSOR_UPDATE_SQL);
        executeUserUpdate(AWARD_AMT_FNA_DISTRIBUTION_SELECT_SQL, AWARD_AMT_FNA_DISTRIBUTION_UPDATE_SQL);
        executeUserUpdate(AWARD_AMOUNT_INFO_SELECT_SQL, AWARD_AMOUNT_INFO_UPDATE_SQL);
        executeUserUpdate(AWARD_UNIT_CONTACTS_SELELCT_SQL, AWARD_UNIT_CONTACTS_UPDATE_SQL);
        executeUserUpdate(AWARD_SPONSOR_CONTACTS_SELECT_SQL, AWARD_SPONSOR_CONTACTS_UPDATE_SQL);
        executeUserUpdate(AWARD_CUSTOM_DATA_SELECT_SQL, AWARD_CUSTOM_DATA_UPDATE_SQL);
        executeUserUpdate(AWARD_CLOSEOUT_SELECT_SQL, AWARD_CLOSEOUT_UPDATE_SQL);
        executeUserUpdate(AWARD_NOTEPAD_SELECT_SQL, AWARD_NOTEPAD_UPDATE_SQL);
        executeUserUpdate(AWARD_ATTACHMENT_SELECT_SQL, AWARD_ATTACHMENT_UPDATE_SQL);
        executeUserUpdate(ATTACHMENT_FILE_SELECT_SQL, ATTACHMENT_FILE_UPDATE_SQL);
        executeUserUpdate(AWARD_SYNC_CHANGE_SELECT_SQL, AWARD_SYNC_CHANGE_UPDATE_SQL);
        executeUserUpdate(AWARD_CGB_SELECT_SQL, AWARD_CGB_UPDATE_SQL);

        //award budget
        executeUserUpdate(BUDGET_DOCUMENT_SELECT_SQL, BUDGET_DOCUMENT_UPDATE_SQL);
        executeUserUpdate(AWARD_BUDGET_EXT_SELECT_SQL, AWARD_BUDGET_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_SELECT_SQL, BUDGET_UPDATE_SQL);
        executeUserUpdate(AWARD_BUDGET_PERIOD_EXT_SELECT_SQL, AWARD_BUDGET_PERIOD_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_PERIODS_SELECT_SQL, BUDGET_PERIODS_UPDATE_SQL);
        executeUserUpdate(EPS_PROP_RATES_SELECT_SQL, EPS_PROP_RATES_UPDATE_SQL);
        executeUserUpdate(EPS_PROP_LA_RATES_SELECT_SQL, EPS_PROP_LA_RATES_UPDATE_SQL);
        executeUserUpdate(BUDGET_PERSONS_SELECT_SQL, BUDGET_PERSONS_UPDATE_SQL);
        executeUserUpdate(BUDGET_PERSON_SALARY_DETAILS_SELECT_SQL, BUDGET_PERSON_SALARY_DETAILS_UPDATE_SQL);
        executeUserUpdate(EPS_PROP_COST_SHARING_SELECT_SQL, EPS_PROP_COST_SHARING_UPDATE_SQL);
        executeUserUpdate(BUDGET_PROJECT_INCOME_SELECT_SQL, BUDGET_PROJECT_INCOME_UPDATE_SQL);
        executeUserUpdate(EPS_PROP_IDC_RATE_SELECT_SQL, EPS_PROP_IDC_RATE_UPDATE_SQL);
        executeUserUpdate(AWARD_BUDGET_DETAILS_EXT_SELECT_SQL, AWARD_BUDGET_DETAILS_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_DETAILS_SELECT_SQL, BUDGET_DETAILS_UPDATE_SQL);
        executeUserUpdate(BUDGET_RATE_AND_BASE_SELECT_SQL, BUDGET_RATE_AND_BASE_UPDATE_SQL);
        executeUserUpdate(BUD_FORMULATED_COST_DETAIL_SELECT_SQL, BUD_FORMULATED_COST_DETAIL_UPDATE_SQL);
        executeUserUpdate(AWD_BGT_DET_CAL_AMTS_EXT_SELECT_SQL, AWD_BGT_DET_CAL_AMTS_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_DETAILS_CAL_AMTS_SELECT_SQL, BUDGET_DETAILS_CAL_AMTS_UPDATE_SQL);
        executeUserUpdate(AWD_BUDGET_PER_DET_EXT_SELECT_SQL, AWD_BUDGET_PER_DET_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_PERSONNEL_DETAILS_SELECT_SQL, BUDGET_PERSONNEL_DETAILS_UPDATE_SQL);
        executeUserUpdate(AWD_BUDGET_PER_CAL_AMTS_EXT_SELECT_SQL, AWD_BUDGET_PER_CAL_AMTS_EXT_UPDATE_SQL);
        executeUserUpdate(BUDGET_PERSONNEL_CAL_AMTS_SELECT_SQL, BUDGET_PERSONNEL_CAL_AMTS_UPDATE_SQL);
        executeUserUpdate(AWD_BGT_PER_SUM_CALC_AMT_SELECT_SQL, AWD_BGT_PER_SUM_CALC_AMT_UPDATE_SQL);

        //document next values
        executeUserUpdate(DOCUMENT_NEXTVALUE_AD_SELECT_SQL, DOCUMENT_NEXTVALUE_AD_UPDATE_SQL);
        executeUserUpdate(DOCUMENT_NEXTVALUE_BD_SELECT_SQL, DOCUMENT_NEXTVALUE_BD_UPDATE_SQL);

        //award budget limit
        Connection connection = getConnectionDaoService().getCoeusConnection();
        try (PreparedStatement select = connection.prepareStatement(AWARD_BUDGET_LIMIT_SELECT_SQL);
             ResultSet result = select.executeQuery();
             PreparedStatement updateAward = connection.prepareStatement(AWARD_BUDGET_LIMIT_AWARD_UPDATE_SQL);
             PreparedStatement updateBudget = connection.prepareStatement(AWARD_BUDGET_LIMIT_BUDGET_UPDATE_SQL)) {

            while (result.next()) {
                final String awardDocumentNumber = result.getString(1);
                final String awardId = result.getString(2);
                final String budgetDocumentNumber = result.getString(3);
                final String budgetId = result.getString(4);
                final LastActionInfo awardLastActionInfo = getLastActionInfoCache().computeIfAbsent(awardDocumentNumber, getLastActionUserDao()::getLastActionInfo);
                final LastActionInfo budgetLastActionInfo = getLastActionInfoCache().computeIfAbsent(budgetDocumentNumber, getLastActionUserDao()::getLastActionInfo);
                if (useFirstLastActionInfo(awardLastActionInfo, budgetLastActionInfo)) {
                    executeUpdate(updateAward, awardDocumentNumber, awardId, awardLastActionInfo);
                } else {
                    executeUpdate(updateBudget, budgetDocumentNumber, budgetId, budgetLastActionInfo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
