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
CREATE TABLE FP_DOC_GROUP_T(
        FDOC_GRP_CD                    VARCHAR2(2) CONSTRAINT FP_DOC_GROUP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DOC_GROUP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DOC_GROUP_TN3 NOT NULL,
        FDOC_GRP_NM                    VARCHAR2(40),
        FDOC_CLASS_CD                  VARCHAR2(2),
     CONSTRAINT FP_DOC_GROUP_TP1 PRIMARY KEY (FDOC_GRP_CD),
     CONSTRAINT FP_DOC_GROUP_TC0 UNIQUE (OBJ_ID)
)
/
