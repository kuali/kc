--
-- Copyright 2005-2012 The Kuali Foundation
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

alter table KREW_PPL_FLW_T modify(TYP_ID NULL)
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_PPL_FLW_MBR_TC1' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_PPL_FLW_MBR_T DROP CONSTRAINT KREW_PPL_FLW_MBR_TC1';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_DOC_TYP_TC0 does not exist, so not running statement to change/drop it.');
END IF;
END;
/
alter table KREW_PPL_FLW_MBR_T DROP COLUMN dlgt_frm_id
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_dlgt_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_dlgt_t (
  ppl_flw_dlgt_id VARCHAR2(40) NOT NULL ,
  ppl_flw_mbr_id VARCHAR2(40) NOT NULL ,
  mbr_id VARCHAR2(40) NOT NULL ,
  mbr_typ_cd VARCHAR2(1) NOT NULL ,
  dlgn_typ_cd VARCHAR2(1) NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  PRIMARY KEY (ppl_flw_dlgt_id) ,
  CONSTRAINT krew_ppl_flw_dlgt_fk1
    FOREIGN KEY (ppl_flw_mbr_id )
    REFERENCES krew_ppl_flw_mbr_t (ppl_flw_mbr_id ))
/

CREATE INDEX krew_ppl_flw_dlgt_ti1 ON krew_ppl_flw_dlgt_t (ppl_flw_mbr_id)
/

CREATE SEQUENCE krew_ppl_flw_dlgt_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
