DELIMITER /
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

-- drop INDEX krew_ppl_flw_mbr_fk2 ON krew_ppl_flw_mbr_t
/

-- alter table krew_ppl_flw_mbr_t drop foreign key krew_ppl_flw_mbr_fk2
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
