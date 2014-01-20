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




-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-07-20.sql
-- 



--
-- KULRICE-7439: Assignment of "Add Message to Route Log" permission to the KR-SYS technical administrator is missing from bootstrap dataset
--

delete from krim_role_perm_t where
role_id = (select role_id from krim_role_t where role_nm = 'Technical Administrator' and nmspc_cd = 'KR-SYS') AND
perm_id = (select perm_id from krim_perm_t where nm = 'Add Message to Route Log' and nmspc_cd = 'KUALI')
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-08-29.sql
-- 



--
--     KULRICE-6676 - external message tables
--

--
-- krad_msg_t
--
CREATE TABLE krad_msg_t
(
    nmspc_cd VARCHAR(20) NOT NULL,
    cmpnt_cd VARCHAR(100) NOT NULL,
    msg_key VARCHAR(100) NOT NULL,
    loc VARCHAR(255) NOT NULL,
    obj_id VARCHAR(36) NOT NULL,
    ver_nbr DECIMAL(8) NOT NULL DEFAULT 1,
    msg_desc VARCHAR(255),
    txt VARCHAR(4000),
    PRIMARY KEY (nmspc_cd,cmpnt_cd,msg_key,loc),
    UNIQUE krad_msg_tc0(obj_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-09-13.sql
-- 



--
--     KULRICE-6676 - adding system parameter for default locale
--

INSERT INTO KRCR_PARM_T VALUES ('KR-NS', 'All', 'DEFAULT_LOCALE_CODE', uuid(), 1, 'CONFG', 'en-US',
'The locale code that should be used within the application when otherwise not specified.', 'A', 'KUALI')
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-09-29.sql
-- 



--
--     KULRICE-8349 - guest user access
--

insert into krim_entity_t (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('KR1000', uuid(), 1, 'Y', now())
/

insert into krim_entity_ent_typ_t (ENT_TYP_CD, ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('PERSON', 'KR1000', uuid(), 1, 'Y', now())
/

insert into krim_prncpl_t (PRNCPL_ID, OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT)
values ('guest', uuid(), 1, 'guest', 'KR1000', '', 'Y', now())
/

insert into krim_role_t (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('KR1000', uuid(), 1, 'GuestRole', 'KUALI', 'This role is used for no login guest users.', '1', 'Y', now())
/

insert into krim_role_mbr_t (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('KR1000', 1, uuid(), 'KR1000', 'guest', 'P', null, null, now())
/


-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-11-14.sql
-- 



--
-- KULRICE-8539: Apply SQL for new KRMS tables to the 2.2 master db
--
-- KULRICE-7367: Implement KRMS Type-Type Relations feature
-- KULRICE-7368: Implement KRMS Natural Language Translation feature
-- KULRICE-7369: Implement KRMS Reference Object Bindings feature
--


CREATE TABLE krms_ref_obj_krms_obj_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_typ_reln_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_nl_usage_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_nl_tmpl_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_nl_tmpl_attr_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_nl_usage_attr_s (
  id bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE  TABLE krms_nl_usage_t(
  nl_usage_id VARCHAR(40) NOT NULL ,
  nm VARCHAR(255) NOT NULL,
  nmspc_cd VARCHAR(40)  NOT NULL,
  desc_txt VARCHAR(255) NULL,
  actv VARCHAR(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr DECIMAL(8,0) DEFAULT 0  NOT NULL,
  PRIMARY KEY (nl_usage_id),
  UNIQUE INDEX krms_nl_usage_tc1 (nm ASC, nmspc_cd ASC)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE  TABLE  krms_nl_usage_attr_t (
  nl_usage_attr_id VARCHAR(40)  NOT NULL ,
  nl_usage_id VARCHAR(40)  NOT NULL ,
  attr_defn_id VARCHAR(40)  NOT NULL ,
  attr_val VARCHAR(400) NULL ,
  ver_nbr DECIMAL(8,0) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (nl_usage_attr_id) ,
  UNIQUE INDEX krms_nl_usage_attr_tc1 (nl_usage_id ASC, attr_defn_id ASC),
  CONSTRAINT krms_nl_usage_attr_fk1
    FOREIGN KEY (nl_usage_id )
    REFERENCES krms_nl_usage_t (nl_usage_id ) ,
  CONSTRAINT krms_nl_usage_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE krms_nl_tmpl_t (
  nl_tmpl_id VARCHAR(40) NOT NULL,
  lang_cd VARCHAR(2) NOT NULL,
  nl_usage_id VARCHAR(40) NOT NULL,
  typ_id VARCHAR(40) NOT NULL,
  tmpl VARCHAR(4000) NOT NULL,
  ver_nbr DECIMAL(8,0) DEFAULT 0  NOT NULL,
  CONSTRAINT krms_nl_tmpl_fk1 FOREIGN KEY (nl_usage_id) REFERENCES krms_nl_usage_t (nl_usage_id),
  CONSTRAINT krms_typ_t FOREIGN KEY (typ_id) REFERENCES krms_typ_t (typ_id),
  PRIMARY KEY (nl_tmpl_id),
  UNIQUE INDEX krms_nl_tmpl_tc1 (lang_cd ASC, nl_usage_id ASC, typ_id ASC)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE  TABLE krms_typ_reln_t (
  TYP_RELN_ID VARCHAR(40) NOT NULL ,
  FROM_TYP_ID VARCHAR(40) NOT NULL ,
  TO_TYP_ID VARCHAR(40) NOT NULL ,
  RELN_TYP VARCHAR(40) NOT NULL ,
  SEQ_NO decimal(5,0) NOT NULL,
  VER_NBR decimal(8,0) NOT NULL DEFAULT '0',
  ACTV VARCHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (TYP_RELN_ID) ,
  UNIQUE INDEX KRMS_TYP_RELN_TC1 (FROM_TYP_ID ASC, TO_TYP_ID ASC, RELN_TYP ASC) ,
  CONSTRAINT KRMS_TYP_RELN_FK1 FOREIGN KEY (FROM_TYP_ID ) REFERENCES krms_typ_t (TYP_ID ),
  CONSTRAINT KRMS_TYP_RELN_FK2 FOREIGN KEY (TO_TYP_ID ) REFERENCES krms_typ_t (TYP_ID )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE  TABLE krms_ref_obj_krms_obj_t(
  ref_obj_krms_obj_id VARCHAR(40) NOT NULL,
  collection_nm VARCHAR(40) NULL,
  krms_obj_id VARCHAR(40) NOT NULL,
  krms_dscr_typ VARCHAR(40) NOT NULL,
  ref_obj_id VARCHAR(255) NOT NULL,
  ref_dscr_typ VARCHAR(255) NOT NULL,
  nmspc_cd VARCHAR(40)  NOT NULL,
  actv VARCHAR(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr DECIMAL(8,0) DEFAULT 0  NOT NULL,
  PRIMARY KEY (ref_obj_krms_obj_id),
  UNIQUE INDEX krms_ref_obj_krms_obj_tc1 (collection_nm ASC, krms_obj_id ASC, krms_dscr_typ ASC, ref_obj_id ASC, ref_dscr_typ ASC, nmspc_cd ASC)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE  TABLE  krms_nl_tmpl_attr_t (
  nl_tmpl_attr_id VARCHAR(40)  NOT NULL ,
  nl_tmpl_id VARCHAR(40)  NOT NULL ,
  attr_defn_id VARCHAR(40)  NOT NULL ,
  attr_val VARCHAR(400) NULL ,
  ver_nbr DECIMAL(8,0) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (nl_tmpl_attr_id) ,
  UNIQUE INDEX krms_nl_tmpl_attr_tc1 (nl_tmpl_id ASC, attr_defn_id ASC),
  CONSTRAINT krms_nl_tmpl_attr_fk1
    FOREIGN KEY (nl_tmpl_id )
    REFERENCES krms_nl_tmpl_t (nl_tmpl_id ) ,
  CONSTRAINT krms_nl_tmpl_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/


-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- mysql-2012-11-28.sql
-- 


INSERT INTO KRCR_PARM_T VALUES ('KR-NS', 'All', 'OLTP_LOCKOUT_DEFAULT_MESSAGE', uuid(), 1, 'CONFG', 'The module you are attempting to access has been locked for maintenance.', 'Default message to display when a module is locked', 'A', 'KUALI')
/
DELIMITER ;
