/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra;

import org.junit.After;
import org.junit.Before;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

/**
 * This class is the base class for all KCRA Tests requiring a Spring context with persistence, and preloaded test data
 */

@PerTestUnitTestData(
        @UnitTestData(order = { 
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
        sqlStatements = {
                      @UnitTestSql("alter table UNIT_ADMINISTRATOR disable constraint FK3_UNIT_ADMINISTRATOR_KRA"), 
                      @UnitTestSql("delete from institute_rates"),
                      @UnitTestSql("delete from institute_la_rates"), 
                      @UnitTestSql("delete from KRIM_GRP_ATTR_DATA_T"), 
                      @UnitTestSql("delete from KRIM_GRP_MBR_T"), 
                      @UnitTestSql("delete from KRIM_ROLE_MBR_ATTR_DATA_T"), 
                      @UnitTestSql("delete from KRIM_ROLE_MBR_T"), 
                      @UnitTestSql("delete from KRIM_ROLE_PERM_T"), 
                      @UnitTestSql("delete from KRIM_PERM_ATTR_DATA_T"), 
                      @UnitTestSql("delete from  KRIM_PERM_T"), 
                      @UnitTestSql("delete from KRIM_PERM_TMPL_T"), 
                      @UnitTestSql("delete from KRIM_ROLE_T"), 
                      @UnitTestSql("delete from UNIT_ACL"), 
                      @UnitTestSql("delete from KRIM_GRP_T"), 
                      @UnitTestSql("delete from PERSON_EXT_T"), 
                      @UnitTestSql("delete from  KRIM_PRNCPL_T where prncpl_nm not in ('quickstart')"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_BIO_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_CTZNSHP_T"), 
                      @UnitTestSql("delete from KRIM_ENTITY_ETHNIC_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_EXT_ID_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_EMP_INFO_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_AFLTN_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_EMAIL_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_PHONE_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_ADDR_T"), 
                      @UnitTestSql("delete from  KRIM_ENTITY_NM_T"), 
                      @UnitTestSql("delete from KRIM_ENTITY_ENT_TYP_T"), 
                      @UnitTestSql("delete from KRIM_ENTITY_T where entity_id not in (select entity_id from KRIM_PRNCPL_T where prncpl_nm = 'quickstart')"), 
                      @UnitTestSql("delete from KRIM_TYP_ATTR_T"), 
                      @UnitTestSql("delete from KRIM_ATTR_DEFN_T"), 
                      @UnitTestSql("delete from KRIM_TYP_T"), 
                      @UnitTestSql("delete from KRIM_EMP_STAT_T"), 
                      @UnitTestSql("delete from KRIM_CTZNSHP_STAT_T"), 
                      @UnitTestSql("delete from KRIM_EXT_ID_TYP_T"), 
                      @UnitTestSql("delete from KRIM_EMP_TYP_T"), 
                      @UnitTestSql("delete from KRIM_AFLTN_TYP_T"), 
                      @UnitTestSql("delete from KRIM_EMAIL_TYP_T"), 
                      @UnitTestSql("delete from KRIM_PHONE_TYP_T"), 
                      @UnitTestSql("delete from KRIM_ADDR_TYP_T"), 
                      @UnitTestSql("delete from KRIM_ENT_NM_TYP_T"), 
                      @UnitTestSql("delete from KRIM_ENT_TYP_T"), 
                      @UnitTestSql("delete from krns_nmspc_t where nmspc_cd in ('KR-SYS', 'KC-IDM', 'KC-UNT', 'KC-ADM', 'KC-SYS', 'KC-WKFLW')")                   
                      }, 
        sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/kim_test_data.sql", delimiter = ";"),
                 @UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_PERIOD_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPPING.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_COST_ELEMENT.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_EPS_PROP_PER_DOC_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_RATE_CLASS_TYPE.SQL", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_RATE_CLASS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_RATE_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CALC_TYPES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_JOB_CODE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CE_JOB_CODES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CE_RATE_TYPES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_abstract_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_activity_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_carrier_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_country_code.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_deadline_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_degree_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_eps_prop_person_role.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_fp_doc_group_t.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_fp_doc_type_t.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_unit.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_LA_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_inv_credit_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_mail_by.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_mail_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_narrative_status.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_narrative_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_note_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_notice_of_opportunity.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_nsf_codes.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_organization_type_list.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_organization.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_person.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_person_editable_fields.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_proposal_response.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_proposal_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_rights.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_roles.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_role_rights.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_rolodex.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_science_keyword.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sp_rev_approval_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_special_review.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sponsor_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sponsor.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_state_code.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_system_params.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_principal.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_unit_administrator_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_unit_administrator.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_user_roles.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_user_roles_all.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_valid_sp_rev_approval.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_budget_status.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_custom_attributes.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_appointment_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_ynq_explanation_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_ynq.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_s2s_submission_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_s2s_revision_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_exemption_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_ORGANIZATION_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_EPS_PROPOSAL_STATUS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_proposal_state.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_IDC_RATE_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_VULNERABLE_SUBJECT_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_COMMENT_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_FREQUENCY.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_FREQUENCY_BASE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_CONTACT_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_REPORT.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_REPORT_CLASS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_DISTRIBUTION.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CLASS_REPORT_FREQ.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_FREQUENCY_BASE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_COST_SHARE_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_SPONSOR_TERM_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_SPONSOR_TERM.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_BASIS_OF_PAYMENT.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_METHOD_OF_PAYMENT.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_ACCOUNT_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_STATUS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_research_areas.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_CLOSEOUT_REPORT_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_PROPOSAL_STATUS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_TRANSACTION_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_LOCATION_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_AWARD_ATTACHMENT_TYPE.sql", delimiter = ";")
                })
        )
public abstract class KraTestBase extends KcraNoDataTestBase {

    @Before
    public void setUp() throws Exception {
        setContextName("/kra-dev");
        setRelativeWebappRoot("/src/main/webapp");
        //setXmlFilename("classpath:DefaultTestData.xml");
        setSqlFilename("classpath:DefaultTestData.sql");
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
