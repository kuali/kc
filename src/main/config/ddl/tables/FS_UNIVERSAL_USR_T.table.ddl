/*
 * Copyright 2005-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
CREATE TABLE FS_UNIVERSAL_USR_T (
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT FS_UNIVERSAL_USR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FS_UNIVERSAL_USR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_UNIVERSAL_USR_TN3 NOT NULL,
        PERSON_USER_ID                 VARCHAR2(8) CONSTRAINT FS_UNIVERSAL_USR_TN4 NOT NULL,
        FS_ENCRPTD_PWD_TXT             VARCHAR2(4000),
        PERSON_NM                      VARCHAR2(80),
        PRSN_1ST_NM                    VARCHAR2(20),
        PRSN_LST_NM                    VARCHAR2(20),
        PRSN_EMAIL_ADDR                VARCHAR2(100),
        CAMPUS_CD                      VARCHAR2(2),
        PRSN_CMP_ADDR                  VARCHAR2(55),
        PRSN_LOC_PHN_NBR               VARCHAR2(10),
        EMP_STAT_CD                    VARCHAR2(1),
        EMP_TYPE_CD                    VARCHAR2(1),
        PRSN_BASE_SLRY_AMT             NUMBER(19,2),
        PRSN_PYRL_ID                   VARCHAR2(255),
        PRSN_TAX_ID                    VARCHAR2(255),
        PRSN_TAX_ID_TYP_CD             CHAR(1),
        PRSN_STAFF_IND                 CHAR(1),
        PRSN_FAC_IND                   CHAR(1),
        PRSN_STU_IND                   CHAR(1),
        PRSN_AFLT_IND                  CHAR(1),
        EMP_PRM_DEPT_CD                VARCHAR2(10),
        PRSN_MID_NM                    VARCHAR2(20), 
     CONSTRAINT FS_UNIVERSAL_USR_TP1 PRIMARY KEY (
        PERSON_UNVL_ID) USING INDEX TABLESPACE KUL_IDX01,
     CONSTRAINT FS_UNIVERSAL_USR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX01,
     CONSTRAINT FS_UNIVERSAL_USR_TC1 UNIQUE (PRSN_PYRL_ID) USING INDEX TABLESPACE KUL_IDX01
)
TABLESPACE KUL01
/

