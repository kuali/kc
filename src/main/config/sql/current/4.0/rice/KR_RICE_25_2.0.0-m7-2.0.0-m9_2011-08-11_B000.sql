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



-- -----------------------------------------------------
-- Table krew_typ_t
-- -----------------------------------------------------

CREATE  TABLE krew_typ_t (
  typ_id VARCHAR2(40) NOT NULL ,
  nm VARCHAR2(100) NOT NULL ,
  nmspc_cd VARCHAR2(40) NOT NULL ,
  srvc_nm VARCHAR2(200) NULL ,
  actv VARCHAR2(1) DEFAULT 'Y' NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  PRIMARY KEY (typ_id) )
/

CREATE UNIQUE INDEX krew_typ_tc1 ON krew_typ_t (nm, nmspc_cd)
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_t (
  ppl_flw_id VARCHAR2(40) NOT NULL ,
  nm VARCHAR2(100) NOT NULL ,
  nmspc_cd VARCHAR2(40) NOT NULL ,
  typ_id VARCHAR2(40) NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y' NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  desc_txt VARCHAR2(4000) NULL ,
  PRIMARY KEY (ppl_flw_id) ,
  CONSTRAINT krew_ppl_flw_fk1
    FOREIGN KEY (typ_id )
    REFERENCES krew_typ_t (typ_id ))
/

CREATE UNIQUE INDEX krew_ppl_flw_tc1 ON krew_ppl_flw_t (nm, nmspc_cd)
/
CREATE INDEX krew_ppl_flw_fk1 ON krew_ppl_flw_t (typ_id)
/

-- -----------------------------------------------------
-- Table krew_attr_defn_t
-- -----------------------------------------------------

CREATE  TABLE krew_attr_defn_t (
  attr_defn_id VARCHAR2(40) NOT NULL ,
  nm VARCHAR2(100) NOT NULL ,
  nmspc_cd VARCHAR2(40) NOT NULL ,
  lbl VARCHAR2(40) NULL ,
  actv VARCHAR2(1) DEFAULT 'Y' NOT NULL ,
  cmpnt_nm VARCHAR2(100) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  desc_txt VARCHAR2(40) NULL ,
  PRIMARY KEY (attr_defn_id) )
/

CREATE UNIQUE INDEX krew_attr_defn_tc1 ON krew_attr_defn_t (nm, nmspc_cd)
/

-- -----------------------------------------------------
-- Table krew_typ_attr_t
-- -----------------------------------------------------

CREATE  TABLE krew_typ_attr_t (
  typ_attr_id VARCHAR2(40) NOT NULL ,
  seq_no NUMBER(5) NOT NULL ,
  typ_id VARCHAR2(40) NOT NULL ,
  attr_defn_id VARCHAR2(255) NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y' NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  PRIMARY KEY (typ_attr_id) ,
  CONSTRAINT krew_typ_attr_fk1
    FOREIGN KEY (attr_defn_id )
    REFERENCES krew_attr_defn_t (attr_defn_id ),
  CONSTRAINT krew_typ_attr_fk2
    FOREIGN KEY (typ_id )
    REFERENCES krew_typ_t (typ_id ))
/

CREATE INDEX krew_typ_attr_ti1 ON krew_typ_attr_t (attr_defn_id)
/
CREATE INDEX krew_typ_attr_ti2 ON krew_typ_attr_t (typ_id)
/
CREATE UNIQUE INDEX krew_typ_attr_tc1 ON krew_typ_attr_t (typ_id, attr_defn_id)
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_mbr_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_mbr_t (
  ppl_flw_mbr_id VARCHAR2(40) NOT NULL ,
  ppl_flw_id VARCHAR2(40) NOT NULL ,
  mbr_typ_cd VARCHAR2(1) NOT NULL ,
  mbr_id VARCHAR2(40) NOT NULL ,
  prio NUMBER(8) NULL ,
  dlgt_frm_id VARCHAR2(40) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  PRIMARY KEY (ppl_flw_mbr_id) ,
  CONSTRAINT krew_ppl_flw_mbr_fk1
    FOREIGN KEY (ppl_flw_id )
    REFERENCES krew_ppl_flw_t (ppl_flw_id ),
  CONSTRAINT krew_ppl_flw_mbr_fk2
    FOREIGN KEY (dlgt_frm_id )
    REFERENCES krew_ppl_flw_mbr_t (ppl_flw_mbr_id ))
/

CREATE INDEX krew_ppl_flw_mbr_ti1 ON krew_ppl_flw_mbr_t (ppl_flw_id)
/
CREATE INDEX krew_ppl_flw_mbr_ti2 ON krew_ppl_flw_mbr_t (ppl_flw_id, prio)
/
CREATE UNIQUE INDEX krew_ppl_flw_mbr_tc1 ON krew_ppl_flw_mbr_t (ppl_flw_id, mbr_typ_cd, mbr_id, dlgt_frm_id)
/
CREATE INDEX krew_ppl_flw_mbr_fk2 ON krew_ppl_flw_mbr_t (dlgt_frm_id)
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_attr_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_attr_t (
  ppl_flw_attr_id VARCHAR2(40) NOT NULL ,
  ppl_flw_id VARCHAR2(40) NOT NULL ,
  attr_defn_id VARCHAR2(40) NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL ,
  PRIMARY KEY (ppl_flw_attr_id) ,
  CONSTRAINT krew_ppl_flw_attr_fk1
    FOREIGN KEY (ppl_flw_id )
    REFERENCES krew_ppl_flw_t (ppl_flw_id ),
  CONSTRAINT krew_ppl_flw_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krew_attr_defn_t (attr_defn_id ))
/

CREATE INDEX krew_ppl_flw_attr_ti1 ON krew_ppl_flw_attr_t (ppl_flw_id)
/
CREATE INDEX krew_ppl_flw_attr_ti2 ON krew_ppl_flw_attr_t (attr_defn_id)
/

-- -----------------------------------------------------
-- Table krew_typ_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_typ_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- -----------------------------------------------------
-- Table krew_typ_attr_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_typ_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- -----------------------------------------------------
-- Table krew_attr_defn_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_attr_defn_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_ppl_flw_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_attr_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_ppl_flw_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- -----------------------------------------------------
-- Table krew_ppl_flw_mbr_s
-- -----------------------------------------------------
CREATE SEQUENCE krew_ppl_flw_mbr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
