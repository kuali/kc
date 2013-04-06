--
-- Copyright 2005-2013 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--


--
-- KULRICE-7786: Document Specific Doc Search Application Document Status should be available
-- (and groupable) on the basic version of search
--

-- add category name (which is part of composite fk) to app doc stat
alter table KREW_DOC_TYP_APP_DOC_STAT_T add CAT_NM varchar2(64)
/

-- add index for queries from category to status.  Using non-standard index name to follow table precedent.
CREATE INDEX KREW_DOC_TYP_APP_DOC_STAT_T2 on KREW_DOC_TYP_APP_DOC_STAT_T (DOC_TYP_ID, CAT_NM)
/

-- add sequence number column for ordering to app doc stat
alter table KREW_DOC_TYP_APP_DOC_STAT_T add SEQ_NO number(5)
/

-- create category table
CREATE TABLE KREW_DOC_TYP_APP_STAT_CAT_T  (
    DOC_TYP_ID   varchar2(40) NOT NULL,
	CAT_NM	varchar2(64) NOT NULL,
    VER_NBR number(8) DEFAULT '0',
    OBJ_ID varchar2(36) NOT NULL,

	PRIMARY KEY(DOC_TYP_ID, CAT_NM),
	CONSTRAINT KREW_DOC_TYP_APP_STAT_CAT_FK1 foreign key (DOC_TYP_ID) references KREW_DOC_TYP_T (DOC_TYP_ID)
)
/

-- object id must be unique
ALTER TABLE KREW_DOC_TYP_APP_STAT_CAT_T
	ADD CONSTRAINT KREW_DOC_TYP_APP_STAT_CAT_TC1
	UNIQUE (OBJ_ID)
/

-- add constraint to tie app doc stat and category together
alter table KREW_DOC_TYP_APP_DOC_STAT_T add constraint KREW_DOC_TYP_APP_DOC_STAT_FK1
foreign key (DOC_TYP_ID, CAT_NM) references KREW_DOC_TYP_APP_STAT_CAT_T (DOC_TYP_ID, CAT_NM)
/