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

alter table KREW_PPL_FLW_T modify(TYP_ID NULL)
/
DECLARE
c NUMBER;
BEGIN
select count(*) into c from all_constraints where CONSTRAINT_NAME = 'KREW_PPL_FLW_MBR_TC1' ;
IF c>0 THEN
EXECUTE IMMEDIATE 'ALTER TABLE KREW_PPL_FLW_MBR_T DROP CONSTRAINT KREW_PPL_FLW_MBR_TC1';
ELSE
DBMS_OUTPUT.PUT_LINE('KREW_PPL_FLW_MBR_TC1 does not exist, so not running statement to change/drop it.');
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
