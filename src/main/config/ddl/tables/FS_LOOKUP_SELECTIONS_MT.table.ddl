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
CREATE TABLE FS_LOOKUP_SELECTIONS_MT(
        LOOKUP_RESULT_SEQUENCE_NBR     VARCHAR2(14) CONSTRAINT FS_LOOKUP_SELECTIONS_MTN1 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FS_LOOKUP_SELECTIONS_MTN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_LOOKUP_SELECTIONS_MTN3 NOT NULL,
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT FS_LOOKUP_SELECTIONS_MTN4 NOT NULL,
        LOOKUP_DATE                    DATE CONSTRAINT FS_LOOKUP_SELECTIONS_MTN5 NOT NULL,
        SELECTED_OBJ_IDS               CLOB, 
     CONSTRAINT FS_LOOKUP_SELECTIONS_MTP1 PRIMARY KEY (
        LOOKUP_RESULT_SEQUENCE_NBR),
     CONSTRAINT FS_LOOKUP_SELECTIONS_MTC0 UNIQUE (OBJ_ID)
)
/
