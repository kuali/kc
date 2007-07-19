/*
 * Copyright 2007 The Kuali Foundation.
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
CREATE TABLE SH_USR_PROP_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT SH_USR_PROP_TN1 NOT NULL,
        APPL_MOD_ID                    VARCHAR2(20) CONSTRAINT SH_USR_PROP_TN2 NOT NULL,
        USR_PROP_NM                    VARCHAR2(40) CONSTRAINT SH_USR_PROP_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT SH_USR_PROP_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_USR_PROP_TN5 NOT NULL,
        USR_PROP_VAL                   VARCHAR2(4000),
     CONSTRAINT SH_USR_PROP_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        APPL_MOD_ID,
        USR_PROP_NM) USING INDEX TABLESPACE KUL_IDX01,
     CONSTRAINT SH_USR_PROP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX01
)
TABLESPACE KUL01
/