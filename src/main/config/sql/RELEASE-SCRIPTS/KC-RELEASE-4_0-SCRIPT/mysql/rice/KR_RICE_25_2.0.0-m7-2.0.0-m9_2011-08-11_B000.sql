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


-- DROP TABLE IF EXISTS krew_ppl_flw_attr_t ;
-- DROP TABLE IF EXISTS krew_ppl_flw_mbr_t ;
-- DROP TABLE IF EXISTS krew_typ_attr_t ;
-- DROP TABLE IF EXISTS krew_attr_defn_t ;
-- DROP TABLE IF EXISTS krew_ppl_flw_t ;
-- DROP TABLE IF EXISTS krew_typ_t ;
-- DROP TABLE IF EXISTS krew_ppl_flw_mbr_s ;
-- DROP TABLE IF EXISTS krew_ppl_flw_attr_s ;
-- DROP TABLE IF EXISTS krew_ppl_flw_s ;
-- DROP TABLE IF EXISTS krew_attr_defn_s ;
-- DROP TABLE IF EXISTS krew_typ_attr_s ;
-- DROP TABLE IF EXISTS krew_typ_s ;


-- -----------------------------------------------------
-- Table krew_typ_t
-- -----------------------------------------------------

CREATE  TABLE krew_typ_t (
  typ_id VARCHAR(40) NOT NULL ,
  nm VARCHAR(100) NOT NULL ,
  nmspc_cd VARCHAR(40) NOT NULL ,
  srvc_nm VARCHAR(200) NULL ,
  actv VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (typ_id) )
ENGINE = InnoDB
/

CREATE UNIQUE INDEX krew_typ_tc1 ON krew_typ_t (nm, nmspc_cd) 
/


-- -----------------------------------------------------
-- Table krew_ppl_flw_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_t (
  ppl_flw_id VARCHAR(40) NOT NULL ,
  nm VARCHAR(100) NOT NULL ,
  nmspc_cd VARCHAR(40) NOT NULL ,
  typ_id VARCHAR(40) NOT NULL ,
  actv VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  desc_txt VARCHAR(4000) NULL ,
  PRIMARY KEY (ppl_flw_id) ,
  CONSTRAINT krew_ppl_flw_fk1
    FOREIGN KEY (typ_id )
    REFERENCES krew_typ_t (typ_id ))
ENGINE = InnoDB
/

CREATE UNIQUE INDEX krew_ppl_flw_tc1 ON krew_ppl_flw_t (nm, nmspc_cd) 
/

DELIMITER ;
DROP PROCEDURE IF EXISTS p;

DELIMITER //
CREATE PROCEDURE p ()
BEGIN
    DECLARE c INT;

    SELECT COUNT(*) INTO c FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_NAME = 'krew_ppl_flw_t' AND INDEX_NAME = 'krew_ppl_flw_fk1';
    IF c=0 THEN
        set @create_index := 'CREATE INDEX krew_ppl_flw_fk1 ON krew_ppl_flw_t (typ_id)';
        prepare create_index_stmt from @create_index;
        execute create_index_stmt;
        deallocate prepare create_index_stmt;
    ELSE
        SELECT 'krew_ppl_flw_fk1 exists, so not running statement to add it.';
    END IF;
END //
DELIMITER ;

CALL p ();

DROP PROCEDURE IF EXISTS p;
DELIMITER /


-- -----------------------------------------------------
-- Table krew_attr_defn_t
-- -----------------------------------------------------

CREATE  TABLE krew_attr_defn_t (
  attr_defn_id VARCHAR(40) NOT NULL ,
  nm VARCHAR(100) NOT NULL ,
  nmspc_cd VARCHAR(40) NOT NULL ,
  lbl VARCHAR(40) NULL ,
  actv VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  cmpnt_nm VARCHAR(100) NULL ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  desc_txt VARCHAR(40) NULL ,
  PRIMARY KEY (attr_defn_id) )
ENGINE = InnoDB
/

CREATE UNIQUE INDEX krew_attr_defn_tc1 ON krew_attr_defn_t (nm, nmspc_cd) 
/


-- -----------------------------------------------------
-- Table krew_typ_attr_t
-- -----------------------------------------------------

CREATE  TABLE krew_typ_attr_t (
  typ_attr_id VARCHAR(40) NOT NULL ,
  seq_no DECIMAL(5,0) NOT NULL ,
  typ_id VARCHAR(40) NOT NULL ,
  attr_defn_id VARCHAR(255) NOT NULL ,
  actv VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (typ_attr_id) ,
  CONSTRAINT krew_typ_attr_fk1
    FOREIGN KEY (attr_defn_id )
    REFERENCES krew_attr_defn_t (attr_defn_id ),
  CONSTRAINT krew_typ_attr_fk2
    FOREIGN KEY (typ_id )
    REFERENCES krew_typ_t (typ_id ))
ENGINE = InnoDB
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
  ppl_flw_mbr_id VARCHAR(40) NOT NULL ,
  ppl_flw_id VARCHAR(40) NOT NULL ,
  mbr_typ_cd VARCHAR(1) NOT NULL ,
  mbr_id VARCHAR(40) NOT NULL ,
  prio DECIMAL(8,0) NULL ,
  dlgt_frm_id VARCHAR(40) NULL ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (ppl_flw_mbr_id) ,
  CONSTRAINT krew_ppl_flw_mbr_fk1
    FOREIGN KEY (ppl_flw_id )
    REFERENCES krew_ppl_flw_t (ppl_flw_id ),
  CONSTRAINT krew_ppl_flw_mbr_fk2
    FOREIGN KEY (dlgt_frm_id )
    REFERENCES krew_ppl_flw_mbr_t (ppl_flw_mbr_id ))
ENGINE = InnoDB
/

CREATE INDEX krew_ppl_flw_mbr_ti1 ON krew_ppl_flw_mbr_t (ppl_flw_id) 
/

CREATE INDEX krew_ppl_flw_mbr_ti2 ON krew_ppl_flw_mbr_t (ppl_flw_id, prio) 
/

CREATE UNIQUE INDEX krew_ppl_flw_mbr_tc1 ON krew_ppl_flw_mbr_t (ppl_flw_id, mbr_typ_cd, mbr_id, dlgt_frm_id) 
/

DELIMITER ;
DROP PROCEDURE IF EXISTS p;

DELIMITER //
CREATE PROCEDURE p ()
BEGIN
    DECLARE c INT;

    SELECT COUNT(*) INTO c FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_NAME = 'krew_ppl_flw_mbr_t' AND INDEX_NAME = 'krew_ppl_flw_mbr_fk2';
    IF c=0 THEN
        set @create_index := 'CREATE INDEX krew_ppl_flw_mbr_fk2 ON krew_ppl_flw_mbr_t (dlgt_frm_id)';
        prepare create_index_stmt from @create_index;
        execute create_index_stmt;
        deallocate prepare create_index_stmt;
    ELSE
        SELECT 'krew_ppl_flw_mbr_fk2, so not running statement to add it.';
    END IF;
END //
DELIMITER ;

CALL p ();

DROP PROCEDURE IF EXISTS p;
DELIMITER /


-- -----------------------------------------------------
-- Table krew_ppl_flw_attr_t
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_attr_t (
  ppl_flw_attr_id VARCHAR(40) NOT NULL ,
  ppl_flw_id VARCHAR(40) NOT NULL ,
  attr_defn_id VARCHAR(40) NOT NULL ,
  attr_val VARCHAR(400) NULL ,
  ver_nbr DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (ppl_flw_attr_id) ,
  CONSTRAINT krew_ppl_flw_attr_fk1
    FOREIGN KEY (ppl_flw_id )
    REFERENCES krew_ppl_flw_t (ppl_flw_id ),
  CONSTRAINT krew_ppl_flw_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krew_attr_defn_t (attr_defn_id ))
ENGINE = InnoDB
/

CREATE INDEX krew_ppl_flw_attr_ti1 ON krew_ppl_flw_attr_t (ppl_flw_id) 
/

CREATE INDEX krew_ppl_flw_attr_ti2 ON krew_ppl_flw_attr_t (attr_defn_id) 
/


-- -----------------------------------------------------
-- Table krew_typ_s
-- -----------------------------------------------------

CREATE  TABLE krew_typ_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/


-- -----------------------------------------------------
-- Table krew_typ_attr_s
-- -----------------------------------------------------

CREATE  TABLE krew_typ_attr_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/


-- -----------------------------------------------------
-- Table krew_attr_defn_s
-- -----------------------------------------------------

CREATE  TABLE krew_attr_defn_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/


-- -----------------------------------------------------
-- Table krew_ppl_flw_s
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/


-- -----------------------------------------------------
-- Table krew_ppl_flw_attr_s
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_attr_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/


-- -----------------------------------------------------
-- Table krew_ppl_flw_mbr_s
-- -----------------------------------------------------

CREATE  TABLE krew_ppl_flw_mbr_s (
  id BIGINT(19) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (id) )
ENGINE = MyISAM
AUTO_INCREMENT = 10000
/
DELIMITER ;
