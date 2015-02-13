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

--
--
-- DDL for KRMS repository
--
--

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0
/
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0
/
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL'
/


-- -----------------------------------------------------
-- Table `krms_typ_t`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `krms_typ_t` (
  `typ_id` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `srvc_nm` VARCHAR(200) NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`typ_id`) )
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_prop_t`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `krms_prop_t` (
  `prop_id` VARCHAR(40) NOT NULL ,
  `desc_txt` VARCHAR(100) NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `dscrm_typ_cd` VARCHAR(10) NOT NULL ,
  `cmpnd_op_cd` VARCHAR(40) NULL ,
  `rule_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`prop_id`) ,
  INDEX `krms_prop_ti1` (`rule_id` ASC) ,
  INDEX `krms_prop_fk2` (`typ_id` ASC) ,
  CONSTRAINT `krms_prop_fk1`
    FOREIGN KEY (`rule_id` )
    REFERENCES `krms_rule_t` (`rule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_prop_fk2`
    FOREIGN KEY (`typ_id` )
    REFERENCES `krms_typ_t` (`typ_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_rule_t`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `krms_rule_t` (
  `rule_id` VARCHAR(40) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `prop_id` VARCHAR(40) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  `descr_txt` VARCHAR(4000) NULL ,
  PRIMARY KEY (`rule_id`) ,
  INDEX `krms_rule_ti1` (`prop_id` ASC) ,
  CONSTRAINT `krms_rule_fk1`
    FOREIGN KEY (`prop_id` )
    REFERENCES `krms_prop_t` (`prop_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_t` (
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_id`) )
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_agenda_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_agenda_t` (
  `agenda_id` VARCHAR(40) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `init_agenda_itm_id` VARCHAR(40) NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`agenda_id`) ,
  INDEX `krms_agenda_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_agenda_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_attr_defn_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_attr_defn_t` (
  `attr_defn_id` VARCHAR(255) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `lbl` VARCHAR(40) NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `cmpnt_nm` VARCHAR(100) NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`attr_defn_id`) )
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_typ_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_typ_attr_t` (
  `typ_attr_id` VARCHAR(40) NOT NULL ,
  `seq_no` TINYINT NOT NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `attr_defn_id` VARCHAR(255) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`typ_attr_id`) ,
  INDEX `krms_typ_attr_ti1` (`attr_defn_id` ASC) ,
  INDEX `krms_typ_attr_ti2` (`typ_id` ASC) ,
  UNIQUE INDEX `krms_typ_attr_tc1` (`typ_id` ASC, `attr_defn_id` ASC) ,
  CONSTRAINT `krms_typ_attr_fk1`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_typ_attr_fk2`
    FOREIGN KEY (`typ_id` )
    REFERENCES `krms_typ_t` (`typ_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_actn_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_actn_t` (
  `actn_id` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(40) NULL ,
  `desc_txt` VARCHAR(4000) NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `rule_id` VARCHAR(40) NULL ,
  `seq_no` TINYINT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`actn_id`) ,
  index `krms_actn_ti2` (`rule_id` asc) ,
  index `krms_actn_ti1` (`typ_id` asc) ,
  unique index `krms_actn_tc2` (`actn_id` asc, `rule_id` asc, `seq_no` asc) ,
  index `krms_actn_ti3` (`rule_id` asc, `seq_no` asc) ,
  constraint `krms_actn_fk1`
    FOREIGN KEY (`rule_id` )
    REFERENCES `krms_rule_t` (`rule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_actn_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_actn_attr_t` (
  `actn_attr_data_id` VARCHAR(40) NOT NULL ,
  `actn_id` VARCHAR(40) NOT NULL ,
  `attr_defn_id` VARCHAR(40) NOT NULL ,
  `attr_val` VARCHAR(400) NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`actn_attr_data_id`) ,
  INDEX `krms_actn_attr_ti1` (`actn_id` ASC) ,
  INDEX `krms_actn_attr_ti2` (`attr_defn_id` ASC) ,
  CONSTRAINT `krms_actn_attr_fk1`
    FOREIGN KEY (`actn_id` )
    REFERENCES `krms_actn_t` (`actn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_actn_attr_fk2`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_agenda_itm_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_agenda_itm_t` (
  `agenda_itm_id` VARCHAR(40) NOT NULL ,
  `rule_id` VARCHAR(40) NULL ,
  `sub_agenda_id` VARCHAR(40) NULL ,
  `agenda_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  `when_true` VARCHAR(40) NULL ,
  `when_false` VARCHAR(40) NULL ,
  `always` VARCHAR(40) NULL ,
  PRIMARY KEY (`agenda_itm_id`) ,
  INDEX `krms_agenda_itm_ti1` (`rule_id` ASC) ,
  INDEX `krms_agenda_itm_ti2` (`agenda_id` ASC) ,
  INDEX `krms_agenda_itm_ti3` (`sub_agenda_id` ASC) ,
  INDEX `krms_agenda_itm_ti4` (`when_true` ASC) ,
  INDEX `krms_agenda_itm_ti5` (`when_false` ASC) ,
  INDEX `krms_agenda_itm_ti6` (`always` ASC) ,
  CONSTRAINT `krms_agenda_itm_fk1`
    FOREIGN KEY (`rule_id` )
    REFERENCES `krms_rule_t` (`rule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_itm_fk2`
    FOREIGN KEY (`agenda_id` )
    REFERENCES `krms_agenda_t` (`agenda_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_itm_fk3`
    FOREIGN KEY (`sub_agenda_id` )
    REFERENCES `krms_agenda_t` (`agenda_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_itm_fk4`
    FOREIGN KEY (`when_true` )
    REFERENCES `krms_agenda_itm_t` (`agenda_itm_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_itm_fk5`
    FOREIGN KEY (`when_false` )
    REFERENCES `krms_agenda_itm_t` (`agenda_itm_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_itm_fk6`
    FOREIGN KEY (`always` )
    REFERENCES `krms_agenda_itm_t` (`agenda_itm_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_rule_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_rule_attr_t` (
  `rule_attr_id` VARCHAR(40) NOT NULL ,
  `rule_id` VARCHAR(40) NOT NULL ,
  `attr_defn_id` VARCHAR(40) NOT NULL ,
  `attr_val` VARCHAR(400) NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`rule_attr_id`) ,
  INDEX `krms_rule_attr_ti1` (`rule_id` ASC) ,
  INDEX `krms_rule_attr_ti2` (`attr_defn_id` ASC) ,
  CONSTRAINT `krms_rule_attr_fk1`
    FOREIGN KEY (`rule_id` )
    REFERENCES `krms_rule_t` (`rule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_rule_attr_fk2`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_attr_t` (
  `cntxt_attr_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `attr_val` VARCHAR(400) NULL ,
  `attr_defn_id` VARCHAR(40) NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_attr_id`) ,
  INDEX `krms_cntxt_attr_ti1` (`cntxt_id` ASC) ,
  INDEX `krms_cntxt_attr_ti2` (`attr_defn_id` ASC) ,
  CONSTRAINT `krms_cntxt_attr_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_cntxt_attr_fk2`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_vld_actn_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_vld_actn_t` (
  `cntxt_vld_actn_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `actn_typ_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_vld_actn_id`) ,
  INDEX `krms_cntxt_vld_actn_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_cntxt_vld_actn_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_agenda_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_agenda_attr_t` (
  `agenda_attr_id` VARCHAR(40) NOT NULL ,
  `agenda_id` VARCHAR(40) NOT NULL ,
  `attr_val` VARCHAR(400) NULL ,
  `attr_defn_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`agenda_attr_id`) ,
  INDEX `krms_agenda_attr_ti1` (`agenda_id` ASC) ,
  INDEX `krms_agenda_attr_t12` (`attr_defn_id` ASC) ,
  CONSTRAINT `krms_agenda_attr_fk1`
    FOREIGN KEY (`agenda_id` )
    REFERENCES `krms_agenda_t` (`agenda_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_agenda_attr_fk2`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cmpnd_prop_props_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cmpnd_prop_props_t` (
  `cmpnd_prop_id` VARCHAR(40) NOT NULL ,
  `prop_id` VARCHAR(40) NOT NULL ,
  `seq_no` TINYINT NOT NULL ,
  PRIMARY KEY (`cmpnd_prop_id`, `prop_id`) ,
  INDEX `krms_cmpnd_prop_props_ti1` (`prop_id` ASC) ,
  INDEX `krms_cmpnd_prop_props_fk2` (`cmpnd_prop_id` ASC) ,
  CONSTRAINT `krms_cmpnd_prop_props_fk1`
    FOREIGN KEY (`prop_id` )
    REFERENCES `krms_prop_t` (`prop_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_cmpnd_prop_props_fk2`
    FOREIGN KEY (`cmpnd_prop_id` )
    REFERENCES `krms_prop_t` (`prop_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_prop_parm_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_prop_parm_t` (
  `prop_parm_id` VARCHAR(40) NOT NULL ,
  `prop_id` VARCHAR(40) NOT NULL ,
  `parm_val` VARCHAR(255) NULL ,
  `parm_typ_cd` VARCHAR(1) NOT NULL ,
  `seq_no` TINYINT NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`prop_parm_id`) ,
  INDEX `krms_prop_parm_ti1` (`prop_id` ASC) ,
  CONSTRAINT `krms_prop_parm_fk1`
    FOREIGN KEY (`prop_id` )
    REFERENCES `krms_prop_t` (`prop_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_spec_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_spec_t` (
  `term_spec_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NULL ,
  `nm` VARCHAR(255) NOT NULL ,
  `typ` VARCHAR(255) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL ,
  PRIMARY KEY (`term_spec_id`) ,
  UNIQUE INDEX `krms_asset_tc1` (`nm` ASC, `cntxt_id` ASC) ,
  INDEX `krms_asset_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_asset_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_rslvr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_rslvr_t` (
  `term_rslvr_id` VARCHAR(40) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `output_term_spec_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`term_rslvr_id`) ,
  INDEX `krms_term_rslvr_ti1` (`cntxt_id` ASC) ,
  INDEX `krms_term_rslvr_ti2` (`typ_id` ASC) ,
  UNIQUE INDEX `krms_term_rslvr_tc1` (`nmspc_cd` ASC, `nm` ASC, `cntxt_id` ASC) ,
  CONSTRAINT `krms_term_rslvr_fk1`
    FOREIGN KEY (`output_term_spec_id` )
    REFERENCES `krms_term_spec_t` (`term_spec_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_term_rslvr_fk2`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_term_rslvr_fk3`
    FOREIGN KEY (`typ_id` )
    REFERENCES `krms_typ_t` (`typ_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_rslvr_attr_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_rslvr_attr_t` (
  `term_rslvr_attr_id` VARCHAR(40) NOT NULL ,
  `term_rslvr_id` VARCHAR(40) NOT NULL ,
  `attr_defn_id` VARCHAR(40) NOT NULL ,
  `attr_val` VARCHAR(400) NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`term_rslvr_attr_id`) ,
  INDEX `krms_asset_rslvr_attr_ti1` (`term_rslvr_id` ASC) ,
  INDEX `krms_asset_rslvr_attr_ti2` (`attr_defn_id` ASC) ,
  CONSTRAINT `krms_asset_rslvr_attr_fk1`
    FOREIGN KEY (`term_rslvr_id` )
    REFERENCES `krms_term_rslvr_t` (`term_rslvr_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_asset_rslvr_attr_fk2`
    FOREIGN KEY (`attr_defn_id` )
    REFERENCES `krms_attr_defn_t` (`attr_defn_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_rslvr_input_spec_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_rslvr_input_spec_t` (
  `term_spec_id` VARCHAR(40) NOT NULL ,
  `term_rslvr_id` VARCHAR(40) NOT NULL ,
  INDEX `krms_input_asset_ti1` (`term_spec_id` ASC) ,
  INDEX `krms_input_asset_ti2` (`term_rslvr_id` ASC) ,
  PRIMARY KEY (`term_spec_id`, `term_rslvr_id`) ,
  CONSTRAINT `krms_input_asset_fk2`
    FOREIGN KEY (`term_spec_id` )
    REFERENCES `krms_term_spec_t` (`term_spec_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_input_asset_fk1`
    FOREIGN KEY (`term_rslvr_id` )
    REFERENCES `krms_term_rslvr_t` (`term_rslvr_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_term_spec_prereq_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_term_spec_prereq_t` (
  `cntxt_term_spec_prereq_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `term_spec_id` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`cntxt_term_spec_prereq_id`) ,
  INDEX `krms_cntxt_asset_prereq_ti1` (`cntxt_id` ASC) ,
  INDEX `krms_cntxt_asset_prereq_ti2` (`term_spec_id` ASC) ,
  CONSTRAINT `krms_cntxt_asset_prereq_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `krms_cntxt_asset_prereq_fk2`
    FOREIGN KEY (`term_spec_id` )
    REFERENCES `krms_term_spec_t` (`term_spec_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_t` (
  `term_id` VARCHAR(40) NOT NULL ,
  `term_spec_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL ,
  PRIMARY KEY (`term_id`) ,
  INDEX `krms_term_ti1` (`term_spec_id` ASC) ,
  CONSTRAINT `krms_term_t__fk1`
    FOREIGN KEY (`term_spec_id` )
    REFERENCES `krms_term_spec_t` (`term_spec_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_rslvr_parm_spec_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_rslvr_parm_spec_t` (
  `term_rslvr_parm_spec_id` VARCHAR(40) NOT NULL ,
  `term_rslvr_id` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(45) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL ,
  PRIMARY KEY (`term_rslvr_parm_spec_id`) ,
  INDEX `krms_term_reslv_parm_fk1` (`term_rslvr_id` ASC) ,
  CONSTRAINT `krms_term_reslv_parm_fk1`
    FOREIGN KEY (`term_rslvr_id` )
    REFERENCES `krms_term_rslvr_t` (`term_rslvr_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_term_parm_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_term_parm_t` (
  `term_parm_id` VARCHAR(40) NOT NULL ,
  `term_id` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(255) NOT NULL ,
  `val` VARCHAR(255) NULL ,
  `ver_nbr` DECIMAL NOT NULL ,
  PRIMARY KEY (`term_parm_id`) ,
  INDEX `krms_term_parm_ti1` (`term_id` ASC) ,
  CONSTRAINT `krms_term_parm_fk1`
    FOREIGN KEY (`term_id` )
    REFERENCES `krms_term_t` (`term_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_func_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_func_t` (
  `func_id` VARCHAR(40) NOT NULL ,
  `nmspc_cd` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `desc_txt` VARCHAR(255) NULL ,
  `rtrn_typ` VARCHAR(255) NOT NULL ,
  `typ_id` VARCHAR(40) NOT NULL ,
  `actv` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`func_id`) ,
  INDEX `krms_func_ti1` (`typ_id` ASC) ,
  CONSTRAINT `krms_func_fk1`
    FOREIGN KEY (`typ_id` )
    REFERENCES `krms_typ_t` (`typ_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_func_parm_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_func_parm_t` (
  `func_parm_id` VARCHAR(40) NOT NULL ,
  `nm` VARCHAR(100) NOT NULL ,
  `desc_txt` VARCHAR(255) NULL ,
  `typ` VARCHAR(255) NOT NULL ,
  `func_id` VARCHAR(40) NOT NULL ,
  `seq_no` TINYINT NOT NULL ,
  PRIMARY KEY (`func_parm_id`) ,
  INDEX `krms_func_parm_ti1` (`func_id` ASC) ,
  CONSTRAINT `krms_func_parm_fk1`
    FOREIGN KEY (`func_id` )
    REFERENCES `krms_func_t` (`func_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_vld_func_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_vld_func_t` (
  `cntxt_vld_func_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `func_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_vld_func_id`) ,
  INDEX `krms_cntxt_vld_func_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_cntxt_vld_func_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_vld_rule_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_vld_rule_t` (
  `cntxt_vld_rule_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `rule_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_vld_rule_id`) ,
  INDEX `krms_cntxt_vld_rule_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_cntxt_vld_rule_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/


-- -----------------------------------------------------
-- Table `krms_cntxt_vld_event_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_vld_event_t` (
  `cntxt_vld_event_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `event_nm` VARCHAR(255) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_vld_event_id`) ,
  INDEX `krms_cntxt_vld_event_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_cntxt_vld_event_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/



SET SQL_MODE=@OLD_SQL_MODE
/
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS
/
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS
/


--
--
-- Sequence tables
--
--


create table krms_typ_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_typ_s auto_increment = 1000
/


create table krms_prop_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_prop_s auto_increment = 1000
/


create table krms_rule_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_rule_s auto_increment = 1000
/


create table krms_cntxt_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_s auto_increment = 1000
/


create table krms_agenda_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_agenda_s auto_increment = 1000
/


create table krms_attr_defn_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_attr_defn_s auto_increment = 1000
/


create table krms_typ_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_typ_attr_s auto_increment = 1000
/


create table krms_actn_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_actn_s auto_increment = 1000
/


create table krms_actn_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_actn_attr_s auto_increment = 1000
/


create table krms_agenda_itm_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_agenda_itm_s auto_increment = 1000
/


create table krms_rule_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_rule_attr_s auto_increment = 1000
/


create table krms_cntxt_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_attr_s auto_increment = 1000
/


create table krms_cntxt_vld_actn_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_vld_actn_s auto_increment = 1000
/


create table krms_agenda_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_agenda_attr_s auto_increment = 1000
/


create table krms_cmpnd_prop_props_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cmpnd_prop_props_s auto_increment = 1000
/


create table krms_prop_parm_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_prop_parm_s auto_increment = 1000
/


create table krms_term_spec_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_spec_s auto_increment = 1000
/


create table krms_term_rslvr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_rslvr_s auto_increment = 1000
/


create table krms_term_rslvr_attr_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_rslvr_attr_s auto_increment = 1000
/


create table krms_term_rslvr_input_spec_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_rslvr_input_spec_s auto_increment = 1000
/


create table krms_cntxt_term_spec_prereq_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_term_spec_prereq_s auto_increment = 1000
/


create table krms_term_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_s auto_increment = 1000
/


create table krms_term_rslvr_parm_spec_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_rslvr_parm_spec_s auto_increment = 1000
/


create table krms_term_parm_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_term_parm_s auto_increment = 1000
/


create table krms_func_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_func_s auto_increment = 1000
/


create table krms_func_parm_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_func_parm_s auto_increment = 1000
/


create table krms_cntxt_vld_func_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_vld_func_s auto_increment = 1000
/


create table krms_cntxt_vld_rule_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_vld_rule_s auto_increment = 1000
/


create table krms_cntxt_vld_event_s (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table krms_cntxt_vld_event_s auto_increment = 1000
/
DELIMITER ;
