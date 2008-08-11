-- *****************
--
-- SQL script to patch a release 1.0 DB to release 1.0.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--
-- Includes JIRA Fixes:
--   KRACOEUS-1757 / KRACOEUS 1755 7-AUG-2008 Tyler Wilson
--      - Changed sh_parm_txt from 1 to 7 (TEMPORARY EMPLOYEE to REGULAR EMPLOYEE)
--   KRACOEUS-1753 11-AUG-2008 Tyler Wilson
--      - Inserting roles permissions relationship into KIM
--
-- *****************

-- KRACOEUS-1757 / KRACOEUS-1755
UPDATE sh_parm_t
SET sh_parm_txt = '7'
WHERE sh_parm_nm = 'budgetPersonDefaultAppointmentType';

-- KRACOEUS-1753
INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 5, 'Y');
INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 7, 'Y');
