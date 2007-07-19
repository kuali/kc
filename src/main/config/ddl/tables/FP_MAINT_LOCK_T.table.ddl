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
CREATE TABLE FP_MAINT_LOCK_T(
        LOCK_REPRESENTATION_TXT        VARCHAR2(255) CONSTRAINT FP_MAINT_LOCK_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_MAINT_LOCK_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_MAINT_LOCK_TN3 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_MAINT_LOCK_TN4 NOT NULL,
     CONSTRAINT FP_MAINT_LOCK_TP1 PRIMARY KEY (
        LOCK_REPRESENTATION_TXT) USING INDEX TABLESPACE KUL_IDX01,
     CONSTRAINT FP_MAINT_LOCK_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX01
)
TABLESPACE KUL01
/
