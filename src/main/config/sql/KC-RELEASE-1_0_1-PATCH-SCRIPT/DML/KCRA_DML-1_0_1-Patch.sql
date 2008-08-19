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
