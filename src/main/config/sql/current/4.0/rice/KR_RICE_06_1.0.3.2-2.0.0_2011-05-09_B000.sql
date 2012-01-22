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


--
--
-- DDL for KRMS repository
--
--

-- -----------------------------------------------------
-- Table krms_typ_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_typ_t'; exception when others then null; end;

CREATE  TABLE  krms_typ_t (
  typ_id VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  srvc_nm VARCHAR2(200) NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (typ_id) )
/



-- -----------------------------------------------------
-- Table krms_attr_defn_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_attr_defn_t'; exception when others then null; end;

CREATE  TABLE  krms_attr_defn_t (
  attr_defn_id VARCHAR2(255)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  lbl VARCHAR2(40) NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  cmpnt_nm VARCHAR2(100) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (attr_defn_id) )
/


-- -----------------------------------------------------
-- Table krms_typ_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_typ_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_typ_attr_t (
  typ_attr_id VARCHAR2(40)  NOT NULL ,
  seq_no NUMBER(5)  NOT NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(255)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (typ_attr_id) ,
  -- CREATE INDEX krms_typ_attr_ti1 (attr_defn_id ASC) ,
  -- CREATE INDEX krms_typ_attr_ti2 (typ_id ASC) ,
  CONSTRAINT krms_typ_attr_tc1 UNIQUE (typ_id, attr_defn_id) ,
  CONSTRAINT krms_typ_attr_fk1
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ),
  CONSTRAINT krms_typ_attr_fk2
    FOREIGN KEY (typ_id )
    REFERENCES krms_typ_t (typ_id ))
/

CREATE INDEX krms_typ_attr_ti1 on krms_typ_attr_t (attr_defn_id ASC) 
/
CREATE INDEX krms_typ_attr_ti2 on krms_typ_attr_t (typ_id ASC) 
/


-- -----------------------------------------------------
-- Table krms_rule_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_rule_t'; exception when others then null; end;

CREATE  TABLE  krms_rule_t (
  rule_id VARCHAR2(40)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  prop_id VARCHAR2(40)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  descr_txt VARCHAR2(4000) NULL ,
  PRIMARY KEY (rule_id) 
  -- CREATE INDEX krms_rule_ti1 (prop_id ASC) ,
)
/

CREATE INDEX krms_rule_ti1 on krms_rule_t (prop_id ASC)
/

-- -----------------------------------------------------
-- Table krms_prop_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_prop_t'; exception when others then null; end;

CREATE  TABLE  krms_prop_t (
  prop_id VARCHAR2(40)  NOT NULL ,
  desc_txt VARCHAR2(100) NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  dscrm_typ_cd VARCHAR2(10)  NOT NULL ,
  cmpnd_op_cd VARCHAR2(40) NULL ,
  rule_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (prop_id) ,
  -- CREATE INDEX krms_prop_ti1 (rule_id ASC) ,
  -- CREATE INDEX krms_prop_fk2 (typ_id ASC) ,
  CONSTRAINT krms_prop_fk1
    FOREIGN KEY (rule_id )
    REFERENCES krms_rule_t (rule_id ) ,
  CONSTRAINT krms_prop_fk2
    FOREIGN KEY (typ_id )
    REFERENCES krms_typ_t (typ_id ) )
/

CREATE INDEX krms_prop_ti1 on krms_prop_t (rule_id ASC)
/
CREATE INDEX krms_prop_fk2 on krms_prop_t (typ_id ASC)
/


ALTER TABLE krms_rule_t ADD CONSTRAINT krms_rule_fk1
    FOREIGN KEY (prop_id )
    REFERENCES krms_prop_t (prop_id )
/


-- -----------------------------------------------------
-- Table krms_rule_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_rule_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_rule_attr_t (
  rule_attr_id VARCHAR2(40)  NOT NULL ,
  rule_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (rule_attr_id) ,
  -- CREATE INDEX krms_rule_attr_ti1 (rule_id ASC) ,
  -- CREATE INDEX krms_rule_attr_ti2 (attr_defn_id ASC) ,
  CONSTRAINT krms_rule_attr_fk1
    FOREIGN KEY (rule_id )
    REFERENCES krms_rule_t (rule_id ) ,
  CONSTRAINT krms_rule_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ) )
/

CREATE INDEX krms_rule_attr_ti1 on krms_rule_attr_t (rule_id ASC) 
/
CREATE INDEX krms_rule_attr_ti2 on krms_rule_attr_t (attr_defn_id ASC) 
/


-- -----------------------------------------------------
-- Table krms_actn_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_actn_t'; exception when others then null; end;

CREATE  TABLE  krms_actn_t (
  actn_id VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(40) NULL ,
  desc_txt VARCHAR2(4000) NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  rule_id VARCHAR2(40) NULL ,
  seq_no NUMBER(5) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (actn_id) ,
  -- CREATE INDEX KRMS_ACTN_TI2 (rule_id ASC) ,
  -- CREATE INDEX KRMS_ACTN_TI1 (typ_id ASC) ,
  CONSTRAINT KRMS_ACTN_TC2 UNIQUE (actn_id, rule_id, seq_no) ,
  -- CREATE INDEX KRMS_ACTN_TI3 (rule_id ASC, seq_no ASC) ,
  CONSTRAINT KRMS_ACTN_FK1
    FOREIGN KEY (rule_id )
    REFERENCES krms_rule_t (rule_id ) )
/

CREATE INDEX KRMS_ACTN_TI2 on krms_actn_t (rule_id ASC)
/
CREATE INDEX KRMS_ACTN_TI1 on krms_actn_t (typ_id ASC)
/
CREATE INDEX KRMS_ACTN_TI3 on krms_actn_t (rule_id ASC, seq_no ASC)
/


-- -----------------------------------------------------
-- Table krms_actn_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_actn_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_actn_attr_t (
  actn_attr_data_id VARCHAR2(40)  NOT NULL ,
  actn_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (actn_attr_data_id) ,
  -- CREATE INDEX krms_actn_attr_ti1 (actn_id ASC) ,
  -- CREATE INDEX krms_actn_attr_ti2 (attr_defn_id ASC) ,
  CONSTRAINT krms_actn_attr_fk1
    FOREIGN KEY (actn_id )
    REFERENCES krms_actn_t (actn_id ) ,
  CONSTRAINT krms_actn_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ) )
/

CREATE INDEX krms_actn_attr_ti1 on krms_actn_attr_t (actn_id ASC) 
/
CREATE INDEX krms_actn_attr_ti2 on krms_actn_attr_t (attr_defn_id ASC) 
/


-- -----------------------------------------------------
-- Table krms_cmpnd_prop_props_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cmpnd_prop_props_t'; exception when others then null; end;

CREATE  TABLE  krms_cmpnd_prop_props_t (
  cmpnd_prop_id VARCHAR2(40)  NOT NULL ,
  prop_id VARCHAR2(40)  NOT NULL ,
  seq_no NUMBER(5)  NOT NULL ,
  PRIMARY KEY (cmpnd_prop_id, prop_id) ,
  -- CREATE INDEX krms_cmpnd_prop_props_ti1 (prop_id ASC) ,
  -- CREATE INDEX krms_cmpnd_prop_props_fk2 (cmpnd_prop_id ASC) ,
  CONSTRAINT krms_cmpnd_prop_props_fk1
    FOREIGN KEY (prop_id )
    REFERENCES krms_prop_t (prop_id ) ,
  CONSTRAINT krms_cmpnd_prop_props_fk2
    FOREIGN KEY (cmpnd_prop_id )
    REFERENCES krms_prop_t (prop_id ) )
/

CREATE INDEX krms_cmpnd_prop_props_ti1 on krms_cmpnd_prop_props_t (prop_id ASC)
/
CREATE INDEX krms_cmpnd_prop_props_fk2 on krms_cmpnd_prop_props_t (cmpnd_prop_id ASC)
/


-- -----------------------------------------------------
-- Table krms_prop_parm_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_prop_parm_t'; exception when others then null; end;

CREATE  TABLE  krms_prop_parm_t (
  prop_parm_id VARCHAR2(40)  NOT NULL ,
  prop_id VARCHAR2(40)  NOT NULL ,
  parm_val VARCHAR2(255) NULL ,
  parm_typ_cd VARCHAR2(1)  NOT NULL ,
  seq_no NUMBER(5)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (prop_parm_id) ,
  -- CREATE INDEX krms_prop_parm_ti1 (prop_id ASC) ,
  CONSTRAINT krms_prop_parm_fk1
    FOREIGN KEY (prop_id )
    REFERENCES krms_prop_t (prop_id ))
/

CREATE INDEX krms_prop_parm_ti1 ON krms_prop_parm_t (prop_id ASC)
/




-- -----------------------------------------------------
-- Table krms_cntxt_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_t (
  cntxt_id VARCHAR2(40)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_id) )
/




-- -----------------------------------------------------
-- Table krms_agenda_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_agenda_t'; exception when others then null; end;

CREATE  TABLE  krms_agenda_t (
  agenda_id VARCHAR2(40)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  init_agenda_itm_id VARCHAR2(40) NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (agenda_id) ,
  -- CREATE INDEX krms_agenda_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_agenda_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_agenda_ti1 on krms_agenda_t (cntxt_id ASC)
/




-- -----------------------------------------------------
-- Table krms_agenda_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_agenda_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_agenda_attr_t (
  agenda_attr_id VARCHAR2(40)  NOT NULL ,
  agenda_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (agenda_attr_id) ,
  -- CREATE INDEX krms_agenda_attr_ti1 (agenda_id ASC) ,
  -- CREATE INDEX krms_agenda_attr_t12 (attr_defn_id ASC) ,
  CONSTRAINT krms_agenda_attr_fk1
    FOREIGN KEY (agenda_id )
    REFERENCES krms_agenda_t (agenda_id ),
  CONSTRAINT krms_agenda_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ))
/

CREATE INDEX krms_agenda_attr_ti1 on krms_agenda_attr_t (agenda_id ASC)
/
CREATE INDEX krms_agenda_attr_t12 on krms_agenda_attr_t (attr_defn_id ASC)
/


-- -----------------------------------------------------
-- Table krms_agenda_itm_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_agenda_itm_t'; exception when others then null; end;

CREATE  TABLE  krms_agenda_itm_t (
  agenda_itm_id VARCHAR2(40)  NOT NULL ,
  rule_id VARCHAR2(40) NULL ,
  sub_agenda_id VARCHAR2(40) NULL ,
  agenda_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  when_true VARCHAR2(40) NULL ,
  when_false VARCHAR2(40) NULL ,
  always VARCHAR2(40) NULL ,
  PRIMARY KEY (agenda_itm_id) ,
  -- CREATE INDEX krms_agenda_itm_ti1 (rule_id ASC) ,
  -- CREATE INDEX krms_agenda_itm_ti2 (agenda_id ASC) ,
  -- CREATE INDEX krms_agenda_itm_ti3 (sub_agenda_id ASC) ,
  -- CREATE INDEX krms_agenda_itm_ti4 (when_true ASC) ,
  -- CREATE INDEX krms_agenda_itm_ti5 (when_false ASC) ,
  -- CREATE INDEX krms_agenda_itm_ti6 (always ASC) ,
  CONSTRAINT krms_agenda_itm_fk1
    FOREIGN KEY (rule_id )
    REFERENCES krms_rule_t (rule_id ) ,
  CONSTRAINT krms_agenda_itm_fk2
    FOREIGN KEY (agenda_id )
    REFERENCES krms_agenda_t (agenda_id ) ,
  CONSTRAINT krms_agenda_itm_fk3
    FOREIGN KEY (sub_agenda_id )
    REFERENCES krms_agenda_t (agenda_id ) ,
  CONSTRAINT krms_agenda_itm_fk4
    FOREIGN KEY (when_true )
    REFERENCES krms_agenda_itm_t (agenda_itm_id ) ,
  CONSTRAINT krms_agenda_itm_fk5
    FOREIGN KEY (when_false )
    REFERENCES krms_agenda_itm_t (agenda_itm_id ) ,
  CONSTRAINT krms_agenda_itm_fk6
    FOREIGN KEY (always )
    REFERENCES krms_agenda_itm_t (agenda_itm_id ) )
/

CREATE INDEX krms_agenda_itm_ti1 on krms_agenda_itm_t (rule_id ASC) 
/
CREATE INDEX krms_agenda_itm_ti2 on krms_agenda_itm_t (agenda_id ASC) 
/
CREATE INDEX krms_agenda_itm_ti3 on krms_agenda_itm_t (sub_agenda_id ASC) 
/
CREATE INDEX krms_agenda_itm_ti4 on krms_agenda_itm_t (when_true ASC) 
/
CREATE INDEX krms_agenda_itm_ti5 on krms_agenda_itm_t (when_false ASC) 
/
CREATE INDEX krms_agenda_itm_ti6 on krms_agenda_itm_t (always ASC) 
/



-- -----------------------------------------------------
-- Table krms_func_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_func_t'; exception when others then null; end;

CREATE  TABLE  krms_func_t (
  func_id VARCHAR2(40)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  desc_txt VARCHAR2(255) NULL ,
  rtrn_typ VARCHAR2(255)  NOT NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (func_id) ,
  -- CREATE INDEX krms_func_ti1 (typ_id ASC) ,
  CONSTRAINT krms_func_fk1
    FOREIGN KEY (typ_id )
    REFERENCES krms_typ_t (typ_id ))
/

CREATE INDEX krms_func_ti1 on krms_func_t (typ_id ASC)
/



-- -----------------------------------------------------
-- Table krms_func_parm_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_func_parm_t'; exception when others then null; end;

CREATE  TABLE  krms_func_parm_t (
  func_parm_id VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  desc_txt VARCHAR2(255) NULL ,
  typ VARCHAR2(255)  NOT NULL ,
  func_id VARCHAR2(40)  NOT NULL ,
  seq_no NUMBER(5)  NOT NULL ,
  PRIMARY KEY (func_parm_id) ,
  -- CREATE INDEX krms_func_parm_ti1 (func_id ASC) ,
  CONSTRAINT krms_func_parm_fk1
    FOREIGN KEY (func_id )
    REFERENCES krms_func_t (func_id ) )
/

CREATE INDEX krms_func_parm_ti1 on krms_func_parm_t (func_id ASC)
/

-- -----------------------------------------------------
-- Table krms_term_spec_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_spec_t'; exception when others then null; end;

CREATE  TABLE  krms_term_spec_t (
  term_spec_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40) NULL ,
  nm VARCHAR2(255)  NOT NULL ,
  typ VARCHAR2(255)  NOT NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8)  NOT NULL ,
  PRIMARY KEY (term_spec_id) ,
  CONSTRAINT krms_asset_tc1 UNIQUE (nm, cntxt_id) ,
  -- CREATE INDEX krms_asset_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_asset_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_asset_ti1 on krms_term_spec_t (cntxt_id ASC)
/




-- -----------------------------------------------------
-- Table krms_term_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_t'; exception when others then null; end;

CREATE  TABLE  krms_term_t (
  term_id VARCHAR2(40)  NOT NULL ,
  term_spec_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8)  NOT NULL ,
  PRIMARY KEY (term_id) ,
  -- CREATE INDEX krms_term_ti1 (term_spec_id ASC) ,
  CONSTRAINT krms_term_t__fk1
    FOREIGN KEY (term_spec_id )
    REFERENCES krms_term_spec_t (term_spec_id ) )
/

CREATE INDEX krms_term_ti1 on krms_term_t (term_spec_id ASC)
/


-- -----------------------------------------------------
-- Table krms_term_parm_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_parm_t'; exception when others then null; end;

CREATE  TABLE  krms_term_parm_t (
  term_parm_id VARCHAR2(40)  NOT NULL ,
  term_id VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(255)  NOT NULL ,
  val VARCHAR2(255) NULL ,
  ver_nbr NUMBER(8)  NOT NULL ,
  PRIMARY KEY (term_parm_id) ,
  -- CREATE INDEX krms_term_parm_ti1 (term_id ASC) ,
  CONSTRAINT krms_term_parm_fk1
    FOREIGN KEY (term_id )
    REFERENCES krms_term_t (term_id ))
/

CREATE INDEX krms_term_parm_ti1 on krms_term_parm_t (term_id ASC)
/


-- -----------------------------------------------------
-- Table krms_cntxt_term_spec_prereq_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_term_spec_prereq_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_term_spec_prereq_t (
  cntxt_term_spec_prereq_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  term_spec_id VARCHAR2(40)  NOT NULL ,
  PRIMARY KEY (cntxt_term_spec_prereq_id) ,
  -- CREATE INDEX krms_cntxt_asset_prereq_ti1 (cntxt_id ASC) ,
  -- CREATE INDEX krms_cntxt_asset_prereq_ti2 (term_spec_id ASC) ,
  CONSTRAINT krms_cntxt_asset_prereq_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) ,
  CONSTRAINT krms_cntxt_asset_prereq_fk2
    FOREIGN KEY (term_spec_id )
    REFERENCES krms_term_spec_t (term_spec_id ) )
/

CREATE INDEX krms_cntxt_asset_prereq_ti1 on krms_cntxt_term_spec_prereq_t (cntxt_id ASC)
/
CREATE INDEX krms_cntxt_asset_prereq_ti2 on krms_cntxt_term_spec_prereq_t (term_spec_id ASC)
/



-- -----------------------------------------------------
-- Table krms_term_rslvr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_rslvr_t'; exception when others then null; end;

CREATE  TABLE  krms_term_rslvr_t (
  term_rslvr_id VARCHAR2(40)  NOT NULL ,
  nmspc_cd VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(100)  NOT NULL ,
  typ_id VARCHAR2(40)  NOT NULL ,
  output_term_spec_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40) NULL ,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (term_rslvr_id) ,
  -- CREATE INDEX krms_term_rslvr_ti1 (cntxt_id ASC) ,
  -- CREATE INDEX krms_term_rslvr_ti2 (typ_id ASC) ,
  CONSTRAINT krms_term_rslvr_tc1 UNIQUE (nmspc_cd, nm, cntxt_id) ,
  CONSTRAINT krms_term_rslvr_fk1
    FOREIGN KEY (output_term_spec_id )
    REFERENCES krms_term_spec_t (term_spec_id ) ,
  CONSTRAINT krms_term_rslvr_fk2
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) ,
  CONSTRAINT krms_term_rslvr_fk3
    FOREIGN KEY (typ_id )
    REFERENCES krms_typ_t (typ_id ) )
/

CREATE INDEX krms_term_rslvr_ti1 on krms_term_rslvr_t (cntxt_id ASC) 
/
CREATE INDEX krms_term_rslvr_ti2 on krms_term_rslvr_t (typ_id ASC) 
/

     --
     ----
------------
---------------
------------
     ----
     --


-- -----------------------------------------------------
-- Table krms_cntxt_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_attr_t (
  cntxt_attr_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  attr_defn_id VARCHAR2(40) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_attr_id) ,
  -- CREATE INDEX krms_cntxt_attr_ti1 (cntxt_id ASC) ,
  -- CREATE INDEX krms_cntxt_attr_ti2 (attr_defn_id ASC) ,
  CONSTRAINT krms_cntxt_attr_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) ,
  CONSTRAINT krms_cntxt_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ) )
/

CREATE INDEX krms_cntxt_attr_ti1 on krms_cntxt_attr_t (cntxt_id ASC)
/
CREATE INDEX krms_cntxt_attr_ti2 on krms_cntxt_attr_t (attr_defn_id ASC)
/


-- -----------------------------------------------------
-- Table krms_cntxt_vld_actn_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_vld_actn_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_vld_actn_t (
  cntxt_vld_actn_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  actn_typ_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_vld_actn_id) ,
  -- CREATE INDEX krms_cntxt_vld_actn_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_cntxt_vld_actn_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_cntxt_vld_actn_ti1 on krms_cntxt_vld_actn_t (cntxt_id ASC)
/


-- -----------------------------------------------------
-- Table krms_term_rslvr_attr_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_rslvr_attr_t'; exception when others then null; end;

CREATE  TABLE  krms_term_rslvr_attr_t (
  term_rslvr_attr_id VARCHAR2(40)  NOT NULL ,
  term_rslvr_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (term_rslvr_attr_id) ,
  -- CREATE INDEX krms_asset_rslvr_attr_ti1 (term_rslvr_id ASC) ,
  -- CREATE INDEX krms_asset_rslvr_attr_ti2 (attr_defn_id ASC) ,
  CONSTRAINT krms_asset_rslvr_attr_fk1
    FOREIGN KEY (term_rslvr_id )
    REFERENCES krms_term_rslvr_t (term_rslvr_id ) ,
  CONSTRAINT krms_asset_rslvr_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id ) )
/

CREATE INDEX krms_asset_rslvr_attr_ti1 on krms_term_rslvr_attr_t (term_rslvr_id ASC)
/
CREATE INDEX krms_asset_rslvr_attr_ti2 on krms_term_rslvr_attr_t (attr_defn_id ASC)
/


-- -----------------------------------------------------
-- Table krms_term_rslvr_input_spec_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_rslvr_input_spec_t'; exception when others then null; end;

CREATE  TABLE  krms_term_rslvr_input_spec_t (
  term_spec_id VARCHAR2(40)  NOT NULL ,
  term_rslvr_id VARCHAR2(40)  NOT NULL ,
  -- CREATE INDEX krms_input_asset_ti1 (term_spec_id ASC) ,
  -- CREATE INDEX krms_input_asset_ti2 (term_rslvr_id ASC) ,
  PRIMARY KEY (term_spec_id, term_rslvr_id) ,
  CONSTRAINT krms_input_asset_fk2
    FOREIGN KEY (term_spec_id )
    REFERENCES krms_term_spec_t (term_spec_id ) ,
  CONSTRAINT krms_input_asset_fk1
    FOREIGN KEY (term_rslvr_id )
    REFERENCES krms_term_rslvr_t (term_rslvr_id ) )
/

CREATE INDEX krms_input_asset_ti1 on krms_term_rslvr_input_spec_t (term_spec_id ASC)
/
CREATE INDEX krms_input_asset_ti2 on krms_term_rslvr_input_spec_t (term_rslvr_id ASC)
/



-- -----------------------------------------------------
-- Table krms_term_rslvr_parm_spec_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_term_rslvr_parm_spec_t'; exception when others then null; end;

CREATE  TABLE  krms_term_rslvr_parm_spec_t (
  term_rslvr_parm_spec_id VARCHAR2(40)  NOT NULL ,
  term_rslvr_id VARCHAR2(40)  NOT NULL ,
  nm VARCHAR2(45)  NOT NULL ,
  ver_nbr NUMBER(8)  NOT NULL ,
  PRIMARY KEY (term_rslvr_parm_spec_id) ,
  -- CREATE INDEX krms_term_reslv_parm_fk1 (term_rslvr_id ASC) ,
  CONSTRAINT krms_term_reslv_parm_fk1
    FOREIGN KEY (term_rslvr_id )
    REFERENCES krms_term_rslvr_t (term_rslvr_id ) )
/

CREATE INDEX krms_term_reslv_parm_fk1 on krms_term_rslvr_parm_spec_t (term_rslvr_id ASC)
/

-- -----------------------------------------------------
-- Table krms_cntxt_vld_func_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_vld_func_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_vld_func_t (
  cntxt_vld_func_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  func_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_vld_func_id) ,
  -- CREATE INDEX krms_cntxt_vld_func_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_cntxt_vld_func_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_cntxt_vld_func_ti1 on krms_cntxt_vld_func_t (cntxt_id ASC)
/


-- -----------------------------------------------------
-- Table krms_cntxt_vld_rule_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_vld_rule_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_vld_rule_t (
  cntxt_vld_rule_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  rule_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_vld_rule_id) ,
  -- CREATE INDEX krms_cntxt_vld_rule_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_cntxt_vld_rule_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_cntxt_vld_rule_ti1 on krms_cntxt_vld_rule_t (cntxt_id ASC)
/

-- -----------------------------------------------------
-- Table krms_cntxt_vld_event_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_vld_event_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_vld_event_t (
  cntxt_vld_event_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  event_nm VARCHAR2(255)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_vld_event_id) ,
  -- CREATE INDEX krms_cntxt_vld_event_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_cntxt_vld_event_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_cntxt_vld_event_ti1 on krms_cntxt_vld_event_t (cntxt_id ASC)
/


--
--
-- sequences
--
--


CREATE SEQUENCE krms_typ_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_prop_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_rule_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_agenda_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_attr_defn_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_typ_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_actn_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_actn_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_agenda_itm_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_rule_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_vld_actn_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_agenda_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cmpnd_prop_props_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_prop_parm_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_spec_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_rslvr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_rslvr_attr_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_rslvr_input_spec_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_term_spec_prereq_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_rslvr_parm_spec_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_term_parm_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_func_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_func_parm_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_vld_func_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_vld_rule_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

CREATE SEQUENCE krms_cntxt_vld_event_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
