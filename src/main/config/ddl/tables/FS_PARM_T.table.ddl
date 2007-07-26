/*
 * Copyright 2005-2006 The Kuali Foundation.
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
CREATE TABLE FS_PARM_T (
        FS_SCR_NM                      VARCHAR2(255) CONSTRAINT FS_PARM_TN1 NOT NULL,
        FS_PARM_NM                     VARCHAR2(255) CONSTRAINT FS_PARM_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FS_PARM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_PARM_TN4 NOT NULL,
        FS_PARM_TXT                    VARCHAR2(4000),
        FS_PARM_DESC                   VARCHAR2(2000),
        FS_MULT_VAL_IND                VARCHAR2(1) CONSTRAINT FS_PARM_TN5 NOT NULL,
        FS_MOD_CD                   VARCHAR2(2) NOT NULL,
     CONSTRAINT FS_PARM_TP1 PRIMARY KEY (
        FS_SCR_NM,
        FS_PARM_NM) ,
     CONSTRAINT FS_PARM_TC0 UNIQUE (OBJ_ID) 
)
/
