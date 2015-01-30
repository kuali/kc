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




-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-07-20.sql
-- 



--
-- KULRICE-7439: Assignment of "Add Message to Route Log" permission to the KR-SYS technical administrator is missing from bootstrap dataset
--

delete from krim_role_perm_t where
role_id = (select role_id from krim_role_t where role_nm = 'Technical Administrator' and nmspc_cd = 'KR-SYS') AND
perm_id = (select perm_id from krim_perm_t where nm = 'Add Message to Route Log' and nmspc_cd = 'KUALI')
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-08-29.sql
-- 


--
--     KULRICE-6676 - external message tables
--

-------------------------------------------------------------------------
-- krad_msg_t
-------------------------------------------------------------------------
CREATE TABLE krad_msg_t
(
    nmspc_cd VARCHAR2(20) NOT NULL,
    cmpnt_cd VARCHAR2(100) NOT NULL,
    msg_key VARCHAR2(100) NOT NULL,
    loc VARCHAR2(255) NOT NULL,
    obj_id VARCHAR2(36) NOT NULL,
    ver_nbr DECIMAL(8) DEFAULT 1 NOT NULL,
    msg_desc VARCHAR2(255),
    txt VARCHAR2(4000)
)
/

ALTER TABLE krad_msg_t
    ADD CONSTRAINT krad_msg_tC1
PRIMARY KEY (nmspc_cd,cmpnt_cd,msg_key,loc)
/

ALTER TABLE krad_msg_t
    ADD CONSTRAINT krad_msg_tC2
UNIQUE (obj_id)
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-09-13.sql
-- 



--
--     KULRICE-6676 - adding system parameter for default locale
--

INSERT INTO KRCR_PARM_T VALUES ('KR-NS', 'All', 'DEFAULT_LOCALE_CODE', sys_guid(), 1, 'CONFG', 'en-US',
'The locale code that should be used within the application when otherwise not specified.', 'A', 'KUALI')
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-09-29.sql
-- 


--
--     KULRICE-8349 - guest user access
--

insert into krim_entity_t (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('KR1000', sys_guid(), 1, 'Y', sysdate)
/

insert into krim_entity_ent_typ_t (ENT_TYP_CD, ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('PERSON', 'KR1000', sys_guid(), 1, 'Y', sysdate)
/

insert into krim_prncpl_t (PRNCPL_ID, OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT)
values ('guest', sys_guid(), 1, 'guest', 'KR1000', '', 'Y', sysdate)
/

insert into krim_role_t (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('KR1000', sys_guid(), 1, 'GuestRole', 'KUALI', 'This role is used for no login guest users.', '1', 'Y', sysdate)
/

insert into krim_role_mbr_t (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('KR1000', 1, sys_guid(), 'KR1000', 'guest', 'P', null, null, sysdate)
/



-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-11-14.sql
-- 



--
-- KULRICE-8539: Apply SQL for new KRMS tables to the 2.2 master db
--
-- KULRICE-7367: Implement KRMS Type-Type Relations feature
-- KULRICE-7368: Implement KRMS Natural Language Translation feature
-- KULRICE-7369: Implement KRMS Reference Object Bindings feature
--


create sequence krms_ref_obj_krms_obj_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/
create sequence krms_typ_reln_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/
create sequence krms_nl_usage_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/
create sequence krms_nl_tmpl_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/
create sequence krms_nl_tmpl_attr_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/
create sequence krms_nl_usage_attr_s increment by 1 start with 10000 nomaxvalue nocycle nocache order
/

CREATE  TABLE krms_nl_usage_t(
  nl_usage_id VARCHAR2(40) NOT NULL ,
  nm VARCHAR2(255) NOT NULL,
  nmspc_cd VARCHAR2(40)  NOT NULL,
  desc_txt VARCHAR2(255) NULL,
  actv VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  ver_nbr NUMBER(8) DEFAULT 0 NOT NULL,
  PRIMARY KEY (nl_usage_id),
  CONSTRAINT krms_nl_usage_tc1 UNIQUE (nm, nmspc_cd)
)
/
CREATE  TABLE  krms_nl_usage_attr_t (
  nl_usage_attr_id VARCHAR2(40)  NOT NULL ,
  nl_usage_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (nl_usage_attr_id) ,
  CONSTRAINT krms_nl_usage_attr_tc1 UNIQUE (nl_usage_id, attr_defn_id),
  CONSTRAINT krms_nl_usage_attr_fk1
    FOREIGN KEY (nl_usage_id )
    REFERENCES krms_nl_usage_t (nl_usage_id ) ,
  CONSTRAINT krms_nl_usage_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id )
)
/
CREATE TABLE krms_nl_tmpl_t (
  nl_tmpl_id VARCHAR2(40) NOT NULL,
  lang_cd VARCHAR2(2) NOT NULL,
  nl_usage_id VARCHAR2(40) NOT NULL,
  typ_id VARCHAR2(40) NOT NULL,
  tmpl VARCHAR2(4000) NOT NULL,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL,
  CONSTRAINT krms_nl_tmpl_fk1 FOREIGN KEY (nl_usage_id) REFERENCES krms_nl_usage_t (nl_usage_id),
  CONSTRAINT krms_typ_t FOREIGN KEY (typ_id) REFERENCES krms_typ_t (typ_id),
  PRIMARY KEY (nl_tmpl_id),
  CONSTRAINT krms_nl_tmpl_tc1 UNIQUE (lang_cd, nl_usage_id, typ_id)
)
/
CREATE  TABLE krms_typ_reln_t (
  TYP_RELN_ID VARCHAR2(40) NOT NULL ,
  FROM_TYP_ID VARCHAR2(40) NOT NULL ,
  TO_TYP_ID VARCHAR2(40) NOT NULL ,
  RELN_TYP VARCHAR2(40) NOT NULL ,
  SEQ_NO NUMBER(5) NOT NULL,
  VER_NBR NUMBER(8) DEFAULT '0' NOT NULL,
  ACTV VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  PRIMARY KEY (TYP_RELN_ID) ,
  CONSTRAINT KRMS_TYP_RELN_TC1 UNIQUE (FROM_TYP_ID, TO_TYP_ID, RELN_TYP) ,
  CONSTRAINT KRMS_TYP_RELN_FK1 FOREIGN KEY (FROM_TYP_ID ) REFERENCES krms_typ_t (TYP_ID ),
  CONSTRAINT KRMS_TYP_RELN_FK2 FOREIGN KEY (TO_TYP_ID ) REFERENCES krms_typ_t (TYP_ID )
)
/
CREATE  TABLE krms_ref_obj_krms_obj_t(
  ref_obj_krms_obj_id VARCHAR2(40) NOT NULL,
  collection_nm VARCHAR2(40) NULL,
  krms_obj_id VARCHAR2(40) NOT NULL,
  krms_dscr_typ VARCHAR2(40) NOT NULL,
  ref_obj_id VARCHAR2(255) NOT NULL,
  ref_dscr_typ VARCHAR2(255) NOT NULL,
  nmspc_cd VARCHAR2(40)  NOT NULL,
  actv VARCHAR2(1) DEFAULT 'Y'  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL,
  PRIMARY KEY (ref_obj_krms_obj_id),
  CONSTRAINT krms_ref_obj_krms_obj_tc1 UNIQUE (collection_nm, krms_obj_id, krms_dscr_typ, ref_obj_id, ref_dscr_typ, nmspc_cd)
)
/
CREATE  TABLE  krms_nl_tmpl_attr_t (
  nl_tmpl_attr_id VARCHAR2(40)  NOT NULL ,
  nl_tmpl_id VARCHAR2(40)  NOT NULL ,
  attr_defn_id VARCHAR2(40)  NOT NULL ,
  attr_val VARCHAR2(400) NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (nl_tmpl_attr_id) ,
  CONSTRAINT krms_nl_tmpl_attr_tc1 UNIQUE (nl_tmpl_id, attr_defn_id),
  CONSTRAINT krms_nl_tmpl_attr_fk1
    FOREIGN KEY (nl_tmpl_id )
    REFERENCES krms_nl_tmpl_t (nl_tmpl_id ) ,
  CONSTRAINT krms_nl_tmpl_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES krms_attr_defn_t (attr_defn_id )
)
/


-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- 2012-11-28.sql
-- 


INSERT INTO KRCR_PARM_T VALUES ('KR-NS', 'All', 'OLTP_LOCKOUT_DEFAULT_MESSAGE', sys_guid(), 1, 'CONFG', 'The module you are attempting to access has been locked for maintenance.', 'Default message to display when a module is locked', 'A', 'KUALI')
/
