/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE SH_NTE_T(
        NTE_ID                         NUMBER(14) CONSTRAINT SH_NTE_TN1 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT SH_NTE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_NTE_TN3 NOT NULL,
        RMT_OBJ_ID                     VARCHAR2(36) CONSTRAINT SH_NTE_TN4 NOT NULL,
        NTE_AUTH_ID                    VARCHAR2(10) CONSTRAINT SH_NTE_TN5 NOT NULL,
        NTE_POST_TS                    DATE CONSTRAINT SH_NTE_TN6 NOT NULL,
        NTE_TYP_CD                     VARCHAR2(4) CONSTRAINT SH_NTE_TN7 NOT NULL,
        NTE_TXT                        VARCHAR2(800),
        NTE_PRG_CD                     VARCHAR2(1),
        NTE_TPC_TXT                    VARCHAR2(40),
     CONSTRAINT SH_NTE_TP1 PRIMARY KEY (
        NTE_ID) USING INDEX TABLESPACE KUL_IDX03,
     CONSTRAINT SH_NTE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX03,
     CONSTRAINT SH_NTE_TC1 UNIQUE (RMT_OBJ_ID, NTE_AUTH_ID, NTE_POST_TS, NTE_TYP_CD) USING INDEX TABLESPACE KUL_IDX03
)
TABLESPACE KUL03
/