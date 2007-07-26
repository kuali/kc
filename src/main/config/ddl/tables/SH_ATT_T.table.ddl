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
CREATE TABLE SH_ATT_T (
        NTE_ID                         NUMBER(14) CONSTRAINT SH_ATT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT SH_ATT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_ATT_TN3 NOT NULL,
        ATT_MIME_TYP_CD                VARCHAR2(40),
        ATT_FL_NM                      VARCHAR2(250),
        ATT_ID                         VARCHAR2(36),
        ATT_FL_SZ                      NUMBER(14),
        ATT_TYP_CD                     VARCHAR2(2),
     CONSTRAINT SH_ATT_TP1 PRIMARY KEY (
        NTE_ID) USING INDEX TABLESPACE KUL_IDX03,
     CONSTRAINT SH_ATT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX03
)
TABLESPACE KUL03
/