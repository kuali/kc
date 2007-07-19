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
CREATE TABLE FP_DOC_HEADER_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DOC_HEADER_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DOC_HEADER_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DOC_HEADER_TN3 NOT NULL,
        FDOC_STATUS_CD                 VARCHAR2(2),
        FDOC_DESC                      VARCHAR2(40),
        FDOC_TOTAL_AMT                 NUMBER(19, 2),
        ORG_DOC_NBR                    VARCHAR2(10),
        FDOC_IN_ERR_NBR                VARCHAR2(14),
        FDOC_TMPL_NBR                  VARCHAR2(14),
        TEMP_DOC_FNL_DT                DATE, /* remove after implementation of workflow API to get this from there */
        FDOC_EXPLAIN_TXT               VARCHAR2(400),
     CONSTRAINT FP_DOC_HEADER_TP1 PRIMARY KEY (
        FDOC_NBR) ,
     CONSTRAINT FP_DOC_HEADER_TC0 UNIQUE (OBJ_ID) 
)
/
