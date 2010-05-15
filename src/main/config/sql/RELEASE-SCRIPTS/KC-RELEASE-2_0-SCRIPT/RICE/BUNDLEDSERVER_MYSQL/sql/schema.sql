delimiter /

# -----------------------------------------------------------------------
# ABSTRACT_TYPE
# -----------------------------------------------------------------------
drop table if exists ABSTRACT_TYPE
/

CREATE TABLE ABSTRACT_TYPE
(
      ABSTRACT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ABSTRACT_TYPEP1 PRIMARY KEY(ABSTRACT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ACCOUNT_TYPE
# -----------------------------------------------------------------------
drop table if exists ACCOUNT_TYPE
/

CREATE TABLE ACCOUNT_TYPE
(
      ACCOUNT_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ACCOUNT_TYPEP1 PRIMARY KEY(ACCOUNT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ACCT_DD_ATTR_DOC
# -----------------------------------------------------------------------
drop table if exists ACCT_DD_ATTR_DOC
/

CREATE TABLE ACCT_DD_ATTR_DOC
(
      DOC_HDR_ID VARCHAR(14)
        , OBJ_ID VARCHAR(36)
        , VER_NBR DECIMAL(14)
        , ACCT_NUM DECIMAL(14) NOT NULL
        , ACCT_OWNR VARCHAR(50) NOT NULL
        , ACCT_BAL DECIMAL(16,2) NOT NULL
        , ACCT_OPN_DAT DATETIME NOT NULL
        , ACCT_STAT VARCHAR(30) NOT NULL
        , ACCT_UPDATE_DT_TM DATETIME
        , ACCT_AWAKE VARCHAR(1)
    
    , CONSTRAINT ACCT_DD_ATTR_DOCP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ACTIVITY_TYPE
# -----------------------------------------------------------------------
drop table if exists ACTIVITY_TYPE
/

CREATE TABLE ACTIVITY_TYPE
(
      ACTIVITY_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ACTIVITY_TYPEP1 PRIMARY KEY(ACTIVITY_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AFFILIATION_TYPE
# -----------------------------------------------------------------------
drop table if exists AFFILIATION_TYPE
/

CREATE TABLE AFFILIATION_TYPE
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , AFFILIATION_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AFFILIATION_TYPEP1 PRIMARY KEY(AFFILIATION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# APPOINTMENT_TYPE
# -----------------------------------------------------------------------
drop table if exists APPOINTMENT_TYPE
/

CREATE TABLE APPOINTMENT_TYPE
(
      APPOINTMENT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , DURATION DECIMAL(2) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT APPOINTMENT_TYPEP1 PRIMARY KEY(APPOINTMENT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ARG_VALUE_LOOKUP
# -----------------------------------------------------------------------
drop table if exists ARG_VALUE_LOOKUP
/

CREATE TABLE ARG_VALUE_LOOKUP
(
      ARG_VALUE_LOOKUP_ID DECIMAL(12)
        , ARGUMENT_NAME VARCHAR(30) NOT NULL
        , VALUE VARCHAR(200) NOT NULL
        , DESCRIPTION VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ARG_VALUE_LOOKUPP1 PRIMARY KEY(ARG_VALUE_LOOKUP_ID)


    , INDEX UQ_ARG_VALUE_LOOKUP (ARGUMENT_NAME, VALUE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ATTACHMENT_FILE
# -----------------------------------------------------------------------
drop table if exists ATTACHMENT_FILE
/

CREATE TABLE ATTACHMENT_FILE
(
      FILE_ID DECIMAL(22)
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , FILE_NAME VARCHAR(150) NOT NULL
        , CONTENT_TYPE VARCHAR(250) NOT NULL
        , FILE_DATA LONGBLOB NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ATTACHMENT_FILEP1 PRIMARY KEY(FILE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD
# -----------------------------------------------------------------------
drop table if exists AWARD
/

CREATE TABLE AWARD
(
      CLOSEOUT_DATE DATETIME
        , TRANSACTION_TYPE_CODE VARCHAR(3)
        , NOTICE_DATE DATETIME
        , LEAD_UNIT_NUMBER VARCHAR(8)
        , ACTIVITY_TYPE_CODE DECIMAL(3) NOT NULL
        , AWARD_TYPE_CODE DECIMAL(3) NOT NULL
        , PRIME_SPONSOR_CODE VARCHAR(6)
        , CFDA_NUMBER VARCHAR(6)
        , METHOD_OF_PAYMENT_CODE VARCHAR(3)
        , DFAFS_NUMBER VARCHAR(20)
        , PRE_AWARD_AUTHORIZED_AMOUNT DECIMAL(12,2)
        , PRE_AWARD_EFFECTIVE_DATE DATETIME
        , PROCUREMENT_PRIORITY_CODE VARCHAR(6)
        , PROPOSAL_NUMBER VARCHAR(8)
        , SPECIAL_EB_RATE_OFF_CAMPUS DECIMAL(5,2)
        , SPECIAL_EB_RATE_ON_CAMPUS DECIMAL(5,2)
        , SUB_PLAN_FLAG VARCHAR(1)
        , TITLE VARCHAR(200) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , PRE_AWARD_IN_AUTHORIZED_AMOUNT DECIMAL(12,2)
        , ARCHIVE_LOCATION VARCHAR(50)
        , PRE_AWARD_INST_EFFECTIVE_DATE DATETIME
        , BASIS_OF_PAYMENT_CODE VARCHAR(3)
        , AWARD_ID DECIMAL(22)
        , DOCUMENT_NUMBER VARCHAR(10) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SPONSOR_CODE VARCHAR(6) NOT NULL
        , STATUS_CODE DECIMAL(3) NOT NULL
        , TEMPLATE_CODE DECIMAL(5)
        , ACCOUNT_NUMBER VARCHAR(7)
        , APPRVD_EQUIPMENT_INDICATOR VARCHAR(2) NOT NULL
        , APPRVD_FOREIGN_TRIP_INDICATOR VARCHAR(2) NOT NULL
        , APPRVD_SUBCONTRACT_INDICATOR VARCHAR(2) NOT NULL
        , AWARD_EFFECTIVE_DATE DATETIME
        , AWARD_EXECUTION_DATE DATETIME
        , BEGIN_DATE DATETIME
        , COST_SHARING_INDICATOR VARCHAR(2) NOT NULL
        , IDC_INDICATOR VARCHAR(2) NOT NULL
        , MODIFICATION_NUMBER VARCHAR(50)
        , NSF_CODE VARCHAR(15)
        , PAYMENT_SCHEDULE_INDICATOR VARCHAR(2) NOT NULL
        , SCIENCE_CODE_INDICATOR VARCHAR(2) NOT NULL
        , SPECIAL_REVIEW_INDICATOR VARCHAR(2) NOT NULL
        , SPONSOR_AWARD_NUMBER VARCHAR(70)
        , TRANSFER_SPONSOR_INDICATOR VARCHAR(2) NOT NULL
        , ACCOUNT_TYPE_CODE DECIMAL(3)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARDP1 PRIMARY KEY(AWARD_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_AMOUNT_INFO
# -----------------------------------------------------------------------
drop table if exists AWARD_AMOUNT_INFO
/

CREATE TABLE AWARD_AMOUNT_INFO
(
      ANTICIPATED_CHANGE_DIRECT DECIMAL(12,2)
        , ANTICIPATED_CHANGE_INDIRECT DECIMAL(12,2)
        , ANTICIPATED_TOTAL_DIRECT DECIMAL(12,2)
        , ANTICIPATED_TOTAL_INDIRECT DECIMAL(12,2)
        , OBLIGATED_TOTAL_DIRECT DECIMAL(12,2)
        , OBLIGATED_TOTAL_INDIRECT DECIMAL(12,2)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , TNM_DOCUMENT_NUMBER VARCHAR(10)
        , AWARD_AMOUNT_INFO_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ANTICIPATED_TOTAL_AMOUNT DECIMAL(12,2)
        , ANT_DISTRIBUTABLE_AMOUNT DECIMAL(12,2)
        , FINAL_EXPIRATION_DATE DATETIME
        , CURRENT_FUND_EFFECTIVE_DATE DATETIME
        , AMOUNT_OBLIGATED_TO_DATE DECIMAL(12,2)
        , OBLI_DISTRIBUTABLE_AMOUNT DECIMAL(12,2)
        , OBLIGATION_EXPIRATION_DATE DATETIME
        , TRANSACTION_ID DECIMAL(10)
        , ENTRY_TYPE CHAR(1)
        , EOM_PROCESS_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , ANTICIPATED_CHANGE DECIMAL(12,2)
        , OBLIGATED_CHANGE DECIMAL(12,2)
        , OBLIGATED_CHANGE_DIRECT DECIMAL(12,2)
        , OBLIGATED_CHANGE_INDIRECT DECIMAL(12,2)
        , OBJ_ID VARCHAR(36)
        , ORIGINATING_AWARD_VERSION DECIMAL(4)
    
    , CONSTRAINT AWARD_AMOUNT_INFOP1 PRIMARY KEY(AWARD_AMOUNT_INFO_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_AMOUNT_TRANSACTION
# -----------------------------------------------------------------------
drop table if exists AWARD_AMOUNT_TRANSACTION
/

CREATE TABLE AWARD_AMOUNT_TRANSACTION
(
      AWARD_AMOUNT_TRANSACTION_ID DECIMAL(12)
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , TRANSACTION_ID VARCHAR(10) NOT NULL
        , TRANSACTION_TYPE_CODE DECIMAL(3)
        , NOTICE_DATE DATETIME
        , COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_AMOUNT_TRANSACTIONP1 PRIMARY KEY(AWARD_AMOUNT_TRANSACTION_ID)


    , INDEX UQ_AWARD_AMOUNT_TRANSACTIONS (AWARD_NUMBER, TRANSACTION_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_AMT_FNA_DISTRIBUTION
# -----------------------------------------------------------------------
drop table if exists AWARD_AMT_FNA_DISTRIBUTION
/

CREATE TABLE AWARD_AMT_FNA_DISTRIBUTION
(
      AWARD_AMT_FNA_DISTRIBUTION_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , AMOUNT_SEQUENCE_NUMBER DECIMAL(4)
        , BUDGET_PERIOD DECIMAL(3)
        , START_DATE DATETIME
        , END_DATE DATETIME
        , DIRECT_COST DECIMAL(12,2)
        , INDIRECT_COST DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , AWARD_AMOUNT_INFO_ID DECIMAL(8)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_AMT_FNA_DISTRIBUTIONP1 PRIMARY KEY(AWARD_AMT_FNA_DISTRIBUTION_ID)


    , INDEX U_AWARD_AMT_FNA_DISTRIBUTION (AWARD_AMT_FNA_DISTRIBUTION_ID, AWARD_NUMBER, SEQUENCE_NUMBER, BUDGET_PERIOD)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_APPROVED_EQUIPMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_APPROVED_EQUIPMENT
/

CREATE TABLE AWARD_APPROVED_EQUIPMENT
(
      AWARD_APPROVED_EQUIPMENT_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ITEM VARCHAR(100) NOT NULL
        , VENDOR VARCHAR(50)
        , MODEL VARCHAR(50)
        , AMOUNT DECIMAL(12,2) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_APPROVED_EQUIPMENTP1 PRIMARY KEY(AWARD_APPROVED_EQUIPMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_APPROVED_FOREIGN_TRAVEL
# -----------------------------------------------------------------------
drop table if exists AWARD_APPROVED_FOREIGN_TRAVEL
/

CREATE TABLE AWARD_APPROVED_FOREIGN_TRAVEL
(
      AWARD_APPR_FORN_TRAVEL_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , TRAVELER_NAME VARCHAR(90)
        , DESTINATION VARCHAR(30) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME
        , AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_APPROVED_FOREIGN_TRAVP1 PRIMARY KEY(AWARD_APPR_FORN_TRAVEL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_APPROVED_SUBAWARDS
# -----------------------------------------------------------------------
drop table if exists AWARD_APPROVED_SUBAWARDS
/

CREATE TABLE AWARD_APPROVED_SUBAWARDS
(
      AWARD_APPROVED_SUBAWARD_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , ORGANIZATION_NAME VARCHAR(60)
        , AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , ORGANIZATION_ID VARCHAR(8)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_APPROVED_SUBAWARDSP1 PRIMARY KEY(AWARD_APPROVED_SUBAWARD_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_ATTACHMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_ATTACHMENT
/

CREATE TABLE AWARD_ATTACHMENT
(
      AWARD_ATTACHMENT_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , TYPE_CODE VARCHAR(3) NOT NULL
        , DOCUMENT_ID DECIMAL(4) NOT NULL
        , FILE_ID DECIMAL(22) NOT NULL
        , DESCRIPTION VARCHAR(200)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_ATTACHMENTP1 PRIMARY KEY(AWARD_ATTACHMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_ATTACHMENT_TYPE
# -----------------------------------------------------------------------
drop table if exists AWARD_ATTACHMENT_TYPE
/

CREATE TABLE AWARD_ATTACHMENT_TYPE
(
      TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_ATTACHMENT_TYPEP1 PRIMARY KEY(TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BASIS_OF_PAYMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_BASIS_OF_PAYMENT
/

CREATE TABLE AWARD_BASIS_OF_PAYMENT
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , BASIS_OF_PAYMENT_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_BASIS_OF_PAYMENTP1 PRIMARY KEY(BASIS_OF_PAYMENT_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BUDGET_DETAILS_EXT
# -----------------------------------------------------------------------
drop table if exists AWARD_BUDGET_DETAILS_EXT
/

CREATE TABLE AWARD_BUDGET_DETAILS_EXT
(
      BUDGET_DETAILS_ID DECIMAL(12)
        , OBLIGATED_AMOUNT DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_BUDGET_DETAILS_EXTP1 PRIMARY KEY(BUDGET_DETAILS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BUDGET_EXT
# -----------------------------------------------------------------------
drop table if exists AWARD_BUDGET_EXT
/

CREATE TABLE AWARD_BUDGET_EXT
(
      BUDGET_ID DECIMAL(12)
        , AWARD_BUDGET_STATUS_CODE VARCHAR(3) NOT NULL
        , AWARD_BUDGET_TYPE_CODE VARCHAR(3) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBLIGATED_AMOUNT DECIMAL(12,2)
        , BUDGET_INITIATOR VARCHAR(60)
        , DESCRIPTION VARCHAR(255)
        , DOCUMENT_NUMBER DECIMAL(10)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_BUDGET_EXTP1 PRIMARY KEY(BUDGET_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BUDGET_PERIOD_EXT
# -----------------------------------------------------------------------
drop table if exists AWARD_BUDGET_PERIOD_EXT
/

CREATE TABLE AWARD_BUDGET_PERIOD_EXT
(
      BUDGET_PERIOD_NUMBER DECIMAL(12)
        , OBLIGATED_AMOUNT DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_BUDGET_PERIOD_EXTP1 PRIMARY KEY(BUDGET_PERIOD_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BUDGET_STATUS
# -----------------------------------------------------------------------
drop table if exists AWARD_BUDGET_STATUS
/

CREATE TABLE AWARD_BUDGET_STATUS
(
      AWARD_BUDGET_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_BUDGET_STATUSP1 PRIMARY KEY(AWARD_BUDGET_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_BUDGET_TYPE
# -----------------------------------------------------------------------
drop table if exists AWARD_BUDGET_TYPE
/

CREATE TABLE AWARD_BUDGET_TYPE
(
      AWARD_BUDGET_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_BUDGET_TYPEP1 PRIMARY KEY(AWARD_BUDGET_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_CLOSEOUT
# -----------------------------------------------------------------------
drop table if exists AWARD_CLOSEOUT
/

CREATE TABLE AWARD_CLOSEOUT
(
      AWARD_CLOSEOUT_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , CLOSEOUT_REPORT_CODE VARCHAR(3) NOT NULL
        , CLOSEOUT_REPORT_NAME VARCHAR(100) NOT NULL
        , DUE_DATE DATETIME
        , FINAL_SUBMISSION_DATE DATETIME
        , MULTIPLE CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_CLOSEOUTP1 PRIMARY KEY(AWARD_CLOSEOUT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_COMMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_COMMENT
/

CREATE TABLE AWARD_COMMENT
(
      AWARD_COMMENT_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , COMMENT_TYPE_CODE VARCHAR(3)
        , CHECKLIST_PRINT_FLAG VARCHAR(1)
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_COMMENTP1 PRIMARY KEY(AWARD_COMMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_COST_SHARE
# -----------------------------------------------------------------------
drop table if exists AWARD_COST_SHARE
/

CREATE TABLE AWARD_COST_SHARE
(
      VERIFICATION_DATE DATETIME
        , COST_SHARE_MET DECIMAL(12,2)
        , AWARD_COST_SHARE_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , FISCAL_YEAR VARCHAR(4)
        , COST_SHARE_PERCENTAGE DECIMAL(5,2)
        , COST_SHARE_TYPE_CODE DECIMAL(3)
        , SOURCE VARCHAR(32)
        , DESTINATION VARCHAR(32)
        , COMMITMENT_AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_COST_SHAREP1 PRIMARY KEY(AWARD_COST_SHARE_ID)


    , INDEX U_AWARD_COST_SHARE (AWARD_NUMBER, SEQUENCE_NUMBER, FISCAL_YEAR, COST_SHARE_TYPE_CODE, SOURCE, DESTINATION)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_CUSTOM_DATA
# -----------------------------------------------------------------------
drop table if exists AWARD_CUSTOM_DATA
/

CREATE TABLE AWARD_CUSTOM_DATA
(
      AWARD_CUSTOM_DATA_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , CUSTOM_ATTRIBUTE_ID DECIMAL(8)
        , VALUE VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_CUSTOM_DATAP1 PRIMARY KEY(AWARD_CUSTOM_DATA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_DOCUMENT
/

CREATE TABLE AWARD_DOCUMENT
(
      DOCUMENT_NUMBER VARCHAR(10)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_EXEMPT_NUMBER
# -----------------------------------------------------------------------
drop table if exists AWARD_EXEMPT_NUMBER
/

CREATE TABLE AWARD_EXEMPT_NUMBER
(
      AWARD_EXEMPT_NUMBER_ID DECIMAL(12)
        , AWARD_SPECIAL_REVIEW_ID DECIMAL(12) NOT NULL
        , EXEMPTION_TYPE_CODE VARCHAR(3) NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_EXEMPT_NUMBERP1 PRIMARY KEY(AWARD_EXEMPT_NUMBER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_FUNDING_PROPOSALS
# -----------------------------------------------------------------------
drop table if exists AWARD_FUNDING_PROPOSALS
/

CREATE TABLE AWARD_FUNDING_PROPOSALS
(
      AWARD_FUNDING_PROPOSAL_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , PROPOSAL_ID DECIMAL(22) NOT NULL
        , UPDATE_TIMESTAMP TIMESTAMP NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTIVE CHAR(1)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_FUNDING_PROPOSALSP1 PRIMARY KEY(AWARD_FUNDING_PROPOSAL_ID)


    , INDEX U_AWD_FUND_PROP (AWARD_ID, PROPOSAL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_HIERARCHY
# -----------------------------------------------------------------------
drop table if exists AWARD_HIERARCHY
/

CREATE TABLE AWARD_HIERARCHY
(
      AWARD_HIERARCHY_ID DECIMAL(22)
        , ROOT_AWARD_NUMBER VARCHAR(12) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , PARENT_AWARD_NUMBER VARCHAR(12) NOT NULL
        , ORIGINATING_AWARD_NUMBER VARCHAR(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_HIERARCHYP1 PRIMARY KEY(AWARD_HIERARCHY_ID)


    , INDEX UQ_AWD_HIER_AWARD_NBR (AWARD_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_IDC_RATE
# -----------------------------------------------------------------------
drop table if exists AWARD_IDC_RATE
/

CREATE TABLE AWARD_IDC_RATE
(
      AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(8) NOT NULL
        , AWARD_IDC_RATE_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , APPLICABLE_IDC_RATE DECIMAL(5,2) NOT NULL
        , IDC_RATE_TYPE_CODE DECIMAL(3) NOT NULL
        , FISCAL_YEAR VARCHAR(4) NOT NULL
        , ON_CAMPUS_FLAG VARCHAR(1) NOT NULL
        , UNDERRECOVERY_OF_IDC DECIMAL(12,2)
        , SOURCE_ACCOUNT VARCHAR(32)
        , DESTINATION_ACCOUNT VARCHAR(32)
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_IDC_RATEP1 PRIMARY KEY(AWARD_IDC_RATE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_METHOD_OF_PAYMENT
# -----------------------------------------------------------------------
drop table if exists AWARD_METHOD_OF_PAYMENT
/

CREATE TABLE AWARD_METHOD_OF_PAYMENT
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , METHOD_OF_PAYMENT_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_METHOD_OF_PAYMENTP1 PRIMARY KEY(METHOD_OF_PAYMENT_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_NOTEPAD
# -----------------------------------------------------------------------
drop table if exists AWARD_NOTEPAD
/

CREATE TABLE AWARD_NOTEPAD
(
      AWARD_NOTEPAD_ID DECIMAL(12)
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , ENTRY_NUMBER DECIMAL(4) NOT NULL
        , COMMENTS LONGTEXT NOT NULL
        , RESTRICTED_VIEW CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , CREATE_TIMESTAMP DATETIME NOT NULL
        , NOTE_TOPIC VARCHAR(60) NOT NULL
        , AWARD_ID DECIMAL(22) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_NOTEPADP1 PRIMARY KEY(AWARD_NOTEPAD_ID)


    , INDEX UQ_AWARD_NOTEPAD (AWARD_NUMBER, ENTRY_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_PAYMENT_SCHEDULE
# -----------------------------------------------------------------------
drop table if exists AWARD_PAYMENT_SCHEDULE
/

CREATE TABLE AWARD_PAYMENT_SCHEDULE
(
      AWARD_PAYMENT_SCHEDULE_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , DUE_DATE DATETIME
        , AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , SUBMIT_DATE DATETIME
        , SUBMITTED_BY VARCHAR(9)
        , INVOICE_NUMBER VARCHAR(10)
        , STATUS_DESCRIPTION VARCHAR(50)
        , STATUS VARCHAR(5)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_PAYMENT_SCHEDULEP1 PRIMARY KEY(AWARD_PAYMENT_SCHEDULE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_PERSONS
# -----------------------------------------------------------------------
drop table if exists AWARD_PERSONS
/

CREATE TABLE AWARD_PERSONS
(
      KEY_PERSON_PROJECT_ROLE VARCHAR(60)
        , AWARD_PERSON_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , FULL_NAME VARCHAR(90)
        , CONTACT_ROLE_CODE VARCHAR(12) NOT NULL
        , ACADEMIC_YEAR_EFFORT DECIMAL(5,2)
        , CALENDAR_YEAR_EFFORT DECIMAL(5,2)
        , SUMMER_EFFORT DECIMAL(5,2)
        , TOTAL_EFFORT DECIMAL(5,2)
        , FACULTY_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_PERSONSP1 PRIMARY KEY(AWARD_PERSON_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_PERSON_CREDIT_SPLITS
# -----------------------------------------------------------------------
drop table if exists AWARD_PERSON_CREDIT_SPLITS
/

CREATE TABLE AWARD_PERSON_CREDIT_SPLITS
(
      AWARD_PERSON_CREDIT_SPLIT_ID DECIMAL(22)
        , AWARD_PERSON_ID DECIMAL(22) NOT NULL
        , INV_CREDIT_TYPE_CODE VARCHAR(3) NOT NULL
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_PERSON_CREDIT_SPLITSP1 PRIMARY KEY(AWARD_PERSON_CREDIT_SPLIT_ID)


    , INDEX UNQ_AP_CREDIT_SPLIT (AWARD_PERSON_ID, INV_CREDIT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_PERSON_UNITS
# -----------------------------------------------------------------------
drop table if exists AWARD_PERSON_UNITS
/

CREATE TABLE AWARD_PERSON_UNITS
(
      AWARD_PERSON_UNIT_ID DECIMAL(22)
        , AWARD_PERSON_ID DECIMAL(22) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , LEAD_UNIT_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_PERSON_UNITSP1 PRIMARY KEY(AWARD_PERSON_UNIT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_PERS_UNIT_CRED_SPLITS
# -----------------------------------------------------------------------
drop table if exists AWARD_PERS_UNIT_CRED_SPLITS
/

CREATE TABLE AWARD_PERS_UNIT_CRED_SPLITS
(
      APU_CREDIT_SPLIT_ID DECIMAL(22)
        , AWARD_PERSON_UNIT_ID DECIMAL(22) NOT NULL
        , INV_CREDIT_TYPE_CODE VARCHAR(3) NOT NULL
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_PERS_UNIT_CRED_SPLITSP1 PRIMARY KEY(APU_CREDIT_SPLIT_ID)


    , INDEX UNQ_APU_CREDIT_SPLIT (AWARD_PERSON_UNIT_ID, INV_CREDIT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_REPORT_TERMS
# -----------------------------------------------------------------------
drop table if exists AWARD_REPORT_TERMS
/

CREATE TABLE AWARD_REPORT_TERMS
(
      AWARD_REPORT_TERMS_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , REPORT_CLASS_CODE VARCHAR(3) NOT NULL
        , REPORT_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_CODE VARCHAR(3)
        , FREQUENCY_BASE_CODE VARCHAR(3)
        , OSP_DISTRIBUTION_CODE VARCHAR(3)
        , DUE_DATE DATETIME
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_REPORT_TERMSP1 PRIMARY KEY(AWARD_REPORT_TERMS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_REP_TERMS_RECNT
# -----------------------------------------------------------------------
drop table if exists AWARD_REP_TERMS_RECNT
/

CREATE TABLE AWARD_REP_TERMS_RECNT
(
      CONTACT_ID DECIMAL(12)
        , AWARD_REP_TERMS_RECNT_ID DECIMAL(12)
        , AWARD_REPORT_TERMS_ID DECIMAL(12) NOT NULL
        , CONTACT_TYPE_CODE VARCHAR(3) NOT NULL
        , ROLODEX_ID DECIMAL(6) NOT NULL
        , NUMBER_OF_COPIES DECIMAL(2)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_REP_TERMS_RECNTP1 PRIMARY KEY(AWARD_REP_TERMS_RECNT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_SCIENCE_KEYWORD
# -----------------------------------------------------------------------
drop table if exists AWARD_SCIENCE_KEYWORD
/

CREATE TABLE AWARD_SCIENCE_KEYWORD
(
      AWARD_SCIENCE_KEYWORD_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SCIENCE_KEYWORD_CODE VARCHAR(15) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_SCIENCE_KEYWORDP1 PRIMARY KEY(AWARD_SCIENCE_KEYWORD_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_SPECIAL_REVIEW
# -----------------------------------------------------------------------
drop table if exists AWARD_SPECIAL_REVIEW
/

CREATE TABLE AWARD_SPECIAL_REVIEW
(
      EXPIRATION_DATE DATETIME
        , AWARD_SPECIAL_REVIEW_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SPECIAL_REVIEW_NUMBER DECIMAL(3) NOT NULL
        , SPECIAL_REVIEW_CODE DECIMAL(3) NOT NULL
        , APPROVAL_TYPE_CODE DECIMAL(3) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , COMMENTS LONGTEXT
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_SPECIAL_REVIEWP1 PRIMARY KEY(AWARD_SPECIAL_REVIEW_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_SPONSOR_CONTACTS
# -----------------------------------------------------------------------
drop table if exists AWARD_SPONSOR_CONTACTS
/

CREATE TABLE AWARD_SPONSOR_CONTACTS
(
      AWARD_SPONSOR_CONTACT_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ROLODEX_ID DECIMAL(6)
        , FULL_NAME VARCHAR(90)
        , CONTACT_ROLE_CODE VARCHAR(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_SPONSOR_CONTACTSP1 PRIMARY KEY(AWARD_SPONSOR_CONTACT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_SPONSOR_TERM
# -----------------------------------------------------------------------
drop table if exists AWARD_SPONSOR_TERM
/

CREATE TABLE AWARD_SPONSOR_TERM
(
      AWARD_SPONSOR_TERM_ID DECIMAL(8)
        , AWARD_ID DECIMAL(22)
        , AWARD_NUMBER VARCHAR(12)
        , SEQUENCE_NUMBER DECIMAL(8)
        , SPONSOR_TERM_ID DECIMAL(12)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWARD_SPONSOR_TERMP1 PRIMARY KEY(AWARD_SPONSOR_TERM_ID)


    , INDEX U_AWARD_SPONSOR_TERM (SPONSOR_TERM_ID, AWARD_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_STATUS
# -----------------------------------------------------------------------
drop table if exists AWARD_STATUS
/

CREATE TABLE AWARD_STATUS
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_STATUSP1 PRIMARY KEY(STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPLATE
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPLATE
/

CREATE TABLE AWARD_TEMPLATE
(
      AWARD_TEMPLATE_CODE DECIMAL(5)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , STATUS_CODE VARCHAR(3) NOT NULL
        , PRIME_SPONSOR_CODE CHAR(6)
        , BASIS_OF_PAYMENT_CODE VARCHAR(3) NOT NULL
        , METHOD_OF_PAYMENT_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPLATEP1 PRIMARY KEY(AWARD_TEMPLATE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPLATE_COMMENTS
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPLATE_COMMENTS
/

CREATE TABLE AWARD_TEMPLATE_COMMENTS
(
      AWARD_TEMPLATE_COMMENTS_ID DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , AWARD_TEMPLATE_CODE DECIMAL(5) NOT NULL
        , COMMENT_TYPE_CODE VARCHAR(3) NOT NULL
        , CHECKLIST_PRINT_FLAG CHAR(1) NOT NULL
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPLATE_COMMENTSP1 PRIMARY KEY(AWARD_TEMPLATE_COMMENTS_ID)


    , INDEX UQ_AWARD_TEMPLATE_COMMENTS (AWARD_TEMPLATE_CODE, COMMENT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPLATE_CONTACT
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPLATE_CONTACT
/

CREATE TABLE AWARD_TEMPLATE_CONTACT
(
      AWARD_TEMPLATE_CONTACT_ID DECIMAL(12)
        , AWARD_TEMPLATE_CODE DECIMAL(5) NOT NULL
        , CONTACT_TYPE_CODE VARCHAR(3) NOT NULL
        , ROLODEX_ID DECIMAL(6) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPLATE_CONTACTP1 PRIMARY KEY(AWARD_TEMPLATE_CONTACT_ID)


    , INDEX UQ_AWARD_TEMPLATE_CONTACT (AWARD_TEMPLATE_CODE, CONTACT_TYPE_CODE, ROLODEX_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPLATE_REPORT_TERMS
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPLATE_REPORT_TERMS
/

CREATE TABLE AWARD_TEMPLATE_REPORT_TERMS
(
      TEMPLATE_REPORT_TERMS_ID DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , AWARD_TEMPLATE_CODE DECIMAL(5) NOT NULL
        , REPORT_CLASS_CODE VARCHAR(3) NOT NULL
        , REPORT_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_BASE_CODE VARCHAR(3) NOT NULL
        , OSP_DISTRIBUTION_CODE VARCHAR(3) NOT NULL
        , DUE_DATE DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPLATE_REPORT_TERMSP1 PRIMARY KEY(TEMPLATE_REPORT_TERMS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPLATE_TERMS
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPLATE_TERMS
/

CREATE TABLE AWARD_TEMPLATE_TERMS
(
      AWARD_TEMPLATE_TERMS_ID DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , AWARD_TEMPLATE_CODE DECIMAL(5) NOT NULL
        , SPONSOR_TERM_ID DECIMAL(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPLATE_TERMSP1 PRIMARY KEY(AWARD_TEMPLATE_TERMS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TEMPL_REP_TERMS_RECNT
# -----------------------------------------------------------------------
drop table if exists AWARD_TEMPL_REP_TERMS_RECNT
/

CREATE TABLE AWARD_TEMPL_REP_TERMS_RECNT
(
      TEMPL_REP_TERMS_RECNT_ID DECIMAL(12)
        , TEMPLATE_REPORT_TERMS_ID DECIMAL(12) NOT NULL
        , CONTACT_TYPE_CODE VARCHAR(3) NOT NULL
        , ROLODEX_ID DECIMAL(6) NOT NULL
        , NUMBER_OF_COPIES DECIMAL(2)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TEMPL_REP_TERMS_RECNTP1 PRIMARY KEY(TEMPL_REP_TERMS_RECNT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TRANSACTION_TYPE
# -----------------------------------------------------------------------
drop table if exists AWARD_TRANSACTION_TYPE
/

CREATE TABLE AWARD_TRANSACTION_TYPE
(
      AWARD_TRANSACTION_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , SHOW_IN_ACTION_SUMMARY VARCHAR(1) default 'Y' NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TRANSACTION_TYPEP1 PRIMARY KEY(AWARD_TRANSACTION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TRANSFERRING_SPONSOR
# -----------------------------------------------------------------------
drop table if exists AWARD_TRANSFERRING_SPONSOR
/

CREATE TABLE AWARD_TRANSFERRING_SPONSOR
(
      AWARD_TRANSFERRING_SPONSOR_ID DECIMAL(12)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SPONSOR_CODE CHAR(6) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TRANSFERRING_SPONSORP1 PRIMARY KEY(AWARD_TRANSFERRING_SPONSOR_ID)


    , INDEX UQ_AWARD_TRANSFERRING_SPONSOR (AWARD_NUMBER, SEQUENCE_NUMBER, SPONSOR_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_TYPE
# -----------------------------------------------------------------------
drop table if exists AWARD_TYPE
/

CREATE TABLE AWARD_TYPE
(
      AWARD_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT AWARD_TYPEP1 PRIMARY KEY(AWARD_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWARD_UNIT_CONTACTS
# -----------------------------------------------------------------------
drop table if exists AWARD_UNIT_CONTACTS
/

CREATE TABLE AWARD_UNIT_CONTACTS
(
      AWARD_UNIT_CONTACT_ID DECIMAL(22)
        , AWARD_ID DECIMAL(22) NOT NULL
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , FULL_NAME VARCHAR(90)
        , UNIT_ADMINISTRATOR_TYPE_CODE VARCHAR(3)
        , UNIT_CONTACT_TYPE VARCHAR(13) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , UNIT_ADMINISTRATOR_UNIT_NUMBER VARCHAR(12)
    
    , CONSTRAINT AWARD_UNIT_CONTACTSP1 PRIMARY KEY(AWARD_UNIT_CONTACT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWD_BGT_DET_CAL_AMTS_EXT
# -----------------------------------------------------------------------
drop table if exists AWD_BGT_DET_CAL_AMTS_EXT
/

CREATE TABLE AWD_BGT_DET_CAL_AMTS_EXT
(
      BUDGET_DETAILS_CAL_AMTS_ID DECIMAL(12)
        , OBLIGATED_AMOUNT DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWD_BGT_DET_CAL_AMTS_EXTP1 PRIMARY KEY(BUDGET_DETAILS_CAL_AMTS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWD_BUDGET_PER_CAL_AMTS_EXT
# -----------------------------------------------------------------------
drop table if exists AWD_BUDGET_PER_CAL_AMTS_EXT
/

CREATE TABLE AWD_BUDGET_PER_CAL_AMTS_EXT
(
      BUDGET_PERSONNEL_CAL_AMTS_ID DECIMAL(12)
        , OBLIGATED_AMOUNT DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWD_BUDGET_PER_CAL_AMTS_EXTP1 PRIMARY KEY(BUDGET_PERSONNEL_CAL_AMTS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# AWD_BUDGET_PER_DET_EXT
# -----------------------------------------------------------------------
drop table if exists AWD_BUDGET_PER_DET_EXT
/

CREATE TABLE AWD_BUDGET_PER_DET_EXT
(
      BUDGET_PERSONNEL_DETAILS_ID DECIMAL(12)
        , OBLIGATED_AMOUNT DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT AWD_BUDGET_PER_DET_EXTP1 PRIMARY KEY(BUDGET_PERSONNEL_DETAILS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BAK11202009_PERSON_EXT_T
# -----------------------------------------------------------------------
drop table if exists BAK11202009_PERSON_EXT_T
/

CREATE TABLE BAK11202009_PERSON_EXT_T
(
      PERSON_ID VARCHAR(40) NOT NULL
        , AGE_BY_FISCAL_YEAR DECIMAL(3)
        , RACE VARCHAR(30)
        , EDUCATION_LEVEL VARCHAR(30)
        , DEGREE VARCHAR(11)
        , MAJOR VARCHAR(30)
        , IS_HANDICAPPED CHAR(1)
        , HANDICAP_TYPE VARCHAR(30)
        , IS_VETERAN CHAR(1)
        , VETERAN_TYPE VARCHAR(30)
        , VISA_CODE VARCHAR(20)
        , VISA_TYPE VARCHAR(30)
        , VISA_RENEWAL_DATE DATETIME
        , HAS_VISA CHAR(1)
        , OFFICE_LOCATION VARCHAR(30)
        , SECONDRY_OFFICE_LOCATION VARCHAR(30)
        , SCHOOL VARCHAR(50)
        , YEAR_GRADUATED VARCHAR(30)
        , DIRECTORY_DEPARTMENT VARCHAR(30)
        , PRIMARY_TITLE VARCHAR(51)
        , DIRECTORY_TITLE VARCHAR(50)
        , HOME_UNIT VARCHAR(8)
        , IS_RESEARCH_STAFF CHAR(1)
        , VACATION_ACCURAL CHAR(1)
        , IS_ON_SABBATICAL CHAR(1)
        , ID_PROVIDED VARCHAR(30)
        , ID_VERIFIED VARCHAR(30)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , COUNTY VARCHAR(30)
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BATCH_CORRESPONDENCE
# -----------------------------------------------------------------------
drop table if exists BATCH_CORRESPONDENCE
/

CREATE TABLE BATCH_CORRESPONDENCE
(
      BATCH_CORRESPONDENCE_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , DAYS_TO_EVENT_UI_TEXT VARCHAR(400) NOT NULL
        , SEND_CORRESPONDENCE VARCHAR(10) NOT NULL
        , FINAL_ACTION_DAY DECIMAL(3)
        , FINAL_ACTION_TYPE_CODE VARCHAR(3)
        , FINAL_ACTION_CORRESP_TYPE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BATCH_CORRESPONDENCEP1 PRIMARY KEY(BATCH_CORRESPONDENCE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BATCH_CORRESPONDENCE_DETAIL
# -----------------------------------------------------------------------
drop table if exists BATCH_CORRESPONDENCE_DETAIL
/

CREATE TABLE BATCH_CORRESPONDENCE_DETAIL
(
      BATCH_CORRESPONDENCE_DETAIL_ID DECIMAL(12)
        , BATCH_CORRESPONDENCE_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTO_CORRESP_TYPE_CODE VARCHAR(3) NOT NULL
        , DAYS_TO_EVENT DECIMAL(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BATCH_CORRESPONDENCE_DETAILP1 PRIMARY KEY(BATCH_CORRESPONDENCE_DETAIL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET
# -----------------------------------------------------------------------
drop table if exists BUDGET
/

CREATE TABLE BUDGET
(
      BUDGET_ID DECIMAL(12)
        , BUDGET_JUSTIFICATION LONGTEXT
        , ON_OFF_CAMPUS_FLAG VARCHAR(1) default 'D' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , DOCUMENT_NUMBER DECIMAL(10) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , TOTAL_COST DECIMAL(12,2)
        , TOTAL_DIRECT_COST DECIMAL(12,2)
        , TOTAL_INDIRECT_COST DECIMAL(12,2)
        , COST_SHARING_AMOUNT DECIMAL(12,2)
        , UNDERRECOVERY_AMOUNT DECIMAL(12,2)
        , RESIDUAL_FUNDS DECIMAL(12,2)
        , TOTAL_COST_LIMIT DECIMAL(12,2)
        , OH_RATE_CLASS_CODE VARCHAR(3) NOT NULL
        , OH_RATE_TYPE_CODE VARCHAR(3)
        , COMMENTS LONGTEXT
        , FINAL_VERSION_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UR_RATE_CLASS_CODE VARCHAR(3) default '1' NOT NULL
        , MODULAR_BUDGET_FLAG VARCHAR(1) default 'N' NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGETP1 PRIMARY KEY(BUDGET_ID)


    , INDEX PK_BUDGET_KRA (PROPOSAL_NUMBER, VERSION_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_CATEGORY
# -----------------------------------------------------------------------
drop table if exists BUDGET_CATEGORY
/

CREATE TABLE BUDGET_CATEGORY
(
      BUDGET_CATEGORY_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , CATEGORY_TYPE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_CATEGORYP1 PRIMARY KEY(BUDGET_CATEGORY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_CATEGORY_MAPPING
# -----------------------------------------------------------------------
drop table if exists BUDGET_CATEGORY_MAPPING
/

CREATE TABLE BUDGET_CATEGORY_MAPPING
(
      MAPPING_NAME VARCHAR(100)
        , TARGET_CATEGORY_CODE VARCHAR(15)
        , COEUS_CATEGORY_CODE DECIMAL(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_CATEGORY_MAPPINGP1 PRIMARY KEY(MAPPING_NAME,TARGET_CATEGORY_CODE,COEUS_CATEGORY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_CATEGORY_MAPS
# -----------------------------------------------------------------------
drop table if exists BUDGET_CATEGORY_MAPS
/

CREATE TABLE BUDGET_CATEGORY_MAPS
(
      MAPPING_NAME VARCHAR(100)
        , TARGET_CATEGORY_CODE VARCHAR(15)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , CATEGORY_TYPE CHAR(200)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_CATEGORY_MAPSP1 PRIMARY KEY(MAPPING_NAME,TARGET_CATEGORY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_CATEGORY_TYPE
# -----------------------------------------------------------------------
drop table if exists BUDGET_CATEGORY_TYPE
/

CREATE TABLE BUDGET_CATEGORY_TYPE
(
      BUDGET_CATEGORY_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , SORT_ID DECIMAL(2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_CATEGORY_TYPEP1 PRIMARY KEY(BUDGET_CATEGORY_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_DETAILS
# -----------------------------------------------------------------------
drop table if exists BUDGET_DETAILS
/

CREATE TABLE BUDGET_DETAILS
(
      BUDGET_PERIOD_NUMBER DECIMAL(12) NOT NULL
        , GROUP_NAME VARCHAR(25)
        , BUDGET_ID DECIMAL(12)
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , BUDGET_CATEGORY_CODE DECIMAL(3) NOT NULL
        , COST_ELEMENT VARCHAR(8) NOT NULL
        , LINE_ITEM_DESCRIPTION VARCHAR(80)
        , BASED_ON_LINE_ITEM DECIMAL(3)
        , LINE_ITEM_SEQUENCE DECIMAL(3)
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , LINE_ITEM_COST DECIMAL(12,2)
        , COST_SHARING_AMOUNT DECIMAL(12,2)
        , UNDERRECOVERY_AMOUNT DECIMAL(12,2)
        , ON_OFF_CAMPUS_FLAG CHAR(1) NOT NULL
        , APPLY_IN_RATE_FLAG CHAR(1) NOT NULL
        , BUDGET_JUSTIFICATION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , QUANTITY DECIMAL(4)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , BUDGET_DETAILS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_DETAILSP1 PRIMARY KEY(BUDGET_DETAILS_ID)


    , INDEX PK_BUDGET_DETAILS_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, BUDGET_PERIOD, LINE_ITEM_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_DETAILS_CAL_AMTS
# -----------------------------------------------------------------------
drop table if exists BUDGET_DETAILS_CAL_AMTS
/

CREATE TABLE BUDGET_DETAILS_CAL_AMTS
(
      BUDGET_ID DECIMAL(12)
        , BUDGET_PERIOD_NUMBER DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , RATE_CLASS_CODE VARCHAR(3) NOT NULL
        , RATE_TYPE_CODE VARCHAR(3) NOT NULL
        , APPLY_RATE_FLAG CHAR(1) NOT NULL
        , CALCULATED_COST DECIMAL(12,2)
        , CALCULATED_COST_SHARING DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1
        , BUDGET_DETAILS_CAL_AMTS_ID DECIMAL(12)
        , BUDGET_DETAILS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
        , RATE_TYPE_DESCRIPTION VARCHAR(200)
    
    , CONSTRAINT BUDGET_DETAILS_CAL_AMTSP1 PRIMARY KEY(BUDGET_DETAILS_CAL_AMTS_ID)


    , INDEX PK_BUDGET_DETAILS_CAL_AMTS_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, BUDGET_PERIOD, LINE_ITEM_NUMBER, RATE_CLASS_CODE, RATE_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists BUDGET_DOCUMENT
/

CREATE TABLE BUDGET_DOCUMENT
(
      DOCUMENT_NUMBER VARCHAR(10)
        , PARENT_DOCUMENT_KEY VARCHAR(10)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , PARENT_DOCUMENT_TYPE_CODE VARCHAR(10)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_MODULAR
# -----------------------------------------------------------------------
drop table if exists BUDGET_MODULAR
/

CREATE TABLE BUDGET_MODULAR
(
      BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , DIRECT_COST_LESS_CONSOR_FNA DECIMAL(12,2)
        , CONSORTIUM_FNA DECIMAL(12,2)
        , TOTAL_DIRECT_COST DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_MODULARP1 PRIMARY KEY(BUDGET_PERIOD_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_MODULAR_IDC
# -----------------------------------------------------------------------
drop table if exists BUDGET_MODULAR_IDC
/

CREATE TABLE BUDGET_MODULAR_IDC
(
      BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , RATE_NUMBER DECIMAL(3)
        , DESCRIPTION VARCHAR(64)
        , IDC_RATE DECIMAL(5,2)
        , IDC_BASE DECIMAL(12,2)
        , FUNDS_REQUESTED DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_MODULAR_IDCP1 PRIMARY KEY(BUDGET_PERIOD_NUMBER,RATE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PERIODS
# -----------------------------------------------------------------------
drop table if exists BUDGET_PERIODS
/

CREATE TABLE BUDGET_PERIODS
(
      BUDGET_ID DECIMAL(12)
        , BUDGET_PERIOD_NUMBER DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , TOTAL_COST DECIMAL(12,2)
        , TOTAL_DIRECT_COST DECIMAL(12,2)
        , TOTAL_INDIRECT_COST DECIMAL(12,2)
        , COST_SHARING_AMOUNT DECIMAL(12,2)
        , UNDERRECOVERY_AMOUNT DECIMAL(12,2)
        , TOTAL_COST_LIMIT DECIMAL(12,2)
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PERIODSP1 PRIMARY KEY(BUDGET_PERIOD_NUMBER)


    , INDEX PK_BUDGET_PERIODS_KRA (VERSION_NUMBER, BUDGET_PERIOD, PROPOSAL_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PERIOD_TYPE
# -----------------------------------------------------------------------
drop table if exists BUDGET_PERIOD_TYPE
/

CREATE TABLE BUDGET_PERIOD_TYPE
(
      BUDGET_PERIOD_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PERIOD_TYPEP1 PRIMARY KEY(BUDGET_PERIOD_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PERSONNEL_CAL_AMTS
# -----------------------------------------------------------------------
drop table if exists BUDGET_PERSONNEL_CAL_AMTS
/

CREATE TABLE BUDGET_PERSONNEL_CAL_AMTS
(
      BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , PERSON_NUMBER DECIMAL(3) NOT NULL
        , RATE_CLASS_CODE VARCHAR(3) NOT NULL
        , RATE_TYPE_CODE VARCHAR(3) NOT NULL
        , APPLY_RATE_FLAG CHAR(1) NOT NULL
        , CALCULATED_COST DECIMAL(12,2)
        , CALCULATED_COST_SHARING DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1
        , BUDGET_PERSONNEL_CAL_AMTS_ID DECIMAL(12)
        , BUDGET_PERSONNEL_DETAILS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
        , RATE_TYPE_DESCRIPTION VARCHAR(200)
    
    , CONSTRAINT BUDGET_PERSONNEL_CAL_AMTSP1 PRIMARY KEY(BUDGET_PERSONNEL_CAL_AMTS_ID)


    , INDEX PK_BUDGET_PERS_CAL_AMTS_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, BUDGET_PERIOD, LINE_ITEM_NUMBER, PERSON_NUMBER, RATE_CLASS_CODE, RATE_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PERSONNEL_DETAILS
# -----------------------------------------------------------------------
drop table if exists BUDGET_PERSONNEL_DETAILS
/

CREATE TABLE BUDGET_PERSONNEL_DETAILS
(
      PERSON_SEQUENCE_NUMBER DECIMAL(3)
        , BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BUDGET_ID DECIMAL(12)
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , PERSON_NUMBER DECIMAL(3) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , JOB_CODE VARCHAR(6) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , PERIOD_TYPE VARCHAR(2)
        , LINE_ITEM_DESCRIPTION VARCHAR(80)
        , SEQUENCE_NUMBER DECIMAL(3)
        , SALARY_REQUESTED DECIMAL(12,2)
        , PERCENT_CHARGED DECIMAL(5,2)
        , PERCENT_EFFORT DECIMAL(5,2)
        , COST_SHARING_PERCENT DECIMAL(5,2)
        , COST_SHARING_AMOUNT DECIMAL(12,2)
        , UNDERRECOVERY_AMOUNT DECIMAL(12,2)
        , ON_OFF_CAMPUS_FLAG CHAR(1) NOT NULL
        , APPLY_IN_RATE_FLAG CHAR(1) NOT NULL
        , BUDGET_JUSTIFICATION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , BUDGET_PERSONNEL_DETAILS_ID DECIMAL(12)
        , BUDGET_DETAILS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PERSONNEL_DETAILSP1 PRIMARY KEY(BUDGET_PERSONNEL_DETAILS_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PERSONS
# -----------------------------------------------------------------------
drop table if exists BUDGET_PERSONS
/

CREATE TABLE BUDGET_PERSONS
(
      PERSON_SEQUENCE_NUMBER DECIMAL(3)
        , ROLODEX_ID DECIMAL(6)
        , APPOINTMENT_TYPE_CODE VARCHAR(3)
        , BUDGET_ID DECIMAL(12)
        , TBN_ID VARCHAR(9)
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , PERSON_ID VARCHAR(40)
        , JOB_CODE VARCHAR(6)
        , EFFECTIVE_DATE DATETIME NOT NULL
        , CALCULATION_BASE DECIMAL(12,2)
        , PERSON_NAME VARCHAR(90)
        , NON_EMPLOYEE_FLAG VARCHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PERSONSP1 PRIMARY KEY(PERSON_SEQUENCE_NUMBER,BUDGET_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PER_DET_RATE_AND_BASE
# -----------------------------------------------------------------------
drop table if exists BUDGET_PER_DET_RATE_AND_BASE
/

CREATE TABLE BUDGET_PER_DET_RATE_AND_BASE
(
      BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , PERSON_NUMBER DECIMAL(3) NOT NULL
        , RATE_NUMBER DECIMAL(3) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , RATE_CLASS_CODE VARCHAR(3) NOT NULL
        , RATE_TYPE_CODE VARCHAR(3) NOT NULL
        , ON_OFF_CAMPUS_FLAG VARCHAR(1) NOT NULL
        , APPLIED_RATE DECIMAL(5,2) NOT NULL
        , SALARY_REQUESTED DECIMAL(12,2)
        , BASE_COST_SHARING DECIMAL(14,2)
        , CALCULATED_COST DECIMAL(12,2)
        , CALCULATED_COST_SHARING DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1
        , UNDERRECOVERY_AMOUNT DECIMAL(12,2)
        , BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BGT_PER_DET_RATE_AND_BASE_ID DECIMAL(12)
        , BUDGET_PERSONNEL_DETAILS_ID DECIMAL(12)
        , BUDGET_PERSONNEL_CAL_AMTS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PER_DET_RATE_AND_BASP1 PRIMARY KEY(BGT_PER_DET_RATE_AND_BASE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_PROJECT_INCOME
# -----------------------------------------------------------------------
drop table if exists BUDGET_PROJECT_INCOME
/

CREATE TABLE BUDGET_PROJECT_INCOME
(
      BUDGET_ID DECIMAL(12)
        , BUDGET_PERIOD_NUMBER DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , BUDGET_VERSION_NUMBER DECIMAL(3)
        , PROJECT_INCOME_ID DECIMAL(5)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , AMOUNT DECIMAL(12,2) NOT NULL
        , DESCRIPTION VARCHAR(2000) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_PROJECT_INCOMEP1 PRIMARY KEY(BUDGET_ID,PROJECT_INCOME_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_RATE_AND_BASE
# -----------------------------------------------------------------------
drop table if exists BUDGET_RATE_AND_BASE
/

CREATE TABLE BUDGET_RATE_AND_BASE
(
      BUDGET_PERIOD_NUMBER DECIMAL(12)
        , BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(22)
        , BUDGET_PERIOD DECIMAL(3) NOT NULL
        , LINE_ITEM_NUMBER DECIMAL(3) NOT NULL
        , RATE_NUMBER DECIMAL(3) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , RATE_CLASS_CODE VARCHAR(3) NOT NULL
        , RATE_TYPE_CODE VARCHAR(3) NOT NULL
        , ON_OFF_CAMPUS_FLAG CHAR(1) NOT NULL
        , APPLIED_RATE DECIMAL(5,2) NOT NULL
        , BASE_COST DECIMAL(14,2)
        , BASE_COST_SHARING DECIMAL(14,2)
        , CALCULATED_COST DECIMAL(14,2)
        , CALCULATED_COST_SHARING DECIMAL(14,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1
        , BUDGET_RATE_AND_BASE_ID DECIMAL(12)
        , BUDGET_DETAILS_CAL_AMTS_ID DECIMAL(12)
        , BUDGET_DETAILS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_RATE_AND_BASEP1 PRIMARY KEY(BUDGET_RATE_AND_BASE_ID)


    , INDEX PK_BUDGET_RATE_AND_BASE_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, BUDGET_PERIOD, LINE_ITEM_NUMBER, RATE_NUMBER, RATE_CLASS_CODE, RATE_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_STATUS
# -----------------------------------------------------------------------
drop table if exists BUDGET_STATUS
/

CREATE TABLE BUDGET_STATUS
(
      BUDGET_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_STATUSP1 PRIMARY KEY(BUDGET_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_SUB_AWARDS
# -----------------------------------------------------------------------
drop table if exists BUDGET_SUB_AWARDS
/

CREATE TABLE BUDGET_SUB_AWARDS
(
      PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , SUB_AWARD_NUMBER DECIMAL(3)
        , ORGANIZATION_NAME VARCHAR(60) NOT NULL
        , SUB_AWARD_STATUS_CODE DECIMAL(3) NOT NULL
        , SUB_AWARD_XFD_FILE_NAME VARCHAR(256) NOT NULL
        , COMMENTS VARCHAR(2000)
        , XFD_UPDATE_USER VARCHAR(60)
        , XFD_UPDATE_TIMESTAMP DATETIME
        , TRANSLATION_COMMENTS VARCHAR(2000)
        , XML_UPDATE_USER VARCHAR(60)
        , XML_UPDATE_TIMESTAMP DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , BUDGET_ID DECIMAL(12)
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
        , SUB_AWARD_XFD_FILE LONGBLOB NOT NULL
        , SUB_AWARD_XML_FILE LONGTEXT NOT NULL
    
    , CONSTRAINT BUDGET_SUB_AWARDSP1 PRIMARY KEY(SUB_AWARD_NUMBER,BUDGET_ID)


    , INDEX PK_BUDGET_SUB_AWARDS_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, SUB_AWARD_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_SUB_AWARD_ATT
# -----------------------------------------------------------------------
drop table if exists BUDGET_SUB_AWARD_ATT
/

CREATE TABLE BUDGET_SUB_AWARD_ATT
(
      PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , SUB_AWARD_NUMBER DECIMAL(3) NOT NULL
        , CONTENT_ID VARCHAR(350) NOT NULL
        , CONTENT_TYPE VARCHAR(250)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , BUDGET_ID DECIMAL(12)
        , SUB_AWARD_ATTACHMENT_ID DECIMAL(12)
        , ATTACHMENT LONGBLOB NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT BUDGET_SUB_AWARD_ATTP1 PRIMARY KEY(SUB_AWARD_ATTACHMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# BUDGET_SUB_AWARD_FILES
# -----------------------------------------------------------------------
drop table if exists BUDGET_SUB_AWARD_FILES
/

CREATE TABLE BUDGET_SUB_AWARD_FILES
(
      BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , SUB_AWARD_NUMBER DECIMAL(3)
        , SUB_AWARD_XFD_FILE_NAME VARCHAR(256) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , SUB_AWARD_XFD_FILE LONGBLOB NOT NULL
        , SUB_AWARD_XML_FILE LONGTEXT NOT NULL
    
    , CONSTRAINT BUDGET_SUB_AWARD_FILESP1 PRIMARY KEY(BUDGET_ID,SUB_AWARD_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CARRIER_TYPE
# -----------------------------------------------------------------------
drop table if exists CARRIER_TYPE
/

CREATE TABLE CARRIER_TYPE
(
      CARRIER_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CARRIER_TYPEP1 PRIMARY KEY(CARRIER_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CLOSEOUT_REPORT_TYPE
# -----------------------------------------------------------------------
drop table if exists CLOSEOUT_REPORT_TYPE
/

CREATE TABLE CLOSEOUT_REPORT_TYPE
(
      CLOSEOUT_REPORT_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CLOSEOUT_REPORT_TYPEP1 PRIMARY KEY(CLOSEOUT_REPORT_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COEUS_MODULE
# -----------------------------------------------------------------------
drop table if exists COEUS_MODULE
/

CREATE TABLE COEUS_MODULE
(
      MODULE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COEUS_MODULEP1 PRIMARY KEY(MODULE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COEUS_SUB_MODULE
# -----------------------------------------------------------------------
drop table if exists COEUS_SUB_MODULE
/

CREATE TABLE COEUS_SUB_MODULE
(
      COEUS_SUB_MODULE_ID DECIMAL(12)
        , MODULE_CODE DECIMAL(3) NOT NULL
        , SUB_MODULE_CODE DECIMAL(3) NOT NULL
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COEUS_SUB_MODULEP1 PRIMARY KEY(COEUS_SUB_MODULE_ID)


    , INDEX UQ_COEUS_SUB_MODULE (MODULE_CODE, SUB_MODULE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMMENT_TYPE
# -----------------------------------------------------------------------
drop table if exists COMMENT_TYPE
/

CREATE TABLE COMMENT_TYPE
(
      COMMENT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200)
        , TEMPLATE_FLAG VARCHAR(1)
        , CHECKLIST_FLAG VARCHAR(1)
        , AWARD_COMMENT_SCREEN_FLAG VARCHAR(1)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT COMMENT_TYPEP1 PRIMARY KEY(COMMENT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMMITTEE
# -----------------------------------------------------------------------
drop table if exists COMMITTEE
/

CREATE TABLE COMMITTEE
(
      ID DECIMAL(12)
        , DOCUMENT_NUMBER DECIMAL(10) NOT NULL
        , COMMITTEE_ID VARCHAR(15) NOT NULL
        , COMMITTEE_NAME VARCHAR(60) NOT NULL
        , HOME_UNIT_NUMBER VARCHAR(8) NOT NULL
        , DESCRIPTION VARCHAR(2000)
        , SCHEDULE_DESCRIPTION VARCHAR(2000)
        , COMMITTEE_TYPE_CODE VARCHAR(3) NOT NULL
        , MINIMUM_MEMBERS_REQUIRED DECIMAL(3)
        , MAX_PROTOCOLS DECIMAL(4)
        , ADV_SUBMISSION_DAYS_REQ DECIMAL(3)
        , DEFAULT_REVIEW_TYPE_CODE VARCHAR(3)
        , APPLICABLE_REVIEW_TYPE_CODE VARCHAR(3) NOT NULL
        , CREATE_TIMESTAMP DATETIME
        , CREATE_USER VARCHAR(8)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMMITTEEP1 PRIMARY KEY(ID)


    , INDEX UQ_COMMITTEE_ID (SEQUENCE_NUMBER, COMMITTEE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMMITTEE_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists COMMITTEE_DOCUMENT
/

CREATE TABLE COMMITTEE_DOCUMENT
(
      DOCUMENT_NUMBER DECIMAL(10)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMMITTEE_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMMITTEE_TYPE
# -----------------------------------------------------------------------
drop table if exists COMMITTEE_TYPE
/

CREATE TABLE COMMITTEE_TYPE
(
      COMMITTEE_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMMITTEE_TYPEP1 PRIMARY KEY(COMMITTEE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_BATCH_CORRESP
# -----------------------------------------------------------------------
drop table if exists COMM_BATCH_CORRESP
/

CREATE TABLE COMM_BATCH_CORRESP
(
      COMM_BATCH_CORRESP_ID VARCHAR(10)
        , COMMITTEE_ID VARCHAR(15) NOT NULL
        , BATCH_CORRESPONDENCE_TYPE_CODE VARCHAR(3) NOT NULL
        , BATCH_RUN_DATE DATETIME NOT NULL
        , TIME_WINDOW_START DATETIME
        , TIME_WINDOW_END DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT COMM_BATCH_CORRESPP1 PRIMARY KEY(COMM_BATCH_CORRESP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_BATCH_CORRESP_DETAIL
# -----------------------------------------------------------------------
drop table if exists COMM_BATCH_CORRESP_DETAIL
/

CREATE TABLE COMM_BATCH_CORRESP_DETAIL
(
      COMM_BATCH_CORRESP_DETAIL_ID DECIMAL(12)
        , COMM_BATCH_CORRESP_ID VARCHAR(10) NOT NULL
        , PROTOCOL_ACTION_ID DECIMAL(12) NOT NULL
        , PROTOCOL_CORRESPONDENCE_ID DECIMAL(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT COMM_BATCH_CORRESP_DETAILP1 PRIMARY KEY(COMM_BATCH_CORRESP_DETAIL_ID)

    , CONSTRAINT UQ_COMM_BATCH_CORRESP_DETAIL UNIQUE (COMM_BATCH_CORRESP_ID, PROTOCOL_ACTION_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_MEMBERSHIPS
# -----------------------------------------------------------------------
drop table if exists COMM_MEMBERSHIPS
/

CREATE TABLE COMM_MEMBERSHIPS
(
      COMM_MEMBERSHIP_ID DECIMAL(12)
        , COMMITTEE_ID_FK DECIMAL(12) NOT NULL
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(12)
        , PERSON_NAME VARCHAR(90) NOT NULL
        , MEMBERSHIP_ID VARCHAR(10) NOT NULL
        , PAID_MEMBER_FLAG VARCHAR(1) NOT NULL
        , TERM_START_DATE DATETIME NOT NULL
        , TERM_END_DATE DATETIME
        , MEMBERSHIP_TYPE_CODE VARCHAR(3) NOT NULL
        , COMMENTS LONGTEXT
        , CONTACT_NOTES LONGTEXT
        , TRAINING_NOTES LONGTEXT
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_MEMBERSHIPSP1 PRIMARY KEY(COMM_MEMBERSHIP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_MEMBERSHIP_TYPE
# -----------------------------------------------------------------------
drop table if exists COMM_MEMBERSHIP_TYPE
/

CREATE TABLE COMM_MEMBERSHIP_TYPE
(
      MEMBERSHIP_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_MEMBERSHIP_TYPEP1 PRIMARY KEY(MEMBERSHIP_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_MEMBER_EXPERTISE
# -----------------------------------------------------------------------
drop table if exists COMM_MEMBER_EXPERTISE
/

CREATE TABLE COMM_MEMBER_EXPERTISE
(
      COMM_MEMBER_EXPERTISE_ID DECIMAL(12)
        , COMM_MEMBERSHIP_ID_FK DECIMAL(12) NOT NULL
        , RESEARCH_AREA_CODE VARCHAR(8) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_MEMBER_EXPERTISEP1 PRIMARY KEY(COMM_MEMBER_EXPERTISE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_MEMBER_ROLES
# -----------------------------------------------------------------------
drop table if exists COMM_MEMBER_ROLES
/

CREATE TABLE COMM_MEMBER_ROLES
(
      UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , COMM_MEMBER_ROLES_ID DECIMAL(12)
        , COMM_MEMBERSHIP_ID_FK DECIMAL(12) NOT NULL
        , MEMBERSHIP_ROLE_CODE VARCHAR(3) NOT NULL
        , START_DATE DATETIME NOT NULL
        , END_DATE DATETIME NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_MEMBER_ROLESP1 PRIMARY KEY(COMM_MEMBER_ROLES_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_RESEARCH_AREAS
# -----------------------------------------------------------------------
drop table if exists COMM_RESEARCH_AREAS
/

CREATE TABLE COMM_RESEARCH_AREAS
(
      ID DECIMAL(12)
        , COMMITTEE_ID_FK DECIMAL(12) NOT NULL
        , RESEARCH_AREA_CODE VARCHAR(8) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_RESEARCH_AREASP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE
/

CREATE TABLE COMM_SCHEDULE
(
      ID DECIMAL(12)
        , SCHEDULE_ID VARCHAR(10) NOT NULL
        , COMMITTEE_ID_FK DECIMAL(12) NOT NULL
        , SCHEDULED_DATE DATETIME NOT NULL
        , PLACE VARCHAR(200)
        , TIME DATETIME
        , PROTOCOL_SUB_DEADLINE DATETIME NOT NULL
        , SCHEDULE_STATUS_CODE DECIMAL(3) NOT NULL
        , MEETING_DATE DATETIME
        , START_TIME DATETIME
        , END_TIME DATETIME
        , AGENDA_PROD_REV_DATE DATETIME
        , MAX_PROTOCOLS DECIMAL(4)
        , COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_SCHEDULEP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE_ACT_ITEMS
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE_ACT_ITEMS
/

CREATE TABLE COMM_SCHEDULE_ACT_ITEMS
(
      COMM_SCHEDULE_ACT_ITEMS_ID DECIMAL(12)
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , ACTION_ITEM_NUMBER DECIMAL(4) NOT NULL
        , SCHEDULE_ACT_ITEM_TYPE_CODE VARCHAR(3) NOT NULL
        , ITEM_DESCTIPTION VARCHAR(2000) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_SCHEDULE_ACT_ITEMSP1 PRIMARY KEY(COMM_SCHEDULE_ACT_ITEMS_ID)


    , INDEX UQ_COMM_SCHEDULE_ACT_ITEMS (SCHEDULE_ID_FK, ACTION_ITEM_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE_ATTENDANCE
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE_ATTENDANCE
/

CREATE TABLE COMM_SCHEDULE_ATTENDANCE
(
      PERSON_NAME VARCHAR(90) NOT NULL
        , COMM_SCHEDULE_ATTENDANCE_ID DECIMAL(12)
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , GUEST_FLAG VARCHAR(1) NOT NULL
        , ALTERNATE_FLAG VARCHAR(1) NOT NULL
        , ALTERNATE_FOR VARCHAR(40)
        , NON_EMPLOYEE_FLAG VARCHAR(1) NOT NULL
        , COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_SCHEDULE_ATTENDANCEP1 PRIMARY KEY(COMM_SCHEDULE_ATTENDANCE_ID)


    , INDEX UQ_COMM_SCHEDULE_ATTENDANCE (SCHEDULE_ID_FK, PERSON_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE_FREQUENCY
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE_FREQUENCY
/

CREATE TABLE COMM_SCHEDULE_FREQUENCY
(
      FREQUENCY_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , NO_OF_DAYS DECIMAL(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_SCHEDULE_FREQUENCYP1 PRIMARY KEY(FREQUENCY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE_MINUTES
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE_MINUTES
/

CREATE TABLE COMM_SCHEDULE_MINUTES
(
      FINAL_FLAG VARCHAR(1)
        , REVIEWER_ID_FK DECIMAL(12)
        , COMM_SCHEDULE_MINUTES_ID DECIMAL(12)
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_ID_FK DECIMAL(12)
        , ENTRY_NUMBER DECIMAL(12) NOT NULL
        , MINUTE_ENTRY_TYPE_CODE VARCHAR(3) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12)
        , PRIVATE_COMMENT_FLAG VARCHAR(1)
        , PROTOCOL_CONTINGENCY_CODE VARCHAR(4)
        , MINUTE_ENTRY LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COMM_SCHEDULE_MINUTESP1 PRIMARY KEY(COMM_SCHEDULE_MINUTES_ID)


    , INDEX UQ_COMM_SCHEDULE_MINUTES (SCHEDULE_ID_FK, ENTRY_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COMM_SCHEDULE_MINUTE_DOC
# -----------------------------------------------------------------------
drop table if exists COMM_SCHEDULE_MINUTE_DOC
/

CREATE TABLE COMM_SCHEDULE_MINUTE_DOC
(
      COMM_SCHEDULE_MINUTE_DOC_ID DECIMAL(12)
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , MINUTE_NUMBER DECIMAL(4) NOT NULL
        , MINUTE_NAME VARCHAR(200) NOT NULL
        , CREATE_TIMESTAMP DATETIME NOT NULL
        , CREATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , PDF_STORE LONGBLOB NOT NULL
    
    , CONSTRAINT COMM_SCHEDULE_MINUTE_DOCP1 PRIMARY KEY(COMM_SCHEDULE_MINUTE_DOC_ID)


    , INDEX UQ_COMM_SCHEDULE_MINUTE_DOC (SCHEDULE_ID_FK, MINUTE_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CONTACT_TYPE
# -----------------------------------------------------------------------
drop table if exists CONTACT_TYPE
/

CREATE TABLE CONTACT_TYPE
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , CONTACT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CONTACT_TYPEP1 PRIMARY KEY(CONTACT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CORRESPONDENT_TYPE
# -----------------------------------------------------------------------
drop table if exists CORRESPONDENT_TYPE
/

CREATE TABLE CORRESPONDENT_TYPE
(
      CORRESPONDENT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , QUALIFIER VARCHAR(1) default 'p' NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CORRESPONDENT_TYPEP1 PRIMARY KEY(CORRESPONDENT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COST_ELEMENT
# -----------------------------------------------------------------------
drop table if exists COST_ELEMENT
/

CREATE TABLE COST_ELEMENT
(
      COST_ELEMENT VARCHAR(8)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , BUDGET_CATEGORY_CODE VARCHAR(3)
        , ON_OFF_CAMPUS_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COST_ELEMENTP1 PRIMARY KEY(COST_ELEMENT)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COST_SHARE_TYPE
# -----------------------------------------------------------------------
drop table if exists COST_SHARE_TYPE
/

CREATE TABLE COST_SHARE_TYPE
(
      COST_SHARE_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT COST_SHARE_TYPEP1 PRIMARY KEY(COST_SHARE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# COUNTRY_CODE
# -----------------------------------------------------------------------
drop table if exists COUNTRY_CODE
/

CREATE TABLE COUNTRY_CODE
(
      COUNTRY_CODE CHAR(3)
        , COUNTRY_NAME VARCHAR(40) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT COUNTRY_CODEP1 PRIMARY KEY(COUNTRY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CUSTOM_ATTRIBUTE
# -----------------------------------------------------------------------
drop table if exists CUSTOM_ATTRIBUTE
/

CREATE TABLE CUSTOM_ATTRIBUTE
(
      ID DECIMAL(12)
        , NAME VARCHAR(30) NOT NULL
        , LABEL VARCHAR(30) NOT NULL
        , DATA_TYPE_CODE VARCHAR(3) NOT NULL
        , DATA_LENGTH DECIMAL(4)
        , DEFAULT_VALUE VARCHAR(2000)
        , LOOKUP_CLASS VARCHAR(100)
        , LOOKUP_RETURN VARCHAR(30)
        , GROUP_NAME VARCHAR(250)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CUSTOM_ATTRIBUTEP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CUSTOM_ATTRIBUTE_DATA_TYPE
# -----------------------------------------------------------------------
drop table if exists CUSTOM_ATTRIBUTE_DATA_TYPE
/

CREATE TABLE CUSTOM_ATTRIBUTE_DATA_TYPE
(
      DATA_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CUSTOM_ATTRIBUTE_DATA_TYPEP1 PRIMARY KEY(DATA_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CUSTOM_ATTRIBUTE_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists CUSTOM_ATTRIBUTE_DOCUMENT
/

CREATE TABLE CUSTOM_ATTRIBUTE_DOCUMENT
(
      DOCUMENT_TYPE_CODE VARCHAR(4)
        , CUSTOM_ATTRIBUTE_ID DECIMAL(12)
        , TYPE_NAME VARCHAR(100)
        , IS_REQUIRED CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTIVE_FLAG CHAR(1)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CUSTOM_ATTRIBUTE_DOCUMENTP1 PRIMARY KEY(DOCUMENT_TYPE_CODE,CUSTOM_ATTRIBUTE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# CUSTOM_ATTRIBUTE_DOC_VALUE
# -----------------------------------------------------------------------
drop table if exists CUSTOM_ATTRIBUTE_DOC_VALUE
/

CREATE TABLE CUSTOM_ATTRIBUTE_DOC_VALUE
(
      DOCUMENT_NUMBER DECIMAL(10)
        , CUSTOM_ATTRIBUTE_ID DECIMAL(12)
        , VALUE VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT CUSTOM_ATTRIBUTE_DOC_VALUEP1 PRIMARY KEY(DOCUMENT_NUMBER,CUSTOM_ATTRIBUTE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# DEADLINE_TYPE
# -----------------------------------------------------------------------
drop table if exists DEADLINE_TYPE
/

CREATE TABLE DEADLINE_TYPE
(
      DEADLINE_TYPE_CODE CHAR(1)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT DEADLINE_TYPEP1 PRIMARY KEY(DEADLINE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# DEGREE_TYPE
# -----------------------------------------------------------------------
drop table if exists DEGREE_TYPE
/

CREATE TABLE DEGREE_TYPE
(
      DEGREE_CODE VARCHAR(6)
        , DESCRIPTION VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT DEGREE_TYPEP1 PRIMARY KEY(DEGREE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# DISTRIBUTION
# -----------------------------------------------------------------------
drop table if exists DISTRIBUTION
/

CREATE TABLE DISTRIBUTION
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , OSP_DISTRIBUTION_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT DISTRIBUTIONP1 PRIMARY KEY(OSP_DISTRIBUTION_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# DOCUMENT_NEXTVALUE
# -----------------------------------------------------------------------
drop table if exists DOCUMENT_NEXTVALUE
/

CREATE TABLE DOCUMENT_NEXTVALUE
(
      DOCUMENT_NUMBER DECIMAL(12)
        , PROPERTY_NAME VARCHAR(200)
        , NEXT_VALUE DECIMAL(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT DOCUMENT_NEXTVALUEP1 PRIMARY KEY(DOCUMENT_NUMBER,PROPERTY_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROPOSAL
# -----------------------------------------------------------------------
drop table if exists EPS_PROPOSAL
/

CREATE TABLE EPS_PROPOSAL
(
      POST_SUB_STATUS_CODE DECIMAL(3)
        , SUBMIT_FLAG CHAR(1) default 'N' NOT NULL
        , IS_HIERARCHY CHAR(1) default 'N' NOT NULL
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIERARCHY_HASH_CODE DECIMAL(10)
        , HIERARCHY_BUDGET_TYPE CHAR(1)
        , PROGRAM_ANNOUNCEMENT_NUMBER VARCHAR(50)
        , PROGRAM_ANNOUNCEMENT_TITLE VARCHAR(255)
        , ACTIVITY_TYPE_CODE VARCHAR(3)
        , REQUESTED_START_DATE_INITIAL DATETIME
        , REQUESTED_START_DATE_TOTAL DATETIME
        , REQUESTED_END_DATE_INITIAL DATETIME
        , REQUESTED_END_DATE_TOTAL DATETIME
        , DURATION_MONTHS DECIMAL(3)
        , NUMBER_OF_COPIES VARCHAR(7)
        , DEADLINE_DATE DATETIME
        , DEADLINE_TYPE CHAR(1)
        , MAILING_ADDRESS_ID DECIMAL(6)
        , MAIL_BY CHAR(1)
        , MAIL_TYPE VARCHAR(3)
        , CARRIER_CODE_TYPE VARCHAR(3)
        , CARRIER_CODE VARCHAR(20)
        , MAIL_DESCRIPTION VARCHAR(80)
        , MAIL_ACCOUNT_NUMBER VARCHAR(9)
        , SUBCONTRACT_FLAG CHAR(1)
        , NARRATIVE_STATUS CHAR(1)
        , BUDGET_STATUS CHAR(1)
        , OWNED_BY_UNIT VARCHAR(8)
        , CREATE_TIMESTAMP DATETIME
        , CREATE_USER VARCHAR(60)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , NSF_CODE VARCHAR(15)
        , PRIME_SPONSOR_CODE CHAR(6)
        , CFDA_NUMBER VARCHAR(7)
        , AGENCY_PROGRAM_CODE VARCHAR(50)
        , AGENCY_DIVISION_CODE VARCHAR(50)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOCUMENT_NUMBER DECIMAL(10) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , PROPOSAL_TYPE_CODE VARCHAR(3)
        , STATUS_CODE DECIMAL(3)
        , CREATION_STATUS_CODE DECIMAL(3)
        , BASE_PROPOSAL_NUMBER VARCHAR(8)
        , CONTINUED_FROM VARCHAR(8)
        , TEMPLATE_FLAG CHAR(1)
        , ORGANIZATION_ID VARCHAR(8)
        , PERFORMING_ORGANIZATION_ID VARCHAR(8)
        , CURRENT_ACCOUNT_NUMBER CHAR(7)
        , CURRENT_AWARD_NUMBER VARCHAR(12)
        , TITLE VARCHAR(150)
        , SPONSOR_CODE CHAR(6)
        , SPONSOR_PROPOSAL_NUMBER VARCHAR(70)
        , INTR_COOP_ACTIVITIES_FLAG CHAR(1)
        , INTR_COUNTRY_LIST VARCHAR(150)
        , OTHER_AGENCY_FLAG CHAR(1)
        , NOTICE_OF_OPPORTUNITY_CODE DECIMAL(3)
        , HIERARCHY_ORIG_CHILD_PROP_NBR VARCHAR(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROPOSALP1 PRIMARY KEY(PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROPOSAL_BUDGET_EXT
# -----------------------------------------------------------------------
drop table if exists EPS_PROPOSAL_BUDGET_EXT
/

CREATE TABLE EPS_PROPOSAL_BUDGET_EXT
(
      BUDGET_ID DECIMAL(12)
        , FINAL_VERSION_FLAG VARCHAR(1)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , DOCUMENT_NUMBER DECIMAL(10)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT EPS_PROPOSAL_BUDGET_EXTP1 PRIMARY KEY(BUDGET_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROPOSAL_BUDGET_STATUS
# -----------------------------------------------------------------------
drop table if exists EPS_PROPOSAL_BUDGET_STATUS
/

CREATE TABLE EPS_PROPOSAL_BUDGET_STATUS
(
      PROPOSAL_NUMBER VARCHAR(12)
        , BUDGET_STATUS_CODE CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROPOSAL_BUDGET_STATUSP1 PRIMARY KEY(PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROPOSAL_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists EPS_PROPOSAL_DOCUMENT
/

CREATE TABLE EPS_PROPOSAL_DOCUMENT
(
      DOCUMENT_NUMBER DECIMAL(10)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROPOSAL_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROPOSAL_STATUS
# -----------------------------------------------------------------------
drop table if exists EPS_PROPOSAL_STATUS
/

CREATE TABLE EPS_PROPOSAL_STATUS
(
      STATUS_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
    
    , CONSTRAINT EPS_PROPOSAL_STATUSP1 PRIMARY KEY(STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_ABSTRACT
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_ABSTRACT
/

CREATE TABLE EPS_PROP_ABSTRACT
(
      TIMESTAMP_DISPLAY DATETIME
        , USER_DISPLAY VARCHAR(60)
        , PROPOSAL_NUMBER VARCHAR(12)
        , ABSTRACT_TYPE_CODE VARCHAR(3)
        , ABSTRACT_DETAILS LONGTEXT NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_ABSTRACTP1 PRIMARY KEY(PROPOSAL_NUMBER,ABSTRACT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_CHANGED_DATA
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_CHANGED_DATA
/

CREATE TABLE EPS_PROP_CHANGED_DATA
(
      PROPOSAL_NUMBER VARCHAR(8)
        , COLUMN_NAME VARCHAR(30)
        , CHANGE_NUMBER DECIMAL(3)
        , CHANGED_VALUE VARCHAR(200)
        , DISPLAY_VALUE VARCHAR(200)
        , OLD_DISPLAY_VALUE VARCHAR(200)
        , COMMENTS VARCHAR(300)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_CHANGED_DATAP1 PRIMARY KEY(PROPOSAL_NUMBER,COLUMN_NAME,CHANGE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_COLUMNS_TO_ALTER
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_COLUMNS_TO_ALTER
/

CREATE TABLE EPS_PROP_COLUMNS_TO_ALTER
(
      LOOKUP_RETURN VARCHAR(50)
        , COLUMN_NAME VARCHAR(30)
        , COLUMN_LABEL VARCHAR(30) NOT NULL
        , DATA_TYPE VARCHAR(9) NOT NULL
        , DATA_LENGTH DECIMAL(4)
        , HAS_LOOKUP CHAR(1) NOT NULL
        , LOOKUP_ARGUMENT VARCHAR(100)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_COLUMNS_TO_ALTERP1 PRIMARY KEY(COLUMN_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_CONG_DISTRICT
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_CONG_DISTRICT
/

CREATE TABLE EPS_PROP_CONG_DISTRICT
(
      PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SITE_NUMBER DECIMAL(3) NOT NULL
        , CONG_DISTRICT VARCHAR(50) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , CONG_DISTRICT_ID DECIMAL(22)
    
    , CONSTRAINT EPS_PROP_CONG_DISTRICTP1 PRIMARY KEY(CONG_DISTRICT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_COST_SHARING
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_COST_SHARING
/

CREATE TABLE EPS_PROP_COST_SHARING
(
      BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , BUDGET_VERSION_NUMBER DECIMAL(3)
        , COST_SHARE_ID DECIMAL(5)
        , FISCAL_YEAR DECIMAL(4)
        , AMOUNT DECIMAL(12,2)
        , COST_SHARING_PERCENTAGE DECIMAL(5,2)
        , SOURCE_ACCOUNT VARCHAR(32)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_COST_SHARINGP1 PRIMARY KEY(BUDGET_ID,COST_SHARE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_EXEMPT_NUMBER
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_EXEMPT_NUMBER
/

CREATE TABLE EPS_PROP_EXEMPT_NUMBER
(
      PROPOSAL_NUMBER VARCHAR(12)
        , SPECIAL_REVIEW_NUMBER DECIMAL(3)
        , EXEMPTION_TYPE_CODE VARCHAR(3)
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_EXEMPT_NUMBERP1 PRIMARY KEY(PROPOSAL_NUMBER,SPECIAL_REVIEW_NUMBER,EXEMPTION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_IDC_RATE
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_IDC_RATE
/

CREATE TABLE EPS_PROP_IDC_RATE
(
      BUDGET_ID DECIMAL(12)
        , BUDGET_VERSION_NUMBER DECIMAL(3)
        , UNRECOVERED_FNA_ID DECIMAL(5)
        , FISCAL_YEAR DECIMAL(4)
        , UNDERRECOVERY_OF_IDC DECIMAL(12,2)
        , APPLICABLE_IDC_RATE DECIMAL(6,3)
        , ON_CAMPUS_FLAG CHAR(1)
        , SOURCE_ACCOUNT VARCHAR(32)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_IDC_RATEP1 PRIMARY KEY(BUDGET_ID,UNRECOVERED_FNA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_LA_RATES
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_LA_RATES
/

CREATE TABLE EPS_PROP_LA_RATES
(
      BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8)
        , VERSION_NUMBER DECIMAL(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , FISCAL_YEAR CHAR(4)
        , ON_OFF_CAMPUS_FLAG CHAR(1)
        , START_DATE DATETIME
        , APPLICABLE_RATE DECIMAL(5,2) NOT NULL
        , INSTITUTE_RATE DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , RATE_CLASS_CODE VARCHAR(3)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_LA_RATESP1 PRIMARY KEY(BUDGET_ID,RATE_TYPE_CODE,FISCAL_YEAR,ON_OFF_CAMPUS_FLAG,START_DATE,RATE_CLASS_CODE)


    , INDEX PK_EPS_PROP_LA_RATES_KRA (RATE_TYPE_CODE, FISCAL_YEAR, START_DATE, ON_OFF_CAMPUS_FLAG, PROPOSAL_NUMBER, VERSION_NUMBER, RATE_CLASS_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_LOCATION
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_LOCATION
/

CREATE TABLE EPS_PROP_LOCATION
(
      PROPOSAL_NUMBER VARCHAR(12)
        , LOCATION_SEQUENCE_NUMBER DECIMAL(3)
        , LOCATION VARCHAR(60) NOT NULL
        , ROLODEX_ID DECIMAL(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_LOCATIONP1 PRIMARY KEY(PROPOSAL_NUMBER,LOCATION_SEQUENCE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON
/

CREATE TABLE EPS_PROP_PERSON
(
      HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , PROP_PERSON_ROLE_ID VARCHAR(12)
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , SSN VARCHAR(9)
        , LAST_NAME VARCHAR(30)
        , FIRST_NAME VARCHAR(30)
        , MIDDLE_NAME VARCHAR(30)
        , FULL_NAME VARCHAR(90)
        , PRIOR_NAME VARCHAR(30)
        , USER_NAME VARCHAR(60)
        , EMAIL_ADDRESS VARCHAR(60)
        , DATE_OF_BIRTH DATETIME
        , AGE DECIMAL(3)
        , AGE_BY_FISCAL_YEAR DECIMAL(3)
        , GENDER VARCHAR(30)
        , RACE VARCHAR(30)
        , EDUCATION_LEVEL VARCHAR(30)
        , DEGREE VARCHAR(11)
        , MAJOR VARCHAR(30)
        , IS_HANDICAPPED CHAR(1)
        , HANDICAP_TYPE VARCHAR(30)
        , IS_VETERAN CHAR(1)
        , VETERAN_TYPE VARCHAR(30)
        , VISA_CODE VARCHAR(20)
        , VISA_TYPE VARCHAR(30)
        , VISA_RENEWAL_DATE DATETIME
        , HAS_VISA CHAR(1)
        , OFFICE_LOCATION VARCHAR(30)
        , OFFICE_PHONE VARCHAR(20)
        , SECONDRY_OFFICE_LOCATION VARCHAR(30)
        , SECONDRY_OFFICE_PHONE VARCHAR(20)
        , SCHOOL VARCHAR(50)
        , YEAR_GRADUATED VARCHAR(30)
        , DIRECTORY_DEPARTMENT VARCHAR(30)
        , SALUTATION VARCHAR(30)
        , COUNTRY_OF_CITIZENSHIP VARCHAR(30)
        , PRIMARY_TITLE VARCHAR(51)
        , DIRECTORY_TITLE VARCHAR(50)
        , HOME_UNIT VARCHAR(8)
        , IS_FACULTY CHAR(1)
        , IS_GRADUATE_STUDENT_STAFF CHAR(1)
        , IS_RESEARCH_STAFF CHAR(1)
        , IS_SERVICE_STAFF CHAR(1)
        , IS_SUPPORT_STAFF CHAR(1)
        , IS_OTHER_ACCADEMIC_GROUP CHAR(1)
        , IS_MEDICAL_STAFF CHAR(1)
        , VACATION_ACCURAL CHAR(1)
        , IS_ON_SABBATICAL CHAR(1)
        , ID_PROVIDED VARCHAR(30)
        , ID_VERIFIED VARCHAR(30)
        , ADDRESS_LINE_1 VARCHAR(80)
        , ADDRESS_LINE_2 VARCHAR(80)
        , ADDRESS_LINE_3 VARCHAR(80)
        , CITY VARCHAR(30)
        , COUNTY VARCHAR(30)
        , STATE VARCHAR(30)
        , POSTAL_CODE VARCHAR(15)
        , COUNTRY_CODE CHAR(3)
        , FAX_NUMBER VARCHAR(20)
        , PAGER_NUMBER VARCHAR(20)
        , MOBILE_PHONE_NUMBER VARCHAR(20)
        , ERA_COMMONS_USER_NAME VARCHAR(20)
        , CONFLICT_OF_INTEREST_FLAG CHAR(1)
        , PERCENTAGE_EFFORT DECIMAL(5,2)
        , FEDR_DEBR_FLAG CHAR(1)
        , FEDR_DELQ_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OPT_IN_UNIT_STATUS CHAR(1) default 'Y' NOT NULL
        , IS_OSC CHAR(1)
        , OPT_IN_CERTIFICATION_STATUS CHAR(1) default 'Y' NOT NULL
        , ORDINAL_POSITION DECIMAL(4)
        , PROJECT_ROLE VARCHAR(60)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSONP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,PROP_PERSON_ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON_BIO
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON_BIO
/

CREATE TABLE EPS_PROP_PERSON_BIO
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , BIO_NUMBER DECIMAL(3)
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , DESCRIPTION VARCHAR(200)
        , DOCUMENT_TYPE_CODE VARCHAR(3)
        , FILE_NAME VARCHAR(150)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSON_BIOP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,BIO_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON_BIO_ATTACHMENT
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON_BIO_ATTACHMENT
/

CREATE TABLE EPS_PROP_PERSON_BIO_ATTACHMENT
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , BIO_NUMBER DECIMAL(3)
        , BIO_DATA LONGBLOB
        , FILE_NAME VARCHAR(150)
        , CONTENT_TYPE VARCHAR(250)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSON_BIO_ATTACHMP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,BIO_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON_DEGREE
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON_DEGREE
/

CREATE TABLE EPS_PROP_PERSON_DEGREE
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , DEGREE_SEQUENCE_NUMBER DECIMAL(3)
        , GRADUATION_YEAR VARCHAR(4)
        , DEGREE_CODE VARCHAR(6)
        , DEGREE VARCHAR(80)
        , FIELD_OF_STUDY VARCHAR(80)
        , SPECIALIZATION VARCHAR(80)
        , SCHOOL VARCHAR(50)
        , SCHOOL_ID_CODE VARCHAR(3)
        , SCHOOL_ID VARCHAR(20)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSON_DEGREEP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,DEGREE_SEQUENCE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON_ROLE
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON_ROLE
/

CREATE TABLE EPS_PROP_PERSON_ROLE
(
      UNIT_DETAILS_REQUIRED CHAR(1) default 'Y' NOT NULL
        , PROP_PERSON_ROLE_ID VARCHAR(12)
        , DESCRIPTION VARCHAR(25) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , CERTIFICATION_REQUIRED CHAR(1) default 'Y' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSON_ROLEP1 PRIMARY KEY(PROP_PERSON_ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERSON_UNITS
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERSON_UNITS
/

CREATE TABLE EPS_PROP_PERSON_UNITS
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , UNIT_NUMBER VARCHAR(8)
        , LEAD_UNIT_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERSON_UNITSP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,UNIT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PERS_YNQ
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PERS_YNQ
/

CREATE TABLE EPS_PROP_PERS_YNQ
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , QUESTION_ID VARCHAR(4)
        , ANSWER CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PERS_YNQP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER,QUESTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PER_CREDIT_SPLIT
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PER_CREDIT_SPLIT
/

CREATE TABLE EPS_PROP_PER_CREDIT_SPLIT
(
      PROPOSAL_NUMBER VARCHAR(12)
        , INV_CREDIT_TYPE_CODE VARCHAR(3)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PER_CREDIT_SPLITP1 PRIMARY KEY(PROPOSAL_NUMBER,INV_CREDIT_TYPE_CODE,PROP_PERSON_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_PER_DOC_TYPE
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_PER_DOC_TYPE
/

CREATE TABLE EPS_PROP_PER_DOC_TYPE
(
      DOCUMENT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_PER_DOC_TYPEP1 PRIMARY KEY(DOCUMENT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_POST_SUB_STATUS
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_POST_SUB_STATUS
/

CREATE TABLE EPS_PROP_POST_SUB_STATUS
(
      STATUS_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , DEFINITION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_POST_SUB_STATUSP1 PRIMARY KEY(STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_RATES
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_RATES
/

CREATE TABLE EPS_PROP_RATES
(
      BUDGET_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(12)
        , VERSION_NUMBER DECIMAL(3)
        , RATE_CLASS_CODE VARCHAR(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , FISCAL_YEAR CHAR(4)
        , ON_OFF_CAMPUS_FLAG CHAR(1)
        , ACTIVITY_TYPE_CODE VARCHAR(3) NOT NULL
        , START_DATE DATETIME
        , APPLICABLE_RATE DECIMAL(5,2) NOT NULL
        , INSTITUTE_RATE DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_RATESP1 PRIMARY KEY(BUDGET_ID,RATE_CLASS_CODE,RATE_TYPE_CODE,FISCAL_YEAR,ON_OFF_CAMPUS_FLAG,START_DATE)


    , INDEX PK_EPS_PROP_RATES_KRA (PROPOSAL_NUMBER, VERSION_NUMBER, RATE_CLASS_CODE, RATE_TYPE_CODE, FISCAL_YEAR, START_DATE, ON_OFF_CAMPUS_FLAG)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_SCIENCE_KEYWORD
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_SCIENCE_KEYWORD
/

CREATE TABLE EPS_PROP_SCIENCE_KEYWORD
(
      HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , SCIENCE_KEYWORD_CODE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_SCIENCE_KEYWORDP1 PRIMARY KEY(PROPOSAL_NUMBER,SCIENCE_KEYWORD_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_SITES
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_SITES
/

CREATE TABLE EPS_PROP_SITES
(
      PROPOSAL_NUMBER VARCHAR(8)
        , SITE_NUMBER DECIMAL(3)
        , LOCATION_NAME VARCHAR(60)
        , LOCATION_TYPE_CODE DECIMAL(3) NOT NULL
        , ORGANIZATION_ID VARCHAR(8)
        , ROLODEX_ID DECIMAL(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_SITESP1 PRIMARY KEY(PROPOSAL_NUMBER,SITE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_SPECIAL_REVIEW
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_SPECIAL_REVIEW
/

CREATE TABLE EPS_PROP_SPECIAL_REVIEW
(
      EXPIRATION_DATE DATETIME
        , HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , SPECIAL_REVIEW_NUMBER DECIMAL(3)
        , SPECIAL_REVIEW_CODE VARCHAR(3) NOT NULL
        , APPROVAL_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , COMMENTS LONGTEXT
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_SPECIAL_REVIEWP1 PRIMARY KEY(PROPOSAL_NUMBER,SPECIAL_REVIEW_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_UNIT_CREDIT_SPLIT
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_UNIT_CREDIT_SPLIT
/

CREATE TABLE EPS_PROP_UNIT_CREDIT_SPLIT
(
      PROPOSAL_NUMBER VARCHAR(12)
        , INV_CREDIT_TYPE_CODE VARCHAR(3)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , UNIT_NUMBER VARCHAR(8)
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_UNIT_CREDIT_SPLITP1 PRIMARY KEY(PROPOSAL_NUMBER,INV_CREDIT_TYPE_CODE,PROP_PERSON_NUMBER,UNIT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_USER_ROLES
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_USER_ROLES
/

CREATE TABLE EPS_PROP_USER_ROLES
(
      PROPOSAL_NUMBER VARCHAR(12)
        , USER_ID VARCHAR(40)
        , ROLE_ID DECIMAL(5)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_USER_ROLESP1 PRIMARY KEY(PROPOSAL_NUMBER,USER_ID,ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EPS_PROP_YNQ
# -----------------------------------------------------------------------
drop table if exists EPS_PROP_YNQ
/

CREATE TABLE EPS_PROP_YNQ
(
      PROPOSAL_NUMBER VARCHAR(12)
        , QUESTION_ID VARCHAR(4)
        , ANSWER CHAR(1)
        , EXPLANATION LONGTEXT
        , REVIEW_DATE DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EPS_PROP_YNQP1 PRIMARY KEY(PROPOSAL_NUMBER,QUESTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EXEMPTION_TYPE
# -----------------------------------------------------------------------
drop table if exists EXEMPTION_TYPE
/

CREATE TABLE EXEMPTION_TYPE
(
      EXEMPTION_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , DETAILED_DESCRIPTION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EXEMPTION_TYPEP1 PRIMARY KEY(EXEMPTION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EXEMPT_STUDIES_CHECKLIST
# -----------------------------------------------------------------------
drop table if exists EXEMPT_STUDIES_CHECKLIST
/

CREATE TABLE EXEMPT_STUDIES_CHECKLIST
(
      EXEMPT_STUDIES_CHECKLIST_CODE VARCHAR(4)
        , DESCRIPTION VARCHAR(2000) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EXEMPT_STUDIES_CHECKLISTP1 PRIMARY KEY(EXEMPT_STUDIES_CHECKLIST_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# EXPEDITED_REVIEW_CHECKLIST
# -----------------------------------------------------------------------
drop table if exists EXPEDITED_REVIEW_CHECKLIST
/

CREATE TABLE EXPEDITED_REVIEW_CHECKLIST
(
      EXPEDITED_REV_CHKLST_CODE VARCHAR(4)
        , DESCRIPTION VARCHAR(2000) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT EXPEDITED_REVIEW_CHECKLISTP1 PRIMARY KEY(EXPEDITED_REV_CHKLST_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# FP_DOC_TYPE_T
# -----------------------------------------------------------------------
drop table if exists FP_DOC_TYPE_T
/

CREATE TABLE FP_DOC_TYPE_T
(
      FDOC_TYP_CD VARCHAR(4)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FDOC_NM VARCHAR(40)
        , FDOC_TYP_ACTIVE_CD VARCHAR(1)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT FP_DOC_TYPE_TP1 PRIMARY KEY(FDOC_TYP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# FREQUENCY
# -----------------------------------------------------------------------
drop table if exists FREQUENCY
/

CREATE TABLE FREQUENCY
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , FREQUENCY_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , NUMBER_OF_DAYS DECIMAL(3)
        , NUMBER_OF_MONTHS DECIMAL(2)
        , REPEAT_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , ADVANCE_NUMBER_OF_DAYS DECIMAL(3)
        , ADVANCE_NUMBER_OF_MONTHS DECIMAL(2)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT FREQUENCYP1 PRIMARY KEY(FREQUENCY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# FREQUENCY_BASE
# -----------------------------------------------------------------------
drop table if exists FREQUENCY_BASE
/

CREATE TABLE FREQUENCY_BASE
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , FREQUENCY_BASE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT FREQUENCY_BASEP1 PRIMARY KEY(FREQUENCY_BASE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# FUNDING_SOURCE_TYPE
# -----------------------------------------------------------------------
drop table if exists FUNDING_SOURCE_TYPE
/

CREATE TABLE FUNDING_SOURCE_TYPE
(
      FUNDING_SOURCE_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , FUNDING_SOURCE_TYPE_FLAG VARCHAR(1)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT FUNDING_SOURCE_TYPEP1 PRIMARY KEY(FUNDING_SOURCE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# GROUP_TYPES
# -----------------------------------------------------------------------
drop table if exists GROUP_TYPES
/

CREATE TABLE GROUP_TYPES
(
      GROUP_TYPE_CODE DECIMAL(3)
        , GROUP_NAME VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT GROUP_TYPESP1 PRIMARY KEY(GROUP_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# IDC_RATE_TYPE
# -----------------------------------------------------------------------
drop table if exists IDC_RATE_TYPE
/

CREATE TABLE IDC_RATE_TYPE
(
      IDC_RATE_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT IDC_RATE_TYPEP1 PRIMARY KEY(IDC_RATE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# INSTITUTE_LA_RATES
# -----------------------------------------------------------------------
drop table if exists INSTITUTE_LA_RATES
/

CREATE TABLE INSTITUTE_LA_RATES
(
      ACTIVE_FLAG CHAR(1) default 'Y' NOT NULL
        , UNIT_NUMBER VARCHAR(8)
        , RATE_TYPE_CODE VARCHAR(3)
        , FISCAL_YEAR CHAR(4)
        , START_DATE DATETIME
        , ON_OFF_CAMPUS_FLAG CHAR(1)
        , RATE DECIMAL(5,2) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , RATE_CLASS_CODE VARCHAR(3)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT INSTITUTE_LA_RATESP1 PRIMARY KEY(UNIT_NUMBER,RATE_TYPE_CODE,FISCAL_YEAR,START_DATE,ON_OFF_CAMPUS_FLAG,RATE_CLASS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# INSTITUTE_PROPOSAL_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists INSTITUTE_PROPOSAL_DOCUMENT
/

CREATE TABLE INSTITUTE_PROPOSAL_DOCUMENT
(
      DOCUMENT_NUMBER VARCHAR(10)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT INSTITUTE_PROPOSAL_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# INSTITUTE_RATES
# -----------------------------------------------------------------------
drop table if exists INSTITUTE_RATES
/

CREATE TABLE INSTITUTE_RATES
(
      ACTIVE_FLAG CHAR(1) default 'Y' NOT NULL
        , RATE_CLASS_CODE VARCHAR(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , ACTIVITY_TYPE_CODE VARCHAR(3)
        , FISCAL_YEAR CHAR(4)
        , START_DATE DATETIME
        , ON_OFF_CAMPUS_FLAG CHAR(1)
        , RATE DECIMAL(5,2) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UNIT_NUMBER VARCHAR(8)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT INSTITUTE_RATESP1 PRIMARY KEY(RATE_CLASS_CODE,RATE_TYPE_CODE,ACTIVITY_TYPE_CODE,FISCAL_YEAR,START_DATE,ON_OFF_CAMPUS_FLAG,UNIT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# INV_CREDIT_TYPE
# -----------------------------------------------------------------------
drop table if exists INV_CREDIT_TYPE
/

CREATE TABLE INV_CREDIT_TYPE
(
      INV_CREDIT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(300) NOT NULL
        , ADDS_TO_HUNDRED CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTIVE_FLAG CHAR(1)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT INV_CREDIT_TYPEP1 PRIMARY KEY(INV_CREDIT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# IP_REVIEW
# -----------------------------------------------------------------------
drop table if exists IP_REVIEW
/

CREATE TABLE IP_REVIEW
(
      IP_REVIEW_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , IP_REVIEW_REQ_TYPE_CODE DECIMAL(3)
        , REVIEW_SUBMISSION_DATE DATETIME
        , REVIEW_RECEIVE_DATE DATETIME
        , REVIEW_RESULT_CODE DECIMAL(3)
        , IP_REVIEWER VARCHAR(40)
        , IP_REVIEW_SEQUENCE_STATUS VARCHAR(10) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT IP_REVIEWP1 PRIMARY KEY(IP_REVIEW_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# IP_REVIEW_ACTIVITY_TYPE
# -----------------------------------------------------------------------
drop table if exists IP_REVIEW_ACTIVITY_TYPE
/

CREATE TABLE IP_REVIEW_ACTIVITY_TYPE
(
      IP_REVIEW_ACTIVITY_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT IP_REVIEW_ACTIVITY_TYPEP1 PRIMARY KEY(IP_REVIEW_ACTIVITY_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# IP_REVIEW_REQ_TYPE
# -----------------------------------------------------------------------
drop table if exists IP_REVIEW_REQ_TYPE
/

CREATE TABLE IP_REVIEW_REQ_TYPE
(
      IP_REVIEW_REQ_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT IP_REVIEW_REQ_TYPEP1 PRIMARY KEY(IP_REVIEW_REQ_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# IP_REVIEW_RESULT_TYPE
# -----------------------------------------------------------------------
drop table if exists IP_REVIEW_RESULT_TYPE
/

CREATE TABLE IP_REVIEW_RESULT_TYPE
(
      IP_REVIEW_RESULT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT IP_REVIEW_RESULT_TYPEP1 PRIMARY KEY(IP_REVIEW_RESULT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# JOB_CODE
# -----------------------------------------------------------------------
drop table if exists JOB_CODE
/

CREATE TABLE JOB_CODE
(
      JOB_CODE VARCHAR(6)
        , JOB_TITLE VARCHAR(50) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT JOB_CODEP1 PRIMARY KEY(JOB_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KC_COUNTRY_CODES_T
# -----------------------------------------------------------------------
drop table if exists KC_COUNTRY_CODES_T
/

CREATE TABLE KC_COUNTRY_CODES_T
(
      ID DECIMAL(8)
        , COUNTRY_NAME VARCHAR(50) NOT NULL
        , TWO_CHAR_CODE VARCHAR(2) NOT NULL
        , THREE_CHAR_CODE VARCHAR(3) NOT NULL
        , VER_NBR VARCHAR(8) NOT NULL
        , UPDATE_USER VARCHAR(60)
        , UPDATE_TIMESTAMP DATETIME
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KC_COUNTRY_CODES_TP1 PRIMARY KEY(ID)


    , INDEX KC_COUNTRY_CODES_T_UK1 (TWO_CHAR_CODE)
    , INDEX KC_COUNTRY_CODES_T_UK2 (THREE_CHAR_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRA_USER
# -----------------------------------------------------------------------
drop table if exists KRA_USER
/

CREATE TABLE KRA_USER
(
      USER_ID VARCHAR(10)
        , USER_NAME VARCHAR(90)
        , NON_MIT_PERSON_FLAG CHAR(1)
        , PERSON_ID VARCHAR(40)
        , USER_TYPE CHAR(1)
        , UNIT_NUMBER VARCHAR(8)
        , STATUS CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KRA_USERP1 PRIMARY KEY(USER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_CHNL_PRODCR_T
# -----------------------------------------------------------------------
drop table if exists KREN_CHNL_PRODCR_T
/

CREATE TABLE KREN_CHNL_PRODCR_T
(
      CHNL_ID DECIMAL(8)
        , PRODCR_ID DECIMAL(8)
    
    , CONSTRAINT KREN_CHNL_PRODCR_TP1 PRIMARY KEY(CHNL_ID,PRODCR_ID)


    , INDEX KREN_CHNL_PRODCR_TI1 (CHNL_ID)
    , INDEX KREN_CHNL_PRODCR_TI2 (PRODCR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_CHNL_SUBSCRP_T
# -----------------------------------------------------------------------
drop table if exists KREN_CHNL_SUBSCRP_T
/

CREATE TABLE KREN_CHNL_SUBSCRP_T
(
      CHNL_SUBSCRP_ID DECIMAL(8)
        , CHNL_ID DECIMAL(8) NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
    
    , CONSTRAINT KREN_CHNL_SUBSCRP_TP1 PRIMARY KEY(CHNL_SUBSCRP_ID)


    , INDEX KREN_CHNL_SUBSCRP_TC0 (CHNL_ID, PRNCPL_ID)
    , INDEX KREN_CHNL_SUBSCRP_TI1 (CHNL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_CHNL_T
# -----------------------------------------------------------------------
drop table if exists KREN_CHNL_T
/

CREATE TABLE KREN_CHNL_T
(
      CHNL_ID DECIMAL(8)
        , NM VARCHAR(200) NOT NULL
        , DESC_TXT VARCHAR(4000) NOT NULL
        , SUBSCRB_IND CHAR(1) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KREN_CHNL_TP1 PRIMARY KEY(CHNL_ID)


    , INDEX KREN_CHNL_TC0 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_CNTNT_TYP_T
# -----------------------------------------------------------------------
drop table if exists KREN_CNTNT_TYP_T
/

CREATE TABLE KREN_CNTNT_TYP_T
(
      CNTNT_TYP_ID DECIMAL(8)
        , NM VARCHAR(200) NOT NULL
        , CUR_IND CHAR(1) default 'T' NOT NULL
        , CNTNT_TYP_VER_NBR DECIMAL(8) default 0 NOT NULL
        , DESC_TXT VARCHAR(1000) NOT NULL
        , NMSPC_CD VARCHAR(1000) NOT NULL
        , XSD LONGTEXT NOT NULL
        , XSL LONGTEXT NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KREN_CNTNT_TYP_TP1 PRIMARY KEY(CNTNT_TYP_ID)


    , INDEX KREN_CONTENT_TYPE_UK1 (NM, CNTNT_TYP_VER_NBR)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_MSG_DELIV_T
# -----------------------------------------------------------------------
drop table if exists KREN_MSG_DELIV_T
/

CREATE TABLE KREN_MSG_DELIV_T
(
      MSG_DELIV_ID DECIMAL(8)
        , MSG_ID DECIMAL(8) NOT NULL
        , TYP_NM VARCHAR(200) NOT NULL
        , SYS_ID VARCHAR(300)
        , STAT_CD VARCHAR(15) NOT NULL
        , PROC_CNT DECIMAL(4) default 0 NOT NULL
        , LOCKD_DTTM TIMESTAMP
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_DELIV_TP1 PRIMARY KEY(MSG_DELIV_ID)


    , INDEX KREN_MSG_DELIV_TC0 (MSG_ID, TYP_NM)
    , INDEX KREN_MSG_DELIV_TI1 (MSG_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_MSG_T
# -----------------------------------------------------------------------
drop table if exists KREN_MSG_T
/

CREATE TABLE KREN_MSG_T
(
      MSG_ID DECIMAL(8)
        , ORGN_ID VARCHAR(128)
        , DELIV_TYP VARCHAR(500) NOT NULL
        , CRTE_DTTM TIMESTAMP NOT NULL
        , TTL VARCHAR(255)
        , CHNL VARCHAR(300) NOT NULL
        , PRODCR VARCHAR(300)
        , CNTNT LONGTEXT NOT NULL
        , CNTNT_TYP VARCHAR(128)
        , URL VARCHAR(512)
        , RECIP_ID VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_TP1 PRIMARY KEY(MSG_ID)


    , INDEX KREN_MSG_TC0 (ORGN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_NTFCTN_MSG_DELIV_T
# -----------------------------------------------------------------------
drop table if exists KREN_NTFCTN_MSG_DELIV_T
/

CREATE TABLE KREN_NTFCTN_MSG_DELIV_T
(
      NTFCTN_MSG_DELIV_ID DECIMAL(8)
        , NTFCTN_ID DECIMAL(8) NOT NULL
        , RECIP_ID VARCHAR(40) NOT NULL
        , STAT_CD VARCHAR(15) NOT NULL
        , SYS_ID VARCHAR(300)
        , LOCKD_DTTM TIMESTAMP
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_NTFCTN_MSG_DELIV_TP1 PRIMARY KEY(NTFCTN_MSG_DELIV_ID)


    , INDEX KREN_MSG_DELIVSI1 (NTFCTN_ID)
    , INDEX KREN_NTFCTN_MSG_DELIV_TC0 (NTFCTN_ID, RECIP_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_NTFCTN_T
# -----------------------------------------------------------------------
drop table if exists KREN_NTFCTN_T
/

CREATE TABLE KREN_NTFCTN_T
(
      NTFCTN_ID DECIMAL(8)
        , DELIV_TYP VARCHAR(3) NOT NULL
        , CRTE_DTTM TIMESTAMP NOT NULL
        , SND_DTTM TIMESTAMP
        , AUTO_RMV_DTTM TIMESTAMP
        , PRIO_ID DECIMAL(8) NOT NULL
        , TTL VARCHAR(255)
        , CNTNT LONGTEXT NOT NULL
        , CNTNT_TYP_ID DECIMAL(8) NOT NULL
        , CHNL_ID DECIMAL(8) NOT NULL
        , PRODCR_ID DECIMAL(8) NOT NULL
        , PROCESSING_FLAG VARCHAR(15) NOT NULL
        , LOCKD_DTTM TIMESTAMP
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_NTFCTN_TP1 PRIMARY KEY(NTFCTN_ID)


    , INDEX KREN_CNTNT_TYP_TC0 (CNTNT_TYP_ID)
    , INDEX KREN_PRIO_TC0 (PRIO_ID)
    , INDEX KREN_PRODCR_TC0 (PRODCR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_PRIO_T
# -----------------------------------------------------------------------
drop table if exists KREN_PRIO_T
/

CREATE TABLE KREN_PRIO_T
(
      PRIO_ID DECIMAL(8)
        , NM VARCHAR(40) NOT NULL
        , DESC_TXT VARCHAR(500) NOT NULL
        , PRIO_ORD DECIMAL(4) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KREN_PRIO_TP1 PRIMARY KEY(PRIO_ID)


    , INDEX KREN_NOTIFICATION_PRIO_UK1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_PRODCR_T
# -----------------------------------------------------------------------
drop table if exists KREN_PRODCR_T
/

CREATE TABLE KREN_PRODCR_T
(
      PRODCR_ID DECIMAL(8)
        , NM VARCHAR(200) NOT NULL
        , DESC_TXT VARCHAR(1000) NOT NULL
        , CNTCT_INFO VARCHAR(1000) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KREN_PRODCR_TP1 PRIMARY KEY(PRODCR_ID)


    , INDEX KREN_NOTIFN_PRODUCERS_UK1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_RECIP_DELIV_T
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_DELIV_T
/

CREATE TABLE KREN_RECIP_DELIV_T
(
      RECIP_DELIV_ID DECIMAL(8)
        , RECIP_ID VARCHAR(40) NOT NULL
        , CHNL VARCHAR(300) NOT NULL
        , NM VARCHAR(200) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_RECIP_DELIV_TP1 PRIMARY KEY(RECIP_DELIV_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_RECIP_LIST_T
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_LIST_T
/

CREATE TABLE KREN_RECIP_LIST_T
(
      RECIP_LIST_ID DECIMAL(8)
        , CHNL_ID DECIMAL(8) NOT NULL
        , RECIP_TYP_CD VARCHAR(10) NOT NULL
        , RECIP_ID VARCHAR(40) NOT NULL
    
    , CONSTRAINT KREN_RECIP_LIST_TP1 PRIMARY KEY(RECIP_LIST_ID)


    , INDEX KREN_RECIP_LIST_TC0 (CHNL_ID, RECIP_TYP_CD, RECIP_ID)
    , INDEX KREN_RECIP_LIST_TI1 (CHNL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_RECIP_PREFS_T
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_PREFS_T
/

CREATE TABLE KREN_RECIP_PREFS_T
(
      RECIP_PREFS_ID DECIMAL(8)
        , RECIP_ID VARCHAR(40) NOT NULL
        , PROP VARCHAR(200) NOT NULL
        , VAL VARCHAR(1000) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_RECIP_PREFS_TP1 PRIMARY KEY(RECIP_PREFS_ID)


    , INDEX KREN_RECIP_PREFS_TC0 (RECIP_ID, PROP)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_RECIP_T
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_T
/

CREATE TABLE KREN_RECIP_T
(
      RECIP_ID DECIMAL(8)
        , NTFCTN_ID DECIMAL(8) NOT NULL
        , RECIP_TYP_CD VARCHAR(10) NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
    
    , CONSTRAINT KREN_RECIP_TP1 PRIMARY KEY(RECIP_ID)


    , INDEX KREN_RECIP_TC0 (NTFCTN_ID, RECIP_TYP_CD, PRNCPL_ID)
    , INDEX KREN_RECIP_TI1 (NTFCTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_RVWER_T
# -----------------------------------------------------------------------
drop table if exists KREN_RVWER_T
/

CREATE TABLE KREN_RVWER_T
(
      RVWER_ID DECIMAL(8)
        , CHNL_ID DECIMAL(8) NOT NULL
        , TYP VARCHAR(10) NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KREN_RVWER_TP1 PRIMARY KEY(RVWER_ID)


    , INDEX KREN_RVWER_TC0 (CHNL_ID, TYP, PRNCPL_ID)
    , INDEX KREN_RVWER_TI1 (CHNL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREN_SNDR_T
# -----------------------------------------------------------------------
drop table if exists KREN_SNDR_T
/

CREATE TABLE KREN_SNDR_T
(
      SNDR_ID DECIMAL(8)
        , NTFCTN_ID DECIMAL(8) NOT NULL
        , NM VARCHAR(200) NOT NULL
    
    , CONSTRAINT KREN_SNDR_TP1 PRIMARY KEY(SNDR_ID)


    , INDEX KREN_SNDR_TC0 (NTFCTN_ID, NM)
    , INDEX KREN_SNDR_TI1 (NTFCTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_ACTN_ITM_T
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_ITM_T
/

CREATE TABLE KREW_ACTN_ITM_T
(
      ACTN_ITM_ID DECIMAL(14)
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , ASND_DT DATETIME NOT NULL
        , RQST_CD CHAR(1) NOT NULL
        , ACTN_RQST_ID DECIMAL(14) NOT NULL
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , ROLE_NM VARCHAR(2000)
        , DLGN_PRNCPL_ID VARCHAR(40)
        , DOC_HDR_TTL VARCHAR(255)
        , DOC_TYP_LBL VARCHAR(128) NOT NULL
        , DOC_HDLR_URL VARCHAR(255) NOT NULL
        , DOC_TYP_NM VARCHAR(64) NOT NULL
        , RSP_ID DECIMAL(14) NOT NULL
        , DLGN_TYP VARCHAR(1)
        , VER_NBR DECIMAL(8) default 0
        , DTYPE VARCHAR(50)
        , GRP_ID VARCHAR(40)
        , DLGN_GRP_ID VARCHAR(40)
        , RQST_LBL VARCHAR(255)
    
    , CONSTRAINT KREW_ACTN_ITM_TP1 PRIMARY KEY(ACTN_ITM_ID)


    , INDEX KREW_ACTN_ITM_T1 (PRNCPL_ID)
    , INDEX KREW_ACTN_ITM_TI2 (DOC_HDR_ID)
    , INDEX KREW_ACTN_ITM_TI3 (ACTN_RQST_ID)
    , INDEX KREW_ACTN_ITM_TI5 (PRNCPL_ID, DLGN_TYP, DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_ACTN_RQST_T
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_RQST_T
/

CREATE TABLE KREW_ACTN_RQST_T
(
      ACTN_RQST_ID DECIMAL(14)
        , PARNT_ID DECIMAL(14)
        , ACTN_RQST_CD CHAR(1) NOT NULL
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , RULE_ID DECIMAL(19)
        , STAT_CD CHAR(1) NOT NULL
        , RSP_ID DECIMAL(14) NOT NULL
        , PRNCPL_ID VARCHAR(40)
        , ROLE_NM VARCHAR(2000)
        , QUAL_ROLE_NM VARCHAR(2000)
        , QUAL_ROLE_NM_LBL_TXT VARCHAR(2000)
        , RECIP_TYP_CD CHAR(1)
        , PRIO_NBR DECIMAL(8) NOT NULL
        , RTE_TYP_NM VARCHAR(255)
        , RTE_LVL_NBR DECIMAL(8) NOT NULL
        , RTE_NODE_INSTN_ID DECIMAL(19)
        , ACTN_TKN_ID DECIMAL(14)
        , DOC_VER_NBR DECIMAL(8) NOT NULL
        , CRTE_DT DATETIME NOT NULL
        , RSP_DESC_TXT VARCHAR(200)
        , FRC_ACTN DECIMAL(1) default 0
        , ACTN_RQST_ANNOTN_TXT VARCHAR(2000)
        , DLGN_TYP CHAR(1)
        , APPR_PLCY CHAR(1)
        , CUR_IND DECIMAL(1) default 1
        , VER_NBR DECIMAL(8) default 0
        , GRP_ID VARCHAR(40)
        , RQST_LBL VARCHAR(255)
    
    , CONSTRAINT KREW_ACTN_RQST_TP1 PRIMARY KEY(ACTN_RQST_ID)


    , INDEX KREW_ACTN_RQST_T11 (DOC_HDR_ID)
    , INDEX KREW_ACTN_RQST_T12 (PRNCPL_ID)
    , INDEX KREW_ACTN_RQST_T13 (ACTN_TKN_ID)
    , INDEX KREW_ACTN_RQST_T14 (PARNT_ID)
    , INDEX KREW_ACTN_RQST_T15 (RSP_ID)
    , INDEX KREW_ACTN_RQST_T16 (STAT_CD, RSP_ID)
    , INDEX KREW_ACTN_RQST_T17 (RTE_NODE_INSTN_ID)
    , INDEX KREW_ACTN_RQST_T19 (STAT_CD, DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_ACTN_TKN_T
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_TKN_T
/

CREATE TABLE KREW_ACTN_TKN_T
(
      ACTN_TKN_ID DECIMAL(14)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , DLGTR_PRNCPL_ID VARCHAR(40)
        , ACTN_CD CHAR(1) NOT NULL
        , ACTN_DT DATETIME NOT NULL
        , DOC_VER_NBR DECIMAL(8) NOT NULL
        , ANNOTN VARCHAR(2000)
        , CUR_IND DECIMAL(1) default 1
        , VER_NBR DECIMAL(8) default 0
        , DLGTR_GRP_ID VARCHAR(40)
    
    , CONSTRAINT KREW_ACTN_TKN_TP1 PRIMARY KEY(ACTN_TKN_ID)


    , INDEX KREW_ACTN_TKN_TI1 (DOC_HDR_ID, PRNCPL_ID)
    , INDEX KREW_ACTN_TKN_TI2 (DOC_HDR_ID, PRNCPL_ID, ACTN_CD)
    , INDEX KREW_ACTN_TKN_TI3 (PRNCPL_ID)
    , INDEX KREW_ACTN_TKN_TI4 (DLGTR_PRNCPL_ID)
    , INDEX KREW_ACTN_TKN_TI5 (DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_APP_DOC_STAT_TRAN_T
# -----------------------------------------------------------------------
drop table if exists KREW_APP_DOC_STAT_TRAN_T
/

CREATE TABLE KREW_APP_DOC_STAT_TRAN_T
(
      APP_DOC_STAT_TRAN_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14)
        , APP_DOC_STAT_FROM VARCHAR(64)
        , APP_DOC_STAT_TO VARCHAR(64)
        , STAT_TRANS_DATE DATETIME
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_APP_DOC_STAT_TRAN_TP1 PRIMARY KEY(APP_DOC_STAT_TRAN_ID)


    , INDEX KREW_APP_DOC_STAT_TI1 (DOC_HDR_ID, STAT_TRANS_DATE)
    , INDEX KREW_APP_DOC_STAT_TI2 (DOC_HDR_ID, APP_DOC_STAT_FROM)
    , INDEX KREW_APP_DOC_STAT_TI3 (DOC_HDR_ID, APP_DOC_STAT_TO)
    , INDEX KREW_APP_DOC_STAT_TRAN_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_ATT_T
# -----------------------------------------------------------------------
drop table if exists KREW_ATT_T
/

CREATE TABLE KREW_ATT_T
(
      ATTACHMENT_ID DECIMAL(19)
        , NTE_ID DECIMAL(19) NOT NULL
        , FILE_NM VARCHAR(255) NOT NULL
        , FILE_LOC VARCHAR(255) NOT NULL
        , MIME_TYP VARCHAR(255) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_ATT_TP1 PRIMARY KEY(ATTACHMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DLGN_RSP_T
# -----------------------------------------------------------------------
drop table if exists KREW_DLGN_RSP_T
/

CREATE TABLE KREW_DLGN_RSP_T
(
      DLGN_RULE_ID DECIMAL(19)
        , RSP_ID DECIMAL(19) NOT NULL
        , DLGN_RULE_BASE_VAL_ID DECIMAL(19) NOT NULL
        , DLGN_TYP VARCHAR(20) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_DLGN_RSP_TP1 PRIMARY KEY(DLGN_RULE_ID)


    , INDEX KREW_DLGN_RSP_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_CNTNT_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_CNTNT_T
/

CREATE TABLE KREW_DOC_HDR_CNTNT_T
(
      DOC_HDR_ID DECIMAL(14)
        , DOC_CNTNT_TXT LONGTEXT
    
    , CONSTRAINT KREW_DOC_HDR_CNTNT_TP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_EXT_DT_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_EXT_DT_T
/

CREATE TABLE KREW_DOC_HDR_EXT_DT_T
(
      DOC_HDR_EXT_DT_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , KEY_CD VARCHAR(256) NOT NULL
        , VAL DATETIME
    
    , CONSTRAINT KREW_DOC_HDR_EXT_DT_TP1 PRIMARY KEY(DOC_HDR_EXT_DT_ID)


    , INDEX KREW_DOC_HDR_EXT_DT_TI1 (KEY_CD, VAL)
    , INDEX KREW_DOC_HDR_EXT_DT_TI2 (DOC_HDR_ID)
    , INDEX KREW_DOC_HDR_EXT_DT_TI3 (VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_EXT_FLT_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_EXT_FLT_T
/

CREATE TABLE KREW_DOC_HDR_EXT_FLT_T
(
      DOC_HDR_EXT_FLT_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , KEY_CD VARCHAR(256) NOT NULL
        , VAL DECIMAL(30,15)
    
    , CONSTRAINT KREW_DOC_HDR_EXT_FLT_TP1 PRIMARY KEY(DOC_HDR_EXT_FLT_ID)


    , INDEX KREW_DOC_HDR_EXT_FLT_TI1 (KEY_CD, VAL)
    , INDEX KREW_DOC_HDR_EXT_FLT_TI2 (DOC_HDR_ID)
    , INDEX KREW_DOC_HDR_EXT_FLT_TI3 (VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_EXT_LONG_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_EXT_LONG_T
/

CREATE TABLE KREW_DOC_HDR_EXT_LONG_T
(
      DOC_HDR_EXT_LONG_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , KEY_CD VARCHAR(256) NOT NULL
        , VAL DECIMAL(22)
    
    , CONSTRAINT KREW_DOC_HDR_EXT_LONG_TP1 PRIMARY KEY(DOC_HDR_EXT_LONG_ID)


    , INDEX KREW_DOC_HDR_EXT_LONG_TI1 (KEY_CD, VAL)
    , INDEX KREW_DOC_HDR_EXT_LONG_TI2 (DOC_HDR_ID)
    , INDEX KREW_DOC_HDR_EXT_LONG_TI3 (VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_EXT_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_EXT_T
/

CREATE TABLE KREW_DOC_HDR_EXT_T
(
      DOC_HDR_EXT_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , KEY_CD VARCHAR(256) NOT NULL
        , VAL VARCHAR(2000)
    
    , CONSTRAINT KREW_DOC_HDR_EXT_TP1 PRIMARY KEY(DOC_HDR_EXT_ID)


    , INDEX KREW_DOC_HDR_EXT_TI1 (KEY_CD, VAL)
    , INDEX KREW_DOC_HDR_EXT_TI2 (DOC_HDR_ID)
    , INDEX KREW_DOC_HDR_EXT_TI3 (VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_HDR_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_T
/

CREATE TABLE KREW_DOC_HDR_T
(
      DOC_HDR_ID DECIMAL(14)
        , APP_DOC_STAT VARCHAR(64)
        , APP_DOC_STAT_MDFN_DT DATETIME
        , DOC_TYP_ID DECIMAL(19)
        , DOC_HDR_STAT_CD CHAR(1) NOT NULL
        , RTE_LVL DECIMAL(8) NOT NULL
        , STAT_MDFN_DT DATETIME NOT NULL
        , CRTE_DT DATETIME NOT NULL
        , APRV_DT DATETIME
        , FNL_DT DATETIME
        , RTE_STAT_MDFN_DT DATETIME
        , RTE_LVL_MDFN_DT DATETIME
        , TTL VARCHAR(255)
        , APP_DOC_ID VARCHAR(255)
        , DOC_VER_NBR DECIMAL(8) NOT NULL
        , INITR_PRNCPL_ID VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , RTE_PRNCPL_ID VARCHAR(40)
        , DTYPE VARCHAR(50)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_DOC_HDR_TP1 PRIMARY KEY(DOC_HDR_ID)


    , INDEX KREW_DOC_HDR_T10 (APP_DOC_STAT)
    , INDEX KREW_DOC_HDR_T12 (APP_DOC_STAT_MDFN_DT)
    , INDEX KREW_DOC_HDR_TC0 (OBJ_ID)
    , INDEX KREW_DOC_HDR_TI1 (DOC_TYP_ID)
    , INDEX KREW_DOC_HDR_TI2 (INITR_PRNCPL_ID)
    , INDEX KREW_DOC_HDR_TI3 (DOC_HDR_STAT_CD)
    , INDEX KREW_DOC_HDR_TI4 (TTL)
    , INDEX KREW_DOC_HDR_TI5 (CRTE_DT)
    , INDEX KREW_DOC_HDR_TI6 (RTE_STAT_MDFN_DT)
    , INDEX KREW_DOC_HDR_TI7 (APRV_DT)
    , INDEX KREW_DOC_HDR_TI8 (FNL_DT)
    , INDEX KREW_DOC_HDR_TI9 (APP_DOC_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_LNK_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_LNK_T
/

CREATE TABLE KREW_DOC_LNK_T
(
      DOC_LNK_ID DECIMAL(19)
        , ORGN_DOC_ID DECIMAL(14) NOT NULL
        , DEST_DOC_ID DECIMAL(14) NOT NULL
    
    , CONSTRAINT KREW_DOC_LNK_TP1 PRIMARY KEY(DOC_LNK_ID)


    , INDEX KREW_DOC_LNK_TI1 (ORGN_DOC_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_NTE_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_NTE_T
/

CREATE TABLE KREW_DOC_NTE_T
(
      DOC_NTE_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , AUTH_PRNCPL_ID VARCHAR(40) NOT NULL
        , CRT_DT DATETIME NOT NULL
        , TXT VARCHAR(4000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_DOC_NTE_TP1 PRIMARY KEY(DOC_NTE_ID)


    , INDEX KREW_DOC_NTE_TI1 (DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_TYP_APP_DOC_STAT_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_APP_DOC_STAT_T
/

CREATE TABLE KREW_DOC_TYP_APP_DOC_STAT_T
(
      DOC_TYP_ID DECIMAL(19)
        , DOC_STAT_NM VARCHAR(64)
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TP1 PRIMARY KEY(DOC_TYP_ID,DOC_STAT_NM)


    , INDEX KREW_DOC_TYP_APP_DOC_STAT_T1 (DOC_TYP_ID)
    , INDEX KREW_DOC_TYP_APP_DOC_STAT_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_TYP_ATTR_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_ATTR_T
/

CREATE TABLE KREW_DOC_TYP_ATTR_T
(
      DOC_TYP_ATTRIB_ID DECIMAL(19)
        , DOC_TYP_ID DECIMAL(19) NOT NULL
        , RULE_ATTR_ID DECIMAL(19) NOT NULL
        , ORD_INDX DECIMAL(4) default 0
    
    , CONSTRAINT KREW_DOC_TYP_ATTR_TP1 PRIMARY KEY(DOC_TYP_ATTRIB_ID)


    , INDEX KREW_DOC_TYP_ATTR_TI1 (DOC_TYP_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_TYP_PLCY_RELN_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_PLCY_RELN_T
/

CREATE TABLE KREW_DOC_TYP_PLCY_RELN_T
(
      PLCY_VAL VARCHAR(64)
        , DOC_TYP_ID DECIMAL(19)
        , DOC_PLCY_NM VARCHAR(255)
        , PLCY_NM DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TP1 PRIMARY KEY(DOC_TYP_ID,DOC_PLCY_NM)


    , INDEX KREW_DOC_TYP_PLCY_RELN_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_TYP_PROC_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_PROC_T
/

CREATE TABLE KREW_DOC_TYP_PROC_T
(
      DOC_TYP_PROC_ID DECIMAL(19)
        , DOC_TYP_ID DECIMAL(19) NOT NULL
        , INIT_RTE_NODE_ID DECIMAL(22)
        , NM VARCHAR(255) NOT NULL
        , INIT_IND DECIMAL(1) default 0 NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_DOC_TYP_PROC_TP1 PRIMARY KEY(DOC_TYP_PROC_ID)


    , INDEX KREW_DOC_TYP_PROC_TI1 (DOC_TYP_ID)
    , INDEX KREW_DOC_TYP_PROC_TI2 (INIT_RTE_NODE_ID)
    , INDEX KREW_DOC_TYP_PROC_TI3 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_DOC_TYP_T
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_T
/

CREATE TABLE KREW_DOC_TYP_T
(
      DOC_TYP_ID DECIMAL(19)
        , PARNT_ID DECIMAL(19)
        , DOC_TYP_NM VARCHAR(64)
        , DOC_TYP_VER_NBR DECIMAL(10) default 0
        , ACTV_IND DECIMAL(1)
        , CUR_IND DECIMAL(1)
        , LBL VARCHAR(128)
        , PREV_DOC_TYP_VER_NBR DECIMAL(19)
        , DOC_HDR_ID DECIMAL(14)
        , DOC_TYP_DESC VARCHAR(4000)
        , DOC_HDLR_URL VARCHAR(255)
        , POST_PRCSR VARCHAR(255)
        , JNDI_URL VARCHAR(255)
        , BLNKT_APPR_PLCY VARCHAR(10)
        , ADV_DOC_SRCH_URL VARCHAR(255)
        , CSTM_ACTN_LIST_ATTRIB_CLS_NM VARCHAR(255)
        , CSTM_ACTN_EMAIL_ATTRIB_CLS_NM VARCHAR(255)
        , CSTM_DOC_NTE_ATTRIB_CLS_NM VARCHAR(255)
        , RTE_VER_NBR VARCHAR(2) default '1'
        , NOTIFY_ADDR VARCHAR(255)
        , SVC_NMSPC VARCHAR(255)
        , EMAIL_XSL VARCHAR(255)
        , SEC_XML LONGTEXT
        , VER_NBR DECIMAL(8) default 0
        , BLNKT_APPR_GRP_ID VARCHAR(40)
        , RPT_GRP_ID VARCHAR(40)
        , GRP_ID VARCHAR(40)
        , HELP_DEF_URL VARCHAR(4000)
        , OBJ_ID VARCHAR(36) NOT NULL
        , DOC_SEARCH_HELP_URL VARCHAR(4000)
    
    , CONSTRAINT KREW_DOC_TYP_TP1 PRIMARY KEY(DOC_TYP_ID)


    , INDEX KREW_DOC_TYP_TC0 (OBJ_ID)
    , INDEX KREW_DOC_TYP_TI1 (DOC_TYP_NM, DOC_TYP_VER_NBR)
    , INDEX KREW_DOC_TYP_TI2 (PARNT_ID)
    , INDEX KREW_DOC_TYP_TI3 (DOC_TYP_ID, PARNT_ID)
    , INDEX KREW_DOC_TYP_TI4 (PREV_DOC_TYP_VER_NBR)
    , INDEX KREW_DOC_TYP_TI5 (CUR_IND)
    , INDEX KREW_DOC_TYP_TI6 (DOC_TYP_NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_EDL_ASSCTN_T
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_ASSCTN_T
/

CREATE TABLE KREW_EDL_ASSCTN_T
(
      EDOCLT_ASSOC_ID DECIMAL(19)
        , DOC_TYP_NM VARCHAR(64) NOT NULL
        , EDL_DEF_NM VARCHAR(200)
        , STYLE_NM VARCHAR(200)
        , ACTV_IND DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_EDL_ASSCTN_TP1 PRIMARY KEY(EDOCLT_ASSOC_ID)


    , INDEX KREW_EDL_ASSCTN_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_EDL_DEF_T
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_DEF_T
/

CREATE TABLE KREW_EDL_DEF_T
(
      EDOCLT_DEF_ID DECIMAL(19)
        , NM VARCHAR(200) NOT NULL
        , XML LONGTEXT NOT NULL
        , ACTV_IND DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_EDL_DEF_TP1 PRIMARY KEY(EDOCLT_DEF_ID)


    , INDEX KREW_EDL_DEF_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_EDL_DMP_T
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_DMP_T
/

CREATE TABLE KREW_EDL_DMP_T
(
      DOC_HDR_ID DECIMAL(14)
        , DOC_TYP_NM VARCHAR(64) NOT NULL
        , DOC_HDR_STAT_CD CHAR(1) NOT NULL
        , DOC_HDR_MDFN_DT DATETIME NOT NULL
        , DOC_HDR_CRTE_DT DATETIME NOT NULL
        , DOC_HDR_TTL VARCHAR(255)
        , DOC_HDR_INITR_PRNCPL_ID VARCHAR(40) NOT NULL
        , CRNT_NODE_NM VARCHAR(30) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_EDL_DMP_TP1 PRIMARY KEY(DOC_HDR_ID)


    , INDEX KREW_EDL_DMP_TI1 (DOC_TYP_NM, DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_EDL_FLD_DMP_T
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_FLD_DMP_T
/

CREATE TABLE KREW_EDL_FLD_DMP_T
(
      EDL_FIELD_DMP_ID DECIMAL(14)
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , FLD_NM VARCHAR(255) NOT NULL
        , FLD_VAL VARCHAR(4000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_EDL_FLD_DMP_TP1 PRIMARY KEY(EDL_FIELD_DMP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_HLP_T
# -----------------------------------------------------------------------
drop table if exists KREW_HLP_T
/

CREATE TABLE KREW_HLP_T
(
      HLP_ID DECIMAL(19)
        , NM VARCHAR(500) NOT NULL
        , KEY_CD VARCHAR(500) NOT NULL
        , HLP_TXT VARCHAR(4000) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_HLP_TP1 PRIMARY KEY(HLP_ID)


    , INDEX KREW_HLP_TI1 (KEY_CD)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_INIT_RTE_NODE_INSTN_T
# -----------------------------------------------------------------------
drop table if exists KREW_INIT_RTE_NODE_INSTN_T
/

CREATE TABLE KREW_INIT_RTE_NODE_INSTN_T
(
      DOC_HDR_ID DECIMAL(19)
        , RTE_NODE_INSTN_ID DECIMAL(19)
    
    , CONSTRAINT KREW_INIT_RTE_NODE_INSTN_TP1 PRIMARY KEY(DOC_HDR_ID,RTE_NODE_INSTN_ID)


    , INDEX KREW_INIT_RTE_NODE_INSTN_TI1 (DOC_HDR_ID)
    , INDEX KREW_INIT_RTE_NODE_INSTN_TI2 (RTE_NODE_INSTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_OUT_BOX_ITM_T
# -----------------------------------------------------------------------
drop table if exists KREW_OUT_BOX_ITM_T
/

CREATE TABLE KREW_OUT_BOX_ITM_T
(
      ACTN_ITM_ID DECIMAL(14)
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , ASND_DT DATETIME NOT NULL
        , RQST_CD CHAR(1) NOT NULL
        , ACTN_RQST_ID DECIMAL(14) NOT NULL
        , DOC_HDR_ID DECIMAL(14) NOT NULL
        , ROLE_NM VARCHAR(2000)
        , DLGN_PRNCPL_ID VARCHAR(40)
        , DOC_HDR_TTL VARCHAR(255)
        , DOC_TYP_LBL VARCHAR(128) NOT NULL
        , DOC_HDLR_URL VARCHAR(255) NOT NULL
        , DOC_TYP_NM VARCHAR(64) NOT NULL
        , RSP_ID DECIMAL(14) NOT NULL
        , DLGN_TYP VARCHAR(1)
        , VER_NBR DECIMAL(8) default 0
        , GRP_ID VARCHAR(40)
        , DLGN_GRP_ID VARCHAR(40)
        , RQST_LBL VARCHAR(255)
    
    , CONSTRAINT KREW_OUT_BOX_ITM_TP1 PRIMARY KEY(ACTN_ITM_ID)


    , INDEX KREW_OUT_BOX_ITM_TI1 (PRNCPL_ID)
    , INDEX KREW_OUT_BOX_ITM_TI2 (DOC_HDR_ID)
    , INDEX KREW_OUT_BOX_ITM_TI3 (ACTN_RQST_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RMV_RPLC_DOC_T
# -----------------------------------------------------------------------
drop table if exists KREW_RMV_RPLC_DOC_T
/

CREATE TABLE KREW_RMV_RPLC_DOC_T
(
      DOC_HDR_ID DECIMAL(14)
        , OPRN CHAR(1) NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , RPLC_PRNCPL_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RMV_RPLC_DOC_TP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RMV_RPLC_GRP_T
# -----------------------------------------------------------------------
drop table if exists KREW_RMV_RPLC_GRP_T
/

CREATE TABLE KREW_RMV_RPLC_GRP_T
(
      DOC_HDR_ID DECIMAL(14)
        , GRP_ID DECIMAL(14)
    
    , CONSTRAINT KREW_RMV_RPLC_GRP_TP1 PRIMARY KEY(DOC_HDR_ID,GRP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RMV_RPLC_RULE_T
# -----------------------------------------------------------------------
drop table if exists KREW_RMV_RPLC_RULE_T
/

CREATE TABLE KREW_RMV_RPLC_RULE_T
(
      DOC_HDR_ID DECIMAL(14)
        , RULE_ID DECIMAL(19)
    
    , CONSTRAINT KREW_RMV_RPLC_RULE_TP1 PRIMARY KEY(DOC_HDR_ID,RULE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_BRCH_PROTO_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_BRCH_PROTO_T
/

CREATE TABLE KREW_RTE_BRCH_PROTO_T
(
      RTE_BRCH_PROTO_ID DECIMAL(19)
        , BRCH_NM VARCHAR(255) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RTE_BRCH_PROTO_TP1 PRIMARY KEY(RTE_BRCH_PROTO_ID)


    , INDEX KREW_RTE_BRCH_PROTO_TI1 (BRCH_NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_BRCH_ST_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_BRCH_ST_T
/

CREATE TABLE KREW_RTE_BRCH_ST_T
(
      RTE_BRCH_ST_ID DECIMAL(19)
        , RTE_BRCH_ID DECIMAL(19) NOT NULL
        , KEY_CD VARCHAR(255) NOT NULL
        , VAL VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RTE_BRCH_ST_TP1 PRIMARY KEY(RTE_BRCH_ST_ID)


    , INDEX KREW_RTE_BRCH_ST_TI1 (RTE_BRCH_ID, KEY_CD)
    , INDEX KREW_RTE_BRCH_ST_TI2 (RTE_BRCH_ID)
    , INDEX KREW_RTE_BRCH_ST_TI3 (KEY_CD, VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_BRCH_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_BRCH_T
/

CREATE TABLE KREW_RTE_BRCH_T
(
      RTE_BRCH_ID DECIMAL(19)
        , NM VARCHAR(255) NOT NULL
        , PARNT_ID DECIMAL(19)
        , INIT_RTE_NODE_INSTN_ID DECIMAL(19)
        , SPLT_RTE_NODE_INSTN_ID DECIMAL(19)
        , JOIN_RTE_NODE_INSTN_ID DECIMAL(19)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RTE_BRCH_TP1 PRIMARY KEY(RTE_BRCH_ID)


    , INDEX KREW_RTE_BRCH_TI1 (NM)
    , INDEX KREW_RTE_BRCH_TI2 (PARNT_ID)
    , INDEX KREW_RTE_BRCH_TI3 (INIT_RTE_NODE_INSTN_ID)
    , INDEX KREW_RTE_BRCH_TI4 (SPLT_RTE_NODE_INSTN_ID)
    , INDEX KREW_RTE_BRCH_TI5 (JOIN_RTE_NODE_INSTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_CFG_PARM_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_CFG_PARM_T
/

CREATE TABLE KREW_RTE_NODE_CFG_PARM_T
(
      RTE_NODE_CFG_PARM_ID DECIMAL(19)
        , RTE_NODE_ID DECIMAL(19) NOT NULL
        , KEY_CD VARCHAR(255) NOT NULL
        , VAL VARCHAR(4000)
    
    , CONSTRAINT KREW_RTE_NODE_CFG_PARM_TP1 PRIMARY KEY(RTE_NODE_CFG_PARM_ID)


    , INDEX KREW_RTE_NODE_CFG_PARM_TI1 (RTE_NODE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_INSTN_LNK_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_INSTN_LNK_T
/

CREATE TABLE KREW_RTE_NODE_INSTN_LNK_T
(
      FROM_RTE_NODE_INSTN_ID DECIMAL(19)
        , TO_RTE_NODE_INSTN_ID DECIMAL(19)
    
    , CONSTRAINT KREW_RTE_NODE_INSTN_LNK_TP1 PRIMARY KEY(FROM_RTE_NODE_INSTN_ID,TO_RTE_NODE_INSTN_ID)


    , INDEX KREW_RTE_NODE_INSTN_LNK_TI1 (FROM_RTE_NODE_INSTN_ID)
    , INDEX KREW_RTE_NODE_INSTN_LNK_TI2 (TO_RTE_NODE_INSTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_INSTN_ST_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_INSTN_ST_T
/

CREATE TABLE KREW_RTE_NODE_INSTN_ST_T
(
      RTE_NODE_INSTN_ST_ID DECIMAL(19)
        , RTE_NODE_INSTN_ID DECIMAL(19) NOT NULL
        , KEY_CD VARCHAR(255) NOT NULL
        , VAL VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RTE_NODE_INSTN_ST_TP1 PRIMARY KEY(RTE_NODE_INSTN_ST_ID)


    , INDEX KREW_RTE_NODE_INSTN_ST_TI1 (RTE_NODE_INSTN_ID, KEY_CD)
    , INDEX KREW_RTE_NODE_INSTN_ST_TI2 (RTE_NODE_INSTN_ID)
    , INDEX KREW_RTE_NODE_INSTN_ST_TI3 (KEY_CD, VAL)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_INSTN_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_INSTN_T
/

CREATE TABLE KREW_RTE_NODE_INSTN_T
(
      RTE_NODE_INSTN_ID DECIMAL(19)
        , DOC_HDR_ID DECIMAL(19) NOT NULL
        , RTE_NODE_ID DECIMAL(19) NOT NULL
        , BRCH_ID DECIMAL(19)
        , PROC_RTE_NODE_INSTN_ID DECIMAL(19)
        , ACTV_IND DECIMAL(1) default 0 NOT NULL
        , CMPLT_IND DECIMAL(1) default 0 NOT NULL
        , INIT_IND DECIMAL(1) default 0 NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RTE_NODE_INSTN_TP1 PRIMARY KEY(RTE_NODE_INSTN_ID)


    , INDEX KREW_RTE_NODE_INSTN_TI1 (DOC_HDR_ID, ACTV_IND, CMPLT_IND)
    , INDEX KREW_RTE_NODE_INSTN_TI2 (RTE_NODE_ID)
    , INDEX KREW_RTE_NODE_INSTN_TI3 (BRCH_ID)
    , INDEX KREW_RTE_NODE_INSTN_TI4 (PROC_RTE_NODE_INSTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_LNK_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_LNK_T
/

CREATE TABLE KREW_RTE_NODE_LNK_T
(
      FROM_RTE_NODE_ID DECIMAL(19)
        , TO_RTE_NODE_ID DECIMAL(19)
    
    , CONSTRAINT KREW_RTE_NODE_LNK_TP1 PRIMARY KEY(FROM_RTE_NODE_ID,TO_RTE_NODE_ID)


    , INDEX KREW_RTE_NODE_LNK_TI1 (FROM_RTE_NODE_ID)
    , INDEX KREW_RTE_NODE_LNK_TI2 (TO_RTE_NODE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RTE_NODE_T
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_T
/

CREATE TABLE KREW_RTE_NODE_T
(
      RTE_NODE_ID DECIMAL(19)
        , DOC_TYP_ID DECIMAL(19)
        , NM VARCHAR(255) NOT NULL
        , TYP VARCHAR(255) NOT NULL
        , RTE_MTHD_NM VARCHAR(255)
        , RTE_MTHD_CD VARCHAR(2)
        , FNL_APRVR_IND DECIMAL(1)
        , MNDTRY_RTE_IND DECIMAL(1)
        , ACTVN_TYP VARCHAR(250)
        , BRCH_PROTO_ID DECIMAL(19)
        , VER_NBR DECIMAL(8) default 0
        , CONTENT_FRAGMENT VARCHAR(4000)
        , GRP_ID VARCHAR(40)
        , NEXT_DOC_STAT VARCHAR(64)
    
    , CONSTRAINT KREW_RTE_NODE_TP1 PRIMARY KEY(RTE_NODE_ID)


    , INDEX KREW_RTE_NODE_TI1 (NM, DOC_TYP_ID)
    , INDEX KREW_RTE_NODE_TI2 (DOC_TYP_ID, FNL_APRVR_IND)
    , INDEX KREW_RTE_NODE_TI3 (BRCH_PROTO_ID)
    , INDEX KREW_RTE_NODE_TI4 (DOC_TYP_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_ATTR_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_ATTR_T
/

CREATE TABLE KREW_RULE_ATTR_T
(
      RULE_ATTR_ID DECIMAL(19)
        , NM VARCHAR(255) NOT NULL
        , LBL VARCHAR(2000) NOT NULL
        , RULE_ATTR_TYP_CD VARCHAR(2000) NOT NULL
        , DESC_TXT VARCHAR(2000)
        , CLS_NM VARCHAR(2000)
        , XML LONGTEXT
        , VER_NBR DECIMAL(8) default 0
        , SVC_NMSPC VARCHAR(255)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_ATTR_TP1 PRIMARY KEY(RULE_ATTR_ID)


    , INDEX KREW_RULE_ATTR_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_EXPR_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_EXPR_T
/

CREATE TABLE KREW_RULE_EXPR_T
(
      RULE_EXPR_ID DECIMAL(19)
        , TYP VARCHAR(256) NOT NULL
        , RULE_EXPR VARCHAR(4000)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RULE_EXPR_TP1 PRIMARY KEY(RULE_EXPR_ID)


    , INDEX KREW_RULE_EXPR_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_EXT_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_EXT_T
/

CREATE TABLE KREW_RULE_EXT_T
(
      RULE_EXT_ID DECIMAL(19)
        , RULE_TMPL_ATTR_ID DECIMAL(19) NOT NULL
        , RULE_ID DECIMAL(19) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RULE_EXT_TP1 PRIMARY KEY(RULE_EXT_ID)


    , INDEX KREW_RULE_EXT_T1 (RULE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_EXT_VAL_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_EXT_VAL_T
/

CREATE TABLE KREW_RULE_EXT_VAL_T
(
      RULE_EXT_VAL_ID DECIMAL(19)
        , RULE_EXT_ID DECIMAL(19) NOT NULL
        , VAL VARCHAR(2000) NOT NULL
        , KEY_CD VARCHAR(2000) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RULE_EXT_VAL_TP1 PRIMARY KEY(RULE_EXT_VAL_ID)


    , INDEX KREW_RULE_EXT_VAL_T1 (RULE_EXT_ID)
    , INDEX KREW_RULE_EXT_VAL_T2 (RULE_EXT_VAL_ID, KEY_CD)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_RSP_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_RSP_T
/

CREATE TABLE KREW_RULE_RSP_T
(
      RULE_RSP_ID DECIMAL(19)
        , RSP_ID DECIMAL(19) NOT NULL
        , RULE_ID DECIMAL(19) NOT NULL
        , PRIO DECIMAL(5)
        , ACTN_RQST_CD VARCHAR(2000)
        , NM VARCHAR(200)
        , TYP VARCHAR(1)
        , APPR_PLCY CHAR(1)
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_RSP_TP1 PRIMARY KEY(RULE_RSP_ID)


    , INDEX KREW_RULE_RSP_TC0 (OBJ_ID)
    , INDEX KREW_RULE_RSP_TI1 (RULE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_T
/

CREATE TABLE KREW_RULE_T
(
      RULE_ID DECIMAL(19)
        , NM VARCHAR(256)
        , RULE_TMPL_ID DECIMAL(19)
        , RULE_EXPR_ID DECIMAL(19)
        , ACTV_IND DECIMAL(1) NOT NULL
        , RULE_BASE_VAL_DESC VARCHAR(2000)
        , FRC_ACTN DECIMAL(1) NOT NULL
        , DOC_TYP_NM VARCHAR(64) NOT NULL
        , DOC_HDR_ID DECIMAL(14)
        , TMPL_RULE_IND DECIMAL(1)
        , FRM_DT DATETIME
        , TO_DT DATETIME
        , DACTVN_DT DATETIME
        , CUR_IND DECIMAL(1) default 0
        , RULE_VER_NBR DECIMAL(8) default 0
        , DLGN_IND DECIMAL(1)
        , PREV_RULE_VER_NBR DECIMAL(19)
        , ACTVN_DT DATETIME
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TP1 PRIMARY KEY(RULE_ID)


    , INDEX KREW_RULE_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_TMPL_ATTR_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_TMPL_ATTR_T
/

CREATE TABLE KREW_RULE_TMPL_ATTR_T
(
      RULE_TMPL_ATTR_ID DECIMAL(19)
        , RULE_TMPL_ID DECIMAL(19) NOT NULL
        , RULE_ATTR_ID DECIMAL(19) NOT NULL
        , REQ_IND DECIMAL(1) NOT NULL
        , ACTV_IND DECIMAL(1) NOT NULL
        , DSPL_ORD DECIMAL(5) NOT NULL
        , DFLT_VAL VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TMPL_ATTR_TP1 PRIMARY KEY(RULE_TMPL_ATTR_ID)


    , INDEX KREW_RULE_TMPL_ATTR_TC0 (OBJ_ID)
    , INDEX KREW_RULE_TMPL_ATTR_TI1 (RULE_TMPL_ID)
    , INDEX KREW_RULE_TMPL_ATTR_TI2 (RULE_ATTR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_TMPL_OPTN_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_TMPL_OPTN_T
/

CREATE TABLE KREW_RULE_TMPL_OPTN_T
(
      RULE_TMPL_OPTN_ID DECIMAL(19)
        , RULE_TMPL_ID DECIMAL(19)
        , KEY_CD VARCHAR(250)
        , VAL VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_RULE_TMPL_OPTN_TP1 PRIMARY KEY(RULE_TMPL_OPTN_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RULE_TMPL_T
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_TMPL_T
/

CREATE TABLE KREW_RULE_TMPL_T
(
      RULE_TMPL_ID DECIMAL(19)
        , NM VARCHAR(250) NOT NULL
        , RULE_TMPL_DESC VARCHAR(2000)
        , DLGN_RULE_TMPL_ID DECIMAL(19)
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RULE_TMPL_TP1 PRIMARY KEY(RULE_TMPL_ID)


    , INDEX KREW_RULE_TMPL_TC0 (OBJ_ID)
    , INDEX KREW_RULE_TMPL_TI1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_STYLE_T
# -----------------------------------------------------------------------
drop table if exists KREW_STYLE_T
/

CREATE TABLE KREW_STYLE_T
(
      STYLE_ID DECIMAL(19)
        , NM VARCHAR(200) NOT NULL
        , XML LONGTEXT NOT NULL
        , ACTV_IND DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_STYLE_TP1 PRIMARY KEY(STYLE_ID)


    , INDEX KREW_STYLE_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_USR_OPTN_T
# -----------------------------------------------------------------------
drop table if exists KREW_USR_OPTN_T
/

CREATE TABLE KREW_USR_OPTN_T
(
      PRNCPL_ID VARCHAR(40)
        , PRSN_OPTN_ID VARCHAR(200)
        , VAL VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KREW_USR_OPTN_TP1 PRIMARY KEY(PRNCPL_ID,PRSN_OPTN_ID)


    , INDEX KREW_USR_OPTN_TI1 (PRNCPL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ADDR_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ADDR_TYP_T
/

CREATE TABLE KRIM_ADDR_TYP_T
(
      ADDR_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ADDR_TYP_TP1 PRIMARY KEY(ADDR_TYP_CD)


    , INDEX KRIM_ADDR_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_ADDR_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_AFLTN_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_AFLTN_TYP_T
/

CREATE TABLE KRIM_AFLTN_TYP_T
(
      AFLTN_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , EMP_AFLTN_TYP_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_AFLTN_TYP_TP1 PRIMARY KEY(AFLTN_TYP_CD)


    , INDEX KRIM_AFLTN_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_AFLTN_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ATTR_DEFN_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ATTR_DEFN_T
/

CREATE TABLE KRIM_ATTR_DEFN_T
(
      KIM_ATTR_DEFN_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(100)
        , LBL VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , NMSPC_CD VARCHAR(40)
        , CMPNT_NM VARCHAR(100)
    
    , CONSTRAINT KRIM_ATTR_DEFN_TP1 PRIMARY KEY(KIM_ATTR_DEFN_ID)


    , INDEX KRIM_ATTR_DEFN_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_CTZNSHP_STAT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_CTZNSHP_STAT_T
/

CREATE TABLE KRIM_CTZNSHP_STAT_T
(
      NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , CTZNSHP_STAT_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_CTZNSHP_STAT_TP1 PRIMARY KEY(CTZNSHP_STAT_CD)


    , INDEX KRIM_CTZNSHP_STAT_TC0 (OBJ_ID)
    , INDEX KRIM_CTZNSHP_STAT_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_DLGN_MBR_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_MBR_ATTR_DATA_T
/

CREATE TABLE KRIM_DLGN_MBR_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DLGN_MBR_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TP1 PRIMARY KEY(ATTR_DATA_ID)


    , INDEX KRIM_DLGN_MBR_ATTR_DATA_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_DLGN_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_MBR_T
/

CREATE TABLE KRIM_DLGN_MBR_T
(
      ROLE_MBR_ID VARCHAR(40)
        , DLGN_MBR_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , DLGN_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_DLGN_MBR_TP1 PRIMARY KEY(DLGN_MBR_ID)


    , INDEX KRIM_DLGN_MBR_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_DLGN_T
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_T
/

CREATE TABLE KRIM_DLGN_T
(
      DLGN_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , ROLE_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , DLGN_TYP_CD VARCHAR(1)
    
    , CONSTRAINT KRIM_DLGN_TP1 PRIMARY KEY(DLGN_ID)


    , INDEX KRIM_DLGN_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_EMAIL_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_EMAIL_TYP_T
/

CREATE TABLE KRIM_EMAIL_TYP_T
(
      EMAIL_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_EMAIL_TYP_TP1 PRIMARY KEY(EMAIL_TYP_CD)


    , INDEX KRIM_EMAIL_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_EMAIL_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_EMP_STAT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_EMP_STAT_T
/

CREATE TABLE KRIM_EMP_STAT_T
(
      EMP_STAT_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_EMP_STAT_TP1 PRIMARY KEY(EMP_STAT_CD)


    , INDEX KRIM_EMP_STAT_TC0 (OBJ_ID)
    , INDEX KRIM_EMP_STAT_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_EMP_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_EMP_TYP_T
/

CREATE TABLE KRIM_EMP_TYP_T
(
      EMP_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_EMP_TYP_TP1 PRIMARY KEY(EMP_TYP_CD)


    , INDEX KRIM_EMP_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_EMP_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_ADDR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ADDR_T
/

CREATE TABLE KRIM_ENTITY_ADDR_T
(
      POSTAL_CNTRY_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , ENTITY_ADDR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , ENT_TYP_CD VARCHAR(40)
        , ADDR_TYP_CD VARCHAR(40)
        , ADDR_LINE_1 VARCHAR(45)
        , ADDR_LINE_2 VARCHAR(45)
        , ADDR_LINE_3 VARCHAR(45)
        , CITY_NM VARCHAR(30)
        , POSTAL_STATE_CD VARCHAR(2)
        , POSTAL_CD VARCHAR(20)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_ADDR_TP1 PRIMARY KEY(ENTITY_ADDR_ID)


    , INDEX KRIM_ENTITY_ADDR_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_ADDR_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_AFLTN_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_AFLTN_T
/

CREATE TABLE KRIM_ENTITY_AFLTN_T
(
      ENTITY_AFLTN_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , AFLTN_TYP_CD VARCHAR(40)
        , CAMPUS_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_AFLTN_TP1 PRIMARY KEY(ENTITY_AFLTN_ID)


    , INDEX KRIM_ENTITY_AFLTN_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_AFLTN_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_BIO_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_BIO_T
/

CREATE TABLE KRIM_ENTITY_BIO_T
(
      DECEASED_DT DATETIME
        , MARITAL_STATUS VARCHAR(40)
        , PRIM_LANG_CD VARCHAR(40)
        , SEC_LANG_CD VARCHAR(40)
        , BIRTH_CNTRY_CD VARCHAR(2)
        , BIRTH_STATE_CD VARCHAR(2)
        , BIRTH_CITY VARCHAR(30)
        , GEO_ORIGIN VARCHAR(100)
        , ENTITY_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , BIRTH_DT DATETIME
        , GNDR_CD VARCHAR(1) NOT NULL
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_BIO_TP1 PRIMARY KEY(ENTITY_ID)


    , INDEX KRIM_ENTITY_BIO_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_CACHE_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_CACHE_T
/

CREATE TABLE KRIM_ENTITY_CACHE_T
(
      ENTITY_ID VARCHAR(40)
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , PRNCPL_NM VARCHAR(40)
        , ENTITY_TYP_CD VARCHAR(40)
        , FIRST_NM VARCHAR(40)
        , MIDDLE_NM VARCHAR(40)
        , LAST_NM VARCHAR(40)
        , PRSN_NM VARCHAR(40)
        , CAMPUS_CD VARCHAR(40)
        , PRMRY_DEPT_CD VARCHAR(40)
        , EMP_ID VARCHAR(40)
        , LAST_UPDT_TS DATETIME
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_CACHE_TP1 PRIMARY KEY(ENTITY_ID)


    , INDEX KRIM_ENTITY_CACHE_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_CACHE_TC1 (PRNCPL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_CTZNSHP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_CTZNSHP_T
/

CREATE TABLE KRIM_ENTITY_CTZNSHP_T
(
      ENTITY_CTZNSHP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , POSTAL_CNTRY_CD VARCHAR(2)
        , CTZNSHP_STAT_CD VARCHAR(40)
        , STRT_DT DATETIME
        , END_DT DATETIME
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_CTZNSHP_TP1 PRIMARY KEY(ENTITY_CTZNSHP_ID)


    , INDEX KRIM_ENTITY_CTZNSHP_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_EMAIL_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EMAIL_T
/

CREATE TABLE KRIM_ENTITY_EMAIL_T
(
      ENTITY_EMAIL_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , ENT_TYP_CD VARCHAR(40)
        , EMAIL_TYP_CD VARCHAR(40)
        , EMAIL_ADDR VARCHAR(200)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_EMAIL_TP1 PRIMARY KEY(ENTITY_EMAIL_ID)


    , INDEX KRIM_ENTITY_EMAIL_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_EMAIL_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_EMP_INFO_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EMP_INFO_T
/

CREATE TABLE KRIM_ENTITY_EMP_INFO_T
(
      ENTITY_EMP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , ENTITY_AFLTN_ID VARCHAR(40)
        , EMP_STAT_CD VARCHAR(40)
        , EMP_TYP_CD VARCHAR(40)
        , BASE_SLRY_AMT DECIMAL(15,2)
        , PRMRY_IND VARCHAR(1)
        , ACTV_IND VARCHAR(1) default 'Y'
        , PRMRY_DEPT_CD VARCHAR(40)
        , EMP_ID VARCHAR(40)
        , EMP_REC_ID VARCHAR(40)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_EMP_INFO_TP1 PRIMARY KEY(ENTITY_EMP_ID)


    , INDEX KRIM_ENTITY_EMP_INFO_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_EMP_INFO_TI1 (ENTITY_ID)
    , INDEX KRIM_ENTITY_EMP_INFO_TI2 (ENTITY_AFLTN_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_ENT_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ENT_TYP_T
/

CREATE TABLE KRIM_ENTITY_ENT_TYP_T
(
      ACTV_IND VARCHAR(1) default 'Y'
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENT_TYP_CD VARCHAR(40)
        , ENTITY_ID VARCHAR(40)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_ENT_TYP_TP1 PRIMARY KEY(ENT_TYP_CD,ENTITY_ID)


    , INDEX KRIM_ENTITY_ENT_TYP_TI1 (ENTITY_ID)
    , INDEX KR_KIM_ENTITY_ENT_TYPE_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_ETHNIC_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ETHNIC_T
/

CREATE TABLE KRIM_ENTITY_ETHNIC_T
(
      ID VARCHAR(40)
        , ENTITY_ID VARCHAR(40)
        , ETHNCTY_CD VARCHAR(40)
        , SUB_ETHNCTY_CD VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_ETHNIC_TP1 PRIMARY KEY(ID)


    , INDEX KRIM_ENTITY_ETHNIC_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_EXT_ID_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EXT_ID_T
/

CREATE TABLE KRIM_ENTITY_EXT_ID_T
(
      ENTITY_EXT_ID_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , EXT_ID_TYP_CD VARCHAR(40)
        , EXT_ID VARCHAR(100)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_EXT_ID_TP1 PRIMARY KEY(ENTITY_EXT_ID_ID)


    , INDEX KRIM_ENTITY_EXT_ID_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_EXT_ID_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_NM_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_NM_T
/

CREATE TABLE KRIM_ENTITY_NM_T
(
      ENTITY_NM_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , NM_TYP_CD VARCHAR(40)
        , FIRST_NM VARCHAR(40)
        , MIDDLE_NM VARCHAR(40)
        , LAST_NM VARCHAR(80)
        , SUFFIX_NM VARCHAR(20)
        , TITLE_NM VARCHAR(20)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_NM_TP1 PRIMARY KEY(ENTITY_NM_ID)


    , INDEX KRIM_ENTITY_NM_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_NM_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_PHONE_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_PHONE_T
/

CREATE TABLE KRIM_ENTITY_PHONE_T
(
      ENTITY_PHONE_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_ID VARCHAR(40)
        , ENT_TYP_CD VARCHAR(40)
        , PHONE_TYP_CD VARCHAR(40)
        , PHONE_NBR VARCHAR(20)
        , PHONE_EXTN_NBR VARCHAR(8)
        , POSTAL_CNTRY_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_PHONE_TP1 PRIMARY KEY(ENTITY_PHONE_ID)


    , INDEX KRIM_ENTITY_PHONE_TC0 (OBJ_ID)
    , INDEX KRIM_ENTITY_PHONE_TI1 (ENTITY_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_PRIV_PREF_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_PRIV_PREF_T
/

CREATE TABLE KRIM_ENTITY_PRIV_PREF_T
(
      ENTITY_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SUPPRESS_NM_IND VARCHAR(1) default 'N'
        , SUPPRESS_EMAIL_IND VARCHAR(1) default 'Y'
        , SUPPRESS_ADDR_IND VARCHAR(1) default 'Y'
        , SUPPRESS_PHONE_IND VARCHAR(1) default 'Y'
        , SUPPRESS_PRSNL_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_PRIV_PREF_TP1 PRIMARY KEY(ENTITY_ID)


    , INDEX KRIM_ENTITY_PRIV_PREF_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_RESIDENCY_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_RESIDENCY_T
/

CREATE TABLE KRIM_ENTITY_RESIDENCY_T
(
      ID VARCHAR(40)
        , ENTITY_ID VARCHAR(40)
        , DETERMINATION_METHOD VARCHAR(40)
        , IN_STATE VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_RESIDENCY_TP1 PRIMARY KEY(ID)


    , INDEX KRIM_ENTITY_RESIDENCY_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_T
/

CREATE TABLE KRIM_ENTITY_T
(
      ENTITY_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_TP1 PRIMARY KEY(ENTITY_ID)


    , INDEX KRIM_ENTITY_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_VISA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_VISA_T
/

CREATE TABLE KRIM_ENTITY_VISA_T
(
      ID VARCHAR(40)
        , ENTITY_ID VARCHAR(40)
        , VISA_TYPE_KEY VARCHAR(40)
        , VISA_ENTRY VARCHAR(40)
        , VISA_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_ENTITY_VISA_TP1 PRIMARY KEY(ID)


    , INDEX KRIM_ENTITY_VISA_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENT_NM_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENT_NM_TYP_T
/

CREATE TABLE KRIM_ENT_NM_TYP_T
(
      ENT_NM_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ENT_NM_TYP_TP1 PRIMARY KEY(ENT_NM_TYP_CD)


    , INDEX KRIM_ENT_NM_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_ENT_NM_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENT_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENT_TYP_T
/

CREATE TABLE KRIM_ENT_TYP_T
(
      ENT_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , DISPLAY_SORT_CD VARCHAR(2)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_ENT_TYP_TP1 PRIMARY KEY(ENT_TYP_CD)


    , INDEX KRIM_ENT_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_ENT_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_EXT_ID_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_EXT_ID_TYP_T
/

CREATE TABLE KRIM_EXT_ID_TYP_T
(
      EXT_ID_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ENCR_REQ_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_EXT_ID_TYP_TP1 PRIMARY KEY(EXT_ID_TYP_CD)


    , INDEX KRIM_EXT_ID_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_EXT_ID_TYP_TC1 (NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_GRP_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_ATTR_DATA_T
/

CREATE TABLE KRIM_GRP_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , GRP_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_GRP_ATTR_DATA_TP1 PRIMARY KEY(ATTR_DATA_ID)


    , INDEX KRIM_GRP_ATTR_DATA_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_GRP_DOCUMENT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_DOCUMENT_T
/

CREATE TABLE KRIM_GRP_DOCUMENT_T
(
      FDOC_NBR VARCHAR(14)
        , GRP_ID VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , GRP_NMSPC VARCHAR(100) NOT NULL
        , GRP_NM VARCHAR(400)
        , GRP_DESC VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_GRP_DOCUMENT_TP1 PRIMARY KEY(FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_GRP_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_MBR_T
/

CREATE TABLE KRIM_GRP_MBR_T
(
      GRP_MBR_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , GRP_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_GRP_MBR_TP1 PRIMARY KEY(GRP_MBR_ID)


    , INDEX KRIM_GRP_MBR_TC0 (OBJ_ID)
    , INDEX KRIM_GRP_MBR_TI1 (MBR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_GRP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_T
/

CREATE TABLE KRIM_GRP_T
(
      GRP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , GRP_NM VARCHAR(80) NOT NULL
        , NMSPC_CD VARCHAR(40) NOT NULL
        , GRP_DESC VARCHAR(4000)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_GRP_TP1 PRIMARY KEY(GRP_ID)


    , INDEX KRIM_GRP_TC0 (OBJ_ID)
    , INDEX KRIM_GRP_TC1 (GRP_NM, NMSPC_CD)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PERM_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_ATTR_DATA_T
/

CREATE TABLE KRIM_PERM_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PERM_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_PERM_ATTR_DATA_TP1 PRIMARY KEY(ATTR_DATA_ID)


    , INDEX KRIM_PERM_ATTR_DATA_TC0 (OBJ_ID)
    , INDEX KRIM_PERM_ATTR_DATA_TI1 (PERM_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PERM_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_T
/

CREATE TABLE KRIM_PERM_T
(
      PERM_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PERM_TMPL_ID VARCHAR(40)
        , NMSPC_CD VARCHAR(40)
        , NM VARCHAR(100)
        , DESC_TXT VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_PERM_TP1 PRIMARY KEY(PERM_ID)


    , INDEX KRIM_PERM_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PERM_TMPL_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_TMPL_T
/

CREATE TABLE KRIM_PERM_TMPL_T
(
      PERM_TMPL_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NMSPC_CD VARCHAR(40)
        , NM VARCHAR(100)
        , DESC_TXT VARCHAR(400)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_PERM_TMPL_TP1 PRIMARY KEY(PERM_TMPL_ID)


    , INDEX KRIM_PERM_TMPL_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PERSON_DOCUMENT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PERSON_DOCUMENT_T
/

CREATE TABLE KRIM_PERSON_DOCUMENT_T
(
      PRNCPL_NM VARCHAR(100) NOT NULL
        , PRNCPL_PSWD VARCHAR(400)
        , TAX_ID VARCHAR(100)
        , UNIV_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , FDOC_NBR VARCHAR(14)
        , ENTITY_ID VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_PERSON_DOCUMENT_TP1 PRIMARY KEY(FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PHONE_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PHONE_TYP_T
/

CREATE TABLE KRIM_PHONE_TYP_T
(
      PHONE_TYP_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PHONE_TYP_NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_PHONE_TYP_TP1 PRIMARY KEY(PHONE_TYP_CD)


    , INDEX KRIM_PHONE_TYP_TC0 (OBJ_ID)
    , INDEX KRIM_PHONE_TYP_TC1 (PHONE_TYP_NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ADDR_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ADDR_MT
/

CREATE TABLE KRIM_PND_ADDR_MT
(
      FDOC_NBR VARCHAR(14)
        , ADDR_TYP_CD VARCHAR(40)
        , ADDR_LINE_1 VARCHAR(50)
        , ADDR_LINE_2 VARCHAR(50)
        , ADDR_LINE_3 VARCHAR(50)
        , CITY_NM VARCHAR(30)
        , POSTAL_STATE_CD VARCHAR(2)
        , POSTAL_CD VARCHAR(20)
        , POSTAL_CNTRY_CD VARCHAR(2)
        , DISPLAY_SORT_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , ENTITY_ADDR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_ADDR_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_ADDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_AFLTN_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_AFLTN_MT
/

CREATE TABLE KRIM_PND_AFLTN_MT
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_AFLTN_ID VARCHAR(40)
        , AFLTN_TYP_CD VARCHAR(40)
        , CAMPUS_CD VARCHAR(2)
        , EDIT_FLAG VARCHAR(1) default 'N'
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_PND_AFLTN_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_AFLTN_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_CTZNSHP_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_CTZNSHP_MT
/

CREATE TABLE KRIM_PND_CTZNSHP_MT
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_CTZNSHP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , POSTAL_CNTRY_CD VARCHAR(2)
        , CTZNSHP_STAT_CD VARCHAR(40)
        , STRT_DT DATETIME
        , END_DT DATETIME
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_CTZNSHP_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_CTZNSHP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_DLGN_MBR_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_DLGN_MBR_ATTR_DATA_T
/

CREATE TABLE KRIM_PND_DLGN_MBR_ATTR_DATA_T
(
      FDOC_NBR VARCHAR(14)
        , ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DLGN_MBR_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40)
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_DLGN_MBR_ATTR_DATAP1 PRIMARY KEY(FDOC_NBR,ATTR_DATA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_DLGN_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_DLGN_MBR_T
/

CREATE TABLE KRIM_PND_DLGN_MBR_T
(
      ROLE_MBR_ID VARCHAR(40)
        , FDOC_NBR VARCHAR(14)
        , DLGN_MBR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DLGN_ID VARCHAR(40) NOT NULL
        , MBR_ID VARCHAR(40)
        , MBR_NM VARCHAR(40)
        , MBR_TYP_CD VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
    
    , CONSTRAINT KRIM_PND_DLGN_MBR_TP1 PRIMARY KEY(FDOC_NBR,DLGN_MBR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_DLGN_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_DLGN_T
/

CREATE TABLE KRIM_PND_DLGN_T
(
      FDOC_NBR VARCHAR(14)
        , DLGN_ID VARCHAR(40)
        , ROLE_ID VARCHAR(40) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , KIM_TYP_ID VARCHAR(40)
        , DLGN_TYP_CD VARCHAR(100) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_PND_DLGN_TP1 PRIMARY KEY(FDOC_NBR,DLGN_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_EMAIL_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_EMAIL_MT
/

CREATE TABLE KRIM_PND_EMAIL_MT
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_EMAIL_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENT_TYP_CD VARCHAR(40)
        , EMAIL_TYP_CD VARCHAR(40)
        , EMAIL_ADDR VARCHAR(200)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_EMAIL_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_EMAIL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_EMP_INFO_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_EMP_INFO_MT
/

CREATE TABLE KRIM_PND_EMP_INFO_MT
(
      FDOC_NBR VARCHAR(14)
        , PRMRY_DEPT_CD VARCHAR(40)
        , ENTITY_EMP_ID VARCHAR(40)
        , EMP_ID VARCHAR(40)
        , EMP_REC_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENTITY_AFLTN_ID VARCHAR(40)
        , EMP_STAT_CD VARCHAR(40)
        , EMP_TYP_CD VARCHAR(40)
        , BASE_SLRY_AMT DECIMAL(15,2)
        , PRMRY_IND VARCHAR(1)
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_EMP_INFO_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_EMP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_GRP_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_GRP_ATTR_DATA_T
/

CREATE TABLE KRIM_PND_GRP_ATTR_DATA_T
(
      FDOC_NBR VARCHAR(14)
        , ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , GRP_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40)
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_PND_GRP_ATTR_DATA_TP1 PRIMARY KEY(FDOC_NBR,ATTR_DATA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_GRP_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_GRP_MBR_T
/

CREATE TABLE KRIM_PND_GRP_MBR_T
(
      FDOC_NBR VARCHAR(14)
        , GRP_MBR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , GRP_ID VARCHAR(40) NOT NULL
        , MBR_ID VARCHAR(40)
        , MBR_NM VARCHAR(40)
        , MBR_TYP_CD VARCHAR(40) NOT NULL
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
    
    , CONSTRAINT KRIM_PND_GRP_MBR_TP1 PRIMARY KEY(FDOC_NBR,GRP_MBR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_GRP_PRNCPL_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_GRP_PRNCPL_MT
/

CREATE TABLE KRIM_PND_GRP_PRNCPL_MT
(
      GRP_MBR_ID VARCHAR(40)
        , FDOC_NBR VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , GRP_ID VARCHAR(40) NOT NULL
        , PRNCPL_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , GRP_NM VARCHAR(80) NOT NULL
        , GRP_TYPE VARCHAR(80)
        , KIM_TYP_ID VARCHAR(40)
        , NMSPC_CD VARCHAR(40)
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_GRP_PRNCPL_MTP1 PRIMARY KEY(GRP_MBR_ID,FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_NM_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_NM_MT
/

CREATE TABLE KRIM_PND_NM_MT
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_NM_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM_TYP_CD VARCHAR(40)
        , FIRST_NM VARCHAR(40)
        , MIDDLE_NM VARCHAR(40)
        , LAST_NM VARCHAR(80)
        , SUFFIX_NM VARCHAR(20)
        , TITLE_NM VARCHAR(20)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_NM_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_NM_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_PHONE_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_PHONE_MT
/

CREATE TABLE KRIM_PND_PHONE_MT
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_PHONE_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ENT_TYP_CD VARCHAR(40)
        , PHONE_TYP_CD VARCHAR(40)
        , PHONE_NBR VARCHAR(20)
        , PHONE_EXTN_NBR VARCHAR(8)
        , POSTAL_CNTRY_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_PHONE_MTP1 PRIMARY KEY(FDOC_NBR,ENTITY_PHONE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_PRIV_PREF_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_PRIV_PREF_MT
/

CREATE TABLE KRIM_PND_PRIV_PREF_MT
(
      FDOC_NBR VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SUPPRESS_NM_IND VARCHAR(1) default 'N'
        , SUPPRESS_EMAIL_IND VARCHAR(1) default 'Y'
        , SUPPRESS_ADDR_IND VARCHAR(1) default 'Y'
        , SUPPRESS_PHONE_IND VARCHAR(1) default 'Y'
        , SUPPRESS_PRSNL_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_PRIV_PREF_MTP1 PRIMARY KEY(FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_MBR_ATTR_DATA_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_MBR_ATTR_DATA_MT
/

CREATE TABLE KRIM_PND_ROLE_MBR_ATTR_DATA_MT
(
      FDOC_NBR VARCHAR(14)
        , ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_MBR_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40)
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_ROLE_MBR_ATTR_DATAP1 PRIMARY KEY(FDOC_NBR,ATTR_DATA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_MBR_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_MBR_MT
/

CREATE TABLE KRIM_PND_ROLE_MBR_MT
(
      FDOC_NBR VARCHAR(14)
        , ROLE_MBR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40) NOT NULL
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_ROLE_MBR_MTP1 PRIMARY KEY(FDOC_NBR,ROLE_MBR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_MT
/

CREATE TABLE KRIM_PND_ROLE_MT
(
      FDOC_NBR VARCHAR(14)
        , ROLE_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_NM VARCHAR(100) NOT NULL
        , KIM_TYP_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , NMSPC_CD VARCHAR(40)
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_ROLE_MTP1 PRIMARY KEY(FDOC_NBR,ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_PERM_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_PERM_T
/

CREATE TABLE KRIM_PND_ROLE_PERM_T
(
      FDOC_NBR VARCHAR(14)
        , ROLE_PERM_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40) NOT NULL
        , PERM_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_PND_ROLE_PERM_TP1 PRIMARY KEY(FDOC_NBR,ROLE_PERM_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_RSP_ACTN_MT
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_RSP_ACTN_MT
/

CREATE TABLE KRIM_PND_ROLE_RSP_ACTN_MT
(
      FRC_ACTN VARCHAR(1)
        , ROLE_RSP_ACTN_ID VARCHAR(40)
        , FDOC_NBR VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTN_TYP_CD VARCHAR(40)
        , PRIORITY_NBR DECIMAL(3)
        , ACTN_PLCY_CD VARCHAR(40)
        , ROLE_MBR_ID VARCHAR(40)
        , ROLE_RSP_ID VARCHAR(40)
        , EDIT_FLAG VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_PND_ROLE_RSP_ACTN_MTP1 PRIMARY KEY(ROLE_RSP_ACTN_ID,FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PND_ROLE_RSP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PND_ROLE_RSP_T
/

CREATE TABLE KRIM_PND_ROLE_RSP_T
(
      FDOC_NBR VARCHAR(14)
        , ROLE_RSP_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40) NOT NULL
        , RSP_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_PND_ROLE_RSP_TP1 PRIMARY KEY(FDOC_NBR,ROLE_RSP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PRNCPL_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PRNCPL_T
/

CREATE TABLE KRIM_PRNCPL_T
(
      OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_NM VARCHAR(100) NOT NULL
        , ENTITY_ID VARCHAR(40)
        , PRNCPL_PSWD VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , PRNCPL_ID VARCHAR(40)
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_PRNCPL_TP1 PRIMARY KEY(PRNCPL_ID)


    , INDEX KRIM_PRNCPL_TC0 (OBJ_ID)
    , INDEX KRIM_PRNCPL_TC1 (PRNCPL_NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_DOCUMENT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_DOCUMENT_T
/

CREATE TABLE KRIM_ROLE_DOCUMENT_T
(
      FDOC_NBR VARCHAR(14)
        , ROLE_ID VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_TYP_ID VARCHAR(40) NOT NULL
        , DESC_TXT VARCHAR(4000)
        , ROLE_NMSPC VARCHAR(100) NOT NULL
        , ROLE_NM VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRIM_ROLE_DOCUMENT_TP1 PRIMARY KEY(FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_MBR_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_MBR_ATTR_DATA_T
/

CREATE TABLE KRIM_ROLE_MBR_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_MBR_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TP1 PRIMARY KEY(ATTR_DATA_ID)


    , INDEX KRIM_ROLE_MBR_ATTR_DATA_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_MBR_ATTR_DATA_TI1 (ROLE_MBR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_MBR_T
/

CREATE TABLE KRIM_ROLE_MBR_T
(
      ROLE_MBR_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , ROLE_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ROLE_MBR_TP1 PRIMARY KEY(ROLE_MBR_ID)


    , INDEX KRIM_ROLE_MBR_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_MBR_TI1 (MBR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_PERM_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_PERM_T
/

CREATE TABLE KRIM_ROLE_PERM_T
(
      ROLE_PERM_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40)
        , PERM_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_ROLE_PERM_TP1 PRIMARY KEY(ROLE_PERM_ID)


    , INDEX KRIM_ROLE_PERM_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_PERM_TI1 (PERM_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_RSP_ACTN_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_RSP_ACTN_T
/

CREATE TABLE KRIM_ROLE_RSP_ACTN_T
(
      ROLE_RSP_ACTN_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTN_TYP_CD VARCHAR(40)
        , PRIORITY_NBR DECIMAL(3)
        , ACTN_PLCY_CD VARCHAR(40)
        , ROLE_MBR_ID VARCHAR(40)
        , ROLE_RSP_ID VARCHAR(40)
        , FRC_ACTN VARCHAR(1) default 'N'
    
    , CONSTRAINT KRIM_ROLE_RSP_ACTN_TP1 PRIMARY KEY(ROLE_RSP_ACTN_ID)


    , INDEX KRIM_ROLE_RSP_ACTN_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_RSP_ACTN_TC1 (ROLE_RSP_ID, ROLE_MBR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_RSP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_RSP_T
/

CREATE TABLE KRIM_ROLE_RSP_T
(
      ROLE_RSP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40)
        , RSP_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_ROLE_RSP_TP1 PRIMARY KEY(ROLE_RSP_ID)


    , INDEX KRIM_ROLE_RSP_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_RSP_TI1 (RSP_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ROLE_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_T
/

CREATE TABLE KRIM_ROLE_T
(
      ROLE_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_NM VARCHAR(80) NOT NULL
        , NMSPC_CD VARCHAR(40) NOT NULL
        , DESC_TXT VARCHAR(4000)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME NOT NULL
    
    , CONSTRAINT KRIM_ROLE_TP1 PRIMARY KEY(ROLE_ID)


    , INDEX KRIM_ROLE_TC0 (OBJ_ID)
    , INDEX KRIM_ROLE_TC1 (ROLE_NM, NMSPC_CD)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_RSP_ATTR_DATA_T
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_ATTR_DATA_T
/

CREATE TABLE KRIM_RSP_ATTR_DATA_T
(
      ATTR_DATA_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , RSP_ID VARCHAR(40)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ATTR_VAL VARCHAR(400)
    
    , CONSTRAINT KRIM_RSP_ATTR_DATA_TP1 PRIMARY KEY(ATTR_DATA_ID)


    , INDEX KRIM_RSP_ATTR_DATA_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_RSP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_T
/

CREATE TABLE KRIM_RSP_T
(
      RSP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , RSP_TMPL_ID VARCHAR(40)
        , NMSPC_CD VARCHAR(40)
        , NM VARCHAR(100)
        , DESC_TXT VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_RSP_TP1 PRIMARY KEY(RSP_ID)


    , INDEX KRIM_RSP_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_RSP_TMPL_T
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_TMPL_T
/

CREATE TABLE KRIM_RSP_TMPL_T
(
      RSP_TMPL_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NMSPC_CD VARCHAR(40)
        , NM VARCHAR(80)
        , KIM_TYP_ID VARCHAR(100) NOT NULL
        , DESC_TXT VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_RSP_TMPL_TP1 PRIMARY KEY(RSP_TMPL_ID)


    , INDEX KRIM_RSP_TMPL_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_TYP_ATTR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_TYP_ATTR_T
/

CREATE TABLE KRIM_TYP_ATTR_T
(
      KIM_TYP_ATTR_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SORT_CD VARCHAR(2)
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , KIM_ATTR_DEFN_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
    
    , CONSTRAINT KRIM_TYP_ATTR_TP1 PRIMARY KEY(KIM_TYP_ATTR_ID)


    , INDEX KRIM_TYP_ATTRIBUTE_TI1 (KIM_TYP_ID)
    , INDEX KRIM_TYP_ATTR_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRIM_TYP_T
/

CREATE TABLE KRIM_TYP_T
(
      KIM_TYP_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(100)
        , SRVC_NM VARCHAR(200)
        , ACTV_IND VARCHAR(1) default 'Y'
        , NMSPC_CD VARCHAR(40)
    
    , CONSTRAINT KRIM_TYP_TP1 PRIMARY KEY(KIM_TYP_ID)


    , INDEX KRIM_TYP_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_ADHOC_RTE_ACTN_RECIP_T
# -----------------------------------------------------------------------
drop table if exists KRNS_ADHOC_RTE_ACTN_RECIP_T
/

CREATE TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T
(
      RECIP_TYP_CD DECIMAL(1)
        , ACTN_RQST_CD VARCHAR(30)
        , ACTN_RQST_RECIP_ID VARCHAR(70)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TP1 PRIMARY KEY(RECIP_TYP_CD,ACTN_RQST_CD,ACTN_RQST_RECIP_ID,DOC_HDR_ID)


    , INDEX KRNS_ADHOC_RTE_ACTN_RECIP_T2 (DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_ATT_T
# -----------------------------------------------------------------------
drop table if exists KRNS_ATT_T
/

CREATE TABLE KRNS_ATT_T
(
      FILE_SZ DECIMAL(14)
        , ATT_TYP_CD VARCHAR(40)
        , NTE_ID DECIMAL(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , MIME_TYP VARCHAR(40)
        , FILE_NM VARCHAR(250)
        , ATT_ID VARCHAR(36)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_ATT_TP1 PRIMARY KEY(NTE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_CAMPUS_T
# -----------------------------------------------------------------------
drop table if exists KRNS_CAMPUS_T
/

CREATE TABLE KRNS_CAMPUS_T
(
      CAMPUS_CD VARCHAR(2)
        , CAMPUS_NM VARCHAR(250)
        , CAMPUS_SHRT_NM VARCHAR(250)
        , CAMPUS_TYP_CD VARCHAR(1)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_CAMPUS_TP1 PRIMARY KEY(CAMPUS_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_CMP_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRNS_CMP_TYP_T
/

CREATE TABLE KRNS_CMP_TYP_T
(
      CAMPUS_TYP_CD VARCHAR(1)
        , CMP_TYP_NM VARCHAR(250)
        , DOBJ_MAINT_CD_ACTV_IND VARCHAR(1) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_CMP_TYP_TP1 PRIMARY KEY(CAMPUS_TYP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_DOC_HDR_T
# -----------------------------------------------------------------------
drop table if exists KRNS_DOC_HDR_T
/

CREATE TABLE KRNS_DOC_HDR_T
(
      DOC_HDR_ID VARCHAR(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FDOC_DESC VARCHAR(40)
        , ORG_DOC_HDR_ID VARCHAR(10)
        , TMPL_DOC_HDR_ID VARCHAR(14)
        , EXPLANATION VARCHAR(400)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_DOC_HDR_TP1 PRIMARY KEY(DOC_HDR_ID)


    , INDEX KRNS_DOC_HDR_TI3 (ORG_DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_DOC_TYP_ATTR_T
# -----------------------------------------------------------------------
drop table if exists KRNS_DOC_TYP_ATTR_T
/

CREATE TABLE KRNS_DOC_TYP_ATTR_T
(
      DOC_TYP_ATTR_ID DECIMAL(8)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , CD VARCHAR(100) NOT NULL
        , VAL VARCHAR(400)
        , LBL VARCHAR(400)
        , DOC_TYP_CD VARCHAR(4) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT KRNS_DOC_TYP_ATTR_TP1 PRIMARY KEY(DOC_TYP_ATTR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_LOOKUP_RSLT_T
# -----------------------------------------------------------------------
drop table if exists KRNS_LOOKUP_RSLT_T
/

CREATE TABLE KRNS_LOOKUP_RSLT_T
(
      LOOKUP_RSLT_ID VARCHAR(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , LOOKUP_DT DATETIME NOT NULL
        , SERIALZD_RSLTS LONGTEXT
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_LOOKUP_RSLT_TP1 PRIMARY KEY(LOOKUP_RSLT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_LOOKUP_SEL_T
# -----------------------------------------------------------------------
drop table if exists KRNS_LOOKUP_SEL_T
/

CREATE TABLE KRNS_LOOKUP_SEL_T
(
      LOOKUP_RSLT_ID VARCHAR(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , LOOKUP_DT DATETIME NOT NULL
        , SEL_OBJ_IDS LONGTEXT
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_LOOKUP_SEL_TP1 PRIMARY KEY(LOOKUP_RSLT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_MAINT_DOC_ATT_T
# -----------------------------------------------------------------------
drop table if exists KRNS_MAINT_DOC_ATT_T
/

CREATE TABLE KRNS_MAINT_DOC_ATT_T
(
      FILE_NM VARCHAR(150)
        , CNTNT_TYP VARCHAR(50)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR(14)
        , ATT_CNTNT LONGBLOB NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_MAINT_DOC_ATT_TP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_MAINT_DOC_T
# -----------------------------------------------------------------------
drop table if exists KRNS_MAINT_DOC_T
/

CREATE TABLE KRNS_MAINT_DOC_T
(
      DOC_HDR_ID VARCHAR(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_CNTNT LONGTEXT
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_MAINT_DOC_TP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_MAINT_LOCK_T
# -----------------------------------------------------------------------
drop table if exists KRNS_MAINT_LOCK_T
/

CREATE TABLE KRNS_MAINT_LOCK_T
(
      MAINT_LOCK_REP_TXT VARCHAR(500)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR(14) NOT NULL
        , MAINT_LOCK_ID VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_MAINT_LOCK_TP1 PRIMARY KEY(MAINT_LOCK_ID)


    , INDEX KRNS_MAINT_LOCK_TI2 (DOC_HDR_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_NMSPC_T
# -----------------------------------------------------------------------
drop table if exists KRNS_NMSPC_T
/

CREATE TABLE KRNS_NMSPC_T
(
      NMSPC_CD VARCHAR(20)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , APPL_NMSPC_CD VARCHAR(20)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_NMSPC_TP1 PRIMARY KEY(NMSPC_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_NTE_T
# -----------------------------------------------------------------------
drop table if exists KRNS_NTE_T
/

CREATE TABLE KRNS_NTE_T
(
      NTE_ID DECIMAL(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , RMT_OBJ_ID VARCHAR(36) NOT NULL
        , AUTH_PRNCPL_ID VARCHAR(40) NOT NULL
        , POST_TS DATETIME NOT NULL
        , NTE_TYP_CD VARCHAR(4) NOT NULL
        , TXT VARCHAR(800)
        , PRG_CD VARCHAR(1)
        , TPC_TXT VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_NTE_TP1 PRIMARY KEY(NTE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_NTE_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRNS_NTE_TYP_T
/

CREATE TABLE KRNS_NTE_TYP_T
(
      NTE_TYP_CD VARCHAR(4)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , TYP_DESC_TXT VARCHAR(100)
        , ACTV_IND VARCHAR(1)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_NTE_TYP_TP1 PRIMARY KEY(NTE_TYP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_PARM_DTL_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRNS_PARM_DTL_TYP_T
/

CREATE TABLE KRNS_PARM_DTL_TYP_T
(
      NMSPC_CD VARCHAR(20)
        , PARM_DTL_TYP_CD VARCHAR(100)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(255)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_PARM_DTL_TYP_TP1 PRIMARY KEY(NMSPC_CD,PARM_DTL_TYP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_PARM_T
# -----------------------------------------------------------------------
drop table if exists KRNS_PARM_T
/

CREATE TABLE KRNS_PARM_T
(
      APPL_NMSPC_CD VARCHAR(20) default 'KUALI'
        , NMSPC_CD VARCHAR(20)
        , PARM_DTL_TYP_CD VARCHAR(100)
        , PARM_NM VARCHAR(255)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PARM_TYP_CD VARCHAR(5) NOT NULL
        , TXT VARCHAR(4000)
        , PARM_DESC_TXT VARCHAR(4000)
        , CONS_CD VARCHAR(1)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_PARM_TP1 PRIMARY KEY(APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_PARM_TYP_T
# -----------------------------------------------------------------------
drop table if exists KRNS_PARM_TYP_T
/

CREATE TABLE KRNS_PARM_TYP_T
(
      PARM_TYP_CD VARCHAR(5)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_PARM_TYP_TP1 PRIMARY KEY(PARM_TYP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_PESSIMISTIC_LOCK_T
# -----------------------------------------------------------------------
drop table if exists KRNS_PESSIMISTIC_LOCK_T
/

CREATE TABLE KRNS_PESSIMISTIC_LOCK_T
(
      PESSIMISTIC_LOCK_ID DECIMAL(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , LOCK_DESC_TXT VARCHAR(4000)
        , DOC_HDR_ID VARCHAR(14) NOT NULL
        , GNRT_DT DATETIME NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KRNS_PESSIMISTIC_LOCK_TP1 PRIMARY KEY(PESSIMISTIC_LOCK_ID)


    , INDEX KRNS_PESSIMISTIC_LOCK_TI1 (DOC_HDR_ID)
    , INDEX KRNS_PESSIMISTIC_LOCK_TI2 (PRNCPL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_SESN_DOC_T
# -----------------------------------------------------------------------
drop table if exists KRNS_SESN_DOC_T
/

CREATE TABLE KRNS_SESN_DOC_T
(
      SESN_DOC_ID VARCHAR(40)
        , DOC_HDR_ID VARCHAR(14)
        , PRNCPL_ID VARCHAR(40)
        , IP_ADDR VARCHAR(60)
        , SERIALZD_DOC_FRM LONGBLOB
        , LAST_UPDT_DT DATETIME
        , CONTENT_ENCRYPTED_IND CHAR(1) default 'N'
    
    , CONSTRAINT KRNS_SESN_DOC_TP1 PRIMARY KEY(SESN_DOC_ID,DOC_HDR_ID,PRNCPL_ID,IP_ADDR)


    , INDEX KRNS_SESN_DOC_TI1 (LAST_UPDT_DT)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_BAM_PARM_T
# -----------------------------------------------------------------------
drop table if exists KRSB_BAM_PARM_T
/

CREATE TABLE KRSB_BAM_PARM_T
(
      BAM_PARM_ID DECIMAL(14)
        , BAM_ID DECIMAL(14) NOT NULL
        , PARM LONGTEXT NOT NULL
    
    , CONSTRAINT KRSB_BAM_PARM_TP1 PRIMARY KEY(BAM_PARM_ID)


    , INDEX KREW_BAM_PARM_TI1 (BAM_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_BAM_T
# -----------------------------------------------------------------------
drop table if exists KRSB_BAM_T
/

CREATE TABLE KRSB_BAM_T
(
      BAM_ID DECIMAL(14)
        , SVC_NM VARCHAR(255) NOT NULL
        , SVC_URL VARCHAR(500) NOT NULL
        , MTHD_NM VARCHAR(2000) NOT NULL
        , THRD_NM VARCHAR(500) NOT NULL
        , CALL_DT DATETIME NOT NULL
        , TGT_TO_STR VARCHAR(2000) NOT NULL
        , SRVR_IND DECIMAL(1) NOT NULL
        , EXCPN_TO_STR VARCHAR(2000)
        , EXCPN_MSG LONGTEXT
    
    , CONSTRAINT KRSB_BAM_TP1 PRIMARY KEY(BAM_ID)


    , INDEX KRSB_BAM_TI1 (SVC_NM, MTHD_NM)
    , INDEX KRSB_BAM_TI2 (SVC_NM)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_FLT_SVC_DEF_T
# -----------------------------------------------------------------------
drop table if exists KRSB_FLT_SVC_DEF_T
/

CREATE TABLE KRSB_FLT_SVC_DEF_T
(
      FLT_SVC_DEF_ID DECIMAL(14)
        , FLT_SVC_DEF LONGTEXT NOT NULL
    
    , CONSTRAINT KRSB_FLT_SVC_DEF_TP1 PRIMARY KEY(FLT_SVC_DEF_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_MSG_PYLD_T
# -----------------------------------------------------------------------
drop table if exists KRSB_MSG_PYLD_T
/

CREATE TABLE KRSB_MSG_PYLD_T
(
      MSG_QUE_ID DECIMAL(14)
        , MSG_PYLD LONGTEXT NOT NULL
    
    , CONSTRAINT KRSB_MSG_PYLD_TP1 PRIMARY KEY(MSG_QUE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_MSG_QUE_T
# -----------------------------------------------------------------------
drop table if exists KRSB_MSG_QUE_T
/

CREATE TABLE KRSB_MSG_QUE_T
(
      MSG_QUE_ID DECIMAL(14)
        , DT DATETIME NOT NULL
        , EXP_DT DATETIME
        , PRIO DECIMAL(8) NOT NULL
        , STAT_CD CHAR(1) NOT NULL
        , RTRY_CNT DECIMAL(8) NOT NULL
        , IP_NBR VARCHAR(2000) NOT NULL
        , SVC_NM VARCHAR(255)
        , SVC_NMSPC VARCHAR(255) NOT NULL
        , SVC_MTHD_NM VARCHAR(2000)
        , APP_VAL_ONE VARCHAR(2000)
        , APP_VAL_TWO VARCHAR(2000)
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KRSB_MSG_QUE_TP1 PRIMARY KEY(MSG_QUE_ID)


    , INDEX KRSB_MSG_QUE_TI1 (SVC_NM, SVC_MTHD_NM)
    , INDEX KRSB_MSG_QUE_TI2 (SVC_NMSPC, STAT_CD, IP_NBR, DT)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_BLOB_TRIGGERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_BLOB_TRIGGERS
/

CREATE TABLE KRSB_QRTZ_BLOB_TRIGGERS
(
      TRIGGER_NAME VARCHAR(80)
        , TRIGGER_GROUP VARCHAR(80)
        , BLOB_DATA LONGBLOB
    
    , CONSTRAINT KRSB_QRTZ_BLOB_TRIGGERSP1 PRIMARY KEY(TRIGGER_NAME,TRIGGER_GROUP)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_CALENDARS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_CALENDARS
/

CREATE TABLE KRSB_QRTZ_CALENDARS
(
      CALENDAR_NAME VARCHAR(80)
        , CALENDAR LONGBLOB NOT NULL
    
    , CONSTRAINT KRSB_QRTZ_CALENDARSP1 PRIMARY KEY(CALENDAR_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_CRON_TRIGGERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_CRON_TRIGGERS
/

CREATE TABLE KRSB_QRTZ_CRON_TRIGGERS
(
      TRIGGER_NAME VARCHAR(80)
        , TRIGGER_GROUP VARCHAR(80)
        , CRON_EXPRESSION VARCHAR(80) NOT NULL
        , TIME_ZONE_ID VARCHAR(80)
    
    , CONSTRAINT KRSB_QRTZ_CRON_TRIGGERSP1 PRIMARY KEY(TRIGGER_NAME,TRIGGER_GROUP)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_FIRED_TRIGGERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_FIRED_TRIGGERS
/

CREATE TABLE KRSB_QRTZ_FIRED_TRIGGERS
(
      ENTRY_ID VARCHAR(95)
        , TRIGGER_NAME VARCHAR(80) NOT NULL
        , TRIGGER_GROUP VARCHAR(80) NOT NULL
        , IS_VOLATILE VARCHAR(1) NOT NULL
        , INSTANCE_NAME VARCHAR(80) NOT NULL
        , FIRED_TIME DECIMAL(13) NOT NULL
        , PRIORITY DECIMAL(13) NOT NULL
        , STATE VARCHAR(16) NOT NULL
        , JOB_NAME VARCHAR(80)
        , JOB_GROUP VARCHAR(80)
        , IS_STATEFUL VARCHAR(1)
        , REQUESTS_RECOVERY VARCHAR(1)
    
    , CONSTRAINT KRSB_QRTZ_FIRED_TRIGGERSP1 PRIMARY KEY(ENTRY_ID)


    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI1 (JOB_GROUP)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI2 (JOB_NAME)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI3 (REQUESTS_RECOVERY)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI4 (IS_STATEFUL)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI5 (TRIGGER_GROUP)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI6 (INSTANCE_NAME)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI7 (TRIGGER_NAME)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI8 (TRIGGER_NAME, TRIGGER_GROUP)
    , INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI9 (IS_VOLATILE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_JOB_DETAILS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_JOB_DETAILS
/

CREATE TABLE KRSB_QRTZ_JOB_DETAILS
(
      REQUESTS_RECOVERY VARCHAR(1) NOT NULL
        , JOB_DATA LONGBLOB
        , JOB_NAME VARCHAR(80)
        , JOB_GROUP VARCHAR(80)
        , DESCRIPTION VARCHAR(120)
        , JOB_CLASS_NAME VARCHAR(128) NOT NULL
        , IS_DURABLE VARCHAR(1) NOT NULL
        , IS_VOLATILE VARCHAR(1) NOT NULL
        , IS_STATEFUL VARCHAR(1) NOT NULL
    
    , CONSTRAINT KRSB_QRTZ_JOB_DETAILSP1 PRIMARY KEY(JOB_NAME,JOB_GROUP)


    , INDEX KRSB_QRTZ_JOB_DETAILS_TI1 (REQUESTS_RECOVERY)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_JOB_LISTENERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_JOB_LISTENERS
/

CREATE TABLE KRSB_QRTZ_JOB_LISTENERS
(
      JOB_NAME VARCHAR(80)
        , JOB_GROUP VARCHAR(80)
        , JOB_LISTENER VARCHAR(80)
    
    , CONSTRAINT KRSB_QRTZ_JOB_LISTENERSP1 PRIMARY KEY(JOB_NAME,JOB_GROUP,JOB_LISTENER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_LOCKS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_LOCKS
/

CREATE TABLE KRSB_QRTZ_LOCKS
(
      LOCK_NAME VARCHAR(40)
    
    , CONSTRAINT KRSB_QRTZ_LOCKSP1 PRIMARY KEY(LOCK_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_PAUSED_TRIGGER_GRPS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_PAUSED_TRIGGER_GRPS
/

CREATE TABLE KRSB_QRTZ_PAUSED_TRIGGER_GRPS
(
      TRIGGER_GROUP VARCHAR(80)
    
    , CONSTRAINT KRSB_QRTZ_PAUSED_TRIGGER_GRP1 PRIMARY KEY(TRIGGER_GROUP)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_SCHEDULER_STATE
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_SCHEDULER_STATE
/

CREATE TABLE KRSB_QRTZ_SCHEDULER_STATE
(
      INSTANCE_NAME VARCHAR(80)
        , LAST_CHECKIN_TIME DECIMAL(13) NOT NULL
        , CHECKIN_INTERVAL DECIMAL(13) NOT NULL
    
    , CONSTRAINT KRSB_QRTZ_SCHEDULER_STATEP1 PRIMARY KEY(INSTANCE_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_SIMPLE_TRIGGERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_SIMPLE_TRIGGERS
/

CREATE TABLE KRSB_QRTZ_SIMPLE_TRIGGERS
(
      TRIGGER_NAME VARCHAR(80)
        , TRIGGER_GROUP VARCHAR(80)
        , REPEAT_COUNT DECIMAL(7) NOT NULL
        , REPEAT_INTERVAL DECIMAL(12) NOT NULL
        , TIMES_TRIGGERED DECIMAL(7) NOT NULL
    
    , CONSTRAINT KRSB_QRTZ_SIMPLE_TRIGGERSP1 PRIMARY KEY(TRIGGER_NAME,TRIGGER_GROUP)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_TRIGGERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_TRIGGERS
/

CREATE TABLE KRSB_QRTZ_TRIGGERS
(
      TRIGGER_NAME VARCHAR(80)
        , TRIGGER_GROUP VARCHAR(80)
        , JOB_NAME VARCHAR(80) NOT NULL
        , JOB_GROUP VARCHAR(80) NOT NULL
        , IS_VOLATILE VARCHAR(1) NOT NULL
        , DESCRIPTION VARCHAR(120)
        , NEXT_FIRE_TIME DECIMAL(13)
        , PREV_FIRE_TIME DECIMAL(13)
        , PRIORITY DECIMAL(13)
        , TRIGGER_STATE VARCHAR(16) NOT NULL
        , TRIGGER_TYPE VARCHAR(8) NOT NULL
        , START_TIME DECIMAL(13) NOT NULL
        , END_TIME DECIMAL(13)
        , CALENDAR_NAME VARCHAR(80)
        , MISFIRE_INSTR DECIMAL(2)
        , JOB_DATA LONGBLOB
    
    , CONSTRAINT KRSB_QRTZ_TRIGGERSP1 PRIMARY KEY(TRIGGER_NAME,TRIGGER_GROUP)


    , INDEX KRSB_QRTZ_TRIGGERS_TI1 (NEXT_FIRE_TIME)
    , INDEX KRSB_QRTZ_TRIGGERS_TI2 (NEXT_FIRE_TIME, TRIGGER_STATE)
    , INDEX KRSB_QRTZ_TRIGGERS_TI3 (TRIGGER_STATE)
    , INDEX KRSB_QRTZ_TRIGGERS_TI4 (IS_VOLATILE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_QRTZ_TRIGGER_LISTENERS
# -----------------------------------------------------------------------
drop table if exists KRSB_QRTZ_TRIGGER_LISTENERS
/

CREATE TABLE KRSB_QRTZ_TRIGGER_LISTENERS
(
      TRIGGER_NAME VARCHAR(80)
        , TRIGGER_GROUP VARCHAR(80)
        , TRIGGER_LISTENER VARCHAR(80)
    
    , CONSTRAINT KRSB_QRTZ_TRIGGER_LISTENERSP1 PRIMARY KEY(TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_LISTENER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRSB_SVC_DEF_T
# -----------------------------------------------------------------------
drop table if exists KRSB_SVC_DEF_T
/

CREATE TABLE KRSB_SVC_DEF_T
(
      FLT_SVC_DEF_ID DECIMAL(14) NOT NULL
        , SVC_DEF_CHKSM VARCHAR(30) NOT NULL
        , SVC_DEF_ID DECIMAL(14)
        , SVC_NM VARCHAR(255) NOT NULL
        , SVC_URL VARCHAR(500) NOT NULL
        , SRVR_IP VARCHAR(40) NOT NULL
        , SVC_NMSPC VARCHAR(255) NOT NULL
        , SVC_ALIVE DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
    
    , CONSTRAINT KRSB_SVC_DEF_TP1 PRIMARY KEY(SVC_DEF_ID)


    , INDEX KRSB_SVC_DEF_TI1 (SRVR_IP, SVC_NMSPC)
    , INDEX KRSB_SVC_DEF_TI2 (FLT_SVC_DEF_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KR_COUNTRY_T
# -----------------------------------------------------------------------
drop table if exists KR_COUNTRY_T
/

CREATE TABLE KR_COUNTRY_T
(
      POSTAL_CNTRY_CD VARCHAR(2)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , POSTAL_CNTRY_NM VARCHAR(40)
        , PSTL_CNTRY_RSTRC_IND VARCHAR(1) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KR_COUNTRY_TP1 PRIMARY KEY(POSTAL_CNTRY_CD)


    , INDEX KR_COUNTRY_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KR_COUNTY_T
# -----------------------------------------------------------------------
drop table if exists KR_COUNTY_T
/

CREATE TABLE KR_COUNTY_T
(
      COUNTY_CD VARCHAR(10)
        , STATE_CD VARCHAR(2)
        , POSTAL_CNTRY_CD VARCHAR(2) default 'US'
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , COUNTY_NM VARCHAR(100)
        , ACTV_IND VARCHAR(1)
    
    , CONSTRAINT KR_COUNTY_TP1 PRIMARY KEY(COUNTY_CD,STATE_CD,POSTAL_CNTRY_CD)


    , INDEX KR_COUNTY_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KR_KIM_TEST_BO
# -----------------------------------------------------------------------
drop table if exists KR_KIM_TEST_BO
/

CREATE TABLE KR_KIM_TEST_BO
(
      PK VARCHAR(40)
        , PRNCPL_ID VARCHAR(40)
    



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KR_POSTAL_CODE_T
# -----------------------------------------------------------------------
drop table if exists KR_POSTAL_CODE_T
/

CREATE TABLE KR_POSTAL_CODE_T
(
      POSTAL_CD VARCHAR(20)
        , POSTAL_CNTRY_CD VARCHAR(2) default 'US'
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , POSTAL_STATE_CD VARCHAR(2)
        , COUNTY_CD VARCHAR(10)
        , POSTAL_CITY_NM VARCHAR(30)
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KR_POSTAL_CODE_TP1 PRIMARY KEY(POSTAL_CD,POSTAL_CNTRY_CD)


    , INDEX KR_POSTAL_CODE_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KR_STATE_T
# -----------------------------------------------------------------------
drop table if exists KR_STATE_T
/

CREATE TABLE KR_STATE_T
(
      POSTAL_STATE_CD VARCHAR(2)
        , POSTAL_CNTRY_CD VARCHAR(2) default 'US'
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , POSTAL_STATE_NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KR_STATE_TP1 PRIMARY KEY(POSTAL_STATE_CD,POSTAL_CNTRY_CD)


    , INDEX KR_STATE_TC0 (OBJ_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# LOCATION_TYPE
# -----------------------------------------------------------------------
drop table if exists LOCATION_TYPE
/

CREATE TABLE LOCATION_TYPE
(
      LOCATION_TYPE_CODE DECIMAL(3)
        , LOCATION_TYPE_DESC VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
    
    , CONSTRAINT LOCATION_TYPEP1 PRIMARY KEY(LOCATION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# MAIL_BY
# -----------------------------------------------------------------------
drop table if exists MAIL_BY
/

CREATE TABLE MAIL_BY
(
      MAIL_BY_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT MAIL_BYP1 PRIMARY KEY(MAIL_BY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# MAIL_TYPE
# -----------------------------------------------------------------------
drop table if exists MAIL_TYPE
/

CREATE TABLE MAIL_TYPE
(
      MAIL_TYPE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT MAIL_TYPEP1 PRIMARY KEY(MAIL_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# MEMBERSHIP_ROLE
# -----------------------------------------------------------------------
drop table if exists MEMBERSHIP_ROLE
/

CREATE TABLE MEMBERSHIP_ROLE
(
      MEMBERSHIP_ROLE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT MEMBERSHIP_ROLEP1 PRIMARY KEY(MEMBERSHIP_ROLE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# MINUTE_ENTRY_TYPE
# -----------------------------------------------------------------------
drop table if exists MINUTE_ENTRY_TYPE
/

CREATE TABLE MINUTE_ENTRY_TYPE
(
      MINUTE_ENTRY_TYPE_CODE VARCHAR(3)
        , SORT_ID DECIMAL(3) NOT NULL
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT MINUTE_ENTRY_TYPEP1 PRIMARY KEY(MINUTE_ENTRY_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# MSG_OF_THE_DAY
# -----------------------------------------------------------------------
drop table if exists MSG_OF_THE_DAY
/

CREATE TABLE MSG_OF_THE_DAY
(
      MSG_OF_THE_DAY_ID DECIMAL(12)
        , MSG VARCHAR(4000)
        , VER_NBR DECIMAL(8) NOT NULL
        , DISPLAY_ORDER DECIMAL(8) NOT NULL
        , ACTIVE_FLAG VARCHAR(1) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
    
    , CONSTRAINT MSG_OF_THE_DAYP1 PRIMARY KEY(MSG_OF_THE_DAY_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NARRATIVE
# -----------------------------------------------------------------------
drop table if exists NARRATIVE
/

CREATE TABLE NARRATIVE
(
      HIERARCHY_PROPOSAL_NUMBER VARCHAR(12)
        , HIDE_IN_HIERARCHY CHAR(1) default 'N' NOT NULL
        , PROPOSAL_NUMBER VARCHAR(12)
        , MODULE_NUMBER DECIMAL(4)
        , MODULE_SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , MODULE_TITLE VARCHAR(150)
        , MODULE_STATUS_CODE VARCHAR(3) NOT NULL
        , CONTACT_NAME VARCHAR(30)
        , PHONE_NUMBER VARCHAR(20)
        , EMAIL_ADDRESS VARCHAR(60)
        , COMMENTS VARCHAR(300)
        , FILE_NAME VARCHAR(150)
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , NARRATIVE_TYPE_CODE VARCHAR(3) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NARRATIVEP1 PRIMARY KEY(PROPOSAL_NUMBER,MODULE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NARRATIVE_ATTACHMENT
# -----------------------------------------------------------------------
drop table if exists NARRATIVE_ATTACHMENT
/

CREATE TABLE NARRATIVE_ATTACHMENT
(
      PROPOSAL_NUMBER VARCHAR(12)
        , MODULE_NUMBER DECIMAL(4)
        , NARRATIVE_DATA LONGBLOB
        , FILE_NAME VARCHAR(150)
        , CONTENT_TYPE VARCHAR(250)
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NARRATIVE_ATTACHMENTP1 PRIMARY KEY(PROPOSAL_NUMBER,MODULE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NARRATIVE_STATUS
# -----------------------------------------------------------------------
drop table if exists NARRATIVE_STATUS
/

CREATE TABLE NARRATIVE_STATUS
(
      NARRATIVE_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(20) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NARRATIVE_STATUSP1 PRIMARY KEY(NARRATIVE_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NARRATIVE_TYPE
# -----------------------------------------------------------------------
drop table if exists NARRATIVE_TYPE
/

CREATE TABLE NARRATIVE_TYPE
(
      NARRATIVE_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , SYSTEM_GENERATED VARCHAR(1) NOT NULL
        , ALLOW_MULTIPLE VARCHAR(1) NOT NULL
        , NARRATIVE_TYPE_GROUP VARCHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NARRATIVE_TYPEP1 PRIMARY KEY(NARRATIVE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NARRATIVE_USER_RIGHTS
# -----------------------------------------------------------------------
drop table if exists NARRATIVE_USER_RIGHTS
/

CREATE TABLE NARRATIVE_USER_RIGHTS
(
      PROPOSAL_NUMBER VARCHAR(12)
        , MODULE_NUMBER DECIMAL(4)
        , USER_ID VARCHAR(40)
        , ACCESS_TYPE CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NARRATIVE_USER_RIGHTSP1 PRIMARY KEY(PROPOSAL_NUMBER,MODULE_NUMBER,USER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NOTICE_OF_OPPORTUNITY
# -----------------------------------------------------------------------
drop table if exists NOTICE_OF_OPPORTUNITY
/

CREATE TABLE NOTICE_OF_OPPORTUNITY
(
      NOTICE_OF_OPPORTUNITY_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NOTICE_OF_OPPORTUNITYP1 PRIMARY KEY(NOTICE_OF_OPPORTUNITY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# NSF_CODES
# -----------------------------------------------------------------------
drop table if exists NSF_CODES
/

CREATE TABLE NSF_CODES
(
      NSF_SEQUENCE_NUMBER DECIMAL(12)
        , NSF_CODE VARCHAR(15) NOT NULL
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT NSF_CODESP1 PRIMARY KEY(NSF_SEQUENCE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION
/

CREATE TABLE ORGANIZATION
(
      ORGANIZATION_ID VARCHAR(8)
        , ORGANIZATION_NAME VARCHAR(60) NOT NULL
        , CONTACT_ADDRESS_ID DECIMAL(6) NOT NULL
        , ADDRESS VARCHAR(60)
        , CABLE_ADDRESS VARCHAR(20)
        , TELEX_NUMBER VARCHAR(20)
        , COUNTY VARCHAR(30)
        , CONGRESSIONAL_DISTRICT VARCHAR(50)
        , INCORPORATED_IN VARCHAR(50)
        , INCORPORATED_DATE DATETIME
        , NUMBER_OF_EMPLOYEES DECIMAL(6)
        , IRS_TAX_EXCEMPTION VARCHAR(30)
        , FEDRAL_EMPLOYER_ID VARCHAR(15)
        , MASS_TAX_EXCEMPT_NUM VARCHAR(30)
        , AGENCY_SYMBOL VARCHAR(30)
        , VENDOR_CODE VARCHAR(30)
        , COM_GOV_ENTITY_CODE VARCHAR(30)
        , MASS_EMPLOYEE_CLAIM VARCHAR(30)
        , DUNS_NUMBER VARCHAR(20)
        , DUNS_PLUS_FOUR_NUMBER VARCHAR(20)
        , DODAC_NUMBER VARCHAR(20)
        , CAGE_NUMBER VARCHAR(20)
        , HUMAN_SUB_ASSURANCE VARCHAR(30)
        , ANIMAL_WELFARE_ASSURANCE VARCHAR(20)
        , SCIENCE_MISCONDUCT_COMPL_DATE DATETIME
        , PHS_ACOUNT VARCHAR(30)
        , NSF_INSTITUTIONAL_CODE VARCHAR(30)
        , INDIRECT_COST_RATE_AGREEMENT VARCHAR(50)
        , COGNIZANT_AUDITOR DECIMAL(6)
        , ONR_RESIDENT_REP DECIMAL(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATIONP1 PRIMARY KEY(ORGANIZATION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION_AUDIT
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION_AUDIT
/

CREATE TABLE ORGANIZATION_AUDIT
(
      ORGANIZATION_ID VARCHAR(8)
        , FISCAL_YEAR CHAR(4)
        , AUDIT_ACCEPTED CHAR(1)
        , AUDIT_COMMENT VARCHAR(300)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATION_AUDITP1 PRIMARY KEY(ORGANIZATION_ID,FISCAL_YEAR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION_IDC
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION_IDC
/

CREATE TABLE ORGANIZATION_IDC
(
      ORGANIZATION_ID VARCHAR(8)
        , IDC_NUMBER DECIMAL(3)
        , START_DATE DATETIME
        , END_DATE DATETIME
        , REQUESTED_DATE DATETIME
        , IDC_RATE_TYPE_CODE DECIMAL(3)
        , APPLICABLE_IDC_RATE DECIMAL(5,2)
        , IDC_COMMENT VARCHAR(300)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATION_IDCP1 PRIMARY KEY(ORGANIZATION_ID,IDC_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION_TYPE
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION_TYPE
/

CREATE TABLE ORGANIZATION_TYPE
(
      ORGANIZATION_ID VARCHAR(8)
        , ORGANIZATION_TYPE_CODE DECIMAL(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATION_TYPEP1 PRIMARY KEY(ORGANIZATION_ID,ORGANIZATION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION_TYPE_LIST
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION_TYPE_LIST
/

CREATE TABLE ORGANIZATION_TYPE_LIST
(
      ORGANIZATION_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATION_TYPE_LISTP1 PRIMARY KEY(ORGANIZATION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ORGANIZATION_YNQ
# -----------------------------------------------------------------------
drop table if exists ORGANIZATION_YNQ
/

CREATE TABLE ORGANIZATION_YNQ
(
      ORGANIZATION_ID VARCHAR(8)
        , QUESTION_ID VARCHAR(4)
        , ANSWER CHAR(1) NOT NULL
        , EXPLANATION MEDIUMTEXT
        , REVIEW_DATE DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ORGANIZATION_YNQP1 PRIMARY KEY(ORGANIZATION_ID,QUESTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PENDING_TRANSACTIONS
# -----------------------------------------------------------------------
drop table if exists PENDING_TRANSACTIONS
/

CREATE TABLE PENDING_TRANSACTIONS
(
      TRANSACTION_ID DECIMAL(10)
        , DOCUMENT_NUMBER VARCHAR(10) NOT NULL
        , SOURCE_AWARD_NUMBER VARCHAR(12) NOT NULL
        , DESTINATION_AWARD_NUMBER VARCHAR(12) NOT NULL
        , OBLIGATED_AMOUNT DECIMAL(12,2)
        , ANTICIPATED_AMOUNT DECIMAL(12,2)
        , COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PENDING_TRANSACTIONSP1 PRIMARY KEY(TRANSACTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PERSON_APPOINTMENT
# -----------------------------------------------------------------------
drop table if exists PERSON_APPOINTMENT
/

CREATE TABLE PERSON_APPOINTMENT
(
      APPOINTMENT_ID DECIMAL(22)
        , PERSON_ID VARCHAR(40) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , APPOINTMENT_START_DATE DATETIME
        , APPOINTMENT_END_DATE DATETIME
        , APPOINTMENT_TYPE_CODE VARCHAR(3)
        , JOB_TITLE VARCHAR(50)
        , PREFERED_JOB_TITLE VARCHAR(51)
        , JOB_CODE VARCHAR(6) NOT NULL
        , SALARY DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PERSON_APPOINTMENTP1 PRIMARY KEY(APPOINTMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PERSON_DEGREE
# -----------------------------------------------------------------------
drop table if exists PERSON_DEGREE
/

CREATE TABLE PERSON_DEGREE
(
      DEGREE_ID DECIMAL(22)
        , PERSON_ID VARCHAR(40) NOT NULL
        , GRADUATION_YEAR VARCHAR(4)
        , DEGREE_CODE VARCHAR(6)
        , DEGREE VARCHAR(80)
        , FIELD_OF_STUDY VARCHAR(80)
        , SPECIALIZATION VARCHAR(80)
        , SCHOOL VARCHAR(50)
        , SCHOOL_ID_CODE VARCHAR(3)
        , SCHOOL_ID VARCHAR(20)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PERSON_DEGREEP1 PRIMARY KEY(DEGREE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PERSON_EDITABLE_FIELDS
# -----------------------------------------------------------------------
drop table if exists PERSON_EDITABLE_FIELDS
/

CREATE TABLE PERSON_EDITABLE_FIELDS
(
      FIELD_NAME VARCHAR(255)
        , ACTIVE_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PERSON_EDITABLE_FIELDSP1 PRIMARY KEY(FIELD_NAME)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PERSON_EXT_T
# -----------------------------------------------------------------------
drop table if exists PERSON_EXT_T
/

CREATE TABLE PERSON_EXT_T
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , PERSON_ID VARCHAR(40)
        , AGE_BY_FISCAL_YEAR DECIMAL(3)
        , RACE VARCHAR(30)
        , EDUCATION_LEVEL VARCHAR(30)
        , DEGREE VARCHAR(11)
        , MAJOR VARCHAR(30)
        , IS_HANDICAPPED CHAR(1)
        , HANDICAP_TYPE VARCHAR(30)
        , IS_VETERAN CHAR(1)
        , VETERAN_TYPE VARCHAR(30)
        , VISA_CODE VARCHAR(20)
        , VISA_TYPE VARCHAR(30)
        , VISA_RENEWAL_DATE DATETIME
        , HAS_VISA CHAR(1)
        , OFFICE_LOCATION VARCHAR(30)
        , SECONDRY_OFFICE_LOCATION VARCHAR(30)
        , SCHOOL VARCHAR(50)
        , YEAR_GRADUATED VARCHAR(30)
        , DIRECTORY_DEPARTMENT VARCHAR(30)
        , PRIMARY_TITLE VARCHAR(51)
        , DIRECTORY_TITLE VARCHAR(50)
        , IS_RESEARCH_STAFF CHAR(1)
        , VACATION_ACCURAL CHAR(1)
        , IS_ON_SABBATICAL CHAR(1)
        , ID_PROVIDED VARCHAR(30)
        , ID_VERIFIED VARCHAR(30)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , COUNTY VARCHAR(30)
        , BIOSKETCH_DESCRIPTION VARCHAR(4000)
        , BIOSKETCH_FILE LONGBLOB
        , BIOSKETCH_FILENAME VARCHAR(300)
        , BIOSKETCH_FILE_CONTENT_TYPE VARCHAR(200)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PERSON_EXT_TP1 PRIMARY KEY(PERSON_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PERSON_TRAINING
# -----------------------------------------------------------------------
drop table if exists PERSON_TRAINING
/

CREATE TABLE PERSON_TRAINING
(
      PERSON_TRAINING_ID DECIMAL(12)
        , PERSON_ID VARCHAR(40) NOT NULL
        , TRAINING_NUMBER DECIMAL(4) NOT NULL
        , TRAINING_CODE DECIMAL(4) NOT NULL
        , DATE_REQUESTED DATETIME
        , DATE_SUBMITTED DATETIME
        , DATE_ACKNOWLEDGED DATETIME
        , FOLLOWUP_DATE DATETIME
        , SCORE VARCHAR(9)
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PERSON_TRAININGP1 PRIMARY KEY(PERSON_TRAINING_ID)


    , INDEX UQ_PERSON_TRAINING (PERSON_ID, TRAINING_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL
# -----------------------------------------------------------------------
drop table if exists PROPOSAL
/

CREATE TABLE PROPOSAL
(
      DOCUMENT_NUMBER VARCHAR(10) NOT NULL
        , CREATE_TIMESTAMP DATETIME
        , MAIL_DESCRIPTION VARCHAR(80)
        , PROPOSAL_SEQUENCE_STATUS VARCHAR(10) NOT NULL
        , FISCAL_MONTH VARCHAR(2)
        , FISCAL_YEAR VARCHAR(4)
        , LEAD_UNIT_NUMBER VARCHAR(8)
        , PROPOSAL_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SPONSOR_PROPOSAL_NUMBER VARCHAR(70)
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PROPOSAL_TYPE_CODE VARCHAR(3) NOT NULL
        , CURRENT_ACCOUNT_NUMBER VARCHAR(7)
        , TITLE VARCHAR(200) NOT NULL
        , SPONSOR_CODE CHAR(6) NOT NULL
        , ROLODEX_ID DECIMAL(6)
        , NOTICE_OF_OPPORTUNITY_CODE VARCHAR(3)
        , GRAD_STUD_HEADCOUNT DECIMAL(3)
        , GRAD_STUD_PERSON_MONTHS DECIMAL(5,2)
        , TYPE_OF_ACCOUNT CHAR(1)
        , ACTIVITY_TYPE_CODE VARCHAR(3) NOT NULL
        , REQUESTED_START_DATE_INITIAL DATETIME
        , REQUESTED_START_DATE_TOTAL DATETIME
        , REQUESTED_END_DATE_INITIAL DATETIME
        , REQUESTED_END_DATE_TOTAL DATETIME
        , TOTAL_DIRECT_COST_INITIAL DECIMAL(12,2)
        , TOTAL_DIRECT_COST_TOTAL DECIMAL(12,2)
        , TOTAL_INDIRECT_COST_INITIAL DECIMAL(12,2)
        , TOTAL_INDIRECT_COST_TOTAL DECIMAL(12,2)
        , NUMBER_OF_COPIES VARCHAR(7)
        , DEADLINE_DATE DATETIME
        , DEADLINE_TYPE CHAR(1)
        , MAIL_BY CHAR(1)
        , MAIL_TYPE CHAR(1)
        , MAIL_ACCOUNT_NUMBER CHAR(7)
        , SUBCONTRACT_FLAG CHAR(1) NOT NULL
        , COST_SHARING_INDICATOR CHAR(2) NOT NULL
        , IDC_RATE_INDICATOR CHAR(2) NOT NULL
        , SPECIAL_REVIEW_INDICATOR CHAR(2) NOT NULL
        , STATUS_CODE DECIMAL(3) NOT NULL
        , SCIENCE_CODE_INDICATOR CHAR(2) NOT NULL
        , NSF_CODE VARCHAR(15)
        , PRIME_SPONSOR_CODE CHAR(6)
        , INITIAL_CONTRACT_ADMIN VARCHAR(40)
        , IP_REVIEW_ACTIVITY_INDICATOR CHAR(2) NOT NULL
        , CURRENT_AWARD_NUMBER VARCHAR(12)
        , CFDA_NUMBER VARCHAR(7)
        , OPPORTUNITY VARCHAR(50)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , AWARD_TYPE_CODE DECIMAL(3)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSALP1 PRIMARY KEY(PROPOSAL_ID)


    , INDEX UQ_PROPOSAL (PROPOSAL_NUMBER, SEQUENCE_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_ADMIN_DETAILS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_ADMIN_DETAILS
/

CREATE TABLE PROPOSAL_ADMIN_DETAILS
(
      PROPOSAL_ADMIN_DETAIL_ID DECIMAL(22)
        , DEV_PROPOSAL_NUMBER VARCHAR(12) NOT NULL
        , INST_PROPOSAL_ID DECIMAL(22) NOT NULL
        , DATE_SUBMITTED_BY_DEPT DATETIME
        , DATE_RETURNED_TO_DEPT DATETIME
        , DATE_APPROVED_BY_OSP DATETIME
        , DATE_SUBMITTED_TO_AGENCY DATETIME
        , INST_PROP_CREATE_DATE DATETIME
        , INST_PROP_CREATE_USER VARCHAR(8)
        , SIGNED_BY VARCHAR(8)
        , SUBMISSION_TYPE CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_ADMIN_DETAILSP1 PRIMARY KEY(PROPOSAL_ADMIN_DETAIL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_COMMENTS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_COMMENTS
/

CREATE TABLE PROPOSAL_COMMENTS
(
      PROPOSAL_COMMENTS_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , COMMENT_TYPE_CODE VARCHAR(3) NOT NULL
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_COMMENTSP1 PRIMARY KEY(PROPOSAL_COMMENTS_ID)


    , INDEX UQ_PROPOSAL_COMMENTS (PROPOSAL_NUMBER, SEQUENCE_NUMBER, COMMENT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_COST_SHARING
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_COST_SHARING
/

CREATE TABLE PROPOSAL_COST_SHARING
(
      PROPOSAL_COST_SHARING_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , FISCAL_YEAR CHAR(4) NOT NULL
        , COST_SHARING_PERCENTAGE DECIMAL(5,2)
        , COST_SHARING_TYPE_CODE DECIMAL(3) NOT NULL
        , SOURCE_ACCOUNT VARCHAR(32)
        , AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_COST_SHARINGP1 PRIMARY KEY(PROPOSAL_COST_SHARING_ID)


    , INDEX UQ_PROPOSAL_COST_SHARING (PROPOSAL_NUMBER, SEQUENCE_NUMBER, FISCAL_YEAR, COST_SHARING_TYPE_CODE, SOURCE_ACCOUNT)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_CUSTOM_DATA
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_CUSTOM_DATA
/

CREATE TABLE PROPOSAL_CUSTOM_DATA
(
      PROPOSAL_CUSTOM_DATA_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(22)
        , PROPOSAL_NUMBER VARCHAR(10)
        , SEQUENCE_NUMBER DECIMAL(8)
        , CUSTOM_ATTRIBUTE_ID DECIMAL(12)
        , VALUE VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PROPOSAL_CUSTOM_DATAP1 PRIMARY KEY(PROPOSAL_CUSTOM_DATA_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_EXEMPT_NUMBER
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_EXEMPT_NUMBER
/

CREATE TABLE PROPOSAL_EXEMPT_NUMBER
(
      PROPOSAL_EXEMPT_NUMBER_ID DECIMAL(12)
        , PROPOSAL_SPECIAL_REVIEW_ID DECIMAL(12)
        , EXEMPTION_TYPE_CODE VARCHAR(3)
        , UPDATE_USER VARCHAR(60)
        , UPDATE_TIMESTAMP DATETIME
        , VER_NBR DECIMAL(8) default 1
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_EXEMPT_NUMBERP1 PRIMARY KEY(PROPOSAL_EXEMPT_NUMBER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_IDC_RATE
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_IDC_RATE
/

CREATE TABLE PROPOSAL_IDC_RATE
(
      PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_IDC_RATE_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , APPLICABLE_IDC_RATE DECIMAL(5,2)
        , IDC_RATE_TYPE_CODE DECIMAL(3) NOT NULL
        , FISCAL_YEAR CHAR(4) NOT NULL
        , ON_CAMPUS_FLAG CHAR(1) NOT NULL
        , UNDERRECOVERY_OF_IDC DECIMAL(12,2)
        , SOURCE_ACCOUNT VARCHAR(32)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_IDC_RATEP1 PRIMARY KEY(PROPOSAL_IDC_RATE_ID)


    , INDEX UQ_PROPOSAL_IDC_RATE (PROPOSAL_NUMBER, SEQUENCE_NUMBER, APPLICABLE_IDC_RATE, IDC_RATE_TYPE_CODE, FISCAL_YEAR, ON_CAMPUS_FLAG, SOURCE_ACCOUNT)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_INV_CERTIFICATION
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_INV_CERTIFICATION
/

CREATE TABLE PROPOSAL_INV_CERTIFICATION
(
      PROPOSAL_NUMBER VARCHAR(12)
        , PROP_PERSON_NUMBER DECIMAL(12)
        , CERTIFIED_FLAG CHAR(1)
        , DATE_CERTIFIED DATETIME
        , DATE_RECEIVED_BY_OSP DATETIME
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_INV_CERTIFICATIONP1 PRIMARY KEY(PROPOSAL_NUMBER,PROP_PERSON_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_IP_REVIEW_JOIN
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_IP_REVIEW_JOIN
/

CREATE TABLE PROPOSAL_IP_REVIEW_JOIN
(
      PROPOSAL_IP_REVIEW_JOIN_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , IP_REVIEW_ID DECIMAL(12)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_IP_REVIEW_JOINP1 PRIMARY KEY(PROPOSAL_IP_REVIEW_JOIN_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_IP_REV_ACTIVITY
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_IP_REV_ACTIVITY
/

CREATE TABLE PROPOSAL_IP_REV_ACTIVITY
(
      PROPOSAL_IP_REV_ACTIVITY_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ACTIVITY_NUMBER DECIMAL(3) NOT NULL
        , IP_REVIEW_ACTIVITY_TYPE_CODE VARCHAR(3) NOT NULL
        , ACTIVITY_DATE DATETIME
        , COMMENTS VARCHAR(150)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , IP_REVIEW_ID DECIMAL(12) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_IP_REV_ACTIVITYP1 PRIMARY KEY(PROPOSAL_IP_REV_ACTIVITY_ID)


    , INDEX UQ_PROPOSAL_IP_REV_ACTIVITY (PROPOSAL_NUMBER, SEQUENCE_NUMBER, ACTIVITY_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_LOG
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_LOG
/

CREATE TABLE PROPOSAL_LOG
(
      FISCAL_MONTH DECIMAL(2)
        , FISCAL_YEAR DECIMAL(4)
        , PI_NAME VARCHAR(100)
        , PROPOSAL_TYPE_CODE VARCHAR(3) NOT NULL
        , LOG_STATUS VARCHAR(3) NOT NULL
        , CREATE_TIMESTAMP DATETIME NOT NULL
        , CREATE_USER VARCHAR(60) NOT NULL
        , PI_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , PROPOSAL_LOG_TYPE_CODE VARCHAR(3)
        , PROPOSAL_NUMBER VARCHAR(8)
        , TITLE VARCHAR(200) NOT NULL
        , LEAD_UNIT VARCHAR(8) NOT NULL
        , SPONSOR_CODE CHAR(6)
        , SPONSOR_NAME VARCHAR(200)
        , COMMENTS VARCHAR(300)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , DEADLINE_DATE DATETIME
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_LOGP1 PRIMARY KEY(PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_LOG_STATUS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_LOG_STATUS
/

CREATE TABLE PROPOSAL_LOG_STATUS
(
      PROPOSAL_LOG_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_LOG_STATUSP1 PRIMARY KEY(PROPOSAL_LOG_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_LOG_TYPE
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_LOG_TYPE
/

CREATE TABLE PROPOSAL_LOG_TYPE
(
      PROPOSAL_LOG_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_LOG_TYPEP1 PRIMARY KEY(PROPOSAL_LOG_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_NOTEPAD
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_NOTEPAD
/

CREATE TABLE PROPOSAL_NOTEPAD
(
      CREATE_TIMESTAMP DATETIME NOT NULL
        , NOTE_TOPIC VARCHAR(60) NOT NULL
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NOTEPAD_ID DECIMAL(12)
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , ENTRY_NUMBER DECIMAL(4) NOT NULL
        , COMMENTS LONGTEXT NOT NULL
        , RESTRICTED_VIEW CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_NOTEPADP1 PRIMARY KEY(PROPOSAL_NOTEPAD_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_PERSONS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_PERSONS
/

CREATE TABLE PROPOSAL_PERSONS
(
      PROPOSAL_PERSON_ID DECIMAL(22)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(10) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , ROLODEX_ID DECIMAL(6)
        , FULL_NAME VARCHAR(90)
        , CONTACT_ROLE_CODE VARCHAR(12) NOT NULL
        , KEY_PERSON_PROJECT_ROLE VARCHAR(60)
        , ACADEMIC_YEAR_EFFORT DECIMAL(5,2)
        , CALENDAR_YEAR_EFFORT DECIMAL(5,2)
        , SUMMER_EFFORT DECIMAL(5,2)
        , TOTAL_EFFORT DECIMAL(5,2)
        , FACULTY_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PROPOSAL_PERSONSP1 PRIMARY KEY(PROPOSAL_PERSON_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_PERSON_UNITS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_PERSON_UNITS
/

CREATE TABLE PROPOSAL_PERSON_UNITS
(
      PROPOSAL_PERSON_ID DECIMAL(22) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , LEAD_UNIT_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , PROPOSAL_PERSON_UNIT_ID DECIMAL(22)
    
    , CONSTRAINT PROPOSAL_PERSON_UNITSP1 PRIMARY KEY(PROPOSAL_PERSON_UNIT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_PERS_UNIT_CRED_SPLITS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_PERS_UNIT_CRED_SPLITS
/

CREATE TABLE PROPOSAL_PERS_UNIT_CRED_SPLITS
(
      PPU_CREDIT_SPLIT_ID DECIMAL(22)
        , PROPOSAL_PERSON_UNIT_ID DECIMAL(22) NOT NULL
        , INV_CREDIT_TYPE_CODE VARCHAR(3) NOT NULL
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PROPOSAL_PERS_UNIT_CRED_SPLP1 PRIMARY KEY(PPU_CREDIT_SPLIT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_PER_CREDIT_SPLIT
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_PER_CREDIT_SPLIT
/

CREATE TABLE PROPOSAL_PER_CREDIT_SPLIT
(
      PROPOSAL_PER_CREDIT_SPLIT_ID DECIMAL(22)
        , PROPOSAL_PERSON_ID DECIMAL(22) NOT NULL
        , INV_CREDIT_TYPE_CODE VARCHAR(3) NOT NULL
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PROPOSAL_PER_CREDIT_SPLITP1 PRIMARY KEY(PROPOSAL_PER_CREDIT_SPLIT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_RESPONSE
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_RESPONSE
/

CREATE TABLE PROPOSAL_RESPONSE
(
      PROPOSAL_RESPONSE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_RESPONSEP1 PRIMARY KEY(PROPOSAL_RESPONSE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_SCIENCE_KEYWORD
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_SCIENCE_KEYWORD
/

CREATE TABLE PROPOSAL_SCIENCE_KEYWORD
(
      PROPOSAL_SCIENCE_KEYWORD_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SCIENCE_KEYWORD_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_SCIENCE_KEYWORDP1 PRIMARY KEY(PROPOSAL_SCIENCE_KEYWORD_ID)


    , INDEX UQ_PROPOSAL_SCIENCE_KEYWORD (PROPOSAL_NUMBER, SEQUENCE_NUMBER, SCIENCE_KEYWORD_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_SPECIAL_REVIEW
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_SPECIAL_REVIEW
/

CREATE TABLE PROPOSAL_SPECIAL_REVIEW
(
      EXPIRATION_DATE DATETIME
        , PROPOSAL_SPECIAL_REVIEW_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SPECIAL_REVIEW_NUMBER DECIMAL(3) NOT NULL
        , SPECIAL_REVIEW_CODE VARCHAR(3) NOT NULL
        , APPROVAL_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , COMMENTS LONGTEXT
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_SPECIAL_REVIEWP1 PRIMARY KEY(PROPOSAL_SPECIAL_REVIEW_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_STATE
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_STATE
/

CREATE TABLE PROPOSAL_STATE
(
      STATE_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_STATEP1 PRIMARY KEY(STATE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_STATUS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_STATUS
/

CREATE TABLE PROPOSAL_STATUS
(
      PROPOSAL_STATUS_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_STATUSP1 PRIMARY KEY(PROPOSAL_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_TYPE
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_TYPE
/

CREATE TABLE PROPOSAL_TYPE
(
      PROPOSAL_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_TYPEP1 PRIMARY KEY(PROPOSAL_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_UNITS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_UNITS
/

CREATE TABLE PROPOSAL_UNITS
(
      PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , LEAD_UNIT_FLAG CHAR(1)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PROPOSAL_UNITS_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_UNITSP1 PRIMARY KEY(PROPOSAL_UNITS_ID)


    , INDEX UQ_PROPOSAL_UNITS (PROPOSAL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, UNIT_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_UNIT_CONTACTS
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_UNIT_CONTACTS
/

CREATE TABLE PROPOSAL_UNIT_CONTACTS
(
      PROPOSAL_UNIT_CONTACT_ID DECIMAL(22)
        , PROPOSAL_ID DECIMAL(22) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(10) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , FULL_NAME VARCHAR(90)
        , UNIT_ADMINISTRATOR_TYPE_CODE VARCHAR(3) NOT NULL
        , UNIT_CONTACT_TYPE VARCHAR(13) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT PROPOSAL_UNIT_CONTACTSP1 PRIMARY KEY(PROPOSAL_UNIT_CONTACT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROPOSAL_UNIT_CREDIT_SPLIT
# -----------------------------------------------------------------------
drop table if exists PROPOSAL_UNIT_CREDIT_SPLIT
/

CREATE TABLE PROPOSAL_UNIT_CREDIT_SPLIT
(
      PROPOSAL_UNIT_CREDIT_SPLIT_ID DECIMAL(12)
        , PROPOSAL_ID DECIMAL(12) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , INV_CREDIT_TYPE_CODE VARCHAR(3) NOT NULL
        , CREDIT DECIMAL(5,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROPOSAL_UNIT_CREDIT_SPLITP1 PRIMARY KEY(PROPOSAL_UNIT_CREDIT_SPLIT_ID)


    , INDEX UQ_PROPOSAL_UNIT_CREDIT_SPLIT (PROPOSAL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, UNIT_NUMBER, INV_CREDIT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROP_ROLE_TEMPLATE
# -----------------------------------------------------------------------
drop table if exists PROP_ROLE_TEMPLATE
/

CREATE TABLE PROP_ROLE_TEMPLATE
(
      ID DECIMAL(8)
        , PERSON_ID VARCHAR(40) NOT NULL
        , ROLE_NAME VARCHAR(500) NOT NULL
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , ACTIVE_FLAG CHAR(1) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROP_ROLE_TEMPLATEP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL
# -----------------------------------------------------------------------
drop table if exists PROTOCOL
/

CREATE TABLE PROTOCOL
(
      ACTIVE CHAR(1) default 'T' NOT NULL
        , PROTOCOL_ID DECIMAL(12)
        , DOCUMENT_NUMBER DECIMAL(10) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PROTOCOL_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTOCOL_STATUS_CODE VARCHAR(3) NOT NULL
        , TITLE VARCHAR(2000) NOT NULL
        , DESCRIPTION VARCHAR(2000)
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , EXPIRATION_DATE DATETIME
        , LAST_APPROVAL_DATE DATETIME
        , FDA_APPLICATION_NUMBER VARCHAR(15)
        , REFERENCE_NUMBER_1 VARCHAR(50)
        , REFERENCE_NUMBER_2 VARCHAR(50)
        , IS_BILLABLE VARCHAR(1) default 'n' NOT NULL
        , SPECIAL_REVIEW_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , VULNERABLE_SUBJECT_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , KEY_STUDY_PERSON_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , FUNDING_SOURCE_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , CORRESPONDENT_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , REFERENCE_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , RELATED_PROJECTS_INDICATOR VARCHAR(2) default 'n0' NOT NULL
        , CREATE_TIMESTAMP DATETIME
        , CREATE_USER VARCHAR(8)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOLP1 PRIMARY KEY(PROTOCOL_ID)


    , INDEX UQ_PROTOCOL (PROTOCOL_NUMBER, SEQUENCE_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ACTIONS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ACTIONS
/

CREATE TABLE PROTOCOL_ACTIONS
(
      PROTOCOL_ACTION_ID DECIMAL(12)
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4)
        , ACTION_ID DECIMAL(6) NOT NULL
        , PROTOCOL_ACTION_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12)
        , COMMENTS VARCHAR(2000)
        , ACTION_DATE DATETIME
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTUAL_ACTION_DATE DATETIME
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ACTIONSP1 PRIMARY KEY(PROTOCOL_ACTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ACTION_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ACTION_TYPE
/

CREATE TABLE PROTOCOL_ACTION_TYPE
(
      PROTOCOL_ACTION_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , TRIGGER_SUBMISSION CHAR(1) NOT NULL
        , TRIGGER_CORRESPONDENCE CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FINAL_ACTION_FOR_BATCH_CORRESP CHAR(1) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ACTION_TYPEP1 PRIMARY KEY(PROTOCOL_ACTION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_GROUP
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_GROUP
/

CREATE TABLE PROTOCOL_ATTACHMENT_GROUP
(
      GROUP_CD VARCHAR(3)
        , DESCRIPTION VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_GROUPP1 PRIMARY KEY(GROUP_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_PERSONNEL
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_PERSONNEL
/

CREATE TABLE PROTOCOL_ATTACHMENT_PERSONNEL
(
      PA_PERSONNEL_ID DECIMAL(12)
        , PROTOCOL_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , TYPE_CD VARCHAR(3) NOT NULL
        , DOCUMENT_ID DECIMAL(4) NOT NULL
        , FILE_ID DECIMAL(22) NOT NULL
        , DESCRIPTION VARCHAR(200)
        , PERSON_ID DECIMAL(12) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_PERSONNP1 PRIMARY KEY(PA_PERSONNEL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_PROTOCOL
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_PROTOCOL
/

CREATE TABLE PROTOCOL_ATTACHMENT_PROTOCOL
(
      PA_PROTOCOL_ID DECIMAL(12)
        , PROTOCOL_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , TYPE_CD VARCHAR(3) NOT NULL
        , DOCUMENT_ID DECIMAL(4) NOT NULL
        , FILE_ID DECIMAL(22) NOT NULL
        , DESCRIPTION VARCHAR(200)
        , STATUS_CD VARCHAR(3)
        , CONTACT_NAME VARCHAR(30)
        , EMAIL_ADDRESS VARCHAR(60)
        , PHONE_NUMBER VARCHAR(20)
        , COMMENTS VARCHAR(300)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_PROTOCOP1 PRIMARY KEY(PA_PROTOCOL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_STATUS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_STATUS
/

CREATE TABLE PROTOCOL_ATTACHMENT_STATUS
(
      STATUS_CD VARCHAR(3)
        , DESCRIPTION VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_STATUSP1 PRIMARY KEY(STATUS_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_TYPE
/

CREATE TABLE PROTOCOL_ATTACHMENT_TYPE
(
      TYPE_CD VARCHAR(3)
        , DESCRIPTION VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_TYPEP1 PRIMARY KEY(TYPE_CD)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ATTACHMENT_TYPE_GROUP
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ATTACHMENT_TYPE_GROUP
/

CREATE TABLE PROTOCOL_ATTACHMENT_TYPE_GROUP
(
      TYPE_GROUP_ID DECIMAL(12)
        , TYPE_CD VARCHAR(3) NOT NULL
        , GROUP_CD VARCHAR(3) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ATTACHMENT_TYPE_GRP1 PRIMARY KEY(TYPE_GROUP_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_CONTINGENCY
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_CONTINGENCY
/

CREATE TABLE PROTOCOL_CONTINGENCY
(
      PROTOCOL_CONTINGENCY_CODE VARCHAR(4)
        , DESCRIPTION VARCHAR(2000) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_CONTINGENCYP1 PRIMARY KEY(PROTOCOL_CONTINGENCY_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_CORRESPONDENCE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_CORRESPONDENCE
/

CREATE TABLE PROTOCOL_CORRESPONDENCE
(
      ID DECIMAL(12)
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ACTION_ID DECIMAL(6) NOT NULL
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , ACTION_ID_FK DECIMAL(12) NOT NULL
        , PROTO_CORRESP_TYPE_CODE VARCHAR(3) NOT NULL
        , FINAL_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , CORRESPONDENCE LONGBLOB NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_CORRESPONDENCEP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_DOCUMENT
/

CREATE TABLE PROTOCOL_DOCUMENT
(
      DOCUMENT_NUMBER DECIMAL(10)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , PROTOCOL_WORKFLOW_TYPE VARCHAR(80)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_EXEMPT_CHKLST
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_EXEMPT_CHKLST
/

CREATE TABLE PROTOCOL_EXEMPT_CHKLST
(
      PROTOCOL_EXEMPT_CHKLST_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4) NOT NULL
        , EXEMPT_STUDIES_CHECKLIST_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_EXEMPT_CHKLSTP1 PRIMARY KEY(PROTOCOL_EXEMPT_CHKLST_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_EXEMPT_NUMBER
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_EXEMPT_NUMBER
/

CREATE TABLE PROTOCOL_EXEMPT_NUMBER
(
      PROTOCOL_EXEMPT_NUMBER_ID DECIMAL(12)
        , PROTOCOL_SPECIAL_REVIEW_ID DECIMAL(12) NOT NULL
        , EXEMPTION_TYPE_CODE VARCHAR(3) NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_EXEMPT_NUMBERP1 PRIMARY KEY(PROTOCOL_EXEMPT_NUMBER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_EXPIDITED_CHKLST
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_EXPIDITED_CHKLST
/

CREATE TABLE PROTOCOL_EXPIDITED_CHKLST
(
      PROTOCOL_EXPEDITED_CHKLST_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4) NOT NULL
        , EXPEDITED_REV_CHKLST_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_EXPIDITED_CHKLSTP1 PRIMARY KEY(PROTOCOL_EXPEDITED_CHKLST_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_FUNDING_SOURCE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_FUNDING_SOURCE
/

CREATE TABLE PROTOCOL_FUNDING_SOURCE
(
      PROTOCOL_FUNDING_SOURCE_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , SEQUENCE_NUMBER DECIMAL(4)
        , FUNDING_SOURCE_TYPE_CODE DECIMAL(3) NOT NULL
        , FUNDING_SOURCE VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FUNDING_SOURCE_NAME VARCHAR(200)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_FUNDING_SOURCEP1 PRIMARY KEY(PROTOCOL_FUNDING_SOURCE_ID)


    , INDEX UQ_PROTOCOL_FUNDING_SOURCE (PROTOCOL_ID, FUNDING_SOURCE_TYPE_CODE, FUNDING_SOURCE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_LOCATION
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_LOCATION
/

CREATE TABLE PROTOCOL_LOCATION
(
      PROTOCOL_LOCATION_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PROTOCOL_ORG_TYPE_CODE VARCHAR(3) NOT NULL
        , ORGANIZATION_ID VARCHAR(8) NOT NULL
        , ROLODEX_ID DECIMAL(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_LOCATIONP1 PRIMARY KEY(PROTOCOL_LOCATION_ID)


    , INDEX UQ_PROTOCOL_LOCATION (PROTOCOL_ORG_TYPE_CODE, ORGANIZATION_ID, PROTOCOL_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_MODULES
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_MODULES
/

CREATE TABLE PROTOCOL_MODULES
(
      PROTOCOL_MODULE_CODE VARCHAR(5)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_MODULESP1 PRIMARY KEY(PROTOCOL_MODULE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_NOTEPAD
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_NOTEPAD
/

CREATE TABLE PROTOCOL_NOTEPAD
(
      PROTOCOL_NOTEPAD_ID DECIMAL(12)
        , PROTOCOL_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , ENTRY_NUMBER DECIMAL(4) NOT NULL
        , COMMENTS LONGTEXT
        , RESTRICTED_VIEW CHAR(1) NOT NULL
        , NOTE_TYPE VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(10) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_NOTEPADP1 PRIMARY KEY(PROTOCOL_NOTEPAD_ID)


    , INDEX UQ_PROTOCOL_NOTEPAD (PROTOCOL_NUMBER, ENTRY_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_ORG_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_ORG_TYPE
/

CREATE TABLE PROTOCOL_ORG_TYPE
(
      PROTOCOL_ORG_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_ORG_TYPEP1 PRIMARY KEY(PROTOCOL_ORG_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_PERSONS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_PERSONS
/

CREATE TABLE PROTOCOL_PERSONS
(
      PROTOCOL_PERSON_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , PERSON_NAME VARCHAR(90) NOT NULL
        , PROTOCOL_PERSON_ROLE_ID VARCHAR(12)
        , ROLODEX_ID DECIMAL(12)
        , AFFILIATION_TYPE_CODE DECIMAL(3)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_PERSONSP1 PRIMARY KEY(PROTOCOL_PERSON_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_PERSON_ROLES
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_PERSON_ROLES
/

CREATE TABLE PROTOCOL_PERSON_ROLES
(
      PROTOCOL_PERSON_ROLE_ID VARCHAR(12)
        , DESCRIPTION VARCHAR(250) NOT NULL
        , UNIT_DETAILS_REQUIRED CHAR(1) default 'N' NOT NULL
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , AFFILIATION_DETAILS_REQUIRED CHAR(1) default 'N' NOT NULL
        , TRAINING_DETAILS_REQUIRED CHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_PERSON_ROLESP1 PRIMARY KEY(PROTOCOL_PERSON_ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_PERSON_ROLE_MAPPING
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_PERSON_ROLE_MAPPING
/

CREATE TABLE PROTOCOL_PERSON_ROLE_MAPPING
(
      ROLE_MAPPING_ID DECIMAL(12)
        , SOURCE_ROLE_ID VARCHAR(12) NOT NULL
        , TARGET_ROLE_ID VARCHAR(12) NOT NULL
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_PERSON_ROLE_MAPPINP1 PRIMARY KEY(ROLE_MAPPING_ID)


    , INDEX UQ_PERSON_MAPPING (SOURCE_ROLE_ID, TARGET_ROLE_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_REFERENCES
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_REFERENCES
/

CREATE TABLE PROTOCOL_REFERENCES
(
      PROTOCOL_REFERENCE_ID DECIMAL(4)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PROTOCOL_REFERENCE_NUMBER DECIMAL(3) NOT NULL
        , PROTOCOL_REFERENCE_TYPE_CODE DECIMAL(3) NOT NULL
        , REFERENCE_KEY VARCHAR(50) NOT NULL
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , COMMENTS LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_REFERENCESP1 PRIMARY KEY(PROTOCOL_REFERENCE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_REFERENCE_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_REFERENCE_TYPE
/

CREATE TABLE PROTOCOL_REFERENCE_TYPE
(
      PROTOCOL_REFERENCE_TYPE_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_REFERENCE_TYPEP1 PRIMARY KEY(PROTOCOL_REFERENCE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_RESEARCH_AREAS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_RESEARCH_AREAS
/

CREATE TABLE PROTOCOL_RESEARCH_AREAS
(
      ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , RESEARCH_AREA_CODE VARCHAR(8) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_RESEARCH_AREASP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_REVIEWERS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_REVIEWERS
/

CREATE TABLE PROTOCOL_REVIEWERS
(
      PROTOCOL_REVIEWER_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40) NOT NULL
        , NON_EMPLOYEE_FLAG CHAR(1) NOT NULL
        , REVIEWER_TYPE_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_REVIEWERSP1 PRIMARY KEY(PROTOCOL_REVIEWER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_REVIEWER_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_REVIEWER_TYPE
/

CREATE TABLE PROTOCOL_REVIEWER_TYPE
(
      REVIEWER_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_REVIEWER_TYPEP1 PRIMARY KEY(REVIEWER_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_REVIEW_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_REVIEW_TYPE
/

CREATE TABLE PROTOCOL_REVIEW_TYPE
(
      PROTOCOL_REVIEW_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_REVIEW_TYPEP1 PRIMARY KEY(PROTOCOL_REVIEW_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_RISK_LEVELS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_RISK_LEVELS
/

CREATE TABLE PROTOCOL_RISK_LEVELS
(
      PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , RISK_LEVEL_CODE VARCHAR(3) NOT NULL
        , COMMENTS VARCHAR(2000)
        , DATE_ASSIGNED DATETIME NOT NULL
        , DATE_UPDATED DATETIME
        , STATUS CHAR(1) NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , PROTOCOL_RISK_LEVELS_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_RISK_LEVELSP1 PRIMARY KEY(PROTOCOL_RISK_LEVELS_ID)


    , INDEX UQ_PROTOCOL_RISK_LEVELS (PROTOCOL_NUMBER, SEQUENCE_NUMBER, RISK_LEVEL_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_SPECIAL_REVIEW
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_SPECIAL_REVIEW
/

CREATE TABLE PROTOCOL_SPECIAL_REVIEW
(
      PROTOCOL_SPECIAL_REVIEW_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , SPECIAL_REVIEW_NUMBER DECIMAL(3) NOT NULL
        , SPECIAL_REVIEW_CODE DECIMAL(3) NOT NULL
        , APPROVAL_TYPE_CODE DECIMAL(3) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , APPLICATION_DATE DATETIME
        , APPROVAL_DATE DATETIME
        , EXPIRATION_DATE DATETIME
        , COMMENTS LONGTEXT
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_SPECIAL_REVIEWP1 PRIMARY KEY(PROTOCOL_SPECIAL_REVIEW_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_STATUS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_STATUS
/

CREATE TABLE PROTOCOL_STATUS
(
      PROTOCOL_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_STATUSP1 PRIMARY KEY(PROTOCOL_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_SUBMISSION
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_SUBMISSION
/

CREATE TABLE PROTOCOL_SUBMISSION
(
      SUBMISSION_ID DECIMAL(12)
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4) NOT NULL
        , SCHEDULE_ID VARCHAR(10)
        , COMMITTEE_ID VARCHAR(15)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SCHEDULE_ID_FK DECIMAL(12)
        , COMMITTEE_ID_FK DECIMAL(12)
        , SUBMISSION_TYPE_CODE VARCHAR(3) NOT NULL
        , SUBMISSION_TYPE_QUAL_CODE VARCHAR(3)
        , SUBMISSION_STATUS_CODE VARCHAR(3) NOT NULL
        , PROTOCOL_REVIEW_TYPE_CODE VARCHAR(3) NOT NULL
        , SUBMISSION_DATE DATETIME NOT NULL
        , COMMENTS VARCHAR(2000)
        , YES_VOTE_COUNT DECIMAL(3)
        , NO_VOTE_COUNT DECIMAL(3)
        , ABSTAINER_COUNT DECIMAL(3)
        , VOTING_COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_SUBMISSIONP1 PRIMARY KEY(SUBMISSION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_SUBMISSION_DOC
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_SUBMISSION_DOC
/

CREATE TABLE PROTOCOL_SUBMISSION_DOC
(
      SUBMISSION_DOC_ID DECIMAL(12)
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SUBMISSION_NUMBER DECIMAL(4) NOT NULL
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , SUBMISSION_ID_FK DECIMAL(12) NOT NULL
        , DOCUMENT_ID DECIMAL(3) NOT NULL
        , FILE_NAME VARCHAR(300)
        , DOCUMENT LONGBLOB
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_SUBMISSION_DOCP1 PRIMARY KEY(SUBMISSION_DOC_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_TYPE
/

CREATE TABLE PROTOCOL_TYPE
(
      PROTOCOL_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_TYPEP1 PRIMARY KEY(PROTOCOL_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_UNITS
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_UNITS
/

CREATE TABLE PROTOCOL_UNITS
(
      PROTOCOL_PERSON_ID DECIMAL(12) NOT NULL
        , PROTOCOL_UNITS_ID DECIMAL(12)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , SEQUENCE_NUMBER DECIMAL(4)
        , UNIT_NUMBER VARCHAR(8) NOT NULL
        , LEAD_UNIT_FLAG VARCHAR(1) NOT NULL
        , PERSON_ID VARCHAR(40)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_UNITSP1 PRIMARY KEY(PROTOCOL_UNITS_ID)


    , INDEX UQ_PROTOCOL_UNITS (UNIT_NUMBER, PERSON_ID, PROTOCOL_PERSON_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_VOTE_ABSTAINEES
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_VOTE_ABSTAINEES
/

CREATE TABLE PROTOCOL_VOTE_ABSTAINEES
(
      PROTOCOL_VOTE_ABSTAINEES_ID DECIMAL(12)
        , PROTOCOL_ID_FK DECIMAL(12) NOT NULL
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , PERSON_ID VARCHAR(9) NOT NULL
        , NON_EMPLOYEE_FLAG VARCHAR(1) NOT NULL
        , COMMENTS VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_VOTE_ABSTAINEESP1 PRIMARY KEY(PROTOCOL_VOTE_ABSTAINEES_ID)


    , INDEX UQ_PROTOCOL_VOTE_ABSTAINEE (PROTOCOL_ID_FK, SCHEDULE_ID_FK, PERSON_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTOCOL_VULNERABLE_SUB
# -----------------------------------------------------------------------
drop table if exists PROTOCOL_VULNERABLE_SUB
/

CREATE TABLE PROTOCOL_VULNERABLE_SUB
(
      PROTOCOL_VULNERABLE_SUB_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , VULNERABLE_SUBJECT_TYPE_CODE VARCHAR(3) NOT NULL
        , SUBJECT_COUNT DECIMAL(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTOCOL_VULNERABLE_SUBP1 PRIMARY KEY(PROTOCOL_VULNERABLE_SUB_ID)


    , INDEX UQ_PROTOCOL_VULNERABLE_SUB (PROTOCOL_ID, VULNERABLE_SUBJECT_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTO_AMEND_RENEWAL
# -----------------------------------------------------------------------
drop table if exists PROTO_AMEND_RENEWAL
/

CREATE TABLE PROTO_AMEND_RENEWAL
(
      PROTO_AMEND_RENEWAL_ID DECIMAL(12)
        , PROTO_AMEND_REN_NUMBER VARCHAR(20) NOT NULL
        , DATE_CREATED DATETIME NOT NULL
        , SUMMARY LONGTEXT
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20)
        , SEQUENCE_NUMBER DECIMAL(4)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTO_AMEND_RENEWALP1 PRIMARY KEY(PROTO_AMEND_RENEWAL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTO_AMEND_RENEW_MODULES
# -----------------------------------------------------------------------
drop table if exists PROTO_AMEND_RENEW_MODULES
/

CREATE TABLE PROTO_AMEND_RENEW_MODULES
(
      PROTO_AMEND_RENEW_MODULES_ID DECIMAL(12)
        , PROTO_AMEND_RENEWAL_NUMBER VARCHAR(20) NOT NULL
        , PROTO_AMEND_RENEWAL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , PROTOCOL_MODULE_CODE VARCHAR(5) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTO_AMEND_RENEW_MODULESP1 PRIMARY KEY(PROTO_AMEND_RENEW_MODULES_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTO_CORRESP_TEMPL
# -----------------------------------------------------------------------
drop table if exists PROTO_CORRESP_TEMPL
/

CREATE TABLE PROTO_CORRESP_TEMPL
(
      PROTO_CORRESP_TEMPL_ID DECIMAL(12)
        , PROTO_CORRESP_TYPE_CODE VARCHAR(3) NOT NULL
        , COMMITTEE_ID VARCHAR(15) NOT NULL
        , FILE_NAME VARCHAR(150) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , CORRESPONDENCE_TEMPLATE LONGBLOB
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTO_CORRESP_TEMPLP1 PRIMARY KEY(PROTO_CORRESP_TEMPL_ID)

    , CONSTRAINT UQ_PROTO_CORRESP_TEMPL UNIQUE (PROTO_CORRESP_TYPE_CODE, COMMITTEE_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# PROTO_CORRESP_TYPE
# -----------------------------------------------------------------------
drop table if exists PROTO_CORRESP_TYPE
/

CREATE TABLE PROTO_CORRESP_TYPE
(
      PROTO_CORRESP_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , MODULE_ID VARCHAR(1) default 'Y' NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT PROTO_CORRESP_TYPEP1 PRIMARY KEY(PROTO_CORRESP_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTION
# -----------------------------------------------------------------------
drop table if exists QUESTION
/

CREATE TABLE QUESTION
(
      QUESTION_REF_ID DECIMAL(12)
        , QUESTION_ID DECIMAL(6) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , SEQUENCE_STATUS CHAR(1) NOT NULL
        , QUESTION VARCHAR(2000) NOT NULL
        , STATUS CHAR(1) NOT NULL
        , GROUP_TYPE_CODE DECIMAL(3) NOT NULL
        , QUESTION_TYPE_ID DECIMAL(12) NOT NULL
        , LOOKUP_CLASS VARCHAR(100)
        , LOOKUP_RETURN VARCHAR(30)
        , DISPLAYED_ANSWERS DECIMAL(2)
        , MAX_ANSWERS DECIMAL(2)
        , ANSWER_MAX_LENGTH DECIMAL(4)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONP1 PRIMARY KEY(QUESTION_REF_ID)


    , INDEX UQ_QUESTION_ID (QUESTION_ID, SEQUENCE_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTIONNAIRE
# -----------------------------------------------------------------------
drop table if exists QUESTIONNAIRE
/

CREATE TABLE QUESTIONNAIRE
(
      QUESTIONNAIRE_REF_ID DECIMAL(12)
        , QUESTIONNAIRE_ID DECIMAL(6) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , NAME VARCHAR(50) NOT NULL
        , DESCRIPTION VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , IS_FINAL VARCHAR(1) NOT NULL
        , DOCUMENT_NUMBER DECIMAL(10)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONNAIREP1 PRIMARY KEY(QUESTIONNAIRE_REF_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTIONNAIRE_ANSWER
# -----------------------------------------------------------------------
drop table if exists QUESTIONNAIRE_ANSWER
/

CREATE TABLE QUESTIONNAIRE_ANSWER
(
      QUESTIONNAIRE_ANSWER_ID DECIMAL(12)
        , QUESTIONNAIRE_AH_ID_FK DECIMAL(12) NOT NULL
        , QUESTION_REF_ID_FK DECIMAL(12) NOT NULL
        , QUESTION_NUMBER DECIMAL(6) NOT NULL
        , ANSWER_NUMBER DECIMAL(3) NOT NULL
        , ANSWER VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , QUESTIONNAIRE_QUESTIONS_ID_FK DECIMAL(12) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONNAIRE_ANSWERP1 PRIMARY KEY(QUESTIONNAIRE_ANSWER_ID)


    , INDEX UQ_QUESTIONNAIRE_ANSWER (QUESTIONNAIRE_AH_ID_FK, QUESTION_NUMBER, ANSWER_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTIONNAIRE_ANSWER_HEADER
# -----------------------------------------------------------------------
drop table if exists QUESTIONNAIRE_ANSWER_HEADER
/

CREATE TABLE QUESTIONNAIRE_ANSWER_HEADER
(
      QUESTIONNAIRE_ANSWER_HEADER_ID DECIMAL(12)
        , QUESTIONNAIRE_REF_ID_FK DECIMAL(12) NOT NULL
        , MODULE_ITEM_CODE VARCHAR(3) NOT NULL
        , MODULE_ITEM_KEY VARCHAR(20) NOT NULL
        , MODULE_SUB_ITEM_CODE DECIMAL(3) NOT NULL
        , MODULE_SUB_ITEM_KEY VARCHAR(20) NOT NULL
        , QUESTIONNAIRE_COMPLETED_FLAG VARCHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONNAIRE_ANSWER_HEADERP1 PRIMARY KEY(QUESTIONNAIRE_ANSWER_HEADER_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTIONNAIRE_QUESTIONS
# -----------------------------------------------------------------------
drop table if exists QUESTIONNAIRE_QUESTIONS
/

CREATE TABLE QUESTIONNAIRE_QUESTIONS
(
      QUESTIONNAIRE_QUESTIONS_ID DECIMAL(12)
        , QUESTIONNAIRE_REF_ID_FK DECIMAL(12) NOT NULL
        , QUESTION_REF_ID_FK DECIMAL(12) NOT NULL
        , QUESTION_NUMBER DECIMAL(6) NOT NULL
        , PARENT_QUESTION_NUMBER DECIMAL(6) NOT NULL
        , CONDITION_FLAG VARCHAR(1) NOT NULL
        , CONDITION_TYPE VARCHAR(50)
        , CONDITION_VALUE VARCHAR(2000)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , QUESTION_SEQ_NUMBER DECIMAL(3)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONNAIRE_QUESTIONSP1 PRIMARY KEY(QUESTIONNAIRE_QUESTIONS_ID)


    , INDEX UQ_QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_REF_ID_FK, QUESTION_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTIONNAIRE_USAGE
# -----------------------------------------------------------------------
drop table if exists QUESTIONNAIRE_USAGE
/

CREATE TABLE QUESTIONNAIRE_USAGE
(
      QUESTIONNAIRE_USAGE_ID DECIMAL(12)
        , MODULE_ITEM_CODE DECIMAL(3) NOT NULL
        , MODULE_SUB_ITEM_CODE DECIMAL(3) NOT NULL
        , QUESTIONNAIRE_REF_ID_FK DECIMAL(12) NOT NULL
        , QUESTIONNAIRE_SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , RULE_ID DECIMAL(6)
        , QUESTIONNAIRE_LABEL VARCHAR(50)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTIONNAIRE_USAGEP1 PRIMARY KEY(QUESTIONNAIRE_USAGE_ID)


    , INDEX UQ_QUESTIONNAIRE_USAGE (MODULE_ITEM_CODE, MODULE_SUB_ITEM_CODE, QUESTIONNAIRE_REF_ID_FK, QUESTIONNAIRE_SEQUENCE_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTION_EXPLANATION
# -----------------------------------------------------------------------
drop table if exists QUESTION_EXPLANATION
/

CREATE TABLE QUESTION_EXPLANATION
(
      QUESTION_EXPLANATION_ID DECIMAL(12)
        , QUESTION_REF_ID_FK DECIMAL(12) NOT NULL
        , EXPLANATION_TYPE CHAR(1) NOT NULL
        , EXPLANATION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTION_EXPLANATIONP1 PRIMARY KEY(QUESTION_EXPLANATION_ID)


    , INDEX UQ_QUESTION_EXPLANATION (QUESTION_REF_ID_FK, EXPLANATION_TYPE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# QUESTION_TYPES
# -----------------------------------------------------------------------
drop table if exists QUESTION_TYPES
/

CREATE TABLE QUESTION_TYPES
(
      QUESTION_TYPE_ID DECIMAL(12)
        , QUESTION_TYPE_NAME VARCHAR(30) NOT NULL
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT QUESTION_TYPESP1 PRIMARY KEY(QUESTION_TYPE_ID)


    , INDEX UQ_QUESTION_TYPE (QUESTION_TYPE_NAME)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RATE_CLASS
# -----------------------------------------------------------------------
drop table if exists RATE_CLASS
/

CREATE TABLE RATE_CLASS
(
      RATE_CLASS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , RATE_CLASS_TYPE CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RATE_CLASSP1 PRIMARY KEY(RATE_CLASS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RATE_CLASS_TYPE
# -----------------------------------------------------------------------
drop table if exists RATE_CLASS_TYPE
/

CREATE TABLE RATE_CLASS_TYPE
(
      SORT_ID DECIMAL(2)
        , PREFIX_ACTIVITY_TYPE CHAR(1) default 'N' NOT NULL
        , RATE_CLASS_TYPE CHAR(1)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RATE_CLASS_TYPEP1 PRIMARY KEY(RATE_CLASS_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RATE_TYPE
# -----------------------------------------------------------------------
drop table if exists RATE_TYPE
/

CREATE TABLE RATE_TYPE
(
      RATE_CLASS_CODE VARCHAR(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RATE_TYPEP1 PRIMARY KEY(RATE_CLASS_CODE,RATE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# REPORT
# -----------------------------------------------------------------------
drop table if exists REPORT
/

CREATE TABLE REPORT
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , REPORT_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , FINAL_REPORT_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT REPORTP1 PRIMARY KEY(REPORT_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# REPORT_CLASS
# -----------------------------------------------------------------------
drop table if exists REPORT_CLASS
/

CREATE TABLE REPORT_CLASS
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , REPORT_CLASS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , GENERATE_REPORT_REQUIREMENTS VARCHAR(1) default 'N' NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT REPORT_CLASSP1 PRIMARY KEY(REPORT_CLASS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RESEARCH_AREAS
# -----------------------------------------------------------------------
drop table if exists RESEARCH_AREAS
/

CREATE TABLE RESEARCH_AREAS
(
      RESEARCH_AREA_CODE VARCHAR(8)
        , PARENT_RESEARCH_AREA_CODE VARCHAR(8) NOT NULL
        , HAS_CHILDREN_FLAG VARCHAR(1) NOT NULL
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RESEARCH_AREASP1 PRIMARY KEY(RESEARCH_AREA_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RIGHTS
# -----------------------------------------------------------------------
drop table if exists RIGHTS
/

CREATE TABLE RIGHTS
(
      RIGHT_ID VARCHAR(30)
        , DESCRIPTION VARCHAR(80) NOT NULL
        , RIGHT_TYPE CHAR(1) NOT NULL
        , DESCEND_FLAG CHAR(1) NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RIGHTSP1 PRIMARY KEY(RIGHT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# RISK_LEVEL
# -----------------------------------------------------------------------
drop table if exists RISK_LEVEL
/

CREATE TABLE RISK_LEVEL
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , RISK_LEVEL_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT RISK_LEVELP1 PRIMARY KEY(RISK_LEVEL_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ROLE
# -----------------------------------------------------------------------
drop table if exists ROLE
/

CREATE TABLE ROLE
(
      ROLE_ID DECIMAL(5)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , ROLE_NAME VARCHAR(50) NOT NULL
        , ROLE_TYPE CHAR(1) NOT NULL
        , OWNED_BY_UNIT VARCHAR(8) NOT NULL
        , DESCEND_FLAG CHAR(1) NOT NULL
        , STATUS_FLAG CHAR(1) NOT NULL
        , CREATE_TIMESTAMP DATETIME NOT NULL
        , CREATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ROLEP1 PRIMARY KEY(ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ROLE_RIGHTS
# -----------------------------------------------------------------------
drop table if exists ROLE_RIGHTS
/

CREATE TABLE ROLE_RIGHTS
(
      RIGHT_ID VARCHAR(30)
        , ROLE_ID DECIMAL(5)
        , DESCEND_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ROLE_RIGHTSP1 PRIMARY KEY(RIGHT_ID,ROLE_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# ROLODEX
# -----------------------------------------------------------------------
drop table if exists ROLODEX
/

CREATE TABLE ROLODEX
(
      ROLODEX_ID DECIMAL(6)
        , LAST_NAME VARCHAR(20)
        , FIRST_NAME VARCHAR(20)
        , MIDDLE_NAME VARCHAR(20)
        , SUFFIX VARCHAR(10)
        , PREFIX VARCHAR(10)
        , TITLE VARCHAR(35)
        , ORGANIZATION VARCHAR(80) NOT NULL
        , ADDRESS_LINE_1 VARCHAR(80)
        , ADDRESS_LINE_2 VARCHAR(80)
        , ADDRESS_LINE_3 VARCHAR(80)
        , FAX_NUMBER VARCHAR(20)
        , EMAIL_ADDRESS VARCHAR(60)
        , CITY VARCHAR(30)
        , COUNTY VARCHAR(30)
        , STATE VARCHAR(30)
        , POSTAL_CODE VARCHAR(15)
        , COMMENTS VARCHAR(300)
        , PHONE_NUMBER VARCHAR(20)
        , COUNTRY_CODE CHAR(3)
        , SPONSOR_CODE CHAR(6)
        , OWNED_BY_UNIT VARCHAR(8) NOT NULL
        , SPONSOR_ADDRESS_FLAG CHAR(1) NOT NULL
        , DELETE_FLAG CHAR(1)
        , CREATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT ROLODEXP1 PRIMARY KEY(ROLODEX_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_APPLICATION
# -----------------------------------------------------------------------
drop table if exists S2S_APPLICATION
/

CREATE TABLE S2S_APPLICATION
(
      PROPOSAL_NUMBER VARCHAR(8)
        , APPLICATION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_APPLICATIONP1 PRIMARY KEY(PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_APP_ATTACHMENTS
# -----------------------------------------------------------------------
drop table if exists S2S_APP_ATTACHMENTS
/

CREATE TABLE S2S_APP_ATTACHMENTS
(
      CONTENT_ID VARCHAR(300) NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8) NOT NULL
        , HASH_CODE VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , CONTENT_TYPE VARCHAR(250)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , S2S_APP_ATTACHMENT_ID DECIMAL(12)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_APP_ATTACHMENTSP1 PRIMARY KEY(S2S_APP_ATTACHMENT_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_APP_SUBMISSION
# -----------------------------------------------------------------------
drop table if exists S2S_APP_SUBMISSION
/

CREATE TABLE S2S_APP_SUBMISSION
(
      SUBMISSION_NUMBER DECIMAL(3)
        , COMMENTS VARCHAR(2000)
        , STATUS VARCHAR(50)
        , GG_TRACKING_ID VARCHAR(50)
        , AGENCY_TRACKING_ID VARCHAR(50)
        , RECEIVED_DATE DATETIME
        , LAST_MODIFIED_DATE DATETIME
        , LAST_NOTIFIED_DATE DATETIME
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PROPOSAL_NUMBER VARCHAR(8)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_APP_SUBMISSIONP1 PRIMARY KEY(SUBMISSION_NUMBER,PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_OPPORTUNITY
# -----------------------------------------------------------------------
drop table if exists S2S_OPPORTUNITY
/

CREATE TABLE S2S_OPPORTUNITY
(
      PROPOSAL_NUMBER VARCHAR(8)
        , OPPORTUNITY_TITLE VARCHAR(255)
        , COMPETETION_ID VARCHAR(50)
        , OPENING_DATE DATETIME
        , CLOSING_DATE DATETIME
        , SCHEMA_URL VARCHAR(200)
        , INSTRUCTION_URL VARCHAR(200)
        , OPPORTUNITY_ID VARCHAR(50)
        , CFDA_NUMBER VARCHAR(6)
        , S2S_SUBMISSION_TYPE_CODE VARCHAR(3) default '1'
        , REVISION_CODE VARCHAR(2)
        , REVISION_OTHER_DESCRIPTION VARCHAR(45)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , OPPORTUNITY LONGTEXT NOT NULL
    
    , CONSTRAINT S2S_OPPORTUNITYP1 PRIMARY KEY(PROPOSAL_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_OPP_FORMS
# -----------------------------------------------------------------------
drop table if exists S2S_OPP_FORMS
/

CREATE TABLE S2S_OPP_FORMS
(
      PROPOSAL_NUMBER VARCHAR(8)
        , OPP_NAME_SPACE VARCHAR(200)
        , FORM_NAME VARCHAR(100)
        , MANDATORY VARCHAR(1) default 'N'
        , AVAILABLE VARCHAR(1) default 'Y'
        , INCLUDE VARCHAR(1) default 'Y'
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_OPP_FORMSP1 PRIMARY KEY(PROPOSAL_NUMBER,OPP_NAME_SPACE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_REVISION_TYPE
# -----------------------------------------------------------------------
drop table if exists S2S_REVISION_TYPE
/

CREATE TABLE S2S_REVISION_TYPE
(
      S2S_REVISION_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_REVISION_TYPEP1 PRIMARY KEY(S2S_REVISION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# S2S_SUBMISSION_TYPE
# -----------------------------------------------------------------------
drop table if exists S2S_SUBMISSION_TYPE
/

CREATE TABLE S2S_SUBMISSION_TYPE
(
      SORT_ID DECIMAL(2)
        , S2S_SUBMISSION_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT S2S_SUBMISSION_TYPEP1 PRIMARY KEY(S2S_SUBMISSION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SCHEDULE_ACT_ITEM_TYPE
# -----------------------------------------------------------------------
drop table if exists SCHEDULE_ACT_ITEM_TYPE
/

CREATE TABLE SCHEDULE_ACT_ITEM_TYPE
(
      SCHEDULE_ACT_ITEM_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SCHEDULE_ACT_ITEM_TYPEP1 PRIMARY KEY(SCHEDULE_ACT_ITEM_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SCHEDULE_AGENDA
# -----------------------------------------------------------------------
drop table if exists SCHEDULE_AGENDA
/

CREATE TABLE SCHEDULE_AGENDA
(
      SCHEDULE_AGENDA_ID DECIMAL(12)
        , SCHEDULE_ID_FK DECIMAL(12) NOT NULL
        , AGENDA_NUMBER DECIMAL(4) NOT NULL
        , AGENDA_NAME VARCHAR(200) NOT NULL
        , CREATE_TIMESTAMP DATETIME NOT NULL
        , CREATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , PDF_STORE LONGBLOB
    
    , CONSTRAINT SCHEDULE_AGENDAP1 PRIMARY KEY(SCHEDULE_AGENDA_ID)


    , INDEX UQ_COMM_SCHEDULE_AGENDA (SCHEDULE_ID_FK, AGENDA_NUMBER)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SCHEDULE_STATUS
# -----------------------------------------------------------------------
drop table if exists SCHEDULE_STATUS
/

CREATE TABLE SCHEDULE_STATUS
(
      SCHEDULE_STATUS_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SCHEDULE_STATUSP1 PRIMARY KEY(SCHEDULE_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SCHOOL_CODE
# -----------------------------------------------------------------------
drop table if exists SCHOOL_CODE
/

CREATE TABLE SCHOOL_CODE
(
      SCHOOL_CODE DECIMAL(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SCHOOL_CODEP1 PRIMARY KEY(SCHOOL_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SCIENCE_KEYWORD
# -----------------------------------------------------------------------
drop table if exists SCIENCE_KEYWORD
/

CREATE TABLE SCIENCE_KEYWORD
(
      SCIENCE_KEYWORD_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SCIENCE_KEYWORDP1 PRIMARY KEY(SCIENCE_KEYWORD_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPECIAL_REVIEW
# -----------------------------------------------------------------------
drop table if exists SPECIAL_REVIEW
/

CREATE TABLE SPECIAL_REVIEW
(
      SPECIAL_REVIEW_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPECIAL_REVIEWP1 PRIMARY KEY(SPECIAL_REVIEW_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR
# -----------------------------------------------------------------------
drop table if exists SPONSOR
/

CREATE TABLE SPONSOR
(
      SPONSOR_CODE CHAR(6)
        , SPONSOR_NAME VARCHAR(60) NOT NULL
        , ACRONYM VARCHAR(10)
        , SPONSOR_TYPE_CODE VARCHAR(3) NOT NULL
        , DUN_AND_BRADSTREET_NUMBER VARCHAR(20)
        , DUNS_PLUS_FOUR_NUMBER VARCHAR(20)
        , DODAC_NUMBER VARCHAR(20)
        , CAGE_NUMBER VARCHAR(20)
        , POSTAL_CODE VARCHAR(15)
        , STATE VARCHAR(30)
        , COUNTRY_CODE CHAR(3)
        , ROLODEX_ID DECIMAL(6) NOT NULL
        , AUDIT_REPORT_SENT_FOR_FY CHAR(4)
        , OWNED_BY_UNIT VARCHAR(8) NOT NULL
        , CREATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSORP1 PRIMARY KEY(SPONSOR_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_FORMS
# -----------------------------------------------------------------------
drop table if exists SPONSOR_FORMS
/

CREATE TABLE SPONSOR_FORMS
(
      SPONSOR_CODE CHAR(6)
        , PACKAGE_NUMBER DECIMAL(3)
        , PACKAGE_NAME VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_FORMSP1 PRIMARY KEY(SPONSOR_CODE,PACKAGE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_FORM_TEMPLATES
# -----------------------------------------------------------------------
drop table if exists SPONSOR_FORM_TEMPLATES
/

CREATE TABLE SPONSOR_FORM_TEMPLATES
(
      SPONSOR_CODE CHAR(6)
        , PACKAGE_NUMBER DECIMAL(3)
        , PAGE_NUMBER DECIMAL(3)
        , PAGE_DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FILE_NAME VARCHAR(150)
        , CONTENT_TYPE VARCHAR(250)
        , FORM_TEMPLATE LONGTEXT
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_FORM_TEMPLATESP1 PRIMARY KEY(SPONSOR_CODE,PACKAGE_NUMBER,PAGE_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_HIERARCHY
# -----------------------------------------------------------------------
drop table if exists SPONSOR_HIERARCHY
/

CREATE TABLE SPONSOR_HIERARCHY
(
      HIERARCHY_NAME VARCHAR(100)
        , SPONSOR_CODE CHAR(6)
        , LEVEL1 VARCHAR(50)
        , LEVEL2 VARCHAR(50)
        , LEVEL3 VARCHAR(50)
        , LEVEL4 VARCHAR(50)
        , LEVEL5 VARCHAR(50)
        , LEVEL6 VARCHAR(50)
        , LEVEL7 VARCHAR(50)
        , LEVEL8 VARCHAR(50)
        , LEVEL9 VARCHAR(50)
        , LEVEL10 VARCHAR(50)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , LEVEL1_SORTID DECIMAL(4)
        , LEVEL2_SORTID DECIMAL(4)
        , LEVEL3_SORTID DECIMAL(4)
        , LEVEL4_SORTID DECIMAL(4)
        , LEVEL5_SORTID DECIMAL(4)
        , LEVEL6_SORTID DECIMAL(4)
        , LEVEL7_SORTID DECIMAL(4)
        , LEVEL8_SORTID DECIMAL(4)
        , LEVEL9_SORTID DECIMAL(4)
        , LEVEL10_SORTID DECIMAL(4)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_HIERARCHYP1 PRIMARY KEY(HIERARCHY_NAME,SPONSOR_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_HIERARCHY_MT
# -----------------------------------------------------------------------
drop table if exists SPONSOR_HIERARCHY_MT
/

CREATE TABLE SPONSOR_HIERARCHY_MT
(
      HIERARCHY_NAME VARCHAR(150)
        , SPONSOR_CODE CHAR(6)
        , LEVEL1 VARCHAR(50)
        , LEVEL2 VARCHAR(50)
        , LEVEL3 VARCHAR(50)
        , LEVEL4 VARCHAR(50)
        , LEVEL5 VARCHAR(50)
        , LEVEL6 VARCHAR(50)
        , LEVEL7 VARCHAR(50)
        , LEVEL8 VARCHAR(50)
        , LEVEL9 VARCHAR(50)
        , LEVEL10 VARCHAR(50)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , LEVEL1_SORTID DECIMAL(4)
        , LEVEL2_SORTID DECIMAL(4)
        , LEVEL3_SORTID DECIMAL(4)
        , LEVEL4_SORTID DECIMAL(4)
        , LEVEL5_SORTID DECIMAL(4)
        , LEVEL6_SORTID DECIMAL(4)
        , LEVEL7_SORTID DECIMAL(4)
        , LEVEL8_SORTID DECIMAL(4)
        , LEVEL9_SORTID DECIMAL(4)
        , LEVEL10_SORTID DECIMAL(4)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_HIERARCHY_MTP1 PRIMARY KEY(HIERARCHY_NAME,SPONSOR_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_TERM
# -----------------------------------------------------------------------
drop table if exists SPONSOR_TERM
/

CREATE TABLE SPONSOR_TERM
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , SPONSOR_TERM_ID DECIMAL(12)
        , SPONSOR_TERM_CODE VARCHAR(3) NOT NULL
        , SPONSOR_TERM_TYPE_CODE VARCHAR(3) NOT NULL
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_TERMP1 PRIMARY KEY(SPONSOR_TERM_ID)


    , INDEX U_SPONSOR_TERM (SPONSOR_TERM_CODE, SPONSOR_TERM_TYPE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_TERM_TYPE
# -----------------------------------------------------------------------
drop table if exists SPONSOR_TERM_TYPE
/

CREATE TABLE SPONSOR_TERM_TYPE
(
      VER_NBR DECIMAL(8) default 1 NOT NULL
        , SPONSOR_TERM_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_TERM_TYPEP1 PRIMARY KEY(SPONSOR_TERM_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SPONSOR_TYPE
# -----------------------------------------------------------------------
drop table if exists SPONSOR_TYPE
/

CREATE TABLE SPONSOR_TYPE
(
      SPONSOR_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(100) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SPONSOR_TYPEP1 PRIMARY KEY(SPONSOR_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SP_REV_APPROVAL_TYPE
# -----------------------------------------------------------------------
drop table if exists SP_REV_APPROVAL_TYPE
/

CREATE TABLE SP_REV_APPROVAL_TYPE
(
      APPROVAL_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SP_REV_APPROVAL_TYPEP1 PRIMARY KEY(APPROVAL_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# STATE_CODE
# -----------------------------------------------------------------------
drop table if exists STATE_CODE
/

CREATE TABLE STATE_CODE
(
      COUNTRY_CODE CHAR(3)
        , STATE_CODE VARCHAR(15)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT STATE_CODEP1 PRIMARY KEY(COUNTRY_CODE,STATE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SUBMISSION_HISTORY
# -----------------------------------------------------------------------
drop table if exists SUBMISSION_HISTORY
/

CREATE TABLE SUBMISSION_HISTORY
(
      ID DECIMAL(22)
        , PROPOSAL_NUMBER VARCHAR(8)
        , PROPOSAL_NUMBER_ORIG VARCHAR(8)
        , ORIGINAL_PROPOSAL_ID VARCHAR(8)
        , SUBMITTED_BY VARCHAR(60)
        , S2S_SUBMISSION_TYPE_CODE VARCHAR(3)
        , S2S_REVISION_TYPE_CODE VARCHAR(3)
        , SUBMISSION_TIMESTAMP DATETIME
        , FEDERAL_IDENTIFIER VARCHAR(50)
        , UPDATE_TIMESTAMP DATETIME
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SUBMISSION_HISTORYP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SUBMISSION_STATUS
# -----------------------------------------------------------------------
drop table if exists SUBMISSION_STATUS
/

CREATE TABLE SUBMISSION_STATUS
(
      SUBMISSION_STATUS_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SUBMISSION_STATUSP1 PRIMARY KEY(SUBMISSION_STATUS_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SUBMISSION_TYPE
# -----------------------------------------------------------------------
drop table if exists SUBMISSION_TYPE
/

CREATE TABLE SUBMISSION_TYPE
(
      SUBMISSION_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SUBMISSION_TYPEP1 PRIMARY KEY(SUBMISSION_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# SUBMISSION_TYPE_QUALIFIER
# -----------------------------------------------------------------------
drop table if exists SUBMISSION_TYPE_QUALIFIER
/

CREATE TABLE SUBMISSION_TYPE_QUALIFIER
(
      SUBMISSION_TYPE_QUAL_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT SUBMISSION_TYPE_QUALIFIERP1 PRIMARY KEY(SUBMISSION_TYPE_QUAL_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TBN
# -----------------------------------------------------------------------
drop table if exists TBN
/

CREATE TABLE TBN
(
      TBN_ID VARCHAR(9)
        , PERSON_NAME VARCHAR(90) NOT NULL
        , JOB_CODE VARCHAR(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT TBNP1 PRIMARY KEY(TBN_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TIME_AND_MONEY_DOCUMENT
# -----------------------------------------------------------------------
drop table if exists TIME_AND_MONEY_DOCUMENT
/

CREATE TABLE TIME_AND_MONEY_DOCUMENT
(
      DOCUMENT_NUMBER VARCHAR(10)
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT TIME_AND_MONEY_DOCUMENTP1 PRIMARY KEY(DOCUMENT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRAINING
# -----------------------------------------------------------------------
drop table if exists TRAINING
/

CREATE TABLE TRAINING
(
      TRAINING_CODE DECIMAL(4)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT TRAININGP1 PRIMARY KEY(TRAINING_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRANSACTION_DETAILS
# -----------------------------------------------------------------------
drop table if exists TRANSACTION_DETAILS
/

CREATE TABLE TRANSACTION_DETAILS
(
      TRANSACTION_DETAIL_ID DECIMAL(10)
        , AWARD_NUMBER VARCHAR(12) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , TRANSACTION_ID DECIMAL(10) NOT NULL
        , TNM_DOCUMENT_NUMBER VARCHAR(10) NOT NULL
        , COMMENTS VARCHAR(200)
        , SOURCE_AWARD_NUMBER VARCHAR(12) NOT NULL
        , DESTINATION_AWARD_NUMBER VARCHAR(12) NOT NULL
        , OBLIGATED_AMOUNT DECIMAL(12,2)
        , ANTICIPATED_AMOUNT DECIMAL(12,2)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , TRANSACTION_DETAIL_TYPE VARCHAR(12)
    
    , CONSTRAINT TRANSACTION_DETAILSP1 PRIMARY KEY(TRANSACTION_DETAIL_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRAV_DOC_2_ACCOUNTS
# -----------------------------------------------------------------------
drop table if exists TRAV_DOC_2_ACCOUNTS
/

CREATE TABLE TRAV_DOC_2_ACCOUNTS
(
      FDOC_NBR VARCHAR(14)
        , ACCT_NUM VARCHAR(10)
    
    , CONSTRAINT TRAV_DOC_2_ACCOUNTSP1 PRIMARY KEY(FDOC_NBR,ACCT_NUM)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_ACCT
# -----------------------------------------------------------------------
drop table if exists TRV_ACCT
/

CREATE TABLE TRV_ACCT
(
      ACCT_NUM VARCHAR(10)
        , ACCT_NAME VARCHAR(50)
        , ACCT_TYPE VARCHAR(100)
        , ACCT_FO_ID DECIMAL(14)
    
    , CONSTRAINT TRV_ACCTP1 PRIMARY KEY(ACCT_NUM)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_ACCT_EXT
# -----------------------------------------------------------------------
drop table if exists TRV_ACCT_EXT
/

CREATE TABLE TRV_ACCT_EXT
(
      ACCT_NUM VARCHAR(10)
        , ACCT_TYPE VARCHAR(100)
    
    , CONSTRAINT TRV_ACCT_EXTP1 PRIMARY KEY(ACCT_NUM,ACCT_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_ACCT_FO
# -----------------------------------------------------------------------
drop table if exists TRV_ACCT_FO
/

CREATE TABLE TRV_ACCT_FO
(
      ACCT_FO_ID DECIMAL(14)
        , ACCT_FO_USER_NAME VARCHAR(50) NOT NULL
    
    , CONSTRAINT TRV_ACCT_FOP1 PRIMARY KEY(ACCT_FO_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_ACCT_TYPE
# -----------------------------------------------------------------------
drop table if exists TRV_ACCT_TYPE
/

CREATE TABLE TRV_ACCT_TYPE
(
      ACCT_TYPE VARCHAR(10)
        , ACCT_TYPE_NAME VARCHAR(50)
    
    , CONSTRAINT TRV_ACCT_TYPEP1 PRIMARY KEY(ACCT_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_DOC_2
# -----------------------------------------------------------------------
drop table if exists TRV_DOC_2
/

CREATE TABLE TRV_DOC_2
(
      FDOC_NBR VARCHAR(14)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FDOC_EXPLAIN_TXT VARCHAR(400)
        , REQUEST_TRAV VARCHAR(30) NOT NULL
        , TRAVELER VARCHAR(200)
        , ORG VARCHAR(60)
        , DEST VARCHAR(60)
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT TRV_DOC_2P1 PRIMARY KEY(FDOC_NBR)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TRV_DOC_ACCT
# -----------------------------------------------------------------------
drop table if exists TRV_DOC_ACCT
/

CREATE TABLE TRV_DOC_ACCT
(
      DOC_HDR_ID DECIMAL(14)
        , ACCT_NUM VARCHAR(10)
    
    , CONSTRAINT TRV_DOC_ACCTP1 PRIMARY KEY(DOC_HDR_ID,ACCT_NUM)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# TST_SEARCH_ATTR_INDX_TST_DOC_T
# -----------------------------------------------------------------------
drop table if exists TST_SEARCH_ATTR_INDX_TST_DOC_T
/

CREATE TABLE TST_SEARCH_ATTR_INDX_TST_DOC_T
(
      DOC_HDR_ID VARCHAR(14)
        , OBJ_ID VARCHAR(36)
        , VER_NBR DECIMAL(14)
        , RTE_LVL_CNT DECIMAL(14)
        , CNSTNT_STR VARCHAR(50)
        , RTD_STR VARCHAR(50)
        , HLD_RTD_STR VARCHAR(50)
        , RD_ACCS_CNT DECIMAL(14)
    
    , CONSTRAINT TST_SEARCH_ATTR_INDX_TST_DOP1 PRIMARY KEY(DOC_HDR_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# UNIT
# -----------------------------------------------------------------------
drop table if exists UNIT
/

CREATE TABLE UNIT
(
      UNIT_NUMBER VARCHAR(8)
        , UNIT_NAME VARCHAR(60)
        , ORGANIZATION_ID VARCHAR(8)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PARENT_UNIT_NUMBER VARCHAR(8)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT UNITP1 PRIMARY KEY(UNIT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# UNIT_ADMINISTRATOR
# -----------------------------------------------------------------------
drop table if exists UNIT_ADMINISTRATOR
/

CREATE TABLE UNIT_ADMINISTRATOR
(
      UNIT_NUMBER VARCHAR(8)
        , PERSON_ID VARCHAR(40)
        , UNIT_ADMINISTRATOR_TYPE_CODE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT UNIT_ADMINISTRATORP1 PRIMARY KEY(UNIT_NUMBER,PERSON_ID,UNIT_ADMINISTRATOR_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# UNIT_ADMINISTRATOR_TYPE
# -----------------------------------------------------------------------
drop table if exists UNIT_ADMINISTRATOR_TYPE
/

CREATE TABLE UNIT_ADMINISTRATOR_TYPE
(
      UNIT_ADMINISTRATOR_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DEFAULT_GROUP_FLAG VARCHAR(1)
        , MULTIPLES_FLAG VARCHAR(1)
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT UNIT_ADMINISTRATOR_TYPEP1 PRIMARY KEY(UNIT_ADMINISTRATOR_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# USER_ROLES
# -----------------------------------------------------------------------
drop table if exists USER_ROLES
/

CREATE TABLE USER_ROLES
(
      USER_ID VARCHAR(10)
        , ROLE_ID DECIMAL(5)
        , UNIT_NUMBER VARCHAR(8)
        , DESCEND_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT USER_ROLESP1 PRIMARY KEY(USER_ID,ROLE_ID,UNIT_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# USER_TABLES_TEMP
# -----------------------------------------------------------------------
drop table if exists USER_TABLES_TEMP
/

CREATE TABLE USER_TABLES_TEMP
(
      TABLE_NAME VARCHAR(30) NOT NULL
        , USER_KEY CHAR(1)
    



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_AWARD_BASIS_PAYMENT
# -----------------------------------------------------------------------
drop table if exists VALID_AWARD_BASIS_PAYMENT
/

CREATE TABLE VALID_AWARD_BASIS_PAYMENT
(
      VALID_AWARD_BASIS_PAYMENT_ID DECIMAL(12)
        , AWARD_TYPE_CODE DECIMAL(3) NOT NULL
        , BASIS_OF_PAYMENT_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_AWARD_BASIS_PAYMENTP1 PRIMARY KEY(VALID_AWARD_BASIS_PAYMENT_ID)


    , INDEX UQ_VALID_AWARD_BASIS_PAYMENT (AWARD_TYPE_CODE, BASIS_OF_PAYMENT_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_BASIS_METHOD_PMT
# -----------------------------------------------------------------------
drop table if exists VALID_BASIS_METHOD_PMT
/

CREATE TABLE VALID_BASIS_METHOD_PMT
(
      VALID_BASIS_METHOD_PMT_ID DECIMAL(12)
        , BASIS_OF_PAYMENT_CODE VARCHAR(3) NOT NULL
        , METHOD_OF_PAYMENT_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_INDICATOR CHAR(1) NOT NULL
        , INV_INSTRUCTIONS_INDICATOR CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_BASIS_METHOD_PMTP1 PRIMARY KEY(VALID_BASIS_METHOD_PMT_ID)


    , INDEX UQ_VALID_BASIS_METHOD_PMT (BASIS_OF_PAYMENT_CODE, METHOD_OF_PAYMENT_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_CALC_TYPES
# -----------------------------------------------------------------------
drop table if exists VALID_CALC_TYPES
/

CREATE TABLE VALID_CALC_TYPES
(
      CALC_TYPE_ID VARCHAR(8)
        , RATE_CLASS_TYPE CHAR(1)
        , DEPENDENT_SEQ_NUMBER DECIMAL(3)
        , DEPENDENT_RATE_CLASS_TYPE CHAR(1)
        , RATE_CLASS_CODE VARCHAR(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_CALC_TYPESP1 PRIMARY KEY(CALC_TYPE_ID,RATE_CLASS_TYPE,DEPENDENT_SEQ_NUMBER)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_CE_JOB_CODES
# -----------------------------------------------------------------------
drop table if exists VALID_CE_JOB_CODES
/

CREATE TABLE VALID_CE_JOB_CODES
(
      COST_ELEMENT VARCHAR(8)
        , JOB_CODE VARCHAR(6)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_CE_JOB_CODESP1 PRIMARY KEY(COST_ELEMENT,JOB_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_CE_RATE_TYPES
# -----------------------------------------------------------------------
drop table if exists VALID_CE_RATE_TYPES
/

CREATE TABLE VALID_CE_RATE_TYPES
(
      ACTIVE_FLAG VARCHAR(1) default 'Y' NOT NULL
        , COST_ELEMENT VARCHAR(8)
        , RATE_CLASS_CODE VARCHAR(3)
        , RATE_TYPE_CODE VARCHAR(3)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_CE_RATE_TYPESP1 PRIMARY KEY(COST_ELEMENT,RATE_CLASS_CODE,RATE_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_CLASS_REPORT_FREQ
# -----------------------------------------------------------------------
drop table if exists VALID_CLASS_REPORT_FREQ
/

CREATE TABLE VALID_CLASS_REPORT_FREQ
(
      VALID_CLASS_REPORT_FREQ_ID DECIMAL(12)
        , REPORT_CLASS_CODE VARCHAR(3) NOT NULL
        , REPORT_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_CLASS_REPORT_FREQP1 PRIMARY KEY(VALID_CLASS_REPORT_FREQ_ID)


    , INDEX UQ_VALID_CLASS_REPORT_FREQ (REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_FREQUENCY_BASE
# -----------------------------------------------------------------------
drop table if exists VALID_FREQUENCY_BASE
/

CREATE TABLE VALID_FREQUENCY_BASE
(
      VALID_FREQUENCY_BASE_ID DECIMAL(12)
        , FREQUENCY_CODE VARCHAR(3) NOT NULL
        , FREQUENCY_BASE_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_FREQUENCY_BASEP1 PRIMARY KEY(VALID_FREQUENCY_BASE_ID)


    , INDEX UQ_VALID_FREQUENCY_BASE (FREQUENCY_CODE, FREQUENCY_BASE_CODE)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_PROTO_ACTION_CORESP
# -----------------------------------------------------------------------
drop table if exists VALID_PROTO_ACTION_CORESP
/

CREATE TABLE VALID_PROTO_ACTION_CORESP
(
      VALID_PROTO_ACTION_CORESP_ID DECIMAL(12)
        , PROTOCOL_ACTION_TYPE_CODE VARCHAR(3) NOT NULL
        , PROTO_CORRESP_TYPE_CODE VARCHAR(3) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , FINAL_FLAG VARCHAR(1)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_PROTO_ACTION_CORESPP1 PRIMARY KEY(VALID_PROTO_ACTION_CORESP_ID)

    , CONSTRAINT UQ_VALID_PROTO_ACTION_CORESP UNIQUE (PROTOCOL_ACTION_TYPE_CODE, PROTO_CORRESP_TYPE_CODE)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_RATES
# -----------------------------------------------------------------------
drop table if exists VALID_RATES
/

CREATE TABLE VALID_RATES
(
      VALID_RATES_ID DECIMAL(12)
        , ON_CAMPUS_RATE DECIMAL(5,2) NOT NULL
        , OFF_CAMPUS_RATE DECIMAL(5,2) NOT NULL
        , RATE_CLASS_TYPE VARCHAR(1) NOT NULL
        , ADJUSTMENT_KEY VARCHAR(6) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_RATESP1 PRIMARY KEY(VALID_RATES_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VALID_SP_REV_APPROVAL
# -----------------------------------------------------------------------
drop table if exists VALID_SP_REV_APPROVAL
/

CREATE TABLE VALID_SP_REV_APPROVAL
(
      EXEMPT_NUMBER_FLAG CHAR(1) NOT NULL
        , SPECIAL_REVIEW_CODE VARCHAR(3)
        , APPROVAL_TYPE_CODE VARCHAR(3)
        , PROTOCOL_NUMBER_FLAG CHAR(1) NOT NULL
        , APPROVAL_DATE_FLAG CHAR(1) NOT NULL
        , APPLICATION_DATE_FLAG CHAR(1) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VALID_SP_REV_APPROVALP1 PRIMARY KEY(SPECIAL_REVIEW_CODE,APPROVAL_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VERSION_HISTORY
# -----------------------------------------------------------------------
drop table if exists VERSION_HISTORY
/

CREATE TABLE VERSION_HISTORY
(
      VERSION_HISTORY_ID DECIMAL(22) NOT NULL
        , SEQ_OWNER_CLASS_NAME VARCHAR(256) NOT NULL
        , SEQ_OWNER_VERSION_NAME_FIELD VARCHAR(32) NOT NULL
        , SEQ_OWNER_VERSION_NAME_VALUE VARCHAR(32) NOT NULL
        , SEQ_OWNER_SEQ_NUMBER DECIMAL(12) NOT NULL
        , VERSION_STATUS VARCHAR(16) NOT NULL
        , VERSION_DATE DATETIME NOT NULL
        , USER_ID VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# VULNERABLE_SUBJECT_TYPE
# -----------------------------------------------------------------------
drop table if exists VULNERABLE_SUBJECT_TYPE
/

CREATE TABLE VULNERABLE_SUBJECT_TYPE
(
      VULNERABLE_SUBJECT_TYPE_CODE VARCHAR(3)
        , DESCRIPTION VARCHAR(200) NOT NULL
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT VULNERABLE_SUBJECT_TYPEP1 PRIMARY KEY(VULNERABLE_SUBJECT_TYPE_CODE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# YNQ
# -----------------------------------------------------------------------
drop table if exists YNQ
/

CREATE TABLE YNQ
(
      QUESTION_ID VARCHAR(4)
        , DESCRIPTION VARCHAR(500) NOT NULL
        , QUESTION_TYPE CHAR(1) NOT NULL
        , NO_OF_ANSWERS DECIMAL(2) NOT NULL
        , EXPLANATION_REQUIRED_FOR VARCHAR(3)
        , DATE_REQUIRED_FOR VARCHAR(3)
        , STATUS CHAR(1) NOT NULL
        , EFFECTIVE_DATE DATETIME NOT NULL
        , GROUP_NAME VARCHAR(150)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT YNQP1 PRIMARY KEY(QUESTION_ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# YNQ_EXPLANATION
# -----------------------------------------------------------------------
drop table if exists YNQ_EXPLANATION
/

CREATE TABLE YNQ_EXPLANATION
(
      QUESTION_ID VARCHAR(4)
        , EXPLANATION_TYPE CHAR(1)
        , EXPLANATION LONGTEXT
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT YNQ_EXPLANATIONP1 PRIMARY KEY(QUESTION_ID,EXPLANATION_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# YNQ_EXPLANATION_TYPE
# -----------------------------------------------------------------------
drop table if exists YNQ_EXPLANATION_TYPE
/

CREATE TABLE YNQ_EXPLANATION_TYPE
(
      EXPLANATION_TYPE CHAR(1)
        , DESCRIPTION VARCHAR(200)
        , UPDATE_TIMESTAMP DATETIME NOT NULL
        , UPDATE_USER VARCHAR(60) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
    
    , CONSTRAINT YNQ_EXPLANATION_TYPEP1 PRIMARY KEY(EXPLANATION_TYPE)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_GRP_MBR_V
# -----------------------------------------------------------------------
drop view if exists KRIM_GRP_MBR_V
/
CREATE VIEW KRIM_GRP_MBR_V AS 
SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, mg.GRP_NM AS mbr_grp_nm
, mg.GRP_ID AS mbr_grp_id
FROM KRIM_GRP_MBR_T gm
LEFT JOIN krim_grp_t g
ON g.GRP_ID = gm.GRP_ID
LEFT OUTER JOIN krim_grp_t mg
ON mg.GRP_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'G'
LEFT OUTER JOIN krim_prncpl_t p
ON p.PRNCPL_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'P'
LEFT OUTER JOIN krim_entity_nm_t en
ON en.ENTITY_ID = p.ENTITY_ID
AND en.DFLT_IND = 'Y'
AND en.ACTV_IND = 'Y'
ORDER BY nmspc_cd, grp_nm, prncpl_nm
 
/


# -----------------------------------------------------------------------
# KRIM_GRP_V
# -----------------------------------------------------------------------
drop view if exists KRIM_GRP_V
/
CREATE VIEW KRIM_GRP_V AS 
SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, t.NM AS grp_typ_nm
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM krim_grp_t g
LEFT OUTER JOIN KRIM_GRP_ATTR_DATA_T d
ON d.grp_id = g.GRP_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_TYP_T t
ON g.KIM_TYP_ID = t.KIM_TYP_ID
 
/


# -----------------------------------------------------------------------
# KRIM_PERM_ATTR_V
# -----------------------------------------------------------------------
drop view if exists KRIM_PERM_ATTR_V
/
CREATE VIEW KRIM_PERM_ATTR_V AS 
SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
ORDER BY tmpl_nmspc_cd, tmpl_nm, perm_nmspc_cd, perm_id, attr_nm
 
/


# -----------------------------------------------------------------------
# KRIM_PERM_V
# -----------------------------------------------------------------------
drop view if exists KRIM_PERM_V
/
CREATE VIEW KRIM_PERM_V AS 
SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, typ.NM AS perm_typ_nm
, typ.SRVC_NM
FROM KRIM_PERM_T p
INNER JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_TYP_T typ
ON t.KIM_TYP_ID = typ.KIM_TYP_ID
 
/


# -----------------------------------------------------------------------
# KRIM_PRNCPL_V
# -----------------------------------------------------------------------
drop view if exists KRIM_PRNCPL_V
/
CREATE VIEW KRIM_PRNCPL_V AS 
SELECT
p.PRNCPL_ID
,p.PRNCPL_NM
,en.FIRST_NM
,en.LAST_NM
,ea.AFLTN_TYP_CD
,ea.CAMPUS_CD
,eei.EMP_STAT_CD
,eei.EMP_TYP_CD
FROM krim_prncpl_t p
LEFT OUTER JOIN krim_entity_emp_info_t eei
ON eei.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_afltn_t ea
ON ea.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_nm_t en
ON p.ENTITY_ID = en.ENTITY_ID
AND 'Y' = en.DFLT_IND
 
/


# -----------------------------------------------------------------------
# KRIM_ROLE_GRP_V
# -----------------------------------------------------------------------
drop view if exists KRIM_ROLE_GRP_V
/
CREATE VIEW KRIM_ROLE_GRP_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, g.NMSPC_CD AS grp_nmspc_cd
, g.GRP_NM
, rm.ROLE_MBR_ID
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_GRP_T g
ON g.GRP_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'G'
ORDER BY nmspc_cd, role_nm, grp_nmspc_cd, grp_nm, role_mbr_id, attr_nm
 
/


# -----------------------------------------------------------------------
# KRIM_ROLE_PERM_V
# -----------------------------------------------------------------------
drop view if exists KRIM_ROLE_PERM_V
/
CREATE VIEW KRIM_ROLE_PERM_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, pt.NMSPC_CD AS tmpl_nmspc_cd
, pt.NM AS tmpl_nm
, pt.PERM_TMPL_ID
, p.NMSPC_CD AS perm_nmpsc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T pt
ON p.PERM_TMPL_ID = pt.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_ROLE_PERM_T rp
ON rp.PERM_ID = p.PERM_ID
LEFT OUTER JOIN KRIM_ROLE_T r
ON rp.ROLE_ID = r.ROLE_ID
ORDER BY NMSPC_CD, role_nm, tmpl_nmspc_cd, tmpl_nm, perm_id, attr_nm
 
/


# -----------------------------------------------------------------------
# KRIM_ROLE_PRNCPL_V
# -----------------------------------------------------------------------
drop view if exists KRIM_ROLE_PRNCPL_V
/
CREATE VIEW KRIM_ROLE_PRNCPL_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, en.FIRST_NM
, en.LAST_NM
, rm.ROLE_MBR_ID
, ad.NM AS attr_nm
, rmad.ATTR_VAL AS attr_val
FROM KRIM_ROLE_T r
LEFT OUTER JOIN KRIM_ROLE_MBR_T rm
ON r.ROLE_ID = rm.ROLE_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T rmad
ON rm.ROLE_MBR_ID = rmad.role_mbr_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T ad
ON rmad.KIM_ATTR_DEFN_ID = ad.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_PRNCPL_T p
ON rm.MBR_ID = p.PRNCPL_ID
AND rm.mbr_typ_cd = 'P'
LEFT OUTER JOIN KRIM_ENTITY_NM_T en
ON p.ENTITY_ID = en.ENTITY_ID
WHERE (en.DFLT_IND = 'Y')
ORDER BY nmspc_cd, role_nm, prncpl_nm, rm.ROLE_MBR_ID, attr_nm
 
/


# -----------------------------------------------------------------------
# KRIM_ROLE_ROLE_V
# -----------------------------------------------------------------------
drop view if exists KRIM_ROLE_ROLE_V
/
CREATE VIEW KRIM_ROLE_ROLE_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, mr.NMSPC_CD AS mbr_role_nmspc_cd
, mr.role_NM AS mbr_role_nm
, mr.role_id AS mbr_role_id
, rm.role_mbr_id
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_role_T mr
ON mr.role_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'R'
ORDER BY nmspc_cd, role_nm, mbr_role_nmspc_cd, mbr_role_nm, role_mbr_id, attr_nm
 
/


# -----------------------------------------------------------------------
# KRIM_ROLE_V
# -----------------------------------------------------------------------
drop view if exists KRIM_ROLE_V
/
CREATE VIEW KRIM_ROLE_V AS 
SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, t.nm AS role_typ_nm
, t.SRVC_NM
, t.KIM_TYP_ID
FROM KRIM_ROLE_T r
, KRIM_TYP_T t
WHERE t.KIM_TYP_ID = r.KIM_TYP_ID
AND r.ACTV_IND = 'Y'
ORDER BY nmspc_cd
, role_nm
 
/


# -----------------------------------------------------------------------
# KRIM_RSP_ATTR_V
# -----------------------------------------------------------------------
drop view if exists KRIM_RSP_ATTR_V
/
CREATE VIEW KRIM_RSP_ATTR_V AS 
SELECT
krim_typ_t.NM AS responsibility_type_name
, KRIM_rsp_TMPL_T.NM AS rsp_TEMPLATE_NAME
, KRIM_rsp_T.nmspc_cd AS rsp_namespace_code
, KRIM_rsp_T.NM AS rsp_NAME
, krim_rsp_t.RSP_ID AS rsp_id
, KRIM_ATTR_DEFN_T.NM AS attribute_name
, KRIM_rsp_ATTR_DATA_T.ATTR_VAL AS attribute_value
FROM KRIM_rsp_T KRIM_rsp_T
INNER JOIN KRIM_rsp_ATTR_DATA_T KRIM_rsp_ATTR_DATA_T
ON KRIM_rsp_T.rsp_ID = KRIM_rsp_ATTR_DATA_T.rsp_id
INNER JOIN KRIM_ATTR_DEFN_T KRIM_ATTR_DEFN_T
ON KRIM_rsp_ATTR_DATA_T.KIM_ATTR_DEFN_ID = KRIM_ATTR_DEFN_T.KIM_ATTR_DEFN_ID
INNER JOIN KRIM_rsp_TMPL_T KRIM_rsp_TMPL_T
ON KRIM_rsp_T.rsp_TMPL_ID = KRIM_rsp_TMPL_T.rsp_TMPL_ID
INNER JOIN KRIM_TYP_T KRIM_TYP_T
ON KRIM_rsp_TMPL_T.KIM_TYP_ID = KRIM_TYP_T.KIM_TYP_ID
ORDER BY rsp_TEMPLATE_NAME, rsp_NAME, attribute_name
 
/


# -----------------------------------------------------------------------
# KRIM_RSP_ROLE_ACTN_V
# -----------------------------------------------------------------------
drop view if exists KRIM_RSP_ROLE_ACTN_V
/
CREATE VIEW KRIM_RSP_ROLE_ACTN_V AS 
select
rsp.nmspc_cd as rsp_nmspc_cd
, rsp.rsp_id
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
, rm.MBR_ID
, rm.MBR_TYP_CD
, rm.ROLE_MBR_ID
, actn.ACTN_TYP_CD
, actn.ACTN_PLCY_CD
, actn.FRC_ACTN
, actn.PRIORITY_NBR
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join KRIM_ROLE_MBR_T rm
ON rm.ROLE_ID = rr.ROLE_ID
left outer join KRIM_ROLE_RSP_ACTN_T actn
ON actn.ROLE_RSP_ID = rr.ROLE_RSP_ID
AND (actn.ROLE_MBR_ID = rm.ROLE_MBR_ID OR actn.ROLE_MBR_ID = '*')
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_nmspc_cd
, rsp_id
, role_id
, role_mbr_id
 
/


# -----------------------------------------------------------------------
# KRIM_RSP_ROLE_V
# -----------------------------------------------------------------------
drop view if exists KRIM_RSP_ROLE_V
/
CREATE VIEW KRIM_RSP_ROLE_V AS 
select
rspt.nmspc_cd as rsp_tmpl_nmspc_cd
, rspt.nm as rsp_tmpl_nm
, rsp.nmspc_cd as rsp_nmspc_cd
, rsp.nm as rsp_nm
, rsp.rsp_id
, a.nm as attr_nm
, d.attr_val
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_rsp_attr_data_t d
on rsp.rsp_id = d.rsp_id
left outer join krim_attr_defn_t a
on d.kim_attr_defn_id = a.kim_attr_defn_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_tmpl_nmspc_cd, rsp_tmpl_nm, rsp_nmspc_cd, rsp_nm, rsp_id, attr_nm, attr_val
 
/


# -----------------------------------------------------------------------
# VIEW_CO_C
# -----------------------------------------------------------------------
drop view if exists VIEW_CO_C
/
CREATE VIEW VIEW_CO_C AS 
SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '1'
 
/


# -----------------------------------------------------------------------
# VIEW_CO_D
# -----------------------------------------------------------------------
drop view if exists VIEW_CO_D
/
CREATE VIEW VIEW_CO_D AS 
SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '4'
 
/


# -----------------------------------------------------------------------
# VIEW_CO_E
# -----------------------------------------------------------------------
drop view if exists VIEW_CO_E
/
CREATE VIEW VIEW_CO_E AS 
SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '3'
 
/


# -----------------------------------------------------------------------
# VIEW_CO_F
# -----------------------------------------------------------------------
drop view if exists VIEW_CO_F
/
CREATE VIEW VIEW_CO_F AS 
SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '2'
 
/


# -----------------------------------------------------------------------
# BAM_PARAM_SEQ
# -----------------------------------------------------------------------
drop table if exists BAM_PARAM_SEQ
/

CREATE TABLE BAM_PARAM_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE BAM_PARAM_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# BAM_SEQ
# -----------------------------------------------------------------------
drop table if exists BAM_SEQ
/

CREATE TABLE BAM_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE BAM_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# DIRTY_CACHE_SEQ
# -----------------------------------------------------------------------
drop table if exists DIRTY_CACHE_SEQ
/

CREATE TABLE DIRTY_CACHE_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE DIRTY_CACHE_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# EN_SERVICE_DEF_INTERFACE_SEQ
# -----------------------------------------------------------------------
drop table if exists EN_SERVICE_DEF_INTERFACE_SEQ
/

CREATE TABLE EN_SERVICE_DEF_INTERFACE_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE EN_SERVICE_DEF_INTERFACE_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# FP_DOC_TYPE_ATTR_ID_SEQ
# -----------------------------------------------------------------------
drop table if exists FP_DOC_TYPE_ATTR_ID_SEQ
/

CREATE TABLE FP_DOC_TYPE_ATTR_ID_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE FP_DOC_TYPE_ATTR_ID_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# KCB_MESSAGES_SEQ
# -----------------------------------------------------------------------
drop table if exists KCB_MESSAGES_SEQ
/

CREATE TABLE KCB_MESSAGES_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KCB_MESSAGES_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# KCB_MSG_DELIVS_SEQ
# -----------------------------------------------------------------------
drop table if exists KCB_MSG_DELIVS_SEQ
/

CREATE TABLE KCB_MSG_DELIVS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KCB_MSG_DELIVS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# KCB_RECIP_DELIVS_SEQ
# -----------------------------------------------------------------------
drop table if exists KCB_RECIP_DELIVS_SEQ
/

CREATE TABLE KCB_RECIP_DELIVS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KCB_RECIP_DELIVS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# KCB_RECIP_PREFS_SEQ
# -----------------------------------------------------------------------
drop table if exists KCB_RECIP_PREFS_SEQ
/

CREATE TABLE KCB_RECIP_PREFS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KCB_RECIP_PREFS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# KC_COUNTRY_CODES_SEQ
# -----------------------------------------------------------------------
drop table if exists KC_COUNTRY_CODES_SEQ
/

CREATE TABLE KC_COUNTRY_CODES_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KC_COUNTRY_CODES_SEQ auto_increment = 261
/

# -----------------------------------------------------------------------
# KREN_CHNL_S
# -----------------------------------------------------------------------
drop table if exists KREN_CHNL_S
/

CREATE TABLE KREN_CHNL_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_CHNL_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_CHNL_SUBSCRP_S
# -----------------------------------------------------------------------
drop table if exists KREN_CHNL_SUBSCRP_S
/

CREATE TABLE KREN_CHNL_SUBSCRP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_CHNL_SUBSCRP_S auto_increment = 1020
/

# -----------------------------------------------------------------------
# KREN_CNTNT_TYP_S
# -----------------------------------------------------------------------
drop table if exists KREN_CNTNT_TYP_S
/

CREATE TABLE KREN_CNTNT_TYP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_CNTNT_TYP_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_MSG_DELIV_S
# -----------------------------------------------------------------------
drop table if exists KREN_MSG_DELIV_S
/

CREATE TABLE KREN_MSG_DELIV_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_MSG_DELIV_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_MSG_S
# -----------------------------------------------------------------------
drop table if exists KREN_MSG_S
/

CREATE TABLE KREN_MSG_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_MSG_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_NTFCTN_MSG_DELIV_S
# -----------------------------------------------------------------------
drop table if exists KREN_NTFCTN_MSG_DELIV_S
/

CREATE TABLE KREN_NTFCTN_MSG_DELIV_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_NTFCTN_MSG_DELIV_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_NTFCTN_S
# -----------------------------------------------------------------------
drop table if exists KREN_NTFCTN_S
/

CREATE TABLE KREN_NTFCTN_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_NTFCTN_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_PRIO_S
# -----------------------------------------------------------------------
drop table if exists KREN_PRIO_S
/

CREATE TABLE KREN_PRIO_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_PRIO_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_PRODCR_S
# -----------------------------------------------------------------------
drop table if exists KREN_PRODCR_S
/

CREATE TABLE KREN_PRODCR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_PRODCR_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_RECIP_DELIV_S
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_DELIV_S
/

CREATE TABLE KREN_RECIP_DELIV_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_RECIP_DELIV_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_RECIP_LIST_S
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_LIST_S
/

CREATE TABLE KREN_RECIP_LIST_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_RECIP_LIST_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_RECIP_PREF_S
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_PREF_S
/

CREATE TABLE KREN_RECIP_PREF_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_RECIP_PREF_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_RECIP_S
# -----------------------------------------------------------------------
drop table if exists KREN_RECIP_S
/

CREATE TABLE KREN_RECIP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_RECIP_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_RVWER_S
# -----------------------------------------------------------------------
drop table if exists KREN_RVWER_S
/

CREATE TABLE KREN_RVWER_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_RVWER_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREN_SNDR_S
# -----------------------------------------------------------------------
drop table if exists KREN_SNDR_S
/

CREATE TABLE KREN_SNDR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREN_SNDR_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KREW_ACTN_ITM_S
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_ITM_S
/

CREATE TABLE KREW_ACTN_ITM_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_ACTN_ITM_S auto_increment = 10224
/

# -----------------------------------------------------------------------
# KREW_ACTN_LIST_OPTN_S
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_LIST_OPTN_S
/

CREATE TABLE KREW_ACTN_LIST_OPTN_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_ACTN_LIST_OPTN_S auto_increment = 1267
/

# -----------------------------------------------------------------------
# KREW_ACTN_RQST_S
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_RQST_S
/

CREATE TABLE KREW_ACTN_RQST_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_ACTN_RQST_S auto_increment = 2367
/

# -----------------------------------------------------------------------
# KREW_ACTN_TKN_S
# -----------------------------------------------------------------------
drop table if exists KREW_ACTN_TKN_S
/

CREATE TABLE KREW_ACTN_TKN_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_ACTN_TKN_S auto_increment = 2327
/

# -----------------------------------------------------------------------
# KREW_DOC_HDR_S
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_HDR_S
/

CREATE TABLE KREW_DOC_HDR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_DOC_HDR_S auto_increment = 4083
/

# -----------------------------------------------------------------------
# KREW_DOC_LNK_S
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_LNK_S
/

CREATE TABLE KREW_DOC_LNK_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_DOC_LNK_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KREW_DOC_NTE_S
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_NTE_S
/

CREATE TABLE KREW_DOC_NTE_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_DOC_NTE_S auto_increment = 2020
/

# -----------------------------------------------------------------------
# KREW_DOC_TYP_ATTR_S
# -----------------------------------------------------------------------
drop table if exists KREW_DOC_TYP_ATTR_S
/

CREATE TABLE KREW_DOC_TYP_ATTR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_DOC_TYP_ATTR_S auto_increment = 2154
/

# -----------------------------------------------------------------------
# KREW_EDL_FLD_DMP_S
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_FLD_DMP_S
/

CREATE TABLE KREW_EDL_FLD_DMP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_EDL_FLD_DMP_S auto_increment = 5000
/

# -----------------------------------------------------------------------
# KREW_EDL_S
# -----------------------------------------------------------------------
drop table if exists KREW_EDL_S
/

CREATE TABLE KREW_EDL_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_EDL_S auto_increment = 2020
/

# -----------------------------------------------------------------------
# KREW_HLP_S
# -----------------------------------------------------------------------
drop table if exists KREW_HLP_S
/

CREATE TABLE KREW_HLP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_HLP_S auto_increment = 100
/

# -----------------------------------------------------------------------
# KREW_OUT_BOX_ITM_S
# -----------------------------------------------------------------------
drop table if exists KREW_OUT_BOX_ITM_S
/

CREATE TABLE KREW_OUT_BOX_ITM_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_OUT_BOX_ITM_S auto_increment = 10043
/

# -----------------------------------------------------------------------
# KREW_RSP_S
# -----------------------------------------------------------------------
drop table if exists KREW_RSP_S
/

CREATE TABLE KREW_RSP_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RSP_S auto_increment = 2191
/

# -----------------------------------------------------------------------
# KREW_RTE_NODE_CFG_PARM_S
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_CFG_PARM_S
/

CREATE TABLE KREW_RTE_NODE_CFG_PARM_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RTE_NODE_CFG_PARM_S auto_increment = 5862
/

# -----------------------------------------------------------------------
# KREW_RTE_NODE_S
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_NODE_S
/

CREATE TABLE KREW_RTE_NODE_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RTE_NODE_S auto_increment = 4230
/

# -----------------------------------------------------------------------
# KREW_RTE_TMPL_S
# -----------------------------------------------------------------------
drop table if exists KREW_RTE_TMPL_S
/

CREATE TABLE KREW_RTE_TMPL_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RTE_TMPL_S auto_increment = 1879
/

# -----------------------------------------------------------------------
# KREW_RULE_EXPR_S
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_EXPR_S
/

CREATE TABLE KREW_RULE_EXPR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RULE_EXPR_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KREW_RULE_TMPL_OPTN_S
# -----------------------------------------------------------------------
drop table if exists KREW_RULE_TMPL_OPTN_S
/

CREATE TABLE KREW_RULE_TMPL_OPTN_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RULE_TMPL_OPTN_S auto_increment = 2020
/

# -----------------------------------------------------------------------
# KREW_SRCH_ATTR_S
# -----------------------------------------------------------------------
drop table if exists KREW_SRCH_ATTR_S
/

CREATE TABLE KREW_SRCH_ATTR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_SRCH_ATTR_S auto_increment = 2060
/

# -----------------------------------------------------------------------
# KREW_USR_S
# -----------------------------------------------------------------------
drop table if exists KREW_USR_S
/

CREATE TABLE KREW_USR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_USR_S auto_increment = 100000000000
/

# -----------------------------------------------------------------------
# KRIM_ATTR_DATA_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ATTR_DATA_ID_S
/

CREATE TABLE KRIM_ATTR_DATA_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ATTR_DATA_ID_S auto_increment = 1311
/

# -----------------------------------------------------------------------
# KRIM_ATTR_DEFN_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ATTR_DEFN_ID_S
/

CREATE TABLE KRIM_ATTR_DEFN_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ATTR_DEFN_ID_S auto_increment = 1011
/

# -----------------------------------------------------------------------
# KRIM_DLGN_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_ID_S
/

CREATE TABLE KRIM_DLGN_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_DLGN_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_DLGN_MBR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_MBR_ID_S
/

CREATE TABLE KRIM_DLGN_MBR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_DLGN_MBR_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_ADDR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ADDR_ID_S
/

CREATE TABLE KRIM_ENTITY_ADDR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_ADDR_ID_S auto_increment = 1020
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_AFLTN_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_AFLTN_ID_S
/

CREATE TABLE KRIM_ENTITY_AFLTN_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_AFLTN_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_CTZNSHP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_CTZNSHP_ID_S
/

CREATE TABLE KRIM_ENTITY_CTZNSHP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_CTZNSHP_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_EMAIL_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EMAIL_ID_S
/

CREATE TABLE KRIM_ENTITY_EMAIL_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_EMAIL_ID_S auto_increment = 1320
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_EMP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EMP_ID_S
/

CREATE TABLE KRIM_ENTITY_EMP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_EMP_ID_S auto_increment = 1079
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_ETHNIC_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ETHNIC_ID_S
/

CREATE TABLE KRIM_ENTITY_ETHNIC_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_ETHNIC_ID_S auto_increment = 10000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_EXT_ID_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_EXT_ID_ID_S
/

CREATE TABLE KRIM_ENTITY_EXT_ID_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_EXT_ID_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ID_S
/

CREATE TABLE KRIM_ENTITY_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_ID_S auto_increment = 1281
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_NM_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_NM_ID_S
/

CREATE TABLE KRIM_ENTITY_NM_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_NM_ID_S auto_increment = 1380
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_PHONE_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_PHONE_ID_S
/

CREATE TABLE KRIM_ENTITY_PHONE_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_PHONE_ID_S auto_increment = 1020
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_RESIDENCY_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_RESIDENCY_ID_S
/

CREATE TABLE KRIM_ENTITY_RESIDENCY_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_RESIDENCY_ID_S auto_increment = 10000
/

# -----------------------------------------------------------------------
# KRIM_ENTITY_VISA_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_VISA_ID_S
/

CREATE TABLE KRIM_ENTITY_VISA_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ENTITY_VISA_ID_S auto_increment = 10000
/

# -----------------------------------------------------------------------
# KRIM_GRP_ATTR_DATA_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_ATTR_DATA_ID_S
/

CREATE TABLE KRIM_GRP_ATTR_DATA_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_GRP_ATTR_DATA_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_GRP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_ID_S
/

CREATE TABLE KRIM_GRP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_GRP_ID_S auto_increment = 1000006
/

# -----------------------------------------------------------------------
# KRIM_GRP_MBR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_GRP_MBR_ID_S
/

CREATE TABLE KRIM_GRP_MBR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_GRP_MBR_ID_S auto_increment = 1376
/

# -----------------------------------------------------------------------
# KRIM_PERM_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_ID_S
/

CREATE TABLE KRIM_PERM_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_PERM_ID_S auto_increment = 1183
/

# -----------------------------------------------------------------------
# KRIM_PERM_RQRD_ATTR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_RQRD_ATTR_ID_S
/

CREATE TABLE KRIM_PERM_RQRD_ATTR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_PERM_RQRD_ATTR_ID_S auto_increment = 1098
/

# -----------------------------------------------------------------------
# KRIM_PERM_TMPL_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_PERM_TMPL_ID_S
/

CREATE TABLE KRIM_PERM_TMPL_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_PERM_TMPL_ID_S auto_increment = 1010
/

# -----------------------------------------------------------------------
# KRIM_PRNCPL_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_PRNCPL_ID_S
/

CREATE TABLE KRIM_PRNCPL_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_PRNCPL_ID_S auto_increment = 10000000081
/

# -----------------------------------------------------------------------
# KRIM_ROLE_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_ID_S
/

CREATE TABLE KRIM_ROLE_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ROLE_ID_S auto_increment = 1206
/

# -----------------------------------------------------------------------
# KRIM_ROLE_MBR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_MBR_ID_S
/

CREATE TABLE KRIM_ROLE_MBR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ROLE_MBR_ID_S auto_increment = 1471
/

# -----------------------------------------------------------------------
# KRIM_ROLE_PERM_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_PERM_ID_S
/

CREATE TABLE KRIM_ROLE_PERM_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ROLE_PERM_ID_S auto_increment = 10377
/

# -----------------------------------------------------------------------
# KRIM_ROLE_RSP_ACTN_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_RSP_ACTN_ID_S
/

CREATE TABLE KRIM_ROLE_RSP_ACTN_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ROLE_RSP_ACTN_ID_S auto_increment = 1034
/

# -----------------------------------------------------------------------
# KRIM_ROLE_RSP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_ROLE_RSP_ID_S
/

CREATE TABLE KRIM_ROLE_RSP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_ROLE_RSP_ID_S auto_increment = 1035
/

# -----------------------------------------------------------------------
# KRIM_RSP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_ID_S
/

CREATE TABLE KRIM_RSP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_RSP_ID_S auto_increment = 1034
/

# -----------------------------------------------------------------------
# KRIM_RSP_RQRD_ATTR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_RQRD_ATTR_ID_S
/

CREATE TABLE KRIM_RSP_RQRD_ATTR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_RSP_RQRD_ATTR_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_RSP_TMPL_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_RSP_TMPL_ID_S
/

CREATE TABLE KRIM_RSP_TMPL_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_RSP_TMPL_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRIM_TYP_ATTR_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_TYP_ATTR_ID_S
/

CREATE TABLE KRIM_TYP_ATTR_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_TYP_ATTR_ID_S auto_increment = 1014
/

# -----------------------------------------------------------------------
# KRIM_TYP_ID_S
# -----------------------------------------------------------------------
drop table if exists KRIM_TYP_ID_S
/

CREATE TABLE KRIM_TYP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRIM_TYP_ID_S auto_increment = 1016
/

# -----------------------------------------------------------------------
# KRNS_DOC_TYP_ATTR_S
# -----------------------------------------------------------------------
drop table if exists KRNS_DOC_TYP_ATTR_S
/

CREATE TABLE KRNS_DOC_TYP_ATTR_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRNS_DOC_TYP_ATTR_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRNS_LOCK_S
# -----------------------------------------------------------------------
drop table if exists KRNS_LOCK_S
/

CREATE TABLE KRNS_LOCK_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRNS_LOCK_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KRNS_LOOKUP_RSLT_S
# -----------------------------------------------------------------------
drop table if exists KRNS_LOOKUP_RSLT_S
/

CREATE TABLE KRNS_LOOKUP_RSLT_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRNS_LOOKUP_RSLT_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KRNS_MAINT_LOCK_S
# -----------------------------------------------------------------------
drop table if exists KRNS_MAINT_LOCK_S
/

CREATE TABLE KRNS_MAINT_LOCK_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRNS_MAINT_LOCK_S auto_increment = 2020
/

# -----------------------------------------------------------------------
# KRNS_NTE_S
# -----------------------------------------------------------------------
drop table if exists KRNS_NTE_S
/

CREATE TABLE KRNS_NTE_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRNS_NTE_S auto_increment = 2020
/

# -----------------------------------------------------------------------
# KRSB_BAM_PARM_S
# -----------------------------------------------------------------------
drop table if exists KRSB_BAM_PARM_S
/

CREATE TABLE KRSB_BAM_PARM_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRSB_BAM_PARM_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KRSB_BAM_S
# -----------------------------------------------------------------------
drop table if exists KRSB_BAM_S
/

CREATE TABLE KRSB_BAM_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRSB_BAM_S auto_increment = 2000
/

# -----------------------------------------------------------------------
# KRSB_FLT_SVC_DEF_S
# -----------------------------------------------------------------------
drop table if exists KRSB_FLT_SVC_DEF_S
/

CREATE TABLE KRSB_FLT_SVC_DEF_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRSB_FLT_SVC_DEF_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# KRSB_MSG_QUE_S
# -----------------------------------------------------------------------
drop table if exists KRSB_MSG_QUE_S
/

CREATE TABLE KRSB_MSG_QUE_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRSB_MSG_QUE_S auto_increment = 65904
/

# -----------------------------------------------------------------------
# KRSB_SVC_DEF_S
# -----------------------------------------------------------------------
drop table if exists KRSB_SVC_DEF_S
/

CREATE TABLE KRSB_SVC_DEF_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KRSB_SVC_DEF_S auto_increment = 3900
/

# -----------------------------------------------------------------------
# LOCK_ID_SEQ
# -----------------------------------------------------------------------
drop table if exists LOCK_ID_SEQ
/

CREATE TABLE LOCK_ID_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE LOCK_ID_SEQ auto_increment = 2020
/

# -----------------------------------------------------------------------
# LOOKUP_RESULT_SEQUENCE_NBR_SEQ
# -----------------------------------------------------------------------
drop table if exists LOOKUP_RESULT_SEQUENCE_NBR_SEQ
/

CREATE TABLE LOOKUP_RESULT_SEQUENCE_NBR_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE LOOKUP_RESULT_SEQUENCE_NBR_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# NOTIFICATIONS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATIONS_SEQ
/

CREATE TABLE NOTIFICATIONS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATIONS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_CHANNELS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_CHANNELS_SEQ
/

CREATE TABLE NOTIFICATION_CHANNELS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_CHANNELS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_CONTENT_TYPES_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_CONTENT_TYPES_SEQ
/

CREATE TABLE NOTIFICATION_CONTENT_TYPES_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_CONTENT_TYPES_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_MSG_DELIVS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_MSG_DELIVS_SEQ
/

CREATE TABLE NOTIFICATION_MSG_DELIVS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_MSG_DELIVS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_PRIORITIES_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_PRIORITIES_SEQ
/

CREATE TABLE NOTIFICATION_PRIORITIES_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_PRIORITIES_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_PRODUCERS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_PRODUCERS_SEQ
/

CREATE TABLE NOTIFICATION_PRODUCERS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_PRODUCERS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_RECIPIENTS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_RECIPIENTS_SEQ
/

CREATE TABLE NOTIFICATION_RECIPIENTS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_RECIPIENTS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_RECIPS_LISTS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_RECIPS_LISTS_SEQ
/

CREATE TABLE NOTIFICATION_RECIPS_LISTS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_RECIPS_LISTS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_REVIEWERS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_REVIEWERS_SEQ
/

CREATE TABLE NOTIFICATION_REVIEWERS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_REVIEWERS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NOTIFICATION_SENDERS_SEQ
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION_SENDERS_SEQ
/

CREATE TABLE NOTIFICATION_SENDERS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NOTIFICATION_SENDERS_SEQ auto_increment = 1000
/

# -----------------------------------------------------------------------
# NTE_ID_SEQ
# -----------------------------------------------------------------------
drop table if exists NTE_ID_SEQ
/

CREATE TABLE NTE_ID_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE NTE_ID_SEQ auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQUENCE_EN_USR
# -----------------------------------------------------------------------
drop table if exists SEQUENCE_EN_USR
/

CREATE TABLE SEQUENCE_EN_USR
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQUENCE_EN_USR auto_increment = 100000000000
/

# -----------------------------------------------------------------------
# SEQUENCE_NSF_CODES
# -----------------------------------------------------------------------
drop table if exists SEQUENCE_NSF_CODES
/

CREATE TABLE SEQUENCE_NSF_CODES
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQUENCE_NSF_CODES auto_increment = 39
/

# -----------------------------------------------------------------------
# SEQUENCE_PROPOSAL_ID
# -----------------------------------------------------------------------
drop table if exists SEQUENCE_PROPOSAL_ID
/

CREATE TABLE SEQUENCE_PROPOSAL_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQUENCE_PROPOSAL_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQUENCE_SUBMISSION_HISTORY
# -----------------------------------------------------------------------
drop table if exists SEQUENCE_SUBMISSION_HISTORY
/

CREATE TABLE SEQUENCE_SUBMISSION_HISTORY
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQUENCE_SUBMISSION_HISTORY auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_ACTION_LIST_OPTN
# -----------------------------------------------------------------------
drop table if exists SEQ_ACTION_LIST_OPTN
/

CREATE TABLE SEQ_ACTION_LIST_OPTN
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ACTION_LIST_OPTN auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_ACTION_REQUEST
# -----------------------------------------------------------------------
drop table if exists SEQ_ACTION_REQUEST
/

CREATE TABLE SEQ_ACTION_REQUEST
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ACTION_REQUEST auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_ACTION_TAKEN
# -----------------------------------------------------------------------
drop table if exists SEQ_ACTION_TAKEN
/

CREATE TABLE SEQ_ACTION_TAKEN
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ACTION_TAKEN auto_increment = 2260
/

# -----------------------------------------------------------------------
# SEQ_ACTN_ITM
# -----------------------------------------------------------------------
drop table if exists SEQ_ACTN_ITM
/

CREATE TABLE SEQ_ACTN_ITM
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ACTN_ITM auto_increment = 10000
/

# -----------------------------------------------------------------------
# SEQ_APPLICATIONS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_APPLICATIONS_ID
/

CREATE TABLE SEQ_APPLICATIONS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_APPLICATIONS_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_APP_SPONSRD_USER_ATTR_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_APP_SPONSRD_USER_ATTR_ID
/

CREATE TABLE SEQ_APP_SPONSRD_USER_ATTR_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_APP_SPONSRD_USER_ATTR_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_ATTACHMENT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_ATTACHMENT_ID
/

CREATE TABLE SEQ_ATTACHMENT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ATTACHMENT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_ATTRIBUTE_TYPES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_ATTRIBUTE_TYPES_ID
/

CREATE TABLE SEQ_ATTRIBUTE_TYPES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ATTRIBUTE_TYPES_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_AWARD_AMOUNT_TRANS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_AMOUNT_TRANS_ID
/

CREATE TABLE SEQ_AWARD_AMOUNT_TRANS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_AMOUNT_TRANS_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_AMT_FNA_DSTRBTN_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_AMT_FNA_DSTRBTN_ID
/

CREATE TABLE SEQ_AWARD_AMT_FNA_DSTRBTN_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_AMT_FNA_DSTRBTN_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_APPROVED_SUBAWARD_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_APPROVED_SUBAWARD_ID
/

CREATE TABLE SEQ_AWARD_APPROVED_SUBAWARD_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_APPROVED_SUBAWARD_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_ATTACHMENT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_ATTACHMENT_ID
/

CREATE TABLE SEQ_AWARD_ATTACHMENT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_ATTACHMENT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_AWARD_CLOSEOUT
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_AWARD_CLOSEOUT
/

CREATE TABLE SEQ_AWARD_AWARD_CLOSEOUT
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_AWARD_CLOSEOUT auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_AWARD_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_AWARD_ID
/

CREATE TABLE SEQ_AWARD_AWARD_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_AWARD_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_AWARD_NUMBER
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_AWARD_NUMBER
/

CREATE TABLE SEQ_AWARD_AWARD_NUMBER
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_AWARD_NUMBER auto_increment = 21
/

# -----------------------------------------------------------------------
# SEQ_AWARD_COMMENT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_COMMENT_ID
/

CREATE TABLE SEQ_AWARD_COMMENT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_COMMENT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_COST_SHARE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_COST_SHARE_ID
/

CREATE TABLE SEQ_AWARD_COST_SHARE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_COST_SHARE_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_CUSTOM_DATA_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_CUSTOM_DATA_ID
/

CREATE TABLE SEQ_AWARD_CUSTOM_DATA_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_CUSTOM_DATA_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_EXEMPT_NUMBER_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_EXEMPT_NUMBER_ID
/

CREATE TABLE SEQ_AWARD_EXEMPT_NUMBER_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_EXEMPT_NUMBER_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_NOTEPAD_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_NOTEPAD_ID
/

CREATE TABLE SEQ_AWARD_NOTEPAD_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_NOTEPAD_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_REP_TERMS_RECNT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_REP_TERMS_RECNT_ID
/

CREATE TABLE SEQ_AWARD_REP_TERMS_RECNT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_REP_TERMS_RECNT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_SCIENCE_KEYWORD_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_SCIENCE_KEYWORD_ID
/

CREATE TABLE SEQ_AWARD_SCIENCE_KEYWORD_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_SCIENCE_KEYWORD_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_SPECIAL_REVIEW_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_SPECIAL_REVIEW_ID
/

CREATE TABLE SEQ_AWARD_SPECIAL_REVIEW_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_SPECIAL_REVIEW_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_SPONSOR_TERM
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_SPONSOR_TERM
/

CREATE TABLE SEQ_AWARD_SPONSOR_TERM
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_SPONSOR_TERM auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_SPONSOR_TERMS
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_SPONSOR_TERMS
/

CREATE TABLE SEQ_AWARD_SPONSOR_TERMS
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_SPONSOR_TERMS auto_increment = 321
/

# -----------------------------------------------------------------------
# SEQ_AWARD_TEMPLATE
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_TEMPLATE
/

CREATE TABLE SEQ_AWARD_TEMPLATE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_TEMPLATE auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_AWARD_TRANS_SPONSOR_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_AWARD_TRANS_SPONSOR_ID
/

CREATE TABLE SEQ_AWARD_TRANS_SPONSOR_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_AWARD_TRANS_SPONSOR_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_BATCH_CORRESPONDENCE
# -----------------------------------------------------------------------
drop table if exists SEQ_BATCH_CORRESPONDENCE
/

CREATE TABLE SEQ_BATCH_CORRESPONDENCE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BATCH_CORRESPONDENCE auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_BGT_PER_DET_RATE_BASE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BGT_PER_DET_RATE_BASE_ID
/

CREATE TABLE SEQ_BGT_PER_DET_RATE_BASE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BGT_PER_DET_RATE_BASE_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_DETAILS_CAL_AMTS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_DETAILS_CAL_AMTS_ID
/

CREATE TABLE SEQ_BUDGET_DETAILS_CAL_AMTS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_DETAILS_CAL_AMTS_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_DETAILS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_DETAILS_ID
/

CREATE TABLE SEQ_BUDGET_DETAILS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_DETAILS_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_ID
/

CREATE TABLE SEQ_BUDGET_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_PERIOD_NUMBER
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_PERIOD_NUMBER
/

CREATE TABLE SEQ_BUDGET_PERIOD_NUMBER
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_PERIOD_NUMBER auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_PER_CAL_AMTS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_PER_CAL_AMTS_ID
/

CREATE TABLE SEQ_BUDGET_PER_CAL_AMTS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_PER_CAL_AMTS_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_PER_DET_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_PER_DET_ID
/

CREATE TABLE SEQ_BUDGET_PER_DET_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_PER_DET_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_BUDGET_RATE_AND_BASE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_BUDGET_RATE_AND_BASE_ID
/

CREATE TABLE SEQ_BUDGET_RATE_AND_BASE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_BUDGET_RATE_AND_BASE_ID auto_increment = 9
/

# -----------------------------------------------------------------------
# SEQ_COMMITTEE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_COMMITTEE_ID
/

CREATE TABLE SEQ_COMMITTEE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_COMMITTEE_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_COMM_RESEARCH_AREAS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_COMM_RESEARCH_AREAS_ID
/

CREATE TABLE SEQ_COMM_RESEARCH_AREAS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_COMM_RESEARCH_AREAS_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_COMM_SCHEDULE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_COMM_SCHEDULE_ID
/

CREATE TABLE SEQ_COMM_SCHEDULE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_COMM_SCHEDULE_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_CONG_DISTRICT_ID_KRA
# -----------------------------------------------------------------------
drop table if exists SEQ_CONG_DISTRICT_ID_KRA
/

CREATE TABLE SEQ_CONG_DISTRICT_ID_KRA
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_CONG_DISTRICT_ID_KRA auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_DOCUMENT_ROUTE_HEADER
# -----------------------------------------------------------------------
drop table if exists SEQ_DOCUMENT_ROUTE_HEADER
/

CREATE TABLE SEQ_DOCUMENT_ROUTE_HEADER
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_DOCUMENT_ROUTE_HEADER auto_increment = 3360
/

# -----------------------------------------------------------------------
# SEQ_DOCUMENT_TYPE_ATTRIBUTE
# -----------------------------------------------------------------------
drop table if exists SEQ_DOCUMENT_TYPE_ATTRIBUTE
/

CREATE TABLE SEQ_DOCUMENT_TYPE_ATTRIBUTE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_DOCUMENT_TYPE_ATTRIBUTE auto_increment = 2280
/

# -----------------------------------------------------------------------
# SEQ_DOC_NTE
# -----------------------------------------------------------------------
drop table if exists SEQ_DOC_NTE
/

CREATE TABLE SEQ_DOC_NTE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_DOC_NTE auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_EDL_FIELD_DMP
# -----------------------------------------------------------------------
drop table if exists SEQ_EDL_FIELD_DMP
/

CREATE TABLE SEQ_EDL_FIELD_DMP
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_EDL_FIELD_DMP auto_increment = 5000
/

# -----------------------------------------------------------------------
# SEQ_EN_EDOCLT
# -----------------------------------------------------------------------
drop table if exists SEQ_EN_EDOCLT
/

CREATE TABLE SEQ_EN_EDOCLT
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_EN_EDOCLT auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_GROUPS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_GROUPS_ID
/

CREATE TABLE SEQ_GROUPS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_GROUPS_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_GROUP_ATTRIBUTES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_GROUP_ATTRIBUTES_ID
/

CREATE TABLE SEQ_GROUP_ATTRIBUTES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_GROUP_ATTRIBUTES_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_HELP_ENTRY
# -----------------------------------------------------------------------
drop table if exists SEQ_HELP_ENTRY
/

CREATE TABLE SEQ_HELP_ENTRY
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_HELP_ENTRY auto_increment = 100
/

# -----------------------------------------------------------------------
# SEQ_IP_COMMENT
# -----------------------------------------------------------------------
drop table if exists SEQ_IP_COMMENT
/

CREATE TABLE SEQ_IP_COMMENT
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_IP_COMMENT auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_IP_REVIEW_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_IP_REVIEW_ID
/

CREATE TABLE SEQ_IP_REVIEW_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_IP_REVIEW_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_IP_REV_ACTIVITY_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_IP_REV_ACTIVITY_ID
/

CREATE TABLE SEQ_IP_REV_ACTIVITY_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_IP_REV_ACTIVITY_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_MEETING_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_MEETING_ID
/

CREATE TABLE SEQ_MEETING_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_MEETING_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_OUT_BOX_ITM
# -----------------------------------------------------------------------
drop table if exists SEQ_OUT_BOX_ITM
/

CREATE TABLE SEQ_OUT_BOX_ITM
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_OUT_BOX_ITM auto_increment = 10000
/

# -----------------------------------------------------------------------
# SEQ_PERMISSIONS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PERMISSIONS_ID
/

CREATE TABLE SEQ_PERMISSIONS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PERMISSIONS_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_PERSON_APPOINTMENT
# -----------------------------------------------------------------------
drop table if exists SEQ_PERSON_APPOINTMENT
/

CREATE TABLE SEQ_PERSON_APPOINTMENT
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PERSON_APPOINTMENT auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_PERSON_DEGREE
# -----------------------------------------------------------------------
drop table if exists SEQ_PERSON_DEGREE
/

CREATE TABLE SEQ_PERSON_DEGREE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PERSON_DEGREE auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_ADMIN_DETAILS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_ADMIN_DETAILS_ID
/

CREATE TABLE SEQ_PROPOSAL_ADMIN_DETAILS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_ADMIN_DETAILS_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_COMMENTS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_COMMENTS_ID
/

CREATE TABLE SEQ_PROPOSAL_COMMENTS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_COMMENTS_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_COST_SHARE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_COST_SHARE_ID
/

CREATE TABLE SEQ_PROPOSAL_COST_SHARE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_COST_SHARE_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_CUSTOM_DATA_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_CUSTOM_DATA_ID
/

CREATE TABLE SEQ_PROPOSAL_CUSTOM_DATA_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_CUSTOM_DATA_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_EXEMPT_NUMBER_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_EXEMPT_NUMBER_ID
/

CREATE TABLE SEQ_PROPOSAL_EXEMPT_NUMBER_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_EXEMPT_NUMBER_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_IP_REVIEW_JOIN_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_IP_REVIEW_JOIN_ID
/

CREATE TABLE SEQ_PROPOSAL_IP_REVIEW_JOIN_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_IP_REVIEW_JOIN_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_NOTEPAD_IDN
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_NOTEPAD_IDN
/

CREATE TABLE SEQ_PROPOSAL_NOTEPAD_IDN
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_NOTEPAD_IDN auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_NUMBER
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_NUMBER
/

CREATE TABLE SEQ_PROPOSAL_NUMBER
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_NUMBER auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_NUMBER_KRA
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_NUMBER_KRA
/

CREATE TABLE SEQ_PROPOSAL_NUMBER_KRA
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_NUMBER_KRA auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_PROPOSAL_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_PROPOSAL_ID
/

CREATE TABLE SEQ_PROPOSAL_PROPOSAL_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_PROPOSAL_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROPOSAL_SPECIAL_REVIEW_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROPOSAL_SPECIAL_REVIEW_ID
/

CREATE TABLE SEQ_PROPOSAL_SPECIAL_REVIEW_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROPOSAL_SPECIAL_REVIEW_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROP_ROLE_TEMPLATE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROP_ROLE_TEMPLATE_ID
/

CREATE TABLE SEQ_PROP_ROLE_TEMPLATE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROP_ROLE_TEMPLATE_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_PROP_UNIT_ADMIN_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROP_UNIT_ADMIN_ID
/

CREATE TABLE SEQ_PROP_UNIT_ADMIN_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROP_UNIT_ADMIN_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTOCOL_EXEMPT_NUMBER_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTOCOL_EXEMPT_NUMBER_ID
/

CREATE TABLE SEQ_PROTOCOL_EXEMPT_NUMBER_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTOCOL_EXEMPT_NUMBER_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTOCOL_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTOCOL_ID
/

CREATE TABLE SEQ_PROTOCOL_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTOCOL_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTOCOL_REFERENCES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTOCOL_REFERENCES_ID
/

CREATE TABLE SEQ_PROTOCOL_REFERENCES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTOCOL_REFERENCES_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTOCOL_RESEARCH_AREAS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTOCOL_RESEARCH_AREAS_ID
/

CREATE TABLE SEQ_PROTOCOL_RESEARCH_AREAS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTOCOL_RESEARCH_AREAS_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTOCOL_SPECIAL_REVIEW_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTOCOL_SPECIAL_REVIEW_ID
/

CREATE TABLE SEQ_PROTOCOL_SPECIAL_REVIEW_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTOCOL_SPECIAL_REVIEW_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_PROTO_CORRESP_TEMPL
# -----------------------------------------------------------------------
drop table if exists SEQ_PROTO_CORRESP_TEMPL
/

CREATE TABLE SEQ_PROTO_CORRESP_TEMPL
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_PROTO_CORRESP_TEMPL auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_QUESTIONNAIRE_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_QUESTIONNAIRE_ID
/

CREATE TABLE SEQ_QUESTIONNAIRE_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_QUESTIONNAIRE_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_QUESTIONNAIRE_REF_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_QUESTIONNAIRE_REF_ID
/

CREATE TABLE SEQ_QUESTIONNAIRE_REF_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_QUESTIONNAIRE_REF_ID auto_increment = 317
/

# -----------------------------------------------------------------------
# SEQ_QUESTION_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_QUESTION_ID
/

CREATE TABLE SEQ_QUESTION_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_QUESTION_ID auto_increment = 80
/

# -----------------------------------------------------------------------
# SEQ_RESPONSIBILITY_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_RESPONSIBILITY_ID
/

CREATE TABLE SEQ_RESPONSIBILITY_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_RESPONSIBILITY_ID auto_increment = 2220
/

# -----------------------------------------------------------------------
# SEQ_ROLES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_ROLES_ID
/

CREATE TABLE SEQ_ROLES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ROLES_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_ROLE_ATTRIBUTES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_ROLE_ATTRIBUTES_ID
/

CREATE TABLE SEQ_ROLE_ATTRIBUTES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ROLE_ATTRIBUTES_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_ROUTE_QUEUE
# -----------------------------------------------------------------------
drop table if exists SEQ_ROUTE_QUEUE
/

CREATE TABLE SEQ_ROUTE_QUEUE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ROUTE_QUEUE auto_increment = 4420
/

# -----------------------------------------------------------------------
# SEQ_ROUTE_TEMPLATE
# -----------------------------------------------------------------------
drop table if exists SEQ_ROUTE_TEMPLATE
/

CREATE TABLE SEQ_ROUTE_TEMPLATE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_ROUTE_TEMPLATE auto_increment = 1300
/

# -----------------------------------------------------------------------
# SEQ_RTE_NODE
# -----------------------------------------------------------------------
drop table if exists SEQ_RTE_NODE
/

CREATE TABLE SEQ_RTE_NODE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_RTE_NODE auto_increment = 3600
/

# -----------------------------------------------------------------------
# SEQ_RTE_NODE_CFG_PARM
# -----------------------------------------------------------------------
drop table if exists SEQ_RTE_NODE_CFG_PARM
/

CREATE TABLE SEQ_RTE_NODE_CFG_PARM
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_RTE_NODE_CFG_PARM auto_increment = 3800
/

# -----------------------------------------------------------------------
# SEQ_RULE_EXPR
# -----------------------------------------------------------------------
drop table if exists SEQ_RULE_EXPR
/

CREATE TABLE SEQ_RULE_EXPR
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_RULE_EXPR auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_RULE_TMPL_OPTN
# -----------------------------------------------------------------------
drop table if exists SEQ_RULE_TMPL_OPTN
/

CREATE TABLE SEQ_RULE_TMPL_OPTN
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_RULE_TMPL_OPTN auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_S2S_APP_ATTACHMENT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_S2S_APP_ATTACHMENT_ID
/

CREATE TABLE SEQ_S2S_APP_ATTACHMENT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_S2S_APP_ATTACHMENT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_SEARCHABLE_ATTRIBUTE_VALUE
# -----------------------------------------------------------------------
drop table if exists SEQ_SEARCHABLE_ATTRIBUTE_VALUE
/

CREATE TABLE SEQ_SEARCHABLE_ATTRIBUTE_VALUE
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_SEARCHABLE_ATTRIBUTE_VALUE auto_increment = 2000
/

# -----------------------------------------------------------------------
# SEQ_SPONSOR_TERM
# -----------------------------------------------------------------------
drop table if exists SEQ_SPONSOR_TERM
/

CREATE TABLE SEQ_SPONSOR_TERM
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_SPONSOR_TERM auto_increment = 307
/

# -----------------------------------------------------------------------
# SEQ_SUB_AWD_BGT_ATT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_SUB_AWD_BGT_ATT_ID
/

CREATE TABLE SEQ_SUB_AWD_BGT_ATT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_SUB_AWD_BGT_ATT_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_TRANSACTION_DETAIL_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_TRANSACTION_DETAIL_ID
/

CREATE TABLE SEQ_TRANSACTION_DETAIL_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_TRANSACTION_DETAIL_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_TRANSACTION_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_TRANSACTION_ID
/

CREATE TABLE SEQ_TRANSACTION_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_TRANSACTION_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_TRANSACTION_TST
# -----------------------------------------------------------------------
drop table if exists SEQ_TRANSACTION_TST
/

CREATE TABLE SEQ_TRANSACTION_TST
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_TRANSACTION_TST auto_increment = 100
/

# -----------------------------------------------------------------------
# SEQ_TRAVEL_DOC_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_TRAVEL_DOC_ID
/

CREATE TABLE SEQ_TRAVEL_DOC_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_TRAVEL_DOC_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_TRAVEL_FO_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_TRAVEL_FO_ID
/

CREATE TABLE SEQ_TRAVEL_FO_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_TRAVEL_FO_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_UNRECOVERED_FNA_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_UNRECOVERED_FNA_ID
/

CREATE TABLE SEQ_UNRECOVERED_FNA_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_UNRECOVERED_FNA_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SEQ_USERS_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_USERS_ID
/

CREATE TABLE SEQ_USERS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_USERS_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_USER_ATTRIBUTES_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_USER_ATTRIBUTES_ID
/

CREATE TABLE SEQ_USER_ATTRIBUTES_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_USER_ATTRIBUTES_ID auto_increment = 1000
/

# -----------------------------------------------------------------------
# SEQ_VALID_BASIS_METHOD_PMT_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_VALID_BASIS_METHOD_PMT_ID
/

CREATE TABLE SEQ_VALID_BASIS_METHOD_PMT_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_VALID_BASIS_METHOD_PMT_ID auto_increment = 28
/

# -----------------------------------------------------------------------
# SEQ_VALID_CLASS_REPORT_FREQ
# -----------------------------------------------------------------------
drop table if exists SEQ_VALID_CLASS_REPORT_FREQ
/

CREATE TABLE SEQ_VALID_CLASS_REPORT_FREQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_VALID_CLASS_REPORT_FREQ auto_increment = 521
/

# -----------------------------------------------------------------------
# SEQ_VERSION_HISTORY_ID
# -----------------------------------------------------------------------
drop table if exists SEQ_VERSION_HISTORY_ID
/

CREATE TABLE SEQ_VERSION_HISTORY_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SEQ_VERSION_HISTORY_ID auto_increment = 1
/

# -----------------------------------------------------------------------
# SERVICE_DEF_SEQ
# -----------------------------------------------------------------------
drop table if exists SERVICE_DEF_SEQ
/

CREATE TABLE SERVICE_DEF_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE SERVICE_DEF_SEQ auto_increment = 2320
/

# -----------------------------------------------------------------------
# TRV_FO_ID_S
# -----------------------------------------------------------------------
drop table if exists TRV_FO_ID_S
/

CREATE TABLE TRV_FO_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE TRV_FO_ID_S auto_increment = 1000
/

# -----------------------------------------------------------------------
# USER_CHANNEL_SUBSCRIPTIONS_SEQ
# -----------------------------------------------------------------------
drop table if exists USER_CHANNEL_SUBSCRIPTIONS_SEQ
/

CREATE TABLE USER_CHANNEL_SUBSCRIPTIONS_SEQ
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE USER_CHANNEL_SUBSCRIPTIONS_SEQ auto_increment = 1000
/
delimiter ;
