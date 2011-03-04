-- 
-- SQL script to patch a release 1.0 DB to release 1.0.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--
-- Includes JIRA Fixes:
--   KRACOEUS-1726 6-AUG-2008 Tyler Wilson
--

-- Table scripts
-- KRACOEUS-1726
ALTER TABLE BUDGET_SUB_AWARDS MODIFY (XFD_UPDATE_USER VARCHAR2(60));
ALTER TABLE BUDGET_SUB_AWARDS MODIFY (XML_UPDATE_USER VARCHAR2(60));
ALTER TABLE SCHOOL_CODE 
ADD (    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE 
   Person 
MODIFY 
   ( 
   user_name varchar2(10) not null);
   
-- Add Active Flag to Valid Rate Types.
alter table VALID_CE_RATE_TYPES add ACTIVE_FLAG varchar2(1) DEFAULT 'Y' NOT NULL;

create table chlength as select deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id from deadline_type;
delete from deadline_type;
alter table deadline_type modify deadline_type_code char(1);
insert into deadline_type (deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id)
    select deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id from chlength;
drop table chlength;
commit;
