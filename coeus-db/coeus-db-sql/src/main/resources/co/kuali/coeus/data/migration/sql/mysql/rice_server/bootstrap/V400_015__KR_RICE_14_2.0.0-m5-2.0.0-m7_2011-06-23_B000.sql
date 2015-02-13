--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /

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
