delimiter /

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

    , CONSTRAINT KREN_CHNL_SUBSCRP_TC0 UNIQUE (CHNL_ID, PRNCPL_ID)

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
    
    , CONSTRAINT KREN_CHNL_TP1 PRIMARY KEY(CHNL_ID)

    , CONSTRAINT KREN_CHNL_TC0 UNIQUE (NM)


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
    
    , CONSTRAINT KREN_CNTNT_TYP_TP1 PRIMARY KEY(CNTNT_TYP_ID)

    , CONSTRAINT KREN_CNTNT_TYP_TC0 UNIQUE (NM, CNTNT_TYP_VER_NBR)


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
        , LOCKD_DTTM DATETIME
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_DELIV_TP1 PRIMARY KEY(MSG_DELIV_ID)

    , CONSTRAINT KREN_MSG_DELIV_TC0 UNIQUE (MSG_ID, TYP_NM)

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
        , CRTE_DTTM DATETIME NOT NULL
        , TTL VARCHAR(255)
        , CHNL VARCHAR(300) NOT NULL
        , PRODCR VARCHAR(300)
        , CNTNT LONGTEXT NOT NULL
        , CNTNT_TYP VARCHAR(128)
        , URL VARCHAR(512)
        , RECIP_ID VARCHAR(300) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_MSG_TP1 PRIMARY KEY(MSG_ID)

    , CONSTRAINT KREN_MSG_TC0 UNIQUE (ORGN_ID)


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
        , LOCKD_DTTM DATETIME
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_NTFCTN_MSG_DELIV_TP1 PRIMARY KEY(NTFCTN_MSG_DELIV_ID)

    , CONSTRAINT KREN_NTFCTN_MSG_DELIV_TC0 UNIQUE (NTFCTN_ID, RECIP_ID)

    , INDEX KREN_MSG_DELIVSI1 (NTFCTN_ID)

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
        , CRTE_DTTM DATETIME NOT NULL
        , SND_DTTM DATETIME
        , AUTO_RMV_DTTM DATETIME
        , PRIO_ID DECIMAL(8) NOT NULL
        , TTL VARCHAR(255)
        , CNTNT LONGTEXT NOT NULL
        , CNTNT_TYP_ID DECIMAL(8) NOT NULL
        , CHNL_ID DECIMAL(8) NOT NULL
        , PRODCR_ID DECIMAL(8) NOT NULL
        , PROCESSING_FLAG VARCHAR(15) NOT NULL
        , LOCKD_DTTM DATETIME
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    
    , CONSTRAINT KREN_NTFCTN_TP1 PRIMARY KEY(NTFCTN_ID)


    , INDEX KREN_NTFCTN_I1 (CNTNT_TYP_ID)
    , INDEX KREN_NTFCTN_I2 (PRIO_ID)
    , INDEX KREN_NTFCTN_I3 (PRODCR_ID)

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
    
    , CONSTRAINT KREN_PRIO_TP1 PRIMARY KEY(PRIO_ID)

    , CONSTRAINT KREN_PRIO_TC0 UNIQUE (NM)


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
    
    , CONSTRAINT KREN_PRODCR_TP1 PRIMARY KEY(PRODCR_ID)

    , CONSTRAINT KREN_PRODCR_TC0 UNIQUE (NM)


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

    , CONSTRAINT KREN_RECIP_LIST_TC0 UNIQUE (CHNL_ID, RECIP_TYP_CD, RECIP_ID)

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

    , CONSTRAINT KREN_RECIP_PREFS_TC0 UNIQUE (RECIP_ID, PROP)


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

    , CONSTRAINT KREN_RECIP_TC0 UNIQUE (NTFCTN_ID, RECIP_TYP_CD, PRNCPL_ID)

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
    
    , CONSTRAINT KREN_RVWER_TP1 PRIMARY KEY(RVWER_ID)

    , CONSTRAINT KREN_RVWER_TC0 UNIQUE (CHNL_ID, TYP, PRNCPL_ID)

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

    , CONSTRAINT KREN_SNDR_TC0 UNIQUE (NTFCTN_ID, NM)

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

    , CONSTRAINT KREW_APP_DOC_STAT_TRAN_TC0 UNIQUE (OBJ_ID)

    , INDEX KREW_APP_DOC_STAT_TI1 (DOC_HDR_ID, STAT_TRANS_DATE)
    , INDEX KREW_APP_DOC_STAT_TI2 (DOC_HDR_ID, APP_DOC_STAT_FROM)
    , INDEX KREW_APP_DOC_STAT_TI3 (DOC_HDR_ID, APP_DOC_STAT_TO)

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

    , CONSTRAINT KREW_DLGN_RSP_TC0 UNIQUE (OBJ_ID)


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
        , APP_DOC_STAT VARCHAR(64)
        , APP_DOC_STAT_MDFN_DT DATETIME
    
    , CONSTRAINT KREW_DOC_HDR_TP1 PRIMARY KEY(DOC_HDR_ID)

    , CONSTRAINT KREW_DOC_HDR_TC0 UNIQUE (OBJ_ID)

    , INDEX KREW_DOC_HDR_T10 (APP_DOC_STAT)
    , INDEX KREW_DOC_HDR_T12 (APP_DOC_STAT_MDFN_DT)
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

    , CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TC0 UNIQUE (OBJ_ID)

    , INDEX KREW_DOC_TYP_APP_DOC_STAT_T1 (DOC_TYP_ID)

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
      DOC_TYP_ID DECIMAL(19)
        , DOC_PLCY_NM VARCHAR(255)
        , PLCY_NM DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , OBJ_ID VARCHAR(36) NOT NULL
        , PLCY_VAL VARCHAR(64)
    
    , CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TP1 PRIMARY KEY(DOC_TYP_ID,DOC_PLCY_NM)

    , CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KREW_DOC_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KREW_DOC_TYP_TI1 UNIQUE (DOC_TYP_NM, DOC_TYP_VER_NBR)

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

    , CONSTRAINT KREW_EDL_ASSCTN_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KREW_EDL_DEF_TC0 UNIQUE (OBJ_ID)


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
# KREW_RIA_DOCTYPE_MAP_T
# -----------------------------------------------------------------------
drop table if exists KREW_RIA_DOCTYPE_MAP_T
/

CREATE TABLE KREW_RIA_DOCTYPE_MAP_T
(
      ID DECIMAL(22)
        , RIA_DOC_TYPE_NAME VARCHAR(100)
        , UPDATED_AT DATETIME
        , RIA_URL VARCHAR(255)
        , HELP_URL VARCHAR(255)
        , EDITABLE CHAR(1)
        , INIT_GROUPS VARCHAR(255)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RIA_DOCTYPE_MAP_TP1 PRIMARY KEY(ID)



) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KREW_RIA_DOC_T
# -----------------------------------------------------------------------
drop table if exists KREW_RIA_DOC_T
/

CREATE TABLE KREW_RIA_DOC_T
(
      RIA_ID DECIMAL(22)
        , XML_CONTENT VARCHAR(4000)
        , RIA_DOC_TYPE_NAME VARCHAR(100)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
    
    , CONSTRAINT KREW_RIA_DOC_TP1 PRIMARY KEY(RIA_ID)



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

    , CONSTRAINT KREW_RULE_ATTR_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KREW_RULE_EXPR_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KREW_RULE_RSP_TC0 UNIQUE (OBJ_ID)

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

    , CONSTRAINT KREW_RULE_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KREW_RULE_TMPL_ATTR_TC0 UNIQUE (OBJ_ID)

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

    , CONSTRAINT KREW_RULE_TMPL_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KREW_RULE_TMPL_TI1 UNIQUE (NM)


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

    , CONSTRAINT KREW_STYLE_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ADDR_TYP_TP1 PRIMARY KEY(ADDR_TYP_CD)

    , CONSTRAINT KRIM_ADDR_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ADDR_TYP_TC1 UNIQUE (NM)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_AFLTN_TYP_TP1 PRIMARY KEY(AFLTN_TYP_CD)

    , CONSTRAINT KRIM_AFLTN_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_AFLTN_TYP_TC1 UNIQUE (NM)


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

    , CONSTRAINT KRIM_ATTR_DEFN_TC0 UNIQUE (OBJ_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_CTZNSHP_STAT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_CTZNSHP_STAT_T
/

CREATE TABLE KRIM_CTZNSHP_STAT_T
(
      CTZNSHP_STAT_CD VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DISPLAY_SORT_CD VARCHAR(2)
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_CTZNSHP_STAT_TP1 PRIMARY KEY(CTZNSHP_STAT_CD)

    , CONSTRAINT KRIM_CTZNSHP_STAT_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_CTZNSHP_STAT_TC1 UNIQUE (NM)


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

    , CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_DLGN_MBR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_DLGN_MBR_T
/

CREATE TABLE KRIM_DLGN_MBR_T
(
      DLGN_MBR_ID VARCHAR(40)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , DLGN_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME
        , ROLE_MBR_ID VARCHAR(40)
    
    , CONSTRAINT KRIM_DLGN_MBR_TP1 PRIMARY KEY(DLGN_MBR_ID)

    , CONSTRAINT KRIM_DLGN_MBR_TC0 UNIQUE (OBJ_ID)


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
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , ROLE_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , DLGN_TYP_CD VARCHAR(1)
    
    , CONSTRAINT KRIM_DLGN_TP1 PRIMARY KEY(DLGN_ID)

    , CONSTRAINT KRIM_DLGN_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_EMAIL_TYP_TP1 PRIMARY KEY(EMAIL_TYP_CD)

    , CONSTRAINT KRIM_EMAIL_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMAIL_TYP_TC1 UNIQUE (NM)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_EMP_STAT_TP1 PRIMARY KEY(EMP_STAT_CD)

    , CONSTRAINT KRIM_EMP_STAT_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMP_STAT_TC1 UNIQUE (NM)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_EMP_TYP_TP1 PRIMARY KEY(EMP_TYP_CD)

    , CONSTRAINT KRIM_EMP_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EMP_TYP_TC1 UNIQUE (NM)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_ENTITY_ADDR_T
# -----------------------------------------------------------------------
drop table if exists KRIM_ENTITY_ADDR_T
/

CREATE TABLE KRIM_ENTITY_ADDR_T
(
      ENTITY_ADDR_ID VARCHAR(40)
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
        , POSTAL_CNTRY_CD VARCHAR(2)
        , DFLT_IND VARCHAR(1) default 'N'
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_ADDR_TP1 PRIMARY KEY(ENTITY_ADDR_ID)

    , CONSTRAINT KRIM_ENTITY_ADDR_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_AFLTN_TP1 PRIMARY KEY(ENTITY_AFLTN_ID)

    , CONSTRAINT KRIM_ENTITY_AFLTN_TC0 UNIQUE (OBJ_ID)

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
      ENTITY_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , BIRTH_DT DATETIME
        , GNDR_CD VARCHAR(1) NOT NULL
        , LAST_UPDT_DT DATETIME
        , DECEASED_DT DATETIME
        , MARITAL_STATUS VARCHAR(40)
        , PRIM_LANG_CD VARCHAR(40)
        , SEC_LANG_CD VARCHAR(40)
        , BIRTH_CNTRY_CD VARCHAR(2)
        , BIRTH_STATE_CD VARCHAR(2)
        , BIRTH_CITY VARCHAR(30)
        , GEO_ORIGIN VARCHAR(100)
    
    , CONSTRAINT KRIM_ENTITY_BIO_TP1 PRIMARY KEY(ENTITY_ID)

    , CONSTRAINT KRIM_ENTITY_BIO_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_ENTITY_CACHE_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENTITY_CACHE_TC1 UNIQUE (PRNCPL_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_CTZNSHP_TP1 PRIMARY KEY(ENTITY_CTZNSHP_ID)

    , CONSTRAINT KRIM_ENTITY_CTZNSHP_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_EMAIL_TP1 PRIMARY KEY(ENTITY_EMAIL_ID)

    , CONSTRAINT KRIM_ENTITY_EMAIL_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
        , PRMRY_DEPT_CD VARCHAR(40)
        , EMP_ID VARCHAR(40)
        , EMP_REC_ID VARCHAR(40)
    
    , CONSTRAINT KRIM_ENTITY_EMP_INFO_TP1 PRIMARY KEY(ENTITY_EMP_ID)

    , CONSTRAINT KRIM_ENTITY_EMP_INFO_TC0 UNIQUE (OBJ_ID)

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
      ENT_TYP_CD VARCHAR(40)
        , ENTITY_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_ENT_TYP_TP1 PRIMARY KEY(ENT_TYP_CD,ENTITY_ID)

    , CONSTRAINT KRIM_ENTITY_ENT_TYP_TC0 UNIQUE (OBJ_ID)

    , INDEX KRIM_ENTITY_ENT_TYP_TI1 (ENTITY_ID)

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

    , CONSTRAINT KRIM_ENTITY_ETHNIC_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_EXT_ID_TP1 PRIMARY KEY(ENTITY_EXT_ID_ID)

    , CONSTRAINT KRIM_ENTITY_EXT_ID_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_NM_TP1 PRIMARY KEY(ENTITY_NM_ID)

    , CONSTRAINT KRIM_ENTITY_NM_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_PHONE_TP1 PRIMARY KEY(ENTITY_PHONE_ID)

    , CONSTRAINT KRIM_ENTITY_PHONE_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_PRIV_PREF_TP1 PRIMARY KEY(ENTITY_ID)

    , CONSTRAINT KRIM_ENTITY_PRIV_PREF_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_ENTITY_RESIDENCY_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENTITY_TP1 PRIMARY KEY(ENTITY_ID)

    , CONSTRAINT KRIM_ENTITY_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_ENTITY_VISA_TC0 UNIQUE (OBJ_ID)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ENT_NM_TYP_TP1 PRIMARY KEY(ENT_NM_TYP_CD)

    , CONSTRAINT KRIM_ENT_NM_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENT_NM_TYP_TC1 UNIQUE (NM)


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

    , CONSTRAINT KRIM_ENT_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ENT_TYP_TC1 UNIQUE (NM)


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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_EXT_ID_TYP_TP1 PRIMARY KEY(EXT_ID_TYP_CD)

    , CONSTRAINT KRIM_EXT_ID_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_EXT_ID_TYP_TC1 UNIQUE (NM)


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

    , CONSTRAINT KRIM_GRP_ATTR_DATA_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , KIM_TYP_ID VARCHAR(40) NOT NULL
        , GRP_NMSPC VARCHAR(100) NOT NULL
        , GRP_NM VARCHAR(400)
        , GRP_DESC VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
    
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
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , GRP_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_GRP_MBR_TP1 PRIMARY KEY(GRP_MBR_ID)

    , CONSTRAINT KRIM_GRP_MBR_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_GRP_TP1 PRIMARY KEY(GRP_ID)

    , CONSTRAINT KRIM_GRP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_GRP_TC1 UNIQUE (GRP_NM, NMSPC_CD)


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

    , CONSTRAINT KRIM_PERM_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

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

    , CONSTRAINT KRIM_PERM_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_PERM_TMPL_TC0 UNIQUE (OBJ_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRIM_PERSON_DOCUMENT_T
# -----------------------------------------------------------------------
drop table if exists KRIM_PERSON_DOCUMENT_T
/

CREATE TABLE KRIM_PERSON_DOCUMENT_T
(
      FDOC_NBR VARCHAR(14)
        , ENTITY_ID VARCHAR(40) NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , PRNCPL_NM VARCHAR(100) NOT NULL
        , PRNCPL_PSWD VARCHAR(400)
        , UNIV_ID VARCHAR(40)
        , ACTV_IND VARCHAR(1) default 'Y'
    
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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_PHONE_TYP_TP1 PRIMARY KEY(PHONE_TYP_CD)

    , CONSTRAINT KRIM_PHONE_TYP_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_PHONE_TYP_TC1 UNIQUE (PHONE_TYP_NM)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
    
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
      FDOC_NBR VARCHAR(14)
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
        , ROLE_MBR_ID VARCHAR(40)
    
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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40) NOT NULL
        , PERM_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
    
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
      ROLE_RSP_ACTN_ID VARCHAR(40)
        , FDOC_NBR VARCHAR(14)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTN_TYP_CD VARCHAR(40)
        , PRIORITY_NBR DECIMAL(3)
        , ACTN_PLCY_CD VARCHAR(40)
        , ROLE_MBR_ID VARCHAR(40)
        , ROLE_RSP_ID VARCHAR(40)
        , EDIT_FLAG VARCHAR(1) default 'N'
        , FRC_ACTN VARCHAR(1)
    
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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_ID VARCHAR(40) NOT NULL
        , RSP_ID VARCHAR(40) NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y'
    
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
      PRNCPL_ID VARCHAR(40)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_NM VARCHAR(100) NOT NULL
        , ENTITY_ID VARCHAR(40)
        , PRNCPL_PSWD VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_PRNCPL_TP1 PRIMARY KEY(PRNCPL_ID)

    , CONSTRAINT KRIM_PRNCPL_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_PRNCPL_TC1 UNIQUE (PRNCPL_NM)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ROLE_TYP_ID VARCHAR(40) NOT NULL
        , ROLE_NMSPC VARCHAR(100) NOT NULL
        , ROLE_NM VARCHAR(400)
        , ACTV_IND VARCHAR(1) default 'Y'
        , DESC_TXT VARCHAR(4000)
    
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

    , CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID)

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
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
        , ROLE_ID VARCHAR(40)
        , MBR_ID VARCHAR(40)
        , MBR_TYP_CD CHAR(1) default 'P'
        , ACTV_FRM_DT DATETIME
        , ACTV_TO_DT DATETIME
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ROLE_MBR_TP1 PRIMARY KEY(ROLE_MBR_ID)

    , CONSTRAINT KRIM_ROLE_MBR_TC0 UNIQUE (OBJ_ID)

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

    , CONSTRAINT KRIM_ROLE_PERM_TC0 UNIQUE (OBJ_ID)

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

    , CONSTRAINT KRIM_ROLE_RSP_ACTN_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ROLE_RSP_ACTN_TC1 UNIQUE (ROLE_RSP_ID, ROLE_MBR_ID)


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

    , CONSTRAINT KRIM_ROLE_RSP_TC0 UNIQUE (OBJ_ID)

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
        , LAST_UPDT_DT DATETIME
    
    , CONSTRAINT KRIM_ROLE_TP1 PRIMARY KEY(ROLE_ID)

    , CONSTRAINT KRIM_ROLE_TC0 UNIQUE (OBJ_ID)
    , CONSTRAINT KRIM_ROLE_TC1 UNIQUE (ROLE_NM, NMSPC_CD)


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

    , CONSTRAINT KRIM_RSP_ATTR_DATA_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_RSP_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_RSP_TMPL_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KRIM_TYP_ATTR_TC0 UNIQUE (OBJ_ID)

    , INDEX KRIM_TYP_ATTRIBUTE_TI1 (KIM_TYP_ID)

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

    , CONSTRAINT KRIM_TYP_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR(14)
    
    , CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TP1 PRIMARY KEY(RECIP_TYP_CD,ACTN_RQST_CD,ACTN_RQST_RECIP_ID,DOC_HDR_ID)

    , CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TC0 UNIQUE (OBJ_ID)

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
      NTE_ID DECIMAL(14)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , MIME_TYP VARCHAR(255)
        , FILE_NM VARCHAR(250)
        , ATT_ID VARCHAR(36)
        , FILE_SZ DECIMAL(14)
        , ATT_TYP_CD VARCHAR(40)
    
    , CONSTRAINT KRNS_ATT_TP1 PRIMARY KEY(NTE_ID)

    , CONSTRAINT KRNS_ATT_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_CAMPUS_TP1 PRIMARY KEY(CAMPUS_CD)

    , CONSTRAINT KRNS_CAMPUS_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , ACTV_IND VARCHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_CMP_TYP_TP1 PRIMARY KEY(CAMPUS_TYP_CD)

    , CONSTRAINT KRNS_CMP_TYP_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , FDOC_DESC VARCHAR(40)
        , ORG_DOC_HDR_ID VARCHAR(10)
        , TMPL_DOC_HDR_ID VARCHAR(14)
        , EXPLANATION VARCHAR(400)
    
    , CONSTRAINT KRNS_DOC_HDR_TP1 PRIMARY KEY(DOC_HDR_ID)

    , CONSTRAINT KRNS_DOC_HDR_TC0 UNIQUE (OBJ_ID)

    , INDEX KRNS_DOC_HDR_TI3 (ORG_DOC_HDR_ID)

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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , LOOKUP_DT DATETIME NOT NULL
        , SERIALZD_RSLTS LONGTEXT
    
    , CONSTRAINT KRNS_LOOKUP_RSLT_TP1 PRIMARY KEY(LOOKUP_RSLT_ID)

    , CONSTRAINT KRNS_LOOKUP_RSLT_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
        , LOOKUP_DT DATETIME NOT NULL
        , SEL_OBJ_IDS LONGTEXT
    
    , CONSTRAINT KRNS_LOOKUP_SEL_TP1 PRIMARY KEY(LOOKUP_RSLT_ID)

    , CONSTRAINT KRNS_LOOKUP_SEL_TC0 UNIQUE (OBJ_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_MAINT_DOC_ATT_T
# -----------------------------------------------------------------------
drop table if exists KRNS_MAINT_DOC_ATT_T
/

CREATE TABLE KRNS_MAINT_DOC_ATT_T
(
      DOC_HDR_ID VARCHAR(14)
        , ATT_CNTNT LONGBLOB NOT NULL
        , FILE_NM VARCHAR(150)
        , CNTNT_TYP VARCHAR(255)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
    
    , CONSTRAINT KRNS_MAINT_DOC_ATT_TP1 PRIMARY KEY(DOC_HDR_ID)

    , CONSTRAINT KRNS_MAINT_DOC_ATT_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_CNTNT LONGTEXT
    
    , CONSTRAINT KRNS_MAINT_DOC_TP1 PRIMARY KEY(DOC_HDR_ID)

    , CONSTRAINT KRNS_MAINT_DOC_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , DOC_HDR_ID VARCHAR(14) NOT NULL
        , MAINT_LOCK_ID VARCHAR(14)
    
    , CONSTRAINT KRNS_MAINT_LOCK_TP1 PRIMARY KEY(MAINT_LOCK_ID)

    , CONSTRAINT KRNS_MAINT_LOCK_TC0 UNIQUE (OBJ_ID)

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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
        , APPL_NMSPC_CD VARCHAR(20)
    
    , CONSTRAINT KRNS_NMSPC_TP1 PRIMARY KEY(NMSPC_CD)

    , CONSTRAINT KRNS_NMSPC_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , RMT_OBJ_ID VARCHAR(36) NOT NULL
        , AUTH_PRNCPL_ID VARCHAR(40) NOT NULL
        , POST_TS DATETIME NOT NULL
        , NTE_TYP_CD VARCHAR(4) NOT NULL
        , TXT VARCHAR(800)
        , PRG_CD VARCHAR(1)
        , TPC_TXT VARCHAR(40)
    
    , CONSTRAINT KRNS_NTE_TP1 PRIMARY KEY(NTE_ID)

    , CONSTRAINT KRNS_NTE_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , TYP_DESC_TXT VARCHAR(100)
        , ACTV_IND VARCHAR(1)
    
    , CONSTRAINT KRNS_NTE_TYP_TP1 PRIMARY KEY(NTE_TYP_CD)

    , CONSTRAINT KRNS_NTE_TYP_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(255)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_PARM_DTL_TYP_TP1 PRIMARY KEY(NMSPC_CD,PARM_DTL_TYP_CD)

    , CONSTRAINT KRNS_PARM_DTL_TYP_TC0 UNIQUE (OBJ_ID)


) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


# -----------------------------------------------------------------------
# KRNS_PARM_T
# -----------------------------------------------------------------------
drop table if exists KRNS_PARM_T
/

CREATE TABLE KRNS_PARM_T
(
      NMSPC_CD VARCHAR(20)
        , PARM_DTL_TYP_CD VARCHAR(100)
        , PARM_NM VARCHAR(255)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , PARM_TYP_CD VARCHAR(5) NOT NULL
        , TXT VARCHAR(4000)
        , PARM_DESC_TXT VARCHAR(4000)
        , CONS_CD VARCHAR(1)
        , APPL_NMSPC_CD VARCHAR(20) default 'KUALI'
    
    , CONSTRAINT KRNS_PARM_TP1 PRIMARY KEY(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,APPL_NMSPC_CD)

    , CONSTRAINT KRNS_PARM_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(40)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_PARM_TYP_TP1 PRIMARY KEY(PARM_TYP_CD)

    , CONSTRAINT KRNS_PARM_TYP_TC0 UNIQUE (OBJ_ID)


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
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , LOCK_DESC_TXT VARCHAR(4000)
        , DOC_HDR_ID VARCHAR(14) NOT NULL
        , GNRT_DT DATETIME NOT NULL
        , PRNCPL_ID VARCHAR(40) NOT NULL
    
    , CONSTRAINT KRNS_PESSIMISTIC_LOCK_TP1 PRIMARY KEY(PESSIMISTIC_LOCK_ID)

    , CONSTRAINT KRNS_PESSIMISTIC_LOCK_TC0 UNIQUE (OBJ_ID)

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
      JOB_NAME VARCHAR(80)
        , JOB_GROUP VARCHAR(80)
        , DESCRIPTION VARCHAR(120)
        , JOB_CLASS_NAME VARCHAR(128) NOT NULL
        , IS_DURABLE VARCHAR(1) NOT NULL
        , IS_VOLATILE VARCHAR(1) NOT NULL
        , IS_STATEFUL VARCHAR(1) NOT NULL
        , REQUESTS_RECOVERY VARCHAR(1) NOT NULL
        , JOB_DATA LONGBLOB
    
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
      SVC_DEF_ID DECIMAL(14)
        , SVC_NM VARCHAR(255) NOT NULL
        , SVC_URL VARCHAR(500) NOT NULL
        , SRVR_IP VARCHAR(40) NOT NULL
        , SVC_NMSPC VARCHAR(255) NOT NULL
        , SVC_ALIVE DECIMAL(1) NOT NULL
        , VER_NBR DECIMAL(8) default 0
        , FLT_SVC_DEF_ID DECIMAL(14) NOT NULL
        , SVC_DEF_CHKSM VARCHAR(30) NOT NULL
    
    , CONSTRAINT KRSB_SVC_DEF_TP1 PRIMARY KEY(SVC_DEF_ID)

    , CONSTRAINT KRSB_SVC_DEF_TI2 UNIQUE (FLT_SVC_DEF_ID)

    , INDEX KRSB_SVC_DEF_TI1 (SRVR_IP, SVC_NMSPC)

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
        , ALT_POSTAL_CNTRY_CD VARCHAR(3)
    
    , CONSTRAINT KR_COUNTRY_TP1 PRIMARY KEY(POSTAL_CNTRY_CD)

    , CONSTRAINT KR_COUNTRY_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KR_COUNTY_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KR_POSTAL_CODE_TC0 UNIQUE (OBJ_ID)


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

    , CONSTRAINT KR_STATE_TC0 UNIQUE (OBJ_ID)


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
ALTER TABLE KREW_ACTN_ITM_S auto_increment = 10226
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
ALTER TABLE KREW_ACTN_LIST_OPTN_S auto_increment = 1269
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
ALTER TABLE KREW_ACTN_RQST_S auto_increment = 2369
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
ALTER TABLE KREW_ACTN_TKN_S auto_increment = 2329
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
ALTER TABLE KREW_DOC_HDR_S auto_increment = 3000
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
ALTER TABLE KREW_DOC_TYP_ATTR_S auto_increment = 2009
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
# KREW_RIA_DOCTYPE_MAP_ID_S
# -----------------------------------------------------------------------
drop table if exists KREW_RIA_DOCTYPE_MAP_ID_S
/

CREATE TABLE KREW_RIA_DOCTYPE_MAP_ID_S
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/
ALTER TABLE KREW_RIA_DOCTYPE_MAP_ID_S auto_increment = 1000
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
ALTER TABLE KREW_RSP_S auto_increment = 2065
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
ALTER TABLE KREW_RTE_NODE_CFG_PARM_S auto_increment = 2456
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
ALTER TABLE KREW_RTE_NODE_S auto_increment = 2912
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
ALTER TABLE KREW_RTE_TMPL_S auto_increment = 1645
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
ALTER TABLE KREW_RULE_EXPR_S auto_increment = 2002
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
ALTER TABLE KRIM_ATTR_DATA_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ATTR_DEFN_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_DLGN_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_DLGN_MBR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_ADDR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_AFLTN_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_CTZNSHP_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_EMAIL_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_EMP_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_EXT_ID_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_NM_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ENTITY_PHONE_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_GRP_ATTR_DATA_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_GRP_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_GRP_MBR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_PERM_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_PERM_RQRD_ATTR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_PERM_TMPL_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_PRNCPL_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ROLE_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ROLE_MBR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ROLE_PERM_ID_S auto_increment = 1000
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
ALTER TABLE KRIM_ROLE_RSP_ACTN_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_ROLE_RSP_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_RSP_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_RSP_RQRD_ATTR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_RSP_TMPL_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_TYP_ATTR_ID_S auto_increment = 10000
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
ALTER TABLE KRIM_TYP_ID_S auto_increment = 10000
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
ALTER TABLE KRSB_MSG_QUE_S auto_increment = 463
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
ALTER TABLE KRSB_SVC_DEF_S auto_increment = 4058
/
delimiter ;
