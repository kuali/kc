-- *****************
--
-- SQL script to patch a release 1.0 DB to release 1.0.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--
-- Includes JIRA Fixes:
--   KRACOEUS-1757 / KRACOEUS 1755 7-AUG-2008 Tyler Wilson
--      - Change sh_parm_txt from 1 to 7 (TEMPORARY EMPLOYEE to REGULAR EMPLOYEE)
--   KRACOEUS-1753 11-AUG-2008 Tyler Wilson
--      - Insert roles permissions relationship into KIM
--   KRACOEUS-1803 14-AUG-2008 Tyler Wilson
--      - Remove Unit 000000 per KRACOEUS-827 change to Unit Hierarchy.
--   KRACOEUS-1813 19-AUG-2008 Tyler Wilson
--      - Add 'budgetPersonDefaultPeriodType' to the 'sh_parm_t' table.
--   KRACOEUS-1817 25-AUG-2008 Tyler Wilson
--      - Add 'POST SUBMISSION STATUS CODE' to fp_doc_type_t table.
--   KRACOEUS-1748 25-AUG-2008 Tyler Wilson
--      - Change Active_Ind from '1' to 'Y'
--   KRACOEUS-1832 28-AUG-2008 Tyler Wilson
--      - Add cron job parameters
--   KRACOEUS-2021 15-OCT-2008 Tyler Wilson
--      - Add parameters for F&A processing.
--
-- *****************

-- KRACOEUS-1757 / KRACOEUS-1755
UPDATE sh_parm_t
SET sh_parm_txt = '7'
WHERE sh_parm_nm = 'budgetPersonDefaultAppointmentType';

-- KRACOEUS-1753
INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 5, 'Y');
INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 7, 'Y');

-- KRACOEUS-1803
UPDATE unit SET parent_unit_number = null WHERE unit_number = '000001';
DELETE FROM unit WHERE unit_number = '000000';

-- KRACOEUS-1813
INSERT
INTO sh_parm_t(sh_parm_nmspc_cd,    sh_parm_dtl_typ_cd,    sh_parm_nm,    sh_parm_typ_cd,    sh_parm_txt,    sh_parm_desc,    sh_parm_cons_cd,    active_ind)
VALUES('KRA-B',    'D',    'budgetPersonDefaultPeriodType',    'CONFG',    '3',    'Default Period Type',    'A',    'Y');

-- KRACOEUS-1817
INSERT
INTO fp_doc_type_t(fdoc_typ_cd,    ver_nbr,    fdoc_grp_cd,    fdoc_nm,    fin_elim_elgbl_cd,    fdoc_typ_active_cd,    fdoc_rtng_rule_cd,    fdoc_autoaprv_days,    fdoc_balanced_cd,    trn_scrbbr_ofst_gen_ind)
VALUES('POSS',    1,    'KR',    'POST SUBMISSION STATUS CODE',    'N',    'Y',    'N',    0,    'N',    'N');

-- KRACOEUS-1748
update SH_PARM_TYP_T set ACTIVE_IND = 'Y' where ACTIVE_IND = '1';

-- KRACOEUS-1832
-- System parameters for controlling the deletion of pessimistic locks.
-- The CRON expression controls when the Quartz timer will trigger.
-- The expiration timeout (in minutes) determines which locks will be deleted.
-- Locks that are older than the expiration timeout value will be deleted.
 
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 * * ?','The Cron Expression for Quartz to activate a clearing of old locks','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','pessimisticLocking.timeout','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A','Y');

-- New Flags for budget cost sharing and unrecovered f and a allocation enforcement

INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetCostSharingEnforcementFlag', '205246DF7F364A3083357960305030C5',1, 'CONFG', 'Y', 'Flag indicating if Cost Sharing allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');

INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetUnrecoveredFandAEnforcementFlag', 'C9E4C6277E234C45BF0E1D041B2491CF',1, 'CONFG', 'Y', 'Flag indicating if Unrecovered F and A allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');

-- Adding proposal Submission statuses 
INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('6', 'Deactivated', 'not selected for funding',SYSDATE,'KRADEV');
INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('7', 'Helds for Funds Availability', 'was not awarded as sponsor ran out of funds', SYSDATE, 'KRADEV');
INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('8', 'Void', 'Used to mark unwanted,incorrect reports', SYSDATE, 'KRADEV');

-- Adding new Abstract Types for grants.gov compliance.
insert into abstract_type (abstract_type_code,description,update_timestamp,update_user) values('16','Areas Affected',sysdate,'kradev');
insert into abstract_type (abstract_type_code,description,update_timestamp,update_user) values('17','Relevance',sysdate,'kradev');

