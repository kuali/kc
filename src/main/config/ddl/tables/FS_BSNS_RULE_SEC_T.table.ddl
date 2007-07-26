/*
 * Copyright 2006 The Kuali Foundation.
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
CREATE TABLE FS_BSNS_RULE_SEC_T (
        FS_RULE_GRP_NM                 VARCHAR2(255) CONSTRAINT FS_BSNS_RULE_SEC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FS_BSNS_RULE_SEC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_BSNS_RULE_SEC_TN3 NOT NULL,
        WRKGRP_NM                      VARCHAR2(70) CONSTRAINT FS_BSNS_RULE_SEC_TN4 NOT NULL,
        FS_RULE_GRP_DESC               VARCHAR2(2000),
     CONSTRAINT FS_BSNS_RULE_SEC_TP1 PRIMARY KEY (
        FS_RULE_GRP_NM) USING INDEX TABLESPACE KUL_IDX01,
     CONSTRAINT FS_BSNS_RULE_SEC_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX01
)
TABLESPACE KUL01
/