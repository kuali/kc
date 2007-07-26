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
CREATE TABLE FS_ADHOC_RTE_ACTN_RECP_T (
	    ACTN_RQST_RECP_TYP_CD          NUMBER(1) CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN1 NOT NULL,
	    ACTN_RQST_CD                   VARCHAR2(30) CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN2 NOT NULL,
        ACTN_RQST_RECP_ID              VARCHAR2(70) CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN5 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TN6 NOT NULL,
     CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TP1 PRIMARY KEY (
	    ACTN_RQST_RECP_TYP_CD,
	    ACTN_RQST_CD,
        ACTN_RQST_RECP_ID) USING INDEX TABLESPACE KUL_IDX03,
     CONSTRAINT FS_ADHOC_RTE_ACTN_RECP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE KUL_IDX03
)
TABLESPACE KUL03
/