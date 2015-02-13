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

alter table KREW_PPL_FLW_T CHANGE TYP_ID TYP_ID varchar(40) NULL
/

alter table KREW_PPL_FLW_MBR_T DROP FOREIGN KEY krew_ppl_flw_mbr_fk2
/
alter table KREW_PPL_FLW_MBR_T DROP KEY krew_ppl_flw_mbr_fk2
/
alter table KREW_PPL_FLW_MBR_T DROP COLUMN dlgt_frm_id
/

drop INDEX krew_ppl_flw_mbr_tc1 ON krew_ppl_flw_mbr_t
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_dlgt_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_dlgt_t (
  ppl_flw_dlgt_id VARCHAR(40) NOT NULL ,
  ppl_flw_mbr_id VARCHAR(40) NOT NULL ,
  mbr_id VARCHAR(40) NOT NULL ,
  mbr_typ_cd VARCHAR(1) NOT NULL ,
  dlgn_typ_cd VARCHAR(1) NOT NULL ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (ppl_flw_dlgt_id) ,
  CONSTRAINT krew_ppl_flw_dlgt_fk1
    FOREIGN KEY (ppl_flw_mbr_id )
    REFERENCES krew_ppl_flw_mbr_t (ppl_flw_mbr_id ))
ENGINE = InnoDB
/

CREATE INDEX krew_ppl_flw_dlgt_ti1 ON krew_ppl_flw_dlgt_t (ppl_flw_mbr_id) 
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_dlgt_s
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_dlgt_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/
DELIMITER ;
