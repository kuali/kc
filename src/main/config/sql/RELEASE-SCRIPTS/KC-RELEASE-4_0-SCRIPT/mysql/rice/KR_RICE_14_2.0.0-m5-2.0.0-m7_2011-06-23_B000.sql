DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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

-- Sequence table
CREATE TABLE KRMS_CTGRY_S
(
    ID BIGINT(19) NOT NULL AUTO_INCREMENT
    , PRIMARY KEY (ID)
) ENGINE MyISAM
/
ALTER TABLE KRMS_CTGRY_S AUTO_INCREMENT = 1
/

CREATE TABLE KRMS_CTGRY_T
(
    CTGRY_ID VARCHAR(40) NOT NULL
      , NM VARCHAR(255) NOT NULL
      , NMSPC_CD VARCHAR(40) NOT NULL
      , VER_NBR DECIMAL(8) DEFAULT 0
    , PRIMARY KEY (CTGRY_ID)
    , UNIQUE INDEX krms_ctgry_tc0 (NM, NMSPC_CD)
)ENGINE = InnoDB
/

CREATE TABLE KRMS_TERM_SPEC_CTGRY_T
(
  TERM_SPEC_ID VARCHAR(40) NOT NULL
      , CTGRY_ID VARCHAR(40) NOT NULL
  , PRIMARY KEY (TERM_SPEC_ID, CTGRY_ID)
  , constraint krms_term_spec_ctgry_fk1 foreign key (term_spec_id) references krms_term_spec_t (term_spec_id)
  , constraint krms_term_spec_ctgry_fk2 foreign key (ctgry_id) references krms_ctgry_t (ctgry_id)
)
/

CREATE TABLE KRMS_FUNC_CTGRY_T
(
  FUNC_ID VARCHAR(40) NOT NULL
  , CTGRY_ID VARCHAR(40) NOT NULL
  , PRIMARY KEY (FUNC_ID, CTGRY_ID)
  , constraint krms_func_ctgry_fk1 foreign key (func_id) references krms_func_t (func_id)
  , constraint krms_func_ctgry_fk2 foreign key (ctgry_id) references krms_ctgry_t (ctgry_id)
)
/
DELIMITER ;
